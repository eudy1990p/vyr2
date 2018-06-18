/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formularios.administracion;

import ClassStatic.Log;
import conexion.Mysql;
import formularios.ListadoProducto;
import javax.swing.JOptionPane;

/**
 *
 * @author Eudy
 */
public class crearProducto extends javax.swing.JFrame {

    /**
     * Creates new form crearProducto
     */
    private boolean mostrarOpciones = true;
    private Mysql mysql;
    private String nombre="",precioVenta,precioCompra,cantidadComprada,cantidadVendida;
    private String productoID="1",usuarioID="1";
    private ListadoProducto classSeleccionListado = null;
    
     private String nombreUsuario = "";
     private String tipoProducto = "venta_producto";
    
        public void setTipoProducto( int tipoTabla){
                                    Log.Registrar(this.getClass(), "setTipoProducto"," "); 

        switch(tipoTabla){
            case 1:
                this.tipoProducto="venta_producto";
                
                break;
            case 2:
                //JOptionPane.showMessageDialog(null, "repacion_producto "+tipoTabla);
        
                this.tipoProducto="repacion_producto";
                
                break;
            default:
                //JOptionPane.showMessageDialog(null, "venta_producto "+tipoTabla);
                this.tipoProducto="venta_producto";
               
                break;
        }
    }
    public void setDatosUsuario(String nombre,String id){
                              Log.Registrar(this.getClass(), "setDatosUsuario"," "); 

        this.nombreUsuario = nombre;
        this.usuarioID = id;
        System.out.println(nombre+" "+id);
    }
    
