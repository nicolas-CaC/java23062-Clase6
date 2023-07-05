package services;

import com.google.gson.Gson;
import daos.TicketsDaoMysql;
import java.sql.SQLException;
import java.util.List;

public class TicketsServices {
    
    private final Gson GSON = new Gson();
    private final TicketsDaoMysql DAO = new TicketsDaoMysql();
    
    public String getTickets() throws SQLException{
        
        List tickets;
        tickets = DAO.getTickets();
        return "";
    }

}
