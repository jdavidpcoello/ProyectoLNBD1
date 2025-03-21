document.addEventListener("DOMContentLoaded", function () {
   
    const enlaces = document.querySelectorAll(".nav-link[data-target]");
  
    const contenidos = document.querySelectorAll(".contenido-item");
  
    enlaces.forEach((enlace) => {
      enlace.addEventListener("click", function (e) {
        e.preventDefault();
  
        contenidos.forEach((contenido) => {
          contenido.classList.add("d-none");
        });
  
        const target = this.getAttribute("data-target");
        const contenidoMostrar = document.getElementById(target);
        if (contenidoMostrar) {
          contenidoMostrar.classList.remove("d-none");
        }
      });
    });
  });