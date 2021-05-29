package domain.qmp.usuarios;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import domain.qmp.atuendos.GeneradorAtuendos;
import domain.services.ServicioClima;
import java.time.LocalDateTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GuardarropaTests {
  Guardarropa guardarropa;
  GeneradorAtuendos generador;
  ServicioClima servicioClima;

  @BeforeEach
  void initFixture() {
    generador = mock(GeneradorAtuendos.class);
    servicioClima = mock(ServicioClima.class);
    guardarropa = new Guardarropa("Ropa de Verano", generador, servicioClima);
  }

  @Test
  void sugerirAtuendoSugiereRopaDeTemporada() {
    guardarropa.sugerirAtuendo("Buenos Aires", LocalDateTime.now());
    verify(generador).generarAtuendoDesde(any());
  }

  @Test
  void unGuardarropaTieneUnNombre() {
    assertEquals("Ropa de Verano", guardarropa.getNombre());
  }
}
