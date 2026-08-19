package ucu.edu.aed.Ejercicio19;

import ucu.edu.aed.implementaciones.Pila;


public class AnalizadorSintatico {

    public static boolean controlCorchetes(String caracteres){
        Pila<Character> pila = new Pila<>();

        if (caracteres == null){
            return true;
        }

        for (int i = 0; i < caracteres.length(); i++){
            char actual = caracteres.charAt(i);

            if (actual == '{'){
                pila.mete(actual);
            }
            else{
                if (actual == '}'){
                    if (pila.esVacio()){
                        return false;
                    }
                    pila.saca();
                }
            }
        }
        return true;
    }
}
