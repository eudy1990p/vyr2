/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formularios.Conduce;

import Clases.Texto;
import ClassStatic.Log;
import conexion.Mysql;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Eudy
 */
public class PasoReparacionSeguimiento extends javax.swing.JFrame {

     private Mysql mysql;
     private String estado = "pendiente";
     private String clausula = "";
     private String reparacion_detalle_id = "",usuario_id = "1";

    
     private String nota = "",precioCompleto = "0.00";
     private String[] TiposNotas = {"","","","",""};
     private String[] TiposNotas1 = {"","","","","","","","","","","","","","",""};
     private String item_id = "";
     private boolean activarSelect = false;
     private Reparacion reparacion = null;
    /**
     * Creates new form PasoReparacionSeguimiento
     */
     public void setPadre(Reparacion reparacion){
         this.reparacion = reparacion;
     }
     public void setReparacion_detalle_id(String reparacion_detalle_id) {
        this.reparacion_detalle_id = reparacion_detalle_id;
    }
    public PasoReparacionSeguimiento(Mysql mysql) {
        initComponents();
         this.mysql = mysql;
    }
    public void validarSelect(){             
        Log.Registrar(this.getClass(), "validarSelect", "");

        this.estado = this.JCBestado.getSelectedItem().toString();
        this.precioCompleto = this.JTFPrecioFinal.getText();
    }
    public void obtenerDatosDB(){     
        Log.Registrar(this.getClass(), "obtenerDatosDB", "");

        this.activarSelect = false;
         String table_name = " reparacion_detalle as c ";
         String campos = " c.* ";
         String otros = " where c.id = '"+this.item_id+"' ";
         java.sql.ResultSet resultSet = this.mysql.optenerDatosParaTabla(table_name, campos, otros);
         String codigo,monto,fecha,cantidad,precio,nombre,precio_completo;
         try {
             if(resultSet.next()){
                 codigo = item_id;
                     monto = Texto.validarNull(resultSet.getString("total"));
                     precio = Texto.validarNull(resultSet.getString("precio"));
                    precio_completo = Texto.validarNull(resultSet.getString("precio_completado"));
                    this.precioCompleto = precio_completo;
                     estado = Texto.validarNull(resultSet.getString("estado"));
                     fecha =Texto.validarNull(resultSet.getString("fecha_creada"));
                      cantidad =Texto.validarNull(resultSet.getString("cantidad"));
                      nombre =Texto.validarNull(resultSet.getString("nombre"));
                   this.TiposNotas[0] = Texto.validarNull(resultSet.getString("nota"));
                   this.TiposNotas[1] = Texto.validarNull(resultSet.getString("nota_procesando"));
                   this.TiposNotas[2] = Texto.validarNull(resultSet.getString("nota_completado"));
                   this.TiposNotas[3] = Texto.validarNull(resultSet.getString("nota_incompleto_por"));
                   this.TiposNotas[4] = Texto.validarNull(resultSet.getString("comentario_anulado"));
                 setDatosItemDetalle(precio,cantidad, nombre, codigo, monto,estado,precio_completo);
                 
             }
         } catch (SQLException ex) {
                         Log.Error(this.getClass().getName(), ex);

             Logger.getLogger(PasoReparacionSeguimiento.class.getName()).log(Level.SEVERE, null, ex);
         }
         this.activarSelect = true;
    }
    public void obtenerDatosDB(String item_id){      
        Log.Registrar(this.getClass(), "obtenerDatosDB", "");

        this.item_id =item_id;
        this.reparacion_detalle_id =item_id;
        this.activarSelect = false;
         String table_name = " reparacion_detalle as c ";
         String campos = " c.* ";
         String otros = " where c.id = '"+this.item_id+"' ";
         java.sql.ResultSet resultSet = this.mysql.optenerDatosParaTabla(table_name, campos, otros);
         String codigo="",monto="",fecha="",cantidad="",precio="",nombre="",precio_completo="";
         try {
             

             if(resultSet.next()){
                 System.out.println("prueba");
                 codigo = item_id;
                     monto = Texto.validarNull(resultSet.getString("total"));
                     precio = Texto.validarNull(resultSet.getString("precio"));
                    precio_completo = Texto.validarNull(resultSet.getString("precio_completado"));
                    this.precioCompleto = precio_completo;
                     estado = Texto.validarNull(resultSet.getString("estado"));
                     fecha =Texto.validarNull(resultSet.getString("fecha_creada"));
                      cantidad =Texto.validarNull(resultSet.getString("cantidad"));
                      nombre =Texto.validarNull(resultSet.getString("nombre"));
                   
                   this.TiposNotas[0] = Texto.validarNull(resultSet.getString("nota"));
                   this.TiposNotas[1] = Texto.validarNull(resultSet.getString("nota_procesando"));
                   this.TiposNotas[2] = Texto.validarNull(resultSet.getString("nota_completado"));
                   this.TiposNotas[3] = Texto.validarNull(resultSet.getString("nota_incompleto_por"));
                   this.TiposNotas[4] = Texto.validarNull(resultSet.getString("comentario_anulado"));
                 
                    
             }
             //this.setDatosItemDetalle(precio,cantidad, nombre, codigo, monto,estado,precio_completo);
                
             System.out.println("========prueba");
         } catch (SQLException ex) {
                         Log.Error(this.getClass().getName(), ex);

             Logger.getLogger(PasoReparacionSeguimiento.class.getName()).log(Level.SEVERE, null, ex);
         }
        this.setDatosItemDetalle(precio,cantidad, nombre, codigo, monto,estado,precio_completo);
        this.activarSelect = true;
    }
    public void setDatosItemDetalle(String precio,String cantidad,String Nombre, String Codigo,String montoTotal,String estado,String precioCompletado){
        System.out.println("setDatosItemDetalle");
        this.JLPrecioTotal.setText(montoTotal);
        this.JLCodigo.setText(Codigo);
        this.JLPrecio.setText(precio);
        this.JTFPrecioFinal.setText(precioCompletado);
        this.JLCantidad.setText(cantidad);
        this.JLNombre.setText(Nombre);
        this.JCBestado.setSelectedItem(estado);
        validarEstadoDB(estado);
       // this.validarEstadoDB(estado,this.TiposNotas[0],this.TiposNotas[3],this.TiposNotas[2],this.TiposNotas[4],this.TiposNotas[1]);
    }
    
