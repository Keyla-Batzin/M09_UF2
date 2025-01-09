public class Coet{
    private static Motor[] motors = new Motor[4];
    public static void main(String[] args) {
        int potencia = 3; // Añadir classe Entrada
        arranca();
        passaAPotencia(potencia);
    }

    public static void passaAPotencia(int p){
        if(p < 10 && p > 0){
            System.out.println("Passant a potència " + p);
            for(int i=0; i < motors.length; i++){
                motors[i].setPotencia(p);
                System.out.println("Motor " + i + ": " + "No se " + "Objectiu: " + motors[i].getObjectiu() + " Actual: " + motors[i].getPotencia());
            }
        }else{
            System.out.println("Error: valor no valid");
        }
    }

    public static void arranca(){
        for(int i=0; i < motors.length; i++){
            motors[i] = new Motor(0, 0);
        }
    }

}
