package aivon.modelos;

import aivon.entidades.Campaña;
import aivon.entidades.Pedido;
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
        pre_instruccion="INSERT INTO revendedor(nombre, apellido, dni, tel, email, nivel, activo) VALUES (?,?,?,?,?,?,?);";
        
        try{
        
        PreparedStatement instruccion;
        instruccion = con.prepareStatement(pre_instruccion, Statement.RETURN_GENERATED_KEYS);
        
        instruccion.setString(1,revendedor.getNombre() );
        instruccion.setString(2, revendedor.getApellido());
        instruccion.setString(3, revendedor.getDni());
        instruccion.setString(4, revendedor.getTel());
        instruccion.setString(5, revendedor.getEmail());
        instruccion.setInt(6, revendedor.getNivel());
        instruccion.setBoolean(7, revendedor.isActivo());
        
        
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
            //JOptionPane.showMessageDialog(null, "Error al guardar Revendedor");
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
    
     public void borrarRevendedor(String dni) { 

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
     
    
            
    //----------------------MODIFICACION-----------------------------------------------////
     
     public void modificarRevendedor(Revendedor revendedor) {

        try {
            String pre_instruccion = "UPDATE revendedor SET nombre=?,apellido=?,dni=?,tel=?,email=?, nivel=?, activo=? WHERE id_revendedor=?";
            PreparedStatement instruccion = con.prepareStatement(pre_instruccion);
            instruccion.setString(1, revendedor.getNombre());
            instruccion.setString(2, revendedor.getApellido());
            instruccion.setString(3, revendedor.getDni());
            instruccion.setString(4, revendedor.getTel());
            instruccion.setString(5, revendedor.getEmail());
            instruccion.setInt(6, revendedor.getNivel());
            instruccion.setBoolean(7, revendedor.isActivo());
            
            instruccion.setInt(8,revendedor.getId_revendedor());

            int celAfectadas = instruccion.executeUpdate();
            if (celAfectadas > 0) {
                System.out.println("Revendedor Modificado");
                JOptionPane.showMessageDialog(null, "Revendedor Modificado");
            } else {
                System.out.println("El Registro " + revendedor.getId_revendedor() + " no pudo ser actualizado");
                JOptionPane.showMessageDialog(null, "El Revendedor no se pudo actualizar");
            }
            instruccion.close();
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
                    revendedor.setNivel(consulta.getInt("nivel"));
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
                    revendedor.setNivel(consulta.getInt("nivel"));
                    revendedor.setActivo(consulta.getBoolean("activo"));
                    
                } else {
                    JOptionPane.showMessageDialog(null, "No existe el Revendedor con DNI: " + dni);
                    //JOptionPane.showMessageDialog(null, "No se pudo buscar revendedor por dni");
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
     
      //Buscar revendedores
     
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
                    revendedor.setNivel(consulta.getInt("nivel"));
                    revendedor.setActivo(consulta.getBoolean("activo"));
                    
                    revendedores.add(revendedor);
            }
                JOptionPane.showMessageDialog(null, "Se encontraron revendedores");
                System.out.println("Se encontraron revdendedores");
            } else {
                JOptionPane.showMessageDialog(null, "No se encontraron revendedores");
                System.out.println("No se encontraron revendedores");
            }
            instruccion.close();
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al obtener revendedores");
            System.out.println(ex.getMessage());
        }

        return revendedores;
    }
      //Buscar revendedores activos
    
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
                    revendedor.setNivel(consulta.getInt("nivel"));
                    revendedor.setActivo(consulta.getBoolean("activo"));
                    
                    revendedores.add(revendedor);
            }
                JOptionPane.showMessageDialog(null, "Se encontraron revendedores activos");
                System.out.println("Se encontraron revendedores activos");
            } else {
                JOptionPane.showMessageDialog(null, "No se encontraron revendedores activos");
                System.out.println("No se encontraron revendedores activos");
            }
            instruccion.close();
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al obtener Revendedores activos");
            System.out.println(ex.getMessage());
        }

        return revendedores;
    }
     
     //Buscar revendedores por nivel 
     
   public List<Revendedor> buscarRevendedoresXNivel(int nivel) {

        Revendedor revendedor;
        List<Revendedor> revendedores = new ArrayList<>();

        try {
            PreparedStatement instruccion = con.prepareStatement("SELECT * FROM revendedor WHERE nivel="+nivel+";");
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
                    revendedor.setNivel(consulta.getInt("nivel"));
                    revendedor.setActivo(consulta.getBoolean("activo"));
                    
                    revendedores.add(revendedor);
            }
                JOptionPane.showMessageDialog(null, "Se encontraron revendedores por nivel");
                System.out.println("Se encontraron revendedores por nivel");
            } else {
                JOptionPane.showMessageDialog(null, "No se encontraron revendedores para ese nivel");
                System.out.println("No se encontraron revendedores para ese nivel");
            }
            instruccion.close();
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al obtener Revendedores de ese nivel");
            System.out.println(ex.getMessage());
        }

        return revendedores;
    }
   
    //Buscar revendedores por campaña
    
    public List<Revendedor> buscarRevendedoresXCampaña(int idCampaña) {  

        Revendedor revendedor;
        List<Revendedor> revendedores = new ArrayList<>();

        try {
            PreparedStatement instruccion = con.prepareStatement("SELECT revendedor.id_revendedor, revendedor.nombre, apellido, dni, tel, email, revendedor.nivel, revendedor.activo FROM revendedor,campaña, historico where historico.id_campaña = campaña.id_campaña AND revendedor.id_revendedor= historico.id_revendedor AND campaña.id_campaña = " + idCampaña+";");

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
                    revendedor.setNivel(consulta.getInt("nivel"));
                    revendedor.setActivo(consulta.getBoolean("activo"));
                    
                    revendedores.add(revendedor);
            }
                JOptionPane.showMessageDialog(null, "Se encontraron revendedores para la campaña "+idCampaña);
                System.out.println("Se encontraron revdendedores para la campaña "+idCampaña);
            } else {
                JOptionPane.showMessageDialog(null, "No se encontraron revendedores en la campaña "+idCampaña);
                System.out.println("No se encontraron revendedores  en la campaña "+idCampaña);
            }
            instruccion.close();
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al obtener revendedores  en la campaña "+idCampaña);
            System.out.println(ex.getMessage());
        }

        return revendedores;
    }
    
    
    //-------------------Metodos auxilares-----------------------------------------------
     
    //ganacias totales del revendedor
    
       public double gananciaFinalRevendedor(Revendedor revendedor) {

        double ganancia = 0;

        try {

            Statement statement = con.createStatement();
            ResultSet consulta = statement.executeQuery("SELECT SUM(costo_caja_publico) - SUM(costo_caja) "
                    + "AS ganancia FROM caja_pedido, pedido WHERE caja_pedido.id_pedido=pedido.id_pedido "
                    + "AND pedido.fecha_pago IS NOT NULL AND pedido.id_revendedor=" 
                    + revendedor.getId_revendedor() + ";");

            if(consulta.next()){
            ganancia = consulta.getDouble("ganancia");
            }
           statement.close();
           
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al realizar la consulta");
            System.out.println(e.getMessage());
        }

        return ganancia;
    }
       
    //ganancias por revendedor y por un pedido especifico
       
       public double gananciaRevendedorPorPedido(Revendedor revendedor, Pedido pedido) {

        double ganancia = 0;

        try {

            Statement statement = con.createStatement();
            ResultSet consulta = statement.executeQuery("SELECT SUM(costo_caja_publico) - SUM(costo_caja) "
                    + "AS ganancia FROM caja_pedido, pedido WHERE caja_pedido.id_pedido=pedido.id_pedido "
                    + "AND pedido.fecha_pago IS NULL AND pedido.id_revendedor=" + revendedor.getId_revendedor() 
                    + " AND pedido.id_pedido ="+ pedido.getId_pedido()+";");

            if(consulta.next()){
            ganancia = consulta.getDouble("ganancia");
            }
            statement.close();
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al realizar la consulta");
            System.out.println(e.getMessage());
        }

        return ganancia;
    }
       
       
       //ganancias por revendedor y por campaña
       
       public double gananciaRevendedorPorCampaña(Revendedor revendedor, Campaña campaña) {

        double ganancia = 0;

        try {

            Statement statement = con.createStatement();
            ResultSet consulta = statement.executeQuery("SELECT SUM(costo_caja_publico) - SUM(costo_caja) AS ganancia FROM caja_pedido, pedido, campaña WHERE caja_pedido.id_pedido=pedido.id_pedido AND pedido.id_campaña = campaña.id_campaña AND pedido.fecha_pago IS NOT null AND pedido.id_revendedor = " + revendedor.getId_revendedor() +" AND campaña.id_campaña = " + campaña.getId_campaña()+";");

            if(consulta.next()){
            ganancia = consulta.getDouble("ganancia");
            }
            statement.close();
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al realizar la consulta ganancia");
            System.out.println(e.getMessage());
        }

        return ganancia;
    }
    
    //consultar estrellas totales de revendedor
       
       public int buscarEstrellasTotalesRevendedor(Revendedor revendedor) {
           
     
           
        int estrellas=0;
        
        try {

            Statement statement = con.createStatement();
            ResultSet consulta = statement.executeQuery("SELECT SUM(estrellas_pedido) AS estrellas FROM pedido WHERE pedido.fecha_pago IS NOT null AND pedido.id_revendedor = " + revendedor.getId_revendedor()+" ;");

            if(consulta.next()){
            estrellas = consulta.getInt("estrellas");
            }
            
            statement.close();
            } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al realizar la consulta");
            System.out.println(e.getMessage());
        }

        return estrellas;
    }   
    //calcular nivel de revendedor
       
    public int calcularNivelRevendedor(Revendedor revendedor) {

        /*SELECT SUM(estrellas_pedido) / 50 
                    AS nivel FROM pedido
                    WHERE pedido.fecha_pago IS NOT null
                    AND pedido.id_revendedor = 1
         */
        int nivel = 1;
        int escalon = 50;

        try {

            Statement statement = con.createStatement();
            ResultSet consulta = statement.executeQuery("SELECT SUM(estrellas_pedido) / "+escalon+ " AS nivel FROM pedido WHERE pedido.fecha_pago IS NOT null AND pedido.id_revendedor =" + revendedor.getId_revendedor()+";");

            if (consulta.next()) {
                nivel = consulta.getInt("nivel")+1;
                revendedor.setNivel(nivel);
            } else {
                JOptionPane.showMessageDialog(null, "No se obtuvo nivel");
                System.out.println("No se obtuvo el nivel");
            }
                
            
            int celAfectadas = statement.executeUpdate("Update revendedor Set nivel = "+nivel+" where id_revendedor = "+revendedor.getId_revendedor()+";");
            if (celAfectadas > 0) {
                System.out.println("Nivel actualizado");
                JOptionPane.showMessageDialog(null, "Nivel actualizado");
            } else {
                System.out.println("El Registro del nivel de Id " + revendedor.getId_revendedor() + " no pudo ser actualizado");
                JOptionPane.showMessageDialog(null, "El Nivel del Revendedor no se pudo actualizar");
            }

            statement.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al realizar la consulta");
            System.out.println(e.getMessage());
        }

        return nivel;
    }
       
       public double calcularMontoMinimoRevendedor(Revendedor revendedor) {
           
         
        Campaña campaña;   
        int nivel = revendedor.getNivel()-1;
        double min=0;
        
        try {

            Statement statement = con.createStatement();
            ResultSet consulta = statement.executeQuery("SELECT monto_min FROM campaña WHERE campaña.activa = 1;");
            
            if(consulta.next()){
            min=(consulta.getDouble("monto_min"));
            }
            min += 0.10*nivel;
            
            

            statement.close();
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al realizar la consulta");
            System.out.println(e.getMessage());
        }

        return min;
    }
       
    
       public double calcularMontoMaximoRevendedor(Revendedor revendedor) {
           
           
        Campaña campaña;   
        int nivel = revendedor.getNivel()-1;
        double max=0;
        
        try {

            Statement statement = con.createStatement();
            ResultSet consulta = statement.executeQuery("SELECT monto_max FROM campaña WHERE campaña.activa = 1;");
            if(consulta.next()){        
            max=(consulta.getDouble("monto_max"));
            }
            max += 0.10*nivel;
                  

            statement.close();
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al realizar la consulta");
            System.out.println(e.getMessage());
        }

        return max;
    }
       
}
