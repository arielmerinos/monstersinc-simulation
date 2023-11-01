package com.monstersinc;

public class Monstruo {

    private String nombre;
    private String tipo;  // Grande, Pequeño, Peludo, Escamoso, etc.
    private String color;
    private int edad;
    private int numeroCasillero; // Número de casillero en el vestidor
    private String contrasenaCasillero;

    // Constructor
    public Monstruo(String nombre, String tipo, String color, int edad, int numeroCasillero, String contrasenaCasillero) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.color = color;
        this.edad = edad;
        this.numeroCasillero = numeroCasillero;
        this.contrasenaCasillero = contrasenaCasillero;
    }

    // Getters
    public String getNombre() {
        return nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public String getColor() {
        return color;
    }

    public int getEdad() {
        return edad;
    }

    public int getNumeroCasillero() {
        return numeroCasillero;
    }

    public String getContrasenaCasillero() {
        return contrasenaCasillero;
    }

    // Setters
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public void setNumeroCasillero(int numeroCasillero) {
        this.numeroCasillero = numeroCasillero;
    }

    public void setContrasenaCasillero(String contrasenaCasillero) {
        this.contrasenaCasillero = contrasenaCasillero;
    }

    // Método toString para imprimir bonito
    @Override
    public String toString() {
        return "Monstruo{" +
                "nombre='" + nombre + '\'' +
                ", tipo='" + tipo + '\'' +
                ", color='" + color + '\'' +
                ", edad=" + edad +
                ", numeroCasillero=" + numeroCasillero +
                ", contrasenaCasillero='" + contrasenaCasillero + '\'' +
                '}';
    }
}
