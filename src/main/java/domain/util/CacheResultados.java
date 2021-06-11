package domain.util;

import java.time.LocalDateTime;
import java.time.temporal.TemporalAmount;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class CacheResultados<KeyType, ResultadoType> {
  private final Map<KeyType, CacheEntry<ResultadoType>> cache;
  private final TemporalAmount duration;
  private final Function<KeyType, ResultadoType> newResultSupplier;

  public CacheResultados(TemporalAmount duration,
      Function<KeyType, ResultadoType> newResultSupplier) {
    this.cache = new HashMap<>();
    this.duration = duration;
    this.newResultSupplier = newResultSupplier;
  }

  public ResultadoType get(KeyType key, LocalDateTime now) {
    CacheEntry<ResultadoType> cacheEntry;
    if (cache.containsKey(key)) {
      cacheEntry = cache.get(key);
      if (!cacheEntry.expiro(now)) {
        return cacheEntry.get();
      }
    }
    // si no la contiene o lo hizo pero ya expiro, recalculo!
    ResultadoType resultado = newResultSupplier.apply(key);
    cacheEntry = new CacheEntry<>(resultado, now.plus(duration));
    cache.put(key, cacheEntry);
    return resultado;
  }

  public int size() {
    return cache.size();
  }
}
