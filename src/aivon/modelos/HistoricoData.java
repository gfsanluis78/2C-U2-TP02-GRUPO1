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

    public HistoricoData(Conexion conexion) {
        this.con = conexion.getConnection();
    }
    
   public void altaHistorico(Historico historico){
       String pre_instruccion = "INSERT INTO historico (id_revendedor, id_campaña, monto_min, monto_max, estado_campaña_revendedor) VALUES (?, ?, ?, ?, ?);";

        try {
            PreparedStatement instruccion = con.prepareStatement(pre_instruccion, Statement.RETURN_GENERATED_KEYS);

            instruccion.setInt(1, historico.getRevendedor().getId_revendedor());
            instruccion.setInt(2, historico.getCampaña().getId_campaña());
            instruccion.setDouble(3, historico.getMonto_min());

            instruccion.setDouble(4, historico.getMonto_max());
            //El estado campaña revendedor es cuando pasada 3 campañas se desactiva 
            instruccion.setBoolean(5, historico.isEstado_campaña_revendedor());
            

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
            // CARGANDO ID GENERADA EN TABLA AL ALUMNO EN JAVA
            if (llaves.next()) {
                historico.setId_historico(llaves.getInt(1));
            }

            instruccion.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al cargar pedido del producto");
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

}
