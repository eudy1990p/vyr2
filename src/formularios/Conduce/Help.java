/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formularios.Conduce;

/**
 *
 * @author VOSTRO
 */
public class Help extends javax.swing.JFrame {

    /**
     * Creates new form Help
     */
    public Help() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Ayuda Conduce");

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jTextArea1.setText("                    Ayuda\n\nBuscar las reparaciones pendiente de un cliente\n\n1.  Seleccione el cliente como si fueramos ha crear un conduce nuevo.\n2.  Seleccione el conduce que desea darle seguimiento es decir al cual se le desea registrar los avances.\n3.  Una vez le haya dado clic al conduce que desea, automaticamente se cargara el conduce seleccionado y podra ver los equipos que se estan reparando y cual seria el estado de cada uno.\n\nBotones\n\nAgregar Producto :  Cuando haya seleccionado el cliente y el producto o equipo, le damos clic a este boton para poder agregar el equipo para el cliente.\nAgregar Nota     : Este boton se utiliza para poder agregar una nota para todos los equipos que se van a reparar.\nFacturar         : Este boton se utiliza para poder cargar o llevar esta reparacion al area de facturación.\nVISTA PREVIA     : Este boton se utiliza para poder ver como esta quedando la hoja que se va a imprimir.\nReparar          : Este boton se utiliza para poder guardar todo lo hecho hasta el momento en la base de datos y asi ser utilizado mas adelante. \nAbonar           : Este boton se utiliza para poder agregar un avance de dinero para realizar la reparación del equipo.\n\n\nEstados para seguimiento del producto.\n\nPendiente  : No se ha trabajado ese equipo.\nProcesando : Es cuando se esta trabajando el equipo.\nCompletado : Es cuando ya se reparo el equipo y esta listo para entregarselo al cliente.\n\nEstados que indican que no se pudo reparar el equipo.\n\nIncompleto : Es cuando no se puede reparar el equipo.\nAnulado    : Es cuando se agrego un equipo para reparar por error o el cliente decidio no reparar el equipo.\n");
        jScrollPane1.setViewportView(jTextArea1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 877, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 275, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    // End of variables declaration//GEN-END:variables
}