
![InGamePicture](http://gdurl.com/wkrP)

# Rosjam

Rosjam is a 2D top-down video game, classified as roguelike. The aim of the game is to fight against different opponents controlled by the AI algorithms.
The algorithm used to control each enemies are the following:
* RandomWalker
* A* Algorithm
* NEAT (Neuroevolution of Augmented Topologies)


![InGamePicture](http://gdurl.com/C59W)


This is my dissertation project for Aberystwyth University. This code has been developed in four months using a agile methodology.

### How to play
#### Desktop
They are two options to use as a controller on desktop versions.

##### Mouse and Keyboard
The following keys are used:

* ESCAPE: Pause the game

WASD:
* W: Move the player up
* A: Move the player left
* S: Move the player down
* D: Move the player right

Arrow keys:
* UP: Shot a bullet upwards
* DOWN: Shot a bullet downwards
* LEFT: Shot a bullet to the left
* RIGHT: Shot a bullet to the right

##### GamePad
To control the game with a supported game (Only a xBox controller) insert the game pad before the game is started. Once the gamepad is installed, run the game. Notice that the game pad is only used for the actual gameplay, it can't be use for the any part of the game. Use the mouse as a input to navigate throw different states in game.



#### Android
Uses the virtual joysticks in screen to control the player and the to shot in a direction. They are two virtual joysticks:
 * Left Joystick: Control the player
 * Right Joystick: Shoot and control the shooting direction.

### Get the official release
The official release version can be obtained on the official website.

https://jotask.github.io/Rosjam/

### Running the game
To run the game on desktop double click the release version. Or run the following command:

```
java -jar <jar-file-name>.jar
```

To run the android version download the APK official release and installed on any android device.
The .apk file can be used to install on devices directly such as via an email attachment or download. This requires the devices to have the 'Allow Unknown Sources' option enabled in settings. Some carriers unfortunately disable this setting.


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
