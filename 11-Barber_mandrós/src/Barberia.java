import java.util.LinkedList;
import java.util.Queue;

public class Barberia extends Thread {
    private final Queue<Client> salaEspera = new LinkedList<>();
    private final int numCadires;
    public static final Object condBarber = new Object();
    public static Barberia barberia;

    public Barberia(int numCadires) {
        this.numCadires = numCadires;
        barberia = this;
    }

    public Client seguentClient() {
        synchronized (condBarber) {
            return salaEspera.poll();
        }
    }

    public void entraClient(Client client) {
        synchronized (condBarber) {
            if (salaEspera.size() < numCadires) {
                salaEspera.offer(client);
                System.out.println("Client " + client.getNom() + " en espera");
                condBarber.notify();
            } else {
                System.out.println("No queden cadires, client " + client.getNom() + " se'n va");
            }
        }
    }

    @Override
    public void run() {
        try {
            for (int i = 1; i <= 10; i++) {
                entraClient(new Client(i));
                Thread.sleep(500);
            }

            Thread.sleep(10000);

            for (int i = 11; i <= 20; i++) {
                entraClient(new Client(i));
                Thread.sleep(500);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static void main(String[] args) {
        Barberia barberia = new Barberia(3);
        Barber barber = new Barber("Pepe");

        barber.start();
        barberia.start();
    }
}
