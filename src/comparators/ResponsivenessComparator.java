/*
 * @author Bruno Carmo n:57418 e Sahil Kumar n:57449
 */

package comparators;


/**
 * Classe que implementa a interface Comparator, contendo, por isso, o metodo
 * compare, que compara dois objetos do tipo User conforme a percentagem de 
 * comentarios por post acessiveis a cada User e por ordem alfabetica do 
 * identificador de cada User.
 */


import java.util.Comparator;

import users.User;


public class ResponsivenessComparator implements Comparator<User> {

	@Override
	public int compare(User usr1, User usr2) {
		float usr1Percentage = usr1.getPercentageOfCommentsByPosts();
		float usr2Percentage = usr2.getPercentageOfCommentsByPosts();
		
		if(usr1Percentage > usr2Percentage) {
			return -1;
		}
		else if(usr1Percentage < usr2Percentage) {
			return 1;
		}
		else {
			return usr1.getID().compareTo(usr2.getID());
		}
	}

}
