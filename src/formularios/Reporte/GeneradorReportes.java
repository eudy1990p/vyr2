/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formularios.Reporte;

import Clases.ModeloReporteFactura;
import Clases.Empresa;
import ClassStatic.Log;
import conexion.Mysql;
import formularios.Factura.Facturacion;
import static formularios.Factura.Facturacion.instancia;
import formularios.ListadoProducto;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
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
 * @author VOSTRO
 */
public class GeneradorReportes extends javax.swing.JFrame {

    /**
     * Creates new form GeneradorReportes
     */
    private Mysql mysql;
    private ArrayList<Integer> UsuariosID =new ArrayList<Integer>();
    private ArrayList<Integer> ClientesID =new ArrayList<Integer>();
    private String fechaDesde="",fechaHasta="",tipoReporte="";
    private String ListIDUsuario = "";
    private String ListIDCliente = "";
    private List Facturas = null;
    private int TotalFactura = 0;
    private double TotalSubMontoFactura = 0;
    private double TotalItbisFactura = 0;
    private double TotalMontoFactura = 0;
    private String nota = "";
    private boolean soloConNCF = false;
    private String campoNCF ="";
    public static Facturacion instancia = null;
    
    public static Facturacion getInstancia(Mysql mysql){
        Log.Registrar( "Facturacion/getInstancia");

        if(instancia == null){
            instancia = new Facturacion(mysql);
        }
        return instancia;
    }
    public GeneradorReportes(Mysql mysql) {
        initComponents();
        this.mysql = mysql;
        this.cargarUsuarios();
        this.cargarClientes();
        this.setLocationRelativeTo(null);
    }

