package controlador;

import vista.VistaInventario;
import modelo.Consultas;
import modelo.Producto;
import modelo.Usuario;
import java.awt.event.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
/**
 *
 * @author aleja
 */
public class CtrlInventario implements ActionListener, KeyListener{
    private VistaInventario view;
    private Consultas cons;
    private Producto pro;
    private DefaultTableModel tm;
    private Usuario usr;
    
    public CtrlInventario(VistaInventario view, Consultas cons, Producto pro, DefaultTableModel tm, Usuario usr){
        this.view = view;
        this.cons = cons;
        this.pro = pro;
        this.tm = tm;
        this.usr = usr;
        this.view.btn_agregar.addActionListener(this);
        this.view.chb_caducidad.addActionListener(this);
        this.view.txt_nombre.addKeyListener(this);
        this.view.btn_buscar.addActionListener(this);
        this.view.txt_codigobarras.addKeyListener(this);
        this.view.txt_costo.addKeyListener(this);
        this.view.txt_id.addKeyListener(this);
        this.view.txt_precio.addKeyListener(this);
        this.view.btn_actualizar.addActionListener(this);
        this.view.btn_borrar.addActionListener(this);
        this.view.btn_select.addActionListener(this);
        this.view.btn_limpiar.addActionListener(this);
    }
    
    public void init(){
        if(!usr.getTipo().equals("Admin")){
            view.btn_agregar.setEnabled(false);
            view.btn_actualizar.setEnabled(false);
            view.btn_borrar.setEnabled(false);
        }
        ArrayList<String> lista =  cons.listaCategorias();
        for(int i = 0; i<lista.size(); i++){
            view.cb_categoria.addItem(lista.get(i));
        }
        modelo.Funciones fun = new modelo.Funciones();
        int anio = fun.obtenerAnio();
        for(int i = anio; i<=anio+10;i++){
            view.cb_anio.addItem(String.valueOf(i));
        }
        limpiar();
    }
    
