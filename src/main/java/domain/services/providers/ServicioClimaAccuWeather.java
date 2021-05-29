package domain.services.providers;

import domain.qmp.exceptions.UnidadDesconocidaException;
import domain.services.ServicioClima;
import domain.services.accuweather.AccuWeatherAPI;
import domain.util.CacheResultados;
import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class ServicioClimaAccuWeather implements ServicioClima {
  private final AccuWeatherAPI api;
  private final CacheResultados<String, BigDecimal> temperaturaCiudadCache;

  public ServicioClimaAccuWeather(AccuWeatherAPI api) {
    this.api = api;
    temperaturaCiudadCache = new CacheResultados<>(Duration.ofHours(3), ciudad -> {
      List<Map<String, Object>> resultados = this.api.getWeather(ciudad);
      Map<String, Object> temperatureData =
          (Map<String, Object>) resultados.get(0).get("Temperature");
      String unit = (String) temperatureData.get("Unit");
      Double tempValue = ((Number) temperatureData.get("Value")).doubleValue();
      return convertirACelsius(tempValue, unit);
    });
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

  @Override
  public BigDecimal temperatura(String ciudad, LocalDateTime now) {
    return temperaturaCiudadCache.get(ciudad, now);
  }
}
