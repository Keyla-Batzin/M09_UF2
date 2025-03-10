public class Client {
    private final int id;
    private final String nom;

    public Client(int id) {
        this.id = id;
        this.nom = "Client-" + id;
    }

    public String getNom() {
        return nom;
    }

    public void tallarSeElCabell() {
        System.out.println("Tallant cabell a " + nom);
    }
}
