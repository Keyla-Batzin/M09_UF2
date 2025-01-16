public class Administracio extends Thread {
    private static int num_poblacio_activa = 50;
    private Treballador[] poblacio_activa = new Treballador[num_poblacio_activa];
    
    public Administracio() {
        for(int i=0; i < poblacio_activa.length; i++){
            poblacio_activa[i] = new Treballador(25000, 20, 65, 65);
            poblacio_activa[i].start();
        }
    }

    public static void main(String[] args) {
        Administracio admin = new Administracio();
        admin.start();
        for (int i=0; i < admin.poblacio_activa.length; i++) {
            try {
                System.out.println("Ciutadà-" + i + "-> edat: " + admin.poblacio_activa[i].getEdat() + "/ total: " + admin.poblacio_activa[i].getCobrat());
                admin.poblacio_activa[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
