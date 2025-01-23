public class Compte {
    private static Compte compte; // Instancia única (inicialmente null)
    public float saldo;

    // Constructor privado para evitar que se creen objetos desde fuera
    private Compte() {
    }

    // Método público para obtener la instancia única
    public static Compte getInstance() {
        if (compte == null) { // Si aún no se ha creado la instancia
            compte = new Compte();
        }
        return compte; // Devuelve siempre la misma instancia
    }

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }

    public void ingresa(float ingres) {
        saldo += ingres;
    }

    public void retira(float retirada) {
        saldo -= retirada;
        ;
    }
}
