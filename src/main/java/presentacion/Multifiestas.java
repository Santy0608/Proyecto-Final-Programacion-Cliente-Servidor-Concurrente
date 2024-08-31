
package presentacion;

import datos.AsientoDAO;
import datos.CajaDAO;
import datos.EventoDAO;
import datos.Conexion;
import domain.Asiento;
import domain.Evento;
import domain.Administrador;
import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import static datos.Conexion.getConnection; 
//import datos.OrdenDAO;
//import datos.OrdenDAO;
import datos.UsuarioDAO;
import datos.VentaDAO;
import domain.Caja;
import domain.Orden;
import domain.Usuario;
import domain.Venta;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.sql.ResultSet; 
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import ufide.controller.MetodosController;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;
import javax.swing.table.TableModel;


public class Multifiestas extends javax.swing.JFrame {
//    AdministradorDAO usuarioDao = new AdministradorDAO();
//    List<Administrador> administradores = usuarioDao.listarAdministrador();
    EventoDAO eventoDao = new EventoDAO();
    List<Evento> eventos = new ArrayList<>();
    List<Evento> eventosDB = eventoDao.listarEventos();
    AsientoDAO asientoDao = new AsientoDAO();
    List<Asiento> asientosDB = asientoDao.listarAsientos();
    List<Asiento> asientos = new ArrayList<>();
    MetodosController metodos = new MetodosController();
    UsuarioDAO usuarioDB = new UsuarioDAO();
//    List<Usuario> usuarios = usuarioDB.listarUsurios();
    VentaDAO ventaDao = new VentaDAO();
    List<Venta> ventas = ventaDao.listarVentas();
    DefaultTableModel modeloEventos = new DefaultTableModel();
    ArrayList<Usuario> usuarios = new ArrayList<>();
    CajaDAO cajaDao = new CajaDAO();
    
    //--------------------------------------------------------
    private double auxMontoTotal = 0;
    private boolean tAseleccionado = false;
    private boolean tEseleccionado = false;
    private String auxIdEvento = null;
    private String auxIdAsiento = null;
    private int idVenta = -1;
    private Evento eventoAsignado;
    private Asiento asientoAsignado;
    //--------------------------------------------------------
    public Multifiestas() {
        initComponents();
        this.setLocationRelativeTo(this);
        jp1.setVisible(false);
        jp2.setVisible(false);
        jp3.setVisible(false);
        jp4.setVisible(false);
        tab1.setBackground(new Color(204, 204, 255));
        tab2.setBackground(new Color(204, 204, 255));
        tab3.setBackground(new Color(204, 204, 255));
        tab4.setBackground(new Color(204, 204, 255));
//        nombreEventoTexto.setVisible(false);
//        fechaTexto.setVisible(false);
//        LugarComboBox.setVisible(false); 
//        ciudadTexto.setVisible(false);
//        direccionTexto.setVisible(false);
//        estadoTexto.setVisible(false);
//        confirmadoRadioBoton.setVisible(false);
//        noConfirmadoRadioBoton.setVisible(false);      
//        estadoTexto.setVisible(false);
        actualizarFechaHora1();
        actualizarFechaHora2();
        actualizarFechaHora3();
        actualizarFechayHoraConHilo();
        listadoVentas();
        listarAsientos();
        listadoUsuarios();
        listarAsientosVenta();
        listadoEventosVentas();
        listadoEventos();
        calculoMontoTotalConHilo();
    }
//------------------------------------------------------------------------------   
   public void actualizarFechaHora1(){
       Thread hilo = new Thread(() -> {
           int delay = 1000;
           while(true){
               LocalDateTime ahora = LocalDateTime.now();
               int año = ahora.getYear();
               int mes = ahora.getMonthValue();
               int dia = ahora.getDayOfMonth();
               int hora = ahora.getHour();
               int minutos = ahora.getMinute();
               int segundos = ahora.getSecond();
               String horaTexto = Integer.toString(hora) + ";" + Integer.toString(minutos) + ":" + Integer.toString(segundos);
               String fechaTexto = Integer.toString(dia) + " / " + Integer.toString(mes) + " / " + Integer.toString(año);
               SwingUtilities.invokeLater(() ->{
                   fechaTexto1.setText(fechaTexto);
                   horaTexto1.setText(horaTexto);
               });
               try {
                   Thread.sleep(delay);
               } catch (InterruptedException e) {
                   e.printStackTrace(System.out);
               }
           }
       });
       hilo.start();
   }
   
   public void actualizarFechaHora2(){
       Thread hilo = new Thread(() -> {
           int delay = 1000;
           while (true){
               LocalDateTime ahora = LocalDateTime.now();
               int año = ahora.getYear();
               int mes = ahora.getMonthValue();
               int dia = ahora.getDayOfMonth();
               int hora = ahora.getHour();
               int minutos = ahora.getMinute();
               int segundos = ahora.getSecond();
               String horaTexto = Integer.toString(hora) + ";" + Integer.toString(minutos) + ":" + Integer.toString(segundos);
               String fechaTexto = Integer.toString(dia) + " / " + Integer.toString(mes) + " / " + Integer.toString(año);
               SwingUtilities.invokeLater(() -> {
                   fechaTexto2.setText(fechaTexto);
                   horaTexto2.setText(horaTexto);
               });
               try {
                   Thread.sleep(delay);
               } catch (InterruptedException e) {
                   e.printStackTrace(System.out);
               }
           }
       });
       hilo.start();
   }
   
   public void actualizarFechaHora3(){
       Thread hilo = new Thread(() -> {
          int delay = 1000;
          while (true){
              LocalDateTime ahora = LocalDateTime.now();
              int año = ahora.getYear();
              int mes = ahora.getMonthValue();
              int dia = ahora.getDayOfMonth();
              int hora = ahora.getHour();
              int minutos = ahora.getMinute();
              int segundos = ahora.getSecond();
              String horaTexto = Integer.toString(hora) + ";" + Integer.toString(minutos) + ":" + Integer.toString(segundos);
              String fechaTexto = Integer.toString(dia) + " / " + Integer.toString(mes) + " / " + Integer.toString(año);
              SwingUtilities.invokeLater(() -> {
                  fechaTexto3.setText(fechaTexto);
                  horaTexto3.setText(horaTexto);
                  
              });
              try {
                  Thread.sleep(delay);
              } catch (InterruptedException e) {
                  e.printStackTrace(System.out);
              }
          }
       });
       hilo.start();
   }
   
   public void calculoMontoTotalConHilo(){
          Thread hilo = new Thread(() -> {
          int delay = 1000;
          while (true){
              double montoTotal = 0;
              for (Venta venta : ventas) {
                  if (venta != null){
                      montoTotal += venta.getMonto();
                      montoTotalTexto.setText(String.valueOf(montoTotal));
                  }   
              }
              try {
                  Thread.sleep(delay);
              } catch (InterruptedException e) {
                  e.printStackTrace(System.out);
              }
          }
       });
       hilo.start();
   }
   
