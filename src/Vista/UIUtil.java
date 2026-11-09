package Vista;

import Modelos.Habitacion;
import Modelos.HabitacionSimple;
import Modelos.Persona;
import Modelos.Reserva;
import Modelos.Suite;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.SpinnerDateModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.JTextComponent;

/**
 * Estilos y utilidades comunes a las pantallas Huespedes, Reservas y Reportes.
 * Sigue los estandares de GUI del informe (seccion 14.1): sans-serif 12 pt para
 * texto y 16 pt para titulos; azul oscuro en encabezados y botones principales,
 * verde para confirmar, rojo para cancelar y gris para campos desactivados.
 */
public final class UIUtil
{
    public static final Color AZUL = new Color(35, 59, 87);
    public static final Color DORADO = new Color(196, 155, 92);
    public static final Color VERDE = new Color(46, 125, 50);
    public static final Color ROJO = new Color(183, 28, 28);
    public static final Color GRIS_CAMPO = new Color(224, 224, 224);
    public static final Color FONDO = new Color(245, 242, 235);

    public static final Font TEXTO = new Font("SansSerif", Font.PLAIN, 12);
    public static final Font TEXTO_NEGRITA = new Font("SansSerif", Font.BOLD, 12);
    public static final Font TITULO = new Font("SansSerif", Font.BOLD, 16);

    public static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private UIUtil() { }

    // ---------------------------------------------------------------- componentes

    /** Franja azul oscuro con el titulo de la pantalla. */
    public static JPanel crearEncabezado(String titulo, String subtitulo)
    {
        JPanel p = new JPanel(new java.awt.GridLayout(2, 1, 0, 2));
        p.setBackground(AZUL);
        p.setBorder(BorderFactory.createEmptyBorder(12, 18, 12, 18));

        JLabel lblTitulo = new JLabel(titulo);
        lblTitulo.setFont(TITULO);
        lblTitulo.setForeground(DORADO);

        JLabel lblSub = new JLabel(subtitulo);
        lblSub.setFont(TEXTO);
        lblSub.setForeground(new Color(245, 235, 220));

        p.add(lblTitulo);
        p.add(lblSub);
        return p;
    }

    /** Etiqueta de campo; si es obligatorio se marca con un asterisco rojo. */
    public static JLabel etiqueta(String texto, boolean obligatorio)
    {
        JLabel l = new JLabel(obligatorio
                ? "<html>" + texto + " <font color='#B71C1C'>*</font></html>"
                : texto);
        l.setFont(TEXTO);
        return l;
    }

    public static JButton boton(String texto, Color fondo)
    {
        JButton b = new JButton(texto);
        b.setFont(TEXTO_NEGRITA);
        b.setBackground(fondo);
        b.setForeground(Color.WHITE);
        b.setOpaque(true);
        b.setBorderPainted(false);
        b.setFocusPainted(false);
        b.setCursor(new Cursor(Cursor.HAND_CURSOR));
        b.setPreferredSize(new Dimension(Math.max(110, b.getPreferredSize().width + 20), 32));
        return b;
    }

    /** Cursor de mano sobre los botones (el diseñador visual no lo genera). */
    public static void cursorMano(javax.swing.AbstractButton... botones)
    {
        for (javax.swing.AbstractButton b : botones)
        {
            b.setCursor(new Cursor(Cursor.HAND_CURSOR));
        }
    }

    /** Campo bloqueado = fondo gris; desbloqueado = fondo blanco. */
    public static void bloquear(JTextComponent campo, boolean bloqueado)
    {
        campo.setEditable(!bloqueado);
        campo.setBackground(bloqueado ? GRIS_CAMPO : Color.WHITE);
    }

    /** Tabla de solo lectura, con el estilo comun (encabezado azul, filas de 24 px, ordenable). */
    public static JTable crearTabla(String... columnas)
    {
        DefaultTableModel modelo = new DefaultTableModel(columnas, 0)
        {
            @Override
            public boolean isCellEditable(int fila, int columna)
            {
                return false;
            }
        };
        JTable t = new JTable(modelo);
        estilizarTabla(t);
        return t;
    }

