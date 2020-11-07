package aivon.modelos;

import aivon.entidades.*;
import java.sql.*;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Ezequiel
 */
public class PedidoData {

    Connection c;
    Conexion conexion;

    public PedidoData(Conexion conexion) {
        this.c = conexion.getConnection();
        this.conexion = conexion;
    }
//##############################################################################
//################ ALTA PEDIDO #################################################    

    public void altaPedido(Pedido pedido) {

        if (pedido.getFecha_ingreso().isAfter(this.buscarCampañaActiva().getFecha_inicio()) && pedido.getFecha_ingreso().isBefore(this.buscarCampañaActiva().getFecha_fin())) {

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
        } else {
            JOptionPane.showMessageDialog(null, "La fecha de ingreso del pedido no es válida");
            System.out.println("La fecha de ingreso del pedido no es válida");
        }
    }
//##############################################################################
//################### BUSCAR UN PEDIDO X REVENDEDOR Y CAMAPAÑA #################

    public Pedido buscarPedido(int id_revendedor, int id_campaña) {
        Pedido pedido = new Pedido();
        Revendedor revendedor;
        Campaña campaña;
        try {
            Statement statement = c.createStatement();
            ResultSet consulta = statement.executeQuery("SELECT * FROM pedido WHERE id_campaña="
                    + id_campaña + " AND id_revendedor=" + id_revendedor + ";");

            if (consulta.next()) {
                pedido.setId_pedido(consulta.getInt("id_pedido"));
                pedido.setFecha_ingreso(consulta.getDate("fecha_ingreso").toLocalDate());
                pedido.setActivo(consulta.getBoolean("activo"));
                if (consulta.getDate("fecha_entrega") != null) {
                    pedido.setFecha_entrega(consulta.getDate("fecha_entrega").toLocalDate());
                } else {
                    System.out.println("No hay fecha de entrega aún");
                }

                if (consulta.getDate("fecha_pago") != null) {
                    pedido.setFecha_pago(consulta.getDate("fecha_pago").toLocalDate());
                } else {
                    System.out.println("No hay fecha de pago aún");
                }
                pedido.setCantidad_cajas(this.cantCajasPedido(pedido));
                pedido.setEstrellas_pedido(this.cantCajasPedido(pedido));

                revendedor = this.buscarRevendedor(id_revendedor);
                campaña = this.buscarCampaña(id_campaña);

                pedido.setRevendedor(revendedor);
                pedido.setCampaña(campaña);
            } else {
                System.out.println("No se pudo obtener pedido");
                JOptionPane.showMessageDialog(null, "No se pudo obtener pedido");
            }
            statement.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al obtener pedido");
            System.out.println(e.getMessage());
        }

        return pedido;
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
            if (consulta.next()) {
                costo = consulta.getDouble("costo_pedido");
            } else {
                JOptionPane.showMessageDialog(null, "No se pudo obtener el costo del pedido");
                System.out.println("No se pudo obtener el costo del pedido");
            }
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
                    + "FROM caja_pedido, pedido\n"
                    + "WHERE pedido.activo=1\n"
                    + "AND caja_pedido.id_pedido=" + pedido.getId_pedido() + ";");
            if (consulta.next()) {
                costo = consulta.getDouble("costo_pedido");
            } else {
                JOptionPane.showMessageDialog(null, "No se pudo obtener el costo del pedido");
                System.out.println("No se pudo obtener el costo del pedido");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al realizar la consulta");
            System.out.println(e.getMessage());
        }

        return costo;
    }
//##############################################################################
//################## COSTO PEDIDO PAGO #########################################    

    public double costoPedidoPago(Pedido pedido) {

        double costo = 0;

        try {

            Statement statement = c.createStatement();
            ResultSet consulta = statement.executeQuery("SELECT SUM(costo_caja)\n"
                    + "AS costo_pedido\n"
                    + "FROM caja_pedido, pedido\n"
                    + "WHERE pedido.fecha_pago IS NOT NULL\n"
                    + "AND caja_pedido.id_pedido=" + pedido.getId_pedido() + ";");
            if (consulta.next()) {
                costo = consulta.getDouble("costo_pedido");
            } else {
                JOptionPane.showMessageDialog(null, "No se pudo obtener el costo del pedido");
                System.out.println("No se pudo obtener el costo del pedido");
            }
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
            if (consulta.next()) {
                costo = consulta.getDouble("costo_pedido");
            } else {
                JOptionPane.showMessageDialog(null, "No se pudo obtener el costo del pedido");
                System.out.println("No se pudo obtener el costo del pedido");
            }
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
                    + "FROM caja_pedido, pedido\n"
                    + "WHERE pedido.activo=1\n"
                    + "AND caja_pedido.id_pedido=" + pedido.getId_pedido() + ";");
            if (consulta.next()) {
                costo = consulta.getDouble("costo_pedido");
            } else {
                JOptionPane.showMessageDialog(null, "No se pudo obtener el costo del pedido");
                System.out.println("No se pudo obtener el costo del pedido");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al realizar la consulta");
            System.out.println(e.getMessage());
        }

        return costo;
    }
