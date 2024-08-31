package presentacion;
import datos.*;
import domain.*;

import static datos.Conexion.getConnection;
import java.sql.*;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import ufide.controller.MetodosController;

public class menu2Ventas extends javax.swing.JFrame {

    VentaDAO vDAO = new VentaDAO();
    List<Venta> ventas;
    
    MetodosController metodos = new MetodosController();
    public menu2Ventas(List<Venta> ventas) {
        this.ventas = ventas;
        initComponents();
        setLocationRelativeTo(null);
        setTitle("Ventas");
        listadoVentas();
        setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
        
    }

    public void listadoVentas() {
        try {
            DefaultTableModel model = (DefaultTableModel) tablaVentas.getModel();
            model.setNumRows(0);
            //------------------------
            PreparedStatement ps = null;
            Connection conn = null;
            ResultSet rs = null;
            conn = getConnection();
            String SQL_SELECT = "SELECT id_venta, fecha_venta, hora_venta, usuario, evento, asiento, monto from venta";
            ps = conn.prepareStatement(SQL_SELECT);
            rs = ps.executeQuery();
            ResultSetMetaData rsmt = rs.getMetaData();
            int cantidadColumnas = rsmt.getColumnCount();
            
            while (rs.next()) {
                Object[] filas = new Object[cantidadColumnas];
                for (int i = 0; i < filas.length; i++) {
                    filas[i] = rs.getObject(i + 1);
                }
                model.addRow(filas);
            }
        } catch (SQLException e) {
            System.out.println("Ocurrió un error al listar ventas en la tabla");
            
        } 
    }
//------------------------------------------------------------------------------
    public void limpiar(){
        txtAuxUsuario.setText("");
        txtAuxEvento.setText("");
        txtAuxAsiento.setText("");
        txtIDventa.setText("");
    }
    
     
    public void eliminarVenta(){      
        int consultaEliminar = metodos.SIoNo("¿Desea eliminar esta venta?", "Mensaje de Consulta");
        if (consultaEliminar == JOptionPane.YES_OPTION){
            if (!txtIDventa.getText().isEmpty()) { 
                if (!ventas.isEmpty()){//Validar que el array de ventas no este vacio
                    Venta venta = buscarVenta(); 
                    if (venta != null) {
                        vDAO.eliminarVenta(venta);
                        ventas.remove(venta); 
                        listadoVentas();
                        JOptionPane.showMessageDialog(null, "Venta eliminada exitosamente.");
                    }else{
                    JOptionPane.showMessageDialog(null, "Venta null");
                    } 
                } else{
                JOptionPane.showMessageDialog(null, "No hay ventas en el ArrayList");
                }
            }else{
                JOptionPane.showMessageDialog(null, "Venta no selecionada.");
            }
        }
        else if (consultaEliminar == JOptionPane.NO_OPTION){
            
        }
    }
   
    
    public Venta buscarVenta(){
        for (int i = 0; i < ventas.size(); i++) { 
            if (Integer.parseInt(txtIDventa.getText()) == ventas.get(i).getIdVenta()) {
            return ventas.get(i); 
            } 
        }     
        return null;
    }
    public void actualizarVentaArray(int idVenta, String idEvento, String idAsiento, String usuario){
        if (!txtIDventa.getText().isEmpty()) {
            for (Venta venta: ventas) {
                if (idVenta == venta.getIdVenta()) {
                    venta.setEvento(idEvento);
                    venta.setAsiento(idAsiento);
                    venta.setUsuario(usuario);
                }
            }         
        }else{
            JOptionPane.showMessageDialog(null, "Venta no seleccionada");
        }
    }
     
