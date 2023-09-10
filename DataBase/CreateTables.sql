-- Create the User table
CREATE TABLE User (
    UserID INT PRIMARY KEY,
    Username VARCHAR(255) NOT NULL,
    Password VARCHAR(255) NOT NULL,
    Email VARCHAR(255) NOT NULL,
    FirstName VARCHAR(255),
    LastName VARCHAR(255),
    PRIMARY KEY (UserID)
);

-- Create the Classes table
CREATE TABLE Classes (
    ClassID INT PRIMARY KEY,
    ClassName VARCHAR(255) NOT NULL,
    PriceMultiplier DECIMAL(5, 2) NOT NULL,
    PRIMARY KEY (ClassID)
);

-- Create the Station table
CREATE TABLE Station (
    StationID INT PRIMARY KEY,
    StationName VARCHAR(255) NOT NULL,
    City VARCHAR(255) NOT NULL;
    Department VARCHAR(255) NOT NULL,
    PRIMARY KEY (StationID)
);

-- Create the Train table
CREATE TABLE Train (
    TrainID INT PRIMARY KEY,
    TrainType VARCHAR(255) NOT NULL,
    Name VARCHAR(255),
    Seats INT NOT NULL,
    Capacity INT NOT NULL,
    PRIMARY KEY (TrainID)
);

-- Create the Ride table
CREATE TABLE Ride (
    RideID INT PRIMARY KEY,
    TrainID INT,
    DepartureStationID INT,
    ArrivalStationID INT,
    DepartureDate DATE,
    ArrivalDate DATE,
    PRIMARY KEY (RideID),
    FOREIGN KEY (TrainID) REFERENCES Train(TrainID),
    FOREIGN KEY (DepartureStationID) REFERENCES Station(StationID),
    FOREIGN KEY (ArrivalStationID) REFERENCES Station(StationID)
);

-- Create the Ticket table
CREATE TABLE Ticket (
    TicketID INT PRIMARY KEY,
    UserID INT,
    TravelClass INT,
    RideID INT,
    Seat INT,
    Price DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (UserID) REFERENCES User(UserID),
    FOREIGN KEY (TravelClass) REFERENCES Classes(ClassID),
    FOREIGN KEY (RideID) REFERENCES Ride(RideID)
);
