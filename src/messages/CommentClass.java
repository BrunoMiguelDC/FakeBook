/*
 * @author Bruno Carmo n:57418 e Sahil Kumar n:57449
 */

package messages;


/*
 * Classe que implementa a interface Comment e que apenas contem o 
 * construtor desta classe e um metodo para retornar o Post onde
 * este comentario foi escrito, ou seja, o Post que contem este
 * Comment.
 */


import users.User;


public class CommentClass extends MessageClass implements Comment {
	
	
	/**
	 * Post onde esta este comentario.
	 */
	private Post commentedPost;
	
	
	/**
	 * Contrutor da classe.
	 * Constroi um comentario com o autor igual a auhtor, postura igual a stance, conteudo igual
	 * a text e o post onde esta este comentario igual a post.
	 * @param author - autor do comentario.
	 * @param stance - stance(postura) do post.
	 * @param text - texto do comentario.
	 * @param post - Post onde esta este comentario.
	 */
	public CommentClass(User author, String stance, String text, Post post) {
		super(author, stance, text);
		commentedPost = post;
	}
	
	
	@Override
	public Post getPost() {
		return commentedPost;
	}

}
