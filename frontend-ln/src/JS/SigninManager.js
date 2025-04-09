const emailField = document.querySelector('#input1');
const passwordField = document.querySelector('#input2');
const signInForm = document.querySelector('#sign-in-form');
const signInButton = document.querySelector('#sign-in-button');
const emailLabel = document.querySelector('.email-label');
const passwordLabel = document.querySelector('.password-label');

signInForm.addEventListener('submit', submitSignin);

function submitSignin(event) {
    event.preventDefault();
    
    const validez = validateForm();
    if (validez) {
        window.location.href = '../Pages/Inicio.html';
    }
}



function validateForm(){
    const invalidEmailDiv = document.querySelector('.invalid-email');
    const invalidPasswordDiv = document.querySelector('.invalid-password');
    invalidEmailDiv.innerHTML = '';
    invalidPasswordDiv.innerHTML = '';
    emailField.classList.remove('invalid-input');
    emailLabel.classList.remove('invalid-input-label');
    passwordField.classList.remove('invalid-input');
    passwordLabel.classList.remove('invalid-input-label');
    const emailRegex = /^[\w\d\.-]+@\w+(\.\w+)+$/;

    if(emailField.value === ''){
        invalidEmailDiv.innerHTML = 'Introduce un email o un número de teléfono';
        emailField.classList.add('invalid-input');
        emailLabel.classList.add('invalid-input-label');
        return false;
    }
    else if(!emailRegex.test(emailField.value)){
        invalidEmailDiv.innerHTML = 'Introduce un nombre de usuario válido.';
        emailField.classList.add('invalid-input');
        emailLabel.classList.add('invalid-input-label');
        return false;
    }
    else if(passwordField.value === ''){
        invalidPasswordDiv.innerHTML = 'Introduce una contraseña.';
        passwordField.classList.add('invalid-input');
        passwordLabel.classList.add('invalid-input-label');
        return false;
    }
    return true;
}
