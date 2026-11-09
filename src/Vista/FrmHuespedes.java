package Vista;

import Controladores.HuespedController;
import Modelos.Huesped;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;


public class FrmHuespedes extends javax.swing.JFrame
{
    private static final int COL_DOC = 1;

    private final HuespedController huespedController;

    private String docEnEdicion = null;

    public FrmHuespedes(HuespedController controlador)
    {
        this.huespedController = controlador;
        initComponents();

        getContentPane().setBackground(UIUtil.FONDO);
        UIUtil.estilizarTabla(tabla);
        tabla.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        UIUtil.anchos(tabla, 45, 90, 200, 100, 200, 100, 70, 65);
        scrollTabla.getViewport().setBackground(Color.WHITE);
        UIUtil.cursorMano(btnGuardar, btnCancelar, btnCerrar, btnBuscar, btnTodos, btnHistorial, btnEliminar);
        tabla.getSelectionModel().addListSelectionListener(e ->
        {
            if (!e.getValueIsAdjusting() && tabla.getSelectedRow() >= 0)
            {
                String doc = String.valueOf(UIUtil.valorSeleccionado(tabla, COL_DOC));
                Huesped h = huespedController.Buscarhuesped(doc);
                if (h != null)
                {
                    cargarEnFormulario(h);
                }
            }
        });

        setLocationRelativeTo(null);
        cargarTabla(null);
        nuevo();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        pnlEncabezado = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        lblSubtitulo = new javax.swing.JLabel();
        pnlFormulario = new javax.swing.JPanel();
        pnlDatos = new javax.swing.JPanel();
        lblModo = new javax.swing.JLabel();
        lblTipoDoc = new javax.swing.JLabel();
        cboTipoDoc = new javax.swing.JComboBox<>();
        lblNroDoc = new javax.swing.JLabel();
        txtNroDoc = new javax.swing.JTextField();
        lblNombre = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        lblApePat = new javax.swing.JLabel();
        txtApePat = new javax.swing.JTextField();
        lblApeMat = new javax.swing.JLabel();
        txtApeMat = new javax.swing.JTextField();
        lblTelefono = new javax.swing.JLabel();
        txtTelefono = new javax.swing.JTextField();
        lblCorreo = new javax.swing.JLabel();
        txtCorreo = new javax.swing.JTextField();
        lblNacionalidad = new javax.swing.JLabel();
        cboNacionalidad = new javax.swing.JComboBox<>();
        lblPreferencias = new javax.swing.JLabel();
        txtPreferencias = new javax.swing.JTextField();
        chkFrecuente = new javax.swing.JCheckBox();
        lblRelleno = new javax.swing.JLabel();
        pnlSurForm = new javax.swing.JPanel();
        lblNota = new javax.swing.JLabel();
        pnlBotonesForm = new javax.swing.JPanel();
        btnGuardar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnCerrar = new javax.swing.JButton();
        pnlListado = new javax.swing.JPanel();
        pnlBusqueda = new javax.swing.JPanel();
        lblBuscar = new javax.swing.JLabel();
        txtBuscar = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        btnTodos = new javax.swing.JButton();
        scrollTabla = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        pnlAcciones = new javax.swing.JPanel();
        btnHistorial = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        lblAyuda = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Gestión de Huéspedes - Indigo Loft");
        setMinimumSize(new java.awt.Dimension(900, 680));
        setSize(new java.awt.Dimension(1000, 720));

        pnlEncabezado.setBackground(new java.awt.Color(35, 59, 87));
        pnlEncabezado.setBorder(javax.swing.BorderFactory.createEmptyBorder(12, 18, 12, 18));
        pnlEncabezado.setLayout(new java.awt.GridLayout(2, 1, 0, 2));

        lblTitulo.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        lblTitulo.setForeground(new java.awt.Color(196, 155, 92));
        lblTitulo.setText("GESTIÓN DE HUÉSPEDES");
        pnlEncabezado.add(lblTitulo);

        lblSubtitulo.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        lblSubtitulo.setForeground(new java.awt.Color(245, 235, 220));
        lblSubtitulo.setText("Registra, busca y modifica los datos de los huéspedes del hotel");
        pnlEncabezado.add(lblSubtitulo);

        getContentPane().add(pnlEncabezado, java.awt.BorderLayout.NORTH);

        pnlFormulario.setOpaque(false);
        pnlFormulario.setBorder(javax.swing.BorderFactory.createEmptyBorder(12, 12, 12, 6));
        pnlFormulario.setLayout(new java.awt.BorderLayout());

        pnlDatos.setOpaque(false);
        pnlDatos.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos del huésped", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("SansSerif", 1, 12), new java.awt.Color(35, 59, 87))); // NOI18N
        pnlDatos.setLayout(new java.awt.GridBagLayout());

        lblModo.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        lblModo.setText(" ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 8, 0, 8);
        pnlDatos.add(lblModo, gridBagConstraints);

        lblTipoDoc.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        lblTipoDoc.setText("<html>Tipo de documento <font color='#B71C1C'>*</font></html>");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 8, 0, 8);
        pnlDatos.add(lblTipoDoc, gridBagConstraints);

        cboTipoDoc.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        cboTipoDoc.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "DNI", "CE", "PAS" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 8, 0, 8);
        pnlDatos.add(cboTipoDoc, gridBagConstraints);

