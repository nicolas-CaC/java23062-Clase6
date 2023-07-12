package services;

import com.google.gson.Gson;
import daos.TicketsDaoMysql;
import java.sql.SQLException;
import java.util.List;
import models.Result;
import models.Ticket;

public class TicketsServices {
    
    private final Gson GSON = new Gson();
    private final TicketsDaoMysql DAO = new TicketsDaoMysql();
    
    public String getTickets() throws SQLException{
        
        List tickets;
        tickets = DAO.getTickets();
        String result = GSON.toJson(tickets);
        System.out.println("EN SERVICES" + result);
        return result;
    }
    
    public String postTickets(String ticket) throws SQLException{
        
        Ticket newTicket = GSON.fromJson(ticket, Ticket.class);
        boolean error = DAO.postTicket(newTicket);
        Result result = new Result(error);
        return GSON.toJson(result);
    }

    public String modifyticket(String ticket) throws SQLException{
        Ticket newTicket = GSON.fromJson(ticket, Ticket.class);
        boolean error = DAO.modifyTicket(newTicket);
        Result result = new Result(error);
        return GSON.toJson(result);
    }
    
    public String deleteTicket(String path) throws SQLException{
        int id = Integer.parseInt(path.substring(1));
        int error = DAO.deleteTicket(id);
        Result result = new Result(error == 0);
        return GSON.toJson(result);
    }
}
