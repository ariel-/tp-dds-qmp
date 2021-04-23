package ar.edu.utn.frba.dds.atuendos;

import ar.edu.utn.frba.dds.prendas.Prenda;

import java.util.List;

public class Atuendo {
  private final List<Prenda> prendas;

  public Atuendo(List<Prenda> prendas) {
    this.prendas = prendas;
  }

  public void cargarPrenda(Prenda prenda) {
    prendas.add(prenda);
  }
}
