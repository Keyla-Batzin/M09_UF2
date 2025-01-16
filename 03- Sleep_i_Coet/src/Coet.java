class Coet {
    // Array de 4 motors per representar els motors del coet.
    private final Motor[] motors = new Motor[4];

    // Constructor de la classe Coet.
    public Coet() {
        // Inicialitzem els 4 motors i els assignem un nom únic.
        for (int i = 0; i < motors.length; i++) {
            motors[i] = new Motor(); // Creació d'un nou motor.
            motors[i].setName("Motor " + (i + 1)); // Assignem un nom al motor (Motor 1, Motor 2, etc.).
        }
    }

    // Mètode per establir la potència objectiu dels motors.
    public void passaAPotencia(int p) {
        // Comprovem si la potència està dins el rang permès (0-10).
        if (p < 0 || p > 10) {
            System.out.println("Error: La potència ha d'estar entre 0 i 10."); // Missatge d'error si el valor no és vàlid.
            return; // Finalitzem l'execució del mètode.
        }

        // Establim la potència objectiu a cada motor.
        for (Motor motor : motors) {
            motor.setPotencia(p); // Assignem la potència a través del setter.
        }
    }

    // Mètode per engegar els motors.
    public void arranca() {
        // Iniciem cada motor utilitzant el mètode start().
        for (Motor motor : motors) {
            motor.start(); // Es crea un fil per a cada motor.
        }
    }

    // Mètode per llegir i establir la potència objectiu des de la consola.
    public void leerPotencia() {
        int potencia = 0; // Variable per emmagatzemar la potència introduïda.

        // Bucle per demanar la potència fins que es introdueixi 0.
        do {
            System.out.print("Introdueix la potència objectiu (0 per acabar): ");
            System.out.println(); // Es mostra un missatge per introduir la potència.
            potencia = Integer.parseInt(Entrada.readLine()); // Llegim la potència introduïda per l'usuari.
            passaAPotencia(potencia); // Establim la potència introduïda als motors.
        } while (potencia != 0); // El bucle continua fins que la potència és 0.

        // Esperem que tots els fils (motors) acabin.
        for (Motor motor : motors) {
            try {
                motor.join(); // Esperem que el fil del motor actual acabi.
            } catch (Exception e) {
                System.out.println(e.getMessage()); // Gestionem qualsevol error durant l'espera.
            }
        }

        // Missatge final quan tots els motors s'han aturat.
        System.out.println("Tots els motors s'han aturat.");
    }

    // Mètode principal per iniciar el programa.
    public static void main(String[] args) {
        Coet coet = new Coet(); // Creem una nova instància del coet amb els seus motors.
        coet.arranca(); // Engeguem els motors.
        coet.leerPotencia(); // Demanem i establim la potència dels motors.
    }
}
