/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aivon.modelos;

import aivon.entidades.*;
import java.sql.*;
import javax.swing.JOptionPane;

/**
 *
 * @author Ezequiel
 */
public class PedidoData {

    Connection c;

    public PedidoData(Conexion conexion) {
        this.c = conexion.getConnection();
    }
    
//    public void alta_pedido(Pedido pedido) {
//
//        String pre_instruccion = "INSERT INTO pedido (id_revendedor, id_campaña, fecha_ingreso) VALUES (?, ?, ?);";
//
//        try {
//
//            // CREANDO PREPARED STATEMENT
//            PreparedStatement instruccion = c.prepareStatement(pre_instruccion, Statement.RETURN_GENERATED_KEYS);
//            // LLENANDO SIGNOS '?'
//            instruccion.setInt(1, inscripcion.getAlumno().getId_alumno());
//            instruccion.setInt(2, inscripcion.getMateria().getId_materia());
//            instruccion.setDouble(3, inscripcion.getCalificacion());
//            //instruccion.setDate(4, Date.valueOf(inscripcion.getFecha_i()));
//            // EJECUTANDO Y GUARDANDO LLAVES GENERADAS
//            int celAfectadas = instruccion.executeUpdate();
//            ResultSet llaves = instruccion.getGeneratedKeys();
//            // NOTIFICANDO OPERACION
//            if (celAfectadas > 0) {
//                System.out.println("Inscripción realizada");
//                JOptionPane.showMessageDialog(null, "Inscripcion Exitosa");
//            } else {
//                System.out.println("No se pudo realizar la inscripción");
//                JOptionPane.showMessageDialog(null, "No se pudo realizar la inscripción");
//            }
//            // CARGANDO ID GENERADA EN TABLA AL ALUMNO EN JAVA
//            if (llaves.next()) {
//                inscripcion.getAlumno().setId_alumno(llaves.getInt(1));
//            }
//            instruccion.close();
//        } catch (SQLException e) {
//            JOptionPane.showMessageDialog(null, "No se pudo realizar la inscripción");
//            System.out.println(e.getMessage());
//
//        }        
//        
//        
//    }
    
    public void ver_pedido(int id_pedido) {
        
    }
    
    
    
    
    
}
