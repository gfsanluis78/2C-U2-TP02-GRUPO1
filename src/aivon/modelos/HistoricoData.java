/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aivon.modelos;

import aivon.entidades.Campaña;
import aivon.entidades.Historico;
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
public class HistoricoData {
    private Connection con;
    private Conexion conexion;

    public HistoricoData(Conexion conexion) {
        this.con = conexion.getConnection();
        this.conexion=conexion;
    }
    
   public void altaHistorico(Historico historico){
       String pre_instruccion = "INSERT INTO historico (id_revendedor, id_campaña, monto_min, monto_max, nivel, ganancia, estrellas_campaña_revendedor) VALUES (?, ?, ?, ?, ?, ?, ?);";

        try {
            PreparedStatement instruccion = con.prepareStatement(pre_instruccion, Statement.RETURN_GENERATED_KEYS);
            instruccion.setInt(1, historico.getRevendedor().getId_revendedor());
            instruccion.setInt(2, historico.getCampaña().getId_campaña());
            instruccion.setDouble(3, historico.getMonto_min());
            instruccion.setDouble(4, historico.getMonto_max());
            //El estado campaña revendedor es cuando pasada 3 campañas se desactiva 
            instruccion.setInt(5, historico.getNivel());
            instruccion.setDouble(6, historico.getGanancia());
            instruccion.setInt(7, historico.getEstrellas_campaña_revendedor());
            

            int celAfectadas = instruccion.executeUpdate();
            ResultSet llaves = instruccion.getGeneratedKeys();
            // NOTIFICANDO OPERACION
            if (celAfectadas > 0) {
                System.out.println("Historico cargado");
                JOptionPane.showMessageDialog(null, "Historico cargado");
            } else {
                System.out.println("No se pudo cargar historico");
                JOptionPane.showMessageDialog(null, "No se pudo cargar historico");
            }
            // CARGANDO ID GENERADA EN TABLA AL HISTORICO EN JAVA
            if (llaves.next()) {
                historico.setId_historico(llaves.getInt(1));
            }

            instruccion.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al cargar Historico");
            System.out.println(e.getMessage());
        }
   
   }
   // Metodo para actualizar estrellas, montos y estado de campaña
   // 
   // Hacer metodo auxiliar que cree un metodo data y llame algun metodo para actualizar
   public void actualizarHistorico(Historico historico){
       String pre_instruccion = "UPDATE historico SET monto_min=?, monto_max=?, nivel=?, ganancia=?, estrellas_campaña_revendedor=?"
               + " WHERE id_historico="+ historico.getId_historico()+";";

        try {
            PreparedStatement instruccion = con.prepareStatement(pre_instruccion);
            instruccion.setDouble(1, historico.getMonto_min());
            instruccion.setDouble(2, historico.getMonto_max());
            //El estado campaña revendedor es cuando pasada 3 campañas se desactiva 
            instruccion.setInt(3, historico.getNivel());
            instruccion.setDouble(4, historico.getGanancia());
            instruccion.setInt(5, historico.getEstrellas_campaña_revendedor());
            

            int celAfectadas = instruccion.executeUpdate();
            if (celAfectadas > 0) {
                System.out.println("Historico Actualizado");
                JOptionPane.showMessageDialog(null, "Historico Actualizado");
            } else {
                System.out.println("No se pudo actualizar historico");
                JOptionPane.showMessageDialog(null, "No se pudo actualizar historico");
            }

            instruccion.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar Historico");
            System.out.println(e.getMessage());
        }
   }
   
   public Historico buscarHistorico(Revendedor revendedor, Campaña campaña){
       Historico historico=null; 
   
        try {
            Statement instruccion = con.createStatement();
            ResultSet consulta=instruccion.executeQuery(" SELECT * FROM historico "
                    + "WHERE id_revendedor="+revendedor.getId_revendedor()
                    + " AND id_campaña="+campaña.getId_campaña()+";");
            if(consulta.next()){
                historico= new Historico();
                historico.setId_historico(consulta.getInt("id_historico"));
                historico.setRevendedor(revendedor);
                historico.setCampaña(campaña);
                historico.setEstrellas_campaña_revendedor(consulta.getInt("estrellas_campaña_revendedor"));
                historico.setGanancia(consulta.getDouble("ganancia"));
                historico.setMonto_max(consulta.getDouble("monto_max"));
                historico.setMonto_min(consulta.getDouble("monto_min"));
                historico.setNivel(consulta.getInt("nivel"));
             }
            else{
                JOptionPane.showMessageDialog(null, "No hay historico del revendedor "+revendedor.getDni()+" para campaña "+campaña.getNombre());
                System.out.println("No hay historico del revendedor "+revendedor.getDni()+" para campaña "+campaña.getNombre());
            }
         instruccion.close();           
        }
        catch (SQLException e){
                JOptionPane.showMessageDialog(null, "Error al obtener historico");
                System.out.println(e.getMessage()+"Error al obtener historico");
        }
       return historico;
   }
   
