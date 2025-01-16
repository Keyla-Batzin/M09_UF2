class Motor extends Thread {
    // Potència actual del motor.
    private int potenciaActual = 0;
    // Potència objectiu del motor.
    private int potenciaObjectiu = 0;

    // Setter sincronitzat per establir la potència objectiu.
    public synchronized void setPotencia(int p) {
        potenciaObjectiu = p; // Actualitzem la potència objectiu.
        notify(); // Notifiquem al fil que hi ha hagut un canvi en la potència objectiu.
    }

    // Mètode principal del fil.
    @Override
    public void run() {
        try {
            // Bucle infinit que simula el funcionament del motor.
            while (true) {
                synchronized (this) {
                    // Esperem mentre la potència actual sigui igual a la potència objectiu.
                    while (potenciaActual == potenciaObjectiu) {
                        wait(); // El fil entra en estat d'espera fins a rebre una notificació.
                    }
                }

                // Ajustem la potència actual fins arribar a la potència objectiu.
                while (potenciaActual != potenciaObjectiu) {
                    if (potenciaActual < potenciaObjectiu) {
                        // Incrementem la potència si és menor que l'objectiu.
                        potenciaActual++;
                        System.out.println(getName() + ": Incre. " +
                                "Objectiu: " + potenciaObjectiu + " Potència actual: " + potenciaActual);
                    } else if (potenciaActual > potenciaObjectiu) {
                        // Reduïm la potència si és major que l'objectiu.
                        potenciaActual--;
                        System.out.println(getName() + ": Decre. " +
                                "Objectiu: " + potenciaObjectiu + " Potència actual: " + potenciaActual);
                    } else {
                        // No fem res si la potència actual és igual a l'objectiu.
                        System.out.println(getName() + ": FerRes. " +
                                "Objectiu: " + potenciaObjectiu + " Potència actual: " + potenciaActual);
                    }

                    // Simulem el retard en l'ajust de la potència (temps del motor).
                    Thread.sleep(1000 + (int) (Math.random() * 1000));
                }

                // Si la potència objectiu és 0, aturem el motor i sortim del bucle.
                if (potenciaObjectiu == 0) {
                    System.out.println(getName() + " - Motor aturat.");
                    break; // Sortim del bucle i finalitzem el fil.
                }
            }
        } catch (InterruptedException e) {
            // Gestionem la interrupció del fil.
            System.out.println(getName() + " - Fil interromput.");
        }
    }
}
