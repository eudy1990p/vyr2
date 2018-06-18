/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Render;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author VOSTRO
 */
public class RenderListaReparaciones extends DefaultTableCellRenderer{
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSeleted, boolean hasFocus, int row, int column){
            int dato = Integer.parseInt(table.getValueAt(row, 3).toString());
            javax.swing.JLabel cell = (javax.swing.JLabel)super.getTableCellRendererComponent(table,value,isSeleted,hasFocus,row,column);
            /*
                Si la cantidad de producto esta en la siguiente escala se toman las siguientes medidas
                cantidad > 10 verde
                cantidad >= 5 && cantidad < 10 azul
                cantidad >= 1 && cantidad < 4 rojo
                cantidad == 0  rosa/rojo vino
                cantidad < 0  gris
            */
            this.setForeground(Color.WHITE);
            //FONT
             //Font fuente=new Font("Monospaced", Font.BOLD, 36);
            //cell.setFont(new Font("Georgia",Font.BOLD,16));
            cell.setFont(new Font("Arial",Font.BOLD,15));
            if( (dato >= 0) && (dato <= 15) ){
                //verde
                this.setBackground(new Color(24,168,8));
                
            }else if( (dato >= 16) && (dato <= 30) ){
                this.setForeground(Color.BLACK);
                //amarillo
                this.setBackground(new Color(255,255,0));
                //azul
                //this.setBackground(new Color(7,2,144));
            }else if( (dato >= 30) && (dato <= 40) ){
                //rojo
                this.setBackground(new Color(238,47,9));
            }else if( (dato >45 ) ){
                //rojo vino
                this.setBackground(new Color(179,35,6));
            //}else if( (dato < 0) ){
                //this.setBackground(Color.gray);
                //this.setBackground(new Color(161,162,161));
            }
        // return super.getTableCellRendererComponent(table,value,isSeleted,hasFocus,row,column);
        return cell ;
    }

}
