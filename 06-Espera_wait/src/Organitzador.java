import java.util.ArrayList;
import java.util.List;

public class Organitzador {
    public static void main(String[] args) {
        // Crea un esdeveniment amb 10 places disponibles
        Esdeveniment event = new Esdeveniment(10);

        // Crea una llista per emmagatzemar els assistents
        List<Assistent> assistents = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            // Afegeix 10 assistents amb noms únics a la llista
            assistents.add(new Assistent("Assistent-" + i, event));
        }

        // Inicia tots els assistents
        for (Assistent ass : assistents) {
            ass.start();
        }
    }
}