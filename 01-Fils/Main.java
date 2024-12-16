/*  */

public class Main {
    public static void main(String[] args) {
        // Crear instàncies de fils
        Fil fil1 = new Fil("Juan");
        Fil fil2 = new Fil("Pepe");

        // Inicia primer el fil "Pepe"
        fil2.start();
        try {
            fil2.join(); // Espera que "Pepe" acabi completament
        } catch (InterruptedException e) {
            System.out.println("El fil Pepe ha estat interromput.");
        }

        // Inicia després el fil "Juan"
        fil1.start();
        try {
            fil1.join(); // Espera que "Juan" acabi completament
        } catch (InterruptedException e) {
            System.out.println("El fil Juan ha estat interromput.");
        }

        // Mostra els missatges finals
        System.out.println("Termina el fil Pepe");
        System.out.println("Termina el fil Juan");

        // Missatge de terminació del fil principal
        System.out.println("Termina thread main");
    }
}
