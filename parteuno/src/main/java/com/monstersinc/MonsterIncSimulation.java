package com.monstersinc;

import java.util.concurrent.*;

/**
 * Esta clase simula la actividad diaria de un grupo de monstruos en Monster Inc.
 * Cada monstruo va a la cafetería, luego al vestidor y, finalmente, al baño
 */
public class MonsterIncSimulation {

    public static void main(String[] args) {
        // Este bloque permite recibir el número de monstruos como argumento al ejecutar el programa.
        // Validar que se reciba el número de monstruos como argumento
//        if(args.length != 1) {
//           System.out.println("Uso: MonsterIncSimulation <numero de monstruos>");
//            return;
//        }
        //int numMonstruos = Integer.parseInt(args[0]);


        // Por simplicidad, se establece el número de monstruos a 12 para esta simulación.
        int numMonstruos = 12;

        // Creación de las distintas zonas de la empresa
        // La cafetería tiene un número limitado de mesas y sillas, definido por los argumentos.
        CafeteriaMediator cafeteria = new CafeteriaMediator(5, 5, 3, 100); // Ejemplo de parámetros

        // El vestidor es un singleton lo que significa que solo hay un vestidor en la simulación.
        Vestidor vestidor = Vestidor.getInstance();

        // Se inicializa el pool de hilos con capacidad para ejecutar las actividades de los monstruos en paralelo.
        ExecutorService executor = Executors.newFixedThreadPool(numMonstruos);

        // Simulación de la actividad de cada monstruo
        for(int i = 0; i < numMonstruos; i++) {
            // Creación del monstruo ESPECIAL con atributos específicos.
            Monstruo monstruo = new Monstruo("Monstruo" + i, "Grande", "Azul", 25, i, "password" + i);


            // Ejecución de hilos concurrentes usando executor
            // la lambda está Representando el ciclo de vida de los monstruos
            //
            executor.execute(() -> {
                try {
                    // Ir a la cafetería

                    // El monstruo busca una mesa para dos en la cafetería.
                    cafeteria.getMesa(2); // Por simplicidad, todos quieren mesa para 2
                    System.out.println(monstruo.getNombre() + " está comiendo...");


                    // Es la manera más simple que encontré de simular que algo está tomando tiempo
                    Thread.sleep(2000); // Simulación de tiempo comiendo
                    cafeteria.releaseMesa(2);  // El monstruo libera la mesa después de comer.

                    // Ir al vestidor
                    vestidor.usarCasillero(monstruo.getNumeroCasillero());
                    System.out.println(monstruo.getNombre() + " está en el vestidor...");
                    // Es la manera más simple que encontré de simular que algo está tomando tiempo
                    Thread.sleep(1000); // Simulación de tiempo en el vestidor

                    // Ir al baño
                    Bano bano = BanoFactory.createBano(monstruo.getTipo());
                    bano.usar();
                    System.out.println(monstruo.getNombre() + " está en el baño...");
                    // Es la manera más simple que encontré de simular que algo está tomando tiempo
                    Thread.sleep(1500); // Simulación de tiempo en el baño
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        // Quién es el proceso de cierre del Executor Service Lo que significa que no se aceptan más tareas
        executor.shutdown();
        try {
            // Espera que todas las tareas se completen o que pase una hora
            executor.awaitTermination(1, TimeUnit.HOURS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
