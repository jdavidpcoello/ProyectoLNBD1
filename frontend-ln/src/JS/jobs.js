import { obtenerCodigoUsuario, redirigirSiNoEstaLogueado } from './usuarioUtils.js';

document.addEventListener('DOMContentLoaded', function () {

    redirigirSiNoEstaLogueado();

    const codigoUsuario = obtenerCodigoUsuario();

    fetch(`http://localhost:8080/api/empleos/recomendados/${codigoUsuario}`)
        .then(response => response.json())
        .then(data => {
            const empleosList = document.getElementById('empleosList');
            empleosList.innerHTML = '';

            data.forEach(empleo => {
                const empleoHTML = `
                <li class="list-group-item m-0 p-0">
                    <div class="d-flex mt-3">
                        <a href="./recommendedJobs.html" class="text-decoration-none text-dark d-flex flex-grow-1">
                            <div>
                                <img src="${empleo.fotoEmpresa}" alt="" width="100">
                            </div>
                            <div class="d-flex flex-grow-1 flex-column">
                                <div>
                                    <p class="h5 text-primary ms-3">${empleo.cargo} ${empleo.tipoLugarTrabajo} ${empleo.ubicacionEmpleo}</p>
                                    <small class="ms-3">${empleo.nombreEmpresa} · ${empleo.ubicacionEmpleo} (${empleo.tipoLugarTrabajo})</small>
                                </div>
                                <div class="row mt-2">
                                    <div class="col-md-1 position-relative">
                                        <img src="../Image/UNAH.jpg" alt="Escudo_de_la_UNAH" class="w-100 ps-3">
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
                </li>`;

                
                empleosList.insertAdjacentHTML('beforeend', empleoHTML);
            });
        })
        .catch(error => {
            console.error('Error al cargar los empleos:', error);
        });
});
