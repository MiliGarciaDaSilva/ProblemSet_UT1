package ucu.edu.aed.Ejercicio24;

import ucu.edu.aed.implementaciones.Conjunto;
import ucu.edu.aed.tda.TDAConjunto;

public class MainEjercicio24 {

    public static void main(String[] args) {

        Conjunto<TAlumno> aed1 = new Conjunto<>();
        Conjunto<TAlumno> pf = new Conjunto<>();

        TAlumno alumno1 = new TAlumno(1234, "Juan", "Perez");
        TAlumno alumno2 = new TAlumno(2345, "Ana", "Gomez");
        TAlumno alumno3 = new TAlumno(3456, "Pedro", "Rodriguez");
        TAlumno alumno4 = new TAlumno(4567, "Maria", "Fernandez");
        TAlumno alumno5 = new TAlumno(5678, "Lucas", "Martinez");

        aed1.agregar(alumno1);
        aed1.agregar(alumno2);
        aed1.agregar(alumno3);

        pf.agregar(alumno3);
        pf.agregar(alumno4);
        pf.agregar(alumno5);

        System.out.println("Alumnos de AED1:");
        for (int i = 0; i < aed1.tamaño(); i++) {
            System.out.println(aed1.obtener(i));
        }

        System.out.println("\nAlumnos de PF:");
        for (int i = 0; i < pf.tamaño(); i++) {
            System.out.println(pf.obtener(i));
        }

        TDAConjunto<TAlumno> union = aed1.union(pf);

        System.out.println("\nAlumnos matriculados en AED1 o PF:");
        for (int i = 0; i < union.tamaño(); i++) {
            System.out.println(union.obtener(i));
        }

        TDAConjunto<TAlumno> interseccion = aed1.interseccion(pf);

        System.out.println("\nAlumnos matriculados en ambos cursos:");
        for (int i = 0; i < interseccion.tamaño(); i++) {
            System.out.println(interseccion.obtener(i));
        }
    }
}