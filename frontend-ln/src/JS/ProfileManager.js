import { estaLogueado, obtenerUsuario, redirigirSiNoEstaLogueado} from './usuarioUtils.js'; 

if(estaLogueado()){
    const usuario = JSON.parse(localStorage.getItem('infoUsuario'));

    document.querySelector('.name-title').innerHTML = `${usuario.nombre} ${usuario.apellidos}`;
    document.querySelector('title').innerHTML = `${usuario.nombre} ${usuario.apellidos} | LinkedIn`;
    document.querySelector('.description-container').innerHTML = `${usuario.titular}`;

    if(usuario.ciudad.ciudadPadre){
        document.querySelector('.location-container').innerHTML = `${usuario.ciudad.nombreCiudad}, ${usuario.ciudad.ciudadPadre.nombreCiudad}, ${usuario.pais.nombre}`;
    }else{
        document.querySelector('.location-container').innerHTML = `${usuario.ciudad.nombreCiudad}, ${usuario.pais.nombre}`;
    }

    document.querySelector('.link-span').innerHTML = `${usuario.urlPerfil}`;


    document.querySelector('#profile-photo').setAttribute('src',usuario.fotoPerfil);

    if(usuario.fotoPortada != null){
        document.querySelector('#background-photo').setAttribute('src',usuario.fotoPortada);
    }
    else{
        document.querySelector('#background-photo').setAttribute('src','../Image/DefaultBackground.jpg');
    }

    const profilePhoto = document.querySelector('#profilePhoto');
const backgroundPhoto = document.querySelector('#background-photo');
const profileInput = document.querySelector('#pp-file');
const backgroundInput = document.querySelector('#bck-file');

profileInput.addEventListener('change', uploadNewFile.bind(profileInput, profilePhoto, 'profilePhoto'));
backgroundInput.addEventListener('change', uploadNewFile.bind(backgroundInput, backgroundPhoto, 'backgroundPhoto'));

function uploadNewFile(imgElement, storageKey) {
    const selectedFile = this.files[0];
    if (selectedFile) {
        if (!validareForm(this)) return;

        const reader = new FileReader();
        reader.addEventListener('load', function () {
            newPhoto(imgElement, storageKey, reader.result);
        });

        reader.readAsDataURL(selectedFile);
    }
}

function validareForm(input) {
    const validTypes = ['image/jpeg', 'image/png', 'image/jpg'];
    const file = input.files[0];

    if (!validTypes.includes(file.type)) {
        alert('Sube una foto válida (jpg, jpeg o png).');
        return false;
    }

    return true;
}

function newPhoto(imgElement, key, result) {
    imgElement.setAttribute('src', result);
    localStorage.setItem(key, result);
}


}{
    redirigirSiNoEstaLogueado();
}
