/*  */

public class Main {
    public static void main(String[] args) {
        // Crear instancias de hilos
        Fil fil1 = new Fil("Juan");
        Fil fil2 = new Fil("Pepe");

        // Iniciar hilos
        fil1.start();
        fil2.start();

        // Mensaje de terminación del hilo principal
        System.out.println("Termina thread main");
    }
}
