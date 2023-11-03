package com.monstersinc;

public class Ultratanque implements Tanque {
    private final int capacidad;

    public Ultratanque(int capacidad) {
        this.capacidad = capacidad;
    }

    @Override
    public int obtenerCapacidad() {
        return capacidad;
    }

    @Override
    public String obtenerTipo() {
        return "Ultratanque";
    }
}
