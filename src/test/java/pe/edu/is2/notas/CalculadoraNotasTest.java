package pe.edu.is2.notas;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CalculadoraNotasTest {
    private final CalculadoraNotas calculadora = new CalculadoraNotas();

    @Test
    void calculaPromedioDeVariasNotas() {
        assertEquals(16.0, calculadora.promedio(14, 16, 18), 1e-9);
    }

    @Test
    void conservaDecimales() {
        assertEquals(13.75, calculadora.promedio(12.5, 15), 1e-9);
    }

    @Test
    void aceptaLimitesDeLaEscala() {
        assertEquals(10.0, calculadora.promedio(0, 20), 1e-9);
    }

    @Test
    void aceptaUnaSolaNota() {
        assertEquals(17.5, calculadora.promedio(17.5), 1e-9);
    }

    @Test
    void rechazaListaVacia() {
        assertThrows(IllegalArgumentException.class, () -> calculadora.promedio());
    }

    @Test
    void rechazaListaNula() {
        assertThrows(IllegalArgumentException.class, () -> calculadora.promedio((double[]) null));
    }

    @ParameterizedTest
    @ValueSource(doubles = {-1, 20.1, Double.NaN, Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY})
    void rechazaNotasInvalidas(double nota) {
        assertThrows(IllegalArgumentException.class, () -> calculadora.promedio(10, nota));
    }
}
