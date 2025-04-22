import { obtenerCodigoUsuario, redirigirSiNoEstaLogueado } from './usuarioUtils.js'; 

document.addEventListener("DOMContentLoaded", () => {
    redirigirSiNoEstaLogueado();
    cargarPosiblesConexiones();

    fetch('seguirMiRed.html')
        .then(response => response.text())
        .then(data => {
            const containers = document.querySelectorAll('.seguir-container');
            containers.forEach(container => container.innerHTML += data);
        })
        .catch(error => console.error('Error loading contactos:', error));
});

async function cargarPosiblesConexiones() {
    const codigoUsuario = obtenerCodigoUsuario();

    if (!codigoUsuario) {
        alert("Usuario no encontrado. Vuelve a iniciar sesión.");
        return;
    }

    const url = `http://localhost:8080/api/conexiones/sugerencias/${codigoUsuario}`;
    const rowContainer = document.getElementById('contenedor-contactos');
    rowContainer.innerHTML = '';

    try {
        const response = await fetch(url);
        const data = await response.json();

        if (data.length > 0) {
            data.forEach(usuario => {
                const html = crearCardHTML(usuario);
                if (html) rowContainer.innerHTML += html;
            });
            agregarEventosConectar();
            agregarEventosCerrarTarjeta();
        } else {
            rowContainer.innerHTML = "<p>No hay conexiones disponibles.</p>";
        }
    } catch (error) {
        console.error("Error al cargar las conexiones:", error);
    }
}

function agregarEventosCerrarTarjeta() {
    const botonesCerrar = document.querySelectorAll(".btn-close-custom");

    botonesCerrar.forEach(boton => {
        boton.addEventListener("click", () => {
            const tarjeta = boton.closest(".col");
            if (tarjeta) {
                tarjeta.classList.add("fade-out");
                setTimeout(() => tarjeta.remove(), 300);
            }
        });
    });
}

function agregarEventosConectar() {
    const botones = document.querySelectorAll(".btn-conectar");

    botones.forEach(boton => {
        boton.addEventListener("click", async () => {
            const idUsuario1 = obtenerCodigoUsuario();
            const idUsuario2 = boton.getAttribute("data-usuario-id");

            const conexion = {
                usuario1Id: idUsuario1,
                usuario2Id: parseInt(idUsuario2),
                estado: 3
            };

            try {
                const response = await fetch("http://localhost:8080/api/conexiones", {
                    method: "POST",
                    headers: { "Content-Type": "application/json" },
                    body: JSON.stringify(conexion)
                });

                if (response.ok) {
                    const data = await response.json();

                    const nuevoBoton = document.createElement('button');
                    nuevoBoton.className = "btn btn-outline-secondary btn-sm rounded-pill w-100 btn-cancelar";
                    nuevoBoton.innerHTML = '<i class="bi bi-stopwatch"></i> Pendiente';
                    nuevoBoton.addEventListener('click', () => {
                        mostrarCancelarModal(data.codigoConexion);
                    });

                    const tarjeta = boton.closest(".col");
                    if (tarjeta) tarjeta.setAttribute("data-conexion-id", data.codigoConexion);

                    boton.replaceWith(nuevoBoton);
                } else {
                    alert("Error al enviar solicitud");
                }
            } catch (err) {
                console.error("Error al conectar:", err);
            }
        });
    });
}

function crearCardHTML(usuario) {
    if (usuario.estadoConexion === 1) return ''; 
    const baseUrl = 'http://localhost:5501';
    const fotoPortada = usuario.fotoPortada || '';
    const fotoPerfil = usuario.fotoPerfil || '';
    const fotoEmpresa = usuario.fotoEmpresa ? `${baseUrl}${usuario.fotoEmpresa}` : `${baseUrl}/public/Image/fotoEmpresaPorDefecto.png`;

    const botonId = `btn-conectar-${usuario.codigoUsuario}`;
    let botonHTML = '';

    if (usuario.estadoConexion === 3) {
        botonHTML = `
            <button class="btn btn-outline-secondary btn-sm rounded-pill w-100 btn-cancelar"
                onclick="mostrarCancelarModal(${usuario.codigoConexion})">
                <i class="bi bi-stopwatch"></i> Pendiente
            </button>`;
    } else {
        botonHTML = `
            <button class="btn btn-azul btn-sm rounded-pill w-100 btn-conectar" 
                id="${botonId}" 
                data-usuario-id="${usuario.codigoUsuario}">
                <i class="bi bi-person-plus-fill"></i> Conectar
            </button>`;
    }

    return `
        <div class="col" data-conexion-id="${usuario.codigoConexion || ''}">
            <div class="card h-100 card-custom position-relative">
                <div class="position-relative" style="height: 100px;">
                    <img src="${fotoPortada}" class="w-100 rounded-top" alt="Cover Photo">
                    <button class="btn m-2 position-absolute top-0 end-0 btn-close-custom text-light">
                        <i class="bi bi-x"></i>
                    </button>
                    <img src="${fotoPerfil}" class="rounded-circle position-absolute start-50 translate-middle img-contenedor-mired perfil-superpuesto" alt="Profile Photo">
                </div>
                <div class="position-relative mt-5 mb-3">
                    <div class="text-center texto-usuario">
                        <p class="mb-1 fw-bold">${usuario.nombre} ${usuario.apellidos}</p>
                        <p class="mb-1 text-muted">${usuario.titular}</p>
                    </div>
                    <div class="d-flex justify-content-between">
                        <div class="my-3 mx-2 px-2 w-25">
                            <img src="${fotoEmpresa}" alt="Empresa" class="img-responsive img-fluid">
                        </div>
                        <div class="d-flex align-items-center me-3 w-75">
                            <small class="mb-1 text-small text-muted">${usuario.sector}</small>
                        </div>
                    </div>
                    <div class="mx-2">${botonHTML}</div>
                </div>
            </div>
        </div>`;
}

let conexionIdSeleccionada = null;

function mostrarCancelarModal(codigoConexion) {
    conexionIdSeleccionada = codigoConexion;
    const modal = new bootstrap.Modal(document.getElementById('modalCancelar'));
    modal.show();
}

function cerrarModal() {
    if (modalCancelarInstancia) {
        modalCancelarInstancia.hide();
    }
}

async function cancelarSolicitud() {
    if (!conexionIdSeleccionada) return;

    try {
        const response = await fetch(`http://localhost:8080/api/conexiones/cancelar/${conexionIdSeleccionada}`, {
            method: 'PUT'
        });

        if (response.ok) {
            const tarjeta = document.querySelector(`[data-conexion-id="${conexionIdSeleccionada}"]`);
            if (tarjeta) {
                tarjeta.classList.add("fade-out");
                setTimeout(() => tarjeta.remove(), 300);
            }
            cerrarModal();
        } else {
            alert("No se pudo cancelar");
        }
    } catch (error) {
        console.error("Error al cancelar solicitud:", error);
    }

    const modalElement = document.getElementById('modalCancelar');
    const modalInstance = bootstrap.Modal.getInstance(modalElement);
    if (modalInstance) {
      modalInstance.hide();
    }

    cerrarModal();
}
