package domain.qmp.usuarios;

import domain.servicios.clima.interfaces.ServicioClima;
import domain.servicios.clima.objetos.SuscriptorClima;
import java.util.Collection;

public class Notificaciones {
  private final ServicioClima servicioClima;
  private final Collection<SuscriptorClima> notificadores;

  public Notificaciones(ServicioClima servicioClima, Collection<SuscriptorClima> notificadores) {
    this.servicioClima = servicioClima;
    this.notificadores = notificadores;
  }

  public void desregistrar() {
    notificadores.forEach(servicioClima::removerSuscriptor);
  }

  public void registrar() {
    notificadores.forEach(servicioClima::agregarSuscriptor);
  }
}
