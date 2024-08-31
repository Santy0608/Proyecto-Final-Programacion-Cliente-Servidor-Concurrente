package ufide.controller;

import javax.swing.JOptionPane;

public class MetodosController {

    public void msg(String msg, String titulo, int tipoMsg) {
        /*
         * 0 = "ERROR_MESSAGE"
         * 1 = "INFORMATION_MESSAGE"
         * 2 = "WARNING_MESSAGE"
         * 3 = "QUESTION_MESSAGE"
         */
        if (tipoMsg <= 3 && tipoMsg >= 0) {
            JOptionPane.showMessageDialog(null, msg, titulo, tipoMsg);
        } else {
            JOptionPane.showMessageDialog(null, msg, titulo, 0);
        }
    }

    public int SIoNo(String msg, String titulo) {
        int respuesta;
        return JOptionPane.showConfirmDialog(null, msg, titulo, JOptionPane.YES_NO_OPTION);
    }

    public int menuBotones(String msg, String titulo, String opciones[], String valorDefecto) {
        int opcion = JOptionPane.showOptionDialog(null, msg, titulo, 0,
                JOptionPane.QUESTION_MESSAGE, null, opciones, valorDefecto);
        return opcion;
    }

    public String getCadena(String msg, String titulo) {
        String cadena = JOptionPane.showInputDialog(null, msg, titulo, JOptionPane.PLAIN_MESSAGE);
        return cadena;
    }

    public String combo(String opciones[], String msg, String titulo, String valorInicial) {
        Object opcion = JOptionPane.showInputDialog(null, msg,
                titulo, JOptionPane.QUESTION_MESSAGE, null,
                opciones, valorInicial);
        return opcion.toString();
    }

}
