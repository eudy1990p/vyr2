/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

/**
 *
 * @author VOSTRO
 */
public class ModeloReporteFactura {
   private String codigo_factura,sub_monto_factura,monto_factura,itbis_factura,cliente_factura,usuario_factura,no_factura,ncf_factura;

    public String getNcf_factura() {
        return ncf_factura;
    }

    public void setNcf_factura(String ncf_factura) {
        this.ncf_factura = ncf_factura;
    }

    public String getCodigo_factura() {
        return codigo_factura;
    }

    public void setCodigo_factura(String codigo_factura) {
        this.codigo_factura = codigo_factura;
    }

    public String getSub_monto_factura() {
        return sub_monto_factura;
    }

    public void setSub_monto_factura(String sub_monto_factura) {
        this.sub_monto_factura = sub_monto_factura;
    }

    public String getMonto_factura() {
        return monto_factura;
    }

    public void setMonto_factura(String monto_factura) {
        this.monto_factura = monto_factura;
    }

    public String getItbis_factura() {
        return itbis_factura;
    }

    public void setItbis_factura(String itbis_factura) {
        this.itbis_factura = itbis_factura;
    }

    public String getCliente_factura() {
        return cliente_factura;
    }

    public void setCliente_factura(String cliente_factura) {
        this.cliente_factura = cliente_factura;
    }

    public String getUsuario_factura() {
        return usuario_factura;
    }

    public void setUsuario_factura(String usuario_factura) {
        this.usuario_factura = usuario_factura;
    }

    public String getNo_factura() {
        return no_factura;
    }

    public void setNo_factura(String no_factura) {
        this.no_factura = no_factura;
    }
   
    public ModeloReporteFactura (String codigo_factura,String sub_monto_factura,String monto_factura,String itbis_factura,String cliente_factura,String usuario_factura,String no_factura,String ncf_factura){
        this.no_factura = no_factura;
        this.codigo_factura = codigo_factura;
        this.usuario_factura = usuario_factura;
        this.cliente_factura = cliente_factura;
        this.sub_monto_factura = "$ "+sub_monto_factura;
        this.itbis_factura = "$ "+itbis_factura;
        this.monto_factura = "$ "+monto_factura;
        this.ncf_factura = ncf_factura;
        
    }
}
