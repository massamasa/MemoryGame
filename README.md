# Memory Game
A practice programming project for the course Ohjelmistotekniikan menetelmät at the University Of Helsinki. The program is a memory game with the objective to click "cards" (buttons) to flip them and find a match in the fastest time. High Scores are stored in a database. Hold the S-key to show all cards and to speed up the timer to triple speed as a penalty. Try to be accurate as well. If you make a mistake clicking a pair of cards that have already been checked, you will face a one second penalty each time.
## Documentation

[User manual (käyttöohje)](https://github.com/massamasa/otm-harjoitustyo/blob/master/documentation/usermanual.md)


[Requirements document (määrittelydokumentti)](https://github.com/massamasa/otm-harjoitustyo/blob/master/documentation/requirementsdocument.md)

[Record of working hours (työaikakirjanpito)](https://github.com/massamasa/otm-harjoitustyo/blob/master/documentation/workhoursrecord.md)

[Architecture document (arkkitehtuuri)](https://github.com/massamasa/otm-harjoitustyo/blob/master/documentation/architecturedocument.md)

## Command line functions

### Testing
Tests can be run with command
```
mvn test
```
A test coverage report is created with command
```
mvn jacoco:report
```
### Generating executable jar 
An executable .jar file can be generated with command
```
mvn package
```
### Checkstyle
Checkstyle tests defined in file [checkstyle.xml](https://github.com/massamasa/otm-harjoitustyo/blob/master/MemoryGame/checkstyle.xml) can be performed with the command
```
mvn jxr:jxr checkstyle:checkstyle
```
Any error messages are found in the file at target/site/checkstyle.html


### Javadoc
Javadoc is generated with command
```
mvn javadoc:javadoc
```
Javadoc can be viewed by using a web browser to open file at target/site/apidocs/index.html

## Releases
- [v1.1](https://github.com/massamasa/otm-harjoitustyo/releases/tag/v1.1)
- [v1.0](https://github.com/massamasa/otm-harjoitustyo/releases/tag/v1.0)
