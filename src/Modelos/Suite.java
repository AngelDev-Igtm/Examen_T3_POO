
package Modelos;

public class Suite extends Habitacion
{
    private Boolean tieneJacuzzi;

    public Suite() 
    {
        this.precio_noche = 310.0;
    }

    public Boolean getTieneJacuzzi() {
        return tieneJacuzzi;
    }

    public void setTieneJacuzzi(Boolean tieneJacuzzi) 
    {
        if(tieneJacuzzi == null)
        {
            throw new IllegalArgumentException("Error: Debe llenar este campo primero. Intente nuevamente:");
        }
        this.tieneJacuzzi = tieneJacuzzi;
    }

    @Override
    public String MostrarDatosHabitacion() 
    {
        String jacuzzi = (this.getTieneJacuzzi() == true) ? "SI":"NO";
        return "NRO HABITACION: "+this.nro_habitacion+" | PRECIO POR NOCHE: "+this.precio_noche+" | ESTADO: "+this.estado+" | INCLUYE JACUZZI: "+jacuzzi;
    } 
}