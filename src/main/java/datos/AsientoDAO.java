
package datos;

import static datos.Conexion.getConnection;
import domain.Asiento;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import static datos.Conexion.getConnection;
import javax.swing.JOptionPane;

public class AsientoDAO {
    private static final String SQL_SELECT = "SELECT id_asiento, codigo_area, numero_asiento, costo_venta, estado, disponibilidad FROM asiento";
    private static final String SQL_INSERT = "INSERT INTO asiento (codigo_area, numero_asiento, costo_venta, estado, disponibilidad) VALUES (?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE asiento SET codigo_area = ?, numero_asiento = ?, costo_venta = ?, estado = ?, disponibilidad = ? WHERE id_asiento = ?";
    private static final String SQL_DELETE = "DELETE FROM asiento WHERE id_asiento = ?";
//    private static final String SQL_CONSULTA_ACTUALIZAR = "UPDATE asiento SET estado = 'Ocupado' WHERE id_asiento = ?";
    
    public List<Asiento> listarAsientos(){
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Asiento asiento = null;
        List<Asiento> asientos = new ArrayList<>();
        try {
            conn = getConnection();
            ps = conn.prepareStatement(SQL_SELECT);
            rs = ps.executeQuery();
            while (rs.next()){
                asiento = new Asiento();
                asiento.setIdAsiento(rs.getInt("id_asiento"));
                asiento.setCodigoArea(rs.getString("codigo_area"));
                asiento.setNumeroAsiento(rs.getString("numero_asiento"));
                asiento.setCostoVenta(rs.getDouble("costo_venta"));
                asiento.setEstado(rs.getString("estado"));
                asiento.setDisponibilidad(rs.getString("disponibilidad"));
                asientos.add(asiento);
            }
        } catch (SQLException e) {
            System.out.println("Ocurrió un error al listar asientos");
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
        return asientos;
    }
    public int agregarAsientos(Asiento asiento){
        Connection conn = null;
        PreparedStatement ps = null;
        int registros = 0;
        try {
            conn = getConnection();
            ps = conn.prepareStatement(SQL_INSERT);
            ps.setString(1, asiento.getCodigoArea());
            ps.setString(2, asiento.getNumeroAsiento());
            ps.setDouble(3, asiento.getCostoVenta());
            ps.setString(4,asiento.getEstado());
            ps.setString(5, asiento.getDisponibilidad());
            registros = ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Ocurrió un error al agregar asientos");
        }
        finally{ 
            try {
                Conexion.close(ps);
                Conexion.close(conn);
            } catch (SQLException e) {
                System.out.println("Ocurrió un error al cerrar conexión");
            }
        }
        return registros;
    }
    public int actualizarAsientos(Asiento asiento){
        Connection conn = null;
        PreparedStatement ps = null;
        int registros = 0;
        try {
            conn = getConnection();
            ps = conn.prepareStatement(SQL_UPDATE);
            ps.setString(1, asiento.getCodigoArea());
            ps.setString(2, asiento.getNumeroAsiento());
            ps.setDouble(3, asiento.getCostoVenta());
            ps.setString(4, asiento.getEstado());
            ps.setString(5, asiento.getDisponibilidad());
            ps.setInt(6, asiento.getIdAsiento());
            registros = ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Ocurrió un error al actualizar asientos");
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
    
//    public int actualizarEstadoAsiento(Asiento asiento){
//        Connection conn = null;
//        PreparedStatement ps = null;
//        int registros = 0;
//        try {
//            conn = getConnection();
//            ps = conn.prepareStatement(SQL_CONSULTA_ACTUALIZAR);
//            ps.setString(4, asiento.getEstado());
//            ps.setInt(7, asiento.getIdAsiento());
//            registros = ps.executeUpdate();
//        } catch (SQLException e) {
//            System.out.println("Ocurrió un error al actualizar asientos");
//        }
//        finally{
//            try {
//                Conexion.close(conn);
//                Conexion.close(ps);
//            } catch (SQLException e) {
//                System.out.println("Ocurrió un error al cerrar conexión");
//            }
//        }
//        return registros;
//    }
    
    public int eliminarAsientos(Asiento asiento){
        Connection conn = null;
        PreparedStatement ps = null;
        int registros = 0;
        try {
            conn = getConnection();
            ps = conn.prepareStatement(SQL_DELETE);
            ps.setInt(1, asiento.getIdAsiento());
            registros = ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Ocurrió un error al eliminar asientos");
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
