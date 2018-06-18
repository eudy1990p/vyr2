/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Permiso;

/**
 *
 * @author VOSTRO
 */
public class TipoUsuario {
    public static boolean cajero = false;
    public static boolean admin = false;
    public static boolean supervisor = false;
    public static boolean tecnico = false;
    public static boolean versatil =false;
    
    public static void Warning(){
        javax.swing.JOptionPane.showMessageDialog(null,
                "Usted no tiene permiso para poder usar está opción.",
                "Advertencia",
                javax.swing.JOptionPane.WARNING_MESSAGE
                );
    }
}
