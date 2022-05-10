package modelo;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author aleja
 */
public class Conexion {
    private final String driver = "com.mysql.cj.jdbc.Driver";
    private final String bd = "tienda";
    private final String user = "root";
    private final String password = "";
    private final String url = "jdbc:mysql://localhost:3306/" + bd;
    Connection con = null;
    
    public Connection getConexion(){
        try{
            Class.forName(driver);
            con = DriverManager.getConnection(this.url, this.user, this.password);
        }catch(SQLException e){
            System.err.println(e);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return con;
    }
}