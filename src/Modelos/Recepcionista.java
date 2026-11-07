
package Modelos;

import Modelos.Empleado;

public class Recepcionista extends Empleado
{
    private String turno;

    public Recepcionista() {
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) 
    {
        if(turno== null || turno.isEmpty())
        {
            throw new IllegalArgumentException("Error: Debe llenar este campo primero. Intente nuevamente:");
        }
        if(!turno.equalsIgnoreCase("mañana") && !turno.equalsIgnoreCase("tarde")&& !turno.equalsIgnoreCase("noche")&&!turno.equalsIgnoreCase("completo"))
        {
            throw new IllegalArgumentException("Error: Debe llenar este campo con informacion valida. Intente nuevamente:");
        }
        this.turno = turno;
    }

    @Override
    public double CalcularSueldo() 
    {
        return this.sueldo;
    }

    @Override
    public String MostrarDatosLaborales() 
    {
        String documentoJefe = (this.jefe != null) 
            ? this.jefe.getNro_doc() 
            : "SIN JEFE";
        return "DOC RECEPCIONISTA: "+this.nro_doc+" | DEPARTAMENTO: "+this.departamento.getNombre_departamento()+" | SUELDO: S/"+CalcularSueldo()+" | TURNO: "+this.turno
                +" | DOC JEFE: "+documentoJefe;
    }
}