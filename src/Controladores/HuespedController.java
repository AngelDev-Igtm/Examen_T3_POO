package Controladores;

import Modelos.Huesped;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;

public class HuespedController 
{
    public ArrayList<Huesped>ListaHuespedes;
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
    //REQ: modificar un huesped. El documento (tipo y numero) identifica al huesped y no se cambia;
    //el historial de reservas se conserva. 'datos' es un Huesped ya validado con los nuevos valores.
    public void ActualizarHuesped(Huesped datos)
    {
        if(datos == null)
        {
            throw new IllegalArgumentException("Error: El huesped no puede ser nulo.");
        }
        Huesped existente = Buscarhuesped(datos.getNro_doc());
        if(existente == null)
        {
            throw new IllegalArgumentException("Error: No existe un huesped con ese documento.");
        }
        existente.setNombre(datos.getNombre());
        existente.setApellido_pat(datos.getApellido_pat());
        existente.setApellido_mat(datos.getApellido_mat());
        existente.setTelefono(datos.getTelefono());
        existente.setCorreo(datos.getCorreo());
        existente.setNacionalidad(datos.getNacionalidad());
        existente.setPreferencias(datos.getPreferencias());
        existente.setEsFrecuente(datos.getEsFrecuente());
    }
    //D de huespedes: no se puede eliminar un huesped que tenga reservas asociadas
    public void EliminarHuesped(String doc)
    {
        Huesped h = Buscarhuesped(doc);
        if(h == null)
        {
            throw new IllegalArgumentException("Error: No existe un huesped con ese documento.");
        }
        if(!h.getHisorialReservas().isEmpty())
        {
            throw new IllegalArgumentException("Error: No se puede eliminar al huesped porque tiene "
                    + h.getHisorialReservas().size() + " reserva(s) asociada(s). Elimine primero esas reservas.");
        }
        ListaHuespedes.remove(h);
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