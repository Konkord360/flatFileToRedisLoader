# flaFileToRedisLoader

Program that downloads flat file from https://plikplaski.mf.gov.pl/pliki containing active vat payers and loads it to local redis database

## Installation

to run this program at least java 8 (preferably 11) and maven is required.

## Usage
To run this program, firstly go to the directory with pom file and run:

```bash
    mvn clean compile assembly:single
```

after then run 
```bash
   cd target
   java -jar vatFlatFileToRedis-1.0-jar-with-dependencies.jar
```
program without parameters runs everything on default: 
generates file name and downloads it from https://plikplaski.mf.gov.pl/pliki/ to /home/osboxes/fileDownload/ as a file.7z - which can be changed in the code, in the main method of App class, or can be given as an input parameters like that:

```bash
   java -jar vatFlatFileToRedis-1.0-jar-with-dependencies.jar "directory" "file name" "fileUrl" 
```
all parameters are optional.

Program by default looks for Redis on localhost port 6379 - can be changed in DatabseHandler class
