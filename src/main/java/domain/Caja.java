
package domain;

public class Caja {
    private int id;
    private String fecha;
    private String hora;
    private double montoTotal;
    
    public Caja(){
        
    }
    public Caja(int id, String fecha, String hora, double montoTotal){
        this.id = id;
        this.fecha = fecha;
        this.hora = hora;
        this.montoTotal = montoTotal;
    }
    public Caja(String fecha, String hora, double montoTotal){
        this.fecha = fecha;
        this.hora = hora;
        this.montoTotal = montoTotal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public double getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(double montoTotal) {
        this.montoTotal = montoTotal;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Caja{");
        sb.append("id=").append(id);
        sb.append(", fecha=").append(fecha);
        sb.append(", hora=").append(hora);
        sb.append(", montoTotal=").append(montoTotal);
        sb.append('}');
        return sb.toString();
    }
    
    
}
