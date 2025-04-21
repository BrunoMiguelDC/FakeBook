/*
 * @author Bruno Carmo n:57418 e Sahil Kumar n:57449
 */

package users;


/*
 * Interface que representa um utilizador(geral) na aplicacao.
 */


import java.util.Iterator;
import java.util.List;

import exceptions.AlreadyFriendsException;
import exceptions.NoCommentsException;
import exceptions.NoFriendsException;
import exceptions.NoPostsException;
import exceptions.PostDoesNotExistException;

import messages.Comment;
import messages.Post;


public interface User {	
	
	/**
	 * Verifica se este User consegue postar na aplicacao dependendo da stance do Post e dos seus topicos.
	 * @param postStance - stance do Post.
	 * @param postTopics - lista de topicos do Post.
	 * @return - true se este User conseguir postar na aplicacao.
	 */
	boolean canPost(String postStance, List<String> postTopics);
	
	/**
	 * Verifica se este User consegue comentar um post dependendo da stance do Comment, da stance do Post e 
	 * dos topicos do Post.
	 * @param postStance - stance do Post.
	 * @param commentStance - stance do Comment.
	 * @param postTopics- lista de topicos do Post.
	 * @return - true se este User conseguir comentar o post na aplicacao.
	 */
	boolean canComment(String postStance, String commentStance, List<String> postTopics);
	
	/**
	 * Verifica se este User tem comentarios num post que tenha na sua lista de topicos um topico igual a topic.
	 * @param topic - topico do post.
	 * @return - true se tiver comentarios num post que tenha um topico igual a topic.
	 */
	boolean hasCommentIn(String topic);
	
	/**
	 * Verifica se este User tem acesso a um post feito por um amigo.
	 * @param friendID - identificador do amigo.
	 * @param postID - identificador do post.
	 * @return - true se este User tiver acesso ao post feito pelo amigo.
	 */
	boolean hasFriendsPost(String friendID, int postID);
	
	/**
	 * Verifica se este User tem um post com o identificador igual a postID.
	 * @param postID - identificador do post.
	 * @return - true se este User tiver o post com o identificador igual a postID.
	 */
	boolean hasPost(int postID);
	
	/**
	 * Devolve o numero de posts disponiveis/acessiveis a este User.
	 * @return - o numero de posts.
	 */
	int getNumberOfAvailablePosts();
	
	/**
	 * Devoleve o numero de comentarios total deste User na aplicacao.
	 * @return - numero de comentarios total.
	 */
	int getNumberOfComments();
	

	/**
	 * Devolve o numero de amigos deste User na aplicacao.
	 * @return - numero de amigos deste User.
	 */
	int getNumberOfFriends();
	
	/**
	 * Devolve a percentagem de comentarios feitos por posts a que este User tem acesso, 
	 * deste User na aplicacao.
	 * @return - percentagem de comentarios feitos por posts acessiveis.
	 */
	float getPercentageOfCommentsByPosts();
	
	/**
	 * Devolve o numero de mentiras deste User na aplicacao.
	 * @return - numero de mentiras deste User.
	 */
	int getNumberOfLies();
	
	/**
	 * Devolve o numero de posts deste User na aplicacao.
	 * @return - numero de posts na aplicacao.
	 */
	int getNumberOfPosts();
	
	/**
	 * Devolve um objeto do tipo Iterator que itera sobre todos os 
	 * comentarios feitos por este User no topico com a descricao 
	 * igual a topicID.
	 * @param topicID - topico no qual este User fez comentarios.
	 * @return - um Iterator sobre todos os comentarios deste User em topicID.
	 * @throws NoCommentsException - se este User nao tiver feito nenhum comentario num post que contenha
	 * nos seus topicos topic, na aplicacao.
	 */
	Iterator<Comment> getAllCommentsIn(String topic) throws NoCommentsException;
	
	/**
	 * Devolve um objeto do tipo Iterator que itera sobre todos os 
	 * posts(objetos do tipo Post) deste User.
	 * @return - um Iterator sobre todos os posts deste User.
	 * @throws NoPostsException - se este User nao tiver nenhum post na aplicacao.
	 */
	Iterator<Post> getAllPosts() throws NoPostsException;
	
	/**
	 * Devolve um objeto do tipo Iterator que itera sobre todos os 
	 * amigos(objetos do tipo User) deste User.
	 * @return - um Iterator sobre todos os amigos deste User.
	 * @throws NoFriendsException - se este User nao tiver nenhuma amizade na aplicacao.
	 */
	Iterator<User> getAllFriends() throws NoFriendsException;
	
	/**
	 * Devolve um objeto do tipo Post que pertence a este User e tem o identificador igual a postID.
	 * @param postID - identificador do Post.
	 * @return - um post que pertence a este User.
	 * @throws PostDoesNotExistException - se este User nao possuir nenhum post com o identificador igual a postID.
	 * @throws NoCommentsException - se o post nao contiver nenhum comentario.
	 */
	Post getPost(int postID) throws PostDoesNotExistException, NoCommentsException;
	
	/**
	 * Devolve o id deste User.
	 * @return - identificador deste User. 
	 */
	String getID();
	
	/**
	 * Devolve o tipo de User deste User.
	 * @return - tipo de User.
	 */
	String getKind();
	
	/**
	 * Adiciona um objeto do tipo Comment a um post a que este User tem acesso, ou seja, a uma lista de comentarios.
	 * Pre: comment != null
	 * @param comment - o comentario a ser adicionado.
	 */
	void addComment(Comment comment);
	
	/**
	 * Adiciona um outro objeto do tipo User a lista de amigos deste User.
	 * Pre: usr != null
	 * @param usr - um utilizador a ser adicionado.
	 * @throws AlreadyFriendsException - se este User ja for amigo de usr.
	 */
	void addFriend(User usr) throws AlreadyFriendsException;
	
	/**
	 * Adiciona um objeto do tipo Post a uma lista de posts que pertencem a este User.
	 * Pre: post != null
	 * @param post - post que este User criou.
	 */
	void addPost(Post post);
	
	/**
	 * Adiciona um objeto do tipo Post a uma lista de posts a que este User tem acesso e que nao e o seu autor, ou seja, 
	 * um Post de um amigo deste User.
	 * Pre: post != null
	 * @param post - um post que este User recebeu.
	 */
	void addFriendsPost(Post post);
	
	/**
	 * Incrementa o numero de mentiras deste User.
	 */
	void incLies();
}
