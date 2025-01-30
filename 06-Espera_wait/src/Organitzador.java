import java.util.ArrayList;
import java.util.List;

public class Organitzador {
    public static void main(String[] args) {
        // Crea un Esdeveniment con 5 plazas disponibles
        Esdeveniment event = new Esdeveniment(5);

        // Crea una lista para almacenar los asistentes
        List<Assistent> assistents = new ArrayList();
        
        // Genera 10 asistentes y los añade a la lista
        for (int i = 0; i < 10; i++) {
            assistents.add(new Assistent("Assistent-" + i, event));
        }

        // Inicia los hilos de los asistentes
        for (Assistent ass : assistents) {
            ass.start();
        }
    }
}