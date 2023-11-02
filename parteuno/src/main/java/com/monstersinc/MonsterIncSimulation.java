package com.monstersinc;

import java.util.concurrent.*;

public class MonsterIncSimulation {

    public static void main(String[] args) {
        // Validar que se reciba el número de monstruos como argumento
//        if(args.length != 1) {
//           System.out.println("Uso: MonsterIncSimulation <numero de monstruos>");
//            return;
//        }

        //int numMonstruos = Integer.parseInt(args[0]);
        int numMonstruos = 12;

        // Crear zonas de la empresa
        CafeteriaMediator cafeteria = new CafeteriaMediator(5, 5, 3, 100); // Ejemplo de parámetros
        Vestidor vestidor = Vestidor.getInstance();
        BanoFactory banoFactory = new BanoFactory();

        // Crear hilos para monstruos
        ExecutorService executor = Executors.newFixedThreadPool(numMonstruos);

        for(int i = 0; i < numMonstruos; i++) {
            Monstruo monstruo = new Monstruo("Monstruo" + i, "Grande", "Azul", 25, i, "password" + i);
            executor.execute(() -> {
                try {
                    // Ir a la cafetería
                    cafeteria.getMesa(2); // Por simplicidad, todos quieren mesa para 2
                    System.out.println(monstruo.getNombre() + " está comiendo...");
                    Thread.sleep(2000); // Simulación de tiempo comiendo
                    cafeteria.releaseMesa(2);

                    // Ir al vestidor
                    vestidor.usarCasillero(monstruo.getNumeroCasillero());
                    System.out.println(monstruo.getNombre() + " está en el vestidor...");
                    Thread.sleep(1000); // Simulación de tiempo en el vestidor

                    // Ir al baño
                    Bano bano = BanoFactory.createBano(monstruo.getTipo());
                    bano.usar();
                    System.out.println(monstruo.getNombre() + " está en el baño...");
                    Thread.sleep(1500); // Simulación de tiempo en el baño
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        executor.shutdown();
        try {
            executor.awaitTermination(1, TimeUnit.HOURS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
