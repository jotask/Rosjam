
![InGamePicture](http://gdurl.com/wkrP)

# Rosjam

Rosjam is a 2D top-down video game, classified as roguelike. The aim of the game is to fight against different opponents controlled by the AI algorithms.
The algorithm used to control each enemies are the following:
* RandomWalker
* A* Algorithm
* NEAT (Neuroevolution of Augmented Topologies)


![InGamePicture](http://gdurl.com/C59W)


This is my dissertation project for Aberystwyth University. This code has been developed in four months using a agile methodology.

### Get the official release
The runnable version can be obtained on the official website.

https://jotask.github.io/Rosjam/

### Running the game
Say what the step will be

```
java -jar <jar-file-name>.jar
```

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Installing

The followings steps show you how to create a project using gradle with Intellij IDEA.
1. Download or clone this repository.
2. Import this repository using gradle by selection the build.gradle file.
3. Press ok.
4. A final window will appear asking which modules to import, leave everything checked and click ok.

## Deployment

Every platform has a different kind of distribution format. To deploy the code use the following commands on the command line:

For desktop environments:
```
gradlew desktop:dist
```

This will create a runnable JAR file located in the desktop/build/libs/ folder.

For android environments:
```
gradlew android:assembleRelease
```

This will create an unsigned APK file in the android/build/outputs/apk folder. 

## Built With

* [Gradle](https://gradle.org/) - Dependency Management
* [libgdx](https://libgdx.badlogicgames.com/) - The framework used

## Author

* **Jose Vives Iznardo**

## License

This project is licensed under the Apache License 2.0 License - see the [LICENSE.md](LICENSE.md) file for details
