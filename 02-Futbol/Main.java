public class Main {
    public static void main(String[] args) {
        System.out.println("Inici dels xuts --------------------");

        // Noms dels jugadors
        String[] noms = { "Piqué", "Vinicius", "Torres", "Ramos", "Ronaldo", 
                          "Lewan", "Belli", "Arnau", "Aspas", "Messi", "MBapé" };

        // Crear els jugadors (fils)
        Futbolista[] jugadors = new Futbolista[Futbolista.NUM_JUGADORS];

        // Inicialitzar cada jugador
        for (int i = 0; i < Futbolista.NUM_JUGADORS; i++) {
            jugadors[i] = new Futbolista(); // Crear un nou jugador
        }

        // Iniciar tots els fils
        for (Futbolista jugador : jugadors) {
            jugador.start(); // Iniciar el fil
        }

        // Esperar que tots els fils acabin
        for (Futbolista jugador : jugadors) {
            try {
                jugador.join(); // Esperar que acabi
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Fi dels xuts -----------------------");

        // Mostrar estadístiques
        System.out.println("--- Estadístiques ------");
        for (int i = 0; i < Futbolista.NUM_JUGADORS; i++) {
            System.out.println(noms[i] + " -> " + jugadors[i].getNgols() + " gols");
        }
    }
}
