import { obtenerCodigoUsuario, redirigirSiNoEstaLogueado } from './usuarioUtils.js';

document.addEventListener('DOMContentLoaded', () => {
    
    redirigirSiNoEstaLogueado();

    const codigoUsuario = obtenerCodigoUsuario();
    console.log("Código de usuario obtenido:", codigoUsuario);
    
    cargarEmpleos(codigoUsuario);   
});

function actualizarContador(resultados) {
    const contador = document.getElementById("empleosCount");
    contador.textContent = `${resultados} resultado${resultados !== 1 ? 's' : ''}`;
}

async function cargarEmpleos(codigoUsuario) {
    try {
        const response = await fetch(`http://localhost:8080/api/empleos/recomendados/${codigoUsuario}`);
        const data = await response.json();
        const empleosList = document.getElementById('empleosList');
        empleosList.innerHTML = '';

        data.forEach(empleo => {
            const empleoHTML = `
                <li class="list-group-item empleo p-2" data-empleo='${JSON.stringify(empleo)}'>
                    <div class="d-flex mt-3">
                        <a href="#" class="text-decoration-none text-dark d-flex flex-grow-1">
                            <div>
                                <img src="${empleo.fotoEmpresa}" alt="" width="60">
                            </div>
                            <div class="d-flex flex-grow-1 flex-column">
                                <div>
                                    <p class="h5 text-primary ms-3 titulo">${empleo.cargo}</p>
                                    <small class="ms-3">${empleo.nombreEmpresa} · ${empleo.ubicacionEmpleo} (${empleo.tipoLugarTrabajo})</small>
                                </div>
                                <div class="row mt-2">
                                    <div class="col-md-1 position-relative">
                                        <img src="../Image/UNAH.jpg" alt="UNAH" class="w-100 ps-3">
                                    </div>
                                    <div class="col-md-11 ps-0">
                                        <small class="mb-1 text-small text-muted">Universidad Nacional Autónoma de Honduras</small>
                                    </div>
                                </div>
                            </div>
                        </a>
                        <div class="d-flex align-items-start">
                            <button class="btn m-0 p-0 ms-3"><i class="bi bi-x"></i></button>
                        </div>
                    </div>
                </li>
            `;
            empleosList.insertAdjacentHTML('beforeend', empleoHTML);
        });

        document.querySelectorAll('.empleo').forEach(item => {
            item.addEventListener('click', function () {
                document.querySelectorAll('.empleo').forEach(el => el.classList.remove('activo'));
                this.classList.add('activo');
                const empleo = JSON.parse(this.getAttribute('data-empleo'));
                mostrarInformacionDesdeObjeto(empleo);
            });
        });

        actualizarContador(data.length);

        mostrarPrimerEmpleo();
    } catch (error) {
        console.error('Error al cargar los empleos:', error);
    }
}

function mostrarPrimerEmpleo() {
    const primerElemento = document.querySelector(".empleo");
    if (primerElemento) {
        primerElemento.classList.add("activo");
        seleccionarEmpleo(primerElemento);
    }
}

function seleccionarEmpleo(elemento) {
    document.querySelectorAll('.empleo').forEach(el => el.classList.remove('activo'));
    elemento.classList.add('activo');

    const empleo = JSON.parse(elemento.getAttribute("data-empleo"));
    mostrarInformacionDesdeObjeto(empleo);
}

function mostrarInformacionDesdeObjeto(empleo) {
    const panel = document.getElementById("detalle-empleo");

    if (!empleo) {
        panel.innerHTML = `<h5>No hay información disponible</h5>`;
        return;
    }

    panel.innerHTML = `             
    <div class="d-flex justify-content-between">
        <div>
            <a href="#" class="text-decoration-none text-dark d-flex justify-content-between align-items-center">
                <div class="my-3 w-25">
                    <img src="${empleo.fotoEmpresa}" alt="Titular" class="img-responsive img-fluid">
                </div>
                <span class="flex-grow-1 p-3"><span>${empleo.nombreEmpresa}</span></span>
            </a>
        </div>
        <div>  
            <button class="btn icono-grey"><i class="bi bi-arrow-90deg-right"></i></button>
            <button class="btn me-3 icono-grey"><i class="bi bi-three-dots"></i></button>
        </div>
    </div>
    <a href="#" class="text-black text-decoration-none"><h2>${empleo.cargo}</h2></a>
    <p class="text-muted">${empleo.ubicacionEmpleo} (${empleo.tipoLugarTrabajo})</p>
    <div class="d-flex flex-row"><i class="bi bi-briefcase-fill icono-grey me-2"></i><p>${empleo.tipoEmpleo}</p></div>
    <div>
        <h3>Acerca del empleo</h3>
        <p>${empleo.descripcionEmpleo}</p>
    </div>`;
}
