/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formularios.cliente;

import ClassStatic.Log;
import conexion.Mysql;
import formularios.ListadoProducto;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author Eudy
 */
public class crearCliente extends javax.swing.JFrame {

    /**
     * Creates new form crearCliente
     */
    private boolean mostrarOpciones = true;
    private Mysql mysql;
    private String nombre="",apellido,sexo,rnc,fechaNacimiento,cedula,email,telefono,provincia,sector,direccion;
    private String personaID="1",usuarioID="1",clienteID="1";
    private ListadoProducto classSeleccionListado = null;
    private String trabajandoClase = "contizacion";
     private String nombreUsuario = "";
    
    public void setDatosUsuario(String nombre,String id){
             Log.Registrar(this.getClass(), "setDatosUsuario"," ");
        this.nombreUsuario = nombre;
        this.usuarioID = id;
        System.out.println(nombre+" "+id);
    }
            
    public crearCliente(Mysql mysql) {
        initComponents();
        this.mysql = mysql;
        this.mostrarOpciones();
        this.setLocationRelativeTo(null);
    }
    public void setTrabajandoClase(String clase){
             Log.Registrar(this.getClass(), "setTrabajandoClase"," ");
        this.trabajandoClase = clase ;
    }
    public void setClassSeleccionListado(ListadoProducto classSeleccionListado){
            Log.Registrar(this.getClass(), "setClassSeleccionListado"," ");

        this.classSeleccionListado =classSeleccionListado;
    }
    public void cargarVariables(){
Log.Registrar(this.getClass(), "cargarVariables"," ");

        nombre = this.JTFNombreCliente.getText();
        apellido = this.JTFApellidoCliente.getText();
        rnc = this.JTFRNCCliente.getText();
        cedula = this.JTFCedulaCliente.getText();
        email = this.JTFEmailCliente.getText();
        telefono = this.JTFTelefonoCliente.getText();
        provincia = this.JTFProvinciaCliente.getText();
        sector = this.JTFSectorCliente.getText();
        direccion = this.JTADireccionCliente.getText();
        sexo = this.JCBSexoCliente.getSelectedItem().toString();
        //javax.swing.JTextField fn = (javax.swing.JTextField) this.JDCFechaNacimientoCliente.getDateEditor().getUiComponent();
        if(!(((javax.swing.JTextField)this.JDCFechaNacimientoCliente.getDateEditor().getUiComponent()).getText().isEmpty())){
        String[] fecha = ((javax.swing.JTextField)this.JDCFechaNacimientoCliente.getDateEditor().getUiComponent()).getText().split("/");
        fechaNacimiento = fecha[2]+"-"+fecha[1]+"-"+fecha[0];
        }else{
            fechaNacimiento = "0000-00-00";
        }
        Log.Registrar(this.getClass(), "cargarVariables", "cargar variables");
        //fechaNacimiento = fn.getText();
       // JOptionPane.showMessageDialog(null, this.fechaNacimiento);
    }
    public void insertarDBEmail(){
 Log.Registrar(this.getClass(), "insertarDBEmail"," ");

             String campos = "usuario_id,email,persona_id,fecha_creado";
             String valores = "'"+this.usuarioID+"','"+this.email+"','"+this.personaID+"',now()";
             this.mysql.insertData("email", campos, valores);
    }
    
