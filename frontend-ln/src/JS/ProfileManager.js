import { estaLogueado, obtenerUsuario, redirigirSiNoEstaLogueado } from './usuarioUtils.js';

if (estaLogueado()) {
    const usuario = JSON.parse(localStorage.getItem('infoUsuario'));

    console.log(usuario.codigoUsuario);

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

    const profilePhoto = document.querySelector('#profilePhoto');
    const backgroundPhoto = document.querySelector('#background-photo');
    const profileInput = document.querySelector('#pp-file');
    const backgroundInput = document.querySelector('#bck-file');

    profileInput.addEventListener('change', (event) => {
        const selectedFile = event.target.files[0];
    
        if (selectedFile) {
            const reader = new FileReader();
    
            reader.addEventListener('load', function () {
                newPhoto(reader.result);
            });
    
            reader.readAsDataURL(selectedFile);
        }
    });
    


    backgroundInput.addEventListener('change', (event) => {
        const selectedFile = event.target.files[0];
    
        if (selectedFile) {
            const reader = new FileReader();
    
            reader.addEventListener('load', function () {
                newPhoto(reader.result);
            });
    
            reader.readAsDataURL(selectedFile);
        }
    });

    
    
    async function newPhoto(result) {
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
    }
}
