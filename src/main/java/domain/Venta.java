
package domain;

public class Venta {
    private int idVenta;
    private String fechaVenta;
    private String horaVenta;
    private String usuario;
    private String evento;
    private String asiento;
    private double monto;
    
    private static int contadorComputadoras;
    
    public Venta(){
        idVenta = ++contadorComputadoras;
    }
    
//    public Venta(String fechaVenta, String horaVenta, String usuario, String evento, String asiento, String vendido){
//        this();
//        this.fechaVenta = fechaVenta;
//        this.horaVenta = horaVenta;
//        this.usuario = usuario;
//        this.evento = evento;
//        this.asiento = asiento;
//        this.vendido = vendido;
//    }
    
    public Venta(int idVenta, String fechaVenta, String horaVenta, String usuario, String evento, String asiento, double monto){
        this.idVenta = idVenta;
        this.fechaVenta = fechaVenta;
        this.horaVenta = horaVenta;
        this.usuario = usuario;
        this.evento = evento;
        this.asiento = asiento;
        this.monto = monto;
    }
    
    
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Venta{");
        sb.append("idVenta=").append(idVenta);
        sb.append(", fechaVenta=").append(fechaVenta);
        sb.append(", horaVenta=").append(horaVenta);
        sb.append(", usuario=").append(usuario);
        sb.append("\n");
        sb.append(", evento=").append(evento);
        sb.append("\n");
        sb.append(", asiento=").append(asiento);
        sb.append(", monto=").append(monto);
        sb.append('}');
        return sb.toString();
        
    }

    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    public String getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(String fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    public String getHoraVenta() {
        return horaVenta;
    }

    public void setHoraVenta(String horaVenta) {
        this.horaVenta = horaVenta;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getEvento() {
        return evento;
    }

    public void setEvento(String evento) {
        this.evento = evento;
    }

    public String getAsiento() {
        return asiento;
    }

    public void setAsiento(String asiento) {
        this.asiento = asiento;
    }
    
    public double getMonto(){
        return monto;
    }
    
    public void setMonto(double monto){
        this.monto = monto;
    }
}
