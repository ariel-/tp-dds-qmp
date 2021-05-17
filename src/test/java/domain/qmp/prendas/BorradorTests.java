package domain.qmp.prendas;

import domain.qmp.exceptions.PrendaInvalidaException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class BorradorTests {
  private Borrador unBorrador;

  @BeforeEach
  void newBorradorBase() {
    unBorrador = new Borrador(Trama.LISA);
  }

  @Test
  void unBorradorDebeTenerUnTipo() {
    assertThrows(PrendaInvalidaException.class, unBorrador::crearPrenda, "El tipo no puede ser nulo.");
  }

  @Test
  void unBorradorDebeTenerUnMaterial() {
    unBorrador.setTipo(Tipo.REMERA);
    assertThrows(PrendaInvalidaException.class, unBorrador::crearPrenda, "La prenda debe tener un material");
  }

  @Test
  void elTipoyElMaterialDebenSerConsistentes() {
    unBorrador.setTipo(Tipo.REMERA)
              .setMaterial(Material.JEAN);
    assertThrows(PrendaInvalidaException.class, unBorrador::crearPrenda, "El material no es admitido por el tipo de prenda");
  }

  @Test
  void unBorradorDebeTenerUnColor() {
    unBorrador.setTipo(Tipo.REMERA)
              .setMaterial(Material.ALGODON);
    assertThrows(PrendaInvalidaException.class, unBorrador::crearPrenda, "La prenda debe tener un color primario");
  }
}
