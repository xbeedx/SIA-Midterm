from flask import Flask, render_template
from zeep import Client

app = Flask(__name__)

# Replace the URL with the actual endpoint URL of your web service
wsdl_url = "http://localhost:8088/axis2/services/BookingWS?wsdl"

# Create a Zeep client
client = Client(wsdl_url)

@app.route('/')
def home():
    # Call the sayHello operation
    stations = client.service.getStations()

    return render_template('index.html')

if __name__ == '__main__':
    app.run(debug=True)
