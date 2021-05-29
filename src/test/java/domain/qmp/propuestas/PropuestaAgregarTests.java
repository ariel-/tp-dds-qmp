package domain.qmp.propuestas;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import domain.qmp.prendas.Prenda;
import domain.qmp.usuarios.Guardarropa;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PropuestaAgregarTests {
  Propuesta propuesta;
  Guardarropa guardarropa;
  Prenda prenda;

  @BeforeEach
  void initFixture() {
    guardarropa = mock(Guardarropa.class);
    prenda = mock(Prenda.class);
    propuesta = new PropuestaAgregar(guardarropa, prenda);
  }

  @Test
  void aplicarPropuestaAgregaPrendaAGuardarropa() {
    propuesta.apply();
    verify(guardarropa).agregarPrenda(prenda);
  }

  @Test
  void deshacerPropuestaQuitaLaPrendaDelGuardarropa() {
   propuesta.deshacerPropuesta();
   verify(guardarropa).quitarPrenda(prenda);
  }
}
