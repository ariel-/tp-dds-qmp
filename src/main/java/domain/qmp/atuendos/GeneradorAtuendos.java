package domain.qmp.atuendos;

import domain.qmp.prendas.Prenda;
import java.util.List;

public interface GeneradorAtuendos {
  Atuendo generarAtuendosDesde(List<Prenda> prendas);
}
