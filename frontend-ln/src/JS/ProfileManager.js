import { estaLogueado, obtenerUsuario, redirigirSiNoEstaLogueado} from './usuarioUtils.js'; 

if(estaLogueado){
    const usuario = JSON.parse(localStorage.getItem('infoUsuario'));

    document.querySelector('.name-title').innerHTML = `${usuario.nombre} ${usuario.apellidos}`;
    document.querySelector('title').innerHTML = `${usuario.nombre} ${usuario.apellidos} | LinkedIn`;
    document.querySelector('.description-container').innerHTML = `${usuario.titular}`;
    document.querySelector('.location-container').innerHTML = `${usuario.ciudad.nombreCiudad}, ${usuario.pais.nombre}`;

    if(usuario.ciudad.ciudadPadre.nombreCiudad != null){
        document.querySelector('.location-container').innerHTML = `${usuario.ciudad.nombreCiudad}, ${usuario.ciudad.ciudadPadre.nombreCiudad}, ${usuario.pais.nombre}`;
    }else{
        document.querySelector('.location-container').innerHTML = `${usuario.ciudad.nombreCiudad}, ${usuario.pais.nombre}`;
    }

    document.querySelector('.link-span').innerHTML = `${usuario.urlPerfil}`;
}{
    redirigirSiNoEstaLogueado();
}
