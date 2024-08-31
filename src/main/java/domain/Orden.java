
package domain;

import java.util.ArrayList;
import java.util.List;
import ufide.controller.MetodosController;

public class Orden {
    MetodosController metodos = new MetodosController();
    private String idOrdenBuscar;
    private final int idOrden;
    private final List<Venta> ventas;
    private static int contadorOrdenes;
    
    public Orden(){
         ventas = new ArrayList<>();
         this.idOrden = ++contadorOrdenes;
    }
    
    public void agregarVenta(Venta venta){
        ventas.add(venta);
    }
    
    public void mostrarOrden(){
        StringBuilder lista = new StringBuilder("");
        metodos.msg("Orden #: " + idOrden, "Número de Orden",1);
        metodos.msg("Total de ventas " + ventas.size(), "Mensaje de Información", 1);
        for (Venta venta : ventas) {
            if (venta != null){
                lista.append(venta).append("\n");
            }
        }
        metodos.msg("" + lista.toString(), "Ventas Registradas", 1);
    }

    public String getIdOrdenBuscar() {
        return idOrdenBuscar;
    }

    public void setIdOrdenBuscar(String idOrdenBuscar) {
        this.idOrdenBuscar = idOrdenBuscar;
    }
     
    
}
