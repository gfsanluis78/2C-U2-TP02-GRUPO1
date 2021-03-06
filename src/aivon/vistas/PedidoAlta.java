/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aivon.vistas;

import aivon.entidades.Campaña;
import aivon.entidades.DetallePedido;
import aivon.entidades.Pedido;
import aivon.entidades.Producto;
import aivon.entidades.Revendedor;
import aivon.modelos.CampañaData;
import aivon.modelos.Conexion;
import aivon.modelos.DetallePedidoData;
import aivon.modelos.PedidoData;
import aivon.modelos.ProductoData;
import aivon.modelos.RevendedorData;
import java.sql.Date;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Ezequiel
 */
public class PedidoAlta extends javax.swing.JInternalFrame {
    private Conexion conexion;
    
    private RevendedorData rd;
    private PedidoData ped;
    private DetallePedidoData dpd;
    private ProductoData pd;
    private CampañaData cd;
    
    private Revendedor revendedor = null;
    private Campaña campaña = null;
    private Pedido pedido = null;
    
    private DefaultTableModel modelo;
    private DefaultTableModel modelo_pedido;
    
    private double total_costo_lista=0;
    private double total_costo_publico=0;
    
    /**
     * Creates new form PedidoAlta
     */
    public PedidoAlta() {
        initComponents();
        try {
            conexion = new Conexion();
            rd = new RevendedorData(conexion);
            cd = new CampañaData(conexion);
            ped = new PedidoData(conexion);
            pd = new ProductoData(conexion);
            dpd = new DetallePedidoData(conexion);
            
            modelo = new DefaultTableModel();
            modelo_pedido = new DefaultTableModel();
            
            campaña = cd.buscarCampañaActiva();
            jl_campaña_activa.setText(campaña.toString());
            jl_fechas_camp.setText(campaña.getFecha_inicio()+" a "+campaña.getFecha_fin());
            
            armaCabeceraTabla();
            cargaProductos();
            
            armaCabeceraTablaPedido();
            borraFilasTablaPedido();
            
            
            jb_crear_pedido.setEnabled(false);
            jdc_fecha_ingreso.setEnabled(false);
            jt_prod_pedidos.setEnabled(false);
            jb_agregar_producto.setEnabled(false);
            js_cantidad.setEnabled(false);
            
            jb_ordenar.setEnabled(false);
            jtf_dni.requestFocus();
            
        } catch (Exception e) {
        }
    }
    
    
    //######################## TABLA DE PRODUCTOS ##############################
   
    private void armaCabeceraTabla() {
        //Titulos de Columnas
        ArrayList<Object> columnas = new ArrayList<>();
        columnas.add("ID");
        columnas.add("Producto");
        columnas.add("Uso");
        columnas.add("Volumen (cm3)");
        columnas.add("Costo Lista");
        columnas.add("Costo Público");
        columnas.add("Estrellas");
        
        columnas.forEach((it) -> {
            modelo.addColumn(it);
        });
        jt_productos.setModel(modelo);
        jt_productos.getColumnModel().getColumn(0).setMaxWidth(0);
        jt_productos.getColumnModel().getColumn(0).setMinWidth(0);
        jt_productos.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(0);
        jt_productos.getTableHeader().getColumnModel().getColumn(0).setMinWidth(0);
    }
    
    private void borraFilasTabla() {
        int a = modelo.getRowCount() - 1;
        for (int i = a; i >= 0; i--) {
            modelo.removeRow(i);
        }
    }
    
    private void cargaProductos() {
        this.borraFilasTabla();

        List<Producto> lista = pd.buscarProductosActivos();
        lista.forEach((p) -> {
            modelo.addRow(new Object[]{p.getId_producto(), p.getNombre(), p.getUso(), p.getTamaño(), p.getCosto(), p.getCosto_publico(), p.getEstrellas()});
//            if (p.isActivo()) {
//                modelo.addRow(new Object[]{p.getId_producto(), p.getNombre(), p.getUso(), p.getTamaño(), p.getCosto(), p.getCosto_publico(), p.getEstrellas(), "Sí"});
//            } else {
//                modelo.addRow(new Object[]{p.getId_producto(), p.getNombre(), p.getUso(), p.getTamaño(), p.getCosto(), p.getCosto_publico(), p.getEstrellas(), "No"});
//            }
            
        });

    }
    
