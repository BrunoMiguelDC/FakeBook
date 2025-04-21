/*
 * @author Bruno Carmo n:57418 e Sahil Kumar n:57449
 */

package exceptions;


/**
 * Classe que representa a excecao que e lancada quando o utilizador nao tem acesso a um post.
 */


@SuppressWarnings("serial")
public class UserHasNoAccessException extends RuntimeException {
	
	/**
	 * Contrutor da excecao que invoca o construtor da superclasse.
	 */	
	public UserHasNoAccessException() {
		super();
	}

}
