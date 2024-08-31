
package domain;

public class Asiento {
    private int idAsiento; 
    private String codigoArea;
    private String numeroAsiento;
    private double costoVenta;
    private String estado;
    private String disponibilidad;
    
    public Asiento() {
        
    }
      
    public Asiento(String codigoArea, String numeroAsiento, double costoVenta, String estado, String disponibilidad){
        this.codigoArea = codigoArea;
        this.numeroAsiento = numeroAsiento;
        this.costoVenta = costoVenta;
        this.estado = estado;
        this.disponibilidad = disponibilidad;
    }
    
//    public Asiento(int idAsiento){
//        this.idAsiento = idAsiento;
//    }
    
    public Asiento(int idAsiento, String codigoArea, String numeroAsiento, double costoVenta, String estado, String disponibilidad){
        this.idAsiento = idAsiento;
        this.codigoArea = codigoArea;
        this.numeroAsiento = numeroAsiento;
        this.costoVenta = costoVenta;
        this.estado = estado;
        this.disponibilidad = disponibilidad;
    }
    
    public Asiento(int idAsiento){
        this.idAsiento = idAsiento;
    }

    

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Asiento{");
        sb.append("idAsiento=").append(idAsiento);
        sb.append(", codigoArea=").append(codigoArea);
        sb.append(", numeroAsiento=").append(numeroAsiento);
        sb.append(", costoVenta=").append(costoVenta);
        sb.append(", estado=").append(estado);
        sb.append(", asignado=").append(disponibilidad);
        sb.append('}');
        return sb.toString();
    }

    public int getIdAsiento() {
        return idAsiento;
    }

    public void setIdAsiento(int idAsiento) {
        this.idAsiento = idAsiento;
    }

    public String getCodigoArea() {
        return codigoArea;
    }

    public void setCodigoArea(String codigoArea) {
        this.codigoArea = codigoArea;
    }

    public String getNumeroAsiento() {
        return numeroAsiento;
    }

    public void setNumeroAsiento(String numeroAsiento) {
        this.numeroAsiento = numeroAsiento;
    }

    public double getCostoVenta() {
        return costoVenta;
    }

    public void setCostoVenta(double costoVenta) {
        this.costoVenta = costoVenta;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(String disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    

    
    
    
    
}
