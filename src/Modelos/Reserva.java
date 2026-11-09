package Modelos;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;

public class Reserva 
{
    // estados posibles de una reserva (segun el documento de requerimientos) 
    public static final String CONFIRMADA = "Confirmada";
    public static final String EN_CURSO = "En curso";
    public static final String FINALIZADA = "Finalizada";
    public static final String CANCELADA = "Cancelada";

    private String cod_reserva;
    //el codigo ya no tiene setter pq se lo asignamos aleatoriamente
    private LocalDate fecha_inicio;
    private LocalDate fecha_fin;
    //fecha real en la que el huesped hizo check-out (null mientras no haya salido)
    private LocalDate fecha_salida_real;
    private Huesped cliente;
    private Recepcionista recepcionista_a_cargo;
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
        
        //el estado de la reserva: confirmada, en curso, finalizada o cancelada
        this.estadoReserva = CONFIRMADA;
        
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
            throw new IllegalArgumentException("Error: La fecha de entrada no puede ser anterior a hoy.");
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
        //RN-06: la salida debe ser POSTERIOR a la entrada (no puede ser el mismo dia)
        if(this.fecha_inicio!=null && !fecha_fin.isAfter(this.fecha_inicio))
        {
            throw new IllegalArgumentException("Error: La fecha de salida debe ser posterior a la de entrada. Intente nuevamente:");
        }
        this.fecha_fin = fecha_fin;
    }

    public LocalDate getFecha_salida_real() {
        return fecha_salida_real;
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

    public Recepcionista getRecepcionista_a_cargo() {
        return recepcionista_a_cargo;
    }

    //RN-05: toda reserva debe tener un empleado que la registra
    public void setRecepcionista_a_cargo(Recepcionista recepcionista_a_cargo) 
    {
        if(recepcionista_a_cargo == null)
        {
            throw new IllegalArgumentException("Error: Debe asignar el recepcionista que registra la reserva.");
        }
        this.recepcionista_a_cargo = recepcionista_a_cargo;
    }
    
    public Factura getFactura() {
        return factura;
    }

    public String getEstadoReserva() {
        return estadoReserva;
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
        //una habitacion en mantenimiento no se puede reservar. La disponibilidad por fechas
        //(que no se crucen con otra reserva) la valida ReservaController
        if ("mantenimiento".equalsIgnoreCase(h.getEstado())) 
        {
            throw new IllegalArgumentException("Error: La habitación "+h.getNro_habitacion()+" está en mantenimiento.");
        }
        if(ListaHabitaciones.contains(h))
        {
            throw new IllegalArgumentException("Error: La habitación "+h.getNro_habitacion()+" ya está en la reserva.");
        }
        ListaHabitaciones.add(h);
    }
    
    //monto de la reserva = noches x precio por noche (sin IGV)
    public double CalcularMonto()
    {
        return this.factura.CalcularSubtotal();
    }
    
    public void RealizarCheckIn(Recepcionista recepcionista_a_cargo)
    {
        if(recepcionista_a_cargo == null)
        {
            throw new IllegalArgumentException("Error: Debe asignar un recepcionista");
        }
        //REQ-011: solo si esta Confirmada
        if(!CONFIRMADA.equalsIgnoreCase(this.estadoReserva))
        {
            throw new IllegalArgumentException("Error: Solo se puede hacer check-in de una reserva Confirmada (estado actual: "+this.estadoReserva+")");
        }
        //RN-09: el check-in solo se hace el dia de entrada
        if(!LocalDate.now().equals(this.fecha_inicio))
        {
            throw new IllegalArgumentException("Error: El check-in solo se puede hacer el día de entrada ("+this.fecha_inicio+").");
        }
        //primero validamos todas las habitaciones para no dejar la reserva a medias
        for(Habitacion h : ListaHabitaciones)
        {
            if(!"disponible".equalsIgnoreCase(h.getEstado()))
            {
                throw new IllegalArgumentException("Error: La habitación "+h.getNro_habitacion()+" no está disponible (estado: "+h.getEstado()+").");
            }
        }
        
        //cambiamos el estado de las habitaciones a ocupadas
        for(Habitacion h : ListaHabitaciones)
        {
            h.setEstado("ocupada");
        }
        this.estadoReserva = EN_CURSO;
        System.out.println("El check-in se realizó con exito. Disfrute su estadía!");
    }
    public void RealizarCheckOut(Recepcionista recepcionista_a_cargo)
    {
        if(recepcionista_a_cargo == null)
        {
            throw new IllegalArgumentException("Error: Debe asignar un recepcionista");
        }
        if(!EN_CURSO.equalsIgnoreCase(this.estadoReserva))
        {
            throw new IllegalArgumentException("Error: Solo se puede hacer check-out de una reserva En curso (estado actual: "+this.estadoReserva+")");
        }
        
        //REQ-013: el monto final considera las noches realmente hospedadas
        this.fecha_salida_real = LocalDate.now();
        
        //RN-09: tras el check-out la habitacion pasa a limpieza
        for(Habitacion h : ListaHabitaciones)
        {
            h.setEstado("limpieza");
        }
        this.estadoReserva = FINALIZADA;
        System.out.println("El check-out se realizó con exito. Gracias por haber elegido a 'Indigo Loft'!");
    }
    //actualizar una reserva: solo si esta Confirmada. Cambia fechas y habitaciones en un solo paso
    //(si algo no es valido no se modifica nada). La disponibilidad por fechas la valida ReservaController.
    public void Modificar(LocalDate inicio, LocalDate fin, ArrayList<Habitacion> nuevas)
    {
        if(!CONFIRMADA.equalsIgnoreCase(this.estadoReserva))
        {
            throw new IllegalArgumentException("Error: Solo se pueden modificar reservas en estado Confirmada (estado actual: "+this.estadoReserva+")");
        }
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
        if(nuevas == null || nuevas.isEmpty())
        {
            throw new IllegalArgumentException("Error: Debe seleccionar al menos una habitacion.");
        }
        for(Habitacion h : nuevas)
        {
            if("mantenimiento".equalsIgnoreCase(h.getEstado()))
            {
                throw new IllegalArgumentException("Error: La habitación "+h.getNro_habitacion()+" está en mantenimiento.");
            }
        }
        this.fecha_inicio = inicio;
        this.fecha_fin = fin;
        this.ListaHabitaciones.clear();
        this.ListaHabitaciones.addAll(nuevas);
    }
    //REQ-010 / RN-08: solo se puede cancelar una reserva Confirmada
    public void Cancelar()
    {
        if(!CONFIRMADA.equalsIgnoreCase(this.estadoReserva))
        {
            throw new IllegalArgumentException("Error: Solo se pueden cancelar reservas en estado Confirmada (estado actual: "+this.estadoReserva+")");
        }
        this.estadoReserva = CANCELADA;
    }
    public String MostrarDatosReserva()
    {
        String recepcionista = (this.recepcionista_a_cargo != null) ? this.recepcionista_a_cargo.getNro_doc() : "SIN REGISTRAR";
        return "CODIGO RESERVA: "+this.cod_reserva+" | FECHA INICIO: "+this.fecha_inicio.toString()+" | FECHA FIN: "+this.fecha_fin.toString()
                +" | DOC HUESPED: "+this.cliente.getNro_doc()
                +" | DOC RECEPCIONISTA: "+recepcionista
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