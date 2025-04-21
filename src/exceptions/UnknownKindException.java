/*
 * @author Bruno Carmo n:57418 e Sahil Kumar n:57449
 */

package exceptions;


/**
 * Classe que representa a excecao que e lancada quando e introduzido um tipo de utilizador desconhecido.
 */


@SuppressWarnings("serial")
public class UnknownKindException extends RuntimeException {
	
	/**
	 * Contrutor da excecao que invoca o construtor da superclasse.
	 */	
	public UnknownKindException() {
		super();
	}

}
