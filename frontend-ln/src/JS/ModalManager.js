import NewEducation from './NewEducation.js';
import NewExperience from './NewExperience.js'
import UpdateUser from './UpdateUser.js'
import RegexValidator from './RegexValidator.js';


//Modales

//
const usuario = JSON.parse(localStorage.getItem('infoUsuario'));
const codigoUsuario = usuario.codigoUsuario;

//EducationModal ADD
const nombreEscuela = document.querySelector('#nombre-escuela');
const grado = document.querySelector('#degree');
const mesInicioEducation = document.querySelector('#mes-inicio-education');
const anioInicioEducation = document.querySelector('#anio-inicio-education');
const mesFinalEducation = document.querySelector('#mes-final-education');
const anioFinalEducation = document.querySelector('#anio-final-education');
const titulo = document.querySelector('#titulo');
const nota = document.querySelector('#nota');
const actividades = document.querySelector('#actividades');
const educationDescription = document.querySelector('#education-description');
const educationButton = document.querySelector('#education-btn');


//JobModal ADD
const cargo = document.querySelector('#cargo');
const empresa = document.querySelector('#empresa');
const mesInicioJob = document.querySelector('#mes-inicio-job');
const anioInicioJob = document.querySelector('#anio-inicio-job');
const mesFinalJob = document.querySelector('#mes-final-job');
const anioFinalJob = document.querySelector('#anio-final-job');
const tipoEmpleo = document.querySelector('#tipo-empleo');
const tipoLugarTrabajo = document.querySelector('#tipo-lugar-trabajo');
const jobDescription = document.querySelector('#job-description');
const jobButton = document.querySelector('#job-btn');


//Personal ADD
const nombre = document.querySelector('#nombre');
const apellidos = document.querySelector('#apellidos');
const nombreAdicional = document.querySelector('#nombre-adicional');
const titular = document.querySelector('#titular');
const locationField = document.querySelector('#location');
const enlace = document.querySelector('#enlace');
const textoEnlace = document.querySelector('#texto-enlace');
const tipoWeb = document.querySelector('#tipo-web');
const sector = document.querySelector('#sector');
const userButton = document.querySelector('#user-btn');




nombre.value = usuario.nombre;
apellidos.value = usuario.apellidos;
titular.value = usuario.titular;
nombreAdicional.value = usuario.nombreAdicional != null ? usuario.nombreAdicional : '';
sector.value = usuario.sector != null ? usuario.sector : '';


if (usuario.ciudad.ciudadPadre) {
    locationField.value = `${usuario.ciudad.nombreCiudad}, ${usuario.ciudad.ciudadPadre.nombreCiudad}, ${usuario.pais.nombre}`;
} else {
    locationField.value = `${usuario.ciudad.nombreCiudad}, ${usuario.pais.nombre}`;
}

//Informacion de contacto
document.querySelector('#name-contact').innerHTML = `${usuario.nombre} ${usuario.apellidos}`;



//Eventos
educationButton.addEventListener('click', createNewEducation);
jobButton.addEventListener('click', createNewJob);
userButton.addEventListener('click', updateInfoUser);

//Urls
const urlEducation = 'http://localhost:8080/api/educacion/nuevo';
const urlJob = 'http://localhost:8080/api/experiencia/nuevo';
const urlUser = 'http://localhost:8080/api/usuarios/actualizar';

//Funciones
async function createNewEducation(event) {
    event.preventDefault();

    const data = new NewEducation(
        titulo.value,
        grado.value,
        anioInicioEducation.value,
        anioFinalEducation.value,
        mesInicioEducation.value,
        mesFinalEducation.value,
        nota.value,
        actividades.value,
        nombreEscuela.value,
        codigoUsuario,
        educationDescription.value
    )

    try {
        const response = await fetch(urlEducation, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data)
        });

        if (response.ok) {
            location.reload();
        } else {
            const errorData = await response.json();
            alert("Error al crear un nuevo registro");
        }
    } catch (error) {
        alert("Error en la solicitud. Revisa la consola.");
        console.error("Error en la solicitud:", error);
    }
}

async function createNewJob(event) {

    let validez = validateForm(1);

    if (validez) {
        event.preventDefault();

        const data = new NewExperience(
            cargo.value,
            empresa.value,
            mesInicioJob.value,
            anioInicioJob.value,
            mesFinalJob.value,
            anioFinalJob.value,
            tipoEmpleo.value,
            tipoLugarTrabajo.value,
            jobDescription.value,
            codigoUsuario
        )


        try {
            const response = await fetch(urlJob, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(data)
            });


            if (response.ok) {
                location.reload();
            } else {
                const errorData = await response.json();
                alert("Error al crear un nuevo registro");
            }

        } catch (error) {
            alert("Error en la solicitud. Revisa la consola.");
            console.error("Error en la solicitud:", error);
        }
    }

}



