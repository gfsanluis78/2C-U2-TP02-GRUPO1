/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aivon.vistas;

import aivon.entidades.Campaña;
import aivon.entidades.Historico;
import aivon.entidades.Revendedor;
import aivon.modelos.CampañaData;
import aivon.modelos.Conexion;
import aivon.modelos.HistoricoData;
import aivon.modelos.RevendedorData;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author MArio
 */
public class RevendedorAlta extends javax.swing.JInternalFrame {
    private Conexion conexion;
    private RevendedorData revendedor_data;
    private HistoricoData historico_data;
    private CampañaData campaña_data;
    private Revendedor revendedor;
    private Color color;
    private Historico historico;
    private Campaña campaña;
    /**
     * Creates new form RevendedorAlta
     */
  
    public RevendedorAlta() {
        initComponents();
        try {
             color= new Color(204, 204, 204); 
             conexion=new Conexion();
             revendedor_data=new RevendedorData(conexion);
             historico_data=new HistoricoData(conexion);
             campaña_data=new CampañaData(conexion);
             this.colorGris();
             this.agregarAyuda();
             
        } catch (Exception e) {
        }

        
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
    private static final String EMAIL_PATTERN = 
    "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\\\.[A-Za-z0-9-]+)*(\\\\.[A-Za-z]{2,})$";
     private static final String EMAIL_PATTERN2 = "^(.+)@(\\S+)$";
     private static final String NOMBRE = "/^[A-Z a-z\\s]+$/";
     private static final String EMAIL_PATTERN3 = "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$";
     private static final String EMAIL_PATTERN4="[A-Za-z0-9_-]+([A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(a?.[A-Za-z0-9-]+)*([A-Za-z]{2,})$";
    private void Limpiar(){
        jtf_nombre.setText("");
        jtf_apellido.setText("");
        jtf_dni.setText("");
        jtf_email.setText("");
        jtf_caracteristica.setText("");
        jcb_activo.setSelectedIndex(0);
        
       this.delay("", 5, 5);
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
     private void limpiarJL(JLabel var, int a, int b ){
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                var.setText(" ");
            }
        };
        timer.schedule(task, a*1000 , b*20000);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jl_nombre = new javax.swing.JLabel();
        jl_apellido = new javax.swing.JLabel();
        jl_DNI = new javax.swing.JLabel();
        jl_telefono = new javax.swing.JLabel();
        jl_email = new javax.swing.JLabel();
        jl_activo = new javax.swing.JLabel();
        jtf_email = new javax.swing.JTextField();
        jtf_caracteristica = new javax.swing.JTextField();
        jtf_apellido = new javax.swing.JTextField();
        jtf_dni = new javax.swing.JTextField();
        jtf_nombre = new javax.swing.JTextField();
        jSeparator3 = new javax.swing.JSeparator();
        jb_limpiar = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        jb_guardar = new javax.swing.JButton();
        jb_salir = new javax.swing.JButton();
        jtf_aviso = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jcb_activo = new javax.swing.JComboBox<>();
        jtf_telefono = new javax.swing.JTextField();

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel1.setText("Alta Revendedoras");

        jl_nombre.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jl_nombre.setText("Nombre:");

        jl_apellido.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jl_apellido.setText("Apellido: ");

        jl_DNI.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jl_DNI.setText("DNI:");

        jl_telefono.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jl_telefono.setText("Telefono:");

        jl_email.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jl_email.setText("Email:");

        jl_activo.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jl_activo.setText("Activo:");

        jtf_email.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jtf_email.setCaretColor(new java.awt.Color(255, 51, 51));
        jtf_email.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtf_emailFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtf_emailFocusLost(evt);
            }
        });
        jtf_email.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtf_emailActionPerformed(evt);
            }
        });
        jtf_email.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jtf_emailKeyTyped(evt);
            }
        });

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

        jtf_apellido.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jtf_apellido.setCaretColor(new java.awt.Color(255, 51, 51));
        jtf_apellido.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtf_apellidoFocusGained(evt);
            }
        });
        jtf_apellido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtf_apellidoActionPerformed(evt);
            }
        });
        jtf_apellido.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jtf_apellidoKeyTyped(evt);
            }
        });

        jtf_dni.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jtf_dni.setCaretColor(new java.awt.Color(255, 51, 51));
        jtf_dni.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtf_dniFocusGained(evt);
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

        jtf_nombre.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jtf_nombre.setToolTipText("");
        jtf_nombre.setCaretColor(new java.awt.Color(255, 51, 51));
        jtf_nombre.setName(""); // NOI18N
        jtf_nombre.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtf_nombreFocusGained(evt);
            }
        });
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

        jSeparator3.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jb_limpiar.setText("Limpiar");
        jb_limpiar.setToolTipText("Limpia todo los campos");
        jb_limpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jb_limpiarActionPerformed(evt);
            }
        });

        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jb_guardar.setText("Guardar");
        jb_guardar.setToolTipText("Deben estar llenos lo campos");
        jb_guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jb_guardarActionPerformed(evt);
            }
        });

        jb_salir.setText("Salir");
        jb_salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jb_salirActionPerformed(evt);
            }
        });

        jtf_aviso.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jtf_aviso.setForeground(new java.awt.Color(153, 0, 0));
        jtf_aviso.setToolTipText("Da informacion de lo que esta suceciendo");

        jLabel2.setText("Estado:");

        jcb_activo.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jcb_activo.setForeground(new java.awt.Color(0, 0, 0));
        jcb_activo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Elija", "SI", "NO" }));
        jcb_activo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jcb_activoFocusGained(evt);
            }
        });
        jcb_activo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcb_activoActionPerformed(evt);
            }
        });

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jtf_aviso, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jl_email)
                            .addComponent(jl_activo)
                            .addComponent(jl_telefono)
                            .addComponent(jl_DNI)
                            .addComponent(jl_apellido)
                            .addComponent(jl_nombre))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(jtf_caracteristica, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jtf_telefono, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jtf_dni, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jtf_nombre, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
                                            .addComponent(jtf_apellido))
                                        .addGap(27, 27, 27)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(40, 40, 40)
                                                .addComponent(jb_limpiar)))
                                        .addGap(36, 36, 36)
                                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jb_salir)
                                        .addComponent(jb_guardar, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(jtf_email, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jcb_activo, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(92, 92, 92)
                        .addComponent(jLabel1)))
                .addContainerGap(40, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jb_salir)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jl_nombre)
                            .addComponent(jtf_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jl_apellido)
                            .addComponent(jtf_apellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jl_DNI)
                            .addComponent(jtf_dni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(14, 14, 14)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jl_telefono)
                            .addComponent(jtf_caracteristica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtf_telefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(14, 14, 14)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jl_email)
                            .addComponent(jtf_email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(9, 9, 9)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jl_activo)
                            .addComponent(jcb_activo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addComponent(jb_guardar, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(54, 54, 54)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jb_limpiar))
                            .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addGap(4, 4, 4)
                .addComponent(jtf_aviso, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        jtf_nombre.getAccessibleContext().setAccessibleName("");
        jtf_nombre.getAccessibleContext().setAccessibleDescription("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jb_limpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jb_limpiarActionPerformed
        this.Limpiar();
        this.colorGris();
        this.agregarAyuda();
    }//GEN-LAST:event_jb_limpiarActionPerformed

    private void jb_guardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jb_guardarActionPerformed
        int cont=0;
        if("".equals(jtf_nombre.getText())){
            cont++;
            jtf_aviso.setText("No Debe estar vacio el NOMBRE");
            jtf_nombre.requestFocus();
        }
        if("".equals(jtf_apellido.getText())){
            cont++;
            jtf_aviso.setText("No Debe estar vacio el APELLIDO");
            jtf_apellido.requestFocus();
        }
        if("".equals(jtf_dni.getText())){
            cont++;
            jtf_aviso.setText("No Debe estar vacio el DNI");
            jtf_dni.requestFocus();
        }
        if(cont==0&&(JOptionPane.YES_NO_OPTION==JOptionPane.showConfirmDialog(this, "Estan correctos todos los datos??", "Guardar Revendedora", JOptionPane.YES_NO_OPTION))){

            revendedor= new Revendedor();
            historico=new Historico();
            //campaña=new Campaña();
            revendedor.setNombre(jtf_nombre.getText());
            revendedor.setApellido(jtf_apellido.getText());
            revendedor.setDni(jtf_dni.getText());
            revendedor.setEmail(jtf_email.getText());
            revendedor.setTel(jtf_caracteristica.getText()+jtf_telefono.getText());

            if ( jcb_activo.getSelectedItem().toString().equalsIgnoreCase("si")){
                revendedor.setActivo(true);
            }
            else{
                revendedor.setActivo(false);
            }
            revendedor_data.guardarRevendedor(revendedor);
            historico.setMonto_max(revendedor_data.calcularMontoMaximoRevendedor(revendedor));
            historico.setMonto_min(revendedor_data.calcularMontoMinimoRevendedor(revendedor));
            historico.setNivel(1);
            historico.setCampaña(campaña_data.ultimaCampaña());
            historico.setRevendedor(revendedor);
            historico_data.altaHistorico(historico);
            System.out.println(revendedor.getId_revendedor());
            if(revendedor.getId_revendedor()==0){
                this.Limpiar();
                System.out.println("Ya existe el revendedor");
                jtf_aviso.setText("Ya existe el Revendedor");
                this.delay(" ", 3, 5);
            }else{
                jtf_aviso.setText("Revendedor guardado con Exito");
                this.delay(" ", 5, 7);
                this.Limpiar();
                this.colorGris();
                this.agregarAyuda();
            }
        }
        else{
            jtf_aviso.setText("Llene todos los campos Nombre, Apellido, DNI!!! ");
            //this.delay("Llene todos los campos Nombre, Apellido, DNI y FechaN ");
            this.delay(" ", 3, 5);
            this.Limpiar();
            this.agregarAyuda();
            //JOptionPane.showMessageDialog(this, "Deben estar los ca");

        }

    }//GEN-LAST:event_jb_guardarActionPerformed

    private void jb_salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jb_salirActionPerformed
        this.setVisible(false);
        this.dispose();
    }//GEN-LAST:event_jb_salirActionPerformed

    private void jtf_nombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtf_nombreKeyTyped
