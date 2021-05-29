package domain.qmp.atuendos;

import domain.qmp.exceptions.CategoriaNoCoincideException;
import domain.qmp.prendas.Categoria;
import domain.qmp.prendas.Prenda;

public class Atuendo {
  private final Prenda parteSuperior;
  private final Prenda parteInferior;
  private final Prenda calzado;
  private final Prenda accesorio;

  public Atuendo(Prenda parteSuperior, Prenda parteInferior, Prenda calzado, Prenda accesorio) {
    validarAtuendo(parteSuperior, parteInferior, calzado, accesorio);

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

  private void validarAtuendo(Prenda parteSuperior, Prenda parteInferior, Prenda calzado,
                              Prenda accesorio) {
    validarCategoria(Categoria.SUPERIOR, parteSuperior);
    validarCategoria(Categoria.INFERIOR, parteInferior);
    validarCategoria(Categoria.CALZADO, calzado);
    validarCategoria(Categoria.ACCESORIO, accesorio);
  }

  private void validarCategoria(Categoria cat, Prenda prenda) {
    if (prenda != null && cat != prenda.categoria()) {
      throw new CategoriaNoCoincideException(
          "La prenda no es de la categoria esperada " + cat.toString());
    }
  }
}
