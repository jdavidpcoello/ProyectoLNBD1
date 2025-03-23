const emailField = document.querySelector('#input1');
const passwordField = document.querySelector('#input2');
const signInForm = document.querySelector('#sign-in-form');

signInForm.addEventListener('submit', submitSignin);


//JS provisional solo para navegar
function submitSignin(event) {
    event.preventDefault();
    window.location.href = '../Pages/Inicio.html';
}
