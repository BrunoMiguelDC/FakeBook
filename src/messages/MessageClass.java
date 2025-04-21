/*
 * @author Bruno Carmo n:57418 e Sahil Kumar n:57449
 */

package messages;


/*
 * Classe abstrata que implementa a interface Message, contendo por isso
 * todos os metodos e variaveis associados a acoes que possam vir a ser feitas
 * numa mensagem por um ou mais utilizadores na aplicacao, e que sao  comuns a 
 * todas as diferentes variantes de MessageClass, neste caso, PostClass e CommentClass.
 */


import users.User;


public abstract class MessageClass implements Message {

	/**
	 * Stance(postura) da mensagem.
	 */
	private String messageStance;

	/**
	 * Conteudo da mensagem.
	 */
	private String text;

	/**
	 * Autor da mensagem.
	 */
	private User author;


	/**
	 * Construtor da classe apenas a ser invocado pelas subclasses.
	 * @param author - autor da mensagem.
	 * @param messageType - stance(postura) da mensagem.
	 * @param text - conteudo da mensagem.
	 */
	protected MessageClass(User author, String messageType, String text) {
		this.author = author;
		this.messageStance = messageType;
		this.text = text;
	}

	
	@Override
	public String getMessageStance() {
		return messageStance;
	}

	@Override
	public String getText() {
		return text;
	}

	@Override
	public User getAuthor() {
		return author;
	}

}
