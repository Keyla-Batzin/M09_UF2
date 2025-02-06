public class Filosof extends Thread {
    private int id = 0;
    public Forquilla forquillaDreta = new Forquilla();
    public Forquilla forquillaEsquerra = new Forquilla();
    public static int gana = 0;
    public String nom;

    public Filosof(int id, Forquilla esquerra, Forquilla dreta) {
        this.id = id;
        this.forquillaEsquerra = esquerra;
        this.forquillaDreta = dreta;
    }

    public boolean menjar() {
        if (!forquillaEsquerra.ocupat()) {
            forquillaDreta.setEnUs(true);
            System.out
                    .println("Filòsof: fil " + id + " agafa la forquilla esquerra "
                            + forquillaEsquerra.getIdForquilla());
            if (forquillaDreta.ocupat()) {
                forquillaDreta.setEnUs(false);
                System.out
                        .println("Filòsof: fil " + id + " deixa l'esquerra " + "(" + forquillaEsquerra.getIdForquilla()
                                + ")" + "i espera dreta(ocupada)");
                gana++;
                System.out.println("gana = " + gana);
            } else {
                forquillaEsquerra.setEnUs(true);
                System.out
                        .println("Filòsof: fil " + id + " agafa la forquilla dreta " + forquillaDreta.getIdForquilla());
                System.out.println("menja");
                gana = 0;
                return true;
            }
        } else {
            gana++;
            System.out.println("gana = " + gana);
        }
        return false;
    }

    public static void pensar() {
        System.out.println("pensa");
        gana++;
    }

    public static int getGana() {
        return gana;
    }

    public static void setGana(int gana) {
        Filosof.gana = gana;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public void run() {
        try {
            menjar();
            boolean menjar = menjar();
            if (!menjar) {
                Thread.sleep(1000 + (int) (Math.random() * 1000)); // Espera i torna a intentar-ho
                menjar();
            } else {
                Thread.sleep(1000 + (int) (Math.random() * 1000)); // Menja i espera 1/2 s
            }
            pensar();
        } catch (InterruptedException E) {
            System.out.println("Error hilo");
        }
    }
}
