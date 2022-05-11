package controlador;

import vista.VistaVentas;
import modelo.Consultas;
import modelo.Producto;
import modelo.Funciones;
import modelo.Usuario;
import modelo.Venta;
import modelo.VentaDetalle;
import java.util.ArrayList;
import java.awt.event.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author aleja
 */
public class CtrlVentas implements ActionListener, KeyListener{
    private VistaVentas view;
    private Consultas cons;
    private Producto pro;
    private ArrayList<VentaDetalle> listapro;
    private DefaultTableModel tm;
    private DefaultTableModel tm2;
    private final Usuario usr;
    private Venta ven;
    private VentaDetalle vd;
    
    public CtrlVentas(VistaVentas view, Consultas cons, Producto pro,ArrayList<VentaDetalle> listapro, DefaultTableModel tm, DefaultTableModel tm2, Usuario usr, Venta ven, VentaDetalle vd){
        this.view = view;
        this.cons = cons;
        this.pro = pro;
        this.listapro = listapro;
        this.tm = tm;
        this.tm2 = tm2;
        this.usr = usr;
        this.ven = ven;
        this.vd = vd;
        this.view.btn_buscar.addActionListener(this);
        this.view.cb_clientes.addActionListener(this);
        this.view.btn_eliminar.addActionListener(this);
        this.view.btn_agregar.addActionListener(this);
        this.view.txt_pago.addKeyListener(this);
        this.view.btn_pagar.addActionListener(this);
        this.view.txt_puntos.addKeyListener(this);
    }
    
    public void init(){
        view.lbl_resultado.setVisible(false);
        ArrayList<String> lista = cons.listaClientes();
        for(int i = 0; i<lista.size(); i++){
            view.cb_clientes.addItem(lista.get(i));
        }
        
        if(usr.getTipo().equals("Admin")){
            view.cb_pago.addItem("perdida");
        }
    }
    
