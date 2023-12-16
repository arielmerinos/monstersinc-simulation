package com.monstersinc;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * La clase Vestidor representa un vestuario con casilleros protegidos por cerraduras ReentrantLock.
 * Cada casillero tiene una contraseña asociada.
 */
public class Vestidor {
    private static final Vestidor instance = new Vestidor();
    private final Lock[] lockers;
    private final Map<Integer, String> lockerPasswords;


    /**
     * La clase Vestidor representa un vestuario con casilleros protegidos por cerraduras ReentrantLock.
     * Cada casillero tiene una contraseña asociada.
     */
    private Vestidor() {
        lockers = new ReentrantLock[100];
        lockerPasswords = new HashMap<>();  // Asumamos que hay 100 casilleros y cada casillero lo vamos a proteger con un ReentrantLock

        for (int i = 0; i < 100; i++) {
            lockers[i] = new ReentrantLock();
            lockerPasswords.put(i, "password" + i);
        }
    }

    /**
     * Devuelve la instancia única del vestidor.
     *
     * @return la instancia única del vestidor
     */
    public static Vestidor getInstance() {
        return instance;
    }

    /**
     * Verifica el acceso a un casillero específico.
     * 
     * @param lockerNumber el número del casillero a verificar
     * @param password la contraseña para acceder al casillero
     * @return true si el acceso es válido, false de lo contrario
     */
    public boolean verifyAcces(int lockerNumber, String password){
        if( lockerNumber < 0 || lockerNumber >= lockers.length){
            return false;
        }
        return password.equals(password);
    }

    /**
     * Método que permite a un monstruo usar un casillero en el vestidor.
     * 
     * @param lockerNumber el número del casillero que se desea usar
     * @param password la contraseña para acceder al casillero
     * @throws InterruptedException si ocurre un error durante la espera del hilo
     */
    public void usarCasillero(int lockerNumber, String password) throws InterruptedException {

        if (verifyAcces(lockerNumber, password)) {
            lockers[lockerNumber].lock();

            try {
                // Dejan sus cosas personales
                // Se ponen olorante
                // Only they can use the locker with their password 
                System.out.println("Usando casillero " + lockerNumber);
                Thread.sleep(100); // Simulación de tiempo en el vestidor

                // Se ponen su olorante
                System.out.println("Poniendose olorante " + lockerNumber);
                Thread.sleep(100); // Simulación de tiempo en el vestidor

                // they might take some time to do this
                System.out.println("Dejando sus cosas personales y poniéndose el casco " + lockerNumber);
                Thread.sleep(1000); // Simulación de tiempo en el vestidor
            } finally {
                lockers[lockerNumber].unlock();
            }
        } else {
            System.out.println("No se pudo acceder al casillero " + lockerNumber);
        }
    }

}
