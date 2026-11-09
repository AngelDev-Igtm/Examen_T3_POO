
package Modelos;

import java.util.ArrayList;
import java.util.UUID;

public class Departamento 
{
    private String id_departamento;
    private String nombre_departamento;
    private double presupuesto_mensual;
    private ArrayList<Empleado> ListaEmpleados;
    private Gerente gerente_a_cargo;

    public Departamento() 
    {
        //usamos uuid para generar un id aleatotio para el departamento
        String uuid = UUID.randomUUID().toString();
        String id = uuid.substring(0, 3).toUpperCase();
        this.id_departamento= "DEP-"+id;
        
        this.ListaEmpleados = new ArrayList<>();
    }

    public String getId_departamento() {
        return id_departamento;
    }

    public String getNombre_departamento() {
        return nombre_departamento;
    }

    public void setNombre_departamento(String nombre_departamento) 
    {
        if(nombre_departamento == null || nombre_departamento.isEmpty())
        {
            throw new IllegalArgumentException("Error: Debe llenar este campo primero. Intente nuevamente:");
        }
        this.nombre_departamento = nombre_departamento;
    }

    public ArrayList<Empleado> getListaEmpleados() {
        return ListaEmpleados;
    }

    public void setListaEmpleados(ArrayList<Empleado> ListaEmpleados) {
        this.ListaEmpleados = ListaEmpleados;
    }

    public Gerente getGerente_a_cargo() {
        return gerente_a_cargo;
    }

    public void setGerente_a_cargo(Gerente gerente_a_cargo) 
    {
        this.gerente_a_cargo = gerente_a_cargo;
    }

    public double getPresupuesto_mensual() {
        return presupuesto_mensual;
    }

    public void setPresupuesto_mensual(double presupuesto_mensual) 
    {
        if(presupuesto_mensual<=0)
        {
            throw new IllegalArgumentException("Error: Debe llenar este campo con información válida. Intente nuevamente:");
        }
        this.presupuesto_mensual = presupuesto_mensual;
    }
    public void AñadirEmpleado(Empleado e)
    {
        ListaEmpleados.add(e);
    }
    public String MostrarDatosDepartamento()
    {
        StringBuilder sb = new StringBuilder();
        
        String gerente = (this.gerente_a_cargo==null) ? "SIN GERENTE" : this.gerente_a_cargo.getNro_doc();
        sb.append("ID DEPARTAMENTO: ").append(this.id_departamento);
        sb.append(" | NOMBRE: ").append(this.nombre_departamento);
        sb.append(" | PRESUPUESTO MENSUAL: ").append(this.presupuesto_mensual);
        sb.append(" | DOC GERENTE A CARGO. ").append(gerente);
        sb.append("-------------------------------LISTA DE EMPLEADOS DEPARTAMENTO"+this.nombre_departamento+"---------------------------------------\n");
        for(Empleado e : ListaEmpleados)
        {
            sb.append(e.MostrarDatosLaborales()).append("\n");
        }
        sb.append("\n");
        return sb.toString();
    }
}