package Modelos;

public abstract class Persona 
{
    protected String nombre;
    protected String apellido_pat;
    protected String apellido_mat;
    protected String tipo_doc;
    protected String nro_doc;
    protected String telefono;
    protected String correo;

     
    public Persona() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre)
    {
        if(nombre == null || nombre.trim().isEmpty())
        {
            throw new IllegalArgumentException("Error: Debe llenar este campo primero. Intente nuevamente:");
        }
        this.nombre = nombre;
    }

    public String getApellido_pat() {
        return apellido_pat;
    }

    public void setApellido_pat(String apellido_pat) 
    {
        if(apellido_pat == null || apellido_pat.trim().isEmpty())
        {
            throw new IllegalArgumentException("Error: Debe llenar este campo primero. Intente nuevamente:");
        }
        this.apellido_pat = apellido_pat;
    }

    public String getApellido_mat() {
        return apellido_mat;
    }

    public void setApellido_mat(String apellido_mat)
    {
        if(apellido_mat == null || apellido_mat.trim().isEmpty())
        {
            throw new IllegalArgumentException("Error: Debe llenar este campo primero. Intente nuevamente:");
        }
        this.apellido_mat = apellido_mat;
    }

    public String getTipo_doc() {
        return tipo_doc;
    }

    public void setTipo_doc(String tipo_doc) 
    {
        if(tipo_doc == null || tipo_doc.trim().isEmpty())
        {
            throw new IllegalArgumentException("Error: Debe llenar este campo primero. Intente nuevamente:");
        }
        if(!tipo_doc.equalsIgnoreCase("dni")&& !tipo_doc.equalsIgnoreCase("ce")&& !tipo_doc.equalsIgnoreCase("pas"))
        {
            throw new IllegalArgumentException("Error: Este tipo de documento no es válido. Intente con DNI, CE o PAS");
        }
        this.tipo_doc = tipo_doc;
    }

    public String getNro_doc() {
        return nro_doc;
    }

    public void setNro_doc(String nro_doc) 
    {
        if(nro_doc == null || nro_doc.trim().isEmpty())
        {
            throw new IllegalArgumentException("Error: Debe llenar este campo primero. Intente nuevamente:");
        }
        if(this.tipo_doc == null)
        {
           throw new IllegalArgumentException("Error: Primero debe establecer el tipo de documento.");
        }
        //regex para validar la entrada de digitos unicamente
        if (!nro_doc.matches("^[0-9]+$")) 
        {
            throw new IllegalArgumentException("Error: El documento solo debe contener números.");
        }
        if(this.tipo_doc!=null && this.tipo_doc.equalsIgnoreCase("dni") && nro_doc.length()!=8)
        {
            throw new IllegalArgumentException("Error: El DNI debe tener 8 digitos de forma obligatoria. Intente nuevamente:");
        }
        if( this.tipo_doc!=null && this.tipo_doc.equalsIgnoreCase("ce") && nro_doc.length()!=9)
        {
            throw new IllegalArgumentException("Error: El CE debe tener 9 digitos de forma obligatoria. Intente nuevamente:");
        }
        if( this.tipo_doc!=null && this.tipo_doc.equalsIgnoreCase("pas") && nro_doc.length()!=9)
        {
            throw new IllegalArgumentException("Error: El PAS debe tener 9 digitos de forma obligatoria. Intente nuevamente:");
        }
        this.nro_doc = nro_doc;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) 
    {
        if(telefono == null || telefono.trim().isEmpty())
        {
            throw new IllegalArgumentException("Error: Debe llenar este campo primero. Intente nuevamente:");
        }
       // Expresión regular regex: 
        // ^[+]? -> Puede o no empezar con un signo más
        // [0-9]{7,15}$ -> solo numeros, y seguido de entre 7 y 15 dígitos
        
        String regexInternacional = "^[+]?[0-9]{7,15}$";
        String telefonoLimpio = telefono.trim().replace(" ", "");
        
        if(telefonoLimpio.matches(regexInternacional))
        {
            this.telefono = telefonoLimpio;
        }
        else
        {
            throw new IllegalArgumentException("Error: Formato de celular invalido. Intente nuevamente:");
        }
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) 
    {
        if(correo == null || correo.trim().isEmpty())
        {
            throw new IllegalArgumentException("Error: Debe llenar este campo primero. Intente nuevamente:");
        }
        
        //regex para correo (admite .com, .es, .edu.pe, etc) y si o si con@ y .
        String regexCorreo = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        if(!correo.matches(regexCorreo))
        {
            throw new IllegalArgumentException("Error: Formato de correo invalido. Debe incluir '@' y un dominio válido (ej. usuario@dominio.com).");
        }
        this.correo = correo;
    }
    public String MostrarDatosPersonales()
    {
        return "NOMBRE: "+this.nombre+" | APELLIDO PAT: "+this.apellido_pat+" | APELLIDO MAT: "+this.apellido_mat+" | TIPO DOC: "+this.tipo_doc+" | NRO DOC: "+this.nro_doc
                +" | TELEFONO: "+this.telefono+" | CORREO: "+this.correo;
    }
}