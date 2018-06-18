/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formularios.Finanza;


import Clases.Texto;
import ClassStatic.Log;
import conexion.Mysql;
import formularios.ListadoEmpleado;
import formularios.ListadoProducto;
import java.awt.FlowLayout;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Eudy
 */
public class PagarNomina extends javax.swing.JFrame {

    /**
     * Creates new form PagarNomina
     */
    private ListadoEmpleado[] Empleados;
    private Mysql mysql;
    private int totalEmpleados = 0;
    private String UsuarioID= "1";
    
     private String nombreUsuario = "";
    
    public void setDatosUsuario(String nombre,String id){        
        Log.Registrar(this.getClass(), "setDatosUsuario", "");

        this.nombreUsuario = nombre;
        this.UsuarioID = id;
        System.out.println(nombre+" "+id);
    }
    
    public PagarNomina(Mysql mysql) {
        initComponents();
       this.mysql = mysql;  
        this.cargarEmpleados();

    }

    public void cargarEmpleados(){                
        Log.Registrar(this.getClass(), "cargarEmpleados", "");


            //SELECT * FROM persona as p inner join empleado as e on p.id = e.persona_id
//inner join usuario as u on e.usuario_empleado_id = u.id
            String table_name = " persona as p inner join empleado as e on p.id = e.persona_id inner join usuario as u on e.usuario_empleado_id = u.id ";
            String campos = " e.id, p.nombre,p.apellido,u.nombre_usuario ";
            String otros = " where e.display = '1' ";
            
           java.sql.ResultSet resultSet = this.mysql.optenerDatosParaTabla(table_name, campos, otros);
        try {
            resultSet.beforeFirst();
            resultSet.last();
            this.totalEmpleados  = resultSet.getRow();
            Empleados = new ListadoEmpleado[this.totalEmpleados];
            String codigo,empleado,usuario,monto,cantidad;
            
            resultSet.beforeFirst();
            int c = 0;
            
            while(resultSet.next()){

                     codigo = Texto.validarNull(resultSet.getString("id"));
                     empleado = Texto.validarNull(resultSet.getString("nombre"))+" "+Texto.validarNull(resultSet.getString("apellido"));
                     usuario = Texto.validarNull(resultSet.getString("nombre_usuario"));
                    // fecha =Texto.validarNull(resultSet.getString("fecha_creada"));
                      //cantidad =Texto.validarNull(resultSet.getString("cantidad"));
                    ListadoEmpleado ListE = new ListadoEmpleado(); 
                    ListE.setDatosEmpleado(codigo, empleado, usuario);
                    Empleados[c] = ListE;
                    this.jPanel1.add(Empleados[c]);
                    c++;
                     
            } 
   
        } catch (SQLException ex) {
            Logger.getLogger(ListadoProducto.class.getName()).log(Level.SEVERE, null, ex);
        } 
        /*
        //this.jPanel1.setLayout(new FlowLayout(FlowLayout.LEFT));
        Empleados = new ListadoEmpleado[3000];
        for(int i = 0 ; i < Empleados.length ; i++){
            ListadoEmpleado ListE = new ListadoEmpleado(); 
            ListE.setDatosEmpleado("codigo"+i, "nombre"+i, "usuario"+i);
            Empleados[i] = ListE;
            this.jPanel1.add(Empleados[i]); 
        /*
        //this.jPanel1.setLayout(new FlowLayout(FlowLayout.LEFT));
        Empleados = new ListadoEmpleado[3000];
        for(int i = 0 ; i < Empleados.length ; i++){
            ListadoEmpleado ListE = new ListadoEmpleado(); 
            ListE.setDatosEmpleado("codigo"+i, "nombre"+i, "usuario"+i);
            Empleados[i] = ListE;
            this.jPanel1.add(Empleados[i]);
        }*/
    }
    public void obtenerMontos(){         
        Log.Registrar(this.getClass(), "obtenerMontos", "");

        String id = "1",monto = "0.00";
         for(int i = 0 ; i < Empleados.length ; i++){
           // JOptionPane.showMessageDialog(null, "Mensaje", title, HEIGHT, icon);
            monto = Empleados[i].getMontoPagado();
            id = Empleados[i].getCodigo();
            
            this.insertarDBPagoNomina(id,monto);
            System.out.println(i+" - "+ Empleados[i].getMontoPagado());
        }
    }
     public void insertarDBPagoNomina(String id,String monto){        
         
         Log.Registrar(this.getClass(), "insertarDBPagoNomina", "");

             String campos = "usuario_id,fecha_creado,monto_pagar_al_empleado,empleado_id,fecha";
             String valores = "'"+this.UsuarioID+"',now(),'"+monto+"','"+id+"',now()";
             this.mysql.insertData("pago_nomina_empleado", campos, valores);           
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jButton1.setBackground(new java.awt.Color(0, 51, 204));
        jButton1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Guardar");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("Lista de empleados para aplicar pago por empleado");

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setHorizontalScrollBar(null);
        jScrollPane1.setMaximumSize(new java.awt.Dimension(800, 300));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setAutoscrolls(true);
        jPanel1.setMaximumSize(new java.awt.Dimension(666, 666));
        jPanel1.setLayout(new javax.swing.BoxLayout(jPanel1, javax.swing.BoxLayout.Y_AXIS));
        jScrollPane1.setViewportView(jPanel1);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 78, Short.MAX_VALUE)
                        .addComponent(jButton1))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jButton1))
                .addGap(8, 8, 8)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 353, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(1, 1, 1))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(6, 6, 6))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        // TODO add your handling code here:
        obtenerMontos();
    }//GEN-LAST:event_jButton1MouseClicked

    /**
     * @param args the command line arguments
     */


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
