
package datos;

import domain.Usuario;
import java.io.IOException;
import java.util.ArrayList;
import ufide.controller.MetodosController;

public class ListadoUsuarios {
    private ArrayList<Usuario> usuarios; 
    MetodosController metodos = new MetodosController();
    public ListadoUsuarios(){
        usuarios = new ArrayList<>();
    }
    
    public void listarUsuarios(){
        for (Usuario usuario : usuarios) {
            if (usuario != null){
                metodos.msg("" + usuario, "Mensaje de Información", 1);
            }
        }
    }
    
    public void agregarUsuario(Usuario usuario){
        usuarios.add(usuario);
        
    }
    
    public void buscarUsuario(Usuario usuario){
        var indice = usuarios.indexOf(usuario);
        if (indice == -1){
            metodos.msg("Usuario no encontrado", "Mensaje de Información", 1);
        }else{
            metodos.msg("Usuario encontrado en el índice " + indice, "Mensaje de Información", 1);
        }
    }
}
