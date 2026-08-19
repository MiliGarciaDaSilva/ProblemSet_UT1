package ucu.edu.aed.implementaciones;

import java.util.Comparator;
import java.util.function.Predicate;

import ucu.edu.aed.tda.TDALista;

/**
 * Define un Tipo de Dato Abstracto (TDA) Lista genérica.
 *
 * <p>Una lista permite almacenar elementos en una secuencia ordenada por posición,
 * admitiendo inserciones, accesos, eliminaciones, búsquedas y operaciones de ordenamiento.</p>
 *
 * <p>Las posiciones de los elementos se interpretan mediante índices enteros.
 * Salvo que la implementación indique lo contrario, se asume indexación basada en 0.</p>
 *
 * @param <T> el tipo de los elementos almacenados en la lista
 */

public class ListaEnlazada<T> implements TDALista<T> {

    protected Nodo<T> cabeza;
    protected Nodo<T> cola;
    protected int tamanio;

    /**
     * Agrega un elemento al final de la lista.
     *
     * @param elem el elemento a agregar
     */
    @Override
    public void agregar(T elem){
        Nodo<T> nuevoNodo = new Nodo<>(elem);
        if (cabeza == null){
            cabeza = nuevoNodo;
            cola = nuevoNodo;
        }
        else{
            cola.siguiente = nuevoNodo;
            cola = nuevoNodo;
        }
        tamanio++;
    }

    /**
     * Agrega un elemento en la posición indicada.
     *
     * <p>Los elementos ubicados desde esa posición en adelante
     * desplazan su índice una posición hacia la derecha.</p>
     *
     * @param index la posición en la que se insertará el elemento
     * @param elem el elemento a agregar
     * @throws IndexOutOfBoundsException si el índice está fuera de rango
     */

    @Override
    public void agregar(int index, T elem){
        if (index < 0 || index > tamanio){
            throw new IndexOutOfBoundsException();
        }
        Nodo<T> nuevoNodo = new Nodo<>(elem);
        if (index == 0){
            nuevoNodo.siguiente = cabeza;
            cabeza = nuevoNodo;
            if (tamanio == 0){
                cola = nuevoNodo;
            }
        }
        else{
            int contador = 0;
            Nodo<T> actual = cabeza;
            while (contador < index - 1){
                actual = actual.siguiente;
                contador++;
            }
            nuevoNodo.siguiente = actual.siguiente;
            actual.siguiente = nuevoNodo;
            if (nuevoNodo.siguiente == null){
                cola = nuevoNodo;
            }
        }
        tamanio++;
    }

    /**
     * Obtiene el elemento almacenado en la posición indicada.
     *
     * @param index la posición del elemento a recuperar
     * @return el elemento ubicado en la posición indicada
     * @throws IndexOutOfBoundsException si el índice está fuera de rango
     */

    @Override
    public T obtener(int index){
        if (index < 0 || index >= tamanio){
            throw new IndexOutOfBoundsException();
        }
        Nodo<T> actual = cabeza;
        int contador = 0;
        while (contador != index){
            actual = actual.siguiente;
            contador++;
        }
        return actual.getDato();
    }

    /**
     * Remueve y devuelve el elemento almacenado en la posición indicada.
     *
     * <p>Los elementos posteriores, si existen, desplazan su índice
     * una posición hacia la izquierda.</p>
     *
     * <p>Comportamiento "Quitar": el nodo se desconecta de la lista y
     * su campo siguiente se deja en null para evitar referencias
     * residuales hacia el resto de la lista.</p>
     *
     * @param index la posición del elemento a remover
     * @return el elemento removido
     * @throws IndexOutOfBoundsException si el índice está fuera de rango
     */

    @Override
    public T remover(int index){
        if (index < 0 || index >= tamanio){
            throw new IndexOutOfBoundsException();
        }
        if (index == 0){
            Nodo<T> nodoRemovido = cabeza;
            cabeza = cabeza.siguiente;
            nodoRemovido.siguiente = null;
            if (cabeza == null){
                cola = null;
            }
            tamanio--;
            return nodoRemovido.getDato();
        }
        Nodo<T> actual = cabeza;
        int contador = 0;
        while (contador < index - 1){
            actual = actual.siguiente;
            contador++;
        }
        Nodo<T> nodoASacar = actual.siguiente;
        actual.siguiente = actual.siguiente.siguiente;
        nodoASacar.siguiente = null;
        if (actual.siguiente == null){
            cola = actual;
        }
        tamanio--;
        return nodoASacar.getDato();
    }

    /**
     * Remueve la primera ocurrencia del elemento indicado en la lista.
     *
     * <p>La comparación del elemento queda sujeta al criterio definido
     * por la implementación, normalmente mediante {@code equals}.</p>
     *
     * <p>Comportamiento "Eliminar": el nodo se desconecta de la lista y
     * su campo siguiente se deja en null para evitar referencias
     * residuales hacia el resto de la lista.</p>
     *
     * @param elem el elemento a remover
     * @return {@code true} si el elemento fue encontrado y removido;
     *         {@code false} en caso contrario
     */

