package domain.qmp.prendas;

import static java.util.Objects.requireNonNull;

import java.awt.Color;
import java.math.BigDecimal;

public class Prenda {
  private final Material material;
  private final Tipo tipo;
  private final Trama trama;
  private final Color colorPrimario;
  private final Color colorSecundario;
  private final BigDecimal temperaturaMaxima;

  public Prenda(Material material, Tipo tipo, Trama trama, Color colorPrimario,
                Color colorSecundario, BigDecimal temperaturaMaxima) {
    this.material = requireNonNull(material, "La prenda requiere un material");
    this.tipo = requireNonNull(tipo, "La prenda requiere un tipo");
    this.trama = requireNonNull(trama, "La prenda requiere una trama");
    this.colorPrimario = requireNonNull(colorPrimario, "La prenda requiere un colorPrimario");
    this.colorSecundario = colorSecundario;
    this.temperaturaMaxima = temperaturaMaxima;
  }

  public Categoria categoria() {
    return this.tipo.getCategoria();
  }

  public boolean aptaParaTemperatura(BigDecimal temperatura) {
    // es apta si la temperatura es menor o igual a la maxima
    // (Ej.: “Remera de mangas largas” no es apta a más de 20°C), en este caso tempMax = 20
    return temperatura.compareTo(temperaturaMaxima) <= 0;
  }
}
