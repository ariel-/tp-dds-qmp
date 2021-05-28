package domain.services;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface ServicioClima {
  BigDecimal temperatura(String ciudad, LocalDateTime now);
}
