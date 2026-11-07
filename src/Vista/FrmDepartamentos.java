package Vista;
import Controladores.DepartamentoController;
import javax.swing.table.DefaultTableModel;
import Modelos.Departamento;
import Controladores.EmpleadoController;
import Modelos.Empleado;
import Modelos.Gerente;

public class FrmDepartamentos extends javax.swing.JFrame 
{
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(FrmDepartamentos.class.getName());
    private DepartamentoController departamentoController;
    private EmpleadoController empleadoController;
    
    public FrmDepartamentos(DepartamentoController controlador,EmpleadoController controladorEmpleados) 
    {
        initComponents();
        jTextField1.setEditable(true);
        this.departamentoController = controlador;
        this.empleadoController = controladorEmpleados;
        CargarGerentesEnComboBox();
        
        jComboBox1.addActionListener(e -> { Object seleccionado = jComboBox1.getSelectedItem();
        if (seleccionado != null && seleccionado.toString().equals("+ Registrar nuevo gerente")) 
        {
            DlgRegistrarGerente ventana = new DlgRegistrarGerente(
            this,
            true,
            empleadoController
        );
            ventana.setVisible(true);
            // Actualizar la lista después de cerrar la ventana
            CargarGerentesEnComboBox();
        }
        });

        setLocationRelativeTo(null);
        CargarTablaDepartamentos();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton5 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Gestión de Departamentos - Indigo Loft");
        setBackground(new java.awt.Color(245, 242, 235));
        setResizable(false);
        setSize(new java.awt.Dimension(924, 700));

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(196, 155, 92));
        jLabel1.setText("GESTION DE DEPARTAMENTOS");

