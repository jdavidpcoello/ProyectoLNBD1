
function notDisplayForm(i){
    const btnElement1 = document.querySelector('.not-display');
    const btnElement2 = document.querySelector(`.step-${i}`);
    btnElement1.classList.remove('not-display');
    btnElement2.classList.add('not-display');
}

//JS provisional solo para navegar
//Despues lo mejoramos
function submitForm(event,i) {
    window.location.href = `login-${i}.html`;
}
