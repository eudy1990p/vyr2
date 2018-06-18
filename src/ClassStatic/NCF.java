/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClassStatic;

import conexion.Mysql;
import javax.swing.JOptionPane;

/**
 *
 * @author VOSTRO
 */
public class NCF {
    
   public static Mysql conector = null;
   public static String ncf="";
   public static String id="0";
   public static  boolean getNCF(){
       boolean resp = false;
       id = "0";
       try {
       String select = ""
               + "  \n" +
"( \n" +
"	IF( (ifnull(ncf_ult_usado,ncf_ini) < ncf_fin)\n" +
"        ,\n" +
"       		IF(\n" +
"                ifnull(ncf_ult_usado,0) <> 0\n" +
"                ,\n" +
"                ncf_ult_usado + 1\n" +
"                ,\n" +
"                ncf_ini\n" +
"              )\n" +
"        ,\n" +
"       0\n" +
"      )\n" +
") as nuevo_ncf,"
               + "id";
       String where = "WHERE "
               + "usando = 1\n" +
"	AND\n" +
"    agotado = 0\n" +
"order by \n" +
"	 id\n" +
"    ASC";
       /*
       SELECT 
( 
	IF( (ifnull(ncf_ult_usado,ncf_ini) < ncf_fin)
        ,
       		IF(
                ifnull(ncf_ult_usado,0) <> 0
                ,
                ncf_ult_usado + 1
                ,
                ncf_ini
              )
        ,
       0
      )
) as nuevo_ncf 
FROM `compra_ncf` 
WHERE 
	usando = 1
	AND
    agotado = 0
order by 
	 id
    ASC
       */
       java.sql.ResultSet resulset = NCF.conector.optenerDatosParaTabla("compra_ncf", select,where);
           if (resulset.first()) {
               if (resulset.getInt("nuevo_ncf") > 0) {
               NCF.ncf = resulset.getString("nuevo_ncf");
               digitos(NCF.ncf);
               NCF.id = resulset.getString("id");
               System.out.println(" el ncf es "+resulset.getString("nuevo_ncf"));
               resp = true;
               Log.Registrar("getNCF "+NCF.ncf +" id "+NCF.id);
               Log.Registrar(" el ncf es "+resulset.getString("nuevo_ncf"));

               }
           }else{
               Log.Registrar("Se necesita comprar mas ncf");
               System.out.println("Se necesita comprar mas ncf");
               JOptionPane.showMessageDialog(null, "Se necesita comprar mas NCF");
           }
       
       } catch (Exception e) {
        Log.Error("getNCF", e);
       }
       return resp;
   }
   public static void digitos(String num){
       String d = "";
       for (int i = num.length(); i < 8; i++) {
           d += "0";
       }
       NCF.ncf = d+NCF.ncf;
       Log.Registrar("NCF/digitos ncf "+NCF.ncf);
   }
   
   public static void Insert(){
       //update compra_ncf as com set com.ncf_ult_usado='4', com.usando=(select if(com.ncf_ult_usado=com.ncf_fin,0,1) ) WHERE com.id = 1
       NCF.conector.actulizarDatos("compra_ncf", "ncf_ult_usado='"+NCF.ncf+"', usando=(select if(ncf_ult_usado=ncf_fin,0,1) )  "," id = "+NCF.id);
       Log.Registrar("NCF/Insert "+"compra_ncf "+" ncf_ult_usado='"+NCF.ncf+"', usando=(select if(ncf_ult_usado=ncf_fin,0,1) )  "+" id = "+NCF.id);
   }
   
}
