public class Taula {
    public static Filosof[] comensals; // Array de filósofos
    public static Forquilla[] forquilles; // Array de tenedores

    // Constructor
    public Taula(int numFilo) {
        comensals = new Filosof[numFilo]; // Inicializar el arreglo de filósofos
        forquilles = new Forquilla[numFilo]; // Inicializar el arreglo de tenedores

        // Crear tenedores
        for (int i = 0; i < numFilo; i++) {
            forquilles[i] = new Forquilla(i);
        }

        // Crear filósofos y asignar tenedores
        for (int i = 0; i < numFilo; i++) {
            Forquilla esquerra = forquilles[i]; // Tenedor izquierdo
            Forquilla dreta = forquilles[(i + 1) % numFilo]; // Tenedor derecho (circular)
            comensals[i] = new Filosof(i, esquerra, dreta);
        }
    }

    // Método para mostrar la mesa
    public static void showTaula() {
        for (int i = 0; i < comensals.length; i++) {
            System.out.println("Comensal: fil" + comensals[i].id +
                    " esq:" + comensals[i].forquillaEsquerra.getIdForquilla() +
                    " dret:" + comensals[i].forquillaDreta.getIdForquilla());
        }
        System.out.println("--------------------------------------------------");
    }

    // Método para iniciar los hilos de los filósofos
    public static void cridaATaula() {
        for (Filosof filo : comensals) {
            filo.start();
        }
    }

    // Método principal
    public static void main(String[] args) {
        Taula taula = new Taula(4); // Crear una mesa con 4 filósofos
        showTaula(); // Mostrar la mesa
        cridaATaula(); // Iniciar los hilos de los filósofos
    }
}