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
    
    public int empleadoID(String emp){ //Funcion que nos facilita obtener el id de un usuario/empleado
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
    
    public int clienteID(String cli){ //funcion que nos facilita obtener el nombre de un cliente
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
        
        String sql = "SELECT * FROM empleados WHERE Usuario = ?";
        try{
            ps = con.prepareStatement(sql);
            ps.setString(1, usr.getUsuario());
            rs = ps.executeQuery();
            
            if(rs.next()){
                if(usr.getPassword().equals(rs.getString(3))){
                    usr.setId(rs.getInt(1));
                    usr.setTipo(rs.getString(4));
                    usr.setNombre(rs.getString(5));
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
    
    public boolean buscarProducto(String sql2, Producto pro){ //buscamos un producto, edita el objeto producto enviado como parametro y el String es para poner un filtro a la consulta
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
                pro.setExistencias(rs.getInt(7));
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
    
    //Primer funcion de buscar producto pero fue rechazada debido a que no es muy optima, ademas devuelve una tabla, al obtener un objeto es mas facil trabajar y aplicar la funcion en otras partes del codigo
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
    
    //Esta funcion se rechazo debido a que se tenian 2 funciones para buscar y la nueva se aplica en el crud de inventario y al buscar productos en la interfaz de ventas
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
    
    public int registrarVenta(Venta ven){ //registramos una venta completa
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
    
    public boolean registrarVentaDetalle(VentaDetalle vd){ //aqui se registra detalle a detalle las ventas de ccada producto
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
    
    public boolean actualizarExistencias(Producto pro){ //Cuando se realiza una venta hay que modificar las existencias
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
    
    public boolean agregarUsuario(Usuario usr){ //Funcion para agregar usuario a la base de datos
        PreparedStatement ps = null;
        Connection con = getConexion();
        
        String sql = "INSERT INTO `empleados` (`Id_Empleado`, `Usuario`, `Contrasena`, `Nivel`, `Nombre`) VALUES (NULL, ?, ?, ?, ?)";
        try{
            ps = con.prepareStatement(sql);
            ps.setString(1, usr.getUsuario());
            ps.setString(2, usr.getPassword());
            ps.setString(3, usr.getTipo());
            ps.setString(4, usr.getNombre());
            ps.execute();
            
            return true;
        }catch(SQLException e){
            System.err.println(e);
            return false;
        }finally{
            cerrarConsulta(null, ps, con);
        }
    }
    
    public boolean buscarUsuario(String sql2, Usuario usr){ //funcion para buscar usuarios que de igual manera solo edita el objeto del parametro y usa un filtro con el String
        PreparedStatement ps = null;
        Connection con = getConexion();
        ResultSet rs = null;
        
        String sql = "SELECT * FROM `empleados`" + sql2;
        try{
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while(rs.next()){
                usr.setId(rs.getInt(1));
                usr.setUsuario(rs.getString(2));
                usr.setPassword(rs.getString(3));
                usr.setTipo(rs.getString(4));
                usr.setNombre(rs.getString(5));
                return true;
            }
            return false;
        }catch(SQLException e){
            System.err.println(e);
            return false;
        }finally{
            cerrarConsulta(rs, ps, con);
        }
    }
    
    public boolean actualizarUsuario(Usuario usr){ //Funcion para modificar los datos de un usuario en la base de datos
        PreparedStatement ps = null;
        Connection con = getConexion();
        
        String sql = "UPDATE `empleados` SET `Usuario` = ?, `Contrasena` = ?, `Nivel` = ?, `Nombre` = ? WHERE `Id_Empleado` = ?";
        try{
            ps = con.prepareStatement(sql);
            ps.setString(1, usr.getUsuario());
            ps.setString(2, usr.getPassword());
            ps.setString(3, usr.getTipo());
            ps.setString(4, usr.getNombre());
            ps.setInt(5, usr.getId());
            ps.execute();
            return true;
        }catch(SQLException e){
            System.err.println(e);
            return false;
        }finally{
            cerrarConsulta(null, ps, con);
        }
    }
    
    public boolean borrarUsuario(Usuario usr){ //Funcion para borrar un usuario de la base de datos
        PreparedStatement ps = null;
        Connection con = getConexion();
        
        String sql = "DELETE FROM `empleados` WHERE `empleados`.`Id_Empleado` = ?";
        try{
            ps = con.prepareStatement(sql);
            ps.setInt(1, usr.getId());
            ps.execute();
            return true;
        }catch(SQLException e){
            System.err.println(e);
            return false;
        }finally{
            cerrarConsulta(null, ps, con);
        }
    }
    
    public double valorInventario(){
        PreparedStatement ps = null;
        Connection con = getConexion();
        ResultSet rs = null;
        
        String sql = "SELECT SUM(Existencias*Costo) AS Total FROM `producto`;";
        try{
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            
            if(rs.next()){
                return rs.getDouble(1);
            }
            return 0;
        }catch(SQLException e){
            System.err.println(e);
            return 0;
        }finally{
            cerrarConsulta(rs, ps, con);
        }
    }
    
    public double dineroEfectivo(String fecha){
        PreparedStatement ps = null;
        Connection con = getConexion();
        ResultSet rs = null;
        
        String sql = "SELECT IFNULL(SUM(Monto_Total), 0) AS Total FROM `ventas` WHERE Metodo_Pago = 'Efectivo' AND Fecha LIKE ?;";
        try{
            ps = con.prepareStatement(sql);
            ps.setString(1, fecha);
            rs = ps.executeQuery();
            
            if(rs.next()){
                return rs.getDouble(1);
            }
            return 0;
        }catch(SQLException e){
            System.err.println(e);
            return 0;
        }finally{
            cerrarConsulta(rs, ps, con);
        }
    }
    
    public double dineroBancos(String fecha){
        PreparedStatement ps = null;
        Connection con = getConexion();
        ResultSet rs = null;
        
        String sql = "SELECT IFNULL(SUM(Monto_Total), 0) AS Total FROM `ventas` WHERE Metodo_Pago = 'tarjeta' AND Fecha LIKE ?;";
        try{
            ps = con.prepareStatement(sql);
            ps.setString(1, fecha);
            rs = ps.executeQuery();
            
            if(rs.next()){
                return rs.getDouble(1);
            }
            return 0;
        }catch(SQLException e){
            System.err.println(e);
            return 0;
        }finally{
            cerrarConsulta(rs, ps, con);
        }
    }
    
    public double descuentos(String fecha){
        PreparedStatement ps = null;
        Connection con = getConexion();
        ResultSet rs = null;
        
        String sql = "SELECT IFNULL(SUM(Descuento), 0) AS Total FROM `ventas` WHERE Fecha LIKE ?;";
        try{
            ps = con.prepareStatement(sql);
            ps.setString(1, fecha);
            rs = ps.executeQuery();
            
            if(rs.next()){
                return rs.getDouble(1);
            }
            return 0;
        }catch(SQLException e){
            System.err.println(e);
            return 0;
        }finally{
            cerrarConsulta(rs, ps, con);
        }
    }
    
    public double costoVentas(String fecha){
        PreparedStatement ps = null;
        Connection con = getConexion();
        ResultSet rs = null;
        
        String sql = "SELECT SUM(C.Costo*B.Cantidad) AS costo FROM (SELECT Id_Venta, Fecha FROM ventas) A LEFT JOIN (SELECT Id_Venta, Id_Producto, Cantidad FROM detalle_ventas) B ON A.Id_Venta = B.Id_Venta LEFT JOIN (SELECT Id_Producto, Costo FROM producto) C ON B.Id_Producto = C.Id_Producto WHERE A.Fecha LIKE ?";
        try{
            ps = con.prepareStatement(sql);
            ps.setString(1, fecha);
            rs = ps.executeQuery();
            
            if(rs.next()){
                return rs.getDouble(1);
            }
            return 0;
        }catch(SQLException e){
            System.err.println(e);
            return 0;
        }finally{
            cerrarConsulta(rs, ps, con);
        }
    }
    
    public double perdidas(String fecha){
        PreparedStatement ps = null;
        Connection con = getConexion();
        ResultSet rs = null;
        
        String sql = "SELECT IFNULL(SUM(Monto_Total), 0) AS Total FROM `ventas` WHERE Metodo_Pago = 'perdida' AND Fecha LIKE ?;";
        try{
            ps = con.prepareStatement(sql);
            ps.setString(1, fecha);
            rs = ps.executeQuery();
            
            if(rs.next()){
                return rs.getDouble(1);
            }
            return 0;
        }catch(SQLException e){
            System.err.println(e);
            return 0;
        }finally{
            cerrarConsulta(rs, ps, con);
        }
    }
    
    public ArrayList<String> listaIdProductos(){ //Funcion para obtener la lista de todos los id de producto para facilitar la funcion de stock de productos y caducidad de productos
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConexion();
        ArrayList<String> lista = new ArrayList();
        
        String sql = "SELECT * FROM `producto`;";
        try{
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while(rs.next()){
                lista.add(rs.getString(1));
            }
            return lista;
        }catch(SQLException e){
            System.err.println(e);
            return null;
        }
    }
    
    public boolean stockProductos(Producto pro){
        PreparedStatement ps = null;
        Connection con = getConexion();
        ResultSet rs = null;
        
        String sql = "SELECT * FROM `producto` WHERE Id_Producto = ?";
        try{
            ps = con.prepareStatement(sql);
            ps.setInt(1, pro.getId());
            rs = ps.executeQuery();
            
            while(rs.next()){
                if(rs.getDouble(7)==0){
                    return true;
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
    
    /*public boolean caducidadProductos(Producto pro){
        PreparedStatement ps = null;
        Connection con = getConexion();
        ResultSet rs = null;
        
        String sql = "SELECT * FROM `producto` WHERE Id_Producto = ?";
        try{
            ps = con.prepareStatement(sql);
            ps.setInt(1, pro.getId());
            rs = ps.executeQuery();
            
            if(rs.next()){
                pro.setCaducidad(rs.getString(6));
            }
            
            return Funciones.caducidad(pro);
        }catch(SQLException e){
            System.err.println(e);
            return false;
        }finally{
            cerrarConsulta(rs, ps, con);
        }
    }*/
    
    public Object[] masVendido(String fecha){
        PreparedStatement ps = null;
        Connection con = getConexion();
        ResultSet rs = null;
        
        String sql = "SELECT A.Id_Producto, A.Nom_Producto, MAX(B.suma) FROM (SELECT Id_Producto, Nom_Producto FROM producto) A INNER JOIN (SELECT Id_Producto, Id_Venta, SUM(Cantidad) AS suma FROM detalle_ventas GROUP BY Id_Producto) B ON A.Id_Producto = B.Id_Producto INNER JOIN (SELECT Id_Venta, Fecha FROM ventas) C ON C.Id_Venta = B.Id_Venta WHERE C.Fecha LIKE ?";
        try{
            ps = con.prepareStatement(sql);
            ps.setString(1, fecha);
            rs = ps.executeQuery();
            
            if(rs.next()){
                return new Object[]{rs.getInt(1), rs.getString(2), rs.getInt(3)};
            }
            return null;
        }catch(SQLException e){
            System.err.println(e);
            return null;
        }finally{
            cerrarConsulta(rs, ps, con);
        }
    }
    
    public boolean agregarCliente(Cliente cli){ //Funcion para agregar clientes a la base de datos
        PreparedStatement ps = null;
        Connection con = getConexion();
        
        String sql = "INSERT INTO `clientes` (`Id_Cliente`, `Nombre`, `A_Paterno`, `A_Materno`, `Direccion`, `Telefono`) VALUES (NULL, ?, ?, ?, ?, ?)";
        try{
            ps = con.prepareStatement(sql);
            ps.setString(1, cli.getNombre());
            ps.setString(2, cli.getA_paterno());
            ps.setString(3, cli.getA_materno());
            ps.setString(4, cli.getDireccion());
            ps.setString(5, cli.getTelefono());
            ps.execute();
            return true;
        }catch(SQLException e){
            System.err.println(e);
            return false;
        }finally{
            cerrarConsulta(null, ps, con);
        }
    }
    
    public boolean buscarCliente(String sql2, Cliente cli){ //buscamos un cliente, edita el objeto producto enviado como parametro y el String es para poner un filtro a la consulta
        PreparedStatement ps = null;
        Connection con = getConexion();
        ResultSet rs = null;
        
        String sql = "SELECT * FROM clientes" + sql2;
        try{
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while(rs.next()){
                cli.setId(rs.getInt(1));
                cli.setNombre(rs.getString(2));
                cli.setA_paterno(rs.getString(3));
                cli.setA_materno(rs.getString(4));
                cli.setDireccion(rs.getString(5));
                cli.setTelefono(rs.getString(6));
                return true;
            }
            return false;
        }catch(SQLException e){
            System.err.println(e);
            return false;
        }
    }
    
    public boolean actualizarCliente(Cliente cli){ //Funcion para actualizar clientes de la base de datos
        PreparedStatement ps = null;
        Connection con = getConexion();
        
        String sql = "UPDATE clientes SET Nombre = ?, A_Paterno = ?, A_Materno = ?, Direccion = ?, Telefono = ? WHERE Id_Cliente = ?";
        try{
            ps = con.prepareCall(sql);
            ps.setString(1, cli.getNombre());
            ps.setString(2, cli.getA_paterno());
            ps.setString(3, cli.getA_materno());
            ps.setString(4, cli.getDireccion());
            ps.setString(5, cli.getTelefono());
            ps.setInt(6, cli.getId());
            ps.execute();
            return true;
        }catch(SQLException e){
            System.err.println(e);
            return false;
        }finally{
            cerrarConsulta(null, ps, con);
        }
    }
    
    public boolean borrarCliente(Cliente cli){ //Funcion para borrar clientes de la base de datos
        PreparedStatement ps = null;
        Connection con = getConexion();
        
        String sql = "DELETE FROM `clientes` WHERE `clientes`.`Id_Cliente` = ?";
        try{
            ps = con.prepareStatement(sql);
            ps.setInt(1, cli.getId());
            ps.execute();
            return true;
        }catch(SQLException e){
            System.err.println(e);
            return false;
        }finally{
            cerrarConsulta(null, ps, con);
        }
    }
    
    public boolean insertarRecompensas(Venta ven){
        PreparedStatement ps = null;
        Connection con = getConexion();
        
        String sql = "INSERT INTO `recompensas` (`Id_Recompensa`, `Id_Cliente`, `Puntos`, `Fecha`, `Expiracion`) VALUES (NULL, ?, ?, ?, ?)";
        try{
            ps = con.prepareStatement(sql);
            ps.setInt(1, ven.getId_cliente());
            ps.setInt(2, Funciones.calcularPts(ven.getMonto_total()));
            ps.setString(3, Funciones.obtenerFecha());
            ps.setString(4, Funciones.obtenerExpiracion());
            
            ps.execute();
            return true;
        }catch(SQLException e){
            System.err.println(e);
            return false;
        }finally{
            cerrarConsulta(null, ps, con);
        }
    }
    
    public int puntosDisponibles(int id){
        PreparedStatement ps = null;
        Connection con = getConexion();
        ResultSet rs = null;
        
        String sql = "SELECT (A.total - B.tot) AS suma FROM (SELECT Id_Cliente, SUM(Puntos) AS total FROM `recompensas` GROUP BY Id_Cliente) A INNER JOIN (SELECT Id_Cliente, SUM(Descuento*10) AS tot FROM `ventas` GROUP BY Id_Cliente) B ON A.Id_Cliente = B.Id_Cliente WHERE A.Id_Cliente = ?";
        try{
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
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
}