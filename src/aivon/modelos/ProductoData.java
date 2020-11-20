/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aivon.modelos;

import aivon.entidades.Producto;
import aivon.entidades.Revendedor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Mario
 */
public class ProductoData {
    private Connection con;

    public ProductoData(Conexion conexion) {
        con = conexion.getConnection();
    }
    //--------------------------ALTA----------------------------------------------//////
    

    public void altaProducto (Producto producto){
        
        String pre_instruccion;
        pre_instruccion="INSERT INTO producto(nombre, uso, tamaño_cm3, costo, costo_publico, estrellas) VALUES (?,?,?,?,?,?);";
        
        try{
        
        PreparedStatement instruccion;
        instruccion = con.prepareStatement(pre_instruccion, Statement.RETURN_GENERATED_KEYS);
        
        instruccion.setString(1, producto.getNombre() );
        instruccion.setString(2, producto.getUso());
        instruccion.setInt(3, producto.getTamaño());
        instruccion.setDouble(4, producto.getCosto());
        instruccion.setDouble(5, producto.getCosto_publico());
        instruccion.setInt(6, producto.getEstrellas());
                
        
        instruccion.executeUpdate();
        
        try (ResultSet resultado = instruccion.getGeneratedKeys()) {
                if (resultado.next()) {
                   producto.setId_producto(resultado.getInt(1));
                    System.out.println("Producto cargado");
                    JOptionPane.showMessageDialog(null, "Producto cargado");
                } else {
                    JOptionPane.showMessageDialog(null, "No pudo obtener id");
                    System.out.println("No pudo obtener id");
                }
            }

            instruccion.close();
        }catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al guardar Producto");
            System.out.println(ex.getMessage());
        }
       
    }
    
     //---------------------------BAJA------------------------------------------------//////
    
    //Por id
    
//    public void bajaProducto(int id) {
//
//        try {
//            Statement statement = con.createStatement();
//            int celAfectadas = statement.executeUpdate("DELETE FROM producto WHERE id_producto=" + id + ";");
//
//            if (celAfectadas > 0) {
//                JOptionPane.showMessageDialog(null, "Producto Borrado");
//                System.out.println("Producto Borrado");
//            } else {
//                JOptionPane.showMessageDialog(null, "El Registro con id " + id + " que pretende borrar no existe!!");
//                System.out.println("El Registro con id " + id + " que pretende borrar no existe!!");
//            }
//            statement.close();
//        } catch (SQLException ex) {
//            JOptionPane.showMessageDialog(null, "Error al borrar producto por Id");
//            System.out.println("Error al borrar producto");
//        }
//
//    }
    //Desactivar producto por id
    public void desactivarProducto(int id) {

        try {
            Statement statement = con.createStatement();
            int celAfectadas = statement.executeUpdate("UPDATE producto SET activo=0 WHERE id_producto=" + id + ";");

            if (celAfectadas > 0) {
                JOptionPane.showMessageDialog(null, "Producto dado de baja");
                System.out.println("Producto Borrado");
            } else {
                JOptionPane.showMessageDialog(null, "El Registro con id " + id + " que pretende borrar no existe!!");
                System.out.println("El Registro con id " + id + " que pretende borrar no existe!!");
            }
            statement.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al borrar producto por Id");
            System.out.println("Error al borrar producto");
        }

    }
    
    //Desactivar producto
