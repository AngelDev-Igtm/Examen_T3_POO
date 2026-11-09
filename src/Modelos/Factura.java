package Modelos;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.UUID;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;

public class Factura 
{
    private String cod_factura;
    private Reserva reserva;
    private LocalDate fecha_emision;

    public Factura() 
    {
        //UUID para el codigo
        String uuidCompleto = UUID.randomUUID().toString();
        
        //cortamos los 8 primeros digitos y lo pasamos a mayusculas:
        String codigo = uuidCompleto.substring(0,8).toUpperCase();
        this.cod_factura = "FAC-"+codigo;
        //fecha de emision: hoy
        this.fecha_emision = LocalDate.now();
    }

    public String getCod_factura() {
        return cod_factura;
    }

    public Reserva getReserva() {
        return reserva;
    }

    public void setReserva(Reserva reserva) 
    {
        if(reserva == null)
        {
            throw new IllegalArgumentException("Error: Debe llenar este campo con informacion valida. Intente nuevamente:");
        }
        this.reserva = reserva;
    }

    public LocalDate getFecha_emision() {
        return fecha_emision;
    }
    //para calcular los dias de estancia
    public long CalcularDiasEstancia()
    {
        if(this.reserva == null || this.reserva.getFecha_inicio() == null || this.reserva.getFecha_fin() == null)
        {
            return 0;
        }
        //si el huesped ya hizo check-out se cobran las noches realmente hospedadas (REQ-013)
        LocalDate salida = (this.reserva.getFecha_salida_real() != null) ? this.reserva.getFecha_salida_real() : this.reserva.getFecha_fin();
        //chronoUnit usa long
        long dias = ChronoUnit.DAYS.between(this.reserva.getFecha_inicio(), salida);
        //quiere decir que si el cliente sale el mismo dia, se marca como 1, y no como 0 dias
        return (dias<=0)? 1: dias;
    }
    public double CalcularSubtotal()
    {
        //calculamos el precio de cada habitacion + sus dias
        if(this.reserva == null || this.reserva.getListaHabitaciones().isEmpty())
        {
            return 0.0;
        }
        long dias = CalcularDiasEstancia();
        double subtotal = 0.0;
        
        for(Habitacion h : this.reserva.getListaHabitaciones())
        {
            subtotal+=(h.getPrecio_noche()*dias);
        }
        return subtotal;
    }
    public double CalcularImpuesto()
    {
        return CalcularSubtotal()*0.18;
    }
    public double CalcularTotal()
    {
        return CalcularSubtotal()+CalcularImpuesto();
    }
    public String MostrarDatosFactura()
    {
        StringBuilder sb = new StringBuilder();
        if(this.reserva == null)
        {
            sb.append("ESTA FACTURA NO ESTA ASOCIADA A NINGUNA RESERVA");
        }
        else
        {
            sb.append(" | COD FACTURA: ").append(this.cod_factura).append("\n");
            sb.append(" | COD RESERVA: ").append(this.reserva.getCod_reserva()).append("\n");
            sb.append(" | FECHA DE EMISION: ").append(this.fecha_emision.toString()).append("\n");
            Huesped c = this.reserva.getCliente();
            sb.append(" | HUESPED: ").append(c.getNombre()).append(" ").append(c.getApellido_pat()).append(" ").append(c.getApellido_mat()).append("\n");
            sb.append(" | DOC CLIENTE: ").append(c.getNro_doc()).append("\n");
            sb.append(" | HABITACIONES: ");
            for(int i = 0; i < this.reserva.getListaHabitaciones().size(); i++)
            {
                if(i > 0) { sb.append(", "); }
                sb.append(this.reserva.getListaHabitaciones().get(i).getNro_habitacion());
            }
            sb.append("\n");
            sb.append(" | FECHA ENTRADA: ").append(this.reserva.getFecha_inicio()).append("\n");
            sb.append(" | FECHA SALIDA: ").append((this.reserva.getFecha_salida_real() != null) ? this.reserva.getFecha_salida_real() : this.reserva.getFecha_fin()).append("\n");
            sb.append(" | DIAS ESTANCIA: ").append(CalcularDiasEstancia()).append("\n");
            sb.append(" | SUBTOTAL: S/").append(CalcularSubtotal()).append("\n");
            sb.append(" | IGV (18%): S/").append(CalcularImpuesto()).append("\n");
            sb.append(" | TOTAL: S/").append(CalcularTotal()).append("\n");
        }
        return sb.toString();
    }
    public void ExportarFacturaTXT(String nombre_archivo) throws IOException
    {
        try(PrintWriter escritor = new PrintWriter(new FileWriter(nombre_archivo)))
        {
            escritor.println("==========================================================================================");
            escritor.println("                             🏨 COMPROBANTE DE PAGO - FACTURA 🏨                          ");
            escritor.println("==========================================================================================");
            escritor.println(MostrarDatosFactura());
            escritor.println("==========================================================================================");
            escritor.println("¡Gracias por su preferencia! Generado automáticamente por el Sistema de Hotel 'Indigo Loft' ✨");
        
        }
    }
}