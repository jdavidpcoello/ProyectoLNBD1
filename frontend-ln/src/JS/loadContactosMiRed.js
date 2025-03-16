document.addEventListener("DOMContentLoaded", function() {
    fetch('contactoMiRed.html')
        .then(response => response.text())
        .then(data => {
            const containers = document.querySelectorAll('.contacto-container');
            containers.forEach(container => {
                container.innerHTML += data;
            });
        })
        .catch(error => console.error('Error loading contactos:', error));
});

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