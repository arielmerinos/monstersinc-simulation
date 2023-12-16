package com.monstersinc;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Clase que representa un almacén de tanques.
 */
public class AlmacenDeTanques {

    private final Map<String, Tanque> almacen;
    private final Lock lockAlmacen = new ReentrantLock();

    /**
     * Constructor de la clase AlmacenDeTanques.
     * Inicializa el almacén como un ConcurrentHashMap vacío.
     */
    public AlmacenDeTanques() {
        this.almacen = new ConcurrentHashMap<>();
    }

    /**
     * Agrega un tanque al almacén.
     *
     * @param tanque el tanque a agregar
     */
    public void agregarTanque(Tanque tanque) {
        lockAlmacen.lock();
        try {
            almacen.put(tanque.getId(), tanque);
        } finally {
            lockAlmacen.unlock();
        }
    }

    /**
     * Obtiene un tanque del almacén utilizando su identificador.
     *
     * @param id el identificador del tanque a obtener
     * @return el tanque correspondiente al identificador especificado
     */
    public Tanque obtenerTanque(String id) {
        lockAlmacen.lock();
        try {
            return almacen.get(id);
        } finally {
            lockAlmacen.unlock();
        }
    }

    /**
     * Actualiza el estado de un tanque en el almacén.
     *
     * @param id          el identificador del tanque a actualizar
     * @param nuevoEstado el nuevo estado del tanque
     */
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
