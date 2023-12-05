package com.monstersinc;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.Random;
import java.util.Queue;
import java.util.LinkedList;

public class FabricaDePuertas {

    private final Lock lockFabricacion = new ReentrantLock();
    private final Random random = new Random();
    private final Queue<Puerta> almacenPuertas;

    public FabricaDePuertas() {
        almacenPuertas = new LinkedList<>();
    }

    public Puerta fabricarPuerta() {
        lockFabricacion.lock();
        try {
            // Simular la fabricación de la puerta
            String destino = random.nextInt(10) < 8 ? "Niño" : "Adulto"; // Mayor frecuencia de niños
            boolean esEventoEspecial = random.nextBoolean(); // Probabilidad de evento especial

            Puerta nuevaPuerta = new Puerta(destino, esEventoEspecial);
            enviarAlmacen(nuevaPuerta); // Simular el envío al almacén

            return nuevaPuerta;
        } finally {
            lockFabricacion.unlock();
        }
    }

    private void enviarAlmacen(Puerta puerta) {
        // Asumiendo que este método también puede ser llamado concurrentemente
        synchronized (almacenPuertas) {
            almacenPuertas.add(puerta);
            System.out.println("Puerta enviada al almacén: " + puerta);
        }
    }

    public Puerta obtenerPuertaDelAlmacen() {
        synchronized (almacenPuertas) {
            return almacenPuertas.poll();
        }
    }

    public int cantidadPuertasEnAlmacen() {
        synchronized (almacenPuertas) {
            return almacenPuertas.size();
        }
    }
}
