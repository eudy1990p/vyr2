/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClassStatic;

import java.awt.Color;
import javax.swing.JScrollPane;

/**
 *
 * @author VOSTRO
 */
public class ConfTable {
    public static void setBackGroundColor(JScrollPane[] jScrollPanes){
        for (int i = 0; i < jScrollPanes.length; i++) {
           jScrollPanes[i].getViewport().setBackground(Color.WHITE);
        }
    }
    /*
    this.jTableCliente.getColumnModel().getColumn(0).setWidth(15);
        this.jTableCliente.getColumnModel().getColumn(0).setMaxWidth(35);
        this.jTableCliente.getColumnModel().getColumn(0).setMinWidth(35);
    */
}
