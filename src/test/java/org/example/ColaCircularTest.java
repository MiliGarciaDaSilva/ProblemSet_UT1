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
}
