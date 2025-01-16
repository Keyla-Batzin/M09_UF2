import java.util.Random;

public class Treballador extends Thread {
    private int sou_anual_brut;
    private int edat_inici_treball;
    private int edat_fi_treball;
    private int edat_actual;
    private float cobrat; // Total acumulado
    private Random rnd;

    public Treballador(int sou_anual_brut, int edat_inici_treball, int edat_fi_treball) {
        this.sou_anual_brut = sou_anual_brut;
        this.edat_inici_treball = edat_inici_treball;
        this.edat_fi_treball = edat_fi_treball;
        this.edat_actual = 0;
    }

    public float getCobrat() {
        return cobrat;
    }

    public int getEdat() {
        return edat_actual;
    }

    // Calcula el salario mensual antes de impuestos
    public int cobra() {
        return sou_anual_brut / 12;
    }

    // Calcula los impuestos a pagar por el salario mensual
    public int pagaImpostos(int sou_mes) {
        return (int) (sou_mes * 0.24); // 24% de impuestos
    }

    @Override
    public void run() {
        int sou_mes = cobra();
        int impostos = pagaImpostos(sou_mes);
        cobrat = sou_mes - impostos; // Acumula el salario mensual después de impuestos
        this.cobrat = cobrat * 12;
        try {
            Thread.sleep(2000); // Simula trabajo con 2 segundos de pausa
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}