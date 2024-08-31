package presentacion;
import datos.ArchivoUsuario;
import domain.Asiento;
import domain.Evento;
import domain.Usuario;
import domain.Venta;
import inicio_sesionn.Login; 
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import ufide.controller.MetodosController; 
/**
 *
 * @author Santiago  
 */
      
public class Main {
       
    public static ArrayList<Evento> eventos;
    public static ArrayList<Asiento> asientos;
    public static ArrayList<Usuario> usuarios; 
     
    public static void main(String[] args) { 
        eventos = new ArrayList<>();  
        asientos = new ArrayList<>();
        usuarios = new ArrayList<>(); 
         
//        System.out.println("Hello World!");
//        Evento evento = new Evento();
//    List<Usuario> usuarios = new ArrayList<>();
//    List<Venta> ventas = new ArrayList<>();
//    List<Evento> eventos = new ArrayList<>();
//    List<Asiento> asientos = new ArrayList<>(); 
//    
//    MetodosController metodos = new MetodosController();
//    String opc[] = new String[7];
//    opc[0] = "Agregar Usuario";
//    opc[1] = "Agregar Evento";
//    opc[2] = "Agregar Asiento";
//    opc[3] = "Agregar Venta";
//    opc[4] = "Mostrar Venta";
//    opc[5] = "Salir";
//    int selecciono = -1;
//    while (selecciono != 5){
//        selecciono = metodos.menuBotones("Seleccione una opción","Gestión Ventas", opc, "Salir");
//        if (selecciono == 0){
//            int idUsuario = 1;
//            String nombreUsuario = metodos.getCadena("Nombre","Nombre Usuario");
//            String apellido = metodos.getCadena("Apellido Usuario","Apellido Usuario");
//            String nombreUsuarioLogin = metodos.getCadena("Nombre Usuario", "Nombre de Usuario");
//            String contraseña = metodos.getCadena("Contraseña", "Contraseña Usuario");
//            String estado = "Activo";
//            var usuario = new Usuario();
//            usuario.setIdUsuario(idUsuario);
//            usuario.setNombre(nombreUsuario);
//            usuario.setApellido(apellido);
//            usuario.setNombreUsuario(nombreUsuarioLogin);
//            usuario.setContraseña(contraseña);
//            usuario.setEstado(estado);
//            usuarios.add(usuario);
//            metodos.msg("Usuario agregado", "Mensaje de Información", 1);
//        }
//        else if (selecciono == 1){
//            int idEvento = 1;
//            String nombreEvento = metodos.getCadena("Nombre","Nombre Evento");
//            String fecha = metodos.getCadena("Fecha","Fecha Evento");
//            String lugar = metodos.getCadena("Lugar","Lugar Evento");
//            String ciudad = metodos.getCadena("Ciudad", "Ciudad Evento");
//            String direccion = metodos.getCadena("Dirección", "Dirección Evento");
//            String estado = metodos.getCadena("Estado Evento","Estado Evento");
//            String asignado = "No Asignado";
//            var evento = new Evento();
//            evento.setId(idEvento);
//            evento.setNombreEvento(nombreEvento); 
//            evento.setFecha(fecha);
//            evento.setLugar(lugar);
//            evento.setCiudad(ciudad);
//            evento.setDireccion(direccion);
//            evento.setEstado(estado);
//            evento.setAsignado(asignado);
//            eventos.add(evento);
//            metodos.msg("Evento Agregado", "Mensaje de Información",1);
//        }
//        else if (selecciono == 2){
//            int idAsiento = 1;
//            String codigoArea = metodos.getCadena("Codigo Área","Código Área Asiento");
//            String numeroAsiento = metodos.getCadena("Número Asiento","Número de Asiento");
//            double precio = Double.parseDouble(metodos.getCadena("Precio Asiento", "Precio de Asiento"));
//            String estado = "No vendido";
//            String asignado = "No Asignado";
//            var asiento = new Asiento(idAsiento, codigoArea, numeroAsiento, precio, estado, asignado);
//            asientos.add(asiento);
//        }
//        else if (selecciono == 3){
//            boolean usuarioEncontrado = false;
//            String nombreUsuario = metodos.getCadena("Nombre", "Nombre Usuario");
//            for (Usuario usuario : usuarios) {
//                if (usuario != null && usuario.getNombre().equalsIgnoreCase(nombreUsuario)){
//                    metodos.msg("Usuario " + usuario, "Mensaje de Información", 1);
//                    usuarioEncontrado = true;
//                    String nombreEvento = metodos.getCadena("Nombre Evento","Eventos");
//                    boolean eventoEncontrado = false;
//                    for (Evento evento : eventos) {
//                        if (evento != null && evento.getNombreEvento().equalsIgnoreCase(nombreEvento)){
//                            metodos.msg("Evento " + evento, "Mensaje de Información", 1);
//                            eventoEncontrado = true;
//                            String asiento = metodos.getCadena("Número Asiento","Número de Asiento");
//                            boolean asientoEncontrado = false;
//                            for (Asiento asiento1 : asientos) {
//                                if (asiento1 != null && asiento1.getNumeroAsiento().equalsIgnoreCase(asiento)){
//                                    asientoEncontrado = true;
//                                    metodos.msg("Asiento " + asiento1, "Mensaje de Información", 1);
//                                    int c = metodos.SIoNo("¿Desa agregar una venta?","Agregar Venta");
//                                    if (c == JOptionPane.YES_OPTION){
//                                        for (Venta venta : ventas) {
//                                            if (venta != null){
//                                                int idVentaCompra = 1;
//                                                String fechaCompraVenta = metodos.getCadena("Fecha Compra", "Agregar Venta");
//                                                String horaCompraVenta = metodos.getCadena("Hora", "Hora Compra");
//                                                String usuarioVenta = nombreUsuario;
//                                                String eventoVenta = nombreEvento;
//                                                String estadoVenta = "Vendido";
////                                                var venta1 = new Venta(idVentaCompra, fechaCompraVenta, horaCompraVenta, usuarioVenta, eventoVenta, estadoVenta);
////                                                venta1.agregarAsientoVenta(asiento1);
//                                            }
//                                        }
//                                    }
//                                    else if (c == JOptionPane.NO_OPTION){
//                                        
//                                    }
//                                }
//                            }
//                            if(!asientoEncontrado){
//                                metodos.msg("Asiento no encontrado", "Mensaje de Información", 1);
//                            }
//                        }
//                    }
//                    if(!eventoEncontrado){
//                        metodos.msg("Evento no encontrado","Mensaje de Información", 1);
//                    }
//                }
//            }
//            if(!usuarioEncontrado){
//                metodos.msg("Usuario no encontrado", "Mensaje de Información",1);
//            }
//        }
//        else if (selecciono == 4){
//            for (Evento evento : eventos) {
//                if (evento != null){
//                    for (Asiento asiento : asientos) {
//                        if (asiento != null){
//                            for (Venta venta : ventas) {
//                                if (venta != null){
//                                    for (Usuario usuario : usuarios) {
//                                        if (usuario != null){
////                                            venta.mostrarVentaConfirmada(usuario, evento);
//                                        }
//                                    }
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//        }
//        else if (selecciono == 5){
//            
//        }
//    }
            
//        Multifiestas f1 = new Multifiestas();
//        f1.setVisible(true);
//        f1.setLocationRelativeTo(f1);      
////       
//    Login f1 = new Login();
//    f1.setVisible(true);
//    f1.setLocationRelativeTo(f1);
//          Login l2 = new Login();
//          l2.setVisible(true);
//          l2.setLocationRelativeTo(l2);
       Login l1 = new Login(); 
       l1.setVisible(true);
//         ArchivoUsuario usuarios = new ArchivoUsuario();
         
    }
}
