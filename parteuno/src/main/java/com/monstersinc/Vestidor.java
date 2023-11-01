package com.monstersinc;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Vestidor {
    private static final Vestidor instance = new Vestidor();
    private final Lock[] lockers;

    private Vestidor() {
        lockers = new ReentrantLock[100];  // Asumamos que hay 100 casilleros
        for (int i = 0; i < 100; i++) {
            lockers[i] = new ReentrantLock();
        }
    }

    public static Vestidor getInstance() {
        return instance;
    }

    public void usarCasillero(int lockerNumber) throws InterruptedException {
        lockers[lockerNumber].lock();
        try {
            // Interactuar con el casillero aquí
        } finally {
            lockers[lockerNumber].unlock();
        }
    }
}
