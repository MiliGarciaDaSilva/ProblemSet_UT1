package org.example;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

import ucu.edu.aed.implementaciones.ColaCircular;
import java.util.NoSuchElementException;

public class ColaCircularTest {

  @Test
  public void colaRecienCreadaEsVacia() {
    ColaCircular<Integer> cola = new ColaCircular<>(3);

    assertTrue(cola.esVacio());
    assertEquals(0, cola.tamaño());
  }

  @Test
  public void poneEnColaYFrenteRespetaOrden() {
    ColaCircular<Integer> cola = new ColaCircular<>(3);
    cola.poneEnCola(10);
    cola.poneEnCola(20);

    assertEquals(Integer.valueOf(10), cola.frente());
  }

  @Test
  public void quitaDeColaDevuelveElementosEnOrden() {
    ColaCircular<Integer> cola = new ColaCircular<>(3);
    cola.poneEnCola(10);
    cola.poneEnCola(20);
    cola.poneEnCola(30);

    assertEquals(Integer.valueOf(10), cola.quitaDeCola());
    assertEquals(Integer.valueOf(20), cola.quitaDeCola());
    assertEquals(1, cola.tamaño());
  }

  @Test(expected = NoSuchElementException.class)
  public void quitaDeColaEnColaVacia() {
    ColaCircular<Integer> cola = new ColaCircular<>(3);
    cola.quitaDeCola();
  }

  @Test(expected = NoSuchElementException.class)
  public void frenteEnColaVacia() {
    ColaCircular<Integer> cola = new ColaCircular<>(3);
    cola.frente();
  }

  @Test
  public void poneEnColaConColaLlenaDevuelveFalse() {
    ColaCircular<Integer> cola = new ColaCircular<>(3);
    cola.poneEnCola(10);
    cola.poneEnCola(20);
    cola.poneEnCola(30);

    assertFalse(cola.poneEnCola(40));
    assertEquals(3, cola.tamaño());
    assertEquals(Integer.valueOf(10), cola.frente());
  }

  @Test
  public void poneEnColaLuegoDeVaciarUnLugarVuelveAAdmitirElementos() {
    ColaCircular<Integer> cola = new ColaCircular<>(3);
    cola.poneEnCola(10);
    cola.poneEnCola(20);
    cola.poneEnCola(30);

    cola.quitaDeCola();

    assertTrue(cola.poneEnCola(40));
    assertEquals(3, cola.tamaño());
  }

  @Test
  public void wraparoundDelVectorMantieneOrdenFifo() {
    ColaCircular<Integer> cola = new ColaCircular<>(3);
    cola.poneEnCola(10);
    cola.poneEnCola(20);
    cola.poneEnCola(30);

    // front y rear coinciden en el índice 0: rear dio la vuelta al vector.
    assertEquals(Integer.valueOf(10), cola.quitaDeCola());
    assertEquals(Integer.valueOf(20), cola.quitaDeCola());
    cola.poneEnCola(40);
    cola.poneEnCola(50);

    assertEquals(3, cola.tamaño());
    assertEquals(Integer.valueOf(30), cola.quitaDeCola());
    assertEquals(Integer.valueOf(40), cola.quitaDeCola());
    assertEquals(Integer.valueOf(50), cola.quitaDeCola());
    assertTrue(cola.esVacio());
  }

  @Test
  public void wraparoundPermiteLlenarLaColaNuevamente() {
    ColaCircular<Integer> cola = new ColaCircular<>(3);
    cola.poneEnCola(1);
    cola.poneEnCola(2);
    cola.quitaDeCola();
    cola.quitaDeCola();

    cola.poneEnCola(3);
    cola.poneEnCola(4);
    cola.poneEnCola(5);

    assertFalse(cola.poneEnCola(6));
    assertEquals(3, cola.tamaño());
  }

  @Test
  public void vaciarDejaLaColaEnEstadoInicial() {
    ColaCircular<Integer> cola = new ColaCircular<>(3);
    cola.poneEnCola(10);
    cola.poneEnCola(20);

    cola.vaciar();

    assertTrue(cola.esVacio());
    assertEquals(0, cola.tamaño());
    assertTrue(cola.poneEnCola(99));
    assertEquals(Integer.valueOf(99), cola.frente());
  }

  @Test
  public void obtenerRespetaElOrdenLogicoConWraparound() {
    ColaCircular<Integer> cola = new ColaCircular<>(3);
    cola.poneEnCola(1);
    cola.poneEnCola(2);
    cola.quitaDeCola();
    cola.poneEnCola(3);
    cola.poneEnCola(4);

    assertEquals(Integer.valueOf(2), cola.obtener(0));
    assertEquals(Integer.valueOf(3), cola.obtener(1));
    assertEquals(Integer.valueOf(4), cola.obtener(2));
  }

