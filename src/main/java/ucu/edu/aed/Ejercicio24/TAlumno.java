package ucu.edu.aed.Ejercicio24;

import java.util.Objects;

public class TAlumno {

    private int cedula;
    private String nombre;
    private String apellido;

    public TAlumno(int cedula, String nombre, String apellido) {
        if (cedula < 1000 || cedula > 9999) {
            throw new IllegalArgumentException("La cédula debe tener 4 dígitos.");
        }

        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public int getCedula() {
        return cedula;
    }

    public void setCedula(int cedula) {
        if (cedula < 1000 || cedula > 9999) {
            throw new IllegalArgumentException("La cédula debe tener 4 dígitos.");
        }

        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof TAlumno)) {
            return false;
        }

        TAlumno alumno = (TAlumno) o;

        return cedula == alumno.cedula;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cedula);
    }

    @Override
    public String toString() {
        return cedula + " - " + nombre + " " + apellido;
    }
}