package views;

import database.Conexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Reservas extends javax.swing.JPanel {

    // Conexión y variables relacionadas con la base de datos
    Connection conet;
    Statement st;
    ResultSet rs;
    int idc;

    // Modelo para la tabla
    DefaultTableModel modelo;

    // Constructor de la clase
    public Reservas() {
        initComponents();
        consultar();
    }

    //metodos CRUD
    private void consultar() {
        String query = "SELECT * FROM reservas";

        try {
            conet = Conexion.getConnection();
            st = conet.createStatement();
            rs = st.executeQuery(query);
            modelo = (DefaultTableModel) Tabla.getModel();
            modelo.setRowCount(0);
            Object[] reserva = new Object[10]; // Número de columnas de la tabla
            while (rs.next()) {
                reserva[0] = rs.getInt("id");
                reserva[1] = rs.getString("nombre");
                reserva[2] = rs.getString("apellido");
                reserva[3] = rs.getString("correo");
                reserva[4] = rs.getString("telefono");
                reserva[5] = rs.getInt("numero_personas");
                reserva[6] = rs.getString("fecha_reserva");
                reserva[7] = rs.getString("hora_reserva");
                reserva[8] = rs.getString("preferencia_ubicacion");
                reserva[9] = rs.getString("restaurante");

                modelo.addRow(reserva.clone());
            }
            Tabla.setModel(modelo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void agregar() {
        String nombreCadena = txtNombre.getText();
        String apellidoCadena = txtApellido.getText();
        String correoCadena = txtCorreo.getText();
        String telefonoCadena = txtTelefono.getText();
        String numeroPersonasCadena = txtNumeroPersonas.getText();
        String fechaReservaCadena = txtFechaReserva.getText();
        String horaReservaCadena = txtHoraReserva.getText();
        String preferenciaUbicacionCadena = txtPreferenciaUbicacion.getText();
        String restauranteCadena = txtRestaurante.getText();

        try {
            if (nombreCadena.equals("") || apellidoCadena.equals("") || correoCadena.equals("") || telefonoCadena.equals("") || numeroPersonasCadena.equals("") || fechaReservaCadena.equals("") || horaReservaCadena.equals("")) {
                JOptionPane.showMessageDialog(null, "Los campos no pueden quedar vacíos");
                resetearFormulario();
            } else {
                String query = "INSERT INTO reservas (nombre, apellido, correo, telefono, numero_personas, fecha_reserva, hora_reserva, preferencia_ubicacion, restaurante) "
                        + "VALUES ('" + nombreCadena + "', '" + apellidoCadena + "', '" + correoCadena + "', '" + telefonoCadena + "', '" + numeroPersonasCadena + "', '" + fechaReservaCadena + "', '" + horaReservaCadena + "', '" + preferenciaUbicacionCadena + "', '" + restauranteCadena + "')";

                System.out.println("Query: " + query); // Depuración: muestra la consulta SQL

                conet = Conexion.getConnection();
                if (conet != null) {
                    System.out.println("Conexión establecida con éxito");
                    st = conet.createStatement();
                    int rowsInserted = st.executeUpdate(query);
                    if (rowsInserted > 0) {
                        JOptionPane.showMessageDialog(null, "Registro agregado!");
                    } else {
                        JOptionPane.showMessageDialog(null, "No se pudo agregar el registro. Intente nuevamente.");
                    }
                } else {
                    System.out.println("Error al establecer la conexión con la base de datos");
                    JOptionPane.showMessageDialog(null, "Error al establecer la conexión con la base de datos.");
                }
                resetearFormulario();
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }

    private void modificar() {
        String nombreCadena = txtNombre.getText();
        String apellidoCadena = txtApellido.getText();
        String correoCadena = txtCorreo.getText();
        String telefonoCadena = txtTelefono.getText();
        String numeroPersonasCadena = txtNumeroPersonas.getText();
        String fechaReservaCadena = txtFechaReserva.getText();
        String horaReservaCadena = txtHoraReserva.getText();
        String preferenciaUbicacionCadena = txtPreferenciaUbicacion.getText();
        String restauranteCadena = txtRestaurante.getText();

        try {
            if (nombreCadena.equals("") || apellidoCadena.equals("") || correoCadena.equals("") || telefonoCadena.equals("") || numeroPersonasCadena.equals("") || fechaReservaCadena.equals("") || horaReservaCadena.equals("")) {
                JOptionPane.showMessageDialog(null, "Por favor, seleccione el registro que deseas modificar");
                resetearFormulario();
            } else {
                String query = "UPDATE reservas SET nombre='" + nombreCadena + "', apellido='" + apellidoCadena + "', correo='" + correoCadena + "', telefono='" + telefonoCadena + "', numero_personas='" + numeroPersonasCadena + "', fecha_reserva='" + fechaReservaCadena + "', hora_reserva='" + horaReservaCadena + "', preferencia_ubicacion='" + preferenciaUbicacionCadena + "', restaurante='" + restauranteCadena + "' WHERE id=" + idc;
                conet = Conexion.getConnection();
                st = conet.createStatement();
                st.executeUpdate(query);
                JOptionPane.showMessageDialog(null, "Registro modificado");
                resetearFormulario();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void eliminar() {
        int fila = Tabla.getSelectedRow();
        try {
            if (fila < 0) {
                //JOptionPane.showMessageDialog(null, "Por favor, selecciona un registro");
                mostrarDialogo("Por favor, selecciona un registro");
                resetearFormulario();
            } else {
                String query = "delete from reservas where id=" + idc;
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
        txtNombre.setText("");
        txtTelefono.setText("");
        txtCorreo.setText("");
        txtNumeroPersonas.setText("");
        txtApellido.setText("");
        txtHoraReserva.setText("");
        txtFechaReserva.setText("");
        txtPreferenciaUbicacion.setText("");
        txtRestaurante.setText("");
        txtId.requestFocus();
    }

    private void resetearFormulario() {
        txtId.setText("");
        txtNombre.setText("");
        txtTelefono.setText("");
        txtCorreo.setText("");
        txtNumeroPersonas.setText("");
        txtApellido.setText("");
        txtHoraReserva.setText("");
        txtFechaReserva.setText("");
        txtPreferenciaUbicacion.setText("");
        txtRestaurante.setText("");
    }

    private void mostrarDialogo(String mensaje) {
        JOptionPane optionPane = new JOptionPane(mensaje, JOptionPane.WARNING_MESSAGE);
        JDialog dialog = optionPane.createDialog(this, "Mensaje");
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        nombre = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        telefono = new javax.swing.JLabel();
        txtTelefono = new javax.swing.JTextField();
        localidad = new javax.swing.JLabel();
        txtCorreo = new javax.swing.JTextField();
        habilidades = new javax.swing.JLabel();
        txtNumeroPersonas = new javax.swing.JTextField();
        apellidos = new javax.swing.JLabel();
        txtApellido = new javax.swing.JTextField();
        habilidades1 = new javax.swing.JLabel();
        txtFechaReserva = new javax.swing.JTextField();
        habilidades2 = new javax.swing.JLabel();
        habilidades3 = new javax.swing.JLabel();
        habilidades4 = new javax.swing.JLabel();
        txtRestaurante = new javax.swing.JTextField();
        txtPreferenciaUbicacion = new javax.swing.JTextField();
        txtHoraReserva = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        Tabla = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        btnAgregar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnNuevo = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setToolTipText("datos");
        setPreferredSize(new java.awt.Dimension(790, 510));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setForeground(new java.awt.Color(102, 102, 102));
        jLabel1.setText("Id");

        txtId.setEditable(false);
        txtId.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        txtId.setEnabled(false);

        nombre.setForeground(new java.awt.Color(102, 102, 102));
        nombre.setText("Nombre:");

        telefono.setForeground(new java.awt.Color(102, 102, 102));
        telefono.setText("Telefono:");

        localidad.setForeground(new java.awt.Color(102, 102, 102));
        localidad.setText("Correo electronico:");

        habilidades.setForeground(new java.awt.Color(102, 102, 102));
        habilidades.setText("NroPersona:");

        apellidos.setForeground(new java.awt.Color(102, 102, 102));
        apellidos.setText("Apellido:");

        habilidades1.setForeground(new java.awt.Color(102, 102, 102));
        habilidades1.setText("Fecha reserva:");

        habilidades2.setForeground(new java.awt.Color(102, 102, 102));
        habilidades2.setText("Hora reserva:");

        habilidades3.setForeground(new java.awt.Color(102, 102, 102));
        habilidades3.setText("Pref.Ubicación:");

        habilidades4.setForeground(new java.awt.Color(102, 102, 102));
        habilidades4.setText("Restaurante:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(nombre))
                            .addComponent(telefono))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNombre)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(habilidades3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPreferenciaUbicacion, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(habilidades)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtNumeroPersonas))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(apellidos)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtApellido, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(localidad)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(95, 95, 95)
                                .addComponent(habilidades1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtFechaReserva, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(habilidades4)
                        .addGap(12, 12, 12)
                        .addComponent(txtRestaurante, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39)
                        .addComponent(habilidades2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtHoraReserva, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(93, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(apellidos)
                            .addComponent(nombre)
                            .addComponent(txtApellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(localidad)
                            .addComponent(txtCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(telefono)
                                .addComponent(habilidades)
                                .addComponent(txtNumeroPersonas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtFechaReserva, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(habilidades1))
                            .addComponent(txtTelefono, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtRestaurante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtPreferenciaUbicacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(habilidades3)
                                    .addComponent(habilidades4)
                                    .addComponent(habilidades2)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(txtHoraReserva, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(17, 17, 17))
        );

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 30, 770, 180));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        Tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "NOMBRE", "APELLIDOS", "CORREO", "TELEFONO", "NUM_PERSONA", "FECHA_RESERVA", "HORA_RESERVA", "PREF_UBIC", "RESTAURANTE"
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
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 744, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE))
        );

        add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 220, 750, 200));

        jLabel5.setFont(new java.awt.Font("Segoe UI Black", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(102, 102, 102));
        jLabel5.setText("Reservas");
        add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 10, -1, -1));

        btnAgregar.setForeground(new java.awt.Color(102, 102, 102));
        btnAgregar.setText("Añadir");
        btnAgregar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });
        add(btnAgregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 440, 98, -1));

        btnModificar.setForeground(new java.awt.Color(102, 102, 102));
        btnModificar.setText("Modificar");
        btnModificar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });
        add(btnModificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 440, 98, -1));

        btnEliminar.setForeground(new java.awt.Color(102, 102, 102));
        btnEliminar.setText("Borrar");
        btnEliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 440, 98, -1));

        btnNuevo.setForeground(new java.awt.Color(102, 102, 102));
        btnNuevo.setText("Nuevo");
        btnNuevo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });
        add(btnNuevo, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 440, 98, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void TablaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TablaMouseClicked
        int fila = Tabla.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(null, "Por favor, seleccione una fila");
        } else {
            idc = Integer.parseInt((String) Tabla.getValueAt(fila, 0).toString());
            String nombreCadena = (String) Tabla.getValueAt(fila, 1).toString();
            String apellidoCadena = (String) Tabla.getValueAt(fila, 2).toString();
            String correoCadena = (String) Tabla.getValueAt(fila, 3).toString();
            String telefonoCadena = (String) Tabla.getValueAt(fila, 4).toString();
            String NumeroPersonaCadena = (String) Tabla.getValueAt(fila, 5).toString();
            String fechaReservaCadena = (String) Tabla.getValueAt(fila, 6).toString();
            String horaReservaCadena = (String) Tabla.getValueAt(fila, 7).toString();
            String prefUicacionCadena = (String) Tabla.getValueAt(fila, 8).toString();
            String restauranteCadena = (String) Tabla.getValueAt(fila, 9).toString();

            txtId.setText("" + idc);
            txtNombre.setText(nombreCadena);
            txtApellido.setText(apellidoCadena);
            txtCorreo.setText(correoCadena);
            txtTelefono.setText(telefonoCadena);
            txtNumeroPersonas.setText(NumeroPersonaCadena);
            txtFechaReserva.setText(fechaReservaCadena);
            txtHoraReserva.setText(horaReservaCadena);
            txtPreferenciaUbicacion.setText(prefUicacionCadena);
            txtRestaurante.setText(restauranteCadena);
        }
    }//GEN-LAST:event_TablaMouseClicked

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        nuevo();
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        eliminar();
        consultar();
        nuevo();
    }//GEN-LAST:event_btnEliminarActionPerformed

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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable Tabla;
    private javax.swing.JLabel apellidos;
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JLabel habilidades;
    private javax.swing.JLabel habilidades1;
    private javax.swing.JLabel habilidades2;
    private javax.swing.JLabel habilidades3;
    private javax.swing.JLabel habilidades4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel localidad;
    private javax.swing.JLabel nombre;
    private javax.swing.JLabel telefono;
    private javax.swing.JTextField txtApellido;
    private javax.swing.JTextField txtCorreo;
    private javax.swing.JTextField txtFechaReserva;
    private javax.swing.JTextField txtHoraReserva;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtNumeroPersonas;
    private javax.swing.JTextField txtPreferenciaUbicacion;
    private javax.swing.JTextField txtRestaurante;
    private javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables
}
