/*
 * @author Bruno Carmo n:57418 e Sahil Kumar n:57449
 */

package exceptions;


/**
 * Classe que representa a excecao que e lancada quando dois utilizadores ja sao amigos.
 */


@SuppressWarnings("serial")
public class AlreadyFriendsException extends RuntimeException {

	/**
	 * Contrutor da excecao que invoca o construtor da superclasse.
	 */
	public AlreadyFriendsException() {
		super();
	}
	
}
