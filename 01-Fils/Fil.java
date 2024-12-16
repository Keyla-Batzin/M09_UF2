/*  */

public class Fil extends Thread {
    private String nom; // Nom del fil

    public Fil(String nom) {
        this.nom = nom; // Inicialitza el nom
    }

    @Override
    public void run() {
        try {
            for (int i = 1; i <= 9; i++) {
                System.out.println(nom + " " + i);
                Thread.sleep(100); // Pausa fixa per a més control
            }
        } catch (InterruptedException e) {
            System.out.println("El fil " + nom + " ha estat interromput.");
        }
    }
}