     public void insertarDBTelefono(){
       Log.Registrar(this.getClass(), "insertarDBTelefono"," ");

             String campos = "usuario_id,telephone,persona_id,fecha_creado,tipo_telefono_id";
             String valores = "'"+this.usuarioID+"','"+this.telefono+"','"+this.personaID+"',now(),'1'";
             this.mysql.insertData("telephone", campos, valores);
    }
     public void insertarDBDireccion(){
 Log.Registrar(this.getClass(), "insertarDBDireccion"," ");
             String campos = "usuario_id,sector,persona_id,fecha_creado,provincia,localidad";
             String valores = "'"+this.usuarioID+"','"+this.sector+"','"+this.personaID+"',now(),'"+this.provincia+"','"+this.direccion+"'";
             this.mysql.insertData("direccion", campos, valores);
    }
    public void insertarDBPersona(){
    Log.Registrar(this.getClass(), "insertarDBPersona"," ");

           String campos = "nombre,apellido,sexo,fecha_creado,usuario_id,rnc,cedula,fecha_nacimiento";//fecha_nacimiento
             String valores = "'"+this.nombre+"','"+this.apellido+"','"+this.sexo+"',now(),'"+this.usuarioID+"','"+this.rnc+"','"+this.cedula+"','"+this.fechaNacimiento+"'";
             this.mysql.insertData("persona", campos, valores);
             this.personaID = this.mysql.optenerUltimoID("persona");
    }
    public void insertarDBCliente(){
   Log.Registrar(this.getClass(), "insertarDBCliente"," ");

           String campos = "usuario_id,persona_id,fecha_creado";//fecha_nacimiento
             String valores = "'"+this.usuarioID+"','"+this.personaID+"',now()";
             this.mysql.insertData("cliente", campos, valores);
             this.clienteID = this.mysql.optenerUltimoID("cliente");
    }
    public void mostrarOpciones(){
            Log.Registrar(this.getClass(), "mostrarOpciones"," ");

          if(this.mostrarOpciones){
              this.mostrarOpciones = false;
          }else{
              this.mostrarOpciones = true;
          }
          this.JLEmailCliente.setVisible(mostrarOpciones);
          this.JTFEmailCliente.setVisible(mostrarOpciones);
          
          this.JCBSexoCliente.setVisible(mostrarOpciones);
          this.JLSexoCliente.setVisible(mostrarOpciones);
          
          this.JDCFechaNacimientoCliente.setVisible(mostrarOpciones);
          this.JLFechaNacimientoCliente.setVisible(mostrarOpciones);
          
          //this.JTADireccionCliente.setVisible(mostrarOpciones);
          this.JPDireccion.setVisible(mostrarOpciones);
          this.JLDireccionCliente.setVisible(mostrarOpciones);
          
          this.JTFCedulaCliente.setVisible(mostrarOpciones);
          this.JLCedulaCliente.setVisible(mostrarOpciones);
          
          this.JTFProvinciaCliente.setVisible(mostrarOpciones);
          this.JLProvinciaCliente.setVisible(mostrarOpciones);
          
          //this.JTFRNCCliente.setVisible(mostrarOpciones);
          //this.JLRNCCliente.setVisible(mostrarOpciones);
          
          this.JTFSectorCliente.setVisible(mostrarOpciones);
          this.JLSectorCliente.setVisible(mostrarOpciones);

          Log.Registrar(this.getClass(), "mostrarOpciones", "se mostraron las opciones ocultas");
    }
    public void insertDatosCliente(){
  Log.Registrar(this.getClass(), "insertDatosCliente"," ");

        try{
            this.cargarVariables();
            if(this.nombre.isEmpty()){
                JOptionPane.showMessageDialog(null, "El campo nombre esta vacío","Existen campo vacío",JOptionPane.WARNING_MESSAGE);
            }else if(this.apellido.isEmpty()){
                JOptionPane.showMessageDialog(null, "El campo apellido esta vacío","Existen campo vacío",JOptionPane.WARNING_MESSAGE);
            }else if (this.telefono.isEmpty()){
                JOptionPane.showMessageDialog(null, "El campo telefono esta vacío","Existen campo vacío",JOptionPane.WARNING_MESSAGE);
            }else{
               this.insertarDBPersona();
               if(!this.email.isEmpty()){
                   this.insertarDBEmail();
               }
               if(!this.telefono.isEmpty()){
                   this.insertarDBTelefono();
               }
               if( (!this.sector.isEmpty()) || (!this.provincia.isEmpty()) || (!this.direccion.isEmpty()) ){
                   this.insertarDBDireccion();
               }
               this.insertarDBCliente();
               if(this.classSeleccionListado != null){
                    if(this.classSeleccionListado.getCotizacion() != null){

                         this.dispose();
                         this.classSeleccionListado.getCotizacion().setDatosCliente(this.clienteID, this.nombre, this.cedula,this.telefono,this.email);
                         this.classSeleccionListado.perderFocus();
                         this.classSeleccionListado.dispose();
                    }

                    if(this.classSeleccionListado.getFacturacion() != null){
                         //this.insertarDBCliente();
                         this.dispose();
                         this.classSeleccionListado.getFacturacion().setDatosCliente(this.clienteID, this.nombre, this.cedula,this.telefono,this.email);
                          this.classSeleccionListado.getFacturacion().setRNCCliente(rnc);
                         this.classSeleccionListado.perderFocus();
                         this.classSeleccionListado.dispose();
                    }
                    if(this.classSeleccionListado.getReparacion() != null){
                         //this.insertarDBCliente();
                         this.dispose();
                         this.classSeleccionListado.getReparacion().setDatosCliente(this.clienteID, this.nombre, this.cedula,this.telefono,this.email);
                         this.classSeleccionListado.perderFocus();
                         this.classSeleccionListado.dispose();
                    }
               }else{
                   JOptionPane.showMessageDialog(null, "El cliente fue Guardado correctamente","Se Guardo correctamente",JOptionPane.INFORMATION_MESSAGE);
                  this.limpiarTextos(); 
               }


          /* if( (this.classSeleccionListado.getReparacion()  == null) && (this.classSeleccionListado.getFacturacion()  == null) && (this.classSeleccionListado.getCotizacion() == null) ){
               this.insertarDBCliente();
               JOptionPane.showMessageDialog(null, "Insertado Correctamente","Cliente Creado",JOptionPane.INFORMATION_MESSAGE);
           }*/
        Log.Registrar(this.getClass(), "insertDatosCliente", "se inserto el productto");
           
        }
        }catch(Exception e){
            Log.Error("crearProducto/insertDatosCliente producto", e);
        }
    }
   public void limpiarTextos(){
         Log.Registrar(this.getClass(), "limpiarTextos"," ");

       this.JTFTelefonoCliente.setText("");
       this.JTFSectorCliente.setText("");
       this.JTFCedulaCliente.setText("");this.JTFRNCCliente.setText("");
       this.JTFProvinciaCliente.setText("");this.JTADireccionCliente.setText("");
       this.JTFEmailCliente.setText("");
       this.JTFNombreCliente.setText("");
       this.JTFApellidoCliente.setText("");
       JTextField fechaT = (JTextField) this.JDCFechaNacimientoCliente.getDateEditor().getUiComponent();
       fechaT.setText("");
       Log.Registrar(this.getClass(), "limpiarTextos", "Se limpio los campos");
               
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
        btCrear = new javax.swing.JButton();
        btMostrarOpciones = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        JTFNombreCliente = new javax.swing.JTextField();
        JTFApellidoCliente = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        JLRNCCliente = new javax.swing.JLabel();
        JTFTelefonoCliente = new javax.swing.JTextField();
        JTFRNCCliente = new javax.swing.JTextField();
        JLCedulaCliente = new javax.swing.JLabel();
        JLFechaNacimientoCliente = new javax.swing.JLabel();
        JTFCedulaCliente = new javax.swing.JTextField();
        JDCFechaNacimientoCliente = new com.toedter.calendar.JDateChooser();
        JLSexoCliente = new javax.swing.JLabel();
        JLEmailCliente = new javax.swing.JLabel();
        JTFSectorCliente = new javax.swing.JTextField();
        JTFEmailCliente = new javax.swing.JTextField();
        JLProvinciaCliente = new javax.swing.JLabel();
        JLSectorCliente = new javax.swing.JLabel();
        JTFProvinciaCliente = new javax.swing.JTextField();
        JCBSexoCliente = new javax.swing.JComboBox<>();
        JLDireccionCliente = new javax.swing.JLabel();
        JPDireccion = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        JTADireccionCliente = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Crear Cliente");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new java.awt.GridLayout(0, 2, 4, 4));

        btCrear.setBackground(new java.awt.Color(0, 153, 102));
        btCrear.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btCrear.setForeground(new java.awt.Color(255, 255, 255));
        btCrear.setText("Crear Cliente");
        btCrear.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btCrearMouseClicked(evt);
            }
        });
        jPanel1.add(btCrear);

        btMostrarOpciones.setBackground(new java.awt.Color(0, 51, 204));
        btMostrarOpciones.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btMostrarOpciones.setForeground(new java.awt.Color(255, 255, 255));
        btMostrarOpciones.setText("Ver mas opciones");
        btMostrarOpciones.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btMostrarOpcionesMouseClicked(evt);
            }
        });
        jPanel1.add(btMostrarOpciones);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setText("Nombre cliente *");
        jPanel1.add(jLabel1);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel3.setText("Apellido cliente *");
        jPanel1.add(jLabel3);

        JTFNombreCliente.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jPanel1.add(JTFNombreCliente);

        JTFApellidoCliente.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        JTFApellidoCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JTFApellidoClienteActionPerformed(evt);
            }
        });
        jPanel1.add(JTFApellidoCliente);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel4.setText("Teléfono cliente *");
        jPanel1.add(jLabel4);

        JLRNCCliente.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        JLRNCCliente.setText("RNC cliente");
        jPanel1.add(JLRNCCliente);

        JTFTelefonoCliente.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jPanel1.add(JTFTelefonoCliente);

        JTFRNCCliente.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jPanel1.add(JTFRNCCliente);

        JLCedulaCliente.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        JLCedulaCliente.setText("Cedula cliente ");
        jPanel1.add(JLCedulaCliente);

        JLFechaNacimientoCliente.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        JLFechaNacimientoCliente.setText("Fecha Nacimiento");
        jPanel1.add(JLFechaNacimientoCliente);

        JTFCedulaCliente.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        JTFCedulaCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JTFCedulaClienteActionPerformed(evt);
            }
        });
        jPanel1.add(JTFCedulaCliente);

        JDCFechaNacimientoCliente.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jPanel1.add(JDCFechaNacimientoCliente);

        JLSexoCliente.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        JLSexoCliente.setText("Sexo cliente");
        jPanel1.add(JLSexoCliente);

        JLEmailCliente.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        JLEmailCliente.setText("Email cliente");
        jPanel1.add(JLEmailCliente);

        JTFSectorCliente.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        JTFSectorCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JTFSectorClienteActionPerformed(evt);
            }
        });
        jPanel1.add(JTFSectorCliente);

        JTFEmailCliente.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        JTFEmailCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JTFEmailClienteActionPerformed(evt);
            }
        });
        jPanel1.add(JTFEmailCliente);

        JLProvinciaCliente.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        JLProvinciaCliente.setText("Provincia cliente");
        jPanel1.add(JLProvinciaCliente);

        JLSectorCliente.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        JLSectorCliente.setText("Sector cliente");
        jPanel1.add(JLSectorCliente);

        JTFProvinciaCliente.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        JTFProvinciaCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JTFProvinciaClienteActionPerformed(evt);
            }
        });
        jPanel1.add(JTFProvinciaCliente);

        JCBSexoCliente.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        JCBSexoCliente.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "masculino", "femenino" }));
        JCBSexoCliente.setToolTipText("");
        jPanel1.add(JCBSexoCliente);

        JLDireccionCliente.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        JLDireccionCliente.setText("Dirección cliente");
        jPanel1.add(JLDireccionCliente);

        JPDireccion.setBackground(new java.awt.Color(255, 255, 255));

        JTADireccionCliente.setColumns(20);
        JTADireccionCliente.setRows(5);
        jScrollPane2.setViewportView(JTADireccionCliente);

        javax.swing.GroupLayout JPDireccionLayout = new javax.swing.GroupLayout(JPDireccion);
        JPDireccion.setLayout(JPDireccionLayout);
        JPDireccionLayout.setHorizontalGroup(
            JPDireccionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 338, Short.MAX_VALUE)
        );
        JPDireccionLayout.setVerticalGroup(
            JPDireccionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
        );

        jPanel1.add(JPDireccion);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(2, 2, 2))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 466, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void JTFApellidoClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JTFApellidoClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JTFApellidoClienteActionPerformed

    private void JTFEmailClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JTFEmailClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JTFEmailClienteActionPerformed

    private void JTFCedulaClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JTFCedulaClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JTFCedulaClienteActionPerformed

    private void JTFProvinciaClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JTFProvinciaClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JTFProvinciaClienteActionPerformed

    private void JTFSectorClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JTFSectorClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JTFSectorClienteActionPerformed

    private void btMostrarOpcionesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btMostrarOpcionesMouseClicked
        // TODO add your handling code here:
        this.mostrarOpciones();
    }//GEN-LAST:event_btMostrarOpcionesMouseClicked

    private void btCrearMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btCrearMouseClicked
        // TODO add your handling code here:
        //this.cargarVariables();
        insertDatosCliente();
    }//GEN-LAST:event_btCrearMouseClicked

    /**
     * @param args the command line arguments
     */
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> JCBSexoCliente;
    private com.toedter.calendar.JDateChooser JDCFechaNacimientoCliente;
    private javax.swing.JLabel JLCedulaCliente;
    private javax.swing.JLabel JLDireccionCliente;
    private javax.swing.JLabel JLEmailCliente;
    private javax.swing.JLabel JLFechaNacimientoCliente;
    private javax.swing.JLabel JLProvinciaCliente;
    private javax.swing.JLabel JLRNCCliente;
    private javax.swing.JLabel JLSectorCliente;
    private javax.swing.JLabel JLSexoCliente;
    private javax.swing.JPanel JPDireccion;
    private javax.swing.JTextArea JTADireccionCliente;
    private javax.swing.JTextField JTFApellidoCliente;
    private javax.swing.JTextField JTFCedulaCliente;
    private javax.swing.JTextField JTFEmailCliente;
    private javax.swing.JTextField JTFNombreCliente;
    private javax.swing.JTextField JTFProvinciaCliente;
    private javax.swing.JTextField JTFRNCCliente;
    private javax.swing.JTextField JTFSectorCliente;
    private javax.swing.JTextField JTFTelefonoCliente;
    private javax.swing.JButton btCrear;
    private javax.swing.JButton btMostrarOpciones;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}
