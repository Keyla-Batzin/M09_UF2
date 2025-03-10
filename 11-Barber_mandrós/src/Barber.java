public class Barber extends Thread {
    private final String nom;

    public Barber(String nom) {
        this.nom = nom;
    }

    @Override
    public void run() {
        while (true) {
            Client client;
            synchronized (Barberia.condBarber) {
                while ((client = Barberia.barberia.seguentClient()) == null) {
                    try {
                        System.out.println("Ningú en espera");
                        System.out.println("Barber " + nom + " dormint");
                        Barberia.condBarber.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        return;
                    }
                }
            }

            System.out.println("Li toca al client " + client.getNom());
            client.tallarSeElCabell();
            try {
                Thread.sleep(900 + (int) (Math.random() * 100));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
    }
}
