/*
 * @author Bruno Carmo n:57418 e Sahil Kumar n:57449
 */

package exceptions;


/**
 * Classe que representa a excecao que e lancada quando um utilizador nao pode comentar num post.
 */


@SuppressWarnings("serial")
public class CannotCommentException extends RuntimeException {
	
	/**
	 * Contrutor da excecao que invoca o construtor da superclasse.
	 */
	public CannotCommentException() {
		super();
	}
	
}
