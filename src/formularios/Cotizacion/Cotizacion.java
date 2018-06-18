/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formularios.Cotizacion;

import Clases.producto;
import Clases.Texto;
import Clases.Empresa;
import ClassStatic.Log;
import conexion.Mysql;
import formularios.ListadoProducto;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JREmptyDataSource;

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
public class Cotizacion extends javax.swing.JFrame {

    /**
     * Creates new form Cotizacion
     */
    private ArrayList<String> producto_id = new ArrayList<String>();
    private ArrayList<String> sub_total_producto = new ArrayList<String>();
    private ArrayList<String> codigo_producto = new ArrayList<String>();
    private ArrayList<String> nombre_producto = new ArrayList<String>();
    private ArrayList<String> precio_producto = new ArrayList<String>();
    private ArrayList<String> cantidad_producto = new ArrayList<String>();

    private List productos;
    
    private String n_producto,p_producto,c_producto,co_producto, sub_total,itbis_total, monto_total;
    private boolean tiene_itbis = false;
    
    private boolean existe_cliente = false,existe_producto= false,cotizado = false,no_agrego_producto = true,no_agrego_cliente = true;
    private Mysql mysql;
    
    private double montoTotal = 0.00,subMontoTotal = 0.00,itbisTotal = 0.00;
            
    
    private String ClienteID="1",UsuarioID="1",CotizacionID="1",productoID="1";
    
    private Cotizacion ObjectCotizacion;
    
    private String nombreUsuario = "";
    private String nota = "";
    
    public static Cotizacion instancia = null;

    public static Cotizacion getInstancia(Mysql mysql){
        Log.Registrar( "Cotizacion/getInstancia");

        if(instancia == null){
            instancia = new Cotizacion(mysql);
        }
        return instancia;
    }
    
    public void setDatosUsuario(String nombre,String id){
        this.nombreUsuario = nombre;
        this.UsuarioID = id;
        System.out.println(nombre+" "+id);
    }
    
    public Cotizacion(Mysql mysql) {
        initComponents();
        //this.limpiarProductoTexto();
        this.limpiarTodoTexto();
        this.mysql = mysql;
       // setIconImage(new ImageIcon(getClass().getResource("../icono/logovyr.gif")).getImage());
        this.setIconImage(new ImageIcon("C:/app_vyr/img/logovyr.gif").getImage());
        this.setLocationRelativeTo(null);
        JScrollPane[] listScroll = {this.jScrollPane1,this.jScrollPane2};
        ClassStatic.ConfTable.setBackGroundColor(listScroll );
    }
    
