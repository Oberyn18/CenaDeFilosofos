package cenadefilosofos;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CenaDeFilosofos {

    // Número de filósofos en la mesa
    private static final int NUM_FILOSOFOS = 10;
    private static final int MILISEGUNDOS = 1000 * 10;
    
    
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
      
      // Creamos los distintos objetos palillos
      for (int i = 0; i < NUM_FILOSOFOS; i++) {
        palillos[i] = new Palillo(i);
      }

      // Crea un conjunto de hilos, un hilo por filósofo.
      executorService = Executors.newFixedThreadPool(NUM_FILOSOFOS);

      // Crea cada filósofo con los palillos de sus costados correspondientes.
      for (int i = 0; i < NUM_FILOSOFOS; i++) {
        filosofos[i] = new Filosofo(i, palillos[i], palillos[(i + 1) % NUM_FILOSOFOS]);
          System.out.println("El filosofo " + filosofos[i].getId() + " tiene como palillos: ");
          System.out.println("   Palillo-" + filosofos[i].getPalilloDerecho().getId() + " <-- derecho");
          System.out.println("   Palillo-" + filosofos[i].getPalilloIzquierdo().getId() + " <-- izquierdo");
      }
      
        System.out.println("");
        System.out.println("");
      
      // Además, asigna el filosofo a su hilo correspondiente y lo pone a ejecutar.
      for (int i = 0; i < NUM_FILOSOFOS; i++) {
        executorService.execute(filosofos[i]);
      }
      
      // El hilo principal duerme luego de la simulación
      Thread.sleep(MILISEGUNDOS);
      // Detenemos a todos los filósofos que se van ejecutando haciendo que estén lleno.
      for (Filosofo philosopher : filosofos) {
        philosopher.estaLleno = true;
      }

    } finally {
      // Acabamos todos los hilos.
      executorService.shutdown();

      // Esperamos a que todos los hilos hayan acabado
      while (!executorService.isTerminated()) {
        Thread.sleep(1000);
      }
      
        System.out.println("");
        System.out.println("");
      
      // Muestra cuanto se alimentó cada filósofo finalmente
      for (Filosofo filosofo : filosofos) {
        System.out.println("El " + filosofo + "  tuvo " 
                + filosofo.getNumTurnosDeComida() + " turnos para comer.");
      }
    }
  }
}