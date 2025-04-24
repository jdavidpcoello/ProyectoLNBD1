import { obtenerCodigoUsuario, redirigirSiNoEstaLogueado } from './usuarioUtils.js';

document.addEventListener("DOMContentLoaded", async () => {
    const codigoUsuario = obtenerCodigoUsuario();

    try {
        const response = await fetch(`http://localhost:8080/api/usuarios/${codigoUsuario}`);
        if (!response.ok) throw new Error("No se pudo obtener el perfil del usuario");

        const usuario = await response.json();

        document.querySelectorAll(".foto-perfil").forEach(img => {
            img.src = usuario.fotoPerfil;
        });
        
        document.getElementById("foto-portada").src = usuario.fotoPortada;
        document.getElementById("foto-perfil").src = usuario.fotoPerfil;
        document.getElementById("nombre-usuario").textContent = `${usuario.nombre} ${usuario.apellidos}`;
        document.getElementById("info-institucion").textContent = `Fue a la ${usuario.nombreInstitucion || 'Universidad Nacional AUtonoma de Honduras'}`;
        document.getElementById("ubicacion-usuario").textContent = usuario.ubicacion;
        document.getElementById("logo-institucion").src = usuario.fotoInstitucion || '../Image/UNAH.jpg';
        document.getElementById("nombre-institucion").textContent = usuario.nombreInstitucion || 'Universidad Nacional AUtonoma de Honduras';

    } catch (error) {
        console.error("Error al cargar los datos del usuario:", error);
    }
});

