/*
 * @author Bruno Carmo n:57418 e Sahil Kumar n:57449
 */

package users;


/**
 * Classe que implementa a interface FanaticUser, contendo, por isso
 * todos os metodos e acoes associadas que um utilizador fanatico pode
 * ter na aplicacao.
 */


import java.util.Iterator;
import java.util.List;


public class FanaticUserClass extends UserClass implements FanaticUser {

	/**
	 * Lista de fanatismos deste FanaticUser.
	 */
	private List<String> fanatisms;


	/**
	 * Construtor da classe.
	 * Constroi um utilizador fanatico com identificdor igual a userID, 
	 * as suas estruturas de dados vazias e as variaveis inteiras a 0.
	 * @param kind - tipo de utilizador.
	 * @param userID - identificador do utilizador.
	 * @param fanatisms - lista de fanatismos 
	 */
	public FanaticUserClass(String kind, String userID, List<String> fanatisms) {
		super(kind, userID);
		this.fanatisms = fanatisms;
	}


	@Override
	public boolean canPost(String postStance, List<String> postTopics) {
		boolean canPost = canPostComment(postTopics);

		if(postStance.equals(HONEST)) {
			return canPost;
		} else {
			return !canPost;
		}
	}

	@Override
	public boolean canComment(String postStance, String commentStance, List<String> postTopics) {
		boolean canComment = this.canCommentInHonest(commentStance, postTopics);

		if(postStance.equals(HONEST)) {
			return canComment;
		} else {
			return !canComment;
		}
	}

	@Override
	public boolean hasFanatism(String fanatism) {
		return fanatisms.contains(fanatism);
	}

	@Override
	public Iterator<String> getAllFanatisms() {
		return fanatisms.iterator();
	}

	/**
	 * Verifica se este FanaticUser consegur comentar num Post com a stance honest dependo
	 * dos topicos do post e dos fanatismo deste FanaticUser.
	 * @param commentStance - stance do comentario deste FanaticUser.
	 * @param postTopics - list de topicos do post onde este FanaticUser vai comentar.
	 * @return - true se este FanaticUser puder comentar no post.
	 */
	private boolean canCommentInHonest(String commentStance, List<String> postTopics) {
		boolean canCommentInHonest = this.canPostComment(postTopics);

		if(commentStance.equals(POSITIVE)) {
			return canCommentInHonest;
		} else {
			return !canCommentInHonest;
		}
	}

	/**
	 * Verifica se um fanatico pode postar ou comentar dependendo apenas da lista de topicos
	 * do post que vai postar ou do post onde vai comentar.
	 * @param postTopics - lista de topico do post que vais postar ou do post onde vai comentar.
	 * @return - true se puder postar ou comentar.
	 */
	private boolean canPostComment(List<String> postTopics) {
		Iterator<String> usrFanatisms = this.getAllFanatisms();

		while(usrFanatisms.hasNext()) {
			String type = usrFanatisms.next();
			String fanatism = usrFanatisms.next();

			if((type.equals("loves")) && (postTopics.contains(fanatism))) {
				return true;
			}
			if((type.equals("hates")) && (postTopics.contains(fanatism))) {
				return false;
			}
		}
		return false;
	}

}