   public void listadoVentasConHilo(){
       Thread hilo = new Thread(() -> {
           int delay = 1000;
           while (true) {
               try {
                   DefaultTableModel modeloVentas = new DefaultTableModel();
                   tablaVentas.setModel(modeloVentas);
                   PreparedStatement ps = null;
                   Connection conn = null;
                   ResultSet rs = null;
                   conn = getConnection();
                   String SQL_SELECT = "SELECT id_venta, fecha_venta, hora_venta, usuario, evento, asiento, monto from venta";
                   ps = conn.prepareStatement(SQL_SELECT);
                   rs = ps.executeQuery();
                   ResultSetMetaData rsmt = rs.getMetaData();
                   int cantidadColumnas = rsmt.getColumnCount();
                   modeloVentas.addColumn("Id Venta");
                   modeloVentas.addColumn("Fecha Venta");
                   modeloVentas.addColumn("Hora Venta");
                   modeloVentas.addColumn("Usuario");
                   modeloVentas.addColumn("Evento");
                   modeloVentas.addColumn("Asiento");
                   modeloVentas.addColumn("Monto");
                   while (rs.next()) {
                       Object[] filas = new Object[cantidadColumnas];
                       for (int i = 0; i < filas.length; i++) {
                           filas[i] = rs.getObject(i + 1);
                       }
                       modeloVentas.addRow(filas);
                   }
               } catch (SQLException e) {
                   System.out.println("Ocurrió un error al listar ventas en la tabla");
               }
               try {
                   Thread.sleep(delay);
               } catch (Exception e) {
                   e.printStackTrace(System.out);
               }
           }
       });
       hilo.start();
   }
   
