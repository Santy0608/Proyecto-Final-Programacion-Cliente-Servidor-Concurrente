
package datos;

import domain.Usuario;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;
import ufide.controller.MetodosController;

public class ArchivoUsuario {
    MetodosController metodos = new MetodosController();
    private final String nombreArchivo = "usuarios.txt";
//    public ArrayList<Usuario> obtener() throws IOException{
//        ArrayList<Usuario> usuarios = null;
//        Archivo archivo = new Archivo("usuarios.txt");
//        ArrayList<String> lineas = archivo.obtenerTextoDelArchivo();
//        if (lineas != null){
//            usuarios = new ArrayList<>();
//            for (int i = 0; i < lineas.size(); i++) {
//                String linea = lineas.get(i);
//                StringTokenizer tokens = new StringTokenizer(linea,",");
//                int id = Integer.parseInt(tokens.nextToken());
//                String nombre = tokens.nextToken();
//                String apellido = tokens.nextToken();
//                String nombreUsuario = tokens.nextToken();
//                String contraseña = tokens.nextToken();
//                String estado = tokens.nextToken();
//                var usuario = new Usuario(id, nombre, apellido, nombreUsuario, contraseña, estado);
//                usuarios.add(usuario);
//            }
//        }
//        return usuarios;
//    }
    
    public ArchivoUsuario(){
        var archivo = new File(nombreArchivo);
        try {
            if (archivo.exists()){
                metodos.msg("El archivo ya existe","Mensaje de Información", 1);
            }else{
                var salida = new PrintWriter(new FileWriter(archivo));
                salida.close();
                metodos.msg("Se ha creado el arhivo", "Mensaje de Información", 1);
            }
        } catch (IOException e) {
            System.out.println("Ocurrió un error al crear el archivo");
        }
    }
    
    public void listarUsuarios(){
        var archivo = new File(nombreArchivo);
        try {
            var entrada = new BufferedReader(new FileReader(archivo));
            String linea;
            linea = entrada.readLine();
            while (linea != null){
                var usuario = new Usuario();
                metodos.msg("Usuario " + usuario,"Mensaje de Información",1);
                linea = entrada.readLine();
            }
            entrada.close();
        } catch (IOException e) {
            System.out.println("Ocurrió un error al abrir el archivo: " + e.getMessage());
        }
    }
    
    public void agregarUsuario(Usuario usuario){
        boolean anexar = false;
        var archivo = new File(nombreArchivo);
        try {
            anexar = archivo.exists();
            var salida = new PrintWriter(new FileWriter(archivo, anexar));
            salida.println(usuario);
            salida.close();
            metodos.msg("Se ha agregado el usuario al archivo","Mensaje de Información", 1);
            
        } catch (Exception e) {
            System.out.println("Ocurrió un error al agregar un usuario al archivo");
        }
    }
      
    public void buscarUsuario(Usuario usuario){
        var archivo = new File(nombreArchivo);
        try {
            var entrada = new BufferedReader(new FileReader(archivo));
            String lineaTexto;
            lineaTexto = entrada.readLine();
            var indice = 1;
            var encontrada = false;
            var usuarioBuscar = usuario.getNombre();
            
        } catch (Exception e) {
            System.out.println("Ocurrió un error al buscar un usuario en el archivo");
        }
    }
    
    
    
}