    @Override
     public void keyPressed(KeyEvent e) { }
     @Override
    public void keyReleased(KeyEvent e) { }
    @Override
    public void keyTyped(KeyEvent e){
        if(e.getSource()==view.txt_nombre){
            if(view.txt_nombre.getText().length()>=20){
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
        }else if(e.getSource()==view.txt_codigobarras){
            if(view.txt_codigobarras.getText().length()>=20){
                e.consume();
            }
        }else if(e.getSource()==view.txt_costo){
            int key = e.getKeyChar();
            boolean numeros = key >= 48 && key <= 57;
            boolean punto = key == 46;
            if (!(numeros || punto)){
                e.consume();
            }
            if(view.txt_costo.getText().length()>=10){
                e.consume();
            }
        }else if(e.getSource()==view.txt_precio){
            int key = e.getKeyChar();
            boolean numeros = key >= 48 && key <= 57;
            boolean punto = key == 46;
            if (!(numeros || punto)){
                e.consume();
            }
            if(view.txt_precio.getText().length()>=10){
                e.consume();
            }
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource()==view.btn_agregar){
            if(view.cb_categoria.getSelectedItem().toString()!=null && view.txt_nombre.getText()!=null && view.txt_codigobarras.getText()!=null && view.txt_precio.getText()!=null){
                pro = new Producto();
                pro.setId_categoria(cons.categoriaID(view.cb_categoria.getSelectedItem().toString()));
                pro.setNombre(view.txt_nombre.getText());
                pro.setCodigo_barras(view.txt_codigobarras.getText());
                pro.setPrecio(Double.parseDouble(view.txt_precio.getText()));
                if(view.chb_caducidad.isSelected()){
                    pro.setCaducidad(null);
                }else{
                    String fecha = view.cb_anio.getSelectedItem().toString() + "-" + view.cb_mes.getSelectedIndex() + "-01";
                    pro.setCaducidad(fecha);
                }
                pro.setExistencias((Integer)view.sp_existencias.getValue());
                pro.setCosto(Double.parseDouble(view.txt_costo.getText()));

                if(cons.agregarProducto(pro)){
                    JOptionPane.showMessageDialog(null, "Producto agregado exitosamente");
                    limpiar();
                }else{
                    JOptionPane.showMessageDialog(null, "Error al agregar producto");
                    limpiar();
                }
            }
        }else if(e.getSource()==view.chb_caducidad){
            if(view.cb_anio.isEnabled() && view.cb_mes.isEnabled()){
                view.cb_anio.setEnabled(false);
                view.cb_mes.setEnabled(false);
            }else{
                view.cb_anio.setEnabled(true);
                view.cb_mes.setEnabled(true);
            }
        }else if(e.getSource()==view.btn_buscar){
             if(view.txt_id.getText().equals("") && view.txt_nombre.getText().equals("") && view.txt_codigobarras.getText().equals("") && view.cb_categoria.getSelectedItem()==null){
                 JOptionPane.showMessageDialog(null, "No deje campos vacios");
             }else{
                 tm.setRowCount(0);
                pro = new Producto();
                String sql;
                if(!view.txt_id.getText().equals("") && view.txt_nombre.getText().equals("") && view.txt_codigobarras.getText().equals("")){
                   sql = " WHERE Id_Producto = '" + view.txt_id.getText() + "'";
                }else if(view.txt_id.getText().equals("") && !view.txt_nombre.getText().equals("") && view.txt_codigobarras.getText().equals("")){
                    sql = " WHERE Nom_Producto LIKE '%" + view.txt_nombre.getText() + "%'";
                }else if(view.txt_id.getText().equals("") && view.txt_nombre.getText().equals("") && !view.txt_codigobarras.getText().equals("")){
                    sql = " WHERE Codigo_Barras = '" + view.txt_codigobarras.getText() + "'";
                   limpiar();
                }else if(view.txt_id.getText().equals("") && view.txt_nombre.getText().equals("") && view.txt_codigobarras.getText().equals("") && view.cb_categoria.getSelectedItem()!=null){
                    int categoria = cons.categoriaID(view.cb_categoria.getSelectedItem().toString());
                    sql = " WHERE Id_Categoria = '" + categoria + "'";
                }else{
                    JOptionPane.showMessageDialog(null, "Solo puede llenar un campo al buscar");
                    return;
                }

                if(cons.buscarProducto(sql, pro)){
                    tm.addRow(new Object[]{pro.getId(), pro.getCategoria(), pro.getNombre(), pro.getCodigo_barras(), pro.getPrecio(), pro.getCaducidad(), pro.getExistencias(), pro.getCosto()});
                    limpiar();
                }else{
                    JOptionPane.showMessageDialog(null, "Error al buscar");
                    limpiar();
                }
             }
        }else if(e.getSource()==view.btn_actualizar){
            pro = new Producto();
            pro.setId(Integer.parseInt(view.txt_id.getText()));
            pro.setId_categoria(cons.categoriaID(view.cb_categoria.getSelectedItem().toString()));
            pro.setNombre(view.txt_nombre.getText());
            pro.setCodigo_barras(view.txt_codigobarras.getText());
            pro.setPrecio(Double.parseDouble(view.txt_precio.getText()));
            
            if(view.chb_caducidad.isSelected()){
                pro.setCaducidad(null);
            }else{
                String fecha = view.cb_anio.getSelectedItem().toString() + "-" + view.cb_mes.getSelectedIndex() + "-01";
                pro.setCaducidad(fecha);
            }
            pro.setExistencias((Integer)view.sp_existencias.getValue());
            pro.setCosto(Double.parseDouble(view.txt_costo.getText()));
            
            if(cons.actualizarProducto(pro)){
                JOptionPane.showMessageDialog(null, "Producto actualizado correctamente");
                limpiar();
            }else{
                JOptionPane.showMessageDialog(null, "Error al actualizar");
                limpiar();
            }
        }else if(e.getSource()==view.btn_borrar){
            pro = new Producto();
            pro.setId(Integer.parseInt(view.txt_id.getText()));
            
            if(cons.borrarProducto(pro)){
                JOptionPane.showMessageDialog(null, "Producto eliminado exitosamente");
                limpiar();
            }else{
                JOptionPane.showMessageDialog(null, "Error al eliminar");
                limpiar();
            }
        }else if(e.getSource()==view.btn_select){
            if(view.tabla_inventario.getSelectedRow() != -1){
                int fila = view.tabla_inventario.getSelectedRow();
                view.txt_id.setText(String.valueOf(view.tabla_inventario.getValueAt(fila, 0)));
                view.cb_categoria.setSelectedItem(String.valueOf(view.tabla_inventario.getValueAt(fila, 1)));
                view.txt_nombre.setText(String.valueOf(view.tabla_inventario.getValueAt(fila, 2)));
                view.txt_codigobarras.setText(String.valueOf(view.tabla_inventario.getValueAt(fila, 3)));
                view.txt_precio.setText(String.valueOf(view.tabla_inventario.getValueAt(fila, 4)));
                if(view.tabla_inventario.getValueAt(fila, 5)==null){
                    view.chb_caducidad.setSelected(true);
                    view.cb_anio.setEnabled(false);
                    view.cb_mes.setEnabled(false);
                }else{
                    String fecha = String.valueOf(view.tabla_inventario.getValueAt(fila, 5));
                    String fechas[] = fecha.split("-");
                    int mes = Integer.parseInt(fechas[1]);
                    view.cb_mes.setSelectedItem(String.valueOf(mes));
                    view.cb_anio.setSelectedItem(fechas[0]);
                }
                view.sp_existencias.setValue(Integer.parseInt(view.tabla_inventario.getValueAt(fila, 6).toString()));
                view.txt_costo.setText(String.valueOf(view.tabla_inventario.getValueAt(fila, 7)));
            }else{
                JOptionPane.showMessageDialog(null, "No se ha seleccionado alguna fila de la tabla");
            }
        }else if(e.getSource()==view.btn_limpiar){
            limpiar();
        }
    }
    
    public void limpiar(){
        view.txt_codigobarras.setText(null);
        view.txt_costo.setText(null);
        view.txt_id.setText(null);
        view.txt_nombre.setText(null);
        view.txt_precio.setText(null);
        view.cb_anio.setSelectedItem(null);
        view.cb_categoria.setSelectedItem(null);
        view.cb_mes.setSelectedItem(null);
        view.chb_caducidad.setSelected(false);
        view.sp_existencias.setValue(0);
    }
}