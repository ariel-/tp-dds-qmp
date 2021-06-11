package domain.servicios.clima.interfaces;

import domain.servicios.clima.objetos.EstadoClima;
import domain.servicios.clima.objetos.SuscriptorClima;
import java.time.LocalDateTime;

public interface ServicioClima {
  EstadoClima estadoClimatico(String ciudad, LocalDateTime now);

  void agregarSuscriptor(SuscriptorClima suscriptor);

  void removerSuscriptor(SuscriptorClima suscriptor);
}
