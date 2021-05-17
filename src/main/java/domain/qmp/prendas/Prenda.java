package domain.qmp.prendas;

import java.awt.Color;

import static java.util.Objects.requireNonNull;

public class Prenda {
  private final Material material;
  private final Tipo tipo;
  private final Trama trama;
  private final Color colorPrimario;
  private final Color colorSecundario;
  private final Integer temperaturaMaxima;

  public Prenda(Material material, Tipo tipo, Trama trama, Color colorPrimario, Color colorSecundario, Integer temperaturaMaxima) {
    this.material = requireNonNull(material, "La prenda requiere un material");
    this.tipo = requireNonNull(tipo, "La prenda requiere un tipo");
    this.trama = requireNonNull(trama, "La prenda requiere una trama");
    this.colorPrimario = requireNonNull(colorPrimario, "La prenda requiere un colorPrimario");
    this.colorSecundario = colorSecundario;
    this.temperaturaMaxima = temperaturaMaxima;
  }

  Categoria categoria() {
    return this.tipo.getCategoria();
  }
}
