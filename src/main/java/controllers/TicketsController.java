package controllers;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import services.TicketsServices;


@WebServlet(name = "TicketsController", urlPatterns = {"/api/tickets", "/api/tickets/*"})
public class TicketsController extends HttpServlet {

    private final TicketsServices ticketsServices = new TicketsServices();
    
    @Override
    protected void doGet(
            HttpServletRequest req, 
            HttpServletResponse res) 
            throws ServletException, IOException {

        try {
            String tickets = ticketsServices.getTickets();
            enviar(res, tickets);
        } 
        catch (SQLException ex) {
            System.out.println(ex.toString());
        }
        
    }

    
    @Override
    protected void doPost(
            HttpServletRequest req, 
            HttpServletResponse res) 
            throws ServletException, IOException {
        
        try {
            String body = bodyToString(req.getInputStream());
            String result = ticketsServices.postTickets(body);
            enviar(res, result);
        } 
        catch (SQLException ex) {
            System.out.println(ex.toString());
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
            Logger.getLogger(TicketsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doDelete(
            HttpServletRequest req, 
            HttpServletResponse res) 
            throws ServletException, IOException {
        
        try {
            String path = req.getPathInfo();
            String result = ticketsServices.deleteTicket(path);
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
