/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formularios.telefono;


import ClassStatic.Log;
import conexion.Mysql;
import formularios.cliente.ClienteAdministrar;
import formularios.usuarios.EmpleadoAdministrar;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author VOSTRO
 */
public class TipoTelefonoAdministrar extends javax.swing.JFrame {

    /**
     * Creates new form UsuariosAdministrar
     */
    
    private String usuarioID = "1";
    private String CurrentUsuarioID ="1";
    private String usuarioNombre = "admin";
    private Mysql conector = null;
    private String Origen= "tipotelefono";
    private EmpleadoAdministrar EmpleadoAdministrar;
    private ClienteAdministrar ClienteAdministrar;
    private formularios.administracion.ProveedorAdministrar ProveedorAdministrar;

    private String Buscador = "";
        
    public void setUsuarioID(String usuarioID) {
        this.usuarioID = usuarioID;
    }

    public void setUsuarioNombre(String usuarioNombre) {
        this.usuarioNombre = usuarioNombre;
    }

    public void setConector(Mysql conector) {
        this.conector = conector;
    }
    
    
    public void setFOrigen(EmpleadoAdministrar fOrigen,String origen){
        this.EmpleadoAdministrar = fOrigen;
        this.Origen = origen;
    }
    public void setFOrigen(formularios.administracion.ProveedorAdministrar fOrigen,String origen){
        this.ProveedorAdministrar = fOrigen;
        this.Origen = origen;
    }
    public void setFOrigen(ClienteAdministrar fOrigen,String origen){
        this.ClienteAdministrar = fOrigen;
        this.Origen = origen;
    }
    
    public void validarOrigen(){
        Log.Registrar(this.getClass(), "validarOrigen"," ");

            if(this.Origen.equals("tipotelefono")){
                JOptionPane.showMessageDialog(null,"Se inserto");
                this.jTextField1.setText("");
                this.cargarTabla();
            }else if(this.Origen.equals("empleado")){
                this.EmpleadoAdministrar.RecargarTipoTelefono();
                this.setVisible(false);
            }else if(this.Origen.equals("cliente")){
                this.ClienteAdministrar.RecargarTipoTelefono();
                this.setVisible(false);
            }else if(this.Origen.equals("proveedor")){
                this.ProveedorAdministrar.RecargarTipoTelefono();
                this.setVisible(false);
            }else if(this.Origen.equals("compraproducto")){
                //this.ProveedorAdministrar.RecargarTipoTelefono();
                this.setVisible(false);
            }
    }
    
    public TipoTelefonoAdministrar(Mysql conector,String usuarioID,
            String usuarioNombre) {
        initComponents();
        this.setConector(conector);
        this.setUsuarioID(usuarioID);
        this.setUsuarioNombre(usuarioNombre);
        this.cargarTabla();
    }
    
    
    /*
        Buscador
    */
    public void BuscadorCliente(){
 Log.Registrar(this.getClass(), "BuscadorCliente"," ");

        if(!(this.jTextFieldBuscador.getText().isEmpty())){
            this.Buscador = " and concat(nombre,' ',id) like '%"+this.jTextFieldBuscador.getText()+"%'";
            this.cargarTabla();
        }else{
            this.Buscador = "";
            this.cargarTabla();
        }
    }
    

