package cenadefilosofos;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Palillo {
    // Lock me garantiza el acceso único a un recurso compatido en multihilos
    /* 
        ReentrantLock me indica que solo puedes bloquear el recurso compartido mientras
        ningún otro hilo posea dicho recurso        
    */
    
    // Con esto nos aseguramos que solo un filósofo pueda tener cada objeto palillo a la vez
    Lock up = new ReentrantLock();
    // Identificador del palillo.
    private final int id;

    public Palillo(int id) {
      this.id = id;
    }

    public boolean recogerPalillo(Filosofo filo, String lado) throws InterruptedException {
      // EL palillo puede ser tomado solo sí en los ultimos 10 ms éste no ha sido tomado por nadie.
      if (up.tryLock(10, TimeUnit.MILLISECONDS)) {
        System.out.println(filo + " recogió el " + this + " que se encuentra a su lado" + lado);
        return true;
      }
      return false;
    }

    public void dejarPalillo(Filosofo filo, String lado) {
      up.unlock();
      System.out.println(filo + " dejó el " + this + " " + lado);
    }

    @Override
    public String toString() {
      return "Palillo-" + id;
    }

    public int getId() {
        return id;
    }
    
    
}
