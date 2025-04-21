const usuario = JSON.parse(localStorage.getItem('infoUsuario'));

document.querySelector('.name-title').innerHTML = `${usuario.nombre} ${usuario.apellidos}`;
document.querySelector('title').innerHTML = `${usuario.nombre} ${usuario.apellidos} | LinkedIn`;
document.querySelector('.description-container').innerHTML = `${usuario.titular}`;
document.querySelector('.location-container').innerHTML = `${usuario.ciudad.nombreCiudad}, ${usuario.pais.nombre}`;
document.querySelector('.link-span').innerHTML = `${usuario.urlPerfil}`;