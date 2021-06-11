package domain.qmp.usuarios;

import domain.qmp.atuendos.Atuendo;
import domain.qmp.atuendos.GeneradorAtuendos;
import domain.qmp.prendas.Prenda;
import domain.servicios.clima.interfaces.ServicioClima;
import domain.servicios.clima.objetos.EstadoClima;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class Guardarropa {
  private final String nombre;
  private final GeneradorAtuendos generador;
  private final ServicioClima servicioClima;
  private final Set<Prenda> prendas = new HashSet<>();

  public Guardarropa(String nombre, GeneradorAtuendos generador, ServicioClima servicioClima) {
    this.nombre = nombre;
    this.generador = generador;
    this.servicioClima = servicioClima;
  }

  public String getNombre() {
    return nombre;
  }

  public void agregarPrenda(Prenda prenda) {
    prendas.add(prenda);
  }

  public void quitarPrenda(Prenda prenda) {
    prendas.remove(prenda);
  }

  public Atuendo sugerirAtuendo(String ciudad, LocalDateTime now) {
    EstadoClima estadoClimatico = servicioClima.estadoClimatico(ciudad, now);
    return obtenerAtuendoSegunCondiciones(estadoClimatico);
  }

  private Atuendo obtenerAtuendoSegunCondiciones(EstadoClima estadoClima) {
    return generador.generarAtuendoDesde(
        prendas.stream().filter(prenda -> prenda.aptaParaClima(estadoClima))
               .collect(Collectors.toList()));
  }
}
