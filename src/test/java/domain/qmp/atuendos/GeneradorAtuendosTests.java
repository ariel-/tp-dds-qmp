package domain.qmp.atuendos;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import domain.qmp.prendas.Categoria;
import domain.qmp.prendas.Prenda;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GeneradorAtuendosTests {
  GeneradorAtuendos generadorAtuendos;
  Prenda superior, inferior, calzado, accesorio;
  List<Prenda> prendas;

  @BeforeEach
  void init() {
    generadorAtuendos = mock(GeneradorAtuendos.class);
    when(generadorAtuendos.generarAtuendosDesde(any()))
        .thenAnswer(invocation ->
                    {
                      List<Prenda> prendas = invocation.getArgument(0);
                      return new Atuendo(prendas.get(0), prendas.get(1), prendas.get(2),
                                         prendas.get(3));
                    });

    prendas = new ArrayList<>();

    superior = mock(Prenda.class);
    when(superior.categoria()).thenReturn(Categoria.SUPERIOR);

    inferior = mock(Prenda.class);
    when(inferior.categoria()).thenReturn(Categoria.INFERIOR);

    calzado = mock(Prenda.class);
    when(calzado.categoria()).thenReturn(Categoria.CALZADO);

    accesorio = mock(Prenda.class);
    when(accesorio.categoria()).thenReturn(Categoria.ACCESORIO);

    prendas.add(superior);
    prendas.add(inferior);
    prendas.add(calzado);
    prendas.add(accesorio);
  }

  @Test
  void unGeneradorGenera() {
    Atuendo atuendo = generadorAtuendos.generarAtuendosDesde(prendas);
    assertEquals(superior, atuendo.getParteSuperior());
    assertEquals(inferior, atuendo.getParteInferior());
    assertEquals(calzado, atuendo.getCalzado());
    assertEquals(accesorio, atuendo.getAccesorio());
  }
}