    public void setObjectCotizacion(Cotizacion c){
        this.ObjectCotizacion = c;
    }
    public void setDatosCliente(String ClienteID,String nombre,String cedula,String telefono,String email){
            this.ClienteID = ClienteID;
            this.t_codigo_cliente.setText(this.ClienteID);
            this.t_nombre_cliente.setText(nombre);
            this.t_cedula_cliente.setText(cedula);
            this.t_telefono_cliente.setText(telefono);
            this.t_email_cliente.setText(email);
            this.no_agrego_cliente = false;
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

        this.t_nombre_producto.setText("");
        this.t_precio_producto.setText("");
        this.t_cantidad_producto.setText("");
        this.t_codigo_producto.setText("");
        
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
        this.t_email_cliente.setText(Texto.email_cliente);
        */
        String text = "";
        this.t_nombre_producto.setText(text);
        this.t_precio_producto.setText(text);
        this.t_cantidad_producto.setText(text);
        this.t_codigo_producto.setText(text);
        
        this.t_nombre_cliente.setText(text);
        this.t_cedula_cliente.setText(text);
        this.t_codigo_cliente.setText(text);
        this.t_telefono_cliente.setText(text);
        this.t_email_cliente.setText(text);

    }
    public void insertarDBDetalleCotizacion(String id,String nombre,String precio,String cantidad,String total){
                Log.Registrar(this.getClass(), "insertarDBDetalleCotizacion", "");

            if(!this.existe_producto){
             String campos = "usuario_id,nombre,precio,cantidad,total,fecha_creada,cliente_id,cotizacion_id,producto_inventariado_id";
             String valores = "'"+this.UsuarioID+"','"+nombre+"','"+precio+"','"+cantidad+"','"+total+"',now(),'"+this.ClienteID+"','"+this.CotizacionID+"','"+id+"'";
             this.mysql.insertData("cotizacion_detalle", campos, valores);
            }
    }
    public void insertarDBCotizacion(String sub_total,String itbis,String tiene_itbis,String total){       
        Log.Registrar(this.getClass(), "insertarDBCotizacion", "");

            String campos = "usuario_id,sub_total,itbis,tiene_itbis,total,fecha_creada,cliente_id,nota";
             String valores = "'"+this.UsuarioID+"','"+sub_total+"','"+itbis+"','"+tiene_itbis+"','"+total+"',now(),'"+this.ClienteID+"','"+this.nota+"'";
             this.mysql.insertData("cotizacion", campos, valores);
             this.CotizacionID = this.mysql.optenerUltimoID("cotizacion");
    }
    public void crearCotizacion(){       
        Log.Registrar(this.getClass(), "crearCotizacion", "");

           if(!this.cotizado){
            this.insertarDBCotizacion(this.subMontoTotal+"", this.itbisTotal+"","0", this.montoTotal+"");
            int lineas = this.nombre_producto.size();
            String nombre,precio,cantidad,total,id;
            for(int c = 0 ; c < lineas ; c++){
                     nombre = this.nombre_producto.get(c);
                     precio = this.precio_producto.get(c);
                     cantidad = this.cantidad_producto.get(c);
                     total = this.sub_total_producto.get(c);
                     id = this.producto_id.get(c);
                 this.insertarDBDetalleCotizacion(id,nombre, precio, cantidad, total);
            }
            this.cotizado = true;
           }
           this.generarPDF();
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
        //Texto.placeholder(Texto.codigo_producto,this.t_codigo_producto.getText(), this.t_codigo_producto);
        //Texto.placeholder(Texto.nombre_producto,this.t_nombre_producto.getText(), this.t_nombre_producto);
        //Texto.placeholder(Texto.precio_producto,this.t_precio_producto.getText(), this.t_precio_producto);
        //Texto.placeholder(Texto.cantidad_producto,this.t_cantidad_producto.getText(), this.t_cantidad_producto);
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
        /*
        double itbis = 0,monto_total = 0;
        this.sub_total = ""+monto;
        
        if(this.tiene_itbis){
            itbis = monto * 0.18; 
        }
        monto_total = monto - itbis; 
        
        this.montoTotal =monto_total;
        this.itbisTotal = itbis;
        this.subMontoTotal = monto;
        this.itbis_total = ""+itbis;d
        this.monto_total = ""+monto_total;
        this.cotizacion_itbis_total.setText("$ "+this.itbisTotal);
        this.cotizacion_sub_total.setText("$ "+this.subMontoTotal);
        this.cotizacion_monto_total.setText("$ "+this.montoTotal);*/
    }
    public void tieneITBIS(){       
        Log.Registrar(this.getClass(), "tieneITBIS", "");

        if(this.JCBTieneITBIS.isSelected()){
            this.tiene_itbis = true;
        }
    }
    public void cargarJTable(){      
        Log.Registrar(this.getClass(), "cargarJTable", "");

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
           // String ruta = getClass().getResource("../theme/cotizacion.jasper");
           
            //System.out.println(ruta);
           //JasperReport loadObject = (JasperReport) JRLoader.loadObject(ruta);
           URL ruta = new URL("file:///C:/app_vyr/theme/cotizacion_matricial.jasper");
           JasperReport loadObject =(JasperReport) JRLoader.loadObject(ruta);
//JasperReport loadObject =(JasperReport) JRLoader.loadObject(getClass().getResource("../theme/cotizacion.jasper"));
           //JasperReport loadObject = (JasperReport) JRLoader.loadObject("C:\\vyrarchivos\\cotizacion.jasper");
           //"C:/vyr/img/iconoclinica.gif"
             Map parameters = new HashMap<String, Object>();
            JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(productos); 
            //System.out.println(getClass().getResource("../icono/iconoclinica.gif"));
            // getClass().getResource("../icono/iconoclinica.gif").toString()
            parameters.put("logo_empresa",Empresa.logo);
            parameters.put("nombre_empresa", "V & R");
            parameters.put("no_factura", this.CotizacionID);
            
            parameters.put("eslogan_empresa", "SERVICIOS DE HERRAMIENTAS");
            parameters.put("fecha", this.obtenerFechaActual());
            
            parameters.put("numero_cliente", this.ClienteID);
            parameters.put("nombre_cliente", this.t_nombre_cliente.getText());
            
            /*parameters.put("no_factura", this.idFactura);*/
            parameters.put("sub_total","$ "+this.subMontoTotal);
            parameters.put("monto_total","$ "+this.montoTotal);
            parameters.put("itbis", "$ "+this.itbisTotal);
            parameters.put("nota", ""+this.nota);
              System.out.println("ok");
            //JasperPrint jp = JasperFillManager.fillReport(loadObject, parameters,  new JREmptyDataSource());           
            JasperPrint jp = JasperFillManager.fillReport(loadObject, parameters,ds);           
            System.out.println("ok");
            JasperViewer jv = new JasperViewer(jp,false);
            System.out.println("ok");
            jv.setDefaultCloseOperation(JasperViewer.DISPOSE_ON_CLOSE);
            System.out.println("ok");
            jv.setVisible(true);
            System.out.println("ok");
                    
        } catch (JRException ex) {
                        Log.Error(this.getClass().getName(), ex);

           System.out.println("JRException error");
            System.out.println(ex.getMessage());
            System.out.println(ex.getCause());
            //Logger.getLogger(Cotizacion.class.getName()).log(Level.SEVERE, null, ex);
        }catch (Exception ex) {
                        Log.Error(this.getClass().getName(), ex);

           System.out.println("Exception error");
            System.out.println(ex.getCause());
            //Logger.getLogger(Cotizacion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void setTipoInventario(ListadoProducto select){
        
        if(this.JCBEsConduce.isSelected()){
            select.setTipoProducto(2);
        }else{
            select.setTipoProducto(1);
        }
    }
    public void administrar_focus_clientes(String menu,String tabla){
        Log.Registrar(this.getClass(), "administrar_focus_clientes", "");

        ListadoProducto selecciona = new ListadoProducto(this.mysql,tabla);
         selecciona.setObjectCotizacion(this.ObjectCotizacion);
         this.setTipoInventario(selecciona);
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
                ////Texto.setPlaceholder(Texto.nombre_producto,this.t_nombre_producto.getText(), this.t_nombre_producto);
                selecciona.setTextBox(t_nombre_producto);
                selecciona.setPalabra("nombre");
                selecciona.setDatosUsuario(this.nombreUsuario, this.UsuarioID, "Listado De Producto");
            break;
        }
        
