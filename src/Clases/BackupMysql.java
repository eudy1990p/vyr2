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
import ClassStatic.Fecha;
import ClassStatic.Log;
import java.util.Calendar;
import javax.swing.*;

public class BackupMysql {
    public static void Generar(){
        try {
             //"cd c:\\xampp\\mysql\\bin && mysqldump -u root db_vyr_cliente1 > eudy01111112.sql"
            Log.Registrar("Generar backup");
            String nombreBackup = "backup_db_vyr_cliente_"+Fecha.RDHoras()+".sql";
            String[] comando={"cmd.exe","/c","cd", "c:\\xampp\\mysql\\bin", "&&", "mysqldump", "-u", "root", "db_vyr_cliente2", ">", nombreBackup}; 
            //String[] comando={"cmd.exe","/c","cd", "c:\\xampp\\mysql\\bin", "&&", "mysqldump", "-u", "root", "db_vyr_cliente1", ">", "backup_db_vyr_cliente_"+fecha+".sql"}; 
            Runtime.getRuntime().exec(comando);
            JOptionPane.showMessageDialog(null, "Se ha generando el backup");
            Log.Registrar("Se genero el backup "+nombreBackup);
        } catch (Exception e) {
            Log.Error("Generar Backup", e);
            JOptionPane.showMessageDialog(null, "Error generando backup");
            
        }
    }
}