    @Override
    public boolean remover(T elem){
        if (cabeza == null){
            return false;
        }
        if (cabeza.getDato().equals(elem)){
            Nodo<T> nodoRemovido = cabeza;
            cabeza = cabeza.siguiente;
            nodoRemovido.siguiente = null;
            if (cabeza == null){
                cola = null;
            }
            tamanio--;
            return true;
        }
        Nodo<T> actual = cabeza;
        while (actual.siguiente != null){
            if (actual.siguiente.getDato().equals(elem)){
                Nodo<T> nodoRemovido = actual.siguiente;
                actual.siguiente = actual.siguiente.siguiente;
                nodoRemovido.siguiente = null;
                if (actual.siguiente == null){
                    cola = actual;
                }
                tamanio--;
                return true;
            }
            actual = actual.siguiente;
        }
        return false;
    }

    /**
     * Determina si la lista contiene el elemento indicado.
     *
     * <p>La comparación del elemento queda sujeta al criterio definido
     * por la implementación, normalmente mediante {@code equals}.</p>
     *
     * @param elem el elemento a buscar
     * @return {@code true} si el elemento está presente en la lista;
     *         {@code false} en caso contrario
     */

    @Override
    public boolean contiene(T elem){
        Nodo<T> actual = cabeza;
        while (actual != null){
            if (actual.getDato().equals(elem)){
                return true;
            }
            actual = actual.siguiente;
        }
        return false;
    }

    /**
     * Retorna el índice de la primera ocurrencia del elemento indicado.
     *
     * <p>La comparación del elemento queda sujeta al criterio definido
     * por la implementación, normalmente mediante {@code equals}.</p>
     *
     * @param elem el elemento a buscar
     * @return el índice de la primera ocurrencia del elemento, o {@code -1}
     *         si el elemento no se encuentra en la lista
     */

    @Override
    public int indiceDe(T elem){
        Nodo<T> actual = cabeza;
        int contador = 0;
        while (actual != null){
            if (actual.getDato().equals(elem)){
                return contador;
            }
            contador++;
            actual = actual.siguiente;
        }
        return -1;
    }

    /**
     * Busca y retorna el primer elemento que cumple con el criterio dado.
     *
     * @param criterio el predicado que define la condición de búsqueda
     * @return el primer elemento que cumple el criterio, o {@code null}
     *         si no existe ninguno
     */

    @Override
    public T buscar(Predicate<T> criterio){
        Nodo<T> actual = cabeza;
        while (actual != null){
            if (criterio.test(actual.getDato())){
                return actual.getDato();
            }
            actual = actual.siguiente;
        }
        return null;
    }

    /**
     * Retorna una nueva lista con los elementos ordenados según el comparador dado.
     *
     * <p>El criterio de orden está determinado por el objeto {@link Comparator}
     * recibido como parámetro.</p>
     *
     * @param comparator el comparador que define el orden de los elementos
     * @return una lista ordenada según el criterio indicado
     */

    @Override
    public TDALista<T> ordenar(Comparator<T> comparator){
        ListaEnlazada<T> resultado = new ListaEnlazada<>();
        Nodo<T> actual = cabeza;

        while (actual != null){
            T dato = actual.getDato();

            if (resultado.cabeza == null || comparator.compare(dato, resultado.cabeza.getDato()) < 0){
                Nodo<T> nuevoNodo = new Nodo<>(dato);
                nuevoNodo.siguiente = resultado.cabeza;
                resultado.cabeza = nuevoNodo;
                if (resultado.cola == null){
                    resultado.cola = nuevoNodo;
                }
            }
            else{
                Nodo<T> actualResultado = resultado.cabeza;
                while (actualResultado.siguiente != null &&
                    comparator.compare(dato, actualResultado.siguiente.getDato()) >= 0){
                    actualResultado = actualResultado.siguiente;
                }
                Nodo<T> nuevoNodo = new Nodo<>(dato);
                nuevoNodo.siguiente = actualResultado.siguiente;
                actualResultado.siguiente = nuevoNodo;
                if (nuevoNodo.siguiente == null){
                    resultado.cola = nuevoNodo;
                }
            }

            actual = actual.siguiente;
        }

        resultado.tamanio = this.tamanio;
        return resultado;
    }

    /**
     * Retorna la cantidad de elementos almacenados en la lista.
     *
     * @return la cantidad de elementos de la lista
     */

    @Override
    public int tamaño(){
        return tamanio;
    }

    /**
     * Determina si la lista no contiene elementos.
     *
     * @return {@code true} si la lista está vacía;
     *         {@code false} en caso contrario
     */

    @Override
    public boolean esVacio(){
        return cabeza == null;
    }

    /**
     * Elimina todos los elementos de la lista.
     *
     * <p>Luego de invocar este método, la lista queda vacía.</p>
     */

    @Override
    public void vaciar(){
        cabeza = null;
        cola = null;
        tamanio = 0;
    }

}