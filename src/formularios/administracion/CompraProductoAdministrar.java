/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formularios.administracion;

import ClassStatic.Log;
import formularios.usuarios.*;
import conexion.Mysql;
import formularios.ListadoProducto;
import java.awt.Color;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author VOSTRO
 */
public class CompraProductoAdministrar extends javax.swing.JFrame {

    /**
     * Creates new form UsuariosAdministrar
     */
    
    private String usuarioID = "1";
    private String CurrentClienteID ="1";
    private String CurrentPersonaID ="1";
    private String usuarioNombre = "admin";
    private Mysql conector = null;
    
    private String nombre="",
            apellido,
            sexo,
            rnc,
            fechaNacimiento,
            cedula,
            email,
            telefono,
            provincia,
            sector,
            direccion,
            usuario,
            clave,
            tipoUsuario;
    
    private String personaID="1",
            empleadoID="1",
            usuarioEmpleadoID="1",
            FiltrarProducto="",
            FiltrarProveedor="";
    
  
    private ArrayList<String> PersonaEmails = new ArrayList<String>();
    private ArrayList<String> PersonaEmailIDs = new ArrayList<String>();
    
    private ArrayList<String> PersonaDireccion = new ArrayList<String>();
    private ArrayList<String> PersonaDireccionIDs = new ArrayList<String>();
    
    private ArrayList<String> PersonaTelefono = new ArrayList<String>();
    private ArrayList<String> PersonaTelefonoTipoNombre = new ArrayList<String>();
    private ArrayList<String> PersonaTelefonoTipoID = new ArrayList<String>();
    private ArrayList<String> PersonaTelefonoIDs = new ArrayList<String>();
 
    private ArrayList<String> ArrayTipoTelefonoIDs =new ArrayList<String>();
       
    private String CurrentEmailID = "1",CurrentDireccionID = "1",CurrentTelefonoID = "1";
    private boolean EditarClienteActivado = false;
    
    private String Buscador = "";
    private CompraProductoAdministrar yo;
    
    private ListadoProducto classSeleccionListado;
    private String trabajandoClase = "contizacion";
    
    private String nombreUsuario = "";
    
    private String productoID,proveedorID;
    

    public void setYo(CompraProductoAdministrar yo) {
        this.yo = yo;
    }
    
    public void setUsuarioID(String usuarioID) {
        this.usuarioID = usuarioID;
    }

    public void setUsuarioNombre(String usuarioNombre) {
        this.usuarioNombre = usuarioNombre;
    }

    public void setConector(Mysql conector) {
        this.conector = conector;
    }
    

    
    public CompraProductoAdministrar(Mysql conector,String usuarioID,String usuarioNombre) {
        initComponents();
        this.setConector(conector);
        this.setUsuarioID(usuarioID);
        this.setUsuarioNombre(usuarioNombre);
        this.cargarTabla();
        this.cargarTablaProducto();
        this.cargarTablaProveedor();
        //this.MostrarBotonesEmail(true, false, false);
        //this.MostrarBotonesDireccion(true, false, false);
        //this.MostrarBotonesTelefono(true, false, false);
        this.setLocationRelativeTo(null);
        JScrollPane[] listScroll = {this.jScrollPane1,this.jScrollPane2,this.jScrollPane3};
        ClassStatic.ConfTable.setBackGroundColor(listScroll );
        
       
    }
    
    
    /*
        Buscador
    */
    public void BuscadorCliente(){
Log.Registrar(this.getClass(), "BuscadorCliente", "");
         this.Buscador = "";
        if(!(this.jTextFieldBuscador.getText().isEmpty())){
            this.Buscador = " and concat(p.nombre,' ',p.apellido,' ',p.cedula,' ',p.rnc,' ',c.id) like '%"+this.jTextFieldBuscador.getText()+"%'";
            //this.cargarTabla();
        }
        this.cargarTabla();
        
    }
    
    
    
    /*
    Tipo telefono
    */
       
