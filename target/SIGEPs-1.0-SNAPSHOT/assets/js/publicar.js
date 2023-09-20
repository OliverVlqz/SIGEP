document.addEventListener('DOMContentLoaded', function() {
    const styleSelect = document.getElementById('styleSelect');
    const submitBtn = document.getElementById('submitBtn');
    const result = document.getElementById('resultado');

    submitBtn.addEventListener('click', function() {
        const selectedStyle = styleSelect.value;
        result.textContent = `Estilo seleccionado: ${selectedStyle}`;
    });
});
document.addEventListener('DOMContentLoaded', function() {
    const postText = document.getElementById('postText');
    const imageInput = document.getElementById('imageInput');
    const publishButton = document.getElementById('publishButton');
    const preview = document.getElementById('preview');

    imageInput.addEventListener('change', function(event) {
        preview.innerHTML = '';
        const file = event.target.files[0];
        if (file) {
            const image = document.createElement('img');
            image.src = URL.createObjectURL(file);
            preview.appendChild(image);
            preview.style.display = 'block';
        }
    });

    publishButton.addEventListener('click', function() {
        const text = postText.value;
        const image = preview.innerHTML;
        console.log('Texto:', text);
        console.log('Imagen:', image);
        postText.value = '';
        imageInput.value = '';
        preview.innerHTML = '';
        preview.style.display = 'none';
    });
});
function actualizarSelect(valor) {
    document.getElementById('categorias').innerHTML += `<option value="${valor}">${valor}</option>`;
}

function agregarCategoria() {
    const nuevaCategoria = document.getElementById('nuevaCategoria').value;
    if (nuevaCategoria.trim() !== "") {
        const selectCategorias = document.getElementById('categorias');
        const opcionesExistentes = selectCategorias.getElementsByTagName('option');
        let categoriaExiste = false;

        for (const opcion of opcionesExistentes) {
            if (opcion.value.toLowerCase() === nuevaCategoria.toLowerCase()) {
                categoriaExiste = true;
                break;
            }
        }

        if (categoriaExiste) {
            alert('La categoría ya existe.');
        } else {
            const nuevaOpcion = document.createElement('option');
            nuevaOpcion.value = nuevaCategoria.toLowerCase();
            nuevaOpcion.textContent = nuevaCategoria;
            selectCategorias.appendChild(nuevaOpcion);
            document.getElementById('nuevaCategoria').value = "";
        }
    }
}