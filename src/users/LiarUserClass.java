/*
 * @author Bruno Carmo n:57418 e Sahil Kumar n:57449
 */

package users;


/**
 * Classe que representa uma variante de UserClass, ou seja,
 * um tipo de utilizador na aplicacao que neste caso e um 
 * utlizador liar. 
 */


import java.util.List;


public class LiarUserClass extends UserClass {

	
	/**
	 * Contrutor da classe.
	 * Constroi um utilizador liar com identificdor igual a userID, 
	 * as suas estruturas de dados vazias e as variaveis inteiras a 0.
	 * @param kind - tipo de utilizador.
	 * @param userID - Identificador do utilizador.
	 */
	public LiarUserClass(String kind, String userID) {
		super(kind, userID);
	}
	
	
	@Override
	public boolean canPost(String postStance, List<String> postTopics) {
		if(postStance.equals(HONEST)) {
			return false;
		} else {
			return true;
		}
	}
	
	@Override
	public boolean canComment(String postStance, String commentStance, List<String> postTopics) {
		if(postStance.equals(HONEST)) {
			return commentStance.equals(NEGATIVE);
		} else {
			return commentStance.equals(POSITIVE);
		}
	}
	
}


