
package Modelos;

import java.util.ArrayList;

public class Huesped extends Persona
{
    private String nacionalidad;
    private String preferencias;
    private Boolean esFrecuente;
    private ArrayList<Reserva>HisorialReservas;

    public Huesped() 
    {
        this.esFrecuente = false; //no inicia como visitante frecuente del hotel
        this.HisorialReservas = new ArrayList<>(); //se crea una lista de reservas al momento de crear un huesped
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad)
    {
        if(nacionalidad == null || nacionalidad.isEmpty())
        {
            throw new IllegalArgumentException("Error: Debe llenar este campo primero. Intente nuevamente:");
        }
        this.nacionalidad = nacionalidad;
    }

    public String getPreferencias() {
        return preferencias;
    }

    public void setPreferencias(String preferencias) 
    {
        this.preferencias = preferencias;
    }

    public Boolean getEsFrecuente() {
        return esFrecuente;
    }

    public void setEsFrecuente(Boolean esFrecuente) 
    {
        this.esFrecuente = esFrecuente;
    }

    public ArrayList<Reserva> getHisorialReservas()
    {
        return HisorialReservas;
    } 
    public void AgregarReserva(Reserva r)
    {
        if(r == null)
        {
            throw new IllegalArgumentException("Error: No se puede agregar una reserva nula.");
        }
        HisorialReservas.add(r);
    }

    @Override
    //metodo sobrescrito de la clase padre 'persona'
    public String MostrarDatosPersonales()
    {
        return "NOMBRE: "+this.nombre+" | APELLIDO PAT: "+this.apellido_pat+" | APELLIDO MAT: "+this.apellido_mat+" | TIPO DOC: "+this.tipo_doc+" | NRO DOC: "+this.nro_doc
                +" | NACIONALIDAD: "+this.nacionalidad+" | TELEFONO: "+this.telefono+" | CORREO: "+this.correo;
    }
    public String MostrarDatosCliente()
    {
        StringBuilder sb = new StringBuilder();
        String frecuente = (this.esFrecuente == true) ? "SI" : "NO";
        sb.append("-------------------------HISTORIAL DE RESERVAS: CLIENTE "+this.nombre.toUpperCase()+" "+this.apellido_pat.toUpperCase()+"--------------------------\n");
        if(HisorialReservas.isEmpty())
        {
            sb.append("EL CLIENTE NO CUENTA CON RESERVACIONES REGISTRADAS");
        }
        else
        {
            for(Reserva r : HisorialReservas)
            {
                sb.append(r.MostrarDatosReserva()).append("\n");
            }
        }
        return "NACIONALIDAD: "+this.nacionalidad+" | PREFERENCIAS: "+this.preferencias+" | CLIENTE FRECUENTE: "+frecuente+"\n"+sb.toString();
    }
}