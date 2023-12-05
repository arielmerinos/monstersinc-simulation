package com.monstersinc;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.atomic.AtomicInteger;

public class RecolectorIndustrial {
    private final int capacidadRecolector; // Capacidad J del recolector
    private final AtomicInteger energiaAcumulada;
    private final Lock lockRecolector = new ReentrantLock();

    public RecolectorIndustrial(int capacidadRecolector) {
        this.capacidadRecolector = capacidadRecolector;
        this.energiaAcumulada = new AtomicInteger(0);
    }

    public void vaciarTanque(Tanque tanque) {
        lockRecolector.lock();
        try {
            int energiaExtraida = tanque.getCapacidad(); // Suponiendo que cada tanque tiene un atributo 'capacidad'
            if (energiaAcumulada.get() + energiaExtraida <= capacidadRecolector) {
                energiaAcumulada.addAndGet(energiaExtraida);
                tanque.setEstado(EstadoTanque.DISPONIBLE);
            }
        } finally {
            lockRecolector.unlock();
        }
    }

    public int getEnergiaAcumulada() {
        return energiaAcumulada.get();
    }

    // Otros métodos según sea necesario
    // ...
}

