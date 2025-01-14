public class Motor {
    private int potenciaActual = 0;
    private int potenciaObjectiu = 0;
    private int id;

    public Motor() {}

    public Motor(int id) {this.id = id;}

    public void setId(int id) {this.id = id;}

    public void setPotencia(int potencia) {
        if (potencia < 0 || potencia > 10) {
            System.out.println("Potència no vàlida!");
            return;
        }
        this.potenciaObjectiu = potencia;
        // Incrementar o decrementar la potencia actual hasta alcanzar la potencia
        // objectiu
        new Thread(() -> {
            try {
                while (potenciaActual != potenciaObjectiu) {
                    if (potenciaActual < potenciaObjectiu) {
                        potenciaActual++;
                        System.out.println("Motor " + id + ": Incre. Objectiu: " + potenciaObjectiu + " Actual: "
                                + potenciaActual);
                        id++;
                        if(id == 4){id = 0;}
                    } else {
                        potenciaActual--;
                        System.out.println("Motor " + id + ": Decre. Objectiu: " + potenciaObjectiu + " Actual: "
                                + potenciaActual);
                        id--;
                        if(id == 0){id = 4;}
                    }
                    Thread.sleep((int) (Math.random() * 1000) + 1000); // Simula el tiempo de respuesta del motor
                }
                System.out.println(
                        "Motor " + id + ": FerRes Objectiu: " + potenciaObjectiu + " Actual: " + potenciaActual);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public boolean estaApagat() {
        return potenciaActual == 0;
    }
}
