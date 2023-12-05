package com.monstersinc;

import java.util.concurrent.locks.ReentrantLock;

public class CentroDeSustos {
    // Identificador único
    private final String id;

    // Variables para manejar el estado del centro
    private int numeroDeContenedores;
    private final int capacidadMaximaContenedores;
    private String estado; // Ejemplo: "Activo", "En Espera", "Necesita Recarga"

    // Lock para manejar la concurrencia
    private final ReentrantLock lock = new ReentrantLock();

    // Constructor
    public CentroDeSustos(String id, int capacidadMaximaContenedores) {
        this.id = id;
        this.capacidadMaximaContenedores = capacidadMaximaContenedores;
        this.numeroDeContenedores = capacidadMaximaContenedores;
        this.estado = "Activo";
    }

    // Métodos de estado
    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    // Método para simular asustar y generar energía
    public void asustarYGenerarEnergia() {
        lock.lock();
        try {
            if (numeroDeContenedores > 0) {
                // Lógica para asustar y generar energía
                registrarEvento("Energía generada");
                numeroDeContenedores--;
            } else {
                setEstado("Necesita Recarga");
                registrarEvento("Contenedores agotados, necesita recarga");
            }
        } finally {
            lock.unlock();
        }
    }

    // Método para recargar contenedores
    public void recargarContenedores() {
        lock.lock();
        try {
            numeroDeContenedores = capacidadMaximaContenedores;
            setEstado("Activo");
            registrarEvento("Contenedores recargados");
        } finally {
            lock.unlock();
        }
    }

    // Método para registrar eventos
    private void registrarEvento(String mensaje) {
        // Implementar lógica de registro de eventos, por ejemplo, escribir en un archivo de log
        System.out.println("Evento en Centro de Sustos [" + id + "]: " + mensaje);
    }

    // Método para inicializar y limpiar recursos
    public void inicializar() {
        // Implementar lógica de inicialización si es necesario
        registrarEvento("Centro de Sustos inicializado");
    }

    public void limpiar() {
        // Implementar lógica de limpieza si es necesario
        registrarEvento("Centro de Sustos limpiado");
    }
}
