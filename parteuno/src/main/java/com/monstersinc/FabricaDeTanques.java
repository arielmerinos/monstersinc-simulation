package com.monstersinc;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.Random;
import java.util.UUID;
import java.util.Queue;
import java.util.LinkedList;

public class FabricaDeTanques {


    // class Tanque {
    //     TipoTanque tipo;
    //     int capacidad;

    //     Tanque(TipoTanque tipo, int capacidad) {
    //         this.tipo = tipo;
    //         this.capacidad = capacidad;
    //     }

    //     @Override
    //     public String toString() {
    //         return "Tanque{" +
    //                 "tipo=" + tipo +
    //                 ", capacidad=" + capacidad +
    //                 '}';
    //     }
    // }

    private final Lock lockFabricacion = new ReentrantLock();
    private final Queue<Tanque> almacenTanques = new LinkedList<>();
    private final Random random = new Random();

    public Tanque fabricarTanque(TipoTanque tipo) {
        lockFabricacion.lock();
        try {
            int capacidad = calcularCapacidad(tipo);
            String id = UUID.randomUUID().toString();
            Tanque nuevoTanque = new Tanque( id, tipo, capacidad);
            almacenTanques.add(nuevoTanque);

            simularTiempoFabricacion();
            return nuevoTanque;
        } finally {
            lockFabricacion.unlock();
        }
    }

    private void simularTiempoFabricacion() {
        try {
            Thread.sleep(random.nextInt(100) + 50); // Simular tiempo de fabricación
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private int calcularCapacidad(TipoTanque tipo) {
        switch (tipo) {
            case ESTANDAR:
                return 100; //Capacidad para tanque estándar
            case MAXITANQUE:
                return 1000; //Capacidad para maxitanque
            case ULTRATANQUE:
                return 10000; //Capacidad para ultratanque
            case GIGATANQUE:
                return 20000; //Capacidad para gigatanque
            default:
                return 0;
        }
    }

    public Tanque obtenerTanqueDelAlmacen() {
        synchronized (almacenTanques) {
            return almacenTanques.poll();
        }
    }

    public int cantidadTanquesEnAlmacen() {
        synchronized (almacenTanques) {
            return almacenTanques.size();
        }
    }
}