    /**
     * Aplica el estilo comun a una tabla ya creada (por ejemplo, desde un formulario de NetBeans):
     * solo lectura, encabezado azul, filas de 24 px y ordenable.
     */
    public static void estilizarTabla(JTable t)
    {
        t.setDefaultEditor(Object.class, null);   // las celdas no se editan
        t.setFont(TEXTO);
        t.setRowHeight(24);
        t.setAutoCreateRowSorter(true);
        t.setSelectionBackground(new Color(222, 205, 170));
        t.setSelectionForeground(Color.BLACK);
        t.setGridColor(new Color(220, 220, 220));
        t.getTableHeader().setReorderingAllowed(false);
        t.getTableHeader().setFont(TEXTO_NEGRITA);
        t.getTableHeader().setBackground(AZUL);
        t.getTableHeader().setForeground(Color.WHITE);
        t.getTableHeader().setPreferredSize(new Dimension(100, 28));
        // el encabezado respeta el color en cualquier Look & Feel
        t.getTableHeader().setDefaultRenderer(new DefaultTableCellRenderer()
        {
            {
                setHorizontalAlignment(SwingConstants.LEFT);
            }
            @Override
            public Component getTableCellRendererComponent(JTable tabla, Object valor, boolean sel, boolean foco, int fila, int col)
            {
                super.getTableCellRendererComponent(tabla, valor, false, false, fila, col);
                setBackground(AZUL);
                setForeground(Color.WHITE);
                setFont(TEXTO_NEGRITA);
                setBorder(BorderFactory.createEmptyBorder(0, 6, 0, 6));
                return this;
            }
        });
    }

    /** Ancho preferido de cada columna (si la tabla tiene menos columnas, se ignoran las sobrantes). */
    public static void anchos(JTable t, int... anchos)
    {
        for (int i = 0; i < anchos.length && i < t.getColumnModel().getColumnCount(); i++)
        {
            t.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
        }
    }

    public static DefaultTableModel modelo(JTable t)
    {
        return (DefaultTableModel) t.getModel();
    }

    /** Valor de la columna indicada en la fila seleccionada (null si no hay seleccion). */
    public static Object valorSeleccionado(JTable t, int columna)
    {
        int fila = t.getSelectedRow();
        if (fila < 0)
        {
            return null;
        }
        return t.getModel().getValueAt(t.convertRowIndexToModel(fila), columna);
    }

    /** Pinta el texto de la columna de estado: Confirmada azul, En curso verde, Finalizada gris, Cancelada rojo. */
    public static void colorearEstado(JTable t, int columna)
    {
        t.getColumnModel().getColumn(columna).setCellRenderer(new DefaultTableCellRenderer()
        {
            @Override
            public Component getTableCellRendererComponent(JTable tabla, Object valor, boolean sel, boolean foco, int fila, int col)
            {
                super.getTableCellRendererComponent(tabla, valor, sel, foco, fila, col);
                String v = String.valueOf(valor);
                Color c = Color.BLACK;
                if (v.equals(Reserva.CONFIRMADA) || v.equals("Reservada")) { c = AZUL; }
                else if (v.equals(Reserva.EN_CURSO) || v.equals("Ocupada")) { c = VERDE; }
                else if (v.equals(Reserva.FINALIZADA)) { c = Color.GRAY; }
                else if (v.equals(Reserva.CANCELADA)) { c = ROJO; }
                else if (v.equals("Mantenimiento") || v.equals("Limpieza")) { c = new Color(190, 110, 0); }
                setForeground(c);
                setFont(TEXTO_NEGRITA);
                return this;
            }
        });
    }

