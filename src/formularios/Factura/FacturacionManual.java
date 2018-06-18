/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formularios.Factura;

import Clases.producto;
import Clases.Texto;
import Clases.Empresa;
import ClassStatic.Log;
import conexion.Mysql;
import formularios.Finanza.Pago;
import formularios.ListadoProducto;
import java.awt.Color;
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
public class FacturacionManual extends javax.swing.JFrame {

    /**
     * Creates new form Facturacion
     */
    private ArrayList<String> producto_id = new ArrayList<String>();
    private ArrayList<String> sub_total_producto = new ArrayList<String>();
    private ArrayList<String> codigo_producto = new ArrayList<String>();
    private ArrayList<String> nombre_producto = new ArrayList<String>();
    private ArrayList<String> precio_producto = new ArrayList<String>();
    private ArrayList<String> cantidad_producto = new ArrayList<String>();
        private double montoTotal = 0.00,subMontoTotal = 0.00,itbisTotal = 0.00;

    private List productos;
    
    private String n_producto,p_producto,c_producto,co_producto, sub_total,itbis_total, monto_total;
    private boolean tiene_itbis = false,factura_generada = false,deuda = false;
    
    private boolean existe_cliente = false,existe_producto= false,cotizado = false,no_agrego_producto = true,no_agrego_cliente = true;
    private Mysql mysql;
    
    private String rnc_cliente="";
    
    private String ClienteID="1",UsuarioID="1",FacturacionID="1",productoID="1";
    
    private String NCF = "",notaFactura="";
    
    private FacturacionManual ObjectFacturacion;
    
    private String nombreUsuario = "";
    String tieneNCF = "0";
    
    public static FacturacionManual instancia = null;
    
    public void resetArrayList(){
                Log.Registrar(this.getClass(), "resetArrayList", "");

        this.producto_id = new ArrayList<String>();
        this.sub_total_producto = new ArrayList<String>();
        this.codigo_producto = new ArrayList<String>();
        this.nombre_producto = new ArrayList<String>();
        this.precio_producto = new ArrayList<String>();
        this.cantidad_producto = new ArrayList<String>();
    }
    
    public void setDatosUsuario(String nombre,String id){
        this.nombreUsuario = nombre;
        this.UsuarioID = id;
        System.out.println(nombre+" "+id);
    }
    
    public FacturacionManual(Mysql mysql) {
        initComponents();
        //this.limpiarProductoTexto();
        this.limpiarTodoTexto();
        this.mysql = mysql;
        //this.jTDeuda.setVisible(false);
        //this.jTReparacion.setVisible(false);
        //this.jTCotizacion.setVisible(false);
        this.setLocationRelativeTo(null);
        JScrollPane[] listScroll = {this.jScrollPane1/*this.jScrollPane2,this.jScrollPane3,this.jScrollPane4,this.jScrollPane5
                */};
        ClassStatic.ConfTable.setBackGroundColor(listScroll );
        //this.jbAgregarNCF.setVisible(false);
    }
    
    public static FacturacionManual getInstancia(Mysql mysql){
       Log.Registrar("FacturacionManual/generarPDF");

        if(instancia == null){
            instancia = new FacturacionManual(mysql);
        }
        return instancia;
    }
    
