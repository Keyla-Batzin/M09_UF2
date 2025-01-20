public class Administracio {
    private static final int num_poblacio_activa = 50;
    private Treballador[] poblacio_activa = new Treballador[num_poblacio_activa];

    public Administracio() {
        for (int i = 0; i < poblacio_activa.length; i++) {
            poblacio_activa[i] = new Treballador(25000, 20, 65);
        }
    }

    public Treballador[] getPoblacioActiva() {
        return poblacio_activa;
    }

    public static void main(String[] args) {
        Administracio admin = new Administracio();
        Treballador[] treballadors = admin.getPoblacioActiva();

        // Inicia los hilos
        for (Treballador treballador : treballadors) {
            treballador.start();
        }

        // Espera a que los hilos terminen  
        for (Treballador treballador : treballadors) {
            try {
                treballador.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Calcula y muestra el resultado
        float totalCobrat = 0.0f;
        for (int i = 0; i < treballadors.length; i++) {
            Treballador treballador = treballadors[i];
            System.out.printf("Ciutadà-%d -> edat: %d / total: %.2f\n", i, treballador.getEdat(), treballador.getCobrat());
            totalCobrat += treballador.getCobrat();
        }
    }
}
