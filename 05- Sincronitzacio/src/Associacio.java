public class Associacio {
    private static final int numSocis = 1000; // Número de socios
    private static final Soci[] socis = new Soci[numSocis]; // Array de socios

    // Inicia los hilos de los socios
    public static void iniciaCompteTempsSocis() {
        for (int i = 0; i < numSocis; i++) {
            socis[i] = new Soci();
            socis[i].start();
        }
    }

    // Espera a que todos los hilos terminen
    public static void esperaPeriodeSocis() {
        for (Soci soci : socis) {
            try {
                soci.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // Muestra el saldo final de la cuenta
    public static void mostraBalancComptes() {
        System.out.printf("Saldo: %.2f%n", Compte.getInstance().getSaldo());
    }

    // Método principal
    public static void main(String[] args) {
        iniciaCompteTempsSocis(); // Inicia los hilos
        esperaPeriodeSocis(); // Espera a que todos terminen
        mostraBalancComptes(); // Muestra el saldo final
    }
}
