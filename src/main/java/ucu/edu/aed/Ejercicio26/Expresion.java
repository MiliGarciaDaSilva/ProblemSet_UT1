package ucu.edu.aed.Ejercicio26;

import java.util.List;
import java.util.Stack;

import ucu.edu.aed.implementaciones.Pila;

public class Expresion {

    public boolean controlCorchetes(List<Character> listaDeEntrada) {
        if (listaDeEntrada == null) {
            return true;
        }

        Pila<Character> pila = new Pila<>();

        for (Character actual : listaDeEntrada) {
            if (actual == '{') {
                pila.mete(actual);
            } else if (actual == '}') {
                if (pila.esVacio()) {
                    return false;
                }
                pila.saca();
            }
        }
        return pila.esVacio();
    }

    public boolean controlCorchetesConStackDeJava(List<Character> listaDeEntrada) {
        if (listaDeEntrada == null) {
            return true;
        }

        Stack<Character> pila = new Stack<>();

        for (Character actual : listaDeEntrada) {
            if (actual == '{') {
                pila.push(actual);
            } else if (actual == '}') {
                if (pila.isEmpty()) {
                    return false;
                }
                pila.pop();
            }
        }
        return pila.isEmpty();
    }
}