//######  Controla que no sean numeros y que no tenga espacios no dejando entrar espacios        
        if(!(evt.getKeyChar()==KeyEvent.VK_BACK_SPACE)&&!(Character.isDigit(evt.getKeyChar()))&&!(evt.getKeyChar()==KeyEvent.VK_SPACE)){
            
        }
        else{
            jtf_aviso.setText("Solo letras");
            evt.consume();
        }
        this.limpiarJL(jtf_aviso, 3, 2);
    }//GEN-LAST:event_jtf_nombreKeyTyped

    private void jtf_dniKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtf_dniKeyTyped
         char caracter = evt.getKeyChar();
//#####  Controla que sean solo numeros y sin espacios  ########################
        if (((caracter < '0')
                || (caracter > '9'))
                && (caracter != '\b' /*corresponde a BACK_SPACE*/)) {
            evt.consume();  // ignorar el evento de teclado
            jtf_aviso.setText("Solo numeros");
        }
//######    Controla que sean 8 digitos   ######################################
        if(!(jtf_dni.getText().length()<8)){
            evt.consume();
            jtf_aviso.setText("Solo 8 digitos");
        }
        this.limpiarJL(jtf_aviso, 3, 2);
        
    }//GEN-LAST:event_jtf_dniKeyTyped

    private void jtf_apellidoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtf_apellidoKeyTyped
