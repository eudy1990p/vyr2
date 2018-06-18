/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexion;

import ClassStatic.Log;
import java.util.Scanner;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Eudy
 */
public class Mysql {

     private static Connection Conexion;
     //private String user="vyr",pass="123456",db_name="db_vyr_cliente2";
     private String user="root",pass="",db_name="db_vyr_cliente1";
     private String orden = "";
     private boolean mostrarMensaje = true;
     private boolean remote = false;
     public static boolean Estado = true;

    public boolean isMostrarMensaje() {
        return mostrarMensaje;
    }

    public void setMostrarMensaje(boolean mostrarMensaje) {
        this.mostrarMensaje = mostrarMensaje;
    }
             
     public Mysql(){
         this.MySQLConnection(user, pass, db_name);
     }

    public void MySQLConnection(String user, String pass, String db_name) {       
        Log.Registrar(this.getClass(), "MySQLConnection", "");

        try {
            if(this.remote){ user="vyr";pass="123456";db_name="db_vyr_cliente2"; }
            Class.forName("com.mysql.jdbc.Driver");
            Conexion =(this.remote)?DriverManager.getConnection("jdbc:mysql://10.0.0.24:3306/"+db_name, user, pass): DriverManager.getConnection("jdbc:mysql://localhost:3306/"+db_name, user, pass);
            //Conexion = DriverManager.getConnection("jdbc:mysql://10.0.0.24:3306/"+db_name, user, pass);
           // Conexion.createStatement().executeQuery("use java_hospital_theme_db");
            System.out.println("Se ha iniciado la conexión con el servidor de forma exitosa");
            Estado= true;
        
        } catch (ClassNotFoundException ex) {
            Estado= false;
            Log.Error("Conexion ",ex);
            JOptionPane.showMessageDialog(null, "Mysql error 1");
            System.out.println("Error1");
            Logger.getLogger(Mysql.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Estado= false;
            Log.Error("Conexion ",ex);
            JOptionPane.showMessageDialog(null, "Mysql error 2");
            System.out.println("error "+ex.getLocalizedMessage());
            System.out.println("Error2 : 1 "+ex.getErrorCode()+" 2- "+ex.getCause()+" 3- "+ex.getLocalizedMessage()+" 4- "+ex.getMessage()+" 5- "+ex.getSQLState()
                    );
            switch(ex.getSQLState()){
                /*case "java.net.ConnectException: Connection refused: connect":
                    System.out.println("entro en este caso");
                    break;*/
                 case "08S01":
                    System.out.println("no conecta al servidor");
                    break;
                case "42000":
                    System.out.println("acceso denegado");
                    System.out.println(ex.getCause());
                    if(ex.getCause() == null){
                        System.out.println("prueba caso "+ex.getCause());
                    }
                    break;
            }
            //Logger.getLogger(Mysql.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean generarInsert(String[] key, String[] value,String tableName) {  
        Log.Registrar(this.getClass(), "generarInsert", "");

        String values = "";
        String keys = "";
    String Query = "";
        //JOptionPane.showMessageDialog(null,String.join(",", value));
        //JOptionPane.showMessageDialog(null,String.join(",", key));
        try {
            for(int i = 0 ; i < value.length ; i++){
                System.out.println(i +" "+value.length);
                if(i == 0){
                    System.out.println("0 Values "+value[i]+" key "+key[i]);
                    keys += key[i];
                    if(value[i].equals("now()")){
                        values += ""+value[i]+"";
                    }else{
                        values += "'"+value[i]+"'";
                    }
                }else{
                    System.out.println("0 + Values "+value[i]+" key "+key[i]);
                    keys += ","+key[i];
                    if(value[i].equals("now()")){
                        values += ","+value[i]+"";
                    }else{
                        values += ",'"+value[i]+"'";
                    }
                }
                
            }
            //System.out.println("Salimos Values "+values+" key "+keys);
            Query = "INSERT INTO " + tableName + " "
                    + "("+keys+")"
                    + "values ("+values+")";
        Log.Registrar(this.getClass(), "generarInsert", Query);
   
            System.out.println("insert "+Query);
          /*  String Query = "INSERT INTO " + table_name + " VALUES("
                    + "\"" + ID + "\", "
                    + "\"" + name + "\", "
                    + "\"" + lastname + "\", "
                    + "\"" + age + "\", "
                    + "\"" + gender + "\")";*/
            Statement st = Conexion.createStatement();
            st.executeUpdate(Query);
            if(this.mostrarMensaje){
            JOptionPane.showMessageDialog(null, "Datos almacenados de forma exitosa");
            }
            return true;
        } catch (SQLException ex) {
             Log.Error(this.getClass().getName(), ex);
            JOptionPane.showMessageDialog(null, "Error en el almacenamiento de datos");
            System.out.println(ex.getErrorCode()+" "+ex.getMessage()+" "+ex.getCause()+" Consulta "+Query);

            return false;
        }
    }
    
    public String generarInsertWithGetLastID(String[] key, String[] value,String tableName) {   
        Log.Registrar(this.getClass(), "generarInsertWithGetLastID", "");

        String values = "";
        String keys = "";
        String ultimoID = "";
        try {
            for(int i = 0 ; i < value.length ; i++){
                System.out.println(i +" "+value.length);
                if(i == 0){
                    System.out.println("0 Values "+value[i]+" key "+key[i]);
                    keys += key[i];
                    if(value[i].equals("now()")){
                        values += ""+value[i]+"";
                    }else{
                        values += "'"+value[i]+"'";
                    }
                }else{
                    System.out.println("0 + Values "+value[i]+" key "+key[i]);
                    keys += ","+key[i];
                    if(value[i].equals("now()")){
                        values += ","+value[i]+"";
                    }else{
                        values += ",'"+value[i]+"'";
                    }
                }
                
            }
            String Query = "INSERT INTO " + tableName + " "
                    + "("+keys+")"
                    + "values ("+values+")";
                    Log.Registrar(this.getClass(), "generarInsertWithGetLastID", Query);

            System.out.println("Query "+Query);
          
            Statement st = Conexion.createStatement();
            st.executeUpdate(Query);
            JOptionPane.showMessageDialog(null, "Datos almacenados de forma exitosa");
            
            Query = "SELECT max(id) as id FROM " + tableName+"";
            st = Conexion.createStatement();
            java.sql.ResultSet resultSet;
            resultSet = st.executeQuery(Query);
            
            if(resultSet.next()){
                ultimoID = resultSet.getString("id");
            }
                
            
        } catch (SQLException ex) {
             Log.Error(this.getClass().getName(), ex);
            JOptionPane.showMessageDialog(null, "Error en el almacenamiento de datos");
            ultimoID = "";
                        System.out.println(ex.getErrorCode()+" "+ex.getMessage()+" "+ex.getCause());

        }
        return ultimoID;
    }
    
    public void generarSelectLastRow(String table_name,String where,DefaultTableModel model,Object[] columnas,String[] datos) {
                          Log.Registrar(this.getClass(), "generarSelectLastRow", "");
     try {
            String Query = "SELECT * FROM " + table_name +" "+where;
                       Log.Registrar(this.getClass(), "generarSelectLastRow", Query);

            System.out.println(Query);
            Statement st = Conexion.createStatement();
            java.sql.ResultSet resultSet;
            resultSet = st.executeQuery(Query);
            
            
            while (resultSet.next()) {
                    for(int i = 0 ; i < datos.length; i++){
                        columnas[i] = resultSet.getString(datos[i]);
                    }
                    model.addRow(columnas);
               /* System.out.println("ID: " + resultSet.getString("ID") + " "
                        + "Nombre: " + resultSet.getString("Nombre") + " " + resultSet.getString("Apellido") + " "
                        + "Edad: " + resultSet.getString("Edad") + " "
                        + "Sexo: " + resultSet.getString("Sexo"));
                    */
            }

        } catch (SQLException ex) {
             Log.Error(this.getClass().getName(), ex);
           // JOptionPane.showMessageDialog(null, "Error en la adquisición de datos generarSelectLastRow");
            System.out.println("Error generarSelectLastRow "+ex.getMessage());
        }
    }
    
    public Object generarSelect(String table_name,String[] campos) {       
        Log.Registrar(this.getClass(), "generarSelect", "");

        try {
            String Query = "SELECT * FROM " + table_name+" where display = '1' ";
                    Log.Registrar(this.getClass(), "generarSelect", Query);

            System.out.println("QUERY "+Query);

            Statement st = Conexion.createStatement();
            java.sql.ResultSet resultSet;
            resultSet = st.executeQuery(Query);            
            resultSet.beforeFirst();  
            resultSet.last();  
            
           //System.out.print("GetRow "+resultSet.getRow());
           int totalFilas = resultSet.getRow();
           int f = 0 ;
            resultSet.beforeFirst();
            Object[][] fila = new Object[totalFilas][campos.length];
            while (resultSet.next()) {
                    //System.out.println("ID: " + resultSet.getString("id"));
                    for(int i = 0 ; i < campos.length ;i++){
                      System.out.println("i " + i+" valor campo "+resultSet.getString(campos[i])+" campo "+campos[i]);
                        fila[f][i] = resultSet.getString(campos[i]);
                    }
                    f++;
            }
            System.out.print(" fin while" );
            Object[][] resultado = new Object[1][2];
            System.out.print(" objecto" );

            resultado[0][0]=fila;
            resultado[0][1]=totalFilas;
            System.out.print(" fin" );

            return resultado;
        } catch (SQLException ex) {
             Log.Error(this.getClass().getName(), ex);
            Object[][] resultado = new Object[1][2];
            Object[][] fila = new Object[1][2];
            resultado[0][0]=fila;
            resultado[0][1]=0;
            //JOptionPane.showMessageDialog(null, "Error en la adquisición de datos generarSelect");
            System.out.println("error generarSelect "+ex.getMessage());
            return resultado;
        }
            
    }
    
    public Object generarSelect(String table_name,String[] campos, String where) {
                   Log.Registrar(this.getClass(), "generarSelect", "");

        try {
            String Query = "SELECT * FROM " + table_name+" where "+where+" display = '1' ";
           Log.Registrar(this.getClass(), "generarSelect", Query);

            System.out.println("QUERY "+Query);

            Statement st = Conexion.createStatement();
            java.sql.ResultSet resultSet;
            resultSet = st.executeQuery(Query);            
            resultSet.beforeFirst();  
            resultSet.last();  
            
           //System.out.print("GetRow "+resultSet.getRow());
           int totalFilas = resultSet.getRow();
           int f = 0 ;
            resultSet.beforeFirst();
            Object[][] fila = new Object[totalFilas][campos.length];
            while (resultSet.next()) {
                    //System.out.println("ID: " + resultSet.getString("id"));
                    for(int i = 0 ; i < campos.length ;i++){
                      System.out.println("i " + i+" valor campo "+resultSet.getString(campos[i])+" campo "+campos[i]);
                        fila[f][i] = resultSet.getString(campos[i]);
                    }
                    f++;
            }
            System.out.print(" fin while" );
            Object[][] resultado = new Object[1][2];
            System.out.print(" objecto" );

            resultado[0][0]=fila;
            resultado[0][1]=totalFilas;
            System.out.print(" fin" );

            return resultado;
        } catch (SQLException ex) {
             Log.Error(this.getClass().getName(), ex);
            Object[][] resultado = new Object[1][2];
            Object[][] fila = new Object[1][2];
            resultado[0][0]=fila;
            resultado[0][1]=0;
            //JOptionPane.showMessageDialog(null, "Error en la adquisición de datos generarSelect");
            System.out.println("error generarSelect "+ex.getMessage());
            return resultado;
        }
            
    }
    
    public Object generarSelectMultipleTabla(String table_name,String[] campos,String select, String where,String prefijo) {
                     Log.Registrar(this.getClass(), "generarSelectMultipleTabla","");

        System.out.println(String.join(",", campos));

        try {
            String Query = "SELECT "+select+" FROM " + table_name+" where "+where+" "+prefijo+"display = '1' "+this.orden;
             Log.Registrar(this.getClass(), "generarSelectMultipleTabla", Query);
 System.out.println("QUERY "+Query);

            Statement st = Conexion.createStatement();
            java.sql.ResultSet resultSet;
            resultSet = st.executeQuery(Query);            
            resultSet.beforeFirst();  
            resultSet.last();  
            
           //System.out.print("GetRow "+resultSet.getRow());
           int totalFilas = resultSet.getRow();
           int f = 0 ;
            resultSet.beforeFirst();
            Object[][] fila = new Object[totalFilas][campos.length];
             System.out.println(" ok estamos por mostrar ");
            while (resultSet.next()) {
                    
                    //System.out.println("ID: " + resultSet.getString("id"));
                    for(int i = 0 ; i < campos.length ;i++){
                        if(campos[i].equalsIgnoreCase("poh.price")){
                            System.out.println(" dato que no se muestra "+resultSet.getBigDecimal("poh.price")+" | "+campos[1]);
                            String da = new String(resultSet.getBigDecimal(campos[i]).toPlainString()); 
                            fila[f][i] = da;
                        }else{
                            System.out.println(" ok ya estamos ");
                            
                            System.out.println("i " + i+" valor campo "+resultSet.getString(campos[i])+" campo "+campos[i]);
                            fila[f][i] = resultSet.getString(campos[i]).toString();
                        }
                    }
                    f++;
            }
            this.mostrarElementoArreglo(fila);
            System.out.print(" fin while" );
            Object[][] resultado = new Object[1][2];
            System.out.print(" objecto" );

            resultado[0][0]=fila;
            resultado[0][1]=totalFilas;
            System.out.print(" fin" );

            return resultado;
        } catch (SQLException ex) {
             Log.Error(this.getClass().getName(), ex);
            Object[][] resultado = new Object[1][2];
            Object[][] fila = new Object[1][2];
            resultado[0][0]=fila;
            resultado[0][1]=0;
            //JOptionPane.showMessageDialog(null, "Error en la adquisición de datos generarSelectMultipleTabla(String table_name,String[] campos,String select, String where,String prefijo)");
           System.out.println("Error en la adquisición de datos generarSelectMultipleTabla(String table_name,String[] campos,String select, String where,String prefijo) "+ex.getMessage());
            return resultado;
        }
            
    }

    public void setOrden(String orden) {
        this.orden = orden;
    }
    
    public void mostrarElementoArreglo(Object[][] p){
        for(int i = 0 ; i < p.length ; i++){
            for(int c = 0 ; c < p[i].length ; c++){
                System.out.println(i+"Resultado "+p[i][c]);
           }   
        }
        
    }
    public Object generarSelect(String table_name,String[] campos,String CampoOrdenar,String tipoOrden,String otros) { 
        Log.Registrar(this.getClass(), "generarSelect", "");

        try {
            String Query = "SELECT * FROM " + table_name+" where display = '1' "+otros+"  order by "+CampoOrdenar+" "+tipoOrden+" ";
           Log.Registrar(this.getClass(), "generarSelect", Query);

            System.out.println("Generar Select QUERY "+Query);

            Statement st = Conexion.createStatement();
            java.sql.ResultSet resultSet;
            resultSet = st.executeQuery(Query);            
            resultSet.beforeFirst();  
            resultSet.last();  
            
           //System.out.print("GetRow "+resultSet.getRow());
           int totalFilas = resultSet.getRow();
           int f = 0 ;
            resultSet.beforeFirst();
            Object[][] fila = new Object[totalFilas][campos.length];
            while (resultSet.next()) {
                    //System.out.println("ID: " + resultSet.getString("id"));
                    for(int i = 0 ; i < campos.length ;i++){
                        System.out.println("i " + i+" valor campo "+resultSet.getString(campos[i])+" campo "+campos[i]);
                        fila[f][i] = resultSet.getString(campos[i]);
                    }
                    f++;
            }
            System.out.print(" fin while" );
            Object[][] resultado = new Object[1][2];
            System.out.print(" objecto" );

            resultado[0][0]=fila;
            resultado[0][1]=totalFilas;
            System.out.print(" fin" );

            return resultado;
        } catch (SQLException ex) {
             Log.Error(this.getClass().getName(), ex);
            Object[][] resultado = new Object[1][2];
            resultado[0][0]=0;
            resultado[0][1]=0;
            //JOptionPane.showMessageDialog(null, "Error en la adquisición de datos generarSelect(String table_name,String[] campos,String CampoOrdenar,String tipoOrden,String otros)");
            System.out.println("Error en la adquisición de datos generarSelect(String table_name,String[] campos,String CampoOrdenar,String tipoOrden,String otros)"+ex.getMessage());

            return resultado;
        }
            
    }
    
    
    public Object generarSelect(String table_name,String[] campos,String palabra,String campo) { 
        Log.Registrar(this.getClass(), "generarSelect", "");

        try {
            String Query = "SELECT * FROM " + table_name+" where "+campo+" like '%"+palabra+"%' and display = '1' ";
                       Log.Registrar(this.getClass(), "generarSelect", Query);

            System.out.println(Query);
            Statement st = Conexion.createStatement();
            java.sql.ResultSet resultSet;
            resultSet = st.executeQuery(Query);
            
            resultSet.beforeFirst();  
            resultSet.last();  
            
           //System.out.print("GetRow "+resultSet.getRow());
           int totalFilas = resultSet.getRow();
           int f = 0 ;
            resultSet.beforeFirst();
            Object[][] fila = new Object[totalFilas][campos.length];
            while (resultSet.next()) {
                    //System.out.println("ID: " + resultSet.getString("id"));
                      for(int i = 0 ; i < campos.length ;i++){
                        fila[f][i] = resultSet.getString(campos[i]);
                    }
                    //fila[f][0] = resultSet.getString("id");
                    //fila[f][1]  = resultSet.getString("name_user");
                    //fila[f][2]  = resultSet.getString("type_of_user");

                    f++;
            }
            Object[][] resultado = new Object[1][2];
            resultado[0][0]=fila;
            resultado[0][1]=totalFilas;
            return resultado;
        } catch (SQLException ex) {
             Log.Error(this.getClass().getName(), ex);
            Object[][] resultado = new Object[1][2];
            Object[][] fila = new Object[1][2];
            resultado[0][0]=fila;
            resultado[0][1]=0;
            //JOptionPane.showMessageDialog(null, "Error en la adquisición de datos generarSelect(String table_name,String[] campos,String palabra,String campo)");
            System.out.println("Error en la adquisición de datos generarSelect(String table_name,String[] campos,String palabra,String campo)"+ex.getMessage());

            return resultado;
        }
            
    }
    
       public String[] generarSelect(String table_name,String id,String[] campos) {       
           Log.Registrar(this.getClass(), "generarSelect", "");

        try {
            String Query = "SELECT * FROM " + table_name+" where id = "+id+" and display = '1'";
                       Log.Registrar(this.getClass(), "generarSelect", Query);

            System.out.println(Query);
            Statement st = Conexion.createStatement();
            java.sql.ResultSet resultSet;
            resultSet = st.executeQuery(Query);
            resultSet.beforeFirst();  
            resultSet.last();  
           int totalFilas = resultSet.getRow();
           int f = 0 ;
            resultSet.beforeFirst();
           String[] fila = new String[campos.length];
            if (resultSet.next()) {
                    for(int i = 0 ; i < campos.length ; i++){
                        fila[i]=resultSet.getString(campos[i]);
                        System.out.println(" Probando "+fila[i]+" ID: " + resultSet.getString("id"));
                    }       
            }
            return fila;
        } catch (SQLException ex) {
             Log.Error(this.getClass().getName(), ex);
            String[] fila = {"no"};
            JOptionPane.showMessageDialog(null, "Error en la adquisición de datos generarSelect(String table_name,String id,String[] campos)");
            System.out.println("Error en la adquisición de datos generarSelect(String table_name,String id,String[] campos)"+ex.getMessage());
            
            return fila;
        }
    }
    
       public String[] generarSelectWithJoin(String table_name,String mostrarCampos,String id,String[] campos,String prefijo) {
                                 Log.Registrar(this.getClass(), "generarSelectWithJoin", "");
try {
            String Query = "SELECT "+mostrarCampos+" FROM " + table_name+" where "+prefijo+"id = "+id+" and  "+prefijo+"display = '1'";
                         Log.Registrar(this.getClass(), "generarSelectWithJoin", Query);
System.out.println(Query);
            Statement st = Conexion.createStatement();
            java.sql.ResultSet resultSet;
            resultSet = st.executeQuery(Query);
            resultSet.beforeFirst();  
            resultSet.last();  
           int totalFilas = resultSet.getRow();
           int f = 0 ;
            resultSet.beforeFirst();
           String[] fila = new String[campos.length];
            if (resultSet.next()) {
                    for(int i = 0 ; i < campos.length ; i++){
                         if(campos[i].equalsIgnoreCase("poh.price")){
                            System.out.println(" dato que no se muestra "+resultSet.getBigDecimal("poh.price")+" | "+campos[1]);
                            //fila[i]=resultSet.getString(campos[i]);
                            String da = new String(resultSet.getBigDecimal(campos[i]).toPlainString()); 
                            fila[i] = da;
                        }else{
                            fila[i]=resultSet.getString(campos[i]);
                            System.out.println(" Probando "+fila[i]+" ID: " + resultSet.getString("id"));
                        }
                        
                        
                    }       
            }
            return fila;
        } catch (SQLException ex) {
             Log.Error(this.getClass().getName(), ex);
            String[] fila = {"no"};
            //JOptionPane.showMessageDialog(null, "Error en la adquisición de datos generarSelectWithJoin(String table_name,String mostrarCampos,String id,String[] campos,String prefijo)");
            System.out.println("Error en la adquisición de datos generarSelectWithJoin(String table_name,String mostrarCampos,String id,String[] campos,String prefijo)"+ex.getMessage());            
            return fila;
        }
    }
       
       
    public String[] generarSelect(String table_name,String columnas,String id,String[] campos) {
                   Log.Registrar(this.getClass(), "generarSelect", "");

        try {
            String Query = "SELECT "+columnas+" FROM " + table_name+" where id = "+id+" and display = '1'";
                       Log.Registrar(this.getClass(), "generarSelect", Query);

            System.out.println(Query);
            Statement st = Conexion.createStatement();
            java.sql.ResultSet resultSet;
            resultSet = st.executeQuery(Query);
            resultSet.beforeFirst();  
            resultSet.last();  
           int totalFilas = resultSet.getRow();
           int f = 0 ;
            resultSet.beforeFirst();
           String[] fila = new String[campos.length];
            if (resultSet.next()) {
                    for(int i = 0 ; i < campos.length ; i++){
                        fila[i]=resultSet.getString(campos[i]);
                        System.out.println(" Probando "+fila[i]+" ID: " + resultSet.getString("id"));
                    }       
            }
            return fila;
        } catch (SQLException ex) {
             Log.Error(this.getClass().getName(), ex);
            String[] fila = {"no"};
            //JOptionPane.showMessageDialog(null, "Error en la adquisición de datos generarSelect(String table_name,String columnas,String id,String[] campos)");
            System.out.println("Error en la adquisición de datos generarSelect(String table_name,String columnas,String id,String[] campos)"+ex.getMessage());            
            
            return fila;
        }
    }
    
    
    public String[] generarSelect(String table_name,String nameId,String id,String[] campos,String CampoOrder,String order) { 
        Log.Registrar(this.getClass(), "generarSelect", "");

        try {
            String Query = "SELECT * FROM " + table_name+" where "+nameId+" = "+id+" and display = '1' order by "+CampoOrder+" "+order+" ";
                       Log.Registrar(this.getClass(), "generarSelect", Query);
System.out.println(Query);
            Statement st = Conexion.createStatement();
            java.sql.ResultSet resultSet;
            resultSet = st.executeQuery(Query);
            resultSet.beforeFirst();  
            resultSet.last();  
           int totalFilas = resultSet.getRow();
           int f = 0 ;
            resultSet.beforeFirst();
           String[] fila = new String[campos.length];
            if (resultSet.next()) {
                    for(int i = 0 ; i < campos.length ; i++){
                        fila[i]=resultSet.getString(campos[i]);
                        System.out.println(" Probando "+fila[i]+" ID: " + resultSet.getString("id"));
                    }       
            }
            return fila;
        } catch (SQLException ex) {
             Log.Error(this.getClass().getName(), ex);
            String[] fila = {"no"};
            //JOptionPane.showMessageDialog(null, "Error en la adquisición de datos generarSelect(String table_name,String nameId,String id,String[] campos,String CampoOrder,String order)");
            System.out.println("Error en la adquisición de datos generarSelect(String table_name,String nameId,String id,String[] campos,String CampoOrder,String order)"+ex.getMessage());            
            
            return fila;
        }
    }
    public void closeConnection() {     
        Log.Registrar(this.getClass(), "closeConnection", "");

        try {
            Conexion.close();
            System.out.println("Se ha finalizado la conexión con el servidor");
        } catch (SQLException ex) {
             Log.Error(this.getClass().getName(), ex);
            Logger.getLogger(Mysql.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void createDB(String name) {   
        Log.Registrar(this.getClass(), "createDB", "");

        try {
            String Query = "CREATE DATABASE " + name;
            Statement st = Conexion.createStatement();
            st.executeUpdate(Query);
            MySQLConnection("root", "", name);
            JOptionPane.showMessageDialog(null, "Se ha creado la base de datos " + name + " de forma exitosa");
        } catch (SQLException ex) {
             Log.Error(this.getClass().getName(), ex);
            Logger.getLogger(Mysql.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void createTable(String name) {      
        Log.Registrar(this.getClass(), "createTable", "");

        try {
            String Query = "CREATE TABLE " + name + ""
                    + "(ID VARCHAR(25),Nombre VARCHAR(50), Apellido VARCHAR(50),"
                    + " Edad VARCHAR(3), Sexo VARCHAR(1))";
                    Log.Registrar(this.getClass(), "createTable", Query);

            JOptionPane.showMessageDialog(null, "Se ha creado la base de tabla " + name + " de forma exitosa");
            Statement st = Conexion.createStatement();
            st.executeUpdate(Query);
        } catch (SQLException ex) {
             Log.Error(this.getClass().getName(), ex);
            Logger.getLogger(Mysql.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void insertData(String table_name, String ID, String name, String lastname, String age, String gender) {
        try {
            String Query = "INSERT INTO " + table_name + " VALUES("
                    + "\"" + ID + "\", "
                    + "\"" + name + "\", "
                    + "\"" + lastname + "\", "
                    + "\"" + age + "\", "
                    + "\"" + gender + "\")";
            Statement st = Conexion.createStatement();
            st.executeUpdate(Query);
            JOptionPane.showMessageDialog(null, "Datos almacenados de forma exitosa");
        } catch (SQLException ex) {
             Log.Error(this.getClass().getName(), ex);
            System.out.println("Error en el almacenamiento de datos "+ex.getErrorCode()+" "+ex.getMessage()+" "+ex.getCause());
           // JOptionPane.showMessageDialog(null, "Error en el almacenamiento de datos");
        }
    }

    public int getValues(String table_name, String where) {   
        Log.Registrar(this.getClass(), "getValues", "");

        try {
            String Query = "SELECT * FROM " + table_name+" "+where;
                    Log.Registrar(this.getClass(), "getValues", Query);
             Log.Registrar("conexion/mysql/getValues Query "+Query);

            Statement st = Conexion.createStatement();
            java.sql.ResultSet resultSet;
            Log.Registrar("conexion/mysql/getValues Ejecutar Query "+Query);
            resultSet = st.executeQuery(Query);
            Log.Registrar("conexion/mysql/getValues Exito Query "+Query);
            
            resultSet.beforeFirst();  
            resultSet.last();  
           int totalFilas = resultSet.getRow();
           Log.Registrar("conexion/mysql/getValues Total registro "+totalFilas);
           return totalFilas;
            
        } catch (SQLException ex) {
            Log.Error("Conexion/mysql/getValues ", ex);
            //JOptionPane.showMessageDialog(null, "Error en la adquisición de datos getValues(String table_name, String where)");
            System.out.println("Error en la adquisición de datos getValues(String table_name, String where)"+ex.getMessage());
            return 0;
        }
    }
    private String selectCompos = "*";
    public String[] getValues(String table_name, String where, String[] campos) {       
        Log.Registrar(this.getClass(), "getValues", "");

        String[] datos = new String[campos.length];
        try {
            String Query = "SELECT "+this.selectCompos+" FROM " + table_name+" "+where;
                                Log.Registrar(this.getClass(), "getValues", Query);
Statement st = Conexion.createStatement();
            java.sql.ResultSet resultSet;
            resultSet = st.executeQuery(Query);
             
             
             if(resultSet.next()){
                 for(int i = 0 ; i < campos.length ; i++){
                     datos[i] = resultSet.getString(campos[i]);
                 }
             }
             
           return datos;
            
        } catch (SQLException ex) {
             Log.Error(this.getClass().getName(), ex);
            //JOptionPane.showMessageDialog(null, "Error en la adquisición de datos getValues(String table_name, String where, String[] campos)");
            System.out.println("Error en la adquisición de datos getValues(String table_name, String where, String[] campos)"+ex.getMessage());
            return datos;
        }
    }

    public void setSelectCompos(String selectCompos) {
        this.selectCompos = selectCompos;
    }
    
    public void getValues(String table_name) {   
        Log.Registrar(this.getClass(), "getValues", "");

        try {
            String Query = "SELECT * FROM " + table_name;
                    Log.Registrar(this.getClass(), "getValues", Query);
Statement st = Conexion.createStatement();
            java.sql.ResultSet resultSet;
            resultSet = st.executeQuery(Query);

            while (resultSet.next()) {
                System.out.println("ID: " + resultSet.getString("ID") + " "
                        + "Nombre: " + resultSet.getString("Nombre") + " " + resultSet.getString("Apellido") + " "
                        + "Edad: " + resultSet.getString("Edad") + " "
                        + "Sexo: " + resultSet.getString("Sexo"));
            }

        } catch (SQLException ex) {
             Log.Error(this.getClass().getName(), ex);
            //JOptionPane.showMessageDialog(null, "Error en la adquisición de datos getValues(String table_name)");
            System.out.println("Error en la adquisición de datos getValues(String table_name)"+ex.getMessage());
        }
    }

    public boolean deleteRecord(String table_name, String ID) {   
        Log.Registrar(this.getClass(), "deleteRecord", "");

        try {
            //String Query = "DELETE FROM " + table_name + " WHERE ID = \"" + ID + "\"";
            String Query = "update " + table_name + " set display = '0' WHERE ID = \"" + ID + "\"";
                    Log.Registrar(this.getClass(), "deleteRecord", Query);

            Statement st = Conexion.createStatement();
            st.executeUpdate(Query);
            return true;
        } catch (SQLException ex) {
             Log.Error(this.getClass().getName(), ex);
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, "Error borrando el registro especificado");
            return false;
        }
    }
    
    public boolean actulizarDatos(String table_name,String campos, String where) {  
        Log.Registrar(this.getClass(), "actulizarDatos", "");

        try {
            //String Query = "DELETE FROM " + table_name + " WHERE ID = \"" + ID + "\"";
            String Query = "update " + table_name + " set "+campos+" WHERE  " + where +"";
            Log.Registrar("Mysql/actulizarDatos Query "+Query);
            System.out.println(Query);
            Statement st = Conexion.createStatement();
            st.executeUpdate(Query);
            Log.Registrar("Mysql/actulizarDatos se actualizo");
            return true;
        } catch (SQLException ex) {
           
            Log.Error("Mysql/actulizarDatos", ex);
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, "Error al tratar de editar");
            return false;
        }
    }
    
    public boolean updateRecord(String table_name, String id,String[] key, String[] value) { 
        Log.Registrar(this.getClass(), "updateRecord", "");

        String keysValues = "";
        try {
            for(int i = 0 ; i < value.length ; i++){
                System.out.println(i +" "+value.length);
                if(i == 0){
                    System.out.println("0 Values "+value[i]+" key "+key[i]);
                    if(value[i].equals("now()")){
                        keysValues += key[i]+" = "+value[i]+" ";
                    }else{
                         keysValues += key[i]+" = '"+value[i]+"' ";
                    }
                }else{
                    System.out.println("0 + Values "+value[i]+" key "+key[i]);
                    if(value[i].equals("now()")){
                         keysValues += ", "+key[i]+" = "+value[i]+" ";
                    }else{
                         keysValues += ", "+key[i]+" = '"+value[i]+"' ";
                    }
                }
            }
            
            String Query = "update " + table_name + " set "+keysValues+" WHERE id = '" +id+ "' ";
                    Log.Registrar(this.getClass(), "updateRecord", Query);

            System.out.println(Query);
            Statement st = Conexion.createStatement();
            st.executeUpdate(Query);
            return true;
        } catch (SQLException ex) {
             Log.Error(this.getClass().getName(), ex);
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, "Error actualizando el registro especificado");
            return false;
        }
    }
    
    public boolean updateRecord(String table_name,String nameId, String id,String[] key, String[] value) {  
        Log.Registrar(this.getClass(), "updateRecord", "");

        String keysValues = "";
        try {
            for(int i = 0 ; i < value.length ; i++){
                System.out.println(i +" "+value.length);
                if(i == 0){
                    System.out.println("0 Values "+value[i]+" key "+key[i]);
                    if(value[i].equals("now()")){
                        keysValues += key[i]+" = "+value[i]+" ";
                    }else{
                         keysValues += key[i]+" = '"+value[i]+"' ";
                    }
                }else{
                    System.out.println("0 + Values "+value[i]+" key "+key[i]);
                    if(value[i].equals("now()")){
                         keysValues += ", "+key[i]+" = "+value[i]+" ";
                    }else{
                         keysValues += ", "+key[i]+" = '"+value[i]+"' ";
                    }
                }
            }
            
            String Query = "update " + table_name + " set "+keysValues+" WHERE "+nameId+" = " +id+ " ";
                    Log.Registrar(this.getClass(), "updateRecord", Query);

            System.out.println(Query);
            Statement st = Conexion.createStatement();
            st.executeUpdate(Query);
            return true;
        } catch (SQLException ex) {
             Log.Error(this.getClass().getName(), ex);
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, "Error borrando el registro especificado");
            return false;
        }
    }
    
    
    
    
    
    
    
    
    public void insertData(String table_name,String campos, String valores) {
                      Log.Registrar(this.getClass(), "insertData", "");
      try {
            String sqlInsert = "INSERT INTO " + table_name + "("+campos+")  VALUES("+valores+" )";
                                  Log.Registrar(this.getClass(), "insertData", sqlInsert);
   System.out.println("consulta insert "+sqlInsert);
            Statement st = Conexion.createStatement();
            st.executeUpdate(sqlInsert);
            //JOptionPane.showMessageDialog(null, "Datos almacenados de forma exitosa");
         
        } catch (SQLException ex) {
             Log.Error(this.getClass().getName(), ex);
            System.out.println(ex.getErrorCode()+" "+ex.getMessage()+" "+ex.getCause());
            //JOptionPane.showMessageDialog(null, "Error en el almacenamiento de datos");
        }
    }
    public String optenerUltimoID(String table_name) {     
        Log.Registrar(this.getClass(), "optenerUltimoID", "");

        String id = "1";
        try {
            String Query = "SELECT max(id) as id FROM " + table_name;
                    Log.Registrar(this.getClass(), "optenerUltimoID", Query);

            Statement st = Conexion.createStatement();
            java.sql.ResultSet resultSet;
            resultSet = st.executeQuery(Query);

           if (resultSet.next()) {
                id = resultSet.getString("id");
                System.out.println("ID: " + resultSet.getString("ID") );
            }

        } catch (SQLException ex) {
             Log.Error(this.getClass().getName(), ex);
            //JOptionPane.showMessageDialog(null, "Error en la adquisición de datos getValues(String table_name)");
            System.out.println("Error en la adquisición de datos getValues(String table_name)"+ex.getErrorCode()+" "+ex.getMessage()+" "+ex.getCause());
        }
        return id;
    }
    //datediff(CURRENT_DATE,fecha)
    public java.sql.ResultSet optenerListaReparacionesPorFecha() { 
        Log.Registrar(this.getClass(), "optenerListaReparacionesPorFecha", "");

        java.sql.ResultSet resultSet = null;
        try {
            String Query = "SELECT datediff(CURRENT_DATE,r.fecha_creada) as dias, r.id, r.sub_total,r.estado,r.nota,r.abono,r.itbis,r.total,concat(p.nombre,' ',p.apellido) as nombrecompleto," +
                            "u.nombre_usuario " +
                            "FROM reparacion as r " +
                            "inner join cliente as c on c.id = r.cliente_id " +
                            "inner join persona as p on c.persona_id = p.id " +
                            "inner join usuario as u on u.id = r.usuario_id "
                    + " where r.estado <> 'anulada' ";
           Log.Registrar(this.getClass(), "optenerListaReparacionesPorFecha", Query);

            Statement st = Conexion.createStatement();
            resultSet = st.executeQuery(Query);

        } catch (SQLException ex) {
             Log.Error(this.getClass().getName(), ex);
            //JOptionPane.showMessageDialog(null, "Error en la adquisición de datos getValues(String table_name)");
            System.out.println("Error en la adquisición de datos getValues(String table_name)"+ex.getErrorCode()+" "+ex.getMessage()+" "+ex.getCause());
            
            resultSet = null;
        }
        return resultSet;
    }
    
    public java.sql.ResultSet optenerDatosParaTabla(String table_name, String campos,String otros) {    
        Log.Registrar(this.getClass(), "optenerDatosParaTabla", "");

        Log.Registrar("Conexion/Mysql/optenerDatosParaTabla tabla "+table_name+" campos "+campos+" otro "+otros+" ");
     java.sql.ResultSet resultSet = null;
        try {
            String Query = "SELECT "+campos+" FROM " + table_name+" "+otros;
                                              Log.Registrar(this.getClass(), "optenerDatosParaTabla", Query);

           Log.Registrar("Conexion/Mysql/optenerDatosParaTabla query "+Query);

           System.out.println("****************************************************************************************************************");
            System.out.println(Query);
            Statement st = Conexion.createStatement();
            
            resultSet = st.executeQuery(Query);
            Log.Registrar("Conexion/Mysql/optenerDatosParaTabla Exito "+Query);
        } catch (SQLException ex) {
            Log.Error("Conexion/Mysql/optenerDatosParaTabla ",ex);
            //JOptionPane.showMessageDialog(null, "Error en la adquisición de datos getValues(String table_name)");
            System.out.println("Error en la adquisición de datos getValues(String table_name)"+ex.getErrorCode()+" "+ex.getMessage()+" "+ex.getCause());

        }
        
        return resultSet;
    }
}
