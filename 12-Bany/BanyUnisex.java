import java.util.ArrayList;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

public class BanyUnisex {
    private int BANY_BUIT = 0;
    private int BANY_AMB_HOMES = 1;
    private int BANY_AMB_DONES = 2;

    private int estatActual = 0;
    private int CAPACITAT_MAX = 3;
    private int ocupants = 0;
    private Semaphore capacitat;
    private ReentrantLock lockEstat = new ReentrantLock(true);

    public BanyUnisex() {
        capacitat = new Semaphore(CAPACITAT_MAX, true);
    }

    public int getOcupants() {
        return ocupants;
    }

    public void entraHome() throws InterruptedException{
        while (true) {
            lockEstat.lock();
            try {
                if (estatActual == BANY_BUIT || estatActual == BANY_AMB_HOMES) {
                    if (capacitat.tryAcquire()) {
                        if (estatActual == BANY_BUIT) {
                            estatActual = BANY_AMB_HOMES;
                        }
                        ocupants++;
                        System.out.println("Home entra al bany. Ocupants: " + getOcupants());
                        break;
                    }
                } else if (estatActual == BANY_AMB_DONES) {
                    lockEstat.lock();
                }
            } finally {
                lockEstat.unlock();
            }
            Thread.sleep(100);
        }
    }

    public void surtHome() {
        capacitat.release();
        System.out.println("Home surt del bany. Ocupants: " + getOcupants());
    }

    public void entraDona() {
        lockEstat.lock();
        try {
            if (estatActual == BANY_BUIT || estatActual == BANY_AMB_DONES) {
                lockEstat.unlock();
                estatActual = BANY_AMB_DONES;
                if (capacitat.tryAcquire()) {
                    capacitat.tryAcquire();
                    System.out.println("Dona entra al bany. Ocupants: " + getOcupants());
                    Thread.sleep(2000 + (int) (Math.random() * 1000)); // utilitzaLavabo (durant entre 2 i 3 segons)
                }
            } else if (estatActual == BANY_AMB_HOMES) {
                lockEstat.lock();
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void surtDona() {
        capacitat.release();
        System.out.println("Dona surt del bany. Ocupants: " + getOcupants());
    }

    public static void main(String[] args) {
        BanyUnisex bany = new BanyUnisex();
        ArrayList<Home> listaHomes = new ArrayList<>();
        ArrayList<Dona> listaDones = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            listaHomes.add(new Home(i, bany));
            listaDones.add(new Dona(i, bany));
        }

        for (int i = 0; i < 5; i++) {
            listaHomes.get(i).start();
            listaDones.get(i).start();
        }
    }
}
