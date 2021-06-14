package domain.servicios.clima.interfaces;

import domain.servicios.clima.objetos.AlertaMeteorologico;
import domain.servicios.clima.objetos.EstadoClima;
import domain.servicios.clima.objetos.SuscriptorClima;
import java.time.LocalDateTime;
import java.util.Collection;

public interface ServicioClima {
  Collection<AlertaMeteorologico> obtenerAlertas(String ciudad, LocalDateTime now);

  EstadoClima estadoClimatico(String ciudad, LocalDateTime now);

  void agregarSuscriptor(SuscriptorClima suscriptor);

  void removerSuscriptor(SuscriptorClima suscriptor);
}
