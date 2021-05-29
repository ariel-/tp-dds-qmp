package domain.qmp.propuestas;

import domain.qmp.prendas.Prenda;
import domain.qmp.usuarios.Guardarropa;

public class PropuestaAgregar extends Propuesta {
  private final Guardarropa guardarropa;
  private final Prenda prenda;

  public PropuestaAgregar(Guardarropa guardarropa, Prenda prenda) {
    this.guardarropa = guardarropa;
    this.prenda = prenda;
  }

  @Override
  protected void apply() {
    guardarropa.agregarPrenda(prenda);
  }

  @Override
  protected void undo() {
    guardarropa.quitarPrenda(prenda);
  }
}
