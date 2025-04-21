/*
 * @author Bruno Carmo n:57418 e Sahil Kumar n:57449
 */

package exceptions;


/**
 * Classe que representa a excecao que e lancada quando a stance(postura) introduzida e invalida.
 */


@SuppressWarnings("serial")
public class InvalidStanceException extends RuntimeException {

	/**
	 * Contrutor da excecao que invoca o construtor da superclasse.
	 */	
	public InvalidStanceException() {
		super();
	}
	
}
