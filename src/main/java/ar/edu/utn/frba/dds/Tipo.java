package ar.edu.utn.frba.dds;

public enum Tipo {
  ZAPATILLA {
    Categoria categoria() {
      return Categoria.CALZADO;
    }
  },
  REMERA_MANGA_LARGA {
    Categoria categoria() {
      return Categoria.SUPERIOR;
    }
  },
}
