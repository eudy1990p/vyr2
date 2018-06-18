/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formularios.Finanza;

import formularios.Factura.Facturacion;
import Clases.Texto;
import ClassStatic.Log;
import conexion.Mysql;
import formularios.ListadoProducto;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Eudy
 */
public class Pago extends javax.swing.JFrame {

    /**
     * Creates new form Pago
     */
    private Mysql mysql;
    private int totalFila;
    private Facturacion facturacion = null;
    private String balance = "0.00",cambio = "0.00",montoPagado = "0.00",balanceDespuesDelPago = "0.00",tipoPago = "";
    private String facturaID = "1",usuarioID = "1";

    public void setFacturaID(String facturaID) {
          Log.Registrar(this.getClass(), "setFacturaID", "");

        this.facturaID = facturaID;
        this.obtenerDatoFacturaAPagar();
    }
    private Facturacion ObjectFacturacion;
    
    public Pago(Mysql mysql) {
        initComponents();
        this.mysql = mysql;
    }
    public void setBalance(String balance){        
        Log.Registrar(this.getClass(), "setBalance", "");

        this.balance = balance;
        this.jLBalanceDeuda.setText("$ "+this.balance);
        this.jLCambio.setText("$ 0.00");
        this.jLPendiente.setText("$ 0.00");
    }
    public void calcularCambio(){
                          Log.Registrar(this.getClass(), "calcularCambio", "");

         this.getValores();
         //this.montoPagado = this.jTextField1.getText();
         double balance = Double.parseDouble(this.balance);
         double montoPagado = Double.parseDouble(this.montoPagado);
         if(montoPagado > balance){
             this.cambio = String.valueOf(balance - montoPagado);
             this.balanceDespuesDelPago = "0.00";
         }else{
             this.balanceDespuesDelPago = String.valueOf( montoPagado - balance);
         }
         this.setValores();
    }
    private String balanceDeudaDespuesPago = "0.00",montoDeudaFactura = "0.00";
    public void obtenerDatoFacturaAPagar(){        
        Log.Registrar(this.getClass(), "obtenerDatoFacturaAPagar", "");

            String table_name = " factura ";
            String campos = " * ";
            String otros = " where id = '"+this.facturaID+"' ";
            java.sql.ResultSet resultSet = this.mysql.optenerDatosParaTabla(table_name, campos, otros);
            try {
                resultSet.beforeFirst();
                resultSet.last();
                this.totalFila  = resultSet.getRow();
                resultSet.beforeFirst();
                int c = 0;
                if(resultSet.next()){
                    this.balanceDeudaDespuesPago = Texto.validarNull(resultSet.getString("balance_deuda"));
                    this.montoDeudaFactura = Texto.validarNull(resultSet.getString("total"));
                    c++;
                }
            } catch (SQLException ex) {
            Logger.getLogger(ListadoProducto.class.getName()).log(Level.SEVERE, null, ex);
        }
            
    }
    public void getValores(){                          
        Log.Registrar(this.getClass(), "getValores", "");

        this.tipoPago = this.jCBTipoPago.getSelectedItem().toString();
        this.montoPagado = this.jTextField1.getText();
    }
    public void setValores(){       
        Log.Registrar(this.getClass(), "setValores", "");

        this.jLBalanceDeuda.setText(this.balance);
        this.jLCambio.setText(this.cambio);
        this.jLPendiente.setText(this.balanceDespuesDelPago);
    }
    public void setObjectFacturacion(Facturacion facturacion){          
        Log.Registrar(this.getClass(), "setObjectFacturacion", "");

       this.ObjectFacturacion = facturacion;
    }
    public void insertarDBPagoFacturacion(){        
        Log.Registrar(this.getClass(), "insertarDBPagoFacturacion", "");

           
             String campos = "usuario_id,monto_pagado,balance_anterior,balance_despues_del_pago,cambio,fecha_creado,tipo_pago,factura_id ";
             String valores = "'"+this.usuarioID+"','"+this.montoPagado+"','"+this.balance+"','"+this.balanceDespuesDelPago+"','"+this.cambio+"',now(),'"+this.tipoPago+"','"+this.facturaID+"'";
             this.mysql.insertData("pagos", campos, valores);
            
    }
    public void insertarDBPagoFacturacion(String pago,String balanceAnterior,String balanceDespuesDelPago,String cambio){
        Log.Registrar(this.getClass(), "insertarDBPagoFacturacion", "");
           
             String campos = "usuario_id,monto_pagado,balance_anterior,balance_despues_del_pago,cambio,fecha_creado,tipo_pago,factura_id ";
             String valores = "'"+this.usuarioID+"','"+pago+"','"+balanceAnterior+"','"+balanceDespuesDelPago+"','"+cambio+"',now(),'"+this.tipoPago+"','"+this.facturaID+"'";
             this.mysql.insertData("pagos", campos, valores);
            
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField1 = new javax.swing.JTextField();
        jCBTipoPago = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLBalanceDeuda = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLCambio = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLPendiente = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField1KeyReleased(evt);
            }
        });

        jCBTipoPago.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "efectivo", "cheque", "tarjeta", "transferencia" }));

        jLabel1.setText("Tipo De Pago");

        jLabel2.setText("Monto A Pagar");

        jLabel3.setText("Balance Deuda");

        jLBalanceDeuda.setText("$ 12345678965");

        jLabel5.setText("Cambio");

        jLCambio.setText("$ 12345678965");

        jButton1.setText("Aplicar");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });

        jButton2.setText("Cancelar");
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton2MouseClicked(evt);
            }
        });

        jLabel4.setText("Balance Pendiente");

        jLPendiente.setText("$ 12345678965");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLBalanceDeuda))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLPendiente)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLCambio)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jCBTipoPago, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel5)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLBalanceDeuda)
                    .addComponent(jLCambio)
                    .addComponent(jLPendiente))
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCBTipoPago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyReleased
        // TODO add your handling code here:
        if(!this.jTextField1.getText().isEmpty()){
        this.calcularCambio();
        }
    }//GEN-LAST:event_jTextField1KeyReleased

    private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseClicked
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jButton2MouseClicked

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        // TODO add your handling code here:
        if(!this.jTextField1.getText().isEmpty()){
            this.calcularCambio();
            this.verificarSiHanPagadoAntes();
            //this.insertarDBPagoFacturacion();
        }else{
            JOptionPane.showMessageDialog(null, "Debe de ingresar el monto a pagar", "Monto Vacío", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_jButton1MouseClicked

    public void verificarSiHanPagadoAntes(){
                Log.Registrar(this.getClass(), "verificarSiHanPagadoAntes", "");

        this.getValores();
        double deuda = Double.parseDouble(this.balanceDeudaDespuesPago);
        double pago =  Double.parseDouble(this.montoPagado);
        double efectivo = deuda - pago;
        if(deuda > 0){
            if(efectivo <= 0 ){
                this.actualizarFacturaSaldada("0.00", pago+"", ( efectivo *(-1) )+"", "saldada");
                insertarDBPagoFacturacion(pago+"",deuda+"","0.00",( efectivo *(-1) )+"");
            }else{
                this.actualizarFacturaSaldada(efectivo+"", pago+"","0.00", "pendiente");
                insertarDBPagoFacturacion(pago+"",deuda+"",efectivo+"","0.00");
            }
            //this.insertarDBPagoFacturacion();
        }else{
            deuda =  Double.parseDouble(this.montoDeudaFactura);
            efectivo = deuda - pago;
            if(efectivo <= 0 ){
                this.actualizarFacturaSaldada("0.00", pago+"", ( efectivo *(-1) )+"", "saldada");
               // this.insertarDBPagoFacturacion();
               insertarDBPagoFacturacion(pago+"",deuda+"","0.00",( efectivo *(-1) )+"");
            }else{
                this.actualizarFacturaSaldada(efectivo+"", pago+"","0.00", "pendiente");
                insertarDBPagoFacturacion(pago+"",deuda+"",efectivo+"","0.00");
                //this.insertarDBPagoFacturacion();
            }
            //this.insertarDBPagoFacturacion();
        }
        //saldada
    }
    public void actualizarFacturaSaldada(String montoDeuda,String montoPagado,String montoEfectivo, String estado){
                  Log.Registrar(this.getClass(), "actualizarFacturaSaldada", "");

        String campos = "estado = '"+estado+"', balance_deuda = "+montoDeuda+",monto_pagado = monto_pagado + "+montoPagado+", cambio_efectivo = "+montoEfectivo+" ";
        String where = " id = '"+this.facturaID+"' ";
        this.mysql.actulizarDatos(" factura ",campos, where);
    }
    /**
     * @param args the command line arguments
     */


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox<String> jCBTipoPago;
    private javax.swing.JLabel jLBalanceDeuda;
    private javax.swing.JLabel jLCambio;
    private javax.swing.JLabel jLPendiente;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
