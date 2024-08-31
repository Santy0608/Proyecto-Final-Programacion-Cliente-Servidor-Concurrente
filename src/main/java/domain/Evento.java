
package domain;

public class Evento {
    private int id;
    private String nombreEvento;
    private String fecha;
    private String lugar;
    private String ciudad;
    private String direccion;
    private String estado;
    private String asignado;
    private String disponibilidad;
    
    public Evento(){
        
    }
    
    public Evento(int id){
        this.id = id;
    }
    
    public Evento(int id, String nombreEvento, String fecha, String lugar, String ciudad, String direccion, String estado, String asignado, String disponibilidad) {
        this.id = id;
        this.nombreEvento = nombreEvento;
        this.fecha = fecha;
        this.lugar = lugar;
        this.ciudad = ciudad;
        this.direccion = direccion;
        this.estado = estado;
        this.asignado = asignado;
        this.disponibilidad = disponibilidad;
    }

    public Evento(String nombreEvento, String fecha, String lugar, String ciudad, String direccion, String estado, String asignado, String disponibilidad) {
        this.nombreEvento = nombreEvento;
        this.fecha = fecha;
        this.lugar = lugar;
        this.ciudad = ciudad;
        this.direccion = direccion;
        this.estado = estado;
        this.asignado = asignado;
        this.disponibilidad = disponibilidad;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreEvento() {
        return nombreEvento;
    }

    public void setNombreEvento(String nombreEvento) {
        this.nombreEvento = nombreEvento;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    
    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    public String getAsignado(){
        return asignado;
    }
    
    public void setAsignado(String asignado){
        this.asignado = asignado;  
    }
    
    public String getDisponibilidad(){
        return disponibilidad;
    }
    
    public void setDisponibilidad(String disponibilidad){
        this.disponibilidad = disponibilidad;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("CatalogoEvento{");
        sb.append("id=").append(id);
        sb.append(", nombreEvento=").append(nombreEvento);
        sb.append(", fecha=").append(fecha);
        sb.append(", lugar=").append(lugar);
        sb.append(", ciudad=").append(ciudad);
        sb.append(", direccion=").append(direccion);
        sb.append(", estado=").append(estado);
        sb.append(", disponibilidad=").append(disponibilidad);
        sb.append('}');
        return sb.toString();
    }
    
    
    
}
