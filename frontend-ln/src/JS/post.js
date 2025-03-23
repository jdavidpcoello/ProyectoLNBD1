// Objeto para almacenar los contadores de likes por publicación
const likeCounts = {
    1: 42,
    2: 27,
    3: 87
  };
  
  // Objeto para almacenar los contadores de comentarios por publicación
  const commentCounts = {
    1: 0,
    2: 0,
    3: 0
  };
  
  // Objeto para almacenar los contadores de compartir por publicación
  const shareCounts = {
    1: 0,
    2: 0,
    3: 0
  };
  
  // Función para dar like a una publicación
  function likePost(postId) {
    likeCounts[postId]++;
    document.getElementById(`like-count-${postId}`).innerText = likeCounts[postId];
  }
  
  // Función para compartir una publicación
  function sharePost(postId) {
    shareCounts[postId]++;
    alert(`Has compartido esta publicación ${shareCounts[postId]} veces`);
  }
  
  // Función para mostrar/ocultar la sección de comentarios
  function toggleComments(postId) {
    document.getElementById(`comments-section-${postId}`).classList.toggle("d-none");
  }
  
  // Función para añadir un comentario
  function addComment(postId) {
    const input = document.getElementById(`comment-input-${postId}`);
    const commentText = input.value.trim();
    
    if (commentText !== "") {
      const commentList = document.getElementById(`comments-list-${postId}`);
      
      if (commentList.innerText === "No hay comentarios aún.") {
        commentList.innerHTML = "";
      }
      
      const newComment = document.createElement("div");
      newComment.classList.add("mt-2", "p-2", "bg-light", "border", "rounded");
      newComment.innerHTML = `
        <p>
          <strong>Usuario:</strong> ${commentText} 
          <button class='btn btn-link btn-sm' onclick="replyComment(this, ${postId})">Responder</button>
        </p>
      `;
      
      commentList.appendChild(newComment);
      input.value = "";
      
      // Incrementar contador de comentarios
      commentCounts[postId]++;
      document.getElementById(`comment-count-${postId}`).innerText = commentCounts[postId];
    }
  }
  
  // Función para responder a un comentario
  function replyComment(button, postId) {
    const commentDiv = button.parentElement.parentElement;
    
    if (commentDiv.querySelector("input")) {
      return;
    }
    
    const replyContainer = document.createElement("div");
    replyContainer.classList.add("mt-2", "d-flex");
    
    const replyInput = document.createElement("input");
    replyInput.type = "text";
    replyInput.placeholder = "Escribe una respuesta...";
    replyInput.classList.add("form-control");
    
    const replyButton = document.createElement("button");
    replyButton.innerText = "Responder";
    replyButton.classList.add("btn", "btn-primary", "btn-sm", "ms-2");
    
    replyButton.onclick = function() {
      const replyText = replyInput.value.trim();
      
      if (replyText !== "") {
        const replyDiv = document.createElement("div");
        replyDiv.classList.add("ms-3", "mt-2", "p-2", "bg-light", "border", "rounded");
        replyDiv.innerHTML = `<p><strong>Usuario:</strong> ${replyText}</p>`;
        
        // Insertar la respuesta después del contenedor de respuesta
        commentDiv.insertBefore(replyDiv, replyContainer.nextSibling);
        
        replyContainer.remove();
        
        // Incrementar contador de comentarios
        commentCounts[postId]++;
        document.getElementById(`comment-count-${postId}`).innerText = commentCounts[postId];
      }
    };
    
    replyContainer.appendChild(replyInput);
    replyContainer.appendChild(replyButton);
    commentDiv.appendChild(replyContainer);
  }
