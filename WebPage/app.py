from flask import Flask, render_template, request, redirect, url_for, jsonify, session
from datetime import datetime
from zeep import Client
import json

app = Flask(__name__)
app.secret_key = 'your_secure_random_key'  # Replace with a secure, random key

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
    





# ----------------- ROUTES ----------------
@app.route('/')
def home():
    stations = get_stations()
    return render_template('index.html', stations=stations)

@app.route('/booktrain', methods=['POST'])
def booktrain():
    try:
        data = request.get_json()
        train_id = data.get('trainId')
        nbTickets = data.get('nbTickets')
        travel_class = data.get('travelClass').replace("'","")
        user_id = session["user"]

        if not train_id or not travel_class:
            return jsonify({"status": 400, "message": "Missing trainId or travelClass in the request."}), 400
        
        response = get_client().service.bookSeat(user_id,train_id,travel_class,"flexible",nbTickets)
        # Process the response as needed
        if response:
            return jsonify({"status": 200, "message": "Booking successful"}), 200
        else:
            return jsonify({"status": 400, "message": f"Booking failed. Reason: {response}"}), 400

    except Exception as e:
        app.logger.error("Error processing booking request: %s", str(e))
        return jsonify({"status": 500, "message": "Internal Server Error"}), 500


@app.route('/register', methods=['GET', 'POST'])
def register():
    if request.method == 'POST':
        # Get the form data
        username = request.form.get('username')
        password = request.form.get('password')
        request_data = {'username': username, 'password': password}

        try:
            user = get_client().service.createUser(**request_data)
            app.logger.info("message: " + user)
            print("Register message: " + user)

            return redirect(url_for('login'))
        except Exception as e:
            app.logger.error("Error processing booking request: %s", str(e))
            print("Error processing booking request: %s", str(e))

    # If it's a GET request, render the registration form
    return render_template('register.html')


@app.route('/login', methods=['GET', 'POST'])
def login():
    if request.method == 'POST':
        # Get the form data
        username = request.form.get('username')
        password = request.form.get('password')
        request_data = {'username': username, 'password': password}

        try:
            user = get_client().service.authenticateUser(**request_data)

            # Store user information in the session
            session["user"] = user
            session["name"] = username

            app.logger.info("message: " + user)
            print("Login message: " + user)
        except Exception as e:
            app.logger.error("Error processing login request: %s", str(e))
            # print("Error processing login request: %s", str(e))

        # Redirect to a success page or handle the login logic
        return redirect(url_for('home'))

    # Handle GET request for login form rendering (if needed)
    return render_template('login.html')

@app.route('/logout')
def logout():
    # Clear the user-related session data
    session.pop('user', None)
    session.pop('name', None)

    # Redirect to the login page or any other desired destination
    return redirect(url_for('login'))

@app.route('/account')
def account():
    user_id = session["user"]
    # TODO : train you reserve
    return render_template('account.html', stations=[])

@app.route('/selectArrival', methods=['GET'])
def booking():
    stations = get_stations()

    stationId = json.loads(request.args.get('station'))['station']
    request_data = {'stationId': stationId}

    # global stationArrivalId 
    # stationArrivalId = stationId

    try:
        current_datetime = datetime.now()
        current_date = current_date = current_datetime.strftime('%Y-%m-%d')
        station = get_client().service.getStation(**request_data)
        return render_template('booking.html', stations=stations, stationArrival=station, current_date=current_date)
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