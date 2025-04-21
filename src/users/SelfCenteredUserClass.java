/*
 * @author Bruno Carmo n:57418 e Sahil Kumar n:57449
 */

package users;


/**
 * Classe que representa uma variante de UserClass, ou seja,
 * um tipo de utilizador na aplicacao que neste caso e um 
 * utlizador self-centered.
 */


import java.util.List;


public class SelfCenteredUserClass extends UserClass {


	/**
	 * Construtor da classe.
	 * Constroi um utilizador selfcentered com identificdor igual a userID, 
	 * as suas estruturas de dados vazias e as variaveis inteiras a 0.
	 * @param kind - tipo de utilizador.
	 * @param userID - identificador do utilizador.
	 */ 
	public SelfCenteredUserClass(String kind, String userID) {
		super(kind, userID);
	}
	

	@Override
	public boolean canPost(String postStance, List<String> postTopics) {
		return true;
	}
	
	@Override
	public boolean canComment(String postStance, String commentStance, List<String> postTopics) {
		return commentStance.equals(POSITIVE);
	}
	
}
