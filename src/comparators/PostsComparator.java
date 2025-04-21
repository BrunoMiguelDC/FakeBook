/*
 * @author Bruno Carmo n:57418 e Sahil Kumar n:57449
 */

package comparators;


/**
 * Classe que implementa a interface Comparator, contendo, por isso, o metodo
 * compare, que compara dois objetos do tipo Post conforme os seus numeros de 
 * comentarios, ordem alfabetica do identificador dos autores e finalmente pelo
 * identificador dos posts.
 */


import java.util.Comparator;
import messages.*;


public class PostsComparator implements Comparator<Post> {

	@Override
	public int compare(Post post1, Post post2) {
		int post1Comments = post1.getNumberOfComments();
		int post2Comments = post2.getNumberOfComments();
		int postAuthorIDCompare = post1.getAuthor().getID().compareTo(post2.getAuthor().getID());
		
		if(post1Comments > post2Comments) {
			return -1;
		}
	    else if(post1Comments < post2Comments) {
	    	return 1;
	    }
	    else if(postAuthorIDCompare < 0 ){
	    	return -1;
	    }
	    else if(postAuthorIDCompare > 0 ){
	    	return 1;
	    }
	    else if (post1.getID() > post2.getID()) {
	    	return -1;
	    }
	    else {
	    	return 1;
	    }	    
	}
	
}