    public void insert(){
  Log.Registrar(this.getClass(), "insert"," ");
       String usuario = this.jTextField1.getText();
       
       if(usuario.isEmpty()){
           JOptionPane.showMessageDialog(null,"El campo "
                   + "tipo teléfono esta vacío");
       }else{
           String table_name = "tipo_telefono";
            String campos = " * ";
            String otros = " where nombre = '"+usuario+"' ";
            java.sql.ResultSet resultSet = 
          this.conector.optenerDatosParaTabla(table_name,campos ,otros);
           try {
               if( !(resultSet.first()) ){
                   String tableI = "tipo_telefono";
                   String camposI = "nombre,usuario_id,fecha_creado";
                   String valoresI = " '"+usuario+"','"+this.usuarioID+"',now() ";
                   this.conector.insertData(tableI,camposI , valoresI);
                   this.validarOrigen();
                   
               }else{
                   JOptionPane.showMessageDialog(null, "El "
                           + "tipo teléfono  ya existe, favor ingrese otro");
               }
           } catch (SQLException ex) {
               Log.Error(this.getClass().getName(), ex);
               Logger.getLogger(TipoTelefonoAdministrar.class.getName())
                       .log(Level.SEVERE, null, ex);
           }
       }
       
       
    }
    public void editar(){
       Log.Registrar(this.getClass(), "editar"," ");

       String usuario = this.jTextField1.getText();

       if(usuario.isEmpty()){
           JOptionPane.showMessageDialog(null,"El campo tipo teléfono "
                   + "esta vacío");
       }else{
           String table = "tipo_telefono";
           String campos = "nombre='"+usuario+"' ";
           String where = " id = '"+this.CurrentUsuarioID+"' ";
           this.conector.actulizarDatos(table,campos , where);
           JOptionPane.showMessageDialog(null,"Se edito");
           this.jTextField1.setText("");
           this.cargarTabla();
       }
       
       
    }
    public void limpiar(){
    Log.Registrar(this.getClass(), "limpiar"," ");

         this.jTextField1.setText("");
    }
    public void eliminar(){
       Log.Registrar(this.getClass(), "eliminar"," ");

       int index = this.jTable1.getSelectedRow();
        if(index >= 0){
           if(JOptionPane.showConfirmDialog(null, "Esta "
                   + "seguro que desea eliminar el tipo teléfono?",
                   "Eliminar tipo teléfono", JOptionPane.YES_NO_OPTION,
                   JOptionPane.QUESTION_MESSAGE) 
                   == JOptionPane.YES_OPTION ){ 
                this.CurrentUsuarioID = this.jTable1.getValueAt(index, 0)
                        .toString();
                String table = "tipo_telefono";
                String campos = "display=false ";
                String where = " id = '"+this.CurrentUsuarioID+"' ";
                this.conector.actulizarDatos(table,campos , where);
                JOptionPane.showMessageDialog(null,"Se Elimino");
                this.limpiar();
                this.cargarTabla();
           }
        }else{
            JOptionPane.showMessageDialog(null, "No hay fila"
                    + " seleccionada");

       }
       
       
    }
    
  public void MostrarDato(){
       Log.Registrar(this.getClass(), "MostrarDato"," ");

        int index = this.jTable1.getSelectedRow();
        if(index >= 0){
        String id = this.jTable1.getValueAt(index, 0).toString();
        //String nombre= this.jTable1.getValueAt(index, 0).toString();
        //String tipo = this.jTable1.getValueAt(index, 0).toString();
        //JOptionPane.showMessageDialog(null, nombre+" "+id+" "+tipo);
        try {
        String table_name = "tipo_telefono";
        String campos = " * ";
        String otros = " where id = '"+id+"' ";
        java.sql.ResultSet resultSet = this.conector.
                
                optenerDatosParaTabla(table_name,campos ,otros);
        if( resultSet.first() ){
             this.CurrentUsuarioID = resultSet.getString("id");
             this.jTextField1.setText(resultSet.getString("nombre"));
             this.MostrarBotones(false, true,true );
          
        }else{
            JOptionPane.showMessageDialog(null, "No hay registro");
            //System.out.println("");
        }
          } catch (SQLException ex) {
              Log.Error(this.getClass().getName(), ex);
            System.out.println(ex.getCause().toString());
          }
        }else{
            JOptionPane.showMessageDialog(null, "No hay fila "
                    + "seleccionada");
        }
    }
    
