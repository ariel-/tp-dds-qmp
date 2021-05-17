package domain.qmp.sugerencias;

import domain.services.ServicioClima;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Collection;

public class GeneradorSugerencias {
  private final ServicioClima servicioClima;

  public GeneradorSugerencias(ServicioClima servicioClima) {
    this.servicioClima = servicioClima;
  }

  public Collection<Sugerencia> generarSugerencias() {
    throw new NotImplementedException();
  }
}
