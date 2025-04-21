/*
 * @author Bruno Carmo n:57418 e Sahil Kumar n:57449
 */

package exceptions;


/**
 * Classe que representa a excecao que e lancada quando um post ou um utilizador nao tem comentarios.
 */


@SuppressWarnings("serial")
public class NoCommentsException extends RuntimeException {

	/**
	 * Contrutor da excecao que invoca o construtor da superclasse.
	 */	
	public NoCommentsException() {
		super();
	}
	
}
