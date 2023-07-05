package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    private final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private final String URL = "jdbc:mysql://localhost:3306/java23062";
    private final String USER = "root";
    private final String PASS = "";
    
    public Connection getConnection(){
        
        Connection conexion = null;
        
        try{
            Class.forName(DRIVER);
            conexion = DriverManager.getConnection(URL, USER, PASS);
        }
        catch(ClassNotFoundException | SQLException e){
            System.out.println(e.toString());
        }
        
        return conexion;
    }
    
    
}
