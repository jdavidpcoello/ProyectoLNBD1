import User from './User.js';

const photoDiv = document.querySelector('.photo-input-container');
const photo = document.querySelector('#profile-photo');
const photoInput = document.querySelector('#upload-photo');
const uploadButton = document.querySelector('#upload-btn');

const greetings = document.querySelector('.greeting-user');
const nameDiv = document.querySelector('.display-name');
const jobDiv = document.querySelector('.display-job');
const locationDiv = document.querySelector('.display-location');

const submitBtn = document.querySelector('#btn6');

submitBtn.addEventListener('click',createUser)

greetings.innerHTML = `¡Sales fenomenal, ${localStorage.getItem('name')} ${localStorage.getItem('lastName')}!`
nameDiv.innerHTML = `${localStorage.getItem('name')} ${localStorage.getItem('lastName')}`;
jobDiv.innerHTML = `${localStorage.getItem('job')} en ${localStorage.getItem('placeJob')}`;
locationDiv.innerHTML = `${localStorage.getItem('location')}`;

photoInput.addEventListener('change', uploadNewFile);

function uploadNewFile() {
    const selectedFile = this.files[0];
    if (selectedFile) {
        const reader = new FileReader();

        reader.addEventListener('load', function () {
            newPhoto(reader.result);
        });

        reader.readAsDataURL(selectedFile);
    }
}

function newPhoto(result) {
    photo.setAttribute('src', result);
    localStorage.setItem('profilePhoto',result);
}

async function createUser(event) {
    const url = 'http://localhost:8080/api/usuarios/login';

    event.preventDefault();
    
    const email = localStorage.getItem('email');
    const password = localStorage.getItem('password');
    const firstName = localStorage.getItem('name');
    const lastName = localStorage.getItem('lastName');
    const country = localStorage.getItem('country');
    const city = localStorage.getItem('city');
    const parentCity = localStorage.getItem('city2');
    const job = localStorage.getItem('job');
    const typeJob = localStorage.getItem('typeJob');
    const placeJob = localStorage.getItem('placeJob');
    const schoolName = localStorage.getItem('schoolName');
    const firstYear = localStorage.getItem('firstYear');
    const lastYear = localStorage.getItem('lastYear');
    const birthDay = localStorage.getItem('birthDay');
    const birthMonth = localStorage.getItem('birthMonth');
    const birthYear = localStorage.getItem('birthYear');
    const profilePhoto = localStorage.getItem('profilePhoto');


    const newUser = new User(
        email,
        password,
        firstName,
        lastName,
        country,
        parentCity,
        city,
        job,
        typeJob,
        placeJob,
        schoolName,
        firstYear,
        lastYear,
        birthDay,
        birthMonth,
        birthYear,
        profilePhoto
    );

    try {
        const response = await fetch(url, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(newUser)
        });
    
        if (response.ok) {
            const responseData = await response.json();
            
            alert("Eres grande Franky, eres grande Rivers. te paso al inicio");
            window.location.href = 'inicio.html'
        } else {
            const errorData = await response.json();
            alert("Error al iniciar sesión: " + errorData.message || "Cambiate a Trabajo social.");
        }
    } catch (error) {
        console.error("Error en la solicitud:", error);
        alert("Para la persona que recibio este error, corre con el backend primero y luego esto o internet.");
    }
}
