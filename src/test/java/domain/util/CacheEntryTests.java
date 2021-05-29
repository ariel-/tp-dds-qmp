package domain.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import org.junit.jupiter.api.Test;

public class CacheEntryTests {
  private final LocalDate moonLandingDate = LocalDate.of(1969, 7, 20);
  private final LocalDateTime moonLanding =
      LocalDateTime.of(moonLandingDate, LocalTime.of(20, 17, 40));
  private final CacheEntry<Integer> entry = new CacheEntry<>(42, moonLanding);

  @Test
  void unaEntryEstaExpiradaSiEsLaHoraDeExpiracion() {
    assertTrue(entry.expiro(moonLanding));
  }

  @Test
  void unaEntryNoEstaExpiradaSiNoPasoSuHoraDeExpiracion() {
    assertFalse(entry.expiro(moonLanding.minusSeconds(1)));
  }

  @Test
  void unaEntryAlmacenaSuValor() {
    assertEquals(42, entry.get());
  }
}
