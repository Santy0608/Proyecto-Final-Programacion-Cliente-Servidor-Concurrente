
package domain;

public class Usuario {
    private int idUsuario;
    private String nombre;
    private String apellido;
    private String nombreUsuario;
    private String contraseña;
    private String estado;
    
    public Usuario(){
        
    }
    
    public Usuario(int idUsuario, String estado){
        this.idUsuario = idUsuario;
        this.estado = estado;
    }
    
    public Usuario(int idUsuario){
        this.idUsuario = idUsuario;
    }
    
    public Usuario(String nombre, String apellido, String nombreUsuario, String contraseña, String estado){
        this.nombre = nombre;
        this.apellido = apellido;
        this.nombreUsuario = nombreUsuario;
        this.contraseña = contraseña;
        this.estado = estado;
    }
    
    public Usuario(int idUsuario, String nombre, String apellido, String nombreUsuario, String contraseña, String estado){
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.apellido = apellido;
        this.nombreUsuario = nombreUsuario;
        this.contraseña = contraseña;
        this.estado = estado;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Usuario{");
        sb.append("idUsuario=").append(idUsuario);
        sb.append(", nombre=").append(nombre);
        sb.append(", apellido=").append(apellido);
        sb.append(", nombreUsuario=").append(nombreUsuario);
        sb.append(", contrase\u00f1a=").append(contraseña);
        sb.append(", estado=").append(estado);
        sb.append('}');
        return sb.toString();
    }
    
    
}
