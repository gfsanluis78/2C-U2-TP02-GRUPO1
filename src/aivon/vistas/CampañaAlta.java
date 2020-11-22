/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aivon.vistas;

import aivon.entidades.Campaña;
import aivon.modelos.CampañaData;
import aivon.modelos.Conexion;
import aivon.modelos.ProductoData;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import javax.swing.JOptionPane;

/**
 *
 * @author Genaro
 */
public class CampañaAlta extends javax.swing.JInternalFrame {

    private Conexion conexion;
    private CampañaData cd;

    /**
     * Creates new form ProductoAlta
     */
    public CampañaAlta() {
        initComponents();
        try {
            conexion = new Conexion();
            cd = new CampañaData(conexion);
            jtf_nombre.requestFocus();

            jt_idNextCampana.setText(String.valueOf(cd.ultimaCampaña().getId_campaña() + 1));
            jDateChooser_finUltima.setDate(Date.valueOf(cd.ultimaCampaña().getFecha_fin()));
        } catch (Exception e) {
        }
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
        jLabel1 = new javax.swing.JLabel();
        jL_nombre = new javax.swing.JLabel();
        jl_fechaInicio = new javax.swing.JLabel();
        jl_montoMinimo = new javax.swing.JLabel();
        jl_montoMaximo = new javax.swing.JLabel();
        jtf_nombre = new javax.swing.JTextField();
        jtf_montoMinimo = new javax.swing.JTextField();
        jtf_montoMaximo = new javax.swing.JTextField();
        jbt_limpiar = new javax.swing.JButton();
        jbt_cargar = new javax.swing.JButton();
        jbt_salir = new javax.swing.JButton();
        jDateChooser_inicio = new com.toedter.calendar.JDateChooser();
        jLabel2 = new javax.swing.JLabel();
        jt_idNextCampana = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jDateChooser_finUltima = new com.toedter.calendar.JDateChooser();

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("ALTA DE CAMPAÑA");

        jL_nombre.setText("NOMBRE");

        jl_fechaInicio.setText("FECHA DE INICIO");

        jl_montoMinimo.setText("MONTO MINIMO");

        jl_montoMaximo.setText("MONTO MAXIMO");

        jtf_nombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtf_nombreActionPerformed(evt);
            }
        });
        jtf_nombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jtf_nombreKeyTyped(evt);
            }
        });

        jtf_montoMinimo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jtf_montoMinimoKeyTyped(evt);
            }
        });

        jtf_montoMaximo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jtf_montoMaximoKeyTyped(evt);
            }
        });

        jbt_limpiar.setText("LIMPIAR");
        jbt_limpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbt_limpiarActionPerformed(evt);
            }
        });

        jbt_cargar.setText("CARGAR");
        jbt_cargar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbt_cargarActionPerformed(evt);
            }
        });

        jbt_salir.setText("SALIR");
        jbt_salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbt_salirActionPerformed(evt);
            }
        });

        jDateChooser_inicio.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jDateChooser_inicioFocusGained(evt);
            }
        });
        jDateChooser_inicio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jDateChooser_inicioMouseEntered(evt);
            }
        });
        jDateChooser_inicio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jDateChooser_inicioKeyTyped(evt);
            }
        });

        jLabel2.setText("N° Campaña");

        jt_idNextCampana.setEditable(false);
        jt_idNextCampana.setText("___");
        jt_idNextCampana.setToolTipText("El numero de Id que se va a dar de alta");
        jt_idNextCampana.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jt_idNextCampanaActionPerformed(evt);
            }
        });

        jLabel3.setText("fecha fin ultima campaña");

        jDateChooser_finUltima.setEnabled(false);
        jDateChooser_finUltima.setFocusable(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(79, 79, 79)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jbt_limpiar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jbt_cargar))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jl_montoMinimo)
                                .addComponent(jl_montoMaximo))
                            .addGap(131, 131, 131)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jtf_montoMaximo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jtf_montoMinimo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jL_nombre)
                                .addComponent(jl_fechaInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(26, 26, 26)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jtf_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jDateChooser_inicio, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel2)
                                    .addGap(18, 18, 18)
                                    .addComponent(jt_idNextCampana, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addGap(0, 85, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(199, 199, 199)
                                .addComponent(jbt_salir))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(144, 144, 144)
                                .addComponent(jLabel1)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jDateChooser_finUltima, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(jLabel3))
                    .addComponent(jDateChooser_finUltima, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jt_idNextCampana, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jL_nombre)
                    .addComponent(jtf_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jDateChooser_inicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jl_fechaInicio))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jl_montoMinimo)
                    .addComponent(jtf_montoMinimo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jl_montoMaximo)
                    .addComponent(jtf_montoMaximo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(76, 76, 76)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbt_limpiar)
                    .addComponent(jbt_cargar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jbt_salir)
                .addContainerGap(62, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbt_limpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbt_limpiarActionPerformed
        // TODO add your handling code here:
        limpiar();
    }//GEN-LAST:event_jbt_limpiarActionPerformed

    private void jtf_nombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtf_nombreKeyTyped
        // TODO add your handling code here:
        if (!(jtf_nombre.getText().length() < 30)) {
            evt.consume();
        }
    }//GEN-LAST:event_jtf_nombreKeyTyped

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


    private void jbt_cargarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbt_cargarActionPerformed
        // TODO add your handling code here:
        //¿Validamos si hay dos campañas en el mismo lapso de tiempo? Consultar a la catedra! Genaro.

        Campaña campaña = new Campaña();
        if (jtf_nombre.getText().length() > 4) {
            campaña.setNombre(jtf_nombre.getText());
            try {
                double monto_min = Double.parseDouble(jtf_montoMinimo.getText());
                if (monto_min > 0) {
                    campaña.setMonto_min(monto_min);

                    try {
                        double monto_max = Double.parseDouble(jtf_montoMaximo.getText());
                        if (monto_max > campaña.getMonto_min()) {
                            campaña.setMonto_max(monto_max);

                            LocalDate fecha = null;

                            try {
                                fecha = LocalDateTime.ofInstant(jDateChooser_inicio.getDate().toInstant(), ZoneId.systemDefault()).toLocalDate();

                                if (jDateChooser_inicio.getDate() != null) {
                                    if (jDateChooser_inicio.getDate().after(Date.valueOf(cd.ultimaCampaña().getFecha_fin()))) {
                                        campaña.setFecha_inicio(fecha);
                                        cd.guardarCampaña(campaña);
                                        limpiar();
                                    } else {
                                        JOptionPane.showMessageDialog(this, "La fecha elegida es anterior a la fecha de fin de la ultima campaña");
                                        jDateChooser_inicio.requestFocus();
                                    }

                                } else {
                                    JOptionPane.showMessageDialog(this, "La fecha elegida es nula");
                                    jDateChooser_inicio.requestFocus();
                                }

                            } catch (Exception e) {
                                JOptionPane.showMessageDialog(this, "No ha elegido una fecha" + e.toString());
                                jDateChooser_inicio.requestFocus();
                            }
                        } else {
                            JOptionPane.showMessageDialog(this, "Debe ingresar un monto maximo mayor que el monto minimo");
                            jtf_montoMaximo.setText("");
                            jtf_montoMaximo.requestFocus();
                        }
                    } catch (NumberFormatException e) { ///
                        JOptionPane.showMessageDialog(this, "Debe ingresar un monto maximo mayor que el monto minimo");
                        jtf_montoMaximo.setText("");
                        jtf_montoMaximo.requestFocus();
                    }
                } else { //
                    JOptionPane.showMessageDialog(this, "Debe ingresar un monto minimo válido");
                    jtf_montoMinimo.setText("");
                    jtf_montoMinimo.requestFocus();
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Debe ingresar un monto minimo válido");
                jtf_montoMinimo.setText("");
                jtf_montoMinimo.requestFocus();
            }

        } else {
            JOptionPane.showMessageDialog(this, "Debe ingresar un Noombre con mas de 4 caracteres");
            jtf_nombre.requestFocus();
        }


    }//GEN-LAST:event_jbt_cargarActionPerformed
    private void limpiar() {
        jtf_nombre.setText("");
        jtf_montoMinimo.setText("");
        jtf_montoMaximo.setText("");
        jDateChooser_inicio.setDate(null);
        jDateChooser_finUltima.setDate(Date.valueOf(cd.ultimaCampaña().getFecha_fin()));
        jt_idNextCampana.setText(String.valueOf(cd.ultimaCampaña().getId_campaña() + 1));
        jtf_nombre.requestFocus();

    }
    private void jbt_salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbt_salirActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jbt_salirActionPerformed

    private void jtf_nombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtf_nombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtf_nombreActionPerformed

    private void jt_idNextCampanaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jt_idNextCampanaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jt_idNextCampanaActionPerformed

    private void jDateChooser_inicioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jDateChooser_inicioKeyTyped
        // TODO add your handling code here:
        //jbt_cargar.setEnabled(true);
    }//GEN-LAST:event_jDateChooser_inicioKeyTyped

    private void jDateChooser_inicioMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jDateChooser_inicioMouseEntered
        // TODO add your handling code here:
        //jbt_cargar.setEnabled(true);
    }//GEN-LAST:event_jDateChooser_inicioMouseEntered

    private void jDateChooser_inicioFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jDateChooser_inicioFocusGained
        // TODO add your handling code here:

    }//GEN-LAST:event_jDateChooser_inicioFocusGained


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup bg_jrb_activo;
    private com.toedter.calendar.JDateChooser jDateChooser_finUltima;
    private com.toedter.calendar.JDateChooser jDateChooser_inicio;
    private javax.swing.JLabel jL_nombre;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JButton jbt_cargar;
    private javax.swing.JButton jbt_limpiar;
    private javax.swing.JButton jbt_salir;
    private javax.swing.JLabel jl_fechaInicio;
    private javax.swing.JLabel jl_montoMaximo;
    private javax.swing.JLabel jl_montoMinimo;
    private javax.swing.JTextField jt_idNextCampana;
    private javax.swing.JTextField jtf_montoMaximo;
    private javax.swing.JTextField jtf_montoMinimo;
    private javax.swing.JTextField jtf_nombre;
    // End of variables declaration//GEN-END:variables
}
