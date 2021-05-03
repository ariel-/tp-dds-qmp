package ar.edu.utn.frba.dds.prendas;

import java.awt.*;

public class Borrador {
  private Material material;
  private Tipo tipo;
  private Trama trama;
  private Color colorPrimario;
  private Color colorSecundario;

  private final Trama tramaPorDefecto;

  public Borrador(Trama tramaPorDefecto) {
    this.tramaPorDefecto = tramaPorDefecto;
    this.trama = tramaPorDefecto;
  }

  public void setTipo(Tipo tipo) {
    this.tipo = tipo;
  }

  public void setTrama(Trama trama) {
    this.trama = trama == null ? tramaPorDefecto : trama;
  }

  public void setColorPrimario(Color colorPrimario) {
    this.colorPrimario = colorPrimario;
  }

  public void setColorSecundario(Color colorSecundario) {
    this.colorSecundario = colorSecundario;
  }

  public void setMaterial(Material material) {
    this.material = material;
  }

  public Prenda crearPrenda() {
    validarPrenda();
    return new Prenda(material, tipo, trama, colorPrimario, colorSecundario);
  }

  private void validarPrenda() {
    if (tipo == null)
      throw new RuntimeException("El tipo no puede ser nulo.");

    if (material == null)
      throw new RuntimeException("La prenda debe tener un material");

    if (!tipo.admiteMaterial(material))
      throw new RuntimeException("El material no es admitido por el tipo de prenda");

    if (colorPrimario == null)
      throw new RuntimeException("La prenda debe tener un color primario");
  }
}
