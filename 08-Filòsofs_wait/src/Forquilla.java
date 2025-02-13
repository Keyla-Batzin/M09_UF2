public class Forquilla {
    private int idForquilla; // Identificador del tenedor
    private int propietari;  // ID del filósofo que tiene el tenedor (-1 si está libre)
    public static final int LLIURE = -1; // Constante para indicar que el tenedor está libre

    // Constructor por defecto
    public Forquilla() {
        this.idForquilla = 0; // Valor por defecto
        this.propietari = LLIURE; // Inicialmente, el tenedor está libre
    }

    // Constructor con número de tenedor
    public Forquilla(int idForquilla) {
        this.idForquilla = idForquilla;
        this.propietari = LLIURE; // Inicialmente, el tenedor está libre
    }

    // Getters y Setters
    public int getIdForquilla() {
        return idForquilla;
    }

    public void setIdForquilla(int idForquilla) {
        this.idForquilla = idForquilla;
    }

    public int getPropietari() {
        return propietari;
    }

    public void setPropietari(int propietari) {
        this.propietari = propietari;
    }

    // Método para verificar si el tenedor está libre
    public boolean esLliure() {
        return this.propietari == LLIURE;
    }
    
    // Método para liberar el tenedor
    public void alliberar() {
        this.propietari = LLIURE;
    }

    // Método para asignar el tenedor a un filósofo
    public void assignar(int idFilosof) {
        this.propietari = idFilosof;
    }
}