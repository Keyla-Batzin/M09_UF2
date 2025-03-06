public class Barber extends Thread {
    private String nom;

    public Barber(String nom){
        this.nom = nom;
    }

    public void tallaCabell(){
        try {
            Thread.sleep(900 + (int) (Math.random() * 100)); // 0,9s a 0,1s
        } catch (Exception e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }

    public void dorm(){
        try {
            wait();
        } catch (Exception e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }

    @Override
    public void run(){
        while (true) {
            Barberia.condBarber;
            if(){ // Condicion para saber si hay clientes (condBarber)
                notify(); // Depiesrta al barbero
            }else{
                dorm();
            }
        }
    }
}
