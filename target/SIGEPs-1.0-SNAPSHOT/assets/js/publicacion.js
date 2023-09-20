function addComment() {
    const commentContent = document.getElementById('commentContent').value;

    if (commentContent) {
      const commentDiv = document.createElement('div');
      commentDiv.className = 'comment';

      const commentText = document.createElement('p');
      commentText.innerHTML = `</strong> ${commentContent}`;

      const timestamp = document.createElement('p');
      commentDiv.appendChild(commentText);
      commentDiv.appendChild(timestamp);

      const now = new Date();
      const timestampText = `Publicado el ${formatDate(now)}`;
      timestamp.textContent = timestampText;

      const responseTextarea = document.createElement('textarea');
      responseTextarea.placeholder = 'Responder a este comentario...';
      const responseButton = document.createElement('button');
      responseButton.textContent = 'Responder';
      responseButton.onclick = function() {
        const responseContent = responseTextarea.value;
        if (responseContent) {
          const responseDiv = document.createElement('div');
          responseDiv.className = 'response';
          responseDiv.innerHTML = `<p></strong> ${responseContent}</p>`;
          commentDiv.appendChild(responseDiv);
          responseTextarea.value = '';
        }
      };

      commentDiv.appendChild(responseTextarea);
      commentDiv.appendChild(responseButton);

      document.getElementById('comments').appendChild(commentDiv);

      // Limpiar los campos
      document.getElementById('commentContent').value = '';
    }
  }

  function formatDate(date) {
    const options = { day: '2-digit', hour: '2-digit', minute: '2-digit' };
    return date.toLocaleDateString('es-MX', options);
  }

  function updateTimestamps() {
    const timestamps = document.querySelectorAll('.timestamp');
    timestamps.forEach(timestamp => {
      const now = new Date();
      const timestampText = `Publicado el ${formatDate(now)}`;
      timestamp.textContent = timestampText;
    });
  }

  setInterval(updateTimestamps, 1000);