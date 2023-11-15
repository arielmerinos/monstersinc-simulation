package com.monstersinc;

import java.rmi.server.RemoteStub;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Vestidor {
    private static final Vestidor instance = new Vestidor();
    private final Lock[] lockers;
    private final Map<Integer, String> lockerPasswords;


    private Vestidor() {
        lockers = new ReentrantLock[100];
        lockerPasswords = new HashMap<>();  // Asumamos que hay 100 casilleros y cada casillero lo vamos a proteger con un ReentrantLock

        for (int i = 0; i < 100; i++) {
            lockers[i] = new ReentrantLock();
            lockerPasswords.put(i, "password" + i);
        }
    }

    public static Vestidor getInstance() {
        return instance;
    }

    public boolean verifyAcces(int lockerNumber, String password){
        if( lockerNumber < 0 || lockerNumber >= lockers.length){
            return false;
        }

        String storedPassword = lockerPasswords.get(lockerNumber);
        return storedPassword.equals(password);
    }

    public void usarCasillero(int lockerNumber) throws InterruptedException {
        lockers[lockerNumber].lock();
        try {
            // Dejan sus cosas personales
            // Se ponen olorante
            // Only they can use the locker with their password 
            System.out.println("Usando casillero " + lockerNumber);
            Thread.sleep(1000); // Simulación de tiempo en el vestidor

            // Se ponen su olorante
            System.out.println("Poniendose olorante " + lockerNumber);
            Thread.sleep(1000); // Simulación de tiempo en el vestidor

            // they might take some time to do this
            System.out.println("Dejando sus cosas personales y poniéndose el casco " + lockerNumber);
            Thread.sleep(1000); // Simulación de tiempo en el vestidor
        } finally {
            lockers[lockerNumber].unlock();
        }
    }
}
