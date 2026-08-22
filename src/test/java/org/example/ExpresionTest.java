package org.example;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import ucu.edu.aed.Ejercicio26.Expresion;

import java.util.ArrayList;
import java.util.List;

public class ExpresionTest {

    // Mismos casos de prueba para la Parte A (pila propia) y la Parte B (Stack de Java).
    private static final String[] EXPRESIONES = {
        "",
        "{}",
        "{}{{}}",
        "{{}{{}",
        "{",
        "}",
        "{{{}}}",
        "}{",
        "{{}}{}{}"
    };

    private static final boolean[] RESULTADOS_ESPERADOS = {
        true,
        true,
        true,
        false,
        false,
        false,
        true,
        false,
        true
    };

    private final Expresion expresion = new Expresion();

    private List<Character> caracteres(String texto) {
        List<Character> lista = new ArrayList<>();
        for (char c : texto.toCharArray()) {
            lista.add(c);
        }
        return lista;
    }

    @Test
    public void controlCorchetesConPilaPropia() {
        for (int i = 0; i < EXPRESIONES.length; i++) {
            assertEquals("Fallo en expresión: \"" + EXPRESIONES[i] + "\"",
                    RESULTADOS_ESPERADOS[i],
                    expresion.controlCorchetes(caracteres(EXPRESIONES[i])));
        }
    }

    @Test
    public void controlCorchetesConStackDeJavaDaLosMismosResultadosQueLaPilaPropia() {
        for (int i = 0; i < EXPRESIONES.length; i++) {
            boolean resultadoPilaPropia = expresion.controlCorchetes(caracteres(EXPRESIONES[i]));
            boolean resultadoStackJava = expresion.controlCorchetesConStackDeJava(caracteres(EXPRESIONES[i]));

            assertEquals("Fallo en expresión: \"" + EXPRESIONES[i] + "\"",
                    RESULTADOS_ESPERADOS[i], resultadoStackJava);
            assertEquals("Las dos implementaciones difieren en: \"" + EXPRESIONES[i] + "\"",
                    resultadoPilaPropia, resultadoStackJava);
        }
    }

    @Test
    public void listaVaciaEsBienFormadaEnAmbasImplementaciones() {
        assertEquals(true, expresion.controlCorchetes(new ArrayList<>()));
        assertEquals(true, expresion.controlCorchetesConStackDeJava(new ArrayList<>()));
    }
}
