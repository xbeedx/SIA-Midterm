from flask import Flask, render_template, request
from zeep import Client
import json
import mysql.connector


app = Flask(__name__)

# Replace the URL with the actual endpoint URL of your web service
wsdl_url = "http://localhost:8088/axis2/services/BookingWS?wsdl"

# Connect SOAP client
try:
    client = Client(wsdl_url)
except Exception as e:
    app.logger.error("Error initializing SOAP client: %s", str(e))
    exit()

# mysql client
database_config = {
    'host': 'localhost',
    'user': 'root',
    'database': 'SIA',
}

try:
    connection = mysql.connector.connect(**database_config)
except mysql.connector.errors.ProgrammingError as err:
        print(f"Error: {err}")

stationArrivalId = ""

# Fetch stations using the global client
def get_stations():
    try:
        stations = client.service.getStations()
        if not stations:
            app.logger.error("No stations found")
            

        return stations
    except Exception as e:
        app.logger.error("Error getting stations: %s", str(e))
        return []
    
@app.route('/')
def home():
    stations = get_stations()
    return render_template('index.html', stations=stations)

@app.route('/selectArrival', methods=['GET'])
def booking():
    stations = get_stations()

    stationId = json.loads(request.args.get('station'))['station']
    request_data = {'stationId': stationId}

    global stationArrivalId 
    stationArrivalId = stationId

    try:
        station = client.service.getStation(**request_data)
        return render_template('booking.html', stations=stations, stationArrival=station)
    except Exception as e:
        app.logger.error("Error processing booking request: %s", str(e))
        return "Error processing booking request"

@app.route('/getTrains', methods=['POST'])
def trains():
    data = request.get_json() 
    print(data)
    departure = data.get('departure')
    departureId = data.get('departureId')
    arrival = data.get('arrival')
    arrivalId = data.get('arrivalId')
    outbound = data.get('outbound')
    returnDate = data.get('return')
    tickets = data.get('tickets')
    travelClass = data.get('class')
    return ""

if __name__ == '__main__':
    app.run(debug=True)