package domain.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ServicioClimaTests {
  ServicioClima servicioClima;

  @BeforeEach
  void init() {
    servicioClima = mock(ServicioClima.class);
    when(servicioClima.temperatura(eq("Buenos Aires"), any())).thenReturn(new BigDecimal("20"));
  }

  @Test
  void unServicioDevuelveLaTemperatura() {
    BigDecimal temp = servicioClima.temperatura("Buenos Aires", LocalDateTime.now());
    assertEquals(0, temp.compareTo(BigDecimal.valueOf(20)));
  }
}
