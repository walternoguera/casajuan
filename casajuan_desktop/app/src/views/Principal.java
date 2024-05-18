package views;

import database.Conexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JLabel;

public class Principal extends javax.swing.JPanel {
    
    Connection conet;
    Statement st;
    ResultSet rs;
    int idc;
    
    public Principal() {
        initComponents();
        conteoReservas();
        conteoContacto();
    }
    
    //metodos CRUD
    public void conteoReservas() {
        String query = "SELECT COUNT(*) FROM reservas;";

        try {
            conet = Conexion.getConnection();
            st = conet.createStatement();
            rs = st.executeQuery(query);
            
            
            if(rs.next()){
                String resultado = rs.getString(1);
                infoAnimal.setText(resultado);
            } else {
                infoAnimal.setText("0");
   
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void conteoContacto() {
        String query = "SELECT COUNT(*) FROM contacto;";

        try {
            conet = Conexion.getConnection();
            st = conet.createStatement();
            rs = st.executeQuery(query);
            
            
            if(rs.next()){
                String resultado = rs.getString(1);
                infoVoluntarios.setText(resultado);
            } else {
                infoVoluntarios.setText("0");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void getLabelInfoReservas(){
         conteoReservas();
    }
    public void getLabelInfoContacto(){
        conteoContacto();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel4 = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        panelInfoANimal = new javax.swing.JPanel();
        infoAnimal = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        dashboard = new javax.swing.JLabel();
        panelInfoVoluntarios = new javax.swing.JPanel();
        infoVoluntarios = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(246, 246, 246));
        setToolTipText("datos");
        setPreferredSize(new java.awt.Dimension(790, 510));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder(null, new java.awt.Color(204, 204, 204)));

        jLabel2.setFont(new java.awt.Font("Segoe UI Black", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(102, 102, 102));
        jLabel2.setText("¡Bienvenid@ al sistema de gestión de reservas de Casa Juan!");

        jLabel1.setForeground(new java.awt.Color(102, 102, 102));
        jLabel1.setText("Sistema de gestión para restaurantes");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                .addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, 9, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel2)
                .addGap(11, 11, 11)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(164, Short.MAX_VALUE))
        );

        add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 610, 260));

        panelInfoANimal.setBackground(new java.awt.Color(102, 0, 51));

        infoAnimal.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 36)); // NOI18N
        infoAnimal.setForeground(new java.awt.Color(255, 255, 255));
        infoAnimal.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Total reservas");
        jLabel4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel4.setVerticalTextPosition(javax.swing.SwingConstants.TOP);

        javax.swing.GroupLayout panelInfoANimalLayout = new javax.swing.GroupLayout(panelInfoANimal);
        panelInfoANimal.setLayout(panelInfoANimalLayout);
        panelInfoANimalLayout.setHorizontalGroup(
            panelInfoANimalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelInfoANimalLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(infoAnimal, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28))
            .addGroup(panelInfoANimalLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelInfoANimalLayout.setVerticalGroup(
            panelInfoANimalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelInfoANimalLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(infoAnimal, javax.swing.GroupLayout.DEFAULT_SIZE, 76, Short.MAX_VALUE)
                .addContainerGap())
        );

        add(panelInfoANimal, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 80, 120, -1));

        dashboard.setFont(new java.awt.Font("Segoe UI Black", 0, 18)); // NOI18N
        dashboard.setForeground(new java.awt.Color(102, 102, 102));
        dashboard.setText("Principal");
        add(dashboard, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        panelInfoVoluntarios.setBackground(new java.awt.Color(102, 0, 51));

        infoVoluntarios.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 36)); // NOI18N
        infoVoluntarios.setForeground(new java.awt.Color(255, 255, 255));
        infoVoluntarios.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Tickets");
        jLabel6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel6.setVerticalTextPosition(javax.swing.SwingConstants.TOP);

        javax.swing.GroupLayout panelInfoVoluntariosLayout = new javax.swing.GroupLayout(panelInfoVoluntarios);
        panelInfoVoluntarios.setLayout(panelInfoVoluntariosLayout);
        panelInfoVoluntariosLayout.setHorizontalGroup(
            panelInfoVoluntariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelInfoVoluntariosLayout.createSequentialGroup()
                .addContainerGap(32, Short.MAX_VALUE)
                .addGroup(panelInfoVoluntariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelInfoVoluntariosLayout.createSequentialGroup()
                        .addComponent(infoVoluntarios, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelInfoVoluntariosLayout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        panelInfoVoluntariosLayout.setVerticalGroup(
            panelInfoVoluntariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelInfoVoluntariosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(infoVoluntarios, javax.swing.GroupLayout.DEFAULT_SIZE, 76, Short.MAX_VALUE)
                .addContainerGap())
        );

        add(panelInfoVoluntarios, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 210, 120, -1));
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel dashboard;
    private javax.swing.JLabel infoAnimal;
    private javax.swing.JLabel infoVoluntarios;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JPanel panelInfoANimal;
    private javax.swing.JPanel panelInfoVoluntarios;
    // End of variables declaration//GEN-END:variables
}
