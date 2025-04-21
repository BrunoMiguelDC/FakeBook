/*
 * @author Bruno Carmo n:57418 e Sahil Kumar n:57449
 */

package exceptions;


/**
 * Classe que representa a excecao que e lancada quando a aplicacao nao tem posts.
 */


@SuppressWarnings("serial")
public class NoPostsException extends RuntimeException {
		
	/**
	 * Contrutor da excecao que invoca o construtor da superclasse.
	 */	
	public NoPostsException() {
		super();
	}
	
}
