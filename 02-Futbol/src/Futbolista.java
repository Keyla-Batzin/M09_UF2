import java.util.Random;

public class Futbolista extends Thread {
    // Constants
    public static final int NUM_JUGADORS = 11;
    public static final int NUM_TIRADES = 20;
    public static final float PROBABILITAT = 0.5f;

    // Atributs per guardar els gols i tirades
    private int ngols;
    private int ntirades;

    // Constructor que inicialitza els valors
    public Futbolista() {
        this.ngols = 0;
        this.ntirades = 0;
    }

    // Mètode que executa la lògica principal del fil
    @Override
    public void run() {
        Random random = new Random();
        for (int i = 0; i < NUM_TIRADES; i++) {
            ntirades++; // Incrementar el nombre de tirades
            if (random.nextFloat() < PROBABILITAT) { // Probabilitat de marcar
                ngols++;
            }
        }
    }

    // Getters per accedir als valors de gols i tirades
    public int getNgols() {
        return ngols;
    }

    public int getNTirades() {
        return ntirades;
    }
}
