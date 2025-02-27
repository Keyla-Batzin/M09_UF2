import java.util.ArrayList;
import java.util.Random;

public class Estanc extends Thread{
    private ArrayList<Tabac> tabacDisponible;
    private ArrayList<Paper> paperDisponible;
    private ArrayList<Llumi> llumiDisponible;
    private boolean obert = true;

    public Estanc(){
        tabacDisponible = new ArrayList<Tabac>();
        paperDisponible = new ArrayList<Paper>();
        llumiDisponible = new ArrayList<Llumi>();
    }

    public void nouSubministrament(){
        Random rnd = new Random();
        int num = rnd.nextInt(3) + 1;
        if(num == 1){
            addTabac();
            System.out.println("Afegint Tabac");
        }else if(num == 2){
            addPaper();
            System.out.println("Afegint Paper");
        }else{
            addLlumi();
            System.out.println("Afegint Llumí");
        }
    }

    // Mètodes per afegir Suministres
    public void addTabac(){
        Tabac tabac = new Tabac();
        tabacDisponible.add(tabac);
    }

    public void addPaper(){
        Paper paper = new Paper();
        paperDisponible.add(paper);
    }

    public void addLlumi(){
        Llumi llumi = new Llumi();
        llumiDisponible.add(llumi);
    }

    // Mètodes
    public Tabac venTabac(){
        Tabac tabac = null;
        if(tabacDisponible.size() > 0){
            tabac = tabacDisponible.get(0);
            tabacDisponible.remove(0);
            return tabac;
        }
        return tabac;
    }

    public Paper venPaper(){
        Paper paper = null;
        if(paperDisponible.size() > 0){
            paper = paperDisponible.get(0);
            paperDisponible.remove(0);
            return paper;
        }
        return paper;
    }

    public Llumi venLlumi(){
        Llumi llumi = null;
        if(llumiDisponible.size() > 0){
            llumi = llumiDisponible.get(0);
            llumiDisponible.remove(0);
            return llumi;
        }
        return llumi;
    }

    public boolean disponibilitat(){
        if(tabacDisponible.size() > 0 || paperDisponible.size() > 0 || llumiDisponible.size() > 0){
            return true;
        }
        return false;
    }

    public void tancarEstanc(){
        obert = false;
    }

    @Override
    public synchronized void run(){
        while (obert) {
            try {
                nouSubministrament();
                Thread.sleep(500 + (int) (Math.random() * 1000)); // Espera 0.5 - 1.5 segundo antes de volver a intentar
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
                break;
            }
            // tancarEstanc(); // Parar el bucle
        }
    }
}
