public class exemplo {
    public static void main(String[] args) {
        java.util.Scanner teclado = new java.util.Scanner(System.in);
        int x;
        int i;
        int resultado;

        System.out.println("informe um numero natural: ");

        x = teclado.nextInt();

        if (x < 0) {
            System.out.println("numero invalido");
        } else {
            resultado = 1;

            for (i = 1; i <= x; i++) {

                resultado = resultado * i;

            }

            System.out.println("fatorial:");
            System.out.println(resultado);

        }

    }
}
