import java.util.concurrent.locks.ReentrantLock;

public class Forquilla {
    private int idForquilla;
    private final ReentrantLock bloqueig = new ReentrantLock();

    public Forquilla() {
        this.idForquilla = 0;
    }

    public Forquilla(int id) {
        this.idForquilla = id;
    }

    public int getIdForquilla(){
        return idForquilla;
    }

    public void agafa(){
        bloqueig.lock();
    }

    public void deixa() {
        // Verifica si el hilo actual tiene el lock antes de liberarlo
        if (bloqueig.isHeldByCurrentThread()) {
            bloqueig.unlock(); // Libera el tenedor
        } else {
            System.out.println("Error: El filòsof " + Thread.currentThread().getName() + " intenta deixar una forquilla que no té.");
        }
    }
}
