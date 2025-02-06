public class Forquilla {
    public int idForquilla;
    private volatile boolean enUs = false;

    // Constructor
    public Forquilla() {
    }

    public Forquilla(int idForquilla) {
        this.idForquilla = idForquilla;
    }

    // Getters i Setters
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

    public boolean ocupat() {
        return this.enUs;
    }
}
