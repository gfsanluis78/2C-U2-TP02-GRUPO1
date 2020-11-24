/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aivon.vistas;

import aivon.modelos.*;
import aivon.entidades.*;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Ezequiel
 */
public class PedidoActualizar extends javax.swing.JInternalFrame {
    
    private Conexion conexion;
    private PedidoData ped;
    private RevendedorData rd;
    private CampañaData cd;
    private DetallePedidoData dpd;
    private DefaultTableModel modelo_pedido;
    private Revendedor revendedor;
    private Campaña campaña;
    private Pedido pedido;
    
    /**
     * Creates new form PedidoActualizar
     */
    public PedidoActualizar() {
        initComponents();
        try {
            conexion = new Conexion();
            ped = new PedidoData(conexion);
            rd = new RevendedorData(conexion);
            cd = new CampañaData(conexion);
            dpd = new DetallePedidoData(conexion);
            modelo_pedido = new DefaultTableModel();
            armaCabeceraTablaPedido();
            
            jdc_fecha_ingreso.setEnabled(false);
            jdc_fecha_entrega.setEnabled(false);
            jdc_fecha_pago.setEnabled(false);
            jb_guardar_f_pago.setEnabled(false);
            jb_guardar_f_entrega.setEnabled(false);
            jb_baja_pedido.setEnabled(false);
            jb_actualizar_fecha_entrega.setEnabled(false);
            jb_actualizar_fecha_pago.setEnabled(false);
            
        } catch (Exception e) {
        }
    }
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
    
    private void cargaProductos() {
        this.borraFilasTablaPedido();

        List<DetallePedido> lista = dpd.listaDetallesPedido(pedido);
        lista.forEach((p) -> {
            modelo_pedido.addRow(new Object[]{p.getId_caja(), p.getProducto().getNombre(), p.getCosto_caja(), p.getCosto_caja_publico(), p.getCantidad_producto()});
        });

    }
//############################# DATOS PEDIDO ###################################

    private void cargarPedido() {
        pedido = ped.buscarPedido(revendedor.getId_revendedor(), campaña.getId_campaña());
        
        if (pedido!=null) {
            jtf_id.setText(String.valueOf(pedido.getId_pedido()));
            jtf_id_camp.setText(String.valueOf(pedido.getCampaña().getId_campaña()));
            jtf_cant_cajas.setText(String.valueOf(ped.cantCajasPedido(pedido)));
            jtf_cant_est.setText(String.valueOf(ped.cantEstrellasPedido(pedido)));
            jdc_fecha_ingreso.setDate(Date.valueOf(pedido.getFecha_ingreso()));
            if (pedido.getFecha_entrega()!=null) {
                jdc_fecha_entrega.setDate(Date.valueOf(pedido.getFecha_entrega()));
            }
            if (pedido.getFecha_pago()!=null) {
                jdc_fecha_pago.setDate(Date.valueOf(pedido.getFecha_pago()));
            }
            jb_baja_pedido.setEnabled(true);
            jb_actualizar_fecha_entrega.setEnabled(true);
            jb_actualizar_fecha_pago.setEnabled(true);
            this.cargaProductos();
            
        } else {
            JOptionPane.showMessageDialog(this, "No se encontró pedido del revendedor en la campaña actual");
        }
    }    
    
//############################ HERRAMIENTAS ####################################

    private void limpiar() {
        this.borraFilasTablaPedido();
        jdc_fecha_entrega.setDate(null);
        jdc_fecha_ingreso.setDate(null);
        jdc_fecha_pago.setDate(null);
        jb_guardar_f_entrega.setEnabled(false);
        jb_guardar_f_pago.setEnabled(false);
        jb_actualizar_fecha_entrega.setEnabled(false);
        jb_actualizar_fecha_pago.setEnabled(false);
        jtf_cant_est.setText("");
        jtf_cant_cajas.setText("");
        jtf_id_camp.setText("");
        jtf_id.setText("");
        jtf_apellido_rev.setText("");
        jtf_nombre_rev.setText("");
        jtf_activo.setText("");
        
        jtf_dni.setText("");
        jtf_dni.setEditable(true);
        jtf_dni.requestFocus();
    }    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bg_jrb_ped_activo = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jb_buscar_revendedor = new javax.swing.JButton();
        jtf_dni = new javax.swing.JTextField();
        jtf_nombre_rev = new javax.swing.JTextField();
        jtf_apellido_rev = new javax.swing.JTextField();
        jtf_activo = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jtf_id = new javax.swing.JTextField();
        jtf_id_camp = new javax.swing.JTextField();
        jtf_cant_cajas = new javax.swing.JTextField();
        jtf_cant_est = new javax.swing.JTextField();
        jdc_fecha_entrega = new com.toedter.calendar.JDateChooser();
        jdc_fecha_pago = new com.toedter.calendar.JDateChooser();
        jScrollPane1 = new javax.swing.JScrollPane();
        jt_prod_pedidos = new javax.swing.JTable();
        jb_salir = new javax.swing.JButton();
        jb_actualizar_fecha_pago = new javax.swing.JButton();
        jb_baja_pedido = new javax.swing.JButton();
        jb_guardar_f_pago = new javax.swing.JButton();
        jb_limpiar = new javax.swing.JButton();
        jdc_fecha_ingreso = new com.toedter.calendar.JDateChooser();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jb_actualizar_fecha_entrega = new javax.swing.JButton();
        jb_guardar_f_entrega = new javax.swing.JButton();

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("ACTUALIZACIÓN DE PEDIDOS");

