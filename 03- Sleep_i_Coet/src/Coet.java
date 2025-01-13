public class Coet {
    private Motor[] motors;

    public Coet() {
        motors = new Motor[4];
        for (int i = 0; i < motors.length; i++) {
            motors[i] = new Motor();
            motors[i].setName("Motor " + i);
            motors[i].start();
        }
    }

    public void passaAPotencia(int p) {
        if (p < 0 || p > 10) {
            System.out.println("Error: La potència ha d'estar entre 0 i 10.");
            return;
        }
        System.out.printf("Passant a potència %d%n", p);
        for (Motor motor : motors) {
            motor.setPotencia(p);
        }

        // Espera fins que tots els motors arribin a l'objectiu
        boolean totsComplets;
        do {
            totsComplets = true;
            for (Motor motor : motors) {
                synchronized (motor) {
                    if (motor.getPotenciaActual() != p) {
                        totsComplets = false;
                        break;
                    }
                }
            }
            try {
                Thread.sleep(500); // Evita sobrecarregar el processador
            } catch (InterruptedException e) {
                System.err.println("Execució interrompuda!");
            }
        } while (!totsComplets);
    }

    public static void main(String[] args) {
        Coet coet = new Coet();
        while (true) {
            System.out.print("Introdueix la potència objectiu (0 per sortir): ");
            String entrada = Entrada.readLine();

            // Verifica que l'entrada no sigui buida
            if (entrada.isEmpty()) {
                System.out.println("Si us plau, introdueix un número vàlid.");
                continue;
            }

            try {
                int potencia = Integer.parseInt(entrada);
                if (potencia == 0) {
                    coet.passaAPotencia(0);
                    break;
                }
                coet.passaAPotencia(potencia);
            } catch (NumberFormatException e) {
                System.out.println("Error: Introdueix un número enter vàlid.");
            }
        }
    }
}
