package com.monstersinc;

public class Ultratanque implements TanqueI {
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
