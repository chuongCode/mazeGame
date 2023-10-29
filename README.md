# mazeGame
A fun little maze game utilizing Java Swing and Abstract Window Toolkit. Can you beat it the fastest?

The program consists of 3 classes: mazeMaker, Player, and MazeGame. 


The Player class contains the fields used for changing the coordinates of the player rectangle, as well as the methods that do so by multiplying the x, y coordinates by a certain amount depending on user key input. 


The mazeMaker class provides the fields and methods used to create the walls of the mazes displayed on the screen. 


The MazeGame class is the “main” class used for implementing most of the gameplay and game state. Within it contains fields such as the timer, used to move the player and decrement the score, booleans such as gameWon and gameLost, and rectangle objects used for maze walls. The methods it contains implements the main gameplay functionality: the paintComponent method prints out the maze; the score method decrements the score starting from 5000; the collisionDetection method checks if the player hits a wall and, if true, forces the player to restart the maze; the menu method displays the main menu and precedes the maze screen; the winCheck method checks if the user has hit the “goal” at the end of the maze; the loseCheck method checks if the userScore has hit 0; and the winnerScreen and loserScreen replace the maze screen when win or lose conditions are met.

- The game uses "animation" and contains interactive player-controlled movement
- There is a score displayed during the game and when the user beats the game. The score starts at 5000 and decrements by 100 every second and if you hit a wall- you lose if it hits 0
- You win if you hit the goal at the end of the maze. You lose if score hits 0
- There is collision detection when you hit a wall or hit the end goal
- There are sound effects for flourish: when you hit a wall, when you win, when you lose, and when you start up the game

Our game implements a playSound method utilizing the javax.sound.sampled imports which searches for a file containing the specific .wav file to play a sound. Our project game plays the Family Feud ‘Ding!’ sound on start up (and has a fun little button to just keep playing it). It also has a hit marker sound when colliding with something, plus a victory and loser theme when either happens. The sound files seem to inconsistently work across different devices, but it does for sure work on the computer the sound methods were built on, so if needed, we can record a video showing that it works on our end. The user will have to download the wav files and store them in the project folder in another folder specifically named “SoundFiles” otherwise the sound doesn't work. In the situation where the audio cannot be played, it will enter a catch throw and display an error message to the user.
