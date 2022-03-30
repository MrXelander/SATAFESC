package modelo;

import java.sql.*;

/**
 *
 * @author aleja
 * 
 * NOTA:
 * 1. AQUI SE HACEN TODAS LAS CONSULTAS A LA BASE DE DATOS, TRATEMOS DE MANTENER EL MISMO ORDEN
 * 2. Casi todas las funciones deberan devolver un boolean
 */
public class Consultas extends Conexion{
    public boolean login(Usuario usr){ //Funcion de login
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConexion();
        
        String sql = "SELECT Id_Empleado, Usuario, Contrasena, Nivel FROM empleados WHERE Usuario = ?";
        try{
            ps = con.prepareStatement(sql);
            ps.setString(1, usr.getUsuario());
            rs = ps.executeQuery();
            
            if(rs.next()){
                if(usr.getPassword().equals(rs.getString(3))){
                    usr.setId(rs.getInt(1));
                    usr.setTipo(rs.getString(4));
                    return true;
                }else{
                    return false;
                }
            }
            return false;
        }catch(SQLException e){
            System.err.println(e);
            return false;
        }
    }
    
    
    
    
    
    
    
    
    
    
    
}