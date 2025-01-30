import java.util.ArrayList;
import java.util.List;

class Esdeveniment extends Thread {
    private final List<Assistent> assistents; // Lista de asistentes con reserva
    private int placesDisponibles; // Número de plazas disponibles en el evento

    public Esdeveniment(int placesMaximes) {
        this.placesDisponibles = placesMaximes; // Establece el número máximo de plazas
        this.assistents = new ArrayList<>(); // Inicializa la lista de asistentes
    }

    public synchronized void ferReserva(Assistent assistent) throws InterruptedException {
        while (placesDisponibles <= 0) { // Si no hay plazas disponibles, espera
            wait();
        }
        assistents.add(assistent); // Añade el asistente a la lista de reservas
        placesDisponibles--; // Reduce el número de plazas disponibles
        System.out.println(assistent.getNom() + " ha reservat. Places disponibles: " + placesDisponibles);
        notifyAll(); // Notifica a otros hilos que una plaza ha cambiado de estado
    }

    public synchronized void cancelaReserva(Assistent assistent) {
        if (assistents.remove(assistent)) { // Si el asistente tenía reserva, se elimina
            if (placesDisponibles == 0) { // Si no había plazas disponibles antes de la cancelación
                System.out.println(assistent.getNom() + " no ha pogut cancel·lar una reserva inexistent. Places disponibles: " + placesDisponibles);
            } else {
                System.out.println(assistent.getNom() + " ha cancel·lat. Places disponibles: " + placesDisponibles);
            }
            placesDisponibles++; // Libera una plaza
            notifyAll(); // Notifica a otros hilos que hay una plaza disponible
        }
    }
}