   public void actualizarFechayHoraConHilo() {//Hilo para actualizar fecha y hora
    Thread hilo = new Thread(() -> {//Instanciar el Hilo
        // al usar el LAMBDA se ahorra la linea de codigo de escribir el metodo "Run"
        int delay = 1000; // milisegundos / Es 1 segundo
        while (true) { //ejecutar indefinidamente el metodo
            LocalDateTime ahora = LocalDateTime.now();
            int año = ahora.getYear();
            int mes = ahora.getMonthValue();
            int dia = ahora.getDayOfMonth();
            int hora = ahora.getHour();
            int minutos = ahora.getMinute();
            int segundos = ahora.getSecond();
            
            //cracion con las cadenas de texto de la fecha y hora:
            String txHora = Integer.toString(hora) + ":" + Integer.toString(minutos) + ":" + Integer.toString(segundos);
            String txFecha = Integer.toString(dia) + " / " + Integer.toString(mes) + " / " + Integer.toString(año);
            
            // Actualiza la interfaz de usuario
            SwingUtilities.invokeLater(() -> {//Esto es para evitar errores con el hilo principal que maneja al SWING
                txtHora.setText(txHora);//asignarle el valor a los TextField
                txtFecha.setText(txFecha);
                } 
            );
            try {
                Thread.sleep(delay); //Esperar 1 segundo para volver a actualizarse
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    });
    hilo.start(); // Inicia el hilo
    //video que explica el "lambda": https://youtu.be/OFXOscTAGCI?si=x991UC0MdNexdhxW
}
   
//------------------------------------------------------------------------------

public void realizarCompra(){//DE MOMENTO AGREGAR FUNCIONA BIEN PERO HAY QUE REVISAR VARIAS COSAS
      for (Evento evento : Main.eventos) {
        if (evento != null) {
            if (evento.getDisponibilidad().equalsIgnoreCase("Activo")) {
                if (evento.getAsignado().equalsIgnoreCase("No Asignado")) {
                    for (Asiento asiento : Main.asientos) {
                        if (asiento != null) {
                            if (asiento.getDisponibilidad().equalsIgnoreCase("Activo")) {
                                if (asiento.getEstado().equalsIgnoreCase("Libre")) {
                                    if (!txtUsu.getText().isEmpty()) {
                                        String usu = buscarUsuarioV();//Paso 1- Obtener el usuario
                                        if (usu != null) {
                                            if (tAseleccionado && tEseleccionado) {//Paso 2- Verificar que tanto el evento como asiento sean seleccionados
                                                idVenta += 1;
                                                Venta nVenta = new Venta(idVenta, txtFecha.getText(), txtHora.getText(), usu, auxIdEvento, auxIdAsiento, auxMontoTotal);
                                                evento.setAsignado("Vendido");
                                                asiento.setEstado("Ocupado");
                                                ventas.add(nVenta);
                                                ventaDao.agregarVenta(nVenta);
                                                JOptionPane.showMessageDialog(null, "Venta guardada", "Venta exitosa", JOptionPane.INFORMATION_MESSAGE);
                                                //Restablecer valores: 
                                                auxMontoTotal = 0;
                                                auxIdAsiento = null;
                                                auxIdEvento = null;
                                                tAseleccionado = false;
                                                tEseleccionado = false;
                                            } else {
                                                JOptionPane.showMessageDialog(null, "Evento o Asiento no seleccionado.");
                                            }
                                        } else {
                                            JOptionPane.showMessageDialog(null, "Usuario no encontrado");
                                        }
                                    } else {
                                        JOptionPane.showMessageDialog(null, "Usuario vacio");
                                    }

                                } else if (asiento.getDisponibilidad().equalsIgnoreCase("Ocupado")) {
                                    metodos.msg("Este asiento ya está ocupado", "Mensaje de Información", 1);
                                } 

                            } else if (asiento.getDisponibilidad().equalsIgnoreCase("Inactivo")) {
                                metodos.msg("No se puede comprar un asiento inactivo", "Mensaje de Información", 1);
                            }
                        }//Fin If Asientos

                    }//Fin For Asientos
                } else if (evento.getAsignado().equalsIgnoreCase("Vendido")) {
                    metodos.msg("Este evento ya ha sido vendido", "Mensaje de Información", 1);
                }
            } else if (evento.getDisponibilidad().equalsIgnoreCase("Inactivo")) {
                metodos.msg("No se puede comprar un evento inactivo", "Mensaje de Información", 1);
            }
        }//Fin If Eventos
    }//Fin For Eventos

    }

public String buscarUsuarioV(){
    for (Usuario usuario : Main.usuarios) {
        if (usuario.getNombreUsuario().equals(txtUsu.getText())) {
            return usuario.getNombreUsuario();
        }
    }
    return null;
}

//------------------------------------------------------------------------------    
   public void listadoUsuarios(){
        DefaultTableModel modeloUsuarios = new DefaultTableModel(new String[]{"Id","Nombre","Apellido","Nombre Usuario","Contraseña","Estado"}, Main.usuarios.size());
        tablaUsuarios.setModel(modeloUsuarios);
        TableModel modelo = tablaUsuarios.getModel();
        for (int i = 0; i < Main.usuarios.size(); i++) {
            Usuario usuario = Main.usuarios.get(i);
            modelo.setValueAt(usuario.getIdUsuario(),i, 0);
            modelo.setValueAt(usuario.getNombre(),i, 1);
            modelo.setValueAt(usuario.getApellido(), i, 2);
            modelo.setValueAt(usuario.getNombreUsuario(), i, 3);
            modelo.setValueAt(usuario.getContraseña(), i, 4);
            modelo.setValueAt(usuario.getEstado(), i,5);
        }
        
    }

    public void listadoEventos(){
        DefaultTableModel modeloEventos = new DefaultTableModel(new String[]{"Id","Nombre Evento","Fecha","Lugar","Ciudad","Dirección","Estado","Asignado","Disponibilidad"}, Main.eventos.size());
        tablaEventos.setModel(modeloEventos);
        TableModel modelo = tablaEventos.getModel();
        for (int i = 0; i < Main.eventos.size(); i++) {
            Evento evento = Main.eventos.get(i);
            modelo.setValueAt(evento.getId(),i, 0);
            modelo.setValueAt(evento.getNombreEvento(),i, 1);
            modelo.setValueAt(evento.getFecha(), i, 2);
            modelo.setValueAt(evento.getLugar(), i, 3);
            modelo.setValueAt(evento.getCiudad(), i, 4);
            modelo.setValueAt(evento.getDireccion(), i,5);
            modelo.setValueAt(evento.getEstado(), i,6);
            modelo.setValueAt(evento.getAsignado(), i, 7);
            modelo.setValueAt(evento.getDisponibilidad(), i, 8);
        }
    }
    
    public void listarAsientos(){
        DefaultTableModel modeloAsientos = new DefaultTableModel(new String[]{"Id","Código Área","Número Asiento","Costo Venta","Estado","Disponibilidad"},Main.asientos.size());
        tablaAsientos.setModel(modeloAsientos);
        TableModel modelo = tablaAsientos.getModel();
        for (int i = 0; i < Main.asientos.size(); i++) {
            Asiento asiento = Main.asientos.get(i);
            modelo.setValueAt(asiento.getIdAsiento(),i, 0);
            modelo.setValueAt(asiento.getCodigoArea(),i,1);
            modelo.setValueAt(asiento.getNumeroAsiento(), i, 2);
            modelo.setValueAt(asiento.getCostoVenta(), i,3);
            modelo.setValueAt(asiento.getEstado(), i, 4);
            modelo.setValueAt(asiento.getDisponibilidad(), i, 5);
            
        }
    }
    
    public void listarAsientosVenta(){
//        try {
//            DefaultTableModel modeloAsientos = new DefaultTableModel();
//            tablaAsientosVentas.setModel(modeloAsientos);
//            PreparedStatement ps = null;
//            Connection conn = null;
//            ResultSet rs = null;
//            conn = getConnection();
//            String SQL_SELECT = "SELECT id_asiento, codigo_area, numero_asiento, costo_venta, estado, disponibilidad FROM asiento";
//            ps = conn.prepareStatement(SQL_SELECT);
//            rs = ps.executeQuery(); 
//            
//            ResultSetMetaData rsmt = rs.getMetaData();
//            int cantidadColumnas = rsmt.getColumnCount();
//            modeloAsientos.addColumn("Id");
//            modeloAsientos.addColumn("Codigo de Area");
//            modeloAsientos.addColumn("Número de Asiento");
//            modeloAsientos.addColumn("Precio Asiento");
//            modeloAsientos.addColumn("Estado");
//            modeloAsientos.addColumn("Disponibilidad");
//            while (rs.next()){
//                Object[] filas = new Object[cantidadColumnas];
//                for (int i = 0; i < filas.length; i++) {
//                    filas[i] = rs.getObject(i + 1);
//                }
//                modeloAsientos.addRow(filas);
//            }
//        } catch (SQLException e) {
//            System.out.println("Ocurrió un error al listar empleados en la tabla");
//        }
        DefaultTableModel modeloAsientos = new DefaultTableModel(new String[]{"Id","Código Área","Número Asiento","Costo Venta","Estado","Disponibilidad"},Main.asientos.size());
        tablaAsientosVentas.setModel(modeloAsientos);
        TableModel modelo = tablaAsientosVentas.getModel();
        for (int i = 0; i < Main.asientos.size(); i++) {
            Asiento asiento = Main.asientos.get(i);
            modelo.setValueAt(asiento.getIdAsiento(),i, 0);
            modelo.setValueAt(asiento.getCodigoArea(),i,1);
            modelo.setValueAt(asiento.getNumeroAsiento(), i, 2);
            modelo.setValueAt(asiento.getCostoVenta(), i,3);
            modelo.setValueAt(asiento.getEstado(), i, 4);
            modelo.setValueAt(asiento.getDisponibilidad(), i, 5);
        }
    }
    public void listadoEventosVentas(){
//        try {
//            DefaultTableModel modeloEventos = new DefaultTableModel();
//            tablaEventosVentas.setModel(modeloEventos);
//            PreparedStatement ps = null;
//            Connection conn = null;
//            ResultSet rs = null;
//            conn = getConnection();
//            String SQL_SELECT = "SELECT id_evento, nombre_evento, fecha, lugar, ciudad, direccion, estado, asignado, disponibilidad FROM evento";
//            ps = conn.prepareStatement(SQL_SELECT);
//            rs = ps.executeQuery(); 
//            
//            ResultSetMetaData rsmt = rs.getMetaData();
//            int cantidadColumnas = rsmt.getColumnCount();
//            modeloEventos.addColumn("Id");
//            modeloEventos.addColumn("Nombre Evento");
//            modeloEventos.addColumn("Fecha");
//            modeloEventos.addColumn("Lugar");
//            modeloEventos.addColumn("Ciudad");
//            modeloEventos.addColumn("Direccion");
//            modeloEventos.addColumn("Estado");
//            modeloEventos.addColumn("Asignado");
//            while (rs.next()){
//                Object[] filas = new Object[cantidadColumnas];
//                for (int i = 0; i < filas.length; i++) {
//                    filas[i] = rs.getObject(i + 1);
//                }
//                modeloEventos.addRow(filas);
//            }
//        } catch (SQLException e) {
//            System.out.println("Ocurrió un error al listar empleados en la tabla");
//        }
        DefaultTableModel modeloEventos = new DefaultTableModel(new String[]{"Id","Nombre Evento","Fecha","Lugar","Ciudad","Dirección","Estado","Asignado","Disponibilidad"}, Main.eventos.size());
        tablaEventosVentas.setModel(modeloEventos);
        TableModel modelo = tablaEventosVentas.getModel();
        for (int i = 0; i < Main.eventos.size(); i++) {
            Evento evento = Main.eventos.get(i);
            modelo.setValueAt(evento.getId(),i, 0);
            modelo.setValueAt(evento.getNombreEvento(),i, 1);
            modelo.setValueAt(evento.getFecha(), i, 2);
            modelo.setValueAt(evento.getLugar(), i, 3);
            modelo.setValueAt(evento.getCiudad(), i, 4);
            modelo.setValueAt(evento.getDireccion(), i,5);
            modelo.setValueAt(evento.getEstado(), i,6);
            modelo.setValueAt(evento.getAsignado(), i, 7);
            modelo.setValueAt(evento.getDisponibilidad(), i, 8);
        }
    }
    
    public void listadoVentas(){
        try {
            DefaultTableModel modeloVentas = new DefaultTableModel();
            tablaVentas.setModel(modeloVentas); 
            PreparedStatement ps = null;
            Connection conn = null;
            ResultSet rs = null;
            conn = getConnection();
            String SQL_SELECT = "SELECT id_venta, fecha_venta, hora_venta, usuario, evento, asiento, monto from venta";
            ps = conn.prepareStatement(SQL_SELECT);
            rs = ps.executeQuery();
            ResultSetMetaData rsmt = rs.getMetaData();
            int cantidadColumnas = rsmt.getColumnCount();
            modeloVentas.addColumn("Id Venta");
            modeloVentas.addColumn("Fecha Venta");
            modeloVentas.addColumn("Hora Venta");
            modeloVentas.addColumn("Usuario");
            modeloVentas.addColumn("Evento");
            modeloVentas.addColumn("Asiento");
            modeloVentas.addColumn("Monto");
            while (rs.next()){
                Object[] filas = new Object[cantidadColumnas];
                for (int i = 0; i < filas.length; i++) {
                    filas[i] = rs.getObject(i + 1); 
                }
                modeloVentas.addRow(filas);
            }
        } catch (SQLException e) {
            System.out.println("Ocurrió un error al listar ventas en la tabla");            
        }    
    }     
//------------------------------------------------------------------------------    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuBar2 = new javax.swing.JMenuBar();
        jMenu3 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();
        jPanel1 = new javax.swing.JPanel();
        tab1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        tab2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        tab3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        tab4 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel2 = new javax.swing.JPanel();
        jp1 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jSeparator5 = new javax.swing.JSeparator();
        jScrollPane3 = new javax.swing.JScrollPane();
        tablaUsuarios = new javax.swing.JTable();
        jLabel33 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        fechaTexto1 = new javax.swing.JTextField();
        jLabel35 = new javax.swing.JLabel();
        horaTexto1 = new javax.swing.JTextField();
        jp3 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        txtUsu = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tablaAsientosVentas = new javax.swing.JTable();
        jLabel24 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tablaEventosVentas = new javax.swing.JTable();
        jLabel26 = new javax.swing.JLabel();
        txtMonto = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        txtFecha = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        txtHora = new javax.swing.JTextField();
        jSeparator6 = new javax.swing.JSeparator();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jp4 = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tablaVentas = new javax.swing.JTable();
        jSeparator7 = new javax.swing.JSeparator();
        jLabel43 = new javax.swing.JLabel();
        montoTotalTexto = new javax.swing.JTextField();
        jp2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        tab6 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        tab7 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jp7 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaAsientos = new javax.swing.JTable();
        jLabel10 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        agregarAsiento = new javax.swing.JLabel();
        editarAsiento = new javax.swing.JLabel();
        activarEvento = new javax.swing.JLabel();
        inactivarEvento = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        fechaTexto2 = new javax.swing.JTextField();
        jLabel37 = new javax.swing.JLabel();
        horaTexto2 = new javax.swing.JTextField();
        jp6 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaEventos = new javax.swing.JTable();
        jLabel11 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        fechaTexto3 = new javax.swing.JTextField();
        horaTexto3 = new javax.swing.JTextField();

        jMenu3.setText("File");
        jMenuBar2.add(jMenu3);

        jMenu4.setText("Edit");
        jMenuBar2.add(jMenu4);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(51, 51, 255));

        tab1.setBackground(new java.awt.Color(204, 204, 255));
        tab1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tab1MouseClicked(evt);
            }
        });

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setFont(new java.awt.Font("Segoe UI", 3, 24)); // NOI18N
        jLabel2.setText(" Usuarios");

        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/icono Usuarios.png"))); // NOI18N

        javax.swing.GroupLayout tab1Layout = new javax.swing.GroupLayout(tab1);
        tab1.setLayout(tab1Layout);
        tab1Layout.setHorizontalGroup(
            tab1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab1Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        tab1Layout.setVerticalGroup(
            tab1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab1Layout.createSequentialGroup()
                .addGroup(tab1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tab1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel19))
                    .addGroup(tab1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel2)))
                .addContainerGap(10, Short.MAX_VALUE))
        );

        tab2.setBackground(new java.awt.Color(204, 204, 255));
        tab2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tab2MouseClicked(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 3, 24)); // NOI18N
        jLabel3.setText(" Eventos");

        jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos Eventos.png"))); // NOI18N
        jLabel20.setText("jLabel20");

        javax.swing.GroupLayout tab2Layout = new javax.swing.GroupLayout(tab2);
        tab2.setLayout(tab2Layout);
        tab2Layout.setHorizontalGroup(
            tab2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47))
        );
        tab2Layout.setVerticalGroup(
            tab2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab2Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(tab2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel20))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        tab3.setBackground(new java.awt.Color(204, 204, 255));
        tab3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tab3MouseClicked(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 3, 24)); // NOI18N
        jLabel4.setText("Ventas");

        jLabel22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/cajas Iconos.png"))); // NOI18N
        jLabel22.setText("jLabel22");

        javax.swing.GroupLayout tab3Layout = new javax.swing.GroupLayout(tab3);
        tab3.setLayout(tab3Layout);
        tab3Layout.setHorizontalGroup(
            tab3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab3Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(jLabel4)
                .addGap(26, 26, 26)
                .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        tab3Layout.setVerticalGroup(
            tab3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab3Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(tab3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel22))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tab4.setBackground(new java.awt.Color(204, 204, 255));
        tab4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tab4MouseClicked(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI", 3, 24)); // NOI18N
        jLabel5.setText("Cajas");

        jLabel21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/punto-de-venta.png"))); // NOI18N
        jLabel21.setText("jLabel21");

        javax.swing.GroupLayout tab4Layout = new javax.swing.GroupLayout(tab4);
        tab4.setLayout(tab4Layout);
        tab4Layout.setHorizontalGroup(
            tab4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab4Layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        tab4Layout.setVerticalGroup(
            tab4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab4Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(tab4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel21))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jLabel1.setFont(new java.awt.Font("Segoe UI", 3, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Multifiestas");

        jSeparator1.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tab1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(tab2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(tab3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(tab4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator1))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43)
                .addComponent(tab1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(tab2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(tab3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(tab4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new javax.swing.OverlayLayout(jPanel2));

        jp1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 3, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 102));
        jLabel6.setText("Gestión Usuarios");

        jSeparator5.setForeground(new java.awt.Color(0, 0, 102));

        tablaUsuarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane3.setViewportView(tablaUsuarios);

        jLabel33.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Agregar Usuario Icono.png"))); // NOI18N
        jLabel33.setText("jLabel33");
        jLabel33.setToolTipText("Agregar Usuario");
        jLabel33.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel33MouseClicked(evt);
            }
        });

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Editar Usuario Icono.jpg"))); // NOI18N
        jLabel12.setText("jLabel12");
        jLabel12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel12MouseClicked(evt);
            }
        });

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Activar Usuario Icono.jpg"))); // NOI18N
        jLabel13.setText("jLabel13");
        jLabel13.setToolTipText("Activar Usuario");
        jLabel13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel13MouseClicked(evt);
            }
        });

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Inactivar Usuario Icono.jpg"))); // NOI18N
        jLabel14.setText("jLabel14");
        jLabel14.setToolTipText("Inactivar Usuario Icono");
        jLabel14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel14MouseClicked(evt);
            }
        });

        jLabel34.setText("Fecha");

        fechaTexto1.setEditable(false);
        fechaTexto1.setBackground(new java.awt.Color(255, 255, 255));
        fechaTexto1.setBorder(null);

        jLabel35.setText("Hora");

        horaTexto1.setEditable(false);
        horaTexto1.setBackground(new java.awt.Color(255, 255, 255));
        horaTexto1.setBorder(null);

        javax.swing.GroupLayout jp1Layout = new javax.swing.GroupLayout(jp1);
        jp1.setLayout(jp1Layout);
        jp1Layout.setHorizontalGroup(
            jp1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jp1Layout.createSequentialGroup()
                .addGroup(jp1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jp1Layout.createSequentialGroup()
                        .addGap(242, 242, 242)
                        .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(106, 106, 106)
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(117, 117, 117)
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(123, 123, 123)
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jp1Layout.createSequentialGroup()
                        .addGap(401, 401, 401)
                        .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jp1Layout.createSequentialGroup()
                        .addGap(85, 85, 85)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 940, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jp1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fechaTexto1, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(horaTexto1, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(128, 128, 128)
                        .addComponent(jLabel6)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jp1Layout.setVerticalGroup(
            jp1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jp1Layout.createSequentialGroup()
                .addGroup(jp1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jp1Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jLabel6))
                    .addGroup(jp1Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(jp1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(fechaTexto1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel34)
                            .addComponent(jLabel35)
                            .addComponent(horaTexto1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 483, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                .addGroup(jp1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel33)
                    .addComponent(jLabel12)
                    .addComponent(jLabel13)
                    .addComponent(jLabel14))
                .addContainerGap(56, Short.MAX_VALUE))
        );

        jPanel2.add(jp1);

        jp3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(0, 0, 102));
        jLabel17.setText("Gestión Ventas");

        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel18.setText("Usuario");

        jLabel23.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel23.setText("Asientos");

        tablaAsientosVentas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaAsientosVentas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaAsientosVentasMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tablaAsientosVentas);
        if (tablaAsientosVentas.getColumnModel().getColumnCount() > 0) {
            tablaAsientosVentas.getColumnModel().getColumn(0).setResizable(false);
            tablaAsientosVentas.getColumnModel().getColumn(1).setResizable(false);
            tablaAsientosVentas.getColumnModel().getColumn(2).setResizable(false);
            tablaAsientosVentas.getColumnModel().getColumn(3).setResizable(false);
        }

        jLabel24.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel24.setText("Eventos");

        tablaEventosVentas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaEventosVentas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaEventosVentasMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tablaEventosVentas);
        if (tablaEventosVentas.getColumnModel().getColumnCount() > 0) {
            tablaEventosVentas.getColumnModel().getColumn(0).setResizable(false);
            tablaEventosVentas.getColumnModel().getColumn(1).setResizable(false);
            tablaEventosVentas.getColumnModel().getColumn(2).setResizable(false);
            tablaEventosVentas.getColumnModel().getColumn(3).setResizable(false);
        }

        jLabel26.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel26.setText("Monto Pagar");

        jPanel4.setBackground(new java.awt.Color(204, 204, 255));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 77, Short.MAX_VALUE)
        );

        jLabel15.setText("Fecha:");

        txtFecha.setEditable(false);
        txtFecha.setBackground(new java.awt.Color(255, 255, 255));
        txtFecha.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtFecha.setBorder(null);
        txtFecha.setFocusable(false);

        jLabel16.setText("Hora:");

        txtHora.setEditable(false);
        txtHora.setBackground(new java.awt.Color(255, 255, 255));
        txtHora.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtHora.setBorder(null);
        txtHora.setFocusable(false);

        jSeparator6.setForeground(new java.awt.Color(0, 0, 102));

        jLabel40.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Listar Compras Icono.jpg"))); // NOI18N
        jLabel40.setToolTipText("Gestionar Ventas");
        jLabel40.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel40MouseClicked(evt);
            }
        });

        jLabel41.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Realizar Compra Icono.jpg"))); // NOI18N
        jLabel41.setToolTipText("Realizar Compra");
        jLabel41.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel41MouseClicked(evt);
            }
        });

        jLabel42.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/ListarCompras.jpg"))); // NOI18N
        jLabel42.setText("jLabel42");
        jLabel42.setToolTipText("Listado General de Ventas");
        jLabel42.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel42MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jp3Layout = new javax.swing.GroupLayout(jp3);
        jp3.setLayout(jp3Layout);
        jp3Layout.setHorizontalGroup(
            jp3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jp3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jp3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jp3Layout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtHora, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jp3Layout.createSequentialGroup()
                        .addGroup(jp3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 377, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtMonto, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jp3Layout.createSequentialGroup()
                                .addGap(144, 144, 144)
                                .addComponent(jLabel23)))
                        .addGroup(jp3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jp3Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jp3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 418, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jp3Layout.createSequentialGroup()
                                        .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(31, 31, 31)
                                        .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(42, 42, 42)
                                        .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(64, 64, 64))))
                            .addGroup(jp3Layout.createSequentialGroup()
                                .addGap(184, 184, 184)
                                .addComponent(jLabel24)))))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jp3Layout.createSequentialGroup()
                .addGroup(jp3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jp3Layout.createSequentialGroup()
                        .addGap(352, 352, 352)
                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jp3Layout.createSequentialGroup()
                        .addGap(301, 301, 301)
                        .addGroup(jp3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtUsu, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17)
                            .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jp3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel26)))
                .addContainerGap(602, Short.MAX_VALUE))
        );
        jp3Layout.setVerticalGroup(
            jp3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jp3Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jp3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16)
                    .addComponent(txtHora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addComponent(jLabel17)
                .addGap(2, 2, 2)
                .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jp3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jp3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtUsu, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addGroup(jp3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel24)
                            .addComponent(jLabel23))
                        .addGap(18, 18, 18)
                        .addGroup(jp3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jp3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jp3Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jLabel26)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtMonto, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jp3Layout.createSequentialGroup()
                                .addGap(41, 41, 41)
                                .addGroup(jp3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel42)
                                    .addComponent(jLabel40))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jp3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel41)
                        .addGap(175, 175, 175))))
        );

        jPanel2.add(jp3);

        jp4.setBackground(new java.awt.Color(255, 255, 255));

        jLabel27.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(0, 0, 153));
        jLabel27.setText("Cajas");

        jLabel28.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(0, 0, 255));
        jLabel28.setText("Ventas Registradas");

        tablaVentas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane6.setViewportView(tablaVentas);

        jSeparator7.setForeground(new java.awt.Color(0, 51, 153));

        jLabel43.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel43.setText("Ingresos Diarios");

        montoTotalTexto.setEditable(false);
        montoTotalTexto.setBackground(new java.awt.Color(255, 255, 255));
        montoTotalTexto.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        javax.swing.GroupLayout jp4Layout = new javax.swing.GroupLayout(jp4);
        jp4.setLayout(jp4Layout);
        jp4Layout.setHorizontalGroup(
            jp4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jp4Layout.createSequentialGroup()
                .addGroup(jp4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jp4Layout.createSequentialGroup()
                        .addGap(401, 401, 401)
                        .addComponent(jLabel28))
                    .addGroup(jp4Layout.createSequentialGroup()
                        .addGap(449, 449, 449)
                        .addGroup(jp4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jp4Layout.createSequentialGroup()
                        .addGap(92, 92, 92)
                        .addGroup(jp4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jp4Layout.createSequentialGroup()
                                .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(montoTotalTexto, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 887, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(92, Short.MAX_VALUE))
        );
        jp4Layout.setVerticalGroup(
            jp4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jp4Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel27)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel28)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addGroup(jp4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel43)
                    .addComponent(montoTotalTexto, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(102, Short.MAX_VALUE))
        );

        jPanel2.add(jp4);

        jp2.setBackground(new java.awt.Color(255, 255, 255));

        jPanel3.setBackground(new java.awt.Color(0, 0, 255));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 3, 36)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Eventos");

        tab6.setBackground(new java.awt.Color(204, 204, 255));
        tab6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tab6MouseClicked(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Segoe UI", 3, 24)); // NOI18N
        jLabel8.setText("Gestión Eventos");

        javax.swing.GroupLayout tab6Layout = new javax.swing.GroupLayout(tab6);
        tab6.setLayout(tab6Layout);
        tab6Layout.setHorizontalGroup(
            tab6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tab6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel8)
                .addGap(19, 19, 19))
        );
        tab6Layout.setVerticalGroup(
            tab6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab6Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel8)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        tab7.setBackground(new java.awt.Color(204, 204, 255));
        tab7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tab7MouseClicked(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Segoe UI", 3, 24)); // NOI18N
        jLabel9.setText("Gestión Asientos");

        javax.swing.GroupLayout tab7Layout = new javax.swing.GroupLayout(tab7);
        tab7.setLayout(tab7Layout);
        tab7Layout.setHorizontalGroup(
            tab7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab7Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel9)
                .addContainerGap(19, Short.MAX_VALUE))
        );
        tab7Layout.setVerticalGroup(
            tab7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tab7Layout.createSequentialGroup()
                .addContainerGap(23, Short.MAX_VALUE)
                .addComponent(jLabel9)
                .addGap(20, 20, 20))
        );

        jSeparator2.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tab6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(tab7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jSeparator2)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel7)
                .addGap(39, 39, 39))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(103, 103, 103)
                .addComponent(tab6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(69, 69, 69)
                .addComponent(tab7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jp7.setBackground(new java.awt.Color(255, 255, 255));

        tablaAsientos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tablaAsientos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaAsientosMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tablaAsientos);

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 0, 153));
        jLabel10.setText("Módulo Asientos");

        jSeparator4.setForeground(new java.awt.Color(0, 0, 102));

        agregarAsiento.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Agregar Evento Icono.png"))); // NOI18N
        agregarAsiento.setText("jLabel33");
        agregarAsiento.setToolTipText("Agregar Asiento");
        agregarAsiento.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                agregarAsientoMouseClicked(evt);
            }
        });

        editarAsiento.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Editar Evento Icono.png"))); // NOI18N
        editarAsiento.setText("jLabel34");
        editarAsiento.setToolTipText("Editar Asiento");
        editarAsiento.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                editarAsientoMouseClicked(evt);
            }
        });

        activarEvento.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/activar evento.png"))); // NOI18N
        activarEvento.setText("jLabel35");
        activarEvento.setToolTipText("Activar Evento");
        activarEvento.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                activarEventoMouseClicked(evt);
            }
        });

        inactivarEvento.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Inactivar Evento Icono.png"))); // NOI18N
        inactivarEvento.setText("jLabel36");
        inactivarEvento.setToolTipText("Inactivar Evento");
        inactivarEvento.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                inactivarEventoMouseClicked(evt);
            }
        });

        jLabel36.setText("Fecha");

        fechaTexto2.setBorder(null);

        jLabel37.setText("Hora");

        horaTexto2.setBorder(null);

        javax.swing.GroupLayout jp7Layout = new javax.swing.GroupLayout(jp7);
        jp7.setLayout(jp7Layout);
        jp7Layout.setHorizontalGroup(
            jp7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jp7Layout.createSequentialGroup()
                .addGroup(jp7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jp7Layout.createSequentialGroup()
                        .addGap(140, 140, 140)
                        .addComponent(agregarAsiento, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(73, 73, 73)
                        .addComponent(editarAsiento, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(75, 75, 75)
                        .addComponent(activarEvento, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(88, 88, 88)
                        .addComponent(inactivarEvento, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jp7Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jp7Layout.createSequentialGroup()
                .addContainerGap(59, Short.MAX_VALUE)
                .addGroup(jp7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jp7Layout.createSequentialGroup()
                        .addComponent(fechaTexto2, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(horaTexto2, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(508, 508, 508))
                    .addGroup(jp7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jp7Layout.createSequentialGroup()
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 685, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(32, 32, 32))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jp7Layout.createSequentialGroup()
                            .addComponent(jLabel10)
                            .addGap(276, 276, 276))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jp7Layout.createSequentialGroup()
                            .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(260, 260, 260)))))
        );
        jp7Layout.setVerticalGroup(
            jp7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jp7Layout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addGroup(jp7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel36)
                    .addComponent(fechaTexto2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel37)
                    .addComponent(horaTexto2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 488, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jp7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(agregarAsiento)
                    .addComponent(editarAsiento)
                    .addComponent(activarEvento)
                    .addComponent(inactivarEvento))
                .addGap(36, 36, 36))
        );

        jp6.setBackground(new java.awt.Color(255, 255, 255));

        tablaEventos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tablaEventos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaEventosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablaEventos);

        jLabel25.setBackground(new java.awt.Color(0, 0, 153));
        jLabel25.setFont(new java.awt.Font("Segoe UI", 3, 24)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(0, 0, 204));
        jLabel25.setText("Módulo Eventos");

        jLabel29.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/activar evento.png"))); // NOI18N
        jLabel29.setToolTipText("Activar Evento");
        jLabel29.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel29MouseClicked(evt);
            }
        });

        jLabel30.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Agregar Evento Icono.png"))); // NOI18N
        jLabel30.setText("jLabel30");
        jLabel30.setToolTipText("Agregar Evento Icono");
        jLabel30.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel30MouseClicked(evt);
            }
        });

        jLabel31.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Editar Evento Icono.png"))); // NOI18N
        jLabel31.setText("jLabel31");
        jLabel31.setToolTipText("Editar Evento");
        jLabel31.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel31MouseClicked(evt);
            }
        });

        jLabel32.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Inactivar Evento Icono.png"))); // NOI18N
        jLabel32.setToolTipText("Inactivar Evento");
        jLabel32.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel32MouseClicked(evt);
            }
        });

        jSeparator3.setForeground(new java.awt.Color(0, 0, 153));

        jLabel38.setText("Fecha");

        jLabel39.setText("Hora");

        fechaTexto3.setEditable(false);
        fechaTexto3.setBackground(new java.awt.Color(255, 255, 255));
        fechaTexto3.setBorder(null);

        horaTexto3.setEditable(false);
        horaTexto3.setBackground(new java.awt.Color(255, 255, 255));
        horaTexto3.setBorder(null);

        javax.swing.GroupLayout jp6Layout = new javax.swing.GroupLayout(jp6);
        jp6.setLayout(jp6Layout);
        jp6Layout.setHorizontalGroup(
            jp6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jp6Layout.createSequentialGroup()
                .addGroup(jp6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jp6Layout.createSequentialGroup()
                        .addGroup(jp6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jp6Layout.createSequentialGroup()
                                .addGap(149, 149, 149)
                                .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(73, 73, 73)
                                .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(89, 89, 89)
                                .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(91, 91, 91)
                                .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jp6Layout.createSequentialGroup()
                                .addGap(54, 54, 54)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 715, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jp6Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(fechaTexto3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(horaTexto3, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(58, 58, 58)
                        .addComponent(jLabel25)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jp6Layout.createSequentialGroup()
                .addGap(295, 295, 295)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jp6Layout.setVerticalGroup(
            jp6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jp6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel11)
                .addGap(352, 352, 352))
            .addGroup(jp6Layout.createSequentialGroup()
                .addGroup(jp6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jp6Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(jLabel25))
                    .addGroup(jp6Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(jp6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel38)
                            .addComponent(jLabel39)
                            .addComponent(fechaTexto3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(horaTexto3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 454, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jp6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jp6Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addGroup(jp6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel32)
                            .addGroup(jp6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel30)
                                .addComponent(jLabel31))))
                    .addGroup(jp6Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(jLabel29)))
                .addGap(64, 64, 64))
        );

        javax.swing.GroupLayout jp2Layout = new javax.swing.GroupLayout(jp2);
        jp2.setLayout(jp2Layout);
        jp2Layout.setHorizontalGroup(
            jp2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jp2Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jp6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jp2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jp2Layout.createSequentialGroup()
                    .addGap(221, 221, 221)
                    .addComponent(jp7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(74, Short.MAX_VALUE)))
        );
        jp2Layout.setVerticalGroup(
            jp2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jp6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jp2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jp2Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jp7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        jPanel2.add(jp2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tab1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab1MouseClicked
        // TODO add your handling code here:
        jp1.setVisible(true);
        jp2.setVisible(false);
        jp3.setVisible(false); 
        jp4.setVisible(false);
        tab1.setBackground(Color.white);
        tab2.setBackground(new Color(204, 204, 255));
        tab3.setBackground(new Color(204, 204, 255));
        tab4.setBackground(new Color(204, 204, 255));
    }//GEN-LAST:event_tab1MouseClicked

    private void tab2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab2MouseClicked
        // TODO add your handling code here:
        jp2.setVisible(true);
        jp1.setVisible(false);
        jp3.setVisible(false);
        jp4.setVisible(false);
        tab2.setBackground(Color.white);
        tab1.setBackground(new Color(204, 204, 255));
        tab3.setBackground(new Color(204, 204, 255));
        tab4.setBackground(new Color(204, 204, 255));
//        tab6.setBackground(Color.white);
        jp6.setVisible(false);
        jp7.setVisible(false);
//        JOptionPane.showMessageDialog(null,"Bienvenido al Módulo Eventos");
//        tituloEvento.setVisible(false);
//        tituloNombreEvento.setVisible(false);
//        tituloFechaEvento.setVisible(false);
//        lugarTituloEvento.setVisible(false);
//        ciudadTituloEvento.setVisible(false);
//        direccionTituloEvento.setVisible(false);
//        estadoTituloEvento.setVisible(false);
//        asignadoTituloEvento.setVisible(false);    
    }//GEN-LAST:event_tab2MouseClicked
    
    private void tab3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab3MouseClicked
        // TODO add your handling code here:
        jp3.setVisible(true);
        jp1.setVisible(false);
        jp2.setVisible(false);
        jp4.setVisible(false);
        tab3.setBackground(Color.white);
        tab1.setBackground(new Color(204, 204, 255));
        tab2.setBackground(new Color(204, 204, 255));
        tab4.setBackground(new Color(204, 204, 255));
        //-------------------------------------------------
        listadoEventos();
        listarAsientos();
    }//GEN-LAST:event_tab3MouseClicked
       
    private void tab4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab4MouseClicked
        // TODO add your handling code here:
        jp4.setVisible(true);
        jp1.setVisible(false);
        jp2.setVisible(false); 
        jp3.setVisible(false);
        tab4.setBackground(Color.white); 
        tab1.setBackground(new Color(204, 204, 255));
        tab2.setBackground(new Color(204, 204, 255));
        tab3.setBackground(new Color(204, 204, 255));