    public void validarEstado(){       
        Log.Registrar(this.getClass(), "obtenerDatosDB1", "");

        this.validarSelect();
         this.clausula = " ";
        this.clausula += " estado = '"+this.estado+"', precio_completado = '"+precioCompleto+"' ";
        this.clausula += (precioCompleto.isEmpty())?"":", total = cantidad * "+precioCompleto;
        switch(this.estado){
            case "pendiente":
               this.clausula += ",nota = '"+nota+"' ";
                break;
             case "procesando":
               this.clausula += ",nota_procesando = '"+nota+"', usuario_id_procesando = '"+usuario_id+"', fecha_procesado = now() ";                
                break;
            case "completado":
                this.clausula += ",nota_completado = '"+nota+"', usuario_id_completado = '"+usuario_id+"', fecha_completado = now() ";                
                break;
            case "incompleto":
                this.clausula += ",nota_incompleto_por = '"+nota+"', usuario_id_incompleto_por = '"+usuario_id+"', fecha_incompleto = now() ";                                
                break;
            case "anulada":
                this.clausula += ",comentario_anulado = '"+nota+"', usuario_id_anulado = '"+usuario_id+"', fecha_anulado = now() ";                                
                break;
        }
    }
     public void validarEstadoDB(String estado){      
         Log.Registrar(this.getClass(), "validarEstadoDB", "");

       System.out.println("validarEstadoDB");
       //this.TiposNotas[0],this.TiposNotas[3],this.TiposNotas[2],this.TiposNotas[4],this.TiposNotas[1]
       String notaPendiente=this.TiposNotas[0];
       String notaIncompleto=this.TiposNotas[3];
       String notaCompleto=this.TiposNotas[2];
       String notaAnulado=this.TiposNotas[4];
       String notaProcesando=this.TiposNotas[1];
       this.JTANota.setText("notaPendiente"+notaPendiente);
       
        switch(estado){
            case "pendiente":
                this.JTANota.setText(notaPendiente);
                 break;
             case "procesando":
                 this.JTANota.setText(notaProcesando);
                 break;
            case "completado":
                this.JTANota.setText(notaCompleto);
                break;
            case "incompleto":
                this.JTANota.setText(notaIncompleto);
                break;
            case "anulada":
                this.JTANota.setText(notaAnulado);
                break;
        }
    }
    public void validarEstadoDB(String estado,String notaPendiente,String notaIncompleto,String notaCompleto,String notaAnulado,String notaProcesando){
             Log.Registrar(this.getClass(), "validarEstadoDB", "");
   System.out.println("validarEstadoDB");
       
       this.JTANota.setText("notaPendiente"+notaPendiente);
       

       
       
        switch(estado){
            case "pendiente":
                this.JTANota.setText(notaPendiente);
                this.JTANota.setVisible(true);
                break;
             case "procesando":
                 this.JTANota.setText(notaProcesando);
                 //this.JTANotaProcesando.setVisible(true);
                 break;
            case "completado":
                this.JTANota.setText(notaCompleto);
                //this.JTANotaCompletado.setVisible(true);
                break;
            case "incompleto":
                this.JTANota.setText(notaIncompleto);
                //this.JTANotaIncompleto.setVisible(true);
                break;
            case "anulada":
                this.JTANota.setText(notaAnulado);
                //this.JTANotaAnulado.setVisible(true);
                break;
        }
    }
    public void MostrarNotasPorEstado(String estado){                    
        Log.Registrar(this.getClass(), "MostrarNotasPorEstado", "");

        switch(estado){
            case "pendiente":
                this.JTANota.setVisible(true);
                System.out.println("Pendiente");
                break;
             case "procesando":
                 this.JTANota.setVisible(true);
                 System.out.println("procesando");
                 break;
            case "completado":
                this.JTANota.setVisible(true);
                System.out.println("completado");
                break;
            case "incompleto":
                this.JTANota.setVisible(true);
                System.out.println("incompleto");
                break;
            case "anulada":
                this.JTANota.setVisible(true);
                System.out.println("anulada");
                break;
        }
    }
    public void actualizarDato(){       
        Log.Registrar(this.getClass(), "actualizarDato", "");

        this.nota = this.JTANota.getText();
        this.estado = this.JCBestado.getSelectedItem().toString();
        this.validarEstado();
       boolean r = this.mysql.actulizarDatos("reparacion_detalle",this.clausula, " id = '"+reparacion_detalle_id+"' ");
       this.actualizarReparacion();
       if(r){
           if (this.reparacion != null) {
               this.reparacion.recargarDatos();
           }
           JOptionPane.showMessageDialog(null, "Se guardaron los datos", "Datos Guardados", JOptionPane.INFORMATION_MESSAGE);
       }else{
       JOptionPane.showMessageDialog(null, "No se guardaron los datos", "Error", JOptionPane.WARNING_MESSAGE);
       }
    }
    public void actualizarReparacion(){      
        Log.Registrar(this.getClass(), "actualizarReparacion", "");

        String tabla = "reparacion";
        String montoCalculado = "(SELECT "
                + "SUM( "
                + "     IF( "
                + "         ifnull(reparacion_detalle.precio_completado,0) <> 0, "
                + "         reparacion_detalle.precio_completado * reparacion_detalle.cantidad "
                + "         ,reparacion_detalle.precio * reparacion_detalle.cantidad "
                + "        ) "
                + "   ) "
                + " FROM `reparacion_detalle` "
                + "  WHERE reparacion_detalle.reparacion_id = "
                + "     ( select reparacion_detalle.reparacion_id from reparacion_detalle " +
                "       where reparacion_detalle.id = '"+reparacion_detalle_id+"') " +
                "   ) ";
        String camposModificar = "reparacion.total =  "+montoCalculado+
                 " , reparacion.sub_total = "+montoCalculado
                ;
        String where = " reparacion.id = " +
                        "( " +
                        "    select reparacion_detalle.reparacion_id " +
                        "    FROM reparacion_detalle WHERE reparacion_detalle.id = '"+reparacion_detalle_id+"' " +
                        ")  ";
        this.mysql.actulizarDatos(tabla,camposModificar, where);
    }
    
