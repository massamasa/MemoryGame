# Memory Game
A practice programming project for the course Ohjelmistotekniikan menetelmät at the University Of Helsinki. The program is a memory game with the objective to click "cards" (buttons) to flip them and find a match in the fastest time. High Scores are stored in a database. Click the S-key to show all cards and to speed up the timer to triple speed as a penalty. Try to be accurate as well. If you make a mistake clicking a pair of cards already shown, you will face a one second penalty each time.
## Documentation
[Requirements document (määrittelydokumentti)](https://github.com/massamasa/otm-harjoitustyo/blob/master/documentation/requirementsdocument.md)

[Record of working hours (työaikakirjanpito)](https://github.com/massamasa/otm-harjoitustyo/blob/master/documentation/workhoursrecord.md)

[Architecture document (arkkitehtuuri)](https://github.com/massamasa/otm-harjoitustyo/blob/master/documentation/architecturedocument.md)

## Command line functions

### Testing
Tests can be run with command
> - mvn test

A test coverage report is created with command
> - mvn jacoco:report

### Generating executable jar 
An executable .jar file can be generated with command
> - mvn package
