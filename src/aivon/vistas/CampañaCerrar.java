/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aivon.vistas;

import aivon.entidades.Campaña;
import aivon.entidades.Producto;
import aivon.entidades.Revendedor;
import aivon.modelos.CampañaData;
import aivon.modelos.Conexion;
import aivon.modelos.ProductoData;
import aivon.modelos.RevendedorData;
import aivon.modelos.SeguimientoData;
import static java.lang.ProcessBuilder.Redirect.to;
import java.sql.Date;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Genaro
 */
public class CampañaCerrar extends javax.swing.JInternalFrame {

    private Conexion conexion;
    private CampañaData cd;
    private RevendedorData rd;
    private Campaña campaña;
    private SeguimientoData sd;

    private DefaultTableModel modelo;

    /**
     * Creates new form ProductoActualizar
     */
    public CampañaCerrar() {
        initComponents();
        try {
            conexion = new Conexion();
            cd = new CampañaData(conexion);
            rd = new RevendedorData(conexion);
            sd=new SeguimientoData(conexion);
            modelo = new DefaultTableModel();
            armaCabeceraTabla();
            cargaCampañas();
            deshabilitarCampos();
            jt_campañas.requestFocus();
        } catch (Exception e) {
        }
    }

    private void armaCabeceraTabla() {
        //Titulos de Columnas
        ArrayList<Object> columnas = new ArrayList<>();
        columnas.add("ID");
        columnas.add("Nombre");
        columnas.add("Fecha de Inicio");
        columnas.add("Fecha de Fin");
        columnas.add("Monto Minimo");
        columnas.add("Monto Maximo");
        columnas.add("Activo");

        columnas.forEach((it) -> {
            modelo.addColumn(it);
        });
        jt_campañas.setModel(modelo);
    }

    private void borraFilasTabla() {
        int a = modelo.getRowCount() - 1;
        for (int i = a; i >= 0; i--) {
            modelo.removeRow(i);
        }
    }

    private void cargaCampañas() {
        this.borraFilasTabla();

        List<Campaña> lista = cd.buscarCampañas();
        lista.forEach((p) -> {
            if (p.isActiva()) {
                modelo.addRow(new Object[]{p.getId_campaña(), p.getNombre(), p.getFecha_inicio(), p.getFecha_fin(), p.getMonto_min(), p.getMonto_max(), "Sí"});
            } else {
                modelo.addRow(new Object[]{p.getId_campaña(), p.getNombre(), p.getFecha_inicio(), p.getFecha_fin(), p.getMonto_min(), p.getMonto_max(), "No"});
            }

        });
        // Instanciamos el TableRowSorter y lo añadimos al JTable
        TableRowSorter<TableModel> elQueOrdena = new TableRowSorter<>(modelo);

        jt_campañas.setRowSorter(elQueOrdena);

    }

