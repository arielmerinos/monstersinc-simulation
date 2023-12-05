package com.monstersinc;

public class GigaTanque implements TanqueI {
    private final int capacidad;

    public GigaTanque(int capacidad) {
        this.capacidad = 2*capacidad;
    }

    @Override
    public int obtenerCapacidad() {
        return capacidad;
    }

    @Override
    public String obtenerTipo() {
        return "GigaTanque";
    }
}