    /*
    update reparacion set reparacion.total = 
(SELECT 
SUM(
    IF(
        ifnull(reparacion_detalle.precio_completado,0) <> 0,
        reparacion_detalle.precio_completado
        ,reparacion_detalle.precio
    )
)
FROM `reparacion_detalle`  
WHERE 
reparacion_detalle.reparacion_id 
=
(select reparacion_detalle.reparacion_id from reparacion_detalle
where reparacion_detalle.id = 8)
) 
WHERE reparacion.id = 
(
    select reparacion_detalle.reparacion_id 
    FROM reparacion_detalle WHERE reparacion_detalle.id = 8 
)
    */
    
    

















    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        JTANota = new javax.swing.JTextArea();
        jLabel9 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        JCBestado = new javax.swing.JComboBox<>();
        JTFPrecioFinal = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        JBGuardar = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLEstadoR = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        JLCodigoR = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        JLNombreClienteR = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        JLMontoR = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLAbonoR = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        JLCantidadEquiposR = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jPanel4 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        JLCodigo = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        JLNombre = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        JLCantidad = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        JLPrecio = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        JLPrecioTotal = new javax.swing.JLabel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel5 = new javax.swing.JPanel();
        jLNotaPendiente = new javax.swing.JLabel();
        jLFechaPendiente = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLFechaProcesado = new javax.swing.JLabel();
        jLNotaProcesando = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLNotaCompleto = new javax.swing.JLabel();
        jLFechaCompleto = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jLNotaIncompleto = new javax.swing.JLabel();
        jLFechaIncompleto = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jLNotaAnulado = new javax.swing.JLabel();
        jLFechaAnulado = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        JLEstado = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        JLMonto = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        LBClienteID = new javax.swing.JLabel();
        LBNombreCliente = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Seguimiento del equipo");

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        JTANota.setColumns(20);
        JTANota.setRows(5);
        jScrollPane1.setViewportView(JTANota);

