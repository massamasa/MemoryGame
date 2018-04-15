# Description of architecture

## Structure
The program follows a level architecture with three levels. The package structure is as follows

[packagearchitecture.png]

The package MemoryGame.ui contains an Ui created with JavaFX, MemoryGame.domain contains the necessary logic classes and MemoryGame.dao contains classes necassary to save information to the harddrive and retrieve informaiton from the harddrive.

##User Interface

The user interface contains three separate views.
- The start menu
- High Score list
- The game stage

Each of these views have been implemented as their own classes and Scenes. Only one is connected to the program's stage at one time. The ui.Gui class works as a launcher for Scene classes.

## Program Logic

The MemoryGame.domain package contains classes necessary to run the Menus and Game Stage. The GameBoard class is linked to number of cards that is the square of the dimension variable.

[datamodel.png]

The GameBoard class provides logical function for the game stage. Examples of methods:
-getCardCheckedPenalty
-boolean foundAllPairs

MemoryGame.MenuLogic can access high scores contained in sqlite3 files through the dao.HighScoreDao class.

Package and class relations diagram for the MemoryGame program.

[classdiagram.png]