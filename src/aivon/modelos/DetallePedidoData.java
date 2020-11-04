package aivon.modelos;

import aivon.entidades.*;
import java.sql.*;
import javax.swing.JOptionPane;

/**
 *
 * @author Ezequiel
 */
public class DetallePedidoData {

    Connection c;

    public DetallePedidoData(Conexion conexion) {
        this.c = conexion.getConnection();
    }

    public void pedir_producto(DetallePedido detallePedido) {

        String pre_instruccion = "INSERT INTO detalle_pedido (id_pedido, id_producto, cant_producto, costo_caja, costo_caja_publico, estrellas_caja) VALUES (?, ?, ?, ?, ?, ?);";

        try {
            PreparedStatement instruccion = c.prepareStatement(pre_instruccion, Statement.RETURN_GENERATED_KEYS);

            instruccion.setInt(1, detallePedido.getPedido().getId_pedido());
            instruccion.setInt(2, detallePedido.getProducto().getId_producto());
            instruccion.setInt(3, detallePedido.getCantidad_producto());

            instruccion.setDouble(4, detallePedido.getProducto().getCosto() * detallePedido.getCantidad_producto());
            instruccion.setDouble(5, detallePedido.getProducto().getCosto_publico() * detallePedido.getCantidad_producto());
            instruccion.setInt(6, detallePedido.getProducto().getEstrellas() * detallePedido.getCantidad_producto());

            int celAfectadas = instruccion.executeUpdate();
            ResultSet llaves = instruccion.getGeneratedKeys();
            // NOTIFICANDO OPERACION
            if (celAfectadas > 0) {
                System.out.println("Pedido de producto cargado");
                JOptionPane.showMessageDialog(null, "Pedido producto cargado");
            } else {
                System.out.println("No se pudo cargar pedido del producto");
                JOptionPane.showMessageDialog(null, "No se pudo cargar pedido del producto");
            }
            // CARGANDO ID GENERADA EN TABLA AL ALUMNO EN JAVA
            if (llaves.next()) {
                detallePedido.setId_caja(llaves.getInt(1));
            }

            instruccion.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al cargar pedido del producto");
            System.out.println(e.getMessage());
        }
    }
    
    
    
    
    
    
    
}
