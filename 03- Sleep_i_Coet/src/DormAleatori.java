import java.util.Random;

public class DormAleatori extends Thread {
    private final String nom;
    private final long temps; // Tiempo

    public DormAleatori(String nom) {
        this.nom = nom;
        temps = System.currentTimeMillis(); // Calcula el tiempo
    }

    @Override
    public void run() {
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            long temps_total = System.currentTimeMillis() - this.temps;
            long interval_aleatori = random.nextInt(1000);
            System.out.println(nom + "(" + i + ")" + "a dormir " + interval_aleatori + "ms total   " + temps_total);
            try {
                Thread.sleep(interval_aleatori);
            } catch (InterruptedException e) {
                System.out.println("El fil " + nom + " ha estat interromput.");
            }
        }
    }

    public static void main(String[] args) {
        DormAleatori instancia01 = new DormAleatori("Joan");
        DormAleatori instancia02 = new DormAleatori("Pep");

        instancia01.start();
        instancia02.start();

        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            System.out.println("El Thread principal ha estat interromput.");
        }
        System.out.println("-- Fi de main -----------");
    }
}