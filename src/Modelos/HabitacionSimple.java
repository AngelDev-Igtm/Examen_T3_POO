
package Modelos;

public class HabitacionSimple extends Habitacion
{
    private Boolean tieneVistaCalle;

    public HabitacionSimple() {
        this.precio_noche = 90.0;
    }

    public Boolean getTieneVistaCalle() {
        return tieneVistaCalle;
    }

    public void setTieneVistaCalle(Boolean tieneVistaCalle) 
    {
        if(tieneVistaCalle == null)
        {
            throw new IllegalArgumentException("Error: Debe llenar este campo primero. Intente nuevamente:");
        }
        this.tieneVistaCalle = tieneVistaCalle;
    }

    @Override
    public String MostrarDatosHabitacion()
    {
        String vista = (this.getTieneVistaCalle() == true) ? "SI": "NO";
        return "NRO HABITACION: "+this.nro_habitacion+" | PRECIO POR NOCHE: "+this.precio_noche+" | ESTADO: "+this.estado+" | VISTA A LA CALLE: "+vista;
    } 
}