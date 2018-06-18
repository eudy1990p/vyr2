/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formularios.Conduce;

import Clases.producto;
import Clases.Texto;
import Clases.Empresa;
import ClassStatic.Log;
import conexion.Mysql;
import formularios.Factura.Facturacion;
import formularios.ListadoProducto;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Eudy
 */
public class Reparacion extends javax.swing.JFrame {

    /**
     * Creates new form Reparacion
     */
    private ArrayList<String> producto_id = new ArrayList<String>();
    private ArrayList<String> sub_total_producto = new ArrayList<String>();
    private ArrayList<String> codigo_producto = new ArrayList<String>();
    private ArrayList<String> nombre_producto = new ArrayList<String>();
    private ArrayList<String> precio_producto = new ArrayList<String>();
    private ArrayList<String> cantidad_producto = new ArrayList<String>();
    private ArrayList<String> nota_producto = new ArrayList<String>();
    private ArrayList<String> estado_producto = new ArrayList<String>();
    private ArrayList<String> precio_completo_producto  = new ArrayList<String>();
                
    private double montoTotal = 0.00,subMontoTotal = 0.00,abonoTotal = 0.00,itbisTotal = 0.00;
    private Boolean SeguimientoConduce = false;//Cuando seleccionas un coduce para editar o agregar le mas equipos
    
    private int ultimoNumeroGeneredo = 0 ;
    private List productos = null;
    
    private String no_producto,n_producto,p_producto,c_producto,co_producto, sub_total,itbis_total, monto_total;
    private boolean factura_generada = false,deuda = false,mostrarPDF = true;
    
    private boolean existe_cliente = false,existe_producto= false,cotizado = false,no_agrego_producto = true,no_agrego_cliente = true;
    private Mysql mysql;
    private boolean tiene_itbis = false;
    
    private String rnc_cliente="";
    private String estado_productot="",precio_completo_productot="0";
    
    private String ClienteID="1",UsuarioID="1",ReparacionID="1",productoID="1";
    
    private String NCF = "",notaReparacion="";
 
    private Reparacion ObjectReparacion;
    
    private String nombreUsuario = "";
    
    public static Reparacion instancia = null;

    public static Reparacion getInstancia(Mysql mysql){                
        Log.Registrar("Reparacion/getInstancia");


        if(instancia == null){
            instancia = new Reparacion(mysql);
        }
        return instancia;
    }
    
    public void resetArrayList(){       
        Log.Registrar(this.getClass(), "resetArrayList", "");

        this.producto_id = new ArrayList<String>();
        this.sub_total_producto = new ArrayList<String>();
        this.codigo_producto = new ArrayList<String>();
        this.nombre_producto = new ArrayList<String>();
        this.precio_producto = new ArrayList<String>();
        this.cantidad_producto = new ArrayList<String>();
        this.nota_producto = new ArrayList<String>();
        this.estado_producto = new ArrayList<String>();
        this.precio_completo_producto = new ArrayList<String>();
    }
    
    public void setDatosUsuario(String nombre,String id){
        this.nombreUsuario = nombre;
        this.UsuarioID = id;
        System.out.println(nombre+" "+id);
    }
    
    public Reparacion(Mysql mysql) {
        initComponents();
        //this.limpiarProductoTexto();
        this.limpiarTodoTexto();
        this.mysql =mysql;
//        this.jTDeuda.setVisible(false);
        this.jTReparacion.setVisible(false);
  //      this.jTCotizacion.setVisible(false);
        //this.jButton2.setVisible(false);
        this.setLocationRelativeTo(null);
        this.JBReImprimir.setVisible(false);
        //this.nuevoConduce();
        JScrollPane[] listScroll = {this.jScrollPane1,this.jScrollPane2,this.jScrollPane3 ,this.jScrollPane5};
        ClassStatic.ConfTable.setBackGroundColor(listScroll );
    }
    
