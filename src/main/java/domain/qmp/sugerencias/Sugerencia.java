package domain.qmp.sugerencias;

import domain.qmp.prendas.Prenda;

public class Sugerencia {
  private final Prenda parteSuperior;
  private final Prenda parteInferior;
  private final Prenda calzado;
  private final Prenda accesorio;

  public Sugerencia(Prenda parteSuperior, Prenda parteInferior, Prenda calzado, Prenda accesorio) {
    this.parteSuperior = parteSuperior;
    this.parteInferior = parteInferior;
    this.calzado = calzado;
    this.accesorio = accesorio;
  }

  public Prenda getParteSuperior() {
    return parteSuperior;
  }

  public Prenda getParteInferior() {
    return parteInferior;
  }

  public Prenda getCalzado() {
    return calzado;
  }

  public Prenda getAccesorio() {
    return accesorio;
  }
}