    //VALIDAR USUARIO 
    public boolean validarUsuario(){
                Log.Registrar(this.getClass(), "validarUsuario", "");

         int existe = this.conector.getValues("producto_inventariado", "where nombre = '"+this.usuario+"' ");
          if(existe < 1){
            return true;
        }else{
            //JOptionPane.showMessageDialog(null, "El usuario ya existe");
            return false;
          }
    }
    
    public void insertarDBPersona(){
               Log.Registrar(this.getClass(), "insertarDBPersona", "");

           String campos = "nombre,apellido,sexo,fecha_creado,usuario_id,rnc";//fecha_nacimiento
             String valores = "'"+this.nombre+"','"+this.apellido+"','"+this.sexo+"',now(),'"+this.usuarioID+"','"+this.rnc+"'";
             this.conector.insertData("producto_inventariado", campos, valores);
             this.personaID = this.conector.optenerUltimoID("persona");
    }
    /*public void insertarDBEmpleado(){
           String campos = "usuario_id,persona_id,fecha_creado,usuario_empleado_id";//fecha_nacimiento
             String valores = "'"+this.usuarioID+"','"+this.personaID+"',now(),'"+this.usuarioEmpleadoID+"'";
             this.conector.insertData("empleado", campos, valores);
             this.empleadoID = this.conector.optenerUltimoID("empleado");
    }*/
    /*
   ADMINISTRAR empleado / PERSONA
   */
    public void insert(){
           Log.Registrar(this.getClass(), "insert", "");

       //valida si podemos continuar
        if(this.validarVacioContinuo()){
            if(this.validarUsuario()){
                try {
                   //validamos de que el resultado de la db sea 0 si lo es podemos crear el cliente
                   //if( !(resultSet.first()) ){
                       //insertamos primero a persona porque es una entidad fuerte
                       this.insertarDBPersona();
                       //this.insertarDBEmpleado();

                       this.limpiar();
                       this.cargarTabla();

               }catch (Exception ex) {
                   Log.Error(this.getClass().getName(), ex);
                   Logger.getLogger(CompraProductoAdministrar.class.getName()).log(Level.SEVERE, null, ex);
               }
           }else{
                   JOptionPane.showMessageDialog(null, " Ya existe el producto, favor ingrese otro");
             }
       }
       
       
    }
    public void cargarVariables(){      
        Log.Registrar(this.getClass(), "cargarVariables", "");

        nombre = this.JTFCantidad.getText();
        apellido = this.JTFPrecioCompra.getText();
        cedula = this.jTFPrecioVenta.getText();
        //javax.swing.JTextField fn = (javax.swing.JTextField) this.JDCFechaNacimientoCliente.getDateEditor().getUiComponent();
    }

    
    
