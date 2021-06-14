package domain.servicios.clima.providers;

import domain.qmp.exceptions.AlertaDesconocidaException;
import domain.qmp.exceptions.UnidadDesconocidaException;
import domain.servicios.clima.accuweather.AccuWeatherAPI;
import domain.servicios.clima.interfaces.ServicioClima;
import domain.servicios.clima.objetos.AlertaMeteorologico;
import domain.servicios.clima.objetos.EstadoClima;
import domain.servicios.clima.objetos.SuscriptorClima;
import domain.util.CacheResultados;
import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ServicioClimaAccuWeather implements ServicioClima {
  private final AccuWeatherAPI api;
  private final CacheResultados<String, EstadoClima> cacheEstadoClimatico;
  private final List<SuscriptorClima> suscriptores = new ArrayList<>();

  public ServicioClimaAccuWeather(AccuWeatherAPI api) {
    this.api = api;
    cacheEstadoClimatico = new CacheResultados<>(Duration.ofHours(3), this::actualizarEstado);
  }

  private static BigDecimal convertirACelsius(Double tempValue, String unit) {
    switch (unit) {
      case "K":
        return BigDecimal.valueOf(tempValue - 273.15f);
      case "F":
        return BigDecimal.valueOf((tempValue - 32.0f) * 5.0f / 9.0f);
      case "C":
        return BigDecimal.valueOf(tempValue);
      default:
        throw new UnidadDesconocidaException("Unidad de temperatura desconocida: " + unit);
    }
  }

  private static AlertaMeteorologico convertirAlerta(String alerta) {
    switch (alerta) {
      case "HAIL":
        return AlertaMeteorologico.GRANIZO;
      case "STORM":
        return AlertaMeteorologico.TORMENTA;
      case "TORNADO":
        return AlertaMeteorologico.TORNADO;
      case "HURRICANE":
        return AlertaMeteorologico.HURACAN;
      case "EARTHQUAKE":
        return AlertaMeteorologico.TERREMOTO;
      case "VOLCANO":
        return AlertaMeteorologico.VOLCAN;
      default:
        throw new AlertaDesconocidaException("Alerta meteorologica no reconocida: " + alerta);
    }
  }

  @Override
  public void agregarSuscriptor(SuscriptorClima suscriptor) {
    suscriptores.add(suscriptor);
  }

  @Override
  public void removerSuscriptor(SuscriptorClima suscriptor) {
    suscriptores.remove(suscriptor);
  }

  private EstadoClima actualizarEstado(String ciudad) {
    BigDecimal temperatura = temperaturaActual(ciudad);
    List<AlertaMeteorologico> alertas = alertasActuales(ciudad);
    EstadoClima estadoClima = new EstadoClima(temperatura, alertas);
    alertarSuscriptores(estadoClima);
    return estadoClima;
  }

  private void alertarSuscriptores(EstadoClima estadoClima) {
    suscriptores.forEach(suscriptor -> suscriptor.cambioCondiciones(estadoClima));
  }

  private BigDecimal temperaturaActual(String ciudad) {
    List<Map<String, Object>> resultadoApi = this.api.getWeather(ciudad);
    Map<String, Object> temperatureData =
        (Map<String, Object>) resultadoApi.get(0).get("Temperature");
    String unit = (String) temperatureData.get("Unit");
    Double tempValue = ((Number) temperatureData.get("Value")).doubleValue();
    return convertirACelsius(tempValue, unit);
  }

  private List<AlertaMeteorologico> alertasActuales(String ciudad) {
    Map<String, List<String>> resultadoApi = this.api.getAlerts(ciudad);
    List<String> resultadoAlertas = resultadoApi.get("CurrentAlerts");
    return resultadoAlertas.stream().map(ServicioClimaAccuWeather::convertirAlerta)
                           .collect(Collectors.toList());
  }

  @Override
  public Collection<AlertaMeteorologico> obtenerAlertas(String ciudad, LocalDateTime now) {
    EstadoClima estadoActual = cacheEstadoClimatico.update(ciudad, now);
    return estadoActual.getAlertas();
  }

  @Override
  public EstadoClima estadoClimatico(String ciudad, LocalDateTime now) {
    return cacheEstadoClimatico.get(ciudad, now);
  }
}
