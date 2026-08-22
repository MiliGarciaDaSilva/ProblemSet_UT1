package ucu.edu.aed.Ejercicio27;

public class Cabina {

    private int idcabina;
    private int capacidad;
    private int pasajeros;

    public Cabina(int idcabina, int capacidad) {
        if (capacidad < 1){
            throw new IllegalArgumentException("La capacidad de las cabinas no puede ser menor a 1");
        }
        if (idcabina < 0){
            throw new IllegalArgumentException("El número de cabina no puede ser negativo");
        }
        this.capacidad = capacidad;
        this.idcabina = idcabina;
        this.pasajeros = 0;
    }

    public int getCapacidad(){
        return this.capacidad;
    }

    public int getIdCabina(){
        return this.idcabina;
    }

    public int getPasajeros(){
        return this.pasajeros;
    }

    public int getLugaresLibres() {
        return this.capacidad - this.pasajeros;
    }

    public int subir(int nuevosPasajeros) {
        if (nuevosPasajeros < 0) {
            throw new IllegalArgumentException("La cantidad de pasajeros no puede ser negativa.");
        }

        int suben = Math.min(nuevosPasajeros, getLugaresLibres());
        pasajeros += suben;

        return nuevosPasajeros - suben;
    }

    public int bajar() {
        int pasajerosantes = pasajeros;
        pasajeros = 0;
        return pasajerosantes;
    }

    @Override
    public String toString() {
        return "Cabina " + idcabina + " (" + pasajeros + "/" + capacidad + ")";
    }
}