        jb_buscar_revendedor.setText("BUSCAR REVENDEDOR");
        jb_buscar_revendedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jb_buscar_revendedorActionPerformed(evt);
            }
        });

        jtf_dni.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtf_dniActionPerformed(evt);
            }
        });
        jtf_dni.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jtf_dniKeyTyped(evt);
            }
        });

        jtf_nombre_rev.setEditable(false);

        jtf_apellido_rev.setEditable(false);

        jtf_activo.setEditable(false);

        jLabel2.setText("PEDIDO EN CAMPAÑA ACTIVA");

        jLabel3.setText("COD PEDIDO");

        jLabel4.setText("ID CAMPAÑA");

        jLabel5.setText("FECHA ALTA");

        jLabel6.setText("FECHA ENTREGA");

        jLabel7.setText("FECHA PAGO");

        jLabel8.setText("CANT. CAJAS");

        jLabel9.setText("CANT. ESTRELLAS");

        jtf_id.setEditable(false);

        jtf_id_camp.setEditable(false);

        jtf_cant_cajas.setEditable(false);

        jtf_cant_est.setEditable(false);

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
        jScrollPane1.setViewportView(jt_prod_pedidos);

        jb_salir.setText("SALIR");
        jb_salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jb_salirActionPerformed(evt);
            }
        });

        jb_actualizar_fecha_pago.setText("ACTUALIZAR");
        jb_actualizar_fecha_pago.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jb_actualizar_fecha_pagoActionPerformed(evt);
            }
        });

        jb_baja_pedido.setText("BAJA");
        jb_baja_pedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jb_baja_pedidoActionPerformed(evt);
            }
        });

        jb_guardar_f_pago.setText("GUARDAR");
        jb_guardar_f_pago.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jb_guardar_f_pagoActionPerformed(evt);
            }
        });

        jb_limpiar.setText("LIMPIAR");
        jb_limpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jb_limpiarActionPerformed(evt);
            }
        });

        jLabel11.setText("DNI");

        jLabel12.setText("NOMBRE");

        jLabel13.setText("APELLIDO");

        jLabel14.setText("ACTIVO");

        jb_actualizar_fecha_entrega.setText("ACTUALIZAR");
        jb_actualizar_fecha_entrega.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jb_actualizar_fecha_entregaActionPerformed(evt);
            }
        });

        jb_guardar_f_entrega.setText("GUARDAR");
        jb_guardar_f_entrega.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jb_guardar_f_entregaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jtf_dni, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jb_buscar_revendedor))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jtf_activo)
                                .addComponent(jtf_apellido_rev)
                                .addComponent(jtf_nombre_rev, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jtf_cant_est, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jtf_cant_cajas, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jtf_id_camp, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jtf_id, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(73, 73, 73)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(26, 26, 26)
                                        .addComponent(jdc_fecha_pago, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jb_actualizar_fecha_pago, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jb_guardar_f_pago))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel6)
                                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jdc_fecha_ingreso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jdc_fecha_entrega, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(jb_actualizar_fecha_entrega)
                                                .addGap(18, 18, 18)
                                                .addComponent(jb_guardar_f_entrega)))))))
                        .addGap(109, 109, 109))))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 716, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jb_baja_pedido)
                        .addGap(249, 249, 249)
                        .addComponent(jb_limpiar)
                        .addGap(255, 255, 255)
                        .addComponent(jb_salir)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtf_dni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jb_buscar_revendedor)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtf_nombre_rev, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtf_apellido_rev, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtf_activo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14))
                .addGap(29, 29, 29)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jtf_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jtf_id_camp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(jtf_cant_cajas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(jtf_cant_est, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jdc_fecha_ingreso, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(11, 11, 11)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jdc_fecha_entrega, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jb_actualizar_fecha_entrega, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jb_guardar_f_entrega, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jdc_fecha_pago, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jb_actualizar_fecha_pago, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jb_guardar_f_pago, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(11, 11, 11)))
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jb_salir)
                    .addComponent(jb_baja_pedido)
                    .addComponent(jb_limpiar))
                .addGap(21, 21, 21))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jb_buscar_revendedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jb_buscar_revendedorActionPerformed
        // TODO add your handling code here:
        campaña = cd.buscarCampañaActiva();
        if (campaña != null) {
            if (!"".equals(jtf_dni.getText())) {
                revendedor = rd.buscarRevendedor(jtf_dni.getText());

                if (revendedor != null) {
                    jtf_dni.setEditable(false);
                    jtf_nombre_rev.setText(revendedor.getNombre());
                    jtf_apellido_rev.setText(revendedor.getApellido());

                    if (revendedor.isActivo()) {
                        jtf_activo.setText("Sí");
                    } else {
                        jtf_activo.setText("No");
                    }
                    this.cargarPedido();
                } else {
                    jtf_dni.setText("");
                    jtf_dni.requestFocus();
                }
            } else {
                jtf_dni.requestFocus();
            }

        } else {
            JOptionPane.showMessageDialog(this, "No se encontró campaña activa");
        }


    }//GEN-LAST:event_jb_buscar_revendedorActionPerformed

    private void jtf_dniKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtf_dniKeyTyped
        // TODO add your handling code here:
        char caracter = evt.getKeyChar();
        if (!(((caracter < '0') || (caracter > '9')) && (caracter != '\b'))) {
        } else {
            evt.consume();
        }
    }//GEN-LAST:event_jtf_dniKeyTyped

    private void jtf_dniActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtf_dniActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtf_dniActionPerformed

    private void jb_salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jb_salirActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jb_salirActionPerformed

    private void jb_actualizar_fecha_pagoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jb_actualizar_fecha_pagoActionPerformed
        // TODO add your handling code here:
        if (pedido.getFecha_entrega() != null) {
            if (pedido.getFecha_pago() != null) {
                JOptionPane.showMessageDialog(this, "El pedido ya fue pagado");
            } else {
                jdc_fecha_pago.setEnabled(true);
                jdc_fecha_pago.setDate(Date.valueOf(LocalDate.now()));
                jb_guardar_f_pago.setEnabled(true);
            }
        } else {
            JOptionPane.showMessageDialog(this, "El pedido no registra fecha de entrega");
        }
    }//GEN-LAST:event_jb_actualizar_fecha_pagoActionPerformed

    private void jb_actualizar_fecha_entregaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jb_actualizar_fecha_entregaActionPerformed
        // TODO add your handling code here:
        if (pedido.getFecha_ingreso() != null) {
            if (pedido.getFecha_entrega() != null) {
                JOptionPane.showMessageDialog(this, "El pedido ya fue entregado");
            } else {
                jdc_fecha_entrega.setEnabled(true);
                jdc_fecha_entrega.setDate(Date.valueOf(LocalDate.now()));
                jb_guardar_f_entrega.setEnabled(true);
            }
        } else {
            JOptionPane.showMessageDialog(this, "El pedido no registra fecha de ingreso");
        }
        

    }//GEN-LAST:event_jb_actualizar_fecha_entregaActionPerformed

    private void jb_guardar_f_pagoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jb_guardar_f_pagoActionPerformed
        // TODO add your handling code here:
        LocalDate fecha_entrega = pedido.getFecha_entrega();
        
       // if (pedido.getFecha_pago() == null) {
            if (jdc_fecha_pago.getDate() != null) {
                LocalDate fecha_pago = LocalDateTime.ofInstant(jdc_fecha_pago.getDate().toInstant(), ZoneId.systemDefault()).toLocalDate();

                if (fecha_pago.isAfter(fecha_entrega) || fecha_pago.isEqual(fecha_entrega)) {
                    Period p = Period.between(fecha_pago, fecha_entrega);

                    if (p.getDays() < 11) {
                        ped.actualizarFechaPago(pedido, fecha_pago);
                        pedido = ped.buscarPedido(revendedor.getId_revendedor(), campaña.getId_campaña());
                        
                        jdc_fecha_pago.setEnabled(false);
                        jb_guardar_f_pago.setEnabled(false);
                        this.cargarPedido();
                    } else {
                        JOptionPane.showMessageDialog(this, "Ya pasaron los 10 días hábiles para pagar\nEl pedido fue desestimado");

                    }

                } else {
                    JOptionPane.showMessageDialog(this, "La fecha de pago debe ser posterior o igual\na la fecha de entrega del pedido");
                    jdc_fecha_pago.requestFocus();
                }

            } else {
                JOptionPane.showMessageDialog(this, "Debe ingresar una fecha de pago válida");
                jdc_fecha_pago.requestFocus();
            }

       // }

    }//GEN-LAST:event_jb_guardar_f_pagoActionPerformed

    private void jb_guardar_f_entregaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jb_guardar_f_entregaActionPerformed
        // TODO add your handling code here:
        
        if (jdc_fecha_entrega.getDate()!=null) {
            LocalDate fecha_entrega = LocalDateTime.ofInstant(jdc_fecha_entrega.getDate().toInstant(), ZoneId.systemDefault()).toLocalDate();
            LocalDate fecha_ingreso = pedido.getFecha_ingreso();
            
            if (fecha_entrega.isAfter(fecha_ingreso)||fecha_entrega.isEqual(fecha_ingreso)) {
                ped.actualizarFechaEntrega(pedido, fecha_entrega);
                pedido = ped.buscarPedido(revendedor.getId_revendedor(), campaña.getId_campaña());
                jdc_fecha_entrega.setEnabled(false);
                jb_guardar_f_entrega.setEnabled(false);
                this.cargarPedido();
                //jdc_fecha_entrega.setEnabled(false);
                
            } else {
                JOptionPane.showMessageDialog(this, "La fecha de entrega debe ser posterior o igual\na la fecha de ingreso");
                jdc_fecha_entrega.requestFocus();
            }
            
        } else {
            JOptionPane.showMessageDialog(this, "Debe ingresar una fecha de entrega válida");
            jdc_fecha_entrega.requestFocus();
        }
    }//GEN-LAST:event_jb_guardar_f_entregaActionPerformed

    private void jb_baja_pedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jb_baja_pedidoActionPerformed
        // TODO add your handling code here:
        LocalDate fecha_entrega = pedido.getFecha_entrega();
        
        if (fecha_entrega!=null) {
            JOptionPane.showMessageDialog(this, "El pedido ya fue entregado. No se puede cancelar");
            
        } else {
            int respuesta = JOptionPane.showConfirmDialog(this, "¿Seguro que desea eliminar el pedido?", "Eliminar pedido", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

                    if (respuesta == 0) {
                        ped.borrarPedido(pedido);
                        this.limpiar();
                    }
        }
        
    }//GEN-LAST:event_jb_baja_pedidoActionPerformed

    private void jb_limpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jb_limpiarActionPerformed
        // TODO add your handling code here:
        this.limpiar();
    }//GEN-LAST:event_jb_limpiarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup bg_jrb_ped_activo;
    private javax.swing.JLabel jLabel1;
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jb_actualizar_fecha_entrega;
    private javax.swing.JButton jb_actualizar_fecha_pago;
    private javax.swing.JButton jb_baja_pedido;
    private javax.swing.JButton jb_buscar_revendedor;
    private javax.swing.JButton jb_guardar_f_entrega;
    private javax.swing.JButton jb_guardar_f_pago;
    private javax.swing.JButton jb_limpiar;
    private javax.swing.JButton jb_salir;
    private com.toedter.calendar.JDateChooser jdc_fecha_entrega;
    private com.toedter.calendar.JDateChooser jdc_fecha_ingreso;
    private com.toedter.calendar.JDateChooser jdc_fecha_pago;
    private javax.swing.JTable jt_prod_pedidos;
    private javax.swing.JTextField jtf_activo;
    private javax.swing.JTextField jtf_apellido_rev;
    private javax.swing.JTextField jtf_cant_cajas;
    private javax.swing.JTextField jtf_cant_est;
    private javax.swing.JTextField jtf_dni;
    private javax.swing.JTextField jtf_id;
    private javax.swing.JTextField jtf_id_camp;
    private javax.swing.JTextField jtf_nombre_rev;
    // End of variables declaration//GEN-END:variables




}
