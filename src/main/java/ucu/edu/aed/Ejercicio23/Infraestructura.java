package ucu.edu.aed.Ejercicio23;

import ucu.edu.aed.implementaciones.ListaEnlazada;

public class Infraestructura {

    ListaEnlazada<String> sucursales;

    public Infraestructura(){
        sucursales = new ListaEnlazada<>();
    }

    public void agregarSucursal(String sucursalNueva){
        sucursales.agregar(sucursalNueva);
    }

    public boolean buscarSucursal(String sucursalBuscada){
        return sucursales.contiene(sucursalBuscada);
    }

    public boolean quitarSucursal(String sucursalBuscada){
        return sucursales.remover(sucursalBuscada);
    }

    public void listarSucursales(){
        int i = 0;
        while (i < sucursales.tamaño()){
            System.out.println(sucursales.obtener(i));
            i++;
        }
    }

    public int numeroSucursales(){
        return sucursales.tamaño();
    }

    public boolean directorioVacio(){
        return sucursales.esVacio();
    }
}