    public static void mostrarError(Component padre, String mensaje)
    {
        JOptionPane.showMessageDialog(padre, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public static void mostrarInfo(Component padre, String mensaje)
    {
        JOptionPane.showMessageDialog(padre, mensaje, "Indigo Loft", JOptionPane.INFORMATION_MESSAGE);
    }

    /** Dialogo con texto en fuente monoespaciada (detalles, historiales, comprobantes). */
    public static void mostrarTexto(Component padre, String titulo, String texto)
    {
        JTextArea area = new JTextArea(texto);
        area.setEditable(false);
        area.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
        area.setCaretPosition(0);
        JScrollPane sp = new JScrollPane(area);
        sp.setPreferredSize(new Dimension(760, 340));
        JOptionPane.showMessageDialog(padre, sp, titulo, JOptionPane.PLAIN_MESSAGE);
    }

    // -------------------------------------------------------------------- fechas

    public static JSpinner crearSpinnerFecha(LocalDate inicial)
    {
        JSpinner sp = new JSpinner();
        configurarSpinnerFecha(sp, inicial);
        sp.setPreferredSize(new Dimension(110, 28));
        return sp;
    }

    /** Convierte un JSpinner (por ejemplo, el de un formulario de NetBeans) en selector de fecha dd/MM/yyyy. */
    public static void configurarSpinnerFecha(JSpinner sp, LocalDate inicial)
    {
        Date d = Date.from(inicial.atStartOfDay(ZoneId.systemDefault()).toInstant());
        sp.setModel(new SpinnerDateModel(d, null, null, java.util.Calendar.DAY_OF_MONTH));
        sp.setEditor(new JSpinner.DateEditor(sp, "dd/MM/yyyy"));
        sp.setFont(TEXTO);
    }

    public static void fijarFecha(JSpinner sp, LocalDate f)
    {
        sp.setValue(Date.from(f.atStartOfDay(ZoneId.systemDefault()).toInstant()));
    }

    public static LocalDate fecha(JSpinner sp)
    {
        return ((Date) sp.getValue()).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    // ------------------------------------------------------- texto de objetos del modelo

    public static String nombreCompleto(Persona p)
    {
        if (p == null)
        {
            return "-";
        }
        return p.getNombre() + " " + p.getApellido_pat() + " " + p.getApellido_mat();
    }

    public static String tipoHabitacion(Habitacion h)
    {
        if (h instanceof Suite) { return "Suite"; }
        if (h instanceof HabitacionSimple) { return "Simple"; }
        return "Estándar";
    }

    public static String detalleHabitacion(Habitacion h)
    {
        if (h instanceof Suite)
        {
            return Boolean.TRUE.equals(((Suite) h).getTieneJacuzzi()) ? "Con jacuzzi" : "Sin jacuzzi";
        }
        if (h instanceof HabitacionSimple)
        {
            return Boolean.TRUE.equals(((HabitacionSimple) h).getTieneVistaCalle()) ? "Vista a la calle" : "Sin vista a la calle";
        }
        return "";
    }

    /** "disponible" -> "Disponible". */
    public static String capitalizar(String s)
    {
        if (s == null || s.isEmpty())
        {
            return "";
        }
        return Character.toUpperCase(s.charAt(0)) + s.substring(1).toLowerCase();
    }

    public static String habitacionesTexto(Reserva r)
    {
        StringBuilder sb = new StringBuilder();
        for (Habitacion h : r.getListaHabitaciones())
        {
            if (sb.length() > 0) { sb.append(", "); }
            sb.append(h.getNro_habitacion());
        }
        return sb.toString();
    }

    public static String soles(double monto)
    {
        return String.format(java.util.Locale.US, "%.2f", monto);
    }

    /** Columnas comunes de las tablas de reservas (reservas, reportes, historial). */
    public static final String[] COLUMNAS_RESERVA =
    {
        "Código", "Documento", "Huésped", "Habitaciones", "Entrada", "Salida", "Monto S/", "Estado", "Registró"
    };

    public static final int[] ANCHOS_RESERVA = { 100, 90, 190, 100, 85, 85, 80, 90, 170 };

    public static Object[] filaReserva(Reserva r)
    {
        LocalDate salida = (r.getFecha_salida_real() != null) ? r.getFecha_salida_real() : r.getFecha_fin();
        return new Object[]
        {
            r.getCod_reserva(),
            r.getCliente().getNro_doc(),
            nombreCompleto(r.getCliente()),
            habitacionesTexto(r),
            r.getFecha_inicio().format(FORMATO_FECHA),
            salida.format(FORMATO_FECHA),
            soles(r.CalcularMonto()),
            r.getEstadoReserva(),
            nombreCompleto(r.getRecepcionista_a_cargo())
        };
    }

    public static JPanel panelConBorde(String titulo)
    {
        JPanel p = new JPanel();
        p.setOpaque(false);
        javax.swing.border.TitledBorder b = BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(DORADO), titulo);
        b.setTitleFont(TEXTO_NEGRITA);
        b.setTitleColor(AZUL);
        p.setBorder(b);
        return p;
    }
}
