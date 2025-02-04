import java.util.ArrayList;
import java.util.List;

class Esdeveniment {
    private final List<Assistent> assistents; // Llista d'assistents amb reserva
    private int placesDisponibles; // Nombre de places lliures

    public Esdeveniment(int placesMaximes) {
        this.placesDisponibles = placesMaximes;
        this.assistents = new ArrayList<>();
    }

    public synchronized void ferReserva(Assistent assistent) throws InterruptedException {
        while (placesDisponibles <= 0) {
            wait(); // Espera fins que hi hagi una plaça lliure
        }
        assistents.add(assistent); // Afegeix l'assistent a la llista
        placesDisponibles--; // Redueix el nombre de places disponibles
        System.out.println(assistent.getNom() + " ha fet una reserva. Places disponibles: " + placesDisponibles);
        notifyAll(); // Notifica a altres fils en espera
    }

    public synchronized void cancelaReserva(Assistent assistent) {
        if (assistents.remove(assistent)) { // Si l'assistent tenia una reserva
            placesDisponibles++; // Incrementa el nombre de places disponibles
            System.out.println(assistent.getNom() + " ha cancel·lat una reserva. Places disponibles: " + placesDisponibles);
            notifyAll(); // Notifica a altres fils que una plaça està lliure
        } else {
            System.out.println(assistent.getNom() + " no ha pogut cancel·lar una reserva inexistent. Places disponibles: " + placesDisponibles);
        }
    }
}