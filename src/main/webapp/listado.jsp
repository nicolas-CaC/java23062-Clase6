<%-- 
    Document   : listado
    Created on : 11 jul 2023, 20:34:44
    Author     : Diseno
--%>

<%@page import="java.util.LinkedList"%>
<%@page import="models.Ticket"%>
<%@page import="models.Ticket"%>
<%@page import="daos.TicketsDaoMysql"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
        <title>Listado de Tickets</title>
    </head>
    <body>
        
        <table class="table table-striped">
            <thead class="thead-dark">
                <tr>
                    <th>Id</th>
                    <th>Nombre</th>
                    <th>Apellido</th>
                    <th>Correo</th>
                    <th>Cantidad</th>
                    <th>Tipo</th>
                </tr>
            </thead>
            
            <tbody>
                <% 
                TicketsDaoMysql dao = new TicketsDaoMysql();
                LinkedList<Ticket> tickets = dao.getTickets();
                
                for(Ticket ticket: tickets){
                %>
                <tr>
                    <td><%= ticket.getId() %></td> 
                    <td><%= ticket.getNombre() %></td> 
                    <td><%= ticket.getApellido()%></td> 
                    <td><%= ticket.getCorreo()%></td> 
                    <td><%= ticket.getCantidad()%></td> 
                    <td><%= ticket.getCategoria().getNombre() %></td> 
                    <td>
                        <button class="btn btn-danger" onclick="deleteTicket(<%= ticket.getId() %>)">Borrar</button>
                    </td>
                </tr>
                <%
                    }
                %>
            </tbody>
                
            <tfoot>
                <tr>
                    <td>Tickets Vendidos</td>
                    <td><b><%= tickets.size() %></b></td>
                </tr>
            </tfoot>      
        </table>
        
        <script src="./../js/jsp.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz" crossorigin="anonymous"></script>
    </body>
</html>
