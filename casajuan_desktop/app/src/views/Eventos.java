package views;

import database.Conexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Eventos extends javax.swing.JPanel {

    // Conexión y variables relacionadas con la base de datos
    Connection conet;
    Statement st;
    ResultSet rs;
    int idc;

    // Modelo para la tabla
    DefaultTableModel modelo;

    // Constructor de la clase
    public Eventos() {
        initComponents();
        consultar();
    }

    //metodos CRUD
    private void consultar() {
        String query = "SELECT * FROM eventos";

        try {
            conet = Conexion.getConnection();
            st = conet.createStatement();
            rs = st.executeQuery(query);
            modelo = (DefaultTableModel) Tabla.getModel();
            modelo.setRowCount(0);
            Object[] evento = new Object[4]; // número de columnas de la tabla
            while (rs.next()) {
                evento[0] = rs.getInt("id");
                evento[1] = rs.getString("titulo");
                evento[2] = rs.getString("fecha_evento");
                evento[3] = rs.getString("descripcion");

                modelo.addRow(evento.clone());
            }
            Tabla.setModel(modelo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void agregar() {
        String nombreEventoCadena = txtNombreEvento.getText();
        String fechaCadena = txtFecha.getText();
        String descripcionEventoCadena = txtDescripcionEvento.getText();

        try {
            if (nombreEventoCadena.equals("") || fechaCadena.equals("") || descripcionEventoCadena.equals("")) {
                JOptionPane.showMessageDialog(null, "Por favor, añada los datos");
                resetearFormulario();
            } else {

                String query = "INSERT into eventos(titulo, fecha_evento, descripcion) values ('" + nombreEventoCadena + "', '" + fechaCadena + "', '" + descripcionEventoCadena + "')";
                conet = Conexion.getConnection();
                st = conet.createStatement();
                st.executeUpdate(query);
                JOptionPane.showMessageDialog(null, "Nuevo registro agregado");
                consultar();
                resetearFormulario();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

private void modificar() {
    String nombreEventoCadena = txtNombreEvento.getText();
    String fechaCadena = txtFecha.getText();
    String descripcionEventoCadena = txtDescripcionEvento.getText();

    try {
        if (nombreEventoCadena.equals("") || fechaCadena.equals("") || descripcionEventoCadena.equals("")) {
            JOptionPane.showMessageDialog(null, "Por favor, seleccione el registro que deseas modificar");
        } else {
            // Reemplaza los nombres de las columnas de acuerdo con tu tabla "eventos"
            String query = "UPDATE eventos SET titulo='" + nombreEventoCadena + "', fecha_evento='" + fechaCadena + "', descripcion='" + descripcionEventoCadena + "' WHERE id=" + idc;
            conet = Conexion.getConnection();
            st = conet.createStatement();
            st.executeUpdate(query);
            JOptionPane.showMessageDialog(null, "Registro modificado");
            consultar();
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
}


  
    private void eliminar() {
        int fila = Tabla.getSelectedRow();
        try {
            if (fila < 0) {
                JOptionPane.showMessageDialog(null, "Por favor, selecciona un registro");
                resetearFormulario();
            } else {
                String query = "delete from eventos where id=" + idc;
                conet = Conexion.getConnection();
                st = conet.createStatement();
                st.executeUpdate(query);
                JOptionPane.showMessageDialog(null, "Registro eliminado");
                resetearFormulario();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

 
    private void nuevo() {
        txtId.setText("");
        txtNombreEvento.setText("");
        txtFecha.setText("");
        txtDescripcionEvento.setText("");
        txtId.requestFocus();
    }

    private void resetearFormulario() {
        txtId.setText("");
        txtNombreEvento.setText("");
        txtFecha.setText("");
        txtDescripcionEvento.setText("");
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        tituloEvento = new javax.swing.JLabel();
        txtNombreEvento = new javax.swing.JTextField();
        fecha = new javax.swing.JLabel();
        txtFecha = new javax.swing.JTextField();
        descripcionEvento = new javax.swing.JLabel();
        txtDescripcionEvento = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        Tabla = new javax.swing.JTable();
        btnModificar = new javax.swing.JButton();
        btnAgregar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnNuevo = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setToolTipText("datos");
        setPreferredSize(new java.awt.Dimension(0, 642));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setForeground(new java.awt.Color(102, 102, 102));
        jLabel1.setText("ID:");

        txtId.setEditable(false);
        txtId.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        txtId.setEnabled(false);

        tituloEvento.setForeground(new java.awt.Color(102, 102, 102));
        tituloEvento.setText("TITULO DEL EVENTO:");

        fecha.setForeground(new java.awt.Color(102, 102, 102));
        fecha.setText("FECHA:");

        descripcionEvento.setForeground(new java.awt.Color(102, 102, 102));
        descripcionEvento.setText("DESCRIPCIÓN:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(tituloEvento)
                        .addGap(18, 18, 18)
                        .addComponent(txtNombreEvento, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(fecha, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(descripcionEvento)
                    .addComponent(txtDescripcionEvento, javax.swing.GroupLayout.PREFERRED_SIZE, 815, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(59, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(38, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tituloEvento)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtNombreEvento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(fecha)))
                .addGap(22, 22, 22)
                .addComponent(descripcionEvento)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtDescripcionEvento, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 880, 220));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        Tabla.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 12), new java.awt.Color(102, 102, 102))); // NOI18N
        Tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "NOMBRE_EVENTO", "FECHA", "DESCRIPCIÓN"
            }
        ));
        Tabla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TablaMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(Tabla);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 820, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 300, 850, 240));

        btnModificar.setForeground(new java.awt.Color(102, 102, 102));
        btnModificar.setText("Modificar");
        btnModificar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });
        add(btnModificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 550, 98, -1));

        btnAgregar.setForeground(new java.awt.Color(102, 102, 102));
        btnAgregar.setText("Añadir");
        btnAgregar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });
        add(btnAgregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 550, 98, -1));

        btnEliminar.setForeground(new java.awt.Color(102, 102, 102));
        btnEliminar.setText("Borrar");
        btnEliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 550, 98, -1));

        btnNuevo.setForeground(new java.awt.Color(102, 102, 102));
        btnNuevo.setText("Nuevo");
        btnNuevo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });
        add(btnNuevo, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 550, 98, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI Black", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(102, 102, 102));
        jLabel4.setText("Eventos");
        add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 20, -1, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        agregar();
        consultar();
        nuevo();
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        modificar();
        consultar();
        nuevo();
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        eliminar();
        consultar();
        //nuevo();
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        nuevo();
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void TablaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TablaMouseClicked
        int fila = Tabla.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(null, "Por favor, seleccione una fila");
        } else {
            idc = Integer.parseInt((String) Tabla.getValueAt(fila, 0).toString());
            String nombreEventoCadena = (String) Tabla.getValueAt(fila, 1).toString();
            String fechaCadena = (String) Tabla.getValueAt(fila, 2).toString();
            String descripcionEventoCadena = (String) Tabla.getValueAt(fila, 3).toString();

            txtId.setText("" + idc);
            txtNombreEvento.setText(nombreEventoCadena);
            txtFecha.setText(fechaCadena);
            txtDescripcionEvento.setText(descripcionEventoCadena);
        }
    }//GEN-LAST:event_TablaMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable Tabla;
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JLabel descripcionEvento;
    private javax.swing.JLabel fecha;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel tituloEvento;
    private javax.swing.JTextField txtDescripcionEvento;
    private javax.swing.JTextField txtFecha;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtNombreEvento;
    // End of variables declaration//GEN-END:variables
}
