package domain.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CacheResultadosTests {
  private CacheResultados<String, Integer> cache;
  private LocalDateTime ahora = LocalDateTime.of(2020, 1, 1, 20, 20);

  @BeforeEach
  void inicializarNueva() {
    cache = new CacheResultados<>(Duration.ofSeconds(1), key -> 42);
  }

  @Test
  void unMissEnCacheCreaUnaNuevaEntry() {
    assertEquals(0, cache.size());
    cache.get("banana", ahora);
    assertEquals(1, cache.size());
  }

  @Test
  void laCacheDevuelveElResultadoEsperado() {
    assertEquals(42, cache.get("la respuesta a todo", ahora));
  }
}
