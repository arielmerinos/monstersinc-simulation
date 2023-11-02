package com.monstersinc;


import java.util.concurrent.Semaphore;

public abstract class Bano {
    protected final Semaphore capacidad;

    protected Bano(int maxCapacidad) {
        capacidad = new Semaphore(maxCapacidad);
    }

    public void usar() throws InterruptedException {
        capacidad.acquire();
        try {
            // Usar el baño aquí
        } finally {
            capacidad.release();
        }
    }
}