        jLabel9.setText("NOTA PARA EL ESTADO");

        jLabel7.setText("ESTADO");

        JCBestado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "procesando", "pendiente", "completado", "incompleto", "anulada" }));
        JCBestado.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                JCBestadoItemStateChanged(evt);
            }
        });

        JTFPrecioFinal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JTFPrecioFinalActionPerformed(evt);
            }
        });

        jLabel8.setText("PRECIO FINAL");

        JBGuardar.setBackground(java.awt.Color.blue);
        JBGuardar.setForeground(new java.awt.Color(255, 255, 255));
        JBGuardar.setText("Guardar Cambios");
        JBGuardar.setToolTipText("Cuando le de clic a este boton se guardara la nota y si ha ingresado el precio final ese sera el precio que se va a facturar.");
        JBGuardar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        JBGuardar.setDefaultCapable(false);
        JBGuardar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JBGuardarMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(JBGuardar)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel7)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(JCBestado, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel9)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 217, Short.MAX_VALUE)
                            .addComponent(jLabel8)
                            .addComponent(JTFPrecioFinal))))
                .addGap(0, 0, 0))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JCBestado, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel9)
                .addGap(6, 6, 6)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(JTFPrecioFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(JBGuardar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setToolTipText("");

        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel11.setText("ESTADO");
        jLabel11.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        jLEstadoR.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLEstadoR.setText("jLabel1");

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel5.setText("CODIGO");
        jLabel5.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        JLCodigoR.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        JLCodigoR.setText("jLabel2");

        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel12.setText("NOMBRE ");
        jLabel12.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        JLNombreClienteR.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        JLNombreClienteR.setText("jLabel1");

        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel14.setText("MONTO");
        jLabel14.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        JLMontoR.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        JLMontoR.setText("jLabel5");

        jLabel15.setText("ABONO");
        jLabel15.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        jLAbonoR.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLAbonoR.setText("jLabel2");

        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("CANTIDAD DE EQUIPOS");
        jLabel16.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        JLCantidadEquiposR.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        JLCantidadEquiposR.setText("jLabel11");
        JLCantidadEquiposR.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel17.setText("COMENTARIO");
        jLabel17.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane2.setViewportView(jTextArea1);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(89, 89, 89)
                        .addComponent(JLNombreClienteR, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(94, 94, 94)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(JLCodigoR, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLEstadoR, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(29, 29, 29)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(JLMontoR, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLAbonoR, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(JLCantidadEquiposR, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane2)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(JLCodigoR))
                .addGap(11, 11, 11)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLEstadoR))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(JLNombreClienteR))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(JLMontoR))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLAbonoR))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(JLCantidadEquiposR))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Datos General Conduce", null, jPanel3, "Aquí se muestran los datos generales de la reparación. ");

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jLabel4.setText("CODIGO");

        JLCodigo.setText("jLabel2");

        jLabel3.setText("NOMBRE ");

        JLNombre.setText("jLabel1");

        jLabel10.setText("CANTIDAD");

        JLCantidad.setText("jLabel11");

        jLabel6.setText("PRECIO");

        JLPrecio.setText("jLabel5");

        jLabel13.setText("PRECIO FINAL");

        JLPrecioTotal.setText("PRECIO TOTAL");

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        jLNotaPendiente.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLNotaPendiente.setText("jLabel9");
        jLNotaPendiente.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        jLFechaPendiente.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLFechaPendiente.setText("jLabel5");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLNotaPendiente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addGap(0, 253, Short.MAX_VALUE)
                        .addComponent(jLFechaPendiente, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addComponent(jLFechaPendiente)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLNotaPendiente, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane2.addTab("Pendiente", null, jPanel5, "Pendiente aun no se ha trabajado el equipo");

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        jLFechaProcesado.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLFechaProcesado.setText("jLabel5");

        jLNotaProcesando.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLNotaProcesando.setText("jLabel9");
        jLNotaProcesando.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addGap(0, 243, Short.MAX_VALUE)
                        .addComponent(jLFechaProcesado, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLNotaProcesando, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLFechaProcesado)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLNotaProcesando, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane2.addTab("Procesando", null, jPanel6, "Cuando se esta trabajando el equipo pero aun no se completado la reaparación");

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));

        jLNotaCompleto.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLNotaCompleto.setText("jLabel9");
        jLNotaCompleto.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        jLFechaCompleto.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLFechaCompleto.setText("jLabel5");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                        .addContainerGap(253, Short.MAX_VALUE)
                        .addComponent(jLFechaCompleto, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLNotaCompleto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addComponent(jLFechaCompleto)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLNotaCompleto, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane2.addTab("Completo", null, jPanel8, "Cuando el equipo fue reparado de forma exitosa.");

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));

        jLNotaIncompleto.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLNotaIncompleto.setText("jLabel9");
        jLNotaIncompleto.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        jLFechaIncompleto.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLFechaIncompleto.setText("jLabel5");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                        .addContainerGap(253, Short.MAX_VALUE)
                        .addComponent(jLFechaIncompleto, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLNotaIncompleto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addComponent(jLFechaIncompleto)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLNotaIncompleto, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane2.addTab("Incompleto", null, jPanel9, "Si el equipo no se puede reparar por alguna razon esta se puede ver aqui");

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));

        jLNotaAnulado.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLNotaAnulado.setText("jLabel9");
        jLNotaAnulado.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        jLFechaAnulado.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLFechaAnulado.setText("jLabel5");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                        .addContainerGap(253, Short.MAX_VALUE)
                        .addComponent(jLFechaAnulado, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLNotaAnulado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addComponent(jLFechaAnulado)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLNotaAnulado, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane2.addTab("Anulado", null, jPanel10, "Cuando se anula una reparación para un equipo");

        jLabel18.setText("ESTADO ACTUAL");

        JLEstado.setText("jLabel2");

        jLabel19.setText("MONTO");

        JLMonto.setText("jLabel2");

        jLabel20.setText("HISTORIAL ESTADOS");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTabbedPane2)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel19)
                                    .addComponent(jLabel13))
                                .addGap(30, 30, 30))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel18)
                                .addGap(18, 18, 18)))
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(JLMonto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(JLEstado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(JLPrecioTotal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(jLabel6)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addGap(48, 48, 48)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(JLCodigo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(JLNombre, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(JLPrecio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(JLCantidad, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel20)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(JLCodigo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(JLNombre)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(JLCantidad))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(JLPrecio))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(JLPrecioTotal))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(JLMonto))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(JLEstado))
                .addGap(17, 17, 17)
                .addComponent(jLabel20)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jTabbedPane1.addTab("Datos Del Equipo", null, jPanel4, "Aquí se muestran los datos del equipo que se ha seleccionado para registrar el avance o cambiar lo de estado.");

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setText("Nombre Completo");

        jLabel2.setText("ID");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 415, Short.MAX_VALUE)
                    .addComponent(LBClienteID, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(LBNombreCliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(LBClienteID, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(LBNombreCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(215, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Datos Del Cliente", jPanel7);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 440, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void JTFPrecioFinalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JTFPrecioFinalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JTFPrecioFinalActionPerformed

    private void JBGuardarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JBGuardarMouseClicked
        // TODO add your handling code here:
        this.actualizarDato();
        this.setVisible(false);
    }//GEN-LAST:event_JBGuardarMouseClicked

    private void JCBestadoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_JCBestadoItemStateChanged
        // TODO add your handling code here:
        if(this.activarSelect){
           // this.obtenerDatosDB();
           this.validarEstadoDB(this.JCBestado.getSelectedItem().toString());
           //this.MostrarNotasPorEstado(this.JCBestado.getSelectedItem().toString());
           System.out.println("se esta cambiando el checkbox");
        }
    }//GEN-LAST:event_JCBestadoItemStateChanged

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton JBGuardar;
    private javax.swing.JComboBox<String> JCBestado;
    private javax.swing.JLabel JLCantidad;
    private javax.swing.JLabel JLCantidadEquiposR;
    private javax.swing.JLabel JLCodigo;
    private javax.swing.JLabel JLCodigoR;
    private javax.swing.JLabel JLEstado;
    private javax.swing.JLabel JLMonto;
    private javax.swing.JLabel JLMontoR;
    private javax.swing.JLabel JLNombre;
    private javax.swing.JLabel JLNombreClienteR;
    private javax.swing.JLabel JLPrecio;
    private javax.swing.JLabel JLPrecioTotal;
    private javax.swing.JTextArea JTANota;
    private javax.swing.JTextField JTFPrecioFinal;
    private javax.swing.JLabel LBClienteID;
    private javax.swing.JLabel LBNombreCliente;
    private javax.swing.JLabel jLAbonoR;
    private javax.swing.JLabel jLEstadoR;
    private javax.swing.JLabel jLFechaAnulado;
    private javax.swing.JLabel jLFechaCompleto;
    private javax.swing.JLabel jLFechaIncompleto;
    private javax.swing.JLabel jLFechaPendiente;
    private javax.swing.JLabel jLFechaProcesado;
    private javax.swing.JLabel jLNotaAnulado;
    private javax.swing.JLabel jLNotaCompleto;
    private javax.swing.JLabel jLNotaIncompleto;
    private javax.swing.JLabel jLNotaPendiente;
    private javax.swing.JLabel jLNotaProcesando;
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
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTextArea jTextArea1;
    // End of variables declaration//GEN-END:variables




    
    
     
   
/*    public void validarSelect(){
        //this.estado = this.JCBestado.getSelectedItem().toString();
        //this.precioCompleto = this.JTFPrecioFinal.getText();
    }*/

    public void obtenerDatosDB1(String item_id){      
        Log.Registrar(this.getClass(), "obtenerDatosDB1", "");

        this.item_id =item_id;
        this.reparacion_detalle_id =item_id;
        this.activarSelect = false;
         String table_name = " reparacion_detalle as rd inner join reparacion as r on rd.reparacion_id = r.id inner join cliente as c on r.cliente_id = c.id inner join persona as p on p.id = c.persona_id";
         String campos = " rd.*,r.id as codigo_r ,r.estado as estado_r,r.nota as nota_r,r.abono as abono_r,r.total as total_r,c.id as cod_cliente,concat(p.nombre,' ',p.apellido) as nombre_completo, (select count(*) from reparacion_detalle where reparacion_detalle.reparacion_id = r.id ) as total_equipos ";
         String otros = " where rd.id = '"+this.item_id+"' ";
         java.sql.ResultSet resultSet = this.mysql.optenerDatosParaTabla(table_name, campos, otros);
         String codigo="",
                 monto="",
                 fecha="",
                 cantidad="",
                 precio="",
                 nombre="",
                 precio_completo="",
                 codigoR="",
                 estadoR="",
                 nombreClienteR="",
                 codigoCliente="",
                 montoR="",
                 abonoR="",
                 cantidadEquipoR="",
                 notaR="";
         try {
             

             if(resultSet.next()){
                 System.out.println("prueba");
                 codigo = item_id;
                     monto = Texto.validarNull(resultSet.getString("total"));
                     precio = Texto.validarNull(resultSet.getString("precio"));
                    precio_completo = Texto.validarNull(resultSet.getString("precio_completado"));
                    this.precioCompleto = precio_completo;
                     estado = Texto.validarNull(resultSet.getString("estado"));
                     fecha =Texto.validarNull(resultSet.getString("fecha_creada"));
                      cantidad =Texto.validarNull(resultSet.getString("cantidad"));
                      nombre =Texto.validarNull(resultSet.getString("nombre"));
                      
                      codigoR =Texto.validarNull(resultSet.getString("codigo_r"));
                      estadoR =Texto.validarNull(resultSet.getString("estado_r"));
                      nombreClienteR =Texto.validarNull(resultSet.getString("nombre_completo"));
                      codigoCliente =Texto.validarNull(resultSet.getString("cod_cliente"));
                      montoR =Texto.validarNull(resultSet.getString("total_r"));
                      abonoR =Texto.validarNull(resultSet.getString("abono_r"));
                      cantidadEquipoR =Texto.validarNull(resultSet.getString("total_equipos"));
                      notaR =Texto.validarNull(resultSet.getString("nota_r"));
                   
                   this.TiposNotas1[0] = Texto.validarNull(resultSet.getString("nota"));
                   this.TiposNotas1[1] = Texto.validarNull(resultSet.getString("nota_procesando"));
                   this.TiposNotas1[2] = Texto.validarNull(resultSet.getString("nota_completado"));
                   this.TiposNotas1[3] = Texto.validarNull(resultSet.getString("nota_incompleto_por"));
                   this.TiposNotas1[4] = Texto.validarNull(resultSet.getString("comentario_anulado"));
                   this.TiposNotas1[5] = Texto.validarNull(resultSet.getString("fecha_creada"));
                   this.TiposNotas1[6] = Texto.validarNull(resultSet.getString("fecha_procesado"));
                   this.TiposNotas1[7] = Texto.validarNull(resultSet.getString("fecha_completado"));
                   this.TiposNotas1[8] = Texto.validarNull(resultSet.getString("fecha_incompleto"));
                   this.TiposNotas1[9] = Texto.validarNull(resultSet.getString("fecha_anulado"));

                    
             }
             //this.setDatosItemDetalle(precio,cantidad, nombre, codigo, monto,estado,precio_completo);
                
             System.out.println("========prueba");
         } catch (SQLException ex) {
                         Log.Error(this.getClass().getName(), ex);

             Logger.getLogger(PasoReparacionSeguimiento.class.getName()).log(Level.SEVERE, null, ex);
         }
        this.setDatosReparacion(montoR, cantidadEquipoR, nombreClienteR, codigoR, estadoR, abonoR, notaR);
        this.setDatosItemDetalle1(precio,cantidad, nombre, codigo, monto,estado,precio_completo);
        this.setDatoCliente(nombreClienteR, codigoCliente);
        this.activarSelect = true;
    }
    public void setDatosItemDetalle1(String precio,String cantidad,String Nombre, String Codigo,String montoTotal,String estado,String precioCompletado){
        System.out.println("setDatosItemDetalle");
        this.JLPrecioTotal.setText(montoTotal);
        this.JLCodigo.setText(Codigo);
        this.JLPrecio.setText(precio);
        this.JLMonto.setText(precioCompletado);
        this.JLCantidad.setText(cantidad);
        this.JLNombre.setText(Nombre);
        this.JLEstado.setText(estado);
        AsignarNotaFechaEstados();
        //validarEstadoDB(estado);
       // this.validarEstadoDB(estado,this.TiposNotas[0],this.TiposNotas[3],this.TiposNotas[2],this.TiposNotas[4],this.TiposNotas[1]);
    }
    public void setDatosReparacion(String monto,String cantidad,String Nombre, String Codigo,String estado,String abonoR,String nota){
        System.out.println("setDatosItemDetalle");
        this.JLMontoR.setText(monto);
        this.JLCodigoR.setText(Codigo);
        this.jLAbonoR.setText(abonoR);
        this.JLNombreClienteR.setText(Nombre);
        this.JLCantidadEquiposR.setText(cantidad);
        this.jTextArea1.setText(nota);
        this.jLEstadoR.setText(estado);
        //validarEstadoDB(estado);
       // this.validarEstadoDB(estado,this.TiposNotas[0],this.TiposNotas[3],this.TiposNotas[2],this.TiposNotas[4],this.TiposNotas[1]);
    }
    public void setDatoCliente(String nombre,String codigo){
        this.LBClienteID.setText(codigo);
        this.LBNombreCliente.setText(nombre);
    }
 
     public void AsignarNotaFechaEstados(){
                         Log.Registrar(this.getClass(), "AsignarNotaFechaEstados", "");

       System.out.println("validarEstadoDB");
       //this.TiposNotas[0],this.TiposNotas[3],this.TiposNotas[2],this.TiposNotas[4],this.TiposNotas[1]
       String notaPendiente=this.TiposNotas1[0];
       String fechaPendiente=this.TiposNotas1[5];
       
       String notaIncompleto=this.TiposNotas1[3];
       String fechaIncompleto=this.TiposNotas1[8];
       String notaCompleto=this.TiposNotas1[2];
       String fechaCompleto=this.TiposNotas1[7];
       String notaAnulado=this.TiposNotas1[4];
       String fechaAnulado=this.TiposNotas1[9];
       String notaProcesando=this.TiposNotas1[1];
       String fechaProcesando=this.TiposNotas1[6];
      // this.JTANota.setText("notaPendiente"+notaPendiente);
       this.jLNotaPendiente.setText(notaPendiente);
       this.jLNotaProcesando.setText(notaProcesando);
       this.jLNotaCompleto.setText(notaCompleto);
       this.jLNotaIncompleto.setText(notaIncompleto);
       this.jLNotaAnulado.setText(notaAnulado);
       
       this.jLFechaPendiente.setText(fechaPendiente);
       this.jLFechaProcesado.setText(fechaProcesando);
       this.jLFechaCompleto.setText(fechaCompleto);
       this.jLFechaIncompleto.setText(fechaIncompleto);
       this.jLFechaAnulado.setText(fechaAnulado);
       
        
    }


}
