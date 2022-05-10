package vista;

import java.awt.CardLayout;

/**
 *
 * @author aleja
 */
public class VistaMenu extends javax.swing.JFrame {

    //CardLayout para intercambiar paneles
    CardLayout panelPrincipal;
    
    
    /**
     * Creates new form VistaMenu
     */
    public VistaMenu() {
        initComponents();
        //panelPrincipal = (CardLayout) panelWorkZone.getLayout();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelSideBar = new javax.swing.JPanel();
        btn_ventas = new javax.swing.JButton();
        btn_inventario = new javax.swing.JButton();
        btn_usuarios = new javax.swing.JButton();
        buttonAyuda = new javax.swing.JButton();
        btn_logout = new javax.swing.JButton();
        labelLogo = new javax.swing.JLabel();
        labelSATA = new javax.swing.JLabel();
        btn_finanzas = new javax.swing.JButton();
        panelMenuBar = new javax.swing.JPanel();
        labelBienvenido = new javax.swing.JLabel();
        lbl_user = new javax.swing.JLabel();
        escritorio = new javax.swing.JDesktopPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Menu");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setMinimumSize(new java.awt.Dimension(800, 600));
        setUndecorated(true);
        setSize(new java.awt.Dimension(1000, 700));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelSideBar.setBackground(new java.awt.Color(255, 102, 102));
        panelSideBar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btn_ventas.setBackground(new java.awt.Color(255, 102, 102));
        btn_ventas.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        btn_ventas.setForeground(new java.awt.Color(255, 255, 255));
        btn_ventas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_add_shopping_cart_24px.png"))); // NOI18N
        btn_ventas.setText("Ventas");
        btn_ventas.setBorderPainted(false);
        panelSideBar.add(btn_ventas, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 200, 180, -1));

        btn_inventario.setBackground(new java.awt.Color(255, 102, 102));
        btn_inventario.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        btn_inventario.setForeground(new java.awt.Color(255, 255, 255));
        btn_inventario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_package_24px.png"))); // NOI18N
        btn_inventario.setText("Inventario");
        btn_inventario.setBorderPainted(false);
        panelSideBar.add(btn_inventario, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 250, 180, -1));

        btn_usuarios.setBackground(new java.awt.Color(255, 102, 102));
        btn_usuarios.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        btn_usuarios.setForeground(new java.awt.Color(255, 255, 255));
        btn_usuarios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/male_user_26px.png"))); // NOI18N
        btn_usuarios.setText("Usuarios");
        btn_usuarios.setBorderPainted(false);
        panelSideBar.add(btn_usuarios, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 350, 180, -1));

        buttonAyuda.setBackground(new java.awt.Color(255, 102, 102));
        buttonAyuda.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        buttonAyuda.setForeground(new java.awt.Color(255, 255, 255));
        buttonAyuda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_question_mark_24px.png"))); // NOI18N
        buttonAyuda.setText("Ayuda");
        buttonAyuda.setBorderPainted(false);
        panelSideBar.add(buttonAyuda, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 580, 180, -1));

        btn_logout.setBackground(new java.awt.Color(255, 102, 102));
        btn_logout.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        btn_logout.setForeground(new java.awt.Color(255, 255, 255));
        btn_logout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_Logout_24px.png"))); // NOI18N
        btn_logout.setText("Cerrar Sesión");
        btn_logout.setBorderPainted(false);
        panelSideBar.add(btn_logout, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 540, 180, -1));

        labelLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_online_store_100px.png"))); // NOI18N
        labelLogo.setToolTipText("");
        labelLogo.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        panelSideBar.add(labelLogo, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, 100, 100));

        labelSATA.setFont(new java.awt.Font("Dubai Medium", 1, 24)); // NOI18N
        labelSATA.setForeground(new java.awt.Color(255, 255, 255));
        labelSATA.setText("SATA-FESC");
        panelSideBar.add(labelSATA, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, -1, -1));

        btn_finanzas.setBackground(new java.awt.Color(255, 102, 102));
        btn_finanzas.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        btn_finanzas.setForeground(new java.awt.Color(255, 255, 255));
        btn_finanzas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_Us_Dollar_Circled_24px.png"))); // NOI18N
        btn_finanzas.setText("Finanzas");
        btn_finanzas.setBorderPainted(false);
        panelSideBar.add(btn_finanzas, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 300, 180, -1));

        getContentPane().add(panelSideBar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 180, 700));

        panelMenuBar.setBackground(new java.awt.Color(102, 102, 102));

        labelBienvenido.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        labelBienvenido.setForeground(new java.awt.Color(255, 255, 255));
        labelBienvenido.setText("Bienvenido, ");

        lbl_user.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lbl_user.setForeground(new java.awt.Color(255, 255, 255));
        lbl_user.setText("user");

        javax.swing.GroupLayout panelMenuBarLayout = new javax.swing.GroupLayout(panelMenuBar);
        panelMenuBar.setLayout(panelMenuBarLayout);
        panelMenuBarLayout.setHorizontalGroup(
            panelMenuBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMenuBarLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(labelBienvenido)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_user, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(445, Short.MAX_VALUE))
        );
        panelMenuBarLayout.setVerticalGroup(
            panelMenuBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelMenuBarLayout.createSequentialGroup()
                .addContainerGap(25, Short.MAX_VALUE)
                .addGroup(panelMenuBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelBienvenido)
                    .addComponent(lbl_user))
                .addGap(26, 26, 26))
        );

        getContentPane().add(panelMenuBar, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 0, 820, 80));

        javax.swing.GroupLayout escritorioLayout = new javax.swing.GroupLayout(escritorio);
        escritorio.setLayout(escritorioLayout);
        escritorioLayout.setHorizontalGroup(
            escritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 820, Short.MAX_VALUE)
        );
        escritorioLayout.setVerticalGroup(
            escritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 620, Short.MAX_VALUE)
        );

        getContentPane().add(escritorio, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 80, 820, 620));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btn_finanzas;
    public javax.swing.JButton btn_inventario;
    public javax.swing.JButton btn_logout;
    public javax.swing.JButton btn_usuarios;
    public javax.swing.JButton btn_ventas;
    private javax.swing.JButton buttonAyuda;
    public javax.swing.JDesktopPane escritorio;
    private javax.swing.JLabel labelBienvenido;
    private javax.swing.JLabel labelLogo;
    private javax.swing.JLabel labelSATA;
    public javax.swing.JLabel lbl_user;
    private javax.swing.JPanel panelMenuBar;
    private javax.swing.JPanel panelSideBar;
    // End of variables declaration//GEN-END:variables
}
