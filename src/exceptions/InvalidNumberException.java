/*
 * @author Bruno Carmo n:57418 e Sahil Kumar n:57449
 */

package exceptions;


/**
 * Classe que representa a excecao que e lancada quando o numero de hashtags introduzido e invalido.
 */


@SuppressWarnings("serial")
public class InvalidNumberException extends RuntimeException {
	
	/**
	 * Contrutor da excecao que invoca o construtor da superclasse.
	 */	
	public InvalidNumberException() {
		super();
	}
	
}
