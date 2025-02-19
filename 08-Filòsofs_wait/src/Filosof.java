public class Filosof extends Thread {
    public int id;
    public Forquilla forquillaEsquerra;
    public Forquilla forquillaDreta;
    public int gana = 0;

    public Filosof(int id, Forquilla esquerra, Forquilla dreta) {
        this.id = id;
        this.forquillaEsquerra = esquerra;
        this.forquillaDreta = dreta;
    }

    public void menjar() throws InterruptedException {
        if (id % 2 == 0) {
            if (!forquillaEsquerra.esLliure() || !forquillaDreta.esLliure()) {
                System.out.println("Filòsof " + id + " no pot menjar, deixa les forquilles.");
                gana++;
                System.out.println("Filòsof " + id + " gana = " + gana);
                return;
            }
            agafaForquillaEsquerra();
            agafaForquillaDreta();
        } else {
            if (!forquillaDreta.esLliure() || !forquillaEsquerra.esLliure()) {
                System.out.println("Filòsof " + id + " no pot menjar, deixa les forquilles.");
                gana++;
                System.out.println("Filòsof " + id + " gana = " + gana);
                return;
            }
            agafaForquillaDreta();
            agafaForquillaEsquerra();
        }

        // Comer si tiene ambas forquillas
        if (forquillaEsquerra.getPropietari() == id && forquillaDreta.getPropietari() == id) {
            System.out.println("Filòsof " + id + " està menjant.");
            Thread.sleep(1000 + (int) (Math.random() * 1000));
            deixaForquilles();
            System.out.println("Filòsof " + id + " deixa les forquilles.");
            gana = 0;
        } else {
            deixaForquilles();
            System.out.println("Filòsof " + id + " no pot menjar, deixa les forquilles.");
            gana++;
            System.out.println("Filòsof " + id + " gana = " + gana);
        }
    }

    // Filo agafa les 2 forquilles
    public void agafaForquilles() throws InterruptedException {
        agafaForquillaEsquerra();
        agafaForquillaDreta();
    }

    // Filo agafa ESSQUERRA
    public void agafaForquillaEsquerra() throws InterruptedException {
        forquillaEsquerra.assignar(id);
    }

    // Filo agafa DRETA
    public void agafaForquillaDreta() throws InterruptedException {
        forquillaDreta.assignar(id);
    }

    // Filo deixa les 2 forquilles
    public void deixaForquilles() {
        forquillaEsquerra.alliberar();
        forquillaDreta.alliberar();
    }

    public void pensar() {
        System.out.println("Filòsof " + id + " està pensant.");
        try {
            Thread.sleep(1000 + (int) (Math.random() * 1000)); // Piensa durante 1-2 segundos
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                menjar();
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
                break;
            }
            if (gana > 0) {
                try {
                    Thread.sleep(500 + (int) (Math.random() * 500));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    Thread.currentThread().interrupt();
                    break;
                }
            }
            pensar();
        }
    }
}