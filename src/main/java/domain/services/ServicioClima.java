package domain.services;

import java.time.LocalDateTime;

public interface ServicioClima {
  int temperatura(String ciudad, LocalDateTime now);
}
