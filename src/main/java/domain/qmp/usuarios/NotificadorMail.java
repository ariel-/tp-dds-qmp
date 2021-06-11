package domain.qmp.usuarios;

import domain.servicios.clima.objetos.AlertaMeteorologico;
import domain.servicios.clima.objetos.EstadoClima;
import domain.servicios.clima.objetos.SuscriptorClima;
import domain.servicios.mails.mailsender.MailSender;
import java.util.Collection;
import java.util.stream.Collectors;

public class NotificadorMail implements SuscriptorClima {
  private final MailSender mailSender;
  private final String direccionMail;

  public NotificadorMail(MailSender mailSender, String direccionMail) {
    this.mailSender = mailSender;
    this.direccionMail = direccionMail;
  }

  private static String alertasMailFriendly(Collection<AlertaMeteorologico> alertas) {
    return alertas.stream().map(AlertaMeteorologico::toString).collect(Collectors.joining(", "));
  }

  @Override
  public void cambioCondiciones(EstadoClima estadoClima) {
    mailSender.send(direccionMail,
        "Se detecto un cambio en las condiciones Climáticas, en este momento estamos "
        + "experimentando " + alertasMailFriendly(estadoClima.getAlertas()));
  }
}
