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
        }else {
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


    async function EducationList(){
        const url = 'http://localhost:8080/api/usuarios/newBackgroundPhoto';

        let data = {
            codigouUsuario: usuario.codigoUsuario
        };

        try {
            const response = await fetch(url, {
                method: "GET",
                headers: {
                    "Content-Type": "application/x-www-form-urlencoded"
                },
                body: new URLSearchParams(data).toString()
            });

            const responseData = await response.json();
                        
            if (response.ok) {

                let list = JSON.parse(responseData);

                educationList.innerHTML = 
                `<li id="ed-1" class="education-items">
                    <h2 class="school-name"><a href=""></a></h2>
                    <h3 class="degree">Diseñador grafico.</h3>
                    <p class="time">2003-2009</p>
                </li>`
            }
            else {
                alert('Error');
            }
        } catch (error) {
            console.error("Error en la solicitud:", error);
        }

    }
}
