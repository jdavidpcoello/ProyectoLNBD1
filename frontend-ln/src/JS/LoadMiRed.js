document.addEventListener("DOMContentLoaded", function () {
    // Recordatorio: Cambiar la URL según el usuario logueado
    const codigoUsuario = 1;
    const url = `http://localhost:8080/api/conexiones/amigos/${codigoUsuario}`;
    const container = document.getElementById("contactosContainer");

    fetch(url)
        .then(response => response.json())
        .then(data => {
            if (data.length === 0) {
                container.innerHTML = "<p>No tienes contactos aún.</p>";
                return;
            }

            const contador = document.getElementById("contadorContactos");
            contador.textContent = `${data.length} contactos`;

            data.forEach(contacto => {
                const html = `
                    <div class="d-flex justify-content-between mb-3">
                        <div class="col-md-1">
                            <img src="../Image/profile1.jpg" class="img-responsive rounded-circle" alt="#">
                        </div>
                        <div class="col-md-7 d-flex flex-column">
                            <p class="m-0">${contacto.nombre} ${contacto.apellidos}</p>
                            <p class="m-0">${contacto.titular || "Sin descripción"}</p>
                            <p class="text-muted m-0">contacto desde: ${formatearFecha(contacto.fechaConexion)}</p>
                        </div>
                        <div class="col-md-3 mt-3">
                            <div class="d-flex justify-content-end gap-3">
                                <a class="btn btn-azul btn-sm rounded-pill w-75" href="../Pages/messages.html">
                                    <span>Enviar mensaje</span>
                                </a>

                                <button class="nav-link me-3 icono-grey" type="button" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                    <i class="bi bi-three-dots"></i>
                                </button>
                                <div class="dropdown-menu dropdown-menu-right shadow mt-2" style="width: 256px;">
                                    <button class="dropdown-item" type="button" onclick="eliminarContacto(${contacto.codigoConexion})">
                                        <i class="bi bi-trash3-fill"></i><small> Eliminar contacto</small>
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>
                    <hr>
                `;
                container.innerHTML += html;
            });
        })
        .catch(error => {
            console.error("Error al cargar los contactos:", error);
            container.innerHTML = "<p>Error al cargar los contactos.</p>";
        });

    function formatearFecha(fechaISO) {
        const fecha = new Date(fechaISO);
        if (isNaN(fecha)) {
            console.error('Fecha inválida:', fechaISO);
            return 'Fecha inválida';
        }
        const opciones = { day: 'numeric', month: 'long', year: 'numeric' };
        return fecha.toLocaleDateString('es-ES', opciones);
    }
});



function eliminarContacto(codigoConexion) {
    if (!confirm("¿Estás seguro de que deseas eliminar este contacto?")) return;

    fetch(`http://localhost:8080/api/conexiones/eliminar/${codigoConexion}`, {
        method: "DELETE",
    })
    .then(response => {
        if (response.ok) {
            alert("Contacto eliminado con éxito");
            location.reload();
        } else {
            alert("No se pudo eliminar el contacto.");
        }
    })
    .catch(error => {
        console.error("Error al eliminar el contacto:", error);
    });
}
