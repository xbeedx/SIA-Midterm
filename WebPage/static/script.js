document.querySelector('.input-reservation').addEventListener('focus', function() {
    document.body.style.paddingBottom = '100vh'; 
    
    const targetScrollPosition = this.getBoundingClientRect().top + window.scrollY - 20;
    window.scrollTo({ top: targetScrollPosition, behavior: 'smooth' , block: 'start'});

    document.querySelector(".container-reservation-content").style.width = '100vw'; 
    document.querySelector(".container-reservation-content").style.borderRadius = '0';
    document.querySelector(".container-reservation-content").style.padding = '10px';
    this.classList.add('double-border-input'); 
    document.querySelector('.overlay').classList.add('blur'); // Add the .blur class to the overlay

});

document.querySelector('.input-reservation').addEventListener('blur', function() {
    document.body.style.paddingBottom = '0';
    document.querySelector(".container-reservation-content").style.width = '75vw'; 
    document.querySelector(".container-reservation-content").style.padding = '30px';
    document.querySelector(".container-reservation-content").style.borderRadius = '30px';
    this.classList.remove('double-border-input');
    window.scrollTo({ top: 0, behavior: 'smooth' });
    document.querySelector('.overlay').classList.remove('blur');
});