async function updateInfoUser(event) {
    event.preventDefault();

    let validez = validateForm(0);
    asignCountryandCity(locationField.value);

    if (validez) {
        const data = new UpdateUser(
            nombre.value,
            apellidos.value,
            nombreAdicional.value,
            titular.value,
            localStorage.getItem('city'),
            localStorage.getItem('city2'),
            localStorage.getItem('country'),
            enlace.value,
            tipoWeb.value,
            textoEnlace.value,
            sector.value,
            codigoUsuario
        );


        try {
            const response = await fetch(urlUser, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(data)
            });

            if (response.ok) {
                const responseData = await response.json();
                localStorage.setItem('infoUsuario', JSON.stringify(responseData));
                location.reload();
            } else {
                const errorData = await response.json();
                alert("Error al actualizar registro");
            }
        } catch (error) {
            alert("Error en la solicitud. Revisa la consola.");
            console.error("Error en la solicitud:", error);
        }
    }

}

function asignCountryandCity(locationField) {
    const parts = locationField.split(',').map(p => p.trim());

    if (parts.length >= 3) {
        const ciudad = parts.slice(0, parts.length - 2).join(', ');
        const ciudad2 = parts[parts.length - 2];
        const pais = parts[parts.length - 1];

        localStorage.setItem('city', ciudad);
        localStorage.setItem('city2', ciudad2);
        localStorage.setItem('country', pais);
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


function validateForm(i) {
    if (i === 0) {
        const invalidName = document.querySelector('.name-invalid');
        const invalidLastName = document.querySelector('.last-name-invalid');
        const invalidTitular = document.querySelector('.titular-invalid');
        const locationDataList = document.querySelector('#location-list');
        const invalidLocation = document.querySelector('.location-invalid');

        nombre.classList.remove('invalid-input');
        apellidos.classList.remove('invalid-input');
        titular.classList.remove('invalid-input');
        locationField.classList.remove('invalid-input');

        invalidName.innerHTML = '';
        invalidLastName.innerHTML = '';
        invalidTitular.innerHTML = '';
        invalidLocation.innerHTML = '';



        if (RegexValidator.isEmpty(nombre.value)) {
            nombre.classList.add('invalid-input');
            invalidName.innerHTML = 'Ingresa un nombre.';
            return false;
        } else if (RegexValidator.isEmpty(apellidos.value)) {
            apellidos.classList.add('invalid-input');
            invalidLastName.innerHTML = 'Ingresa un apellido.';
            return false;
        } else if (RegexValidator.isEmpty(titular.value)) {
            titular.classList.add('invalid-input');
            invalidTitular.innerHTML = 'Ingresa el titular.';
            return false;
        } else if (RegexValidator.isEmpty(locationField.value)) {
            locationField.classList.add('invalid-input');
            invalidLocation.innerHTML = 'Ingresa tu ubicacion';
            return false;
        } else if (!RegexValidator.isValidName(nombre.value)) {
            locationField.classList.add('invalid-input');
            invalidLastName.innerHTML = 'Ingresa un nombre valido.';
            return false;
        }
        else if (!RegexValidator.isValidName(apellidos.value)) {
            apellidos.classList.add('invalid-input');
            invalidLastName.innerHTML = 'Ingresa un apellido valido.';
            return false;
        } else if (!Array.from(locationDataList.options).some(option => option.value === locationField.value)) {
            locationField.classList.add('invalid-input');
            invalidLocation.innerHTML = 'Ingresa una ubicacion valida.';
            return false;
        }
    }
    if (i === 1) {
        const cargoInvalid = document.querySelector('.cargo-invalid');
        const tipoEmpleoInvalid = document.querySelector('.tipo-empleo-invalid');
        const empresaInvalid = document.querySelector('.empresa-invalid');
        const empresaDataList = document.querySelector('#company-list')
        const dateInvalid = document.querySelector('.date-job-invalid');
        const lugarTrabajoInvalid = document.querySelector('.tipo-lugar-trabajo-invalid');

        cargo.classList.remove('invalid-input');
        empresa.classList.remove('invalid-input');

        cargoInvalid.value = '';
        tipoEmpleoInvalid.value = '';
        empresaInvalid.value = '';
        dateInvalid.value = '';
        lugarTrabajoInvalid.value = ''

        if (RegexValidator.isEmpty(cargo.value)) {
            cargo.classList.add('invalid-input');
            cargoInvalid.innerHTML = 'Ingrese un cargo';
            return false;
        } else if (tipoEmpleo.value === '') {
            tipoEmpleoInvalid.innerHTML = 'Ingresa un opcion valida.';
            return false;
        } else if (RegexValidator.isEmpty(empresa.value)) {
            empresa.classList.add('invalid-input');
            empresaInvalid.innerHTML = 'Ingrese un nombre de empresa.';
            return false;
        } else if (mesInicioJob.value === '' || anioInicioJob.value==='') {
            dateInvalid.innerHTML = 'Campos de mes y año obligatorios';
            return false;
        } else if (!Array.from(empresaDataList.options).some(option => option.value === empresa.value)) {
            empresa.classList.add('invalid-input');
            empresaInvalid.innerHTML = 'Ingrese un nombre de empresa valida.';
            return false;
        }

    }
    return true;
}