    public void modificar(){       
        //RECALCAR que este metod primero hace las busqueda en los ArrayList y por ultimo realiza la modificacion en la BSD
        try{
            if (!txtAuxEvento.getText().isBlank() || txtAuxUsuario.getText().isEmpty() || txtAuxAsiento.getText().isEmpty()) {
                Usuario auxUsu = buscarUsuarioV();
                if (auxUsu != null) {//Paso 1- Buscar ususario ingresado
                    Evento auxEvento = buscarEvento();
                    if (auxEvento!=null) {//Paso 2- Buscar Evento ingresado(se buscar por ID)
                       Asiento auxAsiento = buscarAsiento();
                        if (auxAsiento!=null) {//Paso 3- Buscar Asiento ingresado(se buscar por ID)
                            if (!ventas.isEmpty()) {//Paso 4- Verificar que el array de ventas no este vacio
                                Venta venta = buscarVenta();                                
                                if (venta != null) {//Paso 5- Buscar la venta
                                   
                                    int idVenta = Integer.parseInt(txtIDventa.getText());
                                    String usuario = auxUsu.getNombreUsuario();
                                    String idEvento = txtAuxEvento.getText();
                                    String idAsiento = txtAuxAsiento.getText();
                                    
                                    actualizarVentaArray(idVenta,idEvento,idAsiento,usuario);//actualizar array
                                    vDAO.actualizarVenta(venta);//actualizar BSD
                                    
                                }else{
                                JOptionPane.showMessageDialog(null, "Venta NULL");
                                }     
                            }else{
                            JOptionPane.showMessageDialog(null, "Array de ventas vacio.");
                            }   
                        }else{ //Fin if Asientos
                        JOptionPane.showMessageDialog(null, "Asiento no encontrado.");
                        }    
                    }else{ //Fin if Eventos
                    JOptionPane.showMessageDialog(null, "Evento no encontrado.");
                    }  
                }else{ //Fin if Usuarios
                JOptionPane.showMessageDialog(null, "Usuario no encontrado.");
                }
            }else{
                JOptionPane.showMessageDialog(null, "Campo vacio.");
            }
        }catch(Exception a){
            JOptionPane.showMessageDialog(null, "Error: \n"+a);
        }
    }
    
    public void modificarVenta(){
        boolean usuarioEncontrado = false;
        boolean eventoEncontrado = false;
        boolean asientoEncontrado = false;
        boolean ventaEncontrado = false;
        boolean arrayVacio = false; 
        if (txtAuxEvento.getText().equals("") || txtAuxUsuario.getText().equals("") || txtAuxAsiento.getText().equals("") || txtIDventa.getText().equals("")){
            metodos.msg("Hay campos vacíos", "Mensaje de Informacióo", 1);
        }else{
            var nombreUsuario = txtAuxUsuario.getText();
            for (Usuario usuario : Main.usuarios) {
                if (usuario != null && usuario.getNombreUsuario().equalsIgnoreCase(nombreUsuario)){
                    usuarioEncontrado = true;
                    var nombreEvento = txtAuxEvento.getText(); 
                    for (Evento evento : Main.eventos) {
                        if (evento != null && evento.getNombreEvento().equalsIgnoreCase(nombreEvento)){
                            eventoEncontrado = true;
                            var numeroAsiento = txtAuxAsiento.getText();
                            for (Asiento asiento : Main.asientos) {
                                if (asiento != null && asiento.getNumeroAsiento().equalsIgnoreCase(numeroAsiento)){
                                    asientoEncontrado = true;                
                                    if (!ventas.isEmpty()){
                                        Venta venta = buscarVenta();
                                        arrayVacio = true;
                                        if (venta != null){  
                                            ventaEncontrado = true;
                                             int idVenta = Integer.parseInt(txtIDventa.getText());
                                                String nombre = usuario.getNombreUsuario();
                                                String idEvento = txtAuxEvento.getText();
                                                String idAsiento = txtAuxAsiento.getText();
                                                evento.setAsignado("Vendido");
                                                asiento.setEstado("Ocupado");
                                                actualizarVentaArray(idVenta,idEvento,idAsiento,nombre);//actualizar array
                                                vDAO.actualizarVenta(venta);//actualizar BSD
                                                metodos.msg("Venta Actualizada", "Mensaje de Información",1);
                                        }
                                        if(!ventaEncontrado){
                                            metodos.msg("Venta no encontrada","Mensaje de Información", 1);
                                        }
                                    }
                                    if(!arrayVacio){
                                        metodos.msg("No hay registros en la tabla", "Mensaje de Información", 1);
                                    }      
                                }
                            }
                            if(!asientoEncontrado){
                                metodos.msg("Asiento no encontrado", "Mensaje de Información", 1);
                            }
                        }
                    }
                    if(!eventoEncontrado){
                        metodos.msg("Evento no encontrado", "Mensaje de Información", 1);
                    }
                }
            }
            if(!usuarioEncontrado){
                metodos.msg("Usuario no encontrado","Mensaje de Información", 1);
            }
        }
    }
    public Usuario buscarUsuarioV(){
        for (Usuario usuario : Main.usuarios) {
            if (usuario.getNombreUsuario().equals(txtAuxUsuario.getText())) {
                return usuario;
            }
        }
        return null;
    }
    public Evento buscarEvento(){       
        String nombreEvento = txtAuxEvento.getText();
        for (Evento evento : Main.eventos) {
            if (evento != null && evento.getNombreEvento().equalsIgnoreCase(nombreEvento)){
                if (evento.getDisponibilidad().equalsIgnoreCase("Activo")){
                    if (evento.getAsignado().equalsIgnoreCase("No Asignado")){
                        return evento; 
                    }
                    else if (evento.getAsignado().equalsIgnoreCase("Vendido")){
                        metodos.msg("No se puede vender un evento vendido", "Mensaje de Información", 1);
                    }
                } 
                else if (evento.getDisponibilidad().equalsIgnoreCase("Inactivo")){
                    metodos.msg("No se puede vender un evento Inactivo", "Mensaje de Información", 1);
                }
            }
        }
        return null;
    }
    public Asiento buscarAsiento(){       
        String numeroAsiento = txtAuxAsiento.getText();
        for (Asiento asiento : Main.asientos) {
            if (asiento != null && asiento.getNumeroAsiento().equalsIgnoreCase(numeroAsiento)){
                if (asiento.getDisponibilidad().equalsIgnoreCase("Activo")){
                    if (asiento.getEstado().equalsIgnoreCase("Libre")){
                        return asiento;
                    }
                    else if (asiento.getEstado().equalsIgnoreCase("Ocupado")){
                        metodos.msg("No se puede vender un asiento ocupado","Mensaje de Información", 1);
                    }
                }
                else if (asiento.getDisponibilidad().equalsIgnoreCase("Inactivo")){
                    metodos.msg("No se puede vender un asiento Inactivo","Mensaje de Información", 1);
                }
            }
            
        }
        return null; 
    }
    
    
    
//------------------------------------------------------------------------------ 
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jInternalFrame2 = new javax.swing.JInternalFrame();
        jTextField3 = new javax.swing.JTextField();
        Fondo = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaVentas = new javax.swing.JTable();
        Cabezal = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        txtIDventa = new javax.swing.JTextField();
        txtAuxUsuario = new javax.swing.JTextField();
        txtAuxEvento = new javax.swing.JTextField();
        txtAuxAsiento = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();

