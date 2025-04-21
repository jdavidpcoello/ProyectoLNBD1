document.addEventListener('DOMContentLoaded', () => {
    fetch('http://localhost:8080/api/mensajes/todos') // Ajusta esta ruta si es necesario
      .then(response => response.json())
      .then(data => {
        const contenedor = document.getElementById('contenedor-mensajes'); // Este contenedor debe estar en tu HTML
        const plantilla = document.getElementById('plantilla-mensaje'); // Esta es la plantilla oculta que clonamos
  
        data.forEach(mensaje => {
          const clon = plantilla.cloneNode(true);
          clon.style.display = 'flex';
          clon.removeAttribute('id'); // Eliminamos el id duplicado
  
          // Seteamos los valores dentro del clon usando tus IDs
          clon.querySelector('#foto-perfil').src = `${mensaje.emisorFotoPerfil}`; // Cambia por el src real si lo tienes
  
          const nombreCompleto = `${mensaje.nombreEmisor} ${mensaje.apellidoEmisor}`;
          clon.querySelector('#nombre').textContent = nombreCompleto;
  
          // FORMATEO PERSONALIZADO DE LA FECHA
          const fecha = new Date(mensaje.fecha);
          const opciones = { month: 'long' };
          const dia = fecha.getDate().toString().padStart(2, '0');
          const mes = fecha.toLocaleDateString('es-ES', opciones);
          const año = fecha.getFullYear();
          const fechaFormateada = `${dia} ${mes} ${año}`;
          clon.querySelector('#fecha').textContent = fechaFormateada;
  
          const cargoEmisor = mensaje.emisorCargo || 'Sin cargo';
          clon.querySelector('#cargo-emisor').textContent = cargoEmisor;
  
        //   const cantidadNuevos = mensaje.mensajePadre ? 1 : 0; 
        //   clon.querySelector('#cantidad-nuevos').textContent = cantidadNuevos;
  
          contenedor.appendChild(clon);
        });
      })
      .catch(error => {
        console.error('Error al cargar mensajes:', error);
      });
  });
  