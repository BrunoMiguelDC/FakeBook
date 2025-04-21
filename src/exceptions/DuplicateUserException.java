/*
 * @author Bruno Carmo n:57418 e Sahil Kumar n:57449
 */

package exceptions;


/**
 * Classe que representa a excecao que e lancada quando um utilizador tenta
 * adicionar um amigo tal que o identificador desse amigo e igual ao identificador
 * do utilizador.
 */


@SuppressWarnings("serial")
public class DuplicateUserException extends RuntimeException {

	/**
	 * Contrutor da excecao que invoca o construtor da superclasse.
	 */	
	public DuplicateUserException() {
		super();
	}
	
}
