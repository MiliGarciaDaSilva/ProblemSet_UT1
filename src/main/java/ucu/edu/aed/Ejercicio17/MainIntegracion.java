package ucu.edu.aed.Ejercicio17;

import ucu.edu.aed.tda.TDALista;

public class MainIntegracion {

    public static void main(String[] args){
        Biblioteca biblioteca = new Biblioteca();

        Adquisiciones adquisiciones = new Adquisiciones();
        String rutaAdquisiciones = "src/main/resources ej17/adquisiciones.txt";
        double valorTotalAgregado = adquisiciones.procesarArchivo(rutaAdquisiciones, biblioteca);

        Prestamos prestamos = new Prestamos();
        String rutaPrestamos = "src/main/resources ej17/prestamos.txt";
        int variacionTotalPrestados = prestamos.procesarArchivo(rutaPrestamos, biblioteca);

        System.out.println("Valor total agregado al stock: " + valorTotalAgregado);
        System.out.println("Variación total de ejemplares prestados: " + variacionTotalPrestados);
        System.out.println("Catálogo final:");
        TDALista<Libro> catalogo = biblioteca.listarPorTitulo();
        for (int i = 0; i < catalogo.tamaño(); i++){
            System.out.println(catalogo.obtener(i));
        }
    }
}