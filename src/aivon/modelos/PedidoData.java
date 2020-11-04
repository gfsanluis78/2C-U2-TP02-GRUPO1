package aivon.modelos;

import aivon.entidades.*;
import java.sql.*;
import java.time.*;
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
//##############################################################################
//################ ALTA PEDIDO #################################################    
    public void altaPedido(Pedido pedido) {

        String pre_instruccion = "INSERT INTO pedido (id_revendedor, id_campaña, fecha_ingreso) VALUES (?, ?, ?);";

        try {

            // CREANDO PREPARED STATEMENT
            PreparedStatement instruccion = c.prepareStatement(pre_instruccion, Statement.RETURN_GENERATED_KEYS);

            // LLENANDO SIGNOS '?'
            instruccion.setInt(1, pedido.getRevendedor().getId_revendedor());
            instruccion.setInt(2, pedido.getCampaña().getId_campaña());
            instruccion.setDate(3, Date.valueOf(pedido.getFecha_ingreso()));

            // EJECUTANDO Y GUARDANDO LLAVES GENERADAS
            int celAfectadas = instruccion.executeUpdate();
            ResultSet llaves = instruccion.getGeneratedKeys();
            // NOTIFICANDO OPERACION
            if (celAfectadas > 0) {
                System.out.println("Pedido iniciado");
                JOptionPane.showMessageDialog(null, "Pedido iniciado");
            } else {
                System.out.println("No se pudo iniciar pedido");
                JOptionPane.showMessageDialog(null, "No se pudo iniciar pedido");
            }
            // CARGANDO ID GENERADA EN TABLA AL ALUMNO EN JAVA
            if (llaves.next()) {
                pedido.setId_pedido(llaves.getInt(1));
            }
            instruccion.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al realizar el alta del pedido");
            System.out.println(e.getMessage());
        }
    }
//##############################################################################
//################ COSTO PEDIDO ################################################    
    public double costoPedido(Pedido pedido) {

        double costo = 0;

        try {

            Statement statement = c.createStatement();
            ResultSet consulta = statement.executeQuery("SELECT SUM(costo_caja)\n"
                    + "AS costo_pedido\n"
                    + "FROM caja_pedido\n"
                    + "WHERE id_pedido=" + pedido.getId_pedido() + ";");

            costo = consulta.getDouble("costo_pedido");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al realizar la consulta");
            System.out.println(e.getMessage());
        }

        return costo;
    }
//##############################################################################
//################### COSTO PEDIDO PÚBLICO #####################################
    public double costoPedidoPublico(Pedido pedido) {

        double costo = 0;

        try {

            Statement statement = c.createStatement();
            ResultSet consulta = statement.executeQuery("SELECT SUM(costo_caja_publico)\n"
                    + "AS costo_pedido\n"
                    + "FROM caja_pedido\n"
                    + "WHERE id_pedido=" + pedido.getId_pedido() + ";");

            costo = consulta.getDouble("costo_pedido");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al realizar la consulta");
            System.out.println(e.getMessage());
        }

        return costo;
    }
//##############################################################################
//################ CANTIDAD ESTRELLAS PEDIDO ###################################
    public int cantEstrellasPedido(Pedido pedido) {

        int cantidad = 0;

        try {

            Statement statement = c.createStatement();
            ResultSet consulta = statement.executeQuery("SELECT SUM(estrellas_caja)\n"
                    + "AS estrellas\n"
                    + "FROM caja_pedido\n"
                    + "WHERE id_pedido=" + pedido.getId_pedido() + ";");

            cantidad = consulta.getInt("estrellas");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al realizar la consulta");
            System.out.println(e.getMessage());
        }
        pedido.setEstrellas_pedido(cantidad);
        return cantidad;
    }
//##############################################################################
//################ CANTIDAD DE CAJAS DE PEDIDO #################################

    public int cantCajasPedido(Pedido pedido) {

        int cantidad = 0;

        try {

            Statement statement = c.createStatement();
            ResultSet consulta = statement.executeQuery("SELECT COUNT(*) "
                    + "AS cajas "
                    + "FROM caja_pedido "
                    + "WHERE id_pedido="
                    + pedido.getId_pedido() + ";");

            cantidad = consulta.getInt("cajas");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al realizar la consulta");
            System.out.println(e.getMessage());
        }
        pedido.setCantidad_cajas(cantidad);
        return cantidad;
    }

