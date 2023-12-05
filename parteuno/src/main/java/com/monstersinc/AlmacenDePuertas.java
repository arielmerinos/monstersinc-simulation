package com.monstersinc;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class AlmacenDePuertas {
    
    private final Map<String, Puerta> almacen;
    private final Lock lockAlmacen = new ReentrantLock();

    public AlmacenDePuertas(int numMonstruos) {
        int tamanoAlmacen = 4 * numMonstruos; // Puede variar entre 4 y 10 veces el número de monstruos
        this.almacen = new ConcurrentHashMap<>(tamanoAlmacen);
    }

    public void agregarPuerta(Puerta puerta) {
        lockAlmacen.lock();
        try {
            almacen.put(puerta.getId(), puerta);
        } finally {
            lockAlmacen.unlock();
        }
    }

    public Puerta obtenerPuerta(String id) {
        lockAlmacen.lock();
        try {
            return almacen.get(id);
        } finally {
            lockAlmacen.unlock();
        }
    }

    public void actualizarEstadoPuerta(String id, EstadoPuerta nuevoEstado) {
        lockAlmacen.lock();
        try {
            if (almacen.containsKey(id)) {
                Puerta puerta = almacen.get(id);
                puerta.setEstado(nuevoEstado);
            }
        } finally {
            lockAlmacen.unlock();
        }
    }

}
