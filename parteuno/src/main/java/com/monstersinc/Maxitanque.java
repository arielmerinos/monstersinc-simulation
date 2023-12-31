package com.monstersinc;

public class Maxitanque implements TanqueI {
    private final int capacidad;

    public Maxitanque(int capacidad) {
        this.capacidad = capacidad;
    }

    @Override
    public int obtenerCapacidad() {
        return capacidad;
    }

    @Override
    public String obtenerTipo() {
        return "Maxitanque";
    }
}
