package aivon.modelos;

import aivon.entidades.Revendedor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class RevendedorData {
    private Connection con;

    public RevendedorData(Conexion conexion) {
        con = conexion.getConnection();
    }
    
    //--------------------------ALTA----------------------------------------------//////
    

    public void guardarRevendedor (Revendedor revendedor){
        
        String pre_instruccion;
        pre_instruccion="INSERT INTO revendedor(nombre, apellido, dni, tel, email, activo) VALUES (?,?,?,?,?);";
        
        try{
        
        PreparedStatement instruccion;
        instruccion = con.prepareStatement(pre_instruccion, Statement.RETURN_GENERATED_KEYS);
        
        instruccion.setString(1,revendedor.getNombre() );
        instruccion.setString(2, revendedor.getApellido());
        instruccion.setString(3, revendedor.getDni());
        instruccion.setString(4, revendedor.getTel());
        instruccion.setString(5, revendedor.getEmail());
        instruccion.setBoolean(6, revendedor.isActivo());
        
        
        instruccion.executeUpdate();
        
        try (ResultSet resultado = instruccion.getGeneratedKeys()) {
                if (resultado.next()) {
                   revendedor.setId_revendedor(resultado.getInt(1));
                    System.out.println("Revendedor cargado");
                    JOptionPane.showMessageDialog(null, "Revendedor cargado");
                } else {
                    JOptionPane.showMessageDialog(null, "No pudo obtener id");
                    System.out.println("No pudo obtener id");
                }
            }

            instruccion.close();
        }catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al guardar Revendedor");
            System.out.println(ex.getMessage());
        }
       
    }
    
     //---------------------------BAJA------------------------------------------------//////
    
    //Por id
    
    public void borrarRevendedor(int id) {

        try {
            Statement statement = con.createStatement();
            int celAfectadas = statement.executeUpdate("DELETE FROM revendedor WHERE id_revendedor=" + id + ";");

            if (celAfectadas > 0) {
                JOptionPane.showMessageDialog(null, "Revendedor Borrado");
                System.out.println("Revendedor Borrado");
            } else {
                JOptionPane.showMessageDialog(null, "El Registro con id " + id + " que pretende borrar no existe!!");
                System.out.println("El Registro con id " + id + " que pretende borrar no existe!!");
            }
            statement.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al borrar revendedor por Id");
            System.out.println("Error al borrar revendedor");
        }

    }
    
    //Por dni
    
     public void borrarRevendedor(String dni) { //en bd pasar el dni a string

        try {
            Statement statement = con.createStatement();
            int celAfectadas = statement.executeUpdate("DELETE FROM revendedor WHERE dni='" + dni + "';");

            if (celAfectadas > 0) {
                JOptionPane.showMessageDialog(null, "Revendedor Borrado");
                System.out.println("Revendedor Borrado");
            } else {
                JOptionPane.showMessageDialog(null, "El Registro con dni " + dni + " que pretende borrar no existe!!");
                System.out.println("El Registro con dni " + dni + " que pretende borrar no existe!!");
            }
            statement.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al borrar revendedor por dni");
            System.out.println("Error al borrar revendedor");
        }

    }
            
    //----------------------Modificacion-----------------------------------------------////
     
     public void modificarRevendedor(Revendedor revendedor) {

        try {
            String pre_instruccion = "UPDATE revendedor SET nombre=?,apellido=?,dni=?,tel=?,email=?,activo=? WHERE id_revendedor=?";
            PreparedStatement instruccion = con.prepareStatement(pre_instruccion);
            instruccion.setString(1, revendedor.getNombre());
            instruccion.setString(2, revendedor.getApellido());
            instruccion.setString(3, revendedor.getDni());
            instruccion.setString(4, revendedor.getTel());
            instruccion.setString(5, revendedor.getEmail());
            instruccion.setBoolean(6, revendedor.isActivo());
            
            instruccion.setInt(7,revendedor.getId_revendedor());

            int celAfectadas = instruccion.executeUpdate();
            if (celAfectadas > 0) {
                System.out.println("Revendedor Modificado");
                JOptionPane.showMessageDialog(null, "Revendedor Modificado");
            } else {
                System.out.println("El Registro " + revendedor.getId_revendedor() + " no pudo ser actualizado");
                JOptionPane.showMessageDialog(null, "El Revendedor no se pudo actualizar");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, "Error al actualizar revendedor");
        }

    }

        
    //----------------------CONSULTA--------------------------------------------//////
     
    //Por Id: 
     
     
     public Revendedor buscarRevendedor (int id) {

        Revendedor revendedor = null;

        try {
            Statement instruccion = con.createStatement();
            try (ResultSet consulta = instruccion.executeQuery("SELECT * FROM revendedor WHERE id_revendedor=" + id + ";")) {
                if (consulta.next()) {
                    revendedor = new Revendedor();
                    revendedor.setId_revendedor(consulta.getInt("id_revendedor"));
                    revendedor.setNombre(consulta.getString("nombre"));
                    revendedor.setApellido(consulta.getString("apellido"));
                    revendedor.setDni(consulta.getString("dni"));
                    revendedor.setTel(consulta.getString("tel"));
                    revendedor.setEmail(consulta.getString("email"));
                    revendedor.setActivo(consulta.getBoolean("activo"));
                    
                } else {
                    JOptionPane.showMessageDialog(null, "No se pudo buscar revendedor por dni");
                    System.out.println("No se pudo buscar revendedor por dni");
                }
            }
            instruccion.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al buscar Revendedor por dni");
            System.out.println(ex.getMessage());
        }
        return revendedor;
    }
     
    //Por Dni:
    
     public Revendedor buscarRevendedor (String dni) {

        Revendedor revendedor = null;

        try {
            Statement instruccion = con.createStatement();
            try (ResultSet consulta = instruccion.executeQuery("SELECT * FROM revendedor WHERE dni='" + dni + "';")) {
                if (consulta.next()) {
                    revendedor = new Revendedor();
                    revendedor.setId_revendedor(consulta.getInt("id_revendedor"));
                    revendedor.setNombre(consulta.getString("nombre"));
                    revendedor.setApellido(consulta.getString("apellido"));
                    revendedor.setDni(consulta.getString("dni"));
                    revendedor.setTel(consulta.getString("tel"));
                    revendedor.setEmail(consulta.getString("email"));
                    revendedor.setActivo(consulta.getBoolean("activo"));
                    
                } else {
                    JOptionPane.showMessageDialog(null, "No se pudo buscar revendedor por dni");
                    System.out.println("No se pudo buscar revendedor por dni");
                }
            }
            instruccion.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al buscar Revendedor por dni");
            System.out.println(ex.getMessage());
        }
        return revendedor;
    }
        
    //-------------------Consulta Multiple-----------------------------------------------
     
     public List<Revendedor> buscarRevendedores() {

        Revendedor revendedor;
        List<Revendedor> revendedores = new ArrayList<>();

        try {
            PreparedStatement instruccion = con.prepareStatement("SELECT * FROM revendedor");
            ResultSet consulta = instruccion.executeQuery();
            
            if (consulta.next()) {
                consulta.beforeFirst();
                while (consulta.next()) {
                    revendedor = new Revendedor();
                    revendedor.setId_revendedor(consulta.getInt("id_revendedor"));
                    revendedor.setNombre(consulta.getString("nombre"));
                    revendedor.setApellido(consulta.getString("apellido"));
                    revendedor.setDni(consulta.getString("dni"));
                    revendedor.setTel(consulta.getString("tel"));
                    revendedor.setEmail(consulta.getString("email"));
                    revendedor.setActivo(consulta.getBoolean("activo"));
                    
                    revendedores.add(revendedor);
            }
                JOptionPane.showMessageDialog(null, "Se encontraron revendedores");
                System.out.println("Se encontraron revdendedores");
            } else {
                JOptionPane.showMessageDialog(null, "No se encontraron revendedores");
                System.out.println("No se encontraron revendedores");
            }
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al obtener revendedores");
            System.out.println(ex.getMessage());
        }

        return revendedores;
    }
    
     public List<Revendedor> buscarRevendedoresActivos() {

        Revendedor revendedor;
        List<Revendedor> revendedores = new ArrayList<>();

        try {
            PreparedStatement instruccion = con.prepareStatement("SELECT * FROM revendedor WHERE activo=1");
            ResultSet consulta = instruccion.executeQuery();
            if (consulta.next()) {
                consulta.beforeFirst();
                while (consulta.next()) {
                    revendedor = new Revendedor();
                    revendedor.setId_revendedor(consulta.getInt("id_revendedor"));
                    revendedor.setNombre(consulta.getString("nombre"));
                    revendedor.setApellido(consulta.getString("apellido"));
                    revendedor.setDni(consulta.getString("dni"));
                    revendedor.setTel(consulta.getString("tel"));
                    revendedor.setEmail(consulta.getString("email"));
                    revendedor.setActivo(consulta.getBoolean("activo"));
                    
                    revendedores.add(revendedor);
            }
                JOptionPane.showMessageDialog(null, "Se encontraron revendedores activos");
                System.out.println("Se encontraron revendedores activos");
            } else {
                JOptionPane.showMessageDialog(null, "No se encontraron revendedores activos");
                System.out.println("No se encontraron revendedores activos");
            }
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al obtener Revendedores activos");
            System.out.println(ex.getMessage());
        }

        return revendedores;
    }

    public List<Revendedor> buscarRevendedoresInactivos() {

        Revendedor revendedor;
        List<Revendedor> revendedores = new ArrayList<>();

        try {
            PreparedStatement instruccion = con.prepareStatement("SELECT * FROM revendedor WHERE activo=0");
            ResultSet consulta = instruccion.executeQuery();
            if (consulta.next()) {
                consulta.beforeFirst();
                while (consulta.next()) {
                    revendedor = new Revendedor();
                    revendedor.setId_revendedor(consulta.getInt("id_revendedor"));
                    revendedor.setNombre(consulta.getString("nombre"));
                    revendedor.setApellido(consulta.getString("apellido"));
                    revendedor.setDni(consulta.getString("dni"));
                    revendedor.setTel(consulta.getString("tel"));
                    revendedor.setEmail(consulta.getString("email"));
                    revendedor.setActivo(consulta.getBoolean("activo"));
                    
                    revendedores.add(revendedor);
            }
                JOptionPane.showMessageDialog(null, "Se encontraron revendedores inactivos");
                System.out.println("Se encontraron revendedores inactivos");
            } else {
                JOptionPane.showMessageDialog(null, "No se encontraron revendedores inactivos");
                System.out.println("No se encontraron revendedores inactivos");
            }
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al obtener Revendedores inactivos");
            System.out.println(ex.getMessage());
        }

        return revendedores;
    }
    
    public List<Revendedor> buscarRevendedoresXCampaña(int idCampaña) {  

        Revendedor revendedor;
        List<Revendedor> revendedores = new ArrayList<>();

        try {
            PreparedStatement instruccion = con.prepareStatement("SELECT revendedor.id_revendedor, nombre, apellido, dni, tel, email, "
                    + "revendedor.activo FROM revendedor, historico,campaña  where historico.id_campaña = campaña.id_campaña AND  "+
                    "revendedor.id_revendedor= historico.id_revendedor AND campaña.id_campaña =" + idCampaña);

            ResultSet consulta = instruccion.executeQuery();
            
            if (consulta.next()) {
                consulta.beforeFirst();
                while (consulta.next()) {
                    revendedor = new Revendedor();
                    revendedor.setId_revendedor(consulta.getInt("id_revendedor"));
                    revendedor.setNombre(consulta.getString("nombre"));
                    revendedor.setApellido(consulta.getString("apellido"));
                    revendedor.setDni(consulta.getString("dni"));
                    revendedor.setTel(consulta.getString("tel"));
                    revendedor.setEmail(consulta.getString("email"));
                    revendedor.setActivo(consulta.getBoolean("activo"));
                    
                    revendedores.add(revendedor);
            }
                JOptionPane.showMessageDialog(null, "Se encontraron revendedores para la campaña "+idCampaña);
                System.out.println("Se encontraron revdendedores para la campaña "+idCampaña);
            } else {
                JOptionPane.showMessageDialog(null, "No se encontraron revendedores en la campaña "+idCampaña);
                System.out.println("No se encontraron revendedores  en la campaña "+idCampaña);
            }
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al obtener revendedores  en la campaña "+idCampaña);
            System.out.println(ex.getMessage());
        }

        return revendedores;
    }
    
}
