/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

/**
 *
 * @author Eudy
 */
public class Texto {
    
    public static String codigo_cliente = "Codigo Cliente";
    public static String cedula_cliente = "Cedula Cliente";
    public static String nombre_cliente = "Nombre Completo Cliente";
    public static String telefono_cliente = "Teléfono Cliente";  
    public static String email_cliente = "Email Cliente";    

    public static String codigo_producto = "Codigo Producto";
    public static String nombre_producto = "Nombre Producto";
    public static String precio_producto = "Precio Producto";  
    public static String cantidad_producto = "Cantidad Producto";
    
    public static String nota_producto = "Nota Producto";
    
    
    public static void placeholder(String texto1, String texto2, javax.swing.JTextField t){
        if( texto1.equals(texto2) ){
            t.setText("");
        }
    }
    public static void placeholder(String texto1, String texto2, javax.swing.JTextArea t){
        if( texto1.equals(texto2) ){
            t.setText("");
        }
    }
    public static void setPlaceholder(String texto1, String texto2, javax.swing.JTextField t){
        if( texto2.isEmpty() ){
            t.setText(texto1);
        }
    }
    public static void setPlaceholder(String texto1, String texto2, javax.swing.JTextArea t){
        if( texto2.isEmpty() ){
            t.setText(texto1);
        }
    }
    public static String validarNull(String t){
        String dato = "";
        if(t == null){
            dato= "";
        }else{
            dato = t;
        }
        return dato;
    }
}
