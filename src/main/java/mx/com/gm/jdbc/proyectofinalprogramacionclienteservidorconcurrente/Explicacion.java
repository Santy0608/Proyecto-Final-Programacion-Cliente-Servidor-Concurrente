
package mx.com.gm.jdbc.proyectofinalprogramacionclienteservidorconcurrente;

import datos.UsuarioDAO;
import domain.Usuario;
import java.util.List;
import ufide.controller.MetodosController;

public class Explicacion {
    public static void main(String[] args) {
        MetodosController metodos = new MetodosController();
        UsuarioDAO usuarioDao = new UsuarioDAO();
        List<Usuario> usuarios = usuarioDao.listarUsurios();
        String opc[] = new String[5];
        opc[0] = "Agregar Usuario";
        opc[1] = "Listar";
        opc[2] = "Modificar";
        opc[3] = "Eliminar";
        opc[4] = "Salir";
        int selecciono = -1;
        while (selecciono != 4){
            selecciono = metodos.menuBotones("Seleccione una opción", "Menú", opc, "Salir");
            if (selecciono == 0){
                var nombre = metodos.getCadena("Nombre","Nombre Usuario");
                var apellido = metodos.getCadena("Apellido", "Apellido Usuario");
                var nombreUsuario = metodos.getCadena("Nombre Usuario","Nombre Usuario");
                var contraseña = metodos.getCadena("Contraseña","Password");
                var estado = "Activo";
                var usuario = new Usuario(nombre, apellido, nombreUsuario, contraseña, estado);
                usuarioDao.agregarUsuario(usuario);
                metodos.msg("Usuario agregado","Mensaje de Informacióm", 1);
            }
            else if (selecciono == 1){
                for (Usuario usuario : usuarios) {
                    if (usuario != null){
                        metodos.msg("" + usuario, "Listado",1);
                    }
                }
            }
            else if (selecciono == 2){
                int id = Integer.parseInt(metodos.getCadena("Id", "Modificar"));
                boolean encontrado = false;
                for (Usuario usuario : usuarios) {
                    if (usuario != null && usuario.getIdUsuario() == id){
                        encontrado = true;
                        usuario.setNombre("Karla");
                        usuario.setApellido("Lara");
                        usuario.setNombreUsuario("KLara");
                        usuario.setContraseña("24325");
                        usuario.setEstado("Activo");
                        usuarioDao.actualizarUsuario(usuario); 
                    }
                }
                if(!encontrado){
                    metodos.msg("Not found","Mensaje de Información",1);
                    
                }
            }
            else if (selecciono == 3){
                int id = Integer.parseInt(metodos.getCadena("Id", "Eliminar"));
                boolean found = false;
                for (Usuario usuario : usuarios) {
                    if (usuario != null && usuario.getIdUsuario() == id){
                        found = true;
                        usuarioDao.eliminarUsuario(usuario);
                        metodos.msg("Eliminado", "Mensaje de Información", 1);
                        
                    }
                }
                if(!found){
                    metodos.msg("No encontrado", "Mensaje de Error", 1);
                }
            }
            else if (selecciono == 4){
                
            }
        }
    }
}
