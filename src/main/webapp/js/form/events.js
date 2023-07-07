// EVENTS

// Resets

const resetCategories = () => {
    total = null
    selected = null
    eventsAssignmentAll()
    totalTag.innerText = totalText
}

const resetForm = (e) => {

    e.preventDefault()

    for (let input of inputs)
        input.value = ''

    select.value = 'none'

    resetCategories()
}

// Setters

const setCategory = (e) => {

    const option = e.target.value

    if (option === 'none') {
        resetCategories()
        return
    }

    category = option
    const index = categories[option].value
    const container = cardsContainer[index]

    selected = index
    changeColor(container, index)
    eventsAssignmentAll()

    totalPrice()
}

const setTicket = (e) => {

    const { value } = e.target

    if (value < 0 || isNaN(value)) {
        e.target.value = 0
        total = null
        return
    }

    tickets = value
    totalPrice()
}

// Submit

const adapterForm = ({ firstname, lastname, email, tickets, category }) => {

    const data = {
        nombre: firstname.value,
        apellido: lastname.value,
        correo: email.value,
        cantidad: tickets.value,
        categoria: category.value
    }

    return data
}

const verifyForm = (form) => {

    const { firstname, lastname, email, tickets, category } = form

    const verified = {
        firstname: firstname.value !== '',
        lastname: lastname.value !== '',
        email: email.value.includes('@'),
        tickets: tickets.value > 0,
        category: category.value !== 'none'
    }

    const values = Object.values(verified)
    const accepted = values.every(value => value)

    return accepted
}

const submitForm = async (form) => {

    const data = adapterForm(form)

    const config = {
        method: 'POST',
        body: JSON.stringify(data)
    }

    console.log(config.body)

    let confirm
    confirm = await fetch('/api/tickets', config)
    confirm = await confirm.json()
    console.log(confirm)
}

const submit = (e) => {

    e.preventDefault()

    const submitAccepted = verifyForm(form)

    submitAccepted
        ? submitForm(form)
        : alert('Debes completar todos los campos correctamente')


}

// ASIGNACION DE EVENTOS

form.category.addEventListener('change', setCategory)

form.tickets.addEventListener('change', setTicket)
form.tickets.addEventListener('keyup', setTicket)

form.addEventListener('submit', submit)
resetBtn.addEventListener('click', resetForm)