  public void MostrarBotones(boolean b1,boolean b2,boolean b3){
  Log.Registrar(this.getClass(), "MostrarBotones"," ");

      this.jButton1.setVisible(b1);
      this.jButton2.setVisible(b2);
      this.jButton3.setVisible(b3);
  }
   public void cargarTabla(){
       Log.Registrar(this.getClass(), "cargarTabla"," ");

        try {
        String table_name = "tipo_telefono";
        String campos = " * ";
        String otros = " where display = 1 "+this.Buscador;
        java.sql.ResultSet resultSet = this.conector.
                optenerDatosParaTabla(table_name,campos ,otros);
        if( resultSet.first() ){
            int total = 0;
        do{
            total++;
        }while(resultSet.next());
        resultSet.first();
        String[] titulos = {"ID","NOMBRE"};
        Object[][] fila = new Object[total][10];
            
         int c = 0;
         
          do{
                fila[c][0] = resultSet.getString("id");
                    fila[c][1] = resultSet.getString("nombre");
                    c++;
             } while(resultSet.next());
          DefaultTableModel modelo = new DefaultTableModel(fila,titulos);
          this.jTable1.setModel(modelo);
          this.MostrarBotones(true, false, false);
        }else{
            JOptionPane.showMessageDialog(null, "No hay registro");
            //System.out.println("");
        }
          } catch (SQLException ex) {
              Log.Error(this.getClass().getName(), ex);
            System.out.println(ex.getCause().toString());
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

        jPopupMenu1 = new javax.swing.JPopupMenu();
        Editar = new javax.swing.JMenuItem();
        Eliminar = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jTextFieldBuscador = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();

        Editar.setText("Editar");
        Editar.setToolTipText("");
        Editar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                EditarMousePressed(evt);
            }
        });
        jPopupMenu1.add(Editar);

        Eliminar.setText("Eliminar");
        Eliminar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                EliminarMousePressed(evt);
            }
        });
        jPopupMenu1.add(Eliminar);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel9.setText("Que deseas buscar?");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, -1, -1));

        jTextFieldBuscador.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldBuscadorKeyReleased(evt);
            }
        });
        jPanel1.add(jTextFieldBuscador, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 540, -1));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTable1.setToolTipText("Click izquierdo para seleccionar la fila y luego click derecho para desplegar las opciones");
        jTable1.setComponentPopupMenu(jPopupMenu1);
        jScrollPane1.setViewportView(jTable1);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 540, 377));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 0, 550, 440));

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton3.setText("Cancelar");
        jButton3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton3MousePressed(evt);
            }
        });
        jPanel2.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 10, -1, -1));

        jButton2.setText("Editar");
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton2MousePressed(evt);
            }
        });
        jPanel2.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        jButton1.setText("Agregar");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton1MousePressed(evt);
            }
        });
        jPanel2.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 10, -1, -1));

        jLabel1.setText("Tipo de teléfono");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, -1, -1));
        jPanel2.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 265, -1));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 270, 430));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void EditarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_EditarMousePressed
        // TODO add your handling code here:
        this.MostrarDato();
    }//GEN-LAST:event_EditarMousePressed

    private void jButton1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MousePressed
        // TODO add your handling code here:
        this.insert();
    }//GEN-LAST:event_jButton1MousePressed

    private void jButton2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MousePressed
        // TODO add your handling code here:
        this.editar();
    }//GEN-LAST:event_jButton2MousePressed

    private void EliminarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_EliminarMousePressed
        // TODO add your handling code here:
        this.eliminar();
    }//GEN-LAST:event_EliminarMousePressed

    private void jButton3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton3MousePressed
        // TODO add your handling code here:
        this.MostrarBotones(true, false, false);
        this.limpiar();
    }//GEN-LAST:event_jButton3MousePressed

    private void jTextFieldBuscadorKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldBuscadorKeyReleased
        // TODO add your handling code here:
        this.BuscadorCliente();
    }//GEN-LAST:event_jTextFieldBuscadorKeyReleased

    /**
     * @param args the command line arguments
     */
 

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem Editar;
    private javax.swing.JMenuItem Eliminar;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextFieldBuscador;
    // End of variables declaration//GEN-END:variables
}
