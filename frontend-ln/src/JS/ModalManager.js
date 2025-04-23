import NewEducation from './NewEducation.js';
import NewExperience from './NewExperience.js'

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
const ciudad = document.querySelector('#ciudad');
const pais = document.querySelector('#pais');
const enlace = document.querySelector('#enlace');
const textoEnlace = document.querySelector('#texto-enlace');
const sector = document.querySelector('#sector');

nombre.value = usuario.nombre;
apellidos.value = usuario.apellidos;
nombreAdicional.value = usuario.nombreAdicional != null ? nombreAdicional : '';
titular.value = usuario.titular;
sector.value = usuario.sector != null ? sector: '';
pais.value = usuario.pais.nombre;


//Informacion de contacto
document.querySelector('#name-contact').innerHTML = `${usuario.nombre} ${usuario.apellidos}`;




if (usuario.ciudad.ciudadPadre) {
    ciudad.value = `${usuario.ciudad.nombreCiudad}, ${usuario.ciudad.ciudadPadre.nombreCiudad}`
} else {
    ciudad.value = usuario.ciudad.nombreCiudad;
}



//Eventos
educationButton.addEventListener('click',createNewEducation);
jobButton.addEventListener('click',createNewJob);

//Urls
const urlEducation = 'http://localhost:8080/api/educacion/nuevo';
const urlJob = 'http://localhost:8080/api/experiencia/nuevo';

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
            alert("Error al crear un nuevo registro");
        }
    } catch (error) {
        alert("Error en la solicitud. Revisa la consola.");
        console.error("Error en la solicitud:", error);
    }
}

async function createNewJob(event) {
    event.preventDefault();

    const data = new NewExperience (
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