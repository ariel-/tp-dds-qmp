package domain.qmp.propuestas;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class PropuestaTests {
  @Test
  void aceptarUnaPropuestaHaceQueDejeDeEstarPendiente() {
    Propuesta propuestaMock = new Propuesta() {
      @Override
      protected void apply() {
      }

      @Override
      protected void undo() {
      }
    };

    assertTrue(propuestaMock.estaPendiente());
    propuestaMock.aceptarPropuesta();
    assertFalse(propuestaMock.estaPendiente());
  }

  @Test
  void aplicarUnaPropuestaReflejaSusCambios() {
    final int[] num = { 40 };
    Propuesta propuestaMock = new Propuesta() {
      @Override
      protected void apply() {
        num[0] += 2;
      }

      @Override
      protected void undo() {
        num[0] -= 2;
      }
    };

    assertEquals(40, num[0]);
    propuestaMock.apply();
    assertEquals(42, num[0]);
  }

  @Test
  void deshacerUnaPropuestaDeshaceElCambio() {
    final int[] num = { 42 };
    Propuesta propuestaMock = new Propuesta() {
      @Override
      protected void apply() {
        num[0] += 2;
      }

      @Override
      protected void undo() {
        num[0] -= 2;
      }
    };

    assertEquals(42, num[0]);
    propuestaMock.undo();
    assertEquals(40, num[0]);
  }
}