    @Override
    public void keyPressed(KeyEvent e){ }
    @Override
    public void keyReleased(KeyEvent e){
        if(e.getSource()==view.txt_pago){
            double pago;
            if(view.txt_pago.getText().equals("")){
                pago = 0;
            }else{
                pago = Double.parseDouble(view.txt_pago.getText());
            }  
            double total = Double.parseDouble(view.txt_totalfinal.getText()); 
            double cambio = pago - total;
            view.txt_cambio.setText(String.valueOf(cambio));
            if(Double.parseDouble(view.txt_cambio.getText())<0){
                view.btn_pagar.setEnabled(false);
            }else{
                view.btn_pagar.setEnabled(true);
            }
        }else if(e.getSource()==view.txt_puntos){
            int pts;
            if(view.txt_puntos.getText().equals("")){
                pts = 0;
            }else{
                pts = Integer.parseInt(view.txt_puntos.getText());
            }
            double pago;
            if(view.txt_pago.getText().equals("")){
                pago = 0;
            }else{
                pago = Double.parseDouble(view.txt_pago.getText());
            }
            double total = Double.parseDouble(view.txt_total.getText());
            double desc = Funciones.puntosAPesos(pts);
            double totalfinal = total - desc;
            view.txt_totalfinal.setText(String.valueOf(totalfinal));
            double cambio = pago - totalfinal;
            view.txt_cambio.setText(String.valueOf(cambio));
            if(Double.parseDouble(view.txt_cambio.getText())<0){
                view.btn_pagar.setEnabled(false);
            }else{
                view.btn_pagar.setEnabled(true);
            }
            if(pts>Integer.parseInt(view.txt_puntosdisponibles.getText())){
                view.btn_pagar.setEnabled(false);
            }else{
                view.btn_pagar.setEnabled(true);
            }
        }
    }
    @Override
    public void keyTyped(KeyEvent e){
        if(e.getSource()==view.txt_pago){
            int key = e.getKeyChar();
            boolean numeros = key >= 48 && key <= 57;
            boolean punto = key == 46;
            if (!(numeros || punto)){
                e.consume();
            }
            if(view.txt_pago.getText().length()>=10){
                e.consume();
            }
            int c = Funciones.contador(view.txt_pago.getText(), '.');
            if(c>0){
                e.consume();
            }
        }else if(e.getSource()==view.txt_id){
            int key = e.getKeyChar();
            boolean numeros = key >= 48 && key <= 57;
            if (!(numeros)){
                e.consume();
            }
            if(view.txt_id.getText().length()>=11){
                e.consume();
            }
        }else if(e.getSource()==view.txt_codigo){
            if(view.txt_codigo.getText().length()>=20){
                e.consume();
            }
        }else if(e.getSource()==view.txt_puntos){
            int key = e.getKeyChar();
            boolean numeros = key >= 48 && key <= 57;
            if (!(numeros)){
                e.consume();
            }
            if(view.txt_id.getText().length()>=7){
                e.consume();
            }
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource()==view.btn_buscar){
            if(!view.txt_nombre.getText().equals("")){
                pro = new Producto();
                String sql = " WHERE Nom_Producto LIKE '%" + view.txt_nombre.getText() + "%'";
                if(cons.buscarProducto(sql, pro)){
                    tm2.setRowCount(0);
                    tm2.addRow(new Object[]{pro.getId(), pro.getNombre(), pro.getPrecio()});
                    limpiar();
                }else{
                    JOptionPane.showMessageDialog(null, "Error al buscar");
                }
            }else{
                JOptionPane.showMessageDialog(null, "No deje campos vacios");
                limpiar();
            }
        }else if(e.getSource()==view.cb_clientes){
            if(view.cb_clientes.getSelectedItem().equals("Invitado")){
                view.txt_puntos.setEnabled(false);
            }else{
                view.txt_puntos.setEnabled(true);
            }
            int id = cons.clienteID(view.cb_clientes.getSelectedItem().toString());
            int pts = cons.puntosDisponibles(id);
            view.txt_puntosdisponibles.setText(String.valueOf(pts));
        }else if(e.getSource()==view.btn_agregar){
            if((Integer)view.sp_cantidad.getValue()>0){
                String sql2 = null;
                pro = new Producto();
                if(!view.txt_id.getText().equals("") && view.txt_codigo.getText().equals("")){
                    pro.setId(Integer.parseInt(view.txt_id.getText()));
                    sql2 = " WHERE Id_Producto = '" + pro.getId() + "'";
                }else if(view.txt_id.getText().equals("") && !view.txt_codigo.getText().equals("")){
                    pro.setCodigo_barras(view.txt_codigo.getText());
                    sql2 = " WHERE Codigo_Barras = '" + pro.getCodigo_barras() + "'";
                }
                
                if(cons.buscarProducto(sql2, pro)){
                    int sp = (Integer)view.sp_cantidad.getValue();
                    int con = 0;
                    if(view.tabla_ventas.getRowCount()==0){
                        con = 0;
                    }else{
                        for(int i=0;i<view.tabla_ventas.getRowCount();i++){
                            if(pro.getId()==Integer.parseInt(view.tabla_ventas.getValueAt(i, 4).toString())){
                                con += Integer.parseInt(view.tabla_ventas.getValueAt(i, 4).toString());
                            }
                        }
                    }
                    int exis = sp + con;
                    if(pro.getExistencias()>=exis){
                        vd = new VentaDetalle();
                        double total = pro.getPrecio()*(Integer)view.sp_cantidad.getValue();
                        tm.addRow(new Object[]{pro.getNombre(), (Integer)view.sp_cantidad.getValue(), pro.getPrecio(), total, pro.getId()});
                        vd.setId_producto(pro.getId());
                        vd.setCantidad((Integer)view.sp_cantidad.getValue());
                        vd.setTotal(total);
                        listapro.add(vd);
                        double totalcompra = Double.parseDouble(view.txt_total.getText()) + total;
                        view.txt_total.setText(String.valueOf(totalcompra));
                        int puntos = Integer.parseInt(view.txt_puntos.getText());
                        double puntosusar = Funciones.puntosAPesos(puntos);
                        double totalfinal = totalcompra - puntosusar;
                        view.txt_totalfinal.setText(String.valueOf(totalfinal));
                        double pago = Double.parseDouble(view.txt_pago.getText());
                        double cambio = pago - totalfinal;
                        view.txt_cambio.setText(String.valueOf(cambio));
                        if(cambio<0){
                        view.btn_pagar.setEnabled(false);
                        }else{
                            view.btn_pagar.setEnabled(true);
                        }
                        limpiar();
                    }else{
                        JOptionPane.showMessageDialog(null, "No hay suficientes existencias");
                    }
                }else{
                        JOptionPane.showMessageDialog(null, "Producto no encontrado");
                    }
            }else{
                JOptionPane.showMessageDialog(null, "No puede añadir 0 productos");
            }
        }else if(e.getSource()==view.btn_eliminar){
            if(view.tabla_ventas.getSelectedRow() !=-1){
                int fila = view.tabla_ventas.getSelectedRow();
                double subtotal = Double.parseDouble(view.tabla_ventas.getValueAt(fila, 3).toString());
                double total = Double.parseDouble(view.txt_total.getText()) - subtotal;
                view.txt_total.setText(String.valueOf(total));
                int puntos = Integer.parseInt(view.txt_puntos.getText());
                double puntosusar = Funciones.puntosAPesos(puntos);
                double totalfinal = total - puntosusar;
                view.txt_totalfinal.setText(String.valueOf(totalfinal));
                double pago;
                if(view.txt_pago.getText().equals("")){
                    pago = 0;
                }else{
                    pago = Double.parseDouble(view.txt_pago.getText());
                }
                double cambio = pago - totalfinal;
                if(cambio<0){
                view.btn_pagar.setEnabled(false);
                }else{
                    view.btn_pagar.setEnabled(true);
                }
                view.txt_cambio.setText(String.valueOf(cambio));
                listapro.remove(view.tabla_ventas.getSelectedRow());
                tm.removeRow(view.tabla_ventas.getSelectedRow());
            }else{
                JOptionPane.showMessageDialog(null, "Selecciona una fila");
            }
        }else if(e.getSource()==view.btn_pagar){
            if(Integer.parseInt(view.txt_total.getText())>0){
                ven = new Venta();
                ven.setId_empleado(usr.getId());
                ven.setId_cliente(cons.clienteID(view.cb_clientes.getSelectedItem().toString()));
                ven.setMetodo_pago(view.cb_pago.getSelectedItem().toString());
                ven.setFecha(Funciones.obtenerFecha());
                ven.setHora(Funciones.obtenerHora());
                ven.setDescuento(Funciones.puntosAPesos(Integer.parseInt(view.txt_puntos.getText())));
                ven.setMonto_total(Double.parseDouble(view.txt_totalfinal.getText()));
                int id = cons.registrarVenta(ven);
                if(id!=0){
                    for(int i=0;i<listapro.size();i++){
                        listapro.get(i).setId_venta(id);
                        if(cons.registrarVentaDetalle(listapro.get(i))){
                            pro = new Producto();
                            pro.setId(listapro.get(i).getId_producto());
                            if(cons.buscarProducto(" WHERE Id_Producto = '" + pro.getId() + "'", pro)){
                                int exis = pro.getExistencias() - listapro.get(i).getCantidad();
                                pro.setExistencias(exis);
                                cons.actualizarExistencias(pro);
                                cons.insertarRecompensas(ven);
                                view.lbl_resultado.setText("Venta realizada correctamente");
                                view.lbl_resultado.setVisible(true);
                                limpiar();
                            }
                        }
                    }
                }
            }else{
                JOptionPane.showMessageDialog(null, "No puede hacer una compra sin agregar productos");
            }
        }
    }
    
    public void limpiar(){
        view.txt_id.setText(null);
        view.txt_codigo.setText(null);
        view.txt_nombre.setText(null);
        view.sp_cantidad.setValue(0);
    }
}