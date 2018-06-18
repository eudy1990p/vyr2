/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClassStatic;

import java.util.Calendar;

/**
 *
 * @author VOSTRO
 */
public class Fecha {
    public static String RD(){
         Calendar c1 = Calendar.getInstance();            
            String fecha = ""+Integer.toString(c1.get(Calendar.YEAR))+Integer.toString(c1.get(Calendar.MONTH))+Integer.toString(c1.get(Calendar.DAY_OF_MONTH));
            return fecha;
    }
    public static  String RDHoras(){
        Calendar c1 = Calendar.getInstance();            
            String fechaHora = ""+Integer.toString(c1.get(Calendar.YEAR))+Integer.toString(c1.get(Calendar.MONTH))+Integer.toString(c1.get(Calendar.DAY_OF_MONTH))+Integer.toString(c1.get(Calendar.HOUR))+Integer.toString(c1.get(Calendar.MINUTE))+Integer.toString(c1.get(Calendar.SECOND));
        return fechaHora;
    }
    public static  String RDFechaHoras(){
        Calendar c1 = Calendar.getInstance();            
            String fechaHora = "En la fecha : "+Integer.toString(c1.get(Calendar.YEAR))+"/"+Integer.toString(c1.get(Calendar.MONTH))+"/"+Integer.toString(c1.get(Calendar.DAY_OF_MONTH))+"  Hora "+Integer.toString(c1.get(Calendar.HOUR))+":"+Integer.toString(c1.get(Calendar.MINUTE))+":"+Integer.toString(c1.get(Calendar.SECOND));
        return fechaHora;
    }
}
