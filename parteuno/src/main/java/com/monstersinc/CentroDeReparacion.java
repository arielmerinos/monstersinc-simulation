package com.monstersinc;

import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Lock;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Random;

public class CentroDeReparacion {
    class ItemReparable {
        String tipo;
        int usos;
        boolean necesitaReparacion;

        public ItemReparable(String tipo, int usos) {
            this.tipo = tipo;
            this.usos = usos;
            this.necesitaReparacion = false;
        }

        void usar() {
            usos++;
            if (debeRepararse()) {
                necesitaReparacion = true;
            }
        }

        private boolean debeRepararse() {
            // Lógica para determinar si el item necesita reparación
            // Por ejemplo, una probabilidad basada en el número de usos
            return new Random().nextInt(100) < usos; // Probabilidad aumenta con el uso
        }

        void reparar() {
            necesitaReparacion = false;
            usos = 0; // Resetear el contador de usos después de la reparación
        }
    }

    private final Queue<ItemReparable> colaReparaciones;
    private final Lock lockColaReparaciones = new ReentrantLock();

    public CentroDeReparacion() {
        colaReparaciones = new LinkedList<>();
    }

    public void agregarItemParaReparar(ItemReparable item) {
        lockColaReparaciones.lock();
        try {
            colaReparaciones.add(item);
        } finally {
            lockColaReparaciones.unlock();
        }
    }

    public void repararItem() {
        lockColaReparaciones.lock();
        try {
            if (!colaReparaciones.isEmpty()) {
                ItemReparable item = colaReparaciones.poll();
                if (item != null && item.necesitaReparacion) {
                    // Simular el tiempo de reparación
                    try {
                        Thread.sleep(10); // Simular tiempo de reparación
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                    item.reparar();
                }
            }
        } finally {
            lockColaReparaciones.unlock();
        }
    }

    // Método para verificar el estado de la cola de reparaciones
    public int obtenerTamanioColaReparaciones() {
        return colaReparaciones.size();
    }
}
