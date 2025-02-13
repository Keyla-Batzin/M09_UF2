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
        if (!forquillaEsquerra.esLliure()) {
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
                } catch (InterruptedException e) {  b b 
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

    // Filo agafa les 2 forquilles
    public void agafaForquilles(){

    }

    // Filo agafa ESSQUERRA
    public void agafaForquillaEsquerra(){
        
    }

    // Filo agafa DRETA
    public void agafaForquillaDreta(){
        
    }

    // Filo deixa les 2 forquilles
    public void deixaForquilles(){

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
            menjar();
            if (gana > 0) {
                try {
                    // Espera un tiempo aleatorio
                    Thread.sleep(500 + (int) (Math.random() * 500));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            pensar();
        }
    }
}