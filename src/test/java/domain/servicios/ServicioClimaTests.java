package domain.servicios;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import domain.servicios.clima.interfaces.ServicioClima;
import domain.servicios.clima.objetos.EstadoClima;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ServicioClimaTests {
  ServicioClima servicioClima;

  @BeforeEach
  void init() {
    servicioClima = mock(ServicioClima.class);
    when(servicioClima.estadoClimatico(eq("Buenos Aires"), any()))
        .thenReturn(new EstadoClima(new BigDecimal("20"), null));
  }

  @Test
  void unServicioDevuelveLaTemperatura() {
    EstadoClima estadoClima = servicioClima.estadoClimatico("Buenos Aires", LocalDateTime.now());
    assertEquals(0, estadoClima.getTemperatura().compareTo(BigDecimal.valueOf(20)));
  }
}