        jLabel2.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(100, 100, 100));
        jLabel2.setText("ADMINISTRA LOS DEPARTAMENTOS Y SUS RESPONSABLES");

        jLabel3.setText("ID DEL DAPARTAMENTO");

        jTextField1.setName("txtID"); // NOI18N

        jLabel4.setText("NOMBRE DEL DEPARTAMENTO");

        jTextField2.setName("txtNombre"); // NOI18N

        jLabel5.setText("PRESUPUESTO MENSUAL S/.");

        jTextField3.setName("txtPresupuesto"); // NOI18N

        jLabel6.setText("GERENTE A CARGO");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox1.setName("cboGerente"); // NOI18N
        jComboBox1.addActionListener(this::jComboBox1ActionPerformed);

        jButton1.setText("REGISTRAR");
        jButton1.setName("btnRegistrar"); // NOI18N
        jButton1.addActionListener(this::jButton1ActionPerformed);

        jButton2.setText("ACTUALIZAR");
        jButton2.setName("btnActualizar"); // NOI18N
        jButton2.addActionListener(this::jButton2ActionPerformed);

        jButton3.setText("ELIMINAR");
        jButton3.setName("btnEliminar"); // NOI18N
        jButton3.addActionListener(this::jButton3ActionPerformed);

        jButton4.setText("LIMPIAR");
        jButton4.setName("btnLimpiar"); // NOI18N
        jButton4.addActionListener(this::jButton4ActionPerformed);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID", "DEPARTAMENTO", "PRESUPUESTO MENSUAL S/.", "GERENTE A CARGO"
            }
        ));
        jTable1.setName("tblDepartamentos"); // NOI18N
        jScrollPane1.setViewportView(jTable1);

        jButton5.setText("BUSCAR");
        jButton5.setName("btnBuscar"); // NOI18N
        jButton5.addActionListener(this::jButton5ActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE)
                                                .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE))
                                            .addComponent(jButton3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(jButton4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 594, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(16, 16, 16))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void CargarGerentesEnComboBox()
    {
        // Limpiar los elementos anteriores
        jComboBox1.removeAllItems();
        // Primera opción
        jComboBox1.addItem("Seleccione un gerente");
        // Recorrer todos los empleados registrados
        for (Empleado empleado : empleadoController.ListaEmpleados)
        {
            if (empleado instanceof Gerente)
            {
                Gerente gerente = (Gerente) empleado;
                String nombreCompleto = gerente.getNombre() + " " + gerente.getApellido_pat() + " " + gerente.getApellido_mat() + " - " + gerente.getTipo_doc().toUpperCase() + ": " + gerente.getNro_doc();
                jComboBox1.addItem(nombreCompleto);
            }
        }
        // Opción para registrar un nuevo gerente
        jComboBox1.addItem("+ Registrar nuevo gerente");
    }
    //-- El nombre del gerente q registremos
    private String ObtenerNombreGerente(Departamento departamento) 
    {
        Gerente gerente = departamento.getGerente_a_cargo();
        if (gerente == null) 
        {
            return "Sin gerente";
        }
        return gerente.getNombre() + " " + gerente.getApellido_pat() + " " + gerente.getApellido_mat();
    }
    
    private Gerente ObtenerGerenteSeleccionado() 
    {

    Object seleccionado = jComboBox1.getSelectedItem();

    if (seleccionado == null) 
    {
        return null;
    }

    for (Empleado empleado : empleadoController.ListaEmpleados) 
    {
        if (empleado instanceof Gerente) 
        {    
            Gerente gerente = (Gerente) empleado;
            String nombreCompleto = gerente.getNombre() + " " + gerente.getApellido_pat() + " " + gerente.getApellido_mat() + " - " + gerente.getTipo_doc().toUpperCase() + ": " + gerente.getNro_doc();
            if (nombreCompleto.equals(seleccionado.toString())) {
                return gerente;
            }
        }
    }
    return null;
    }
    private void CargarTablaDepartamentos()
    {
        DefaultTableModel modelo = (DefaultTableModel) jTable1.getModel();
        modelo.setRowCount(0);
        for (Departamento departamento : departamentoController.ListaDepartamentos) 
        {
            modelo.addRow(new Object[]
            {
                departamento.getId_departamento(),
                departamento.getNombre_departamento(),
                departamento.getPresupuesto_mensual(),
                ObtenerNombreGerente(departamento)
            });
        }
    }
    
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        try 
        {
        // Validar que los campos no estén vacíos
        String nombre = jTextField2.getText().trim();
        String textoPresupuesto = jTextField3.getText().trim();

        if (nombre.isEmpty() || textoPresupuesto.isEmpty()) 
        {
            javax.swing.JOptionPane.showMessageDialog(this,"Complete el nombre y el presupuesto.");
            return;
        }

        // Crear departamento
        Departamento departamento = new Departamento();

        // Asignar información
        departamento.setNombre_departamento(nombre);
        //No te olvides angel --
        double presupuesto = Double.parseDouble(textoPresupuesto);
        departamento.setPresupuesto_mensual(presupuesto);
        
        // Obtener gerente seleccionado
        if (jComboBox1.getSelectedIndex() == jComboBox1.getItemCount() - 1) 
        {
            javax.swing.JOptionPane.showMessageDialog(this,"Seleccione un gerente válido.");
            return;
        }
        Gerente gerenteSeleccionado = ObtenerGerenteSeleccionado();
        // Asignar gerente al departamento
        departamento.setGerente_a_cargo(gerenteSeleccionado);
        // Establecer el departamento del gerente
        if (gerenteSeleccionado != null) 
        {
            gerenteSeleccionado.setDepartamento(departamento);
        }

        // Registrar en el controlador
        departamentoController.AñadirDepartamento(departamento);

        // Mostrar ID generado
        jTextField1.setText(departamento.getId_departamento());
        
        // esto carga la tabla de los depas
        CargarTablaDepartamentos();
        
        javax.swing.JOptionPane.showMessageDialog(this,"Departamento registrado correctamente.");

        // Limpiar campos para el siguiente registro
        jTextField2.setText("");
        jTextField3.setText("");
        } 
        catch (NumberFormatException e) 
        {
            
        javax.swing.JOptionPane.showMessageDialog(this,"El presupuesto debe ser un número válido.");
        
        } catch (IllegalArgumentException e) 
            
        {
            
        javax.swing.JOptionPane.showMessageDialog(this,e.getMessage());
        
        }  
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        String id =  jTextField1.getText().trim();
        
        if (id.isEmpty())
        {
            javax.swing.JOptionPane.showMessageDialog(this, "Ingrese el nombre del ID a BUSCAR");
            return;
        }
        
        Departamento departamento = departamentoController.BuscarDepartamento(id);
        
        if(departamento == null)
        {
            javax.swing.JOptionPane.showMessageDialog(this, "No se encontro ningin departemento con ese ID, Ingese nuevamente");
            return;
        }        
        //Mostrar datos encontrados
        jTextField2.setText(departamento.getNombre_departamento());
        jTextField3.setText(String.valueOf(departamento.getPresupuesto_mensual()));
        
        javax.swing.JOptionPane.showMessageDialog(this, "DEPARTAMENTO ENCONTRADO CORRECTAMENTE");
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        try 
        {
        // Obtener el ID del departamento
        String id = jTextField1.getText().trim();

        if (id.isEmpty()) 
        {
            javax.swing.JOptionPane.showMessageDialog(this,"Primero busque un departamento.");
            return;
        }

        // Verificar que el departamento exista
        Departamento departamento = departamentoController.BuscarDepartamento(id);

        if (departamento == null) 
        {
            javax.swing.JOptionPane.showMessageDialog(this,"No existe un departamento con ese ID.");
            return;
        }

        // Obtener los nuevos datos
        String nombre = jTextField2.getText().trim();
        String textoPresupuesto = jTextField3.getText().trim();

        if (nombre.isEmpty() || textoPresupuesto.isEmpty()) 
        {
            javax.swing.JOptionPane.showMessageDialog(this,"Complete todos los campos.");
            return;
        }

        double presupuesto = Double.parseDouble(textoPresupuesto);

        // Actualizar el objeto existente
        departamento.setNombre_departamento(nombre);
        departamento.setPresupuesto_mensual(presupuesto);

        // Actualizar la tabla
        CargarTablaDepartamentos();

        javax.swing.JOptionPane.showMessageDialog(this,"Departamento actualizado correctamente.");

    } catch (NumberFormatException e) 
    {
        javax.swing.JOptionPane.showMessageDialog(this,"El presupuesto debe ser un número válido.");

    } catch (IllegalArgumentException e) 
    {
        javax.swing.JOptionPane.showMessageDialog(this,e.getMessage());
    }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        try 
        {

        // Obtener el ID del departamento
        String id = jTextField1.getText().trim();

        if (id.isEmpty()) 
        {
            javax.swing.JOptionPane.showMessageDialog(this,"Primero busque el departamento que desea eliminar.");
            return;
        }

        // Verificar que exista
        Departamento departamento = departamentoController.BuscarDepartamento(id);

        if (departamento == null) 
        {
            javax.swing.JOptionPane.showMessageDialog(this,"No se encontró el departamento.");
            return;
        }

        // Solicitar confirmación
        int respuesta = javax.swing.JOptionPane.showConfirmDialog(this,"¿Está seguro de eliminar el departamento "+ departamento.getNombre_departamento() + "?","Confirmar eliminación",javax.swing.JOptionPane.YES_NO_OPTION);

        if (respuesta != javax.swing.JOptionPane.YES_OPTION) 
        {
            return;
        }

        // Eliminar departamento
        departamentoController.EliminarDepartamento(id);

        // Actualizar tabla
        CargarTablaDepartamentos();

        // Limpiar campos
        jTextField1.setText("");
        jTextField2.setText("");
        jTextField3.setText("");

        javax.swing.JOptionPane.showMessageDialog(this,"Departamento eliminado correctamente.");

    } catch (IllegalArgumentException e) 
    {
        javax.swing.JOptionPane.showMessageDialog(this,e.getMessage());
    }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        // Limpiar los campos de texto
        jTextField1.setText("");
        jTextField2.setText("");
        jTextField3.setText("");
        // Restablecer el ComboBox
        jComboBox1.setSelectedIndex(0);
        // Quitar cualquier selección de la tabla
        jTable1.clearSelection();
        // Colocar el cursor en el campo del nombre
        jTextField2.requestFocus();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed

    public static void main(String args[]) 
    {
        try 
        {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels())
            {
                if ("Nimbus".equals(info.getName())) 
                {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) 
        {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(() -> new FrmDepartamentos(new DepartamentoController(), new EmpleadoController()).setVisible(true));
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    // End of variables declaration//GEN-END:variables
}