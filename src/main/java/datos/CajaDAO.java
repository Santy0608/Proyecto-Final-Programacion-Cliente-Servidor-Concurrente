
package datos;

import static datos.Conexion.getConnection;
import domain.Caja;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List; 
 
public class CajaDAO {
    private static final String SQL_SELECT = "SELECT id_caja, fecha, hora, monto_total FROM caja";
    private static final String SQL_INSERT = "INSERT INTO caja(fecha, hora, monto_total) VALUES(?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE caja SET fecha = ?, hora = ?, monto_total = ? WHERE id_caja = ?";
    private static final String SQL_DELETE = "DELETE FROM caja SET id_caja = ?";
    
    public List<Caja> listarCajas(){
        Connection conn = null;
        PreparedStatement ps = null;
        List<Caja> cajas = new ArrayList<>();
        Caja caja = null;
        ResultSet rs = null;
        try {
          conn = getConnection();
          ps = conn.prepareStatement(SQL_SELECT);
          rs = ps.executeQuery();
          while(rs.next()){
              caja = new Caja();
              caja.setId(rs.getInt("id_caja"));
              caja.setFecha(rs.getString("fecha"));
              caja.setHora(rs.getString("hora"));
              caja.setMontoTotal(rs.getDouble("monto_total"));
              cajas.add(caja);
          }
        } catch (SQLException e) {
            System.out.println("Ocurrió un error al listar cajas");
        }
        finally{
            try {
                Conexion.close(conn);
                Conexion.close(ps);
                Conexion.close(rs);
            } catch (SQLException e) {
                System.out.println("Ocurrió un error al cerrar conexión");
            }
        }
        return cajas;
    }
    
    public int agregarCaja(Caja caja){
        Connection conn = null;
        PreparedStatement ps = null;
        int registros = 0;
        try {
            conn = getConnection();
            ps = conn.prepareStatement(SQL_INSERT);
            ps.setString(1, caja.getFecha());
            ps.setString(2,caja.getHora());
            ps.setDouble(3,caja.getMontoTotal());
            registros = ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Ocurrió un error al agregar caja");
        }
        finally{
            try {
                Conexion.close(conn);
                Conexion.close(ps);
            } catch (SQLException e) {
                System.out.println("Ocurrió un error al cerrar conexión");
            }
        }
        return registros;
    }
    
    public int actualizarCaja(Caja caja){
        Connection conn = null;
        PreparedStatement ps = null;
        int registros = 0;
        try {
            conn = getConnection();
            ps = conn.prepareStatement(SQL_UPDATE);
            ps.setString(1, caja.getFecha());
            ps.setString(2,caja.getHora());
            ps.setDouble(3,caja.getMontoTotal());
            ps.setInt(4,caja.getId());
            registros = ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Ocurrió un error al actualizar caja");
        }
        finally{
            try {
                Conexion.close(conn);
                Conexion.close(ps);
            } catch (SQLException e) {
                System.out.println("Ocurrió un error al cerrar conexión");
            }
        }
        return registros;
    }
    
    public int eliminarCaja(Caja caja){
        Connection conn = null;
        PreparedStatement ps = null;
        int registros = 0;
        try {
            conn = getConnection();
            ps = conn.prepareStatement(SQL_DELETE);
            ps.setInt(1, caja.getId());
            registros = ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Ocurrió un error al eliminar caja");
        }
        finally{
            try {
                Conexion.close(conn);
                Conexion.close(ps);
            } catch (SQLException e) {
                System.out.println("Ocurrió un error al cerrar conexión");
            } 
        }
        return registros;
    }
   
}
