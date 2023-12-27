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

### Run 
Right click on ```RESTDistributor.java``` > Run as ... > Run on server 

Running on port 8182

## Train Booking 

### Run 
Right click on project > Run as ... > Run on server > Select a server/ If no server, create a Tomcat v9.0 server > Next > Add Train_Booking to the ```configured``` table > Finish 

Running on port 8080

## Client

### Run 
Right click on ```Client.java``` > Run as ... > Run as java application