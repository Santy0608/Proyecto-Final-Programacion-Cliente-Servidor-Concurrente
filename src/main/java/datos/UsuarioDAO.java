
package datos;
import static datos.Conexion.getConnection;
import domain.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;  


public class UsuarioDAO {
    private static final String SQL_SELECT = "SELECT id_usuario, nombre, apellido, nombre_usuario, contraseña, estado FROM usuario";
    private static final String SQL_INSERT = "INSERT INTO usuario(nombre, apellido, nombre_usuario, contraseña, estado) VALUES (?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE usuario SET nombre = ?, apellido = ?, nombre_usuario = ?, contraseña = ?, estado = ? WHERE id_usuario = ?";  
    private static final String SQL_DELETE = "DELETE FROM usuario WHERE id_usuario = ?";
    private static final String SQL_ACTIVO = "UPDATE usuario SET estado = ? WHERE id_usuario = ?";
    private static final String SQL_INACTIVO = "UPDATE usuario SET estado = ? WHERE id_usuario = ?";
    public List<Usuario> listarUsurios(){
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Usuario> usuarios = new ArrayList<>();
        Usuario usuario = null; 
        try {
            conn = getConnection();
            ps = conn.prepareStatement(SQL_SELECT);
            rs = ps.executeQuery();
            while (rs.next()){
                usuario = new Usuario();
                usuario.setIdUsuario(rs.getInt("id_usuario"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setApellido(rs.getString("apellido"));
                usuario.setNombreUsuario(rs.getString("nombre_usuario"));
                usuario.setContraseña(rs.getString("contraseña"));
                usuario.setEstado(rs.getString("estado"));
                usuarios.add(usuario);
            }
        } catch (SQLException e) {
            System.out.println("Ocurrió un error al listar usuarios");
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
        return usuarios;
    }
    public int agregarUsuario(Usuario usuario){
        Connection conn = null;
        PreparedStatement ps = null;
        int registros = 0;
        try {
            conn = getConnection();
            ps = conn.prepareStatement(SQL_INSERT);
            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getApellido());
            ps.setString(3, usuario.getNombreUsuario());
            ps.setString(4, usuario.getContraseña());
            ps.setString(5, usuario.getEstado());
            registros = ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Ocurrió un error al agregar un usuario");
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
    
    public int actualizarUsuario(Usuario usuario){
        Connection conn = null;
        PreparedStatement ps = null;
        int registros = 0;
        try {
            conn = getConnection();
            ps = conn.prepareStatement(SQL_UPDATE);
            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getApellido());
            ps.setString(3, usuario.getNombreUsuario());
            ps.setString(4, usuario.getContraseña());
            ps.setString(5, usuario.getEstado());
            ps.setInt(6, usuario.getIdUsuario());
            registros = ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Ocurrió un error al actualizar un usuario");
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
    
    public int usuarioActivo(Usuario usuario){
        Connection conn = null;
        PreparedStatement ps = null;
        int registros = 0;
        try {
            conn = getConnection();
            ps = conn.prepareStatement(SQL_ACTIVO);
            ps.setString(1,usuario.getEstado());
            ps.setInt(2, usuario.getIdUsuario());
            registros = ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Ocurrió un error al actualizar el estado a activo " + e.getMessage());
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
    
    public int usuarioInactivo(Usuario usuario){ 
        Connection conn = null;
        PreparedStatement ps = null; 
        int registros = 0;
        try {
            conn = getConnection();
            ps = conn.prepareStatement(SQL_INACTIVO);
            ps.setString(1, usuario.getEstado());
            ps.setInt(2, usuario.getIdUsuario());
            registros = ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Ocurrió un error al actualizar el estado a inactivo " + e.getMessage());
        }
        finally{
            try {
                
            } catch (Exception e) {
                System.out.println("Ocurrió un error al cerrar conexión");
            }
        }
        return registros;
    }
    
    public int eliminarUsuario(Usuario usuario){
        Connection conn = null;
        PreparedStatement ps = null;
        int registros = 0;
        try {
            conn = getConnection();
            ps = conn.prepareStatement(SQL_DELETE);
            ps.setInt(1, usuario.getIdUsuario());
            registros = ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Ocurrió un error al eliminar");
            
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
