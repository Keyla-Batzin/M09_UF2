import java.util.LinkedList;
import java.util.Queue;

public class Barberia extends Thread {
    private Queue<Client> cola = new LinkedList<>(); // Cua de Clients
    private int numCadires;
    private static final Object condBarber = new Object();
    private static Barberia barberia;

    public Barberia(int numCadires) {
        this.numCadires = numCadires;
    }

    public static Client seguentClient(Client c) {
        // Devuelve el Cliente que toca. Si no hay nadie devuelve null
        return c;
    }

    public void entraClient(Client c) {
        synchronized(condBarber){}
        while (cola.size() > 0) {
            seguentClient(cola.poll()); // Elimina y devuelve el primer elemento de la cola
        }
    }

    @Override
    public void run() {
        int id = 0;
        try {
            for (int i = 0; i < 2; i++) {
                for (int y = 0; y < 10; y++) {
                    id++;
                    entraClient(new Client(id));
                    Thread.sleep(500); // Espera 5,0s
                }
                Thread.sleep(10000); // Espera 10s
            }
        } catch (Exception e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }

    public static void main(String[] args) {
        Barberia barberia = new Barberia(3);
        Barber barber = new Barber("Pepe");
        
    }
}
