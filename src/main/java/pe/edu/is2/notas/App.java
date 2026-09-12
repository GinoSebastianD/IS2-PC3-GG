package pe.edu.is2.notas;

import java.util.Locale;

/** Punto de entrada del JAR ejecutable. */
public final class App {
    private App() { }

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Uso: java -jar target/calculadora-notas-1.0.0.jar <nota1> [nota2 ...]");
            System.out.println("Ejemplo: java -jar target/calculadora-notas-1.0.0.jar 14 16 18");
            return;
        }
        try {
            double[] notas = new double[args.length];
            for (int i = 0; i < args.length; i++) {
                notas[i] = Double.parseDouble(args[i]);
            }
            double promedio = new CalculadoraNotas().promedio(notas);
            System.out.println("CALCULADORA DE NOTAS | IS2 - PRACTICA 03");
            System.out.println("Notas procesadas: " + notas.length);
            System.out.printf(Locale.ROOT, "Promedio: %.2f%n", promedio);
        } catch (NumberFormatException ex) {
            System.err.println("Error: ingrese solo numeros; use punto para los decimales.");
            System.exit(1);
        } catch (IllegalArgumentException ex) {
            System.err.println("Error: " + ex.getMessage());
            System.exit(1);
        }
    }
}
