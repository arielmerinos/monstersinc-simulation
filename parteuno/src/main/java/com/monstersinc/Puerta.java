package com.monstersinc;

import java.util.UUID;

public class Puerta {

    private final String id;
    private final String categoria; // Ej: "Niño", "Adulto"
    private EstadoPuerta estado;

    public Puerta(String id, String categoria) {
        this.id = id;
        this.categoria = categoria;
        this.estado = EstadoPuerta.DISPONIBLE;
    }

    public EstadoPuerta getEstado() {
        return estado;
    }

    public void setEstado(EstadoPuerta estado) {
        this.estado = estado;
    }

    public String getId() {
        return id;
    }
    String destino; // "Niño" o "Adulto"
    boolean esEventoEspecial; // Fiesta o Pijamada

    Puerta(String destino, boolean esEventoEspecial) {
        this.destino = destino;
        this.categoria = "";
        this.id = UUID.randomUUID().toString();
        this.esEventoEspecial = esEventoEspecial;
    }

    @Override
    public String toString() {
        return "Puerta{" +
                "id='" + id + '\'' +
                ", categoria='" + categoria + '\'' +
                ", estado=" + estado +
                '}';
    }
}
