import java.util.ArrayList;

public class Barri {
    private Estanc estanc; // Estanco del barrio
    private ArrayList<Fumador> fumadors; // Lista de fumadores

    // Constructor
    public Barri() {
        estanc = new Estanc(); // Inicializa el estanco
        fumadors = new ArrayList<>(); // Inicializa la lista de fumadores
    }

    // Método para iniciar los fumadores
    public void inicaFumadors() {
        // Crea 3 fumadores y los añade a la lista
        for (int i = 0; i < 3; i++) {
            fumadors.add(new Fumador(estanc, i));
        }

        // Inicia los hilos de los fumadores
        for (Fumador fum : fumadors) {
            fum.start();
        }
    }

    // Método para esperar a que los fumadores terminen
    public void esperaFumadors() {
        for (Fumador fum : fumadors) {
            try {
                fum.join(); // Espera a que el fumador termine
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // Método principal
    public static void main(String[] args) {
        Barri barri = new Barri(); // Crea el barrio
        barri.inicaFumadors(); // Inicia los fumadores
        barri.esperaFumadors(); // Espera a que los fumadores terminen
        barri.estanc.tancarEstanc(); // Cierra el estanco
    }
}