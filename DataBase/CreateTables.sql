USE SIA;
-- Create the User table
CREATE TABLE IF NOT EXISTS `User` (
    UserID INT PRIMARY KEY,
    Username VARCHAR(255) NOT NULL,
    Password VARCHAR(255) NOT NULL,
    Email VARCHAR(255) NOT NULL,
    FirstName VARCHAR(255),
    LastName VARCHAR(255)
);

-- Create the Station table
CREATE TABLE IF NOT EXISTS Station (
    StationID VARCHAR(255) PRIMARY KEY,
    StationName VARCHAR(255) NOT NULL,
    City VARCHAR(255) NOT NULL,
    ZipCode VARCHAR(20),
    Lat DECIMAL(9,6) NOT NULL,
    Lon DECIMAL(9,6) NOT NULL
);

-- Create the Reservations table
CREATE TABLE IF NOT EXISTS Reservation (
    ReservationID INT PRIMARY KEY,
    TrainID VARCHAR(255),
    TrainName VARCHAR(255),
    Direction VARCHAR(255),
    DepartureStopID VARCHAR(255),
    DepartureStopName VARCHAR(255),
    ArrivalStopID VARCHAR(255),
    ArrivalStopName VARCHAR(255),
    DepartureTime DATETIME,
    ArrivalTime DATETIME
);

-- Create the Train table
CREATE TABLE IF NOT EXISTS Train (
    TrainID INT PRIMARY KEY,
    TrainName VARCHAR(255),
    DepartureStation VARCHAR(255),
    ArrivalStation VARCHAR(255),
    departureDate DATETIME,
    arrivalDate DATETIME,
    NumTickets INT,
    TravelClass VARCHAR(20)
);
