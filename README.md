# SIA-Midterm

## Objective

Create a train journeys and booking service which has basic functions:

- ### Train Searching: 
    customers can search available trains based on departure and arrival stations, the outbound and return dates and time, number of tickets and travel class (First, Business or Standard).

- ### Train booking: 
    customers can reserve a seat by providing the trains IDs, the travel class (First, Business or Standard)and ticket type (flexible, not flexible).

## [Scenario](https://www-inf.telecom-sudparis.eu/SIMBAD/courses/doku.php?id=teaching_assistant:web_services:midterm2021_88)

![](./Schema.svg)


## Java version
This project is written in Java 8. You can download and install it from the official website: [Java SE Downloads](https://www.oracle.com/fr/java/technologies/javase/javase8-archive-downloads.html)

## Train Filtering

Running on port 8182

## Train Booking 

Running on port 8088

## Client

Running on port 8081

## SNCF API 

### Departures 
```
https://api.sncf.com/v1/coverage/sncf/stop_areas/stop_area:SNCF:87686006/departures
```