package controllers;

import daos.TicketsDaoMysql;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Ticket;
import services.TicketsServices;


@WebServlet(name = "ReservasController", urlPatterns = {"/api/reservas"})
public class ReservasController extends HttpServlet {

    private final TicketsDaoMysql DAO = TicketsDaoMysql.getInstance();
    private final TicketsServices ticketsServices = new TicketsServices();
    
    @Override
    protected void doGet(
            HttpServletRequest req, 
            HttpServletResponse res) 
            throws ServletException, IOException {
    
        RequestDispatcher dispatcher = req.getRequestDispatcher("./../listado.jsp");
        dispatcher.forward(req, res);
    }
    
    

    @Override
    protected void doPost(
            HttpServletRequest req, 
            HttpServletResponse res) 
            throws ServletException, IOException {
        
        String nombre = req.getParameter("nombre");
        String apellido = req.getParameter("apellido");
        String correo = req.getParameter("correo");
        int cantidad = Integer.parseInt(req.getParameter("cantidad"));
        String categoria = req.getParameter("categoria");
        
        Ticket ticket = new Ticket(nombre, apellido, correo, cantidad, categoria);
        
        try {
            boolean error = DAO.postTicket(ticket);
            if(!error){
                res.sendRedirect("./../exito.html");
            }else{
                res.sendRedirect("./../error.html");
            }
        } catch (SQLException ex) {
            System.out.println(ex.toString());
            res.sendRedirect("./../error.html");
        }
    }

    @Override
    protected void doPut(
            HttpServletRequest req, 
            HttpServletResponse res) 
            throws ServletException, IOException {

         try {
            String body = bodyToString(req.getInputStream());
            String result = ticketsServices.modifyticket(body);
            enviar(res, result);
        } catch (SQLException ex) {
             System.out.println(ex.toString());
             res.sendRedirect("./../error.html");
        }
    }

    @Override
    protected void doDelete(
            HttpServletRequest req, 
            HttpServletResponse res) 
            throws ServletException, IOException {
        
        try {
            String id = bodyToString(req.getInputStream());
            String result = ticketsServices.deleteTicket(id);
            enviar(res, result);
        } 
        catch (SQLException ex) {
            System.out.println(ex.toString());
        }
    }
    
    
    
    

    
    // Private
    
    private String bodyToString(InputStream inputStream){
        
        Scanner scanner = new Scanner(inputStream, "UTF-8");
        return scanner.hasNext() 
                ? scanner.useDelimiter("\\A").next()
                : "";
    }
    
    private void enviar(HttpServletResponse res, String json) throws IOException{
        
        PrintWriter out = res.getWriter();
        res.setContentType("application/json");
        res.setCharacterEncoding("UTF-8");
        out.print(json);
        out.flush();
    }
}
