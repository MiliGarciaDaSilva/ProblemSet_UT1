package ucu.edu.aed.Ejercicio7;

public class MainEj7 {
  public static void main(String[] args) {
    Funcion f = new Funcion();
    f.cargarDesdeArchivo("ProblemSet_UT1\\src\\main\\java\\ucu\\edu\\aed\\resources ej7\\numeros.txt");
    f.ordenar();
    f.mostrarResultados();
  }
}
