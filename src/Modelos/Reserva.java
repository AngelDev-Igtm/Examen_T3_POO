
package Modelos;

import Modelos.Recepcionista;
import Modelos.Huesped;
import Modelos.Habitacion;
import Modelos.Factura;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;

public class Reserva 
{
    private String cod_reserva;
    //el codigo ya no tiene setter pq se lo asignamos aleatoriamente
    private LocalDate fecha_inicio;
    private LocalDate fecha_fin;
    private Huesped cliente;
    private Factura factura;
    private String estadoReserva;
    private ArrayList<Habitacion>ListaHabitaciones;
    public Reserva()
    {
        //UUID genera lgo como "550e8400-e29b-41d4-a716-446655440000"
        String uuidCompleto = UUID.randomUUID().toString();
        
        //cortamos los 6 primeros digitos y lo pasamos a mayusculas:
        String codigo = uuidCompleto.substring(0,6).toUpperCase();
        this.cod_reserva = "HTL-"+codigo;
        
        //composicion en reserva-factura
        //se crea la factura junto a su respectiva reserva
        this.factura = new Factura();
        this.factura.setReserva(this);
        
        //el estado de la reserva: pendiente, check-in realizado, finalizada
        this.estadoReserva = "Pendiente";
        
        ListaHabitaciones = new ArrayList<>();
    }

    public String getCod_reserva() {
        return cod_reserva;
    }

    public LocalDate getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(LocalDate fecha_inicio)
    {
        if(fecha_inicio == null)
        {
            throw new IllegalArgumentException("Error: Debe llenar este campo primero. Intente nuevamente:");
        }
        if(fecha_inicio.isBefore(LocalDate.now()))
        {
            throw new IllegalArgumentException("Error: Debe llenar esta campo con informacion valida. Intente nuevamente:");
        }
        this.fecha_inicio = fecha_inicio;
    }

    public LocalDate getFecha_fin() {
        return fecha_fin;
    }

    public void setFecha_fin(LocalDate fecha_fin) 
    {
        if(fecha_fin == null)
        {
            throw new IllegalArgumentException("Error: Debe llenar este campo primero. Intente nuevamente:");
        }
        if(this.fecha_inicio!=null && fecha_fin.isBefore(this.fecha_inicio))
        {
            throw new IllegalArgumentException("Error: La fecha de salida no puede ser antes que la de entrada. Intente nuevamente:");
        }
        this.fecha_fin = fecha_fin;
    }

    public Huesped getCliente() {
        return cliente;
    }

    public void setCliente(Huesped cliente) 
    {
        if(cliente == null)
        {
            throw new IllegalArgumentException("Error: Debe llenar este campo primero. Intente nuevamente:");
        }
        this.cliente = cliente;
    }
    
    public Factura getFactura() {
        return factura;
    }

    public ArrayList<Habitacion> getListaHabitaciones() {
        return ListaHabitaciones;
    }

    public void AñadirHabitacion(Habitacion h)
    {
        if(h == null)
        {
            throw new IllegalArgumentException("Error: Debe llenar este campo primero. Intente nuevamente:");
        }
        //validaciones para ver si sí se úede ocupar la habitacion
        if (!"disponible".equalsIgnoreCase(h.getEstado())) 
        {
            throw new IllegalArgumentException("Error: La habitación seleccionada no está disponible en este momento.");
        }
        ListaHabitaciones.add(h);
    }
    public void RealizarCheckIn(Recepcionista recepcionista_a_cargo)
    {
        if(recepcionista_a_cargo == null)
        {
            throw new IllegalArgumentException("Error: Debe asiganr un recepcionista");
        }
        if(!"Pendiente".equalsIgnoreCase(this.estadoReserva))
        {
            throw new IllegalArgumentException("Error: Esta reserva ya fue procesada");
        }
        
        //cambiamos el estado de las habitaciones a ocupadas
        for(Habitacion h : ListaHabitaciones)
        {
            h.setEstado("ocupada");
        }
        this.estadoReserva = "Check-in realizado";
        System.out.println("El check-in se realizó con exito. Disfrute su estadía!");
    }
    public void RealizarCheckOut(Recepcionista recepcionista_a_cargo)
    {
        if(recepcionista_a_cargo == null)
        {
            throw new IllegalArgumentException("Error: Debe asignar un recepcionista");
        }
        if(!"Check-in realizado".equalsIgnoreCase(this.estadoReserva))
        {
            throw new IllegalArgumentException("Error: No se puede hacer check-out de una reserva que no ha sido procesada");
        }
        
        //cambiamos el estado de las habitaciones a limpieza
        for(Habitacion h : ListaHabitaciones)
        {
            h.setEstado("limpieza");
        }
        this.estadoReserva = "Finalizada";
        System.out.println("El check-out se realizó con exito. Gracias por haber elegido a 'Indigo Loft'!");
    }
    public String MostrarDatosReserva()
    {
        return "CODIGO RESERVA: "+this.cod_reserva+" | FECHA INICIO: "+this.fecha_inicio.toString()+" | FECHA FIN: "+this.fecha_fin.toString()
                +" | DOC HUESPED: "+this.cliente.getNro_doc()
                +" | ESTADO: "+this.estadoReserva;
    }
    public String MostrarListaHabitaciones()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("---------------------------LISTA DE HABITACIONES: RESERVA "+this.cod_reserva+"--------------------------------\n");
        if(ListaHabitaciones.isEmpty())
        {
            sb.append("LA RESERVA NO TIENE HABITACIONES AUN");
        }
        else
        {
            for(Habitacion h : ListaHabitaciones)
            {
                if(h instanceof HabitacionSimple)
                {
                    sb.append("HABITACIÓN SIMPLE: ");
                    sb.append(h.MostrarDatosHabitacion());
                }
                if(h instanceof Suite)
                {
                    sb.append("SUITE:");
                    sb.append(h.MostrarDatosHabitacion());
                }
                sb.append("----------------------------------------------------------------------------------------").append("\n");
            }
        }
        return sb.toString();
    }
}