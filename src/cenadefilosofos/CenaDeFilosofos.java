package cenadefilosofos;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CenaDeFilosofos {

    // Número de filósofos en la mesa
    private static final int NUM_FILOSOFOS = 10;
    private static final int SIMULATION_MILLIS = 1000 * 10;
    
    
    public static void main(String[] args) throws InterruptedException {
    // Para manejar los hilos
    ExecutorService executorService = null;
    // Arreglo de filósofos
    Filosofo[] filosofos = null;
    try {
      // Asigno tamaño al arreglo de filósofos
      filosofos = new Filosofo[NUM_FILOSOFOS];

      // Tantos palillos como filósofos
      Palillo[] palillos = new Palillo[NUM_FILOSOFOS];
      
      for (int i = 0; i < NUM_FILOSOFOS; i++) {
        palillos[i] = new Palillo(i);
      }

      executorService = Executors.newFixedThreadPool(NUM_FILOSOFOS);

      for (int i = 0; i < NUM_FILOSOFOS; i++) {
        filosofos[i] = new Filosofo(i, palillos[i], palillos[(i + 1) % NUM_FILOSOFOS]);
        executorService.execute(filosofos[i]);
      }
      // Main thread sleeps till time of simulation
      Thread.sleep(SIMULATION_MILLIS);
      // Stop all philosophers.
      for (Filosofo philosopher : filosofos) {
        philosopher.estaLleno = true;
      }

    } finally {
      // Close everything down.
      executorService.shutdown();

      // Wait for all thread to finish
      while (!executorService.isTerminated()) {
        Thread.sleep(1000);
      }

      // Time for check
      for (Filosofo filosofo : filosofos) {
        System.out.println(filosofo + " => No of Turns to Eat ="
                + filosofo.getNumTurnosDeComida());
      }
    }
  }
}

