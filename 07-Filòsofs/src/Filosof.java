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

    public void menjar() {
        // Intenta coger el tenedor izquierdo
        if (!forquillaEsquerra.isEnUs()) {
            forquillaEsquerra.setEnUs(true);
            System.out.println("Filòsof " + id + " agafa la forquilla esquerra " + forquillaEsquerra.getIdForquilla());

            // Intenta coger el tenedor derecho
            if (!forquillaDreta.isEnUs()) {
                forquillaDreta.setEnUs(true);
                System.out.println("Filòsof " + id + " agafa la forquilla dreta " + forquillaDreta.getIdForquilla());

                // Come durante 1-2 segundos
                System.out.println("Filòsof " + id + " està menjant.");
                try {
                    Thread.sleep(1000 + (int) (Math.random() * 1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                // Libera los tenedores
                forquillaEsquerra.setEnUs(false);
                forquillaDreta.setEnUs(false);
                System.out.println("Filòsof " + id + " deixa les forquilles.");
                gana = 0; // Resetea el contador de hambre
            } else {
                // Si no puede coger el tenedor derecho, suelta el izquierdo
                forquillaEsquerra.setEnUs(false);
                System.out.println("Filòsof " + id + " deixa l'esquerra(" + forquillaEsquerra.getIdForquilla() + ") i espera (dreta ocupada)");
                gana++;
                System.out.println("Filòsof " + id + " gana=" + gana);
            }
        } else {
            // Si no puede coger el tenedor izquierdo, incrementa el hambre
            System.out.println("Filòsof " + id + " no pot agafar la forquilla esquerra. Espera...");
            gana++;
            System.out.println("Filòsof " + id + " gana=" + gana);
        }
    }

    public void pensar() {
        System.out.println("Filòsof " + id + " està pensant.");
        gana++;
        try {
            Thread.sleep(1000 + (int) (Math.random() * 1000)); // Piensa durante 1-2 segundos
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (true) {
            menjar();
            if (gana > 0) {
                try {
                    Thread.sleep(500 + (int) (Math.random() * 500)); // Espera 0.5-1 segundo antes de volver a intentar
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            pensar();
        }
    }
}