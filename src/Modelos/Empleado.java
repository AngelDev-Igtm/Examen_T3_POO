package Modelos;

import Modelos.Departamento;
import java.util.ArrayList;

public abstract class Empleado extends Persona
{
    protected double sueldo;
    protected Empleado jefe;
    protected Departamento departamento;
    protected ArrayList<Empleado> ListaSubordinados;

    public Empleado() 
    {
        this.ListaSubordinados = new ArrayList<>();
    }

    public double getSueldo() {
        return sueldo;
    }

    public void setSueldo(double sueldo) 
    {
        if(sueldo<=0)
        {
            throw new IllegalArgumentException("Error: Debe ingresar una cantidad de sueldo valida en este campo. Intente nuevamente:");
        }
        this.sueldo = sueldo;
    }

    public Empleado getJefe() {
        return jefe;
    }

    public void setJefe(Empleado jefe) 
    { //para que un empleado no se tenga de jefe a si mismo
        if(jefe == this)
        {
            throw new IllegalArgumentException("Error: Un empleado no puede ser su propio jefe. Intente nuevamente:");
        }
        
        Empleado actual = jefe;

         while(actual != null)
         {
            if(actual == this)
              {
                throw new IllegalArgumentException("Error: No se puede crear una jerarquia circular.");
              }
              actual = actual.getJefe();
         }
        this.jefe = jefe;
    }
    public ArrayList<Empleado> getListaSubordinados() {
        return ListaSubordinados;
    }
    public void AgregarSubordinado(Empleado e)
    {
        //para que un empleado no sea su propio subordinado
        if(e == null)
        {
           throw new IllegalArgumentException("Error: Debe llenar este campo con informacion valida. Intente nuevamente:");
        }
        if(e == this)
        {
           throw new IllegalArgumentException("Error: Un empleado no puede ser su propio subordinado. Intente nuevamente:");
        }
        if(ListaSubordinados.contains(e)) 
        {
           throw new IllegalArgumentException("Error: Este empleado ya está registrado como subordinado.");
        }
        ListaSubordinados.add(e);
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento)
    {
        this.departamento = departamento;
    }
    
    public abstract double CalcularSueldo();
    public abstract String MostrarDatosLaborales();
}