    private void deshabilitarCampos() {
        jtf_nombre.setEnabled(false);
        jtf_montoMinimo.setEnabled(false);
        jtf_montoMaximo.setEnabled(false);
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
        jtf_montoMinimo.setEnabled(true);
        jtf_montoMaximo.setEnabled(true);
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
        jt_campañas = new javax.swing.JTable();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel3 = new javax.swing.JLabel();
        jL_montoMinimo = new javax.swing.JLabel();
        jl_montoMaximo = new javax.swing.JLabel();
        jtf_montoMinimo = new javax.swing.JTextField();
        jtf_montoMaximo = new javax.swing.JTextField();
        jb_limpiar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jb_cargar = new javax.swing.JButton();
        jrb_activo_si = new javax.swing.JRadioButton();
        jbt_salir = new javax.swing.JButton();
        jrb_activo_no = new javax.swing.JRadioButton();
        jl_activo = new javax.swing.JLabel();
        jtf_nombre = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jDateChooser_inicio = new com.toedter.calendar.JDateChooser();
        jl_fin = new javax.swing.JLabel();
        jDateChooser_fin = new com.toedter.calendar.JDateChooser();

        jt_campañas.setModel(new javax.swing.table.DefaultTableModel(
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
        jt_campañas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jt_campañasMouseClicked(evt);
            }
        });
        jt_campañas.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
                jt_campañasCaretPositionChanged(evt);
            }
        });
        jScrollPane1.setViewportView(jt_campañas);

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jLabel3.setText("FECHA INICIO");

        jL_montoMinimo.setText("MONTO MINIMO");

        jl_montoMaximo.setText("MONTO MAXIMO");

        jtf_montoMinimo.setEditable(false);
        jtf_montoMinimo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jtf_montoMinimoKeyTyped(evt);
            }
        });

        jtf_montoMaximo.setEditable(false);
        jtf_montoMaximo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jtf_montoMaximoKeyTyped(evt);
            }
        });

        jb_limpiar.setText("LIMPIAR");
        jb_limpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jb_limpiarActionPerformed(evt);
            }
        });

        jLabel2.setText("NOMBRE");

        jb_cargar.setText("ACTUALIZAR ESTADO CAMPAÑA");
        jb_cargar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jb_cargarActionPerformed(evt);
            }
        });

        bg_jrb_activo.add(jrb_activo_si);
        jrb_activo_si.setText("SÍ");
        jrb_activo_si.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrb_activo_siActionPerformed(evt);
            }
        });

        jbt_salir.setText("SALIR");
        jbt_salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbt_salirActionPerformed(evt);
            }
        });

        bg_jrb_activo.add(jrb_activo_no);
        jrb_activo_no.setText("NO");

        jl_activo.setText("ACTIVO");

        jtf_nombre.setEditable(false);
        jtf_nombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jtf_nombreKeyTyped(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("APERTURA Y CIERRE DE CAMPAÑAS");

        jDateChooser_inicio.setEnabled(false);

        jl_fin.setText("FECHA FIN");

        jDateChooser_fin.setEnabled(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 550, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(259, 259, 259)
                        .addComponent(jbt_salir)
                        .addContainerGap(58, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jl_activo)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jrb_activo_si)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(jrb_activo_no))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jL_montoMinimo)
                                                .addComponent(jl_montoMaximo))
                                            .addGap(119, 133, Short.MAX_VALUE)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(jtf_montoMaximo, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jtf_montoMinimo, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jb_limpiar)
                                        .addGap(27, 27, 27)
                                        .addComponent(jb_cargar)))
                                .addGap(26, 26, 26))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(3, 3, 3)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel3)))
                                    .addComponent(jl_fin, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(32, 32, 32)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jtf_nombre)
                                    .addComponent(jDateChooser_inicio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jDateChooser_fin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addContainerGap())))
            .addGroup(layout.createSequentialGroup()
                .addGap(353, 353, 353)
                .addComponent(jLabel1)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 62, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jLabel2))
                            .addComponent(jtf_nombre, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3)
                            .addComponent(jDateChooser_inicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(16, 16, 16)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jl_fin)
                            .addComponent(jDateChooser_fin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jL_montoMinimo)
                            .addComponent(jtf_montoMinimo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jl_montoMaximo)
                            .addComponent(jtf_montoMaximo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(44, 44, 44)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jl_activo)
                            .addComponent(jrb_activo_si)
                            .addComponent(jrb_activo_no))
                        .addGap(40, 40, 40)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jb_limpiar)
                            .addComponent(jb_cargar))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jbt_salir))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 549, Short.MAX_VALUE)
                    .addComponent(jSeparator1))
                .addGap(32, 32, 32))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jtf_montoMinimoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtf_montoMinimoKeyTyped
        // TODO add your handling code here:
        char caracter = evt.getKeyChar();
        if (((caracter < '0') || (caracter > '9')) && (caracter != '\b') && (caracter != '.')) {
            evt.consume();
        }
        if (!(jtf_montoMinimo.getText().length() < 7)) {
            evt.consume();
        }
    }//GEN-LAST:event_jtf_montoMinimoKeyTyped

    private void jtf_montoMaximoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtf_montoMaximoKeyTyped
        // TODO add your handling code here:
        char caracter = evt.getKeyChar();
        if (((caracter < '0') || (caracter > '9')) && (caracter != '\b') && (caracter != '.')) {
            evt.consume();
        }
        if (!(jtf_montoMaximo.getText().length() < 7)) {
            evt.consume();
        }
    }//GEN-LAST:event_jtf_montoMaximoKeyTyped

    private void jb_limpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jb_limpiarActionPerformed
        // TODO add your handling code here:
        limpiar();
    }//GEN-LAST:event_jb_limpiarActionPerformed

    private void jb_cargarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jb_cargarActionPerformed
        // TODO add your handling code here:
        if (jtf_nombre.getText().length() > 4) {
            campaña.setNombre(jtf_nombre.getText());
            try {
                double monto_min = Double.parseDouble(jtf_montoMinimo.getText());
                if (monto_min > 0) {
                    campaña.setMonto_min(monto_min);
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Debe ingresar un monto minimo válido");
                jtf_montoMinimo.setText("");
                jtf_montoMinimo.requestFocus();
            }
            try {
                double monto_max = Double.parseDouble(jtf_montoMaximo.getText());
                if (monto_max > campaña.getMonto_min()) {
                    campaña.setMonto_max(monto_max);
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Debe ingresar un monto maximo mayor que el monto minimo");
                jtf_montoMaximo.setText("");
                jtf_montoMaximo.requestFocus();
            }
            LocalDate fecha;
            fecha = LocalDateTime.ofInstant(jDateChooser_inicio.getDate().toInstant(), ZoneId.systemDefault()).toLocalDate();
            if (jDateChooser_inicio.getDate() != null) {
                campaña.setFecha_inicio(fecha);

                int respuesta = JOptionPane.showConfirmDialog(this, "¿Seguro que desea modificar el estado de la campaña?", "Modificar Estado de Campaña", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

                if (respuesta == 0) {
                    if (jrb_activo_si.isSelected()) {
                        int cont=sd.revisar();
                        if(cont>0){
                            JOptionPane.showMessageDialog(this, "Al cierre de campaña, se inhabilitaron "+ cont +" revendedores");
                        }
                        campaña.setActiva(true);
                        cd.desactivarCampañas();
                        cd.modificarCampaña(campaña);
                        
                        this.cargaCampañas();
                        limpiar();
                    } else if (jrb_activo_no.isSelected()) {
                        int cont=sd.revisar();
                        if(cont>0){
                            JOptionPane.showMessageDialog(this, "Al cierre de campaña, se inhabilitaron "+ cont +" revendedores");
                        }
                        campaña.setActiva(false);
                        cd.modificarCampaña(campaña);
                        this.cargaCampañas();
                        limpiar();

                        actualizarNiveles();
                    }
                } else {
                    System.out.println("Modificacion cancelada");
                }
            } else {
                JOptionPane.showMessageDialog(this, "La fecha elegida es nula");
                jDateChooser_inicio.requestFocus();

            }
        }

    }//GEN-LAST:event_jb_cargarActionPerformed

    private void jbt_salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbt_salirActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jbt_salirActionPerformed

    private void jtf_nombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtf_nombreKeyTyped
        // TODO add your handling code here:
        if (!(jtf_nombre.getText().length() < 30)) {
            evt.consume();
        }
    }//GEN-LAST:event_jtf_nombreKeyTyped

    private void actualizarNiveles() {
        List<Revendedor> lista = rd.buscarRevendedores();
        lista.forEach((revendedor) -> {

            rd.calcularNivelRevendedorMejorado(revendedor);
        });

    }

    private void jrb_activo_siActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrb_activo_siActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jrb_activo_siActionPerformed

    private void jt_campañasCaretPositionChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_jt_campañasCaretPositionChanged
        // TODO add your handling code here:

    }//GEN-LAST:event_jt_campañasCaretPositionChanged

    private void jt_campañasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jt_campañasMouseClicked
        // TODO add your handling code here:
        jrb_activo_no.setEnabled(true);
        jrb_activo_si.setEnabled(true);
        int fila_sel = jt_campañas.getSelectedRow();
        System.out.println("la fila seleccionda es" + fila_sel);
        if (fila_sel != -1) {
            try {
                int id_campaña = Integer.parseInt(jt_campañas.getValueAt(fila_sel, 0).toString());
                campaña = cd.buscarCampaña(id_campaña);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Fallo al traer id entero");
            }

            System.out.println("El estado activo de la campaña es " + campaña.isActiva());
            this.habilitarCampos();
            jtf_nombre.setText(campaña.getNombre());
            jtf_montoMinimo.setText(String.valueOf(campaña.getMonto_min()));
            jtf_montoMaximo.setText(String.valueOf(campaña.getMonto_max()));
            jDateChooser_inicio.setDate(Date.valueOf(campaña.getFecha_inicio()));
            jDateChooser_fin.setDate(Date.valueOf(campaña.getFecha_fin()));

            if (campaña.isActiva()) {
                jrb_activo_no.setSelected(false);
                jrb_activo_si.setSelected(true);

            } else {
                jrb_activo_si.setSelected(false);
                jrb_activo_no.setSelected(true);

            }
            //actualizarNiveles();
        } else {
            JOptionPane.showMessageDialog(this, "Debe seleccionar una campaña");
            System.out.println("Debe seleccionar una campaña");
        }


    }//GEN-LAST:event_jt_campañasMouseClicked
    private void limpiar() {
        jrb_activo_no.setEnabled(false);
        jrb_activo_si.setEnabled(false);
        jtf_nombre.setText("");
        jtf_montoMinimo.setText("");
        jtf_montoMaximo.setText("");
        jrb_activo_si.setSelected(false);
        jrb_activo_no.setSelected(true);
        jDateChooser_inicio.setDate(null);
        jDateChooser_fin.setDate(null);
        jtf_nombre.requestFocus();
        cargaCampañas();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup bg_jrb_activo;
    private com.toedter.calendar.JDateChooser jDateChooser_fin;
    private com.toedter.calendar.JDateChooser jDateChooser_inicio;
    private javax.swing.JLabel jL_montoMinimo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JButton jb_cargar;
    private javax.swing.JButton jb_limpiar;
    private javax.swing.JButton jbt_salir;
    private javax.swing.JLabel jl_activo;
    private javax.swing.JLabel jl_fin;
    private javax.swing.JLabel jl_montoMaximo;
    private javax.swing.JRadioButton jrb_activo_no;
    private javax.swing.JRadioButton jrb_activo_si;
    private javax.swing.JTable jt_campañas;
    private javax.swing.JTextField jtf_montoMaximo;
    private javax.swing.JTextField jtf_montoMinimo;
    private javax.swing.JTextField jtf_nombre;
    // End of variables declaration//GEN-END:variables
}