        this.cargarJTable();
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
    public void resetTotatel(){      
        Log.Registrar(this.getClass(), "resetTotatel", "");

         this.montoTotal = 0.00;
        this.itbisTotal = 0.00;
        this.subMontoTotal = 0.00;
    }
    public void reCalcular(){       
        Log.Registrar(this.getClass(), "reCalcular", "");

           int lineas = this.nombre_producto.size();
            String nombre,precio,cantidad,total,id;
            double  monto = 0 ;
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
    
     /****************************************** 20-05-2018 ********************************************************************/
    //inicializa de nuevo el formulario para generar la factura
    public void ini(){
        Log.Registrar(this.getClass(), "ini", "");
        this.limpiarProductoTexto();
        this.limpiarTodoTexto();
        
        this.resetArrayList();
        this.resetTotatel();
        
        this.jTable1.setModel(new DefaultTableModel());
        
        this.JCBTieneITBIS.setSelected(false);
        
        this.cotizacion_sub_total.setText("0.00");
        this.cotizacion_itbis_total.setText("0.00");
        this.cotizacion_monto_total.setText("0.00");

        this.no_agrego_cliente = true;
        this.no_agrego_producto = true;
        
        this.cotizado = false;
        
    }
    public void resetArrayList(){        
        Log.Registrar(this.getClass(), "resetArrayList", "");

        this.producto_id = new ArrayList<String>();
        this.sub_total_producto = new ArrayList<String>();
        this.codigo_producto = new ArrayList<String>();
        this.nombre_producto = new ArrayList<String>();
        this.precio_producto = new ArrayList<String>();
        this.cantidad_producto = new ArrayList<String>();
    }
    /******************************************************************************************************************/
    
   
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        JPFondo = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        t_codigo_producto = new javax.swing.JTextField();
        t_nombre_producto = new javax.swing.JTextField();
        t_precio_producto = new javax.swing.JTextField();
        t_cantidad_producto = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
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
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        cotizacion_sub_total = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        cotizacion_itbis_total = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        cotizacion_monto_total = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        JCBEsConduce = new javax.swing.JCheckBox();
        JCBTieneITBIS = new javax.swing.JCheckBox();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();

        jMenuItem1.setText("Eliminar Item");
        jMenuItem1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jMenuItem1MousePressed(evt);
            }
        });
        jPopupMenu1.add(jMenuItem1);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cotización");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        JPFondo.setBackground(new java.awt.Color(255, 255, 255));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null}
            },
            new String [] {
                "CODIGO", "PRODUCTO", "CANTIDAD", "PRECIO", "SUB TOTAL"
            }
        ));
        jTable1.setComponentPopupMenu(jPopupMenu1);
        jScrollPane1.setViewportView(jTable1);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

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
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Agregar Producto");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel9.setText("Código");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setText("Producto");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel10.setText("Precio");

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel11.setText("Cantidad");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(t_codigo_producto))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(t_nombre_producto))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(t_precio_producto))
                .addGap(6, 6, 6)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(t_cantidad_producto)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(t_codigo_producto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(t_nombre_producto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(t_precio_producto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(t_cantidad_producto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

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
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(t_codigo_cliente)
                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(t_cedula_cliente)
                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(t_nombre_cliente)
                    .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(t_telefono_cliente)
                    .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(1, 1, 1))
                    .addComponent(t_email_cliente))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(0, 0, 204));

        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("TOTALES");

        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Sub Total $");

        cotizacion_sub_total.setForeground(new java.awt.Color(255, 255, 255));

        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Itbis Total $");

        cotizacion_itbis_total.setForeground(new java.awt.Color(255, 255, 255));

        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Monto Total $");

        cotizacion_monto_total.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(cotizacion_itbis_total, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE)
                    .addComponent(cotizacion_monto_total, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cotizacion_sub_total, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cotizacion_sub_total, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cotizacion_itbis_total, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cotizacion_monto_total, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(217, 225, 233)));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));

        JCBEsConduce.setBackground(new java.awt.Color(255, 255, 255));
        JCBEsConduce.setText("Es para un conduce?");
        JCBEsConduce.setName("JCBEsConduce"); // NOI18N

        JCBTieneITBIS.setBackground(new java.awt.Color(255, 255, 255));
        JCBTieneITBIS.setText("Tiene ITBIS?");
        JCBTieneITBIS.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                JCBTieneITBISStateChanged(evt);
            }
        });
        JCBTieneITBIS.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JCBTieneITBISMouseClicked(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(0, 153, 51));
        jButton2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Ver");
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton2MouseClicked(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(0, 51, 204));
        jButton3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("Guardar");
        jButton3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton3MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton2)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(JCBTieneITBIS, javax.swing.GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton3)
                    .addComponent(JCBEsConduce))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JCBEsConduce)
                    .addComponent(JCBTieneITBIS))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel17.setText("Nota cotización");

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jTextArea1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextArea1KeyReleased(evt);
            }
        });
        jScrollPane2.setViewportView(jTextArea1);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 18, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                .addGap(2, 2, 2))
        );

        javax.swing.GroupLayout JPFondoLayout = new javax.swing.GroupLayout(JPFondo);
        JPFondo.setLayout(JPFondoLayout);
        JPFondoLayout.setHorizontalGroup(
            JPFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPFondoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(JPFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JPFondoLayout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JPFondoLayout.createSequentialGroup()
                        .addGroup(JPFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1)
                            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(1, 1, 1))))
        );
        JPFondoLayout.setVerticalGroup(
            JPFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JPFondoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(JPFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 213, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(JPFondo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(JPFondo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        // TODO add your handling code here:
        this.agregarTabla();
    }//GEN-LAST:event_jButton1MouseClicked

    private void t_codigo_productoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_t_codigo_productoFocusGained
        // TODO add your handling code here:
        //Texto.placeholder(Texto.codigo_producto,this.t_codigo_producto.getText(), this.t_codigo_producto);
               administrar_focus_clientes("codigo_producto","producto");

    }//GEN-LAST:event_t_codigo_productoFocusGained

    private void t_codigo_productoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_t_codigo_productoFocusLost
        // TODO add your handling code here:
        //Texto.setPlaceholder(Texto.codigo_producto,this.t_codigo_producto.getText(), this.t_codigo_producto);
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

    private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseClicked
        // TODO add your handling code here:
        if(this.validarQueTengaUnoSeleccionado()){
            
             this.generarPDF();
        }
    }//GEN-LAST:event_jButton2MouseClicked

    private void jButton3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton3MouseClicked
        // TODO add your handling code here:
        if(this.validarQueTengaUnoSeleccionado()){
            this.crearCotizacion();
            this.ini();
        }
    }//GEN-LAST:event_jButton3MouseClicked

    private void t_codigo_clienteFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_t_codigo_clienteFocusGained
        // TODO add your handling code here:
       /* Texto.placeholder(Texto.codigo_cliente,this.t_codigo_cliente.getText(), this.t_codigo_cliente);
        ListadoProducto selecciona = new ListadoProducto(this.mysql);
        selecciona.setObjectCotizacion(this.ObjectCotizacion);
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
       ////Texto.setPlaceholder(Texto.nombre_cliente,this.t_nombre_cliente.getText(), this.t_nombre_cliente);
       administrar_focus_clientes("nombre_cliente","cliente");
    }//GEN-LAST:event_t_nombre_clienteFocusGained

    private void t_nombre_clienteFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_t_nombre_clienteFocusLost
        // TODO add your handling code here:
        ////Texto.placeholder(Texto.nombre_cliente,this.t_nombre_cliente.getText(), this.t_nombre_cliente);
       //Texto.setPlaceholder(Texto.nombre_cliente,this.t_nombre_cliente.getText(), this.t_nombre_cliente);
   
    }//GEN-LAST:event_t_nombre_clienteFocusLost

    private void t_telefono_clienteFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_t_telefono_clienteFocusGained
        // TODO add your handling code here:
        //Texto.placeholder(Texto.telefono_cliente,this.t_telefono_cliente.getText(), this.t_telefono_cliente);
       ////Texto.setPlaceholder(Texto.telefono_cliente,this.t_telefono_cliente.getText(), this.t_telefono_cliente);
       administrar_focus_clientes("telefono_cliente","cliente");
    }//GEN-LAST:event_t_telefono_clienteFocusGained

    private void t_telefono_clienteFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_t_telefono_clienteFocusLost
        // TODO add your handling code here:
        ////Texto.placeholder(Texto.telefono_cliente,this.t_telefono_cliente.getText(), this.t_telefono_cliente);
       //Texto.setPlaceholder(Texto.telefono_cliente,this.t_telefono_cliente.getText(), this.t_telefono_cliente);
   
    }//GEN-LAST:event_t_telefono_clienteFocusLost

    private void t_email_clienteFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_t_email_clienteFocusGained
        // TODO add your handling code here:
        //Texto.placeholder(Texto.email_cliente,this.t_email_cliente.getText(), this.t_email_cliente);
       ////Texto.setPlaceholder(Texto.email_cliente,this.t_email_cliente.getText(), this.t_email_cliente);
       administrar_focus_clientes("email_cliente","cliente");
    }//GEN-LAST:event_t_email_clienteFocusGained

    private void t_email_clienteFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_t_email_clienteFocusLost
        // TODO add your handling code here:
        ////Texto.placeholder(Texto.email_cliente,this.t_email_cliente.getText(), this.t_email_cliente);
       //Texto.setPlaceholder(Texto.email_cliente,this.t_email_cliente.getText(), this.t_email_cliente);
   
    }//GEN-LAST:event_t_email_clienteFocusLost

    private void jMenuItem1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem1MousePressed
        // TODO add your handling code here:
        this.eliminarItem();
    }//GEN-LAST:event_jMenuItem1MousePressed

    private void JCBTieneITBISStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_JCBTieneITBISStateChanged
        // TODO add your handling code here:
        
    }//GEN-LAST:event_JCBTieneITBISStateChanged

    private void JCBTieneITBISMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JCBTieneITBISMouseClicked
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
    }//GEN-LAST:event_JCBTieneITBISMouseClicked

    private void jTextArea1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextArea1KeyReleased
        // TODO add your handling code here:
        this.nota = this.jTextArea1.getText();
    }//GEN-LAST:event_jTextArea1KeyReleased

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        // TODO add your handling code here:
        instancia = null;
    }//GEN-LAST:event_formWindowClosed
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
    private javax.swing.JCheckBox JCBEsConduce;
    private javax.swing.JCheckBox JCBTieneITBIS;
    private javax.swing.JPanel JPFondo;
    private javax.swing.JLabel cotizacion_itbis_total;
    private javax.swing.JLabel cotizacion_monto_total;
    private javax.swing.JLabel cotizacion_sub_total;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
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
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
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
