public class Dona extends Thread{
    private String nom;
    private BanyUnisex lavabo;

    public Dona(int id, BanyUnisex lavabo){
        this.nom = "Dona-" + id;
        this.lavabo = lavabo;
    }

    @Override
    public void run(){
        try {
            System.out.println(nom + "vol entrar al bany");
            lavabo.entraDona();
            lavabo.surtDona();
            System.out.println(nom + "ha acabat d'usar el bany");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }finally{
            lavabo.surtDona();
        }
    }
}