    private void cargaProductosPorNombre(String nombre) {

        List<Producto> lista = pd.buscarProductosActivosPorNombre(nombre);

        if (lista.size() > 0) {
            this.borraFilasTabla();
            lista.forEach((p) -> {
                modelo.addRow(new Object[]{p.getId_producto(), p.getNombre(), p.getUso(), p.getTamaño(), p.getCosto(), p.getCosto_publico(), p.getEstrellas()});
            });
        }
    }
    
    //###################### FIN TABLA PRODUCTOS ###############################
    //--------------------------------------------------------------------------
    //################ TABLA DE PRODUCTOS PEDIDOS ##############################
   
    private void armaCabeceraTablaPedido() {
        //Titulos de Columnas
        ArrayList<Object> columnas = new ArrayList<>();
        columnas.add("ID");
        columnas.add("Producto");
        columnas.add("Costo Lista");
        columnas.add("Costo Público");
        columnas.add("Cantidad");
        
        columnas.forEach((it) -> {
            modelo_pedido.addColumn(it);
        });
        jt_prod_pedidos.setModel(modelo_pedido);
        jt_prod_pedidos.getColumnModel().getColumn(0).setMaxWidth(0);
        jt_prod_pedidos.getColumnModel().getColumn(0).setMinWidth(0);
        jt_prod_pedidos.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(0);
        jt_prod_pedidos.getTableHeader().getColumnModel().getColumn(0).setMinWidth(0);
           
    }
    
    private void borraFilasTablaPedido() {
        int a = modelo_pedido.getRowCount() - 1;
        for (int i = a; i >= 0; i--) {
            modelo_pedido.removeRow(i);
        }
    }
    
    private void cargaDetallePedido(DetallePedido det_ped) {

        modelo_pedido.addRow(new Object[]{det_ped.getProducto().getId_producto(), det_ped.getProducto().getNombre(), det_ped.getProducto().getCosto(), det_ped.getProducto().getCosto_publico(), det_ped.getCantidad_producto()});
        
        total_costo_lista+=(det_ped.getProducto().getCosto()*det_ped.getCantidad_producto());
        total_costo_publico+=(det_ped.getProducto().getCosto_publico()*det_ped.getCantidad_producto());
        
        jl_total_costo_lista.setText(String.valueOf(total_costo_lista));
        jl_total_costo_publico.setText(String.valueOf(total_costo_publico));


    }
    
    private void eliminarDetallePedidoSel() {
        int fila_sel = jt_prod_pedidos.getSelectedRow();

        if (fila_sel >= 0) {

            total_costo_lista -= (Double.parseDouble(jt_prod_pedidos.getValueAt(fila_sel, 2).toString()) * Integer.parseInt(jt_prod_pedidos.getValueAt(fila_sel, 4).toString()));
            total_costo_publico -= (Double.parseDouble(jt_prod_pedidos.getValueAt(fila_sel, 3).toString()) * Integer.parseInt(jt_prod_pedidos.getValueAt(fila_sel, 4).toString()));
            jl_total_costo_lista.setText(String.valueOf(total_costo_lista));
            jl_total_costo_publico.setText(String.valueOf(total_costo_publico));
            modelo_pedido.removeRow(jt_prod_pedidos.getSelectedRow());
//            jt_prod_pedidos.remove(fila_sel);
//            jt_prod_pedidos.repaint();
        } else {
            JOptionPane.showMessageDialog(this, "Debe seleccionar una entrada a eliminar");
            jt_prod_pedidos.requestFocus();
        }
    }
    
    //###################### FIN TABLA PRODUCTOS PEDIDOS #######################
    //--------------------------------------------------------------------------
    //######### GENERANDO DETALLES PEDIDO DESDE TABLA PRODUCTOS PEDIDOS ########
    
