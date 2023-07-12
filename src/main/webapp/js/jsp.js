const deleteTicket = async (id) => {
    const config = {method: "DELETE"}
    fetch(`/api/tickets/${id}`, config)
    location.reload()
}


