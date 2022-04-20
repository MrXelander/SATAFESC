package app;

import vista.VistaLogin;
import modelo.Consultas;
import modelo.Hash;
import modelo.Usuario;
import controlador.CtrlLogin;
/**
 *
 * @author aleja
 */
public class App {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Consultas cons = new Consultas();
        VistaLogin view = new VistaLogin();
        Hash hash = new Hash();
        Usuario usr = new Usuario();
        
        CtrlLogin ctrl = new CtrlLogin(view, cons, usr, hash);
        ctrl.init();
        view.setVisible(true);
    }
    
}
