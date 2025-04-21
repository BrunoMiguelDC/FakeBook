/*
 * @author Bruno Carmo n:57418 e Sahil Kumar n:57449
 */

package exceptions;


/**
 * Classe que representa a excecao que e lancada quando e introduzida uma lista invalida de topicos.
 */


@SuppressWarnings("serial")
public class InvalidHashtagListException extends RuntimeException {
	
	/**
	 * Contrutor da excecao que invoca o construtor da superclasse.
	 */	
	public InvalidHashtagListException() {
		super();
	}
	
}