//        JOptionPane.showMessageDialog(null,"Bienvenido al Módulo Cajas");
    }//GEN-LAST:event_tab4MouseClicked
     
    private void tab6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab6MouseClicked
        // TODO add your handling code here:
//        JOptionPane.showMessageDialog(null,"Módulo Eventos");
        jp6.setVisible(true);
        jp7.setVisible(false);
        tab6.setBackground(Color.white);
        tab7.setBackground(new Color(204, 204, 255));
//        nombreEventoTexto.setVisible(true);
//        fechaTexto.setVisible(true);
//        LugarComboBox.setVisible(true);
//        ciudadTexto.setVisible(true);
//        direccionTexto.setVisible(true);
//        estadoTexto.setVisible(true);
//        eventoAsignadoComboBox.setVisible(true);    
    }//GEN-LAST:event_tab6MouseClicked

    private void tab7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab7MouseClicked
        // TODO add your handling code here:
//        JOptionPane.showMessageDialog(null, "Módulo Asientos");
        jp7.setVisible(true);
        jp6.setVisible(false);
        tab7.setBackground(Color.white);
        tab6.setBackground(new Color(204, 204, 255));
//        eventoAsignadoComboBox.setVisible(true);
//        estadoTexto.setVisible(true);
    }//GEN-LAST:event_tab7MouseClicked

    private void tablaAsientosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaAsientosMouseClicked
        // TODO add your handling code here:
        listarAsientos();
    }//GEN-LAST:event_tablaAsientosMouseClicked

    private void tablaAsientosVentasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaAsientosVentasMouseClicked
    //listarAsientosVenta();
        tAseleccionado = true;
        int fila = tablaAsientosVentas.rowAtPoint(evt.getPoint()); 
        auxIdAsiento = tablaAsientosVentas.getValueAt(fila, 2).toString();
        auxMontoTotal += Double.parseDouble(tablaAsientosVentas.getValueAt(fila, 3).toString());//revisar esto, si se selecciona un asiento y despues otro me inmagino que se suman los 2
        txtMonto.setText(Double.toString(auxMontoTotal));       
    }//GEN-LAST:event_tablaAsientosVentasMouseClicked

    private void tablaEventosVentasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaEventosVentasMouseClicked
        //listadoEventosVentas();
        tEseleccionado = true;
        int fila = tablaEventosVentas.rowAtPoint(evt.getPoint()); 
        auxIdEvento = tablaEventosVentas.getValueAt(fila, 1).toString();
    }//GEN-LAST:event_tablaEventosVentasMouseClicked
    
    public void agregarEventoForm(){
        this.setVisible(false);
        AgregarEventoForm f1 = new AgregarEventoForm();
        f1.setLocationRelativeTo(this);
        f1.setVisible(true);
    }
    private void tablaEventosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaEventosMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tablaEventosMouseClicked

    private void jLabel30MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel30MouseClicked
        // TODO add your handling code here:
        agregarEventoForm();
    }//GEN-LAST:event_jLabel30MouseClicked

    private void jLabel31MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel31MouseClicked

        this.dispose();
        ActualizarEventoForm f1 = new ActualizarEventoForm();
        f1.setVisible(true);
        f1.setLocationRelativeTo(this);
    }//GEN-LAST:event_jLabel31MouseClicked

    private void jLabel29MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel29MouseClicked
        // TODO add your handling code here:
        this.dispose();
        ActivarEvento f1 = new ActivarEvento();
        f1.setVisible(true);
        f1.setLocationRelativeTo(this);
    }//GEN-LAST:event_jLabel29MouseClicked

    private void jLabel32MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel32MouseClicked
        // TODO add your handling code here:
        this.dispose();
        InactivarEvento f1 = new InactivarEvento();
        f1.setVisible(true);
        f1.setLocationRelativeTo(this);        
    }//GEN-LAST:event_jLabel32MouseClicked

    private void agregarAsientoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_agregarAsientoMouseClicked
        // TODO add your handling code here:
        this.dispose();
        AgregarAsientoForm f1 = new AgregarAsientoForm();
        f1.setVisible(true);
        f1.setLocationRelativeTo(this);
    }//GEN-LAST:event_agregarAsientoMouseClicked

    private void editarAsientoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editarAsientoMouseClicked
        // TODO add your handling code here:
        this.dispose();
        ActualizarAsientoForm f1 = new ActualizarAsientoForm();
        f1.setVisible(true);
        f1.setLocationRelativeTo(this);
    }//GEN-LAST:event_editarAsientoMouseClicked

    private void activarEventoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_activarEventoMouseClicked
        // TODO add your handling code here:
        this.dispose();
        ActivarAsientoForm f1 = new ActivarAsientoForm();
        f1.setVisible(true);
        f1.setLocationRelativeTo(this);
    }//GEN-LAST:event_activarEventoMouseClicked

    private void inactivarEventoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_inactivarEventoMouseClicked
        // TODO add your handling code here:
        this.dispose();
        InactivarAsiento f1 = new InactivarAsiento();
        f1.setVisible(true);
        f1.setLocationRelativeTo(this);        
    }//GEN-LAST:event_inactivarEventoMouseClicked

    private void jLabel33MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel33MouseClicked
        // TODO add your handling code here:
        this.dispose();
        AgregarUsuarioForm f1 = new AgregarUsuarioForm();
        f1.setVisible(true);
        f1.setLocationRelativeTo(this);
    }//GEN-LAST:event_jLabel33MouseClicked

    private void jLabel12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel12MouseClicked
        // TODO add your handling code here:
        this.dispose();
        ActualizarUsuarioForm f1 = new ActualizarUsuarioForm();
        f1.setVisible(true);
        f1.setLocationRelativeTo(this);
    }//GEN-LAST:event_jLabel12MouseClicked

    private void jLabel13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel13MouseClicked

        this.dispose();
        ActivarUsuarioForm f1 = new ActivarUsuarioForm();
        f1.setVisible(true);
        f1.setLocationRelativeTo(this);
    }//GEN-LAST:event_jLabel13MouseClicked

    private void jLabel14MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel14MouseClicked
        // TODO add your handling code here:
        this.dispose();
        InactivarUsuarioForm f1 = new InactivarUsuarioForm();
        f1.setVisible(true);
        f1.setLocationRelativeTo(this);
    }//GEN-LAST:event_jLabel14MouseClicked

    private void jLabel40MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel40MouseClicked
        // TODO add your handling code here:
        menu2Ventas m1 = new menu2Ventas(ventas);
        m1.setVisible(true);
    }//GEN-LAST:event_jLabel40MouseClicked

    private void jLabel41MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel41MouseClicked
        // TODO add your handling code here:
        realizarCompra();
    }//GEN-LAST:event_jLabel41MouseClicked

    private void jLabel42MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel42MouseClicked
        // TODO add your handling code here:
        StringBuilder listadoVentas = new StringBuilder("");
        for (Venta venta : ventas) {
            if (venta != null){
                listadoVentas.append("Venta ").append(" Id Venta: ").append(venta.getIdVenta()).append("Fecha Venta: ").append(venta.getFechaVenta()).append(", Hora Venta: ").append(venta.getHoraVenta()).append(", Nombre de Usuario: ").append(venta.getUsuario()).append(", Evento: ").append(venta.getEvento()).append(", Asiento: ").append(venta.getAsiento()).append(", Monto: ").append(venta.getMonto()).append("\n");
            }
        }
        metodos.msg("" + listadoVentas, "Ventas Registradas", 1);        
    }//GEN-LAST:event_jLabel42MouseClicked
       
    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Multifiestas().setVisible(true); 
            }
        });
        
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel activarEvento;
    private javax.swing.JLabel agregarAsiento;
    private javax.swing.JLabel editarAsiento;
    private javax.swing.JTextField fechaTexto1;
    private javax.swing.JTextField fechaTexto2;
    private javax.swing.JTextField fechaTexto3;
    private javax.swing.JTextField horaTexto1;
    private javax.swing.JTextField horaTexto2;
    private javax.swing.JTextField horaTexto3;
    private javax.swing.JLabel inactivarEvento;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JPanel jp1;
    private javax.swing.JPanel jp2;
    private javax.swing.JPanel jp3;
    private javax.swing.JPanel jp4;
    private javax.swing.JPanel jp6;
    private javax.swing.JPanel jp7;
    private javax.swing.JTextField montoTotalTexto;
    private javax.swing.JPanel tab1;
    private javax.swing.JPanel tab2;
    private javax.swing.JPanel tab3;
    private javax.swing.JPanel tab4;
    private javax.swing.JPanel tab6;
    private javax.swing.JPanel tab7;
    private javax.swing.JTable tablaAsientos;
    private javax.swing.JTable tablaAsientosVentas;
    private javax.swing.JTable tablaEventos;
    private javax.swing.JTable tablaEventosVentas;
    private javax.swing.JTable tablaUsuarios;
    private javax.swing.JTable tablaVentas;
    private javax.swing.JTextField txtFecha;
    private javax.swing.JTextField txtHora;
    private javax.swing.JTextField txtMonto;
    private javax.swing.JTextField txtUsu;
    // End of variables declaration//GEN-END:variables
}
