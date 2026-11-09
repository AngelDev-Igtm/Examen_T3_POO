
package Controladores;

import Modelos.Habitacion;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.ArrayList;

public class HabitacionController 
{
    public ArrayList<Habitacion> ListaHabitaciones;
    public HabitacionController()
    {
        ListaHabitaciones = new ArrayList<>();
    }
    public Habitacion BuscarHabitacion(int nro_hab)
    {
        for(Habitacion h : ListaHabitaciones)
        {
            if(nro_hab == h.getNro_habitacion())
            {
                return h;
            }
        }
        return null;
    }
    public void AñadirHabitacion(Habitacion h)
    {
        if(h == null)
            {
               throw new IllegalArgumentException("Error: La habitacion no puede ser nula.");
            }

        if(BuscarHabitacion(h.getNro_habitacion()) == null)
           {
               ListaHabitaciones.add(h);
           }
        else
           {
              throw new IllegalArgumentException("Error: Ya existe una habitacion con ese numero.");
           }
    }
    public Boolean EstaVacia()
    {
        return ListaHabitaciones.isEmpty();
    }
    public void ExportarListaHabitacionesTXT(String nombre_archivo) throws IOException
    {
        try(PrintWriter escritor = new PrintWriter(new FileWriter(nombre_archivo)))
        {
            escritor.println("==========================================================================================");
            escritor.println("                            📋 REPORTE GENERAL DE HABITACIONES 📋                          ");
            escritor.println("==========================================================================================");
            
            if(ListaHabitaciones.isEmpty())
            {
                escritor.println("NO HAY NINGUNA HABITACION REGISTRADA");
            }
            else
            {
                for(Habitacion h : ListaHabitaciones)
                {
                    escritor.println(h.MostrarDatosHabitacion());
                    escritor.println("--------------------------------------------------------------------------------------");
                }
            }
            escritor.println("==========================================================================================");
            escritor.println("Generado automáticamente por el Sistema de Hotel 'Indigo Loft' 🏨✨");
        }
    }
}
