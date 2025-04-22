// import { obtenerCodigoUsuario, redirigirSiNoEstaLogueado } from './usuarioUtils.js';

// document.addEventListener("DOMContentLoaded", async () => {
//     await redirigirSiNoEstaLogueado();
//     const codigoUsuario = obtenerCodigoUsuario();
//     cargarTodasInvitaciones(codigoUsuario);
// });

// async function cargarTodasInvitaciones(codigoUsuario) {
//     const contenedor = document.getElementById("todasInvitaciones");
//     try {
//         const response = await fetch(`http://localhost:8080/api/conexiones/solicitudes/${codigoUsuario}`);
//         if (!response.ok) throw new Error("Error al cargar invitaciones");

//         const invitaciones = await response.json();
//         invitaciones.forEach(invitacion => {
//             const html = `
//                 <div class="d-flex justify-content-between align-items-center mb-3 border-bottom pb-2">
//                     <div class="d-flex align-items-center">
//                         <img src="${invitacion.fotoPerfil || '../Image/default.jpg'}" class="rounded-circle me-2" width="48" height="48">
//                         <div>
//                             <p class="m-0">${invitacion.nombre} ${invitacion.apellidos}</p>
//                             <small>${invitacion.nombreInstitucion || invitacion.nombreEmpresa || 'Sin experiencia previa'}</small>
//                         </div>
//                     </div>
//                     <div>
//                         <button class="btn btn-light me-1" onclick="ignorarInvitacion(${invitacion.codigoConexion})">Ignorar</button>
//                         <button class="btn btn-azul rounded-pill btn-conectar me-3" onclick="aceptarInvitacion(${invitacion.codigoConexion})">Aceptar</button>
//                     </div>
//                 </div>
//             `;
//             contenedor.innerHTML += html;
//         });
//     } catch (error) {
//         console.error("Error:", error);
//         contenedor.innerHTML = "<p>No se pudieron cargar las invitaciones.</p>";
//     }
// }

// window.aceptarInvitacion = async function (codigoConexion) {
//     const response = await fetch(`http://localhost:8080/api/conexiones/aceptar/${codigoConexion}`, {
//         method: "PUT",
//     });
//     if (response.ok) location.reload();
// };

// window.ignorarInvitacion = async function (codigoConexion) {
//     const response = await fetch(`http://localhost:8080/api/conexiones/eliminar/${codigoConexion}`, {
//         method: "DELETE",
//     });
//     if (response.ok) location.reload();
// };
