package ucu.edu.aed.Ejercicio27;

import ucu.edu.aed.implementaciones.ColaCircular;
import ucu.edu.aed.tda.TDACola;

public class Rueda {

    private TDACola<Cabina> cabinas;

    public Rueda(int cantidadCabinas, int capacidadPorCabina) {
        if (cantidadCabinas < 1) {
            throw new IllegalArgumentException("La rueda debe tener al menos una cabina.");
        }

        this.cabinas = new ColaCircular<>(cantidadCabinas);

        for (int i = 0; i < cantidadCabinas; i++) {
            this.cabinas.poneEnCola(new Cabina(i, capacidadPorCabina));
        }
    }

    public Cabina cabinaEnPlataforma() {
        return cabinas.frente();
    }

    public int getCantidadCabinas() {
        return cabinas.tamaño();
    }

    public void avanzar() {
        cabinas.poneEnCola(cabinas.quitaDeCola());
    }

    public int subirPasajeros(int cantidad) {
        return cabinaEnPlataforma().subir(cantidad);
    }

    public int bajarPasajeros() {
        return cabinaEnPlataforma().bajar();
    }

    @Override
    public String toString() {
        return "Rueda de " + getCantidadCabinas() + " cabinas, en plataforma: " + cabinaEnPlataforma();
    }
}