package aivon.modelos;

import aivon.entidades.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Ezequiel
 */
public class DetallePedidoData {
    Conexion conexion;
    Connection c;

    public DetallePedidoData(Conexion conexion) {
        this.conexion=conexion;
        this.c = conexion.getConnection();
    }
//##############################################################################
//################ AGREGAR PEDIDO DE X PRODUCTO ################################
    public void pedirProducto(DetallePedido detalle_pedido) {

        String pre_instruccion = "INSERT INTO caja_pedido (id_pedido, id_producto, cantidad_producto, costo_caja, costo_caja_publico, estrellas_caja) VALUES (?, ?, ?, ?, ?, ?);";

        try {
            PreparedStatement instruccion = c.prepareStatement(pre_instruccion, Statement.RETURN_GENERATED_KEYS);

            instruccion.setInt(1, detalle_pedido.getPedido().getId_pedido());
            instruccion.setInt(2, detalle_pedido.getProducto().getId_producto());
            instruccion.setInt(3, detalle_pedido.getCantidad_producto());

            instruccion.setDouble(4, detalle_pedido.getProducto().getCosto() * detalle_pedido.getCantidad_producto());
            instruccion.setDouble(5, detalle_pedido.getProducto().getCosto_publico() * detalle_pedido.getCantidad_producto());
            instruccion.setInt(6, detalle_pedido.getProducto().getEstrellas() * detalle_pedido.getCantidad_producto());

            int celAfectadas = instruccion.executeUpdate();
            ResultSet llaves = instruccion.getGeneratedKeys();
            // NOTIFICANDO OPERACION
            if (celAfectadas > 0) {
                System.out.println("Pedido de producto cargado");
                //JOptionPane.showMessageDialog(null, "Pedido producto cargado");
            } else {
                System.out.println("No se pudo cargar pedido del producto");
                JOptionPane.showMessageDialog(null, "No se pudo cargar pedido del producto");
            }
            // CARGANDO ID GENERADA EN TABLA AL ALUMNO EN JAVA
            if (llaves.next()) {
                detalle_pedido.setId_caja(llaves.getInt(1));
            }

            instruccion.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al cargar pedido del producto");
            System.out.println(e.getMessage());
        }
    }
//##############################################################################
//################ BUSCAR LISTA DETALLES-PEDIDO ################################

//    public List<DetallePedido> listaDetallesPedido (Pedido pedido) {
//        DetallePedido detalle_pedido;
//        List<DetallePedido> lista = new ArrayList<>();
//        
//        
//    }
    
    public List<DetallePedido> listaDetallesPedido (Pedido pedido) {

        List<DetallePedido> lista = new ArrayList();
        DetallePedido detalle_pedido;
        

        try {
            Statement statement = c.createStatement();
            ResultSet consulta = statement.executeQuery("SELECT * FROM caja_pedido WHERE id_pedido=" + pedido.getId_pedido()+ ";");

            if (consulta.next()) {
                consulta.beforeFirst();
                while (consulta.next()) {

                    detalle_pedido = new DetallePedido();
                    detalle_pedido.setId_caja(consulta.getInt("id_caja"));
                    detalle_pedido.setPedido(pedido);
                    detalle_pedido.setProducto(this.buscarProducto(consulta.getInt("id_producto")));
                    detalle_pedido.setCantidad_producto(consulta.getInt("cantidad_producto"));
                    detalle_pedido.setCosto_caja(consulta.getDouble("costo_caja"));
                    detalle_pedido.setCosto_caja_publico(consulta.getDouble("costo_caja_publico"));
                    detalle_pedido.setEstrellas_caja(consulta.getInt("estrellas_caja"));
                    
                    lista.add(detalle_pedido);
                }
            } else {
                System.out.println("No se pudo obtener lista\nde contenido del pedido");
                JOptionPane.showMessageDialog(null, "No se pudo obtener lista\nde contenido del pedido");
            }
            statement.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al obtener lista de\ncontenido del pedido");
            System.out.println(e.getMessage());
        }

        return lista;
    }
    
    
    
//##############################################################################
//################ BORRAR PEDIDO DE X PRODUCTO #################################

/* Usar solamente al armar pedido en las vistas y no después, para preservar
    el histórico. Es decir, si al armar el pedido, nos arrepentiemos de agregar
    x producto, usamos el método, pero no después de cerrar el pedido, porque ya
    va a tener la "factura" hecha (su costo, cant estrellas, etc). De todas
    maneras, en vistas, vamos a armar el pedido y su contenido en clases java, y
    una vez que esté todo definido, se va a cargar a la base de datos, por lo que
    no deberíamos tener que recurrir a este método */
    
    public void borrarPedirProducto(DetallePedido detalle_pedido) {
        try {
            Statement statement = c.createStatement();
            int celAfectadas = statement.executeUpdate("DELETE FROM caja_pedido WHERE caja_pedido.id_caja="+detalle_pedido.getId_caja()+";");
            
            if (celAfectadas > 0) {
                System.out.println("Pedido de producto borrado");
                JOptionPane.showMessageDialog(null, "Pedido producto borrado");
            } else {
                System.out.println("No se pudo borrar pedido del producto");
                JOptionPane.showMessageDialog(null, "No se pudo borrar pedido del producto");
            }
            statement.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al borrar pedido del producto");
            System.out.println(e.getMessage());
        }
    }
    
//##############################################################################
//######################## MÉTODOS AUXILIARES ##################################

private Producto buscarProducto (int id_producto) {
    ProductoData pd = new ProductoData(conexion);
    return pd.buscarProducto(id_producto);
}
    
    
}
