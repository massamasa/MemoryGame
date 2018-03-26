# Memory Game
## The purpose of this application
This application is a simple yet speedy memory game the user can use to practice their working memory. The objective of the game is for the player to find pairs from a large table of cards as fast as they can while avoiding penalties.

## Users
There is only one type of user, a normal user, the player.

## Basic Functionality
### Game stage
- The width and height dimensions of the table are the same for the sake of universal scoring e.g. 4x4 cards or 10x10 cards.
  - The table's dimensions are even numbers.
- The score of the game is defined by the timer displayed in the corner of the stage.
- The player can choose to display all cards. This will speed up the timer as a penalty.
- The objective is to connect two identical cards by clicking on two cards of the same kind with two consecutive clicks.
  - If both cards are the same kind, they are marked as found.
  - Timer penalty for each time the player tries to connect unidentical cards that have already been chosen.
## Proposed Expanded Functionality
- Showing all cards face up at the start of the game
  - The cards are shown at the start of the game for a split second per card on stage.
### Graphical main menu with the following options
  - Play game
  - How to play page with instructions and tips for playing the game.
  - A settings submenu where the player can adjust settings such as the size of the table and the nickname in use.
  - Display Scoreboard
  - Exit
### Scoreboard (accessible from the main menu)
  - Subsections for each table size
  - Best cards per second scoreboard.
### The player may choose to abandon the game. 
  - An abandoned game will be scored, but there will be a large penalty calculated dependent on the cards left.
  - Upon finishing or abandoning the game, the player is prompted for a nickname for the scoreboard. By default the nickname is set to the previous used nickname or the nickname set in the settings submenu.	
### Graphical statistics connected to each nickname
  - Possible to connect or combine nicknames with one another. If someone happens to have used two nicknames.
### Modes where cards are corresponding but not identical. 
  - Can be used for educational purposes.
  - The timer may function differently
  - Examples
    - Countries and their capitals
    - Countries and their flags
    - The periodic table. Elements and their names
    - Connection to facebook, so the player can connect their friends' names and profile pictures?
      - Option to include or exclude some friends
