
package datos;


import static datos.Conexion.getConnection;
import domain.Evento;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EventoDAO {
    private static final String SQL_SELECT = "SELECT id_evento, nombre_evento, fecha, lugar, ciudad, direccion, estado, asignado, disponibilidad FROM evento";
    private static final String SQL_INSERT = "INSERT INTO evento(nombre_evento, fecha, lugar, ciudad, direccion, estado, asignado, disponibilidad) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE evento SET nombre_evento = ?, fecha = ?, lugar = ?, ciudad = ?, direccion = ?, estado = ?, asignado = ?, disponibilidad = ? WHERE id_evento = ?";
    private static final String SQL_DELETE = "DELETE FROM evento WHERE id_evento = ?";
    private static final String SQL_INACTIVAR = "UPDATE evento SET disponibilidad = ? WHERE id_evento = ?";
    private static final String SQL_ACTIVAR = "UPDATE evento SET disponibilidad = ? WHERE id_evento = ?";
    
    public List<Evento> listarEventos(){
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Evento evento = null;
        List<Evento> eventos = new ArrayList<>();
        try {
            conn = getConnection();
            ps = conn.prepareStatement(SQL_SELECT);
            rs = ps.executeQuery();
            while (rs.next()){
                evento = new Evento();
                evento.setId(rs.getInt("id_evento"));
                evento.setNombreEvento(rs.getString("nombre_evento"));
                evento.setFecha(rs.getString("fecha"));
                evento.setLugar(rs.getString("lugar"));
                evento.setCiudad(rs.getString("ciudad"));
                evento.setDireccion(rs.getString("direccion"));
                evento.setEstado(rs.getString("estado"));
                evento.setAsignado(rs.getString("asignado"));
                evento.setDisponibilidad(rs.getString("disponibilidad"));
                eventos.add(evento);
            }
        } catch (SQLException e) {
            System.out.println("Ocurrió un error al listar Eventos");
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
        return eventos;
    }
    public int agregarEvento(Evento evento){
        int registros = 0;
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = getConnection();
            ps = conn.prepareStatement(SQL_INSERT);
            ps.setString(1, evento.getNombreEvento());
            ps.setString(2, evento.getFecha());
            ps.setString(3, evento.getLugar());
            ps.setString(4, evento.getCiudad());
            ps.setString(5, evento.getDireccion());
            ps.setString(6, evento.getEstado());
            ps.setString(7, evento.getAsignado());
            ps.setString(8, evento.getDisponibilidad());
            registros = ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Ocurrió un error al agregar un evento");
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
    public int actualizarEvento(Evento evento){
        int registros = 0;
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = getConnection();
            ps = conn.prepareStatement(SQL_UPDATE);
            ps.setString(1, evento.getNombreEvento());
            ps.setString(2, evento.getFecha());
            ps.setString(3, evento.getLugar());
            ps.setString(4, evento.getCiudad());
            ps.setString(5, evento.getDireccion());
            ps.setString(6, evento.getEstado());
            ps.setString(7, evento.getAsignado());
            ps.setString(8, evento.getDisponibilidad());
            ps.setInt(9, evento.getId());
            registros = ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Ocurrió un error al actualizar un evento");
        }
        finally{
            try{
                Conexion.close(conn);
                Conexion.close(ps);
            }catch(SQLException e){
                System.out.println("Ocurrió un error al cerrar conexión");
            }
        }
        return registros;
    }
    
    public int inactivarEvento(Evento evento){
        int registros = 0;
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = getConnection();
            ps = conn.prepareStatement(SQL_INACTIVAR);
            ps.setString(1, evento.getDisponibilidad());
            ps.setInt(2, evento.getId());
            registros = ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Ocurrió un error al inactivar un evento");
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
    
    public int activarEvento(Evento evento){
        int registros = 0;
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = getConnection();
            ps = conn.prepareStatement(SQL_ACTIVAR);
            ps.setString(1,evento.getDisponibilidad());
            ps.setInt(2, evento.getId());
            registros = ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Ocurrió un error al activar un evento");
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
    
    public int eliminarEvento(Evento evento){
        int registros = 0;
        Connection conn  = null;
        PreparedStatement ps = null;
        try {
            conn = getConnection();
            ps = conn.prepareStatement(SQL_DELETE);
            ps.setInt(1, evento.getId());
            registros = ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Ocurrió un error al eliminar un evento");
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
