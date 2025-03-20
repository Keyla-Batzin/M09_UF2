public class Home extends Thread{
    private String nom;
    private BanyUnisex lavabo;

    public Home(int id, BanyUnisex lavabo){
        this.nom = "Home-" + id;
        this.lavabo = lavabo;
    }

    @Override
    public void run(){
        try {
            System.out.println(nom + " vol entrar al bany");
            lavabo.entraHome();
            Thread.sleep(1000 + (int) (Math.random() * 1000)); // utilitzaLavabo (durant entre 1 i 2 segons)
            lavabo.surtHome();
            System.out.println(nom + " ha acabat d'usar el bany");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }finally{
            lavabo.surtHome();
        }
    }
}