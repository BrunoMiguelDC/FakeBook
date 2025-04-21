/*
 * @author Bruno Carmo n:57418 e Sahil Kumar n:57449
 */

package messages;


/*
 * Classe que implementa a interface Post, contendo por isso
 * os metodos e variaveis associados a acoes que um ou 
 * mais utilizadores possam ter na aplicacao em relacao a um
 * post.
 */


import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import exceptions.NoCommentsException;
import users.User;


public class PostClass extends MessageClass implements Post {

	/**
	 * Identificador deste Post.
	 */
	private int id;

	/**
	 * Lista de comentarios deste Post.
	 */
	private List<Comment> comments; 

	/**
	 * Lista de topicos deste Post.
	 */
	private List<String> topics;

	
	/**
	 * Contrutor da classe.
	 * Constroi um post com o autor(User) igual a author, postura igual a stance, conteudo igual a text
	 * identificador igual a id, lista de topicos igual a topics e as restantes estruturas de dados vazias.
	 * @param author - autor do Post.
	 * @param topics - lista de topicos do Post.
	 * @param stance - postura(stance) do Post.
	 * @param text - conteudo do Post.
	 * @param id - identificador do Post.
	 */
	public PostClass(User author, String stance, List<String> topics, String text, int id) {
		super(author, stance, text);
		this.topics = topics;
		this.id = id + 1;

		comments = new LinkedList<Comment>();
	}

	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PostClass other = (PostClass) obj;
		if (!this.getAuthor().getID().equals(other.getAuthor().getID()))
				return false;
		if (id != other.id)
			return false;
		return true;
	}




	@Override
	public int getID() {
		return id;
	}

	@Override
	public int getNumberOfComments() {
		return comments.size();
	}

	@Override
	public Iterator<Comment> getAllComents() throws NoCommentsException {
		if(comments.isEmpty()) {
			throw new NoCommentsException();
		}

		return comments.iterator();
	}

	@Override
	public Iterator<String> getTopicsIterator() {
		return topics.iterator();
	}

	@Override
	public List<String> getTopics(){
		return topics;
	}

	@Override
	public void addComment(Comment comment) {
		comments.add(comment);
	}

}
