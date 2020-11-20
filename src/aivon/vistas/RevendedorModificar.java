/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aivon.vistas;

import aivon.entidades.Revendedor;
import aivon.modelos.Conexion;
import aivon.modelos.HistoricoData;
import aivon.modelos.RevendedorData;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author MArio
 */
public class RevendedorModificar extends javax.swing.JInternalFrame {
    private Conexion conexion;
    private RevendedorData revendedor_data;
    private HistoricoData historico_data;
    private Revendedor revendedor;
    private Color color;
    /**
     * Creates new form RevendedorActualizar
     */
    public RevendedorModificar() {
        initComponents();
           try {
             color= new Color(204, 204, 204); 
             conexion=new Conexion();
             revendedor_data=new RevendedorData(conexion);
             historico_data=new HistoricoData(conexion);
             jb_actualizar.setEnabled(false);
                          
        } catch (Exception e) {
        }
    }
    private void delay(String mensaje, int a, int b ){
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                jtf_aviso.setText(mensaje);
            }
        };
        timer.schedule(task, a*1000 , b*20000);
    }
    private void Limpiar(){
        jtf_nombre.setText("");
        jtf_apellido.setText("");
        jtf_dni.setText("");
        jtf_email.setText("");
        jcb_activo.setSelectedIndex(0);
        jtf_dni_buscar.setText("");
        jtf_telefono.setText("");
       this.delay("", 5, 5);
    }
    private boolean isDNI(){
        boolean res=true;  
        if(jtf_dni_buscar.getText().isEmpty()){
            JOptionPane.showMessageDialog(this, "Debe ingresar un DNI para buscar");
            this.Limpiar();
            return false;
         }
        if(!jtf_dni_buscar.getText().isEmpty()&&(jtf_dni_buscar.getText().length()<8)){
                JOptionPane.showMessageDialog(this, "Debe ingresar un DNI valido para buscar");
                return false;
            }
        return res;
     }
  private void colorGris(){
        jtf_nombre.setForeground(color);
        jtf_apellido.setForeground(color);
        jtf_dni.setForeground(color);
        jtf_email.setForeground(color);
        jtf_telefono.setForeground(color);
        jtf_caracteristica.setForeground(color);
    }
    private void agregarAyuda(){
        jtf_nombre.setText("Ingrese el nombre");
        jtf_apellido.setText("Ingrese el apellido");
        jtf_dni.setText("Ingrese el DNI");
        jtf_email.setText("Ingrese el Email");
        jtf_telefono.setText("222979");
        jtf_caracteristica.setText("2664");

    }
    private void activarCampos() {
        jtf_nombre.setEnabled(true);
        jtf_apellido.setEnabled(true);
        jtf_dni.setEnabled(true);
        jtf_email.setEnabled(true);
        jcb_activo.setSelectedIndex(0);
        jcb_activo.setEnabled(true);
        jtf_dni_buscar.setEnabled(true);
        jtf_telefono.setEnabled(true);
    }
    private void desactivarCampos() {
        jtf_nombre.setEnabled(false);
        jtf_apellido.setEnabled(false);
        jtf_dni.setEnabled(false);
        jtf_email.setEnabled(false);
        jcb_activo.setSelectedIndex(0);
        jcb_activo.setEnabled(false);
        jtf_dni_buscar.setEnabled(false);
        jtf_telefono.setEnabled(false);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        filler2 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 6), new java.awt.Dimension(0, 6), new java.awt.Dimension(32767, 6));
        jtf_dni_buscar = new javax.swing.JTextField();
        jb_buscar_revendedor = new javax.swing.JButton();
        jl_buscar = new javax.swing.JLabel();
        jl_nombre = new javax.swing.JLabel();
        jl_apellido = new javax.swing.JLabel();
        jl_DNI = new javax.swing.JLabel();
        jl_telefono = new javax.swing.JLabel();
        jl_email = new javax.swing.JLabel();
        jl_activo = new javax.swing.JLabel();
        jtf_email = new javax.swing.JTextField();
        jtf_apellido = new javax.swing.JTextField();
        jtf_dni = new javax.swing.JTextField();
        jtf_nombre = new javax.swing.JTextField();
        jtf_aviso = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jcb_activo = new javax.swing.JComboBox<>();
        jb_actualizar = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JSeparator();
        jb_limpiar = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(14, 0), new java.awt.Dimension(14, 0), new java.awt.Dimension(14, 32767));
        filler3 = new javax.swing.Box.Filler(new java.awt.Dimension(8, 0), new java.awt.Dimension(8, 0), new java.awt.Dimension(8, 32767));
        jtf_caracteristica = new javax.swing.JTextField();
        jtf_telefono = new javax.swing.JTextField();

        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(filler2, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 400, 30, 10));

        jtf_dni_buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtf_dni_buscarActionPerformed(evt);
            }
        });
        jtf_dni_buscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jtf_dni_buscarKeyTyped(evt);
            }
        });
        getContentPane().add(jtf_dni_buscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 100, 107, -1));

        jb_buscar_revendedor.setText("BUSCAR");
        jb_buscar_revendedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jb_buscar_revendedorActionPerformed(evt);
            }
        });
        getContentPane().add(jb_buscar_revendedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 100, 77, -1));

        jl_buscar.setText("Ingrese DNI");
        getContentPane().add(jl_buscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 80, 107, 15));

        jl_nombre.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jl_nombre.setText("Nombre:");
        getContentPane().add(jl_nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 100, -1, -1));

        jl_apellido.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jl_apellido.setText("Apellido: ");
        getContentPane().add(jl_apellido, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 130, -1, -1));

        jl_DNI.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jl_DNI.setText("DNI:");
        getContentPane().add(jl_DNI, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 170, -1, -1));

        jl_telefono.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jl_telefono.setText("Telefono:");
        getContentPane().add(jl_telefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 210, -1, -1));

        jl_email.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jl_email.setText("Email:");
        getContentPane().add(jl_email, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 250, -1, -1));

        jl_activo.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jl_activo.setText("Activo:");
        getContentPane().add(jl_activo, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 290, -1, -1));

        jtf_email.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jtf_email.setCaretColor(new java.awt.Color(255, 51, 51));
        jtf_email.setEnabled(false);
        getContentPane().add(jtf_email, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 250, 230, -1));

        jtf_apellido.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jtf_apellido.setCaretColor(new java.awt.Color(255, 51, 51));
        jtf_apellido.setEnabled(false);
        getContentPane().add(jtf_apellido, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 130, 140, -1));

        jtf_dni.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jtf_dni.setCaretColor(new java.awt.Color(255, 51, 51));
        jtf_dni.setEnabled(false);
        getContentPane().add(jtf_dni, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 170, 100, -1));

        jtf_nombre.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jtf_nombre.setToolTipText("");
        jtf_nombre.setCaretColor(new java.awt.Color(255, 51, 51));
        jtf_nombre.setEnabled(false);
        jtf_nombre.setName(""); // NOI18N
        getContentPane().add(jtf_nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 90, 140, -1));

        jtf_aviso.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jtf_aviso.setForeground(new java.awt.Color(153, 0, 0));
        jtf_aviso.setToolTipText("Da informacion de lo que esta suceciendo");
        getContentPane().add(jtf_aviso, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 350, 342, 20));

        jLabel2.setText("Estado:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 330, -1, -1));

        jcb_activo.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jcb_activo.setForeground(new java.awt.Color(0, 0, 0));
        jcb_activo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "  ", "SI", "NO" }));
        jcb_activo.setEnabled(false);
        getContentPane().add(jcb_activo, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 290, 68, -1));

        jb_actualizar.setText("Actualizar");
        jb_actualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jb_actualizarActionPerformed(evt);
            }
        });
        getContentPane().add(jb_actualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 200, 167, 40));

        jSeparator3.setOrientation(javax.swing.SwingConstants.VERTICAL);
        getContentPane().add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 270, 45, 48));

        jb_limpiar.setText("Limpiar");
        jb_limpiar.setToolTipText("Limpia todo los campos");
        jb_limpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jb_limpiarActionPerformed(evt);
            }
        });
        getContentPane().add(jb_limpiar, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 280, -1, -1));

        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);
        getContentPane().add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 270, 45, 48));

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel1.setText("Baja Revendedoras");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 10, -1, -1));
        getContentPane().add(filler1, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 120, 90, 100));
        getContentPane().add(filler3, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 370, 340, 40));

        jtf_caracteristica.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jtf_caracteristica.setCaretColor(new java.awt.Color(255, 51, 51));
        jtf_caracteristica.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtf_caracteristicaFocusGained(evt);
            }
        });
        jtf_caracteristica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtf_caracteristicaActionPerformed(evt);
            }
        });
        jtf_caracteristica.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jtf_caracteristicaKeyTyped(evt);
            }
        });
        getContentPane().add(jtf_caracteristica, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 210, 50, -1));

        jtf_telefono.setCaretColor(new java.awt.Color(255, 51, 51));
        jtf_telefono.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtf_telefonoFocusGained(evt);
            }
        });
        jtf_telefono.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtf_telefonoActionPerformed(evt);
            }
        });
        jtf_telefono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jtf_telefonoKeyTyped(evt);
            }
        });
        getContentPane().add(jtf_telefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 210, 90, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jtf_dni_buscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtf_dni_buscarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtf_dni_buscarActionPerformed

    private void jtf_dni_buscarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtf_dni_buscarKeyTyped
        //#####  Controla que sean solo numeros y sin espacios  ########################
        Character caracter=evt.getKeyChar();
        if (((caracter < '0')
            || (caracter > '9'))
        && (caracter != '\b' /*corresponde a BACK_SPACE*/)) {
        evt.consume();  // ignorar el evento de teclado
        jtf_aviso.setText("Solo numeros");
        this.delay("", 9, 9);
        }
        //######    Controla que sean 8 digitos   ######################################
        if(!(jtf_dni_buscar.getText().length()<8)){
            evt.consume();
            jtf_aviso.setText("Solo 8 digitos");
            this.delay("", 9, 9);
        }
    }//GEN-LAST:event_jtf_dni_buscarKeyTyped

    private void jb_buscar_revendedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jb_buscar_revendedorActionPerformed
        //        String dni=jtf_dni_buscar.getText();
        //        int cont=0;
        //        if(jtf_dni_buscar.getText().isEmpty()){
            //            JOptionPane.showMessageDialog(this, "Debe ingresar un DNI para buscar");
            //            this.Limpiar();
            //            cont++;
            //         }
        //        if(!jtf_dni_buscar.getText().isEmpty()&&(jtf_dni_buscar.getText().length()<8)){
            //                JOptionPane.showMessageDialog(this, "Debe ingresar un DNI valido para buscar");
            //                cont++;
            //            }
        if(this.isDNI()){
            try {
                revendedor=new Revendedor();
                revendedor=revendedor_data.buscarRevendedor(jtf_dni_buscar.getText());
                jtf_nombre.setText(revendedor.getNombre());
                jtf_apellido.setText(revendedor.getApellido());
                jtf_dni.setText(revendedor.getDni());
                jtf_email.setText(revendedor.getEmail());
                jtf_telefono.setText(revendedor.getTel());
                if(revendedor.isActivo()){
                    jcb_activo.setSelectedIndex(1);
                }
                else{
                    jcb_activo.setSelectedIndex(2);
                }
                jb_actualizar.setEnabled(true);
                activarCampos();
            } catch (Exception e) {
                //JOptionPane.showMessageDialog(this, "No existe Revendedor para ese DNI: "+jtf_dni_buscar.getText());
            }

        }
        //this.Limpiar();
    }//GEN-LAST:event_jb_buscar_revendedorActionPerformed

    private void jb_actualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jb_actualizarActionPerformed
        if((JOptionPane.YES_NO_OPTION==JOptionPane.showConfirmDialog(this, "Esta seguro que desea borrar", "Borrar Revendedor", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE))){
            revendedor_data.borrarRevendedor(jtf_dni_buscar.getText());
            desactivarCampos();
            this.Limpiar();
            this.delay("Revendedor Borrado con Exito", 6, 6);
        }

        this.delay("", 4, 4);
    }//GEN-LAST:event_jb_actualizarActionPerformed

    private void jb_limpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jb_limpiarActionPerformed
        this.Limpiar();
    }//GEN-LAST:event_jb_limpiarActionPerformed

    private void jtf_caracteristicaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtf_caracteristicaFocusGained
        if (jtf_caracteristica.getForeground() == color) {
            jtf_caracteristica.setText("");
            jtf_caracteristica.setForeground(Color.BLACK);
        } else {

        }
    }//GEN-LAST:event_jtf_caracteristicaFocusGained

    private void jtf_caracteristicaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtf_caracteristicaActionPerformed

    }//GEN-LAST:event_jtf_caracteristicaActionPerformed

    private void jtf_caracteristicaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtf_caracteristicaKeyTyped
        char caracter = evt.getKeyChar();
        //#####  Controla que sean solo numeros y sin espacios  ########################
        if (((caracter < '0')
            || (caracter > '9'))
        && (caracter != '\b' /*corresponde a BACK_SPACE*/)) {
        evt.consume();  // ignorar el evento de teclado
        jtf_aviso.setText("Solo numeros");
        }
        //######    Controla que sean 8 digitos   ######################################
        if(!(jtf_caracteristica.getText().length()<4)){
            evt.consume();
            jtf_aviso.setText("Solo 4 digitos");
        }
        this.delay("", 5,5);
    }//GEN-LAST:event_jtf_caracteristicaKeyTyped

    private void jtf_telefonoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtf_telefonoFocusGained
        if (jtf_telefono.getForeground() == color) {
            jtf_telefono.setText("");
            jtf_telefono.setForeground(Color.BLACK);
        } else {

        }
    }//GEN-LAST:event_jtf_telefonoFocusGained

    private void jtf_telefonoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtf_telefonoActionPerformed

    }//GEN-LAST:event_jtf_telefonoActionPerformed

    private void jtf_telefonoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtf_telefonoKeyTyped
        char caracter = evt.getKeyChar();
        //#####  Controla que sean solo numeros y sin espacios  ########################
        if (((caracter < '0')
            || (caracter > '9'))
        && (caracter != '\b' /*corresponde a BACK_SPACE*/)) {
        evt.consume();  // ignorar el evento de teclado
        jtf_aviso.setText("Solo numeros");
        }
        //######    Controla que sean 8 digitos   ######################################
        if(!(jtf_telefono.getText().length()<6)){
            evt.consume();
            jtf_aviso.setText("Solo 6 digitos");
        }
        this.delay("", 3, 2);
    }//GEN-LAST:event_jtf_telefonoKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.Box.Filler filler1;
    private javax.swing.Box.Filler filler2;
    private javax.swing.Box.Filler filler3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JButton jb_actualizar;
    private javax.swing.JButton jb_buscar_revendedor;
    private javax.swing.JButton jb_limpiar;
    private javax.swing.JComboBox<String> jcb_activo;
    private javax.swing.JLabel jl_DNI;
    private javax.swing.JLabel jl_activo;
    private javax.swing.JLabel jl_apellido;
    private javax.swing.JLabel jl_buscar;
    private javax.swing.JLabel jl_email;
    private javax.swing.JLabel jl_nombre;
    private javax.swing.JLabel jl_telefono;
    private javax.swing.JTextField jtf_apellido;
    private javax.swing.JLabel jtf_aviso;
    private javax.swing.JTextField jtf_caracteristica;
    private javax.swing.JTextField jtf_dni;
    private javax.swing.JTextField jtf_dni_buscar;
    private javax.swing.JTextField jtf_email;
    private javax.swing.JTextField jtf_nombre;
    private javax.swing.JTextField jtf_telefono;
    // End of variables declaration//GEN-END:variables

    
}
