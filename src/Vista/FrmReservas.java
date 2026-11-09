package Vista;

import Controladores.EmpleadoController;
import Controladores.HabitacionController;
import Controladores.HuespedController;
import Controladores.ReservaController;
import Modelos.Empleado;
import Modelos.Habitacion;
import Modelos.Huesped;
import Modelos.Recepcionista;
import Modelos.Reserva;
import java.awt.Color;
import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 * Pantalla "Reservas" (informe, seccion 14.1).
 * Pestana 1: Nueva reserva (REQ-006 a REQ-009). Pestana 2: lista de reservas con
 * cancelacion (REQ-010), check-in (REQ-011/012) y check-out con comprobante (REQ-013 a REQ-015).
 * Formulario de NetBeans: la parte visual se edita en FrmReservas.form.
 */
public class FrmReservas extends javax.swing.JFrame
{
    private static final int RES_COL_CODIGO = 0, RES_COL_ESTADO = 7;
    private static final int HAB_COL_NRO = 0;

    private final HuespedController huespedController;
    private final HabitacionController habitacionController;
    private final ReservaController reservaController;
    private final EmpleadoController empleadoController;

    private final ArrayList<Recepcionista> recepcionistas = new ArrayList<>();

    /** Reserva que se esta modificando; null cuando se registra una nueva. */
    private Reserva reservaEnEdicion = null;

