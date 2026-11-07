
package Controladores;

import Modelos.Reserva;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ReservaController 
{
    public ArrayList<Reserva>ListaReservas;
    public ReservaController()
    {
        ListaReservas = new ArrayList<>();
    }
    public Reserva BuscaReserva(String cod)
    {
        for(Reserva r : ListaReservas)
        {
            if(cod.equalsIgnoreCase(r.getCod_reserva()))
            {
                return r;
            }
        }
        return null;
    }
    public void AñadirReserva(Reserva r)
    {
        if(BuscaReserva(r.getCod_reserva())==null)
        {
            ListaReservas.add(r);
        }
    }
    public void ExportarListaReservasTXT(String nombre_archivo) throws IOException
    {
        try(PrintWriter escritor = new PrintWriter(new FileWriter(nombre_archivo)))
        {
            escritor.println("==========================================================================================");
            escritor.println("                            📋 REPORTE GENERAL DE RESERVAS 📋                          ");
            escritor.println("==========================================================================================");
            
            if(ListaReservas.isEmpty())
            {
                escritor.println("NO HAY RESERVAS REGISTRADAS");
            }
            else
            {
                for(Reserva r : ListaReservas)
                {
                    escritor.println(r.MostrarDatosReserva());
                    escritor.println(r.MostrarListaHabitaciones());
                    escritor.println("--------------------------------------------------------------------------------------");
                }
            }
            escritor.println("==========================================================================================");
            escritor.println("Generado automáticamente por el Sistema de Hotel 'Indigo Loft' 🏨✨");
        }
    }
}
