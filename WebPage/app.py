from flask import Flask, render_template, request
from zeep import Client
import json

app = Flask(__name__)

# Replace the URL with the actual endpoint URL of your web service
wsdl_url = "http://localhost:8088/axis2/services/BookingWS?wsdl"

client = Client(wsdl_url)

@app.route('/')
def home():
    stations = client.service.getStations()
    return render_template('index.html', stations=stations)

@app.route('/selectArrival', methods=['GET'])
def booking():
    stationId = json.loads(request.args.get('station'))['station']
    return render_template('booking.html', stationId=stationId)



if __name__ == '__main__':
    app.run(debug=True)
