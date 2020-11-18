/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connection;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author PIU2
 */
public class ConnectionFactory {
    
    private static final String DRIVER = "org.postgresql.Driver";
    private static final String URL = "jdbc:postgresql://localhost:5432/exeraula1";
    private static final String USER = "postgres";
    private static final String SENHA = "postgres";
    
    public static Connection getConnection(){
        
        try {
            Class.forName(DRIVER);
            
            return DriverManager.getConnection(URL, USER, SENHA);
            
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public static void closeConnection(Connection con){
        if (con != null){
            try {
                con.close();
            } catch (SQLException ex) {
                System.out.println("Erro: "+ex);
            }
        }
    }
    
    public static void closeConnection(Connection con, PreparedStatement stmt){
        if (stmt != null){
            try {
                stmt.close();
            } catch (SQLException ex) {
                System.out.println("Erro: "+ex);
            }
        }
        closeConnection(con);
    }
    
    public static void closeConnection(Connection con, PreparedStatement stmt, ResultSet rs){
        if (rs != null){
            try {
                rs.close();
            } catch (SQLException ex) {
                System.out.println("Erro: "+ex);
            }
        }
        closeConnection(con, stmt);
    }
    
}
