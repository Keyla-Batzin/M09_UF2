public class MainDemoFil {
    public static void main(String[] args) {
        // Capturar el fil actual
        Thread filActual = Thread.currentThread();

        // Construir manualment la cadena en el format esperat
        String customToString = "Thread[#" + filActual.getId() + "," + filActual.getName() + ","
                + filActual.getPriority() + "," + filActual.getThreadGroup().getName() + "]";

        // Mostrar les propietats del fil
        System.out.println("Prioritat -> " + filActual.getPriority() + ", Nom -> " + filActual.getName());
        System.out.println("toString() -> " + customToString);
    }
}
