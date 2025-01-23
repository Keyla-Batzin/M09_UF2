import java.util.Random;

public class Soci extends Thread {
    private final Compte compte = Compte.getInstance(); // Instancia única de Compte
    private final float aportacio = 10f; // Aportación mensual
    private final int esperaMax = 100; // Tiempo máximo de espera
    private final Random rnd = new Random(); // Generador de números aleatorios
    private final int maxAnys = 10; // Número máximo de años

    public Soci() {
    }

    public Compte getCompte() {
        return compte;
    }

    @Override
    public void run() {
        for (int any = 1; any < maxAnys; any++) {
            for (int mes = 1; mes <= 12; mes++) {
                if (mes % 2 == 0) {
                    // Meses pares: ingresar
                    compte.ingresa(aportacio);
                } else {
                    // Meses impares: retirar
                    compte.retira(aportacio);
                }
                // Espera aleatoria para simular el paso del tiempo
                try {
                    Thread.sleep(rnd.nextInt(esperaMax));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}