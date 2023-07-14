const form = document.forms.modificar

const fnSubmit = (e) => {

    e.preventDefault()

    const data = {
        id: form.id.value,
        nombre: form.nombre.value,
        apellido: form.apellido.value,
        correo: form.correo.value,
        cantidad: form.cantidad.value,
        categoria: form.categoria.value
    }

    const config = {
        method: 'PUT',
        body: JSON.stringify(data)
    }

    fetch('/api/reservas', config)
        .then(res => res.json())
        .then(data => data.error
            ? alert("Problemas con la actualización de datos")
            : location.href = '/exito.html')

    console.log(form)
}

form.addEventListener('submit', fnSubmit)