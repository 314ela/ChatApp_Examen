package com.examen.chatapp.modelos;

/** Un mensaje del chat. esMio = true se muestra a la derecha, false a la izquierda. */
public class Mensaje {

    private final String texto;
    private final String hora;
    private final boolean esMio;

    public Mensaje(String texto, String hora, boolean esMio) {
        this.texto = texto;
        this.hora = hora;
        this.esMio = esMio;
    }

    public String getTexto() { return texto; }

    public String getHora() { return hora; }

    public boolean esMio() { return esMio; }
}
