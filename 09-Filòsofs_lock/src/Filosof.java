public class Filosof extends Thread {
    public int id;
    public long iniciGana; // Momento en que comienza a intentar coger los tenedores
    public long fiGana; // Momento en que termina de comer
    public int gana; // Diferencia entre fiGana e iniciGana en segundos
    public Forquilla forquillaEsquerra;
    public Forquilla forquillaDreta;
    public int comptadorGana = 0;

    // Constructor
    public Filosof(int id, Forquilla esquerra, Forquilla dreta) {
        this.id = id;
        this.forquillaEsquerra = esquerra;
        this.forquillaDreta = dreta;
    }

    // Método para comer
    public void menjar() throws InterruptedException {
        iniciGana = System.currentTimeMillis(); // Actualiza el momento en que comienza a intentar coger los tenedores
        agafarForquilles(); // Intenta coger los tenedores
        System.out.println("Fil " + id + " té forquilles esq(" + forquillaEsquerra.getIdForquilla() +") dreta (" + forquillaDreta.getIdForquilla() + ")");
        Thread.sleep(1000 + (int) (Math.random() * 1000)); // Come durante 1-2 segundos
        fiGana = System.currentTimeMillis(); // Registra el momento en que termina de comer
        gana = calculaGana(); // Calcula la hambre
        System.out.println("Fil " + id + " menja amb gana " + gana);
        deixarForquilles(); // Libera los tenedores
        System.out.println("Fil " + id + " ha acabat de menjar");
        System.out.println("Fil " + id + " deixa les forquilles");
    }

    // Método para coger ambos tenedores
    public void agafarForquilles() {
        agafarForquillaEsquerra();
        agafarForquillaDreta();
    }

    // Método para coger el tenedor izquierdo
    public void agafarForquillaEsquerra() {
        forquillaEsquerra.agafa();
    }

    // Método para coger el tenedor derecho
    public void agafarForquillaDreta() {
        forquillaDreta.agafa();
    }

    // Método para liberar ambos tenedores
    public void deixarForquilles() {
        forquillaDreta.deixa();
        forquillaEsquerra.deixa();
    }

    // Método para pensar
    public void pensar() {
        System.out.println("Filòsof " + id + " està pensant.");
        try {
            Thread.sleep(1000 + (int) (Math.random() * 1000)); // Piensa durante 1-2 segundos
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Método para calcular la hambre
    public int calculaGana() {
        return (int) ((fiGana - iniciGana) / 1000); 
    }

    // Método de ejecución del hilo
    @Override
    public void run() {
        while (true) {
            try {
                menjar(); // Intenta comer
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
                break;
            }
            if (gana > 0) {
                try {
                    Thread.sleep(500 + (int) (Math.random() * 500)); // Espera 0.5-1 segundo antes de volver a intentar
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    Thread.currentThread().interrupt();
                    break;
                }
            }
            pensar(); // Piensa
        }
    }
}