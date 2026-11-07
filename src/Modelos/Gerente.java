package Modelos;

import Modelos.Empleado;

public class Gerente extends Empleado
{
    private double bono_gestion;

    public Gerente() {
    }

    public double getBono_gestion() {
        return bono_gestion;
    }

    public void setBono_gestion(double bono_gestion) 
    {
        if(bono_gestion<0)
        {
            throw new IllegalArgumentException("Error: Debe llenar este campo con información válida. Intente nuevamente:");
        }
        this.bono_gestion = bono_gestion;
    }
    
    @Override
    public double CalcularSueldo() 
    {
        return this.sueldo+this.bono_gestion;
    }
    
    @Override
    public String MostrarDatosLaborales()
    {
        return "DOC GERENTE: "+this.nro_doc+" | DEPARTAMENTO: "+this.departamento.getNombre_departamento()
                +" | SUELDO: S/"+CalcularSueldo()+" | BONO GESTION: "+this.bono_gestion;
    }
    public String MostrarListaSubordinados()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("--------------------------LISTA DE SUBORDINADOS-----------------------------\n");
        for(Empleado e : ListaSubordinados)
        {
            sb.append(e.MostrarDatosLaborales());
            sb.append("\n");
        }
        return sb.toString();
    }
}