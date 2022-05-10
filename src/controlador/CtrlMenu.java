package controlador;

import vista.VistaMenu;
import modelo.Usuario;
import modelo.Consultas;
import modelo.Funciones;
import java.awt.event.*;
import javax.swing.JOptionPane;
/**
 *
 * @author aleja
 */
public class CtrlMenu implements ActionListener{
    private VistaMenu view;
    private Usuario usr;
    private Consultas cons;
    
    public CtrlMenu(VistaMenu view, Usuario usr, Consultas cons){
        this.view = view;
        this.usr = usr;
        this.cons = cons;
        this.view.btn_logout.addActionListener(this);
        this.view.btn_ventas.addActionListener(this);
        this.view.btn_inventario.addActionListener(this);
        this.view.btn_finanzas.addActionListener(this);
        this.view.btn_usuarios.addActionListener(this);
    }
    
    public void init(){
        view.setLocationRelativeTo(null);
        view.setResizable(false);
        if(!usr.getTipo().equals("Admin")){
            view.btn_finanzas.setEnabled(false);
            view.btn_usuarios.setEnabled(false);
        }
        java.util.ArrayList<modelo.Producto> listacad = new java.util.ArrayList();
        java.util.ArrayList<modelo.Producto> listastock = new java.util.ArrayList();
        java.util.ArrayList<String> listaProductos = cons.listaIdProductos();
        for(int i=0;i<listaProductos.size();i++){
            modelo.Producto pro = new modelo.Producto();
            pro.setId(Integer.parseInt(listaProductos.get(i)));
            String sql = " WHERE Id_Producto = '" + pro.getId() + "'";
            cons.buscarProducto(sql, pro);
            if(Funciones.caducidad(pro)){
                listacad.add(pro);
            }
            if(Funciones.stockProductos(pro)){
                listastock.add(pro);
            }
        }
        
        if(listacad!=null){
            for(int i=0; i<listacad.size();i++){
                JOptionPane.showMessageDialog(null, "Se ha caducado el producto con el id: " + listacad.get(i).getId());
            }
        }
        if(listastock!=null){
            for(int i=0; i<listastock.size();i++){
                JOptionPane.showMessageDialog(null, "Se ha terminado el producto con el id: " + listastock.get(i).getId());
            }
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource()==view.btn_logout){ //al cerrar sesion se abrira una nueva pestaña de login
            view.dispose();
            vista.VistaLogin vi = new vista.VistaLogin();
            modelo.Hash hash = new modelo.Hash();
            modelo.Usuario usr = new modelo.Usuario();

            CtrlLogin ctrl = new CtrlLogin(vi, cons, usr, hash);
            ctrl.init();
            vi.setVisible(true);
        }else if(e.getSource()==view.btn_ventas){
            view.escritorio.removeAll();
            vista.VistaVentas vi = new vista.VistaVentas();
            modelo.Producto pro = new modelo.Producto();
            java.util.ArrayList<modelo.VentaDetalle> listapro = new java.util.ArrayList();
            javax.swing.table.DefaultTableModel tm = (javax.swing.table.DefaultTableModel)vi.tabla_ventas.getModel();
            javax.swing.table.DefaultTableModel tm2 = (javax.swing.table.DefaultTableModel)vi.tabla_busqueda.getModel();
            modelo.Venta ven = new modelo.Venta();
            modelo.VentaDetalle vd = new modelo.VentaDetalle();
            CtrlVentas ctrl = new CtrlVentas(vi, cons, pro, listapro, tm, tm2, usr, ven, vd);
            view.escritorio.add(vi);
            ctrl.init();
            vi.setVisible(true);
        }else if(e.getSource()==view.btn_inventario){
            view.escritorio.removeAll();
            vista.VistaInventario vi = new vista.VistaInventario();
            modelo.Producto pro = new modelo.Producto();
            javax.swing.table.DefaultTableModel tm = (javax.swing.table.DefaultTableModel)vi.tabla_inventario.getModel();
            CtrlInventario ctrl = new CtrlInventario(vi, cons, pro, tm, usr);
            view.escritorio.add(vi);
            ctrl.init();
            vi.setVisible(true);
        }else if(e.getSource()==view.btn_finanzas){
            view.escritorio.removeAll();
            vista.VistaFinanzas vi = new vista.VistaFinanzas();
            controlador.CtrlFinanzas ctrl = new controlador.CtrlFinanzas(vi, cons);
            view.escritorio.add(vi);
            ctrl.init();
            vi.setVisible(true);
        }else if(e.getSource()==view.btn_usuarios){
            view.escritorio.removeAll();
            vista.VistaUsuarios vi = new vista.VistaUsuarios();
            modelo.Consultas cons = new modelo.Consultas();
            modelo.Hash hash = new modelo.Hash();
            javax.swing.table.DefaultTableModel tm = (javax.swing.table.DefaultTableModel)vi.tbl_usuarios.getModel();
            CtrlUsuarios ctrl = new CtrlUsuarios(vi, usr, cons, hash, tm);
            view.escritorio.add(vi);
            ctrl.init();
            vi.setVisible(true);
        }
    }
}
