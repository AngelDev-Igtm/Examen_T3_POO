
package Vista;

import Controladores.HabitacionController;
import Modelos.Habitacion;
import Modelos.HabitacionSimple;
import Modelos.Suite;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
public class FmrHabitaciones extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(FmrHabitaciones.class.getName());
    private HabitacionController habitacionController;

    public FmrHabitaciones(HabitacionController habController) 
    {
        initComponents();
         this.habitacionController = habController;

        configurarCombosIniciales();
        cargarTablaHabitaciones();
        setLocationRelativeTo(null);
        setTitle("Gestión de Habitaciones - Indigo Loft");
    }
    
    private void configurarCombosIniciales() {
        cmbTipoHabitacion.removeAllItems();
        cmbTipoHabitacion.addItem("HabitacionSimple");
        cmbTipoHabitacion.addItem("Suite");

        cmbEstado.removeAllItems();
        cmbEstado.addItem("Disponible");
        cmbEstado.addItem("Ocupada");
        cmbEstado.addItem("Mantenimiento");
        cmbEstado.addItem("Limpieza");

        cmbDatoExtra.removeAllItems();
        cmbDatoExtra.addItem("SI");
        cmbDatoExtra.addItem("NO");

        cmbTipoHabitacion.addActionListener(e -> actualizarPorTipo());
        actualizarPorTipo();
    }
    
    private void actualizarPorTipo() {
        if (cmbTipoHabitacion.getSelectedItem() == null) return;
        String tipo = cmbTipoHabitacion.getSelectedItem().toString();
        if (tipo.equals("HabitacionSimple")) {
            lblDatoExtra.setText("¿Tiene Vista a la Calle?:");
            if (txtNroHabitacion.isEditable()) {
                txtPrecioNoche.setText("90.0");
            }
        } else {
            lblDatoExtra.setText("¿Incluye Jacuzzi?:");
            if (txtNroHabitacion.isEditable()) {
                txtPrecioNoche.setText("310.0");
            }
        }
    }
    
    private void cargarTablaHabitaciones() {
        DefaultTableModel modelo = (DefaultTableModel) tblHabitaciones.getModel();
        modelo.setRowCount(0);

        for (Habitacion h : habitacionController.ListaHabitaciones) {
            String tipo = h.getClass().getSimpleName();
            String extra = "";

            if (h instanceof HabitacionSimple) {
                extra = ((HabitacionSimple) h).getTieneVistaCalle() ? "Vista calle: SÍ" : "Vista calle: NO";
            } else if (h instanceof Suite) {
                extra = ((Suite) h).getTieneJacuzzi() ? "Jacuzzi: SÍ" : "Jacuzzi: NO";
            }

            modelo.addRow(new Object[]{
                h.getNro_habitacion(),
                tipo,
                h.getEstado(),
                "S/ " + h.getPrecio_noche(),
                extra
            });
        }
    }
    private void limpiarCampos() {
        txtNroHabitacion.setText("");
        txtNroHabitacion.setEditable(true);
        cmbTipoHabitacion.setSelectedIndex(0);
        cmbTipoHabitacion.setEnabled(true);
        cmbEstado.setSelectedIndex(0);
        cmbDatoExtra.setSelectedIndex(0);
        txtPrecioNoche.setText("90.0");
        tblHabitaciones.clearSelection();
        txtNroHabitacion.requestFocus();
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtNroHabitacion = new javax.swing.JTextField();
        txtPrecioNoche = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        cmbTipoHabitacion = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        cmbEstado = new javax.swing.JComboBox<>();
        lblDatoExtra = new javax.swing.JLabel();
        cmbDatoExtra = new javax.swing.JComboBox<>();
        btnRegistrar = new javax.swing.JButton();
        btnBuscar = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblHabitaciones = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(203, 208, 236));

        jLabel1.setFont(new java.awt.Font("Serif", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(84, 127, 190));
        jLabel1.setText("REGISTRO DE HABOTACIONES 'ÍNDIGO LOFT'");

        jLabel2.setFont(new java.awt.Font("Serif", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(153, 153, 0));
        jLabel2.setText("NRO HABITACIÓN (100-2000):");

        jLabel3.setFont(new java.awt.Font("Serif", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(153, 153, 0));
        jLabel3.setText("PRECIO POR NOCHE:");

        jLabel4.setFont(new java.awt.Font("Serif", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(153, 153, 0));
        jLabel4.setText("TIPO DE HABITACIÓN");

        cmbTipoHabitacion.setFont(new java.awt.Font("Serif", 0, 14)); // NOI18N
        cmbTipoHabitacion.setForeground(new java.awt.Color(115, 145, 207));
        cmbTipoHabitacion.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "HabitacionSimple", "Suite", " " }));

        jLabel5.setFont(new java.awt.Font("Serif", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(153, 153, 0));
        jLabel5.setText("ESTADO");

        cmbEstado.setFont(new java.awt.Font("Serif", 0, 14)); // NOI18N
        cmbEstado.setForeground(new java.awt.Color(121, 146, 214));
        cmbEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Disponible", "Ocupada", "Mantenimiento", "Limpieza" }));

        lblDatoExtra.setFont(new java.awt.Font("Serif", 0, 14)); // NOI18N
        lblDatoExtra.setForeground(new java.awt.Color(153, 153, 0));
        lblDatoExtra.setText("DATO EXTRA");

        cmbDatoExtra.setFont(new java.awt.Font("Serif", 0, 14)); // NOI18N
        cmbDatoExtra.setForeground(new java.awt.Color(117, 161, 227));
        cmbDatoExtra.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SI", "NO" }));

        btnRegistrar.setBackground(new java.awt.Color(153, 153, 0));
        btnRegistrar.setFont(new java.awt.Font("Serif", 0, 14)); // NOI18N
        btnRegistrar.setForeground(new java.awt.Color(255, 255, 255));
        btnRegistrar.setText("REGISTRAR");
        btnRegistrar.addActionListener(this::btnRegistrarActionPerformed);

        btnBuscar.setBackground(new java.awt.Color(153, 153, 0));
        btnBuscar.setFont(new java.awt.Font("Serif", 0, 14)); // NOI18N
        btnBuscar.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscar.setText("BUSCAR");
        btnBuscar.addActionListener(this::btnBuscarActionPerformed);

        btnActualizar.setBackground(new java.awt.Color(153, 153, 0));
        btnActualizar.setForeground(new java.awt.Color(255, 255, 255));
        btnActualizar.setText("ACTUALIZAR");
        btnActualizar.addActionListener(this::btnActualizarActionPerformed);

        btnEliminar.setBackground(new java.awt.Color(153, 153, 0));
        btnEliminar.setFont(new java.awt.Font("Serif", 0, 14)); // NOI18N
        btnEliminar.setForeground(new java.awt.Color(255, 255, 255));
        btnEliminar.setText("ELIMINAR");
        btnEliminar.setToolTipText("");
        btnEliminar.addActionListener(this::btnEliminarActionPerformed);

        btnLimpiar.setBackground(new java.awt.Color(153, 153, 0));
        btnLimpiar.setFont(new java.awt.Font("Serif", 0, 14)); // NOI18N
        btnLimpiar.setForeground(new java.awt.Color(255, 255, 255));
        btnLimpiar.setText("LIMPIAR");
        btnLimpiar.setToolTipText("");
        btnLimpiar.addActionListener(this::btnLimpiarActionPerformed);

        tblHabitaciones.setFont(new java.awt.Font("Serif", 0, 12)); // NOI18N
        tblHabitaciones.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nro. Habitación", "Tipo", "Estado", "Precio / Noche", "Detalle Extra"
            }
        ));
        jScrollPane1.setViewportView(tblHabitaciones);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 197, Short.MAX_VALUE)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtNroHabitacion)
                                .addComponent(txtPrecioNoche)
                                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cmbTipoHabitacion, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(cmbEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblDatoExtra, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(cmbDatoExtra, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(60, 60, 60)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(98, 98, 98)
                                .addComponent(btnRegistrar, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(178, 178, 178)
                                .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(37, 37, 37)
                                .addComponent(btnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(53, 53, 53)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 713, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 715, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(232, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRegistrar, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtNroHabitacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3)
                        .addGap(28, 28, 28))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(txtPrecioNoche, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cmbTipoHabitacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cmbEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblDatoExtra, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbDatoExtra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(106, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarActionPerformed
        try {
            int nro = Integer.parseInt(txtNroHabitacion.getText().trim());
            double precio = Double.parseDouble(txtPrecioNoche.getText().trim());
            String estado = cmbEstado.getSelectedItem().toString();
            String tipo = cmbTipoHabitacion.getSelectedItem().toString();
            boolean valorExtra = cmbDatoExtra.getSelectedItem().toString().equals("SI");

            Habitacion nuevaHab;
            if (tipo.equals("HabitacionSimple")) {
                HabitacionSimple simple = new HabitacionSimple();
                simple.setTieneVistaCalle(valorExtra);
                nuevaHab = simple;
            } else {
                Suite suite = new Suite();
                suite.setTieneJacuzzi(valorExtra);
                nuevaHab = suite;
            }

            nuevaHab.setNro_habitacion(nro);
            nuevaHab.setEstado(estado);
            nuevaHab.setPrecio_noche(precio);

            habitacionController.AñadirHabitacion(nuevaHab);
            cargarTablaHabitaciones();
            JOptionPane.showMessageDialog(this, "Habitación registrada correctamente.");
            limpiarCampos();

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Error: El número de habitación y el precio deben ser números válidos.");
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }//GEN-LAST:event_btnRegistrarActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        try {
            String textoNro = txtNroHabitacion.getText().trim();
            if (textoNro.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Ingrese el número de habitación a buscar.");
                return;
            }

            int nro = Integer.parseInt(textoNro);
            Habitacion h = habitacionController.BuscarHabitacion(nro);

            if (h == null) {
                JOptionPane.showMessageDialog(this, "No se encontró ninguna habitación con ese número.");
                return;
            }

            txtNroHabitacion.setEditable(false);
            txtPrecioNoche.setText(String.valueOf(h.getPrecio_noche()));
            cmbEstado.setSelectedItem(h.getEstado());

            cmbTipoHabitacion.setSelectedItem(h.getClass().getSimpleName());
            cmbTipoHabitacion.setEnabled(false);

            if (h instanceof HabitacionSimple) {
                cmbDatoExtra.setSelectedItem(((HabitacionSimple) h).getTieneVistaCalle() ? "SI" : "NO");
            } else if (h instanceof Suite) {
                cmbDatoExtra.setSelectedItem(((Suite) h).getTieneJacuzzi() ? "SI" : "NO");
            }

            JOptionPane.showMessageDialog(this, "Habitación encontrada.");

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Error: El número de habitación debe ser un número entero.");
        }
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        try {
            String textoNro = txtNroHabitacion.getText().trim();
            if (textoNro.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Primero busque una habitación.");
                return;
            }

            int nro = Integer.parseInt(textoNro);
            Habitacion h = habitacionController.BuscarHabitacion(nro);

            if (h == null) {
                JOptionPane.showMessageDialog(this, "No existe una habitación con ese número.");
                return;
            }

            double precio = Double.parseDouble(txtPrecioNoche.getText().trim());
            String estado = cmbEstado.getSelectedItem().toString();
            boolean valorExtra = cmbDatoExtra.getSelectedItem().toString().equals("SI");

            h.setEstado(estado);
            h.setPrecio_noche(precio);

            if (h instanceof HabitacionSimple) {
                ((HabitacionSimple) h).setTieneVistaCalle(valorExtra);
            } else if (h instanceof Suite) {
                ((Suite) h).setTieneJacuzzi(valorExtra);
            }

            cargarTablaHabitaciones();
            JOptionPane.showMessageDialog(this, "Habitación actualizada correctamente.");
            limpiarCampos();

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Error: El precio debe ser un número válido.");
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        try {
            String textoNro = txtNroHabitacion.getText().trim();
            if (textoNro.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Primero busque la habitación que desea eliminar.");
                return;
            }

            int nro = Integer.parseInt(textoNro);
            Habitacion h = habitacionController.BuscarHabitacion(nro);

            if (h == null) {
                JOptionPane.showMessageDialog(this, "No se encontró la habitación.");
                return;
            }

            int confirm = JOptionPane.showConfirmDialog(this,
                    "¿Está seguro de eliminar la habitación Nro. " + h.getNro_habitacion() + "?",
                    "Confirmar eliminación", JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                habitacionController.ListaHabitaciones.remove(h);
                cargarTablaHabitaciones();
                limpiarCampos();
                JOptionPane.showMessageDialog(this, "Habitación eliminada correctamente.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Error: Número de habitación inválido.");
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        limpiarCampos();
    }//GEN-LAST:event_btnLimpiarActionPerformed

   
    public static void main(String args[]) {
      
        java.awt.EventQueue.invokeLater(() -> new FmrHabitaciones(new HabitacionController()).setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnRegistrar;
    private javax.swing.JComboBox<String> cmbDatoExtra;
    private javax.swing.JComboBox<String> cmbEstado;
    private javax.swing.JComboBox<String> cmbTipoHabitacion;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblDatoExtra;
    private javax.swing.JTable tblHabitaciones;
    private javax.swing.JTextField txtNroHabitacion;
    private javax.swing.JTextField txtPrecioNoche;
    // End of variables declaration//GEN-END:variables
}
