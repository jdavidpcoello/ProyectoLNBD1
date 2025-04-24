import { obtenerCodigoUsuario, redirigirSiNoEstaLogueado } from './usuarioUtils.js';


document.addEventListener("DOMContentLoaded", function () {

  const codigoReceptor = obtenerCodigoUsuario();

  if (!codigoReceptor) {
    console.error("No se encontró el código del usuario en sessionStorage.");
    return;
  }

  fetch(`http://localhost:8080/api/chats/receptor/${codigoReceptor}`)
    .then(response => {
      if (!response.ok) {
        throw new Error("Error al obtener los mensajes.");
      }
      return response.json();
    })
    .then(data => {
      mostrarMensajes(data);
    })
    .catch(error => {
      console.error("Error al cargar los mensajes:", error);
    });
});

function mostrarMensajes(mensajes) {
  const contenedor = document.getElementById("contenedor-mensajes");
  const plantilla = document.getElementById("plantilla-mensaje");

  mensajes.forEach(mensaje => {
    const clon = plantilla.cloneNode(true);
    clon.style.display = "flex";

    const nombreCompleto = `${mensaje.nombreEmisor} ${mensaje.apellidosEmisor}`;
    clon.querySelector("[data-nombre]").textContent = nombreCompleto;

    clon.querySelector("[data-cargo]").textContent = mensaje.titular;

    const fechaFormateada = new Date(mensaje.fechaUltimoMensaje).toLocaleString("es-ES", {
      day: "2-digit",
      month: "short",
      year: "numeric",
      hour: "2-digit",
      minute: "2-digit"
    });
    clon.querySelector("[data-fecha]").textContent = fechaFormateada;

    
    const mensajeDiv = document.createElement("div");
    mensajeDiv.textContent = mensaje.contenidoUltimoMensaje;
    mensajeDiv.style.fontSize = "11px";
    mensajeDiv.style.marginTop = "4px";
    mensajeDiv.style.color = "#666";
    clon.querySelector(".container.col-8").appendChild(mensajeDiv);

    
    const foto = clon.querySelector("[data-foto]");
    if (mensaje.fotoPerfil) {
      foto.src = mensaje.fotoPerfil;
    }

    
    if (mensaje.cantidadNuevos && mensaje.cantidadNuevos > 0) {
      clon.querySelector("[data-nuevos]").textContent = mensaje.cantidadNuevos;
    } else {
      clon.querySelector("[data-nuevos]").style.display = "none";
    }

    clon.addEventListener("click", () => {
      cargarConversacion(mensaje.codigoChat);
    });

    contenedor.appendChild(clon);
  });
}

function cargarConversacion(codigoChat) {
  fetch(`http://localhost:8080/api/mensajes/chat/${codigoChat}`)
    .then(response => {
      if (!response.ok) throw new Error("Error al obtener la conversación.");
      return response.json();
    })
    .then(data => {
      console.log("Respuesta del backend:", data); 
      const contenedor = document.getElementById("contenido-mensaje");
      contenedor.innerHTML = "";

      data.forEach(mensaje => {
        const div = document.createElement("div");
        div.classList.add("mensaje");
        div.innerHTML = `
          <p><strong>${mensaje.nombreEmisor}</strong> <span style="font-size: smaller;">${new Date(mensaje.fecha).toLocaleString("es-ES")}</span></p>
          <p>${mensaje.contenido}</p>
        `;
        contenedor.appendChild(div);
      });
    })
    .catch(error => {
      console.error("Error al cargar la conversación:", error);
    });
}

