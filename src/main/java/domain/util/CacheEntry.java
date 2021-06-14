package domain.util;

import java.time.LocalDateTime;

public class CacheEntry<ResultadoType> {
  private final ResultadoType resultado;
  private final LocalDateTime expiration;

  public CacheEntry(ResultadoType resultado, LocalDateTime expiration) {
    this.resultado = resultado;
    this.expiration = expiration;
  }

  public boolean entradaValida(LocalDateTime now) {
    // es valida <=> la expiración está en el futuro
    return now.isBefore(expiration);
  }

  public boolean expiro(LocalDateTime now) {
    // expiro <=> no es válida
    return !entradaValida(now);
  }

  public ResultadoType get() {
    return resultado;
  }
}