    public crearProducto(Mysql mysql) {
        initComponents();
        this.mysql = mysql;
        this.mostrarOpciones();
        this.setLocationRelativeTo(null);
    }
    public void setClassSeleccionListado(ListadoProducto classSeleccionListado){
                         Log.Registrar(this.getClass(), "setClassSeleccionListado"," "); 

        this.classSeleccionListado =classSeleccionListado;
    }
    public void cargarVariables(){
                      Log.Registrar(this.getClass(), "cargarVariables"," "); 

        nombre = this.JTFNombreProducto.getText();
        precioVenta = this.JTFPrecioVentaProducto.getText();
        //rnc = this.JTFRNCCliente.getText();
        precioCompra = this.JTFPrecioCompraProducto.getText();
        cantidadComprada = this.JTFCantidadCompradaProducto.getText();
        cantidadVendida = this.JTFCantidadVendidaProducto.getText();
        //provincia = this.JTFProvinciaCliente.getText();
        //sector = this.JTFSectorCliente.getText();
        //direccion = this.JTADireccionCliente.getText();
        //sexo = this.JCBSexoCliente.getSelectedItem().toString();
        //javax.swing.JTextField fn = (javax.swing.JTextField) this.JDCFechaNacimientoCliente.getDateEditor().getUiComponent();
       //fechaNacimiento = ((javax.swing.JTextField)this.JDCFechaNacimientoCliente.getDateEditor().getUiComponent()).getText();

        //fechaNacimiento = fn.getText();
       // JOptionPane.showMessageDialog(null, this.fechaNacimiento);
       Log.Registrar("crearProducto/cargarVariables asignar datos a los atributos");
    }
 /*   public void insertarDBEmail(){
             String campos = "usuario_id,email,persona_id,fecha_creado";
             String valores = "'"+this.usuarioID+"','"+this.email+"','"+this.personaID+"',now()";
             this.mysql.insertData("email", campos, valores);
    }
    
     public void insertarDBTelefono(){
             String campos = "usuario_id,telephone,persona_id,fecha_creado,tipo_telefono_id";
             String valores = "'"+this.usuarioID+"','"+this.telefono+"','"+this.personaID+"',now(),'1'";
             this.mysql.insertData("telephone", campos, valores);
    }
     public void insertarDBDireccion(){
             String campos = "usuario_id,sector,persona_id,fecha_creado,provincia,localidad";
             String valores = "'"+this.usuarioID+"','"+this.sector+"','"+this.personaID+"',now(),'"+this.provincia+"','"+this.direccion+"'";
             this.mysql.insertData("direccion", campos, valores);
    }
    public void insertarDBPersona(){
           String campos = "nombre,apellido,sexo,fecha_creado,usuario_id,rnc";//fecha_nacimiento
             String valores = "'"+this.nombre+"','"+this.apellido+"','"+this.sexo+"',now(),'"+this.usuarioID+"','"+this.rnc+"'";
             this.mysql.insertData("persona", campos, valores);
             this.personaID = this.mysql.optenerUltimoID("persona");
    }*/
    public void insertarDBProducto(){
                Log.Registrar(this.getClass(), "insertarDBProducto"," "); 

           // String tipoProducto = "venta_producto";
           if(this.classSeleccionListado != null){
            if(this.classSeleccionListado.getReparacion() != null){
               this.tipoProducto = "repacion_producto"; 
            }
           }
           String campos = "usuario_id,nombre,cantidad_total,precio_compra,precio_venta,fecha_creado,tipo_producto";//fecha_nacimiento
             String valores = "'"+this.usuarioID+"','"+this.nombre+"','"+this.cantidadComprada+"','"+this.precioCompra+"','"+this.precioVenta+"',now(),'"+this.tipoProducto+"'";
                this.mysql.insertData("producto_inventariado", campos, valores);
             this.productoID = this.mysql.optenerUltimoID("producto_inventariado");
    }
    public void mostrarOpciones(){
                         Log.Registrar(this.getClass(), "mostrarOpciones"," "); 

          if(this.mostrarOpciones){
              this.mostrarOpciones = false;
          }else{
              this.mostrarOpciones = true;
          }
          //this.JCBSexoCliente.setVisible(mostrarOpciones);
          //this.JLSexoCliente.setVisible(mostrarOpciones);
          
          //this.JDCFechaNacimientoCliente.setVisible(mostrarOpciones);
          //this.JLFechaNacimientoCliente.setVisible(mostrarOpciones);
          
         // this.JTADireccionCliente.setVisible(mostrarOpciones);
          //this.JLDireccionCliente.setVisible(mostrarOpciones);
          
          this.JTFPrecioCompraProducto.setVisible(mostrarOpciones);
          this.JLCedulaCliente.setVisible(mostrarOpciones);
          
          this.JTFCantidadCompradaProducto.setVisible(mostrarOpciones);
          this.JLEmailCliente.setVisible(mostrarOpciones);
          
                  
          //this.JTFProvinciaCliente.setVisible(mostrarOpciones);
          //this.JLProvinciaCliente.setVisible(mostrarOpciones);
          
          //this.JTFRNCCliente.setVisible(mostrarOpciones);
          //this.JLRNCCliente.setVisible(mostrarOpciones);
          
          //this.JTFSectorCliente.setVisible(mostrarOpciones);
          //this.JLSectorCliente.setVisible(mostrarOpciones);
          Log.Registrar(this.getClass(),"mostrarOpciones"," se mostraron las opciones ocultas");

    }
    public void insertDatosCliente(){
         Log.Registrar(this.getClass(), "insertDatosCliente"," "); 

        try{
        this.cargarVariables();
        boolean existeUsuario = this.validarUsuario( this.nombre);
        
        if(this.nombre.isEmpty()){
            JOptionPane.showMessageDialog(null, "El campo nombre esta vacío","Existen campo vacío",JOptionPane.WARNING_MESSAGE);
        }else if(!existeUsuario){
            JOptionPane.showMessageDialog(null, "El item ya existe, favor introduzca otro nombre de item","Existe este item",JOptionPane.WARNING_MESSAGE);
        }else if(this.precioVenta.isEmpty()){
            JOptionPane.showMessageDialog(null, "El campo precio venta esta vacío","Existen campo vacío",JOptionPane.WARNING_MESSAGE);
        }else{
           if(this.cantidadComprada.isEmpty()){
               this.cantidadComprada = "0";
           }
           if(this.precioCompra.isEmpty()){
               this.precioCompra = "0.00";
           }
           this.insertarDBProducto();
           if(this.classSeleccionListado != null){
           if(this.classSeleccionListado.getCotizacion() != null){
                this.dispose();
                this.classSeleccionListado.getCotizacion().setDatosProducto(this.productoID, this.nombre, this.precioVenta,this.cantidadVendida);
                this.classSeleccionListado.perderFocus();
                this.classSeleccionListado.dispose();
           }
           if(this.classSeleccionListado.getFacturacion() != null){
                this.dispose();
                this.classSeleccionListado.getFacturacion().setDatosProducto(this.productoID, this.nombre, this.precioVenta,this.cantidadVendida);
                this.classSeleccionListado.perderFocus();
                this.classSeleccionListado.dispose();
           }
           if(this.classSeleccionListado.getReparacion()!= null){
                this.dispose();
                this.classSeleccionListado.getReparacion().setDatosProducto(this.productoID, this.nombre, this.precioVenta,this.cantidadVendida);
                this.classSeleccionListado.perderFocus();
                this.classSeleccionListado.dispose();
           }
           }else{
              JOptionPane.showMessageDialog(null, "El item fue Guardado correctamente","Se Guardo correctamente",JOptionPane.INFORMATION_MESSAGE);
              this.limpiarTextos(); 
           }

           Log.Registrar(this.getClass(), "insertDatosCliente", "se inserto el productto");
           
        }
        }catch(Exception e){
            Log.Error("crearProducto/insertDatosCliente producto", e);
        }
    }
    public boolean validarUsuario(String usuario){
                                    Log.Registrar(this.getClass(), "validarUsuario"," "); 

        String tipoProducto = "venta_producto";
           if(this.classSeleccionListado != null){
            if(this.classSeleccionListado.getReparacion() != null){
               tipoProducto = "repacion_producto"; 
            }
           }
           
         Log.Registrar("crearProducto/validarUsuario validar nombre del producto ");
         Log.Registrar("crearProducto/validarUsuario tabla "+tipoProducto);
         int existe = this.mysql.getValues("producto_inventariado", "where nombre = '"+this.nombre+"' and tipo_producto = '"+tipoProducto+"' ");
          if(existe < 1){
                Log.Registrar("crearProducto/validarUsuario el producto esta disponible");       
            return true;
        }else{
             Log.Registrar("crearProducto/validarUsuario el producto no esta disponible");
            //JOptionPane.showMessageDialog(null, "El usuario ya existe");
            return false;
          }
    }
    public void limpiarTextos(){
                        Log.Registrar(this.getClass(), "limpiarTextos"," "); 

        this.JTFCantidadCompradaProducto.setText("");this.JTFCantidadVendidaProducto.setText("");
        this.JTFNombreProducto.setText("");this.JTFPrecioCompraProducto.setText("");
       this.JTFPrecioVentaProducto.setText("");
       
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
        jButton1 = new javax.swing.JButton();
        btMostrarOpciones = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        JTFNombreProducto = new javax.swing.JTextField();
        JTFPrecioVentaProducto = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        JLEmailCliente = new javax.swing.JLabel();
        JTFCantidadVendidaProducto = new javax.swing.JTextField();
        JTFCantidadCompradaProducto = new javax.swing.JTextField();
        JLCedulaCliente = new javax.swing.JLabel();
        JTFPrecioCompraProducto = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Crear Producto");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new java.awt.GridLayout(0, 2, 4, 6));

        jButton1.setBackground(new java.awt.Color(0, 153, 102));
        jButton1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Crear Producto");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });
        jPanel1.add(jButton1);

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
        jLabel1.setText("Nombre Producto *");
        jPanel1.add(jLabel1);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel3.setText("Precio Venta Producto *");
        jPanel1.add(jLabel3);

        JTFNombreProducto.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jPanel1.add(JTFNombreProducto);

        JTFPrecioVentaProducto.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        JTFPrecioVentaProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JTFPrecioVentaProductoActionPerformed(evt);
            }
        });
        jPanel1.add(JTFPrecioVentaProducto);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel4.setText("Cantidad Vendida Producto");
        jPanel1.add(jLabel4);

        JLEmailCliente.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        JLEmailCliente.setText("Cantidad Comprada Producto");
        jPanel1.add(JLEmailCliente);

        JTFCantidadVendidaProducto.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jPanel1.add(JTFCantidadVendidaProducto);

        JTFCantidadCompradaProducto.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        JTFCantidadCompradaProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JTFCantidadCompradaProductoActionPerformed(evt);
            }
        });
        jPanel1.add(JTFCantidadCompradaProducto);

        JLCedulaCliente.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        JLCedulaCliente.setText("Precio Compra Producto");
        jPanel1.add(JLCedulaCliente);

        JTFPrecioCompraProducto.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        JTFPrecioCompraProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JTFPrecioCompraProductoActionPerformed(evt);
            }
        });
        jPanel1.add(JTFPrecioCompraProducto);

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
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void JTFPrecioVentaProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JTFPrecioVentaProductoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JTFPrecioVentaProductoActionPerformed

    private void JTFCantidadCompradaProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JTFCantidadCompradaProductoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JTFCantidadCompradaProductoActionPerformed

    private void JTFPrecioCompraProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JTFPrecioCompraProductoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JTFPrecioCompraProductoActionPerformed

    private void btMostrarOpcionesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btMostrarOpcionesMouseClicked
        // TODO add your handling code here:
        this.mostrarOpciones();
    }//GEN-LAST:event_btMostrarOpcionesMouseClicked

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        // TODO add your handling code here:
        //this.cargarVariables();
        insertDatosCliente();
    }//GEN-LAST:event_jButton1MouseClicked

    /**
     * @param args the command line arguments
     */
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel JLCedulaCliente;
    private javax.swing.JLabel JLEmailCliente;
    private javax.swing.JTextField JTFCantidadCompradaProducto;
    private javax.swing.JTextField JTFCantidadVendidaProducto;
    private javax.swing.JTextField JTFNombreProducto;
    private javax.swing.JTextField JTFPrecioCompraProducto;
    private javax.swing.JTextField JTFPrecioVentaProducto;
    private javax.swing.JButton btMostrarOpciones;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
