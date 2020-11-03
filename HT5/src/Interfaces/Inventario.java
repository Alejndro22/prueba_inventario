package Interfaces;
import Clases.Inventario_class;
import Clases.Conexion;
import java.awt.Color;
import java.awt.HeadlessException;
import java.beans.PropertyVetoException;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

public class Inventario extends javax.swing.JFrame {
    
    public Inventario() {
        initComponents();
        this.getContentPane().setBackground(Color.white);
        vaciarTabla();
    }

    public void vaciar(){
        id.setText("");
        nombre.setText("");
        marca.setText("");
        precio.setText("");
        existencias.setText("");
    }
    
    public String getTrxID(){
        Connection conid = null;
        Conexion conect = new Conexion();
        conid = conect.conectarMySQL();
        String trx_id="";
        System.out.println("aqui");
        try{
                String sqltxt = "SELECT trx.trx_id FROM INFORMATION_SCHEMA.INNODB_TRX trx WHERE trx.trx_started < CURRENT_TIMESTAMP - INTERVAL 1 SECOND;";
                Statement pst0 = conid.createStatement();
                try {
                    //Ponemos a "Dormir" el programa durante los ms que queremos
                    Thread.sleep(15*100);
                 } catch (Exception e) {
                    System.out.println(e);
                 }
                ResultSet rsl = pst0.executeQuery(sqltxt);
                while(rsl.next()){
                      trx_id = rsl.getString(1);
                      System.out.println("id"+trx_id);
                }
                conid.close();
             } catch (SQLException ex) {
                Logger.getLogger(Inventario.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        return trx_id;
    }
    
    public String writeToLog(String accion, String estado, int id, String tabla, String idtrx){
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String str = "<Id de transacción> " + "<" + idtrx + ">" +  "</Id de transacción>" + "\n" +
                     "<Tipo de transacción> " + "<" + accion + ">" +  "</Tipo de transacción>" + "\n" +
                     "<Estado de la transacción> " + "<" + estado + ">" + "</Estado de la transacción>" +  "\n" +
                     "<Fecha y hora de fin> " + "<" + formatter.format(date) + ">" + "/<Fecha y hora de fin>" + "\n" + "\n";
      return str;
    }
    
    public void vaciarTabla(){
        DefaultTableModel Modelo = (DefaultTableModel) jTable1.getModel();
        
        String titulos[] = {"Id","Nombre","Marca","Precio","Existencias"};
        
        Modelo = new DefaultTableModel(null,titulos);
        jTable1.setModel(Modelo);
    }
    
    
    
    public void verDatos(){
        try {
            DefaultTableModel miModelo = (DefaultTableModel) jTable1.getModel();
            Conexion conect1 = new Conexion();
            com.mysql.jdbc.Connection cn = conect1.conectarMySQL();
            String dts[] = new String[5];
            String sql = "select * from inventario";
            Statement st = (Statement) cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                dts[0] = rs.getString("id");
                dts[1] = rs.getString("Nombre");
                dts[2] = rs.getString("Marca"); 
                dts[3] = rs.getString("Precio");
                dts[4] = rs.getString("Existencias");
                miModelo.addRow(dts);
            }
            jTable1.setModel(miModelo);
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "NO SE PUEDEN VISUALIZAR LOS DATOS DE LA TABLA", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        JDP1 = new javax.swing.JDesktopPane();
        JDP2 = new javax.swing.JDesktopPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        marca = new javax.swing.JTextField();
        nombre = new javax.swing.JTextField();
        id = new javax.swing.JTextField();
        existencias = new javax.swing.JTextField();
        precio = new javax.swing.JTextField();
        load_data = new javax.swing.JButton();
        update = new javax.swing.JButton();
        view_table = new javax.swing.JButton();
        sett_isolation = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        delete = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        JDP1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setText("Precio");

        jLabel3.setText("Marca");

        jLabel1.setText("Nombre");

        jLabel6.setText("Id");

        jLabel4.setText("Existencias");

        marca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                marcaActionPerformed(evt);
            }
        });

