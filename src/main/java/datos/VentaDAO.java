
package datos;

import static datos.Conexion.getConnection;
import domain.Asiento;
import domain.Evento;
import domain.Usuario;
import domain.Venta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VentaDAO {
    private static final String SQL_SELECT = "SELECT id_venta, fecha_venta, hora_venta, usuario, evento, asiento, monto from venta";
    private static final String SQL_INSERT = "INSERT INTO venta(fecha_venta, hora_venta, usuario, evento, asiento, monto) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE venta SET fecha_venta = ?, hora_venta = ?, usuario = ?, evento = ?, asiento = ?, monto = ? WHERE id_venta = ?";
    private static final String SQL_DELETE = "DELETE FROM venta WHERE id_venta = ?";
    
    public List<Venta> listarVentas(){
        List<Venta> ventas = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Venta venta = null;
        try {
            conn = getConnection();
            ps = conn.prepareStatement(SQL_SELECT);
            rs = ps.executeQuery();
            while (rs.next()){
                venta = new Venta();
                venta.setIdVenta(rs.getInt("id_venta"));
                venta.setFechaVenta(rs.getString("fecha_venta"));
                venta.setHoraVenta(rs.getString("hora_venta"));
                venta.setUsuario( rs.getString("usuario"));
                venta.setEvento(rs.getString("evento"));
                venta.setAsiento(rs.getString("asiento"));
                venta.setMonto(rs.getDouble("monto"));
                ventas.add(venta);
            }
        } catch (SQLException e) {
            System.out.println("Ocurrió un error al listar ventas");
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
        return ventas;
    }
    
    public int agregarVenta(Venta venta){
        int registros = 0;
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = getConnection();
            ps = conn.prepareStatement(SQL_INSERT);
            ps.setString(1,venta.getFechaVenta());
            ps.setString(2, venta.getHoraVenta());
            ps.setObject(3, venta.getUsuario());
            ps.setObject(4,venta.getEvento());
            ps.setObject(5, venta.getAsiento());
            ps.setObject(6, venta.getMonto());
            registros = ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Ocurrió un error al agregar venta");
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
    public int actualizarVenta(Venta venta){
        int registros = 0;
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = getConnection();
            ps = conn.prepareStatement(SQL_UPDATE);
            ps.setString(1,venta.getFechaVenta());
            ps.setString(2,venta.getHoraVenta());
            ps.setObject(3, venta.getUsuario());
            ps.setObject(4, venta.getEvento());
            ps.setObject(5, venta.getAsiento());
            ps.setObject(6, venta.getMonto());
            ps.setInt(7, venta.getIdVenta());
            registros = ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Ocurrió un error al actualizar venta");
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
    
    public int eliminarVenta(Venta venta){
        int registros = 0;
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = getConnection();
            ps = conn.prepareStatement(SQL_DELETE);
            ps.setInt(1, venta.getIdVenta());
            registros = ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Ocurrió un error al eliminar venta");
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
