# User Manual
Download the file [Memory.Game.jar](https://github.com/massamasa/otm-harjoitustyo/releases/tag/v1.0)

## Configuration
The application assumes your computer has the [Java Runtime Environment](http://www.oracle.com/technetwork/java/javase/downloads/jre8-downloads-2133155.html) and [Sqlite3 binaries](https://www.sqlite.org/download.html) installed. The application creates a database file HighScores.db in the executable file's root directory in Windows and in the user's home directory in Linux.

## Launching the application
The application is launched with command
```
java -jar Memory.Game.jar
```
## Adjusting settings before a game and navigating the menu
The application displays the start menu at launch. 

The player may adjust the rectangular dimensions of the card table by pressing the - and + buttons.

The game type is set to play with plain integers by default. 
The user can choose to play with country codes or emojis by choosing the option from the dropdown menu.

Highscores can be viewed for a chosen dimension by adjusting the dimension chosen with - and + and clicking HIGH SCORES.

## Playing the game
The game stage is launched by clicking START GAME in the start menu after adjusting settings.

The game is played by clicking on cards to display their face and clicking on a second card to check if it is a match. 
Clicking on a new card will hide the previous card and mark it as touched (yellow) or mark it as found (X and green) if it is a match.

If the player chooses to recheck a card already touched and it is not a match with the previous card, they are assessed a one second timer penalty.
The player can view all cards by holding the S-key, but the timer is also sped up to triple speed as a penalty for the duration held.

Finding all cards ends the game and provides an option to save the score. The player may choose a nickname that will be cut to 8 characters if the nickname is longer than 8 characters.

The score is calculated as follows: Game time + recheck penalty = total time
