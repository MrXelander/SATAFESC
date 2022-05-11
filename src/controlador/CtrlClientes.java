package controlador;

import vista.VistaClientes;
import modelo.Consultas;
import modelo.Cliente;
import modelo.Usuario;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
/**
 *
 * @author aleja
 */
public class CtrlClientes implements KeyListener, ActionListener{
    private VistaClientes view;
    private Consultas cons;
    private Cliente cli;
    private DefaultTableModel tm;
    private Usuario usr;
    
    public CtrlClientes(VistaClientes view, Consultas cons, Cliente cli, DefaultTableModel tm, Usuario usr){
        this.view = view;
        this.cons = cons;
        this.cli = cli;
        this.tm = tm;
        this.usr = usr;
        this.view.btn_agregar.addActionListener(this);
        this.view.btn_actualizar.addActionListener(this);
        this.view.btn_buscar.addActionListener(this);
        this.view.btn_eliminar.addActionListener(this);
        this.view.btn_limpiar.addActionListener(this);
        this.view.btn_select.addActionListener(this);
        this.view.txt_id.addKeyListener(this);
        this.view.txt_nombre.addKeyListener(this);
        this.view.txt_apaterno.addKeyListener(this);
        this.view.txt_amaterno.addKeyListener(this);
        this.view.txt_direccion.addKeyListener(this);
        this.view.txt_telefono.addKeyListener(this);
    }
    
    public void init(){
        if(!usr.getTipo().equals("Admin")){
            view.btn_eliminar.setEnabled(false);
        }
    }
    
    @Override
    public void keyPressed(KeyEvent e){ }
    @Override
    public void keyReleased(KeyEvent e){ }
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
            if(view.txt_nombre.getText().length()>=20){
                e.consume();
            }
        }else if(e.getSource()==view.txt_apaterno){
            if(view.txt_apaterno.getText().length()>=12){
                e.consume();
            }
        }else if(e.getSource()==view.txt_amaterno){
            if(view.txt_amaterno.getText().length()>=12){
                e.consume();
            }
        }else if(e.getSource()==view.txt_direccion){
            if(view.txt_direccion.getText().length()>=25){
                e.consume();
            }
        }else if(e.getSource()==view.txt_telefono){
            int key = e.getKeyChar();
            boolean numeros = key >= 48 && key <= 57;
            if (!(numeros)){
                e.consume();
            }
            if(view.txt_telefono.getText().length()>=10){
                e.consume();
            }
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource()==view.btn_agregar){
            if(!view.txt_nombre.getText().equals("") && !view.txt_apaterno.getText().equals("") && !view.txt_amaterno.getText().equals("") && !view.txt_direccion.getText().equals("") && !view.txt_telefono.getText().equals("")){
                cli = new Cliente();
                cli.setNombre(view.txt_nombre.getText());
                cli.setA_paterno(view.txt_apaterno.getText());
                cli.setA_materno(view.txt_amaterno.getText());
                cli.setDireccion(view.txt_direccion.getText());
                cli.setTelefono(view.txt_telefono.getText());
                if(cons.agregarCliente(cli)){
                    JOptionPane.showMessageDialog(null, "Cliente agregado exitosamente");
                    limpiar();
                }else{
                    JOptionPane.showMessageDialog(null, "Error al agregar");
                }
            }else{
                JOptionPane.showMessageDialog(null, "No deje campos vacios");
            }
        }else if(e.getSource()==view.btn_buscar){
            if(!view.txt_nombre.getText().equals("") || !view.txt_id.getText().equals("")){
                String sql;
                tm.setRowCount(0);
                if(view.txt_nombre.getText().equals("") && !view.txt_id.getText().equals("")){
                    cli.setId(Integer.parseInt(view.txt_id.getText()));
                    sql = " WHERE Id_Cliente = '" + cli.getId() + "'";
                }else if(!view.txt_nombre.getText().equals("") && view.txt_id.getText().equals("")){
                    cli.setNombre(view.txt_nombre.getText());
                    sql = " WHERE Nombre = '" + cli.getNombre() + "'";
                }else{
                    JOptionPane.showMessageDialog(null, "Solo puede llenar un campo");
                    return;
                }
                
                if(cons.buscarCliente(sql, cli)){
                    tm.addRow(new Object[] {cli.getId(), cli.getNombre(), cli.getA_paterno(), cli.getA_materno(), cli.getDireccion(), cli.getTelefono()});
                    limpiar();
                }else{
                    JOptionPane.showMessageDialog(null, "Error al buscar cliente");
                }
            }else{
                JOptionPane.showMessageDialog(null, "No deje campos vacios");
            }
        }else if(e.getSource()==view.btn_actualizar){
            if(!view.txt_id.getText().equals("") &&!view.txt_nombre.getText().equals("") && !view.txt_apaterno.getText().equals("") && !view.txt_amaterno.getText().equals("") && !view.txt_direccion.getText().equals("") && !view.txt_telefono.getText().equals("")){
                cli = new Cliente();
                cli.setId(Integer.parseInt(view.txt_id.getText()));
                cli.setNombre(view.txt_nombre.getText());
                cli.setA_paterno(view.txt_apaterno.getText());
                cli.setA_materno(view.txt_amaterno.getText());
                cli.setDireccion(view.txt_direccion.getText());
                cli.setTelefono(view.txt_telefono.getText());
                if(cons.actualizarCliente(cli)){
                    JOptionPane.showMessageDialog(null, "Cliente actualizado exitosamente");
                    limpiar();
                }else{
                    JOptionPane.showMessageDialog(null, "Error al actualizar");
                }
            }else{
                JOptionPane.showMessageDialog(null, "No deje campos vacios");
            }
        }else if(e.getSource()==view.btn_eliminar){
            if(!view.txt_id.getText().equals("")){
                cli = new Cliente();
                cli.setId(Integer.parseInt(view.txt_id.getText()));
                if(cons.borrarCliente(cli)){
                    JOptionPane.showMessageDialog(null, "Cliente eliminado exitosamente");
                    limpiar();
                }else{
                    JOptionPane.showMessageDialog(null, "Error al eliminar");
                }
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
                view.txt_apaterno.setText(String.valueOf(view.tbl_usuarios.getValueAt(fila, 2)));
                view.txt_amaterno.setText(String.valueOf(view.tbl_usuarios.getValueAt(fila, 3)));
                view.txt_direccion.setText(String.valueOf(view.tbl_usuarios.getValueAt(fila, 4)));
                view.txt_telefono.setText(String.valueOf(view.tbl_usuarios.getValueAt(fila, 5)));
            }else{
                JOptionPane.showMessageDialog(null, "No se ha seleccionado alguna fila de la tabla");
            }
        }
    }
    
    public void limpiar(){
        view.txt_id.setText(null);
        view.txt_nombre.setText(null);
        view.txt_apaterno.setText(null);
        view.txt_amaterno.setText(null);
        view.txt_direccion.setText(null);
        view.txt_telefono.setText(null);
    }
}