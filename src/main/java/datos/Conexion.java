
package datos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement; 

public class Conexion {
    private static final String URL_JDBC = "jdbc:mysql://localhost:3306/multifiestas_db?useSSL=false&useTimezone=true&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "admin";
    
    public static Connection getConnection() throws SQLException{
        return DriverManager.getConnection(URL_JDBC, JDBC_USER,JDBC_PASSWORD);
    }
    
    public static void close(ResultSet rs) throws SQLException{
        rs.close();
    }
    
    public static void close(PreparedStatement ps) throws SQLException{
        ps.close(); 
       
    }
    
    public static void close(Statement stmt) throws SQLException{
        stmt.close();
        
    }
    
    public static void close(Connection conn) throws SQLException{
        conn.close();
    }
    
    
}
