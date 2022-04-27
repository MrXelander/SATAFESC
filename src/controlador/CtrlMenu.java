package controlador;

import vista.VistaMenu;
import modelo.Usuario;
import java.awt.event.*;
/**
 *
 * @author aleja
 */
public class CtrlMenu implements ActionListener{
    private VistaMenu view;
    private Usuario usr;
    
    public CtrlMenu(VistaMenu view, Usuario usr){
        this.view = view;
        this.usr = usr;
        this.view.btn_logout.addActionListener(this);
        this.view.btn_ventas.addActionListener(this);
        this.view.btn_inventario.addActionListener(this);
    }
    
    public void init(){
        view.setLocationRelativeTo(null);
        view.setResizable(false);
        //view.txt_nivel.setVisible(false);
        if(!usr.getTipo().equals("Admin")){
            view.btn_inventario.setEnabled(false);
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource()==view.btn_logout){ //al cerrar sesion se abrira una nueva pestaña de login
            view.dispose();
            modelo.Consultas cons = new modelo.Consultas();
            vista.VistaLogin vi = new vista.VistaLogin();
            modelo.Hash hash = new modelo.Hash();
            modelo.Usuario usr = new modelo.Usuario();

            CtrlLogin ctrl = new CtrlLogin(vi, cons, usr, hash);
            ctrl.init();
            vi.setVisible(true);
        }else if(e.getSource()==view.btn_ventas){
            view.escritorio.removeAll();
            vista.VistaVentas vi = new vista.VistaVentas();
            modelo.Consultas cons = new modelo.Consultas();
            modelo.Producto pro = new modelo.Producto();
            java.util.ArrayList<modelo.VentaDetalle> listapro = new java.util.ArrayList();
            javax.swing.table.DefaultTableModel tm = (javax.swing.table.DefaultTableModel)vi.tabla_ventas.getModel();
            javax.swing.table.DefaultTableModel tm2 = (javax.swing.table.DefaultTableModel)vi.tabla_busqueda.getModel();
            modelo.Funciones fun = new modelo.Funciones();
            modelo.Venta ven = new modelo.Venta();
            modelo.VentaDetalle vd = new modelo.VentaDetalle();
            CtrlVentas ctrl = new CtrlVentas(vi, cons, pro, listapro, tm, fun, tm2, usr, ven, vd);
            view.escritorio.add(vi);
            ctrl.init();
            vi.setVisible(true);
        }else if(e.getSource()==view.btn_inventario){
            view.escritorio.removeAll();
            vista.VistaInventario vi = new vista.VistaInventario();
            modelo.Consultas cons = new modelo.Consultas();
            modelo.Producto pro = new modelo.Producto();
            javax.swing.table.DefaultTableModel tm = (javax.swing.table.DefaultTableModel)vi.tabla_inventario.getModel();
            CtrlInventario ctrl = new CtrlInventario(vi, cons, pro, tm);
            view.escritorio.add(vi);
            ctrl.init();
            vi.setVisible(true);
        }
    }
}
