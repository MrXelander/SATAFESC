package modelo;

import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author aleja
 * 
 * NOTA:
 * 1. AQUI SE HACEN TODAS LAS CONSULTAS A LA BASE DE DATOS, TRATEMOS DE MANTENER EL MISMO ORDEN
 * 2. Casi todas las funciones deberan devolver un Boolean
 */
public class Consultas extends Conexion{
    public static void cerrarConsulta(ResultSet rs, PreparedStatement ps, Connection con){ //Funcion que agiliza el cerrar consultas para no repetir el mismo codigo al final de cada consulta
        if (rs!=null){
            try{
                rs.close();
            }catch(SQLException e){
                System.err.println(e);
            }
        }
        if (ps!=null){
            try{
                ps.close();
            }catch (SQLException e){
                System.err.println(e);
            }
        }
        if (con!=null){
            try{
                con.close();
            }catch (SQLException e){
                System.err.println(e);
            }
        }
    }
    
    public ArrayList<String> listaCategorias(){ //Funcion que retorna una lista de todas las categorias existentes
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConexion();
        ArrayList<String> lista = new ArrayList();
        
        String sql = "SELECT * FROM `categorias`;";
        try{
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while(rs.next()){
                lista.add(rs.getString(2));
            }
            return lista;
        }catch(SQLException e){
            System.err.println(e);
            return null;
        }finally{
            cerrarConsulta(rs, ps, con);
        }
    }
    
    public ArrayList<String> listaClientes(){ //Funcion para obtener la lista de todos los clientes registrados
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConexion();
        ArrayList<String> lista = new ArrayList();
        
        String sql = "SELECT * FROM `clientes`;";
        try{
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while(rs.next()){
                lista.add(rs.getString(2));
            }
            return lista;
        }catch(SQLException e){
            System.err.println(e);
            return null;
        }
    }
    
    public int empleadoID(String emp){
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConexion();
        
        String sql = "SELECT * FROM `empleados` WHERE Usuario = ?;";
        try{
            ps = con.prepareStatement(sql);
            ps.setString(1, emp);
            rs = ps.executeQuery();
            
            while(rs.next()){
                return rs.getInt(1);
            }
            return 0;
        }catch(SQLException e){
            System.err.println(e);
            return 0;
        }finally{
            cerrarConsulta(rs, ps, con);
        }
    }
    
    public int categoriaID(String cat){ //Funcion para encontrar el ID de una categoria
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConexion();
        
        String sql = "SELECT * FROM categorias WHERE Categoria = ?;";
        try{
            ps = con.prepareStatement(sql);
            ps.setString(1, cat);
            rs = ps.executeQuery();
            
            while(rs.next()){
                return rs.getInt(1);
            }
            return 0;
        }catch(SQLException e){
            System.err.println(e);
            return 0;
        }finally{
            cerrarConsulta(rs, ps, con);
        }
    }
    
    public int clienteID(String cli){
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConexion();
        
        String sql = "SELECT * FROM clientes WHERE Nombre = ?;";
        try{
            ps = con.prepareStatement(sql);
            ps.setString(1, cli);
            rs = ps.executeQuery();
            
            while(rs.next()){
                return rs.getInt(1);
            }
            return 0;
        }catch(SQLException e){
            System.err.println(e);
            return 0;
        }finally{
            cerrarConsulta(rs, ps, con);
        }
    }
    
