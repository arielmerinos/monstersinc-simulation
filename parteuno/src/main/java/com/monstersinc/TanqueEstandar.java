package com.monstersinc;

public class TanqueEstandar implements Tanque {
  private final int capacidad;

  public TanqueEstandar(int capacidad) { this.capacidad = capacidad; }

  @Override
  public int obtenerCapacidad() {
    return capacidad;
  }

  @Override
  public String obtenerTipo() {
    return "Estandar";
  }
}
