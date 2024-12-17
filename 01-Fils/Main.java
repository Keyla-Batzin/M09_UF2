
public class Main {
    public static void main(String[] args) {
        Object lock = new Object(); // Objecte de bloqueig per sincronitzar els fils

        Fil filJuan = new Fil("Juan", lock, true); // El primer fil inicia actiu
        Fil filPepe = new Fil("Pepe", lock, false); // El segon fil inicia en espera

        System.out.println("Termina thread main");

        // Iniciar els fils
        filJuan.start();
        filPepe.start();

        try {
            filJuan.join(); // Esperem que "Juan" acabi
            filPepe.join(); // Esperem que "Pepe" acabi
        } catch (InterruptedException e) {
            System.out.println("El fil principal ha estat interromput.");
        }

        System.out.println("Termina el fil Pepe");
        System.out.println("Termina el fil Juan");
    }
}
