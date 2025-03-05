public class Fumador extends Thread {
    private int id;
    private Estanc estanc;
    private Tabac tabac;
    private Paper paper;
    private Llumi llumi;
    private int numFumades = 0;

    public Fumador(Estanc estanc, int id) {
        this.estanc = estanc;
        this.id = id;
    }

    public int getNumFumades() {
        return numFumades;
    }

    public void fuma() {
        try {
            if (tabac != null && paper != null && llumi != null) {
                // Consumir los ingredientes
                tabac = null;
                paper = null;
                llumi = null;
                Thread.sleep(500 + (int) (Math.random() * 500)); // Fumar entre 0.5 y 1 segundo
                numFumades++;
                System.out.println("Fumador " + id + " ha fumat " + numFumades + " vegades");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }

    public void compraTabac() {
        tabac = estanc.venTabac();
        if (tabac != null) {
            System.out.println("Fumador " + id + " ha comprat Tabac");
        }
    }

    public void compraPaper() {
        paper = estanc.venPaper();
        if (paper != null) {
            System.out.println("Fumador " + id + " ha comprat Paper");
        }
    }

    public void compraLlumi() {
        llumi = estanc.venLlumi();
        if (llumi != null) {
            System.out.println("Fumador " + id + " ha comprat Llumí");
        }
    }

    @Override
    public void run() {
        while (numFumades < 3) { // Fumar hasta 3 veces
            compraTabac();
            compraPaper();
            compraLlumi();
            fuma();
        }
        System.out.println("Fumador " + id + " ha acabat de fumar");
    }
}