# Description of architecture

## Structure
The program follows a level architecture with three levels. The package structure is as follows

![Package architecture](https://github.com/massamasa/otm-harjoitustyo/blob/master/documentation/imagesforarchitecturedocumentation/packagearchitecture.png)

The package MemoryGame.ui contains an Ui created with JavaFX, MemoryGame.domain contains the necessary logic classes and MemoryGame.dao contains classes necassary to save information to the harddrive and retrieve informaiton from the harddrive.

##User Interface

The user interface contains three separate views.
- The start menu
- High Score list
- The game stage

Each of these views have been implemented as their own classes and Scenes. Only one is connected to the program's stage at one time. The ui.Gui class works as a launcher for Scene classes.

## Program Logic

The MemoryGame.domain package contains classes necessary to run the Menus and Game Stage. The GameBoard class is linked to number of cards that is the square of the dimension variable.

![datamodel](https://github.com/massamasa/otm-harjoitustyo/blob/master/documentation/imagesforarchitecturedocumentation/datamodel.png)

The GameBoard class provides logical function for the game stage. Examples of methods:
-getCardCheckedPenalty
-boolean foundAllPairs

MemoryGame.MenuLogic can access high scores contained in sqlite3 files through the dao.HighScoreDao class.

Package and class relations diagram for the MemoryGame program.

![classdiagram](https://github.com/massamasa/otm-harjoitustyo/blob/master/documentation/imagesforarchitecturedocumentation/classdiagram.png)

## Main functionalities

Illustrations of main functionalities with sequence diagrams

### Choosing cards and finding pairs

After the GameStage has beeb initalized clicking on two matching cards consequently proceeds as follows

![Sequence Diagram - Finding Pairs](https://github.com/massamasa/otm-harjoitustyo/blob/master/documentation/imagesforarchitecturedocumentation/sequencediagram1.png)


Clicking on the first card will call method matchingCardInDifferentCoordinate in gameBoard to check if the card was the same as the previous, and as there is no previous card, the method will return false. A routine check to check for a recheck penalty is performed and will return 0 as there was no need for recheck. gameStage performs a check on the FirstMemoryButton to see if it's text value is "X" (found) and returns false because firstMemoryButton is a blank placeholder at this time. The returned boolean false is used for the if statement and the if statement passed. The text of the checked card is set to "4" as the default GameBoard class uses the card's integer as its name. Its color is changed to yellow to signify it has been checked. This first clicked button is saved in firstMemoryButton at the end of the block.


Clicking on the second card will check for an integer matching the previous card's integer with the method matchingCardInDifferentCoordinate and as they are deemed equal, the method returns true. Both the first and second card buttons' text values are set to "X" and color style is changed to green. The found pairs String is updated with a string with the found number: "4, ". Both the first and second buttons' onClickListeners are set to null as they are no longer needed.
