window.addEventListener('load', function() {
    var input = document.querySelector('.input-reservation');
    var stationsContainer = document.querySelector('.stations-container');
    stationsContainer.style.width = getComputedStyle(input).width;
});

document.querySelector('.overlay').addEventListener('click', function() {
    document.body.style.paddingBottom = '0';
    document.querySelector(".container-reservation-content").style.width = '75vw'; 
    document.querySelector(".container-reservation-content").style.padding = '30px';
    document.querySelector(".container-reservation-content").style.borderRadius = '30px';
    document.querySelector(".stations-container").style.setProperty("display", "none", "important");

    this.classList.remove('double-border-input');
    window.scrollTo({ top: 0, behavior: 'smooth' });
    document.querySelector('.overlay').classList.remove('blur');
});

document.querySelector('.input-reservation').addEventListener('focus', function() {
    document.body.style.paddingBottom = '100vh'; 
    
    const targetScrollPosition = this.getBoundingClientRect().top + window.scrollY - 20;
    window.scrollTo({ top: targetScrollPosition, behavior: 'smooth' , block: 'start'});

    document.querySelector(".container-reservation-content").style.width = '100vw'; 
    document.querySelector(".container-reservation-content").style.borderRadius = '0';
    document.querySelector(".container-reservation-content").style.padding = '10px';
    this.classList.add('double-border-input'); 
    document.querySelector('.overlay').classList.add('blur');
    setTimeout(function() {
        document.querySelector(".stations-container").style.setProperty("display", "block", "important");
    }, 500);
});

var input = document.querySelector('.input-reservation');
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

function selectArrival (station) {
    window.location.href = '/selectArrival?station=' + JSON.stringify({station: station});
};