/*
 * @author Bruno Carmo n:57418 e Sahil Kumar n:57449
 */

package comparators;


/**
 * Classe que implementa a interface Comparator, contendo, por isso, o metodo
 * compare, que compara dois objetos do tipo User conforme os seus numeros de 
 * mentiras, numeros de comentarios e posts e por ordem alfabetica do 
 * identificador de cada User.
 */


import java.util.Comparator;

import users.User;


public class LiarComparator implements Comparator<User> {

	@Override
	public int compare(User usr1, User usr2) {
		int usr1Lies = usr1.getNumberOfLies();
		int usr2Lies = usr2.getNumberOfLies();
		
		int usr1Messages = usr1.getNumberOfComments() + usr1.getNumberOfPosts();
		int usr2Messages = usr2.getNumberOfComments() + usr2.getNumberOfPosts();
		
		if(usr1Lies > usr2Lies) {
			return -1;
		}
		else if(usr1Lies < usr2Lies) {
			return 1;
		}
		else if(usr1Messages < usr2Messages) {
			return -1;
		}
		else if(usr1Messages > usr2Messages) {
			return 1;
		}
		else {
			return usr1.getID().compareTo(usr2.getID());
		}
	}

}
