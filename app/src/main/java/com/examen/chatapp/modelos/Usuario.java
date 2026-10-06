package com.examen.chatapp.modelos;

/** Datos de un usuario del listado. */
public class Usuario {

    private final String nombre;
    private final String alias;
    private final int color; // color del círculo con la inicial

    public Usuario(String nombre, String alias, int color) {
        this.nombre = nombre;
        this.alias = alias;
        this.color = color;
    }

    public String getNombre() { return nombre; }

    public String getAlias() { return alias; }

    public int getColor() { return color; }
}
