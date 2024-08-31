
package datos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import ufide.controller.MetodosController;


public class Archivo {
    private String nombre;
    MetodosController metodos = new MetodosController();
    public Archivo(String nombre){
        this.nombre = nombre;
    }
    
    public Archivo(){
        
    }
    
    public ArrayList<String> obtenerTextoDelArchivo() throws IOException{
        ArrayList<String> lineasDeTexto=null;
        try {
            File archivo = obtenerArchivo();
            if (archivo.exists()){
                lineasDeTexto=new ArrayList<>();
                BufferedReader br = new BufferedReader(new FileReader(archivo));
                String linea;
                while((linea = br.readLine()) != null){
                    System.out.println(linea);
                    lineasDeTexto.add(linea);
                }
                br.close();
            }else{
                metodos.msg("El Archivo de Texto no existe", "Mensaje de Información", 1);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace(System.out);
            metodos.msg("Ocurrió un error", "Mensaje de Información", 1);
        }
        return lineasDeTexto;
    }
    
    public File obtenerArchivo(){
        try {
            URL url = getClass().getClassLoader().getResource("archivos/" + nombre);            return new File(url.toURI());
        } catch (URISyntaxException e) {
            e.printStackTrace(System.out);
        }
        return null;
    }
    
    
}
