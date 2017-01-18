package com.github.jotask.rosjam.engine.assets;

/**
 * Asset
 *
 * @author Jose Vives Iznardo
 * @since 17/01/2017
 */
abstract class Asset<T> {

    private final Assets assets;

    protected Asset(Assets assets) {
        this.assets = assets;
    }

    abstract void prepare();

}
