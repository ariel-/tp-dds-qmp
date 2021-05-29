package domain.util;

import java.time.LocalDateTime;

public class CacheEntry<ResultadoType> {
  private final ResultadoType resultado;
  private final LocalDateTime expiration;

  public CacheEntry(ResultadoType resultado, LocalDateTime expiration) {
    this.resultado = resultado;
    this.expiration = expiration;
  }

  public boolean expiro(LocalDateTime now) {
    // no antes de = ahora o luego
    // es decir si mi fecha de expiración es despues o igual a ahora, ya expiré
    return !now.isBefore(expiration);
  }

  public ResultadoType get() {
    return resultado;
  }
}
