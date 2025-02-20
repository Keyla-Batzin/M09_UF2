public class Taula {
    public static Filosof[] comensals;
    public static Forquilla[] forquilles;

    public Taula(int numFilo) {
        comensals = new Filosof[numFilo];
        forquilles = new Forquilla[numFilo];

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

    public static void showTaula() {
        for (int i = 0; i < comensals.length; i++) {
            System.out.println("Comensal: fil" + comensals[i].id +
                    " esq:" + comensals[i].forquillaEsquerra.getIdForquilla() +
                    " dret:" + comensals[i].forquillaDreta.getIdForquilla());
        }
        System.out.println("--------------------------------------------------");
    }

    public static void cridaATaula() {
        for (Filosof filo : comensals) {
            filo.start();
        }
    }

    public static void main(String[] args) {
        Taula taula = new Taula(4); 
        showTaula();
        cridaATaula();
    }
}
