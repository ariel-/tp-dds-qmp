package domain.qmp.propuestas;

public abstract class Propuesta {

  private boolean aplicada = false;

  protected abstract void apply();

  protected abstract void undo();

  public boolean estaPendiente() {
    return !aplicada;
  }

  public void aceptarPropuesta() {
    apply();
    aplicada = true;
  }

  public void deshacerPropuesta() {
    undo();
    aplicada = false;
  }
}
