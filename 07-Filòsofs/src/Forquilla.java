public class Forquilla {
    private int idForquilla; // Identificador del tenedor
    private boolean enUs; // Flag para saber si está en uso

    // Constructor por defecto
    public Forquilla() {
        this.idForquilla = 0; // Valor por defecto
        this.enUs = false; // Inicialmente no está en uso
    }

    // Constructor con número de tenedor
    public Forquilla(int idForquilla) {
        this.idForquilla = idForquilla;
        this.enUs = false; // Inicialmente no está en uso
    }

    // Getters y Setters
    public int getIdForquilla() {
        return idForquilla;
    }

    public void setIdForquilla(int idForquilla) {
        this.idForquilla = idForquilla;
    }

    public boolean isEnUs() {
        return enUs;
    }

    public void setEnUs(boolean enUs) {
        this.enUs = enUs;
    }
}