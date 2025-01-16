import java.util.concurrent.TimeUnit;

public class Coet {
    private final Motor[] motors = new Motor[4];
    




    
    public Coet() {
        for (int i = 0; i < motors.length; i++) {
            motors[i] = new Motor(i);
        }
    }

    public void passaAPotencia(int potencia) {
        if (potencia < Motor.MIN_POWER || potencia > Motor.MAX_POWER) {
            System.out.println("Invalid power!");
            return;
        }
        System.out.println("Passant a potència " + potencia);
        for (Motor motor : motors) {
            executorService.submit(() -> motor.setPotencia(potencia));
        }
    }

    public void arranca() {
        for (Motor motor : motors) {
            motor.setPotencia(0);
        }
    }

    public void gestionaPotencies() {
        try {
            while (true) {
                String entrada = Entrada.readLine();
                if (entrada == null || entrada.trim().isEmpty()) {
                    break;
                }
                try {
                    int potencia = Integer.parseInt(entrada.trim());
                    passaAPotencia(potencia);
                } catch (NumberFormatException e) {
                    System.out.println("Potència no vàlida");
                }

                // Exit when all motors are off
                if (todosApagados()) {
                    break;
                }
            }
        } finally {
            executorService.shutdown();
            try {
                if (!executorService.awaitTermination(60, TimeUnit.SECONDS)) {
                    executorService.shutdownNow();
                }
            } catch (InterruptedException e) {
                executorService.shutdownNow();
            }
        }
    }

    public boolean todosApagados() {
        for (Motor motor : motors) {
            if (!motor.estaApagat()) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Coet coet = new Coet();
        coet.arranca();
        coet.gestionaPotencies();
    }
}