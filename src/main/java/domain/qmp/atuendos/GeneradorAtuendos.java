package domain.qmp.atuendos;

import domain.qmp.prendas.Prenda;
import java.util.List;

public interface GeneradorAtuendos {
  Atuendo generarAtuendoDesde(List<Prenda> prendas);
}
