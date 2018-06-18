/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vyr2;

import Clases.BackupMysql;
import ClassStatic.Log;
import conexion.Mysql;
import formularios.splash;
import java.io.IOException;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Eudy
 */
public class Vyr2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
            Log.Registrar("***************************************************************************************");
           
            // TODO code application logic here
            Log.Registrar("Inicio la aplicacion");
            conexion.Mysql mysql = null;
            splash s = null;
            try{
                mysql = new conexion.Mysql();
                if(Mysql.Estado){
                    BackupMysql.Generar();
                    Log.Registrar("Se genero el backup");
                    s = new splash(mysql);
                        try{
                            Thread t = new Thread(s);
                            t.start();
                            Log.Registrar("Inicio el splash");
                            //JOptionPane.showMessageDialog(null, "ok");
                        }catch(Exception ex){
                            Log.Error("Main splash ",ex);
                            Log.Registrar("error no se mostrara el splash");                
                            JOptionPane.showMessageDialog(null, "Mal "+ex);
                        }
                }else{
                    Log.Registrar("No conecto con la base de datos");
                }
            // JOptionPane.showMessageDialog(null, "ok");
            }catch(Exception e){
                Log.Error("Main backup ",e);
                JOptionPane.showMessageDialog(null, "Mal mysql "+e);
                Log.Registrar("error no se pudo conectar con la base de datos");                
                
            }
            
        
    }
    
}
