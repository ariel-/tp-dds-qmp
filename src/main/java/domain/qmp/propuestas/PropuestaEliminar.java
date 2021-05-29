package domain.qmp.propuestas;

import domain.qmp.prendas.Prenda;
import domain.qmp.usuarios.Guardarropa;

public class PropuestaEliminar extends Propuesta {
  private final Guardarropa guardarropa;
  private final Prenda prenda;

  public PropuestaEliminar(Guardarropa guardarropa, Prenda prenda) {
    this.guardarropa = guardarropa;
    this.prenda = prenda;
  }

  @Override
  protected void apply() {
    guardarropa.quitarPrenda(prenda);
  }

  @Override
  protected void undo() {
    guardarropa.agregarPrenda(prenda);
  }
}
