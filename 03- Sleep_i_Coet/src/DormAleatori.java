import java.util.Random;

public class DormAleatori extends Thread {
    private final long tempsInici;

    // Constructor que guarda el tiempo de creación y asigna un nombre al hilo
    public DormAleatori(String nom) {
        super(nom); // Asigna el nombre al hilo
        this.tempsInici = System.currentTimeMillis(); // Guarda el instante de creación
    }

    @Override
    public void run() {
        Random random = new Random(); // Generador de números aleatorios

        for (int i = 0; i < 10; i++) {
            int intervalAleatori = random.nextInt(1000); // Intervalo entre 0 y 999 ms
            long tempsTranscorregut = System.currentTimeMillis() - tempsInici; // Calcula el tiempo total

            // Muestra la información
            System.out.printf("%s(%d) a dormir %dms total %dms%n", 
                getName(), i, intervalAleatori, tempsTranscorregut);

            try {
                Thread.sleep(intervalAleatori); // Pausa el hilo
            } catch (InterruptedException e) {
                System.err.println("El hilo ha sido interrumpido.");
            }
        }
    }

    public static void main(String[] args) {
        DormAleatori joan = new DormAleatori("Joan");
        DormAleatori pep = new DormAleatori("Pep");

        joan.start(); // Inicia el hilo "Joan"
        pep.start(); // Inicia el hilo "Pep"

        System.out.println("-- Fi de main -----------");
    }
}
