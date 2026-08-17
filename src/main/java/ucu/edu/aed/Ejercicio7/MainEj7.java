package ucu.edu.aed.Ejercicio7;

public class MainEj7 {
  public static void main(String[] args) {
    Funcion f = new Funcion();
    f.cargarDesdeArchivo("src\\resources\\numeros.txt");
    f.ordenar();
    f.mostrarResultados();
  }
}
