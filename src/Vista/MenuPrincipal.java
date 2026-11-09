package Vista;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Image;
import Controladores.EmpleadoController;
import Controladores.HabitacionController;
import Controladores.HuespedController;
import Controladores.ReservaController;


import Controladores.DepartamentoController;

public class MenuPrincipal extends javax.swing.JFrame {

    private JPanel panelPrincipal;
    private JLabel lblFondo;
    private JButton btnDepartamentos, btnHabitaciones, btnEmpleados, btnHuespedes, btnReservas, btnReportes;
    
    private DepartamentoController departamentoController = new DepartamentoController();
    private EmpleadoController empleadoController = new EmpleadoController();
    private HabitacionController habitacionController = new HabitacionController();
    private HuespedController huespedController = new HuespedController();
    private ReservaController reservaController = new ReservaController();
    
    public MenuPrincipal() 
    {
        initComponents();
        configurarEfectoHover();
        configurarEtiquetas();
          
        jButton3.addActionListener(e -> 
        {
            FrmDepartamentos ventana = new FrmDepartamentos(departamentoController,empleadoController);
            ventana.setVisible(true);
        });
          
        // HUÉSPEDES
        jButton2.addActionListener(e -> 
        {
            FrmHuespedes ventana = new FrmHuespedes(huespedController);
            ventana.setVisible(true);
        });
        
        // RESERVAS (check-in / check-out incluidos)
        jButton4.addActionListener(e -> 
        {
            FrmReservas ventana = new FrmReservas(huespedController, habitacionController, reservaController, empleadoController);
            ventana.setVisible(true);
        });
        
        // REPORTES
        jButton5.addActionListener(e -> 
        {
            FrmReportes ventana = new FrmReportes(departamentoController, empleadoController, habitacionController, reservaController, huespedController);
            ventana.setVisible(true);
        });
         // EMPLEADOS
        jButton7.addActionListener(e -> 
        {
            FrmEmpleados ventana = new FrmEmpleados(empleadoController, departamentoController);
            ventana.setVisible(true);
        });        
        //HABITACIONES
        jButton1.addActionListener(e -> 
        {
            FmrHabitaciones ventana = new FmrHabitaciones(habitacionController);
            ventana.setVisible(true);
        });
        // configuración de la ventana principal
        setTitle("Indigo Loft - Menú Principal");
        setSize(924, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        colocarFondo();
        setVisible(true);
    }

private void inicializarBotones() 
{
    // Creamos los 6 botones
        btnDepartamentos = new JButton("Departamentos");
        btnHabitaciones = new JButton("Habitaciones");
        btnEmpleados = new JButton("Empleados");
        btnHuespedes = new JButton("Huéspedes");
        btnReservas = new JButton("Reservas");
        btnReportes = new JButton("Reportes");

        // Asignamos coordenadas exactas
        // Fila 1
        btnDepartamentos.setBounds(150, 180, 230, 70);
        btnHabitaciones.setBounds(400, 180, 230, 70);
        btnEmpleados.setBounds(650, 180, 230, 70);

        // Fila 2
        btnHuespedes.setBounds(150, 320, 230, 70);
        btnReservas.setBounds(400, 320, 230, 70);
        btnReportes.setBounds(650, 320, 230, 70);

        // Los agregamos al panel PRINCIPAL (para que queden ENCIMA del fondo)
        panelPrincipal.add(btnDepartamentos);
        panelPrincipal.add(btnHabitaciones);
        panelPrincipal.add(btnEmpleados);
        panelPrincipal.add(btnHuespedes);
        panelPrincipal.add(btnReservas);
        panelPrincipal.add(btnReportes);

        // Forzamos el orden de las capas: Imagen al fondo (-1) y los botones al frente
        panelPrincipal.setComponentZOrder(lblFondo, panelPrincipal.getComponentCount() - 1);
}
    
private void configurarEfectoHover() 
{
    javax.swing.JButton[] botones = 
    {
        jButton3, 
        jButton1, 
        jButton7,
        jButton2, 
        jButton4, 
        jButton5,
    };

    for (javax.swing.JButton boton : botones) {

        // Fondo oscuro translúcido
        boton.setBackground(new java.awt.Color(30, 25, 21, 205));
        boton.setForeground(new java.awt.Color(245, 235, 220));

        boton.setFont(new java.awt.Font("SansSerif", 1, 17));

        boton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        boton.setOpaque(true);
        boton.setContentAreaFilled(true);
        boton.setFocusPainted(false);

        // Borde dorado
        boton.setBorder(javax.swing.BorderFactory.createCompoundBorder
        (new javax.swing.border.LineBorder(new java.awt.Color(196, 155, 92), 2, true)
        ,new javax.swing.border.EmptyBorder(10, 15, 10, 15)));

        boton.setBorderPainted(true);

        boton.addMouseListener(new java.awt.event.MouseAdapter() 
        {

            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                boton.setBackground(
                    new java.awt.Color(75, 58, 42, 225)
                );
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                boton.setBackground(
                    new java.awt.Color(30, 25, 21, 205)
                );
            }

            @Override
            public void mousePressed(java.awt.event.MouseEvent e) {
                boton.setBackground(
                    new java.awt.Color(15, 12, 10, 235)
                );
            }

            @Override
            public void mouseReleased(java.awt.event.MouseEvent e) {
                if (boton.contains(e.getPoint())) {
                    boton.setBackground(
                        new java.awt.Color(75, 58, 42, 225)
                    );
                } else {
                    boton.setBackground(
                        new java.awt.Color(30, 25, 21, 205)
                    );
                }
            }
        });
    }
}

private void configurarEtiquetas() {

    // Título principal
    jLabel1.setForeground(new java.awt.Color(196, 155, 92));

    // Subtítulo
    jLabel2.setForeground(new java.awt.Color(245, 235, 220));

    // Título de sección
    jLabel3.setForeground(java.awt.Color.WHITE);

    // Instrucción
    jLabel4.setForeground(new java.awt.Color(245, 235, 220));

    // Pie de página
    jLabel5.setForeground(java.awt.Color.WHITE);
}

private void colocarFondo() 
{
    try 
    {
        ImageIcon imagenOriginal = new ImageIcon(
            getClass().getResource("/Vista/fondo_indigo.jpg")
        );

        Image imagenEscalada = imagenOriginal.getImage()
            .getScaledInstance(924, 700, Image.SCALE_SMOOTH);

        JLabel fondo = new JLabel(new ImageIcon(imagenEscalada));

        fondo.setBounds(0, 0, 924, 700);

        getContentPane().add(
            fondo,
            new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 924, 700)
        );

        getContentPane().setComponentZOrder(
            fondo,
            getContentPane().getComponentCount() - 1
        );
    } catch (Exception e) 
    {
        System.out.println("Error al cargar el fondo: " + e.getMessage());
    }
}

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Indigo Loft - Menu Principal");
        setName("MenuPrincipal"); // NOI18N
        setResizable(false);
        setSize(new java.awt.Dimension(924, 700));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(245, 247, 250));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(111, 88, -1, -1));

        jLabel1.setBackground(new java.awt.Color(35, 45, 60));
        jLabel1.setFont(new java.awt.Font("Serif", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 51, 51));
        jLabel1.setText("INDIGO LOFT");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 250, 60));

        jLabel2.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(55, 65, 80));
        jLabel2.setText("SISTEMA DE GESTION HOTELERA");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 260, 30));

        jLabel3.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(35, 45, 60));
        jLabel3.setText("GESTION DEL HOTEL");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 250, 30));

        jLabel4.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(35, 45, 60));
        jLabel4.setText("SELECCIONA EL MODULO QUE DESEAS ADMINISTRAR");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, -1, -1));

        jButton1.setBackground(new java.awt.Color(35, 59, 87));
        jButton1.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("HABITACIONES");
        jButton1.setBorderPainted(false);
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButton1.setFocusPainted(false);
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 190, 290, 105));

        jButton2.setBackground(new java.awt.Color(35, 59, 87));
        jButton2.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("HUESPEDES");
        jButton2.setBorderPainted(false);
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButton2.setFocusPainted(false);
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 300, 290, 105));

        jButton3.setBackground(new java.awt.Color(35, 59, 87));
        jButton3.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("DEPARTAMENTOS");
        jButton3.setBorderPainted(false);
        jButton3.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButton3.setFocusPainted(false);
        jButton3.setOpaque(true);
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 190, 290, 105));

        jButton4.setBackground(new java.awt.Color(35, 59, 87));
        jButton4.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setText("RESERVAS");
        jButton4.setBorderPainted(false);
        jButton4.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButton4.setFocusPainted(false);
        getContentPane().add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 300, 290, 105));

        jButton5.setBackground(new java.awt.Color(35, 59, 87));
        jButton5.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        jButton5.setForeground(new java.awt.Color(255, 255, 255));
        jButton5.setText("REPORTES");
        jButton5.setBorderPainted(false);
        jButton5.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButton5.setFocusPainted(false);
        getContentPane().add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 300, 290, 105));

        jButton7.setBackground(new java.awt.Color(35, 59, 87));
        jButton7.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        jButton7.setForeground(new java.awt.Color(255, 255, 255));
        jButton7.setText("EMPLEADOS");
        jButton7.setBorderPainted(false);
        jButton7.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButton7.setFocusPainted(false);
        jButton7.addActionListener(this::jButton7ActionPerformed);
        getContentPane().add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 190, 290, 105));

        jLabel5.setFont(new java.awt.Font("SansSerif", 0, 11)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(70, 80, 95));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("INDIGO LOFT  |  SISTEMA DE GESTIÓN HOTELERA");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(-5, 410, 920, 25));
    }// </editor-fold>//GEN-END:initComponents

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton7ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables

}