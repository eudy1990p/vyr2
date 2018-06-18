/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formularios.cliente;

import ClassStatic.Log;
import formularios.usuarios.*;
import conexion.Mysql;
import formularios.ListadoProducto;
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
public class ClienteAdministrar extends javax.swing.JFrame {

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
            usuarioEmpleadoID="1";
    
  
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
    private ClienteAdministrar yo;
    
    private ListadoProducto classSeleccionListado;
    private String trabajandoClase = "contizacion";
    
    private String nombreUsuario = "";
    

    public void setYo(ClienteAdministrar yo) {
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
    

    
    public ClienteAdministrar(Mysql conector,String usuarioID,String usuarioNombre) {
        initComponents();
        this.setConector(conector);
        this.setUsuarioID(usuarioID);
        this.setUsuarioNombre(usuarioNombre);
        this.cargarTabla();
        this.MostrarBotonesEmail(true, false, false);
        this.MostrarBotonesDireccion(true, false, false);
        this.MostrarBotonesTelefono(true, false, false);
        this.setLocationRelativeTo(null);
        this.MostrarTipoTelefono();
        JScrollPane[] listScroll = {this.jScrollPane1,this.jScrollPane3,this.jScrollPane4,this.jScrollPane5,this.jScrollPane6};
        ClassStatic.ConfTable.setBackGroundColor(listScroll );
    }
 
    /*
        Buscador
    */
    public void BuscadorCliente(){
                    Log.Registrar(this.getClass(), "BuscadorCliente"," ");

         this.Buscador = "";
        if(!(this.jTextFieldBuscador.getText().isEmpty())){
            this.Buscador = " and concat(p.nombre,' ',p.apellido,' ',p.cedula,' ',p.rnc,' ',c.id) like '%"+this.jTextFieldBuscador.getText()+"%'";
            //this.cargarTabla();
        }
        this.cargarTabla();
        
    }
    
    public void agregarTipoTelefono(){
      Log.Registrar(this.getClass(), "agregarTipoTelefono"," ");

        formularios.telefono.TipoTelefonoAdministrar tipoTelefono = new formularios.telefono.TipoTelefonoAdministrar(this.conector,this.usuarioID,this.usuarioNombre);
        tipoTelefono.setFOrigen(this.yo,"cliente");
        tipoTelefono.setVisible(true);
    }
    
    
    /*
    Tipo telefono
    */
        public void RecargarTipoTelefono(){
            this.MostrarTipoTelefono();
        }
       public void MostrarTipoTelefono(){
       Log.Registrar(this.getClass(), "MostrarTipoTelefono"," ");

        //String tipo = this.jTable1.getValueAt(index, 0).toString();
        //JOptionPane.showMessageDialog(null, nombre+" "+id+" "+tipo);
        try {
            String table_name = "tipo_telefono ";
            String campos = " * ";
            String otros = " where display = true ";
            java.sql.ResultSet resultSet = this.conector.optenerDatosParaTabla(table_name,campos ,otros);
            if( resultSet.first() ){
                this.ArrayTipoTelefonoIDs.clear();
                this.jComboBoxTipoTelefono.setModel(new DefaultComboBoxModel());
                do{
                        this.ArrayTipoTelefonoIDs.add(resultSet.getString("id"));
                       this.jComboBoxTipoTelefono.addItem(resultSet.getString("nombre"));
                }while(resultSet.next());
            }else{
               JOptionPane.showMessageDialog(null, "No hay registro");
               //System.out.println("");
           }
          } catch (Exception ex) {
              Log.Error(this.getClass().getName(), ex);
            System.out.println(ex.getCause().toString());
          }
        
    }
    
    public void insertarDBEmail(){
        Log.Registrar(this.getClass(), "insertarDBEmail"," "); 
        //validamos que hayan email para agregar
        if(this.PersonaEmails.size() > 0){
          for (int i = 0; i < this.PersonaEmails.size(); i++) {
            String campos = "usuario_id,email,persona_id,fecha_creado";
            String valores = "'"+this.usuarioID+"','"+this.PersonaEmails.get(i)+"','"+this.personaID+"',now()";
            this.conector.insertData("email", campos, valores);
            //String tableIPE = "persona_email";
            //String camposIPE = "persona_id,correo,usuario_id,fecha";
            //String valoresIPE = "'"+idPersona+"', '"+this.PersonaEmails.get(i)+"','"+this.usuarioID+"',now() ";
            //this.conector.insertData(tableIPE,camposIPE , valoresIPE);
            }
        }        
             
    }
    
     public void insertarDBTelefono(){
                 Log.Registrar(this.getClass(), "insertarDBTelefono"," "); 

                //Teléfono
                    if(this.PersonaTelefono.size() > 0){
                        for (int i = 0; i < this.PersonaTelefono.size(); i++) {
                        /*String tableIPE = "persona_telefono";
                        String camposIPE = "persona_id,telefono,tipo_telefono_id,usuario_id,fecha";
                        String valoresIPE = "'"+idPersona+"', '"+this.PersonaTelefono.get(i)+"', '"+this.PersonaTelefonoTipoID.get(i)+"','"+this.usuarioID+"',now() ";
                        this.conector.insertData(tableIPE,camposIPE , valoresIPE);
                        */
                        String campos = "usuario_id,telephone,persona_id,fecha_creado,tipo_telefono_id";
                        String valores = "'"+this.usuarioID+"','"+this.PersonaTelefono.get(i)+"','"+this.personaID+"',now(),'"+this.PersonaTelefonoTipoID.get(i)+"'";
                        this.conector.insertData("telephone", campos, valores);
                     }
                    }         
             
    }
     public void insertarDBDireccion(){
                         Log.Registrar(this.getClass(), "insertarDBDireccion"," "); 
         //Dirección
        if(this.PersonaDireccion.size() > 0){
           for (int i = 0; i < this.PersonaDireccion.size(); i++) {
                        //String tableIPE = "persona_direccion";
                        //String camposIPE = "persona_id,direccion,usuario_id,fecha";
                        //String valoresIPE = "'"+idPersona+"', '"+this.PersonaDireccion.get(i)+"','"+this.usuarioID+"',now() ";
                        //this.conector.insertData(tableIPE,camposIPE , valoresIPE);
                     
             String campos = "usuario_id,persona_id,fecha_creado,localidad";
            String valores = "'"+this.usuarioID+"','"+this.personaID+"',now(),'"+this.PersonaDireccion.get(i)+"'"; 
            //String valores = "'"+this.usuarioID+"','"+this.sector+"','"+this.personaID+"',now(),'"+this.provincia+"','"+this.direccion+"'";
             this.conector.insertData("direccion", campos, valores);
             }
        }
    }
    public void insertarDBPersona(){
        Log.Registrar(this.getClass(), "insertarDBPersona"," "); 
           String campos = "nombre,apellido,sexo,fecha_creado,usuario_id,rnc";//fecha_nacimiento
             String valores = "'"+this.nombre+"','"+this.apellido+"','"+this.sexo+"',now(),'"+this.usuarioID+"','"+this.rnc+"'";
             this.conector.insertData("persona", campos, valores);
             this.personaID = this.conector.optenerUltimoID("persona");
    }
    public void insertarDBCliente(){
                Log.Registrar(this.getClass(), "insertarDBCliente"," "); 
           String campos = "usuario_id,persona_id,fecha_creado";//fecha_nacimiento
             String valores = "'"+this.usuarioID+"','"+this.personaID+"',now()";
             this.conector.insertData("cliente", campos, valores);
             this.empleadoID = this.conector.optenerUltimoID("cliente");
    }
    /*
   ADMINISTRAR empleado / PERSONA
   */
    public void insert(){
        Log.Registrar(this.getClass(), "insert"," "); 
       //valida si podemos continuar
        if(this.validarVacioContinuo()){
           //queremos evitar que un cliente se ingrese dos veces por esto validamos si existe la cedula o el rnc
           /*String table = "persona as p ";
            String campos = " p.id ";
            String otros = " where (p.cedula ='"+this.cedula+"' ) and (p.cedula !='' ) and (p.cedula is not null) ";
            java.sql.ResultSet resultSet = this.conector.optenerDatosParaTabla(table,campos ,otros);*/
           try {
               //validamos de que el resultado de la db sea 0 si lo es podemos crear el cliente
               //if( !(resultSet.first()) ){
                   //insertamos primero a persona porque es una entidad fuerte
                   this.insertarDBPersona();
                   /*String tableI = "persona";
                   String camposI = "nombre,apellido,cedula,usuario_id,fecha";
                   String valoresI = " '"+this.nombre+"','"+this.apellido+"','"+this.cedula+"','"+this.usuarioID+"',now() ";
                   this.conector.insertData(tableI,camposI , valoresI);*/
                   
                   //obtenemos el ultimo id de la entidad persona
                   //String idPersona = this.conector.optenerUltimoID(tableI);
                   
                   //insertamos cliente
                   /*String tableIC = "cliente";
                   String camposIC = "persona_id,rnc,usuario_id,fecha";
                   String valoresIC = "'"+idPersona+"', '"+this.rnc+"','"+this.usuarioID+"',now() ";
                   this.conector.insertData(tableIC,camposIC , valoresIC);
                  */
                   this.insertarDBEmail();
                   this.insertarDBDireccion();
                   this.insertarDBTelefono();
                   this.insertarDBCliente();
                  /* //validamos que haya teléfono para agregar
                   String tableIC = "cliente";
                   String camposIC = "persona_id,rnc,usuario_id,fecha";
                   String valoresIC = "'"+idPersona+"', '"+this.rnc+"','"+this.usuarioID+"',now() ";
                   this.conector.insertData(tableIC,camposIC , valoresIC);
                   */
                   this.limpiar();
                   this.cargarTabla();
                   
              /*}else{
                   JOptionPane.showMessageDialog(null, "El empleado ya existe, favor ingrese otro");
               }*/
           
           } catch (Exception ex) {
               Log.Error(this.getClass().getName(), ex);
               Logger.getLogger(ClienteAdministrar.class.getName()).log(Level.SEVERE, null, ex);
           }

       }
       
       
    }
    public void cargarVariables(){
     Log.Registrar(this.getClass(), "cargarVariables"," "); 
        nombre = this.JTFNombre.getText();
        apellido = this.JTFApellido.getText();
        rnc = this.JTFRNC.getText();
        cedula = this.jTFCedula.getText();
        sexo = this.JCBSexo.getSelectedItem().toString();
        //javax.swing.JTextField fn = (javax.swing.JTextField) this.JDCFechaNacimientoCliente.getDateEditor().getUiComponent();
        
        this.formatearFecha();
    }
    public void formatearFecha(){
          Log.Registrar(this.getClass(), "formatearFecha"," "); 
         fechaNacimiento = "0000-00-00";
        if(!(((javax.swing.JTextField) this.JDCFechaNacimiento.getDateEditor().getUiComponent()).getText().isEmpty())){
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
        fechaNacimiento = sdf.format(this.JDCFechaNacimiento.getDate());
        }
    }
    
    public boolean validarVacioContinuo(){
              Log.Registrar(this.getClass(), "validarVacioContinuo"," "); 

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
       }else{
           //como los campos necesario no estan vacío le decimos que puede continuar
           respuesta = true;
       }
       /*
       if(this.usuario.isEmpty()){
            JOptionPane.showMessageDialog(null, "El campo usuario esta vacío","Existen campo vacío",JOptionPane.WARNING_MESSAGE);
        }else if(!existeUsuario){
            JOptionPane.showMessageDialog(null, "El usuario ya existe, favor introduzca otro usuario","Existe este usuario",JOptionPane.WARNING_MESSAGE);
        }else if(this.clave.isEmpty()){
            JOptionPane.showMessageDialog(null, "El campo clave esta vacío","Existen campo vacío",JOptionPane.WARNING_MESSAGE);
        }else{
       */
       return respuesta;
    }
    public void editar(){
 Log.Registrar(this.getClass(), "editar"," ");   
       if(this.validarVacioContinuo()){
           this.formatearFecha();
           String table = "cliente as c "
                + "inner join persona as p on c.persona_id = p.id ";
           String campos = "p.nombre='"+this.nombre+"',"
                   + "p.apellido='"+this.apellido+"',"
                   + "p.cedula='"+this.cedula+"',"
                   + "p.rnc='"+this.rnc+"',"
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
   Log.Registrar(this.getClass(), "limpiar"," "); 
        this.JTFApellido.setText("");
        this.JTFNombre.setText("");
        this.JTFRNC.setText("");
        this.jTFCedula.setText("");
        this.jTableEmail.setModel(new DefaultTableModel());
        this.jTabbedPane1.setSelectedIndex(0);
        this.PersonaEmailIDs.clear();
        this.PersonaEmails.clear();
        
        this.jTableDireccion.setModel(new DefaultTableModel());
        this.PersonaDireccionIDs.clear();
        this.PersonaDireccion.clear();
        
        this.jTableTelefono.setModel(new DefaultTableModel());
        this.PersonaTelefonoIDs.clear();
        this.PersonaTelefono.clear();
        this.PersonaTelefonoTipoNombre.clear();
        this.PersonaTelefonoTipoID.clear();
        ((javax.swing.JTextField) this.JDCFechaNacimiento.getDateEditor().getUiComponent()).setText("");
    }
    public void eliminar(){
 Log.Registrar(this.getClass(), "eliminar"," "); 
       int index = this.jTableCliente.getSelectedRow();
        if(index >= 0){
           if(JOptionPane.showConfirmDialog(null, "Esta seguro que desea eliminar el cliente?", "Eliminar cliente", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION ){ 
                this.CurrentClienteID = this.jTableCliente.getValueAt(index, 0).toString();
                String table = "cliente";
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
    Log.Registrar(this.getClass(), "MostrarDato"," ");
        int index = this.jTableCliente.getSelectedRow();
        if(index >= 0){
        String id = this.jTableCliente.getValueAt(index, 0).toString();
        //String nombre= this.jTable1.getValueAt(index, 0).toString();
        //String tipo = this.jTable1.getValueAt(index, 0).toString();
        //JOptionPane.showMessageDialog(null, nombre+" "+id+" "+tipo);
        try {
        String table_name = "cliente as c "
                + "inner join persona as p on c.persona_id = p.id ";
        String campos = "c.id, p.rnc,date(p.fecha_nacimiento) as fecha_nacimiento,p.sexo,p.nombre,p.apellido,p.cedula, p.id as personaid ";
        String otros = " where c.id = '"+id+"' and c.display = true ";
        java.sql.ResultSet resultSet = this.conector.optenerDatosParaTabla(table_name,campos ,otros);
        if( resultSet.first() ){
                   //Mostrar datos personales y del cliente
                   this.CurrentClienteID = resultSet.getString("id");
                   this.JTFApellido.setText(resultSet.getString("apellido"));
                   this.JTFNombre.setText(resultSet.getString("nombre"));
                   this.jTFCedula.setText(resultSet.getString("cedula"));
                   this.JTFRNC.setText(resultSet.getString("rnc"));
                   this.CurrentPersonaID = resultSet.getString("personaid");
                   try{
                   if(!(resultSet.getString("fecha_nacimiento").equals("0000-00-00"))){
                        System.out.println(resultSet.getDate("fecha_nacimiento"));
                        this.JDCFechaNacimiento.setDate(resultSet.getDate("fecha_nacimiento"));
                   }
                   }catch(NullPointerException npe){
                       Log.Error(this.getClass().getName(), npe);
                       System.out.println(npe.getCause()+" "+npe.getMessage());
                      
                   }catch(Exception ecp){
                       Log.Error(this.getClass().getName(), ecp);
                       System.out.println(ecp.getCause()+" "+ecp.getMessage());
                   }
                   JCBSexo.setSelectedItem(resultSet.getString("sexo"));
                   this.MostrarBotones(false, true,true );
                   
                   this.EditarClienteActivado = true;
                   //email
                   this.MostrarEmails();
                   this.MostrarBotonesEmail(true,false, false);
                   
                   //direccion
                   this.MostrarDirecciones();
                   this.MostrarBotonesDireccion(true,false, false);
                   
                   //telefono
                   this.MostrarTelefonos();
                   this.MostrarBotonesTelefono(true,false, false);
                   
          }else{
            JOptionPane.showMessageDialog(null, "No hay registro");
            //System.out.println("");
        }
         }catch (SQLException ex) {
             Log.Error(this.getClass().getName(), ex);
            System.out.println(ex.getCause().toString()+"\n"+ex.getMessage()+"\n"+ex.getLocalizedMessage());
          } catch (Exception ecp) {
              Log.Error(this.getClass().getName(), ecp);
            System.out.println(ecp.getCause().toString()+"\n"+ecp.getMessage()+"\n"+ecp.getLocalizedMessage());
          }
        }else{
            JOptionPane.showMessageDialog(null, "No hay fila seleccionada");
        }
    }
    
  public void MostrarBotones(boolean b1,boolean b2,boolean b3){
                     Log.Registrar(this.getClass(), "MostrarBotones"," ");

      this.jButtonAgregarCliente.setVisible(b1);
      this.jButtonEditarCliente.setVisible(b2);
      this.jButtonCancelarCliente.setVisible(b3);
      
  }
   public void cargarTabla(){
       Log.Registrar(this.getClass(), "cargarTabla"," ");
       try {
         String table_name = "cliente as c "
                + "inner join persona as p on c.persona_id = p.id "
                + "inner join usuario as u1 on c.usuario_id = u1.id ";
        String campos = " c.usuario_id,"
                + "p.rnc,c.id,p.nombre,p.apellido,p.cedula,p.sexo,"
                + " p.id as personaid,u1.nombre_usuario as creado_por ";
        String otros = " where  c.display = true "+this.Buscador+" ";
        java.sql.ResultSet resultSet = this.conector.optenerDatosParaTabla(table_name,campos ,otros);
        if( resultSet.first() ){
            int total = 0;
        do{
            total++;
        }while(resultSet.next());
        resultSet.first();
        String[] titulos = {"ID","CLIENTE","CEDULA","RNC","SEXO","CREADO POR"};
        
        Object[][] fila = new Object[total][10];
            
         int c = 0;
         
          do{
                fila[c][0] = resultSet.getString("id");
                    fila[c][1] =  resultSet.getString("nombre")+" "+resultSet.getString("apellido");
                    fila[c][2] = resultSet.getString("cedula");
                    fila[c][3] =resultSet.getString("rnc"); 
                    fila[c][4] = resultSet.getString("sexo");
                    fila[c][5] = resultSet.getString("creado_por");
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

   /*
   ADMINISTRAR EMAIL
   */
   public void agregarEmail(){
         Log.Registrar(this.getClass(), "agregarEmail"," ");
      
        String email =this.jTextFieldEmail.getText(); 
        if(email.isEmpty()){
            JOptionPane.showMessageDialog(null, "El campo email esta vacío");
        }else{
            
            //Validamos que se este actualizando un cliente para poder saber que hacer
            if(this.EditarClienteActivado){
                //validamos de que ese email no exista
                String table = " email ";
                String campos = " id ";
                String otros = " where email like '%"+email+"%'  and persona_id = '"+this.CurrentPersonaID+"'  ";
                java.sql.ResultSet resultSet = this.conector.optenerDatosParaTabla(table,campos ,otros);
           try {
               //validamos de que el resultado de la db sea 0 si lo es podemos crear el cliente
               if( !(resultSet.first()) ){
                   //insertamos primero a persona porque es una entidad fuerte
                   String tableI = "email";
                   String camposI = "email,persona_id,usuario_id,fecha_creado";
                   String valoresI = " '"+email+"','"+this.CurrentPersonaID+"','"+this.usuarioID+"',now() ";
                   this.conector.insertData(tableI,camposI , valoresI);
                  this.MostrarEmails();
               }else{
                   JOptionPane.showMessageDialog(null, "El cliente ya existe, favor ingrese otro");
               }
            }   catch (SQLException ex) {
                Log.Error(this.getClass().getName(), ex);
                    Logger.getLogger(ClienteAdministrar.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else{
                this.PersonaEmails.add(this.jTextFieldEmail.getText());
                this.PersonaEmailIDs.add("0");
                this.cargarTablaEmail();
            }
        this.MostrarBotonesEmail(true, false, false);
         this.limpiarEmail();
        }
   }
   public void cargarTablaEmail(){
         Log.Registrar(this.getClass(), "cargarTablaEmail"," ");
       int total = this.PersonaEmails.size();
        String[] titulos = {"ID","EMAIL"};
        Object[][] fila = new Object[total][3];
        int c = 0; 
          while(c < total){
                fila[c][0] = this.PersonaEmailIDs.get(c);
                fila[c][1] = this.PersonaEmails.get(c);
                c++;
             }
          DefaultTableModel modelo = new DefaultTableModel(fila,titulos);
          this.jTableEmail.setModel(modelo);
        
   }
   public void MostrarBotonesEmail(boolean agregar,boolean editar,boolean cancelar){
                       Log.Registrar(this.getClass(), "MostrarBotonesEmail"," ");

      this.jButtonEmailAgregar.setVisible(agregar);
      this.jButtonEmailEditar.setVisible(editar);
      this.jButtonEmailCancelar.setVisible(cancelar);   
  }
   public void limpiarEmail(){
       Log.Registrar(this.getClass(), "limpiarEmail"," "); 

       this.jTextFieldEmail.setText("");
   }
   public void eliminarEmail(){
          Log.Registrar(this.getClass(), "eliminarEmail"," "); 

       int index = this.jTableEmail.getSelectedRow();
        if(index >= 0){
           if(JOptionPane.showConfirmDialog(null, "Esta seguro que desea eliminar el email?", "Eliminar email", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION ){ 
              
               //Validamos que se este actualizando un cliente para poder saber que hacer
            if(this.EditarClienteActivado){
               try {
                   String id = this.jTableEmail.getValueAt( index,0 ).toString();
                   String tableE = "email";
                   String camposE = "display = false ";
                   String otrosE = " id = '"+id+"'  ";
                   this.conector.actulizarDatos(tableE,camposE , otrosE);
                  this.MostrarEmails();
               
               } catch (Exception ex) {
                   Log.Error(this.getClass().getName(), ex);
                    Logger.getLogger(ClienteAdministrar.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else{
            
               this.PersonaEmailIDs.remove(index);
               this.PersonaEmails.remove(index);
                this.cargarTablaEmail();
               }
               
               
               
               
               
               /*this.CurrentClienteID = this.jTableCliente.getValueAt(index, 0).toString();
                String table = "cliente";
                String campos = "display=false ";
                String where = " id = '"+this.CurrentClienteID+"' ";
                this.conector.actulizarDatos(table,campos , where);
                JOptionPane.showMessageDialog(null,"Se Elimino");
                */
                
                this.limpiarEmail();
                this.MostrarBotonesEmail(true, false, false);
           }
        }else{
            JOptionPane.showMessageDialog(null, "No hay fila seleccionada");

       }
       
       
    }
   public void MostrarEmails(){
     Log.Registrar(this.getClass(), "MostrarEmails"," ");

        //String tipo = this.jTable1.getValueAt(index, 0).toString();
        //JOptionPane.showMessageDialog(null, nombre+" "+id+" "+tipo);
        try {
            String table_name = "email ";
            String campos = " * ";
            String otros = " where persona_id = '"+this.CurrentPersonaID+"' and display = true ";
            java.sql.ResultSet resultSet = this.conector.optenerDatosParaTabla(table_name,campos ,otros);
            this.PersonaEmailIDs.clear();
            this.PersonaEmails.clear();
            if( resultSet.first() ){
                do{
                       this.PersonaEmailIDs.add(resultSet.getString("id"));
                       this.PersonaEmails.add(resultSet.getString("email"));
                }while(resultSet.next());
               
                this.MostrarBotones(false, true,true );
                this.cargarTablaEmail();
          }else{
            JOptionPane.showMessageDialog(null, "No hay registro de email");
            this.cargarTablaEmail();
            //System.out.println("");
        }
        this.MostrarBotonesEmail(false, true, true);
          } catch (Exception ex) {
              Log.Error(this.getClass().getName(), ex);
            System.out.println(ex.getCause().toString());
          }
        
    }
    public void MostrarDatoEmail(){
     Log.Registrar(this.getClass(), "MostrarDatoEmail"," ");

        int index = this.jTableEmail.getSelectedRow();
        if(index >= 0){
        
        //String tipo = this.jTable1.getValueAt(index, 0).toString();
        //JOptionPane.showMessageDialog(null, nombre+" "+id+" "+tipo);
        try {
            if(this.EditarClienteActivado){
                this.CurrentEmailID = this.jTableEmail.getValueAt(index, 0).toString();
            }else{
                this.CurrentEmailID = index+"";
            }
            String email= this.jTableEmail.getValueAt(index, 1).toString();
            this.jTextFieldEmail.setText(email);
                 /*
        String table_name = "cliente as c "
                + "inner join persona as p on c.persona_id = p.id "
                + "inner join usuario as u on p.usuario_id = u.id ";
        String campos = " c.rnc,c.id,p.nombre,p.apellido,p.cedula, u.nombre as usuario ";
        String otros = " where c.id = '"+id+"' and c.display = true ";
        java.sql.ResultSet resultSet = this.conector.optenerDatosParaTabla(table_name,campos ,otros);
        if( resultSet.first() ){
                   this.CurrentClienteID = resultSet.getString("id");
                   this.jTFApellido.setText(resultSet.getString("apellido"));
                   this.jTFNombre.setText(resultSet.getString("nombre"));
                   this.jTFCedula.setText(resultSet.getString("cedula"));
                   this.jTFRNC.setText(resultSet.getString("rnc"));
                   this.MostrarBotones(false, true,true );
          }else{
            JOptionPane.showMessageDialog(null, "No hay registro");
            //System.out.println("");
        }*/
        this.MostrarBotonesEmail(false, true, true);
          } catch (Exception ex) {
              Log.Error(this.getClass().getName(), ex);
            System.out.println(ex.getCause().toString());
          }
        }else{
            JOptionPane.showMessageDialog(null, "No hay fila seleccionada");
        }
    }
    public void editarEmail(){
 Log.Registrar(this.getClass(), "editarEmail"," "); 
        String email =this.jTextFieldEmail.getText();
        if(email.isEmpty()){
            JOptionPane.showMessageDialog(null, "El campo email esta vacío");
        }else{
            //Validamos que se este actualizando un cliente para poder saber que hacer
            if(this.EditarClienteActivado){
                //validamos de que ese email no exista
                String table = " email ";
                String campos = " id ";
                String otros = " where email like '%"+email+"%'  and persona_id = '"+this.CurrentPersonaID+"'  ";
                java.sql.ResultSet resultSet = this.conector.optenerDatosParaTabla(table,campos ,otros);
           try {
               //validamos de que el resultado de la db sea 0 si lo es podemos crear el cliente
               if( !(resultSet.first()) ){
                   //insertamos primero a persona porque es una entidad fuerte
                   String tableE = "email";
                   String camposE = "email = '"+email+"' ";
                   String otrosE = " id = '"+this.CurrentEmailID+"'  ";
                   this.conector.actulizarDatos(tableE,camposE , otrosE);
                  this.MostrarEmails();
               }else{
                   JOptionPane.showMessageDialog(null, "El email ya existe, favor ingrese otro");
               }
            }   catch (SQLException ex) {
                Log.Error(this.getClass().getName(), ex);
                    Logger.getLogger(ClienteAdministrar.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else{
            
                 this.PersonaEmails.set(Integer.parseInt(this.CurrentEmailID),this.jTextFieldEmail.getText() );
                 this.cargarTablaEmail();
            }
           /* String table = "cliente as c "
                + "inner join persona as p on c.persona_id = p.id";
           String campos = "p.nombre='"+this.nombre+"',p.apellido='"+this.apellido+"',p.cedula='"+this.cedula+"',c.rnc='"+this.rnc+"' ";
           String where = " c.id = '"+this.CurrentClienteID+"' ";
           this.conector.actulizarDatos(table,campos , where);*/
           JOptionPane.showMessageDialog(null,"Se edito");
           this.limpiarEmail();
           this.MostrarBotonesEmail(true, false, false);
      
       }
    }
   /*
   ADMINISTRAR DIRECCION
   */
   
     public void agregarDireccion(){
        Log.Registrar(this.getClass(), "agregarDireccion"," ");
      
        String direccion =this.jTextAreaDireccion.getText(); 
        if(direccion.isEmpty()){
            JOptionPane.showMessageDialog(null, "El campo dirección esta vacío");
        }else{
            
            //Validamos que se este actualizando un cliente para poder saber que hacer
            if(this.EditarClienteActivado){
                //validamos de que ese email no exista
                String table = " direccion ";
                String campos = " id ";
                String otros = " where localidad like '%"+direccion+"%'  and persona_id = '"+this.CurrentPersonaID+"'  ";
                java.sql.ResultSet resultSet = this.conector.optenerDatosParaTabla(table,campos ,otros);
           try {
               //validamos de que el resultado de la db sea 0 si lo es podemos crear el cliente
               if( !(resultSet.first()) ){
                   //insertamos primero a persona porque es una entidad fuerte
                   String tableI = "direccion";
                   String camposI = "localidad,persona_id,usuario_id,fecha_creado";
                   String valoresI = " '"+direccion+"','"+this.CurrentPersonaID+"','"+this.usuarioID+"',now() ";
                   this.conector.insertData(tableI,camposI , valoresI);
                  this.MostrarDirecciones();
               }else{
                   JOptionPane.showMessageDialog(null, "El dirección ya existe, favor ingrese otro");
               }
            }   catch (SQLException ex) {
                Log.Error(this.getClass().getName(), ex);
                    Logger.getLogger(ClienteAdministrar.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else{
                this.PersonaDireccion.add(this.jTextAreaDireccion.getText());
                this.PersonaDireccionIDs.add("0");
                this.cargarTablaDireccion();
            }
        this.MostrarBotonesDireccion(true, false, false);
         this.limpiarDireccion();
        }
   }
   public void cargarTablaDireccion(){
        Log.Registrar(this.getClass(), "cargarTablaDireccion"," ");
       int total = this.PersonaDireccion.size();
        String[] titulos = {"ID","DIRECCION"};
        Object[][] fila = new Object[total][3];
        int c = 0; 
          while(c < total){
                fila[c][0] = this.PersonaDireccionIDs.get(c);
                fila[c][1] = this.PersonaDireccion.get(c);
                c++;
             }
          DefaultTableModel modelo = new DefaultTableModel(fila,titulos);
          this.jTableDireccion.setModel(modelo);
        
   }
   public void MostrarBotonesDireccion(boolean agregar,boolean editar,boolean cancelar){
          Log.Registrar(this.getClass(), "MostrarBotonesDireccion"," ");

                      Log.Registrar(this.getClass(), "MostrarBotonesDireccion"," ");

      this.jButtonDireccionAgregar.setVisible(agregar);
      this.jButtonDireccionEditar.setVisible(editar);
      this.jButtonDireccionCancelar.setVisible(cancelar);   
  }
   public void limpiarDireccion(){
 Log.Registrar(this.getClass(), "limpiarDireccion"," "); 
       this.jTextAreaDireccion.setText("");
   }
   public void eliminarDireccion(){
   Log.Registrar(this.getClass(), "eliminarDireccion"," "); 

       int index = this.jTableDireccion.getSelectedRow();
        if(index >= 0){
           if(JOptionPane.showConfirmDialog(null, "Esta seguro que desea eliminar la dirección?", "Eliminar dirección", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION ){ 
              
               //Validamos que se este actualizando un cliente para poder saber que hacer
            if(this.EditarClienteActivado){
               try {
                   String id = this.jTableDireccion.getValueAt( index,0 ).toString();
                   String tableE = " direccion ";
                   String camposE = "display = false ";
                   String otrosE = " id = '"+id+"'  ";
                   this.conector.actulizarDatos(tableE,camposE , otrosE);
                  this.MostrarDirecciones();
               
               } catch (Exception ex) {
                   Log.Error(this.getClass().getName(), ex);
                    Logger.getLogger(ClienteAdministrar.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else{
            
               this.PersonaDireccionIDs.remove(index);
               this.PersonaDireccion.remove(index);
                this.cargarTablaDireccion();
               }
                this.limpiarDireccion();
                this.MostrarBotonesDireccion(true, false, false);
           }
        }else{
            JOptionPane.showMessageDialog(null, "No hay fila seleccionada");

       }
       
       
    }
   public void MostrarDirecciones(){
     Log.Registrar(this.getClass(), "MostrarDirecciones"," ");

        try {
            String table_name = "direccion ";
            String campos = " * ";
            String otros = " where persona_id = '"+this.CurrentPersonaID+"' and display = true ";
            java.sql.ResultSet resultSet = this.conector.optenerDatosParaTabla(table_name,campos ,otros);
            this.PersonaDireccionIDs.clear();
            this.PersonaDireccion.clear();
            if( resultSet.first() ){
                do{
                       this.PersonaDireccionIDs.add(resultSet.getString("id"));
                       this.PersonaDireccion.add(resultSet.getString("localidad"));
                }while(resultSet.next());
               
                //this.MostrarBotones(false, true,true );
                this.cargarTablaDireccion();
          }else{
            JOptionPane.showMessageDialog(null, "No hay registro de dirección");
            this.cargarTablaDireccion();
            //System.out.println("");
        }
            this.MostrarBotonesDireccion(true, false, false);
          } catch (Exception ex) {
              Log.Error(this.getClass().getName(), ex);
            System.out.println(ex.getCause().toString());
          }
        
    }
    public void MostrarDatoDireccion(){
     Log.Registrar(this.getClass(), "MostrarDatoDireccion"," ");

        int index = this.jTableDireccion.getSelectedRow();
        if(index >= 0){
        
        //String tipo = this.jTable1.getValueAt(index, 0).toString();
        //JOptionPane.showMessageDialog(null, nombre+" "+id+" "+tipo);
        try {
            if(this.EditarClienteActivado){
                this.CurrentDireccionID = this.jTableDireccion.getValueAt(index, 0).toString();
            }else{
                this.CurrentDireccionID = index+"";
            }
            String direccion= this.jTableDireccion.getValueAt(index, 1).toString();
            this.jTextAreaDireccion.setText(direccion);
            
        this.MostrarBotonesDireccion(false, true, true);
          } catch (Exception ex) {
              Log.Error(this.getClass().getName(), ex);
            System.out.println(ex.getCause().toString());
          }
        }else{
            JOptionPane.showMessageDialog(null, "No hay fila seleccionada");
        }
    }
    public void editarDireccion(){
 Log.Registrar(this.getClass(), "editarDireccion"," ");   

        String direccion =this.jTextAreaDireccion.getText();
        if(direccion.isEmpty()){
            JOptionPane.showMessageDialog(null, "El campo dirección esta vacío");
        }else{
            //Validamos que se este actualizando un cliente para poder saber que hacer
            if(this.EditarClienteActivado){
                //validamos de que ese email no exista
                String table = " direccion ";
                String campos = " id ";
                String otros = " where localidad like '%"+direccion+"%' and persona_id = '"+this.CurrentPersonaID+"'   ";
                java.sql.ResultSet resultSet = this.conector.optenerDatosParaTabla(table,campos ,otros);
           try {
               //validamos de que el resultado de la db sea 0 si lo es podemos crear el cliente
               if( !(resultSet.first()) ){
                   //insertamos primero a persona porque es una entidad fuerte
                   String tableE = "direccion";
                   String camposE = "localidad = '"+direccion+"' ";
                   String otrosE = " id = '"+this.CurrentDireccionID+"'  ";
                   this.conector.actulizarDatos(tableE,camposE , otrosE);
                  this.MostrarDirecciones();
               }else{
                   JOptionPane.showMessageDialog(null, "La dirección ya existe, favor ingrese otro");
               }
            }   catch (SQLException ex) {
                Log.Error(this.getClass().getName(), ex);
                    Logger.getLogger(ClienteAdministrar.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else{
            
                 this.PersonaDireccion.set(Integer.parseInt(this.CurrentDireccionID),this.jTextAreaDireccion.getText() );
                 this.cargarTablaDireccion();
            }
           /* String table = "cliente as c "
                + "inner join persona as p on c.persona_id = p.id";
           String campos = "p.nombre='"+this.nombre+"',p.apellido='"+this.apellido+"',p.cedula='"+this.cedula+"',c.rnc='"+this.rnc+"' ";
           String where = " c.id = '"+this.CurrentClienteID+"' ";
           this.conector.actulizarDatos(table,campos , where);*/
           JOptionPane.showMessageDialog(null,"Se edito");
           this.limpiarDireccion();
           this.MostrarBotonesDireccion(true, false, false);
      
       }
    }
    
    
   /*
   ADMINISTRAR TELEFONO
   */
   
     public void agregarTelefono(){
     Log.Registrar(this.getClass(), "agregarTelefono"," ");
      
        String telefono =this.jTextFieldTelefono.getText();
        String tipo = this.jComboBoxTipoTelefono.getSelectedItem().toString();
        if(telefono.isEmpty()){
            JOptionPane.showMessageDialog(null, "El campo teléfono esta vacío");
        }else if(tipo.isEmpty()){
            JOptionPane.showMessageDialog(null, "El campo tipo teléfono esta vacío");
        }else{
            //Validamos que se este actualizando un cliente para poder saber que hacer
            if(this.EditarClienteActivado){
                //validamos de que ese email no exista
                String table = "  telephone  ";
                String campos = " id ";
                String otros = " where (tipo_telefono_id = '"+this.ArrayTipoTelefonoIDs.get(this.jComboBoxTipoTelefono.getSelectedIndex())+"') and (telephone like '%"+telefono+"%' ) and (persona_id = '"+this.CurrentPersonaID+"' )  ";
                java.sql.ResultSet resultSet = this.conector.optenerDatosParaTabla(table,campos ,otros);
           try {
               //validamos de que el resultado de la db sea 0 si lo es podemos crear el cliente
               if( !(resultSet.first()) ){
                   //insertamos primero a persona porque es una entidad fuerte
                   String tableI = " telephone ";
                   String camposI = "tipo_telefono_id,telephone,persona_id,usuario_id,fecha_creado";
                   String valoresI = " '"+this.ArrayTipoTelefonoIDs.get(this.jComboBoxTipoTelefono.getSelectedIndex())+"','"+telefono+"','"+this.CurrentPersonaID+"','"+this.usuarioID+"',now() ";
                   this.conector.insertData(tableI,camposI , valoresI);
                  this.MostrarTelefonos();
               }else{
                   JOptionPane.showMessageDialog(null, "El telefono ya existe, favor ingrese otro");
               }
            }   catch (SQLException ex) {
                Log.Error(this.getClass().getName(), ex);
                    Logger.getLogger(ClienteAdministrar.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else{
                this.PersonaTelefono.add(this.jTextFieldTelefono.getText());
                int index = this.jComboBoxTipoTelefono.getSelectedIndex();
                this.PersonaTelefonoTipoID.add(this.ArrayTipoTelefonoIDs.get(index));
                this.PersonaTelefonoTipoNombre.add(this.jComboBoxTipoTelefono.getSelectedItem().toString());
                this.PersonaTelefonoIDs.add("0");
                this.cargarTablaTelefono();
            }
        this.MostrarBotonesTelefono(true, false, false);
         this.limpiarTelefono();
        }
   }
   public void cargarTablaTelefono(){
       Log.Registrar(this.getClass(), "cargarTablaTelefono"," "); 
       int total = this.PersonaTelefono.size();
        String[] titulos = {"ID","TELEFONO","TIPO"};
        Object[][] fila = new Object[total][4];
        int c = 0; 
          while(c < total){
                fila[c][0] = this.PersonaTelefonoIDs.get(c);
                fila[c][1] = this.PersonaTelefono.get(c);
                fila[c][2] = this.PersonaTelefonoTipoNombre.get(c);
                c++;
             }
          DefaultTableModel modelo = new DefaultTableModel(fila,titulos);
          this.jTableTelefono.setModel(modelo);
        
   }
   public void MostrarBotonesTelefono(boolean agregar,boolean editar,boolean cancelar){
    Log.Registrar(this.getClass(), "MostrarBotonesTelefono"," ");
      this.jButtonTelefonoAgregar.setVisible(agregar);
      this.jButtonTelefonoEditar.setVisible(editar);
      this.jButtonTelefonoCancelar.setVisible(cancelar);   
  }
   public void limpiarTelefono(){
        Log.Registrar(this.getClass(), "limpiarTelefono"," "); 

       this.jTextFieldTelefono.setText("");
       this.jComboBoxTipoTelefono.setSelectedIndex(0);
   }
   public void eliminarTelefono(){
                 Log.Registrar(this.getClass(), "eliminarTelefono"," "); 

       int index = this.jTableTelefono.getSelectedRow();
        if(index >= 0){
           if(JOptionPane.showConfirmDialog(null, "Esta seguro que desea eliminar el teléfono ?", "Eliminar teléfono", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION ){ 
              
               //Validamos que se este actualizando un cliente para poder saber que hacer
            if(this.EditarClienteActivado){
               try {
                   String id = this.jTableTelefono.getValueAt( index,0 ).toString();
                   String tableE = "telephone";
                   String camposE = "display = false ";
                   String otrosE = " id = '"+id+"'  ";
                   this.conector.actulizarDatos(tableE,camposE , otrosE);
                  this.MostrarTelefonos();
               
               } catch (Exception ex) {
                   Log.Error(this.getClass().getName(), ex);
                    Logger.getLogger(ClienteAdministrar.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else{
            
               this.PersonaTelefonoIDs.remove(index);
               this.PersonaTelefono.remove(index);
               this.PersonaTelefonoTipoNombre.remove(index);
               this.PersonaTelefonoTipoID.remove(index);
               this.cargarTablaTelefono();
               }
                this.limpiarTelefono();
                this.MostrarBotonesTelefono(true, false, false);
           }
        }else{
            JOptionPane.showMessageDialog(null, "No hay fila seleccionada");

       }
       
       
    }
   public void MostrarTelefonos(){
      Log.Registrar(this.getClass(), "MostrarTelefonos"," ");

        try {
            String table_name = "telephone as pt inner join tipo_telefono as tt on pt.tipo_telefono_id = tt.id ";
            String campos = " pt.*,tt.nombre ";
            String otros = " where pt.persona_id = '"+this.CurrentPersonaID+"' and pt.display = true ";
            java.sql.ResultSet resultSet = this.conector.optenerDatosParaTabla(table_name,campos ,otros);
            this.PersonaTelefonoIDs.clear();
            this.PersonaTelefono.clear();
            this.PersonaTelefonoTipoNombre.clear();
            this.PersonaTelefonoTipoID.clear();
            if( resultSet.first() ){
                do{
                       this.PersonaTelefonoIDs.add(resultSet.getString("id"));
                       this.PersonaTelefono.add(resultSet.getString("telephone"));
                       this.PersonaTelefonoTipoNombre.add(resultSet.getString("nombre"));
                       this.PersonaTelefonoTipoID.add(resultSet.getString("tipo_telefono_id"));
                }while(resultSet.next());
               
                //this.MostrarBotones(false, true,true );
                this.cargarTablaTelefono();
          }else{
            JOptionPane.showMessageDialog(null, "No hay registro de telefono");
            this.cargarTablaTelefono();
            //System.out.println("");
        }
            this.MostrarBotonesTelefono(true, false, false);
          } catch (Exception ex) {
              Log.Error(this.getClass().getName(), ex);
            System.out.println(ex.getCause().toString());
          }
        
    }
    public void MostrarDatoTelefono(){
     Log.Registrar(this.getClass(), "MostrarDatoTelefono"," ");

        int index = this.jTableTelefono.getSelectedRow();
        if(index >= 0){
        
        //String tipo = this.jTable1.getValueAt(index, 0).toString();
        //JOptionPane.showMessageDialog(null, nombre+" "+id+" "+tipo);
        try {
            if(this.EditarClienteActivado){
                this.CurrentTelefonoID = this.jTableTelefono.getValueAt(index, 0).toString();
            }else{
                this.CurrentTelefonoID = index+"";
            }
            String telefono= this.jTableTelefono.getValueAt(index, 1).toString();
            String tipo= this.jTableTelefono.getValueAt(index, 2).toString();
            this.jTextFieldTelefono.setText(telefono);
            this.jComboBoxTipoTelefono.setSelectedItem(tipo);
            
        this.MostrarBotonesTelefono(false, true, true);
          } catch (Exception ex) {
              Log.Error(this.getClass().getName(), ex);
            System.out.println(ex.getCause().toString());
          }
        }else{
            JOptionPane.showMessageDialog(null, "No hay fila seleccionada");
        }
    }
    public void editarTelefono(){
Log.Registrar(this.getClass(), "editarTelefono"," "); 
        String telefono =this.jTextFieldTelefono.getText();
        String tipoTelefono = this.jComboBoxTipoTelefono.getSelectedItem().toString();
        if(telefono.isEmpty()){
            JOptionPane.showMessageDialog(null, "El campo teléfono esta vacío");
        }if(tipoTelefono.isEmpty()){
            JOptionPane.showMessageDialog(null, "El campo tipo teléfono esta vacío");
        }else{
            //Validamos que se este actualizando un cliente para poder saber que hacer
            if(this.EditarClienteActivado){
                //validamos de que ese email no exista
                String table = "telephone ";
                String campos = " id ";
                //String otros = " where telefono like '%"+telefono+"%' and persona_id = '"+this.CurrentPersonaID+"'  ";
                String otros = " where (tipo_telefono_id = '"+this.ArrayTipoTelefonoIDs.get(this.jComboBoxTipoTelefono.getSelectedIndex())+"' ) and (telephone like '%"+telefono+"%') and (persona_id = '"+this.CurrentPersonaID+"')  ";
                
                java.sql.ResultSet resultSet = this.conector.optenerDatosParaTabla(table,campos ,otros);
           try {
               //validamos de que el resultado de la db sea 0 si lo es podemos crear el cliente
               if( !(resultSet.first()) ){
                   //insertamos primero a persona porque es una entidad fuerte
                   String tableE = " telephone ";
                   String camposE = "telephone = '"+telefono+"', tipo_telefono_id = '"+this.ArrayTipoTelefonoIDs.get(this.jComboBoxTipoTelefono.getSelectedIndex())+"' ";
                   String otrosE = " id = '"+this.CurrentTelefonoID+"'  ";
                   this.conector.actulizarDatos(tableE,camposE , otrosE);
                  this.MostrarTelefonos();
               }else{
                   JOptionPane.showMessageDialog(null, "El teléfono ya existe, favor ingrese otro");
               }
            }   catch (SQLException ex) {
                Log.Error(this.getClass().getName(), ex);
                    Logger.getLogger(ClienteAdministrar.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else{
            
                 this.PersonaTelefono.set(Integer.parseInt(this.CurrentTelefonoID),this.jTextFieldTelefono.getText() );
                 this.cargarTablaTelefono();
            }
           /* String table = "cliente as c "
                + "inner join persona as p on c.persona_id = p.id";
           String campos = "p.nombre='"+this.nombre+"',p.apellido='"+this.apellido+"',p.cedula='"+this.cedula+"',c.rnc='"+this.rnc+"' ";
           String where = " c.id = '"+this.CurrentClienteID+"' ";
           this.conector.actulizarDatos(table,campos , where);*/
           JOptionPane.showMessageDialog(null,"Se edito");
           this.limpiarTelefono();
           this.MostrarBotonesTelefono(true, false, false);
      
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
        jPanel3 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanelDatosPersonales = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        JTFApellido = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        JTFNombre = new javax.swing.JTextField();
        jTFCedula = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        JTFRNC = new javax.swing.JTextField();
        JDCFechaNacimiento = new com.toedter.calendar.JDateChooser();
        JLFechaNacimientoCliente = new javax.swing.JLabel();
        JCBSexo = new javax.swing.JComboBox<>();
        JLSexoCliente = new javax.swing.JLabel();
        jPanelEmail = new javax.swing.JPanel();
        jButtonEmailEditar = new javax.swing.JButton();
        jButtonEmailCancelar = new javax.swing.JButton();
        jButtonEmailAgregar = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jTextFieldEmail = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTableEmail = new javax.swing.JTable();
        jPanelDireccion = new javax.swing.JPanel();
        jButtonDireccionAgregar = new javax.swing.JButton();
        jButtonDireccionCancelar = new javax.swing.JButton();
        jButtonDireccionEditar = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTableDireccion = new javax.swing.JTable();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTextAreaDireccion = new javax.swing.JTextArea();
        jPanelTelefono = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jTextFieldTelefono = new javax.swing.JTextField();
        jComboBoxTipoTelefono = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jButtonTelefonoAgregar = new javax.swing.JButton();
        jButtonTelefonoCancelar = new javax.swing.JButton();
        jButtonTelefonoEditar = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTableTelefono = new javax.swing.JTable();
        jLabel10 = new javax.swing.JLabel();
        jButtonEditarCliente = new javax.swing.JButton();
        jButtonAgregarCliente = new javax.swing.JButton();
        jButtonCancelarCliente = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jTextFieldBuscador = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableCliente = new javax.swing.JTable();

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
        setTitle("Cliente");

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jPanelDatosPersonales.setBackground(new java.awt.Color(255, 255, 255));
        jPanelDatosPersonales.setPreferredSize(new java.awt.Dimension(300, 346));

        jLabel3.setText("Nombre");

        jLabel1.setText("Apellido");

        jLabel2.setText("Cedula");

        jLabel4.setText("RNC");

        JLFechaNacimientoCliente.setText("Fecha Nacimiento");

        JCBSexo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "masculino", "femenino" }));
        JCBSexo.setToolTipText("");

        JLSexoCliente.setText("Sexo cliente");

        javax.swing.GroupLayout jPanelDatosPersonalesLayout = new javax.swing.GroupLayout(jPanelDatosPersonales);
        jPanelDatosPersonales.setLayout(jPanelDatosPersonalesLayout);
        jPanelDatosPersonalesLayout.setHorizontalGroup(
            jPanelDatosPersonalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDatosPersonalesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelDatosPersonalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(JTFNombre)
                    .addGroup(jPanelDatosPersonalesLayout.createSequentialGroup()
                        .addGroup(jPanelDatosPersonalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4)
                            .addComponent(JLFechaNacimientoCliente)
                            .addComponent(JLSexoCliente))
                        .addGap(0, 238, Short.MAX_VALUE))
                    .addComponent(JTFApellido)
                    .addComponent(jTFCedula)
                    .addComponent(JTFRNC)
                    .addComponent(JDCFechaNacimiento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(JCBSexo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanelDatosPersonalesLayout.setVerticalGroup(
            jPanelDatosPersonalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDatosPersonalesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JTFNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JTFApellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTFCedula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JTFRNC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JLFechaNacimientoCliente)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JDCFechaNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JLSexoCliente)
                .addGap(11, 11, 11)
                .addComponent(JCBSexo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(82, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Datos Personales", jPanelDatosPersonales);

        jPanelEmail.setBackground(new java.awt.Color(255, 255, 255));

        jButtonEmailEditar.setBackground(new java.awt.Color(0, 51, 204));
        jButtonEmailEditar.setForeground(new java.awt.Color(255, 255, 255));
        jButtonEmailEditar.setText("Editar");
        jButtonEmailEditar.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jButtonEmailEditar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButtonEmailEditarMousePressed(evt);
            }
        });

        jButtonEmailCancelar.setBackground(new java.awt.Color(255, 51, 0));
        jButtonEmailCancelar.setForeground(new java.awt.Color(255, 255, 255));
        jButtonEmailCancelar.setText("Cancelar");
        jButtonEmailCancelar.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jButtonEmailCancelar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtonEmailCancelarMouseClicked(evt);
            }
        });

        jButtonEmailAgregar.setBackground(new java.awt.Color(0, 153, 102));
        jButtonEmailAgregar.setForeground(new java.awt.Color(255, 255, 255));
        jButtonEmailAgregar.setText("Agregar");
        jButtonEmailAgregar.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jButtonEmailAgregar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtonEmailAgregarMouseClicked(evt);
            }
        });

        jLabel7.setText("Email");

        jTableEmail.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTableEmail.setComponentPopupMenu(jPopupMenuEmail);
        jScrollPane3.setViewportView(jTableEmail);

        javax.swing.GroupLayout jPanelEmailLayout = new javax.swing.GroupLayout(jPanelEmail);
        jPanelEmail.setLayout(jPanelEmailLayout);
        jPanelEmailLayout.setHorizontalGroup(
            jPanelEmailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelEmailLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanelEmailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanelEmailLayout.createSequentialGroup()
                        .addComponent(jButtonEmailEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(jButtonEmailCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonEmailAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addComponent(jLabel7)
                    .addComponent(jTextFieldEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 329, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 329, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        jPanelEmailLayout.setVerticalGroup(
            jPanelEmailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelEmailLayout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jPanelEmailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonEmailEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanelEmailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButtonEmailCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButtonEmailAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(6, 6, 6)
                .addComponent(jLabel7)
                .addGap(6, 6, 6)
                .addComponent(jTextFieldEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Emails", jPanelEmail);

        jPanelDireccion.setBackground(new java.awt.Color(255, 255, 255));

        jButtonDireccionAgregar.setBackground(new java.awt.Color(0, 153, 102));
        jButtonDireccionAgregar.setForeground(new java.awt.Color(255, 255, 255));
        jButtonDireccionAgregar.setText("Agregar");
        jButtonDireccionAgregar.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jButtonDireccionAgregar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtonDireccionAgregarMouseClicked(evt);
            }
        });

        jButtonDireccionCancelar.setBackground(new java.awt.Color(255, 51, 0));
        jButtonDireccionCancelar.setForeground(new java.awt.Color(255, 255, 255));
        jButtonDireccionCancelar.setText("Cancelar");
        jButtonDireccionCancelar.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jButtonDireccionCancelar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtonDireccionCancelarMouseClicked(evt);
            }
        });

        jButtonDireccionEditar.setBackground(new java.awt.Color(0, 51, 204));
        jButtonDireccionEditar.setForeground(new java.awt.Color(255, 255, 255));
        jButtonDireccionEditar.setText("Editar");
        jButtonDireccionEditar.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jButtonDireccionEditar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtonDireccionEditarMouseClicked(evt);
            }
        });

        jLabel8.setText("Dirección");

        jTableDireccion.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTableDireccion.setComponentPopupMenu(jPopupMenuDireccion);
        jScrollPane4.setViewportView(jTableDireccion);

        jTextAreaDireccion.setColumns(20);
        jTextAreaDireccion.setRows(5);
        jScrollPane6.setViewportView(jTextAreaDireccion);

        javax.swing.GroupLayout jPanelDireccionLayout = new javax.swing.GroupLayout(jPanelDireccion);
        jPanelDireccion.setLayout(jPanelDireccionLayout);
        jPanelDireccionLayout.setHorizontalGroup(
            jPanelDireccionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDireccionLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanelDireccionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanelDireccionLayout.createSequentialGroup()
                        .addComponent(jButtonDireccionEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(jButtonDireccionCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonDireccionAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addComponent(jLabel8)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 329, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 329, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        jPanelDireccionLayout.setVerticalGroup(
            jPanelDireccionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDireccionLayout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jPanelDireccionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonDireccionEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanelDireccionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButtonDireccionCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButtonDireccionAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jLabel8)
                .addGap(6, 6, 6)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 197, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Dirección", jPanelDireccion);

        jPanelTelefono.setBackground(new java.awt.Color(255, 255, 255));

        jLabel5.setText("Tipo de teléfono");

        jLabel6.setText("Teléfono");

        jButtonTelefonoAgregar.setBackground(new java.awt.Color(0, 153, 102));
        jButtonTelefonoAgregar.setForeground(new java.awt.Color(255, 255, 255));
        jButtonTelefonoAgregar.setText("Agregar");
        jButtonTelefonoAgregar.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jButtonTelefonoAgregar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButtonTelefonoAgregarMousePressed(evt);
            }
        });

        jButtonTelefonoCancelar.setBackground(new java.awt.Color(255, 51, 0));
        jButtonTelefonoCancelar.setForeground(new java.awt.Color(255, 255, 255));
        jButtonTelefonoCancelar.setText("Cancelar");
        jButtonTelefonoCancelar.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jButtonTelefonoCancelar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButtonTelefonoCancelarMousePressed(evt);
            }
        });

        jButtonTelefonoEditar.setBackground(new java.awt.Color(0, 51, 204));
        jButtonTelefonoEditar.setForeground(new java.awt.Color(255, 255, 255));
        jButtonTelefonoEditar.setText("Editar");
        jButtonTelefonoEditar.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jButtonTelefonoEditar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButtonTelefonoEditarMousePressed(evt);
            }
        });

        jTableTelefono.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTableTelefono.setComponentPopupMenu(jPopupMenuTelefono);
        jScrollPane5.setViewportView(jTableTelefono);

        jLabel10.setText("+");
        jLabel10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel10MousePressed(evt);
            }
        });

        javax.swing.GroupLayout jPanelTelefonoLayout = new javax.swing.GroupLayout(jPanelTelefono);
        jPanelTelefono.setLayout(jPanelTelefonoLayout);
        jPanelTelefonoLayout.setHorizontalGroup(
            jPanelTelefonoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelTelefonoLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jButtonTelefonoEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(jButtonTelefonoCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(67, 67, 67)
                .addComponent(jButtonTelefonoAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanelTelefonoLayout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addComponent(jLabel10))
            .addGroup(jPanelTelefonoLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jComboBoxTipoTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanelTelefonoLayout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanelTelefonoLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jTextFieldTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanelTelefonoLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanelTelefonoLayout.setVerticalGroup(
            jPanelTelefonoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelTelefonoLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanelTelefonoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonTelefonoEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonTelefonoCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonTelefonoAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanelTelefonoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel10))
                .addGap(6, 6, 6)
                .addComponent(jComboBoxTipoTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jLabel6)
                .addGap(6, 6, 6)
                .addComponent(jTextFieldTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 211, Short.MAX_VALUE)
                .addGap(11, 11, 11))
        );

        jTabbedPane1.addTab("Teléfonos", jPanelTelefono);

        jButtonEditarCliente.setBackground(new java.awt.Color(0, 51, 204));
        jButtonEditarCliente.setForeground(new java.awt.Color(255, 255, 255));
        jButtonEditarCliente.setText("Editar");
        jButtonEditarCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButtonEditarClienteMousePressed(evt);
            }
        });

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

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButtonEditarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonCancelarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonAgregarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 347, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonAgregarCliente)
                    .addComponent(jButtonEditarCliente)
                    .addComponent(jButtonCancelarCliente))
                .addGap(21, 21, 21)
                .addComponent(jTabbedPane1))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jTextFieldBuscador.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldBuscadorKeyReleased(evt);
            }
        });

        jLabel9.setText("Que deseas buscar?");

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
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 542, Short.MAX_VALUE))
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
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(1, 1, 1)))
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

    private void jButtonEmailAgregarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonEmailAgregarMouseClicked
        // TODO add your handling code here:
        this.agregarEmail();
    }//GEN-LAST:event_jButtonEmailAgregarMouseClicked

    private void EditarEmailMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_EditarEmailMousePressed
        // TODO add your handling code here:
        this.MostrarDatoEmail();
    }//GEN-LAST:event_EditarEmailMousePressed

    private void EliminarEmailMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_EliminarEmailMousePressed
        // TODO add your handling code here:
        this.eliminarEmail();
    }//GEN-LAST:event_EliminarEmailMousePressed

    private void jButtonEmailCancelarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonEmailCancelarMouseClicked
        // TODO add your handling code here:
        this.MostrarBotonesEmail(true, false, false);
        this.limpiarEmail();
    }//GEN-LAST:event_jButtonEmailCancelarMouseClicked

    private void jButtonEmailEditarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonEmailEditarMousePressed
        // TODO add your handling code here:
        this.editarEmail();
    }//GEN-LAST:event_jButtonEmailEditarMousePressed

    private void jButtonDireccionAgregarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonDireccionAgregarMouseClicked
        // TODO add your handling code here:
        this.agregarDireccion();
    }//GEN-LAST:event_jButtonDireccionAgregarMouseClicked

