// document.addEventListener("DOMContentLoaded", function() {
//     fetch('contactoMiRed.html')
//         .then(response => response.text())
//         .then(data => {
//             const containers = document.querySelectorAll('.contacto-container');
//             containers.forEach(container => {
//                 container.innerHTML += data;
//             });
//         })
//         .catch(error => console.error('Error loading contactos:', error));
// });

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
    cargarConexiones();
});

async function cargarConexiones() {
    const url = 'http://localhost:8080/api/conexiones/usuario/1';
    const container = document.querySelector('.contacto-container');

    try {
        const response = await fetch(url);
        
        if (!response.ok) {
            throw new Error("Error al cargar conexiones 😥");
        }

        for (const conexion of conexiones) {

            const usuario1Response = await fetch(`http://localhost:8080/api/usuarios/${conexion.usuario1Id}`);
            const usuario1 = await usuario1Response.json();

            const usuario2Response = await fetch(`http://localhost:8080/api/usuarios/${conexion.usuario2Id}`);
            const usuario2 = await usuario2Response.json();

            const usuarioAmostrar = usuario1.id === 1 ? usuario2 : usuario1;

            const cardHTML = crearCardHTML(usuarioAmostrar);
            container.innerHTML += cardHTML;
        }

    } catch (error) {
        alert('💔 F bebé: ' + error.message);
        console.error(error);
    }
}

function crearCardHTML(usuario) {
    return `
    <div class="col">
        <div class="bg-white pt-0 border rounded">
            <div class="position-relative " style="height: 100px;">
                <div class="position-relative">
                    <img src="${usuario.fotoPortadaUrl}" class="w-100 rounded-top" alt="Cover Photo">
                    <button class="btn m-2 position-absolute top-0 end-0 btn-sombreado text-light"><i class="bi bi-x"></i></button>
                </div>
                <div class="mb-5">
                    <img src="${usuario.fotoPerfilUrl}" class="rounded-circle position-absolute start-50 translate-middle img-contenedor-mired" alt="Profile Photo">
                </div>
            </div>
            <div class="position-relative mt-5 mb-3">
                <div class="text-center">
                    <p class="mb-1 fw-bold">${usuario.nombre} ${usuario.apellidos}</p>
                    <p class="mb-1 text-muted">${usuario.titular}</p>
                </div> 
                <div class="d-flex justify-content-between">
                    <div class="my-3 mx-2 px-2">
                        <img src="../Image/UNAH.jpg" alt="UNAH" class="img-responsive">
                    </div>
                    <div class="d-flex align-items-center me-3">
                        <small class="mb-1 text-small text-muted">${usuario.sector}</small>
                    </div>
                </div>
                <div class="mx-2">
                    <button class="btn btn-azul btn-sm rounded-pill w-100">
                        <i class="bi bi-person-plus-fill"></i>
                        <span>Conectar</span>
                    </button>
                </div>
            </div>
        </div>
    </div>
    `;
}
