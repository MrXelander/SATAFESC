package controlador;

import vista.VistaUsuarios;
import modelo.Usuario;
import modelo.Consultas;
import modelo.Hash;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
/**
 *
 * @author aleja
 */
public class CtrlUsuarios implements ActionListener, KeyListener{
    private VistaUsuarios view;
    private Usuario usr;
    private Consultas cons;
    private Hash hash;
    private DefaultTableModel tm;
    
    public CtrlUsuarios(VistaUsuarios view, Usuario usr, Consultas cons, Hash hash, DefaultTableModel tm){
        this.view = view;
        this.usr = usr;
        this.cons = cons;
        this.hash = hash;
        this.tm = tm;
        this.view.btn_agregar.addActionListener(this);
        this.view.btn_buscar.addActionListener(this);
        this.view.btn_actualizar.addActionListener(this);
        this.view.btn_eliminar.addActionListener(this);
        this.view.btn_limpiar.addActionListener(this);
        this.view.btn_select.addActionListener(this);
    }
    
    public void init(){
        limpiar();
        view.cb_nivel.addItem("Admin");
        view.cb_nivel.addItem("Empleado");
    }
    
    @Override
    public void keyPressed(KeyEvent e) { }
    @Override
    public void keyReleased(KeyEvent e) { }
    @Override
    public void keyTyped(KeyEvent e){
        if(e.getSource()==view.txt_id){
            int key = e.getKeyChar();
            boolean numeros = key >= 48 && key <= 57;
            if (!(numeros)){
                e.consume();
            }
            if(view.txt_id.getText().length()>=11){
                e.consume();
            }
        }else if(e.getSource()==view.txt_nombre){
            if(view.txt_nombre.getText().length()>=25){
                e.consume();
            }
        }else if(e.getSource()==view.txt_usuario){
            if(view.txt_usuario.getText().length()>=10){
                e.consume();
            }
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource()==view.btn_agregar){
            if(!view.txt_nombre.getText().equals("") && !view.txt_usuario.getText().equals("") & !view.txt_contrasena.getText().equals("") && view.cb_nivel.getSelectedItem().toString()!=null){
                usr = new Usuario();
                usr.setNombre(view.txt_nombre.getText());
                usr.setPassword(hash.getHash(view.txt_contrasena.getText(), "SHA1"));
                usr.setUsuario(view.txt_usuario.getText());
                usr.setTipo(view.cb_nivel.getSelectedItem().toString());

                if(cons.agregarUsuario(usr)){
                    JOptionPane.showMessageDialog(null, "Usuario agregado correctamente");
                }else{
                    JOptionPane.showMessageDialog(null, "Error al agregar usuario");
                }
                limpiar();
            }else{
                JOptionPane.showMessageDialog(null, "No deje campos vacios");
            }
        }else if(e.getSource()==view.btn_buscar){
            if(!view.txt_id.getText().equals("") || !view.txt_usuario.getText().equals("")){
                tm.setRowCount(0);
                usr = new Usuario();
                String sql;
                if(!view.txt_id.getText().equals("") && view.txt_usuario.getText().equals("")){
                    sql = " WHERE Id_Empleado = '" + view.txt_id.getText() + "'";
                }else if(view.txt_id.getText().equals("") && !view.txt_usuario.getText().equals("")){
                    sql = " WHERE Usuario = '" + view.txt_usuario.getText() + "'";
                }else{
                    JOptionPane.showMessageDialog(null, "Solo puede llenar un campo al buscar");
                    return;
                }

                if(cons.buscarUsuario(sql, usr)){
                    tm.addRow(new Object[]{usr.getId(), usr.getNombre(), usr.getUsuario(), usr.getPassword(), usr.getTipo()});
                }else{
                    JOptionPane.showMessageDialog(null, "Usuario no encontrado");
                }
                limpiar();
            }else{
                JOptionPane.showMessageDialog(null, "No deje campos vacios");
            }
        }else if(e.getSource()==view.btn_actualizar){
            if(!view.txt_id.getText().equals("") && !view.txt_nombre.getText().equals("") && !view.txt_usuario.getText().equals("") & !view.txt_contrasena.getText().equals("") && view.cb_nivel.getSelectedItem().toString()!=null){
                usr = new Usuario();
                usr.setNombre(view.txt_nombre.getText());
                usr.setUsuario(view.txt_usuario.getText());
                usr.setPassword(hash.getHash(view.txt_contrasena.getText(), "SHA1"));
                usr.setTipo(view.cb_nivel.getSelectedItem().toString());
                usr.setId(Integer.parseInt(view.txt_id.getText()));
                
                if(cons.actualizarUsuario(usr)){
                    JOptionPane.showMessageDialog(null, "Usuario actualizado correctamente");
                }else{
                    JOptionPane.showMessageDialog(null, "Error al actualizar");
                }
                limpiar();
            }else{
                JOptionPane.showMessageDialog(null, "No deje campos vacios");
            }
        }else if(e.getSource()==view.btn_eliminar){
            if(!view.txt_id.getText().equals("")){
                usr = new Usuario();
                usr.setId(Integer.parseInt(view.txt_id.getText()));
                
                if(cons.borrarUsuario(usr)){
                    JOptionPane.showMessageDialog(null, "Usuario eliminado correctamente");
                }else{
                    JOptionPane.showMessageDialog(null, "Error al eliminar usuario");
                }
                limpiar();
            }else{
                JOptionPane.showMessageDialog(null, "No deje campos vacios");
            }
        }else if(e.getSource()==view.btn_limpiar){
            limpiar();
        }else if(e.getSource()==view.btn_select){
            if(view.tbl_usuarios.getSelectedRow()!=-1){
                int fila = view.tbl_usuarios.getSelectedRow();
                view.txt_id.setText(String.valueOf(view.tbl_usuarios.getValueAt(fila, 0)));
                view.txt_nombre.setText(String.valueOf(view.tbl_usuarios.getValueAt(fila, 1)));
                view.txt_usuario.setText(String.valueOf(view.tbl_usuarios.getValueAt(fila, 2)));
                view.cb_nivel.setSelectedItem(String.valueOf(view.tbl_usuarios.getValueAt(fila, 4)));
            }else{
                JOptionPane.showMessageDialog(null, "No se ha seleccionado alguna fila de la tabla");
            }
        }
    }
    
    public void limpiar(){
        view.txt_id.setText(null);
        view.txt_nombre.setText(null);
        view.txt_usuario.setText(null);
        view.txt_contrasena.setText(null);
        view.cb_nivel.setSelectedItem(null);
    }
}