    private void jButtonDireccionEditarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonDireccionEditarMouseClicked
        // TODO add your handling code here:
        this.editarDireccion();
    }//GEN-LAST:event_jButtonDireccionEditarMouseClicked

    private void jButtonDireccionCancelarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonDireccionCancelarMouseClicked
        // TODO add your handling code here:
        this.MostrarBotonesDireccion(true, false, false);
        this.limpiarDireccion();
    }//GEN-LAST:event_jButtonDireccionCancelarMouseClicked

    private void EditarDireccionMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_EditarDireccionMousePressed
        // TODO add your handling code here:
        this.MostrarDatoDireccion();
    }//GEN-LAST:event_EditarDireccionMousePressed

    private void EliminarDireccionMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_EliminarDireccionMousePressed
        // TODO add your handling code here:
        this.eliminarDireccion();
    }//GEN-LAST:event_EliminarDireccionMousePressed

    private void EditarTelefonoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_EditarTelefonoMousePressed
        // TODO add your handling code here:
        this.MostrarDatoTelefono();
    }//GEN-LAST:event_EditarTelefonoMousePressed

    private void EliminarTelefonoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_EliminarTelefonoMousePressed
        // TODO add your handling code here:
        this.eliminarTelefono();
    }//GEN-LAST:event_EliminarTelefonoMousePressed

    private void jButtonTelefonoAgregarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonTelefonoAgregarMousePressed
        // TODO add your handling code here:
        this.agregarTelefono();
    }//GEN-LAST:event_jButtonTelefonoAgregarMousePressed

    private void jButtonTelefonoCancelarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonTelefonoCancelarMousePressed
        // TODO add your handling code here:
         this.MostrarBotonesTelefono(true, false, false);
        this.limpiarTelefono();
    }//GEN-LAST:event_jButtonTelefonoCancelarMousePressed

    private void jButtonTelefonoEditarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonTelefonoEditarMousePressed
        // TODO add your handling code here:
        this.editarTelefono();
    }//GEN-LAST:event_jButtonTelefonoEditarMousePressed

    private void jLabel10MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel10MousePressed
        // TODO add your handling code here:
        this.agregarTipoTelefono();
    }//GEN-LAST:event_jLabel10MousePressed

    private void jTextFieldBuscadorKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldBuscadorKeyReleased
        // TODO add your handling code here:
        this.BuscadorCliente();
    }//GEN-LAST:event_jTextFieldBuscadorKeyReleased

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
    private javax.swing.JComboBox<String> JCBSexo;
    private com.toedter.calendar.JDateChooser JDCFechaNacimiento;
    private javax.swing.JLabel JLFechaNacimientoCliente;
    private javax.swing.JLabel JLSexoCliente;
    private javax.swing.JTextField JTFApellido;
    private javax.swing.JTextField JTFNombre;
    private javax.swing.JTextField JTFRNC;
    private javax.swing.JButton jButtonAgregarCliente;
    private javax.swing.JButton jButtonCancelarCliente;
    private javax.swing.JButton jButtonDireccionAgregar;
    private javax.swing.JButton jButtonDireccionCancelar;
    private javax.swing.JButton jButtonDireccionEditar;
    private javax.swing.JButton jButtonEditarCliente;
    private javax.swing.JButton jButtonEmailAgregar;
    private javax.swing.JButton jButtonEmailCancelar;
    private javax.swing.JButton jButtonEmailEditar;
    private javax.swing.JButton jButtonTelefonoAgregar;
    private javax.swing.JButton jButtonTelefonoCancelar;
    private javax.swing.JButton jButtonTelefonoEditar;
    private javax.swing.JComboBox<String> jComboBoxTipoTelefono;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanelDatosPersonales;
    private javax.swing.JPanel jPanelDireccion;
    private javax.swing.JPanel jPanelEmail;
    private javax.swing.JPanel jPanelTelefono;
    private javax.swing.JPopupMenu jPopupMenuCliente;
    private javax.swing.JPopupMenu jPopupMenuDireccion;
    private javax.swing.JPopupMenu jPopupMenuEmail;
    private javax.swing.JPopupMenu jPopupMenuTelefono;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTextField jTFCedula;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTableCliente;
    private javax.swing.JTable jTableDireccion;
    private javax.swing.JTable jTableEmail;
    private javax.swing.JTable jTableTelefono;
    private javax.swing.JTextArea jTextAreaDireccion;
    private javax.swing.JTextField jTextFieldBuscador;
    private javax.swing.JTextField jTextFieldEmail;
    private javax.swing.JTextField jTextFieldTelefono;
    // End of variables declaration//GEN-END:variables
}
