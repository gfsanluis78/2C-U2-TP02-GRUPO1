
package aivon.modelos;

import aivon.entidades.Campaña;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class CampañaData {
    
    private Connection con;

    public CampañaData(Conexion conexion) {
        con = conexion.getConnection();
    
    
}
//--------------------------ALTA---------------------------------------------------------//////
    
    public void guardarCampaña (Campaña campaña){
        
        String pre_instruccion;
        pre_instruccion="INSERT INTO campaña(nombre, fecha_inicio, fecha_fin, activa) VALUES (?,?,?,?);";
        
        try{
        
        PreparedStatement instruccion;
        instruccion = con.prepareStatement(pre_instruccion, Statement.RETURN_GENERATED_KEYS);
        
        instruccion.setString(1, campaña.getNombre());
        instruccion.setDate(2,Date.valueOf(campaña.getFecha_inicio()) );
        instruccion.setDate(3, Date.valueOf(campaña.getFecha_fin()) );
        instruccion.setBoolean(4, campaña.isActiva());
        
        
        instruccion.executeUpdate();
        
        try (ResultSet resultado = instruccion.getGeneratedKeys()) {
                if (resultado.next()) {
               campaña.setId_campaña(resultado.getInt(1));
                    System.out.println("Campaña cargada");
                    JOptionPane.showMessageDialog(null, "Campaña cargado");
                } else {
                    JOptionPane.showMessageDialog(null, "No pudo obtener id");
                    System.out.println("No pudo obtener id");
                }
            }

            instruccion.close();
        }catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al guardar Campaña");
            System.out.println(ex.getMessage());
        }
       
    }
    
   
    
//--------------------------BAJA----------------------------------------------------------//////

 //Por id
    
    public void borrarCampaña(int id) {

        try {
            Statement statement = con.createStatement();
            int celAfectadas = statement.executeUpdate("DELETE FROM campaña WHERE id_campaña=" + id + ";");

            if (celAfectadas > 0) {
                JOptionPane.showMessageDialog(null, "Campaña Borrada");
                System.out.println("Campaña Borrada");
            } else {
                JOptionPane.showMessageDialog(null, "El Registro con id " + id + " que pretende borrar no existe!!");
                System.out.println("El Registro con id " + id + " que pretende borrar no existe!!");
            }
            statement.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al borrar campaña por Id");
            System.out.println("Error al borrar campaña");
        }

    }
    
    //Por nombre
    
     public void borrarCampaña(String nombre) { 
        try {
            Statement statement = con.createStatement();
            int celAfectadas = statement.executeUpdate("DELETE FROM campaña WHERE nombre='" + nombre + "';");

            if (celAfectadas > 0) {
                JOptionPane.showMessageDialog(null, "Campaña Borrada");
                System.out.println("Campaña Borrada");
            } else {
                JOptionPane.showMessageDialog(null, "El Registro con nombre " + nombre + " que pretende borrar no existe!!");
                System.out.println("El Registro con nombre " + nombre + " que pretende borrar no existe!!");
            }
            statement.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al borrar campaña por nombre");
            System.out.println("Error al borrar campaña");
        }

    }
            
    
    

//--------------------------MODIFICACION--------------------------------------------------//////
        
    public void modificarCampaña(Campaña campaña) {

        try {
            String pre_instruccion = "UPDATE campaña SET nombre=?, fecha_inicio=?, fecha_fin=?, activa=? WHERE id_campaña=?";
            PreparedStatement instruccion = con.prepareStatement(pre_instruccion);
            instruccion.setString(1, campaña.getNombre());
            instruccion.setDate(2, Date.valueOf(campaña.getFecha_inicio()));
            instruccion.setDate(3, Date.valueOf(campaña.getFecha_fin()));
            instruccion.setBoolean(4, campaña.isActiva());
            
            instruccion.setInt(5,campaña.getId_campaña());

            int celAfectadas = instruccion.executeUpdate();
            if (celAfectadas > 0) {
                System.out.println("Campaña Modificada");
                JOptionPane.showMessageDialog(null, "Campaña Modificado");
            } else {
                System.out.println("El Registro " + campaña.getId_campaña() + " no pudo ser actualizado");
                JOptionPane.showMessageDialog(null, "La Campaña no se pudo actualizar");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, "Error al actualizar campaña");
        }

    }


