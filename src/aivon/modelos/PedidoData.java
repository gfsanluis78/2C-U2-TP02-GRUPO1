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
                System.out.println("No se pudo iniciar pedido. Ya hay un pedido de ese revendedor para esta campaña");
                JOptionPane.showMessageDialog(null, "No se pudo iniciar pedido. Ya hay un pedido de ese revendedor para esta campaña");
            }
            // CARGANDO ID GENERADA EN TABLA AL PEDIDO EN JAVA
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
//################ COSTO PEDIDO ACTIVO #########################################    
    public double costoPedidoActivo(Pedido pedido) {

        double costo = 0;

        try {

            Statement statement = c.createStatement();
            ResultSet consulta = statement.executeQuery("SELECT SUM(costo_caja)\n"
                    + "AS costo_pedido\n"
                    + "FROM caja_pedido\n"
                    + "WHERE pedido.activo=1\n"
                    + "AND id_pedido=" + pedido.getId_pedido() + ";");

            costo = consulta.getDouble("costo_pedido");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al realizar la consulta");
            System.out.println(e.getMessage());
        }

        return costo;
    }
//##############################################################################
//################### COSTO PÚBLICO PEDIDO #####################################
    public double costoPublicoPedido(Pedido pedido) {

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
//################### COSTO PÚBLICO PEDIDO ACTIVO ##############################
    public double costoPublicoPedidoActivo(Pedido pedido) {

        double costo = 0;

        try {

            Statement statement = c.createStatement();
            ResultSet consulta = statement.executeQuery("SELECT SUM(costo_caja_publico)\n"
                    + "AS costo_pedido\n"
                    + "FROM caja_pedido\n"
                    + "WHERE pedido.activo=1\n"
                    + "AND id_pedido=" + pedido.getId_pedido() + ";");

            costo = consulta.getDouble("costo_pedido");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al realizar la consulta");
            System.out.println(e.getMessage());
        }

        return costo;
    }
//##############################################################################
//################ COSTO PEDIDOS PAGOS X REVENDEDOR ############################
    public double costoPedidosPagosRevendedor(Revendedor revendedor) {

        double costo = 0;

        try {

            Statement statement = c.createStatement();
            ResultSet consulta = statement.executeQuery("SELECT SUM(costo_caja) AS costo_pedidos "
                    + "FROM caja_pedido, pedido WHERE caja_pedido.id_pedido = pedido.id_pedido "
                    + "AND pedido.fecha_pago IS NOT NULL AND pedido.id_revendedor=" + revendedor.getId_revendedor() + ";");

            costo = consulta.getDouble("costo_pedidos");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al realizar la consulta");
            System.out.println(e.getMessage());
        }

        return costo;
    }
//##############################################################################
//################ COSTO PÚBLICO PEDIDOS PAGOS X REVENDEDOR ####################    
    public double costoPublicoPedidosPagosRevendedor(Revendedor revendedor) {

        double costo = 0;

        try {

            Statement statement = c.createStatement();
            ResultSet consulta = statement.executeQuery("SELECT SUM(costo_caja_publico) AS costo_pedidos "
                    + "FROM caja_pedido, pedido WHERE caja_pedido.id_pedido = pedido.id_pedido "
                    + "AND pedido.fecha_pago IS NOT NULL AND pedido.id_revendedor=" + revendedor.getId_revendedor() + ";");

            costo = consulta.getDouble("costo_pedidos");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al realizar la consulta");
            System.out.println(e.getMessage());
        }

        return costo;
    }
//##############################################################################
//######################## GANANCIA TOTAL X REVENDEDOR #########################    
//
//    public double gananciaFinalRevendedor(Revendedor revendedor) {
//
//        double ganancia = 0;
//
//        try {
//
//            Statement statement = c.createStatement();
//            ResultSet consulta = statement.executeQuery("SELECT SUM(costo_caja_publico) - SUM(costo_caja) "
//                    + "AS ganancia FROM caja_pedido, pedido WHERE caja_pedido.id_pedido=pedido.id_pedido "
//                    + "AND pedido.fecha_pago IS NULL AND pedido.id_revendedor=" + revendedor.getId_revendedor() + ";");
//
//            ganancia = consulta.getDouble("ganancia");
//
//        } catch (SQLException e) {
//            JOptionPane.showMessageDialog(null, "Error al realizar la consulta");
//            System.out.println(e.getMessage());
//        }
//
//        return ganancia;
//    }    
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
//################ CANTIDAD ESTRELLAS PEDIDO ACTIVO ############################
    public int cantEstrellasPedidoActivo(Pedido pedido) {

        int cantidad = 0;

        try {

            Statement statement = c.createStatement();
            ResultSet consulta = statement.executeQuery("SELECT SUM(estrellas_caja)\n"
                    + "AS estrellas\n"
                    + "FROM caja_pedido, pedido\n"
                    + "WHERE pedido.activo=1\n"
                    + "AND caja_pedido.id_pedido=" + pedido.getId_pedido() + ";");

            cantidad = consulta.getInt("estrellas");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al realizar la consulta");
            System.out.println(e.getMessage());
        }
        pedido.setEstrellas_pedido(cantidad);
        return cantidad;
    }
//##############################################################################
//################ COSTO PEDIDOS PAGOS X REVENDEDOR ############################
    public double cantEstrellasPedidosPagoRevendedor(Revendedor revendedor) {

        int estrellas = 0;

        try {

            Statement statement = c.createStatement();
            ResultSet consulta = statement.executeQuery("SELECT SUM(estrellas_caja) AS estrellas "
                    + "FROM caja_pedido, pedido WHERE caja_pedido.id_pedido = pedido.id_pedido "
                    + "AND pedido.fecha_pago IS NOT NULL AND pedido.id_revendedor=" 
                    + revendedor.getId_revendedor() + ";");

            estrellas = consulta.getInt("estrellas");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al realizar la consulta");
            System.out.println(e.getMessage());
        }

        return estrellas;
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
//################### CANTIDAD DE CAJAS DE PEDIDO ACTIVO #######################
    public int cantCajasPedidoActivo(Pedido pedido) {

        int cantidad = 0;

        try {

            Statement statement = c.createStatement();
            ResultSet consulta = statement.executeQuery("SELECT COUNT(*) "
                    + "AS cajas "
                    + "FROM caja_pedido, pedido "
                    + "WHERE pedido.activo=1\n"
                    + "AND caja_pedido.id_pedido="
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