    public boolean validarVacioContinuo(){
         Log.Registrar(this.getClass(), "validarVacioContinuo", "");

        this.cargarVariables();
       /*this.nombre =  this.JTFNombre.getText();
       this.apellido =  this.JTFApellido.getText();
       this.rnc =  this.JTFRNC.getText();
       this.cedula =  this.jTFCedula.getText();*/
       //significa no puede continuar
       boolean respuesta = false;
       //validamos que el dato no este vacío
       if(nombre.isEmpty()){
           JOptionPane.showMessageDialog(null,"El campo nombre esta vacío");
       }else if(apellido.isEmpty()){
            JOptionPane.showMessageDialog(null,"El campo apellido esta vacío");
       }else if(this.usuario.isEmpty()){
            JOptionPane.showMessageDialog(null,"El campo usuario esta vacío");
       }else if(this.clave.isEmpty()){
            JOptionPane.showMessageDialog(null,"El campo clave esta vacío");
       }else{
           //como los campos necesario no estan vacío le decimos que puede continuar
           respuesta = true;
       }
       return respuesta;
    }
    public void editar(){        
        Log.Registrar(this.getClass(), "editar", "");

      
       if(this.validarVacioContinuo()){
           String table = "empleado as c "
                + "inner join persona as p on c.persona_id = p.id "
                + "inner join usuario as u on c.usuario_empleado_id = u.id ";
           String campos = "p.nombre='"+this.nombre+"',"
                   + "p.apellido='"+this.apellido+"',"
                   + "p.cedula='"+this.cedula+"',"
                   + "u.tipo_usuario='"+this.tipoUsuario+"',"
                   + "u.clave_usuario='"+this.clave+"',"
                   + "p.fecha_nacimiento='"+this.fechaNacimiento+"' ";
           String where = " c.id = '"+this.CurrentClienteID+"' ";
           this.conector.actulizarDatos(table,campos , where);
           JOptionPane.showMessageDialog(null,"Se edito");
           this.limpiar();
           this.cargarTabla();
           this.EditarClienteActivado = false;
        }
       
       
    }
    public void limpiar(){
         Log.Registrar(this.getClass(), "limpiar", "");
        this.JTFPrecioCompra.setText("");
        this.JTFCantidad.setText("");
        this.jTFPrecioVenta.setText("");
        
        this.jTabbedPane1.setSelectedIndex(0);
        this.PersonaEmailIDs.clear();
        this.PersonaEmails.clear();
        
        
        this.PersonaDireccionIDs.clear();
        this.PersonaDireccion.clear();
        
        
        this.PersonaTelefonoIDs.clear();
        this.PersonaTelefono.clear();
        this.PersonaTelefonoTipoNombre.clear();
        this.PersonaTelefonoTipoID.clear();
        
        
    }
    public void eliminar(){        
        Log.Registrar(this.getClass(), "eliminar", "");

       int index = this.jTableCliente.getSelectedRow();
        if(index >= 0){
           if(JOptionPane.showConfirmDialog(null, "Esta seguro que desea eliminar el registro?", "Eliminar ", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION ){ 
                this.CurrentClienteID = this.jTableCliente.getValueAt(index, 0).toString();
                String table = "producto_inventariado";
                String campos = "display=false ";
                String where = " id = '"+this.CurrentClienteID+"' ";
                this.conector.actulizarDatos(table,campos , where);
                JOptionPane.showMessageDialog(null,"Se Elimino");
                
                this.limpiar();
                this.cargarTabla();
           }
        }else{
            JOptionPane.showMessageDialog(null, "No hay fila seleccionada");

       }
       
       
    }
    
  public void MostrarDato(){
Log.Registrar(this.getClass(), "MostrarDato", "");

        int index = this.jTableCliente.getSelectedRow();
        if(index >= 0){
        String id = this.jTableCliente.getValueAt(index, 0).toString();
       try {
        String table_name = "empleado as c "
                + "inner join persona as p on c.persona_id = p.id "
                + "inner join usuario as u on c.usuario_empleado_id = u.id ";
        String campos = " p.rnc,p.fecha_nacimiento,p.sexo,u.clave_usuario,c.id,p.nombre,p.apellido,p.cedula, u.nombre_usuario as usuario,u.tipo_usuario as rol, p.id as personaid ";
        String otros = " where c.id = '"+id+"' and c.display = true ";
        java.sql.ResultSet resultSet = this.conector.optenerDatosParaTabla(table_name,campos ,otros);
        if( resultSet.first() ){
                   //Mostrar datos personales y del cliente
                   this.CurrentClienteID = resultSet.getString("id");
                   this.JTFPrecioCompra.setText(resultSet.getString("apellido"));
                   this.JTFCantidad.setText(resultSet.getString("nombre"));
                   this.jTFPrecioVenta.setText(resultSet.getString("cedula"));
                   this.CurrentPersonaID = resultSet.getString("personaid");
                   
                   this.MostrarBotones(false, true,true );
                   
                   this.EditarClienteActivado = true;
                   //email
                   
          }else{
            JOptionPane.showMessageDialog(null, "No hay registro");
            //System.out.println("");
        }
          } catch (SQLException ex) {
              Log.Error(this.getClass().getName(), ex);
            System.out.println(ex.getCause().toString());
          }
        }else{
            JOptionPane.showMessageDialog(null, "No hay fila seleccionada");
        }
    }
    
  public void MostrarBotones(boolean b1,boolean b2,boolean b3){
Log.Registrar(this.getClass(), "MostrarBotones", "");

      this.jButtonAgregarCliente.setVisible(b1);
      this.jButtonEditarCliente.setVisible(b2);
      this.jButtonCancelarCliente.setVisible(b3);
      
  }
  
  public void cargarTablaProveedor(){      
      Log.Registrar(this.getClass(), "cargarTablaProveedor", "");

        try {
        String table_name = "proveedor as c "
                + "inner join persona as p on c.persona_id = p.id ";
        String campos = 
                "c.id, p.rnc,date(p.fecha_nacimiento) as fecha_nacimiento,p.sexo,p.nombre,p.apellido,p.cedula, p.id as personaid ";
        String otros = " where c.display = true "+this.FiltrarProveedor;
        java.sql.ResultSet resultSet = this.conector.optenerDatosParaTabla(table_name,campos ,otros);
        if( resultSet.first() ){
            int total = 0;
        do{
            total++;
        }while(resultSet.next());
        resultSet.first();
        String[] titulos = {"ID","RNC","SEXO","NOMBRE COMPLE","CEDULA"};
        
        Object[][] fila = new Object[total][10];
            
         int c = 0;
         
          do{
                    fila[c][0] = resultSet.getString("id");
                    fila[c][1] = resultSet.getString("rnc");
                    fila[c][2] = resultSet.getString("sexo");
                    fila[c][3] = resultSet.getString("nombre")+" "+resultSet.getString("apellido");
                    fila[c][3] = resultSet.getString("cedula");
                    c++;
             } while(resultSet.next());
          DefaultTableModel modelo = new DefaultTableModel(fila,titulos);
          this.jTProveedor.setModel(modelo);
          this.MostrarBotones(true, false, false);
        }else{
            //JOptionPane.showMessageDialog(null, "No hay registro");
            //System.out.println("");
        }
        this.limpiar();
          } catch (SQLException ex) {
              Log.Error(this.getClass().getName(), ex);
            System.out.println(ex.getCause().toString());
          }
    }
  
  public void cargarTablaProducto(){      
      Log.Registrar(this.getClass(), "cargarTablaProducto", "");

        try {
         String table_name = 
                " producto_inventariado ";
        String campos = 
                " * ";
                
        String otros = " where  display = true "+this.FiltrarProducto+" order by tipo_producto asc ";
        java.sql.ResultSet resultSet = this.conector.optenerDatosParaTabla(table_name,campos ,otros);
        if( resultSet.first() ){
            int total = 0;
        do{
            total++;
        }while(resultSet.next());
        resultSet.first();
        String[] titulos = {"ID","PRODUCTO","PRECIO VENTA","TIPO"};
        
        Object[][] fila = new Object[total][10];
            
         int c = 0;
         
          do{
                    fila[c][0] = resultSet.getString("id");
                    fila[c][1] = resultSet.getString("nombre");
                    fila[c][2] = resultSet.getString("precio_venta");
                    fila[c][3] = resultSet.getString("cantidad_total");
                    fila[c][3] = resultSet.getString("tipo_producto");
                    c++;
             } while(resultSet.next());
          DefaultTableModel modelo = new DefaultTableModel(fila,titulos);
          this.jTProducto.setModel(modelo);
          this.MostrarBotones(true, false, false);
        }else{
            //JOptionPane.showMessageDialog(null, "No hay registro");
            //System.out.println("");
        }
        this.limpiar();
          } catch (SQLException ex) {
              Log.Error(this.getClass().getName(), ex);
            System.out.println(ex.getCause().toString());
          }
    }
  public void cargarTabla(){
      Log.Registrar(this.getClass(), "cargarTabla", "");

        try {
         String table_name = 
                "compra_producto as cp\n" +
                "inner join proveedor as p on cp.proveedor_id = p.id\n" +
                "inner join persona as pp on pp.id = p.persona_id\n" +
                "inner join producto_inventariado as pi on cp.producto_inventariado_id = pi.id";
         
        String campos = 
                "concat(pp.nombre,' ',pp.apellido) as proveedor,\n" +
                "pi.nombre as producto,\n" +
                "cp.precio_compra, cp.precio_venta, cp.cantidad_comprada\n";
                
        String otros = " where  cp.display = true "+this.Buscador+" ";
        java.sql.ResultSet resultSet = this.conector.optenerDatosParaTabla(table_name,campos ,otros);
        if( resultSet.first() ){
            int total = 0;
        do{
            total++;
        }while(resultSet.next());
        resultSet.first();
        String[] titulos = {"ID","PROVEEDOR","PRODUCTO","PRECIO COMPRA","PRECIO VENTA","CANTIDAD COMPRADA"};
        
        Object[][] fila = new Object[total][10];
            
         int c = 0;
         
          do{
                    fila[c][0] = resultSet.getString("id");
                    fila[c][1] = resultSet.getString("proveedor");
                    fila[c][2] = resultSet.getString("producto");
                    fila[c][3] = resultSet.getString("precio_compra"); 
                    fila[c][4] = resultSet.getString("precio_venta");
                    fila[c][5] = resultSet.getString("cantidad_comprada");
                    c++;
             } while(resultSet.next());
          DefaultTableModel modelo = new DefaultTableModel(fila,titulos);
          this.jTableCliente.setModel(modelo);
          this.MostrarBotones(true, false, false);
        }else{
            //JOptionPane.showMessageDialog(null, "No hay registro");
            //System.out.println("");
        }
        this.limpiar();
          } catch (SQLException ex) {
              Log.Error(this.getClass().getName(), ex);
            System.out.println(ex.getCause().toString());
          }
        
    }
   
  
   public void filtrarProductoTipo(){        
       Log.Registrar(this.getClass(), "filtrarProductoTipo", "");

        this.FiltrarProducto ="and tipo_producto='"+this.jComboBox1.getSelectedItem().toString()+"' ";
        this.cargarTablaProducto();
    }
    public void filtrarProductoTexto(){        
        Log.Registrar(this.getClass(), "filtrarProductoTexto", "");

        this.FiltrarProducto ="and nombre like '%"+this.jTFFiltroProducto.getText()+"%' ";
        this.cargarTablaProducto();
    }
    public void filtrarProveedorTexto(){      
        Log.Registrar(this.getClass(), "filtrarProveedorTexto", "");

        this.FiltrarProducto =" and concat(nombre, ) like '%"+this.jTFFiltroProducto.getText()+"%' ";
        this.cargarTablaProveedor();
    }
    public void obtenerIDProveedorSeleccionado(){
                Log.Registrar(this.getClass(), "obtenerIDProveedorSeleccionado", "");

    this.proveedorID = this.jTProveedor.getValueAt(jTProveedor.getSelectedRow(), 0).toString();
    jTabbedPane1.setSelectedIndex(2);
}  
    
public void obtenerIDProductoSeleccionado(){
             Log.Registrar(this.getClass(), "obtenerIDProductoSeleccionado", "");

    this.productoID = this.jTProducto.getValueAt(jTProducto.getSelectedRow(), 0).toString();
    jTabbedPane1.setSelectedIndex(1);
}  
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenuCliente = new javax.swing.JPopupMenu();
        Editar = new javax.swing.JMenuItem();
        Eliminar = new javax.swing.JMenuItem();
        jPopupMenuEmail = new javax.swing.JPopupMenu();
        EditarEmail = new javax.swing.JMenuItem();
        EliminarEmail = new javax.swing.JMenuItem();
        jPopupMenuDireccion = new javax.swing.JPopupMenu();
        EditarDireccion = new javax.swing.JMenuItem();
        EliminarDireccion = new javax.swing.JMenuItem();
        jPopupMenuTelefono = new javax.swing.JPopupMenu();
        EditarTelefono = new javax.swing.JMenuItem();
        EliminarTelefono = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jTextFieldBuscador = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableCliente = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPPaso1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTProducto = new javax.swing.JTable();
        jTFFiltroProducto = new javax.swing.JTextField();
        jBCrearProducto = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox<>();
        jPPaso2 = new javax.swing.JPanel();
        jTextField2 = new javax.swing.JTextField();
        jBCrearProveedor = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTProveedor = new javax.swing.JTable();
        jPanelDatosPersonales = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        JTFPrecioCompra = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        JTFCantidad = new javax.swing.JTextField();
        jTFPrecioVenta = new javax.swing.JTextField();
        jButtonAgregarCliente = new javax.swing.JButton();
        jButtonCancelarCliente = new javax.swing.JButton();
        jButtonEditarCliente = new javax.swing.JButton();

        Editar.setText("Editar");
        Editar.setToolTipText("");
        Editar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                EditarMousePressed(evt);
            }
        });
        jPopupMenuCliente.add(Editar);

        Eliminar.setText("Eliminar");
        Eliminar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                EliminarMousePressed(evt);
            }
        });
        jPopupMenuCliente.add(Eliminar);

        EditarEmail.setText("Editar");
        EditarEmail.setToolTipText("");
        EditarEmail.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                EditarEmailMousePressed(evt);
            }
        });
        jPopupMenuEmail.add(EditarEmail);

        EliminarEmail.setText("Eliminar");
        EliminarEmail.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                EliminarEmailMousePressed(evt);
            }
        });
        jPopupMenuEmail.add(EliminarEmail);

        EditarDireccion.setText("Editar");
        EditarDireccion.setToolTipText("");
        EditarDireccion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                EditarDireccionMousePressed(evt);
            }
        });
        jPopupMenuDireccion.add(EditarDireccion);

        EliminarDireccion.setText("Eliminar");
        EliminarDireccion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                EliminarDireccionMousePressed(evt);
            }
        });
        jPopupMenuDireccion.add(EliminarDireccion);

        EditarTelefono.setText("Editar");
        EditarTelefono.setToolTipText("");
        EditarTelefono.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                EditarTelefonoMousePressed(evt);
            }
        });
        jPopupMenuTelefono.add(EditarTelefono);

        EliminarTelefono.setText("Eliminar");
        EliminarTelefono.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                EliminarTelefonoMousePressed(evt);
            }
        });
        jPopupMenuTelefono.add(EliminarTelefono);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Administrar producto venta / reparacion  ");

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jTextFieldBuscador.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldBuscadorKeyReleased(evt);
            }
        });

        jLabel9.setText("Que deseas buscar?");

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));

        jTableCliente.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTableCliente.setToolTipText("Click izquierdo para seleccionar la fila y luego click derecho para desplegar las opciones");
        jTableCliente.setComponentPopupMenu(jPopupMenuCliente);
        jScrollPane1.setViewportView(jTableCliente);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel9)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTextFieldBuscador)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 516, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addGap(6, 6, 6)
                .addComponent(jTextFieldBuscador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jTabbedPane1.setBackground(new java.awt.Color(255, 255, 255));

        jPPaso1.setBackground(new java.awt.Color(255, 255, 255));

        jTProducto.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTProducto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTProductoMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTProducto);

        jTFFiltroProducto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTFFiltroProductoKeyReleased(evt);
            }
        });

        jBCrearProducto.setBackground(new java.awt.Color(0, 153, 102));
        jBCrearProducto.setForeground(new java.awt.Color(255, 255, 255));
        jBCrearProducto.setText("Crear nuevo producto");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "venta_producto", "repacion_producto" }));
        jComboBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox1ItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPPaso1Layout = new javax.swing.GroupLayout(jPPaso1);
        jPPaso1.setLayout(jPPaso1Layout);
        jPPaso1Layout.setHorizontalGroup(
            jPPaso1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPPaso1Layout.createSequentialGroup()
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBCrearProducto)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPPaso1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPPaso1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTFFiltroProducto, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 343, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 343, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPPaso1Layout.setVerticalGroup(
            jPPaso1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPPaso1Layout.createSequentialGroup()
                .addGroup(jPPaso1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBCrearProducto)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTFFiltroProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 362, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Paso 1 de 3", jPPaso1);

        jPPaso2.setBackground(new java.awt.Color(255, 255, 255));

        jTextField2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField2KeyReleased(evt);
            }
        });

        jBCrearProveedor.setBackground(new java.awt.Color(0, 153, 102));
        jBCrearProveedor.setForeground(new java.awt.Color(255, 255, 255));
        jBCrearProveedor.setText("Crear nuevo proveedor");

        jTProveedor.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane3.setViewportView(jTProveedor);

        javax.swing.GroupLayout jPPaso2Layout = new javax.swing.GroupLayout(jPPaso2);
        jPPaso2.setLayout(jPPaso2Layout);
        jPPaso2Layout.setHorizontalGroup(
            jPPaso2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPPaso2Layout.createSequentialGroup()
                .addGap(198, 198, 198)
                .addComponent(jBCrearProveedor)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPPaso2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 343, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPPaso2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 343, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPPaso2Layout.setVerticalGroup(
            jPPaso2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPPaso2Layout.createSequentialGroup()
                .addComponent(jBCrearProveedor)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 367, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Paso 2 de 3", jPPaso2);

        jPanelDatosPersonales.setBackground(new java.awt.Color(255, 255, 255));
        jPanelDatosPersonales.setPreferredSize(new java.awt.Dimension(300, 346));

        jLabel3.setText("Cantidad");

        jLabel1.setText("Precio Compra");

        jLabel2.setText("Si desea cambiar el precio de venta ingrese lo aquí");

        jButtonAgregarCliente.setBackground(new java.awt.Color(0, 153, 102));
        jButtonAgregarCliente.setForeground(new java.awt.Color(255, 255, 255));
        jButtonAgregarCliente.setText("Agregar");
        jButtonAgregarCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButtonAgregarClienteMousePressed(evt);
            }
        });

        jButtonCancelarCliente.setBackground(new java.awt.Color(255, 51, 0));
        jButtonCancelarCliente.setForeground(new java.awt.Color(255, 255, 255));
        jButtonCancelarCliente.setText("Cancelar");
        jButtonCancelarCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtonCancelarClienteMouseClicked(evt);
            }
        });

        jButtonEditarCliente.setBackground(new java.awt.Color(0, 51, 204));
        jButtonEditarCliente.setForeground(new java.awt.Color(255, 255, 255));
        jButtonEditarCliente.setText("Editar");
        jButtonEditarCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButtonEditarClienteMousePressed(evt);
            }
        });

        javax.swing.GroupLayout jPanelDatosPersonalesLayout = new javax.swing.GroupLayout(jPanelDatosPersonales);
        jPanelDatosPersonales.setLayout(jPanelDatosPersonalesLayout);
        jPanelDatosPersonalesLayout.setHorizontalGroup(
            jPanelDatosPersonalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDatosPersonalesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelDatosPersonalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelDatosPersonalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(JTFCantidad, javax.swing.GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE)
                        .addComponent(jLabel3)
                        .addComponent(JTFPrecioCompra, javax.swing.GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addComponent(jTFPrecioVenta, javax.swing.GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE)
                        .addComponent(jLabel2))
                    .addGroup(jPanelDatosPersonalesLayout.createSequentialGroup()
                        .addComponent(jButtonEditarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonCancelarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonAgregarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(60, Short.MAX_VALUE))
        );
        jPanelDatosPersonalesLayout.setVerticalGroup(
            jPanelDatosPersonalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDatosPersonalesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JTFCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JTFPrecioCompra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTFPrecioVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addGroup(jPanelDatosPersonalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonAgregarCliente)
                    .addComponent(jButtonCancelarCliente)
                    .addComponent(jButtonEditarCliente))
                .addContainerGap(219, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Paso 3 de 3", jPanelDatosPersonales);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 6, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(1, 1, 1))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void EditarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_EditarMousePressed
        // TODO add your handling code here:
        this.MostrarDato();
    }//GEN-LAST:event_EditarMousePressed

    private void jButtonAgregarClienteMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonAgregarClienteMousePressed
        // TODO add your handling code here:
        this.insert();
    }//GEN-LAST:event_jButtonAgregarClienteMousePressed

    private void jButtonEditarClienteMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonEditarClienteMousePressed
        // TODO add your handling code here:
        this.editar();
    }//GEN-LAST:event_jButtonEditarClienteMousePressed

    private void EliminarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_EliminarMousePressed
        // TODO add your handling code here:
        this.eliminar();
    }//GEN-LAST:event_EliminarMousePressed

    private void jButtonCancelarClienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonCancelarClienteMouseClicked
        // TODO add your handling code here:
        this.MostrarBotones(true, false, false);
        this.EditarClienteActivado = false;
        this.limpiar();
    }//GEN-LAST:event_jButtonCancelarClienteMouseClicked

    private void EditarEmailMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_EditarEmailMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_EditarEmailMousePressed

    private void EliminarEmailMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_EliminarEmailMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_EliminarEmailMousePressed

    private void EditarDireccionMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_EditarDireccionMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_EditarDireccionMousePressed

    private void EliminarDireccionMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_EliminarDireccionMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_EliminarDireccionMousePressed

    private void EditarTelefonoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_EditarTelefonoMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_EditarTelefonoMousePressed

    private void EliminarTelefonoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_EliminarTelefonoMousePressed
        // TODO add your handling code here:
       
    }//GEN-LAST:event_EliminarTelefonoMousePressed

    private void jTextFieldBuscadorKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldBuscadorKeyReleased
        // TODO add your handling code here:
        this.BuscadorCliente();
    }//GEN-LAST:event_jTextFieldBuscadorKeyReleased

    private void jComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox1ItemStateChanged
        // TODO add your handling code here:
       this.filtrarProductoTipo();
    }//GEN-LAST:event_jComboBox1ItemStateChanged

    private void jTFFiltroProductoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTFFiltroProductoKeyReleased
        // TODO add your handling code here:
        this.filtrarProductoTexto();
    }//GEN-LAST:event_jTFFiltroProductoKeyReleased
    
    private void jTProductoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTProductoMouseClicked
        // TODO add your handling code here:
        this.obtenerIDProductoSeleccionado();
    }//GEN-LAST:event_jTProductoMouseClicked

    private void jTextField2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField2KeyReleased
        // TODO add your handling code here:
        this.filtrarProveedorTexto();
    }//GEN-LAST:event_jTextField2KeyReleased
   
    
    /**
     * @param args the command line arguments
     */
 

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem Editar;
    private javax.swing.JMenuItem EditarDireccion;
    private javax.swing.JMenuItem EditarEmail;
    private javax.swing.JMenuItem EditarTelefono;
    private javax.swing.JMenuItem Eliminar;
    private javax.swing.JMenuItem EliminarDireccion;
    private javax.swing.JMenuItem EliminarEmail;
    private javax.swing.JMenuItem EliminarTelefono;
    private javax.swing.JTextField JTFCantidad;
    private javax.swing.JTextField JTFPrecioCompra;
    private javax.swing.JButton jBCrearProducto;
    private javax.swing.JButton jBCrearProveedor;
    private javax.swing.JButton jButtonAgregarCliente;
    private javax.swing.JButton jButtonCancelarCliente;
    private javax.swing.JButton jButtonEditarCliente;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPPaso1;
    private javax.swing.JPanel jPPaso2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanelDatosPersonales;
    private javax.swing.JPopupMenu jPopupMenuCliente;
    private javax.swing.JPopupMenu jPopupMenuDireccion;
    private javax.swing.JPopupMenu jPopupMenuEmail;
    private javax.swing.JPopupMenu jPopupMenuTelefono;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField jTFFiltroProducto;
    private javax.swing.JTextField jTFPrecioVenta;
    private javax.swing.JTable jTProducto;
    private javax.swing.JTable jTProveedor;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTableCliente;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextFieldBuscador;
    // End of variables declaration//GEN-END:variables
}
