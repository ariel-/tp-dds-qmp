package domain.util;

import java.time.LocalDateTime;
import java.time.temporal.TemporalAmount;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * @param <KeyType>       tipo de las claves
 * @param <ResultadoType> tipo de los valores cacheados
 */
public class CacheResultados<KeyType, ResultadoType> {
  private final Map<KeyType, CacheEntry<ResultadoType>> cache;
  private final TemporalAmount duration;
  private final Function<KeyType, ResultadoType> newResultSupplier;

  /**
   * @param duration          Duración de la entrada en caché
   * @param newResultSupplier función k -> v, generadora de valores cuando hay un miss
   */
  public CacheResultados(TemporalAmount duration,
      Function<KeyType, ResultadoType> newResultSupplier) {
    this.cache = new HashMap<>();
    this.duration = duration;
    this.newResultSupplier = newResultSupplier;
  }

  /**
   * @param key clave cuyo valor se desea recuperar
   * @param now LocalDateTime actual
   * @return Valor cacheado, o bien generado luego de producido un miss, en cualquier caso el
   * valor quedará en cache para futuros accesos (hasta que expire)
   */
  public ResultadoType get(KeyType key, LocalDateTime now) {
    CacheEntry<ResultadoType> cacheEntry = cache.get(key);
    if (cacheEntry != null && cacheEntry.entradaValida(now)) {
      return cacheEntry.get();
    }

    // si no la contiene o bien expiro, recalculo!
    return update(key, now);
  }

  /**
   * @param key clave cuyo valor se desea actualizar
   * @param now LocalDateTime actual
   * @return Valor generado, esta función siempre devolverá un valor actualizado, como si se
   * hubiera producido un miss, luego el valor se actualiza también en la cache
   */
  public ResultadoType update(KeyType key, LocalDateTime now) {
    ResultadoType resultado = newResultSupplier.apply(key);
    CacheEntry<ResultadoType> cacheEntry = new CacheEntry<>(resultado, now.plus(duration));
    cache.put(key, cacheEntry);
    return resultado;
  }

  /**
   * @return cantidad de entradas en la cache
   */
  public int size() {
    return cache.size();
  }
}
