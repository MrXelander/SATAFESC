/*
 * El controlador del Login comenzara su desarrollo en cuanto este lista la vista de Login
 * 
 */
package controlador;

import vista.VistaLogin;
import modelo.Consultas;
import modelo.Hash;
import modelo.Usuario;
import java.awt.event.*;
import javax.swing.JOptionPane;

/**
 *
 * @author aleja
 */
public class CtrlLogin implements ActionListener{
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
    }
    
    public void init(){
        view.setLocationRelativeTo(null);
        view.setTitle("Login");
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource()==view.btn_login){
            usr.setUsuario(view.txt_user.getText());
            String pwd = new String(view.pf_password.getPassword());
            usr.setPassword(modelo.Hash.getHash(pwd, "SHA1"));
            if(cons.login(usr)){
                JOptionPane.showMessageDialog(null, "Bienvenido al sistema");
            }else{
                JOptionPane.showMessageDialog(null, "Datos incorrectos");
            }
        }
    }
}