    public void getDatosBase(){
     Log.Registrar(this.getClass(), "getDatosBase", "");

        String[] tmpFecha;
        JTextField fechaD = (JTextField) this.jDateChooserFechaDesde.getDateEditor().getUiComponent();
       JTextField fechaH = (JTextField) this.jDateChooserFechaHasta.getDateEditor().getUiComponent();
       
       if(!fechaD.getText().isEmpty()){
           tmpFecha = fechaD.getText().split("/");
           this.fechaDesde = tmpFecha[2]+"-"+tmpFecha[1]+"-"+tmpFecha[0];
       }else{
           this.fechaDesde = "";
       }
       if(!fechaH.getText().isEmpty()){
       tmpFecha = fechaH.getText().split("/");
       this.fechaHasta = tmpFecha[2]+"-"+tmpFecha[1]+"-"+tmpFecha[0];
       }else{
            this.fechaHasta = "";
       }
       this.tipoReporte = this.jComboBox1.getSelectedItem().toString();

          this.ListIDUsuario = "";
          this.ListIDCliente = "";
          
       for(int i = 0 ; i < this.jListUsuarios.getSelectedIndices().length ; i++){
       int index = this.jListUsuarios.getSelectedIndices()[i];
           
           if(i == 0){
          this.ListIDUsuario += "";
           }else{
               this.ListIDUsuario += ", ";
           }
           this.ListIDUsuario += this.UsuariosID.get(index);
       }
       for(int i = 0 ; i < this.jListClientes.getSelectedIndices().length ; i++){
       int index = this.jListClientes.getSelectedIndices()[i];
       System.out.println(" - "+index);
           if(i == 0){
               this.ListIDCliente += "";
           }else{
               this.ListIDCliente += ", ";
           }
           this.ListIDCliente += this.ClientesID.get(index);
           System.out.println(" - "+this.ClientesID.get(index));
       }
       //this.ListUsuarioID = this.jListUsuarios.getSelectedIndices();
       //this.ListClienteID = this.jListClientes.getSelectedIndices();
    }
    public void mostrarOpcionNCF(){
        Log.Registrar(this.getClass(), "mostrarOpcionNCF", "");
        this.tipoReporte = this.jComboBox1.getSelectedItem().toString();
        switch(this.tipoReporte){
            case "FACTURA":
                this.jCheckBox1.setVisible(true);
                break;
            case "REPARACIÓN":
                this.jCheckBox1.setVisible(false);
                break;
            case "COTIZACIÓN":
                this.jCheckBox1.setVisible(false);
                break;        
            default:
                this.jCheckBox1.setVisible(false);
                break;
        }
    }
     public void cargarUsuarios(){
                           Log.Registrar(this.getClass(), "cargarUsuarios", "");
       
            String table_name = " usuario ";
            String campos = "id,nombre_usuario ";
            String otros = " ";// where  tipo_usuario  <> 'admin'
            
           java.sql.ResultSet resultSet = this.mysql.optenerDatosParaTabla(table_name, campos, otros);
        try {
            resultSet.beforeFirst();
             ArrayList ListItem = new ArrayList();
             
            DefaultListModel modelo = new DefaultListModel();
            while(resultSet.next()){
                modelo.addElement(resultSet.getString("nombre_usuario"));
                this.UsuariosID.add(resultSet.getInt("id"));
                //ListItem.add(resultSet.getString("nombre_usuario"));
            } 
            this.jListUsuarios.setModel(modelo);
        } catch (SQLException ex) {
            Log.Error(this.getClass().getName(), ex);
            Logger.getLogger(ListadoProducto.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    public void cargarClientes(){
                      Log.Registrar(this.getClass(), "cargarClientes", "");
       
            String table_name = " cliente as c inner join persona as p on p.id = c.persona_id ";
            String campos = "c.id,concat_ws(' ',p.nombre,' ',p.apellido,' Cedula: ',p.cedula,' RNC: ',p.rnc) as cliente ";
            String otros = " ";// where  tipo_usuario  <> 'admin'
            
           java.sql.ResultSet resultSet = this.mysql.optenerDatosParaTabla(table_name, campos, otros);
        try {
            resultSet.beforeFirst();
             ArrayList ListItem = new ArrayList();
             
            DefaultListModel modelo = new DefaultListModel();
            while(resultSet.next()){
                modelo.addElement(resultSet.getString("cliente"));
                this.ClientesID.add(resultSet.getInt("id"));
                System.out.println(resultSet.getInt("id"));
                //ListItem.add(resultSet.getString("nombre_usuario"));
            } 
            this.jListClientes.setModel(modelo);
        } catch (SQLException ex) {
            Log.Error(this.getClass().getName(), ex);
            Logger.getLogger(ListadoProducto.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    public void CrearConsulta(){
         Log.Registrar(this.getClass(), "CrearConsulta", "");

        this.getDatosBase();
        String where = " where (estado   <> 'anulada')";
        String tabla = "";
        boolean entrar = false;
        switch(this.tipoReporte){
            case "FACTURA":
                tabla = " factura as f ";
                if(this.jCheckBox1.isSelected())
                    this.soloConNCF = true;
                else
                    this.soloConNCF = false;
                this.campoNCF = " ,f.numero_comprovante_fiscal as ncf ";
                break;
            case "REPARACIÓN":
                tabla = " reparacion as f ";
                this.soloConNCF = false;
                this.campoNCF = " '' as ncf ";
                break;
            case "COTIZACIÓN":
                tabla = " cotizacion as f ";
                this.soloConNCF = false;
                this.campoNCF = " '' as ncf ";
                break;        
        }
        if(this.soloConNCF){
            if(entrar){
                where += "";
            }else{
                where += " and ";
            }
            where += " ( f.tiene_comprovante_fiscal <> '0' )"; 
        }
        if(!(this.ListIDUsuario.isEmpty())){
            if(entrar){
                where += "";
            }else{
                where += " and ";
            }
            where += "(f.usuario_id in ("+this.ListIDUsuario+"))"; 
        }
        if(!(this.ListIDCliente.isEmpty())){
            if(entrar){
                where += "";
            }else{
                where += " and ";
            }
            where += "(f.cliente_id in ("+this.ListIDCliente+"))"; 
        }
        if( ( !(this.fechaDesde.isEmpty()) && !(this.fechaHasta.isEmpty()) ) ){
            if(entrar){
                where += "";
            }else{
                where += " and ";
            }
            
            where += "(date(f.fecha_creada) between '"+this.fechaDesde+"' and '"+this.fechaHasta+"' )"; 
        }
        
        tabla += " inner join cliente as c on f.cliente_id = c.id inner join persona as p on p.id = c.persona_id inner join "
                + "usuario as u on u.id = f.usuario_id ";
        
        this.procesarConsultaCreada(tabla, where);
        this.View();
        //fecha_creada  
        //estado   <> 'anulada'
        //usuario_id
    }
    
    public void procesarConsultaCreada(String tabla,String where){
          Log.Registrar(this.getClass(), "procesarConsultaCreada", "");
            String campos = " f.sub_total,f.itbis,f.total,f.id,concat(p.nombre,' ',p.apellido,'(',p.cedula,')','(',p.rnc,')') as nombrecliente,u.nombre_usuario as nombreusuario "+this.campoNCF+" ";
           java.sql.ResultSet resultSet = this.mysql.optenerDatosParaTabla(tabla, campos, where);
        try {
            if(resultSet.first()){
                try {
                    resultSet.beforeFirst();
                    ArrayList ListItem = new ArrayList();
                    DefaultListModel modelo = new DefaultListModel();
                    this.Facturas = new LinkedList();
                    //int no_factura = 0;
                    this.TotalFactura = 0;
                    this.TotalItbisFactura = 0.0;
                    this.TotalMontoFactura = 0.0;
                    this.TotalSubMontoFactura = 0.0;
                    while(resultSet.next()){
                        //no_factura++;
                        this.TotalFactura++;
                        
                        this.TotalItbisFactura += resultSet.getDouble("itbis");
                        this.TotalMontoFactura += resultSet.getDouble("total");
                        this.TotalSubMontoFactura += resultSet.getDouble("sub_total");
                        this.Facturas.add(new ModeloReporteFactura(resultSet.getString("id"),resultSet.getString("sub_total"),resultSet.getString("total"),resultSet.getString("itbis"),resultSet.getString("nombrecliente"),resultSet.getString("nombreusuario"),""+this.TotalFactura,resultSet.getString("ncf")));
                        //modelo.addElement(resultSet.getString("cliente"));
                        //this.ClientesID.add(resultSet.getInt("id"));
                        //ListItem.add(resultSet.getString("nombre_usuario"));
                    }
                    //this.jListClientes.setModel(modelo);
                } catch (SQLException ex) {
                    Log.Error(this.getClass().getName(), ex);
                    Logger.getLogger(ListadoProducto.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else{
                JOptionPane.showMessageDialog(null, "No hay registro");
                if(this.Facturas != null){
                    this.Facturas.clear();
                }
            }
        } catch (SQLException ex) {
            Log.Error(this.getClass().getName(), ex);
            Logger.getLogger(GeneradorReportes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void View(){
                 Log.Registrar(this.getClass(), "View", "");

        try {
           // String ruta = getClass().getResource("../theme/cotizacion.jasper");
            //System.out.println(ruta);
           // JasperReport loadObject = (JasperReport) JRLoader.loadObject(ruta);
           URL ruta = new URL("file:///C:/app_vyr/theme/PlantillaReporte.jasper");
            JasperReport loadObject = (JasperReport) JRLoader.loadObject(ruta); 
           //JasperReport loadObject =(JasperReport) JRLoader.loadObject(getClass().getResource("../theme/PlantillaReporte.jasper"));
           //JasperReport loadObject = (JasperReport) JRLoader.loadObject("C:\\vyrarchivos\\cotizacion.jasper");
           //"C:/vyr/img/iconoclinica.gif"
             Map parameters = new HashMap<String, Object>();
            JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(this.Facturas); 
            //System.out.println(getClass().getResource("../icono/iconoclinica.gif"));
            parameters.put("logo_empresa", Empresa.logo);
            parameters.put("nombre_empresa", "V & R");
            parameters.put("rnc_empresa", Empresa.rnc);
            parameters.put("eslogan_empresa", "SERVICIOS DE HERRAMIENTAS");
            
            parameters.put("sub_total","$ "+this.TotalSubMontoFactura);
            parameters.put("monto_total","$ "+this.TotalMontoFactura);
            parameters.put("itbis", "$ "+this.TotalItbisFactura);
            parameters.put("catidad_total", "  "+this.TotalFactura);
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
           System.out.println("error");
            System.out.println(ex.getMessage());
            System.out.println(ex.getCause());
            //Logger.getLogger(Cotizacion.class.getName()).log(Level.SEVERE, null, ex);
        }catch (Exception ex) {
            Log.Error(this.getClass().getName(), ex);
           System.out.println("error");
            System.out.println(ex.getCause());
            //Logger.getLogger(Cotizacion.class.getName()).log(Level.SEVERE, null, ex);
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

        jPanel1 = new javax.swing.JPanel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jListClientes = new javax.swing.JList<>();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jListUsuarios = new javax.swing.JList<>();
        jDateChooserFechaDesde = new com.toedter.calendar.JDateChooser();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jDateChooserFechaHasta = new com.toedter.calendar.JDateChooser();
        jButton1 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jCheckBox1 = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Generador de reporte");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "FACTURA", "REPARACIÓN", "COTIZACIÓN" }));
        jComboBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox1ItemStateChanged(evt);
            }
        });
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jLabel1.setText("Tipo de reporte");

        jLabel2.setText("Cliente");

        jScrollPane1.setViewportView(jListClientes);

        jLabel3.setText("Usuario");

        jScrollPane2.setViewportView(jListUsuarios);

        jLabel5.setText("Fecha desde");

        jLabel6.setText("Fecha hasta");

        jButton1.setBackground(new java.awt.Color(0, 153, 102));
        jButton1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Generar reporte");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });

        jButton4.setBackground(new java.awt.Color(0, 51, 204));
        jButton4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setText("Agregar nota");
        jButton4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton4MouseClicked(evt);
            }
        });

        jCheckBox1.setBackground(new java.awt.Color(255, 255, 255));
        jCheckBox1.setText("Solo con NCF");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 460, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(2, 2, 2)
                            .addComponent(jLabel1)
                            .addGap(150, 150, 150)
                            .addComponent(jLabel3))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel5)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGap(2, 2, 2)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jComboBox1, 0, 218, Short.MAX_VALUE)
                                            .addComponent(jLabel6)
                                            .addComponent(jDateChooserFechaHasta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jCheckBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(jDateChooserFechaDesde, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE))
                        .addComponent(jLabel2)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 440, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jButton4)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton1)))
                    .addContainerGap()))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 406, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton4)
                        .addComponent(jButton1))
                    .addGap(12, 12, 12)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel1)
                        .addComponent(jLabel3))
                    .addGap(6, 6, 6)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(5, 5, 5)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(5, 5, 5)
                            .addComponent(jCheckBox1)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jDateChooserFechaDesde, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel6)
                            .addGap(6, 6, 6)
                            .addComponent(jDateChooserFechaHasta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE))
                    .addGap(18, 18, 18)
                    .addComponent(jLabel2)
                    .addGap(6, 6, 6)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
   
    private void jButton4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton4MouseClicked
        // TODO add your handling code here:
        this.nota = JOptionPane.showInputDialog(null, "Ingrese su Nota", "Agregar Nota al reporte a generar", JOptionPane.INFORMATION_MESSAGE);

    }//GEN-LAST:event_jButton4MouseClicked

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        // TODO add your handling code here:
        this.CrearConsulta();
    }//GEN-LAST:event_jButton1MouseClicked

    private void jComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox1ItemStateChanged
        // TODO add your handling code here:

    }//GEN-LAST:event_jComboBox1ItemStateChanged

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
                //JOptionPane.showMessageDialog(null, "prueba");
        this.mostrarOpcionNCF();
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        // TODO add your handling code here:
        instancia = null;
    }//GEN-LAST:event_formWindowClosed

    /**
     * @param args the command line arguments
     */


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton4;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JComboBox<String> jComboBox1;
    private com.toedter.calendar.JDateChooser jDateChooserFechaDesde;
    private com.toedter.calendar.JDateChooser jDateChooserFechaHasta;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JList<String> jListClientes;
    private javax.swing.JList<String> jListUsuarios;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}
