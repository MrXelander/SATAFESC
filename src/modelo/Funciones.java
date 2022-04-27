package modelo;


import java.text.SimpleDateFormat;
import java.util.Calendar;
/**
 *
 * @author aleja
 */
public class Funciones {
    public String obtenerFecha(){
        Calendar c = Calendar.getInstance();
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        return String.valueOf(formato.format(c.getTime()));
    }
    
    public int obtenerAnio(){
        Calendar c = Calendar.getInstance();
        return c.get(Calendar.YEAR);
    }
    
    public double puntosAPesos(int pts){
        return pts * 0.1;
    }
    
    public int contador(String cad, char car){
        int cont = 0;
        char[] arr = cad.toCharArray();
        for(int i=0;i<arr.length;i++){
            if(arr[i]==car){
                cont++;
            }
        }
        return cont;
    }
    
    public String obtenerHora(){
        Calendar c = Calendar.getInstance();
        SimpleDateFormat formato = new SimpleDateFormat("HH:mm:ss");
        return String.valueOf(formato.format(c.getTime()));
    }
}
