/*
 * @author Bruno Carmo n:57418 e Sahil Kumar n:57449
 */

package exceptions;


/**
 * Classe que representa a excecao que e lancada quando um utilizador nao existe.
 */


@SuppressWarnings("serial")
public class UserDoesNotExistException extends RuntimeException {
	
	/**
	 * Contrutor da excecao que invoca o construtor da superclasse.
	 * @param mssg - O nome do utilizador que nao existe.
	 */
	public UserDoesNotExistException(String mssg) {
		super(mssg);
	}
	
}
