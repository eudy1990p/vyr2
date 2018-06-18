/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formularios.Finanza;

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
public class ComprarNCF extends javax.swing.JFrame {

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
            ncf_fin,
            ncf_ini,
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
    private ComprarNCF yo;
    
    private ListadoProducto classSeleccionListado;
    private String trabajandoClase = "contizacion";
    
    private String nombreUsuario = "";
    

    public void setYo(ComprarNCF yo) {
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
    

    
    public ComprarNCF(Mysql conector,String usuarioID,String usuarioNombre) {
        initComponents();
        this.setConector(conector);
        this.setUsuarioID(usuarioID);
        this.setUsuarioNombre(usuarioNombre);
        this.cargarTabla();
       // this.MostrarBotonesEmail(true, false, false);
       // this.MostrarBotonesDireccion(true, false, false);
       // this.MostrarBotonesTelefono(true, false, false);
       // this.setLocationRelativeTo(null);
       // this.MostrarTipoTelefono();
       this.setLocationRelativeTo(null);
       JScrollPane[] listScroll = {this.jScrollPane1};
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
    
    public void agregarTipoTelefono(){      
        Log.Registrar(this.getClass(), "agregarTipoTelefono", "");

        formularios.telefono.TipoTelefonoAdministrar tipoTelefono = new formularios.telefono.TipoTelefonoAdministrar(this.conector,this.usuarioID,this.usuarioNombre);
        //tipoTelefono.setFOrigen(this.yo,"empleado");
        tipoTelefono.setVisible(true);
    }
    
    
    /*
    Tipo telefono
    */
        public void RecargarTipoTelefono(){
           // this.MostrarTipoTelefono();
        }
       /*public void MostrarTipoTelefono(){
       
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
            System.out.println(ex.getCause().toString());
          }
        
    }*/
    //VALIDAR USUARIO 
    public boolean validarUsuario(){         
        Log.Registrar(this.getClass(), "validarUsuario", "");

         int existe = this.conector.getValues("compra_ncf", "where ncf_ini = '"+this.ncf_ini+"' ");
          if(existe < 1){
            return true;
        }else{
            //JOptionPane.showMessageDialog(null, "El usuario ya existe");
            return false;
          }
    }
    public void insertarDBUsuario(){        
        Log.Registrar(this.getClass(), "insertarDBUsuario", "");

           String campos = "ncf_ini,ncf_fin,usuario_id,fecha";
             String valores = "'"+this.ncf_ini+"','"+this.ncf_fin+"','"+this.usuarioID+"',now()";
             this.conector.insertData("compra_ncf", campos, valores);
             this.usuarioEmpleadoID = this.conector.optenerUltimoID("compra_ncf");
    }
    /*
    public void insertarDBEmail(){
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
             
    }*/
    /*
     public void insertarDBTelefono(){
                //Teléfono
                    if(this.PersonaTelefono.size() > 0){
                        for (int i = 0; i < this.PersonaTelefono.size(); i++) {
                        /*String tableIPE = "persona_telefono";
                        String camposIPE = "persona_id,telefono,tipo_telefono_id,usuario_id,fecha";
                        String valoresIPE = "'"+idPersona+"', '"+this.PersonaTelefono.get(i)+"', '"+this.PersonaTelefonoTipoID.get(i)+"','"+this.usuarioID+"',now() ";
                        this.conector.insertData(tableIPE,camposIPE , valoresIPE);
                        ***************
                        String campos = "usuario_id,telephone,persona_id,fecha_creado,tipo_telefono_id";
                        String valores = "'"+this.usuarioID+"','"+this.PersonaTelefono.get(i)+"','"+this.personaID+"',now(),'"+this.PersonaTelefonoTipoID.get(i)+"'";
                        this.conector.insertData("telephone", campos, valores);
                     }
                    }         
             
    }*/
    /*
     public void insertarDBDireccion(){
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
    }*/
    /*
    public void insertarDBPersona(){
           String campos = "nombre,apellido,sexo,fecha_creado,usuario_id,rnc";//fecha_nacimiento
             String valores = "'"+this.nombre+"','"+this.apellido+"','"+this.sexo+"',now(),'"+this.usuarioID+"','"+this.rnc+"'";
             this.conector.insertData("persona", campos, valores);
             this.personaID = this.conector.optenerUltimoID("persona");
    }*/
    /*
    public void insertarDBEmpleado(){
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
           //queremos evitar que un cliente se ingrese dos veces por esto validamos si existe la cedula o el rnc
           /*String table = "persona as p ";
            String campos = " p.id ";
            String otros = " where (p.cedula ='"+this.cedula+"' ) and (p.cedula !='' ) and (p.cedula is not null) ";
            java.sql.ResultSet resultSet = this.conector.optenerDatosParaTabla(table,campos ,otros);*/
           try {
               //validamos de que el resultado de la db sea 0 si lo es podemos crear el cliente
               //if( !(resultSet.first()) ){
                   //insertamos primero a persona porque es una entidad fuerte
                   //this.insertarDBPersona();
                   this.insertarDBUsuario();
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
                   //this.insertarDBEmail();
                   //this.insertarDBDireccion();
                   //this.insertarDBTelefono();
                   //this.insertarDBEmpleado();
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
               Log.Error("insert", ex);
               Logger.getLogger(ComprarNCF.class.getName()).log(Level.SEVERE, null, ex);
           }
           }else{
                   JOptionPane.showMessageDialog(null, "El NCF ya existe, favor ingrese otro");
             }
       }
       
       
    }
    public void cargarVariables(){       
        Log.Registrar(this.getClass(), "cargarVariables", "");

        //nombre = this.JTFNombre.getText();
        //apellido = this.JTFApellido.getText();
        //rnc = this.JTFRNC.getText();
        //cedula = this.jTFCedula.getText();
        //sexo = this.JCBSexo.getSelectedItem().toString();
        //this.tipoUsuario = this.jCBTipoUsuario.getSelectedItem().toString();
        this.ncf_ini = this.jTFNCFINI.getText();
        this.ncf_fin = this.jTFNCFFIN.getText();
        //javax.swing.JTextField fn = (javax.swing.JTextField) this.JDCFechaNacimientoCliente.getDateEditor().getUiComponent();
        
        //this.formatearFecha();
    }
    /*
   public void formatearFecha(){
         fechaNacimiento = "0000-00-00";
        if(!(((javax.swing.JTextField) this.JDCFechaNacimiento.getDateEditor().getUiComponent()).getText().isEmpty())){
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
        fechaNacimiento = sdf.format(this.JDCFechaNacimiento.getDate());
        }
    }*/
    
    
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
       //if(nombre.isEmpty()){
         //  JOptionPane.showMessageDialog(null,"El campo nombre esta vacío");
       //}else if(apellido.isEmpty()){
         //   JOptionPane.showMessageDialog(null,"El campo apellido esta vacío");
        if(this.ncf_ini.isEmpty()){
            JOptionPane.showMessageDialog(null,"El campo ncf inicio esta vacío");
       }else if(this.ncf_fin.isEmpty()){
            JOptionPane.showMessageDialog(null,"El campo ncf final esta vacío");
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
              Log.Registrar(this.getClass(), "editar", "");

       if(this.validarVacioContinuo()){
//           this.formatearFecha();
           String table = " compra_ncf ";
               // + "inner join persona as p on c.persona_id = p.id "
               // + "inner join usuario as u on c.usuario_empleado_id = u.id ";
           String campos = "ncf_ini='"+this.ncf_ini+"',"
                   + "ncf_fin='"+this.ncf_fin+"'"
                   //+ "p.cedula='"+this.cedula+"',"
                   //+ "u.tipo_usuario='"+this.tipoUsuario+"',"
                   //+ "u.clave_usuario='"+this.clave+"',"
                   //+ "p.fecha_nacimiento='"+this.fechaNacimiento+"' "
                   ;
           String where = " id = '"+this.CurrentClienteID+"' ";
           this.conector.actulizarDatos(table,campos , where);
           JOptionPane.showMessageDialog(null,"Se edito");
           this.limpiar();
           this.cargarTabla();
           this.EditarClienteActivado = false;
        }
       
       
    }
    public void limpiar(){
          Log.Registrar(this.getClass(), "limpiar", "");

//        this.JTFApellido.setText("");
//        this.JTFNombre.setText("");
//        this.JTFRNC.setText("");
//        this.jTFCedula.setText("");
//        this.jTableEmail.setModel(new DefaultTableModel());
//        this.jTabbedPane1.setSelectedIndex(0);
//        this.PersonaEmailIDs.clear();
//        this.PersonaEmails.clear();
//        
        //this.jTableDireccion.setModel(new DefaultTableModel());
        //this.PersonaDireccionIDs.clear();
        //this.PersonaDireccion.clear();
        
        //this.jTableTelefono.setModel(new DefaultTableModel());
        //this.PersonaTelefonoIDs.clear();
        //this.PersonaTelefono.clear();
        //this.PersonaTelefonoTipoNombre.clear();
        //this.PersonaTelefonoTipoID.clear();
        this.jTFNCFINI.setText("");
        this.jTFNCFFIN.setText("");
       // this.jPFPassword.setText("");
        //((javax.swing.JTextField) this.JDCFechaNacimiento.getDateEditor().getUiComponent()).setText("");
    }
    public void eliminar(){              
        Log.Registrar(this.getClass(), "eliminar", "");

       int index = this.jTableCliente.getSelectedRow();
        if(index >= 0){
           if(JOptionPane.showConfirmDialog(null, "Esta seguro que desea eliminar el NCF?", "Eliminar NCF", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION ){ 
                this.CurrentClienteID = this.jTableCliente.getValueAt(index, 0).toString();
                String table = "compra_ncf";
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
        //String nombre= this.jTable1.getValueAt(index, 0).toString();
        //String tipo = this.jTable1.getValueAt(index, 0).toString();
        //JOptionPane.showMessageDialog(null, nombre+" "+id+" "+tipo);
        try {
        String table_name = "compra_ncf "
                //+ "inner join persona as p on c.persona_id = p.id "
                //+ "inner join usuario as u on c.usuario_empleado_id = u.id "
                ;
        String campos = " * ";
        String otros = " where id = '"+id+"' and display = true ";
        java.sql.ResultSet resultSet = this.conector.optenerDatosParaTabla(table_name,campos ,otros);
        if( resultSet.first() ){
                   //Mostrar datos personales y del cliente
                   this.CurrentClienteID = resultSet.getString("id");
                   this.jTFNCFINI.setText(resultSet.getString("ncf_ini"));
                   this.jTFNCFFIN.setText(resultSet.getString("ncf_fin"));
                   //this.jTFCedula.setText(resultSet.getString("cedula"));
                   //this.JTFRNC.setText(resultSet.getString("rnc"));
                   //this.CurrentPersonaID = resultSet.getString("personaid");
                   //this.jCBTipoUsuario.setSelectedItem(resultSet.getString("rol"));
                   //this.jTFNCFINI.setText(resultSet.getString("usuario"));
                   //this.jPFPassword.setText(resultSet.getString("clave_usuario"));
//                   try{
//                   if(!(resultSet.getString("fecha_nacimiento").equals("0000-00-00"))){
//                        System.out.println(resultSet.getDate("fecha_nacimiento"));
//                        this.JDCFechaNacimiento.setDate(resultSet.getDate("fecha_nacimiento"));
//                   }
//                   }catch(NullPointerException npe){
//                       System.out.println(npe.getCause()+" "+npe.getMessage());
//                      
//                   }catch(Exception ecp){
//                       System.out.println(ecp.getCause()+" "+ecp.getMessage());
//                   }
//                   JCBSexo.setSelectedItem(resultSet.getString("sexo"));
//                   
//                   
//                   
//                   //email
//                   this.MostrarEmails();
//                   this.MostrarBotonesEmail(true,false, false);
//                   
//                   //direccion
//                   this.MostrarDirecciones();
//                   this.MostrarBotonesDireccion(true,false, false);
//                   
//                   //telefono
//                   this.MostrarTelefonos();
//                   this.MostrarBotonesTelefono(true,false, false);
                   this.MostrarBotones(false, true,true );
                   this.EditarClienteActivado = true;
          }else{
            JOptionPane.showMessageDialog(null, "No hay registro");
            //System.out.println("");
        }
          } catch (SQLException ex) {
              Log.Error("MostrarDato", ex);
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
   public void cargarTabla(){        
       Log.Registrar(this.getClass(), "cargarTabla", "");

        try {
         String table_name = "compra_ncf "
//                + "inner join persona as p on c.persona_id = p.id "
//                + "inner join usuario as u on c.usuario_empleado_id = u.id "
//                + "inner join usuario as u1 on c.usuario_id = u1.id "
                 ;
        String campos = " * ";
        String otros = " where display = true "+this.Buscador+" ";
        java.sql.ResultSet resultSet = this.conector.optenerDatosParaTabla(table_name,campos ,otros);
        if( resultSet.first() ){
            int total = 0;
        do{
            total++;
        }while(resultSet.next());
        resultSet.first();
        String[] titulos = {"ID","INICIO","FINAL","ULTIMO USADO"};
        
        Object[][] fila = new Object[total][10];
            
         int c = 0;
         
          do{
                fila[c][0] = resultSet.getString("id");
                    fila[c][1] = resultSet.getString("ncf_ini");
                    fila[c][2] = resultSet.getString("ncf_fin");
                    fila[c][3] = resultSet.getString("ncf_ult_usado"); 
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
              Log.Error("cargarTabla", ex);
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
        jPanel4 = new javax.swing.JPanel();
        jButtonAgregarCliente = new javax.swing.JButton();
        jButtonCancelarCliente = new javax.swing.JButton();
        jButtonEditarCliente = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jTFNCFINI = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jTFNCFFIN = new javax.swing.JTextField();

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
        setTitle("Empleado");

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
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 530, Short.MAX_VALUE))
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

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

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

        jTabbedPane1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel11.setText("Inicio");

        jLabel12.setText("Final");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTFNCFINI, javax.swing.GroupLayout.PREFERRED_SIZE, 329, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12)))
                    .addComponent(jTFNCFFIN))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTFNCFINI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel12)
                .addGap(1, 1, 1)
                .addComponent(jTFNCFFIN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(285, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("NCF", jPanel3);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButtonEditarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButtonCancelarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(64, 64, 64)
                .addComponent(jButtonAgregarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonCancelarCliente)
                    .addComponent(jButtonEditarCliente)
                    .addComponent(jButtonAgregarCliente))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTabbedPane1))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
  //      this.MostrarDatoEmail();
    }//GEN-LAST:event_EditarEmailMousePressed

    private void EliminarEmailMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_EliminarEmailMousePressed
        // TODO add your handling code here:
    //    this.eliminarEmail();
    }//GEN-LAST:event_EliminarEmailMousePressed

    private void EditarDireccionMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_EditarDireccionMousePressed
        // TODO add your handling code here:
       // this.MostrarDatoDireccion();
    }//GEN-LAST:event_EditarDireccionMousePressed

    private void EliminarDireccionMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_EliminarDireccionMousePressed
        // TODO add your handling code here:
      //  this.eliminarDireccion();
    }//GEN-LAST:event_EliminarDireccionMousePressed

    private void EditarTelefonoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_EditarTelefonoMousePressed
        // TODO add your handling code here:
      //  this.MostrarDatoTelefono();
    }//GEN-LAST:event_EditarTelefonoMousePressed

    private void EliminarTelefonoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_EliminarTelefonoMousePressed
        // TODO add your handling code here:
       // this.eliminarTelefono();
    }//GEN-LAST:event_EliminarTelefonoMousePressed

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
    private javax.swing.JButton jButtonAgregarCliente;
    private javax.swing.JButton jButtonCancelarCliente;
    private javax.swing.JButton jButtonEditarCliente;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPopupMenu jPopupMenuCliente;
    private javax.swing.JPopupMenu jPopupMenuDireccion;
    private javax.swing.JPopupMenu jPopupMenuEmail;
    private javax.swing.JPopupMenu jPopupMenuTelefono;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTFNCFFIN;
    private javax.swing.JTextField jTFNCFINI;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTableCliente;
    private javax.swing.JTextField jTextFieldBuscador;
    // End of variables declaration//GEN-END:variables
}