        id.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                idActionPerformed(evt);
            }
        });

        load_data.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/refresh 40px.png"))); // NOI18N
        load_data.setText("Cargar datos");
        load_data.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        load_data.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                load_dataActionPerformed(evt);
            }
        });

        update.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Update tag 40px.png"))); // NOI18N
        update.setText("Actualizar");
        update.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        update.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateActionPerformed(evt);
            }
        });

        view_table.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/tabla 40px.png"))); // NOI18N
        view_table.setText("     Ver Movimiento");
        view_table.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                view_tableActionPerformed(evt);
            }
        });

        sett_isolation.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Aislamiento 40px.png"))); // NOI18N
        sett_isolation.setText("   Aislamiento");
        sett_isolation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sett_isolationActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable1);

        delete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Eliminar 40px.png"))); // NOI18N
        delete.setText("Eliminar");
        delete.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        delete.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel6)
                                            .addComponent(jLabel1)
                                            .addComponent(jLabel3)
                                            .addComponent(jLabel2))
                                        .addGap(23, 23, 23))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addGap(18, 18, 18)))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(precio)
                                    .addComponent(nombre)
                                    .addComponent(existencias)
                                    .addComponent(marca)
                                    .addComponent(id, javax.swing.GroupLayout.PREFERRED_SIZE, 410, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(291, 291, 291)
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(load_data, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(14, 14, 14)
                                .addComponent(update, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(delete, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(view_table, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(sett_isolation, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(13, 13, 13)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(marca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(precio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(existencias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(load_data)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(delete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(view_table, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(sett_isolation, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(update)))
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        JDP2.setLayer(jPanel1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout JDP2Layout = new javax.swing.GroupLayout(JDP2);
        JDP2.setLayout(JDP2Layout);
        JDP2Layout.setHorizontalGroup(
            JDP2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JDP2Layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        JDP2Layout.setVerticalGroup(
            JDP2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JDP2Layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        JDP1.setLayer(JDP2, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout JDP1Layout = new javax.swing.GroupLayout(JDP1);
        JDP1.setLayout(JDP1Layout);
        JDP1Layout.setHorizontalGroup(
            JDP1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JDP1Layout.createSequentialGroup()
                .addComponent(JDP2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        JDP1Layout.setVerticalGroup(
            JDP1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(JDP2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(JDP1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(JDP1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        DefaultTableModel tabla = (DefaultTableModel)jTable1.getModel();
        int selectedRow = jTable1.getSelectedRow();
        id.setText(tabla.getValueAt(selectedRow, 0).toString());
        nombre.setText(tabla.getValueAt(selectedRow, 1).toString());
        marca.setText(tabla.getValueAt(selectedRow, 2).toString());
        precio.setText(tabla.getValueAt(selectedRow, 3).toString());
        existencias.setText(tabla.getValueAt(selectedRow, 4).toString());
    }//GEN-LAST:event_jTable1MouseClicked

    private void sett_isolationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sett_isolationActionPerformed
        set_isolation obj = new set_isolation();
        JDP1.add(obj);
        obj.toFront();
        obj.setLocation(JDP1.getWidth() / 2 - obj.getWidth() / 2, JDP1.getHeight() / 2 - obj.getHeight() / 2);
        try {
            obj.setMaximum(true); //OPCIONAL
        } catch (PropertyVetoException ex) {
            Logger.getLogger(Inventario.class.getName()).log(Level.SEVERE, null, ex);
        }
        obj.setVisible(true);
    }//GEN-LAST:event_sett_isolationActionPerformed

    private void view_tableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_view_tableActionPerformed
        ver_Tabla2 obj = new ver_Tabla2();
        JDP1.add(obj);
        obj.toFront();
        obj.setLocation(JDP1.getWidth() / 2 - obj.getWidth() / 2, JDP1.getHeight() / 2 - obj.getHeight() / 2);
        try {
            obj.setMaximum(true); //OPCIONAL
        } catch (PropertyVetoException ex) {
            Logger.getLogger(Inventario.class.getName()).log(Level.SEVERE, null, ex);
        }
        obj.setVisible(true);
    }//GEN-LAST:event_view_tableActionPerformed

    private void updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateActionPerformed
        // TODO add your handling code here:
        String accion="Editar";
        String estado="";
        String trx_id = "";
        Inventario_class P = new Inventario_class(Integer.parseInt(id.getText()), nombre.getText(), marca.getText(), Float.valueOf(precio.getText()), Integer.parseInt(existencias.getText()));
        Connection con = null;
        Conexion conect = new Conexion();
        con = conect.conectarMySQL();
        
        try
        {
            con.setAutoCommit(false);
            System.out.println(con.getTransactionIsolation());
            Statement st = con.createStatement();
            String sql = "update inventario set Nombre = '" + P.getNombre() + "', Marca = '" + P.getMarca() + "', Precio = " + P.getPrecio() + ", Existencias = " + P.getExistencias() + " where Id = " + P.getId();
            int n = st.executeUpdate(sql);
            Date date = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String sql1 = "insert into movimiento (fecha, tipo) " + "values ('" + formatter.format(date) + "', 'Editar')";
            int n2 = st.executeUpdate(sql1);
            java.sql.Statement st0 = con.createStatement();  
            estado = "Parcialmente comprometida";
            
            
            int opc = JOptionPane.showConfirmDialog(this, "¿ESTA SEGURO QUE DESEA COMPLETAR LA TRANSACCIÓN?", "Pregunta", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            trx_id = getTrxID();
            jTextField1.requestFocus();
            if (opc == JOptionPane.YES_OPTION)
            {
                if (n > 0 && n2 > 0){
                    con.commit();
                    
                    estado = "Comprometida";
                    JOptionPane.showMessageDialog(this, "DATOS ACTUALIZADOS CORRECTAMENTE");
                    vaciarTabla();
                    verDatos();
                    vaciar();
                }
                else{
                con.rollback();
                estado = "Fallida";
                JOptionPane.showMessageDialog(this, "SE HA CANCELADO LA TRANSACCIÓN");
                }
            }
            else
            {
                con.rollback();
                estado = "Anulada";
                JOptionPane.showMessageDialog(this, "SE HA CANCELADO LA TRANSACCIÓN");
            }
        } catch (SQLException | HeadlessException e)
        {
            JOptionPane.showMessageDialog(this, "LOS DATOS NO HAN SIDO ACTUALIZADOS CORRECTAMENTE", "Error", JOptionPane.ERROR_MESSAGE);
            estado = "Fallida";
            try {
                con.rollback();
            } catch (SQLException ex) {
                Logger.getLogger(Inventario.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("NO SE HA PODIDO HACER EL ROLLBACK");
            }
        }finally{
            System.out.println(writeToLog(accion, estado, P.getId(), "Inventario - Movimiento", trx_id));
            try {
                String ruta = "log.txt";
                String contenido;
                contenido = writeToLog(accion, estado, P.getId(), "Inventario - Movimiento", trx_id);
                File file = new File(ruta);
                // Si el archivo no existe es creado
                if (!file.exists()) {
                    file.createNewFile();
                }
                FileWriter fw = new FileWriter(file, true);
                BufferedWriter bw = new BufferedWriter(fw);
                bw.write(contenido);
                bw.close();
                
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                con.setAutoCommit(true);
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(Inventario.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_updateActionPerformed

    private void load_dataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_load_dataActionPerformed
        vaciarTabla();
        vaciar();
        verDatos();

    }//GEN-LAST:event_load_dataActionPerformed

    private void idActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_idActionPerformed

    private void marcaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_marcaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_marcaActionPerformed

    private void deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteActionPerformed
        
        Connection con = null;
        Conexion conect = new Conexion();
        con = conect.conectarMySQL();
        try
        {
            con.setAutoCommit(false);
            System.out.println(con.getTransactionIsolation());
            Statement st = con.createStatement();
            String sql = "delete from inventario where id = " + Integer.parseInt(id.getText());
            int n = st.executeUpdate(sql);
            Date date = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String sql1 = "insert into movimiento (fecha, tipo) " + "values ('" + formatter.format(date) + "', 'Eliminar')";
            int n2 = st.executeUpdate(sql1);
            int opc = JOptionPane.showConfirmDialog(this, "¿ESTA SEGURO QUE DESEA COMPLETAR LA TRANSACCIÓN?", "Pregunta", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (opc == JOptionPane.YES_OPTION)
            {
                if (n > 0 && n2 > 0)
                {
                    con.commit();
                    JOptionPane.showMessageDialog(this, "DATO ELIMINADO CORRECTAMENTE");
                    vaciarTabla();
                    verDatos();
                    vaciar();
                }
            }
            else
            {
                con.rollback();
                JOptionPane.showMessageDialog(this, "SE HA CANCELADO LA TRANSACCIÓN");
            }
        } catch (SQLException | HeadlessException e)
        {
            JOptionPane.showMessageDialog(this, "LOS DATOS NO HAN SIDO ELIMINADOS CORRECTAMENTE", "Error", JOptionPane.ERROR_MESSAGE);
            try {
                con.rollback();
            } catch (SQLException ex) {
                Logger.getLogger(Inventario.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("NO SE HA PODIDO HACER EL ROLLBACK");
            }
        }finally{
            try {
                con.setAutoCommit(true);
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(Inventario.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_deleteActionPerformed

 
    public static void main(String args[]) {
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Inventario().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane JDP1;
    private javax.swing.JDesktopPane JDP2;
    private javax.swing.JButton delete;
    private javax.swing.JTextField existencias;
    private javax.swing.JTextField id;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    public static javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JButton load_data;
    private javax.swing.JTextField marca;
    private javax.swing.JTextField nombre;
    private javax.swing.JTextField precio;
    private javax.swing.JButton sett_isolation;
    private javax.swing.JButton update;
    private javax.swing.JButton view_table;
    // End of variables declaration//GEN-END:variables
}