    public void setObjectFacturacion(FacturacionManual c){
        this.ObjectFacturacion = c;
    }
    public void setDatosCliente(String ClienteID,String nombre,String cedula,String telefono,String email){
                Log.Registrar(this.getClass(), "setDatosCliente", "");

            this.ClienteID = ClienteID;
            this.t_codigo_cliente.setText(this.ClienteID);
            this.t_nombre_cliente.setText(nombre);
            this.t_cedula_cliente.setText(cedula);
            this.t_telefono_cliente.setText(telefono);
            this.t_email_cliente.setText(email);
            this.no_agrego_cliente = false;
            this.optenerCotizacionPendienteFacturarDelCliente();
            this.optenerReparacionesPendienteFacturarDelCliente();
            this.optenerDeudasPendienteFacturarDelCliente();
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
    }
    public void limpiarProductoTexto(){
                Log.Registrar(this.getClass(), "limpiarProductoTexto", "");

        /*this.t_nombre_producto.setText(Texto.nombre_producto);
        this.t_precio_producto.setText(Texto.precio_producto);
        this.t_cantidad_producto.setText(Texto.cantidad_producto);
        this.t_codigo_producto.setText(Texto.codigo_producto);
        */
        String empty = "";
        this.t_nombre_producto.setText(empty);
        this.t_precio_producto.setText(empty);
        this.t_cantidad_producto.setText(empty);
        this.t_codigo_producto.setText(empty);
        
        //this.t_nombre_cliente.setText(Texto.nombre_cliente);
        //this.t_cedula_cliente.setText(Texto.cedula_cliente);
        //this.t_codigo_cliente.setText(Texto.codigo_cliente);
        //this.t_telefono_cliente.setText(Texto.telefono_cliente);
        //this.t_email_cliente.setText(Texto.email_cliente);

    }
    public void limpiarTodoTexto(){                
        Log.Registrar(this.getClass(), "limpiarTodoTexto", "");

        /*this.t_nombre_producto.setText(Texto.nombre_producto);
        this.t_precio_producto.setText(Texto.precio_producto);
        this.t_cantidad_producto.setText(Texto.cantidad_producto);
        this.t_codigo_producto.setText(Texto.codigo_producto);
        
        this.t_nombre_cliente.setText(Texto.nombre_cliente);
        this.t_cedula_cliente.setText(Texto.cedula_cliente);
        this.t_codigo_cliente.setText(Texto.codigo_cliente);
        this.t_telefono_cliente.setText(Texto.telefono_cliente);
        this.t_email_cliente.setText(Texto.email_cliente);*/
        String empty = "";
        this.t_nombre_producto.setText(empty);
        this.t_precio_producto.setText(empty);
        this.t_cantidad_producto.setText(empty);
        this.t_codigo_producto.setText(empty);
        
        this.t_nombre_cliente.setText(empty);
        this.t_cedula_cliente.setText(empty);
        this.t_codigo_cliente.setText(empty);
        this.t_telefono_cliente.setText(empty);
        this.t_email_cliente.setText(empty);

    }
    public void optenerReparacionesPendienteFacturarDelCliente(){        
        Log.Registrar(this.getClass(), "optenerReparacionesPendienteFacturarDelCliente", "");

            
            String[] titulos = {"CODIGO REPARACION","ESTADO REPARACION","MONTO A PAGAR","CANTIDAD REPARACION","FECHA REPARACION"};
            String table_name = " reparacion as c";
            String campos = "c.id,c.sub_total,c.fecha_creada,c.cliente_id,c.estado, (SELECT count(c1.id) FROM reparacion_detalle as c1 WHERE c1.reparacion_id = c.id) as cantidad ";
            String otros = " where c.cliente_id = '"+this.ClienteID+"' ";
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
           //  this.jTReparacion.setModel(modelo);
            // this.jTReparacion.setVisible(true);    
        } catch (SQLException ex) {
            Logger.getLogger(ListadoProducto.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
     public void optenerCotizacionPendienteFacturarDelCliente(){        
         Log.Registrar(this.getClass(), "optenerCotizacionPendienteFacturarDelCliente", "");

            
            String[] titulos = {"CODIGO COTIZACION","ESTADO COTIZACION","MONTO A PAGAR COTIZACION","CANTIDAD COTIZACION","FECHA COTIZACION"};
            String table_name = " cotizacion as c ";
            String campos = "c.id,c.sub_total,c.fecha_creada,c.cliente_id,c.estado, (SELECT count(c1.id) FROM cotizacion_detalle as c1 WHERE c1.cotizacion_id = c.id) as cantidad ";
            String otros = " where c.cliente_id = '"+this.ClienteID+"' ";
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
             //this.jTCotizacion.setModel(modelo);
             //this.jTCotizacion.setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(ListadoProducto.class.getName()).log(Level.SEVERE, null, ex);
        } 
    
    
    
    }
     
     public void cargarTablaParaFacturar(String id,String accion){
                 Log.Registrar(this.getClass(), "cargarTablaParaFacturar", "");

          this.no_agrego_producto = false;
          this.resetArrayList();
          this.resetTotatel();
          this.FacturacionID = "1";
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
                String table_name = "  reparacion_detalle as c ";
                String campos = "c.id,c.nombre,c.fecha_creada,c.cantidad, c.total,c.precio_completado,c.reparacion_id,c.cliente_id,if(c.precio_completado is null or c.precio_completado = 0.00 , c.precio, c.precio_completado ) as precio";
                String otros = " where c.reparacion_id = '"+id+"'";
                //SELECT if(precio_completado is null or precio_completado = 0.00 , precio, precio_completado ) as precio FROM `reparacion_detalle`
                resultSet = this.mysql.optenerDatosParaTabla(table_name, campos, otros);
            }
        try {
            if(resultSet != null){
                while(resultSet.next()){
                         this.productoID = Texto.validarNull(resultSet.getString("id"));
                         this.c_producto = Texto.validarNull(resultSet.getString("cantidad"));
                         this.n_producto = Texto.validarNull(resultSet.getString("nombre"));
                         this.p_producto =Texto.validarNull(resultSet.getString("precio"));                     
                         this.co_producto =Texto.validarNull(resultSet.getString("id"));
                         if(this.deuda){
                             this.FacturacionID = Texto.validarNull(resultSet.getString("factura_id"));
                         }
                         this.asignarAArrayList();
                } 
            }
        } catch (SQLException ex) {
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
            // this.jTDeuda.setModel(modelo);
            // this.jTDeuda.setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(ListadoProducto.class.getName()).log(Level.SEVERE, null, ex);
        } 
    
    
    
    }
    public void insertarDBDetalleFacturacion(String id,String nombre,String precio,String cantidad,String total){        
        Log.Registrar(this.getClass(), "insertarDBDetalleFacturacion", "");

            if(!this.existe_producto){
             String campos = "usuario_id,nombre,precio,cantidad,total,fecha_creada,cliente_id,factura_id,producto_inventariado_id";
             String valores = "'"+this.UsuarioID+"','"+nombre+"','"+precio+"','"+cantidad+"','"+total+"',now(),'"+this.ClienteID+"','"+this.FacturacionID+"','"+id+"'";
             this.mysql.insertData("factura_detalle", campos, valores);
            }
    }
    public void getNCF(){
        /*if (JCBTieneNCF.isSelected()) {
            if (ClassStatic.NCF.getNCF()) {
             tieneNCF = "1";
            this.NCF = "NCF ";
            this.NCF += Clases.Empresa.ncf;
            System.out.println("NCF "+this.NCF);
            this.NCF += ClassStatic.NCF.ncf;
            System.out.println("NCF "+this.NCF);
            }
        }else{
            tieneNCF = "0";
            this.NCF = "";
        }*/
    }
    public void insertarDBFacturacion(String sub_total,String itbis,String tiene_itbis,String total){        
        Log.Registrar(this.getClass(), "insertarDBFacturacion", "");

            
            if (this.JCBTieneNCF.isSelected()) {
                getNCF();
                //ClassStatic.NCF.Insert();
            }
            if(this.tiene_itbis){
                tiene_itbis = "1";
            }
            String campos = "usuario_id,sub_total,itbis,tiene_itbis,total,fecha_creada,cliente_id,numero_comprovante_fiscal,tiene_comprovante_fiscal";
             String valores = "'"+this.UsuarioID+"','"+sub_total+"','"+itbis+"','"+tiene_itbis+"','"+total+"',now(),'"+this.ClienteID+"', '"+this.NCF+"','"+tieneNCF+"' ";
             this.mysql.insertData("factura", campos, valores);
             this.FacturacionID = this.mysql.optenerUltimoID("factura");
             
    }
    public void crearFacturacion(){
                   Log.Registrar(this.getClass(), "crearFacturacion", "");

           if(!this.cotizado && (!this.deuda) ){
            this.insertarDBFacturacion(this.subMontoTotal+"", this.itbisTotal+"","0", this.montoTotal+"");
            int lineas = this.nombre_producto.size();
            String nombre,precio,cantidad,total,id;
            for(int c = 0 ; c < lineas ; c++){
                     nombre = this.nombre_producto.get(c);
                     precio = this.precio_producto.get(c);
                     cantidad = this.cantidad_producto.get(c);
                     total = this.sub_total_producto.get(c);
                     id = this.producto_id.get(c);
                 this.insertarDBDetalleFacturacion(id,nombre, precio, cantidad, total);
            }
            this.cotizado = true;
           }
           this.generarPDF();
           this.ini();
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
        this.nombre_producto.add(this.n_producto+"("+this.productoID+")");
        this.precio_producto.add(""+this.p_producto);
        this.codigo_producto.add(this.co_producto);
        double monto =(Double.parseDouble(c_producto) * Double.parseDouble(p_producto) );
        this.sub_total_producto.add( ""+monto );
        this.totales(monto);
    }
    public void agregarTabla(){
                Log.Registrar(this.getClass(), "agregarTabla", "");

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
        Log.Registrar(this.getClass(), "validarAgregarEntradaProducto", "");

        Texto.placeholder(Texto.codigo_producto,this.t_codigo_producto.getText(), this.t_codigo_producto);
        Texto.placeholder(Texto.nombre_producto,this.t_nombre_producto.getText(), this.t_nombre_producto);
        Texto.placeholder(Texto.precio_producto,this.t_precio_producto.getText(), this.t_precio_producto);
        Texto.placeholder(Texto.cantidad_producto,this.t_cantidad_producto.getText(), this.t_cantidad_producto);
    }
    public void tieneITBIS(){
                        Log.Registrar(this.getClass(), "tieneITBIS", "");

        if(this.JCBTieneITBIS.isSelected()){
            this.tiene_itbis = true;
        }
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
        //try {
             double monto =0.00;
           String[] titulos = {"CODIGO","PRODUCTO","CANTIDAD","PRECIO","SUB TOTAL"};
           int lineas = this.nombre_producto.size();
           String nombre,precio,cantidad,total,id;
           
           this.productos  = new LinkedList();
           
           Object[][] fila = new Object[lineas][5];
           
           for(int c = 0 ; c < lineas ; c++){
                    id = this.codigo_producto.get(c);
                    nombre = this.nombre_producto.get(c);
                    precio = this.precio_producto.get(c);
                    cantidad = this.cantidad_producto.get(c);
                    total = this.sub_total_producto.get(c);
                    
                    fila[c][0] = id;
                    fila[c][1] = nombre;
                    fila[c][2] = cantidad;
                    fila[c][3] = precio;
                    fila[c][4] = total;
                     monto += Double.parseDouble(total);
                    this.productos.add( new producto(nombre,precio,cantidad,total) );
           }
           this.totales(monto);
          DefaultTableModel modelo = new DefaultTableModel(fila,titulos);
          this.jTable1.setModel(modelo);
       // } catch (Exception e) {
          //  System.out.println("Error cargar tabla "+e.getMessage());
        //}
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
            //JasperReport loadObject = (JasperReport) JRLoader.loadObject("C:/vyr/theme/facturacion.jasper");
            URL ruta = new URL("file:///C:/app_vyr/theme/facturacion_matricial.jasper");
            JasperReport loadObject = (JasperReport) JRLoader.loadObject(ruta);
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

            parameters.put("no_factura", this.FacturacionID);
            parameters.put("fecha", this.obtenerFechaActual());
            
            
            parameters.put("rnc_cliente", this.rnc_cliente);
            parameters.put("numero_cliente", this.ClienteID);
            parameters.put("nombre_cliente", this.t_nombre_cliente.getText());
            /*parameters.put("no_factura", this.idFactura);*/
            parameters.put("sub_total","$ "+this.subMontoTotal);
            parameters.put("monto_total","$ "+this.montoTotal);
            parameters.put("itbis", "$ "+this.itbisTotal);
            parameters.put("nota", this.notaFactura);

            
            JasperPrint jp = JasperFillManager.fillReport(loadObject, parameters, ds);           
            JasperViewer jv = new JasperViewer(jp,false);
            jv.setDefaultCloseOperation(JasperViewer.DISPOSE_ON_CLOSE);
            jv.setVisible(true);
                    
        } catch (JRException ex) {
            Logger.getLogger(FacturacionManual.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(FacturacionManual.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
    public void administrar_focus_clientes(String menu,String tabla){
       /* ListadoProducto selecciona = new ListadoProducto(this.mysql,tabla);
         selecciona.setObjectFacturacion(this.ObjectFacturacion);
        selecciona.setDatosUsuario(this.nombreUsuario, this.UsuarioID, "Listado De Cliente");
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
        */
    }
    public boolean validarQueTengaUnoSeleccionado(){        
        Log.Registrar(this.getClass(), "validarQueTengaUnoSeleccionado", "");

        no_agrego_cliente = false;
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
    /****************************************** 20-05-2018 ********************************************************************/
    //inicializa de nuevo el formulario para generar la factura
    public void ini(){
        Log.Registrar(this.getClass(), "ini", "");
        this.limpiarProductoTexto();
        this.limpiarTodoTexto();
        
        this.resetArrayList();
        this.resetTotatel();
        
       // this.jTCotizacion.setModel(new DefaultTableModel());        
       // this.jTDeuda.setModel(new DefaultTableModel());
       // this.jTReparacion.setModel(new DefaultTableModel());
        
        this.jTable1.setModel(new DefaultTableModel());
        
        this.JCBTieneNCF.setSelected(false);
        this.JCBTieneITBIS.setSelected(false);
        
        this.cotizacion_sub_total.setText("0.00");
        this.cotizacion_itbis_total.setText("0.00");
        this.cotizacion_monto_total.setText("0.00");

        this.no_agrego_cliente = true;
        this.no_agrego_producto = true;
        
        this.deuda = false;
        this.cotizado = false;
        
    }
    
    /******************************************************************************************************************/
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        jMIEliminar = new javax.swing.JMenuItem();
        Fondo = new javax.swing.JPanel();
        JPTotales = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        cotizacion_sub_total = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        cotizacion_itbis_total = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        cotizacion_monto_total = new javax.swing.JLabel();
        JPCliente = new javax.swing.JPanel();
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
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        JPGeneral = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        JPOpciones = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        JCBTieneNCF = new javax.swing.JCheckBox();
        JCBTieneITBIS = new javax.swing.JCheckBox();
        jTextField1 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();

        jMIEliminar.setText("Eliminar");
        jMIEliminar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jMIEliminarMousePressed(evt);
            }
        });
        jPopupMenu1.add(jMIEliminar);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Facturación");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        Fondo.setBackground(new java.awt.Color(255, 255, 255));

        JPTotales.setBackground(new java.awt.Color(0, 0, 204));
        JPTotales.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(150, 199, 248)));
        JPTotales.setForeground(new java.awt.Color(0, 0, 204));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("TOTALES");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Sub Total $");

        cotizacion_sub_total.setForeground(new java.awt.Color(255, 255, 255));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Itbis Total $");

        cotizacion_itbis_total.setForeground(new java.awt.Color(255, 255, 255));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Monto Total $");

        cotizacion_monto_total.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout JPTotalesLayout = new javax.swing.GroupLayout(JPTotales);
        JPTotales.setLayout(JPTotalesLayout);
        JPTotalesLayout.setHorizontalGroup(
            JPTotalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPTotalesLayout.createSequentialGroup()
                .addGroup(JPTotalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JPTotalesLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(JPTotalesLayout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(JPTotalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addGap(18, 18, 18)
                        .addGroup(JPTotalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cotizacion_itbis_total, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cotizacion_sub_total, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(2, 10, Short.MAX_VALUE))
            .addGroup(JPTotalesLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cotizacion_monto_total, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        JPTotalesLayout.setVerticalGroup(
            JPTotalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPTotalesLayout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(jLabel1)
                .addGap(6, 6, 6)
                .addGroup(JPTotalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cotizacion_sub_total, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(JPTotalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(cotizacion_itbis_total, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(JPTotalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(cotizacion_monto_total, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        JPCliente.setBackground(new java.awt.Color(255, 255, 255));

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
        t_email_cliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                t_email_clienteKeyReleased(evt);
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
        jLabel16.setText("RNC");

        javax.swing.GroupLayout JPClienteLayout = new javax.swing.GroupLayout(JPCliente);
        JPCliente.setLayout(JPClienteLayout);
        JPClienteLayout.setHorizontalGroup(
            JPClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPClienteLayout.createSequentialGroup()
                .addGroup(JPClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(t_codigo_cliente))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(JPClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(t_cedula_cliente))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(JPClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(t_nombre_cliente))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(JPClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(t_telefono_cliente)
                    .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(JPClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(t_email_cliente)
                    .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(21, 21, 21))
        );
        JPClienteLayout.setVerticalGroup(
            JPClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JPClienteLayout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addGroup(JPClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(jLabel13)
                    .addComponent(jLabel14)
                    .addComponent(jLabel15)
                    .addComponent(jLabel16))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(JPClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(t_codigo_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(t_cedula_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(t_nombre_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(t_telefono_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(t_email_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
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

        jButton1.setBackground(new java.awt.Color(0, 153, 51));
        jButton1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Agregar Producto");
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setText("Producto");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel9.setText("Código");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel10.setText("Precio");

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel11.setText("Cantidad");

        javax.swing.GroupLayout JPProductoLayout = new javax.swing.GroupLayout(JPProducto);
        JPProducto.setLayout(JPProductoLayout);
        JPProductoLayout.setHorizontalGroup(
            JPProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JPProductoLayout.createSequentialGroup()
                .addGroup(JPProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(t_codigo_producto))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(JPProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(t_nombre_producto))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(JPProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(t_precio_producto))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(JPProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(t_cantidad_producto)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(2, 2, 2)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7))
        );
        JPProductoLayout.setVerticalGroup(
            JPProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPProductoLayout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(JPProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel8)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(JPProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JPProductoLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(JPProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(t_codigo_producto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(t_nombre_producto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(t_precio_producto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(t_cantidad_producto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jButton1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        JPGeneral.setBackground(new java.awt.Color(255, 255, 255));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setText("Nota factura");

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jTextArea1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextArea1KeyReleased(evt);
            }
        });
        jScrollPane5.setViewportView(jTextArea1);

        javax.swing.GroupLayout JPGeneralLayout = new javax.swing.GroupLayout(JPGeneral);
        JPGeneral.setLayout(JPGeneralLayout);
        JPGeneralLayout.setHorizontalGroup(
            JPGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JPGeneralLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(JPGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JPGeneralLayout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 544, Short.MAX_VALUE))
                .addGap(254, 254, 254))
        );
        JPGeneralLayout.setVerticalGroup(
            JPGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPGeneralLayout.createSequentialGroup()
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 66, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jTable1.setComponentPopupMenu(jPopupMenu1);
        jTable1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jScrollPane1.setViewportView(jTable1);

        JPOpciones.setBackground(new java.awt.Color(255, 255, 255));
        JPOpciones.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 255)));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("OPCIONES");

        jButton2.setBackground(new java.awt.Color(0, 153, 51));
        jButton2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Ver");
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton2MouseClicked(evt);
            }
        });

        JCBTieneNCF.setBackground(new java.awt.Color(255, 255, 255));
        JCBTieneNCF.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        JCBTieneNCF.setSelected(true);
        JCBTieneNCF.setText("Tiene NCF?");
        JCBTieneNCF.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        JCBTieneNCF.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                JCBTieneNCFStateChanged(evt);
            }
        });
        JCBTieneNCF.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                JCBTieneNCFMousePressed(evt);
            }
        });

        JCBTieneITBIS.setBackground(new java.awt.Color(255, 255, 255));
        JCBTieneITBIS.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        JCBTieneITBIS.setText("Tiene ITBIS?");
        JCBTieneITBIS.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        JCBTieneITBIS.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                JCBTieneITBISStateChanged(evt);
            }
        });

        javax.swing.GroupLayout JPOpcionesLayout = new javax.swing.GroupLayout(JPOpciones);
        JPOpciones.setLayout(JPOpcionesLayout);
        JPOpcionesLayout.setHorizontalGroup(
            JPOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPOpcionesLayout.createSequentialGroup()
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 2, Short.MAX_VALUE))
            .addGroup(JPOpcionesLayout.createSequentialGroup()
                .addGroup(JPOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JPOpcionesLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(JCBTieneNCF, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(JCBTieneITBIS, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(JPOpcionesLayout.createSequentialGroup()
                        .addGap(77, 77, 77)
                        .addComponent(jButton2)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        JPOpcionesLayout.setVerticalGroup(
            JPOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPOpcionesLayout.createSequentialGroup()
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(JPOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JCBTieneNCF)
                    .addComponent(JCBTieneITBIS))
                .addGap(18, 18, 18)
                .addComponent(jButton2)
                .addContainerGap(29, Short.MAX_VALUE))
        );

        jTextField1.setText("B0100000000");
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField1KeyReleased(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setText("NCF");

        jTextField2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField2KeyReleased(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel17.setText("No. Factura");

        javax.swing.GroupLayout FondoLayout = new javax.swing.GroupLayout(Fondo);
        Fondo.setLayout(FondoLayout);
        FondoLayout.setHorizontalGroup(
            FondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(FondoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(FondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 808, Short.MAX_VALUE)
                    .addComponent(JPGeneral, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(JPProducto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(JPCliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(FondoLayout.createSequentialGroup()
                        .addComponent(JPTotales, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(FondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField1)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTextField2)
                            .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(JPOpciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(16, 16, 16)))
                .addContainerGap())
        );
        FondoLayout.setVerticalGroup(
            FondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(FondoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(FondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(JPTotales, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JPOpciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(FondoLayout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JPCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JPProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JPGeneral, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Fondo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Fondo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
        //Texto.setPlaceholder(Texto.nombre_producto,this.t_nombre_producto.getText(), this.t_nombre_producto);
    }//GEN-LAST:event_t_nombre_productoFocusLost

    private void t_precio_productoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_t_precio_productoFocusGained
        // TODO add your handling code here:
       // Texto.placeholder(Texto.precio_producto,this.t_precio_producto.getText(), this.t_precio_producto);
    }//GEN-LAST:event_t_precio_productoFocusGained

    private void t_precio_productoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_t_precio_productoFocusLost
        // TODO add your handling code here:
        //Texto.setPlaceholder(Texto.precio_producto,this.t_precio_producto.getText(), this.t_precio_producto);
    }//GEN-LAST:event_t_precio_productoFocusLost

    private void t_cantidad_productoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_t_cantidad_productoFocusGained
        // TODO add your handling code here:
     // Texto.placeholder(Texto.cantidad_producto,this.t_cantidad_producto.getText(), this.t_cantidad_producto);
    }//GEN-LAST:event_t_cantidad_productoFocusGained

    private void t_cantidad_productoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_t_cantidad_productoFocusLost
        // TODO add your handling code here:
      //Texto.setPlaceholder(Texto.cantidad_producto,this.t_cantidad_producto.getText(), this.t_cantidad_producto);
    }//GEN-LAST:event_t_cantidad_productoFocusLost

    private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseClicked
        // TODO add your handling code here:
        if(this.validarQueTengaUnoSeleccionado()){
            
             this.generarPDF();
        }
    }//GEN-LAST:event_jButton2MouseClicked

    private void t_codigo_clienteFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_t_codigo_clienteFocusGained
        // TODO add your handling code here:
       /* Texto.placeholder(Texto.codigo_cliente,this.t_codigo_cliente.getText(), this.t_codigo_cliente);
        ListadoProducto selecciona = new ListadoProducto(this.mysql);
        selecciona.setObjectFacturacion(this.ObjectFacturacion);
        selecciona.setTextBox(t_codigo_cliente);
        selecciona.setPalabra("codigo");
        selecciona.setVisible(true);*/
       administrar_focus_clientes("codigo_cliente","cliente");
    }//GEN-LAST:event_t_codigo_clienteFocusGained

    private void t_codigo_clienteFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_t_codigo_clienteFocusLost
        // TODO add your handling code here:
         //Texto.setPlaceholder(Texto.codigo_cliente,this.t_codigo_cliente.getText(), this.t_codigo_cliente);
    
    }//GEN-LAST:event_t_codigo_clienteFocusLost

    private void t_cedula_clienteFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_t_cedula_clienteFocusGained
        // TODO add your handling code here:
       //Texto.placeholder(Texto.cedula_cliente,this.t_cedula_cliente.getText(), this.t_cedula_cliente);
       administrar_focus_clientes("cedula_cliente","cliente");
    }//GEN-LAST:event_t_cedula_clienteFocusGained

    private void t_cedula_clienteFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_t_cedula_clienteFocusLost
        // TODO add your handling code here:
         //Texto.setPlaceholder(Texto.cedula_cliente,this.t_cedula_cliente.getText(), this.t_cedula_cliente);
     
    }//GEN-LAST:event_t_cedula_clienteFocusLost

    private void t_nombre_clienteFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_t_nombre_clienteFocusGained
        // TODO add your handling code here:
       //Texto.placeholder(Texto.nombre_cliente,this.t_nombre_cliente.getText(), this.t_nombre_cliente);
       //Texto.setPlaceholder(Texto.nombre_cliente,this.t_nombre_cliente.getText(), this.t_nombre_cliente);
       administrar_focus_clientes("nombre_cliente","cliente");
    }//GEN-LAST:event_t_nombre_clienteFocusGained

    private void t_nombre_clienteFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_t_nombre_clienteFocusLost
        // TODO add your handling code here:
        //Texto.placeholder(Texto.nombre_cliente,this.t_nombre_cliente.getText(), this.t_nombre_cliente);
       //Texto.setPlaceholder(Texto.nombre_cliente,this.t_nombre_cliente.getText(), this.t_nombre_cliente);
   
    }//GEN-LAST:event_t_nombre_clienteFocusLost

    private void t_telefono_clienteFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_t_telefono_clienteFocusGained
        // TODO add your handling code here:
        //Texto.placeholder(Texto.telefono_cliente,this.t_telefono_cliente.getText(), this.t_telefono_cliente);
       //Texto.setPlaceholder(Texto.telefono_cliente,this.t_telefono_cliente.getText(), this.t_telefono_cliente);
       administrar_focus_clientes("telefono_cliente","cliente");
    }//GEN-LAST:event_t_telefono_clienteFocusGained

    private void t_telefono_clienteFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_t_telefono_clienteFocusLost
        // TODO add your handling code here:
        //Texto.placeholder(Texto.telefono_cliente,this.t_telefono_cliente.getText(), this.t_telefono_cliente);
       //Texto.setPlaceholder(Texto.telefono_cliente,this.t_telefono_cliente.getText(), this.t_telefono_cliente);
   
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

    private void JCBTieneITBISStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_JCBTieneITBISStateChanged
        // TODO add your handling code here:
        this.resetTotatel();
       if( this.JCBTieneITBIS.isSelected())
       {
           this.tiene_itbis = true;
           this.reCalcular();
       }else{
           this.tiene_itbis = false;
           this.reCalcular();
       }
    }//GEN-LAST:event_JCBTieneITBISStateChanged

    private void jMIEliminarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMIEliminarMousePressed
        // TODO add your handling code here:
        this.eliminarItem();
    }//GEN-LAST:event_jMIEliminarMousePressed

    private void JCBTieneNCFStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_JCBTieneNCFStateChanged
        // TODO add your handling code here:
        this.getNCF();
    }//GEN-LAST:event_JCBTieneNCFStateChanged

    private void JCBTieneNCFMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JCBTieneNCFMousePressed
        // TODO add your handling code here:
      
    }//GEN-LAST:event_JCBTieneNCFMousePressed

    private void jTextArea1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextArea1KeyReleased
        // TODO add your handling code here:
         this.notaFactura = this.jTextArea1.getText();
    }//GEN-LAST:event_jTextArea1KeyReleased

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        // TODO add your handling code here:
        instancia = null;
    }//GEN-LAST:event_formWindowClosed

    private void jTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyReleased
        // TODO add your handling code here:
        this.NCF = this.jTextField1.getText();
    }//GEN-LAST:event_jTextField1KeyReleased

    private void jTextField2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField2KeyReleased
        // TODO add your handling code here:
        this.FacturacionID = this.jTextField2.getText();
    }//GEN-LAST:event_jTextField2KeyReleased

    private void t_email_clienteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_t_email_clienteKeyReleased
        // TODO add your handling code here:
        this.rnc_cliente = this.t_email_cliente.getText();
    }//GEN-LAST:event_t_email_clienteKeyReleased
 public void eliminarItem(){
                           Log.Registrar(this.getClass(), "eliminarItem", "");

        int row = this.jTable1.getSelectedRow();
        
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
    }
    /**
     * @param args the command line arguments
     */


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Fondo;
    private javax.swing.JCheckBox JCBTieneITBIS;
    private javax.swing.JCheckBox JCBTieneNCF;
    private javax.swing.JPanel JPCliente;
    private javax.swing.JPanel JPGeneral;
    private javax.swing.JPanel JPOpciones;
    private javax.swing.JPanel JPProducto;
    private javax.swing.JPanel JPTotales;
    private javax.swing.JLabel cotizacion_itbis_total;
    private javax.swing.JLabel cotizacion_monto_total;
    private javax.swing.JLabel cotizacion_sub_total;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenuItem jMIEliminar;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
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