//    public void bajaProducto(Producto producto) {
//
//        try {
//            Statement statement = con.createStatement();
//            int celAfectadas = statement.executeUpdate("UPDATE producto SET activo=0 WHERE id_producto=" + producto.getId_producto()+ ";");
//
//            if (celAfectadas > 0) {
//                JOptionPane.showMessageDialog(null, "Producto dado de baja");
//                System.out.println("Producto Borrado");
//            } else {
//                JOptionPane.showMessageDialog(null, "El Registro con id " + producto.getId_producto() + " que pretende borrar no existe!!");
//                System.out.println("El Registro con id " + producto.getId_producto() + " que pretende borrar no existe!!");
//            }
//            statement.close();
//        } catch (SQLException ex) {
//            JOptionPane.showMessageDialog(null, "Error al dar de baja producto por Id");
//            System.out.println("Error al dar de baja producto");
//        }
//
//    }
            
    //----------------------Modificacion-----------------------------------------------////
     
     public void modificarProducto(Producto producto) {

        try {
            String pre_instruccion = "UPDATE producto SET nombre=?,uso=?, tamaño_cm3=?,costo=?,costo_publico=?,estrellas=?, activo=? WHERE id_producto=" + producto.getId_producto()+";";
            PreparedStatement instruccion = con.prepareStatement(pre_instruccion);
            instruccion.setString(1, producto.getNombre());
            instruccion.setString(2, producto.getUso());
            instruccion.setInt(3, producto.getTamaño());
            instruccion.setDouble(4, producto.getCosto());
            instruccion.setDouble(5, producto.getCosto_publico());
            instruccion.setInt(6, producto.getEstrellas());
            instruccion.setBoolean(7, producto.isActivo());
            int celAfectadas = instruccion.executeUpdate();
            if (celAfectadas > 0) {
                System.out.println("Producto Modificado");
                JOptionPane.showMessageDialog(null, "Producto Modificado");
            } else {
                System.out.println("El Registro " + producto.getId_producto() + " no pudo ser actualizado");
                JOptionPane.showMessageDialog(null, "El Revendedor no se pudo actualizar");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, "Error al actualizar revendedor");
        }

    }

        
    //----------------------CONSULTA--------------------------------------------//////
     
    //Por Id: 
     
     
     public Producto buscarProducto (int id) {

        Producto producto = null;

        try {
            Statement instruccion = con.createStatement();
            try (ResultSet consulta = instruccion.executeQuery("SELECT * FROM producto WHERE id_producto=" + id + ";")) {
                if (consulta.next()) {
                    producto = new Producto();
                    producto.setId_producto(consulta.getInt("id_producto"));
                    producto.setNombre(consulta.getString("nombre"));
                    producto.setUso(consulta.getString("uso"));
                    producto.setTamaño(consulta.getInt("tamaño_cm3"));
                    producto.setCosto(consulta.getDouble("costo"));
                    producto.setCosto_publico(consulta.getDouble("costo_publico"));
                    producto.setEstrellas(consulta.getInt("estrellas"));
                    producto.setActivo(consulta.getBoolean("activo"));
                    
                } else {
                    JOptionPane.showMessageDialog(null, "No se pudo buscar Producto por id");
                    System.out.println("No se pudo buscar Producto por id");
                }
            }
            instruccion.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al buscar Producto por id");
            System.out.println(ex.getMessage());
        }
        return producto;
    }
     
   
        
    //-------------------Consulta Multiple-----------------------------------------------
     
     public List<Producto> buscarProductos() {

        Producto producto;
        List<Producto> productos = new ArrayList<>();

        try {
            PreparedStatement instruccion = con.prepareStatement("SELECT * FROM producto");
            ResultSet consulta = instruccion.executeQuery();
            
            if (consulta.next()) {
                consulta.beforeFirst();
                while (consulta.next()) {
                    producto= new Producto();
                     producto.setId_producto(consulta.getInt("id_producto"));
                    producto.setNombre(consulta.getString("nombre"));
                    producto.setUso(consulta.getString("uso"));
                    producto.setTamaño(consulta.getInt("tamaño_cm3"));
                    producto.setCosto(consulta.getDouble("costo"));
                    producto.setCosto_publico(consulta.getDouble("costo_publico"));
                    producto.setEstrellas(consulta.getInt("estrellas"));
                    producto.setActivo(consulta.getBoolean("activo"));
                    
                    productos.add(producto);
            }
                JOptionPane.showMessageDialog(null, "Se encontraron los productos");
                System.out.println("Se encontraron los productos");
            } else {
                JOptionPane.showMessageDialog(null, "No se encontraron los productos");
                System.out.println("No se encontraron los productos");
            }
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al obtener los productos");
            System.out.println(ex.getMessage());
        }

        return productos;
    }
    
     public List<Producto> buscarProductosActivos() {

        Producto producto;
        List<Producto> productos = new ArrayList<>();

        try {
            PreparedStatement instruccion = con.prepareStatement("SELECT * FROM producto WHERE activo=1");
            ResultSet consulta = instruccion.executeQuery();
            
            if (consulta.next()) {
                consulta.beforeFirst();
                while (consulta.next()) {
                    producto= new Producto();
                     producto.setId_producto(consulta.getInt("id_producto"));
                    producto.setNombre(consulta.getString("nombre"));
                    producto.setUso(consulta.getString("uso"));
                    producto.setTamaño(consulta.getInt("tamaño_cm3"));
                    producto.setCosto(consulta.getDouble("costo"));
                    producto.setCosto_publico(consulta.getDouble("costo_publico"));
                    producto.setEstrellas(consulta.getInt("estrellas"));
                    
                    productos.add(producto);
            }
                //JOptionPane.showMessageDialog(null, "Se encontraron los productos");
                System.out.println("Se encontraron revdendedores");
            } else {
                JOptionPane.showMessageDialog(null, "No se encontraron los productos");
                System.out.println("No se encontraron los productos");
            }
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al obtener los productos");
            System.out.println(ex.getMessage());
        }

        return productos;
    }
    public List<Producto> buscarProductosInactivos() {

        Producto producto;
        List<Producto> productos = new ArrayList<>();

        try {
            PreparedStatement instruccion = con.prepareStatement("SELECT * FROM producto WHERE activo=0");
            ResultSet consulta = instruccion.executeQuery();
            
            if (consulta.next()) {
                consulta.beforeFirst();
                while (consulta.next()) {
                    producto= new Producto();
                     producto.setId_producto(consulta.getInt("id_producto"));
                    producto.setNombre(consulta.getString("nombre"));
                    producto.setUso(consulta.getString("uso"));
                    producto.setTamaño(consulta.getInt("tamaño_cm3"));
                    producto.setCosto(consulta.getDouble("costo"));
                    producto.setCosto_publico(consulta.getDouble("costo_publico"));
                    producto.setEstrellas(consulta.getInt("estrellas"));
                    
                    productos.add(producto);
            }
                JOptionPane.showMessageDialog(null, "Se encontraron los productos");
                System.out.println("Se encontraron revdendedores");
            } else {
                JOptionPane.showMessageDialog(null, "No se encontraron los productos");
                System.out.println("No se encontraron los productos");
            }
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al obtener los productos");
            System.out.println(ex.getMessage());
        }

        return productos;
    }
    public List<Producto> buscarProductosPorNombre(String nombre) {

        Producto producto;
        List<Producto> productos = new ArrayList<>();

        try {
            PreparedStatement instruccion = con.prepareStatement("SELECT * FROM producto WHERE nombre LIKE '%"+ nombre +"%';");
            ResultSet consulta = instruccion.executeQuery();
            
            if (consulta.next()) {
                consulta.beforeFirst();
                while (consulta.next()) {
                    producto= new Producto();
                    producto.setId_producto(consulta.getInt("id_producto"));
                    producto.setNombre(consulta.getString("nombre"));
                    producto.setUso(consulta.getString("uso"));
                    producto.setTamaño(consulta.getInt("tamaño_cm3"));
                    producto.setCosto(consulta.getDouble("costo"));
                    producto.setCosto_publico(consulta.getDouble("costo_publico"));
                    producto.setEstrellas(consulta.getInt("estrellas"));
                    
                    productos.add(producto);
            }
                JOptionPane.showMessageDialog(null, "Se encontraron los productos");
                System.out.println("Se encontraron los productos");
            } else {
                JOptionPane.showMessageDialog(null, "No se encontraron los productos");
                System.out.println("No se encontraron los productos");
            }
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al obtener los productos");
            System.out.println(ex.getMessage());
        }

        return productos;
    }
    //public List<Revendedor> buscarProductosPorEstrellas(int idCampaña){} 
    
}