    private void generarDetallesPedido() {
        DetallePedido det_ped;
        ped.altaPedido(pedido);
        int a = modelo_pedido.getRowCount() - 1;
        for (int i = a; i >= 0; i--) {
            Producto prod = pd.buscarProducto(Integer.parseInt(jt_prod_pedidos.getValueAt(i, 0).toString()));
            System.out.println(prod.toString());
            int cant = Integer.parseInt(jt_prod_pedidos.getValueAt(i, 4).toString());
            System.out.println(cant);
            det_ped = new DetallePedido(pedido, prod, cant);
            dpd.pedirProducto(det_ped);
        }
        //JOptionPane.showMessageDialog(this, "Pedido cargado");
        this.resetearVista();
    }
    //###### FIN GENERANDO DETALLES PEDIDO DESDE TABLA PRODUCTOS PEDIDOS #######
    //--------------------------------------------------------------------------
    //######################### HERRAMIENTAS ###################################
    
    private void resetearVista() {
        jb_ordenar.setEnabled(false);
        jb_eliminar_det_ped.setEnabled(false);
        this.borraFilasTablaPedido();
        jt_prod_pedidos.setEnabled(false);
        jb_agregar_producto.setEnabled(false);
        js_cantidad.setEnabled(false);
        jb_crear_pedido.setEnabled(false);
        jdc_fecha_ingreso.setEnabled(false);
        jdc_fecha_ingreso.setDate(null);
        jtf_activo.setText("");
        jtf_apellido_rev.setText("");
        jtf_nombre_rev.setText("");
        jtf_dni.setText("");
        revendedor = null;
        pedido = null;
        campaña = null;
        jtf_dni.setEditable(true);
        jtf_dni.requestFocus();
        this.total_costo_lista=0;
        this.total_costo_publico=0;
        jl_total_costo_lista.setText("");
        jl_total_costo_publico.setText("");
        jl_monto_min.setText("");
        jl_monto_max.setText("");
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jtf_dni = new javax.swing.JTextField();
        jb_buscar_revendedor = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jtf_nombre_rev = new javax.swing.JTextField();
        jtf_apellido_rev = new javax.swing.JTextField();
        jtf_activo = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jb_crear_pedido = new javax.swing.JButton();
        jdc_fecha_ingreso = new com.toedter.calendar.JDateChooser();
        jLabel7 = new javax.swing.JLabel();
        jb_cancelar = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane2 = new javax.swing.JScrollPane();
        jt_prod_pedidos = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jt_productos = new javax.swing.JTable();
        jb_ordenar = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        js_cantidad = new javax.swing.JSpinner();
        jLabel8 = new javax.swing.JLabel();
        jb_agregar_producto = new javax.swing.JButton();
        jb_eliminar_det_ped = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jl_total_costo_lista = new javax.swing.JLabel();
        jl_total_costo_publico = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jl_monto_min = new javax.swing.JLabel();
        jl_monto_max = new javax.swing.JLabel();
        jb_salir = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        jtf_buscar_por_nombre = new javax.swing.JTextField();
        jb_buscar_por_nombre = new javax.swing.JButton();
        jb_reiniciar_tabla_productos = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        jl_campaña_activa = new javax.swing.JLabel();
        jl_fechas_camp = new javax.swing.JLabel();

        jLabel1.setText("DNI");

        jtf_dni.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jtf_dniKeyTyped(evt);
            }
        });

        jb_buscar_revendedor.setText("BUSCAR REVENDEDOR");
        jb_buscar_revendedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jb_buscar_revendedorActionPerformed(evt);
            }
        });

        jLabel2.setText("NOMBRE");

        jtf_nombre_rev.setEditable(false);

        jtf_apellido_rev.setEditable(false);

        jtf_activo.setEditable(false);

        jLabel3.setText("APELLIDO");

        jLabel4.setText("ACTIVO");

        jb_crear_pedido.setText("CREAR PEDIDO");
        jb_crear_pedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jb_crear_pedidoActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel7.setText("FECHA INGRESO");

        jb_cancelar.setText("REINICIAR");
        jb_cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jb_cancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jtf_activo)
                                .addComponent(jtf_apellido_rev)
                                .addComponent(jtf_nombre_rev, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jtf_dni, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jb_buscar_revendedor)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jb_cancelar))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jdc_fecha_ingreso, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jb_crear_pedido, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(26, 26, 26))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtf_dni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jb_buscar_revendedor)
                    .addComponent(jb_cancelar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jtf_nombre_rev, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtf_apellido_rev, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtf_activo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(18, 21, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jb_crear_pedido, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jdc_fecha_ingreso, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator1.setToolTipText("");

        jt_prod_pedidos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(jt_prod_pedidos);

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel5.setText("ALTA PEDIDO");

        jt_productos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jt_productos);

        jb_ordenar.setText("ALTA PEDIDO");
        jb_ordenar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jb_ordenarActionPerformed(evt);
            }
        });

        jLabel6.setText("PRODUCTOS DISPONIBLES");

        js_cantidad.setModel(new javax.swing.SpinnerNumberModel(1, 1, 10, 1));

        jLabel8.setText("DEL PRODUCTO SELECCIONADO");

        jb_agregar_producto.setText("AGREGAR");
        jb_agregar_producto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jb_agregar_productoActionPerformed(evt);
            }
        });

        jb_eliminar_det_ped.setText("ELIMINAR PRODUCTO");
        jb_eliminar_det_ped.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jb_eliminar_det_pedActionPerformed(evt);
            }
        });

        jLabel10.setText("Total costo lista:");

        jLabel11.setText("Total costo público:");

        jl_total_costo_lista.setText("0.00");

        jl_total_costo_publico.setText("0.00");

        jLabel9.setText("Monto Min");

        jLabel12.setText("Monto Max");

        jl_monto_max.setText("     ");

        jb_salir.setText("SALIR");
        jb_salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jb_salirActionPerformed(evt);
            }
        });

        jLabel13.setText("BUSCAR POR NOMBRE");

        jb_buscar_por_nombre.setText("BUSCAR");
        jb_buscar_por_nombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jb_buscar_por_nombreActionPerformed(evt);
            }
        });

        jb_reiniciar_tabla_productos.setText("REINICIAR LISTA");
        jb_reiniciar_tabla_productos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jb_reiniciar_tabla_productosActionPerformed(evt);
            }
        });

        jLabel14.setText("Campaña activa:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jl_fechas_camp, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                            .addComponent(jl_campaña_activa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(31, 31, 31))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 469, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 3, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                            .addComponent(jb_eliminar_det_ped)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jb_ordenar))
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 466, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jl_total_costo_lista, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jl_total_costo_publico, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(63, 63, 63)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel12))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jl_monto_max, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jl_monto_min, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(57, 57, 57)))))
                        .addGap(21, 21, 21)))
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jb_salir)
                        .addGap(45, 45, 45))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(207, 207, 207)
                                .addComponent(jLabel6))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(132, 132, 132)
                                .addComponent(jb_agregar_producto)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(js_cantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel8))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 550, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(38, 38, 38)
                                .addComponent(jLabel13)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jtf_buscar_por_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jb_buscar_por_nombre)
                                .addGap(18, 18, 18)
                                .addComponent(jb_reiniciar_tabla_productos)))
                        .addGap(13, 13, 13))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(jLabel6)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(jtf_buscar_por_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jb_buscar_por_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jb_reiniciar_tabla_productos, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 441, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(js_cantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8)
                            .addComponent(jb_agregar_producto))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                        .addComponent(jb_salir))
                    .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(18, 18, 18))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel14)
                                    .addComponent(jl_campaña_activa, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jl_fechas_camp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jl_monto_min, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel9))
                                .addGap(2, 2, 2)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel12)
                                    .addComponent(jl_monto_max)))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jl_total_costo_lista)
                                    .addGap(2, 2, 2)
                                    .addComponent(jl_total_costo_publico))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel10)
                                    .addGap(2, 2, 2)
                                    .addComponent(jLabel11))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jb_ordenar)
                            .addComponent(jb_eliminar_det_ped))))
                .addGap(24, 24, 24))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jtf_dniKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtf_dniKeyTyped
        // TODO add your handling code here:
        char caracter = evt.getKeyChar();
        if (!(((caracter < '0') || (caracter > '9')) && (caracter != '\b'))) {
        } else {
            evt.consume();
        }
    }//GEN-LAST:event_jtf_dniKeyTyped

    private void jb_buscar_revendedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jb_buscar_revendedorActionPerformed
        // TODO add your handling code here:
        if (!"".equals(jtf_dni.getText())) {
           revendedor = rd.buscarRevendedor(jtf_dni.getText());
        
        if (revendedor!=null) {
            jtf_dni.setEditable(false);
            jtf_nombre_rev.setText(revendedor.getNombre());
            jtf_apellido_rev.setText(revendedor.getApellido());
            
            if (revendedor.isActivo()) {
                jtf_activo.setText("Sí");
            } else {
                jtf_activo.setText("No");
            }
            jdc_fecha_ingreso.setEnabled(true);
            jdc_fecha_ingreso.setDate(Date.valueOf(LocalDate.now()));
            jb_crear_pedido.setEnabled(true);
        } else {
            jtf_dni.setText("");
            jtf_dni.requestFocus();
        } 
        } else {
            jtf_dni.requestFocus();
        }
        
        
    }//GEN-LAST:event_jb_buscar_revendedorActionPerformed

    private void jb_crear_pedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jb_crear_pedidoActionPerformed
        // BOTON CREAR PEDIDO
        if (revendedor.isActivo()) {
            
        
        if (jdc_fecha_ingreso.getDate() != null) {
            jdc_fecha_ingreso.setEnabled(false);
            campaña = cd.buscarCampañaActiva();

            if (campaña != null) {

                pedido = ped.buscarPedido(revendedor.getId_revendedor(), campaña.getId_campaña());

                LocalDate fecha_inicio = campaña.getFecha_inicio();
                LocalDate fecha_fin = campaña.getFecha_fin();
                LocalDate fecha_ingreso = LocalDateTime.ofInstant(jdc_fecha_ingreso.getDate().toInstant(), ZoneId.systemDefault()).toLocalDate();

                if (fecha_ingreso.isEqual(fecha_inicio) || fecha_ingreso.isEqual(fecha_fin) || (fecha_ingreso.isAfter(fecha_inicio) && fecha_ingreso.isBefore(fecha_fin))) {

                    if (pedido == null) {
                        pedido = new Pedido();
                        pedido.setRevendedor(revendedor);
                        pedido.setCampaña(campaña);
                        pedido.setFecha_ingreso(fecha_ingreso);
                        pedido.setActivo(true);
                        
                        jt_prod_pedidos.setEnabled(true);
                        jb_agregar_producto.setEnabled(true);
                        js_cantidad.setEnabled(true);
                        jb_ordenar.setEnabled(true);
                        jl_monto_min.setText(String.valueOf(rd.calcularMontoMinimoRevendedor(revendedor)));
                        jl_monto_max.setText(String.valueOf(rd.calcularMontoMaximoRevendedor(revendedor)));
                    } else {
                        JOptionPane.showMessageDialog(this, "Ya existe un pedido de este revendedor\nen la campaña activa");

                        jtf_dni.requestFocus();
                    }

                } else {
                    JOptionPane.showMessageDialog(this, "La fecha ingresada está fuera del rango\nde la campaña activa");
                }

            } else {
                JOptionPane.showMessageDialog(this, "No se encontró campaña activa");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Debe elegir una fecha de ingreso");
            jdc_fecha_ingreso.requestFocus();
        }
    }else {
            JOptionPane.showMessageDialog(this, "El revendedor está marcado como inactivo\nno puede realizar pedidos");
            jdc_fecha_ingreso.requestFocus();
        }
    }//GEN-LAST:event_jb_crear_pedidoActionPerformed

    private void jb_agregar_productoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jb_agregar_productoActionPerformed
        // BOTON AGREGAR PRODUCTO A LISTA
        int fila_prod_sel = jt_productos.getSelectedRow();

        if (fila_prod_sel > -1) {
            int id_producto = Integer.parseInt(jt_productos.getValueAt(fila_prod_sel, 0).toString());
            Producto producto = pd.buscarProducto(id_producto);
            int cant_prod = Integer.parseInt(js_cantidad.getValue().toString());

            if (pedido != null) {
                DetallePedido detalle_pedido = new DetallePedido(pedido, producto, cant_prod);
                this.cargaDetallePedido(detalle_pedido);
                js_cantidad.getModel().setValue(1);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Debe elegir un producto de la lista");
            jt_productos.requestFocus();
        }

    }//GEN-LAST:event_jb_agregar_productoActionPerformed

    private void jb_eliminar_det_pedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jb_eliminar_det_pedActionPerformed
        // TODO add your handling code here:
        this.eliminarDetallePedidoSel();
    }//GEN-LAST:event_jb_eliminar_det_pedActionPerformed

    private void jb_ordenarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jb_ordenarActionPerformed
        // TODO add your handling code here:
        int cant_det_ped = modelo_pedido.getRowCount();

        if (cant_det_ped > 0) {
            if (total_costo_lista < rd.calcularMontoMinimoRevendedor(revendedor)) {
                JOptionPane.showMessageDialog(this, "El monto a pagar está por debajo de su monto mínimo");
            } else {
                if (total_costo_lista > rd.calcularMontoMaximoRevendedor(revendedor)) {
                    JOptionPane.showMessageDialog(this, "El monto a pagar está por encima de su monto máximo");
                } else {
                    // ACÁ VA EL CÓDIGO PARA GENERAR LOS DETALLE_PEDIDO
                    int respuesta = JOptionPane.showConfirmDialog(this, "¿Seguro que desea cargar estos productos?", "Pedir productos", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

                    if (respuesta == 0) {
                        this.generarDetallesPedido();
                    }

                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "No ha agregado productos a la lista");
            jt_productos.requestFocus();
        }


    }//GEN-LAST:event_jb_ordenarActionPerformed

    private void jb_cancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jb_cancelarActionPerformed
        // BOTON REINICIAR
        this.resetearVista();
        
    }//GEN-LAST:event_jb_cancelarActionPerformed

    private void jb_salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jb_salirActionPerformed
        // TODO add your handling code here:
        this.resetearVista();
        this.dispose();
    }//GEN-LAST:event_jb_salirActionPerformed

    private void jb_buscar_por_nombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jb_buscar_por_nombreActionPerformed
        // TODO add your handling code here:
        if ("".equals(jtf_buscar_por_nombre.getText())) {
            this.cargaProductos();
            jtf_buscar_por_nombre.setText("");
            jtf_buscar_por_nombre.requestFocus();
        } else {
            this.cargaProductosPorNombre(jtf_buscar_por_nombre.getText());
        }
    }//GEN-LAST:event_jb_buscar_por_nombreActionPerformed

    private void jb_reiniciar_tabla_productosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jb_reiniciar_tabla_productosActionPerformed
        // TODO add your handling code here:
        this.cargaProductos();
        jtf_buscar_por_nombre.setText("");
        jtf_buscar_por_nombre.requestFocus();
    }//GEN-LAST:event_jb_reiniciar_tabla_productosActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JButton jb_agregar_producto;
    private javax.swing.JButton jb_buscar_por_nombre;
    private javax.swing.JButton jb_buscar_revendedor;
    private javax.swing.JButton jb_cancelar;
    private javax.swing.JButton jb_crear_pedido;
    private javax.swing.JButton jb_eliminar_det_ped;
    private javax.swing.JButton jb_ordenar;
    private javax.swing.JButton jb_reiniciar_tabla_productos;
    private javax.swing.JButton jb_salir;
    private com.toedter.calendar.JDateChooser jdc_fecha_ingreso;
    private javax.swing.JLabel jl_campaña_activa;
    private javax.swing.JLabel jl_fechas_camp;
    private javax.swing.JLabel jl_monto_max;
    private javax.swing.JLabel jl_monto_min;
    private javax.swing.JLabel jl_total_costo_lista;
    private javax.swing.JLabel jl_total_costo_publico;
    private javax.swing.JSpinner js_cantidad;
    private javax.swing.JTable jt_prod_pedidos;
    private javax.swing.JTable jt_productos;
    private javax.swing.JTextField jtf_activo;
    private javax.swing.JTextField jtf_apellido_rev;
    private javax.swing.JTextField jtf_buscar_por_nombre;
    private javax.swing.JTextField jtf_dni;
    private javax.swing.JTextField jtf_nombre_rev;
    // End of variables declaration//GEN-END:variables





    
}
