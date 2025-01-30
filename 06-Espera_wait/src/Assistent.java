import java.util.Random;

class Assistent extends Thread {
    private final Esdeveniment event; // Referencia al evento donde se registrará
    private final String nom; // Nombre del asistente
    private final Random random = new Random(); // Números aleatorios

    public Assistent(String nom, Esdeveniment event) {
        this.nom = nom; // Asigna el nombre del asistente
        this.event = event; // Asigna el evento al que pertenece
    }

    @Override
    public void run() {
        while (true) { // Bucle infinito
            try {
                if (random.nextBoolean()) { // 50% de probabilidad de reservar o cancelar
                    event.ferReserva(this); // Intenta hacer una reserva
                } else {
                    event.cancelaReserva(this); // Intenta cancelar su reserva
                }

                // Espera un tiempo aleatorio entre 0 y 1000 ms antes de la siguiente acción
                Thread.sleep(random.nextInt(1000));

            } catch (InterruptedException e) {
                System.out.println(nom + " ha estat interromput."); // Mensaje si el hilo es interrumpido
                break; // Sale del bucle si el hilo es interrumpido
            }
        }
    }

    public String getNom() {
        return nom; // Devuelve el nombre del asistente
    }
}