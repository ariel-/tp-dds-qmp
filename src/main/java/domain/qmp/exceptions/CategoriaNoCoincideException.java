package domain.qmp.exceptions;

public class CategoriaNoCoincideException extends RuntimeException {
  public CategoriaNoCoincideException(String message) {
    super(message);
  }
}
