import java.util.ArrayList;
import java.util.Random;

public class Estanc extends Thread {
    private ArrayList<Tabac> tabacDisponible;
    private ArrayList<Paper> paperDisponible;
    private ArrayList<Llumi> llumiDisponible;
    private boolean obert = true;

    public Estanc() {
        tabacDisponible = new ArrayList<>();
        paperDisponible = new ArrayList<>();
        llumiDisponible = new ArrayList<>();
    }

    public synchronized void nouSubministrament() {
        Random rnd = new Random();
        int num = rnd.nextInt(3); // 0: Tabac, 1: Paper, 2: Llumí
        switch (num) {
            case 0:
                addTabac();
                System.out.println("Afegint Tabac");
                break;
            case 1:
                addPaper();
                System.out.println("Afegint Paper");
                break;
            case 2:
                addLlumi();
                System.out.println("Afegint Llumí");
                break;
        }
        notifyAll(); // Notificar a los fumadores que hay nuevos ingredientes
    }

    public synchronized void addTabac() {
        tabacDisponible.add(new Tabac());
    }

    public synchronized void addPaper() {
        paperDisponible.add(new Paper());
    }

    public synchronized void addLlumi() {
        llumiDisponible.add(new Llumi());
    }

    public synchronized Tabac venTabac() {
        while (tabacDisponible.isEmpty() && obert) {
            try {
                wait(); // Esperar hasta que haya tabaco disponible
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if (!tabacDisponible.isEmpty()) {
            return tabacDisponible.remove(0);
        }
        return null;
    }

    public synchronized Paper venPaper() {
        while (paperDisponible.isEmpty() && obert) {
            try {
                wait(); // Esperar hasta que haya papel disponible
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if (!paperDisponible.isEmpty()) {
            return paperDisponible.remove(0);
        }
        return null;
    }

    public synchronized Llumi venLlumi() {
        while (llumiDisponible.isEmpty() && obert) {
            try {
                wait(); // Esperar hasta que haya llumí disponible
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if (!llumiDisponible.isEmpty()) {
            return llumiDisponible.remove(0);
        }
        return null;
    }

    public synchronized void tancarEstanc() {
        obert = false;
        notifyAll(); // Notificar a todos los hilos que el estanco está cerrado
    }

    @Override
    public void run() {
        while (obert) {
            nouSubministrament();
            try {
                Thread.sleep(500 + (int) (Math.random() * 1000)); // Esperar entre 0.5 y 1.5 segundos
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
                break;
            }
        }
        System.out.println("Estanc tancat");
    }
}