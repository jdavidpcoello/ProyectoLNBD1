import RegexValidator from './RegexValidator.js';

const nameField = document.querySelector('#name-field');
const lastNameField = document.querySelector('#last-name-field');
const buttonElement = document.querySelector('#btn2');

buttonElement.addEventListener('click',submitSecondForm);

function submitSecondForm(){
    let validez = formValidation();
    if(validez){
        localStorage.setItem('name', nameField.value);
        localStorage.setItem('lastName', lastNameField.value);
        window.location.href = 'login-3.html';
    }
}

function formValidation(){
    const invalidNameDiv = document.querySelector('.invalid-name-text');
    const invalidLastNameDiv = document.querySelector('.invalid-last-name-text');
    invalidNameDiv.innerHTML = '';
    invalidLastNameDiv.innerHTML = '';
    nameField.classList.remove('invalid-input');
    lastNameField.classList.remove('invalid-input');
    if(RegexValidator.isEmpty(nameField.value)){
        invalidNameDiv.innerHTML = 'Introduce tu nombre.'
        nameField.classList.add('invalid-input');
        return false;
    }
    else if(!RegexValidator.isValidName(nameField.value)){
        invalidNameDiv.innerHTML = 'Introduce un nombre valido.';
        nameField.classList.add('invalid-input');
        return false;
    }
    else if(RegexValidator.isEmpty(lastNameField.value)){
        invalidLastNameDiv.innerHTML = 'Introduce tus apellido.';
        lastNameField.classList.add('invalid-input');
        return false;
    }else if(!RegexValidator.isValidName(lastNameField.value)){
        invalidLastNameDiv.innerHTML = 'Introduce un apellido valido.';
        lastNameField.classList.add('invalid-input');
        return false;
    }
    return true;
}