/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formularios.Main;


import Clases.BackupMysql;
import Clases.ValidData;
import ClassStatic.Log;
import formularios.Cotizacion.Cotizacion;
import formularios.Finanza.CuadreCaja;
import formularios.Factura.Facturacion;
import formularios.Reporte.GeneradorReportes;
import formularios.inicio.JFrameCambiarClave;
import formularios.Conduce.ListadoReparacionesCompleta;
import formularios.Finanza.PagarNomina;
import formularios.Conduce.Reparacion;
import formularios.cliente.crearCliente;
import formularios.administracion.crearProducto;
import formularios.inicio.AboutCreator;
import formularios.usuarios.EmpleadoAdministrar;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import javax.swing.ImageIcon;
import java.awt.Image;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.JOptionPane;
//import app.jframes.JFrameBlood;


/**
 *
 * @author Eudy
 */
public class Main extends javax.swing.JFrame {
     private conexion.Mysql mysql;
     private String[] session;
     private ValidData validador;
java.sql.ResultSet resultSet = null;
    private int notificacionTotal = 0;
    
     
    /**
     * Creates new form Main
     */
     public void validarPermisoUsuario(){
         switch(this.session[2]){
             case "admin":
                 Permiso.TipoUsuario.admin = true;
                 break;
             case "cajero":
                 Permiso.TipoUsuario.cajero = true;
                 break;
             case "supervisor":
                 Permiso.TipoUsuario.supervisor = true;
                 break;
             case "versatil":
                 Permiso.TipoUsuario.versatil = true;
                 break;
             case "tecnico":
                 Permiso.TipoUsuario.tecnico = true;
                 break;
         }
     }
    public Main(conexion.Mysql mysql,String[] session){
        this.mysql = mysql;
        ClassStatic.NCF.conector = mysql;
        this.session = session;
        this.validador = new ValidData();
        initComponents();
        System.out.println(session[2]+" "+session[1]+" "+session[0]);
        this.JLUsuarioConectado.setText(session[1]);
        this.validador.validarPermisos(session[2]);
        this.setIconImage(new ImageIcon("C:/app_vyr/img/iconoclinica.gif").getImage());
            
        //setIconImage(new ImageIcon(getClass().getResource("../icono/logovyr.gif")).getImage());
        this.setLocationRelativeTo(null);
       
        //setBackground( rgb(1, 1, 1, 1.8));
         this.jPanel1.setBackground(new java.awt.Color(250,250,250,200));
         this.scalarFondo();
         this.getCountReportePendiente();
        //this.configurations();
         this.validarPermisoUsuario();
         Log.Registrar("Abrio Main");
    }
    public void getCountReportePendiente(){
         Log.Registrar(this.getClass(), "getCountReportePendiente", " entro");

       this.resultSet = this.mysql.optenerListaReparacionesPorFecha();
         try {
             int total = 0;
             while(resultSet.next()){
                 total++;
             }
             this.notificacionTotal = total;
             if(total > 0){
             //JOptionPane.showMessageDialog(null, "Tienes repaciones pendiente por entregar.");
             }
             //iconoNotificaciones
             ImageIcon  img1 = new ImageIcon("C:/app_vyr/img/iconoNotificaciones.gif");
               // ImageIcon img1 = new ImageIcon(this.getClass().getResource("../icono/iconoNotificaciones.gif"));
             Icon icono = new ImageIcon(img1.getImage().getScaledInstance(this.JLCantidadReparacion.getWidth(), this.JLCantidadReparacion.getHeight(),Image.SCALE_DEFAULT));
             this.JLCantidadReparacion.setIcon(icono);
             this.JLCantidadReparacion.setText("" +total);
         } catch (SQLException ex) {
             
                         Log.Error(this.getClass().getName(), ex);

             Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
    public void mostrarListadoReparaciones(){
                 Log.Registrar(this.getClass(), "mostrarListadoReparaciones", " entro");

            ListadoReparacionesCompleta c = new ListadoReparacionesCompleta(this.mysql);
             //c.setObjectReparacion(c);
             c.setDatosUsuario(this.session[1],this.session[0]);
             c.setTotalR(this.notificacionTotal);
             c.setResultSet(resultSet);
             c.cargarTabla();
             c.setVisible(true);
    }
    public void scalarFondo(){
                        Log.Registrar(this.getClass(), "scalarFondo", " entro");

       ImageIcon  img = new ImageIcon("C:/app_vyr/img/fondoMainApp.jpg");
       //ImageIcon img = new ImageIcon(getClass().getResource("../icono/fondoMainApp.jpg"));
       Icon icono = new ImageIcon(img.getImage().getScaledInstance(this.jLabel1.getWidth(),this.jLabel1.getHeight() ,Image.SCALE_DEFAULT));
       jLabel1.setIcon(icono);
    }
    public void configurations(){
        
        //maximizar en hambos sentidos
        //this.setExtendedState(MAXIMIZED_BOTH);
        
        //pantalla completa
        //GraphicsDevice grafica=GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        //grafica.setFullScreenWindow(this);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem16 = new javax.swing.JMenuItem();
        jMenuItem17 = new javax.swing.JMenuItem();
        JLCantidadReparacion = new javax.swing.JLabel();
        JLUsuarioConectado = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        btUsuario = new javax.swing.JButton();
        btConduce = new javax.swing.JButton();
        btCotizacion = new javax.swing.JButton();
        btFactura = new javax.swing.JButton();
        btCliente = new javax.swing.JButton();
        btInventario = new javax.swing.JButton();
        btReporte = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        miAbout = new javax.swing.JMenuItem();
        miCambiarClave = new javax.swing.JMenuItem();
        miCambiarClave1 = new javax.swing.JMenuItem();
        miSalir = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        miCrearConduce = new javax.swing.JMenuItem();
        miBuscarConduce = new javax.swing.JMenuItem();
        jMenu7 = new javax.swing.JMenu();
        miCrearFactura = new javax.swing.JMenuItem();
        miBuscarFactura = new javax.swing.JMenuItem();
        miCrearFactura1 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        miCrearCotizacion = new javax.swing.JMenuItem();
        miBuscarCotizacion = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        miCrearCliente = new javax.swing.JMenuItem();
        miBuscarCliente = new javax.swing.JMenuItem();
        jMenu8 = new javax.swing.JMenu();
        miPagoNomina = new javax.swing.JMenuItem();
        miCuadreCaja = new javax.swing.JMenuItem();
        miPagoCliente = new javax.swing.JMenuItem();
        miPagoCliente1 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        miReporte = new javax.swing.JMenuItem();
        jMenu6 = new javax.swing.JMenu();
        miUsuario = new javax.swing.JMenuItem();
        miInventario = new javax.swing.JMenuItem();
        miContraroRenta = new javax.swing.JMenuItem();
        miProveedor = new javax.swing.JMenuItem();

        jMenuItem16.setText("jMenuItem16");

        jMenuItem17.setText("jMenuItem17");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Menu Principal / V & R");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        JLCantidadReparacion.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        JLCantidadReparacion.setForeground(new java.awt.Color(255, 255, 255));
        JLCantidadReparacion.setText("Cantidad");
        JLCantidadReparacion.setToolTipText("Notificaciones de reparaciones no entregadas");
        JLCantidadReparacion.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        JLCantidadReparacion.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        JLCantidadReparacion.setVerifyInputWhenFocusTarget(false);
        JLCantidadReparacion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                JLCantidadReparacionMousePressed(evt);
            }
        });
        getContentPane().add(JLCantidadReparacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 5, 80, 110));

        JLUsuarioConectado.setBackground(new java.awt.Color(255, 255, 255));
        JLUsuarioConectado.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        JLUsuarioConectado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icono/iconoUsuarioConectado.gif"))); // NOI18N
        JLUsuarioConectado.setText("jLabel2");
        JLUsuarioConectado.setToolTipText("Usuario conectado");
        JLUsuarioConectado.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        getContentPane().add(JLUsuarioConectado, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 0, 190, 50));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        btUsuario.setBackground(new java.awt.Color(0, 153, 51));
        btUsuario.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btUsuario.setForeground(new java.awt.Color(255, 255, 255));
        btUsuario.setText("USUARIO");
        btUsuario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btUsuarioMouseClicked(evt);
            }
        });

        btConduce.setBackground(new java.awt.Color(0, 153, 51));
        btConduce.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btConduce.setForeground(new java.awt.Color(255, 255, 255));
        btConduce.setText("CONDUCE");
        btConduce.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btConduceMouseClicked(evt);
            }
        });

        btCotizacion.setBackground(new java.awt.Color(0, 153, 51));
        btCotizacion.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        btCotizacion.setForeground(new java.awt.Color(255, 255, 255));
        btCotizacion.setText("COTIZACION");
        btCotizacion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btCotizacionMouseClicked(evt);
            }
        });

        btFactura.setBackground(new java.awt.Color(0, 153, 51));
        btFactura.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btFactura.setForeground(new java.awt.Color(255, 255, 255));
        btFactura.setText("FACTURA");
        btFactura.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btFacturaMouseClicked(evt);
            }
        });

        btCliente.setBackground(new java.awt.Color(0, 153, 51));
        btCliente.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btCliente.setForeground(new java.awt.Color(255, 255, 255));
        btCliente.setText("CLIENTE");
        btCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btClienteMouseClicked(evt);
            }
        });

        btInventario.setBackground(new java.awt.Color(0, 153, 51));
        btInventario.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        btInventario.setForeground(new java.awt.Color(255, 255, 255));
        btInventario.setText("INVENTARIO");
        btInventario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btInventarioMouseClicked(evt);
            }
        });

        btReporte.setBackground(new java.awt.Color(0, 153, 51));
        btReporte.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btReporte.setForeground(new java.awt.Color(255, 255, 255));
        btReporte.setText("REPORTE");
        btReporte.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btReporteMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btCotizacion, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(102, 102, 102)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btConduce, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btReporte, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 113, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btInventario, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(59, 59, 59))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(btConduce, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btReporte, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btInventario, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btCotizacion, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(49, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 190, 860, 350));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icono/fondoMainApp.jpg"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1070, 600));

        jMenu1.setText("Inicio");
        jMenu1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N

        miAbout.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        miAbout.setText("About It");
        miAbout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                miAboutMousePressed(evt);
            }
        });
        jMenu1.add(miAbout);

        miCambiarClave.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        miCambiarClave.setText("Cambiar Clave");
        miCambiarClave.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                miCambiarClaveMousePressed(evt);
            }
        });
        jMenu1.add(miCambiarClave);

        miCambiarClave1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        miCambiarClave1.setText("Backup");
        miCambiarClave1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                miCambiarClave1MousePressed(evt);
            }
        });
        jMenu1.add(miCambiarClave1);

        miSalir.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        miSalir.setText("Salir");
        miSalir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                miSalirMousePressed(evt);
            }
        });
        jMenu1.add(miSalir);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Conduce");
        jMenu2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N

        miCrearConduce.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        miCrearConduce.setText("Crear Nueva");
        miCrearConduce.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                miCrearConduceMousePressed(evt);
            }
        });
        jMenu2.add(miCrearConduce);

        miBuscarConduce.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        miBuscarConduce.setText("Buscar");
        miBuscarConduce.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                miBuscarConduceMousePressed(evt);
            }
        });
        jMenu2.add(miBuscarConduce);

        jMenuBar1.add(jMenu2);

        jMenu7.setText("Facturaciones");
        jMenu7.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N

        miCrearFactura.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        miCrearFactura.setText("Crear Nueva");
        miCrearFactura.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                miCrearFacturaMousePressed(evt);
            }
        });
        jMenu7.add(miCrearFactura);

        miBuscarFactura.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        miBuscarFactura.setText("Buscar");
        miBuscarFactura.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                miBuscarFacturaMousePressed(evt);
            }
        });
        jMenu7.add(miBuscarFactura);

        miCrearFactura1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        miCrearFactura1.setText("Manual");
        miCrearFactura1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                miCrearFactura1MousePressed(evt);
            }
        });
        jMenu7.add(miCrearFactura1);

        jMenuBar1.add(jMenu7);

        jMenu4.setText("Cotizaciones");
        jMenu4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N

        miCrearCotizacion.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        miCrearCotizacion.setText("Crear Nueva");
        miCrearCotizacion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                miCrearCotizacionMousePressed(evt);
            }
        });
        jMenu4.add(miCrearCotizacion);

        miBuscarCotizacion.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        miBuscarCotizacion.setText("Buscar");
        miBuscarCotizacion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                miBuscarCotizacionMousePressed(evt);
            }
        });
        jMenu4.add(miBuscarCotizacion);

        jMenuBar1.add(jMenu4);

        jMenu5.setText("Cliente");
        jMenu5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N

        miCrearCliente.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        miCrearCliente.setText("Crear Nuevo");
        miCrearCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                miCrearClienteMousePressed(evt);
            }
        });
        jMenu5.add(miCrearCliente);

        miBuscarCliente.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        miBuscarCliente.setText("Buscar");
        miBuscarCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                miBuscarClienteMousePressed(evt);
            }
        });
        jMenu5.add(miBuscarCliente);

        jMenuBar1.add(jMenu5);

        jMenu8.setText("Finanzas");
        jMenu8.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N

        miPagoNomina.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        miPagoNomina.setText("Pago Nomina");
        miPagoNomina.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                miPagoNominaMousePressed(evt);
            }
        });
        jMenu8.add(miPagoNomina);

        miCuadreCaja.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        miCuadreCaja.setText("Cuadrar Caja");
        miCuadreCaja.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                miCuadreCajaMousePressed(evt);
            }
        });
        jMenu8.add(miCuadreCaja);

        miPagoCliente.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        miPagoCliente.setText("Pago Cliente");
        miPagoCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                miPagoClienteMousePressed(evt);
            }
        });
        jMenu8.add(miPagoCliente);

        miPagoCliente1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        miPagoCliente1.setText("Compra ncf");
        miPagoCliente1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                miPagoCliente1MousePressed(evt);
            }
        });
        jMenu8.add(miPagoCliente1);

        jMenuBar1.add(jMenu8);

        jMenu3.setText("Reportes");
        jMenu3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N

        miReporte.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        miReporte.setText("Generar");
        miReporte.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                miReporteMousePressed(evt);
            }
        });
        jMenu3.add(miReporte);

        jMenuBar1.add(jMenu3);

        jMenu6.setText("Administración");
        jMenu6.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N

        miUsuario.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        miUsuario.setText("Usuarios");
        miUsuario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                miUsuarioMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                miUsuarioMousePressed(evt);
            }
        });
        miUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miUsuarioActionPerformed(evt);
            }
        });
        jMenu6.add(miUsuario);

        miInventario.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        miInventario.setText("Inventario");
        miInventario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                miInventarioMousePressed(evt);
            }
        });
        jMenu6.add(miInventario);

        miContraroRenta.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        miContraroRenta.setText("Contrato Renta");
        miContraroRenta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                miContraroRentaMousePressed(evt);
            }
        });
        jMenu6.add(miContraroRenta);

        miProveedor.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        miProveedor.setText("Proveedores");
        miProveedor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                miProveedorMousePressed(evt);
            }
        });
        jMenu6.add(miProveedor);

        jMenuBar1.add(jMenu6);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void miUsuarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_miUsuarioMouseClicked
        // TODO add your handling code here:
      
    }//GEN-LAST:event_miUsuarioMouseClicked

    public void crearUsuario(){
                                Log.Registrar(this.getClass(), "crearUsuario", " entro");

            if(Permiso.TipoUsuario.admin || Permiso.TipoUsuario.supervisor){
            //EmpleadoAdministrar
             formularios.usuarios.EmpleadoAdministrar c = new formularios.usuarios.EmpleadoAdministrar(this.mysql,this.session[0],this.session[1]);
            c.setYo(c);
            //c.setObjectReparacion(c);
            // c.setDatosUsuario(this.session[1],this.session[0]);
             c.setVisible(true);
            }else{
                Permiso.TipoUsuario.Warning();
            }
    }
    public void Proveedor(){
    Log.Registrar(this.getClass(), "Proveedor", " entro");

            if(Permiso.TipoUsuario.admin || Permiso.TipoUsuario.supervisor){
                formularios.administracion.ProveedorAdministrar c = new formularios.administracion.ProveedorAdministrar(this.mysql,this.session[0],this.session[1]);
                c.setYo(c);
                c.setVisible(true);
            }else{
                Permiso.TipoUsuario.Warning();
            }
    }
    public void NCF(){
            Log.Registrar(this.getClass(), "NCF", " entro");

            if(Permiso.TipoUsuario.admin || Permiso.TipoUsuario.supervisor){
                formularios.Finanza.ComprarNCF c = new formularios.Finanza.ComprarNCF(this.mysql,this.session[0],this.session[1]);
                c.setYo(c);
                c.setVisible(true);
            }else{
                Permiso.TipoUsuario.Warning();
            }
    }
    public void administrarCliente(){
          Log.Registrar(this.getClass(), "administrarCliente", " entro");
        if(Permiso.TipoUsuario.admin || Permiso.TipoUsuario.supervisor  ){
                
        //EmpleadoAdministrar
            formularios.cliente.ClienteAdministrar c = new formularios.cliente.ClienteAdministrar(this.mysql,this.session[0],this.session[1]);
            c.setYo(c);
            //c.setObjectReparacion(c);
            // c.setDatosUsuario(this.session[1],this.session[0]);
             c.setVisible(true);
             }else{
                Permiso.TipoUsuario.Warning();
            }
    }
        public void reparacion(){
                        Log.Registrar(this.getClass(), "reparacion", " entro");
           if(Permiso.TipoUsuario.admin || Permiso.TipoUsuario.supervisor  || Permiso.TipoUsuario.cajero ){
                Reparacion c = Reparacion.getInstancia(mysql) /*new Reparacion(this.mysql)*/;
                   c.setObjectReparacion(c);
                   c.setDatosUsuario(this.session[1],this.session[0]);
                   c.setVisible(true);
             }else{
             Permiso.TipoUsuario.Warning();
         }
    }
        public void crearCliente(){
             Log.Registrar(this.getClass(), "crearCliente", " entro");
        if(Permiso.TipoUsuario.admin || Permiso.TipoUsuario.supervisor  ){
            crearCliente c = new crearCliente(this.mysql);
        
             //c.setObjectReparacion(c);
             c.setDatosUsuario(this.session[1],this.session[0]);
             c.setVisible(true);
         }else{
             Permiso.TipoUsuario.Warning();
         }
    }
    public void cotizacion(){
         Log.Registrar(this.getClass(), "cotizacion", " entro");
        if(Permiso.TipoUsuario.admin || Permiso.TipoUsuario.supervisor || Permiso.TipoUsuario.cajero  ){
            
            
            Cotizacion cotizacion =  /*formularios.Cotizacion.getInstancia(mysql)*/ /*new Cotizacion(this.mysql)*/Cotizacion.getInstancia(mysql);
            cotizacion.setObjectCotizacion(cotizacion);
            cotizacion.setDatosUsuario(this.session[1],this.session[0]);
            cotizacion.setVisible(true);
        }else{
             Permiso.TipoUsuario.Warning();
         }
    }
 public void facturacion(){
      Log.Registrar(this.getClass(), "facturacion", " entro");
        if(Permiso.TipoUsuario.admin || Permiso.TipoUsuario.supervisor || Permiso.TipoUsuario.cajero  ){
            Facturacion c = Facturacion.getInstancia(mysql)/*new Facturacion(this.mysql)*/;
            c.setObjectFacturacion(c);
            c.setDatosUsuario(this.session[1],this.session[0]);
            c.setVisible(true);
            Log.Registrar("Abrio facturacion");
         }else{
            this.NoTienePermiso();
         }
    }
 public void NoTienePermiso(){
            Permiso.TipoUsuario.Warning();
             Log.Registrar("El usuario: "+this.session[1]+" ("+this.session[0]+") no tiene permisos para facturar");
 }
 public void PagarNomina(){
           Log.Registrar(this.getClass(), "PagarNomina", " entro");

        if(Permiso.TipoUsuario.admin || Permiso.TipoUsuario.supervisor   ){
                PagarNomina pn = new PagarNomina(this.mysql);
                //c.setObjectReparacion(c);
                pn.setDatosUsuario(this.session[1],this.session[0]);
                pn.setVisible(true);
             }else{
             Permiso.TipoUsuario.Warning();
         }
    }
    public void CuadreCaja(){
                   Log.Registrar(this.getClass(), "CuadreCaja", " entro");
    if(Permiso.TipoUsuario.admin || Permiso.TipoUsuario.supervisor   ){
               
            CuadreCaja cc = new CuadreCaja(this.mysql);
             //c.setObjectReparacion(c);
             cc.setDatosUsuario(this.session[1],this.session[0]);
             cc.setVisible(true);
             }else{
             Permiso.TipoUsuario.Warning();
         }
    }
    public void crearProducto(){
                           Log.Registrar(this.getClass(), "crearProducto", " entro");

        if(Permiso.TipoUsuario.admin || Permiso.TipoUsuario.supervisor   ){
        
                 crearProducto c = new crearProducto(this.mysql);
                //c.setObjectReparacion(c);
                c.setDatosUsuario(this.session[1],this.session[0]);
                c.setVisible(true);
         }else{
             Permiso.TipoUsuario.Warning();
         }
    }
    public void compraProducto(){
                                   Log.Registrar(this.getClass(), "compraProducto", " entro");

        if(Permiso.TipoUsuario.admin || Permiso.TipoUsuario.supervisor   ){
        
                formularios.administracion.CompraProductoAdministrar c = new formularios.administracion.CompraProductoAdministrar(this.mysql,this.session[1],this.session[0]);
                c.setYo(c);
               // c.setDatosUsuario(this.session[1],this.session[0]);
                c.setVisible(true);
         }else{
             Permiso.TipoUsuario.Warning();
         }
    }
    public void generarReporte(){
                                           Log.Registrar(this.getClass(), "generarReporte", " entro");

        GeneradorReportes r = new GeneradorReportes(this.mysql);
        r.setVisible(true);
    }
 /*--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    -----------------------------------------*/
    
    private void miUsuarioMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_miUsuarioMousePressed
        // TODO add your handling code here:
        if(this.validador.getBtCrearUsuario()){
            this.crearUsuario();
              //crearUsuario c = new crearUsuario(this.mysql);
              //c.setVisible(true);
        }
    }//GEN-LAST:event_miUsuarioMousePressed

    private void miCrearClienteMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_miCrearClienteMousePressed
        // TODO add your handling code here:
         if(this.validador.getBtCrearPaciente()){
           this.crearCliente();
          //JFrameCrearPaciente crearPaciente=  new JFrameCrearPaciente(this.mysql);
          //crearPaciente.setDatosUsuario(this.session[0], this.session[1], this.session[3]);
          //crearPaciente.setVisible(true);
        }
    }//GEN-LAST:event_miCrearClienteMousePressed

    private void miCrearConduceMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_miCrearConduceMousePressed
        // TODO add your handling code here:
        this.reparacion();
    }//GEN-LAST:event_miCrearConduceMousePressed

    private void miSalirMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_miSalirMousePressed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_miSalirMousePressed

    private void miCambiarClaveMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_miCambiarClaveMousePressed
        // TODO add your handling code here:
        new JFrameCambiarClave(mysql).setVisible(true);
    }//GEN-LAST:event_miCambiarClaveMousePressed

    private void miAboutMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_miAboutMousePressed
        // TODO add your handling code here:
        new AboutCreator().setVisible(true);
    }//GEN-LAST:event_miAboutMousePressed

    private void miBuscarClienteMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_miBuscarClienteMousePressed
        // TODO add your handling code here:
        this.administrarCliente();
        /*this.crearCliente();
        if(this.validador.getBtBuscarPaciente()){
            
        }*/
    }//GEN-LAST:event_miBuscarClienteMousePressed

    private void miBuscarConduceMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_miBuscarConduceMousePressed
        // TODO add your handling code here:
        //JFrameBuscarSonografia
        this.reparacion();
        if(this.validador.getBtBuscarSonografia()){
        }
    }//GEN-LAST:event_miBuscarConduceMousePressed

    private void miReporteMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_miReporteMousePressed
        // TODO add your handling code here:
        if(this.validador.getBtGenerarReporte()){
            generarReporte();
        }
        
    }//GEN-LAST:event_miReporteMousePressed

    private void miCrearCotizacionMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_miCrearCotizacionMousePressed
        // TODO add your handling code here:
            this.cotizacion();
    }//GEN-LAST:event_miCrearCotizacionMousePressed

    private void miCrearFacturaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_miCrearFacturaMousePressed
        // TODO add your handling code here:
       this.facturacion();
    }//GEN-LAST:event_miCrearFacturaMousePressed

    private void miBuscarFacturaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_miBuscarFacturaMousePressed
        // TODO add your handling code here:
        this.facturacion();
    }//GEN-LAST:event_miBuscarFacturaMousePressed

   
    private void miPagoNominaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_miPagoNominaMousePressed
        // TODO add your handling code here:
        this.PagarNomina();
    }//GEN-LAST:event_miPagoNominaMousePressed
 
    private void miCuadreCajaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_miCuadreCajaMousePressed
        // TODO add your handling code here:
       this.CuadreCaja();
    }//GEN-LAST:event_miCuadreCajaMousePressed

    private void miPagoClienteMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_miPagoClienteMousePressed
        // TODO add your handling code here:
        this.facturacion();
    }//GEN-LAST:event_miPagoClienteMousePressed

    private void miInventarioMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_miInventarioMousePressed
        // TODO add your handling code here:
        
         this.compraProducto();
    }//GEN-LAST:event_miInventarioMousePressed

    private void btInventarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btInventarioMouseClicked
        // TODO add your handling code here:
         this.crearProducto();
    }//GEN-LAST:event_btInventarioMouseClicked

    private void btClienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btClienteMouseClicked
        // TODO add your handling code here:
        this.administrarCliente();
        /*if(this.validador.getBtCrearPaciente()){
           this.crearCliente();
          //JFrameCrearPaciente crearPaciente=  new JFrameCrearPaciente(this.mysql);
          //crearPaciente.setDatosUsuario(this.session[0], this.session[1], this.session[3]);
          //crearPaciente.setVisible(true);
        }*/
    }//GEN-LAST:event_btClienteMouseClicked

    private void btFacturaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btFacturaMouseClicked
        // TODO add your handling code here:
        this.facturacion();
    }//GEN-LAST:event_btFacturaMouseClicked

    private void btCotizacionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btCotizacionMouseClicked
        // TODO add your handling code here:
         this.cotizacion();
    }//GEN-LAST:event_btCotizacionMouseClicked


    private void btConduceMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btConduceMouseClicked
        // TODO add your handling code here:
         this.reparacion();
    }//GEN-LAST:event_btConduceMouseClicked

    private void btUsuarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btUsuarioMouseClicked
        // TODO add your handling code here:
        this.crearUsuario();
    }//GEN-LAST:event_btUsuarioMouseClicked

    private void miBuscarCotizacionMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_miBuscarCotizacionMousePressed
        // TODO add your handling code here:
        this.cotizacion();
    }//GEN-LAST:event_miBuscarCotizacionMousePressed

    private void btReporteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btReporteMouseClicked
        // TODO add your handling code here:
        generarReporte();
    }//GEN-LAST:event_btReporteMouseClicked

    private void miContraroRentaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_miContraroRentaMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_miContraroRentaMousePressed

    private void JLCantidadReparacionMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JLCantidadReparacionMousePressed
        // TODO add your handling code here:
        //Cantidad 
        this.mostrarListadoReparaciones();
    }//GEN-LAST:event_JLCantidadReparacionMousePressed

    private void miUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miUsuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_miUsuarioActionPerformed

    private void miProveedorMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_miProveedorMousePressed
        // TODO add your handling code here:
        this.Proveedor();
    }//GEN-LAST:event_miProveedorMousePressed

    private void miPagoCliente1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_miPagoCliente1MousePressed
        // TODO add your handling code here:
        this.NCF();
    }//GEN-LAST:event_miPagoCliente1MousePressed

    private void miCambiarClave1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_miCambiarClave1MousePressed
        // TODO add your handling code here:
        BackupMysql.Generar();
    }//GEN-LAST:event_miCambiarClave1MousePressed

    private void miCrearFactura1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_miCrearFactura1MousePressed
        // TODO add your handling code here:
                                                   Log.Registrar(this.getClass(), "miCrearFactura1MousePressed", " entro");

        new formularios.Factura.FacturacionManual(this.mysql).setVisible(true);
    }//GEN-LAST:event_miCrearFactura1MousePressed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        // TODO add your handling code here:
        Log.Registrar("Cerro Main");
    }//GEN-LAST:event_formWindowClosed

    /**
     * @param args the command line arguments
     */
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel JLCantidadReparacion;
    private javax.swing.JLabel JLUsuarioConectado;
    private javax.swing.JButton btCliente;
    private javax.swing.JButton btConduce;
    private javax.swing.JButton btCotizacion;
    private javax.swing.JButton btFactura;
    private javax.swing.JButton btInventario;
    private javax.swing.JButton btReporte;
    private javax.swing.JButton btUsuario;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenu jMenu7;
    private javax.swing.JMenu jMenu8;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem16;
    private javax.swing.JMenuItem jMenuItem17;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JMenuItem miAbout;
    private javax.swing.JMenuItem miBuscarCliente;
    private javax.swing.JMenuItem miBuscarConduce;
    private javax.swing.JMenuItem miBuscarCotizacion;
    private javax.swing.JMenuItem miBuscarFactura;
    private javax.swing.JMenuItem miCambiarClave;
    private javax.swing.JMenuItem miCambiarClave1;
    private javax.swing.JMenuItem miContraroRenta;
    private javax.swing.JMenuItem miCrearCliente;
    private javax.swing.JMenuItem miCrearConduce;
    private javax.swing.JMenuItem miCrearCotizacion;
    private javax.swing.JMenuItem miCrearFactura;
    private javax.swing.JMenuItem miCrearFactura1;
    private javax.swing.JMenuItem miCuadreCaja;
    private javax.swing.JMenuItem miInventario;
    private javax.swing.JMenuItem miPagoCliente;
    private javax.swing.JMenuItem miPagoCliente1;
    private javax.swing.JMenuItem miPagoNomina;
    private javax.swing.JMenuItem miProveedor;
    private javax.swing.JMenuItem miReporte;
    private javax.swing.JMenuItem miSalir;
    private javax.swing.JMenuItem miUsuario;
    // End of variables declaration//GEN-END:variables
}
