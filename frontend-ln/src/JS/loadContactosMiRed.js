document.addEventListener("DOMContentLoaded", function() {
    fetch('seguirMiRed.html')
        .then(response => response.text())
        .then(data => {
            const containers = document.querySelectorAll('.seguir-container');
            containers.forEach(container => {
                container.innerHTML += data;
            });
        })
        .catch(error => console.error('Error loading contactos:', error));
});


document.addEventListener("DOMContentLoaded", function () {
    cargarPosiblesConexiones();
});



async function cargarPosiblesConexiones() {
    //Recordatorio: Cambiar la URL según el usuario logueado
    const url = 'http://localhost:8080/api/conexiones/sugerencias/1';
    const rowContainer = document.getElementById('contenedor-contactos');

    try {
        const response = await fetch(url);
        
        if (!response.ok) {
            throw new Error("Error al cargar usuarios :c");
        }

        const usuarios = await response.json();

        rowContainer.innerHTML = '';

        for (const usuario of usuarios) {
            const cardHTML = crearCardHTML(usuario);

            const contactoDiv = document.createElement('div');
            contactoDiv.classList.add('contacto-container');
            

            contactoDiv.innerHTML += cardHTML;

            rowContainer.appendChild(contactoDiv);
            // const cardHTML = crearCardHTML(usuario);
            // rowContainer.innerHTML += cardHTML;
        }

        agregarEventosConectar();
        agregarEventosCerrarTarjeta();

    } catch (error) {
        alert('💔 F bebé: ' + error.message);
        console.error(error);
    }
}

function agregarEventosCerrarTarjeta() {
    const botonesCerrar = document.querySelectorAll(".btn-close-custom");

    botonesCerrar.forEach(boton => {
        boton.addEventListener("click", () => {
           const tarjeta = boton.closest(".card-custom");
            if (tarjeta) {
                tarjeta.classList.add("fade-out");

                setTimeout(() => {
                    tarjeta.remove();
                }, 300); 
            }
        });
    });
}

function agregarEventosConectar() {
    const botones = document.querySelectorAll(".btn-conectar");

    botones.forEach(boton => {
        boton.addEventListener("click", async () => {
            // ! Recordar cambiar el idUsuario1 por el id del usuario logueado dinamicamente
            const idUsuario1 = 1;
            const idUsuario2 = boton.getAttribute("data-usuario-id");

            const conexion = {
                usuario1Id: idUsuario1,
                usuario2Id: parseInt(idUsuario2),
                estado: 3
            };

            try {
                const response = await fetch("http://localhost:8080/api/conexiones", {
                    method: "POST",
                    headers: {
                        "Content-Type": "application/json"
                    },
                    body: JSON.stringify(conexion)
                });

                if (response.ok) {
                    boton.innerHTML = '<i class="bi bi-stopwatch"></i> Pendiente';
                    boton.disabled = true;
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
    console.log('Estado de conexión de usuario:', usuario.estadoConexion); 
    const baseUrl = 'http://localhost:5501';

    const fotoPortada = usuario.fotoPortada ? `data:image/jpeg;base64,${usuario.fotoPortada}` : '';
    const fotoPerfil = usuario.fotoPerfil ? `data:image/png;base64,${usuario.fotoPerfil}` : '';
    const fotoTitular = usuario.fotoTitularUrl ? `${baseUrl}${usuario.fotoTitularUrl}` : '';

    const botonId = `btn-conectar-${usuario.codigoUsuario}`;

    let botonHTML = '';

    if (usuario.estadoConexion === 1) {
        return '';
    }

    if (usuario.estadoConexion === 3) {
        botonHTML = `
            <button class="btn btn-outline-secondary btn-sm rounded-pill w-100 btn-cancelar"
                onclick="mostrarCancelarModal(${usuario.codigoConexion})">
                <i class="bi bi-stopwatch"></i> Pendiente
            </button> 
            
        `;
    } else {
        botonHTML = `
            <button class="btn btn-azul btn-sm rounded-pill w-100 btn-conectar" 
                id="${botonId}" 
                data-usuario-id="${usuario.codigoUsuario}">
                <i class="bi bi-person-plus-fill"></i>
                <span>Conectar</span>
            </button>
        `;
    }

    return `
    <div class="col card-custom">
        <div class="card">
            <div class="position-relative " style="height: 100px;">
                <div class="position-relative">
                    <img src="${fotoPortada}" class="w-100 rounded-top" alt="Cover Photo">
                    <button class="btn m-2 position-absolute top-0 end-0 btn-close-custom text-light"><i class="bi bi-x"></i></button>
                </div>
                <div class="mb-5">
                    <img src="${fotoPerfil}" class="rounded-circle position-absolute start-50 translate-middle img-contenedor-mired" alt="Profile Photo">
                </div>
            </div>
            <div class="position-relative mt-5 mb-3">
                <div class="text-center texto-usuario">
                    <p class="mb-1 fw-bold">${usuario.nombre} ${usuario.apellidos}</p>
                    <p class="mb-1 text-muted">${usuario.titular}</p>
                </div> 
                <div class="d-flex justify-content-between">
                    <div class="my-3 mx-2 px-2 w-25">
                        <img src="${fotoTitular}" alt="Titular" class="img-responsive img-fluid">
                    </div>
                    <div class="d-flex align-items-center me-3 w-75">
                        <small class="mb-1 text-small text-muted">${usuario.sector}</small>
                    </div>
                </div>
                <div class="mx-2">
                    ${botonHTML}
                </div>
            </div>
        </div>
    </div>
    `;
}

let conexionIdSeleccionada = null;

function mostrarCancelarModal(codigoConexion) {
    console.log("Codigo de conexion:", codigoConexion);

    conexionIdSeleccionada = codigoConexion;
    document.getElementById('modalCancelar').style.display = 'flex';
}

function cerrarModal() {
    document.getElementById('modalCancelar').style.display = 'none';
}


async function cancelarSolicitud() {
    if (!conexionIdSeleccionada) return;

    try {
        const response = await fetch(`http://localhost:8080/api/conexiones/cancelar/${conexionIdSeleccionada}`, {
            method: 'PUT'
        });

        if (response.ok) {
            alert("Solicitud cancelada");
            location.reload();
        } else {
            alert("No se pudo cancelar");
        }
    } catch (error) {
        console.error("Error al cancelar solicitud:", error);
    }

    cerrarModal();
}