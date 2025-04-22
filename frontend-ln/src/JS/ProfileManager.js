import { estaLogueado, obtenerUsuario, redirigirSiNoEstaLogueado } from './usuarioUtils.js';

if (estaLogueado()) {
    //Declaraciones
    const usuario = JSON.parse(localStorage.getItem('infoUsuario'));
    const profileInput = document.querySelector('#pp-file');
    const backgroundInput = document.querySelector('#bck-file');
    const educationList = document.querySelector('#education-list');


    //Asignar valores correspondientes
    document.querySelector('.name-title').innerHTML = `${usuario.nombre} ${usuario.apellidos}`;
    document.querySelector('title').innerHTML = `${usuario.nombre} ${usuario.apellidos} | LinkedIn`;
    document.querySelector('.description-container').innerHTML = `${usuario.titular}`;

    if (usuario.ciudad.ciudadPadre) {
        document.querySelector('.location-container').innerHTML = `${usuario.ciudad.nombreCiudad}, ${usuario.ciudad.ciudadPadre.nombreCiudad}, ${usuario.pais.nombre}`;
    } else {
        document.querySelector('.location-container').innerHTML = `${usuario.ciudad.nombreCiudad}, ${usuario.pais.nombre}`;
    }

    document.querySelector('.link-span').innerHTML = `${usuario.urlPerfil}`;


    document.querySelector('#profile-photo').setAttribute('src', usuario.fotoPerfil);

    if (usuario.fotoPortada != null) {
        document.querySelector('#background-photo').setAttribute('src', usuario.fotoPortada);
    }
    else {
        document.querySelector('#background-photo').setAttribute('src', '../Image/DefaultBackground.jpg');
    }

    //Eventos
    profileInput.addEventListener('change', (event) => {
        const selectedFile = event.target.files[0];

        if (selectedFile) {
            const reader = new FileReader();

            reader.addEventListener('load', function () {
                newPhoto(reader.result, 0);
            });

            reader.readAsDataURL(selectedFile);
        }
    });



    backgroundInput.addEventListener('change', (event) => {
        const selectedFile = event.target.files[0];

        if (selectedFile) {
            const reader = new FileReader();

            reader.addEventListener('load', function () {
                newPhoto(reader.result, 1);
            });

            reader.readAsDataURL(selectedFile);
        }
    });

    window.addEventListener("DOMContentLoaded", load);



    //Funciones
    async function newPhoto(result, i) {
        if (i === 0) {
            const url = 'http://localhost:8080/api/usuarios/newProfilePhoto';

            const data = {
                codigoUsuario: usuario.codigoUsuario,
                fotoPerfil: result
            };

            try {
                const response = await fetch(url, {
                    method: "PUT",
                    headers: {
                        "Content-Type": "application/x-www-form-urlencoded"
                    },
                    body: new URLSearchParams(data).toString()
                });

                if (response.ok) {
                    usuario.fotoPerfil = result;
                    document.querySelector('#profile-photo').setAttribute('src', result);
                    localStorage.setItem('infoUsuario', JSON.stringify(usuario));
                }
                else {
                    alert('Error');
                }
            } catch (error) {
                console.error("Error en la solicitud:", error);
            }
        }else if(i===1) {
            const url = 'http://localhost:8080/api/usuarios/newBackgroundPhoto';

            const data = {
                codigoUsuario: usuario.codigoUsuario,
                fotoPortada: result
            };

            try {
                const response = await fetch(url, {
                    method: "PUT",
                    headers: {
                        "Content-Type": "application/x-www-form-urlencoded"
                    },
                    body: new URLSearchParams(data).toString()
                });

                if (response.ok) {
                    usuario.fotoPortada = result;
                    document.querySelector('#background-photo').setAttribute('src', usuario.fotoPortada);
                    localStorage.setItem('infoUsuario', JSON.stringify(usuario));
                }
                else {
                    alert('Error');
                }
            } catch (error) {
                console.error("Error en la solicitud:", error);
            }
        }

    }

    async function load() {
        EducationList();
    }
    

    async function JobsList() {
        
    }

    async function EducationList() {
        const url = 'http://localhost:8080/api/educacion/usuario/obtenertodo';
    
        const data = {
            codigoUsuario: usuario.codigoUsuario
        };
    
        try {
            const response = await fetch(url, {
                method: "POST",
                headers: {
                    "Content-Type": "application/x-www-form-urlencoded"
                },
                body: new URLSearchParams(data).toString()
            });
    
            const educationData = await response.json();
    
            if (response.ok && Array.isArray(educationData)) {
                const educationList = document.getElementById("education-list");
                educationList.innerHTML = "";
    
                educationData.forEach(item => {
                    if (item !== null) {
                        const li = document.createElement("li");
                        li.className = "education-items";
    
                        li.innerHTML = `
                            <h2 class="school-name"><a href="#">${item.institucionEducativa.nombreInstitucion || ''}</a></h2>
                            <h3 class="degree">${item.titulo != null ? item.titulo : ''}</h3>
                            <p class="time">${item.anioInicio != null ? item.anioInicio : ''} - ${item.anioFinal != null ? item.anioFinal : ''}</p>
                        `;
    
                        educationList.appendChild(li);
                    }
                });
            }
        } catch (error) {
            console.error("Error en la solicitud:", error);
        }
    }

}
