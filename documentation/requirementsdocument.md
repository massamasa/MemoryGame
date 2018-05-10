# Memory Game

## The purpose of this application
This application is a simple yet speedy memory game the user can use to practice their working memory. The objective of the game is for the player to find pairs from a large table of cards as fast as they can while avoiding penalties.

## Users
There is only one type of user, a normal user, the player.

## Basic Functionality

### Game stage
- The player may choose to play with one of three different game modes, each differing by how the cards are named. The three sets of names are currently plain integers, country codes and emojis.
- The width and height dimensions of the table are the same for the sake of universal scoring e.g. 4x4 cards or 10x10 cards.
  - The table's dimensions are even numbers.
- The score of the game is defined by the timer displayed in the corner of the stage.
- The player can choose to display all cards. This will speed up the timer as a penalty.
- The objective is to connect two identical cards by clicking on two cards of the same kind with two consecutive clicks.
  - If both cards are the same kind, they are marked as found.
  - Timer penalty for each time the player chooses a card that has already been checked and it does not make a pair with the previous card.
- The game ends when all pairs are found
  - The user is prompted to enter a nickname and given the option to save the score to a database table.
  
### Graphical start menu with the following options
- Play game
- Adjust dimensions
- Choose game mode
- Display highscores
- Exit

### Scoreboard (accessible from the start menu)
  - Subsections for each table size
  - Best cards per second scoreboard.
  
## Proposed Expanded Functionality

### Mode showing all cards face up at the start of the game
  - The cards are shown at the start of the game for a split second per card on stage.
  
### The player may choose to abandon the game. 
  - An abandoned game will be scored, but there will be a large penalty calculated dependent on the cards left.
  
### Graphical statistics connected to each nickname
  - Possible to connect or combine nicknames with one another. If someone happens to have used two nicknames.
  
### Modes where cards are corresponding but not identical.
  - Can be used for educational purposes.
  - The timer may function differently
  - Examples
    - Numbers displayed as words and as numbers (e.g. "one" and 1 for corresponding cards).
    - Countries and their capitals
    - Countries and their flags
    - The periodic table. Elements and their names
