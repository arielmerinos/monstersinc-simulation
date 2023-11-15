package com.monstersinc;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class AlmacenTanques {
  private final Lock accesoLock;
  private int capacidadAlmacen;    // Capacidad máxima del almacén
  private int tanquesEnUso;        // Número de tanques en uso
  private int tanquesDisponibles;  // Número de tanques disponibles
  private int tanquesEnReparacion; // Número de tanques en reparación
  private int tanquesDanados;      // Número de tanques dañados
  private int tanquesParaNinos;    // Número de tanques para niños
  private int tanquesParaAdultos;  // Número de tanques para adultos

  public AlmacenTanques(int capacidadAlmacen) {
    this.accesoLock = new ReentrantLock();
    this.capacidadAlmacen = capacidadAlmacen;
    this.tanquesEnUso = 0;
    this.tanquesDisponibles = capacidadAlmacen;
    this.tanquesEnReparacion = 0;
    this.tanquesDanados = 0;
    this.tanquesParaNinos = 0;
    this.tanquesParaAdultos = 0;
  }

  public void almacenarTanque(Tanque tanque, String estado) {
    accesoLock.lock();
    try {
      if (tanquesDisponibles > 0) {
        tanquesEnUso++;
        tanquesDisponibles--;
        // Clasificamos niños" o "adultos" según su tipo
        if (tanque.obtenerTipo().equals("Estandar") ||
            tanque.obtenerTipo().equals("Maxitanque")) {
          tanquesParaNinos++;
        } else {
          tanquesParaAdultos++;
        }
      } else {
        throw new IllegalStateException("El almacén de tanques está lleno");
      }
    } finally {
      accesoLock.unlock();
    }
  }

  public void liberarTanque(Tanque tanque, String estado) {
    accesoLock.lock();
    try {
      tanquesEnUso--;
      tanquesDisponibles++;
      // ¿Como liberamos los tanques?- como actualizamos su estado ? - como los
      // mandamos a distribucion?
    } finally {
      accesoLock.unlock();
    }
  }
}
