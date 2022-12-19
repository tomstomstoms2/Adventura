# My Adventura

My Adventura Java based Semestral Project for VŠE course 4IT101.
It implements small text based Adventure game. 
There are multiple rooms for players to explore.
To win, players need to beat set level boss. (lvl 25 by default).
The game also offers infinite gameplay.

## Installation

Executable double-click .jar file of the project is included.
The project can also be run by the command below:

```bash
java -jar *pathToThe.jarFile* [arguments]
```

The file can also work with up to 3 arguments. Any further arguments will be ignored.

First argument takes integer value that represents time between text messages from the game in miliseconds. The default value is 200.
Negative values are converted to the default value. 

Second argument takes integer value that represents boss level required to beat to win the game. The default value is 25.
Negative values are converted to the default value. The value is rounded up to the nearest multiple of 5.

Third argument takes name of text file that includes commands to be run by the game. 
This is strictly for testing purposes, as it disables or alters few of the commands in order to be functional.
It is not recommended to use longer time between messages than 50 (preferably 0) miliseconds for testing.

##Authors
Tomáš Beneda (bent07)

##Version
ZS2022

##Notes
This is my first larger Java project.
Constructive criticism is welcomed.
