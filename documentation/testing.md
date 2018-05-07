# Testing document

The application has been tested via JUnit tests and manual testing.

## Unit and integration testing

### Program logic
The domain package forms most of the need for testing. 
The class [domain.GameBoard](https://github.com/massamasa/otm-harjoitustyo/blob/master/MemoryGame/src/main/java/domain/GameBoard.java) was particularly important to test as it contains the most integral logic necessary to play the game.

Integration tests between different packages are mostly focused on tests for the [domain.DataLogic](https://github.com/massamasa/otm-harjoitustyo/blob/master/MemoryGame/src/main/java/domain/DataLogic.java) class and [dao.HighScoreDao](https://github.com/massamasa/otm-harjoitustyo/blob/master/MemoryGame/src/main/java/dao/HighScoreDao.java).
They have been placed in the [dao.HighScoreDaoTest](https://github.com/massamasa/otm-harjoitustyo/blob/master/MemoryGame/src/test/java/dao/HighScoreDaoTest.java) test class.

Program Logic classes are mostly tested through their use in [domain.GameBoard](https://github.com/massamasa/otm-harjoitustyo/blob/master/MemoryGame/src/main/java/domain/GameBoard.java). 
[ScoreTest](https://github.com/massamasa/otm-harjoitustyo/blob/master/MemoryGame/src/test/java/domain/ScoreTest.java) test class was added to make sure the length of the nickname could be both under 8 characters and over, but would be cut to 8 if longer.

### DAO Classes
[dao.CardNamesArray](https://github.com/massamasa/otm-harjoitustyo/blob/master/MemoryGame/src/main/java/dao/CardNameArrays.java) is tested through [domain.GameBoardTest](https://github.com/massamasa/otm-harjoitustyo/blob/master/MemoryGame/src/test/java/domain/GameBoardTest.java) because there is little to test besides the possiblity of an IndexOutOfBoundsException.
[dao.HighScoreDao](https://github.com/massamasa/otm-harjoitustyo/blob/master/MemoryGame/src/main/java/dao/HighScoreDao.java) has a dedicated test class, [dao.HighScoreDaoTest](https://github.com/massamasa/otm-harjoitustyo/blob/master/MemoryGame/src/test/java/dao/HighScoreDaoTest.java) that uses a mock file that it deletes and recreates at the start of each test.

## Test coverage
Not including the user interface package, the test coverage of the application is 98% for instructions and 97% for branches.

![test coverage](https://github.com/massamasa/otm-harjoitustyo/blob/master/documentation/imagesfortestingdocument/testcoverage.JPG)

Some getters were left untested because their testing was found trivial.

## System testing
System testing has been tested manually for Windows 10 and Linux operating systems.

### Installation and configuration
The application has been downloaded, run and tested according to the user manual in Windows 10 and Linux environments.
The application has been tested without preceding database files present as the application can generate them itself.

### Functions
All functions listed by the user manual have been tested manually and different inputs have been tested both manually and through JUnit tests.
