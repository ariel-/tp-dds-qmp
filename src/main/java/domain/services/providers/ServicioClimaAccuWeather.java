package domain.services.providers;

import domain.qmp.exceptions.UnidadDesconocidaException;
import domain.services.accuweather.AccuWeatherAPI;
import domain.services.ServicioClima;
import domain.util.CacheResultados;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class ServicioClimaAccuWeather implements ServicioClima {
  private final AccuWeatherAPI implementation;
  private final CacheResultados<String, Integer> temperaturaCiudadCache;

  public ServicioClimaAccuWeather() {
    implementation = new AccuWeatherAPI();
    temperaturaCiudadCache = new CacheResultados<>(Duration.ofHours(3), ciudad -> {
      List<Map<String, Object>> resultados = implementation.getWeather(ciudad);
      Map<String, Object> temperatureData = (Map<String, Object>) resultados.get(0).get("Temperature");
      String unit = (String) temperatureData.get("Unit");
      Float tempValue = (Float) temperatureData.get("Value");
      return convertirACelsius(tempValue, unit);
    });
  }

  private static Integer convertirACelsius(Float tempValue, String unit) {
    switch (unit) {
      case "K":
        return Math.round(tempValue - 273.15f);
      case "F":
        return Math.round((tempValue - 32.0f) * 5.0f / 9.0f);
      case "C":
        return Math.round(tempValue);
      default:
        throw new UnidadDesconocidaException("Unidad de temperatura desconocida: " + unit);
    }
  }

  @Override
  public int temperatura(String ciudad, LocalDateTime now) {
    return temperaturaCiudadCache.get(ciudad, now);
  }
}
