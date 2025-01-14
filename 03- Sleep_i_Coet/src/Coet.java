public class Coet {
    private Motor motor = new Motor();

    public Coet() {
        for (int i = 0; i < 4; i++) {
            motor = new Motor();
        }
    }

    public void passaAPotencia(int potencia) {
        if (potencia < 0 || potencia > 10) {
            System.out.println("Potència no vàlida!");
            return;
        }
        System.out.println("Passant a potència " + potencia);
        for (int i = 0; i < 4; i++) {
            motor.setPotencia(potencia);
        }
    }

    public void arranca() {
        for (int i = 0; i < 4; i++) {
            motor.setId(0);
            motor.setPotencia(0); // Arrenca els motors a una potència inicial
        }
    }

    public void gestionaPotencies() {
        try {
            while (true) {
                String entrada = Entrada.readLine();  // Cambiado a Entrada.readLine()
                if (entrada == null || entrada.trim().isEmpty()) {
                    break;
                }
                int potencia = Integer.parseInt(entrada.trim());
                passaAPotencia(potencia);

                // Finaliza cuando todos los motores estén apagados
                if (todosApagados()) {
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean todosApagados() {
        for (int i = 0; i < 4; i++) {
            if (!motor.estaApagat()) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Coet coet = new Coet();
        coet.arranca(); // Arrancar el cohete
        coet.gestionaPotencies(); // Gestión de las potencias
    }
}