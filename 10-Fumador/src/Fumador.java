public class Fumador extends Thread{
    private int id;
    private Estanc estanc;
    private Tabac tabac;
    private Paper paper;
    private Llumi llumi;
    private int numFumades = 0;

    public Fumador(Estanc estanc, int id){
        this.estanc = estanc;
        this.id = id;
    }

    public int getNumFumades() {
        return numFumades;
    }

    public void fuma(){
        try {
            if(tabac == new Tabac() && paper == new Paper() && llumi == new Llumi()){
                tabac = null;
                paper = null;
                llumi = null;
                Thread.sleep(500 + (int) (Math.random() * 500));
                System.out.println("Fumador " + id + " fumant");
                numFumades++;
                System.out.println("El Fumador " + id + " ha fumat " + numFumades + " vegades");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }

    public synchronized void compraTabac(){
        tabac = estanc.venTabac();
        System.out.println("Fumador " + id + " comprant Tabac");
    }

    public synchronized void compraPaper(){
        paper = estanc.venPaper();
        System.out.println("Fumador " + id + " comprant Paper");
    }

    public synchronized void compraLlumi(){
        llumi = estanc.venLlumi();
        System.out.println("Fumador " + id + " comprant Llumí");
    }

    @Override
    public synchronized void run() {
        for(int i=0; i < 3; i++){
            try {
                while (!estanc.disponibilitat()) {
                    wait();
                }
                compraTabac();
                compraPaper();
                compraLlumi();
                fuma();
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}
