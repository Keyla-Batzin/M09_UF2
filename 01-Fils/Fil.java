
class Fil extends Thread {
    private final String nom;     // Nom del fil
    private final Object lock;    // Objecte de bloqueig per sincronitzar
    private static boolean torn;  // Booleà per indicar quin fil té el torn
    private final boolean meuTorn; // Defineix si aquest fil inicia amb el torn

    public Fil(String nom, Object lock, boolean meuTorn) {
        this.nom = nom;
        this.lock = lock;
        this.meuTorn = meuTorn;
    }

    @Override
    public void run() {
        synchronized (lock) { // Bloqueig sincronitzat
            for (int i = 1; i <= 9; i++) {
                while (torn != meuTorn) { // Espera si no és el seu torn
                    try {
                        lock.wait(); // Espera que l'altre fil el desperti
                    } catch (InterruptedException e) {
                        System.out.println("El fil " + nom + " ha estat interromput.");
                    }
                }
                System.out.println(nom + " " + i); // Imprimeix el missatge
                torn = !torn; // Canvia el torn
                lock.notifyAll(); // Desperta l'altre fil
            }
        }
    }
}