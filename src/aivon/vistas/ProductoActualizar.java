/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aivon.vistas;

import aivon.entidades.Producto;
import aivon.modelos.Conexion;
import aivon.modelos.ProductoData;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
    
/**
 *
 * @author Ezequiel
 */
public class ProductoActualizar extends javax.swing.JInternalFrame {
    private Conexion conexion;
    private ProductoData pd;
    private Producto producto;
    
    
    private DefaultTableModel modelo;
    /**
     * Creates new form ProductoActualizar
     */
    public ProductoActualizar() {
        initComponents();
        try {
            conexion = new Conexion();
            pd = new ProductoData(conexion);
            modelo = new DefaultTableModel();
            armaCabeceraTabla();
            cargaProductos();
            deshabilitarCampos();
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
        columnas.add("Activo");
        
        columnas.forEach((it) -> {
            modelo.addColumn(it);
        });
        jt_productos.setModel(modelo);
    }
    
    private void borraFilasTabla() {
        int a = modelo.getRowCount() - 1;
        for (int i = a; i >= 0; i--) {
            modelo.removeRow(i);
        }
    }
    
    private void cargaProductos() {
        this.borraFilasTabla();

        List<Producto> lista = pd.buscarProductos();
        lista.forEach((p) -> {
            if (p.isActivo()) {
                modelo.addRow(new Object[]{p.getId_producto(), p.getNombre(), p.getUso(), p.getTamaño(), p.getCosto(), p.getCosto_publico(), p.getEstrellas(), "Sí"});
            } else {
                modelo.addRow(new Object[]{p.getId_producto(), p.getNombre(), p.getUso(), p.getTamaño(), p.getCosto(), p.getCosto_publico(), p.getEstrellas(), "No"});
            }
            
        });

    }
    
    private void cargaProductosPorNombre(String nombre) {

        List<Producto> lista = pd.buscarProductosPorNombre(nombre);

        if (lista.size() > 0) {
            this.borraFilasTabla();
            lista.forEach((p) -> {
                if (p.isActivo()) {
                    modelo.addRow(new Object[]{p.getId_producto(), p.getNombre(), p.getUso(), p.getTamaño(), p.getCosto(), p.getCosto_publico(), p.getEstrellas(), "Sí"});
                } else {
                    modelo.addRow(new Object[]{p.getId_producto(), p.getNombre(), p.getUso(), p.getTamaño(), p.getCosto(), p.getCosto_publico(), p.getEstrellas(), "No"});
                }
            });
        }
    }
    
    //###################### FIN TABLA PRODUCTOS ###############################
    //--------------------------------------------------------------------------
    //########################## HERRAMIENTAS ##################################
    
    private void deshabilitarCampos() {
        jtf_nombre.setEnabled(false);
        jtf_uso.setEnabled(false);
        jtf_volumen.setEnabled(false);
        jtf_costo_lista.setEnabled(false);
        jtf_costo_publico.setEnabled(false);
        jtf_estrellas.setEnabled(false);
        jrb_activo_si.setSelected(false);
        jrb_activo_no.setSelected(false);
        jrb_activo_si.setEnabled(false);
        jrb_activo_no.setEnabled(false);
        jb_cargar.setEnabled(false);
        jb_limpiar.setEnabled(false);
        //jb_baja.setEnabled(false);
    }
    
    private void habilitarCampos() {
        jtf_nombre.setEnabled(true);
        jtf_uso.setEnabled(true);
        jtf_volumen.setEnabled(true);
        jtf_costo_lista.setEnabled(true);
        jtf_costo_publico.setEnabled(true);
        jtf_estrellas.setEnabled(true);
        jrb_activo_si.setSelected(true);
        jrb_activo_no.setSelected(true);
        jrb_activo_si.setEnabled(true);
        jrb_activo_no.setEnabled(true);
        jb_cargar.setEnabled(true);
        jb_limpiar.setEnabled(true);
        //jb_baja.setEnabled(true);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bg_jrb_activo = new javax.swing.ButtonGroup();
        jScrollPane1 = new javax.swing.JScrollPane();
        jt_productos = new javax.swing.JTable();
        jSeparator1 = new javax.swing.JSeparator();
        jb_modificar_producto_sel = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jtf_volumen = new javax.swing.JTextField();
        jtf_costo_lista = new javax.swing.JTextField();
        jtf_costo_publico = new javax.swing.JTextField();
        jtf_estrellas = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jb_limpiar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jb_cargar = new javax.swing.JButton();
        jrb_activo_si = new javax.swing.JRadioButton();
        jButton6 = new javax.swing.JButton();
        jrb_activo_no = new javax.swing.JRadioButton();
        jLabel8 = new javax.swing.JLabel();
        jtf_nombre = new javax.swing.JTextField();
        jtf_uso = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jtf_buscar_por_nombre = new javax.swing.JTextField();
        jb_buscar_por_nombre = new javax.swing.JButton();
        jb_reiniciar_tabla_productos = new javax.swing.JButton();

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

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jb_modificar_producto_sel.setText("MODIFICAR PRODUCTO SELECCIONADO");
        jb_modificar_producto_sel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jb_modificar_producto_selActionPerformed(evt);
            }
        });

        jLabel3.setText("USO");

        jLabel4.setText("VOLUMEN (cm3)");

        jLabel5.setText("COSTO LISTA");

        jLabel6.setText("COSTO PÚBLICO");

        jtf_volumen.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jtf_volumenKeyTyped(evt);
            }
        });

        jtf_costo_lista.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jtf_costo_listaKeyTyped(evt);
            }
        });

        jtf_costo_publico.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jtf_costo_publicoKeyTyped(evt);
            }
        });

        jtf_estrellas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jtf_estrellasKeyTyped(evt);
            }
        });

        jLabel7.setText("ESTRELLAS");

        jb_limpiar.setText("LIMPIAR");
        jb_limpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jb_limpiarActionPerformed(evt);
            }
        });

        jLabel2.setText("PRODUCTO");

        jb_cargar.setText("CARGAR");
        jb_cargar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jb_cargarActionPerformed(evt);
            }
        });

        bg_jrb_activo.add(jrb_activo_si);
        jrb_activo_si.setText("SÍ");

        jButton6.setText("SALIR");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        bg_jrb_activo.add(jrb_activo_no);
        jrb_activo_no.setText("NO");

        jLabel8.setText("ACTIVO");

        jtf_nombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jtf_nombreKeyTyped(evt);
            }
        });

        jtf_uso.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jtf_usoKeyTyped(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("ACTUALIZACIÓN DE PRODUCTOS");

        jLabel13.setText("BUSCAR POR NOMBRE");

        jb_buscar_por_nombre.setText("BUSCAR");
        jb_buscar_por_nombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jb_buscar_por_nombreActionPerformed(evt);
            }
        });

        jb_reiniciar_tabla_productos.setText("REINICIAR TABLA");
        jb_reiniciar_tabla_productos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jb_reiniciar_tabla_productosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(37, 37, 37)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 550, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel13)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jtf_buscar_por_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jb_buscar_por_nombre)
                                .addGap(18, 18, 18)
                                .addComponent(jb_reiniciar_tabla_productos)
                                .addGap(70, 70, 70)))
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(21, 21, 21)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel2)
                                        .addComponent(jLabel3)
                                        .addComponent(jLabel4))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                            .addGap(0, 0, Short.MAX_VALUE)
                                            .addComponent(jtf_volumen, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(jtf_uso)
                                        .addComponent(jtf_nombre)))
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel8)
                                        .addComponent(jLabel7))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jtf_estrellas, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                            .addComponent(jrb_activo_si)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(jrb_activo_no))))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel5)
                                        .addComponent(jLabel6))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jtf_costo_publico, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jtf_costo_lista, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addComponent(jb_modificar_producto_sel, javax.swing.GroupLayout.DEFAULT_SIZE, 245, Short.MAX_VALUE)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jb_limpiar)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jb_cargar)))
                            .addComponent(jButton6)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(150, 150, 150)
                        .addComponent(jLabel1)))
                .addContainerGap(37, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jb_modificar_producto_sel)
                        .addGap(73, 73, 73)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jtf_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jtf_uso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jtf_volumen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jtf_costo_lista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(jtf_costo_publico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(jtf_estrellas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(jrb_activo_si)
                            .addComponent(jrb_activo_no))
                        .addGap(40, 40, 40)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jb_limpiar)
                            .addComponent(jb_cargar))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 162, Short.MAX_VALUE)
                        .addComponent(jButton6))
                    .addComponent(jSeparator1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 448, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(jtf_buscar_por_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jb_buscar_por_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jb_reiniciar_tabla_productos, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(32, 32, 32))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jtf_volumenKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtf_volumenKeyTyped
        // TODO add your handling code here:
        char caracter = evt.getKeyChar();
        if (!(((caracter < '0') || (caracter > '9')) && (caracter != '\b'))) {

        } else {

            evt.consume();
        }
        if(!(jtf_volumen.getText().length()<4)){
            evt.consume();
        }
    }//GEN-LAST:event_jtf_volumenKeyTyped

    private void jtf_costo_listaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtf_costo_listaKeyTyped
        // TODO add your handling code here:
        char caracter = evt.getKeyChar();
        if (((caracter < '0')|| (caracter > '9')) && (caracter != '\b') &&(caracter != '.')) {
            evt.consume();
        }
        if(!(jtf_costo_lista.getText().length()<7)){
            evt.consume();
        }
    }//GEN-LAST:event_jtf_costo_listaKeyTyped

    private void jtf_costo_publicoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtf_costo_publicoKeyTyped
        // TODO add your handling code here:
        char caracter = evt.getKeyChar();
        if (((caracter < '0')|| (caracter > '9')) && (caracter != '\b') &&(caracter != '.')) {
            evt.consume();
        }
        if(!(jtf_costo_publico.getText().length()<7)){
            evt.consume();
        }
    }//GEN-LAST:event_jtf_costo_publicoKeyTyped

    private void jtf_estrellasKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtf_estrellasKeyTyped
        // TODO add your handling code here:
        char caracter = evt.getKeyChar();
        if (!(((caracter < '0') || (caracter > '9')) && (caracter != '\b'))) {

        } else {

            evt.consume();
        }
        if(!(jtf_estrellas.getText().length()<3)){
            evt.consume();
        }
    }//GEN-LAST:event_jtf_estrellasKeyTyped

    private void jb_limpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jb_limpiarActionPerformed
        // TODO add your handling code here:
        this.limpiar();
    }//GEN-LAST:event_jb_limpiarActionPerformed

    private void jb_cargarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jb_cargarActionPerformed
        // TODO add your handling code here:
        //#################### VALIDANDO STRINGS ###############################
        if (jtf_nombre.getText().length() > 4) {
            producto.setNombre(jtf_nombre.getText());
            if (jtf_uso.getText().length() > 4) {
                producto.setUso(jtf_uso.getText());
                //#################### VALIDANDO ENTEROS ###############################
                try {
                    int vol = Integer.parseInt(jtf_volumen.getText());
                    if (vol > 0) {
                        producto.setTamaño(vol);

                        try {
                            int estrellas = Integer.parseInt(jtf_estrellas.getText());
                            if (estrellas > 0) {
                                producto.setEstrellas(estrellas);
                                //#################### VALIDANDO DOUBLES ###############################
                                try {
                                    double costo_lista = Double.parseDouble(jtf_costo_lista.getText());
                                    if (costo_lista > 0) {
                                        producto.setCosto(costo_lista);
                                        try {
                                            double costo_publico = Double.parseDouble(jtf_costo_publico.getText());
                                            if (costo_publico > 0) {
                                                producto.setCosto_publico(costo_publico);
                                                //#################### VALIDANDO BOOL Y CARGA ##########################
                                                int respuesta = JOptionPane.showConfirmDialog(this, "¿Seguro que desea modificar el producto?", "Modificar producto", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

                                                if (respuesta == 0) {
                                                    if (jrb_activo_si.isSelected()) {
                                                        producto.setActivo(true);
                                                        pd.modificarProducto(producto); // MODIFICACION DEL PRODUCTO
                                                        this.cargaProductos();
                                                        jtf_buscar_por_nombre.setText("");
                                                        this.limpiar();
                                                    } else if (jrb_activo_no.isSelected()) {
                                                        producto.setActivo(false);
                                                        pd.modificarProducto(producto); // MODIFICACION DEL PRODUCTO
                                                        this.cargaProductos();
                                                        jtf_buscar_por_nombre.setText("");
                                                        this.limpiar();
                                                    } else {
                                                        JOptionPane.showMessageDialog(this, "Debe seleccionar un estado activo o inactivo");
                                                    }
                                                }
                                             
                                                //########################################################################
                                            } else {
                                                JOptionPane.showMessageDialog(this, "Debe ingresar un costo público válido");
                                                jtf_costo_publico.setText("");
                                                jtf_costo_publico.requestFocus();
                                            }
                                        } catch (NumberFormatException e) {
                                            JOptionPane.showMessageDialog(this, "Debe ingresar un costo público válido");
                                            jtf_costo_publico.setText("");
                                            jtf_costo_publico.requestFocus();
                                        }
                                    } else {
                                        JOptionPane.showMessageDialog(this, "Debe ingresar un costo de lista válido");
                                        jtf_costo_lista.setText("");
                                        jtf_costo_lista.requestFocus();
                                    }
                                } catch (NumberFormatException e) {
                                    JOptionPane.showMessageDialog(this, "Debe ingresar un costo de lista válido");
                                    jtf_costo_lista.setText("");
                                    jtf_costo_lista.requestFocus();
                                }
                            } else {
                                JOptionPane.showMessageDialog(this, "Debe ingresar una cantidad de estrellas entera");
                                jtf_estrellas.setText("");
                                jtf_estrellas.requestFocus();
                            }

                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(this, "Debe ingresar una cantidad de estrellas entera");
                            jtf_estrellas.setText("");
                            jtf_estrellas.requestFocus();
                        }
                    } else {
                        JOptionPane.showMessageDialog(this, "Debe ingresar un volumen entero");
                        jtf_volumen.setText("");
                        jtf_volumen.requestFocus();
                    }

                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(this, "Debe ingresar un volumen entero");
                    jtf_volumen.setText("");
                    jtf_volumen.requestFocus();
                }

            } else {
                JOptionPane.showMessageDialog(this, "Debe ingresar una descripción de uso\nde al menos 5 caracteres");
                jtf_uso.requestFocus();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Debe ingresar una descripción del producto\nde al menos 5 caracteres");
            jtf_nombre.requestFocus();
        }
    }//GEN-LAST:event_jb_cargarActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jtf_nombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtf_nombreKeyTyped
        // TODO add your handling code here:
        if(!(jtf_nombre.getText().length()<30)){
            evt.consume();
        }
    }//GEN-LAST:event_jtf_nombreKeyTyped

    private void jtf_usoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtf_usoKeyTyped
        // TODO add your handling code here:
        if(!(jtf_uso.getText().length()<15)){
            evt.consume();
        }
    }//GEN-LAST:event_jtf_usoKeyTyped

    private void jb_modificar_producto_selActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jb_modificar_producto_selActionPerformed
        // BOTON MODIFICAR PRODUCTO SELECCIONADO

        int fila_sel = jt_productos.getSelectedRow();

        if (fila_sel >= 0) {
            int id_producto = Integer.parseInt(jt_productos.getValueAt(fila_sel, 0).toString());

            producto = pd.buscarProducto(id_producto);

            if (producto != null) {
                System.out.println("El estado activo del producto es " + producto.isActivo());
                this.habilitarCampos();
                jtf_nombre.setText(producto.getNombre());
                jtf_uso.setText(producto.getUso());
                jtf_volumen.setText(String.valueOf(producto.getTamaño()));
                jtf_costo_lista.setText(String.valueOf(producto.getCosto()));
                jtf_costo_publico.setText(String.valueOf(producto.getCosto_publico()));
                jtf_estrellas.setText(String.valueOf(producto.getEstrellas()));

                if (producto.isActivo()) {
                    jrb_activo_no.setSelected(false);
                    jrb_activo_si.setSelected(true);

                } else {
                    jrb_activo_si.setSelected(false);
                    jrb_activo_no.setSelected(true);
                }
            }

        } else {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un producto");
            jt_productos.requestFocus();
        }


    }//GEN-LAST:event_jb_modificar_producto_selActionPerformed

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
    private javax.swing.ButtonGroup bg_jrb_activo;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JButton jb_buscar_por_nombre;
    private javax.swing.JButton jb_cargar;
    private javax.swing.JButton jb_limpiar;
    private javax.swing.JButton jb_modificar_producto_sel;
    private javax.swing.JButton jb_reiniciar_tabla_productos;
    private javax.swing.JRadioButton jrb_activo_no;
    private javax.swing.JRadioButton jrb_activo_si;
    private javax.swing.JTable jt_productos;
    private javax.swing.JTextField jtf_buscar_por_nombre;
    private javax.swing.JTextField jtf_costo_lista;
    private javax.swing.JTextField jtf_costo_publico;
    private javax.swing.JTextField jtf_estrellas;
    private javax.swing.JTextField jtf_nombre;
    private javax.swing.JTextField jtf_uso;
    private javax.swing.JTextField jtf_volumen;
    // End of variables declaration//GEN-END:variables

    private void limpiar() {
        
        jtf_nombre.setText("");
        jtf_uso.setText("");
        jtf_volumen.setText("");
        jtf_costo_lista.setText("");
        jtf_costo_publico.setText("");
        jtf_estrellas.setText("");
        jrb_activo_si.setSelected(false);
        jrb_activo_no.setSelected(false);
        jrb_activo_si.setEnabled(false);
        jrb_activo_no.setEnabled(false);
        producto = null;
        jb_cargar.setEnabled(false);
        jb_limpiar.setEnabled(false);
        //jtf_nombre.requestFocus();
    }
}
