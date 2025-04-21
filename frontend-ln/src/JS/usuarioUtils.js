export function estaLogueado() {
    return localStorage.getItem('infoUsuario') !== null;
}

export function obtenerCodigoUsuario() {
    const usuario = JSON.parse(localStorage.getItem('infoUsuario'));
    return usuario ? usuario.codigoUsuario : null;
}

export function obtenerUsuario() {
    return JSON.parse(localStorage.getItem('infoUsuario'));
}

export function redirigirSiNoEstaLogueado() {
    if (!estaLogueado()) {
        window.location.href = "login.html";
    }
}