function sayHello() {
    // Construct SOAP request
    var soapRequest =
        `<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" ` +
        `xmlns:web="http://ws.booking.tps">` +
        `<soapenv:Header/>` +
        `<soapenv:Body>` +
        `<web:sayHello>` +
        `<web:input>Chuugo</web:input>` +
        `</web:sayHello>` +
        `</soapenv:Body>` +
        `</soapenv:Envelope>`;

    // Make AJAX call
    $.ajax({
        url: 'http://localhost:8085/Train_Booking/services/BookingWS/',
        type: 'POST',
        dataType: 'xml',
        data: soapRequest,
        contentType: 'text/xml',
        success: function (response) {
            // Handle the SOAP response
            var result = $(response).find('ns\\:return, return').text(); // Fix: Use 'ns\\:return' or 'return' depending on the namespace prefix
            $("#response").text("Response: " + result);
        },
        error: function (jqXHR, textStatus, errorThrown) {
            // Handle errors
            $("#response").text("Error: " + textStatus + ", " + errorThrown);
        }
    });
}