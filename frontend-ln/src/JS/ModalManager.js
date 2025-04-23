import NewEducation from './NewEducation.js';

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


//Eventos
educationButton.addEventListener('click',createNewEducation);

//Urls
const urlEducation = 'http://localhost:8080/api/educacion/nuevo';

//Funciones
async function createNewEducation(event){
    event.preventDefault();

    const data = new NewEducation (
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
            alert("Error al crear nuevo registro");
        }
    } catch (error) {
        console.error("Error en la solicitud:", error);
        alert("Error en la solicitud. Revisa la consola");
    }
}

