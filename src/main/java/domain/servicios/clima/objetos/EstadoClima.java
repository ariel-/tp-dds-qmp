package domain.servicios.clima.objetos;

import java.math.BigDecimal;
import java.util.Collection;

public class EstadoClima {
  private final BigDecimal temperatura;
  private final Collection<AlertaMeteorologico> alertas;

  public EstadoClima(BigDecimal temperatura, Collection<AlertaMeteorologico> alertas) {
    this.temperatura = temperatura;
    this.alertas = alertas;
  }

  public BigDecimal getTemperatura() {
    return temperatura;
  }

  public Collection<AlertaMeteorologico> getAlertas() {
    return alertas;
  }
}
