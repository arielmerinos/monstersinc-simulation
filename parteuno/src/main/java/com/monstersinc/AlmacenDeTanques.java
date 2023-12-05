package com.monstersinc;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class AlmacenDeTanques {

    private final Map<String, Tanque> almacen;
    private final Lock lockAlmacen = new ReentrantLock();

    public AlmacenDeTanques() {
        this.almacen = new ConcurrentHashMap<>();
    }

    public void agregarTanque(Tanque tanque) {
        lockAlmacen.lock();
        try {
            almacen.put(tanque.getId(), tanque);
        } finally {
            lockAlmacen.unlock();
        }
    }

    public Tanque obtenerTanque(String id) {
        lockAlmacen.lock();
        try {
            return almacen.get(id);
        } finally {
            lockAlmacen.unlock();
        }
    }

    public void actualizarEstadoTanque(String id, EstadoTanque nuevoEstado) {
        lockAlmacen.lock();
        try {
            if (almacen.containsKey(id)) {
                Tanque tanque = almacen.get(id);
                tanque.estado = nuevoEstado;
            }
        } finally {
            lockAlmacen.unlock();
        }
    }
}
