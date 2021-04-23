package ar.edu.utn.frba.dds.prendas;

import java.awt.*;

import static java.util.Objects.requireNonNull;

public class Prenda {
  private final Material material;
  private final Tipo tipo;
  // En un futuro se podrian agregar mas colores, asi que podria ser interesante
  // Crear una clase Colores, que contenga tanto al primario, secundario, terciario, etc.
  private final Color colorPrimario;
  private final Color colorSecundario;

  public Prenda(Material material, Tipo tipo, Color colorPrimario, Color colorSecundario) {
    this.material = requireNonNull(material, "La prenda requiere un material");
    this.tipo = requireNonNull(tipo, "La prenda requiere un tipo");
    this.colorPrimario = requireNonNull(colorPrimario, "La prenda requiere un colorPrimario");
    this.colorSecundario = colorSecundario;
  }

  Categoria categoria() {
    return this.tipo.categoria();
  }
}
