/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package vista;

/**
 *
 * @author aleja
 */
public class VistaFinanzas extends javax.swing.JInternalFrame {

    /**
     * Creates new form VistaFinanzas
     */
    public VistaFinanzas() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cb_anio = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        cb_mes = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btn_buscar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel7 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        lbl_caja = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        lbl_bancos = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        lbl_perdidas = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        lbl_inventario = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        lbl_descuentos = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        lbl_costos = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        lbl_mas_vendido = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        lbl_ganancias = new javax.swing.JLabel();

        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        getContentPane().add(cb_anio, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 90, 110, -1));

        jLabel1.setText("MES");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 60, -1, -1));

        cb_mes.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));
        getContentPane().add(cb_mes, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 90, 100, -1));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel2.setText("FINANZAS");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, -1, -1));

        jLabel3.setText("AÑO");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 60, -1, -1));

        btn_buscar.setBackground(new java.awt.Color(255, 102, 102));
        btn_buscar.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        btn_buscar.setForeground(new java.awt.Color(255, 255, 255));
        btn_buscar.setText("BUSCAR");
        getContentPane().add(btn_buscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 60, 120, 40));

        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 102, 102));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Efectivo (Caja)");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, 140, 30));

        lbl_caja.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lbl_caja.setForeground(new java.awt.Color(255, 255, 255));
        lbl_caja.setText("$ 1,321,203.00");
        jPanel1.add(lbl_caja, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 160, 90));

        jPanel7.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 20, 170, 140));

        jPanel2.setBackground(new java.awt.Color(255, 102, 102));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Bancos");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 110, 130, 30));

        lbl_bancos.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lbl_bancos.setForeground(new java.awt.Color(255, 255, 255));
        lbl_bancos.setText("$ 1,321,203.00");
        jPanel2.add(lbl_bancos, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 160, 90));

        jPanel7.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 20, 170, 140));

        jPanel3.setBackground(new java.awt.Color(255, 102, 102));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Perdidas");
        jPanel3.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 110, 130, 30));

        lbl_perdidas.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lbl_perdidas.setForeground(new java.awt.Color(255, 255, 255));
        lbl_perdidas.setText("$ 1,321,203.00");
        jPanel3.add(lbl_perdidas, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 160, 90));

        jPanel7.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 240, 170, 140));

        jPanel4.setBackground(new java.awt.Color(255, 102, 102));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Valor actual del inventario");
        jPanel4.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 160, 30));

        lbl_inventario.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lbl_inventario.setForeground(new java.awt.Color(255, 255, 255));
        lbl_inventario.setText("$ 1,321,203.00");
        jPanel4.add(lbl_inventario, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 160, 90));

        jPanel7.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 20, -1, -1));

        jPanel5.setBackground(new java.awt.Color(255, 102, 102));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Descuentos");
        jPanel5.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, 140, 30));

        lbl_descuentos.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lbl_descuentos.setForeground(new java.awt.Color(255, 255, 255));
        lbl_descuentos.setText("$ 1,321,203.00");
        jPanel5.add(lbl_descuentos, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 160, 90));

        jPanel7.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 240, 170, 140));

        jPanel6.setBackground(new java.awt.Color(255, 102, 102));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Costo de ventas");
        jPanel6.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, 140, 30));

        lbl_costos.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lbl_costos.setForeground(new java.awt.Color(255, 255, 255));
        lbl_costos.setText("$ 1,321,203.00");
        jPanel6.add(lbl_costos, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 160, 90));

        jPanel7.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 240, 170, 140));

        jPanel8.setBackground(new java.awt.Color(255, 102, 102));
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Producto más vendido");
        jPanel8.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, 140, 30));

        lbl_mas_vendido.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lbl_mas_vendido.setForeground(new java.awt.Color(255, 255, 255));
        lbl_mas_vendido.setText("Coca");
        jPanel8.add(lbl_mas_vendido, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 160, 90));

        jPanel7.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 430, 170, 140));

        jPanel9.setBackground(new java.awt.Color(255, 102, 102));
        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Ganancias");
        jPanel9.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, 140, 30));

        lbl_ganancias.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lbl_ganancias.setForeground(new java.awt.Color(255, 255, 255));
        lbl_ganancias.setText("$ 1,321,203.00");
        jPanel9.add(lbl_ganancias, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 160, 90));

        jPanel7.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 430, 170, 140));

        jScrollPane1.setViewportView(jPanel7);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 810, 450));

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btn_buscar;
    public javax.swing.JComboBox<String> cb_anio;
    public javax.swing.JComboBox<String> cb_mes;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JLabel lbl_bancos;
    public javax.swing.JLabel lbl_caja;
    public javax.swing.JLabel lbl_costos;
    public javax.swing.JLabel lbl_descuentos;
    public javax.swing.JLabel lbl_ganancias;
    public javax.swing.JLabel lbl_inventario;
    public javax.swing.JLabel lbl_mas_vendido;
    public javax.swing.JLabel lbl_perdidas;
    // End of variables declaration//GEN-END:variables
}
