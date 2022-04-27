package controlador;

import vista.VistaLogin; //importamos clases de otros paquetes que se usaran
import modelo.Consultas;
import modelo.Hash;
import modelo.Usuario;
import java.awt.event.*; //tambien importamos librerias para eventos y alertas
import javax.swing.JOptionPane;

/**
 *
 * @author aleja
 */
public class CtrlLogin implements ActionListener, KeyListener{
    private VistaLogin view;
    private Consultas cons;
    private Usuario usr;
    private Hash hash;
    
    public CtrlLogin(VistaLogin view, Consultas cons, Usuario usr, Hash hash){
        this.view = view;
        this.cons = cons;
        this.usr = usr;
        this.hash = hash;
        this.view.btn_login.addActionListener(this);
        this.view.txt_user.addKeyListener(this);
    }
    
    public void init(){
        view.setLocationRelativeTo(null);
        view.setTitle("Login");
        view.setResizable(false);
    }
    
    @Override
    public void keyPressed(KeyEvent e){ }
    @Override
    public void keyReleased(KeyEvent e){ }
    @Override
    public void keyTyped(KeyEvent e){
        if(e.getSource()==view.txt_user){
            if(view.txt_user.getText().length()>=10){
                e.consume();
            }
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource()==view.btn_login){
            if(view.txt_user.getText().equals("")){
                JOptionPane.showMessageDialog(null, "No deje campos vacios");
            }else{
                usr.setUsuario(view.txt_user.getText());
                String pwd = new String(view.pf_password.getPassword());
                usr.setPassword(hash.getHash(pwd, "SHA1"));
                if(cons.login(usr)){
                    vista.VistaMenu frm_menu = new vista.VistaMenu();
                    controlador.CtrlMenu ctrlmenu = new controlador.CtrlMenu(frm_menu, usr);
                    ctrlmenu.init();
                    frm_menu.lbl_user.setText(view.txt_user.getText());
                    frm_menu.setVisible(true);
                    view.dispose();
                }else{
                    JOptionPane.showMessageDialog(null, "Datos incorrectos");
                }
            }
        }
    }
}
