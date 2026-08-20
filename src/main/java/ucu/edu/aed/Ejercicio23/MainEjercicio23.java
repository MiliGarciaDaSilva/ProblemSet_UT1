package ucu.edu.aed.Ejercicio23;

import ucu.edu.aed.implementaciones.ListaEnlazada;
import ucu.edu.aed.utils.FileUtils;

public class MainEjercicio23 {

    public static void main(String[] args){
        Infraestructura infraestructura = new Infraestructura();
        infraestructura.sucursales = new ListaEnlazada<>();

        FileUtils.leerLineas("ProblemSet_UT1\\src\\main\\java\\ucu\\edu\\aed\\resources ej23\\sucursales.txt", linea -> {
            if (!linea.trim().isEmpty()){
                infraestructura.agregarSucursal(linea.trim());
            }
        });

        infraestructura.listarSucursales();
        System.out.print(infraestructura.numeroSucursales());
    }
}