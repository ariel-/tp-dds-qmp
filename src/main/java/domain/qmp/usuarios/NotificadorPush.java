package domain.qmp.usuarios;

import domain.servicios.clima.objetos.AlertaMeteorologico;
import domain.servicios.clima.objetos.EstadoClima;
import domain.servicios.clima.objetos.SuscriptorClima;
import domain.servicios.notificaciones.NotificationService;
import java.util.Collection;

public class NotificadorPush implements SuscriptorClima {
  private final NotificationService notificationService;

  public NotificadorPush(NotificationService notificationService) {
    this.notificationService = notificationService;
  }

  @Override
  public void cambioCondiciones(EstadoClima estadoClima) {
    Collection<AlertaMeteorologico> alertas = estadoClima.getAlertas();
    alertarPorGranizo(alertas);
    alertarPorLluvia(alertas);
  }

  private void alertarPorLluvia(Collection<AlertaMeteorologico> alertas) {
    if (alertas.contains(AlertaMeteorologico.TORMENTA)) {
      notificationService.notify("Llevate paraguas que llueve!");
    }
  }

  private void alertarPorGranizo(Collection<AlertaMeteorologico> alertas) {
    if (alertas.contains(AlertaMeteorologico.GRANIZO)) {
      notificationService.notify("No salgas en auto que caen cascotes!!");
    }
  }
}
