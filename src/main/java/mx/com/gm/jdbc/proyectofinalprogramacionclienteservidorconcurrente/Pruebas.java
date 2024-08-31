
package mx.com.gm.jdbc.proyectofinalprogramacionclienteservidorconcurrente;

import datos.AsientoDAO;
import datos.EventoDAO;
import domain.Asiento;
import domain.Evento;
import java.util.List;
import ufide.controller.MetodosController;

public class Pruebas {
    public static void main(String[] args) {
        MetodosController metodos = new MetodosController();
        EventoDAO eventoDao = new EventoDAO();
        AsientoDAO asientoDao = new AsientoDAO();
        List<Evento> eventos = eventoDao.listarEventos();
        List<Asiento> asientos = asientoDao.listarAsientos();
        String opc[] = new String[3];
        opc[0] = "Módulo Eventos";
        opc[1] = "Módulo Asientos";
        opc[2] = "Salir";
        int selecciono = -1;
        while (selecciono != 2){
            selecciono = metodos.menuBotones("Seleccione una opción","Menú", opc, "Salir");
            if (selecciono == 0){
                String opc1[] = new String[6];
                opc1[0] = "Agregar Evento";
                opc1[1] = "Listar Eventos";
                opc1[2] = "Editar Evento";
                opc1[3] = "Eliminar Evento";
                opc1[4] = "Buscar Evento";
                opc1[5] = "Salir";
                int selecciono1 = -1;
                while (selecciono1 != 5){
                    selecciono1 = metodos.menuBotones("Seleccione una opción", "Módulo Eventos", opc1, "Salir");
                    if (selecciono1 == 0){
                        var nombreEvento = metodos.getCadena("Nombre", "Nombre Evento");
                        var fecha = metodos.getCadena("Fecha", "Fecha Evento");
                        var lugar = metodos.getCadena("Lugar", "Lugar Evento");
                        var ciudad = metodos.getCadena("Ciudad", "Ciudad Evento");
                        var direccion = metodos.getCadena("Dirección", "Dirección Evento");
                        var estado = metodos.getCadena("Estado", "Estado Evento");
                        var asignado = "No Asignado";
                        var disponibilidad = "Activo";
                        var evento = new Evento(nombreEvento, fecha, lugar, ciudad, direccion, estado, asignado, disponibilidad);
                        eventoDao.agregarEvento(evento);
                        eventos.add(evento);
                        metodos.msg("Evento agregado", "Mensaje de Información", 1);
                    }
                    else if (selecciono1 == 1){
                        for (Evento evento : eventos) {
                            if (evento != null){
                                metodos.msg("Evento " + evento, "Mensaje de Información", 1);
                            }
                        }
                    }
                    else if (selecciono1 == 2){
                        int idBuscar = Integer.parseInt(metodos.getCadena("Id", "Buscar Evento a Actualizar"));
                        boolean encontrado = false;
                        for (Evento evento : eventos) {
                            if (evento != null && evento.getId() == idBuscar){
                                encontrado = true;
                                evento.setNombreEvento(metodos.getCadena("Evento", "Nombre Evento"));
                                evento.setFecha(metodos.getCadena("Fecha", "Fecha Evento"));
                                evento.setLugar(metodos.getCadena("Lugar", "Lugar Evento"));
                                evento.setCiudad(metodos.getCadena("Ciudad", "Ciudad Evento"));
                                evento.setDireccion(metodos.getCadena("Dirección", "Dirección Evento"));
                                evento.setEstado(metodos.getCadena("Estado", "Estado Evento"));
                                evento.setAsignado("No Asignado");
                                evento.setDisponibilidad("Activo");
                                eventoDao.actualizarEvento(evento);
                                metodos.msg("Evento Actualizado", "Mensaje de Información", 1);
                            }
                        }
                        if(!encontrado){
                            metodos.msg("Evento no encontrado","Mensaje de Información", 1);
                        }
                    }
                    else if (selecciono1 == 3){
                        int idEliminar = Integer.parseInt(metodos.getCadena("Id", "Buscar Evento a Eliminar"));
                        boolean encontrado = false;
                        for (Evento evento : eventos) {
                            if (evento != null && evento.getId() == idEliminar){
                                encontrado = true;
                                eventoDao.eliminarEvento(evento);
                                metodos.msg("Evento eliminado", "Mensaje de Información", 1);
                            }
                        }
                        if(!encontrado){
                            metodos.msg("Evento no encontrado", "Mensaje de Información", 1);
                        }
                    }
                    else if (selecciono1 == 4){
                        int idEliminar = Integer.parseInt(metodos.getCadena("Id", "Buscar Evento a Eliminar"));
                        boolean encontrado = false;
                        for (Evento evento : eventos) {
                            if (evento != null && evento.getId() == idEliminar){
                                metodos.msg("Evento " + evento, "Mensaje de Información",1);
                                encontrado = true;
                            }
                        }
                        if(!encontrado){
                            metodos.msg("Evento no encontrado", "Mensaje de Información", 1);
                        }
                    }
                    else if (selecciono1 == 5){
                        
                    }
                }
            }
            else if (selecciono == 1){
                String opc2[] = new String[6];
                opc2[0] = "Agregar Asiento";
                opc2[1] = "Listar Asientos";
                opc2[2] = "Editar Asiento";
                opc2[3] = "Buscar Asiento";
                opc2[4] = "Eliminar Asiento";
                opc2[5] = "Salir";
                int selecciono2 = -1;
                while (selecciono2 != 5){
                    selecciono2 = metodos.menuBotones("Seleccione una opción", "Módulo Asientos", opc2, "Salir");
                    if (selecciono2 == 0){
                        var codigoArea = metodos.getCadena("Código Área", "Código Área Asiento");
                        var numeroAsiento = metodos.getCadena("Número Asiento", "Número de Asiento");
                        double costoVenta = Double.parseDouble(metodos.getCadena("Precio", "Precio Asiento"));
                        var estado = metodos.getCadena("Estado", "Estado Asiento");
                        var disponibilidad = "Activo";
                        var asiento = new Asiento(codigoArea, numeroAsiento, costoVenta, estado, disponibilidad);
                        asientoDao.agregarAsientos(asiento);
                        metodos.msg("Asiento Agregado", "Mensaje de Información", 1);
                    }
                    else if (selecciono2 == 1){
                        for (Asiento asiento : asientos) { 
                            if (asiento != null){
                                metodos.msg("Asiento " + asiento, "Mensaje de Información", 1);
                            }
                        }
                    }
                    else if (selecciono2 == 2){
                        int idBuscar = Integer.parseInt(metodos.getCadena("Id", "Buscar Evento"));
                        boolean encontrado = false;
                        for (Asiento asiento : asientos) {
                            if (asiento != null && asiento.getIdAsiento() == idBuscar){
                                asiento.setCodigoArea(metodos.getCadena("Código Área", "Código Área Asiento"));
                                asiento.setNumeroAsiento(metodos.getCadena("Número Asiento", "Número de Asiento"));
                                asiento.setCostoVenta(Double.parseDouble(metodos.getCadena("Costo Venta", "Costo Venta Asiento")));
                                asiento.setEstado(metodos.getCadena("Estado", "Estado Asiento"));
                                asiento.setDisponibilidad("Activo");
                                asientoDao.actualizarAsientos(asiento);
                                metodos.msg("Asiento Actualizado","Mensaje de Información",1);
                                encontrado = true;
                            }
                        }
                        if(!encontrado){
                            metodos.msg("Asiento no encontrado", "Mensaje de Información", 1);
                        }
                    }
                    else if (selecciono2 == 3){
                        int buscar = Integer.parseInt(metodos.getCadena("Id","Buscar Evento"));
                        boolean encontrado = false;
                        for (Asiento asiento : asientos) {
                            if (asiento != null && asiento.getIdAsiento() == buscar){
                                metodos.msg("Asiento " + asiento, "Mensaje de Información", 1);
                                encontrado = true;
                            }
                        }
                        if(!encontrado){
                            metodos.msg("Evento no encontrado", "Mensaje de Información", 1);
                        }
                    }
                    else if (selecciono2 == 4){
                        int eliminar = Integer.parseInt(metodos.getCadena("Id", "Buscar Evento a Eliminar"));
                        boolean encontrado = false;
                        for (Asiento asiento : asientos) {
                            if (asiento != null && asiento.getIdAsiento() == eliminar){
                                encontrado = true;
                                asientoDao.eliminarAsientos(asiento);
                                metodos.msg("Asiento eliminado exitosamente", "Mensaje de Información", 1);
                            }
                        }
                        if(!encontrado){
                            metodos.msg("Asiento no encontrado", "Mensaje de Información", 1);
                        }
                    }
                    else if (selecciono2 == 5){
                        
                    }
                }
            }
            else if (selecciono == 2){
                
            }
        }
    }
}