    public String categoriaNombre(int cat){ //Funcion para encontrar el nombre de una categoria
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConexion();
        
        String sql = "SELECT * FROM categorias WHERE Id_Categoria = ?;";
        try{
            ps = con.prepareStatement(sql);
            ps.setInt(1, cat);
            rs = ps.executeQuery();
            
            while(rs.next()){
                return rs.getString(2);
            }
            return null;
        }catch(SQLException e){
            System.err.println(e);
            return null;
        }finally{
            cerrarConsulta(rs, ps, con);
        }
    }
    
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
        }finally{
            cerrarConsulta(rs, ps, con);
        }
    }
    
    public boolean agregarProducto(Producto pro){ //Funcion para agregar productos a la base de datos
        PreparedStatement ps = null;
        Connection con = getConexion();
        
        String sql = "INSERT INTO `producto` (`Id_Producto`, `Id_Categoria`, `Nom_Producto`, `Codigo_Barras`, `Precio`, `Caducidad`, `Existencias`, `Costo`) VALUES (NULL, ?, ?, ?, ?, ?, ?, ?)";
        try{
            ps = con.prepareStatement(sql);
            ps.setInt(1, pro.getId_categoria());
            ps.setString(2, pro.getNombre());
            ps.setString(3, pro.getCodigo_barras());
            ps.setDouble(4, pro.getPrecio());
            ps.setString(5, pro.getCaducidad());
            ps.setInt(6, pro.getExistencias());
            ps.setDouble(7, pro.getCosto());
            ps.execute();
            return true;
        }catch(SQLException e){
            System.err.println(e);
            return false;
        }finally{
            cerrarConsulta(null, ps, con);
        }
    }
    
    public boolean buscarProducto(String sql2, Producto pro){
        PreparedStatement ps = null;
        Connection con = getConexion();
        ResultSet rs = null;
        
        String sql = "SELECT * FROM producto" + sql2;
        try{
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while(rs.next()){
                pro.setId(rs.getInt(1));
                pro.setId_categoria(rs.getInt(2));
                pro.setNombre(rs.getString(3));
                pro.setCodigo_barras(rs.getString(4));
                pro.setPrecio(rs.getDouble(5));
                pro.setCaducidad(rs.getString(6));
                pro.setExistencias(7);
                pro.setCosto(rs.getDouble(8));
                pro.setCategoria(categoriaNombre(pro.getId_categoria()));
                return true;
            }
            return false;
        }catch(SQLException e){
            System.err.println(e);
            return false;
        }
    }
    
    /*public DefaultTableModel buscarProductoTabla(String sql2){ //Funcion que retorna un modelo de tabla para las busquedas de productos
        PreparedStatement ps = null;
        Connection con = getConexion();
        ResultSet rs = null;
        
        String sql = "SELECT P.Id_Producto, C.Categoria, P.Nom_Producto, P.Codigo_Barras, P.Precio, P.Caducidad, P.Existencias, Costo FROM producto P LEFT JOIN categorias C ON C.Id_Categoria = P.Id_Categoria" + sql2;
        try{
            DefaultTableModel modeloo = new DefaultTableModel();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            
            ResultSetMetaData rsMd = rs.getMetaData();
            int columnas = rsMd.getColumnCount();
            
            modeloo.addColumn("ID");
            modeloo.addColumn("Categoria");
            modeloo.addColumn("Nombre");
            modeloo.addColumn("Codigo");
            modeloo.addColumn("Precio");
            modeloo.addColumn("Caducidad");
            modeloo.addColumn("Existencias");
            modeloo.addColumn("Costo");
            
            while(rs.next()){
                Object[] filas = new Object[columnas];
                for(int i=0; i<columnas; i++){
                    filas[i] = rs.getObject(i+1);
                }
                modeloo.addRow(filas);
            }
            return modeloo;
        }catch(SQLException e){
            return null;
        }finally{
            cerrarConsulta(rs, ps, con);
        }
    }*/
    
    public boolean actualizarProducto(Producto pro){ //Funcion para actualizar productos de la base de datos
        PreparedStatement ps = null;
        Connection con = getConexion();
        
        String sql = "UPDATE producto SET Nom_Producto = ?, Id_Categoria = ?, Codigo_Barras = ?, Precio = ?, Caducidad = ?, Existencias = ?, Costo = ? WHERE Id_Producto = ?";
        try{
            ps = con.prepareCall(sql);
            ps.setString(1, pro.getNombre());
            ps.setInt(2, pro.getId_categoria());
            ps.setString(3, pro.getCodigo_barras());
            ps.setDouble(4, pro.getPrecio());
            ps.setString(5, pro.getCaducidad());
            ps.setInt(6, pro.getExistencias());
            ps.setDouble(7, pro.getCosto());
            ps.setInt(8, pro.getId());
            ps.execute();
            return true;
        }catch(SQLException e){
            System.err.println(e);
            return false;
        }finally{
            cerrarConsulta(null, ps, con);
        }
    }
    
    public boolean borrarProducto(Producto pro){ //Funcion para borrar productos de la base de datos
        PreparedStatement ps = null;
        Connection con = getConexion();
        
        String sql = "DELETE FROM `producto` WHERE `producto`.`Id_Producto` = ?";
        try{
            ps = con.prepareStatement(sql);
            ps.setInt(1, pro.getId());
            ps.execute();
            return true;
        }catch(SQLException e){
            System.err.println(e);
            return false;
        }finally{
            cerrarConsulta(null, ps, con);
        }
    }
    
    /*public DefaultTableModel buscarProductoVenta(String nom){
        PreparedStatement ps = null;
        Connection con = getConexion();
        ResultSet rs = null;
        
        String sql = "SELECT Id_Producto, Nom_Producto, Precio FROM producto WHERE Nom_Producto LIKE '%" + nom + "%'";
        try{
            DefaultTableModel modeloo = new DefaultTableModel();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            
            ResultSetMetaData rsMd = rs.getMetaData();
            int columnas = rsMd.getColumnCount();
            
            modeloo.addColumn("ID");
            modeloo.addColumn("Nombre");
            modeloo.addColumn("Precio");
            
            while(rs.next()){
                Object[] filas = new Object[columnas];
                for(int i=0; i<columnas; i++){
                    filas[i] = rs.getObject(i+1);
                }
                modeloo.addRow(filas);
            }
            return modeloo;
        }catch(SQLException e){
            return null;
        }finally{
            cerrarConsulta(rs, ps, con);
        }
    }*/
    
    public int registrarVenta(Venta ven){
        PreparedStatement ps = null;
        Connection con = getConexion();
        ResultSet rs = null;
        int id = 0;
        
        String sql = "INSERT INTO `ventas` (`Id_Venta`, `Id_Empleado`, `Id_Cliente`, `Metodo_Pago`, `Fecha`, `Hora`, `Descuento`, `Monto_Total`) VALUES (NULL, ?, ?, ?, ?, ?, ?, ?)";
        try{
            ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, ven.getId_empleado());
            ps.setInt(2, ven.getId_cliente());
            ps.setString(3, ven.getMetodo_pago());
            ps.setString(4, ven.getFecha());
            ps.setString(5, ven.getHora());
            ps.setDouble(6, ven.getDescuento());
            ps.setDouble(7, ven.getMonto_total());
            ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            if(rs.next()){
                id = rs.getInt(1);
            }
            return id;
        }catch(SQLException e){
            System.err.println(e);
        }finally{
            cerrarConsulta(rs, ps, con);
        }
        return id;
    }
    
    public boolean registrarVentaDetalle(VentaDetalle vd){
        PreparedStatement ps = null;
        Connection con = getConexion();
        
        String sql = "INSERT INTO `detalle_ventas` (`Id_detalleventa`, `Id_Venta`, `Id_Producto`, `Cantidad`, `Total`) VALUES (NULL, ?, ?, ?, ?)";
        try{
            ps = con.prepareStatement(sql);
            ps.setInt(1, vd.getId_venta());
            ps.setInt(2, vd.getId_producto());
            ps.setDouble(3, vd.getCantidad());
            ps.setDouble(4, vd.getTotal());
            ps.execute();
            return true;
        }catch(SQLException e){
            System.err.println(e);
            return false;
        }finally{
            cerrarConsulta(null, ps, con);
        }
    }
    
    public boolean actualizarExistencias(Producto pro){
        PreparedStatement ps = null;
        Connection con = getConexion();
        
        String sql = "UPDATE `producto` SET `Existencias` = ? WHERE `producto`.`Id_Producto` = ?;";
        try{
            ps = con.prepareStatement(sql);
            ps.setInt(1, pro.getExistencias());
            ps.setInt(2, pro.getId());
            ps.execute();
            return true;
        }catch(SQLException e){
            System.err.println(e);
            return false;
        }finally{
            cerrarConsulta(null, ps, con);
        }
    }
}