   public List<Historico> buscarHistoricosPorRevendedor(Revendedor revendedor){
       List<Historico> historicos=new ArrayList();
       Historico historico=null;
       
       try {
           Statement statement=con.createStatement();
           ResultSet consulta=statement.executeQuery("SELECT * FROM historico WHERE id_revendedor="+revendedor.getId_revendedor()+";");
           
           if(consulta.next()){
               consulta.beforeFirst();
               while(consulta.next()){
                    historico= new Historico();
                    historico.setId_historico(consulta.getInt("id_historico"));
                    historico.setCampaña(this.buscarCampaña(consulta.getInt("id_campaña")));
                    historico.setRevendedor(revendedor);
                    historico.setMonto_max(consulta.getDouble("monto_max"));
                    historico.setMonto_min(consulta.getDouble("monto_min"));
                    historico.setEstrellas_campaña_revendedor(consulta.getInt("estrellas_campaña_revendedor"));
                    historico.setNivel(consulta.getInt("nivel"));
                    historico.setGanancia(consulta.getDouble("ganancia"));
                    historicos.add(historico);
               }
           }
           else{
                JOptionPane.showMessageDialog(null, "No hay historico del revendedor "+revendedor.getNombre());
                System.out.println("No hay historico del revendedor "+revendedor.getNombre());
            }
         statement.close();           
        }
        catch (SQLException e){
                JOptionPane.showMessageDialog(null, "Error al obtener lista de historicos");
                System.out.println(e.getMessage()+"Error al obtener lista de historicos");
        }
       return historicos;
   }
   public List<Historico> buscarHistoricosPorCampaña(Campaña campaña){
       List<Historico> historicos=new ArrayList();
       Historico historico=null;
       
       try {
           Statement statement=con.createStatement();
           ResultSet consulta=statement.executeQuery("SELECT * FROM historico WHERE id_campaña="+campaña.getId_campaña()+";");
           
           if(consulta.next()){
               consulta.beforeFirst();
               while(consulta.next()){
                    historico= new Historico();
                    historico.setId_historico(consulta.getInt("id_historico"));
                    historico.setCampaña(campaña);
                    historico.setRevendedor(this.buscarRevendedor(consulta.getInt("id_revendedor")));
                    historico.setMonto_max(consulta.getDouble("monto_max"));
                    historico.setMonto_min(consulta.getDouble("monto_min"));
                    historico.setEstrellas_campaña_revendedor(consulta.getInt("estrellas_campaña_revendedor"));
                    historico.setNivel(consulta.getInt("nivel"));
                    historico.setGanancia(consulta.getDouble("ganancia"));
                    historicos.add(historico);
               }
           }
           else{
                JOptionPane.showMessageDialog(null, "No hay historico de la campaña "+campaña.getNombre());
                System.out.println("No hay historico de la campaña "+campaña.getNombre());
            }
         statement.close();           
        }
        catch (SQLException e){
                JOptionPane.showMessageDialog(null, "Error al obtener lista de historicos de campañas");
                System.out.println(e.getMessage()+"Error al obtener lista de historicos de campañas");
        }
       return historicos;
   }
   
   public List<Historico> buscarHistoricos(){
       List<Historico> historicos=new ArrayList();
       Historico historico=null;
       
       try {
           Statement statement=con.createStatement();
           ResultSet consulta=statement.executeQuery("SELECT * FROM historico;");
           
           if(consulta.next()){
               consulta.beforeFirst();
               while(consulta.next()){
                    historico= new Historico();
                    historico.setId_historico(consulta.getInt("id_historico"));
                    historico.setCampaña(this.buscarCampaña(consulta.getInt("id_campaña")));
                    historico.setRevendedor(this.buscarRevendedor(consulta.getInt("id_revendedor")));
                    historico.setMonto_max(consulta.getDouble("monto_max"));
                    historico.setMonto_min(consulta.getDouble("monto_min"));
                    historico.setEstrellas_campaña_revendedor(consulta.getInt("estrellas_campaña_revendedor"));
                    historico.setNivel(consulta.getInt("nivel"));
                    historico.setGanancia(consulta.getDouble("ganancia"));
                    historicos.add(historico);
               }
           }
           else{
                JOptionPane.showMessageDialog(null, "No hay ningun historico ");
                System.out.println("No hay ningun historico ");
            }
         statement.close();           
        }
        catch (SQLException e){
                JOptionPane.showMessageDialog(null, "Error al obtener lista de historicos");
                System.out.println(e.getMessage()+"Error al obtener lista de historicos");
        }
       return historicos;
   }
   
   public void mostrarEstrellasPorRevendedor(){
   
   }
   public void recuperarEstadoUltimaCampaña(){
       //
   }
   public void listaHistoricosDeCampañas(){
       //como fue tal campaña, listar todos los revendedores de tal campaña
   }
   
   public Revendedor buscarRevendedor(String dni){
       RevendedorData rd= new RevendedorData(conexion);
        return rd.buscarRevendedor(dni);
   }
   public Revendedor buscarRevendedor(int id){
       RevendedorData rd= new RevendedorData(conexion);
        return rd.buscarRevendedor(id);
   }
   public Campaña buscarCampaña(int id){
       CampañaData cd=new CampañaData(conexion);
       return cd.buscarCampaña(id);
   }
   public int cantidadEstrellasCampaña(Revendedor revendedor, Campaña campaña){
       PedidoData pd=new PedidoData(conexion);
       return pd.buscarPedido(revendedor.getId_revendedor(), campaña.getId_campaña()).getEstrellas_pedido() ;
   }
   public double gananciaRevendedorCampaña(Revendedor revendedor, Campaña campaña){
       RevendedorData rd= new RevendedorData(conexion);
       return rd.gananciaRevendedorPorCampaña(revendedor, campaña);
   } 
   public int nivelRevendedor(Revendedor revendedor){
       RevendedorData rd=new RevendedorData(conexion);
       return rd.calcularNivelRevendedor(revendedor);
    }
   public double montoMinRevendedor(Revendedor revendedor){
       RevendedorData rd=new RevendedorData(conexion);
       return rd.calcularMontoMinimoRevendedor(revendedor);
   }
   public double montoMaxRevendedor(Revendedor revendedor){
       RevendedorData rd=new RevendedorData(conexion);
       return rd.calcularMontoMaximoRevendedor(revendedor);
   }
}