//--------------------------CONSULTA-------------------------------------------------------//////
    
    //Por Id: 
     
     
     public Campaña buscarCampaña (int id) {

        Campaña campaña = null;

        try {
            Statement instruccion = con.createStatement();
            try (ResultSet consulta = instruccion.executeQuery("SELECT * FROM campaña WHERE id_campaña=" + id + ";")) {
                if (consulta.next()) {
                    campaña = new Campaña();
                    campaña.setId_campaña(consulta.getInt("id_campaña"));
                    campaña.setNombre(consulta.getString("nombre"));
                    campaña.setFecha_inicio(consulta.getDate("fecha_inicio").toLocalDate());
                    campaña.setFecha_inicio(consulta.getDate("fecha_fin").toLocalDate());
                    campaña.setActiva(consulta.getBoolean("activa"));
                    
                                       
                } else {
                    JOptionPane.showMessageDialog(null, "No se pudo buscar campaña por Id");
                    System.out.println("No se pudo buscar campaña por Id");
                }
            }
            instruccion.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al buscar campaña por Id");
            System.out.println(ex.getMessage());
        }
        return campaña;
    }
     
    //Por Nombre:
    
     public Campaña buscarCampaña (String nombre) {

        Campaña campaña = null;

        try {
            Statement instruccion = con.createStatement();
            try (ResultSet consulta = instruccion.executeQuery("SELECT * FROM campaña WHERE nombre='" + nombre + "';")) {
                if (consulta.next()) {
                    campaña = new Campaña();
                    campaña.setId_campaña(consulta.getInt("id_campaña"));
                    campaña.setNombre(consulta.getString("nombre"));
                    campaña.setFecha_inicio(consulta.getDate("fecha_inicio").toLocalDate());
                    campaña.setFecha_inicio(consulta.getDate("fecha_fin").toLocalDate());
                    campaña.setActiva(consulta.getBoolean("activa"));
                    
                } else {
                    JOptionPane.showMessageDialog(null, "No se pudo buscar campaña por Id");
                    System.out.println("No se pudo buscar campaña por Id");
                }
            }
            instruccion.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al buscar campaña por Id");
            System.out.println(ex.getMessage());
        }
        return campaña;
    }
        
     //Por Fecha Inicio:
    
     public Campaña buscarCampañaXFechaInicio (LocalDate fecha_inicio) { 

        Campaña campaña = null;

        try {
            Statement instruccion = con.createStatement();
            try (ResultSet consulta = instruccion.executeQuery("SELECT * FROM campaña WHERE fecha_inicio=" + Date.valueOf(fecha_inicio) + ";")) {
                if (consulta.next()) {
                    campaña = new Campaña();
                    campaña.setId_campaña(consulta.getInt("id_campaña"));
                    campaña.setNombre(consulta.getString("nombre"));
                    campaña.setFecha_inicio(consulta.getDate("fecha_inicio").toLocalDate());
                    campaña.setFecha_inicio(consulta.getDate("fecha_fin").toLocalDate());
                    campaña.setActiva(consulta.getBoolean("activa"));
                    
                } else {
                    JOptionPane.showMessageDialog(null, "No se pudo buscar campaña por fecha e inicio");
                    System.out.println("No se pudo buscar campaña por fecha de inicio");
                }
            }
            instruccion.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al buscar campaña por fecha de inicio");
            System.out.println(ex.getMessage());
        }
        return campaña;
    }
        
    
    