  @Test(expected = IndexOutOfBoundsException.class)
  public void obtenerFueraDeRangoLanzaExcepcion() {
    ColaCircular<Integer> cola = new ColaCircular<>(3);
    cola.poneEnCola(1);

    cola.obtener(1);
  }

  @Test
  public void agregarSinIndiceEquivaleAPonerEnCola() {
    ColaCircular<Integer> cola = new ColaCircular<>(3);
    cola.agregar(1);
    cola.agregar(2);

    assertEquals(2, cola.tamaño());
    assertEquals(Integer.valueOf(1), cola.frente());
  }

  @Test(expected = IllegalStateException.class)
  public void agregarConColaLlenaLanzaExcepcion() {
    ColaCircular<Integer> cola = new ColaCircular<>(2);
    cola.agregar(1);
    cola.agregar(2);

    cola.agregar(3);
  }

  @Test
  public void agregarConIndiceInsertaEnPosicionConWraparound() {
    ColaCircular<Integer> cola = new ColaCircular<>(4);
    cola.poneEnCola(1);
    cola.poneEnCola(2);
    cola.quitaDeCola();
    cola.poneEnCola(3);
    cola.poneEnCola(4);

    // Orden lógico antes de insertar: [2, 3, 4]
    cola.agregar(1, 99);

    assertEquals(4, cola.tamaño());
    assertEquals(Integer.valueOf(2), cola.obtener(0));
    assertEquals(Integer.valueOf(99), cola.obtener(1));
    assertEquals(Integer.valueOf(3), cola.obtener(2));
    assertEquals(Integer.valueOf(4), cola.obtener(3));
  }

  @Test
  public void removerPorIndiceMantieneElOrdenDelResto() {
    ColaCircular<Integer> cola = new ColaCircular<>(4);
    cola.poneEnCola(1);
    cola.poneEnCola(2);
    cola.quitaDeCola();
    cola.poneEnCola(3);
    cola.poneEnCola(4);

    // Orden lógico antes de remover: [2, 3, 4]
    Integer removido = cola.remover(1);

    assertEquals(Integer.valueOf(3), removido);
    assertEquals(2, cola.tamaño());
    assertEquals(Integer.valueOf(2), cola.obtener(0));
    assertEquals(Integer.valueOf(4), cola.obtener(1));

    // La cola sigue siendo utilizable después de remover del medio.
    cola.poneEnCola(5);
    cola.poneEnCola(6);
    assertEquals(4, cola.tamaño());
    assertEquals(Integer.valueOf(6), cola.obtener(3));
  }

  @Test
  public void removerPorElementoExistenteEInexistente() {
    ColaCircular<Integer> cola = new ColaCircular<>(3);
    cola.poneEnCola(1);
    cola.poneEnCola(2);
    cola.poneEnCola(3);

    assertTrue(cola.remover(Integer.valueOf(2)));
    assertEquals(2, cola.tamaño());
    assertEquals(Integer.valueOf(1), cola.obtener(0));
    assertEquals(Integer.valueOf(3), cola.obtener(1));

    assertFalse(cola.remover(Integer.valueOf(99)));
  }

  @Test
  public void contieneEIndiceDeReflejanElEstadoActual() {
    ColaCircular<Integer> cola = new ColaCircular<>(3);
    cola.poneEnCola(10);
    cola.poneEnCola(20);

    assertTrue(cola.contiene(20));
    assertEquals(1, cola.indiceDe(20));

    assertFalse(cola.contiene(99));
    assertEquals(-1, cola.indiceDe(99));
  }

  @Test
  public void buscarDevuelveElPrimerElementoQueCumpleElCriterio() {
    ColaCircular<Integer> cola = new ColaCircular<>(4);
    cola.poneEnCola(1);
    cola.poneEnCola(2);
    cola.poneEnCola(3);
    cola.poneEnCola(4);

    Integer encontrado = cola.buscar(n -> n % 2 == 0);

    assertEquals(Integer.valueOf(2), encontrado);
    assertEquals(null, cola.buscar(n -> n > 100));
  }

  @Test
  public void ordenarDevuelveUnaNuevaColaOrdenadaSinModificarLaOriginal() {
    ColaCircular<Integer> cola = new ColaCircular<>(4);
    cola.poneEnCola(30);
    cola.poneEnCola(10);
    cola.poneEnCola(20);

    ColaCircular<Integer> ordenada = (ColaCircular<Integer>) cola.ordenar((a, b) -> a - b);

    assertEquals(Integer.valueOf(10), ordenada.obtener(0));
    assertEquals(Integer.valueOf(20), ordenada.obtener(1));
    assertEquals(Integer.valueOf(30), ordenada.obtener(2));

    // La cola original no cambió de orden.
    assertEquals(Integer.valueOf(30), cola.obtener(0));
    assertEquals(Integer.valueOf(10), cola.obtener(1));
    assertEquals(Integer.valueOf(20), cola.obtener(2));
  }
}
