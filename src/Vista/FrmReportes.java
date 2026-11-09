package Vista;

import Controladores.DepartamentoController;
import Controladores.EmpleadoController;
import Controladores.HabitacionController;
import Controladores.HuespedController;
import Controladores.ReservaController;
import Modelos.Administrativo;
import Modelos.Conserje;
import Modelos.Departamento;
import Modelos.Empleado;
import Modelos.Gerente;
import Modelos.Habitacion;
import Modelos.Huesped;
import Modelos.Recepcionista;
import Modelos.Reserva;
import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

public class FrmReportes extends javax.swing.JFrame
{
    // deben coincidir con los textos del combo "cboReporte" definido en el .form
    private static final String REP_OCUPACION = "Ocupación del día";
    private static final String REP_RESERVAS = "Reservas por fecha";
    private static final String REP_HISTORIAL = "Historial de un huésped";
    private static final String REP_DEPARTAMENTOS = "Listado de departamentos";
    private static final String REP_EMPLEADOS = "Listado de empleados";
    private static final String REP_HABITACIONES = "Listado de habitaciones";
    private static final String REP_HUESPEDES = "Listado de huéspedes";
    private static final String REP_LISTA_RESERVAS = "Listado general de reservas";

    private final DepartamentoController departamentoController;
    private final EmpleadoController empleadoController;
    private final HabitacionController habitacionController;
    private final ReservaController reservaController;
    private final HuespedController huespedController;

    // ultimo reporte generado (lo que se exporta)
    private String tituloGenerado = null;
    private String resumenGenerado = "";
    private String archivoSugerido = "reporte.txt";