//##############################################################################
//################ ACTUALIZAR FECHA DE ENTREGA #################################
    public void actualizarFechaEntrega(Pedido pedido, LocalDate fecha_entrega) {

        try {
            Statement statement = c.createStatement();
            int celAfectadas = statement.executeUpdate("UPDATE pedido "
                    + "SET fecha_entrega=" + Date.valueOf(fecha_entrega)
                    + "WHERE pedido.id_pedido=" + pedido.getId_pedido() + ";");
            if (celAfectadas > 0) {
                System.out.println("Fecha de entrega cargada");
                JOptionPane.showMessageDialog(null, "Fecha de entrega cargada");
            } else {
                System.out.println("No se cargó la fecha de entrega");
                JOptionPane.showMessageDialog(null, "No se cargó la fecha de entrega");
            }
            statement.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al cargar la fecha de entrega");
            System.out.println(e.getMessage());
        }
    }
//##############################################################################
//################### ACTUALIZAR FECHA DE PAGO #################################
    public void actualizarFechaPago(Pedido pedido, LocalDate fecha_pago) {
        // DEBERÍAMOS INFERIR EL ATRIBUTO PAGO(bool) DE LA BD
        try {
            Statement statement = c.createStatement();
            int celAfectadas = statement.executeUpdate("UPDATE pedido "
                    + "SET fecha_pago=" + Date.valueOf(fecha_pago)
                    + "WHERE pedido.id_pedido=" + pedido.getId_pedido() + ";");
            if (celAfectadas > 0) {
                System.out.println("Fecha de pago cargada");
                JOptionPane.showMessageDialog(null, "Fecha de pago cargada");
            } else {
                System.out.println("No se cargó la fecha de pago");
                JOptionPane.showMessageDialog(null, "No se cargó la fecha de pago");
            }
            statement.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al cargar la fecha de pago");
            System.out.println(e.getMessage());
        }
    }
//##############################################################################
//################### DESACTIVAR/CANCELAR PEDIDO ###############################
    public void inhabilitarPedido(Pedido pedido) {
        // DEBERÍAMOS INFERIR EL ATRIBUTO PAGO(bool) DE LA BD
        try {
            Statement statement = c.createStatement();
            int celAfectadas = statement.executeUpdate("UPDATE pedido SET activo=0 "
                    + "WHERE pedido.id_pedido=" + pedido.getId_pedido() + ";");
            if (celAfectadas > 0) {
                System.out.println("Pedido inhabilitado");
                JOptionPane.showMessageDialog(null, "Pedido inhabilitado");
            } else {
                System.out.println("No se inhabilitó el pedido");
                JOptionPane.showMessageDialog(null, "No se inhabilitó el pedido");
            }
            statement.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al inhabilitar el pedido");
            System.out.println(e.getMessage());
        }
    }
//##############################################################################
//############################# HABILITAR PEDIDO ###############################
    public void habilitarPedido(Pedido pedido) {
        // DEBERÍAMOS INFERIR EL ATRIBUTO PAGO(bool) DE LA BD
        try {
            Statement statement = c.createStatement();
            int celAfectadas = statement.executeUpdate("UPDATE pedido SET activo=1 "
                    + "WHERE pedido.id_pedido=" + pedido.getId_pedido() + ";");
            if (celAfectadas > 0) {
                System.out.println("Pedido habilitado");
                JOptionPane.showMessageDialog(null, "Pedido habilitado");
            } else {
                System.out.println("No se habilitó el pedido");
                JOptionPane.showMessageDialog(null, "No se habilitó el pedido");
            }
            statement.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al habilitar el pedido");
            System.out.println(e.getMessage());
        }
    }
//################### ZONA RESIDUAL QUE NO BORRO POR LAS DUDAS #################
//    public void generar_costo_pedido(Pedido pedido) {
//
//        try {
//
//            Statement statement = c.createStatement();
//            int celAfectadas = statement.executeUpdate("UPDATE ACÁ VA ALGO INCREÍBLE");
//
//            if (celAfectadas > 0) {
//                JOptionPane.showMessageDialog(null, "Costo pedido generado");
//                System.out.println("Costo pedido generado");
//            } else {
//                JOptionPane.showMessageDialog(null, "El pedido con id "+pedido.getId_pedido()+" no se reconoce");
//                System.out.println("El pedido con id "+pedido.getId_pedido()+" no se reconoce");
//            }
//            //################################################
//            ResultSet resultado = statement.executeQuery("SELECT costo_pedido FROM pedido WHERE id_pedido="+pedido.getId_pedido());    
//            
//            //################################################
//            statement.close();
//
//        } catch (SQLException e) {
//            JOptionPane.showMessageDialog(null, "Error al generar costo del pedido");
//            System.out.println("Error al generar costo del pedido");
//        }
//
//    }
//    public void ver_pedidos(int id_pedido) {
//
//    }
//##############################################################################
}
