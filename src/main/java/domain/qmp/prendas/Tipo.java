package domain.qmp.prendas;

import java.util.Arrays;
import java.util.List;

public enum Tipo {
  PANTALONES(Categoria.INFERIOR, Arrays.asList(Material.JEAN)),
  REMERA(Categoria.SUPERIOR, Arrays.asList(Material.ALGODON));

  private final Categoria categoria;
  private final List<Material> materialesCompatibles;

  Tipo(Categoria categoria, List<Material> materialesCompatibles) {
    this.categoria = categoria;
    this.materialesCompatibles = materialesCompatibles;
  }

  public Categoria getCategoria() {
    return categoria;
  }

  public boolean admiteMaterial(Material material) {
    return materialesCompatibles.contains(material);
  }

  }