        lblNroDoc.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        lblNroDoc.setText("<html>Número de documento (DNI: 8 · CE/PAS: 9 dígitos) <font color='#B71C1C'>*</font></html>");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 8, 0, 8);
        pnlDatos.add(lblNroDoc, gridBagConstraints);

        txtNroDoc.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        txtNroDoc.setColumns(14);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 8, 0, 8);
        pnlDatos.add(txtNroDoc, gridBagConstraints);

        lblNombre.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        lblNombre.setText("<html>Nombres <font color='#B71C1C'>*</font></html>");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 8, 0, 8);
        pnlDatos.add(lblNombre, gridBagConstraints);

        txtNombre.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        txtNombre.setColumns(14);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 8, 0, 8);
        pnlDatos.add(txtNombre, gridBagConstraints);

        lblApePat.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        lblApePat.setText("<html>Apellido paterno <font color='#B71C1C'>*</font></html>");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 8, 0, 8);
        pnlDatos.add(lblApePat, gridBagConstraints);

        txtApePat.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        txtApePat.setColumns(14);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 8, 0, 8);
        pnlDatos.add(txtApePat, gridBagConstraints);

        lblApeMat.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        lblApeMat.setText("<html>Apellido materno <font color='#B71C1C'>*</font></html>");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 8, 0, 8);
        pnlDatos.add(lblApeMat, gridBagConstraints);

        txtApeMat.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        txtApeMat.setColumns(14);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 8, 0, 8);
        pnlDatos.add(txtApeMat, gridBagConstraints);

        lblTelefono.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        lblTelefono.setText("<html>Teléfono <font color='#B71C1C'>*</font></html>");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 11;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 8, 0, 8);
        pnlDatos.add(lblTelefono, gridBagConstraints);

        txtTelefono.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        txtTelefono.setColumns(14);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 8, 0, 8);
        pnlDatos.add(txtTelefono, gridBagConstraints);

        lblCorreo.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        lblCorreo.setText("<html>Correo electrónico <font color='#B71C1C'>*</font></html>");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 13;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 8, 0, 8);
        pnlDatos.add(lblCorreo, gridBagConstraints);

        txtCorreo.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        txtCorreo.setColumns(14);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 14;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 8, 0, 8);
        pnlDatos.add(txtCorreo, gridBagConstraints);

        lblNacionalidad.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        lblNacionalidad.setText("<html>Nacionalidad <font color='#B71C1C'>*</font></html>");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 15;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 8, 0, 8);
        pnlDatos.add(lblNacionalidad, gridBagConstraints);

        cboNacionalidad.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        cboNacionalidad.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Peruana", "Argentina", "Boliviana", "Brasileña", "Chilena", "Colombiana", "Ecuatoriana", "Española", "Estadounidense", "Mexicana", "Venezolana" }));
        cboNacionalidad.setEditable(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 16;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 8, 0, 8);
        pnlDatos.add(cboNacionalidad, gridBagConstraints);

        lblPreferencias.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        lblPreferencias.setText("Preferencias de hospedaje");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 17;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 8, 0, 8);
        pnlDatos.add(lblPreferencias, gridBagConstraints);

        txtPreferencias.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        txtPreferencias.setColumns(14);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 18;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 8, 0, 8);
        pnlDatos.add(txtPreferencias, gridBagConstraints);

        chkFrecuente.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        chkFrecuente.setText("Huésped frecuente");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 19;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(6, 8, 8, 8);
        pnlDatos.add(chkFrecuente, gridBagConstraints);

        lblRelleno.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        lblRelleno.setText(" ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 20;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(6, 8, 8, 8);
        pnlDatos.add(lblRelleno, gridBagConstraints);

        pnlFormulario.add(pnlDatos, java.awt.BorderLayout.CENTER);

        pnlSurForm.setOpaque(false);
        pnlSurForm.setLayout(new java.awt.BorderLayout());

        lblNota.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        lblNota.setText("<html><font color='#B71C1C'>*</font> Campo obligatorio</html>");
        lblNota.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 10, 0, 0));
        pnlSurForm.add(lblNota, java.awt.BorderLayout.NORTH);

        pnlBotonesForm.setOpaque(false);
        pnlBotonesForm.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 6, 8));

        btnGuardar.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        btnGuardar.setBackground(new java.awt.Color(46, 125, 50));
        btnGuardar.setForeground(new java.awt.Color(255, 255, 255));
        btnGuardar.setText("Guardar");
        btnGuardar.setOpaque(true);
        btnGuardar.setBorderPainted(false);
        btnGuardar.setFocusPainted(false);
        btnGuardar.setPreferredSize(new java.awt.Dimension(110, 32));
        btnGuardar.addActionListener(this::btnGuardarActionPerformed);
        pnlBotonesForm.add(btnGuardar);

        btnCancelar.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        btnCancelar.setBackground(new java.awt.Color(183, 28, 28));
        btnCancelar.setForeground(new java.awt.Color(255, 255, 255));
        btnCancelar.setText("Cancelar");
        btnCancelar.setOpaque(true);
        btnCancelar.setBorderPainted(false);
        btnCancelar.setFocusPainted(false);
        btnCancelar.setPreferredSize(new java.awt.Dimension(110, 32));
        btnCancelar.addActionListener(this::btnCancelarActionPerformed);
        pnlBotonesForm.add(btnCancelar);

        btnCerrar.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        btnCerrar.setBackground(new java.awt.Color(35, 59, 87));
        btnCerrar.setForeground(new java.awt.Color(255, 255, 255));
        btnCerrar.setText("Cerrar");
        btnCerrar.setOpaque(true);
        btnCerrar.setBorderPainted(false);
        btnCerrar.setFocusPainted(false);
        btnCerrar.setPreferredSize(new java.awt.Dimension(110, 32));
        btnCerrar.addActionListener(this::btnCerrarActionPerformed);
        pnlBotonesForm.add(btnCerrar);

        pnlSurForm.add(pnlBotonesForm, java.awt.BorderLayout.CENTER);

        pnlFormulario.add(pnlSurForm, java.awt.BorderLayout.SOUTH);

        getContentPane().add(pnlFormulario, java.awt.BorderLayout.WEST);

        pnlListado.setOpaque(false);
        pnlListado.setBorder(javax.swing.BorderFactory.createEmptyBorder(12, 6, 12, 12));
        pnlListado.setLayout(new java.awt.BorderLayout(0, 8));

        pnlBusqueda.setOpaque(false);
        pnlBusqueda.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 8, 4));

        lblBuscar.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        lblBuscar.setText("Documento o nombre:");
        pnlBusqueda.add(lblBuscar);

        txtBuscar.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        txtBuscar.setColumns(11);
        txtBuscar.setToolTipText("Escriba el documento (o parte del nombre) y presione Enter");
        txtBuscar.addActionListener(this::txtBuscarActionPerformed);
        pnlBusqueda.add(txtBuscar);

        btnBuscar.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        btnBuscar.setBackground(new java.awt.Color(35, 59, 87));
        btnBuscar.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscar.setText("Buscar");
        btnBuscar.setOpaque(true);
        btnBuscar.setBorderPainted(false);
        btnBuscar.setFocusPainted(false);
        btnBuscar.setPreferredSize(new java.awt.Dimension(110, 32));
        btnBuscar.addActionListener(this::btnBuscarActionPerformed);
        pnlBusqueda.add(btnBuscar);

        btnTodos.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        btnTodos.setBackground(new java.awt.Color(35, 59, 87));
        btnTodos.setForeground(new java.awt.Color(255, 255, 255));
        btnTodos.setText("Mostrar todos");
        btnTodos.setOpaque(true);
        btnTodos.setBorderPainted(false);
        btnTodos.setFocusPainted(false);
        btnTodos.setPreferredSize(new java.awt.Dimension(136, 32));
        btnTodos.addActionListener(this::btnTodosActionPerformed);
        pnlBusqueda.add(btnTodos);

        pnlListado.add(pnlBusqueda, java.awt.BorderLayout.NORTH);

        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tipo", "Documento", "Huésped", "Teléfono", "Correo", "Nacionalidad", "Frecuente", "Reservas"
            }
        ));
        scrollTabla.setViewportView(tabla);

        pnlListado.add(scrollTabla, java.awt.BorderLayout.CENTER);

        pnlAcciones.setOpaque(false);
        pnlAcciones.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 8, 4));

        btnHistorial.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        btnHistorial.setBackground(new java.awt.Color(35, 59, 87));
        btnHistorial.setForeground(new java.awt.Color(255, 255, 255));
        btnHistorial.setText("Ver historial de reservas");
        btnHistorial.setOpaque(true);
        btnHistorial.setBorderPainted(false);
        btnHistorial.setFocusPainted(false);
        btnHistorial.setPreferredSize(new java.awt.Dimension(197, 32));
        btnHistorial.addActionListener(this::btnHistorialActionPerformed);
        pnlAcciones.add(btnHistorial);

        btnEliminar.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        btnEliminar.setBackground(new java.awt.Color(183, 28, 28));
        btnEliminar.setForeground(new java.awt.Color(255, 255, 255));
        btnEliminar.setText("Eliminar huésped");
        btnEliminar.setOpaque(true);
        btnEliminar.setBorderPainted(false);
        btnEliminar.setFocusPainted(false);
        btnEliminar.setPreferredSize(new java.awt.Dimension(154, 32));
        btnEliminar.addActionListener(this::btnEliminarActionPerformed);
        pnlAcciones.add(btnEliminar);

        lblAyuda.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        lblAyuda.setText("Seleccione un huésped de la tabla para modificar sus datos.");
        pnlAcciones.add(lblAyuda);

        pnlListado.add(pnlAcciones, java.awt.BorderLayout.SOUTH);

        getContentPane().add(pnlListado, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        guardar();
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        nuevo();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarActionPerformed
        dispose();
    }//GEN-LAST:event_btnCerrarActionPerformed

    private void txtBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarActionPerformed
        cargarTabla(txtBuscar.getText());
    }//GEN-LAST:event_txtBuscarActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        cargarTabla(txtBuscar.getText());
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnTodosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTodosActionPerformed
        txtBuscar.setText("");
        cargarTabla(null);
    }//GEN-LAST:event_btnTodosActionPerformed

    private void btnHistorialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHistorialActionPerformed
        verHistorial();
    }//GEN-LAST:event_btnHistorialActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        eliminar();
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void nuevo()
    {
        docEnEdicion = null;
        cboTipoDoc.setEnabled(true);
        UIUtil.bloquear(txtNroDoc, false);
        cboTipoDoc.setSelectedIndex(0);
        txtNroDoc.setText("");
        txtNombre.setText("");
        txtApePat.setText("");
        txtApeMat.setText("");
        txtTelefono.setText("");
        txtCorreo.setText("");
        cboNacionalidad.setSelectedItem("Peruana");
        txtPreferencias.setText("");
        chkFrecuente.setSelected(false);
        lblModo.setText("Nuevo huésped");
        lblModo.setForeground(UIUtil.AZUL);
        tabla.clearSelection();
    }

    private void cargarEnFormulario(Huesped h)
    {
        docEnEdicion = h.getNro_doc();
        cboTipoDoc.setSelectedItem(h.getTipo_doc().toUpperCase());
        txtNroDoc.setText(h.getNro_doc());
        txtNombre.setText(h.getNombre());
        txtApePat.setText(h.getApellido_pat());
        txtApeMat.setText(h.getApellido_mat());
        txtTelefono.setText(h.getTelefono());
        txtCorreo.setText(h.getCorreo());
        cboNacionalidad.setSelectedItem(h.getNacionalidad());
        txtPreferencias.setText(h.getPreferencias() == null ? "" : h.getPreferencias());
        chkFrecuente.setSelected(Boolean.TRUE.equals(h.getEsFrecuente()));

        cboTipoDoc.setEnabled(false);
        UIUtil.bloquear(txtNroDoc, true);
        lblModo.setText("Modificando huésped " + h.getTipo_doc().toUpperCase() + " " + h.getNro_doc());
        lblModo.setForeground(UIUtil.DORADO.darker());
    }


    private void cargarTabla(String filtro)
    {
        DefaultTableModel modelo = UIUtil.modelo(tabla);
        modelo.setRowCount(0);
        String f = (filtro == null) ? "" : filtro.trim().toLowerCase();
        int encontrados = 0;
        for (Huesped h : huespedController.ListaHuespedes)
        {
            String nombre = UIUtil.nombreCompleto(h);
            if (!f.isEmpty() && !h.getNro_doc().toLowerCase().contains(f) && !nombre.toLowerCase().contains(f))
            {
                continue;
            }
            encontrados++;
            modelo.addRow(new Object[]
            {
                h.getTipo_doc().toUpperCase(),
                h.getNro_doc(),
                nombre,
                h.getTelefono(),
                h.getCorreo(),
                h.getNacionalidad(),
                Boolean.TRUE.equals(h.getEsFrecuente()) ? "Sí" : "No",
                h.getHisorialReservas().size()
            });
        }
        if (!f.isEmpty() && encontrados == 0)
        {
            UIUtil.mostrarInfo(this, "No se encontró ningún huésped con \"" + filtro.trim() + "\".");
        }
    }


    private Huesped construirDesdeFormulario()
    {

        ArrayList<String> faltan = new ArrayList<>();
        if (txtNroDoc.getText().trim().isEmpty()) { faltan.add("Número de documento"); }
        if (txtNombre.getText().trim().isEmpty()) { faltan.add("Nombres"); }
        if (txtApePat.getText().trim().isEmpty()) { faltan.add("Apellido paterno"); }
        if (txtApeMat.getText().trim().isEmpty()) { faltan.add("Apellido materno"); }
        if (txtTelefono.getText().trim().isEmpty()) { faltan.add("Teléfono"); }
        if (txtCorreo.getText().trim().isEmpty()) { faltan.add("Correo electrónico"); }
        Object nac = cboNacionalidad.getSelectedItem();
        if (nac == null || nac.toString().trim().isEmpty()) { faltan.add("Nacionalidad"); }
        if (!faltan.isEmpty())
        {
            throw new IllegalArgumentException("Complete los campos obligatorios (*):\n - " + String.join("\n - ", faltan));
        }
        String telefono = txtTelefono.getText().trim().replace(" ", "");
if (!telefono.matches("^9[0-9]{8}$"))
{
    txtTelefono.requestFocus();
    throw new IllegalArgumentException("El teléfono debe tener 9 dígitos y empezar con 9.");
}

        Huesped h = new Huesped();

        h.setTipo_doc(cboTipoDoc.getSelectedItem().toString());
        h.setNro_doc(txtNroDoc.getText().trim());
        h.setNombre(txtNombre.getText().trim());
        h.setApellido_pat(txtApePat.getText().trim());
        h.setApellido_mat(txtApeMat.getText().trim());
        h.setTelefono(txtTelefono.getText());
        h.setCorreo(txtCorreo.getText().trim());
        h.setNacionalidad(nac.toString().trim());
        h.setPreferencias(txtPreferencias.getText().trim());
        h.setEsFrecuente(chkFrecuente.isSelected());
        return h;
    }

    private void guardar()
    {
        try
        {
            Huesped h = construirDesdeFormulario();
            if (docEnEdicion == null)
            {
                huespedController.AñadirHuesped(h);    
                UIUtil.mostrarInfo(this, "Huésped registrado correctamente.");
            }
            else
            {
                huespedController.ActualizarHuesped(h);
                UIUtil.mostrarInfo(this, "Datos del huésped actualizados correctamente.");
            }
            cargarTabla(txtBuscar.getText());
            nuevo();
        }
        catch (IllegalArgumentException ex)
        {
            UIUtil.mostrarError(this, ex.getMessage());
        }
    }


    private void eliminar()
    {
        Object doc = UIUtil.valorSeleccionado(tabla, COL_DOC);
        if (doc == null)
        {
            UIUtil.mostrarError(this, "Seleccione un huésped de la tabla.");
            return;
        }
        Huesped h = huespedController.Buscarhuesped(doc.toString());
        if (h == null)
        {
            return;
        }
        if (!h.getHisorialReservas().isEmpty())
        {
            UIUtil.mostrarError(this, "No se puede eliminar a " + UIUtil.nombreCompleto(h) + " porque tiene "
                    + h.getHisorialReservas().size() + " reserva(s) asociada(s).\nElimine primero esas reservas desde la pantalla Reservas.");
            return;
        }
        int ok = javax.swing.JOptionPane.showConfirmDialog(this,
                "¿Eliminar al huésped " + UIUtil.nombreCompleto(h) + " (" + h.getNro_doc() + ")?\nEsta acción no se puede deshacer.",
                "Confirmar eliminación", javax.swing.JOptionPane.YES_NO_OPTION, javax.swing.JOptionPane.WARNING_MESSAGE);
        if (ok != javax.swing.JOptionPane.YES_OPTION)
        {
            return;
        }
        try
        {
            huespedController.EliminarHuesped(h.getNro_doc());
            cargarTabla(txtBuscar.getText());
            nuevo();
            UIUtil.mostrarInfo(this, "Huésped eliminado correctamente.");
        }
        catch (IllegalArgumentException ex)
        {
            UIUtil.mostrarError(this, ex.getMessage());
        }
    }

    private void verHistorial()
    {
        Object doc = UIUtil.valorSeleccionado(tabla, COL_DOC);
        if (doc == null)
        {
            UIUtil.mostrarError(this, "Seleccione un huésped de la tabla.");
            return;
        }
        Huesped h = huespedController.Buscarhuesped(doc.toString());
        if (h != null)
        {
            UIUtil.mostrarTexto(this, "Historial de " + UIUtil.nombreCompleto(h),
                    h.MostrarDatosPersonales() + "\n\n" + h.MostrarDatosCliente());
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnCerrar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnHistorial;
    private javax.swing.JButton btnTodos;
    private javax.swing.JComboBox<String> cboNacionalidad;
    private javax.swing.JComboBox<String> cboTipoDoc;
    private javax.swing.JCheckBox chkFrecuente;
    private javax.swing.JLabel lblApeMat;
    private javax.swing.JLabel lblApePat;
    private javax.swing.JLabel lblAyuda;
    private javax.swing.JLabel lblBuscar;
    private javax.swing.JLabel lblCorreo;
    private javax.swing.JLabel lblModo;
    private javax.swing.JLabel lblNacionalidad;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblNota;
    private javax.swing.JLabel lblNroDoc;
    private javax.swing.JLabel lblPreferencias;
    private javax.swing.JLabel lblRelleno;
    private javax.swing.JLabel lblSubtitulo;
    private javax.swing.JLabel lblTelefono;
    private javax.swing.JLabel lblTipoDoc;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JPanel pnlAcciones;
    private javax.swing.JPanel pnlBotonesForm;
    private javax.swing.JPanel pnlBusqueda;
    private javax.swing.JPanel pnlDatos;
    private javax.swing.JPanel pnlEncabezado;
    private javax.swing.JPanel pnlFormulario;
    private javax.swing.JPanel pnlListado;
    private javax.swing.JPanel pnlSurForm;
    private javax.swing.JScrollPane scrollTabla;
    private javax.swing.JTable tabla;
    private javax.swing.JTextField txtApeMat;
    private javax.swing.JTextField txtApePat;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtCorreo;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtNroDoc;
    private javax.swing.JTextField txtPreferencias;
    private javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables
}
