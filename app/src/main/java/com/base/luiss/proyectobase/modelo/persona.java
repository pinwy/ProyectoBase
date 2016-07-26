package com.base.luiss.proyectobase.modelo;

/**
 * Created by luiss on 22/07/2016.
 */
public class persona {
    private String nombre;
    private String apellido;

    public persona(String nombre, String apellido) {
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public persona() {

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
}
