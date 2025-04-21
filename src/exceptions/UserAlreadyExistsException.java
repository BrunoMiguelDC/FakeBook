/*
 * @author Bruno Carmo n:57418 e Sahil Kumar n:57449
 */

package exceptions;


/**
 * Classe que representa a excecao que e lancada quando tenta se criar um utilizador que tenha o identificador
 * igual a outro utilizador ja registado na aplicacao.
 */


@SuppressWarnings("serial")
public class UserAlreadyExistsException extends RuntimeException {

	/**
	 * Contrutor da excecao que invoca o construtor da superclasse.
	 */	
	public UserAlreadyExistsException() {
		super();
	}
	
}
