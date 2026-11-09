package Controladores;

import Modelos.Departamento;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.ArrayList;
public class DepartamentoController
{
    public ArrayList<Departamento>ListaDepartamentos;
    public DepartamentoController()
    {
        ListaDepartamentos = new ArrayList<>();
    }
    public Departamento BuscarDepartamento(String cod)
    {
        for(Departamento d : ListaDepartamentos)
        {
            if(cod.equalsIgnoreCase(d.getId_departamento()))
            {
                return d;
            }
        }
        return null;
    }
    public void AñadirDepartamento(Departamento d)
    {
       if(d == null)
         {
             throw new IllegalArgumentException("Error: El departamento no puede ser nulo.");
         }

       if(BuscarDepartamento(d.getId_departamento()) != null)
         {
             throw new IllegalArgumentException("Error: Ya existe un departamento con ese ID.");
         }
       ListaDepartamentos.add(d);
    }
    
    public void ActualizarDepartamento(Departamento departamentoActualizado)
    {
        if (departamentoActualizado == null)
        {
            throw new IllegalArgumentException("El departamento no puede ser nulo");
        }
        
        Departamento departamentoExistente = BuscarDepartamento(departamentoActualizado.getId_departamento());
        
        if(departamentoExistente == null)
        {
            throw new IllegalArgumentException("No esiste un depapartamento con ese ID");
        }
        
        departamentoExistente.setNombre_departamento(departamentoActualizado.getNombre_departamento());
        departamentoExistente.setPresupuesto_mensual(departamentoActualizado.getPresupuesto_mensual());
    }
    
    public void EliminarDepartamento(String id)
    {
        if(id == null || id.trim().isEmpty())
        {
            throw new IllegalArgumentException("Debe de ingresar el ID del dapartamento");
        }
        Departamento departamento = BuscarDepartamento(id.trim());
        
        if(departamento == null)
        {
            throw new IllegalArgumentException("No existe un departemento con ese ID");
        }
        ListaDepartamentos.remove(departamento);
    }
    
    public Boolean EstaVacio()
    {
        return ListaDepartamentos.isEmpty();
    }
    public void ExportarListaDepartamentosTXT(String nombre_archivo) throws IOException
    {
        try(PrintWriter escritor = new PrintWriter(new FileWriter(nombre_archivo)))
        {
            escritor.println("==========================================================================================");
            escritor.println("                            📋 REPORTE GENERAL DE DEPARTAMENTOS 📋                          ");
            escritor.println("==========================================================================================");
            
            if(ListaDepartamentos.isEmpty())
            {
                escritor.println("NO HAY NINGUN DEPARTAMENTO REGISTRADO");
            }
            else
            {
                for(Departamento d : ListaDepartamentos)
                {
                    escritor.println(d.MostrarDatosDepartamento());
                    escritor.println("--------------------------------------------------------------------------------------");
                }       
            }
            escritor.println("==========================================================================================");
            escritor.println("Generado automáticamente por el Sistema de Hotel 'Indigo Loft' 🏨✨");
        }
    }
}