    public FrmReportes(DepartamentoController departamentos, EmpleadoController empleados,
            HabitacionController habitaciones, ReservaController reservas, HuespedController huespedes)
    {
        this.departamentoController = departamentos;
        this.empleadoController = empleados;
        this.habitacionController = habitaciones;
        this.reservaController = reservas;
        this.huespedController = huespedes;
        initComponents();

        // ajustes que el diseñador visual no cubre
        getContentPane().setBackground(UIUtil.FONDO);
        UIUtil.estilizarTabla(tabla);
        scrollTabla.getViewport().setBackground(Color.WHITE);
        UIUtil.configurarSpinnerFecha(spDesde, LocalDate.now());
        UIUtil.configurarSpinnerFecha(spHasta, LocalDate.now());
        UIUtil.cursorMano(btnGenerar, btnExportar, btnCerrar);

        setLocationRelativeTo(null);
        actualizarFiltros();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlEncabezado = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        lblSubtitulo = new javax.swing.JLabel();
        pnlCentro = new javax.swing.JPanel();
        pnlConsulta = new javax.swing.JPanel();
        lblReporte = new javax.swing.JLabel();
        cboReporte = new javax.swing.JComboBox<>();
        lblDesde = new javax.swing.JLabel();
        spDesde = new javax.swing.JSpinner();
        lblHasta = new javax.swing.JLabel();
        spHasta = new javax.swing.JSpinner();
        lblDoc = new javax.swing.JLabel();
        txtDoc = new javax.swing.JTextField();
        btnGenerar = new javax.swing.JButton();
        pnlResultados = new javax.swing.JPanel();
        scrollTabla = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        lblResumen = new javax.swing.JLabel();
        pnlPie = new javax.swing.JPanel();
        btnExportar = new javax.swing.JButton();
        btnCerrar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Reportes - Indigo Loft");
        setMinimumSize(new java.awt.Dimension(900, 560));
        setSize(new java.awt.Dimension(1000, 650));

        pnlEncabezado.setBackground(new java.awt.Color(35, 59, 87));
        pnlEncabezado.setBorder(javax.swing.BorderFactory.createEmptyBorder(12, 18, 12, 18));
        pnlEncabezado.setLayout(new java.awt.GridLayout(2, 1, 0, 2));

        lblTitulo.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        lblTitulo.setForeground(new java.awt.Color(196, 155, 92));
        lblTitulo.setText("REPORTES Y CONSULTAS");
        pnlEncabezado.add(lblTitulo);

        lblSubtitulo.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        lblSubtitulo.setForeground(new java.awt.Color(245, 235, 220));
        lblSubtitulo.setText("Ocupación del día, reservas por fecha e historial de huéspedes");
        pnlEncabezado.add(lblSubtitulo);

        getContentPane().add(pnlEncabezado, java.awt.BorderLayout.NORTH);

        pnlCentro.setOpaque(false);
        pnlCentro.setBorder(javax.swing.BorderFactory.createEmptyBorder(12, 12, 12, 12));
        pnlCentro.setLayout(new java.awt.BorderLayout(0, 8));

        pnlConsulta.setOpaque(false);
        pnlConsulta.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Consulta", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("SansSerif", 1, 12), new java.awt.Color(35, 59, 87))); // NOI18N
        pnlConsulta.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 8, 6));

        lblReporte.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        lblReporte.setText("Reporte");
        pnlConsulta.add(lblReporte);

        cboReporte.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        cboReporte.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ocupación del día", "Reservas por fecha", "Historial de un huésped", "Listado de departamentos", "Listado de empleados", "Listado de habitaciones", "Listado de huéspedes", "Listado general de reservas" }));
        cboReporte.addActionListener(this::cboReporteActionPerformed);
        pnlConsulta.add(cboReporte);

        lblDesde.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        lblDesde.setText("Fecha");
        pnlConsulta.add(lblDesde);

        spDesde.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        spDesde.setPreferredSize(new java.awt.Dimension(110, 28));
        pnlConsulta.add(spDesde);

        lblHasta.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        lblHasta.setText("Hasta");
        pnlConsulta.add(lblHasta);

        spHasta.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        spHasta.setPreferredSize(new java.awt.Dimension(110, 28));
        pnlConsulta.add(spHasta);

        lblDoc.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        lblDoc.setText("<html>Documento <font color='#B71C1C'>*</font></html>");
        pnlConsulta.add(lblDoc);

        txtDoc.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        txtDoc.setColumns(9);
        txtDoc.addActionListener(this::txtDocActionPerformed);
        pnlConsulta.add(txtDoc);

        btnGenerar.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        btnGenerar.setBackground(new java.awt.Color(35, 59, 87));
        btnGenerar.setForeground(new java.awt.Color(255, 255, 255));
        btnGenerar.setText("Generar");
        btnGenerar.setOpaque(true);
        btnGenerar.setBorderPainted(false);
        btnGenerar.setFocusPainted(false);
        btnGenerar.setPreferredSize(new java.awt.Dimension(110, 32));
        btnGenerar.addActionListener(this::btnGenerarActionPerformed);
        pnlConsulta.add(btnGenerar);

        pnlCentro.add(pnlConsulta, java.awt.BorderLayout.NORTH);

        pnlResultados.setOpaque(false);
        pnlResultados.setLayout(new java.awt.BorderLayout());

        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Resultados"
            }
        ));
        scrollTabla.setViewportView(tabla);

        pnlResultados.add(scrollTabla, java.awt.BorderLayout.CENTER);

        lblResumen.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        lblResumen.setForeground(new java.awt.Color(35, 59, 87));
        lblResumen.setText("Elija un reporte y presione Generar.");
        lblResumen.setBorder(javax.swing.BorderFactory.createEmptyBorder(6, 4, 0, 4));
        pnlResultados.add(lblResumen, java.awt.BorderLayout.SOUTH);

        pnlCentro.add(pnlResultados, java.awt.BorderLayout.CENTER);

        pnlPie.setOpaque(false);
        pnlPie.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 6, 4));

        btnExportar.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        btnExportar.setBackground(new java.awt.Color(46, 125, 50));
        btnExportar.setForeground(new java.awt.Color(255, 255, 255));
        btnExportar.setText("Exportar");
        btnExportar.setOpaque(true);
        btnExportar.setBorderPainted(false);
        btnExportar.setFocusPainted(false);
        btnExportar.setPreferredSize(new java.awt.Dimension(110, 32));
        btnExportar.addActionListener(this::btnExportarActionPerformed);
        pnlPie.add(btnExportar);

        btnCerrar.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        btnCerrar.setBackground(new java.awt.Color(35, 59, 87));
        btnCerrar.setForeground(new java.awt.Color(255, 255, 255));
        btnCerrar.setText("Cerrar");
        btnCerrar.setOpaque(true);
        btnCerrar.setBorderPainted(false);
        btnCerrar.setFocusPainted(false);
        btnCerrar.setPreferredSize(new java.awt.Dimension(110, 32));
        btnCerrar.addActionListener(this::btnCerrarActionPerformed);
        pnlPie.add(btnCerrar);

        pnlCentro.add(pnlPie, java.awt.BorderLayout.SOUTH);

        getContentPane().add(pnlCentro, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void cboReporteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboReporteActionPerformed
        actualizarFiltros();
    }//GEN-LAST:event_cboReporteActionPerformed

    private void txtDocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDocActionPerformed
        generar();
    }//GEN-LAST:event_txtDocActionPerformed

    private void btnGenerarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarActionPerformed
        generar();
    }//GEN-LAST:event_btnGenerarActionPerformed

    private void btnExportarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportarActionPerformed
        exportar();
    }//GEN-LAST:event_btnExportarActionPerformed

    private void btnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarActionPerformed
        dispose();
    }//GEN-LAST:event_btnCerrarActionPerformed

    /** Activa solo los filtros que usa el reporte elegido; los demas quedan desactivados. */
    private void actualizarFiltros()
    {
        String rep = cboReporte.getSelectedItem().toString();
        boolean ocupacion = rep.equals(REP_OCUPACION);
        boolean reservas = rep.equals(REP_RESERVAS);
        boolean historial = rep.equals(REP_HISTORIAL);

        lblDesde.setText(reservas ? "Desde" : "Fecha");
        spDesde.setEnabled(ocupacion || reservas);
        lblHasta.setEnabled(reservas);
        spHasta.setEnabled(reservas);
        lblDoc.setEnabled(historial);
        UIUtil.bloquear(txtDoc, !historial);
    }

    // ------------------------------------------------------------------------- reportes

    private void generar()
    {
        try
        {
            String rep = cboReporte.getSelectedItem().toString();
            if (rep.equals(REP_OCUPACION)) { reporteOcupacion(UIUtil.fecha(spDesde)); }
            else if (rep.equals(REP_RESERVAS)) { reporteReservas(UIUtil.fecha(spDesde), UIUtil.fecha(spHasta)); }
            else if (rep.equals(REP_HISTORIAL)) { reporteHistorial(txtDoc.getText().trim()); }
            else if (rep.equals(REP_DEPARTAMENTOS)) { reporteDepartamentos(); }
            else if (rep.equals(REP_EMPLEADOS)) { reporteEmpleados(); }
            else if (rep.equals(REP_HABITACIONES)) { reporteHabitaciones(); }
            else if (rep.equals(REP_HUESPEDES)) { reporteHuespedes(); }
            else { reporteListaReservas(); }
        }
        catch (IllegalArgumentException ex)
        {
            UIUtil.mostrarError(this, ex.getMessage());
        }
    }

    private void llenarTabla(String[] columnas, int[] anchos, ArrayList<Object[]> filas)
    {
        DefaultTableModel modelo = UIUtil.modelo(tabla);
        modelo.setColumnIdentifiers(columnas);
        modelo.setRowCount(0);
        for (Object[] f : filas)
        {
            modelo.addRow(f);
        }
        // las columnas se recrean al cambiar de reporte: se vuelven a aplicar anchos y color de estado
        UIUtil.anchos(tabla, anchos);
        for (int i = 0; i < columnas.length; i++)
        {
            if (columnas[i].startsWith("Estado"))
            {
                UIUtil.colorearEstado(tabla, i);
            }
        }
    }

    private void publicar(String titulo, String resumen, String archivo)
    {
        tituloGenerado = titulo;
        resumenGenerado = resumen;
        archivoSugerido = archivo;
        lblResumen.setText(resumen);
    }

    /** Estado de cada habitacion en la fecha: Ocupada, Reservada, o su estado actual si la fecha es hoy. */
    private void reporteOcupacion(LocalDate fecha)
    {
        if (habitacionController.ListaHabitaciones.isEmpty())
        {
            throw new IllegalArgumentException("No hay habitaciones registradas.");
        }
        ArrayList<Habitacion> habitaciones = new ArrayList<>(habitacionController.ListaHabitaciones);
        habitaciones.sort((a, b) -> Integer.compare(a.getNro_habitacion(), b.getNro_habitacion()));

        ArrayList<Object[]> filas = new ArrayList<>();
        int ocupadas = 0, reservadas = 0, otras = 0, libres = 0;
        for (Habitacion h : habitaciones)
        {
            Reserva r = reservaController.ReservaDeHabitacionEnFecha(h, fecha);
            String estado;
            if (r != null)
            {
                estado = Reserva.CONFIRMADA.equals(r.getEstadoReserva()) ? "Reservada" : "Ocupada";
            }
            else if (fecha.equals(LocalDate.now()))
            {
                // solo para hoy se conoce el estado real de la habitacion (limpieza, mantenimiento...)
                estado = UIUtil.capitalizar(h.getEstado());
            }
            else
            {
                estado = "Disponible";
            }
            switch (estado)
            {
                case "Ocupada": ocupadas++; break;
                case "Reservada": reservadas++; break;
                case "Disponible": libres++; break;
                default: otras++; break;
            }
            filas.add(new Object[]
            {
                h.getNro_habitacion(),
                UIUtil.tipoHabitacion(h),
                UIUtil.soles(h.getPrecio_noche()),
                estado,
                (r == null) ? "-" : UIUtil.nombreCompleto(r.getCliente()),
                (r == null) ? "-" : r.getCod_reserva(),
                (r == null) ? "-" : r.getFecha_fin().format(UIUtil.FORMATO_FECHA)
            });
        }
        llenarTabla(new String[] { "Habitación", "Tipo", "Precio por noche S/", "Estado del día", "Huésped", "Reserva", "Salida prevista" },
                new int[] { 90, 90, 140, 120, 220, 110, 110 }, filas);

        int total = habitaciones.size();
        double porcentaje = 100.0 * (ocupadas + reservadas) / total;
        String resumen = "Ocupación al " + fecha.format(UIUtil.FORMATO_FECHA) + ":  " + total + " habitaciones  |  Ocupadas: "
                + ocupadas + "  |  Reservadas: " + reservadas + "  |  Disponibles: " + libres + "  |  "
                + "Limpieza/mantenimiento: " + otras + "  |  Ocupación: " + String.format(java.util.Locale.US, "%.1f", porcentaje) + "%";
        publicar("REPORTE DE OCUPACIÓN DEL DÍA " + fecha, resumen, "reporte_" + fecha + ".txt");
    }

    /** Reservas cuyo periodo se cruza con el rango [desde, hasta]. */
    private void reporteReservas(LocalDate desde, LocalDate hasta)
    {
        if (hasta.isBefore(desde))
        {
            throw new IllegalArgumentException("La fecha \"Hasta\" no puede ser anterior a la fecha \"Desde\".");
        }
        ArrayList<Object[]> filas = new ArrayList<>();
        int[] porEstado = new int[4];
        double total = 0;
        ArrayList<Reserva> ordenadas = new ArrayList<>(reservaController.ListaReservas);
        ordenadas.sort((a, b) -> a.getFecha_inicio().compareTo(b.getFecha_inicio()));
        for (Reserva r : ordenadas)
        {
            LocalDate salida = (r.getFecha_salida_real() != null) ? r.getFecha_salida_real() : r.getFecha_fin();
            boolean seCruza = !r.getFecha_inicio().isAfter(hasta) && salida.isAfter(desde);
            if (!seCruza)
            {
                continue;
            }
            filas.add(UIUtil.filaReserva(r));
            switch (r.getEstadoReserva())
            {
                case Reserva.CONFIRMADA: porEstado[0]++; total += r.CalcularMonto(); break;
                case Reserva.EN_CURSO: porEstado[1]++; total += r.CalcularMonto(); break;
                case Reserva.FINALIZADA: porEstado[2]++; total += r.CalcularMonto(); break;
                default: porEstado[3]++; break;
            }
        }
        llenarTabla(UIUtil.COLUMNAS_RESERVA, UIUtil.ANCHOS_RESERVA, filas);

        String rango = desde.format(UIUtil.FORMATO_FECHA) + " al " + hasta.format(UIUtil.FORMATO_FECHA);
        String resumen = "Reservas del " + rango + ":  " + filas.size() + " en total  |  "
                + porEstado[0] + " confirmadas  |  " + porEstado[1] + " en curso  |  "
                + porEstado[2] + " finalizadas  |  " + porEstado[3] + " canceladas  |  Monto (sin canceladas): S/ " + UIUtil.soles(total);
        publicar("REPORTE DE RESERVAS DEL " + rango, resumen, "reporte_reservas_" + desde + "_" + hasta + ".txt");
    }

    private void reporteHistorial(String doc)
    {
        if (doc.isEmpty())
        {
            throw new IllegalArgumentException("Ingrese el documento del huésped.");
        }
        Huesped h = huespedController.Buscarhuesped(doc);
        if (h == null)
        {
            throw new IllegalArgumentException("No existe un huésped registrado con el documento " + doc + ".");
        }
        ArrayList<Object[]> filas = new ArrayList<>();
        double total = 0;
        for (Reserva r : h.getHisorialReservas())
        {
            filas.add(UIUtil.filaReserva(r));
            if (!Reserva.CANCELADA.equals(r.getEstadoReserva()))
            {
                total += r.CalcularMonto();
            }
        }
        llenarTabla(UIUtil.COLUMNAS_RESERVA, UIUtil.ANCHOS_RESERVA, filas);

        String resumen = "Huésped: " + UIUtil.nombreCompleto(h) + " (" + h.getTipo_doc().toUpperCase() + " " + h.getNro_doc() + ")  |  "
                + (Boolean.TRUE.equals(h.getEsFrecuente()) ? "Frecuente" : "No frecuente") + "  |  "
                + filas.size() + " reservas  |  Monto acumulado (sin canceladas): S/ " + UIUtil.soles(total);
        publicar("HISTORIAL DEL HUÉSPED " + h.getNro_doc(), resumen, "reporte_historial_" + h.getNro_doc() + ".txt");
    }


    // --------------------------------------------------------- listados generales

    /** Un departamento por fila: gerente, cantidad de empleados y planilla mensual. */
    private void reporteDepartamentos()
    {
        if (departamentoController.ListaDepartamentos.isEmpty())
        {
            throw new IllegalArgumentException("No hay departamentos registrados.");
        }
        ArrayList<Object[]> filas = new ArrayList<>();
        double presupuestoTotal = 0, planillaTotal = 0;
        int empleadosTotal = 0;
        for (Departamento d : departamentoController.ListaDepartamentos)
        {
            double planilla = 0;
            for (Empleado e : d.getListaEmpleados())
            {
                planilla += e.CalcularSueldo();
            }
            presupuestoTotal += d.getPresupuesto_mensual();
            planillaTotal += planilla;
            empleadosTotal += d.getListaEmpleados().size();
            filas.add(new Object[]
            {
                d.getId_departamento(),
                d.getNombre_departamento(),
                UIUtil.soles(d.getPresupuesto_mensual()),
                (d.getGerente_a_cargo() == null) ? "Sin gerente" : UIUtil.nombreCompleto(d.getGerente_a_cargo()),
                d.getListaEmpleados().size(),
                UIUtil.soles(planilla)
            });
        }
        llenarTabla(new String[] { "ID", "Departamento", "Presupuesto mensual S/", "Gerente a cargo", "Empleados", "Planilla mensual S/" },
                new int[] { 90, 180, 150, 220, 90, 150 }, filas);

        String resumen = "Departamentos:  " + filas.size() + " en total  |  " + empleadosTotal + " empleados  |  Presupuesto total: S/ "
                + UIUtil.soles(presupuestoTotal) + "  |  Planilla total: S/ " + UIUtil.soles(planillaTotal);
        publicar("REPORTE GENERAL DE DEPARTAMENTOS", resumen, "reporte_listado_departamentos.txt");
    }

    /** Un empleado por fila, con su cargo, departamento, jefe y el dato propio de cada tipo de empleado. */
    private void reporteEmpleados()
    {
        if (empleadoController.ListaEmpleados.isEmpty())
        {
            throw new IllegalArgumentException("No hay empleados registrados.");
        }
        ArrayList<Object[]> filas = new ArrayList<>();
        int gerentes = 0, administrativos = 0, recepcionistas = 0, conserjes = 0;
        double planilla = 0;
        for (Empleado e : empleadoController.ListaEmpleados)
        {
            String cargo, detalle;
            if (e instanceof Gerente)
            {
                cargo = "Gerente"; gerentes++;
                detalle = "Bono de gestión: S/ " + UIUtil.soles(((Gerente) e).getBono_gestion());
            }
            else if (e instanceof Administrativo)
            {
                cargo = "Administrativo"; administrativos++;
                detalle = "Anexo: " + ((Administrativo) e).getNro_anexo();
            }
            else if (e instanceof Recepcionista)
            {
                cargo = "Recepcionista"; recepcionistas++;
                detalle = "Turno: " + ((Recepcionista) e).getTurno();
            }
            else if (e instanceof Conserje)
            {
                cargo = "Conserje"; conserjes++;
                detalle = "Especialidad: " + ((Conserje) e).getEspecialidad();
            }
            else
            {
                cargo = "Empleado";
                detalle = "-";
            }
            planilla += e.CalcularSueldo();
            filas.add(new Object[]
            {
                e.getTipo_doc().toUpperCase() + " " + e.getNro_doc(),
                UIUtil.nombreCompleto(e),
                cargo,
                (e.getDepartamento() == null) ? "-" : e.getDepartamento().getNombre_departamento(),
                (e.getJefe() == null) ? "-" : UIUtil.nombreCompleto(e.getJefe()),
                UIUtil.soles(e.getSueldo()),
                UIUtil.soles(e.CalcularSueldo()),
                e.getListaSubordinados().size(),
                detalle
            });
        }
        llenarTabla(new String[] { "Documento", "Empleado", "Cargo", "Departamento", "Jefe", "Sueldo base S/", "Sueldo total S/", "Subordinados", "Detalle" },
                new int[] { 100, 190, 100, 130, 190, 100, 100, 90, 170 }, filas);

        String resumen = "Empleados:  " + filas.size() + " en total  |  " + gerentes + " gerentes  |  " + administrativos
                + " administrativos  |  " + recepcionistas + " recepcionistas  |  " + conserjes + " conserjes  |  Planilla total: S/ "
                + UIUtil.soles(planilla);
        publicar("REPORTE GENERAL DE EMPLEADOS", resumen, "reporte_listado_empleados.txt");
    }

    /** Todas las habitaciones con su tipo, precio y estado actual. */
    private void reporteHabitaciones()
    {
        if (habitacionController.ListaHabitaciones.isEmpty())
        {
            throw new IllegalArgumentException("No hay habitaciones registradas.");
        }
        ArrayList<Habitacion> habitaciones = new ArrayList<>(habitacionController.ListaHabitaciones);
        habitaciones.sort((a, b) -> Integer.compare(a.getNro_habitacion(), b.getNro_habitacion()));

        ArrayList<Object[]> filas = new ArrayList<>();
        int disponibles = 0, ocupadas = 0, limpieza = 0, mantenimiento = 0, simples = 0, suites = 0;
        for (Habitacion h : habitaciones)
        {
            String estado = UIUtil.capitalizar(h.getEstado());
            switch (estado)
            {
                case "Disponible": disponibles++; break;
                case "Ocupada": ocupadas++; break;
                case "Limpieza": limpieza++; break;
                default: mantenimiento++; break;
            }
            String tipo = UIUtil.tipoHabitacion(h);
            if (tipo.equals("Suite")) { suites++; } else if (tipo.equals("Simple")) { simples++; }
            filas.add(new Object[]
            {
                h.getNro_habitacion(),
                tipo,
                UIUtil.detalleHabitacion(h),
                UIUtil.soles(h.getPrecio_noche()),
                estado
            });
        }
        llenarTabla(new String[] { "Habitación", "Tipo", "Característica", "Precio por noche S/", "Estado" },
                new int[] { 90, 90, 200, 140, 120 }, filas);

        String resumen = "Habitaciones:  " + filas.size() + " en total (" + simples + " simples, " + suites + " suites)  |  Disponibles: "
                + disponibles + "  |  Ocupadas: " + ocupadas + "  |  Limpieza: " + limpieza + "  |  Mantenimiento: " + mantenimiento;
        publicar("REPORTE GENERAL DE HABITACIONES", resumen, "reporte_listado_habitaciones.txt");
    }

    /** Todos los huespedes registrados con su cantidad de reservas. */
    private void reporteHuespedes()
    {
        if (huespedController.ListaHuespedes.isEmpty())
        {
            throw new IllegalArgumentException("No hay huéspedes registrados.");
        }
        ArrayList<Object[]> filas = new ArrayList<>();
        int frecuentes = 0;
        for (Huesped h : huespedController.ListaHuespedes)
        {
            boolean frecuente = Boolean.TRUE.equals(h.getEsFrecuente());
            if (frecuente) { frecuentes++; }
            filas.add(new Object[]
            {
                h.getTipo_doc().toUpperCase() + " " + h.getNro_doc(),
                UIUtil.nombreCompleto(h),
                h.getTelefono(),
                h.getCorreo(),
                h.getNacionalidad(),
                frecuente ? "Sí" : "No",
                h.getHisorialReservas().size()
            });
        }
        llenarTabla(new String[] { "Documento", "Huésped", "Teléfono", "Correo", "Nacionalidad", "Frecuente", "Reservas" },
                new int[] { 110, 220, 110, 210, 110, 80, 80 }, filas);

        String resumen = "Huéspedes:  " + filas.size() + " en total  |  " + frecuentes + " frecuentes  |  "
                + (filas.size() - frecuentes) + " no frecuentes";
        publicar("REPORTE GENERAL DE HUÉSPEDES", resumen, "reporte_listado_huespedes.txt");
    }

    /** Todas las reservas del sistema, sin filtrar por fecha. */
    private void reporteListaReservas()
    {
        if (reservaController.ListaReservas.isEmpty())
        {
            throw new IllegalArgumentException("No hay reservas registradas.");
        }
        ArrayList<Reserva> ordenadas = new ArrayList<>(reservaController.ListaReservas);
        ordenadas.sort((a, b) -> a.getFecha_inicio().compareTo(b.getFecha_inicio()));

        ArrayList<Object[]> filas = new ArrayList<>();
        int[] porEstado = new int[4];
        double total = 0;
        for (Reserva r : ordenadas)
        {
            filas.add(UIUtil.filaReserva(r));
            switch (r.getEstadoReserva())
            {
                case Reserva.CONFIRMADA: porEstado[0]++; total += r.CalcularMonto(); break;
                case Reserva.EN_CURSO: porEstado[1]++; total += r.CalcularMonto(); break;
                case Reserva.FINALIZADA: porEstado[2]++; total += r.CalcularMonto(); break;
                default: porEstado[3]++; break;
            }
        }
        llenarTabla(UIUtil.COLUMNAS_RESERVA, UIUtil.ANCHOS_RESERVA, filas);

        String resumen = "Reservas:  " + filas.size() + " en total  |  " + porEstado[0] + " confirmadas  |  " + porEstado[1]
                + " en curso  |  " + porEstado[2] + " finalizadas  |  " + porEstado[3] + " canceladas  |  Monto (sin canceladas): S/ "
                + UIUtil.soles(total);
        publicar("REPORTE GENERAL DE RESERVAS", resumen, "reporte_listado_reservas.txt");
    }

    // ------------------------------------------------------------------------ exportar

    /** Guarda en texto plano lo que se ve en la tabla (mismo estilo que los demas reportes del sistema). */
    private void exportar()
    {
        if (tituloGenerado == null)
        {
            UIUtil.mostrarError(this, "Primero genere un reporte.");
            return;
        }
        File archivo = new File(archivoSugerido);
        try (PrintWriter w = new PrintWriter(new OutputStreamWriter(new FileOutputStream(archivo), StandardCharsets.UTF_8)))
        {
            String linea = "=".repeat(110);
            w.println(linea);
            w.println("  " + tituloGenerado);
            w.println("  Generado: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
            w.println(linea);
            w.println(resumenGenerado);
            w.println("-".repeat(110));

            DefaultTableModel m = UIUtil.modelo(tabla);
            int cols = m.getColumnCount();
            int[] ancho = new int[cols];
            for (int c = 0; c < cols; c++)
            {
                ancho[c] = String.valueOf(m.getColumnName(c)).length();
                for (int f = 0; f < m.getRowCount(); f++)
                {
                    ancho[c] = Math.max(ancho[c], String.valueOf(m.getValueAt(f, c)).length());
                }
            }
            StringBuilder cab = new StringBuilder();
            for (int c = 0; c < cols; c++)
            {
                cab.append(String.format("%-" + (ancho[c] + 2) + "s", m.getColumnName(c)));
            }
            w.println(cab.toString().stripTrailing());
            if (m.getRowCount() == 0)
            {
                w.println("(sin resultados)");
            }
            for (int f = 0; f < m.getRowCount(); f++)
            {
                StringBuilder fila = new StringBuilder();
                for (int c = 0; c < cols; c++)
                {
                    fila.append(String.format("%-" + (ancho[c] + 2) + "s", String.valueOf(m.getValueAt(f, c))));
                }
                w.println(fila.toString().stripTrailing());
            }
            w.println(linea);
            w.println("Generado automáticamente por el Sistema de Hotel 'Indigo Loft'");
        }
        catch (IOException ex)
        {
            UIUtil.mostrarError(this, "No se pudo exportar el reporte: " + ex.getMessage());
            return;
        }
        UIUtil.mostrarInfo(this, "Reporte exportado en:\n" + archivo.getAbsolutePath());
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCerrar;
    private javax.swing.JButton btnExportar;
    private javax.swing.JButton btnGenerar;
    private javax.swing.JComboBox<String> cboReporte;
    private javax.swing.JLabel lblDesde;
    private javax.swing.JLabel lblDoc;
    private javax.swing.JLabel lblHasta;
    private javax.swing.JLabel lblReporte;
    private javax.swing.JLabel lblResumen;
    private javax.swing.JLabel lblSubtitulo;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JPanel pnlCentro;
    private javax.swing.JPanel pnlConsulta;
    private javax.swing.JPanel pnlEncabezado;
    private javax.swing.JPanel pnlPie;
    private javax.swing.JPanel pnlResultados;
    private javax.swing.JScrollPane scrollTabla;
    private javax.swing.JSpinner spDesde;
    private javax.swing.JSpinner spHasta;
    private javax.swing.JTable tabla;
    private javax.swing.JTextField txtDoc;
    // End of variables declaration//GEN-END:variables
}
