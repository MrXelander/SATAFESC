package satafesc;

/**
 *
 * @author aleja
 */
public class App {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        modelo.Conexion con = new modelo.Conexion();
        con.getConexion();      //Prueba de conexion a base de datos
        
        java.util.Scanner sc = new java.util.Scanner(System.in);
        
        modelo.Usuario usr = new modelo.Usuario();
        modelo.Consultas cons = new modelo.Consultas();
        
        System.out.println("Ingrese el usuario");
        usr.setUsuario(sc.nextLine());
        
        System.out.println("Ingrese la contraseña");
        usr.setPassword(modelo.Hash.getHash(sc.nextLine(), "SHA1"));
        
        if(cons.login(usr)){            //Prueba de sistema login antes de hacer la interfaz
            System.out.println("Bienvenido al sistema");
        }else{
            System.out.println("Datos incorrectos");
        }
    }
    
}
