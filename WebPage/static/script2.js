document.querySelector('.input-departure').addEventListener('focus', function() {
    document.body.style.paddingBottom = '100vh'; 
    
    const targetScrollPosition = this.getBoundingClientRect().top + window.scrollY - 20;
    window.scrollTo({ top: targetScrollPosition, behavior: 'smooth' , block: 'start'});
    
    document.querySelector('.overlay').classList.add('blur');
    setTimeout(function() {
        document.querySelector(".stations-container").style.setProperty("display", "block", "important");
    }, 500);
});

document.querySelector('.overlay').addEventListener('click', function() {
    document.body.style.paddingBottom = '0';
    document.querySelector(".stations-container").style.setProperty("display", "none", "important");
    window.scrollTo({ top: 0, behavior: 'smooth' });
    document.querySelector('.overlay').classList.remove('blur');
});

function selectDeparture(stationID, station_Name) {
    document.querySelector(".input-departure").value = station_Name
    document.querySelector(".input-departure").title = stationID
    document.querySelector(".stations-container").style.setProperty("display", "none", "important");
    document.querySelector('.overlay').classList.remove('blur');
    window.scrollTo({ top: 0, behavior: 'smooth' });
};

// Function to handle form submission
function handleFormSubmission() {
    // Get the form values
    var departure = document.querySelector('#departure').title;
    var arrival = document.querySelector('#arrival').title;
    var outbound = document.querySelector('#outbound').value;
    var returnDate = document.querySelector('#return').value;
    var tickets = document.querySelector('#tickets').value;
    var travelClass = document.querySelector('#class').value;

    // Check if any value is missing
    if (!departure || !arrival || !outbound || !returnDate || !tickets || !travelClass) {
        alert('Please fill out all fields.');
        return;
    }

    // Create the request body
    var body = {
        departure: departure,
        arrival: arrival,
        outbound: outbound,
        return: returnDate,
        tickets: tickets,
        class: travelClass
    };

    fetch('/getTrains', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(body)
    }).then(function(response) {
        return response.json();
    }).then(function(data) {
        // Handle the response data
        console.log(data);
        if (data.status == 200) {
            console.log("success");
            displayTrainResults(data.data);
        }
    }).catch(function(error) {
        console.error('Error:', error);
    });
}

// Function to display train results in the table
function displayTrainResults(trainData) {
    var resultTableBody = document.getElementById('result-table-body');
    resultTableBody.innerHTML = ""; // Clear existing content

    // Parse the JSON data
    console.log(trainData)
    var trains = [JSON.parse(trainData)];
    console.log(trains)

    // Iterate through the trains and append rows to the table
    trains.forEach(function(train) {
        var row = resultTableBody.insertRow();
        var cell1 = row.insertCell(0);
        var cell2 = row.insertCell(1);
        var cell3 = row.insertCell(2);
        var cell4 = row.insertCell(3);

        cell1.innerHTML = train.trainID;
        cell2.innerHTML = train.trainName;
        cell3.innerHTML = train.departureDate;
        cell4.innerHTML = train.arrivalDate;

        var nbTickets = document.querySelector('#tickets').value;

        var bookButton = document.createElement('button');
        bookButton.innerHTML = 'Book';
        bookButton.className = 'btn btn-danger'; 
        bookButton.addEventListener('click', function() {
            var bookData = {
                trainId: train.trainID,
                travelClass: train.travelClass,
                nbTickets: nbTickets
            };
        
            fetch('/booktrain', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(bookData)
            }).then(function(response) {
                if(response.ok)
                    alert("Booked")
                return response
            }).then(function(data) {
                // Handle the response data
                alert('Booking status: ' + data.message);
            }).catch(function(error) {
                console.error('Error:', error);
            });
        });
        cell4.appendChild(bookButton);
    });
}

document.querySelector('.btn-primary').addEventListener('click', function(event) {
    event.preventDefault(); // Prevent the form from submitting normally
    handleFormSubmission();
});

var input = document.querySelector('.input-departure');
var listItems = document.querySelectorAll('.list-group-item');

input.addEventListener('input', function() {
    var filter = input.value.toUpperCase();

    for (var i = 0; i < listItems.length; i++) {
        var listItem = listItems[i];
        var txtValue = listItem.textContent || listItem.innerText;

        if (txtValue.toUpperCase().indexOf(filter) > -1) {
            listItem.style.display = "";
        } else {
            listItem.style.display = "none";
        }
    }
});
