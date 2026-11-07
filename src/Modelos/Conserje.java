
package Modelos;

public class Conserje extends Empleado
{
    private String especialidad;

    public Conserje() {
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) 
    {
        if(especialidad == null || especialidad.isEmpty())
        {
            throw new IllegalArgumentException("Error: Debe llenar este campo primero. Intente nuevamente:");
        }
        this.especialidad = especialidad;
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
      
        return "DOC CONSERJE: "+this.nro_doc+" | DEPARTAMENTO: "+this.departamento.getNombre_departamento()+" | SUELDO: S/"+CalcularSueldo()+" | ESPECIALIDAD: "+this.especialidad
                +" | DOC JEFE: "+documentoJefe;
    }    
}