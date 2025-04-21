/*
 * @author Bruno Carmo n:57418 e Sahil Kumar n:57449
 */

package exceptions;


/**
 * Classe que representa a excecao que e lancada quando nao existem posts com um certo topico na aplicacao.
 */


@SuppressWarnings("serial")
public class NoTopicPostsException extends RuntimeException {

	/**
	 * Contrutor da excecao que invoca o construtor da superclasse.
	 */	
	public NoTopicPostsException() {
		super();
	}
	
}
