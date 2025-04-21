/*
 * @author Bruno Carmo n:57418 e Sahil Kumar n:57449
 */

package messages;


/**
 * Interface que representa um post na aplicacao.
 */


import java.util.Iterator;
import java.util.List;

import exceptions.NoCommentsException;


public interface Post extends Message {

	@Override
	boolean equals(Object obj);
	
	/**
	 * Devolve o id deste Post.
	 * @return - id do Post.
	 */
	int getID();

	/**
	 * Devolve o numero de comentarios total deste Post.
	 * @return - numero de comentarios.
	 */
	int getNumberOfComments();

	/**
	 * Devolve um objeto do tipo Iterator que itera sobre uma lista de todos os comentarios
	 * neste Post.
	 * @return - um Iterator sobre todos os comentario deste Post.
	 * @throws NoCommentsException - se este Post nao contiver nenhum comentario.
	 */
	Iterator<Comment> getAllComents() throws NoCommentsException;

	/**
	 * Devolve um objeto do tipo Iterator que itera sobre a lista de topicos 
	 * deste Post.
	 * @return - um Iterator sobre os topicos deste Post .
	 */
	Iterator<String> getTopicsIterator();

	/**
	 * Devolve um objeto do tipo List que contem os topicos deste Post.
	 * @return - uma List dos topicos deste Post.
	 */
	List<String> getTopics();

	/**
	 * Adiciona um objeto do tipo Comment a lista de comentarios neste post.
	 * @param comment - comentario a ser adicionado.
	 */
	void addComment(Comment comment);

}
