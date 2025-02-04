import java.util.Random;

class Assistent extends Thread {
    private final Esdeveniment event; // Referència a l'esdeveniment
    private final String nom; // Nom de l'assistent
    private final Random random = new Random(); // Generador de nombres aleatoris

    public Assistent(String nom, Esdeveniment event) {
        this.nom = nom;
        this.event = event;
    }

    @Override
    public void run() {
        while (true) {
            try {
                if (random.nextBoolean()) {
                    event.ferReserva(this); // Intenta reservar plaça
                } else {
                    event.cancelaReserva(this); // Intenta cancel·lar una reserva
                }

                // Espera un temps aleatori entre 0 i 1000 ms
                Thread.sleep(random.nextInt(1000));
            } catch (InterruptedException e) {
                System.out.println(nom + " ha estat interromput.");
                break; // Interromp l'execució del fil
            }
        }
    }

    public String getNom() {
        return nom;
    }
}