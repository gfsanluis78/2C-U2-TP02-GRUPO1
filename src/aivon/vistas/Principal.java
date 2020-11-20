/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aivon.vistas;

import java.awt.Dimension;

/**
 *
 * @author Ezequiel
 */
public class Principal extends javax.swing.JFrame {

    /**
     * Creates new form Principal
     */
    public Principal() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jdp_escritorio = new javax.swing.JDesktopPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jmi_producto_alta = new javax.swing.JMenuItem();
        jmi_producto_actualizar = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();
        jCheckBoxMenuItem_campañaAlta = new javax.swing.JCheckBoxMenuItem();
        jCheckBoxMenuItem_campañaActualizar = new javax.swing.JCheckBoxMenuItem();
        jMenu5 = new javax.swing.JMenu();
        jmi_pedido_alta = new javax.swing.JMenuItem();
        jMenu6 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout jdp_escritorioLayout = new javax.swing.GroupLayout(jdp_escritorio);
        jdp_escritorio.setLayout(jdp_escritorioLayout);
        jdp_escritorioLayout.setHorizontalGroup(
            jdp_escritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 973, Short.MAX_VALUE)
        );
        jdp_escritorioLayout.setVerticalGroup(
            jdp_escritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 739, Short.MAX_VALUE)
        );

        jMenu1.setText("Archivo");

        jMenuItem1.setText("Salir");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Producto");

        jmi_producto_alta.setText("Producto Alta");
        jmi_producto_alta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmi_producto_altaActionPerformed(evt);
            }
        });
        jMenu2.add(jmi_producto_alta);

        jmi_producto_actualizar.setText("Producto Actualizar");
        jmi_producto_actualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmi_producto_actualizarActionPerformed(evt);
            }
        });
        jMenu2.add(jmi_producto_actualizar);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("Revendedor");
        jMenuBar1.add(jMenu3);

        jMenu4.setText("Campaña");

        jCheckBoxMenuItem_campañaAlta.setSelected(true);
        jCheckBoxMenuItem_campañaAlta.setText("Campaña Alta");
        jCheckBoxMenuItem_campañaAlta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxMenuItem_campañaAltaActionPerformed(evt);
            }
        });
        jMenu4.add(jCheckBoxMenuItem_campañaAlta);

        jCheckBoxMenuItem_campañaActualizar.setSelected(true);
        jCheckBoxMenuItem_campañaActualizar.setText("Campaña Actualizar");
        jCheckBoxMenuItem_campañaActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxMenuItem_campañaActualizarActionPerformed(evt);
            }
        });
        jMenu4.add(jCheckBoxMenuItem_campañaActualizar);

        jMenuBar1.add(jMenu4);

        jMenu5.setText("Pedido");

        jmi_pedido_alta.setText("Pedido Alta");
        jmi_pedido_alta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmi_pedido_altaActionPerformed(evt);
            }
        });
        jMenu5.add(jmi_pedido_alta);

        jMenuBar1.add(jMenu5);

        jMenu6.setText("Consultas");
        jMenuBar1.add(jMenu6);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jdp_escritorio)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jdp_escritorio, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        this.dispose();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jmi_producto_altaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmi_producto_altaActionPerformed
        // TODO add your handling code here:
        jdp_escritorio.removeAll();
        jdp_escritorio.repaint();
        ProductoAlta pa=new ProductoAlta();
        pa.setVisible(true);
        jdp_escritorio.add(pa);
        Dimension desktopSize = jdp_escritorio.getSize();
        Dimension FrameSize = pa.getSize();
        pa.setLocation((desktopSize.width - FrameSize.width)/2, (desktopSize.height- FrameSize.height)/2);
        pa.show();
        
    }//GEN-LAST:event_jmi_producto_altaActionPerformed

    private void jmi_producto_actualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmi_producto_actualizarActionPerformed
        // TODO add your handling code here:
        jdp_escritorio.removeAll();
        jdp_escritorio.repaint();
        ProductoActualizar pac=new ProductoActualizar();
        pac.setVisible(true);
        jdp_escritorio.add(pac);
        Dimension desktopSize = jdp_escritorio.getSize();
        Dimension FrameSize = pac.getSize();
        pac.setLocation((desktopSize.width - FrameSize.width)/2, (desktopSize.height- FrameSize.height)/2);
        pac.show();
    }//GEN-LAST:event_jmi_producto_actualizarActionPerformed

    private void jCheckBoxMenuItem_campañaAltaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxMenuItem_campañaAltaActionPerformed
        // TODO add your handling code here:
        jdp_escritorio.removeAll();
        jdp_escritorio.repaint();
        CampañaAlta cal=new CampañaAlta();
        cal.setVisible(true);
        jdp_escritorio.add(cal);
        Dimension desktopSize = jdp_escritorio.getSize();
        Dimension FrameSize = cal.getSize();
        cal.setLocation((desktopSize.width - FrameSize.width)/2, (desktopSize.height- FrameSize.height)/2);
        cal.show();
    }//GEN-LAST:event_jCheckBoxMenuItem_campañaAltaActionPerformed

    private void jCheckBoxMenuItem_campañaActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxMenuItem_campañaActualizarActionPerformed
        // TODO add your handling code here:
        jdp_escritorio.removeAll();
        jdp_escritorio.repaint();
        CampañaActualizar cal=new CampañaActualizar();
        cal.setVisible(true);
        jdp_escritorio.add(cal);
        Dimension desktopSize = jdp_escritorio.getSize();
        Dimension FrameSize = cal.getSize();
        cal.setLocation((desktopSize.width - FrameSize.width)/2, (desktopSize.height- FrameSize.height)/2);
        cal.show();
        
    }//GEN-LAST:event_jCheckBoxMenuItem_campañaActualizarActionPerformed
    private void jmi_pedido_altaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmi_pedido_altaActionPerformed
        // TODO add your handling code here:
        jdp_escritorio.removeAll();
        jdp_escritorio.repaint();
        PedidoAlta pal=new PedidoAlta();
        pal.setVisible(true);
        jdp_escritorio.add(pal);
        Dimension desktopSize = jdp_escritorio.getSize();
        Dimension FrameSize = pal.getSize();
        pal.setLocation((desktopSize.width - FrameSize.width)/2, (desktopSize.height- FrameSize.height)/2);
        pal.show();
    }//GEN-LAST:event_jmi_pedido_altaActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Principal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem_campañaActualizar;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem_campañaAlta;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JDesktopPane jdp_escritorio;
    private javax.swing.JMenuItem jmi_pedido_alta;
    private javax.swing.JMenuItem jmi_producto_actualizar;
    private javax.swing.JMenuItem jmi_producto_alta;
    // End of variables declaration//GEN-END:variables
}