//######  Controla que no sean numeros y que no tenga espacios no dejando entrar espacios        
        if(!(evt.getKeyChar()==KeyEvent.VK_BACK_SPACE)&&!(Character.isDigit(evt.getKeyChar()))&&!(evt.getKeyChar()==KeyEvent.VK_SPACE)){
            
        }
        else{
            jtf_aviso.setText("Solo letras");
            evt.consume();
        }
        this.limpiarJL(jtf_aviso, 3, 2);
    }//GEN-LAST:event_jtf_apellidoKeyTyped

    private void jtf_emailKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtf_emailKeyTyped
        
//        boolean ac=(jcb_activo.getSelectedItem()==null)||(jcb_activo.getSelectedItem()!=null);
//        if( !ac && !(email.contains("@"))){
//            jtf_aviso.setText("Debe llevar un @");
//            jcb_activo.setEditable(false);
//        }
        //
    }//GEN-LAST:event_jtf_emailKeyTyped

    private void jtf_nombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtf_nombreActionPerformed
        
    }//GEN-LAST:event_jtf_nombreActionPerformed

    private void jtf_apellidoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtf_apellidoFocusGained
        if(jtf_apellido.getForeground()==color){
            jtf_apellido.setText("");
            jtf_apellido.setForeground(Color.BLACK);
        }
        else{
        
        }
    }//GEN-LAST:event_jtf_apellidoFocusGained

    private void jtf_dniFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtf_dniFocusGained
          if (jtf_dni.getForeground() == color) {
            jtf_dni.setText("");
            jtf_dni.setForeground(Color.BLACK);
        } else {

        }
    }//GEN-LAST:event_jtf_dniFocusGained

    private void jtf_caracteristicaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtf_caracteristicaFocusGained
            if (jtf_caracteristica.getForeground() == color) {
            jtf_caracteristica.setText("");
            jtf_caracteristica.setForeground(Color.BLACK);
        } else {

        }
    }//GEN-LAST:event_jtf_caracteristicaFocusGained

    private void jtf_telefonoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtf_telefonoFocusGained
        if (jtf_telefono.getForeground() == color) {
            jtf_telefono.setText("");
            jtf_telefono.setForeground(Color.BLACK);
        } else {

        }
    }//GEN-LAST:event_jtf_telefonoFocusGained

    private void jtf_emailFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtf_emailFocusGained
        if (jtf_email.getForeground() == color) {
            jtf_email.setText("");
            jtf_email.setForeground(Color.BLACK);
        } else {

        }
    }//GEN-LAST:event_jtf_emailFocusGained

    private void jtf_apellidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtf_apellidoActionPerformed
 
    }//GEN-LAST:event_jtf_apellidoActionPerformed

    private void jtf_dniActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtf_dniActionPerformed

    }//GEN-LAST:event_jtf_dniActionPerformed

    private void jtf_emailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtf_emailActionPerformed
            
    }//GEN-LAST:event_jtf_emailActionPerformed

    private void jtf_telefonoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtf_telefonoActionPerformed
 
    }//GEN-LAST:event_jtf_telefonoActionPerformed

    private void jtf_caracteristicaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtf_caracteristicaActionPerformed
       
    }//GEN-LAST:event_jtf_caracteristicaActionPerformed

    private void jtf_nombreFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtf_nombreFocusGained
        if(jtf_nombre.getForeground()==color){
            jtf_nombre.setText("");
        jtf_nombre.setForeground(Color.BLACK);
        }
        else{
        
        }
        
    }//GEN-LAST:event_jtf_nombreFocusGained

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
        this.limpiarJL(jtf_aviso, 3, 2);
    }//GEN-LAST:event_jtf_caracteristicaKeyTyped

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
        this.limpiarJL(jtf_aviso, 3, 2);
    }//GEN-LAST:event_jtf_telefonoKeyTyped

    private void jcb_activoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jcb_activoFocusGained
        
    }//GEN-LAST:event_jcb_activoFocusGained

    private void jcb_activoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcb_activoActionPerformed
        
    }//GEN-LAST:event_jcb_activoActionPerformed

    private void jtf_emailFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtf_emailFocusLost
        String email=jtf_email.getText();
        System.out.println(email);
        System.out.println(Validacion.isValid(email));
        if(Validacion.isValid(email)){
            jcb_activo.setEnabled(true);
        }
        else{
            jcb_activo.setEnabled(false);
            jtf_aviso.setText("Debe ingresar un email valido");
        }
    }//GEN-LAST:event_jtf_emailFocusLost


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JButton jb_guardar;
    private javax.swing.JButton jb_limpiar;
    private javax.swing.JButton jb_salir;
    private javax.swing.JComboBox<String> jcb_activo;
    private javax.swing.JLabel jl_DNI;
    private javax.swing.JLabel jl_activo;
    private javax.swing.JLabel jl_apellido;
    private javax.swing.JLabel jl_email;
    private javax.swing.JLabel jl_nombre;
    private javax.swing.JLabel jl_telefono;
    private javax.swing.JTextField jtf_apellido;
    private javax.swing.JLabel jtf_aviso;
    private javax.swing.JTextField jtf_caracteristica;
    private javax.swing.JTextField jtf_dni;
    private javax.swing.JTextField jtf_email;
    private javax.swing.JTextField jtf_nombre;
    private javax.swing.JTextField jtf_telefono;
    // End of variables declaration//GEN-END:variables
}
