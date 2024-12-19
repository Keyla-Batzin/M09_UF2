import java.util.Random;

public class Futbolista extends Thread {
    // Constants
    public static final int NUM_JUGADORS = 11;
    public static final int NUM_TIRADES = 20;
    public static final float PROBABILITAT = 0.5f;

    // Atributs de cada jugador
    private int ngols;
    private int ntirades;

    // Constructor
    public Futbolista() {
        this.ngols = 0;
        this.ntirades = 0;
    }

    // Mètode run: lògica principal de cada jugador
    @Override
    public void run() {
        Random random = new Random();
        for (int i = 0; i < NUM_TIRADES; i++) {
            ntirades++;
            if (random.nextFloat() < PROBABILITAT) {
                ngols++;
            }
        }
    }

    // Getters per obtenir les estadístiques
    public int getNgols() {
        return ngols;
    }

    public int getNtirades() {
        return ntirades;
    }

    public static void main(String[] args) {
        System.out.println("Inici dels xuts --------------------");

        // Crear arrays per als fils i estadístiques
        Futbolista[] jugadors = new Futbolista[NUM_JUGADORS];
        int[] gols = new int[NUM_JUGADORS];
        int[] tirades = new int[NUM_JUGADORS];

        // Crear els fils
        for (int i = 0; i < NUM_JUGADORS; i++) {
            jugadors[i] = new Futbolista();
            jugadors[i].setName("Jugador " + (i + 1)); // Assignar nom al fil
        }

        // Iniciar els fils
        for (Futbolista jugador : jugadors) {
            jugador.start();
        }
    }
}
