package domain.servicios.clima.accuweather;

import java.util.List;
import java.util.Map;

public interface AccuWeatherAPI {
  List<Map<String, Object>> getWeather(String ciudad);

  Map<String, List<String>> getAlerts(String ciudad);
}
