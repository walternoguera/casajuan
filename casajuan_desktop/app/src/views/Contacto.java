/**
 * Clase que representa la interfaz gráfica para gestionar citas veterinarias.
 * Permite realizar operaciones CRUD (Crear, Leer, Actualizar, Eliminar) sobre las citas veterinarias.
 *
 * @author Walter Morel Noguera
 * @version 1.0
 * @see <a href="https://www.github.com/walternoguera">Repositorio en GitHub</a>
 */
package views;

import database.Conexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Contacto extends javax.swing.JPanel {

    // Conexión y variables relacionadas con la base de datos
    /**
 * Variables relacionadas con la conexión a la base de datos y la gestión de datos de citas veterinarias.
 * - {@code conet}: Representa la conexión a la base de datos.
 * - {@code st}: Representa el objeto Statement para ejecutar consultas SQL.
 * - {@code rs}: Representa el conjunto de resultados obtenidos de una consulta SQL.
 * - {@code idc}: Almacena el identificador de la cita veterinaria actualmente seleccionada.
 *
 * @see Contacto
 */
    Connection conet;
    Statement st;
    ResultSet rs;
    int idc;

    // Modelo para la tabla
    /**
 * Modelo utilizado para la representación y manipulación de datos en la tabla de citas veterinarias.
 * Permite gestionar la estructura y contenido de la tabla en la interfaz gráfica.
 *
 * @see Contacto
 */
    DefaultTableModel modelo;

    // Constructor de la clase
    /**
 * Constructor de la clase CitaVeterinaria.
 * Inicializa los componentes de la interfaz gráfica y realiza la consulta inicial de datos.
 */
    public Contacto() {
        initComponents();
        consultar();
    }

    //metodos CRUD
    /**
 * Realiza una consulta a la base de datos para obtener todas las citas veterinarias.
 * Actualiza el modelo de la tabla con los resultados de la consulta.
 *
 * La consulta SQL selecciona todas las columnas de la tabla "citas_veterinarias".
 * Los resultados se utilizan para actualizar el modelo de la tabla en la interfaz gráfica.
 *
 * @throws Exception Si ocurre un error durante la consulta o la actualización de la tabla.
 * @see Contacto
 */
    private void consultar() {
        String query = "SELECT * FROM contacto";

        try {
            conet = Conexion.getConnection();
            st = conet.createStatement();
            rs = st.executeQuery(query);
            modelo = (DefaultTableModel) Tabla.getModel();
            modelo.setRowCount(0);
            Object[] contacto = new Object[4]; //numero de columna de la tabla
            while (rs.next()) {
                contacto[0] = rs.getInt("id");
                contacto[1] = rs.getString("asunto");
                contacto[2] = rs.getString("correo");
                contacto[3] = rs.getString("comentario");

                modelo.addRow(contacto.clone());
            }
            Tabla.setModel(modelo);
        } catch (Exception e) {
        }
    }

    /**
 * Agrega una nueva cita veterinaria a la base de datos.
 * Obtiene los datos desde los campos de texto en la interfaz gráfica.
 * Realiza validaciones antes de realizar la inserción.
 * Después de agregar el registro, actualiza la tabla y resetea el formulario.
 *
 * @throws Exception Si ocurre un error durante la inserción o consulta de datos.
 * @see Contacto#consultar()
 * @see Contacto#resetearFormulario()
 */
    private void agregar() {
        String idAnimalCadena = txtAsunto.getText();
        String fechaCadena = txtCorreo.getText();
        String observaciones = txtMensaje.getText();

        try {
            if (idAnimalCadena.equals("") || fechaCadena.equals("") || observaciones.equals("")) {
                JOptionPane.showMessageDialog(null, "Por favor, añada los datos");
                //resetearFormulario();
            } else {

                String query = "INSERT into citas_veterinarias(ID_ANIMAL, FECHA, OBSERVACIONES) values ('" + idAnimalCadena + "', '" + fechaCadena + "', '" + observaciones + "' )";
                //conet = Conexion.getConnection();
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

    /**
 * Modifica una cita veterinaria existente en la base de datos.
 * Obtiene los datos desde los campos de texto en la interfaz gráfica.
 * Realiza validaciones antes de realizar la actualización.
 * Después de modificar el registro, actualiza la tabla.
 *
 * @throws Exception Si ocurre un error durante la actualización o consulta de datos.
 * @see Contacto#consultar()
 */
    private void modificar() {
        String idAnimalCadena = txtAsunto.getText();
        String fechaCadena = txtCorreo.getText();
        String observaciones = txtMensaje.getText();

        try {
            if (idAnimalCadena.equals("") || fechaCadena.equals("") || observaciones.equals("")) {
                JOptionPane.showMessageDialog(null, "Por favor, seleccione el registro que deseas modificar");
                //resetearFormulario();
            } else {

                String query = "UPDATE citas_veterinarias SET id_animal='" + idAnimalCadena + "', fecha='" + fechaCadena +  "', observaciones='" + observaciones + "' WHERE ID=" + idc;
                conet = Conexion.getConnection();
                st = conet.createStatement();
                st.executeUpdate(query);
                JOptionPane.showMessageDialog(null, "Registro modificado");
                consultar();
                //resetearFormulario();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
 * Elimina una cita veterinaria de la base de datos.
 * Obtiene el índice de la fila seleccionada en la tabla.
 * Realiza validaciones antes de realizar la eliminación.
 * Después de eliminar el registro, actualiza la tabla y resetea el formulario.
 *
 * @throws Exception Si ocurre un error durante la eliminación o consulta de datos.
 * @see Contacto#consultar()
 * @see Contacto#resetearFormulario()
 */
    private void eliminar() {
        int fila = Tabla.getSelectedRow();
        try {
            if (fila < 0) {
                JOptionPane.showMessageDialog(null, "Por favor, selecciona un registro");
                resetearFormulario();
            } else {
                String query = "delete from contacto where id=" + idc;
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
    

    /**
 * Prepara el formulario para ingresar una nueva cita veterinaria.
 * Limpia los campos de texto y establece el foco en el campo de ID.
 *
 * @see Contacto
 */
    private void nuevo() {
        txtId.setText("");
        txtAsunto.setText("");
        txtCorreo.setText("");
        txtMensaje.setText("");
        txtId.requestFocus();
    }

    /**
 * Restablece el formulario de la interfaz gráfica para su estado inicial.
 * Limpia todos los campos de texto.
 *
 * @see Contacto
 */
    private void resetearFormulario() {
        txtId.setText("");
        txtAsunto.setText("");
        txtCorreo.setText("");
        txtMensaje.setText("");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        nombre = new javax.swing.JLabel();
        txtAsunto = new javax.swing.JTextField();
        raza = new javax.swing.JLabel();
        txtCorreo = new javax.swing.JTextField();
        edad1 = new javax.swing.JLabel();
        txtMensaje = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        btnAgregar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnNuevo = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        Tabla = new javax.swing.JTable();

        setBackground(new java.awt.Color(255, 255, 255));
        setToolTipText("datos");
        setPreferredSize(new java.awt.Dimension(790, 510));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setForeground(new java.awt.Color(102, 102, 102));

        jLabel1.setForeground(new java.awt.Color(102, 102, 102));
        jLabel1.setText("ID:");

        txtId.setEditable(false);
        txtId.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        txtId.setEnabled(false);

        nombre.setForeground(new java.awt.Color(102, 102, 102));
        nombre.setText("Asunto:");

        raza.setForeground(new java.awt.Color(102, 102, 102));
        raza.setText("Mensaje:");

        edad1.setForeground(new java.awt.Color(102, 102, 102));
        edad1.setText("Correo:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(raza, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(nombre, javax.swing.GroupLayout.Alignment.LEADING))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtAsunto, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(163, 163, 163)
                        .addComponent(edad1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 114, Short.MAX_VALUE))
                    .addComponent(txtMensaje))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(nombre)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtAsunto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1)
                            .addComponent(edad1)
                            .addComponent(txtCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(raza)
                            .addComponent(txtMensaje, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 40, 740, 200));

        jLabel5.setFont(new java.awt.Font("Segoe UI Black", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(102, 102, 102));
        jLabel5.setText("Atencion al cliente");
        add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 10, -1, -1));

        btnAgregar.setForeground(new java.awt.Color(102, 102, 102));
        btnAgregar.setText("Añadir");
        btnAgregar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });
        add(btnAgregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 450, 98, -1));

        btnModificar.setForeground(new java.awt.Color(102, 102, 102));
        btnModificar.setText("Modificar");
        btnModificar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });
        add(btnModificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 450, 98, -1));

        btnEliminar.setForeground(new java.awt.Color(102, 102, 102));
        btnEliminar.setText("Borrar");
        btnEliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 450, 98, -1));

        btnNuevo.setForeground(new java.awt.Color(102, 102, 102));
        btnNuevo.setText("Nuevo");
        btnNuevo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });
        add(btnNuevo, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 450, 98, -1));

        Tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "asunto", "correo", "comentario"
            }
        ));
        Tabla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TablaMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(Tabla);

        add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 250, 730, 180));
    }// </editor-fold>//GEN-END:initComponents
