from flask import Flask, render_template, request
from zeep import Client
import json

app = Flask(__name__)

# Replace the URL with the actual endpoint URL of your web service
wsdl_url = "http://localhost:8088/axis2/services/BookingWS?wsdl"

client = Client(wsdl_url)
stations = client.service.getStations()
stationArrivalId = ""


@app.route('/')
def home():
    return render_template('index.html', stations=stations)

@app.route('/selectArrival', methods=['GET'])
def booking():
    stationId = json.loads(request.args.get('station'))['station']
    request_data = {'stationId': stationId}
    global stationArrivalId 
    stationArrivalId = stationId
    station = client.service.getStation(**request_data)
    return render_template('booking.html', stations=stations, stationArrival=station)

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
