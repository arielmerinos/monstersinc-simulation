package com.monstersinc;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class FabricaTanques {
    private final Lock fabricacionLock;
    private int tiempoFabricacion; // Variable para el tiempo de fabricación (puede variar)

    public FabricaTanques(int tiempoFabricacion) {
        this.fabricacionLock = new ReentrantLock();
        this.tiempoFabricacion = new Random().nextInt(tiempoFabricacion+1); // simular que cada tanque toma su tiempo
    }
    // ¿? Como cubrir la parte - "Para fabricarlos, se requiere un monstruo para cada uno" 
    // Falta la parte de  recoleccion y transporte al almacen
    public TanqueI fabricarTanque(String tipo, int capacidad) {
        fabricacionLock.lock();
        try {
            // Simulación de tiempo de fabricación
            Thread.sleep(tiempoFabricacion);

            switch (tipo) {
                case "Estandar":
                    return new TanqueEstandar(capacidad);
                case "Maxitanque":
                    return new Maxitanque(capacidad);
                case "Ultratanque":
                    return new Ultratanque(capacidad);
                case "GigaTanque":
                    int doble = capacidad*2;
                    return new GigaTanque(doble);
                default:
                    throw new IllegalArgumentException("Tipo de tanque no soportado");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        } finally {
            fabricacionLock.unlock();
        }
    }
}
