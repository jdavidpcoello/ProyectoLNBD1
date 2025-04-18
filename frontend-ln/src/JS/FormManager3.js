import RegexValidator from './RegexValidator.js';

const locationField = document.querySelector('#location-field');
const buttonElement = document.querySelector('#btn3');

buttonElement.addEventListener('click', submitThirdForm);

function submitThirdForm(){
    let validez = formValidation();
    if(validez){
        localStorage.setItem('location',locationField.value);
        asignCountryandCity(locationField.value);
        window.location.href = 'login-4.html';
    }
}

function formValidation(){
    const invalidLocation = document.querySelector('.invalid-location-text');
    invalidLocation.innerHTML = '';
    const datalistLocation = document.querySelector('#location-list');
    locationField.classList.remove('onvalid-input');
    if(RegexValidator.isEmpty(locationField.value)){
        locationField.classList.add('invalid-input');
        invalidLocation.innerHTML = 'Introduce tu ubicacion';
        return false;
    }else if (!Array.from(datalistLocation.options).some(option => option.value === locationField.value)) {
        locationField.classList.add('invalid-input');
        invalidLocation.innerHTML = 'Introduce una ubicacion valida.';
        return false;
    }
    return true;
}


function asignCountryandCity(location){
    const parts = location.split(',').map(p => p.trim());

    if (parts.length === 3) {
        localStorage.setItem('city', parts[0]);
        localStorage.setItem('city2', parts[1]);
        localStorage.setItem('country', parts[2]);
    } else if (parts.length === 2) {
        localStorage.setItem('city', parts[0]);
        localStorage.setItem('city2', ''); 
        localStorage.setItem('country', parts[1]);
    } else {
        localStorage.setItem('city', '');
        localStorage.setItem('city2', '');
        localStorage.setItem('country', parts[0] || '');
    }
}
