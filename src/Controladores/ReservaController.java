package Controladores;

import Modelos.Reserva;
import Modelos.Habitacion;
import Modelos.HabitacionSimple;
import Modelos.Suite;
import Modelos.Huesped;
import Modelos.Recepcionista;
import java.time.LocalDate;
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
    
    //fecha desde la cual la reserva ya no ocupa la habitacion (la de salida; si hubo check-out, la real)
    private LocalDate FinEfectivo(Reserva r)
    {
        return (r.getFecha_salida_real() != null) ? r.getFecha_salida_real() : r.getFecha_fin();
    }
    //una reserva Cancelada o Finalizada ya no bloquea la habitacion
    private Boolean BloqueaHabitacion(Reserva r)
    {
        return Reserva.CONFIRMADA.equals(r.getEstadoReserva()) || Reserva.EN_CURSO.equals(r.getEstadoReserva());
    }
    //RN-04: una habitacion no puede tener dos reservas con fechas que se cruzan.
    //Los rangos son [entrada, salida): el dia de salida de una reserva puede ser el de entrada de otra.
    public Boolean HabitacionLibre(Habitacion h, LocalDate inicio, LocalDate fin)
    {
        return HabitacionLibre(h, inicio, fin, null);
    }
    //igual que la anterior pero ignorando una reserva (la que se esta modificando)
    public Boolean HabitacionLibre(Habitacion h, LocalDate inicio, LocalDate fin, Reserva ignorar)
    {
        for(Reserva r : ListaReservas)
        {
            if(r != ignorar && BloqueaHabitacion(r) && r.getListaHabitaciones().contains(h))
            {
                if(inicio.isBefore(FinEfectivo(r)) && r.getFecha_inicio().isBefore(fin))
                {
                    return false;
                }
            }
        }
        return true;
    }
    private void ValidarFechas(LocalDate inicio, LocalDate fin)
    {
        if(inicio == null || fin == null)
        {
            throw new IllegalArgumentException("Error: Debe indicar las fechas de entrada y salida.");
        }
        if(inicio.isBefore(LocalDate.now()))
        {
            throw new IllegalArgumentException("Error: La fecha de entrada no puede ser anterior a hoy.");
        }
        if(!fin.isAfter(inicio))
        {
            throw new IllegalArgumentException("Error: La fecha de salida debe ser posterior a la de entrada.");
        }
    }
    //REQ-006: habitaciones disponibles para un rango de fechas, filtradas por tipo ("Simple", "Suite" o null/"Todas")
    public ArrayList<Habitacion> HabitacionesDisponibles(HabitacionController habitaciones, LocalDate inicio, LocalDate fin, String tipo)
    {
        return HabitacionesDisponibles(habitaciones, inicio, fin, tipo, null);
    }
    public ArrayList<Habitacion> HabitacionesDisponibles(HabitacionController habitaciones, LocalDate inicio, LocalDate fin, String tipo, Reserva ignorar)
    {
        ValidarFechas(inicio, fin);
        ArrayList<Habitacion> disponibles = new ArrayList<>();
        for(Habitacion h : habitaciones.ListaHabitaciones)
        {
            if("mantenimiento".equalsIgnoreCase(h.getEstado()))
            {
                continue;
            }
            if("Simple".equalsIgnoreCase(tipo) && !(h instanceof HabitacionSimple))
            {
                continue;
            }
            if("Suite".equalsIgnoreCase(tipo) && !(h instanceof Suite))
            {
                continue;
            }
            if(HabitacionLibre(h, inicio, fin, ignorar))
            {
                disponibles.add(h);
            }
        }
        disponibles.sort((a, b) -> Integer.compare(a.getNro_habitacion(), b.getNro_habitacion()));
        return disponibles;
    }
    //REQ-007 / REQ-008: crea la reserva validando RN-04, RN-05 y RN-06. Si algo falla no queda nada registrado.
    public Reserva CrearReserva(Huesped cliente, Recepcionista recepcionista, LocalDate inicio, LocalDate fin, ArrayList<Habitacion> habitaciones)
    {
        if(cliente == null)
        {
            throw new IllegalArgumentException("Error: La reserva debe estar asociada a un huesped registrado.");
        }
        if(recepcionista == null)
        {
            throw new IllegalArgumentException("Error: Debe indicar el recepcionista que registra la reserva.");
        }
        if(habitaciones == null || habitaciones.isEmpty())
        {
            throw new IllegalArgumentException("Error: Debe seleccionar al menos una habitacion.");
        }
        ValidarFechas(inicio, fin);
        
        Reserva r = new Reserva();
        r.setCliente(cliente);
        r.setRecepcionista_a_cargo(recepcionista);
        r.setFecha_inicio(inicio);
        r.setFecha_fin(fin);
        for(Habitacion h : habitaciones)
        {
            if(!HabitacionLibre(h, inicio, fin))
            {
                throw new IllegalArgumentException("Error: La habitación "+h.getNro_habitacion()+" ya tiene una reserva que se cruza con esas fechas.");
            }
            r.AñadirHabitacion(h);
        }
        //recien aqui se registra
        ListaReservas.add(r);
        cliente.AgregarReserva(r);
        return r;
    }
    //U de reservas: cambia fechas y habitaciones validando RN-04, RN-06 y que siga Confirmada
    public void ModificarReserva(Reserva r, LocalDate inicio, LocalDate fin, ArrayList<Habitacion> habitaciones)
    {
        if(r == null)
        {
            throw new IllegalArgumentException("Error: No hay una reserva seleccionada.");
        }
        if(habitaciones == null || habitaciones.isEmpty())
        {
            throw new IllegalArgumentException("Error: Debe seleccionar al menos una habitacion.");
        }
        ValidarFechas(inicio, fin);
        for(Habitacion h : habitaciones)
        {
            if(!HabitacionLibre(h, inicio, fin, r))
            {
                throw new IllegalArgumentException("Error: La habitación "+h.getNro_habitacion()+" ya tiene una reserva que se cruza con esas fechas.");
            }
        }
        r.Modificar(inicio, fin, habitaciones);
    }
    //D de reservas: elimina la reserva del sistema y del historial del huesped. No se puede si esta En curso.
    public void EliminarReserva(Reserva r)
    {
        if(r == null)
        {
            throw new IllegalArgumentException("Error: No hay una reserva seleccionada.");
        }
        if(Reserva.EN_CURSO.equals(r.getEstadoReserva()))
        {
            throw new IllegalArgumentException("Error: No se puede eliminar una reserva En curso. Haga primero el check-out.");
        }
        ListaReservas.remove(r);
        r.getCliente().getHisorialReservas().remove(r);
    }
    //reserva (no cancelada) que ocupa la habitacion en una fecha concreta, o null si no hay
    public Reserva ReservaDeHabitacionEnFecha(Habitacion h, LocalDate fecha)
    {
        for(Reserva r : ListaReservas)
        {
            if(!Reserva.CANCELADA.equals(r.getEstadoReserva()) && r.getListaHabitaciones().contains(h))
            {
                if(!fecha.isBefore(r.getFecha_inicio()) && fecha.isBefore(FinEfectivo(r)))
                {
                    return r;
                }
            }
        }
        return null;
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
