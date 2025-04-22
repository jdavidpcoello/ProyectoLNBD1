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
    
    function validareForm(){
        const invalidPhotoDiv = document.querySelector('.invalid-photo-text');
        invalidPhotoDiv.innerHTML = '';
        const validTypes = ['image/jpeg', 'image/png', 'image/jpg'];
    
    
        const file = photoInput.files[0];
    
        if(!file){
            invalidPhotoDiv.innerHTML = 'Suba una foto de perfil.';
            return false;
        } else if (!validTypes.includes(file.type)) {
            invalidPhotoDiv.innerHTML = 'Sube una foto valida.';
            return false;
        }
    
        return true;
    }

}{
    redirigirSiNoEstaLogueado();
}
