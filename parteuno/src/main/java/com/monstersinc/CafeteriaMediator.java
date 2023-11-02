package com.monstersinc;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CafeteriaMediator {
    private final Semaphore mesas2;
    private final Semaphore mesas4;
    private final Semaphore mesas6;
    private final Lock chefLock;
    private final Lock ingredientLock;
    private int ingredientCount;

    public CafeteriaMediator(int numMesas2, int numMesas4, int numMesas6, int initialIngredients) {
        mesas2 = new Semaphore(numMesas2);
        mesas4 = new Semaphore(numMesas4);
        mesas6 = new Semaphore(numMesas6);
        chefLock = new ReentrantLock();
        ingredientLock = new ReentrantLock();
        ingredientCount = initialIngredients;
    }

    public void getMesa(int numMonstruos) throws InterruptedException {
        switch(numMonstruos) {
            case 2:
                mesas2.acquire();
                break;
            case 4:
                mesas4.acquire();
                break;
            case 6:
                mesas6.acquire();
                break;
        }
    }

    public void releaseMesa(int numMonstruos) {
        switch(numMonstruos) {
            case 2:
                mesas2.release();
                break;
            case 4:
                mesas4.release();
                break;
            case 6:
                mesas6.release();
                break;
        }
    }

    public void cook() throws InterruptedException {
        chefLock.lock();
        try {
            // Cocinar aquí
        } finally {
            chefLock.unlock();
        }
    }

    public boolean useIngredients(int required) {
        ingredientLock.lock();
        try {
            if (ingredientCount < required) {
                return false;
            } else {
                ingredientCount -= required;
                return true;
            }
        } finally {
            ingredientLock.unlock();
        }
    }
}
