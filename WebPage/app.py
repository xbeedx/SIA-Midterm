from flask import Flask, render_template, request, jsonify
from zeep import Client
import json

app = Flask(__name__)

stationArrivalId = ""

# Connect SOAP client
def get_client():
    # Replace the URL with the actual endpoint URL of your web service
    wsdl_url = "http://localhost:8088/axis2/services/BookingWS?wsdl"

    try:
        client = Client(wsdl_url)
        return client
    except Exception as e:
        app.logger.error("Error initializing SOAP client: %s", str(e))
        # Raise a custom exception or return a special value
        raise Exception("Failed to initialize SOAP client")

# Fetch stations using the global client
def get_stations():
    try:
        stations = get_client().service.getStations()
        return stations
    except Exception as e:
        app.logger.error("Error getting stations: %s", str(e))
        # Handle the error gracefully, e.g., log it and return an empty list
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

    # global stationArrivalId 
    # stationArrivalId = stationId

    try:
        station = get_client().service.getStation(**request_data)
        return render_template('booking.html', stations=stations, stationArrival=station)
    except Exception as e:
        app.logger.error("Error processing booking request: %s", str(e))
        return "Error processing booking request"

@app.route('/getTrains', methods=['POST'])
def trains():
    data = request.get_json() 
    print(data)

    # departureId = data.get('departureId')
    # arrivalId = data.get('arrivalId')
    travel_class = data.get("class")

    if travel_class == "first":
        travel_class = "First Class"
    elif travel_class == "premium":
        travel_class = "Premium Class"
    else:
        travel_class = "Second Class"

    request_data = {
        'departureStation': data.get('departure'),
        'arrivalStation': data.get('arrival'),
        'departureDate': data.get('outbound'),
        'returnDate': data.get('return'),
        'numTickets': data.get('tickets'),
        'travelClass': travel_class
    }

    try:
        search_train = get_client().service.searchTrains(**request_data)

        # Split the string into lines
        lines = search_train.split('\n')
        # Remove the first line
        filtered_lines = lines[1:]
        # Join the lines back into a string
        output_string = '\n'.join(filtered_lines)

        # Check if output_string is empty
        is_empty = not bool(output_string)
        if is_empty:
            return jsonify({"status": 404, "message": "No trains found for the given parameters."}), 404
        
        # Remove unwanted parts of the string
        json_string = output_string.split('{')[1].split('}')[0]
        # Convert the remaining string to a dictionary
        data_dict = dict(item.strip().split('=') for item in json_string.split(','))
        # Convert the dictionary to a JSON string
        json_output = json.dumps(data_dict, indent=2)

        train = json_output
        return jsonify({"status": 200, "data": train}), 200
    except Exception as e:
        app.logger.error("Error processing search train request: %s", str(e))
        return "Error processing search train request"


if __name__ == '__main__':
    app.run(debug=True)