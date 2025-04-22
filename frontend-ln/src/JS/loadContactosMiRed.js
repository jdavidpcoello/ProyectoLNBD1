import { obtenerCodigoUsuario, redirigirSiNoEstaLogueado } from './usuarioUtils.js'; 

document.addEventListener("DOMContentLoaded", () => {
    redirigirSiNoEstaLogueado();
    const codigoUsuario = obtenerCodigoUsuario();
    cargarPosiblesConexiones();
    cargarSolicitudesRecibidas(codigoUsuario); 

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
                usuario1: idUsuario1,
                usuario2: parseInt(idUsuario2),
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

                    const nuevoBoton = crearBotonPendiente(data.codigoConexion);

                    const tarjeta = boton.closest(".col");
                    if (tarjeta) {
                        tarjeta.setAttribute("data-conexion-id", data.codigoConexion);
                        boton.replaceWith(nuevoBoton);
                    }

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
window.conexionIdSeleccionada = null;

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
                const nuevoBoton = document.createElement('button');
                nuevoBoton.className = "btn btn-azul btn-sm rounded-pill w-100 btn-conectar";
                nuevoBoton.setAttribute("data-usuario-id", tarjeta.getAttribute("data-usuario-id"));
                nuevoBoton.innerHTML = '<i class="bi bi-person-plus-fill"></i> Conectar';
        
                nuevoBoton.addEventListener("click", async () => {
                    tarjeta.querySelector(".btn-cancelar")?.replaceWith(crearBotonPendiente(conexionIdSeleccionada));
                    await agregarEventosConectar();
                });
        
                const botonActual = tarjeta.querySelector(".btn-cancelar");
                if (botonActual) botonActual.replaceWith(nuevoBoton);
            }
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
}

window.cancelarSolicitud = cancelarSolicitud;
window.mostrarCancelarModal = mostrarCancelarModal;

function crearBotonPendiente(codigoConexion) {
    const botonPendiente = document.createElement('button');
    botonPendiente.className = "btn btn-outline-secondary btn-sm rounded-pill w-100 btn-cancelar";
    botonPendiente.innerHTML = '<i class="bi bi-stopwatch"></i> Pendiente';
    botonPendiente.addEventListener('click', () => {
        mostrarCancelarModal(codigoConexion);
    });
    return botonPendiente;
}


async function cargarSolicitudesRecibidas(codigoUsuario) {
    const url = `http://localhost:8080/api/conexiones/solicitudes/${codigoUsuario}`;
    const contenedor = document.getElementById("listaSolicitudes");

    try {
        const response = await fetch(url);
        const solicitudes = await response.json();

        if (solicitudes.length === 0) {
            contenedor.innerHTML = "<p>No tienes solicitudes de conexión.</p>";
            return;
        }

        solicitudes.slice(0, 3).forEach(solicitud => {

            let institucionEmpresaHTML = "";
            if (
                (solicitud.fotoInstitucion && solicitud.nombreInstitucion) ||
                (solicitud.fotoEmpresa && solicitud.nombreEmpresa)
            ) {
            institucionEmpresaHTML = `
                <div class="d-flex align-items-start mt-2">
                    <img 
                        src="${solicitud.fotoInstitucion && solicitud.nombreInstitucion 
                                ? solicitud.fotoInstitucion 
                                : solicitud.fotoEmpresa}" 
                        alt="Organización"
                        class="me-2 rounded"
                        style="width: 30px; height: 30px; object-fit: cover;"
                    >
                    <p class="mb-1 small text-muted">
                        ${solicitud.fotoInstitucion && solicitud.nombreInstitucion 
                            ? solicitud.nombreInstitucion 
                            : solicitud.nombreEmpresa}
                    </p>
                </div>`;
            }

            const div = document.createElement("div");
            div.className = "d-flex justify-content-between align-items-center mb-2";
            div.innerHTML = `
                <a class="d-flex col text-decoration-none" href="../Pages/invitation-manager.html">
                    <div class="col-md-1 mx-3">
                        <img 
                            src=${solicitud.fotoPerfil} class="img-fluid rounded-circle" 
                            style="width: 200px;
                            object-fit: cover;" 
                        >
                    </div>
                    <div>
                        <p class="m-0 text-dark"><strong>${solicitud.nombre} ${solicitud.apellidos}</strong></p>
                        <p class="m-0 small text-muted">${solicitud.titular || ""}</p>
                        
                        ${institucionEmpresaHTML}

                    </div>
                </a>
                <div class="d-flex flex-row gap-3">
                    <button class="btn btn-light" onclick="ignorarSolicitud(${solicitud.codigoConexion})">Ignorar</button>
                    <button class="btn btn-azul rounded-pill btn-conectar me-3" onclick="aceptarSolicitud(${solicitud.codigoConexion})">Aceptar</button>
                </div>
                
            `;
            const hr = document.createElement("hr");
            contenedor.appendChild(div);
            contenedor.appendChild(hr);
        });
    } catch (error) {
        console.error("Error al cargar solicitudes:", error);
    }
}

async function aceptarSolicitud(codigoConexion) {
    try {
        const response = await fetch(`http://localhost:8080/api/conexiones/aceptar/${codigoConexion}`, {
            method: "PUT"
        });
        if (response.ok) {
            alert("Solicitud aceptada");
            location.reload();
        }
    } catch (e) {
        console.error("Error al aceptar:", e);
    }
}

async function ignorarSolicitud(codigoConexion) {
    try {
        const response = await fetch(`http://localhost:8080/api/conexiones/eliminar/${codigoConexion}`, {
            method: "DELETE"
        });
        if (response.ok) {
            alert("Solicitud ignorada");
            location.reload();
        }
    } catch (e) {
        console.error("Error al ignorar:", e);
    }
}

window.aceptarSolicitud = aceptarSolicitud;
window.ignorarSolicitud = ignorarSolicitud;

