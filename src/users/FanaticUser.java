/*
 * @author Bruno Carmo n:57418 e Sahil Kumar n:57449
 */

package users;


/**
 * Interface que representa uma variante de um utilizador na aplicacao, neste caso,
 * um utilizador fanatico.
 */


import java.util.Iterator;


public interface FanaticUser extends User {
	
	/**
	 * Verifica se este FanaticUser tem um fanatismo com a descricao igual a fanatism.
	 * @param fanatism - descricao do fanatismo.
	 * @return - true se tiver o fanatismo.
	 */
	boolean hasFanatism(String fanatism);
	

	/**
	 * Devolve um objeto do tipo Iterator que itera sobre 
	 * a lista de fanatismos deste FanaticUser.
	 * @return - um Iterator sobre a lista de fanatismos deste FanaticUser.
	 */
	Iterator<String> getAllFanatisms();
	
}
