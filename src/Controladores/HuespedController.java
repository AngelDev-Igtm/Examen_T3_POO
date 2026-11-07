
package Controladores;

import Modelos.Huesped;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;

public class HuespedController 
{
    ArrayList<Huesped>ListaHuespedes;
    public HuespedController()
    {
        ListaHuespedes = new ArrayList<>();
    }
    public Huesped Buscarhuesped(String doc)
    {
        for(Huesped h : ListaHuespedes)
        {
            if(doc.equalsIgnoreCase(h.getNro_doc()))
            {
                return h;
            }
        }
        return null;
    }
    public void AñadirHuesped(Huesped h)
    {
        if(h == null)
         {
            throw new IllegalArgumentException("Error: El huesped no puede ser nulo.");
         }
        if(Buscarhuesped(h.getNro_doc()) == null)
         {
           ListaHuespedes.add(h);
         }
        else
         {
             throw new IllegalArgumentException("Error: Ya existe un huesped con ese documento.");
         }
    }
    public Boolean EstaVacia()
    {
        return ListaHuespedes.isEmpty();
    }
    //lanza una excepcion verificada al momento de usar .txt
    public void ExportarListaHuespedesTXT(String nombre_archivo) throws IOException
    {
        try(PrintWriter escritor = new PrintWriter(new FileWriter(nombre_archivo)))
        {
            escritor.println("==========================================================================================");
            escritor.println("                              📋 REPORTE GENERAL DE HUÉSPEDES 📋                          ");
            escritor.println("==========================================================================================");
            
            if(ListaHuespedes.isEmpty())
            {
                escritor.println("AUN NO HAY HUEPSEDES EN EL REGISTRO");
            }
            else
            {
                for(Huesped h : ListaHuespedes)
                {
                    escritor.println(h.MostrarDatosPersonales());
                    escritor.println(h.MostrarDatosCliente());
                    escritor.println("--------------------------------------------------------------------------------------");
                }
            }
            escritor.println("==========================================================================================");
            escritor.println("Generado automáticamente por el Sistema de Hotel 'Indigo Loft' 🏨✨");
        } 
    }
}