const emailField = document.querySelector("#input1");
const passwordField = document.querySelector("#input2");
const signInForm = document.querySelector("#sign-in-form");
const signInButton = document.querySelector("#sign-in-button");
const emailLabel = document.querySelector(".email-label");
const passwordLabel = document.querySelector(".password-label");

signInForm.addEventListener("submit", submitSignin);

async function submitSignin(event) {
  event.preventDefault();

  const url = "http://localhost:8080/api/usuarios/signin";

  const validez = validateForm();
  if (validez) {
    const data = {
      email: emailField.value,
      password: passwordField.value,
    };

    try {
      const response = await fetch(url, {
        method: "POST",
        headers: {
          "Content-Type": "application/x-www-form-urlencoded",
        },
        body: new URLSearchParams(data).toString(),
      });

      let responseData = null;
      const text = await response.text();
      if (text) {
        responseData = JSON.parse(text);
      }

      if (response.ok && responseData) {
        alert("Inicio de sesión exitoso");
        localStorage.setItem('infoUsuario', JSON.stringify(responseData));
        window.location.href = "../Pages/Inicio.html";
      } else {
        const invalidCredentials = document.querySelector(".invalid-credentials");
        invalidCredentials.classList.remove("hidden");
        emailField.classList.add("invalid-input");
        emailLabel.classList.add("invalid-input-label");
        passwordField.classList.add("invalid-input");
        passwordLabel.classList.add("invalid-input-label");
      }
    } catch (error) {
      alert("Error al realizar la solicitud al backend. Revisa la consola.");
      console.log(error);
    }
  }
}


function validateForm() {
  const invalidEmailDiv = document.querySelector(".invalid-email");
  const invalidPasswordDiv = document.querySelector(".invalid-password");
  invalidEmailDiv.innerHTML = "";
  invalidPasswordDiv.innerHTML = "";
  emailField.classList.remove("invalid-input");
  emailLabel.classList.remove("invalid-input-label");
  passwordField.classList.remove("invalid-input");
  passwordLabel.classList.remove("invalid-input-label");
  const emailRegex = /^[\w\d\.-]+@\w+(\.\w+)+$/;

  if (emailField.value === "") {
    invalidEmailDiv.innerHTML = "Introduce un email o un número de teléfono";
    emailField.classList.add("invalid-input");
    emailLabel.classList.add("invalid-input-label");
    return false;
  } else if (!emailRegex.test(emailField.value)) {
    invalidEmailDiv.innerHTML = "Introduce un nombre de usuario válido.";
    emailField.classList.add("invalid-input");
    emailLabel.classList.add("invalid-input-label");
    return false;
  } else if (passwordField.value === "") {
    invalidPasswordDiv.innerHTML = "Introduce una contraseña.";
    passwordField.classList.add("invalid-input");
    passwordLabel.classList.add("invalid-input-label");
    return false;
  } else if (passwordField.value.length < 6) {
    passwordField.classList.add("invalid-input");
    passwordLabel.classList.add("invalid-input-label");
    invalidPasswordDiv.innerHTML =
      "La contraseña debe tener 6 caracteres como mínimo.";
    return false;
  }
  return true;
}