//--------------------------CONSULTA MULTIPLE----------------------------------------------//////
    
     
    // Busca Todas las Campañas
     
    public List<Campaña> buscarCampañas() {

        Campaña campaña;
        List<Campaña> campañas = new ArrayList<>();

        try {
            PreparedStatement instruccion = con.prepareStatement("SELECT * FROM campaña");
            ResultSet consulta = instruccion.executeQuery();
            
            if (consulta.next()) {
                consulta.beforeFirst();
                while (consulta.next()) {
                    campaña = new Campaña();
                    
                    campaña.setId_campaña(consulta.getInt("id_campaña"));
                    campaña.setNombre(consulta.getString("nombre"));
                    campaña.setFecha_inicio(consulta.getDate("fecha_inicio").toLocalDate());
                    campaña.setFecha_inicio(consulta.getDate("fecha_fin").toLocalDate());
                    campaña.setActiva(consulta.getBoolean("activa"));
                    
                    campañas.add(campaña);
            }
                JOptionPane.showMessageDialog(null, "Se encontraron campañas");
                System.out.println("Se encontraron campañas");
            } else {
                JOptionPane.showMessageDialog(null, "No se encontraron campañas");
                System.out.println("No se encontraron campañas");
            }
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al obtener campañas");
            System.out.println(ex.getMessage());
        }

        return campañas;
    }
    
        
    // BUsca Todas las campañas activas
    
    public List<Campaña> buscarCampañasActivas() {

        Campaña campaña;
        List<Campaña> campañas = new ArrayList<>();

        try {
            PreparedStatement instruccion = con.prepareStatement("SELECT * FROM campaña WHERE activa = 1");
            ResultSet consulta = instruccion.executeQuery();
            
            if (consulta.next()) {
                consulta.beforeFirst();
                while (consulta.next()) {
                    campaña = new Campaña();
                    
                    campaña.setId_campaña(consulta.getInt("id_campaña"));
                    campaña.setNombre(consulta.getString("nombre"));
                    campaña.setFecha_inicio(consulta.getDate("fecha_inicio").toLocalDate());
                    campaña.setFecha_inicio(consulta.getDate("fecha_fin").toLocalDate());
                    campaña.setActiva(consulta.getBoolean("activa"));
                    
                    campañas.add(campaña);
            }
                JOptionPane.showMessageDialog(null, "Se encontraron campañas activas");
                System.out.println("Se encontraron campañas activas");
            } else {
                JOptionPane.showMessageDialog(null, "No se encontraron campañas activas");
                System.out.println("No se encontraron campañas");
            }
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al obtener campañas activas");
            System.out.println(ex.getMessage());
        }

        return campañas;
    }
    
    
    // Busca todas las campañas Inactivas
    
    public List<Campaña> buscarCampañasInActivas() {

        Campaña campaña;
        List<Campaña> campañas = new ArrayList<>();

        try {
            PreparedStatement instruccion = con.prepareStatement("SELECT * FROM campaña WHERE activa = 0");
            ResultSet consulta = instruccion.executeQuery();
            
            if (consulta.next()) {
                consulta.beforeFirst();
                while (consulta.next()) {
                    campaña = new Campaña();
                    
                    campaña.setId_campaña(consulta.getInt("id_campaña"));
                    campaña.setNombre(consulta.getString("nombre"));
                    campaña.setFecha_inicio(consulta.getDate("fecha_inicio").toLocalDate());
                    campaña.setFecha_inicio(consulta.getDate("fecha_fin").toLocalDate());
                    campaña.setActiva(consulta.getBoolean("activa"));
                    
                    campañas.add(campaña);
            }
                JOptionPane.showMessageDialog(null, "Se encontraron campañas activas");
                System.out.println("Se encontraron campañas activas");
            } else {
                JOptionPane.showMessageDialog(null, "No se encontraron campañas activas");
                System.out.println("No se encontraron campañas");
            }
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al obtener campañas activas");
            System.out.println(ex.getMessage());
        }

        return campañas;
    }
    
    
}
