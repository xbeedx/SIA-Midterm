from flask import Flask, render_template, request
from zeep import Client

app = Flask(__name__)

# Replace the URL with the actual endpoint URL of your web service
wsdl_url = "http://localhost:8088/axis2/services/BookingWS?wsdl"

client = Client(wsdl_url)

@app.route('/')
def home():
    stations = client.service.getStations()
    return render_template('index.html', stations=stations)

@app.route('/selectArrival', methods=['POST'])
def handle_post():
    stationId = request.get_json().get('station')
    return "hai"

if __name__ == '__main__':
    app.run(debug=True)
