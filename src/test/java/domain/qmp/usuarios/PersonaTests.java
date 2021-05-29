package domain.qmp.usuarios;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verifyNoInteractions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PersonaTests {
  Persona persona;
  Guardarropa guardarropa;

  @BeforeEach
  void initFixture() {
    persona = new Persona();
    guardarropa = mock(Guardarropa.class);
  }

  @Test
  void siLaPersonaNoAceptaPropuestasElGuardarropaNoSeModifica() {
    persona.agregarGuardarropa(guardarropa);
    persona.quitarGuardarropa(guardarropa);
    persona.getPropuestasPendientes();
    verifyNoInteractions(guardarropa);
  }
}
