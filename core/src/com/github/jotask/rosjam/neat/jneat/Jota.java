package com.github.jotask.rosjam.neat.jneat;

import com.github.jotask.rosjam.neat.Neat;
import com.github.jotask.rosjam.neat.config.Config;
import com.github.jotask.rosjam.neat.jneat.fitness.BasicFitness;
import com.github.jotask.rosjam.neat.jneat.fitness.Fitness;
import com.github.jotask.rosjam.neat.jneat.genetics.Genome;
import com.github.jotask.rosjam.neat.jneat.genetics.Population;
import com.github.jotask.rosjam.neat.jneat.genetics.Specie;
import com.github.jotask.rosjam.neat.jneat.util.Constants;
import com.github.jotask.rosjam.neat.util.Files;
import com.github.jotask.rosjam.neat.util.Timer;

/**
 * Jota
 *
 * @author Jose Vives Iznardo
 * @since 10/03/2017
 */
public class Jota {

    public static final String filename = "jota.properties";

    private static Jota instance;
    public static Jota get(){
        if(Jota.instance == null){
            throw new RuntimeException();
        }
        return instance;
    }

    private final JotaManager manager;

    public final Config config;

    private NeatEnemy best;

    private final Timer timer;
    private Population population;

    private final Fitness fitness;

    private final float INCREASE_GENERATION_TIME;
    private final int EACH_GENERATION;

    public Jota(final Config config) {
        Jota.instance = this;
        this.config = config;
        this.timer = new Timer(new Float(config.get(Config.Property.INIT_TIME)));
        this.manager = new JotaManager();

        this.INCREASE_GENERATION_TIME = new Float(config.get(Config.Property.TIME_INCREASE));
        this.EACH_GENERATION = new Integer(config.get(Config.Property.EACH_GENERATION));

        this.population = Files.load();

        this.fitness = new BasicFitness();

        load();

        this.initializeGame();

    }

    public void eval() {
        for (final NeatEnemy e : this.manager.getActive()) {
            if (e.isDisabled() || e.isDie())
                continue;
            e.evaluateNetwork();
        }
    }

    private void initializeGame() {
        best = null;
        this.manager.clear();
        for (final Specie specie : this.population.getSpecies()) {
            for(final Genome genome: specie.getGenomes()) {
                this.manager.spawn(genome);
            }
        }
        this.manager.moveDisabled();

        if(Constants.SAVE)
            Files.save(this.population);

    }

    public void learn() {

        this.fitness.update();

        NeatEnemy b = this.manager.getActive().getFirst();

        for (final NeatEnemy e : this.manager.getActive()) {

            if (e.isDisabled() || e.isDie())
                continue;

            double fit = this.fitness.evaluate(e);
            fit = fit == 0.0 ? -1.0 : fit;

            e.getGenome().fitness = fit;
            if (fit > this.population.maxFitness)
                this.population.maxFitness = fit;

            if (fit > b.getGenome().fitness)
                b = e;
        }

        if(this.best != b){
            this.setBest(b);
        }

        if (timer.isPassed()) {
            nextGeneration();
        }

    }

    private void nextGeneration(){
        // FIXME
//        this.gui.getFitness().addFitness(this.population.getGeneration(), this.best.getGenome().fitness);
        this.manager.clear();
        this.fitness.reset();
        this.population.newGeneration();
        this.initializeGame();
        Neat.get().getPlayer().respawn();
        if(this.getPop().getGeneration() % EACH_GENERATION == 0){
            this.timer.increase(this.INCREASE_GENERATION_TIME);
        }else {
            this.timer.reset();
        }
    }

    private void setBest(NeatEnemy fp){
        if(this.best != null){
            this.best.isBest = false;
        }
        this.best = fp;
        this.best.isBest = true;
    }

    public NeatEnemy getBest() {
        return best;
    }

    public int getPopulationSize() { return this.population.getSpecies().size(); }

    public JotaManager getManager() { return manager; }

    public Population getPop(){
        return this.population;
    }

    public void dispose(){
        save();
        this.manager.dispose();
        Jota.instance = null;
    }

    public Config getConfig() { return config; }

    public void save(){
        // TODO save
//        FileHandle file = new FileHandle(Jota.filename);
//        OutputStream os = null;
//        try {
//            os = new FileOutputStream(file.file());
//            Properties properties = new Properties();
//            properties.store(os);
//
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }finally {
//            if(os != null){
//                try {
//                    os.close();
//                } catch (IOException e) {
//                    // We can do nothing
//                }
//            }
//        }
    }

    public void load(){
        // TODO load
    }

}