    public void setObjectReparacion(Reparacion c){
        this.ObjectReparacion = c;
    }
    public void setDatosCliente(String ClienteID,String nombre,String cedula,String telefono,String email){
            this.ClienteID = ClienteID;
            this.t_codigo_cliente.setText(this.ClienteID);
            this.t_nombre_cliente.setText(nombre);
            this.t_cedula_cliente.setText(cedula);
            this.t_telefono_cliente.setText(telefono);
            this.t_email_cliente.setText(email);
            this.no_agrego_cliente = false;
            //this.optenerCotizacionPendienteFacturarDelCliente();
            this.optenerReparacionesPendienteFacturarDelCliente();
            //this.optenerDeudasPendienteFacturarDelCliente();
    }
    public void recargarDatos(){        
        Log.Registrar(this.getClass(), "recargarDatos", "");

        this.optenerReparacionesPendienteFacturarDelCliente();
        cargarTablaParaFacturar(this.ReparacionID,"reparacion");
        
    }
    public void setRNCCliente(String rnc){
        this.rnc_cliente = rnc;
    }
    public void setDatosProducto(String productoID,String nombre,String precio,String cantidad){
            this.productoID = productoID;
            this.t_codigo_producto.setText(productoID);
            this.t_nombre_producto.setText(nombre);
            this.t_precio_producto.setText(precio);
            this.t_cantidad_producto.setText(cantidad);
            
    }
    public void asignarProductoTexto(){             
        Log.Registrar(this.getClass(), "asignarProductoTexto", "");

        n_producto = this.t_nombre_producto.getText();
        p_producto = this.t_precio_producto.getText();
        c_producto = this.t_cantidad_producto.getText();
        co_producto = this.t_codigo_producto.getText();
        this.no_producto = this.jTANotaReparacion.getText();
    }
    public void limpiarProductoTexto(){        
        Log.Registrar(this.getClass(), "limpiarProductoTexto", "");

        /*
        this.t_nombre_producto.setText(Texto.nombre_producto);
        this.t_precio_producto.setText(Texto.precio_producto);
        this.t_cantidad_producto.setText(Texto.cantidad_producto);
        this.t_codigo_producto.setText(Texto.codigo_producto);
        this.jTANotaReparacion.setText(Texto.nota_producto);
        */
        this.t_nombre_producto.setText("");
        this.t_precio_producto.setText("");
        this.t_cantidad_producto.setText("");
        this.t_codigo_producto.setText("");
        this.jTANotaReparacion.setText("");
        

        //this.t_nombre_cliente.setText(Texto.nombre_cliente);
        //this.t_cedula_cliente.setText(Texto.cedula_cliente);
        //this.t_codigo_cliente.setText(Texto.codigo_cliente);
        //this.t_telefono_cliente.setText(Texto.telefono_cliente);
        //this.t_email_cliente.setText(Texto.email_cliente);

    }
    public void limpiarTodoTexto(){       
        Log.Registrar(this.getClass(), "limpiarTodoTexto", "");

        this.t_nombre_producto.setText("");
        this.t_precio_producto.setText("");
        this.t_cantidad_producto.setText("");
        this.t_codigo_producto.setText("");
        
        this.t_nombre_cliente.setText("");
        this.t_cedula_cliente.setText("");
        this.t_codigo_cliente.setText("");
        this.t_telefono_cliente.setText("");
        this.t_email_cliente.setText("");

    }
    public void optenerReparacionesPendienteFacturarDelCliente(){       
        Log.Registrar(this.getClass(), "optenerReparacionesPendienteFacturarDelCliente", "");

            
            String[] titulos = {"CODIGO REPARACION","ESTADO REPARACION","MONTO A PAGAR","CANTIDAD REPARACION","FECHA REPARACION"};
            String table_name = " reparacion as c";
            String campos = "c.id,c.sub_total,c.total,c.fecha_creada,c.cliente_id,c.estado, (SELECT count(c1.id) FROM reparacion_detalle as c1 WHERE c1.reparacion_id = c.id) as cantidad ";
            String otros = " where (c.cliente_id = '"+this.ClienteID+"') ";
            //SELECT c.id,c.sub_total,c.fecha_creada,c.cliente_id,c.estado, (SELECT count(c1.id) FROM reparacion_detalle as c1 WHERE c1.reparacion_id = c.id) as cantidad FROM reparacion as c

           java.sql.ResultSet resultSet = this.mysql.optenerDatosParaTabla(table_name, campos, otros);
        try {
            resultSet.beforeFirst();
            resultSet.last();
            int totalFila  = resultSet.getRow();
            Object[][] fila = new Object[totalFila][5];
            String codigo,estado,fecha,monto,cantidad;
            resultSet.beforeFirst();
            int c = 0;
            while(resultSet.next()){

                     codigo = Texto.validarNull(resultSet.getString("id"));
                     estado = Texto.validarNull(resultSet.getString("estado"));
                     monto = Texto.validarNull(resultSet.getString("total"));
                     fecha =Texto.validarNull(resultSet.getString("fecha_creada"));
                     cantidad =Texto.validarNull(resultSet.getString("cantidad"));

                     fila[c][0] = codigo;
                     fila[c][1] = estado;
                     fila[c][2] = monto;
                     fila[c][3] = cantidad;
                     fila[c][4] = fecha;
                     c++;
                     
            } 
             DefaultTableModel modelo = new DefaultTableModel(fila,titulos);
             this.jTReparacion.setModel(modelo);
             this.jTReparacion.setVisible(true);    
        } catch (SQLException ex) {
            Log.Error(this.getClass().getName(), ex);
            Logger.getLogger(ListadoProducto.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    /*public void optenerReparacionesPendienteFacturarDelCliente(){
            
            String[] titulos = {"CODIGO REPARACION","ESTADO REPARACION","MONTO A PAGAR","CANTIDAD REPARACION","FECHA REPARACION"};
            String table_name = " reparacion as c";
            String campos = "c.id,c.sub_total,c.total,c.fecha_creada,c.cliente_id,c.estado, (SELECT count(c1.id) FROM reparacion_detalle as c1 WHERE c1.reparacion_id = c.id) as cantidad ";
            String otros = " where (c.cliente_id = '"+this.ClienteID+"') ";
            //SELECT c.id,c.sub_total,c.fecha_creada,c.cliente_id,c.estado, (SELECT count(c1.id) FROM reparacion_detalle as c1 WHERE c1.reparacion_id = c.id) as cantidad FROM reparacion as c

           java.sql.ResultSet resultSet = this.mysql.optenerDatosParaTabla(table_name, campos, otros);
        try {
            resultSet.beforeFirst();
            resultSet.last();
            int totalFila  = resultSet.getRow();
            Object[][] fila = new Object[totalFila][5];
            String codigo,estado,fecha,monto,cantidad;
            resultSet.beforeFirst();
            int c = 0;
            while(resultSet.next()){

                     codigo = Texto.validarNull(resultSet.getString("id"));
                     estado = Texto.validarNull(resultSet.getString("estado"));
                     monto = Texto.validarNull(resultSet.getString("total"));
                     fecha =Texto.validarNull(resultSet.getString("fecha_creada"));
                     cantidad =Texto.validarNull(resultSet.getString("cantidad"));

                     fila[c][0] = codigo;
                     fila[c][1] = estado;
                     fila[c][2] = monto;
                     fila[c][3] = cantidad;
                     fila[c][4] = fecha;
                     c++;
                     
            } 
             DefaultTableModel modelo = new DefaultTableModel(fila,titulos);
             this.jTReparacion.setModel(modelo);
             this.jTReparacion.setVisible(true);    
        } catch (SQLException ex) {
            Logger.getLogger(ListadoProducto.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    */
     public void filtrarConduce(){        
         Log.Registrar(this.getClass(), "filtrarConduce", "");

      /*      
            String[] titulos = {"CODIGO COTIZACION","ESTADO COTIZACION","MONTO A PAGAR COTIZACION","CANTIDAD COTIZACION","FECHA COTIZACION"};
            String table_name = " cotizacion as c ";
            String campos = "c.id,c.sub_total,c.fecha_creada,c.cliente_id,c.estado, (SELECT count(c1.id) FROM cotizacion_detalle as c1 WHERE c1.cotizacion_id = c.id) as cantidad ";
            String otros = " where c.id = '"+this.JTFFiltroCodigoConduce.getText()+"' ";
            //SELECT c.id,c.sub_total,c.fecha_creada,c.cliente_id,c.estado, (SELECT count(c1.id) FROM cotizacion_detalle as c1 WHERE c1.cotizacion_id = c.id) as cantidad FROM cotizacion as c
           java.sql.ResultSet resultSet = this.mysql.optenerDatosParaTabla(table_name, campos, otros);
        try {
            resultSet.beforeFirst();
            resultSet.last();
            int totalFila  = resultSet.getRow();
            Object[][] fila = new Object[totalFila][5];
            String codigo,estado,fecha,monto,cantidad;
            resultSet.beforeFirst();
            int c = 0;
            while(resultSet.next()){

                     codigo = Texto.validarNull(resultSet.getString("id"));
                     estado = Texto.validarNull(resultSet.getString("sub_total"));
                     monto = Texto.validarNull(resultSet.getString("estado"));
                     fecha =Texto.validarNull(resultSet.getString("fecha_creada"));                     
                     cantidad =Texto.validarNull(resultSet.getString("cantidad"));

                     fila[c][0] = codigo;
                     fila[c][1] = estado;
                     fila[c][2] = monto;
                     fila[c][3] = cantidad;
                     fila[c][4] = fecha;
                     c++;
                     
            } 
             DefaultTableModel modelo = new DefaultTableModel(fila,titulos);
             //DefaultTableModel modelo = new DefaultTableModel(fila,titulos);
             this.jTReparacion.setModel(modelo);
             this.jTReparacion.setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(ListadoProducto.class.getName()).log(Level.SEVERE, null, ex);
        } 
    */
          String[] titulos = {"CODIGO REPARACION","CLIENTE","TELEFONO","ESTADO REPARACION","MONTO A PAGAR"/*,"CANTIDAD REPARACION"*/};
            String table_name = " reparacion as c"
                    + " inner join \n"
                    + " cliente as r  on c.cliente_id = r.id \n"
                    + " inner join \n"
                    + " persona as p on r.persona_id = p.id \n";
            String campos = " "
                    + "concat(p.nombre,' ',p.apellido) as nombre_completo,"
                    + "c.id,c.sub_total,c.total,c.fecha_creada,c.cliente_id,c.estado,"
                    + " (SELECT count(c1.id) FROM reparacion_detalle as c1 WHERE c1.reparacion_id = c.id) as cantidad, "
                    + " (SELECT telephone.telephone from persona inner join telephone on persona.id = telephone.persona_id  where persona.id =r.persona_id  LIMIT 1) as telefono ";
            String otros = " where c.id = '"+this.JTFFiltroCodigoConduce.getText()+"' ";
            //SELECT c.id,c.sub_total,c.fecha_creada,c.cliente_id,c.estado, (SELECT count(c1.id) FROM reparacion_detalle as c1 WHERE c1.reparacion_id = c.id) as cantidad FROM reparacion as c

           java.sql.ResultSet resultSet = this.mysql.optenerDatosParaTabla(table_name, campos, otros);
        try {
            resultSet.beforeFirst();
            resultSet.last();
            int totalFila  = resultSet.getRow();
            Object[][] fila = new Object[totalFila][6];
            String codigo,estado,fecha,monto,cantidad,cliente;
            resultSet.beforeFirst();
            int c = 0;
            while(resultSet.next()){

                     cliente = Texto.validarNull(resultSet.getString("nombre_completo"));
                     codigo = Texto.validarNull(resultSet.getString("id"));
                     estado = Texto.validarNull(resultSet.getString("estado"));
                     monto = Texto.validarNull(resultSet.getString("total"));
                     fecha =Texto.validarNull(resultSet.getString("telefono"));
                     //cantidad =Texto.validarNull(resultSet.getString("cantidad"));
                     
                     fila[c][1] = cliente;
                     fila[c][0] = codigo;
                     fila[c][3] = estado;
                     fila[c][4] = monto;
                     //fila[c][4] = cantidad;
                     fila[c][2] = fecha;
                     c++;
                     
            } 
             DefaultTableModel modelo = new DefaultTableModel(fila,titulos);
             this.jTReparacion.setModel(modelo);
             this.jTReparacion.setVisible(true);    
        } catch (SQLException ex) {
            Log.Error(this.getClass().getName(), ex);
            Logger.getLogger(ListadoProducto.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    
    }
     
     public void cargarTablaParaFacturar(String id,String accion){       
         Log.Registrar(this.getClass(), "cargarTablaParaFacturar", "");

          this.no_agrego_producto = false;
          this.resetArrayList();
          this.resetTotatel();
          this.ReparacionID = "1";
            java.sql.ResultSet resultSet = null;
            this.deuda = false; 
            if(accion.equals("deuda")){
                String table_name = "  factura_detalle as c ";
                String campos = "c.id,c.nombre,c.fecha_creada,c.precio,c.cantidad, c.total,c.factura_id,c.cliente_id";
                String otros = " where c.factura_id = '"+id+"'";
                resultSet = this.mysql.optenerDatosParaTabla(table_name, campos, otros);
                this.deuda = true;
                this.factura_generada = true;
            }else if(accion.equals("cotizacion")){
                String table_name = "  cotizacion_detalle as c ";
                String campos = "c.id,c.nombre,c.fecha_creada,c.precio,c.cantidad, c.total,c.cotizacion_id,c.cliente_id";
                String otros = " where c.cotizacion_id = '"+id+"'";
                resultSet = this.mysql.optenerDatosParaTabla(table_name, campos, otros);
            }else if(accion.equals("reparacion")){
                /*
                SELECT c.nota,c.estado,c.precio_completado,c.precio , c.id,c.nombre,c.fecha_creada,c.cantidad, c.total,c.precio_completado,
c.reparacion_id,
if(c.precio_completado is null or c.precio_completado = 0.00 , c.precio, c.precio_completado ) as precio1 
,r.cliente_id
,p.nombre,p.apellido,p.cedula,p.rnc,
(SELECT telephone.telephone from telephone where telephone.persona_id = p.id LIMIT 1) as telefono

FROM   reparacion_detalle as c
inner join reparacion as r on c.reparacion_id = r.id
inner join cliente on cliente.id = r.cliente_id
inner join persona as p on cliente.persona_id = p.id
where c.reparacion_id = '1'
                */
                String table_name = "    reparacion_detalle as c\n" +
                                " inner join reparacion as r on c.reparacion_id = r.id\n" +
                                " inner join cliente on cliente.id = r.cliente_id\n" +
                                " inner join persona as p on cliente.persona_id = p.id";
                String campos = "c.nota,c.estado,c.precio_completado,c.precio , c.id,c.nombre,c.fecha_creada,c.cantidad, c.total,c.precio_completado,c.reparacion_id,"
                        + "if(c.precio_completado is null or c.precio_completado = 0.00 , c.precio, c.precio_completado ) as precio1"
                        + ",r.cliente_id\n" +
",p.nombre as nombre_cliente,p.apellido as apellido_cliente,p.cedula,p.rnc,\n" +
"(SELECT telephone.telephone from telephone where telephone.persona_id = p.id LIMIT 1) as telefono" ;
                
                String otros = " where c.reparacion_id = '"+id+"'";
                //SELECT if(precio_completado is null or precio_completado = 0.00 , precio, precio_completado ) as precio FROM `reparacion_detalle`
                resultSet = this.mysql.optenerDatosParaTabla(table_name, campos, otros);
                this.deuda = true;
                this.factura_generada = true;
                
                this.SeguimientoConduce = true;
                this.ReparacionID = id;
                this.JBReImprimir.setVisible(true);
            }
        try {
            if(resultSet != null){
                while(resultSet.next()){
                         this.productoID = Texto.validarNull(resultSet.getString("id"));
                         this.c_producto = Texto.validarNull(resultSet.getString("cantidad"));
                         this.n_producto = Texto.validarNull(resultSet.getString("nombre"));
                         this.p_producto =Texto.validarNull(resultSet.getString("precio"));                     
                         this.co_producto =Texto.validarNull(resultSet.getString("id"));
                         this.no_producto =Texto.validarNull(resultSet.getString("nota"));
                         this.estado_productot =Texto.validarNull(resultSet.getString("estado"));
                         this.precio_completo_productot =(Texto.validarNull(resultSet.getString("precio_completado")).isEmpty())?"0":resultSet.getString("precio_completado");
                         
                         if(this.deuda){
                             this.ReparacionID = Texto.validarNull(resultSet.getString("reparacion_id"));
                         }
                         if (accion.equals("reparacion")) {
                            
                             this.setDatosCliente(
                                     resultSet.getString("cliente_id"),
                                     resultSet.getString("nombre_cliente"),
                                     resultSet.getString("cedula"),
                                     resultSet.getString("telefono"),
                                     ""
                             );
                         }
                         this.asignarAArrayList();
                } 
            }
        } catch (SQLException ex) {
            Log.Error(this.getClass().getName(), ex);
            Logger.getLogger(ListadoProducto.class.getName()).log(Level.SEVERE, null, ex);
        }  
        this.cargarJTable();
     }
    public void optenerDeudasPendienteFacturarDelCliente(){       
        Log.Registrar(this.getClass(), "optenerDeudasPendienteFacturarDelCliente", "");

            
            String[] titulos = {"CODIGO FACTURA","ESTADO FACTURA","MONTO FACTURA","MONTO PAGADO FACTURA","FECHA FACTURA"};
            String table_name = "  factura as c ";
            String campos = "c.id,c.total,c.fecha_creada,c.cliente_id,c.estado, (SELECT SUM(c1.monto_pagado) FROM pagos as c1 WHERE c1.factura_id = c.id) as monto_pagado ";
            String otros = " where c.cliente_id = '"+this.ClienteID+"' and c.estado = 'pendiente' ";
            //SELECT c.id,c.total,c.fecha_creada,c.cliente_id,c.estado, (SELECT SUM(c1.monto_pagado) FROM pagos as c1 WHERE c1.factura_id = c.id) as monto_pagado FROM factura as c
            java.sql.ResultSet resultSet = this.mysql.optenerDatosParaTabla(table_name, campos, otros);
        try {
            resultSet.beforeFirst();
            resultSet.last();
            int totalFila  = resultSet.getRow();
            Object[][] fila = new Object[totalFila][5];
            String codigo,estado,fecha,monto,cantidad;
            resultSet.beforeFirst();
            int c = 0;
            while(resultSet.next()){

                     codigo = Texto.validarNull(resultSet.getString("id"));
                     estado = Texto.validarNull(resultSet.getString("total"));
                     monto = Texto.validarNull(resultSet.getString("estado"));
                     fecha =Texto.validarNull(resultSet.getString("fecha_creada"));                     
                     cantidad =Texto.validarNull(resultSet.getString("monto_pagado"));

                     fila[c][0] = codigo;
                     fila[c][2] = estado;
                     fila[c][1] = monto;
                     fila[c][3] = cantidad;
                     fila[c][4] = fecha;
                     c++;
                     
            } 
             DefaultTableModel modelo = new DefaultTableModel(fila,titulos);
//             this.jTDeuda.setModel(modelo);
  //           this.jTDeuda.setVisible(true);
        } catch (SQLException ex) {
            Log.Error(this.getClass().getName(), ex);
            Logger.getLogger(ListadoProducto.class.getName()).log(Level.SEVERE, null, ex);
        } 
    
    
    
    }
    public void insertarDBDetalleReparacion(String id,String nombre,String precio,String cantidad,String total,String nota){
        Log.Registrar(this.getClass(), "insertarDBDetalleReparacion", "");
            if(!this.existe_producto){
             String campos = "usuario_id,nombre,precio,cantidad,total,fecha_creada,cliente_id,reparacion_id,producto_inventariado_id,nota";
             String valores = "'"+this.UsuarioID+"','"+nombre+"','"+precio+"','"+cantidad+"','"+total+"',now(),'"+this.ClienteID+"','"+this.ReparacionID+"','"+id+"','"+nota+"'";
             this.mysql.insertData("reparacion_detalle", campos, valores);
            }
    }
    public void insertarDBReparacion(String sub_total,String nota,double abono){
        Log.Registrar(this.getClass(), "insertarDBReparacion", "");
             String campos = "usuario_id,sub_total,fecha_creada,cliente_id,nota,abono";
             String valores = "'"+this.UsuarioID+"','"+sub_total+"',now(),'"+this.ClienteID+"','"+nota+"','"+abono+"' ";
             this.mysql.insertData("reparacion", campos, valores);
             this.ReparacionID = this.mysql.optenerUltimoID("reparacion");
    }
    public void completarReparacion(){         
        Log.Registrar(this.getClass(), "cargarTablaParaFacturar", "");

        boolean respuesta = this.mysql.actulizarDatos("reparacion","estado = 'completo' ", " id = '"+this.ReparacionID+"' ");
        if(respuesta){
            JOptionPane.showMessageDialog(null, "La reparación paso al estado de completado ","Reparacioón Completada",JOptionPane.INFORMATION_MESSAGE);
        }
    }
    public void crearReparacion(){       
        Log.Registrar(this.getClass(), "crearReparacion", "");

           if(!this.cotizado && (!this.deuda) ){
            /*if(this.ultimoNumeroGeneredo <= 0){
                this.insertarDBReparacion(this.subMontoTotal+"", this.notaReparacion,this.abonoTotal);
            }*/
            this.insertarDBReparacion(this.subMontoTotal+"", this.notaReparacion,this.abonoTotal);
            int lineas = this.nombre_producto.size();
            String nombre,precio,cantidad,total,id,nota;
            for(int c = this.ultimoNumeroGeneredo ; c < lineas ; c++){
                     nombre = this.nombre_producto.get(c);
                     precio = this.precio_producto.get(c);
                     cantidad = this.cantidad_producto.get(c);
                     total = this.sub_total_producto.get(c);
                     nota = this.nota_producto.get(c);
                    id = this.producto_id.get(c);
                    this.insertarDBDetalleReparacion(id,nombre, precio, cantidad, total,nota);
            }
            this.cotizado = true;
           }
           if(this.mostrarPDF){
           this.generarPDF();
           }
    }
    
    public void reCalcular(){       
        Log.Registrar(this.getClass(), "reCalcular", "");

           int lineas = this.nombre_producto.size();
           double  monto = 0;
            String nombre,precio,cantidad,total,id;
            for(int c = 0 ; c < lineas ; c++){
                     nombre = this.nombre_producto.get(c);
                     precio = this.precio_producto.get(c);
                     cantidad = this.cantidad_producto.get(c);
                     total = this.sub_total_producto.get(c);
                     id = this.producto_id.get(c);
                    monto += Double.parseDouble(total);
            }
            this.totales(monto);
    }
    public void asignarAArrayList(){
                Log.Registrar(this.getClass(), "asignarAArrayList", "");

        this.producto_id.add(this.productoID);
        this.cantidad_producto.add(this.c_producto);
        this.nombre_producto.add(this.n_producto+"");
        this.precio_producto.add(""+this.p_producto);
        this.codigo_producto.add(this.co_producto);
        this.nota_producto.add(this.no_producto);
        this.estado_producto.add(this.estado_productot);
        this.precio_completo_producto.add(this.precio_completo_productot);
        double auxPrecio = (Double.parseDouble(this.precio_completo_productot) != 0)? Double.parseDouble(this.precio_completo_productot) : Double.parseDouble(p_producto);
        double monto =(Double.parseDouble(c_producto) * auxPrecio );
        this.sub_total_producto.add( ""+monto );
        this.totales(monto);
    }
    public void agregarTabla(){      
        Log.Registrar(this.getClass(), "agregarTabla", "");

        this.validarAgregarEntradaProducto();
        boolean r = this.validadVacio();
        if(r){
            this.no_agrego_producto = false;
            this.validarAgregarEntradaProducto();
            this.asignarAArrayList();
            this.cargarJTable();
            this.limpiarProductoTexto();
        }
    }
    public void validarAgregarEntradaProducto(){
        /*Texto.placeholder(Texto.codigo_producto,this.t_codigo_producto.getText(), this.t_codigo_producto);
        Texto.placeholder(Texto.nombre_producto,this.t_nombre_producto.getText(), this.t_nombre_producto);
        Texto.placeholder(Texto.precio_producto,this.t_precio_producto.getText(), this.t_precio_producto);
        Texto.placeholder(Texto.cantidad_producto,this.t_cantidad_producto.getText(), this.t_cantidad_producto);
        Texto.placeholder(Texto.nota_producto,this.jTANotaReparacion.getText(), this.jTANotaReparacion);
        */
    }
   public void tieneITBIS(){
        /*if(this.JCBTieneITBIS.isSelected()){
            this.tiene_itbis = true;
        }*/
    }
    public void totales(double monto){      
        Log.Registrar(this.getClass(), "totales", "");

        double itbis = 0,monto_total = 0;
        this.sub_total = ""+monto;
        this.tieneITBIS();
        if(this.tiene_itbis){
            itbis = monto * 0.18; 
        }
        monto_total = monto + itbis; 
        this.montoTotal =monto_total;
        this.itbisTotal = itbis;
        this.subMontoTotal = monto;
        this.itbis_total = ""+itbis;
        this.monto_total = ""+monto_total;
        this.cotizacion_itbis_total.setText("$ "+this.itbisTotal);
        this.cotizacion_sub_total.setText("$ "+this.subMontoTotal);
        this.cotizacion_monto_total.setText("$ "+this.montoTotal);
    }
    public void resetTotatel(){       
        Log.Registrar(this.getClass(), "resetTotatel", "");

         this.montoTotal = 0.00;
        this.itbisTotal = 0.00;
        this.subMontoTotal = 0.00;
    }
    public void cargarJTable(){        
        Log.Registrar(this.getClass(), "cargarJTable", "");

            double monto =0.00;
           String[] titulos = {"CODIGO","PRODUCTO","CANTIDAD","PRECIO","SUB TOTAL","PRECIO COMPLETADO","ESTADO","NOTA"};
           int lineas = this.nombre_producto.size();
           //JOptionPane.showMessageDialog(null, "total "+lineas);
           String nombre,precio,cantidad,total,id,nota,precioCompleto,estado;
           
           this.productos  = new LinkedList();
           
           Object[][] fila = new Object[lineas][8];
           
           for(int c = 0 ; c < lineas ; c++){
                    id = this.codigo_producto.get(c);
                    nombre = this.nombre_producto.get(c);
                    precio = this.precio_producto.get(c);
                    cantidad = this.cantidad_producto.get(c);
                    total = this.sub_total_producto.get(c);
                    nota = this.nota_producto.get(c);
                    estado = this.estado_producto.get(c);
                    precioCompleto = this.precio_completo_producto.get(c);
                    fila[c][0] = id;
                    fila[c][1] = nombre;
                    fila[c][2] = cantidad;
                    fila[c][3] = precio;
                    fila[c][4] = total;
                    
                    fila[c][5] = precioCompleto;
                    fila[c][6] = estado;
                    fila[c][7] = nota;
                     monto += Double.parseDouble(total);
                    this.productos.add( new producto(nombre,precio,cantidad,total) );
           }
          this.totales(monto);
          DefaultTableModel modelo = new DefaultTableModel(fila,titulos);
          this.jTableR.setModel(modelo);
    }
    public boolean validadVacio(){        
        Log.Registrar(this.getClass(), "validadVacio", "");

        boolean respuesta = true;
        this.asignarProductoTexto();
        if(this.n_producto.isEmpty()){
            JOptionPane.showMessageDialog(null, "El campo nombre del producto esta vacío","Campos Vacíos",JOptionPane.WARNING_MESSAGE);
            respuesta = false;
        }else if(this.p_producto.isEmpty()){
            JOptionPane.showMessageDialog(null,"El campo precio del producto esta vacío","Campos Vacíos",JOptionPane.WARNING_MESSAGE);
            respuesta = false;
        }else if(this.c_producto.isEmpty()){
            JOptionPane.showMessageDialog(null,"El campo cantidad del producto esta vacío","Campos Vacíos",JOptionPane.WARNING_MESSAGE);
            respuesta = false;
        }else{
            respuesta = true;
        }
        return respuesta;
    }
    public String obtenerFechaActual(){        
        Log.Registrar(this.getClass(), "obtenerFechaActual", "");

       Calendar c1 = Calendar.getInstance();
       int mesActual = c1.get(Calendar.MONTH)+1;
       //JOptionPane.showMessageDialog(null, ""+c1.get(Calendar.DATE)+"-"+mesActual+"-"+Integer.toString(c1.get(Calendar.YEAR)));
       String fechaAct =Integer.toString(c1.get(Calendar.DATE))+"-"+mesActual+"-"+Integer.toString(c1.get(Calendar.YEAR));
       //JOptionPane.showMessageDialog(null, ""+fechaAct);
       return fechaAct;
   }
    public void generarPDF(){        
        Log.Registrar(this.getClass(), "generarPDF", "");

        try {
            URL ruta = new URL("file:///C:/app_vyr/theme/reparacion_matricial.jasper");
            JasperReport loadObject = (JasperReport) JRLoader.loadObject(ruta); 
           //JasperReport loadObject = (JasperReport) JRLoader.loadObject(getClass().getResource("../theme/Reparacion.jasper")/*"C:/vyr/theme/Reparacion.jasper"*/);
            Map parameters = new HashMap<String, Object>();
            JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(productos); 
            //System.out.println(getClass().getResource("../icono/iconoclinica.gif"));
            parameters.put("logo_empresa", Empresa.logo);
            parameters.put("nombre_empresa", Empresa.nombre);
            parameters.put("eslogan_empresa", Empresa.eslogan);

            parameters.put("ncf_empresa", this.NCF);
            parameters.put("rnc_empresa", Empresa.rnc);
            parameters.put("telefono_empresa", Empresa.telefono);
            parameters.put("celular_empresa", Empresa.celular);
            parameters.put("email_empresa", Empresa.email);

            parameters.put("no_factura", this.ReparacionID);
            parameters.put("fecha", this.obtenerFechaActual());
                        
            parameters.put("rnc_cliente", this.rnc_cliente);
            parameters.put("numero_cliente", this.ClienteID);
            parameters.put("nombre_cliente", this.t_nombre_cliente.getText());
            parameters.put("telefono_cliente", this.t_telefono_cliente.getText());
            
            /*parameters.put("no_factura", this.idFactura);*/
            parameters.put("sub_total","$ "+this.subMontoTotal);
            parameters.put("monto_total","$ "+this.montoTotal);
            parameters.put("itbis", "$ "+this.itbisTotal);
            parameters.put("nota", this.notaReparacion);

            JasperPrint jp = JasperFillManager.fillReport(loadObject, parameters, ds);           
            JasperViewer jv = new JasperViewer(jp,false);
            jv.setDefaultCloseOperation(JasperViewer.DISPOSE_ON_CLOSE);
            jv.setVisible(true);
                    
        } catch (JRException ex) {
            Log.Error(this.getClass().getName(), ex);
            Logger.getLogger(Reparacion.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Log.Error(this.getClass().getName(), ex);
            Logger.getLogger(Reparacion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void administrar_focus_clientes(String menu,String tabla){                  
        Log.Registrar(this.getClass(), "administrar_focus_clientes", "");

        ListadoProducto selecciona = new ListadoProducto(this.mysql,tabla);
         selecciona.setObjectReparacion(this.ObjectReparacion);
         selecciona.setDatosUsuario(this.nombreUsuario, this.UsuarioID, "Listado De Cliente");
         selecciona.validarReparacion();
        
         selecciona.setSeleccionLista(selecciona);
        //JOptionPane.showMessageDialog(null, menu+" "+tabla);
         switch(menu){
            case "codigo_cliente":
                //Texto.placeholder(Texto.codigo_cliente,this.t_codigo_cliente.getText(), this.t_codigo_cliente);
                selecciona.setTextBox(t_codigo_cliente);
                selecciona.setPalabra("codigo");
                
            break;
            case "cedula_cliente":
                //Texto.placeholder(Texto.cedula_cliente,this.t_cedula_cliente.getText(), this.t_cedula_cliente);
                selecciona.setTextBox(t_cedula_cliente);
                selecciona.setPalabra("cedula");
            break;
            case "nombre_cliente":
                //Texto.placeholder(Texto.nombre_cliente,this.t_nombre_cliente.getText(), this.t_nombre_cliente);
                selecciona.setTextBox(t_nombre_cliente);
                selecciona.setPalabra("nombre");
            break;
            case "telefono_cliente":
                //Texto.placeholder(Texto.telefono_cliente,this.t_telefono_cliente.getText(), this.t_telefono_cliente);
                selecciona.setTextBox(t_telefono_cliente);
                selecciona.setPalabra("telefono");
                
            break;
            case "email_cliente":
                //Texto.placeholder(Texto.email_cliente,this.t_email_cliente.getText(), this.t_email_cliente);
                selecciona.setTextBox(t_email_cliente);
                selecciona.setPalabra("email");
            break;
            
            case "codigo_producto":
                
                //Texto.placeholder(Texto.codigo_producto,this.t_codigo_producto.getText(), this.t_codigo_producto);
                selecciona.setTextBox(t_codigo_producto);
                selecciona.setPalabra("codigo");
                selecciona.setDatosUsuario(this.nombreUsuario, this.UsuarioID, "Listado De Producto");
            break;
            case "nombre_producto":
                //Texto.placeholder(Texto.nombre_producto,this.t_nombre_producto.getText(), this.t_nombre_producto);
                //Texto.setPlaceholder(Texto.nombre_producto,this.t_nombre_producto.getText(), this.t_nombre_producto);
                selecciona.setTextBox(t_nombre_producto);
                selecciona.setPalabra("nombre");
                selecciona.setDatosUsuario(this.nombreUsuario, this.UsuarioID, "Listado De Producto");
            break;
        }
        
        
        selecciona.setVisible(true);
    }
    public boolean validarQueTengaUnoSeleccionado(){
                Log.Registrar(this.getClass(), "validarQueTengaUnoSeleccionado", "");

        if(this.no_agrego_cliente)
        {
            JOptionPane.showMessageDialog(null, "Debe de agregar un cliente primero", "Falta Agregar Un Cliente", JOptionPane.WARNING_MESSAGE);
            return false;
        }else if(this.no_agrego_producto)
        {
            JOptionPane.showMessageDialog(null, "Debe de agregar un producto primero", "Falta Agregar Un Producto", JOptionPane.WARNING_MESSAGE);
            return false;
        }else{
            return true;
        }
        
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        RegistrarPaso = new javax.swing.JMenuItem();
        Historial = new javax.swing.JMenuItem();
        jMIEliminar = new javax.swing.JMenuItem();
        Fondo = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        t_codigo_cliente = new javax.swing.JTextField();
        t_cedula_cliente = new javax.swing.JTextField();
        t_nombre_cliente = new javax.swing.JTextField();
        t_telefono_cliente = new javax.swing.JTextField();
        t_email_cliente = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        JPProducto = new javax.swing.JPanel();
        t_codigo_producto = new javax.swing.JTextField();
        t_nombre_producto = new javax.swing.JTextField();
        t_precio_producto = new javax.swing.JTextField();
        t_cantidad_producto = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTANotaReparacion = new javax.swing.JTextArea();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        JPGeneral = new javax.swing.JPanel();
        JPTotal = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        cotizacion_sub_total = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        cotizacion_itbis_total = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        cotizacion_monto_total = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        JPOpcones = new javax.swing.JPanel();
        jButton6 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableR = new javax.swing.JTable();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        JPReparacion = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTReparacion = new javax.swing.JTable();
        JTFFiltroCodigoConduce = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        JBReImprimir = new javax.swing.JButton();

        RegistrarPaso.setText("Registrar Avances");
        RegistrarPaso.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                RegistrarPasoMousePressed(evt);
            }
        });
        jPopupMenu1.add(RegistrarPaso);

        Historial.setText("Detalle");
        Historial.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                HistorialMousePressed(evt);
            }
        });
        jPopupMenu1.add(Historial);

        jMIEliminar.setText("Eliminar");
        jMIEliminar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jMIEliminarMousePressed(evt);
            }
        });
        jPopupMenu1.add(jMIEliminar);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Conduce");
        setBackground(new java.awt.Color(255, 255, 255));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        Fondo.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        t_codigo_cliente.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                t_codigo_clienteFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                t_codigo_clienteFocusLost(evt);
            }
        });

        t_cedula_cliente.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                t_cedula_clienteFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                t_cedula_clienteFocusLost(evt);
            }
        });

        t_nombre_cliente.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                t_nombre_clienteFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                t_nombre_clienteFocusLost(evt);
            }
        });

        t_telefono_cliente.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                t_telefono_clienteFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                t_telefono_clienteFocusLost(evt);
            }
        });

        t_email_cliente.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                t_email_clienteFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                t_email_clienteFocusLost(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel12.setText("Código");

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel13.setText("Cedula");

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel14.setText("Cliente");

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel15.setText("Teléfono");

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel16.setText("Email");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(t_codigo_cliente))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(t_cedula_cliente))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(t_nombre_cliente)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(t_telefono_cliente))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(t_email_cliente)))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(jLabel13)
                    .addComponent(jLabel14)
                    .addComponent(jLabel15)
                    .addComponent(jLabel16))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(t_codigo_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(t_cedula_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(t_nombre_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(t_telefono_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(t_email_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 8, Short.MAX_VALUE))
        );

        JPProducto.setBackground(new java.awt.Color(255, 255, 255));

        t_codigo_producto.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                t_codigo_productoFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                t_codigo_productoFocusLost(evt);
            }
        });

        t_nombre_producto.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                t_nombre_productoFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                t_nombre_productoFocusLost(evt);
            }
        });

        t_precio_producto.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                t_precio_productoFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                t_precio_productoFocusLost(evt);
            }
        });

        t_cantidad_producto.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                t_cantidad_productoFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                t_cantidad_productoFocusLost(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(0, 153, 102));
        jButton1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Agregar Producto");
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });

        jTANotaReparacion.setColumns(20);
        jTANotaReparacion.setRows(5);
        jTANotaReparacion.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTANotaReparacionFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTANotaReparacionFocusLost(evt);
            }
        });
        jScrollPane5.setViewportView(jTANotaReparacion);

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel9.setText("Código");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel10.setText("Producto");

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel11.setText("Precio");

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel17.setText("Cantidad");

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel18.setText("Nota del equipo");

        javax.swing.GroupLayout JPProductoLayout = new javax.swing.GroupLayout(JPProducto);
        JPProducto.setLayout(JPProductoLayout);
        JPProductoLayout.setHorizontalGroup(
            JPProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPProductoLayout.createSequentialGroup()
                .addGroup(JPProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
                    .addComponent(t_codigo_producto))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(JPProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE)
                    .addComponent(t_nombre_producto))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(JPProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
                    .addComponent(t_precio_producto))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(JPProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(t_cantidad_producto)
                    .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(JPProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 269, Short.MAX_VALUE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5))
        );
        JPProductoLayout.setVerticalGroup(
            JPProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPProductoLayout.createSequentialGroup()
                .addGroup(JPProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JPProductoLayout.createSequentialGroup()
                        .addGroup(JPProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(jLabel9)
                            .addComponent(jLabel11)
                            .addComponent(jLabel17)
                            .addComponent(jLabel18))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(JPProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(JPProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(t_codigo_producto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(t_nombre_producto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(t_precio_producto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(t_cantidad_producto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(JPProductoLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton1)))
                .addGap(10, 11, Short.MAX_VALUE))
        );

        JPGeneral.setBackground(new java.awt.Color(255, 255, 255));

        JPTotal.setBackground(new java.awt.Color(0, 0, 204));
        JPTotal.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(217, 225, 233)));
        JPTotal.setToolTipText("");

        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Sub Total $");

        cotizacion_sub_total.setForeground(new java.awt.Color(255, 255, 255));

        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Itbis Total $");

        cotizacion_itbis_total.setForeground(new java.awt.Color(255, 255, 255));

        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Monto Total $");

        cotizacion_monto_total.setForeground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("TOTALES");

        javax.swing.GroupLayout JPTotalLayout = new javax.swing.GroupLayout(JPTotal);
        JPTotal.setLayout(JPTotalLayout);
        JPTotalLayout.setHorizontalGroup(
            JPTotalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPTotalLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(JPTotalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JPTotalLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(36, 36, 36)
                        .addComponent(cotizacion_sub_total, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(JPTotalLayout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(33, 33, 33)
                        .addComponent(cotizacion_itbis_total, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(JPTotalLayout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(24, 24, 24)
                        .addComponent(cotizacion_monto_total, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))))
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        JPTotalLayout.setVerticalGroup(
            JPTotalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPTotalLayout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(15, 15, 15)
                .addGroup(JPTotalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(cotizacion_sub_total, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(JPTotalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(cotizacion_itbis_total, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(JPTotalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(cotizacion_monto_total, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        JPOpcones.setBackground(new java.awt.Color(255, 255, 255));
        JPOpcones.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(217, 225, 233)));

        jButton6.setBackground(new java.awt.Color(0, 51, 204));
        jButton6.setForeground(new java.awt.Color(255, 255, 255));
        jButton6.setText("Guardar");
        jButton6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton6MouseClicked(evt);
            }
        });

        jButton4.setBackground(new java.awt.Color(255, 255, 0));
        jButton4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton4.setText("Abonar");
        jButton4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton4MouseClicked(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(0, 51, 204));
        jButton3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("Pasar a facturar");
        jButton3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton3MouseClicked(evt);
            }
        });

        jButton7.setBackground(new java.awt.Color(0, 153, 102));
        jButton7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton7.setForeground(new java.awt.Color(255, 255, 255));
        jButton7.setText("Ayuda");
        jButton7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton7MouseClicked(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("OPCIONES");

        javax.swing.GroupLayout JPOpconesLayout = new javax.swing.GroupLayout(JPOpcones);
        JPOpcones.setLayout(JPOpconesLayout);
        JPOpconesLayout.setHorizontalGroup(
            JPOpconesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPOpconesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(JPOpconesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(JPOpconesLayout.createSequentialGroup()
                        .addGroup(JPOpconesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, 82, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(JPOpconesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        JPOpconesLayout.setVerticalGroup(
            JPOpconesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPOpconesLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(JPOpconesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton3)
                    .addComponent(jButton6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(JPOpconesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton4)
                    .addComponent(jButton7))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setText("Nota del conduce");

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jTextArea1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextArea1KeyTyped(evt);
            }
        });
        jScrollPane3.setViewportView(jTextArea1);

        javax.swing.GroupLayout JPGeneralLayout = new javax.swing.GroupLayout(JPGeneral);
        JPGeneral.setLayout(JPGeneralLayout);
        JPGeneralLayout.setHorizontalGroup(
            JPGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPGeneralLayout.createSequentialGroup()
                .addGroup(JPGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JPGeneralLayout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JPTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JPOpcones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        JPGeneralLayout.setVerticalGroup(
            JPGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPGeneralLayout.createSequentialGroup()
                .addGroup(JPGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(JPOpcones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JPTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(JPGeneralLayout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTableR.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTableR.setComponentPopupMenu(jPopupMenu1);
        jTableR.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jScrollPane1.setViewportView(jTableR);

        jTabbedPane1.setBackground(new java.awt.Color(255, 255, 255));

        JPReparacion.setBackground(new java.awt.Color(255, 255, 255));

        jTReparacion.setAutoCreateRowSorter(true);
        jTReparacion.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTReparacion.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jTReparacion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTReparacionMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTReparacion);

        JTFFiltroCodigoConduce.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                JTFFiltroCodigoConduceKeyReleased(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setText("Buscar por código");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("CONDUCES PENDIENTES DEL CLIENTE");

        JBReImprimir.setBackground(new java.awt.Color(0, 51, 204));
        JBReImprimir.setForeground(new java.awt.Color(255, 255, 255));
        JBReImprimir.setText("Re Imprimir");
        JBReImprimir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        JBReImprimir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JBReImprimirMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout JPReparacionLayout = new javax.swing.GroupLayout(JPReparacion);
        JPReparacion.setLayout(JPReparacionLayout);
        JPReparacionLayout.setHorizontalGroup(
            JPReparacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2)
            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(JPReparacionLayout.createSequentialGroup()
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(JTFFiltroCodigoConduce, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(695, 695, 695)
                .addComponent(JBReImprimir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        JPReparacionLayout.setVerticalGroup(
            JPReparacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JPReparacionLayout.createSequentialGroup()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(JPReparacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JTFFiltroCodigoConduce, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(JBReImprimir))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("CONDUCES", JPReparacion);

        javax.swing.GroupLayout FondoLayout = new javax.swing.GroupLayout(Fondo);
        Fondo.setLayout(FondoLayout);
        FondoLayout.setHorizontalGroup(
            FondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(FondoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(FondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(JPProducto, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTabbedPane1)
                    .addComponent(JPGeneral, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        FondoLayout.setVerticalGroup(
            FondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(FondoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(JPProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JPGeneral, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Fondo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Fondo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        // TODO add your handling code here:
        this.agregarTabla();
        this.deuda = false;
    }//GEN-LAST:event_jButton1MouseClicked

    private void t_codigo_productoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_t_codigo_productoFocusGained
        // TODO add your handling code here:
        //Texto.placeholder(Texto.codigo_producto,this.t_codigo_producto.getText(), this.t_codigo_producto);
               administrar_focus_clientes("codigo_producto","producto");

    }//GEN-LAST:event_t_codigo_productoFocusGained

    private void t_codigo_productoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_t_codigo_productoFocusLost
        // TODO add your handling code here:
       // Texto.setPlaceholder(Texto.codigo_producto,this.t_codigo_producto.getText(), this.t_codigo_producto);
    }//GEN-LAST:event_t_codigo_productoFocusLost

    private void t_nombre_productoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_t_nombre_productoFocusGained
        // TODO add your handling code here:
        //Texto.placeholder(Texto.nombre_producto,this.t_nombre_producto.getText(), this.t_nombre_producto);
        administrar_focus_clientes("nombre_producto","producto");

    }//GEN-LAST:event_t_nombre_productoFocusGained

    private void t_nombre_productoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_t_nombre_productoFocusLost
        // TODO add your handling code here:
       // Texto.setPlaceholder(Texto.nombre_producto,this.t_nombre_producto.getText(), this.t_nombre_producto);
    }//GEN-LAST:event_t_nombre_productoFocusLost

    private void t_precio_productoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_t_precio_productoFocusGained
        // TODO add your handling code here:
        //Texto.placeholder(Texto.precio_producto,this.t_precio_producto.getText(), this.t_precio_producto);
    }//GEN-LAST:event_t_precio_productoFocusGained

    private void t_precio_productoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_t_precio_productoFocusLost
        // TODO add your handling code here:
        //Texto.setPlaceholder(Texto.precio_producto,this.t_precio_producto.getText(), this.t_precio_producto);
    }//GEN-LAST:event_t_precio_productoFocusLost

    private void t_cantidad_productoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_t_cantidad_productoFocusGained
        // TODO add your handling code here:
      //Texto.placeholder(Texto.cantidad_producto,this.t_cantidad_producto.getText(), this.t_cantidad_producto);
    }//GEN-LAST:event_t_cantidad_productoFocusGained

    private void t_cantidad_productoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_t_cantidad_productoFocusLost
        // TODO add your handling code here:
      //Texto.setPlaceholder(Texto.cantidad_producto,this.t_cantidad_producto.getText(), this.t_cantidad_producto);
    }//GEN-LAST:event_t_cantidad_productoFocusLost

    public void VistaPrevia(){
                        Log.Registrar(this.getClass(), "VistaPrevia", "");

        if(this.validarQueTengaUnoSeleccionado()){

                 this.generarPDF();
            }
    }
    
    private void jButton3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton3MouseClicked
        // TODO add your handling code here:
        /*if(this.validarQueTengaUnoSeleccionado()){
            this.crearReparacion();
            this.factura_generada = true;
        }*/
        if(this.validarQueTengaUnoSeleccionado()){
            this.mostrarPDF  = false;
            this.crearReparacion();
            this.factura_generada = true;
            this.mostrarPDF = true;
            this.completarReparacion();
            
            Facturacion f = new Facturacion(this.mysql);
            
            f.setDatosCliente(this.ClienteID, this.t_nombre_cliente.getText(), this.t_cedula_cliente.getText(), this.t_telefono_cliente.getText(),this.t_email_cliente.getText());
            f.cargarTablaParaFacturar(this.ReparacionID,"reparacion");
            
            JOptionPane.showMessageDialog(null, "Ha pasado a FACTURACIÓN si no desea hacer cambios, clic en facturar","Generar Factura",JOptionPane.INFORMATION_MESSAGE);
            this.dispose();
            f.setVisible(true);
      }
    }//GEN-LAST:event_jButton3MouseClicked

    private void t_codigo_clienteFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_t_codigo_clienteFocusGained
        // TODO add your handling code here:
       /* Texto.placeholder(Texto.codigo_cliente,this.t_codigo_cliente.getText(), this.t_codigo_cliente);
        ListadoProducto selecciona = new ListadoProducto(this.mysql);
        selecciona.setObjectReparacion(this.ObjectReparacion);
        selecciona.setTextBox(t_codigo_cliente);
        selecciona.setPalabra("codigo");
        selecciona.setVisible(true);*/
       administrar_focus_clientes("codigo_cliente","cliente");
    }//GEN-LAST:event_t_codigo_clienteFocusGained

    private void t_codigo_clienteFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_t_codigo_clienteFocusLost
        // TODO add your handling code here:
         Texto.setPlaceholder(Texto.codigo_cliente,this.t_codigo_cliente.getText(), this.t_codigo_cliente);
    
    }//GEN-LAST:event_t_codigo_clienteFocusLost

    private void t_cedula_clienteFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_t_cedula_clienteFocusGained
        // TODO add your handling code here:
       //Texto.placeholder(Texto.cedula_cliente,this.t_cedula_cliente.getText(), this.t_cedula_cliente);
       administrar_focus_clientes("cedula_cliente","cliente");
    }//GEN-LAST:event_t_cedula_clienteFocusGained

    private void t_cedula_clienteFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_t_cedula_clienteFocusLost
        // TODO add your handling code here:
        // Texto.setPlaceholder(Texto.cedula_cliente,this.t_cedula_cliente.getText(), this.t_cedula_cliente);
     
    }//GEN-LAST:event_t_cedula_clienteFocusLost

    private void t_nombre_clienteFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_t_nombre_clienteFocusGained
        // TODO add your handling code here:
       Texto.placeholder(Texto.nombre_cliente,this.t_nombre_cliente.getText(), this.t_nombre_cliente);
       //Texto.setPlaceholder(Texto.nombre_cliente,this.t_nombre_cliente.getText(), this.t_nombre_cliente);
       administrar_focus_clientes("nombre_cliente","cliente");
    }//GEN-LAST:event_t_nombre_clienteFocusGained

    private void t_nombre_clienteFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_t_nombre_clienteFocusLost
        // TODO add your handling code here:
        //Texto.placeholder(Texto.nombre_cliente,this.t_nombre_cliente.getText(), this.t_nombre_cliente);
      // Texto.setPlaceholder(Texto.nombre_cliente,this.t_nombre_cliente.getText(), this.t_nombre_cliente);
   
    }//GEN-LAST:event_t_nombre_clienteFocusLost

    private void t_telefono_clienteFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_t_telefono_clienteFocusGained
        // TODO add your handling code here:
       // Texto.placeholder(Texto.telefono_cliente,this.t_telefono_cliente.getText(), this.t_telefono_cliente);
       //Texto.setPlaceholder(Texto.telefono_cliente,this.t_telefono_cliente.getText(), this.t_telefono_cliente);
       administrar_focus_clientes("telefono_cliente","cliente");
    }//GEN-LAST:event_t_telefono_clienteFocusGained

    private void t_telefono_clienteFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_t_telefono_clienteFocusLost
        // TODO add your handling code here:
        //Texto.placeholder(Texto.telefono_cliente,this.t_telefono_cliente.getText(), this.t_telefono_cliente);
      // Texto.setPlaceholder(Texto.telefono_cliente,this.t_telefono_cliente.getText(), this.t_telefono_cliente);
   
    }//GEN-LAST:event_t_telefono_clienteFocusLost

    private void t_email_clienteFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_t_email_clienteFocusGained
        // TODO add your handling code here:
        //Texto.placeholder(Texto.email_cliente,this.t_email_cliente.getText(), this.t_email_cliente);
       //Texto.setPlaceholder(Texto.email_cliente,this.t_email_cliente.getText(), this.t_email_cliente);
       administrar_focus_clientes("email_cliente","cliente");
    }//GEN-LAST:event_t_email_clienteFocusGained

    private void t_email_clienteFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_t_email_clienteFocusLost
        // TODO add your handling code here:
        //Texto.placeholder(Texto.email_cliente,this.t_email_cliente.getText(), this.t_email_cliente);
       //Texto.setPlaceholder(Texto.email_cliente,this.t_email_cliente.getText(), this.t_email_cliente);
   
    }//GEN-LAST:event_t_email_clienteFocusLost

    private void jButton6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton6MouseClicked
        // TODO add your handling code here:
      // this.NCF = JOptionPane.showInputDialog(null, "Ingrese su NCF (Número de Comprobante Fiscal)", "NCF (Número de Comprobante Fiscal)", JOptionPane.INFORMATION_MESSAGE);
      if(this.validarQueTengaUnoSeleccionado()){
            this.crearReparacion();
            this.factura_generada = true;
            this.nuevoConduce();
      }
    }//GEN-LAST:event_jButton6MouseClicked

    private void jButton4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton4MouseClicked
        try{
            this.abonoTotal =  Double.parseDouble(JOptionPane.showInputDialog(null, "Ingrese su Abono", "Agregar Abono a la Reparación", JOptionPane.INFORMATION_MESSAGE));
        }catch(Exception e){
            Log.Error(this.getClass().getName(), e);
            JOptionPane.showMessageDialog(null, "Debe de ingresar solo número","Solo se perminten números", JOptionPane.WARNING_MESSAGE);
        }
        // TODO add your handling code here:
       /* if(this.factura_generada){
            Pago p = new Pago(this.mysql);
            p.setObjectReparacion(this.ObjectReparacion);
            p.setBalance(this.monto_total);
            p.setVisible(true);
        }else{
            int respuesta  = JOptionPane.showConfirmDialog(  null,  "Debe generar la factura antes de pagarla, Desea generarla ahora?","Advertencia ",JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            if(respuesta == JOptionPane.YES_OPTION){
               // JOptionPane.showMessageDialog(null, "null");
                if(this.validarQueTengaUnoSeleccionado()){
                        this.crearReparacion();
                        this.factura_generada = true;
                }
                if(this.factura_generada){
                        Pago p = new Pago(this.mysql);
                        p.setObjectReparacion(this.ObjectReparacion);
                        p.setBalance(this.monto_total);
                        p.setVisible(true);
                }
            }
        }*/
    }//GEN-LAST:event_jButton4MouseClicked

    private void jTReparacionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTReparacionMouseClicked
        // TODO add your handling code here:
        try {
            int row =  this.jTReparacion.getSelectedRow();
            String id = this.jTReparacion.getValueAt(row, 0).toString();
            this.cargarTablaParaFacturar(id,"reparacion");
        } catch (Exception e) {
            Log.Error(this.getClass().getName(), e);
            JOptionPane.showMessageDialog(null, "Error cargando el conduce.", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
        
    }//GEN-LAST:event_jTReparacionMouseClicked

    private void jTANotaReparacionFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTANotaReparacionFocusGained
        // TODO add your handling code here:
        //Texto.placeholder(Texto.nota_producto,this.jTANotaReparacion.getText(), this.jTANotaReparacion);
         //Texto.setPlaceholder(Texto.email_cliente,this.t_email_cliente.getText(), this.t_email_cliente);
   
    }//GEN-LAST:event_jTANotaReparacionFocusGained

    private void jTANotaReparacionFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTANotaReparacionFocusLost
        // TODO add your handling code here:
       // Texto.placeholder(Texto.nota_producto,this.jTANotaReparacion.getText(), this.jTANotaReparacion);
        //Texto.setPlaceholder(Texto.nota_producto,this.jTANotaReparacion.getText(), this.jTANotaReparacion);
 
        //Texto.setPlaceholder(Texto.email_cliente,this.t_email_cliente.getText(), this.t_email_cliente);
   
    }//GEN-LAST:event_jTANotaReparacionFocusLost

    private void RegistrarPasoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_RegistrarPasoMousePressed
        // TODO add your handling code here:
        this.pop();
    }//GEN-LAST:event_RegistrarPasoMousePressed

    private void HistorialMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_HistorialMousePressed
        // TODO add your handling code here:
        this.pop1();
    }//GEN-LAST:event_HistorialMousePressed

    private void jMIEliminarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMIEliminarMousePressed
        // TODO add your handling code here:
        this.eliminarItem();
    }//GEN-LAST:event_jMIEliminarMousePressed

    private void jButton7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton7MouseClicked
        // TODO add your handling code here:
        new Help().setVisible(true);
    }//GEN-LAST:event_jButton7MouseClicked

    private void JTFFiltroCodigoConduceKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JTFFiltroCodigoConduceKeyReleased
        // TODO add your handling code here:
        this.filtrarConduce();
    }//GEN-LAST:event_JTFFiltroCodigoConduceKeyReleased

    private void jTextArea1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextArea1KeyTyped
        // TODO add your handling code here:
        this.notaReparacion = this.jTextArea1.getText();
    }//GEN-LAST:event_jTextArea1KeyTyped

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        // TODO add your handling code here:
        instancia = null;
    }//GEN-LAST:event_formWindowClosed

    private void JBReImprimirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JBReImprimirMouseClicked
        // TODO add your handling code here:
        this.VistaPrevia();
    }//GEN-LAST:event_JBReImprimirMouseClicked
 
    public void nuevoConduce(){        
        Log.Registrar(this.getClass(), "nuevoConduce", "");

        this.producto_id = new ArrayList<String>();
        this.sub_total_producto = new ArrayList<String>();
        this.codigo_producto = new ArrayList<String>();
        this.nombre_producto = new ArrayList<String>();
        this.precio_producto = new ArrayList<String>();
        this.cantidad_producto = new ArrayList<String>();
        this.nota_producto = new ArrayList<String>();
        this.estado_producto = new ArrayList<String>();
        this.precio_completo_producto  = new ArrayList<String>();

        montoTotal = 0.00;subMontoTotal = 0.00;abonoTotal = 0.00;itbisTotal = 0.00;
        SeguimientoConduce = false;//Cuando seleccionas un coduce para editar o agregar le mas equipos
        ultimoNumeroGeneredo = 0 ;
        productos = null;
        no_producto="0";n_producto="0";p_producto="0";c_producto="0";co_producto="0"; sub_total="0";itbis_total="0"; monto_total="0";
        factura_generada = false;deuda = false;mostrarPDF = true;
        existe_cliente = false;existe_producto= false;cotizado = false;no_agrego_producto = true;no_agrego_cliente = true;
        tiene_itbis = false;
        rnc_cliente="";
        estado_productot="";precio_completo_productot="0";
        ClienteID="1";ReparacionID="1";productoID="1";
         NCF = "";notaReparacion="";
         nombreUsuario = "";
         
        this.limpiarTodoTexto();
        this.jTReparacion.setVisible(false);
        this.jTableR.setModel(new DefaultTableModel());
    }
    
    public void eliminarItem(){        
        Log.Registrar(this.getClass(), "eliminarItem", "");

        
        int row = this.jTableR.getSelectedRow();
        if(row >= 0){
            this.nombre_producto.remove(row);
            this.cantidad_producto.remove(row);
            this.codigo_producto.remove(row);
            this.precio_producto.remove(row);
            this.producto_id.remove(row);
            this.sub_total_producto.remove(row);
            JOptionPane.showMessageDialog(null, "El item se elimino de forma correcta", "Item Eliminado", JOptionPane.INFORMATION_MESSAGE);
            if(this.nombre_producto.size() <= 0){
                 this.no_agrego_producto = false;
            }
            this.cargarJTable();
         }else{
          this.Msg();
        }
    }
    public void Msg(){
                Log.Registrar(this.getClass(), "Msg", "");
      JOptionPane.showMessageDialog(null, "Favor de seleccionar el producto primero");
    }
    public void pop(){        
        Log.Registrar(this.getClass(), "pop", "");

    String r_detalle_id = "";
        int row = this.jTableR.getSelectedRow();
        if(row >= 0){
            r_detalle_id = this.jTableR.getValueAt(row, 0).toString();
        PasoReparacionSeguimiento pasos = new PasoReparacionSeguimiento(this.mysql);
        pasos.setReparacion_detalle_id(r_detalle_id);
        pasos.obtenerDatosDB(r_detalle_id);
        pasos.obtenerDatosDB1(r_detalle_id);
        pasos.setPadre(ObjectReparacion);
        pasos.setVisible(true);
        }else{
          this.Msg();
        }
}
public void pop1(){       
    Log.Registrar(this.getClass(), "pop1", "");

    String r_detalle_id = "";
        int row = this.jTableR.getSelectedRow();
        if(row >= 0){
        r_detalle_id = this.jTableR.getValueAt(row, 0).toString();
        historialItemReparacion pasos = new historialItemReparacion(this.mysql);
        pasos.obtenerDatosDB(r_detalle_id);
        pasos.setVisible(true);
         }else{
        this.Msg();
        }
}
    /**
     * @param args the command line arguments
     */


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Fondo;
    private javax.swing.JMenuItem Historial;
    private javax.swing.JButton JBReImprimir;
    private javax.swing.JPanel JPGeneral;
    private javax.swing.JPanel JPOpcones;
    private javax.swing.JPanel JPProducto;
    private javax.swing.JPanel JPReparacion;
    private javax.swing.JPanel JPTotal;
    private javax.swing.JTextField JTFFiltroCodigoConduce;
    private javax.swing.JMenuItem RegistrarPaso;
    private javax.swing.JLabel cotizacion_itbis_total;
    private javax.swing.JLabel cotizacion_monto_total;
    private javax.swing.JLabel cotizacion_sub_total;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenuItem jMIEliminar;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTextArea jTANotaReparacion;
    private javax.swing.JTable jTReparacion;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTableR;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField t_cantidad_producto;
    private javax.swing.JTextField t_cedula_cliente;
    private javax.swing.JTextField t_codigo_cliente;
    private javax.swing.JTextField t_codigo_producto;
    private javax.swing.JTextField t_email_cliente;
    private javax.swing.JTextField t_nombre_cliente;
    private javax.swing.JTextField t_nombre_producto;
    private javax.swing.JTextField t_precio_producto;
    private javax.swing.JTextField t_telefono_cliente;
    // End of variables declaration//GEN-END:variables
}
