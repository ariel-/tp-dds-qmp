package domain.qmp.usuarios;

import domain.qmp.propuestas.Propuesta;
import domain.servicios.clima.interfaces.ServicioClima;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Persona {
  private final Set<Guardarropa> guardarropas = new HashSet<>();
  private final Set<Propuesta> propuestas = new HashSet<>();
  private Notificaciones notificaciones;

  public Persona(ServicioClima servicioClima) {
    this.notificaciones = new Notificaciones(servicioClima, Collections.emptyList());
  }

  public void agregarGuardarropa(Guardarropa guardarropa) {
    guardarropas.add(guardarropa);
  }

  public void quitarGuardarropa(Guardarropa guardarropa) {
    guardarropas.remove(guardarropa);
  }

  public List<Propuesta> getPropuestasPendientes() {
    return propuestas.stream().filter(Propuesta::estaPendiente).collect(Collectors.toList());
  }

  public void aceptarPropuesta(Propuesta propuesta) {
    propuesta.aceptarPropuesta();
  }

  public void deshacerPropuesta(Propuesta propuesta) {
    propuesta.deshacerPropuesta();
  }

  public void rechazarPropuesta(Propuesta propuesta) {
    propuestas.remove(propuesta);
  }

  public void setNotificaciones(Notificaciones notificaciones) {
    this.notificaciones.desregistrar();
    this.notificaciones = notificaciones;
    this.notificaciones.registrar();
  }
}
