public class Taula {
    public static Filosof[] comensals;
    public static Forquilla[] forquilles;

    // Constructor
    public Taula(int numFilo) {
        comensals = new Filosof[numFilo];  // Inicializar el arreglo de filósofos
        forquilles = new Forquilla[numFilo];  // Inicializar el arreglo de forquilles

        // Crear forquilles
        for (int i = 0; i < numFilo; i++) {
            forquilles[i] = new Forquilla(i);
        }

        // Crear filósofos y asignar forquilles
        for (int i = 0; i < numFilo; i++) {
            Forquilla esquerra = forquilles[i];
            Forquilla dreta = forquilles[(i + 1) % numFilo];  // Forquilla derecha (circular)
            comensals[i] = new Filosof(i, esquerra, dreta);
        }
    }


    public static void showTaula(){
        for(int i=0; i < comensals.length; i++){
            System.out.println("Comensal:" + comensals[i].getName() + " esq:" + comensals[i].forquillaEsquerra + " dret:" + comensals[i].forquillaDreta);
        }
    }

    public static void cridaATaula(){
        for(Filosof filo : comensals){
            filo.start();
        }
    }

    public static void main(String[] args) {
        Taula taula = new Taula(4);
        cridaATaula();
        showTaula();
    }
}
