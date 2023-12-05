package com.monstersinc;

import java.util.concurrent.locks.ReentrantLock;

public class CentroDeRisas {
    // Identificador único
    private final String id;

    // Variables para manejar los diferentes tipos de tanques
    private int numeroDeMaxitanques;
    private int numeroDeUltratanques;
    private final int capacidadMaximaMaxitanques;
    private final int capacidadMaximaUltratanques;
    private String estado;

    // Lock para manejar la concurrencia
    private final ReentrantLock lock = new ReentrantLock();

    // Constructor
    public CentroDeRisas(String id, int capacidadMaximaMaxitanques, int capacidadMaximaUltratanques) {
        this.id = id;
        this.capacidadMaximaMaxitanques = capacidadMaximaMaxitanques;
        this.capacidadMaximaUltratanques = capacidadMaximaUltratanques;
        this.numeroDeMaxitanques = capacidadMaximaMaxitanques;
        this.numeroDeUltratanques = capacidadMaximaUltratanques;
        this.estado = "Activo";
    }

    // Métodos de estado
    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    // Método para simular generar risa y almacenar energía
    public void generarRisaYAlmacenarEnergia() {
        lock.lock();
        try {
            if (numeroDeMaxitanques > 0) {
                // Lógica para generar risa y almacenar energía en maxitanques
                registrarEvento("Energía de risa almacenada en maxitanque");
                numeroDeMaxitanques--;
            } else if (numeroDeUltratanques > 0) {
                // Lógica para generar risa y almacenar energía en ultratanques
                registrarEvento("Energía de risa almacenada en ultratanque");
                numeroDeUltratanques--;
            } else {
                setEstado("Necesita Recarga");
                registrarEvento("Tanques agotados, necesita recarga");
            }
        } finally {
            lock.unlock();
        }
    }

    // Método para recargar tanques
    public void recargarTanques() {
        lock.lock();
        try {
            numeroDeMaxitanques = capacidadMaximaMaxitanques;
            numeroDeUltratanques = capacidadMaximaUltratanques;
            setEstado("Activo");
            registrarEvento("Tanques recargados");
        } finally {
            lock.unlock();
        }
    }

    // Método para registrar eventos
    private void registrarEvento(String mensaje) {
        // Implementar lógica de registro de eventos, por ejemplo, escribir en un archivo de log
        System.out.println("Evento en Centro de Risas [" + id + "]: " + mensaje);
    }

    // Métodos para inicializar y limpiar recursos
    public void inicializar() {
        // Implementar lógica de inicialización si es necesario
        registrarEvento("Centro de Risas inicializado");
    }

    public void limpiar() {
        // Implementar lógica de limpieza si es necesario
        registrarEvento("Centro de Risas limpiado");
    }

    // Método adicional para la estación especial de Mike Wazawsky
    public void estacionEspecialMikeWazawsky() {
        lock.lock();
        try {
            // Lógica para generar una super risa y almacenar en ultratanques o Gigatanques
            registrarEvento("Super risa generada por Mike Wazawsky");
            // Decrementar el número de tanques correspondiente
        } finally {
            lock.unlock();
        }
    }
}