//##############################################################################
//################### COSTO PÚBLICO PEDIDO PAGO ################################

    public double costoPublicoPedidoPago(Pedido pedido) {

        double costo = 0;

        try {

            Statement statement = c.createStatement();
            ResultSet consulta = statement.executeQuery("SELECT SUM(costo_caja_publico)\n"
                    + "AS costo_pedido\n"
                    + "FROM caja_pedido, pedido\n"
                    + "WHERE pedido.fecha_pago IS NOT NULL\n"
                    + "AND caja_pedido.id_pedido=" + pedido.getId_pedido() + ";");
            if (consulta.next()) {
                costo = consulta.getDouble("costo_pedido");
            } else {
                JOptionPane.showMessageDialog(null, "No se pudo obtener el costo del pedido");
                System.out.println("No se pudo obtener el costo del pedido");
            }
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
//################ CANTIDAD ESTRELLAS PEDIDO ###################################
    public int cantEstrellasPedido(Pedido pedido) {

        int cantidad = 0;

        try {

            Statement statement = c.createStatement();
            ResultSet consulta = statement.executeQuery("SELECT SUM(estrellas_caja)\n"
                    + "AS estrellas\n"
                    + "FROM caja_pedido\n"
                    + "WHERE id_pedido=" + pedido.getId_pedido() + ";");
            if (consulta.next()) {
                cantidad = consulta.getInt("estrellas");
            } else {
                JOptionPane.showMessageDialog(null, "No se pudo obtener cant de estrellas");
                System.out.println("No se pudo obtener cant de estrellas");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al realizar la consulta");
            System.out.println(e.getMessage());
        }
        //pedido.setEstrellas_pedido(this.cantEstrellasPedido(pedido));
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
            if (consulta.next()) {
                cantidad = consulta.getInt("estrellas");
            } else {
                JOptionPane.showMessageDialog(null, "No se pudo obtener cant de estrellas");
                System.out.println("No se pudo obtener cant de estrellas");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al realizar la consulta");
            System.out.println(e.getMessage() + "Error al realizar la consulta");
        }
        //pedido.setEstrellas_pedido(this.cantEstrellasPedido(pedido));
        return cantidad;
    }
//##############################################################################
//################ CANTIDAD ESTRELLAS PEDIDO PAGO ############################

    public int cantEstrellasPedidoPago(Pedido pedido) {

        int cantidad = 0;

        try {

            Statement statement = c.createStatement();
            ResultSet consulta = statement.executeQuery("SELECT SUM(estrellas_caja)\n"
                    + "AS estrellas\n"
                    + "FROM caja_pedido, pedido\n"
                    + "WHERE pedido.fecha_pago IS NOT NULL\n"
                    + "AND caja_pedido.id_pedido=" + pedido.getId_pedido() + ";");
            if (consulta.next()) {
                cantidad = consulta.getInt("estrellas");
            } else {
                JOptionPane.showMessageDialog(null, "No se pudo obtener cant de estrellas\nEl pedido puede no estar pago");
                System.out.println("No se pudo obtener cant de estrellas\nEl pedido puede no estar pago");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al realizar la consulta");
            System.out.println(e.getMessage() + "Error al realizar la consulta");
        }
        //pedido.setEstrellas_pedido(this.cantEstrellasPedido(pedido));
        return cantidad;
    }

//##############################################################################
//################ CANT ESTRELLAS PEDIDOS PAGOS DE X REVENDEDOR ################
    public double cantEstrellasPedidosPagosRevendedor(Revendedor revendedor) {

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
//################### LISTA DE PEDIDOS DE X CAMPAÑA ############################

    public List<Pedido> listaPedidosCampaña(Campaña campaña) {

        List<Pedido> pedidos = new ArrayList();
        Pedido pedido;
        Revendedor revendedor;
        int id_revendedor;

        try {
            Statement statement = c.createStatement();
            ResultSet consulta = statement.executeQuery("SELECT * FROM pedido WHERE id_campaña=" + campaña.getId_campaña() + ";");

            if (consulta.next()) {
                consulta.beforeFirst();
                while (consulta.next()) {

                    pedido = new Pedido();
                    pedido.setId_pedido(consulta.getInt("id_pedido"));
                    pedido.setFecha_ingreso(consulta.getDate("fecha_ingreso").toLocalDate());
                    if (consulta.getDate("fecha_entrega") != null) {
                        pedido.setFecha_entrega(consulta.getDate("fecha_entrega").toLocalDate());
                    } else {
                        System.out.println("No hay fecha de entrega aún para el pedido con id: " + consulta.getInt("id_pedido"));
                    }

                    if (consulta.getDate("fecha_pago") != null) {
                        pedido.setFecha_pago(consulta.getDate("fecha_pago").toLocalDate());
                    } else {
                        System.out.println("No hay fecha de pago aún para el pedido con id: " + consulta.getInt("id_pedido"));
                    }

                    pedido.setCantidad_cajas(this.cantCajasPedido(pedido));
                    pedido.setEstrellas_pedido(this.cantEstrellasPedido(pedido));
                    pedido.setActivo(consulta.getBoolean("activo"));
                    id_revendedor = consulta.getInt("id_revendedor");
                    revendedor = this.buscarRevendedor(id_revendedor);
                    pedido.setRevendedor(revendedor);
                    pedido.setCampaña(campaña);
                    pedidos.add(pedido);
                }
            } else {
                System.out.println("No se pudo obtener lista de pedidos");
                JOptionPane.showMessageDialog(null, "No se pudo obtener lista de pedidos");
            }
            statement.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al obtener lista de pedidos");
            System.out.println(e.getMessage());
        }

        return pedidos;
    }
//##############################################################################
//################### LISTA DE PEDIDOS PAGOS DE X CAMPAÑA ######################

    public List<Pedido> listaPedidosPagosCampaña(Campaña campaña) {

        List<Pedido> pedidos = new ArrayList();
        Pedido pedido;
        Revendedor revendedor;
        int id_revendedor;

        try {
            Statement statement = c.createStatement();
            ResultSet consulta = statement.executeQuery("SELECT * FROM pedido WHERE fecha_pago IS NOT NULL"
                    + " AND id_campaña=" + campaña.getId_campaña() + ";");

            if (consulta.next()) {
                consulta.beforeFirst();
                while (consulta.next()) {

                    pedido = new Pedido();
                    pedido.setId_pedido(consulta.getInt("id_pedido"));
                    pedido.setFecha_ingreso(consulta.getDate("fecha_ingreso").toLocalDate());
                    if (consulta.getDate("fecha_entrega") != null) {
                        pedido.setFecha_entrega(consulta.getDate("fecha_entrega").toLocalDate());
                    } else {
                        System.out.println("No hay fecha de entrega aún para el pedido con id: " + consulta.getInt("id_pedido"));
                    }

                    if (consulta.getDate("fecha_pago") != null) {
                        pedido.setFecha_pago(consulta.getDate("fecha_pago").toLocalDate());
                    } else {
                        System.out.println("No hay fecha de pago aún para el pedido con id: " + consulta.getInt("id_pedido"));
                    }

                    pedido.setCantidad_cajas(this.cantCajasPedido(pedido));
                    pedido.setEstrellas_pedido(this.cantEstrellasPedido(pedido));
                    pedido.setActivo(consulta.getBoolean("activo"));
                    id_revendedor = consulta.getInt("id_revendedor");
                    revendedor = this.buscarRevendedor(id_revendedor);
                    pedido.setRevendedor(revendedor);
                    pedido.setCampaña(campaña);
                    pedidos.add(pedido);
                }
            } else {
                System.out.println("No se pudo obtener lista de pedidos");
                JOptionPane.showMessageDialog(null, "No se pudo obtener lista de pedidos");
            }
            statement.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al obtener lista de pedidos");
            System.out.println(e.getMessage());
        }

        return pedidos;
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
            if (consulta.next()) {
                cantidad = consulta.getInt("cajas");
            } else {
                JOptionPane.showMessageDialog(null, "No se pudo obtener cantidad de cajas");
                System.out.println("No se pudo obtener cantidad de cajas");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al realizar la consulta");
            System.out.println(e.getMessage());
        }
        //pedido.setCantidad_cajas(this.cantCajasPedido(pedido));
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
            if (consulta.next()) {
                cantidad = consulta.getInt("cajas");
            } else {
                JOptionPane.showMessageDialog(null, "No se pudo obtener cantidad de cajas");
                System.out.println("No se pudo obtener cantidad de cajas");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al realizar la consulta");
            System.out.println(e.getMessage());
        }
        //pedido.setCantidad_cajas(this.cantCajasPedido(pedido));
        return cantidad;
    }
//##############################################################################
//################### CANTIDAD DE CAJAS DE PEDIDO PAGO #######################

    public int cantCajasPedidoPago(Pedido pedido) {

        int cantidad = 0;

        try {

            Statement statement = c.createStatement();
            ResultSet consulta = statement.executeQuery("SELECT COUNT(*) "
                    + "AS cajas "
                    + "FROM caja_pedido, pedido "
                    + "WHERE pedido.fecha_pago IS NOT NULL\n"
                    + "AND caja_pedido.id_pedido="
                    + pedido.getId_pedido() + ";");
            if (consulta.next()) {
                cantidad = consulta.getInt("cajas");
            } else {
                JOptionPane.showMessageDialog(null, "No se pudo obtener cantidad de cajas");
                System.out.println("No se pudo obtener cantidad de cajas");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al realizar la consulta");
            System.out.println(e.getMessage());
        }
        //pedido.setCantidad_cajas(this.cantCajasPedido(pedido));
        return cantidad;
    }
//##############################################################################
//################ ACTUALIZAR FECHA DE ENTREGA #################################

    public void actualizarFechaEntrega(Pedido pedido, LocalDate fecha_entrega) {

        if (pedido.getFecha_ingreso().isBefore(fecha_entrega)) {

            try {
                Statement statement = c.createStatement();
                int celAfectadas = statement.executeUpdate("UPDATE pedido "
                        + "SET fecha_entrega='" + Date.valueOf(fecha_entrega)
                        + "' WHERE pedido.id_pedido=" + pedido.getId_pedido() + ";");
                if (celAfectadas > 0) {
                    System.out.println("Fecha de entrega cargada");
                    JOptionPane.showMessageDialog(null, "Fecha de entrega cargada");
                } else {
                    System.out.println("No se pudo cargar la fecha de entrega");
                    JOptionPane.showMessageDialog(null, "No se pudo cargar la fecha de entrega");
                }
                statement.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error al cargar la fecha de entrega");
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("La fecha ingresada es anterior a la fecha de ingreso del pedido");
            JOptionPane.showMessageDialog(null, "La fecha ingresada es anterior a la fecha de ingreso del pedido");
        }
    }
//##############################################################################
//################### ACTUALIZAR FECHA DE PAGO #################################

    public void actualizarFechaPago(Pedido pedido, LocalDate fecha_pago) {
        // DEBERÍAMOS INFERIR EL ATRIBUTO PAGO(bool) DE LA BD

        if (pedido.getFecha_entrega() != null && pedido.getFecha_entrega().isBefore(fecha_pago)) {
            Period p = Period.between(pedido.getFecha_entrega(), fecha_pago);
            if (p.getDays() <= 10) {
                try {
                    Statement statement = c.createStatement();
                    int celAfectadas = statement.executeUpdate("UPDATE pedido "
                            + "SET fecha_pago='" + Date.valueOf(fecha_pago)
                            + "' WHERE pedido.id_pedido=" + pedido.getId_pedido() + ";");
                    if (celAfectadas > 0) {
                        System.out.println("Fecha de pago cargada");
                        JOptionPane.showMessageDialog(null, "Fecha de pago cargada");
                    } else {
                        System.out.println("No se pudo cargar la fecha de pago");
                        JOptionPane.showMessageDialog(null, "No se pudo cargar la fecha de pago");
                    }
                    statement.close();
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, "Error al cargar la fecha de pago");
                    System.out.println(e.getMessage());
                }
            } else {
                System.out.println("Ya pasaron los 10 días hábiles para poder pagar el pedido");
                JOptionPane.showMessageDialog(null, "Ya pasaron los 10 días hábiles para poder pagar el pedido");
            }
        } else {
            System.out.println("La fecha ingresada es anterior a la fecha de entrega del pedido\no no hay fecha de entrega");
            JOptionPane.showMessageDialog(null, "La fecha ingresada es anterior a la fecha de entrega del pedido\no no hay fecha de entrega");
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
//##############################################################################
//########################### MÉTODOS AUXILIARES ###############################

    public Revendedor buscarRevendedor(int id) {
        RevendedorData rd = new RevendedorData(conexion);
        return rd.buscarRevendedor(id);
    }

    public Campaña buscarCampaña(int id) {
        CampañaData cd = new CampañaData(conexion);
        return cd.buscarCampaña(id);
    }

    public Campaña buscarCampañaActiva() {
        CampañaData cd = new CampañaData(conexion);
        return cd.buscarCampañaActiva();
    }
//##############################################################################

}
