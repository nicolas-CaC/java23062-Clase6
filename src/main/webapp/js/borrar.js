const form = document.forms.borrar

console.log(form)
const fnSubmit = (e) => {
    e.preventDefault()

    const config = {
        method: 'DELETE',
        body: JSON.stringify(parseInt(form.id.value))
    }

    fetch('/api/reservas', config)
        .then(res => res.json())
        .then(data => data.error
            ? alert("Problemas con la actualización de datos")
            : location.href = '/exito.html')

    console.log(data)
}

form.addEventListener('submit', fnSubmit)