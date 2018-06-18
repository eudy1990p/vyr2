/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClassStatic;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author VOSTRO
 */
public class Log {
    public static void Registrar(){
        String ruta = "c:\\app_vyr\\log\\log"+Fecha.RD()+".txt";
        File file = new File(ruta);
        BufferedWriter bufferedWrite;
       try {
           if (file.exists()) {
                bufferedWrite = new BufferedWriter(new FileWriter(file,true));
                bufferedWrite.write("\r\n"+Fecha.RDFechaHoras()+"\r\nOtras veces");
            }else{
                  bufferedWrite = new BufferedWriter(new FileWriter(file));
                bufferedWrite.write("Primera ves");
            }
           bufferedWrite.close();
        } catch (IOException ex) {
            //JOptionPane.showMessageDialog(null, "No se esta guardando el log");
        }
    }
    public static void Registrar(String registro){
        String ruta = "c:\\app_vyr\\log\\log"+Fecha.RD()+".txt";
        File file = new File(ruta);
        BufferedWriter bufferedWrite;
       try {
           if (file.exists()) {
                bufferedWrite = new BufferedWriter(new FileWriter(file,true));
                bufferedWrite.write("\r\n"+Fecha.RDFechaHoras()+"\r\n"+registro);
            }else{
                bufferedWrite = new BufferedWriter(new FileWriter(file));
                bufferedWrite.write("\r\n"+Fecha.RDFechaHoras()+"\r\n"+registro);
            }
           bufferedWrite.close();
        } catch (IOException ex) {
            //JOptionPane.showMessageDialog(null, "No se esta guardando el log");
        }
    }
    
    public static void Error(String label,Exception e){
        String ruta = "c:\\app_vyr\\log\\log"+Fecha.RD()+".txt";
        File file = new File(ruta);
        BufferedWriter bufferedWrite;
       try {
           if (file.exists()) {
                bufferedWrite = new BufferedWriter(new FileWriter(file,true));
                bufferedWrite.write("\r\n"+Fecha.RDFechaHoras()+"\r\n  "+label+" Mensaje "+e.getMessage());                
                bufferedWrite.write("\r\n  "+label+" Causa "+e.getCause());
                bufferedWrite.write("\r\n  "+label+" Lugar "+e.getLocalizedMessage());
                
            }else{
                bufferedWrite = new BufferedWriter(new FileWriter(file));
                bufferedWrite.write("\r\n"+Fecha.RDFechaHoras()+"\r\n  "+label+" Mensaje "+e.getMessage());                
                bufferedWrite.write("\r\n  "+label+" Causa "+e.getCause());
                bufferedWrite.write("\r\n  "+label+" Lugar "+e.getLocalizedMessage());
                
            }
           bufferedWrite.close();
        } catch (IOException ex) {
            //JOptionPane.showMessageDialog(null, "No se esta guardando en el log");
        }
    }
  public static void Registrar(Class clase,String metodo,String text){
        String ruta = "c:\\app_vyr\\log\\log"+Fecha.RD()+".txt";
        File file = new File(ruta);
        BufferedWriter bufferedWrite;
       try {
           if (file.exists()) {
                bufferedWrite = new BufferedWriter(new FileWriter(file,true));
                bufferedWrite.write(
                        "\r\n"+Fecha.RDFechaHoras()+
                                "\r\n"+clase.toString()
                        +"\r\n Nombre "+clase.getName()
                        +"\r\n Simple Nombre "+clase.getSimpleName()                        
                        +"\r\n Tipo "+clase.getTypeName()
                        +"\r\n Metodo "+metodo                        
                        +"\r\n Mensaje "+text
                );
            }else{
                bufferedWrite = new BufferedWriter(new FileWriter(file));
                bufferedWrite.write(
                        "\r\n"+Fecha.RDFechaHoras()+
                        "\r\n"+clase.toString()
                        +"\r\n Nombre "+clase.getName()
                        +"\r\n Simple Nombre "+clase.getSimpleName()                        
                        +"\r\n Tipo "+clase.getTypeName()                        
                        +"\r\n Metodo "+metodo
                        +"\r\n Mensaje "+text
                );
            }
           bufferedWrite.close();
        } catch (IOException ex) {
           // JOptionPane.showMessageDialog(null, "No se esta guardando el log");
        }
    }
}
