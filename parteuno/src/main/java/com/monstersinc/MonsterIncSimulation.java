package com.monstersinc;

import java.util.Random;
import java.util.concurrent.*;

/**
 * Esta clase simula la actividad diaria de un grupo de monstruos en Monster Inc.
 * Cada monstruo va a la cafetería, luego al vestidor y, finalmente, al baño
 */
public class MonsterIncSimulation {

    // Método para asignar puertas a monstruos
    private static void asignarPuertaAMonstruo(Puerta puerta) {
        // Lógica para asignar la puerta a un monstruo
        // Por ejemplo, decidir si la puerta se usa para asustar o entretener, basado en el destino y si es evento especial
        String accion = puerta.destino.equals("Niño") ? "asustar" : "entretener";
        if (puerta.esEventoEspecial) {
            accion += " en un evento especial";
        }
        System.out.println("Puerta asignada para " + accion);
    }

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

        CentroDeSustos centroDeSustos = new CentroDeSustos("CentroSustos1", 10); // Ejemplo: capacidad de 10 contenedores

        CentroDeRisas centroDeRisas = new CentroDeRisas("CentroRisas1", 5, 3); // Ejemplo: 5 maxitanques y 3 ultratanques

        Random random = new Random();

        CentroDeReparacion centroDeReparacion = new CentroDeReparacion();
        FabricaDePuertas fabricaDePuertas = new FabricaDePuertas();

        // Crear y ejecutar una tarea para manejar puertas del almacén
        ExecutorService manejadorDePuertas = Executors.newSingleThreadExecutor();
        manejadorDePuertas.execute(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                if (fabricaDePuertas.cantidadPuertasEnAlmacen() > 0) {
                    Puerta puerta = fabricaDePuertas.obtenerPuertaDelAlmacen();
                    System.out.println("Manejando puerta del almacén: " + puerta);
                    asignarPuertaAMonstruo(puerta);
                    // Aquí puedes añadir lógica adicional si es necesario
                }
                try {
                    Thread.sleep(500); // Tiempo de espera antes de manejar la siguiente puerta
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });

        FabricaDeTanques fabricaDeTanques = new FabricaDeTanques();

        // Crear y ejecutar una tarea para manejar tanques del almacén
        ExecutorService manejadorDeTanques = Executors.newSingleThreadExecutor();
        manejadorDeTanques.execute(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                if (fabricaDeTanques.cantidadTanquesEnAlmacen() > 0) {
                    FabricaDeTanques.Tanque tanque = fabricaDeTanques.obtenerTanqueDelAlmacen();
                    System.out.println("Manejando tanque del almacén: " + tanque);
                    // Aquí puedes añadir lógica adicional si es necesario
                }
                try {
                    Thread.sleep(500); // Tiempo de espera antes de manejar el siguiente tanque
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });

        AlmacenDeTanques almacenDeTanques = new AlmacenDeTanques();
        RecolectorIndustrial recolector = new RecolectorIndustrial(10000); // Capacidad ejemplo de 10000 unidades de energía



        AlmacenDePuertas almacenDePuertas = new AlmacenDePuertas(numMonstruos);
        
        // Simulación de la actividad de cada monstruo
        for(int i = 0; i < numMonstruos; i++) {
            // Creación del monstruo ESPECIAL con atributos específicos.
            Monstruo monstruo = new Monstruo("Monstruo" + i, "Grande", "Azul", 25, i, "password" + i);


            // Crear y ejecutar una tarea para reparar elementos en un bucle continuo
            ExecutorService reparadores = Executors.newFixedThreadPool(2); // Ejemplo con 2 reparadores
            reparadores.execute(() -> {
                while (!Thread.currentThread().isInterrupted()) {
                    centroDeReparacion.repararItem();
                    try {
                        Thread.sleep(500); // Pequeña pausa entre reparaciones
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            });

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
                    vestidor.usarCasillero(monstruo.getNumeroCasillero(), monstruo.getContrasenaCasillero());
                    System.out.println(monstruo.getNombre() + " está en el vestidor...");

                    // Intentar usar un casillero al azar
                    int randomLocker = random.nextInt(100);
                    String randomPassword = "password" + random.nextInt(100);
                    System.out.println(monstruo.getNombre() + " intenta usar el casillero (might fail)" + randomLocker);
                    vestidor.usarCasillero(randomLocker, randomPassword);

                    // Es la manera más simple que encontré de simular que algo está tomando tiempo
                    Thread.sleep(1000); // Simulación de tiempo en el vestidor

                    // Ir al baño
                    Bano bano = BanoFactory.createBano(monstruo.getTipo());
                    bano.usar();
                    System.out.println(monstruo.getNombre() + " está en el baño...");
                    // Es la manera más simple que encontré de simular que algo está tomando tiempo
                    Thread.sleep(1500); // Simulación de tiempo en el baño


                    // Actividad en el Centro de Sustos
                    System.out.println(monstruo.getNombre() + " está en el Centro de Sustos...");
                    centroDeSustos.asustarYGenerarEnergia();

                    // Simulación de tiempo en el Centro de Sustos
                    Thread.sleep(1000); // Ajustar este tiempo según sea necesario

                    // Actividad en el Centro de Risas
                    System.out.println(monstruo.getNombre() + " está en el Centro de Risas...");
                    centroDeRisas.generarRisaYAlmacenarEnergia();

                    // Simulación de tiempo en el Centro de Risas
                    Thread.sleep(1000); // Ajustar este tiempo según sea necesario
                    
                    // Simular que un elemento se rompe y necesita reparación
                    CentroDeReparacion.ItemReparable item = centroDeReparacion.new ItemReparable("Tanque", 0);
                    item.usar(); // Simula el uso del elemento que potencialmente podría romperlo
                    if (item.necesitaReparacion) {
                        System.out.println(monstruo.getNombre() + " ha roto un tanque. Enviando a reparación...");
                        centroDeReparacion.agregarItemParaReparar(item);
                    }
                    // Fabricar un tanque
                    
                    FabricaDeTanques.TipoTanque tipoTanque = FabricaDeTanques.TipoTanque.ESTANDAR; // O cualquier lógica para elegir el tipo
                    FabricaDeTanques.Tanque nuevoTanque = fabricaDeTanques.fabricarTanque(tipoTanque);
                    System.out.println(Thread.currentThread().getName() + " ha fabricado un tanque: " + nuevoTanque);

                    // Simular la fabricación de una puerta
                    com.monstersinc.Puerta puertaFabricada = fabricaDePuertas.fabricarPuerta();
                    almacenDePuertas.agregarPuerta(puertaFabricada);

                    // Simular la asignación de una puerta y su uso
                    String idPuerta = puertaFabricada.getId(); // Obtener el ID de la puerta fabricada
                    Puerta puertaAsignada = almacenDePuertas.obtenerPuerta(idPuerta);
                    
                    if (puertaAsignada != null) {
                        almacenDePuertas.actualizarEstadoPuerta(idPuerta, EstadoPuerta.EN_USO);
                        almacenDePuertas.actualizarEstadoPuerta(idPuerta, EstadoPuerta.DISPONIBLE);
                    }

                    // Simular la recolección de energía de un tanque
                    Tanque tanqueParaVaciar = almacenDeTanques.obtenerTanque("ID"); // Obtener un tanque del almacén
                    if (tanqueParaVaciar != null && tanqueParaVaciar.estado == EstadoTanque.DISPONIBLE) {
                        recolector.vaciarTanque(tanqueParaVaciar);
                        System.out.println("Energía recolectada: " + recolector.getEnergiaAcumulada());
                    }

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
