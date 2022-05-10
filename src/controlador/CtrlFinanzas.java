package controlador;

import vista.VistaFinanzas;
import modelo.Consultas;
import modelo.Funciones;
import java.awt.event.*;
/**
 *
 * @author aleja
 */
public class CtrlFinanzas  implements ActionListener{
    private VistaFinanzas view;
    private Consultas cons;
    
    public CtrlFinanzas(VistaFinanzas view, Consultas cons){
        this.view = view;
        this.cons = cons;
        this.view.btn_buscar.addActionListener(this);
    }
    
    public void init(){
        int anio = Funciones.obtenerAnio();
        view.cb_anio.addItem(String.valueOf(anio));
        view.cb_anio.addItem(String.valueOf(anio-1));
        view.cb_anio.addItem(String.valueOf(anio-2));
        view.lbl_inventario.setText("$ " + cons.valorInventario());
        view.lbl_caja.setVisible(false);
        view.lbl_bancos.setVisible(false);
        view.lbl_costos.setVisible(false);
        view.lbl_descuentos.setVisible(false);
        view.lbl_perdidas.setVisible(false);
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource()==view.btn_buscar){
            String fecha = view.cb_anio.getSelectedItem().toString() + "-" +view.cb_mes.getSelectedItem().toString() + "-%";
            view.lbl_caja.setText("$ " + cons.dineroEfectivo(fecha));
            view.lbl_bancos.setText("$ " + cons.dineroBancos(fecha));
            view.lbl_costos.setText("$ " + cons.costoVentas(fecha));
            view.lbl_descuentos.setText("$ " + cons.descuentos(fecha));
            view.lbl_perdidas.setText("$ " + cons.perdidas(fecha));
            
            view.lbl_caja.setVisible(true);
            view.lbl_bancos.setVisible(true);
            view.lbl_costos.setVisible(true);
            view.lbl_descuentos.setVisible(true);
            view.lbl_perdidas.setVisible(true);
        }
    }
}