        jInternalFrame2.setVisible(true);

        javax.swing.GroupLayout jInternalFrame2Layout = new javax.swing.GroupLayout(jInternalFrame2.getContentPane());
        jInternalFrame2.getContentPane().setLayout(jInternalFrame2Layout);
        jInternalFrame2Layout.setHorizontalGroup(
            jInternalFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jInternalFrame2Layout.setVerticalGroup(
            jInternalFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jInternalFrame2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 468, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jInternalFrame2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 459, Short.MAX_VALUE))
        );

        jTextField3.setText("jTextField3");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        Fondo.setBackground(new java.awt.Color(255, 255, 255));
        Fondo.setFocusable(false);

        tablaVentas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Fecha", "Hora", "Usuario", "Evento", "Asiento", "Vendido"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaVentas.setGridColor(new java.awt.Color(255, 255, 255));
        tablaVentas.setSelectionForeground(new java.awt.Color(255, 255, 255));
        tablaVentas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaVentasMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tablaVentas);
        if (tablaVentas.getColumnModel().getColumnCount() > 0) {
            tablaVentas.getColumnModel().getColumn(0).setResizable(false);
            tablaVentas.getColumnModel().getColumn(1).setResizable(false);
            tablaVentas.getColumnModel().getColumn(2).setResizable(false);
            tablaVentas.getColumnModel().getColumn(3).setResizable(false);
            tablaVentas.getColumnModel().getColumn(4).setResizable(false);
            tablaVentas.getColumnModel().getColumn(5).setResizable(false);
            tablaVentas.getColumnModel().getColumn(6).setResizable(false);
        }

        Cabezal.setBackground(new java.awt.Color(204, 204, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("Ventas");

        javax.swing.GroupLayout CabezalLayout = new javax.swing.GroupLayout(Cabezal);
        Cabezal.setLayout(CabezalLayout);
        CabezalLayout.setHorizontalGroup(
            CabezalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CabezalLayout.createSequentialGroup()
                .addGap(312, 312, 312)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        CabezalLayout.setVerticalGroup(
            CabezalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, CabezalLayout.createSequentialGroup()
                .addContainerGap(19, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(14, 14, 14))
        );

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/eliminarVenta.png"))); // NOI18N
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/editarVenta.png"))); // NOI18N
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });

        jButton1.setText("Volver");
        jButton1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        txtIDventa.setEditable(false);
        txtIDventa.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtIDventa.setFocusable(false);

        txtAuxUsuario.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtAuxUsuario.setDisabledTextColor(new java.awt.Color(255, 255, 255));

        txtAuxEvento.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtAuxEvento.setDisabledTextColor(new java.awt.Color(255, 255, 255));

        txtAuxAsiento.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtAuxAsiento.setDisabledTextColor(new java.awt.Color(255, 255, 255));

        jLabel4.setText("Usuario");

        jLabel5.setText("Evento");

        jLabel6.setText("Asiento");

        javax.swing.GroupLayout FondoLayout = new javax.swing.GroupLayout(Fondo);
        Fondo.setLayout(FondoLayout);
        FondoLayout.setHorizontalGroup(
            FondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Cabezal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(FondoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(FondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 688, Short.MAX_VALUE)
                    .addGroup(FondoLayout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addGap(222, 222, 222)
                        .addComponent(jLabel3)
                        .addGap(67, 67, 67)
                        .addComponent(jLabel2)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, FondoLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(FondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(FondoLayout.createSequentialGroup()
                                .addComponent(txtAuxEvento, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtAuxUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, FondoLayout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(73, 73, 73)
                                .addComponent(jLabel4)
                                .addGap(49, 49, 49)))
                        .addGroup(FondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtAuxAsiento, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(FondoLayout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addComponent(jLabel6)))
                        .addGap(205, 205, 205)))
                .addContainerGap())
            .addGroup(FondoLayout.createSequentialGroup()
                .addGap(333, 333, 333)
                .addComponent(txtIDventa, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        FondoLayout.setVerticalGroup(
            FondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(FondoLayout.createSequentialGroup()
                .addComponent(Cabezal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 262, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtIDventa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(FondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtAuxUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtAuxAsiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtAuxEvento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(FondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, FondoLayout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, FondoLayout.createSequentialGroup()
                        .addGroup(FondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6))
                        .addGap(18, 18, 18)
                        .addGroup(FondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2))
                        .addGap(22, 22, 22))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Fondo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Fondo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tablaVentasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaVentasMouseClicked
       
        //Al hacer click en la tabla de ventas
        int fila = tablaVentas.rowAtPoint(evt.getPoint());         
        txtIDventa.setText(tablaVentas.getValueAt(fila, 0).toString());     
        //----------------------------------------------------------------------- 
        
        txtAuxUsuario.setText(tablaVentas.getValueAt(fila, 3).toString());
        txtAuxEvento.setText(tablaVentas.getValueAt(fila, 4).toString());
        txtAuxAsiento.setText(tablaVentas.getValueAt(fila, 5).toString());
            
            
    }//GEN-LAST:event_tablaVentasMouseClicked

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        
        //Al hacer click sobre el icono eliminar
        eliminarVenta();
        limpiar();
        
    }//GEN-LAST:event_jLabel2MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        
        modificarVenta();
        limpiar();

    }//GEN-LAST:event_jLabel3MouseClicked

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(menu2Ventas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(menu2Ventas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(menu2Ventas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(menu2Ventas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
               //new menu2Ventas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Cabezal;
    private javax.swing.JPanel Fondo;
    private javax.swing.JButton jButton1;
    private javax.swing.JInternalFrame jInternalFrame2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTable tablaVentas;
    private javax.swing.JTextField txtAuxAsiento;
    private javax.swing.JTextField txtAuxEvento;
    private javax.swing.JTextField txtAuxUsuario;
    private javax.swing.JTextField txtIDventa;
    // End of variables declaration//GEN-END:variables
}
