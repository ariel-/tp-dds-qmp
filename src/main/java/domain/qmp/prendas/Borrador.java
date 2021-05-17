package domain.qmp.prendas;

import domain.qmp.exceptions.PrendaInvalida;

import java.awt.*;

public class Borrador {
  private final Trama tramaPorDefecto;
  private Material material;
  private Tipo tipo;
  private Trama trama;
  private Color colorPrimario;
  private Color colorSecundario;
  private Integer temperaturaMaxima;

  public Borrador(Trama tramaPorDefecto) {
    this.tramaPorDefecto = tramaPorDefecto;
    this.trama = tramaPorDefecto;
  }

  public Borrador setTipo(Tipo tipo) {
    this.tipo = tipo;
    return this;
  }

  public Borrador setTrama(Trama trama) {
    this.trama = trama == null ? tramaPorDefecto : trama;
    return this;
  }

  public Borrador setColorPrimario(Color colorPrimario) {
    this.colorPrimario = colorPrimario;
    return this;
  }

  public Borrador setColorSecundario(Color colorSecundario) {
    this.colorSecundario = colorSecundario;
    return this;
  }

  public Borrador setTemperaturaMaxima(Integer temperaturaMaxima) {
    this.temperaturaMaxima = temperaturaMaxima;
    return this;
  }

  public Borrador setMaterial(Material material) {
    this.material = material;
    return this;
  }

  public Prenda crearPrenda() {
    validarPrenda();
    return new Prenda(material, tipo, trama, colorPrimario, colorSecundario, temperaturaMaxima);
  }

  private void validarPrenda() {
    if (tipo == null)
      throw new PrendaInvalida("El tipo no puede ser nulo.");

    if (material == null)
      throw new PrendaInvalida("La prenda debe tener un material");

    if (!tipo.admiteMaterial(material))
      throw new PrendaInvalida("El material no es admitido por el tipo de prenda");

    if (colorPrimario == null)
      throw new PrendaInvalida("La prenda debe tener un color primario");
  }
}
