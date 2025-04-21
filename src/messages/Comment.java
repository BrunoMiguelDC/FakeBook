/*
 * @author Bruno Carmo n:57418 e Sahil Kumar n:57449
 */

package messages;


/**
 * Interface que representa um comentario na aplicacao.
 */


public interface Comment extends Message {

	/**
	 * Devolve um objeto do tipo Post, sendo esse objeto o post
	 * que contem este Comment, ou seja, o post que recebeu este
	 * comentario(Comment).
	 * @return - objeto do tipo Post que recebeu este comentario.
	 */
	Post getPost();
		
}
