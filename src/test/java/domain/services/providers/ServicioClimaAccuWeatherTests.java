package domain.services.providers;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import domain.services.accuweather.AccuWeatherAPI;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ServicioClimaAccuWeatherTests {
  ServicioClimaAccuWeather servicioClimaAccuWeather;
  AccuWeatherAPI api;

  @BeforeEach
  void init() {
    api = mock(AccuWeatherAPI.class);
    when(api.getWeather("Buenos Aires")).thenReturn(Arrays.asList(new HashMap<String, Object>() {{
      put("Temperature", new HashMap<String, Object>() {{
        put("Value", 30);
        put("Unit", "C");
      }});
    }}));

    servicioClimaAccuWeather = new ServicioClimaAccuWeather(api);
  }

  @Test
  @DisplayName("El adapter llama una sola vez a la API dados 2 request seguidos, abaratando costos")
  void elServicioClimaLlamaUnaVezALaAPI() {
    LocalDateTime now = LocalDateTime.now();
    servicioClimaAccuWeather.temperatura("Buenos Aires", now);
    servicioClimaAccuWeather.temperatura("Buenos Aires", now);
    verify(api, times(1)).getWeather("Buenos Aires");
  }
}