/**
 * Maneja el evento de clic en el botón "Agregar".
 * Invoca el método {@code agregar()} para añadir una nueva cita veterinaria.
 *
 * @param evt Objeto que representa el evento de clic en el botón.
 * @see Contacto#agregar()
 */

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        agregar();
    }//GEN-LAST:event_btnAgregarActionPerformed
/**
 * Maneja el evento de clic en el botón "Modificar".
 * Invoca el método {@code modificar()} para actualizar una cita veterinaria.
 * Después de la modificación, se realiza una nueva consulta, y se establece un nuevo estado en el formulario.
 *
 * @param evt Objeto que representa el evento de clic en el botón.
 * @see Contacto#modificar()
 * @see Contacto#consultar()
 * @see Contacto#nuevo()
 */

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        modificar();
        consultar();
        nuevo();
    }//GEN-LAST:event_btnModificarActionPerformed
/**
 * Maneja el evento de clic en el botón "Eliminar".
 * Invoca el método {@code eliminar()} para borrar una cita veterinaria.
 * Después de la eliminación, se realiza una nueva consulta y se establece un nuevo estado en el formulario.
 *
 * @param evt Objeto que representa el evento de clic en el botón.
 * @see Contacto#eliminar()
 * @see Contacto#consultar()
 * @see Contacto#nuevo()
 */
    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        eliminar();
        consultar();
        nuevo();
    }//GEN-LAST:event_btnEliminarActionPerformed
    /**
 * Maneja el evento de clic en el botón "Nuevo".
 * Invoca el método {@code nuevo()} para preparar el formulario para ingresar una nueva cita veterinaria.
 *
 * @param evt Objeto que representa el evento de clic en el botón.
 * @see Contacto#nuevo()
 */
    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        nuevo();
    }//GEN-LAST:event_btnNuevoActionPerformed

    /**
 * Maneja el evento de clic en una fila de la tabla.
 * Obtiene la información de la fila seleccionada y la muestra en los campos de texto del formulario.
 *
 * @param evt Objeto que representa el evento de clic en una fila de la tabla.
 * @see Contacto
 */
    private void TablaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TablaMouseClicked
        int fila = Tabla.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(null, "Por favor, seleccione una fila");
        } else {
            idc = Integer.parseInt((String) Tabla.getValueAt(fila, 0).toString());
            String idAnimalCadena = (String) Tabla.getValueAt(fila, 1).toString();
            String fechaCadena = (String) Tabla.getValueAt(fila, 2).toString();
            String observaciones = (String) Tabla.getValueAt(fila, 3).toString();

            txtId.setText("" + idc);
            txtAsunto.setText(idAnimalCadena);
            txtCorreo.setText(fechaCadena);
            txtMensaje.setText(observaciones);
        }
    }//GEN-LAST:event_TablaMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable Tabla;
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JLabel edad1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel nombre;
    private javax.swing.JLabel raza;
    private javax.swing.JTextField txtAsunto;
    private javax.swing.JTextField txtCorreo;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtMensaje;
    // End of variables declaration//GEN-END:variables
}
