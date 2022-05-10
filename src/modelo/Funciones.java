package modelo;


import java.text.SimpleDateFormat;
import java.util.Calendar;
/**
 *
 * @author aleja
 */
public class Funciones {
    public static String obtenerFecha(){
        Calendar c = Calendar.getInstance();
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        return String.valueOf(formato.format(c.getTime()));
    }
    
    public static  int obtenerAnio(){
        Calendar c = Calendar.getInstance();
        return c.get(Calendar.YEAR);
    }
    
    public static double puntosAPesos(int pts){
        return pts * 0.1;
    }
    
    public static int contador(String cad, char car){
        int cont = 0;
        char[] arr = cad.toCharArray();
        for(int i=0;i<arr.length;i++){
            if(arr[i]==car){
                cont++;
            }
        }
        return cont;
    }
    
    public static String obtenerHora(){
        Calendar c = Calendar.getInstance();
        SimpleDateFormat formato = new SimpleDateFormat("HH:mm:ss");
        return String.valueOf(formato.format(c.getTime()));
    }
    
    public static boolean stockProductos(Producto pro){
        int existencias = pro.getExistencias();
        
        return existencias==0;
    }
    
    public static boolean caducidad(Producto pro){
        String caducidad = pro.getCaducidad();
        String fecha = obtenerFecha();
        String cad[] = caducidad.split("-");
        String hoy[] = fecha.split("-");
        
        int anio1 = Integer.parseInt(cad[0]);
        int anio2 = Integer.parseInt(hoy[0]);
        if(anio1<=anio2){
            int mes1 = Integer.parseInt(cad[1]);
            int mes2 = Integer.parseInt(hoy[1]);
            if(mes1<=mes2 || anio2>anio1){
                return true;
            }
        }
        return false;
    }
}
