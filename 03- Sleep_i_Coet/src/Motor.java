public class Motor extends Thread{
    private int objectiu = 0; // Potència objectiu
    private int potencia = 0; // Potència actual

    public Motor(int objectiu, int potencia){
        this.objectiu = objectiu;
        this.potencia = potencia;
    }

    public void setPotencia(int p){
        for(int i=0; i < p; i++){
            this.potencia++;
            try{
                Thread.sleep(120);
            }catch (InterruptedException e){
                e.setStackTrace(getStackTrace());
            }

            if(this.potencia == objectiu){break;}
        }
    }

    public int getObjectiu(){return objectiu;}

    public int getPotencia(){return potencia;}
}
