/*
 * @author Bruno Carmo n:57418 e Sahil Kumar n:57449
 */

package messages;


/**
 * Interface que representa uma mensagem na aplicacao, ou seja, um post ou 
 * um comentario.
 */


import users.User;


public interface Message {
	
	/**
	 * Devolve a postura(stance) desta Message.
	 * @return - stance desta Message.
	 */
	String getMessageStance();	
	
	/**
	 * Devolve o conteudo, ou seja, o texto desta Message.
	 * @return - conteudo desta Message.
	 */
	String getText();
	
	/**
	 * Devolve um objeto do tipo User que e o autor desta Message.
	 * @return - autor desta Message.
	 */
	User getAuthor();

}
