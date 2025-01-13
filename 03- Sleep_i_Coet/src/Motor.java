public class Motor extends Thread {
    private int potenciaActual = 0;
    private int potenciaObjectiu = 0;


    public int getPotenciaActual() {
        return potenciaActual;
    }

    public void setPotenciaActual(int potenciaActual) {
        this.potenciaActual = potenciaActual;
    }

    public synchronized void setPotencia(int p) {
        this.potenciaObjectiu = p;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (this) {
                if (potenciaActual == potenciaObjectiu) {
                    if (potenciaActual == 0) {
                        System.out.printf("Motor %s: FerRes Objectiu: %d Actual: %d%n", getName(), potenciaObjectiu, potenciaActual);
                        break;
                    }
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        System.err.println("Execució interrompuda!");
                    }
                    continue;
                }

                if (potenciaActual < potenciaObjectiu) {
                    potenciaActual++;
                    System.out.printf("Motor %s: Incre. Objectiu: %d Actual: %d%n", getName(), potenciaObjectiu, potenciaActual);
                } else if (potenciaActual > potenciaObjectiu) {
                    potenciaActual--;
                    System.out.printf("Motor %s: Decre. Objectiu: %d Actual: %d%n", getName(), potenciaObjectiu, potenciaActual);
                }
            }

            try {
                Thread.sleep((int) (Math.random() * 100 + 500));
            } catch (InterruptedException e) {
                System.err.println("Execució interrompuda!");
            }
        }
    }
}
