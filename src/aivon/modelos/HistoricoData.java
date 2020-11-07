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
   public void actualizarHistorico(){
       //Llamar a los metodos 
   }
   public void mostrarEstrellasPorRevendedor(){
   
   }
   public void recuperarEstadoUltimaCampaña(){
       //
   }
   public void listaHistoricoDeCampañas(){
       //como fue tal campaña, listar todos los revendedores de tal campaña
   }
   
   public Revendedor buscarRevendedor(String dni){
       RevendedorData rd= new RevendedorData(conexion);
        return rd.buscarRevendedor(dni);
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
