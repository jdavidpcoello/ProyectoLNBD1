import RegexValidator from './RegexValidator.js';

const emailField = document.querySelector('#email-field');
const passwordField = document.querySelector('#password-field');
const buttonElement = document.querySelector('#btn1');

buttonElement.addEventListener('click', submitFirstForm);

function submitFirstForm(){
    let validez = formValidation();
    if(validez){
        localStorage.setItem('email', emailField.value);
        localStorage.setItem('password', passwordField.value);
        window.location.href = 'login-2.html';
    }
}

function formValidation(){
    const invalidEmailDiv = document.querySelector('.invalid-email-text');
    const invalidPasswordDiv = document.querySelector('.invalid-password-text');
    invalidEmailDiv.innerHTML = '';
    invalidPasswordDiv.innerHTML = '';
    emailField.classList.remove('invalid-input');
    passwordField.classList.remove('invalid-input');

    if(RegexValidator.isEmpty(emailField.value)){
        invalidEmailDiv.innerHTML = 'Introduce tu dirección de correo electrónico.'
        emailField.classList.add('invalid-input');
        return false;
    } else if(!RegexValidator.isEmail(emailField.value)){
        invalidEmailDiv.innerHTML = 'Introduce una dirección de correo electrónico valida.'
        emailField.classList.add('invalid-input');
        return false;
    }
    else if(RegexValidator.isEmpty(passwordField.value)){
        invalidPasswordDiv.innerHTML = 'Introduce una contraseña.';
        passwordField.classList.add('invalid-input');
        return false;
    }else if(!RegexValidator.validLength(passwordField.value)){
        invalidPasswordDiv.innerHTML = 'La contraseña debe tener 6 caracteres como mínimo.';
        passwordField.classList.add('invalid-input');
        return false;
    }
    return true;
}