    public FrmReservas(HuespedController huespedes, HabitacionController habitaciones,
                       ReservaController reservas, EmpleadoController empleados)
    {
        this.huespedController = huespedes;
        this.habitacionController = habitaciones;
        this.reservaController = reservas;
        this.empleadoController = empleados;
        initComponents();
        // al volver a esta ventana se refresca la lista de recepcionistas (si se registro uno en otra ventana)
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override public void windowActivated(java.awt.event.WindowEvent evt) { formWindowActivated(evt); }
        });

        // ajustes que el diseñador visual no cubre
        getContentPane().setBackground(UIUtil.FONDO);
        UIUtil.configurarSpinnerFecha(spEntrada, LocalDate.now());
        UIUtil.configurarSpinnerFecha(spSalida, LocalDate.now().plusDays(1));

        UIUtil.estilizarTabla(tblDisponibles);
        UIUtil.anchos(tblDisponibles, 90, 100, 200, 150, 120);
        scrollDisponibles.getViewport().setBackground(Color.WHITE);
        tblDisponibles.getSelectionModel().addListSelectionListener(e ->
        {
            if (!e.getValueIsAdjusting())
            {
                actualizarMonto();
            }
        });

        UIUtil.estilizarTabla(tblReservas);
        tblReservas.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        UIUtil.colorearEstado(tblReservas, RES_COL_ESTADO);
        UIUtil.anchos(tblReservas, UIUtil.ANCHOS_RESERVA);
        scrollReservas.getViewport().setBackground(Color.WHITE);

        UIUtil.cursorMano(btnBuscarHuesped, btnNuevoHuesped, btnDisponibles, btnGuardar, btnCancelar, btnCerrar,
                btnBuscar, btnTodas, btnCheckIn, btnCheckOut, btnModificar, btnCancelarReserva,
                btnEliminarReserva, btnDetalle, btnCerrarLista);

        setLocationRelativeTo(null);
        cargarRecepcionistas();
        cargarReservas();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlNorte = new javax.swing.JPanel();
        pnlEncabezado = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        lblSubtitulo = new javax.swing.JLabel();
        pnlRecepcionista = new javax.swing.JPanel();
        lblRecepcionista = new javax.swing.JLabel();
        cboRecepcionista = new javax.swing.JComboBox<>();
        lblNotaRecepcionista = new javax.swing.JLabel();
        pnlCentro = new javax.swing.JPanel();
        pestanas = new javax.swing.JTabbedPane();
        pnlNuevaReserva = new javax.swing.JPanel();
        pnlDatosReserva = new javax.swing.JPanel();
        lblModo = new javax.swing.JLabel();
        pnlFila1 = new javax.swing.JPanel();
        lblDocHuesped = new javax.swing.JLabel();
        txtDocHuesped = new javax.swing.JTextField();
        btnBuscarHuesped = new javax.swing.JButton();
        lblHuesped = new javax.swing.JLabel();
        btnNuevoHuesped = new javax.swing.JButton();
        pnlFila2 = new javax.swing.JPanel();
        lblEntrada = new javax.swing.JLabel();
        spEntrada = new javax.swing.JSpinner();
        lblSalida = new javax.swing.JLabel();
        spSalida = new javax.swing.JSpinner();
        lblTipoHabitacion = new javax.swing.JLabel();
        cboTipo = new javax.swing.JComboBox<>();
        btnDisponibles = new javax.swing.JButton();
        pnlDisponibles = new javax.swing.JPanel();
        scrollDisponibles = new javax.swing.JScrollPane();
        tblDisponibles = new javax.swing.JTable();
        pnlSurReserva = new javax.swing.JPanel();
        pnlMontos = new javax.swing.JPanel();
        lblNoches = new javax.swing.JLabel();
        lblMonto = new javax.swing.JLabel();
        pnlBotonesReserva = new javax.swing.JPanel();
        btnGuardar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnCerrar = new javax.swing.JButton();
        pnlListaReservas = new javax.swing.JPanel();
        pnlFiltro = new javax.swing.JPanel();
        lblCodigo = new javax.swing.JLabel();
        txtBuscar = new javax.swing.JTextField();
        lblEstado = new javax.swing.JLabel();
        cboEstado = new javax.swing.JComboBox<>();
        btnBuscar = new javax.swing.JButton();
        btnTodas = new javax.swing.JButton();
        scrollReservas = new javax.swing.JScrollPane();
        tblReservas = new javax.swing.JTable();
        pnlSurLista = new javax.swing.JPanel();
        pnlAcciones = new javax.swing.JPanel();
        btnCheckIn = new javax.swing.JButton();
        btnCheckOut = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnCancelarReserva = new javax.swing.JButton();
        btnEliminarReserva = new javax.swing.JButton();
        btnDetalle = new javax.swing.JButton();
        pnlCerrarLista = new javax.swing.JPanel();
        btnCerrarLista = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Gestión de Reservas - Indigo Loft");
        setMinimumSize(new java.awt.Dimension(900, 620));
        setSize(new java.awt.Dimension(1000, 700));

        pnlNorte.setOpaque(false);
        pnlNorte.setLayout(new java.awt.BorderLayout());

        pnlEncabezado.setBackground(new java.awt.Color(35, 59, 87));
        pnlEncabezado.setBorder(javax.swing.BorderFactory.createEmptyBorder(12, 18, 12, 18));
        pnlEncabezado.setLayout(new java.awt.GridLayout(2, 1, 0, 2));

        lblTitulo.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        lblTitulo.setForeground(new java.awt.Color(196, 155, 92));
        lblTitulo.setText("GESTIÓN DE RESERVAS");
        pnlEncabezado.add(lblTitulo);

        lblSubtitulo.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        lblSubtitulo.setForeground(new java.awt.Color(245, 235, 220));
        lblSubtitulo.setText("Busca disponibilidad, registra reservas y controla el ingreso y la salida de los huéspedes");
        pnlEncabezado.add(lblSubtitulo);

        pnlNorte.add(pnlEncabezado, java.awt.BorderLayout.NORTH);

        pnlRecepcionista.setBackground(new java.awt.Color(236, 230, 216));
        pnlRecepcionista.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 10, 6));

        lblRecepcionista.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        lblRecepcionista.setText("<html>Recepcionista a cargo <font color='#B71C1C'>*</font></html>");
        pnlRecepcionista.add(lblRecepcionista);

        cboRecepcionista.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        cboRecepcionista.setPreferredSize(new java.awt.Dimension(340, 28));
        pnlRecepcionista.add(cboRecepcionista);

        lblNotaRecepcionista.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        lblNotaRecepcionista.setText("Se registra en cada reserva, check-in y check-out.");
        pnlRecepcionista.add(lblNotaRecepcionista);

        pnlNorte.add(pnlRecepcionista, java.awt.BorderLayout.SOUTH);

        getContentPane().add(pnlNorte, java.awt.BorderLayout.NORTH);

        pnlCentro.setOpaque(false);
        pnlCentro.setBorder(javax.swing.BorderFactory.createEmptyBorder(8, 12, 12, 12));
        pnlCentro.setLayout(new java.awt.BorderLayout());

        pestanas.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        pestanas.addChangeListener(this::pestanasStateChanged);

        pnlNuevaReserva.setBackground(new java.awt.Color(245, 242, 235));
        pnlNuevaReserva.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        pnlNuevaReserva.setLayout(new java.awt.BorderLayout(0, 8));

        pnlDatosReserva.setOpaque(false);
        pnlDatosReserva.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos de la reserva", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("SansSerif", 1, 12), new java.awt.Color(35, 59, 87))); // NOI18N
        pnlDatosReserva.setLayout(new java.awt.GridLayout(3, 1, 0, 4));

        lblModo.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        lblModo.setForeground(new java.awt.Color(35, 59, 87));
        lblModo.setText("Nueva reserva");
        pnlDatosReserva.add(lblModo);

        pnlFila1.setOpaque(false);
        pnlFila1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 8, 2));

        lblDocHuesped.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        lblDocHuesped.setText("<html>Documento del huésped <font color='#B71C1C'>*</font></html>");
        pnlFila1.add(lblDocHuesped);

        txtDocHuesped.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        txtDocHuesped.setColumns(12);
        txtDocHuesped.addActionListener(this::txtDocHuespedActionPerformed);
        pnlFila1.add(txtDocHuesped);

        btnBuscarHuesped.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        btnBuscarHuesped.setBackground(new java.awt.Color(35, 59, 87));
        btnBuscarHuesped.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscarHuesped.setText("Buscar");
        btnBuscarHuesped.setOpaque(true);
        btnBuscarHuesped.setBorderPainted(false);
        btnBuscarHuesped.setFocusPainted(false);
        btnBuscarHuesped.setPreferredSize(new java.awt.Dimension(110, 32));
        btnBuscarHuesped.addActionListener(this::btnBuscarHuespedActionPerformed);
        pnlFila1.add(btnBuscarHuesped);

        lblHuesped.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        lblHuesped.setText("Ingrese el documento y presione Buscar.");
        pnlFila1.add(lblHuesped);

        btnNuevoHuesped.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        btnNuevoHuesped.setBackground(new java.awt.Color(35, 59, 87));
        btnNuevoHuesped.setForeground(new java.awt.Color(255, 255, 255));
        btnNuevoHuesped.setText("Nuevo huésped…");
        btnNuevoHuesped.setOpaque(true);
        btnNuevoHuesped.setBorderPainted(false);
        btnNuevoHuesped.setFocusPainted(false);
        btnNuevoHuesped.setPreferredSize(new java.awt.Dimension(157, 32));
        btnNuevoHuesped.addActionListener(this::btnNuevoHuespedActionPerformed);
        pnlFila1.add(btnNuevoHuesped);

        pnlDatosReserva.add(pnlFila1);

        pnlFila2.setOpaque(false);
        pnlFila2.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 8, 2));

        lblEntrada.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        lblEntrada.setText("<html>Entrada <font color='#B71C1C'>*</font></html>");
        pnlFila2.add(lblEntrada);

        spEntrada.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        spEntrada.setPreferredSize(new java.awt.Dimension(110, 28));
        spEntrada.addChangeListener(this::spEntradaStateChanged);
        pnlFila2.add(spEntrada);

        lblSalida.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        lblSalida.setText("<html>Salida <font color='#B71C1C'>*</font></html>");
        pnlFila2.add(lblSalida);

        spSalida.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        spSalida.setPreferredSize(new java.awt.Dimension(110, 28));
        spSalida.addChangeListener(this::spSalidaStateChanged);
        pnlFila2.add(spSalida);

        lblTipoHabitacion.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        lblTipoHabitacion.setText("Tipo de habitación");
        pnlFila2.add(lblTipoHabitacion);

        cboTipo.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        cboTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todas", "Simple", "Suite" }));
        pnlFila2.add(cboTipo);

        btnDisponibles.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        btnDisponibles.setBackground(new java.awt.Color(35, 59, 87));
        btnDisponibles.setForeground(new java.awt.Color(255, 255, 255));
        btnDisponibles.setText("Buscar disponibles");
        btnDisponibles.setOpaque(true);
        btnDisponibles.setBorderPainted(false);
        btnDisponibles.setFocusPainted(false);
        btnDisponibles.setPreferredSize(new java.awt.Dimension(166, 32));
        btnDisponibles.addActionListener(this::btnDisponiblesActionPerformed);
        pnlFila2.add(btnDisponibles);

        pnlDatosReserva.add(pnlFila2);

        pnlNuevaReserva.add(pnlDatosReserva, java.awt.BorderLayout.NORTH);

        pnlDisponibles.setOpaque(false);
        pnlDisponibles.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Habitaciones disponibles en ese rango (seleccione una o más)", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("SansSerif", 1, 12), new java.awt.Color(35, 59, 87))); // NOI18N
        pnlDisponibles.setLayout(new java.awt.BorderLayout());

        tblDisponibles.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Habitación", "Tipo", "Detalle", "Precio por noche S/", "Estado actual"
            }
        ));
        scrollDisponibles.setViewportView(tblDisponibles);

        pnlDisponibles.add(scrollDisponibles, java.awt.BorderLayout.CENTER);

        pnlNuevaReserva.add(pnlDisponibles, java.awt.BorderLayout.CENTER);

        pnlSurReserva.setOpaque(false);
        pnlSurReserva.setLayout(new java.awt.BorderLayout());

        pnlMontos.setOpaque(false);
        pnlMontos.setLayout(new java.awt.GridLayout(2, 1));

        lblNoches.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        lblNoches.setText("Noches: -");
        pnlMontos.add(lblNoches);

        lblMonto.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        lblMonto.setForeground(new java.awt.Color(35, 59, 87));
        lblMonto.setText("Monto: S/ 0.00");
        pnlMontos.add(lblMonto);

        pnlSurReserva.add(pnlMontos, java.awt.BorderLayout.WEST);

        pnlBotonesReserva.setOpaque(false);
        pnlBotonesReserva.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 6, 4));

        btnGuardar.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        btnGuardar.setBackground(new java.awt.Color(46, 125, 50));
        btnGuardar.setForeground(new java.awt.Color(255, 255, 255));
        btnGuardar.setText("Guardar");
        btnGuardar.setOpaque(true);
        btnGuardar.setBorderPainted(false);
        btnGuardar.setFocusPainted(false);
        btnGuardar.setPreferredSize(new java.awt.Dimension(110, 32));
        btnGuardar.addActionListener(this::btnGuardarActionPerformed);
        pnlBotonesReserva.add(btnGuardar);

        btnCancelar.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        btnCancelar.setBackground(new java.awt.Color(183, 28, 28));
        btnCancelar.setForeground(new java.awt.Color(255, 255, 255));
        btnCancelar.setText("Cancelar");
        btnCancelar.setOpaque(true);
        btnCancelar.setBorderPainted(false);
        btnCancelar.setFocusPainted(false);
        btnCancelar.setPreferredSize(new java.awt.Dimension(110, 32));
        btnCancelar.addActionListener(this::btnCancelarActionPerformed);
        pnlBotonesReserva.add(btnCancelar);

        btnCerrar.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        btnCerrar.setBackground(new java.awt.Color(35, 59, 87));
        btnCerrar.setForeground(new java.awt.Color(255, 255, 255));
        btnCerrar.setText("Cerrar");
        btnCerrar.setOpaque(true);
        btnCerrar.setBorderPainted(false);
        btnCerrar.setFocusPainted(false);
        btnCerrar.setPreferredSize(new java.awt.Dimension(110, 32));
        btnCerrar.addActionListener(this::btnCerrarActionPerformed);
        pnlBotonesReserva.add(btnCerrar);

        pnlSurReserva.add(pnlBotonesReserva, java.awt.BorderLayout.EAST);

        pnlNuevaReserva.add(pnlSurReserva, java.awt.BorderLayout.SOUTH);

        pestanas.addTab("Nueva reserva", pnlNuevaReserva);

        pnlListaReservas.setBackground(new java.awt.Color(245, 242, 235));
        pnlListaReservas.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        pnlListaReservas.setLayout(new java.awt.BorderLayout(0, 8));

        pnlFiltro.setOpaque(false);
        pnlFiltro.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 8, 4));

        lblCodigo.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        lblCodigo.setText("Código o documento:");
        pnlFiltro.add(lblCodigo);

        txtBuscar.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        txtBuscar.setColumns(16);
        txtBuscar.setToolTipText("Código de reserva o documento del huésped");
        txtBuscar.addActionListener(this::txtBuscarActionPerformed);
        pnlFiltro.add(txtBuscar);

        lblEstado.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        lblEstado.setText("Estado:");
        pnlFiltro.add(lblEstado);

        cboEstado.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        cboEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todos", "Confirmada", "En curso", "Finalizada", "Cancelada" }));
        cboEstado.addActionListener(this::cboEstadoActionPerformed);
        pnlFiltro.add(cboEstado);

        btnBuscar.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        btnBuscar.setBackground(new java.awt.Color(35, 59, 87));
        btnBuscar.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscar.setText("Buscar");
        btnBuscar.setOpaque(true);
        btnBuscar.setBorderPainted(false);
        btnBuscar.setFocusPainted(false);
        btnBuscar.setPreferredSize(new java.awt.Dimension(110, 32));
        btnBuscar.addActionListener(this::btnBuscarActionPerformed);
        pnlFiltro.add(btnBuscar);

        btnTodas.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        btnTodas.setBackground(new java.awt.Color(35, 59, 87));
        btnTodas.setForeground(new java.awt.Color(255, 255, 255));
        btnTodas.setText("Mostrar todas");
        btnTodas.setOpaque(true);
        btnTodas.setBorderPainted(false);
        btnTodas.setFocusPainted(false);
        btnTodas.setPreferredSize(new java.awt.Dimension(136, 32));
        btnTodas.addActionListener(this::btnTodasActionPerformed);
        pnlFiltro.add(btnTodas);

        pnlListaReservas.add(pnlFiltro, java.awt.BorderLayout.NORTH);

        tblReservas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Documento", "Huésped", "Habitaciones", "Entrada", "Salida", "Monto S/", "Estado", "Registró"
            }
        ));
        scrollReservas.setViewportView(tblReservas);

        pnlListaReservas.add(scrollReservas, java.awt.BorderLayout.CENTER);

        pnlSurLista.setOpaque(false);
        pnlSurLista.setLayout(new java.awt.BorderLayout());

        pnlAcciones.setOpaque(false);
        pnlAcciones.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 6, 4));

        btnCheckIn.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        btnCheckIn.setBackground(new java.awt.Color(46, 125, 50));
        btnCheckIn.setForeground(new java.awt.Color(255, 255, 255));
        btnCheckIn.setText("Check-in");
        btnCheckIn.setOpaque(true);
        btnCheckIn.setBorderPainted(false);
        btnCheckIn.setFocusPainted(false);
        btnCheckIn.setPreferredSize(new java.awt.Dimension(110, 32));
        btnCheckIn.addActionListener(this::btnCheckInActionPerformed);
        pnlAcciones.add(btnCheckIn);

        btnCheckOut.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        btnCheckOut.setBackground(new java.awt.Color(35, 59, 87));
        btnCheckOut.setForeground(new java.awt.Color(255, 255, 255));
        btnCheckOut.setText("Check-out");
        btnCheckOut.setOpaque(true);
        btnCheckOut.setBorderPainted(false);
        btnCheckOut.setFocusPainted(false);
        btnCheckOut.setPreferredSize(new java.awt.Dimension(115, 32));
        btnCheckOut.addActionListener(this::btnCheckOutActionPerformed);
        pnlAcciones.add(btnCheckOut);

        btnModificar.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        btnModificar.setBackground(new java.awt.Color(35, 59, 87));
        btnModificar.setForeground(new java.awt.Color(255, 255, 255));
        btnModificar.setText("Modificar reserva");
        btnModificar.setOpaque(true);
        btnModificar.setBorderPainted(false);
        btnModificar.setFocusPainted(false);
        btnModificar.setPreferredSize(new java.awt.Dimension(158, 32));
        btnModificar.addActionListener(this::btnModificarActionPerformed);
        pnlAcciones.add(btnModificar);

        btnCancelarReserva.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        btnCancelarReserva.setBackground(new java.awt.Color(183, 28, 28));
        btnCancelarReserva.setForeground(new java.awt.Color(255, 255, 255));
        btnCancelarReserva.setText("Cancelar reserva");
        btnCancelarReserva.setOpaque(true);
        btnCancelarReserva.setBorderPainted(false);
        btnCancelarReserva.setFocusPainted(false);
        btnCancelarReserva.setPreferredSize(new java.awt.Dimension(154, 32));
        btnCancelarReserva.addActionListener(this::btnCancelarReservaActionPerformed);
        pnlAcciones.add(btnCancelarReserva);

        btnEliminarReserva.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        btnEliminarReserva.setBackground(new java.awt.Color(183, 28, 28));
        btnEliminarReserva.setForeground(new java.awt.Color(255, 255, 255));
        btnEliminarReserva.setText("Eliminar reserva");
        btnEliminarReserva.setOpaque(true);
        btnEliminarReserva.setBorderPainted(false);
        btnEliminarReserva.setFocusPainted(false);
        btnEliminarReserva.setPreferredSize(new java.awt.Dimension(148, 32));
        btnEliminarReserva.addActionListener(this::btnEliminarReservaActionPerformed);
        pnlAcciones.add(btnEliminarReserva);

        btnDetalle.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        btnDetalle.setBackground(new java.awt.Color(35, 59, 87));
        btnDetalle.setForeground(new java.awt.Color(255, 255, 255));
        btnDetalle.setText("Ver detalle");
        btnDetalle.setOpaque(true);
        btnDetalle.setBorderPainted(false);
        btnDetalle.setFocusPainted(false);
        btnDetalle.setPreferredSize(new java.awt.Dimension(117, 32));
        btnDetalle.addActionListener(this::btnDetalleActionPerformed);
        pnlAcciones.add(btnDetalle);

        pnlSurLista.add(pnlAcciones, java.awt.BorderLayout.WEST);

        pnlCerrarLista.setOpaque(false);
        pnlCerrarLista.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 6, 4));

        btnCerrarLista.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        btnCerrarLista.setBackground(new java.awt.Color(35, 59, 87));
        btnCerrarLista.setForeground(new java.awt.Color(255, 255, 255));
        btnCerrarLista.setText("Cerrar");
        btnCerrarLista.setOpaque(true);
        btnCerrarLista.setBorderPainted(false);
        btnCerrarLista.setFocusPainted(false);
        btnCerrarLista.setPreferredSize(new java.awt.Dimension(110, 32));
        btnCerrarLista.addActionListener(this::btnCerrarListaActionPerformed);
        pnlCerrarLista.add(btnCerrarLista);

        pnlSurLista.add(pnlCerrarLista, java.awt.BorderLayout.EAST);

        pnlListaReservas.add(pnlSurLista, java.awt.BorderLayout.SOUTH);

        pestanas.addTab("Reservas · Check-in · Check-out", pnlListaReservas);

        pnlCentro.add(pestanas, java.awt.BorderLayout.CENTER);

        getContentPane().add(pnlCentro, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        // si se registra un recepcionista en otra ventana, la lista se actualiza al volver aqui
        cargarRecepcionistas();
    }//GEN-LAST:event_formWindowActivated

    private void pestanasStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_pestanasStateChanged
        if (pestanas.getSelectedIndex() == 1)
        {
            cargarReservas();
        }
    }//GEN-LAST:event_pestanasStateChanged

    private void txtDocHuespedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDocHuespedActionPerformed
        buscarHuesped();
    }//GEN-LAST:event_txtDocHuespedActionPerformed

    private void btnBuscarHuespedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarHuespedActionPerformed
        buscarHuesped();
    }//GEN-LAST:event_btnBuscarHuespedActionPerformed

    private void btnNuevoHuespedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoHuespedActionPerformed
        new FrmHuespedes(huespedController).setVisible(true);
    }//GEN-LAST:event_btnNuevoHuespedActionPerformed

    private void spEntradaStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spEntradaStateChanged
        limpiarResultados();
    }//GEN-LAST:event_spEntradaStateChanged

    private void spSalidaStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spSalidaStateChanged
        limpiarResultados();
    }//GEN-LAST:event_spSalidaStateChanged

    private void btnDisponiblesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDisponiblesActionPerformed
        buscarDisponibles();
    }//GEN-LAST:event_btnDisponiblesActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        guardarReserva();
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        limpiarFormulario();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarActionPerformed
        dispose();
    }//GEN-LAST:event_btnCerrarActionPerformed

    private void txtBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarActionPerformed
        cargarReservas();
    }//GEN-LAST:event_txtBuscarActionPerformed

    private void cboEstadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboEstadoActionPerformed
        cargarReservas();
    }//GEN-LAST:event_cboEstadoActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        cargarReservas();
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnTodasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTodasActionPerformed
        txtBuscar.setText("");
        cboEstado.setSelectedIndex(0);
        cargarReservas();
    }//GEN-LAST:event_btnTodasActionPerformed

    private void btnCheckInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCheckInActionPerformed
        hacerCheckIn();
    }//GEN-LAST:event_btnCheckInActionPerformed

    private void btnCheckOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCheckOutActionPerformed
        hacerCheckOut();
    }//GEN-LAST:event_btnCheckOutActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        modificarReserva();
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnCancelarReservaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarReservaActionPerformed
        cancelarReserva();
    }//GEN-LAST:event_btnCancelarReservaActionPerformed

    private void btnEliminarReservaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarReservaActionPerformed
        eliminarReserva();
    }//GEN-LAST:event_btnEliminarReservaActionPerformed

    private void btnDetalleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDetalleActionPerformed
        verDetalle();
    }//GEN-LAST:event_btnDetalleActionPerformed

    private void btnCerrarListaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarListaActionPerformed
        dispose();
    }//GEN-LAST:event_btnCerrarListaActionPerformed

    // ----------------------------------------------------------------- recepcionistas

    private void cargarRecepcionistas()
    {
        String docSeleccionado = null;
        Recepcionista actual = recepcionistaSeleccionado();
        if (actual != null)
        {
            docSeleccionado = actual.getNro_doc();
        }
        recepcionistas.clear();
        cboRecepcionista.removeAllItems();
        cboRecepcionista.addItem("Seleccione un recepcionista");
        for (Empleado e : empleadoController.ListaEmpleados)
        {
            if (e instanceof Recepcionista)
            {
                recepcionistas.add((Recepcionista) e);
                cboRecepcionista.addItem(UIUtil.nombreCompleto(e) + " - " + e.getTipo_doc().toUpperCase() + ": " + e.getNro_doc());
            }
        }
        if (docSeleccionado != null)
        {
            for (int i = 0; i < recepcionistas.size(); i++)
            {
                if (recepcionistas.get(i).getNro_doc().equals(docSeleccionado))
                {
                    cboRecepcionista.setSelectedIndex(i + 1);
                }
            }
        }
        else if (recepcionistas.size() == 1)
        {
            cboRecepcionista.setSelectedIndex(1);
        }
    }

    private Recepcionista recepcionistaSeleccionado()
    {
        int i = cboRecepcionista.getSelectedIndex();
        if (i <= 0 || i > recepcionistas.size())
        {
            return null;
        }
        return recepcionistas.get(i - 1);
    }

    // ---------------------------------------------------------------- nueva reserva

    private void buscarHuesped()
    {
        String doc = txtDocHuesped.getText().trim();
        if (doc.isEmpty())
        {
            UIUtil.mostrarError(this, "Ingrese el documento del huésped.");
            return;
        }
        Huesped h = huespedController.Buscarhuesped(doc);
        if (h == null)
        {
            lblHuesped.setText("No registrado: use \"Nuevo huésped…\"");
            lblHuesped.setForeground(UIUtil.ROJO);
        }
        else
        {
            lblHuesped.setText(UIUtil.nombreCompleto(h));
            lblHuesped.setForeground(UIUtil.VERDE);
        }
    }

    private void buscarDisponibles()
    {
        try
        {
            ArrayList<Habitacion> lista = reservaController.HabitacionesDisponibles(
                    habitacionController, UIUtil.fecha(spEntrada), UIUtil.fecha(spSalida),
                    cboTipo.getSelectedItem().toString(), reservaEnEdicion);
            DefaultTableModel modelo = UIUtil.modelo(tblDisponibles);
            modelo.setRowCount(0);
            for (Habitacion h : lista)
            {
                modelo.addRow(new Object[]
                {
                    h.getNro_habitacion(),
                    UIUtil.tipoHabitacion(h),
                    UIUtil.detalleHabitacion(h),
                    UIUtil.soles(h.getPrecio_noche()),
                    UIUtil.capitalizar(h.getEstado())
                });
            }
            actualizarMonto();
            if (lista.isEmpty())
            {
                UIUtil.mostrarInfo(this, "No hay habitaciones disponibles para ese rango de fechas.");
            }
        }
        catch (IllegalArgumentException ex)
        {
            UIUtil.mostrarError(this, ex.getMessage());
        }
    }

    private void limpiarResultados()
    {
        UIUtil.modelo(tblDisponibles).setRowCount(0);
        actualizarMonto();
    }

    private ArrayList<Habitacion> habitacionesSeleccionadas()
    {
        ArrayList<Habitacion> lista = new ArrayList<>();
        for (int fila : tblDisponibles.getSelectedRows())
        {
            int nro = (Integer) tblDisponibles.getModel().getValueAt(tblDisponibles.convertRowIndexToModel(fila), HAB_COL_NRO);
            Habitacion h = habitacionController.BuscarHabitacion(nro);
            if (h != null)
            {
                lista.add(h);
            }
        }
        return lista;
    }

    /** REQ-009: monto = noches x precio por noche (se muestra sin IGV; el IGV va en la factura). */
    private void actualizarMonto()
    {
        long noches = ChronoUnit.DAYS.between(UIUtil.fecha(spEntrada), UIUtil.fecha(spSalida));
        double porNoche = 0;
        for (Habitacion h : habitacionesSeleccionadas())
        {
            porNoche += h.getPrecio_noche();
        }
        lblNoches.setText("Noches: " + (noches > 0 ? noches : "-"));
        lblMonto.setText("Monto: S/ " + UIUtil.soles(noches > 0 ? noches * porNoche : 0));
    }

    private void guardarReserva()
    {
        try
        {
            if (reservaEnEdicion != null)
            {
                ArrayList<Habitacion> nuevas = habitacionesSeleccionadas();
                if (nuevas.isEmpty())
                {
                    UIUtil.mostrarError(this, "Busque las habitaciones disponibles y seleccione al menos una.");
                    return;
                }
                reservaController.ModificarReserva(reservaEnEdicion, UIUtil.fecha(spEntrada), UIUtil.fecha(spSalida), nuevas);
                UIUtil.mostrarInfo(this, "Reserva " + reservaEnEdicion.getCod_reserva() + " modificada correctamente.\n"
                        + "Nuevo monto: S/ " + UIUtil.soles(reservaEnEdicion.CalcularMonto()) + " (más IGV 18% en la factura)");
                limpiarFormulario();
                cargarReservas();
                pestanas.setSelectedIndex(1);
                return;
            }
            Recepcionista recepcionista = recepcionistaSeleccionado();
            if (recepcionista == null)
            {
                UIUtil.mostrarError(this, "Seleccione el recepcionista a cargo.\n"
                        + (recepcionistas.isEmpty() ? "Aún no hay recepcionistas registrados (módulo Empleados)." : ""));
                return;
            }
            String doc = txtDocHuesped.getText().trim();
            if (doc.isEmpty())
            {
                UIUtil.mostrarError(this, "Ingrese el documento del huésped.");
                return;
            }
            Huesped huesped = huespedController.Buscarhuesped(doc);
            if (huesped == null)
            {
                UIUtil.mostrarError(this, "El huésped no está registrado. Toda reserva debe estar asociada a un huésped registrado.\n"
                        + "Use el botón \"Nuevo huésped…\".");
                return;
            }
            ArrayList<Habitacion> habitaciones = habitacionesSeleccionadas();
            if (habitaciones.isEmpty())
            {
                UIUtil.mostrarError(this, "Busque las habitaciones disponibles y seleccione al menos una.");
                return;
            }

            Reserva r = reservaController.CrearReserva(huesped, recepcionista,
                    UIUtil.fecha(spEntrada), UIUtil.fecha(spSalida), habitaciones);

            UIUtil.mostrarInfo(this, "Reserva registrada correctamente.\n\n"
                    + "Código: " + r.getCod_reserva() + "\n"
                    + "Huésped: " + UIUtil.nombreCompleto(huesped) + "\n"
                    + "Habitaciones: " + UIUtil.habitacionesTexto(r) + "\n"
                    + "Del " + r.getFecha_inicio().format(UIUtil.FORMATO_FECHA)
                    + " al " + r.getFecha_fin().format(UIUtil.FORMATO_FECHA) + "\n"
                    + "Monto: S/ " + UIUtil.soles(r.CalcularMonto()) + " (más IGV 18% en la factura)");
            limpiarFormulario();
            cargarReservas();
        }
        catch (IllegalArgumentException ex)
        {
            UIUtil.mostrarError(this, ex.getMessage());
            // otra reserva pudo ocupar la habitacion: se refresca la lista
            limpiarResultados();
        }
    }

    private void limpiarFormulario()
    {
        reservaEnEdicion = null;
        UIUtil.bloquear(txtDocHuesped, false);
        lblModo.setText("Nueva reserva");
        txtDocHuesped.setText("");
        lblHuesped.setText("Ingrese el documento y presione Buscar.");
        lblHuesped.setForeground(Color.BLACK);
        UIUtil.fijarFecha(spEntrada, LocalDate.now());
        UIUtil.fijarFecha(spSalida, LocalDate.now().plusDays(1));
        cboTipo.setSelectedIndex(0);
        limpiarResultados();
    }

    // --------------------------------------------------- lista, cancelar, check-in/out

    private void cargarReservas()
    {
        DefaultTableModel modelo = UIUtil.modelo(tblReservas);
        modelo.setRowCount(0);
        String f = txtBuscar.getText().trim().toLowerCase();
        String estado = cboEstado.getSelectedItem().toString();
        for (Reserva r : reservaController.ListaReservas)
        {
            if (!f.isEmpty() && !r.getCod_reserva().toLowerCase().contains(f)
                    && !r.getCliente().getNro_doc().toLowerCase().contains(f))
            {
                continue;
            }
            if (!estado.equals("Todos") && !estado.equals(r.getEstadoReserva()))
            {
                continue;
            }
            modelo.addRow(UIUtil.filaReserva(r));
        }
    }

    private Reserva reservaSeleccionada()
    {
        Object cod = UIUtil.valorSeleccionado(tblReservas, RES_COL_CODIGO);
        if (cod == null)
        {
            UIUtil.mostrarError(this, "Seleccione una reserva de la tabla.");
            return null;
        }
        return reservaController.BuscaReserva(cod.toString());
    }

    private Recepcionista exigirRecepcionista()
    {
        Recepcionista r = recepcionistaSeleccionado();
        if (r == null)
        {
            UIUtil.mostrarError(this, "Seleccione el recepcionista a cargo (barra superior).");
        }
        return r;
    }

    private void hacerCheckIn()
    {
        Reserva r = reservaSeleccionada();
        Recepcionista rec = (r == null) ? null : exigirRecepcionista();
        if (r == null || rec == null)
        {
            return;
        }
        try
        {
            r.RealizarCheckIn(rec);
            cargarReservas();
            UIUtil.mostrarInfo(this, "Check-in realizado. La reserva " + r.getCod_reserva()
                    + " está En curso y las habitaciones quedaron Ocupadas.");
        }
        catch (IllegalArgumentException ex)
        {
            UIUtil.mostrarError(this, ex.getMessage());
        }
    }

    private void hacerCheckOut()
    {
        Reserva r = reservaSeleccionada();
        Recepcionista rec = (r == null) ? null : exigirRecepcionista();
        if (r == null || rec == null)
        {
            return;
        }
        if (!Reserva.EN_CURSO.equals(r.getEstadoReserva()))
        {
            UIUtil.mostrarError(this, "Solo se puede hacer check-out de una reserva En curso (estado actual: "
                    + r.getEstadoReserva() + ").");
            return;
        }
        int ok = JOptionPane.showConfirmDialog(this,
                "¿Registrar la salida del huésped de la reserva " + r.getCod_reserva() + "?\n"
                + "Se calculará el monto final y las habitaciones pasarán a Limpieza.",
                "Confirmar check-out", JOptionPane.YES_NO_OPTION);
        if (ok != JOptionPane.YES_OPTION)
        {
            return;
        }
        try
        {
            r.RealizarCheckOut(rec);
            cargarReservas();
            // REQ-015: comprobante en texto
            String archivo = "comprobante_" + r.getCod_reserva() + ".txt";
            String aviso;
            try
            {
                r.getFactura().ExportarFacturaTXT(archivo);
                aviso = "\n\nComprobante guardado en: " + new java.io.File(archivo).getAbsolutePath();
            }
            catch (IOException ioe)
            {
                aviso = "\n\nNo se pudo guardar el comprobante: " + ioe.getMessage();
            }
            UIUtil.mostrarTexto(this, "Check-out realizado",
                    r.getFactura().MostrarDatosFactura() + aviso);
        }
        catch (IllegalArgumentException ex)
        {
            UIUtil.mostrarError(this, ex.getMessage());
        }
    }

    /** U: carga la reserva Confirmada en la pestana "Nueva reserva" para cambiar fechas y habitaciones. */
    private void modificarReserva()
    {
        Reserva r = reservaSeleccionada();
        if (r == null)
        {
            return;
        }
        if (!Reserva.CONFIRMADA.equals(r.getEstadoReserva()))
        {
            UIUtil.mostrarError(this, "Solo se pueden modificar reservas en estado Confirmada (estado actual: "
                    + r.getEstadoReserva() + ").");
            return;
        }
        limpiarFormulario();
        reservaEnEdicion = r;
        txtDocHuesped.setText(r.getCliente().getNro_doc());
        UIUtil.bloquear(txtDocHuesped, true);      // el huesped de la reserva no cambia
        lblHuesped.setText(UIUtil.nombreCompleto(r.getCliente()));
        lblHuesped.setForeground(UIUtil.VERDE);
        lblModo.setText("Modificando reserva " + r.getCod_reserva() + " (cambie fechas o habitaciones y presione Guardar)");
        // la fecha de entrada nunca es anterior a hoy
        UIUtil.fijarFecha(spEntrada, r.getFecha_inicio().isBefore(LocalDate.now()) ? LocalDate.now() : r.getFecha_inicio());
        UIUtil.fijarFecha(spSalida, r.getFecha_fin());
        pestanas.setSelectedIndex(0);
        buscarDisponibles();
        // se marcan las habitaciones actuales de la reserva
        for (int i = 0; i < tblDisponibles.getRowCount(); i++)
        {
            int nro = (Integer) tblDisponibles.getModel().getValueAt(tblDisponibles.convertRowIndexToModel(i), HAB_COL_NRO);
            for (Habitacion h : r.getListaHabitaciones())
            {
                if (h.getNro_habitacion() == nro)
                {
                    tblDisponibles.addRowSelectionInterval(i, i);
                }
            }
        }
    }

    /** D: elimina la reserva (no se permite En curso). */
    private void eliminarReserva()
    {
        Reserva r = reservaSeleccionada();
        if (r == null)
        {
            return;
        }
        if (Reserva.EN_CURSO.equals(r.getEstadoReserva()))
        {
            UIUtil.mostrarError(this, "No se puede eliminar una reserva En curso. Haga primero el check-out.");
            return;
        }
        int ok = JOptionPane.showConfirmDialog(this,
                "¿Eliminar la reserva " + r.getCod_reserva() + " (" + r.getEstadoReserva() + ")?\n"
                + "Se quitará del sistema y del historial del huésped. Esta acción no se puede deshacer.",
                "Confirmar eliminación", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        if (ok != JOptionPane.YES_OPTION)
        {
            return;
        }
        try
        {
            reservaController.EliminarReserva(r);
            if (reservaEnEdicion == r)
            {
                limpiarFormulario();
            }
            cargarReservas();
        }
        catch (IllegalArgumentException ex)
        {
            UIUtil.mostrarError(this, ex.getMessage());
        }
    }

    private void cancelarReserva()
    {
        Reserva r = reservaSeleccionada();
        if (r == null)
        {
            return;
        }
        if (!Reserva.CONFIRMADA.equals(r.getEstadoReserva()))
        {
            UIUtil.mostrarError(this, "Solo se pueden cancelar reservas en estado Confirmada (estado actual: "
                    + r.getEstadoReserva() + ").");
            return;
        }
        int ok = JOptionPane.showConfirmDialog(this,
                "¿Cancelar la reserva " + r.getCod_reserva() + "?\nLas fechas quedarán libres para otras reservas.",
                "Confirmar cancelación", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        if (ok != JOptionPane.YES_OPTION)
        {
            return;
        }
        try
        {
            r.Cancelar();
            cargarReservas();
        }
        catch (IllegalArgumentException ex)
        {
            UIUtil.mostrarError(this, ex.getMessage());
        }
    }

    private void verDetalle()
    {
        Reserva r = reservaSeleccionada();
        if (r != null)
        {
            UIUtil.mostrarTexto(this, "Detalle de la reserva " + r.getCod_reserva(),
                    r.MostrarDatosReserva() + "\n\n" + r.MostrarListaHabitaciones() + "\n" + r.getFactura().MostrarDatosFactura());
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnBuscarHuesped;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnCancelarReserva;
    private javax.swing.JButton btnCerrar;
    private javax.swing.JButton btnCerrarLista;
    private javax.swing.JButton btnCheckIn;
    private javax.swing.JButton btnCheckOut;
    private javax.swing.JButton btnDetalle;
    private javax.swing.JButton btnDisponibles;
    private javax.swing.JButton btnEliminarReserva;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnNuevoHuesped;
    private javax.swing.JButton btnTodas;
    private javax.swing.JComboBox<String> cboEstado;
    private javax.swing.JComboBox<String> cboRecepcionista;
    private javax.swing.JComboBox<String> cboTipo;
    private javax.swing.JLabel lblCodigo;
    private javax.swing.JLabel lblDocHuesped;
    private javax.swing.JLabel lblEntrada;
    private javax.swing.JLabel lblEstado;
    private javax.swing.JLabel lblHuesped;
    private javax.swing.JLabel lblModo;
    private javax.swing.JLabel lblMonto;
    private javax.swing.JLabel lblNoches;
    private javax.swing.JLabel lblNotaRecepcionista;
    private javax.swing.JLabel lblRecepcionista;
    private javax.swing.JLabel lblSalida;
    private javax.swing.JLabel lblSubtitulo;
    private javax.swing.JLabel lblTipoHabitacion;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JTabbedPane pestanas;
    private javax.swing.JPanel pnlAcciones;
    private javax.swing.JPanel pnlBotonesReserva;
    private javax.swing.JPanel pnlCentro;
    private javax.swing.JPanel pnlCerrarLista;
    private javax.swing.JPanel pnlDatosReserva;
    private javax.swing.JPanel pnlDisponibles;
    private javax.swing.JPanel pnlEncabezado;
    private javax.swing.JPanel pnlFila1;
    private javax.swing.JPanel pnlFila2;
    private javax.swing.JPanel pnlFiltro;
    private javax.swing.JPanel pnlListaReservas;
    private javax.swing.JPanel pnlMontos;
    private javax.swing.JPanel pnlNorte;
    private javax.swing.JPanel pnlNuevaReserva;
    private javax.swing.JPanel pnlRecepcionista;
    private javax.swing.JPanel pnlSurLista;
    private javax.swing.JPanel pnlSurReserva;
    private javax.swing.JScrollPane scrollDisponibles;
    private javax.swing.JScrollPane scrollReservas;
    private javax.swing.JSpinner spEntrada;
    private javax.swing.JSpinner spSalida;
    private javax.swing.JTable tblDisponibles;
    private javax.swing.JTable tblReservas;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtDocHuesped;
    // End of variables declaration//GEN-END:variables
}
