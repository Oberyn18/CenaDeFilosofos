package cenadefilosofos;

import java.util.Random;

public class Filosofo implements Runnable{
    // Identificador del filósofo.
    private final int id;
    // Cada uno de los palillos a sus costados
    private final Palillo palilloIzquierdo;
    private final Palillo palilloDerecho;
    // Indica si el filósofo ya se llenó (no está hambriento)
    // La variable es volátil ya que va a ser manejada por múltiples hilos
    volatile boolean estaLleno = false;
    // Esta variable hará aleatorio el tiempo de comer y pensar en los filósofos
    private Random randomGenerator = new Random();
    // Indica el número de veces que se le permitió comer al filósofo
    private int numTurnosDeComida = 0;

    
    public Filosofo(int id, Palillo palilloIzquierdo, Palillo palilloDerecho) {
      this.id = id;
      this.palilloIzquierdo = palilloIzquierdo;
      this.palilloDerecho = palilloDerecho;
    }

    @Override
    public void run() {

      try {
        // Cada filósofo deberá buscar comer mientras no esté lleno aún.
        while (!estaLleno) {
          // Filosofa (piensa) un poco
          filosofar();
          // Primero intenta  tomar el palillo izquierdo
          if (palilloIzquierdo.recogerPalillo(this, "izquierdo")) {
              // Intenta tomar el palillo derecho
            if (palilloDerecho.recogerPalillo(this, "derecho")) {
              // Empieza a comer
              comer();
              // Y cuando termina deja el palillo
              palilloDerecho.dejarPalillo(this, "derecho");
            }
            // Deja el palillo izquierdo y finaliza
            palilloIzquierdo.dejarPalillo(this, "izquierdo");
          }
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    }

    private void filosofar() throws InterruptedException {
      System.out.println(this + " esta filosofando");
      // Hacemos que el hilo duerma un cantidad aleatoria de ms no mayor a 1000
      Thread.sleep(randomGenerator.nextInt(1000));
    }

    private void comer() throws InterruptedException {
      System.out.println(this + " esta comiendo.");
      // Aumenta el número de turnos de comida de este filosofo
      numTurnosDeComida++;
      // Hacemos que el hilo duerma un cantidad aleatoria de ms no mayor a 1000
      Thread.sleep(randomGenerator.nextInt(1000));
    }

    public int getNumTurnosDeComida() {
      return numTurnosDeComida;
    }

    @Override
    public String toString() {
      return "Filosofo-" + id;
    }

    public Palillo getPalilloIzquierdo() {
        return palilloIzquierdo;
    }

    public Palillo getPalilloDerecho() {
        return palilloDerecho;
    }

    public int getId() {
        return id;
    }
    
    
}
