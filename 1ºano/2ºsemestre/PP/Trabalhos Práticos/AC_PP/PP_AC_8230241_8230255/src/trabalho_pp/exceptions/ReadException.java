/*
Nome: Jose Pedro Vieira Barbosa
Número: 8230241
Turma: LEIT3
*
Nome: Carlos Alberto Moreira Barbosa
Número: 8230255
Turma: LSIRCT2
*/

package trabalho_pp.exceptions;

/**
 * Class responsible for the Read Exception
 */
public class ReadException extends Exception {
  public ReadException() {
    super();
  }
  public ReadException(String errorMessage) {
    super(errorMessage);
  }
}