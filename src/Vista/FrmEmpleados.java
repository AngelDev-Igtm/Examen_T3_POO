package Vista;

import Controladores.DepartamentoController;
import Controladores.EmpleadoController;
import Modelos.Administrativo;
import Modelos.Conserje;
import Modelos.Departamento;
import Modelos.Empleado;
import Modelos.Gerente;
import Modelos.Recepcionista;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

  

    public class FrmEmpleados extends javax.swing.JFrame {

        private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(FrmEmpleados.class.getName());
        private EmpleadoController empleadoController;
        private DepartamentoController departamentoController;

        public FrmEmpleados(EmpleadoController empController, DepartamentoController depController) {
            initComponents();
            this.empleadoController = empController;
            this.departamentoController = depController;

            configurarCombosIniciales();
            cargarDepartamentosEnCombo();
            cargarJefesEnCombo();
            cargarTablaEmpleados();
            setLocationRelativeTo(null);
            
            //actualiza los datos de los departamentos y los gerentes creados en el form de departamentos
            this.addWindowFocusListener(new java.awt.event.WindowFocusListener() {
            @Override
            public void windowGainedFocus(java.awt.event.WindowEvent e) {
                cargarDepartamentosEnCombo();
                cargarJefesEnCombo();
                cargarTablaEmpleados();
            }
            @Override
            public void windowLostFocus(java.awt.event.WindowEvent e) {}
        });
        }

        private void configurarCombosIniciales() {
            cmbTipoDoc.removeAllItems();
            cmbTipoDoc.addItem("DNI");
            cmbTipoDoc.addItem("CE");
            cmbTipoDoc.addItem("PAS");

            cmbCargo.removeAllItems();
            cmbCargo.addItem("Gerente");
            cmbCargo.addItem("Administrativo");
            cmbCargo.addItem("Conserje");
            cmbCargo.addItem("Recepcionista");

            cmbCargo.addActionListener(e -> actualizarEtiquetaCargo());
            actualizarEtiquetaCargo();
        }

        private void actualizarEtiquetaCargo() {
            if (cmbCargo.getSelectedItem() == null) {
                return;
            }
            String cargo = cmbCargo.getSelectedItem().toString();
            switch (cargo) {
                case "Gerente":
                    lblDatoExtra.setText("Bono Gestión (S/):");
                    break;
                case "Administrativo":
                    lblDatoExtra.setText("Nro Anexo (6 dígitos):");
                    break;
                case "Conserje":
                    lblDatoExtra.setText("Especialidad:");
                    break;
                case "Recepcionista":
                    lblDatoExtra.setText("Turno (mañana/tarde/noche/completo):");
                    break;
            }
        }

        private void cargarDepartamentosEnCombo() {
            cmbDepartamento.removeAllItems();
            cmbDepartamento.addItem("Seleccione un departamento");
            for (Departamento dep : departamentoController.ListaDepartamentos) {
                cmbDepartamento.addItem(dep.getId_departamento() + " - " + dep.getNombre_departamento());
            }
        }

        private void cargarJefesEnCombo() {
            cmbJefe.removeAllItems();
            cmbJefe.addItem("Sin jefe");
            for (Empleado emp : empleadoController.ListaEmpleados) {
                String texto = emp.getNro_doc() + " - " + emp.getNombre() + " " + emp.getApellido_pat();
                cmbJefe.addItem(texto);
            }
        }

        private Departamento obtenerDepartamentoSeleccionado() {
            if (cmbDepartamento.getSelectedIndex() <= 0) {
                return null;
            }
            String seleccionado = cmbDepartamento.getSelectedItem().toString();
            String idDep = seleccionado.split(" - ")[0].trim();
            return departamentoController.BuscarDepartamento(idDep);
        }

        private Empleado obtenerJefeSeleccionado() {
            if (cmbJefe.getSelectedIndex() <= 0) {
                return null;
            }
            String seleccionado = cmbJefe.getSelectedItem().toString();
            String docJefe = seleccionado.split(" - ")[0].trim();
            return empleadoController.BuscarEmpleado(docJefe);
        }

        private void cargarTablaEmpleados() {
            DefaultTableModel modelo = (DefaultTableModel) tblEmpleados.getModel();
            modelo.setRowCount(0);

            for (Empleado emp : empleadoController.ListaEmpleados) {
                String cargo = emp.getClass().getSimpleName();
                String nombreDep = (emp.getDepartamento() != null) ? emp.getDepartamento().getNombre_departamento() : "Sin depa";
                String extra = "";

                if (emp instanceof Gerente) {
                    extra = "Bono: S/" + ((Gerente) emp).getBono_gestion();
                } else if (emp instanceof Administrativo) {
                    extra = "Anexo: " + ((Administrativo) emp).getNro_anexo();
                } else if (emp instanceof Conserje) {
                    extra = "Esp: " + ((Conserje) emp).getEspecialidad();
                } else if (emp instanceof Recepcionista) {
                    extra = "Turno: " + ((Recepcionista) emp).getTurno();
                }

                 String jefeNombre = (emp.getJefe() != null) ? emp.getJefe().getNombre() + " " + emp.getJefe().getApellido_pat() : "Sin jefe";

            modelo.addRow(new Object[]{
                emp.getNro_doc(),
                emp.getNombre() + " " + emp.getApellido_pat() + " " + emp.getApellido_mat(),
                cargo,
                nombreDep,
                "S/ " + emp.CalcularSueldo(),
                extra,
                jefeNombre
            });
            }
        }

        private void limpiarCampos() {
            txtNombre.setText("");
            txtApellidoPat.setText("");
            txtApellidoMat.setText("");
            txtNroDoc.setText("");
            txtTelefono.setText("");
            txtCorreo.setText("");
            txtSueldo.setText("");
            txtDatoExtra.setText("");
            cmbTipoDoc.setSelectedIndex(0);
            cmbCargo.setSelectedIndex(0);
            if (cmbDepartamento.getItemCount() > 0) {
                cmbDepartamento.setSelectedIndex(0);
            }
            if (cmbJefe.getItemCount() > 0) {
                cmbJefe.setSelectedIndex(0);
            }
            tblEmpleados.clearSelection();
            txtNroDoc.setEditable(true);
            cmbCargo.setEnabled(true);
            txtNombre.requestFocus();
        }

        @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jCheckBox1 = new javax.swing.JCheckBox();
        jMenuItem1 = new javax.swing.JMenuItem();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        lbl9 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        txtApellidoPat = new javax.swing.JTextField();
        txtApellidoMat = new javax.swing.JTextField();
        txtNroDoc = new javax.swing.JTextField();
        txtCorreo = new javax.swing.JTextField();
        txtSueldo = new javax.swing.JTextField();
        txtTelefono = new javax.swing.JTextField();
        lblDatoExtra = new javax.swing.JLabel();
        txtDatoExtra = new javax.swing.JTextField();
        cmbTipoDoc = new javax.swing.JComboBox<>();
        cmbCargo = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        cmbDepartamento = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        cmbJefe = new javax.swing.JComboBox<>();
        btnRegistrar = new javax.swing.JButton();
        btnBuscar = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblEmpleados = new javax.swing.JTable();

        jCheckBox1.setText("jCheckBox1");

        jMenuItem1.setText("jMenuItem1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Serif", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(87, 118, 205));
        jLabel1.setText("REGISTRO DE EMPLEADOS 'ÍNDIGO LOFT'");

        jLabel2.setFont(new java.awt.Font("Serif", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(153, 153, 0));
        jLabel2.setText("NOMBRE:");
        jLabel2.setToolTipText("");

        jLabel3.setFont(new java.awt.Font("Serif", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(153, 153, 0));
        jLabel3.setText("APELLIDO PATERNO:");
        jLabel3.setToolTipText("");

        jLabel4.setFont(new java.awt.Font("Serif", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(153, 153, 0));
        jLabel4.setText("APELLIDO MATERNO:");
        jLabel4.setToolTipText("");

        jLabel5.setFont(new java.awt.Font("Serif", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(153, 153, 0));
        jLabel5.setText("TIPO DOCUMENTO:");

        jLabel6.setFont(new java.awt.Font("Serif", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(153, 153, 0));
        jLabel6.setText("NRO DOCUMENTO:");

        jLabel7.setFont(new java.awt.Font("Serif", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(153, 153, 0));
        jLabel7.setText("TELÉFONO:");

        jLabel8.setFont(new java.awt.Font("Serif", 0, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(153, 153, 0));
        jLabel8.setText("CORREO:");
        jLabel8.setAutoscrolls(true);

        lbl9.setFont(new java.awt.Font("Serif", 0, 14)); // NOI18N
        lbl9.setForeground(new java.awt.Color(153, 153, 0));
        lbl9.setText("SUELDO BASE:");

        txtNombre.addActionListener(this::txtNombreActionPerformed);

        txtNroDoc.addActionListener(this::txtNroDocActionPerformed);

        lblDatoExtra.setFont(new java.awt.Font("Serif", 0, 14)); // NOI18N
        lblDatoExtra.setForeground(new java.awt.Color(153, 153, 0));
        lblDatoExtra.setText("DATO EXTRA:");

        cmbTipoDoc.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "DNI", "CE", "PAS" }));

        cmbCargo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Gerente", "Administrativo", "Conserje", "Recepcionista" }));

        jLabel10.setFont(new java.awt.Font("Serif", 0, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(153, 153, 0));
        jLabel10.setText("CARGO:");

        jLabel11.setFont(new java.awt.Font("Serif", 0, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(153, 153, 0));
        jLabel11.setText("DEPARTAMENTO:");

        cmbDepartamento.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel12.setFont(new java.awt.Font("Serif", 0, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(153, 153, 0));
        jLabel12.setText("JEFE:");

        cmbJefe.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbJefe.addActionListener(this::cmbJefeActionPerformed);

        btnRegistrar.setBackground(new java.awt.Color(153, 153, 0));
        btnRegistrar.setFont(new java.awt.Font("Serif", 0, 12)); // NOI18N
        btnRegistrar.setForeground(new java.awt.Color(255, 255, 255));
        btnRegistrar.setText("REGISTRAR");
        btnRegistrar.addActionListener(this::btnRegistrarActionPerformed);

        btnBuscar.setBackground(new java.awt.Color(153, 153, 0));
        btnBuscar.setFont(new java.awt.Font("Serif", 0, 12)); // NOI18N
        btnBuscar.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscar.setText("BUSCAR");
        btnBuscar.addActionListener(this::btnBuscarActionPerformed);

        btnActualizar.setBackground(new java.awt.Color(153, 153, 0));
        btnActualizar.setFont(new java.awt.Font("Serif", 0, 12)); // NOI18N
        btnActualizar.setForeground(new java.awt.Color(255, 255, 255));
        btnActualizar.setText("ACTUALIZAR");
        btnActualizar.addActionListener(this::btnActualizarActionPerformed);

        btnEliminar.setBackground(new java.awt.Color(153, 153, 0));
        btnEliminar.setFont(new java.awt.Font("Serif", 0, 12)); // NOI18N
        btnEliminar.setForeground(new java.awt.Color(255, 255, 255));
        btnEliminar.setText("ELIMINAR");
        btnEliminar.addActionListener(this::btnEliminarActionPerformed);

        btnLimpiar.setBackground(new java.awt.Color(153, 153, 0));
        btnLimpiar.setFont(new java.awt.Font("Serif", 0, 12)); // NOI18N
        btnLimpiar.setForeground(new java.awt.Color(255, 255, 255));
        btnLimpiar.setText("LIMPIAR");
        btnLimpiar.addActionListener(this::btnLimpiarActionPerformed);

        tblEmpleados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Documento", "Nombre Completo", "Cargo", "Departamento", "Sueldo Total", "Dato Extra", "Jefe"
            }
        ));
        jScrollPane1.setViewportView(tblEmpleados);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 614, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cmbCargo, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cmbDepartamento, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cmbTipoDoc, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cmbJefe, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(btnRegistrar, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(31, 31, 31)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(btnEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE)
                                            .addComponent(btnLimpiar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(56, 56, 56)
                                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(62, 62, 62)
                                        .addComponent(lbl9, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 560, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lblDatoExtra, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtDatoExtra, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(58, 58, 58)
                                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(174, 174, 174))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addGroup(layout.createSequentialGroup()
                                                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGap(80, 80, 80)
                                                    .addComponent(txtApellidoPat, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addComponent(txtSueldo, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(122, 122, 122)
                                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(304, 304, 304)
                                                .addComponent(txtNroDoc, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(16, 16, 16)
                                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtApellidoMat, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                .addGap(26, 26, 26))
            .addGroup(layout.createSequentialGroup()
                .addGap(105, 105, 105)
                .addComponent(btnActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtApellidoPat, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtApellidoMat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl9)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSueldo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDatoExtra))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cmbTipoDoc, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel10))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtNroDoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtDatoExtra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cmbCargo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbDepartamento, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbJefe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnRegistrar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(33, 33, 33)
                        .addComponent(btnActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreActionPerformed

    private void txtNroDocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNroDocActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNroDocActionPerformed

    private void cmbJefeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbJefeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbJefeActionPerformed

    private void btnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarActionPerformed

         try {
            if (departamentoController.ListaDepartamentos.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "No se puede registrar un empleado porque aún no existen departamentos.\nPor favor, registre primero un departamento.",
                        "Aviso",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            Departamento dep = obtenerDepartamentoSeleccionado();
            if (dep == null) {
                JOptionPane.showMessageDialog(this, "Seleccione un departamento válido de la lista.");
                return;
            }

            String cargo = cmbCargo.getSelectedItem().toString();
            Empleado nuevoEmpleado = null;

            switch (cargo) {
                case "Gerente":
                    Gerente g = new Gerente();
                    g.setBono_gestion(Double.parseDouble(txtDatoExtra.getText().trim()));
                    nuevoEmpleado = g;
                    break;
                case "Administrativo":
                    Administrativo a = new Administrativo();
                    a.setNro_anexo(txtDatoExtra.getText().trim());
                    nuevoEmpleado = a;
                    break;
                case "Conserje":
                    Conserje c = new Conserje();
                    c.setEspecialidad(txtDatoExtra.getText().trim());
                    nuevoEmpleado = c;
                    break;
                case "Recepcionista":
                    Recepcionista r = new Recepcionista();
                    r.setTurno(txtDatoExtra.getText().trim());
                    nuevoEmpleado = r;
                    break;
            }

            nuevoEmpleado.setNombre(txtNombre.getText().trim());
            nuevoEmpleado.setApellido_pat(txtApellidoPat.getText().trim());
            nuevoEmpleado.setApellido_mat(txtApellidoMat.getText().trim());
            nuevoEmpleado.setTipo_doc(cmbTipoDoc.getSelectedItem().toString());
            nuevoEmpleado.setNro_doc(txtNroDoc.getText().trim());
            nuevoEmpleado.setTelefono(txtTelefono.getText().trim());
            nuevoEmpleado.setCorreo(txtCorreo.getText().trim());
            nuevoEmpleado.setSueldo(Double.parseDouble(txtSueldo.getText().trim()));
            nuevoEmpleado.setDepartamento(dep);

            Empleado jefe = obtenerJefeSeleccionado();
            nuevoEmpleado.setJefe(jefe);

            empleadoController.AñadirEmpleado(nuevoEmpleado);

            if (jefe != null) {
                jefe.AgregarSubordinado(nuevoEmpleado);
            }

            cargarTablaEmpleados();
            cargarJefesEnCombo();
            JOptionPane.showMessageDialog(this, "Empleado registrado correctamente.");
            limpiarCampos();

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Error: El sueldo y el bono deben ser números válidos.");
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }                           
    
    }//GEN-LAST:event_btnRegistrarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        String doc = txtNroDoc.getText().trim();
        Empleado emp = empleadoController.BuscarEmpleado(doc);

        if (emp == null) {
            JOptionPane.showMessageDialog(this, "Primero busque el empleado que desea eliminar.");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this,
                "¿Está seguro de eliminar a " + emp.getNombre() + " " + emp.getApellido_pat() + "?",
                "Confirmar eliminación", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            empleadoController.ListaEmpleados.remove(emp);
            cargarTablaEmpleados();
            cargarJefesEnCombo();
            limpiarCampos();
            JOptionPane.showMessageDialog(this, "Empleado eliminado correctamente.");
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        String doc = txtNroDoc.getText().trim();
        if (doc.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese el número de documento a buscar.");
            return;
        }

        Empleado emp = empleadoController.BuscarEmpleado(doc);
        if (emp == null) {
            JOptionPane.showMessageDialog(this, "No se encontró ningún empleado con ese documento.");
            return;
        }

        txtNombre.setText(emp.getNombre());
        txtApellidoPat.setText(emp.getApellido_pat());
        txtApellidoMat.setText(emp.getApellido_mat());
        cmbTipoDoc.setSelectedItem(emp.getTipo_doc().toUpperCase());
        txtNroDoc.setEditable(false);
        txtTelefono.setText(emp.getTelefono());
        txtCorreo.setText(emp.getCorreo());
        txtSueldo.setText(String.valueOf(emp.getSueldo()));

        cmbCargo.setSelectedItem(emp.getClass().getSimpleName());
        cmbCargo.setEnabled(false);

        if (emp instanceof Gerente) {
            txtDatoExtra.setText(String.valueOf(((Gerente) emp).getBono_gestion()));
        } else if (emp instanceof Administrativo) {
            txtDatoExtra.setText(((Administrativo) emp).getNro_anexo());
        } else if (emp instanceof Conserje) {
            txtDatoExtra.setText(((Conserje) emp).getEspecialidad());
        } else if (emp instanceof Recepcionista) {
            txtDatoExtra.setText(((Recepcionista) emp).getTurno());
        }

        if (emp.getDepartamento() != null) {
            String buscarDep = emp.getDepartamento().getId_departamento() + " - " + emp.getDepartamento().getNombre_departamento();
            cmbDepartamento.setSelectedItem(buscarDep);
        }
        if (emp.getJefe() != null) {
            String buscarJefe = emp.getJefe().getNro_doc() + " - " + emp.getJefe().getNombre() + " " + emp.getJefe().getApellido_pat();
            cmbJefe.setSelectedItem(buscarJefe);
        } else {
            cmbJefe.setSelectedIndex(0);
        }
        JOptionPane.showMessageDialog(this, "Empleado encontrado.");
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        limpiarCampos();
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        try {
            String doc = txtNroDoc.getText().trim();
            Empleado emp = empleadoController.BuscarEmpleado(doc);
            if (emp == null) {
                JOptionPane.showMessageDialog(this, "Primero busque un empleado válido.");
                return;
            }
            Departamento dep = obtenerDepartamentoSeleccionado();
            if (dep == null) {
                JOptionPane.showMessageDialog(this, "Seleccione un departamento válido.");
                return;
            }
            emp.setNombre(txtNombre.getText().trim());
            emp.setApellido_pat(txtApellidoPat.getText().trim());
            emp.setApellido_mat(txtApellidoMat.getText().trim());
            emp.setTelefono(txtTelefono.getText().trim());
            emp.setCorreo(txtCorreo.getText().trim());
            emp.setSueldo(Double.parseDouble(txtSueldo.getText().trim()));
            emp.setDepartamento(dep);
            Empleado nuevoJefe = obtenerJefeSeleccionado();
            emp.setJefe(nuevoJefe);
            if (emp instanceof Gerente) {
                ((Gerente) emp).setBono_gestion(Double.parseDouble(txtDatoExtra.getText().trim()));
            } else if (emp instanceof Administrativo) {
                ((Administrativo) emp).setNro_anexo(txtDatoExtra.getText().trim());
            } else if (emp instanceof Conserje) {
                ((Conserje) emp).setEspecialidad(txtDatoExtra.getText().trim());
            } else if (emp instanceof Recepcionista) {
                ((Recepcionista) emp).setTurno(txtDatoExtra.getText().trim());
            }
            cargarTablaEmpleados();
            cargarJefesEnCombo();
            JOptionPane.showMessageDialog(this, "Empleado actualizado correctamente.");
            limpiarCampos();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Error: Sueldo o bono inválidos.");
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    
    }//GEN-LAST:event_btnActualizarActionPerformed
public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> new FrmEmpleados(new EmpleadoController(), new DepartamentoController()).setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnRegistrar;
    private javax.swing.JComboBox<String> cmbCargo;
    private javax.swing.JComboBox<String> cmbDepartamento;
    private javax.swing.JComboBox<String> cmbJefe;
    private javax.swing.JComboBox<String> cmbTipoDoc;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbl9;
    private javax.swing.JLabel lblDatoExtra;
    private javax.swing.JTable tblEmpleados;
    private javax.swing.JTextField txtApellidoMat;
    private javax.swing.JTextField txtApellidoPat;
    private javax.swing.JTextField txtCorreo;
    private javax.swing.JTextField txtDatoExtra;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtNroDoc;
    private javax.swing.JTextField txtSueldo;
    private javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables

    }