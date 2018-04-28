# User Manual
Download the file [Memory.Game.jar](https://github.com/massamasa/otm-harjoitustyo/releases/tag/v1.0)

## Configuration
The application creates a database file HighScores.db in the executable file's root directory. 
If there are any issues with saving scores or viewing them, the user may have to delete HighScores.db and run the program again.

## Launching the application
The application is launched with command
```
java -jar Memory.Game.jar
```
## Adjusting settings before a game and navigating the menu
The application displays the start menu at launch. 

The player can choose an alternate 8 letter nickname.
The player may adjust the rectangular dimensions of the card table by pressing the - and + buttons.

The game type is set to play with plain integers by default. 
The user can choose to play with country codes by choosing the option from the dropdown menu.

Highscores can be viewed for a chosen dimension by adjusting the dimension chosen with - and + and clicking HIGH SCORES.

## Playing the game
The game is launched by clicking START GAME in the start menu after adjusting settings.

The game is played by clicking on cards to display their face and clicking on a second card to check if it is a match. 
Clicking on a new card will hide the previous card and mark it as touched (T) or mark it as found (F) if it is a match.

If the player chooses to recheck a card already touched and it is not a match with the previous card, they are assessed a one second timer penalty.
The player can view all cards by holding the S-key, but the timer is also sped up to triple speed as a penalty for the duration held.

Finding all cards ends the game and saves the score. 

The score is calculated as follows: Game time + recheck penalty = total time
