package models;

import enums.Categoria;

public class Ticket {

    private int id, cantidad;
    private String nombre, apellido, correo;
    private Categoria categoria;

    public Ticket(int id, String nombre, String apellido, String correo, int cantidad, Categoria categoria) {
        this.id = id;
        this.cantidad = cantidad;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.categoria = categoria;
    }
    
    
}
