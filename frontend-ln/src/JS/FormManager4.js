import RegexValidator from './RegexValidator.js';

/*Si es 0 es el JobForm y si es StudentForm*/ 
let status = 0;
const jobDiv = document.querySelector('.step-4');
const studentDiv = document.querySelector('.step-5');

document.querySelector('#btn-student').addEventListener('click', event => {
    jobDiv.classList.add('not-display');
    studentDiv.classList.remove('not-display');
    status = 1;
});

document.querySelector('#btn-not-student').addEventListener('click', event => {
    jobDiv.classList.remove('not-display');
    studentDiv.classList.add('not-display');
    status = 0;
});

// JOB FIELDS
const recentJobField = document.querySelector('#recent-job-field');
const typeJobField = document.querySelector('#type-job-select');
const placeJobField = document.querySelector('#place-job-field');

// STUDENT FIELDS
const schoolNameField = document.querySelector('#school-name-field');
const firstYearField = document.querySelector('#first-year-field');
const lastYearField = document.querySelector('#last-year-field');
const birthDayField = document.querySelector('#birth-day-field');
const birthMonthField = document.querySelector('#birth-month-field');
const birthYearField = document.querySelector('#birth-year-field');


document.querySelectorAll('.form-button').forEach(button => {
    button.addEventListener('click', submitForm);
});

function formValidation(status){
    if(status===0){
        const invalidJobText = document.querySelector('.invalid-job-text');
        const invalidTypeJobText = document.querySelector('.invalid-type-job-text');
        const invalidPlaceJobText = document.querySelector('.invalid-job-place-text');
        invalidJobText.innerHTML = '';
        invalidTypeJobText.innerHTML = '';
        invalidPlaceJobText.innerHTML = '';
        recentJobField.classList.remove('invalid-input');
        placeJobField.classList.remove('invalid-input');
        if(RegexValidator.isEmpty(recentJobField.value)){
            invalidJobText.innerHTML = 'Introduce tu puesto actual.';
            recentJobField.classList.add('invalid-input');
            return false;
        } else if(typeJobField.value === ''){
            invalidTypeJobText.innerHTML = 'Selecciona un tipo de trabajo.';
            return false;
        }else if(RegexValidator.isEmpty(placeJobField.value)){
            invalidPlaceJobText.innerHTML = 'Introduce el lugar de trabajo.';
            placeJobField.classList.add('invalid-input');
            return false;
        }
    }

    if(status===1){
        const invalidSchoolText = document.querySelector('.invalid-school-text');
        const invalidYearText = document.querySelector('.invalid-years-text');
        const invalidBirthDateText = document.querySelector('.invalid-birth-date-text');
        invalidSchoolText.innerHTML = '';
        invalidYearText.innerHTML = '';
        invalidBirthDateText.innerHTML = '';
        schoolNameField.classList.remove('invalid-input');

        if(RegexValidator.isEmpty(schoolNameField.value)){
            invalidSchoolText.innerHTML = 'Introduce el nombre de la escuela.';
            schoolNameField.classList.add('invalid-input');
            return false;
        }else if(RegexValidator.isEmpty(firstYearField.value)){
            invalidYearText.innerHTML = 'Año de inicio de estudios (obligatorio). Año de finalizacion (Opcional).';
            return false;
        }
        else if(birthDayField.value === '' || birthMonthField.value === '' || birthYearField.value === ''){
            invalidBirthDateText.innerHTML = 'Introduce tu fecha de nacimiento.';
            return false;
        }else if(firstYearField.value > lastYearField.value){
            invalidYearText.innerHTML = 'Ingresa los años de estudios validos.';
            return false;
        }
    }
    return true
}


function submitForm(){

    let validez = formValidation(status);

    if(validez){
        if (status === 0) {
            schoolNameField.value = '';
            firstYearField.value = '';
            lastYearField.value = '';
            birthDayField.value = '';
            birthYearField.value = '';
            birthMonthField.value = '';
            localStorage.setItem('job',recentJobField.value);
            localStorage.setItem('typeJob',typeJobField.value);
            localStorage.setItem('placeJob',placeJobField.value);
            localStorage.setItem('schoolName',schoolNameField.value);
            localStorage.setItem('firstYear',firstYearField.value);
            localStorage.setItem('lastYear',lastYearField.value);
            localStorage.setItem('birthDay',birthDayField.value);
            localStorage.setItem('birthMonth',birthMonthField.value);
            localStorage.setItem('birthYear',birthYearField.value);
        } else {
            recentJobField.value = '';
            typeJobField.value = '';
            placeJobField.value = '';
            localStorage.setItem('job',recentJobField.value);
            localStorage.setItem('typeJob',typeJobField.value);
            localStorage.setItem('placeJob',placeJobField.value);
            localStorage.setItem('schoolName',schoolNameField.value);
            localStorage.setItem('firstYear',firstYearField.value);
            localStorage.setItem('lastYear',lastYearField.value);
            localStorage.setItem('birthDay',birthDayField.value);
            localStorage.setItem('birthMonth',birthMonthField.value);
            localStorage.setItem('birthYear',birthYearField.value);
        }
        window.location.href = 'login-5.html';
    }
}
