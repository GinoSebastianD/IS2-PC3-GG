package pe.edu.is2.notas;

/** Calcula un promedio simple sobre una escala de 0 a 20. */
public final class CalculadoraNotas {
    public double promedio(double... notas) {
        if (notas == null || notas.length == 0) {
            throw new IllegalArgumentException("Debe ingresar al menos una nota.");
        }
        double suma = 0;
        for (double nota : notas) {
            if (!Double.isFinite(nota) || nota < 0 || nota > 20) {
                throw new IllegalArgumentException("Cada nota debe ser un numero entre 0 y 20.");
            }
            suma += nota;
        }
        return suma / notas.length;
    }
}
