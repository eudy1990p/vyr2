/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formularios;

import ClassStatic.Log;
import formularios.Main.Main;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
/**
 *
 * @author Eudy
 */
public class login_f extends javax.swing.JFrame {
    private conexion.Mysql mysql;
    private String name_user="",type_user="";
    private String[] session = new String[4]; 
    
    
    public login_f() {
        initComponents();
        this.setLocationRelativeTo(null);
        setIconImage(new ImageIcon(getClass().getResource("../icono/logovyr.gif")).getImage());
            
    }
    public login_f(conexion.Mysql mysql){
        initComponents();
        this.setLocationRelativeTo(null);
        Log.Registrar("Inicio el login");
        try {
            this.mysql = mysql;
            
            //Class awtutil = Class.forName("com.sun.awt.AWTUtilities");
            //com.sun.awt.AWTUtilities.setWindowOpaque(this, false);
            //com.sun.awt.AWTUtilities.setWindowOpaque(this, false);
            //setIconImage(new ImageIcon(getClass().getResource("../icono/logovyr.gif")).getImage());
            this.setIconImage(new ImageIcon("C:/app_vyr/img/iconoclinica.gif").getImage());
            this.getContentPane().setBackground(new Color(255,255,255,255));
            
        } catch (Exception ex) {
             Log.Error("Login", ex);
            //Logger.getLogger(login_f.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
    
    public boolean validarUsuario(String usuario,String clave){        
        Log.Registrar(this.getClass(), "validarUsuario", "");

        int existe = this.mysql.getValues("usuario", "where nombre_usuario = '"+usuario+"' and clave_usuario = '"+clave+"' and display = '1'");
        if(existe > 0){
            this.getValuesUser(usuario, clave);
            return true;
        }else{
             JOptionPane.showMessageDialog(null, "Usuario o clave no coinsiden.");
             return false;
            }
    }
    public void getValuesUser(String usuario,String clave){     
        Log.Registrar(this.getClass(), "getValuesUser", "");

        String[] campos = {"id","nombre_usuario","tipo_usuario"};
       this.session = this.mysql.getValues("usuario", "where nombre_usuario = '"+usuario+"' and clave_usuario = '"+clave+"' and display = '1'",campos);      
    }
    public String[] getSession(){       
        Log.Registrar(this.getClass(), "getSession", "");

        return this.session;
    }
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPasswordField1 = new javax.swing.JPasswordField();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Login");
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setText("Usuario");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 236, -1, -1));

        jTextField1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTextField1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField1.setText("vyr");
        jTextField1.setToolTipText("Ingrese su usuario");
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        getContentPane().add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 257, 223, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setText("Clave");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 293, -1, -1));

        jButton1.setBackground(new java.awt.Color(0, 153, 51));
        jButton1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Entrar");
        jButton1.setToolTipText("Click para poder entrar a la app");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 370, -1, -1));

        jButton2.setBackground(new java.awt.Color(255, 51, 0));
        jButton2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Cancelar");
        jButton2.setToolTipText("Click para cancelar la entrar a la app");
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton2MouseClicked(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 370, -1, -1));

        jPasswordField1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPasswordField1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPasswordField1.setText("vyr123456@");
        jPasswordField1.setToolTipText("Ingrese su clave de usuario");
        jPasswordField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPasswordField1ActionPerformed(evt);
            }
        });
        jPasswordField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jPasswordField1KeyPressed(evt);
            }
        });
        getContentPane().add(jPasswordField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 313, 223, -1));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icono/fondoLogin.gif"))); // NOI18N
        jLabel4.setText("jLabel4");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 440, 490));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseClicked
        // TODO add your handling code here:
        Log.Registrar(this.getClass(),"jButton2MouseClicked"," cancelar login");
        System.exit(0);

    }//GEN-LAST:event_jButton2MouseClicked

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        // TODO add your handling code here:
          this.login();
    }//GEN-LAST:event_jButton1MouseClicked
    public void login(){        
        Log.Registrar(this.getClass(), "login", "");

        String usuario = this.jTextField1.getText();
        String clave = new String(this.jPasswordField1.getPassword());
        //System.out.println(clave);
        boolean respuesta = this.validarUsuario(usuario, clave);
        if(respuesta){
            this.dispose();
            Log.Registrar("Esta logiado usuario "+usuario);
            Log.Registrar("Abrir Main");
           new Main(this.mysql,this.getSession()).setVisible(true);
        }
    }
    private void jPasswordField1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPasswordField1KeyPressed
        // TODO add your handling code here:
        //System.out.println(evt.getKeyCode());
        if(evt.getKeyCode() == 10){
                this.login();
        }
    }//GEN-LAST:event_jPasswordField1KeyPressed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jPasswordField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPasswordField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jPasswordField1ActionPerformed

    /**
     * @param args the command line arguments
     */
   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
