## Arknoid Game
Welcome to the Arknoid Game, a Java-based recreation of the classic brick-breaking arcade game. This game offers players a fun challenge of controlling a paddle to break bricks across four exciting levels; The player loses when all the balls fall off the screen.

---
### Table of Contents
- Project Overview
- Features
- Requirements
- Installation
- Running the Game
- Controls
- Additional Information
  
---
### Project Overview
This game is built in Java and follows the traditional Arknoid mechanics. The player controls a paddle at the bottom of the screen, bouncing a ball to break the bricks at the top. The game is designed using object-oriented principles, making the codebase modular and easy to extend.

The game uses an external library, biuoop-1.4.jar, to handle the graphical user interface (GUI) and user input.

---
### Game Mechanics
The player controls the paddle using the keyboard to keep the ball in play.
The ball bounces off the paddle and bricks. When it hits a brick, the brick is destroyed, and the player scores points.
The game progresses through four levels, each with unique brick arrangements and increasing difficulty.
The game ends when all the balls are lost.

---
### Features
- **Four Levels:** The game has four different levels with increasing difficulty and unique brick layouts.
- **Scoring System:** Players earn points by breaking bricks.
- **Lives System:** The player starts with a limited number of balls (lives). When all balls are lost, the game ends.

---
### Requirements
To run the Arknoid game, you need the following:

- **Java Runtime Environment (JRE) version 8 or later:** Required to run the game. You can download it from Oracle's official site.
- **Download the game:** Download or clone the repository containing the Arknoid game.
  You should have the following files:

  - **dist/arknoid-game.jar:** The main JAR file for the game.
  - **biuoop-1.4.jar:** The external library required to run the game’s graphical interface.

---
### Running the Game:
To run the game, use the following command:

- **For Windows (PowerShell or Command Prompt):**
```powershell
java -cp dist\arknoid-game.jar;.\biuoop-1.4.jar Game
```
- **For Linux/macOS (Terminal):**
```bash
java -cp dist/arknoid-game.jar:./biuoop-1.4.jar Game
```
This command sets the classpath to include both the main game JAR file and the biuoop-1.4.jar library, and it runs the game starting with the Game class.

---
### Controls
- **Move Left:** Use the left arrow key (←) to move the paddle to the left.
- **Move Right:** Use the right arrow key (→) to move the paddle to the right.
- **Start/Restart:** The game starts automatically when you run it. To restart, close and rerun the game.
  
### Additional Information
#### Project Structure
The Arknoid game project is organized into various packages, each responsible for different components of the game. Below is an overview of the directory structure and the role of each package:

```bash
├── bin/                      # Compiled .class files (generated after build)
├── dist/                     # Contains the final JAR file (arknoid-game.jar)
├── src/                      # Source directory containing all the Java files
│   ├── Animation/            # Handles animations and game flow
│   │   ├── AnimationRunner.java
│   │   ├── CountdownAnimation.java
│   │   ├── EndScreen.java
│   │   ├── KeyPressStoppableAnimation.java
│   │   └── PauseScreen.java
│   ├── Collision/            # Handles collisions and related information
│   │   └── CollisionInfo.java
│   ├── GameCollections/      # Manages game entities and objects
│   │   ├── GameEnvironment.java
│   │   └── SpriteCollection.java
│   ├── GameFlow/             # Contains classes to manage game levels and flow
│   │   ├── DirectHit.java
│   │   ├── FinalHour.java
│   │   ├── GameFlow.java
│   │   ├── GameLevel.java
│   │   ├── Green3.java
│   │   └── WideEasy.java
│   ├── GameInterfaces/       # Interfaces for core game functionalities
│   │   ├── Animation.java
│   │   ├── Collidable.java
│   │   ├── HitListener.java
│   │   ├── HitNotifier.java
│   │   ├── LevelInformation.java
│   │   └── Sprite.java
│   ├── GameObjects/          # Contains game object classes like Ball, Block, and Paddle
│   │   └── Features/
│   │       ├── Ball.java
│   │       ├── Block.java
│   │       └── Paddle.java
│   ├── Geometrics/           # Handles geometric shapes (Point, Line, Rectangle)
│   │   ├── Line.java
│   │   ├── Point.java
│   │   └── Rectangle.java
│   ├── Helpers/              # Utility classes used throughout the game
│   │   └── StringToInts.java
│   ├── Removers/             # Classes responsible for removing game objects
│   │   ├── BallRemover.java
│   │   └── BlockRemover.java
│   ├── Tests/                # Test classes for validating functionality
│   │   └── PrintingHitListener.java
│   ├── Trackers/             # Manages tracking of scores, counters, and other game stats
│   │   ├── Base/
│   │   │   ├── Counter.java
│   │   │   ├── ScoreIndicator.java
│   │   │   └── ScoreTrackingListener.java
│   └── Game.java             # Main class for running the game
├── biuoop-1.4.jar            # External library for GUI and input handling
├── build.xml                 # Ant build file for compiling and packaging the project
└── README.md                 # Project README file
```
---
#### Key Components
- **Animation:** This package handles the game's animations, including running the game, displaying countdowns, pausing the game, and managing keypress events.

- **Collision:** Manages collision detection and response. CollisionInfo.java provides details on the point of collision and the object involved.

- **GameCollections:** Contains collections of game objects. GameEnvironment.java holds the environment where game objects interact, while SpriteCollection.java handles the grouping sprites (2D images, which will collectively create the game enviornment).

- **GameFlow:** Responsible for managing the flow between different game levels. Each class (e.g., DirectHit.java, GameLevel.java) represents a level or part of the game's progression.

- **GameInterfaces:** Defines core interfaces for the game. These include Collidable, Sprite, HitListener, and more, ensuring that all game objects adhere to a common structure.

- **GameObjects:** Contains game entities like Ball, Block, and Paddle. These are the physical objects that players interact with during gameplay.

- **Geometrics:** Handles geometric shapes used in the game, such as Line, Point, and Rectangle. These are critical for calculating movement and collision detection.

- **Helpers:** Utility classes to assist with specific tasks. For example, StringToInts.java converts string inputs into integers for processing.

- **Removers:** Contains classes (BallRemover.java, BlockRemover.java) that remove game objects when certain conditions are met, such as when a ball hits a block.

- **Tests:** Houses test-related classes to help debug and validate game functionality, like PrintingHitListener.java which prints collision events for debugging.

- **Trackers:** Manages tracking of various game metrics, such as the player’s score and the number of remaining blocks.

- **Main Class (Game.java):** The entry point for the game. This class initializes and starts the game.

This structure shows the modular nature of the Arknoid game, making it easy to manage and extend different components such as game objects, animations, and collision handling.


