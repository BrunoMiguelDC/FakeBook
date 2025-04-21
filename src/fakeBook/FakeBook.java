/*
 * @author Bruno Carmo n:57418 e Sahil Kumar n:57449
 */

package fakeBook;


/**
 * Interface de topo que representa a aplicacao gerindo todas as 
 * acoes associadas a esta.
 */


import java.util.Iterator;
import java.util.List;

import messages.Comment;
import messages.Post;
import exceptions.*;

/**
* Interface da classe de topo que gere os utilizadores da aplicacao assim como as suas
* acoes, ou seja, os posts e comentarios dos utilizadores.
*/


import users.User;


public interface FakeBook {
	
	/**
	 * Devolve o numero de amigos do utilizador com o
	 * identificador igual a userID.
	 * @param userID - identificador do utilizador.
	 * @return - o numero de amigos.
	 */
	int getNumberOfFriendsOf(String userID);
	
	/**
	 * Devolve o numero de posts do utilizador com o
	 * identificador igual a userID.
	 * @param userID - identificador do utilizador.
	 * @return - o numero de posts.
	 */
	int getNumberOfPostsOf(String userID);
	
	/**
	 * Regista um novo utilizador na aplicacao.
	 * @param kind - tipo de utilizador.
	 * @param userID - identificador do utilizador.
	 * @throws UnknownKindException - se kind nao existe na aplicacao.
	 * @throws UserAlreadyExistsException - se ja existir um User com o identificador igual a userID.
	 * @throws InvalidHashtagListException - se existir um fanatismo repetido, quando o utilizador a registar e um 
	 * utilizador do tipo fanatico.
	 */
	void register(String kind, String userID, List<String> fanatisms) throws UnknownKindException, UserAlreadyExistsException, 
	InvalidHashtagListException;
	
	/**
	 * Devolve um objeto do tipo Iterator que itera sobre todos os utilizadores da aplicacao.
	 * @return - um Iterator sobre todos os utilizadores da aplicacao.
	 * @throws NoUsersException - se nao existirem utilizadores registados na aplicacao.
	 */
	Iterator<User> listUsers() throws NoUsersException;
	
	/**
	 * Cria uma amizade bidirecional entre dois utilizadores da aplicacao.
	 * @param user1ID - identificador de um utilizador.
	 * @param user2ID - identificador do outro utilizador.
	 * @throws UserDoesNotExistException - se nao existir nenhum utilizador com o identificador igual a user1ID
	 * ou user2ID na aplicacao.
	 * @throws DuplicateUserException - se os utilizadores que vao ser amigos forem o mesmo utilizador.
	 * @throws AlreadyFriendsException - se os utilizadores que vao ser amigos ja tiverem uma amizade.
	 */
	void addFriend(String user1ID, String user2ID) throws UserDoesNotExistException, DuplicateUserException, 
	AlreadyFriendsException;
	
	/**
	 * Devolve um objeto do tipo Iterator que itera sobre a lista de amigos do utilizador com o identificador
	 * igual a userID.
	 * @param userID - identificador do utilizador.
	 * @return - um Iterator sobre uma lista de amigos de um utilizador.
	 * @throws UserDoesNotExistException - se nao existir nenhum utilizador com o identificador igual a userID na aplicacao
	 * @throws NoFriendsException - se o utilizador nao tiver nenhuma amizade na aplicacao.
	 */
	Iterator<User> listUserFriends(String userID) throws UserDoesNotExistException, NoFriendsException; 
	
	/**
	 * Posta uma nova mensagem(Post) feita pelo utilizador com o identificador igual a userID na aplicacao
	 * e envia-a a todos os seus amigos. 
	 * @param userID - identificador do utilizador.
	 * @param numberOfHashtags - numero de topicos/hashtags do post.
	 * @param stance - stance(postura) do post.
	 * @param msg - conteudo do post.
	 * @throws UserDoesNotExistException - se nao existir nenhum utilizador com o identificador igual a userID na aplicacao.
	 * @throws InvalidNumberException - se o numero de topicos do post for menor 0.
	 * @throws InvalidHashtagListExeception - se existirem topicos/hashtags repetidos.
	 * @throws InvalidStanceException - se a stance de post contradizer o tipo de utilizador, ou seja, se o utilizador
	 * nao conseguir dazer um post com essa stance.
	 */
	void post(String userID, String stance, List<String> topics,String msg) throws UserDoesNotExistException, 
	InvalidNumberException, InvalidHashtagListException, InvalidStanceException;
	
	/**
	 * Devolve um objeto do tipo Iterator que itera sobre os posts feitos na aplicacao pelo utilizador com o identificador
	 * igual a userID.
	 * @param userID - identificador do utilizador.
	 * @return - um Iterator sobre os posts de um utilizador.
	 * @throws UserDoesNotExistException - se nao existir nenhum utilizador com o identificador igual a userID.
	 * @throws NoPostsException - se o utilizador nao tiver nenhum post na aplicacao.
	 */
	Iterator<Post> listUserPosts(String userID) throws UserDoesNotExistException, NoPostsException; 
	
	/**
	 * Faz um comentario(Comment) num post pertencente a um utilizador com o identificador igual a userID, na aplicacao.
	 * @param commenterID - identificador do utilizador que vai fazer um comentario num post, ou seja, o comentador.
	 * @param authorID - identificador do utilizador que e o autor do post que vai receber um comentario.
	 * @param postID - identificador do post.
	 * @param stance - stance(postura) do comentario
	 * @param msg - conteudo do comentario.
	 * @throws UserDoesNotExistException - se nao existir nenhum utilizador com o identificador igual a commenterID
	 * ou authorID.
	 * @throws PostDoesNotExistException - se nenhum post com o identificador igual a postID. 
	 * @throws UserIsNotFriendException - se o comentador e o autor do post nao eram amigos quando o post foi criado.
	 * @throws CannotCommentException - se o comentador nao poder comentar, ou seja, o comentador e um utilizador do tipo
	 * self-centered.
	 * @throws InvalidStanceException - se a stance do comentario for inadequada/invalida para o tipo de utilizador
	 * do comentador. 
	 */
	void comment(String commenterID, String authorID, int postID, String stance, String msg) throws UserDoesNotExistException,
	PostDoesNotExistException, UserHasNoAccessException, CannotCommentException, InvalidStanceException;
	
	/**
	 * Devolve um objeto do tipo Post cujo o identificador(do post) e igual a postID e 
	 * cujo autor e um utilizador com identificador igual a authorID.
	 * @param authorID - identificador do autor do post.
	 * @param postID - identificador do post.
	 * @return - um Post que tem o identificador igual a postID que pertence ao utilizador
	 * com o identificador igual a authorID.
	 * @throws UserDoesNotExistException - se nao existir nenhum utilizador com o identificador igual a authorID na aplicacao.
	 * @throws PostDoesNotExistException - se o autor nao possuir nenhum post com o identificador igual a postID.
	 * @throws NoCommentsException - se o post nao contiver nenhum comentario.
	 */
	Post readPost(String authorID, int postID) throws UserDoesNotExistException, PostDoesNotExistException, 
	NoCommentsException;
	
	/**
	 * Devolve um objeto do tipo Iterator que itera sobre todos os comentarios(Comment) feitos pelo utilizador 
	 * com o identificador igual a userID no topico/hashtag dado por topic, na aplicacao.
	 * @param userID - identificador do utilizador.
	 * @param topic - nome do topico/hashtag.
	 * @return - um Iterator sobre todos os comentarios de um utilizador num topico/hashtag na aplicacao.
	 * @throws UserDoesNotExistException - se nao existir nenhum utilizador com o identificador igual a userID na aplicacao.
	 * @throws NoCommentsException - se o utilizador nao tiver feito nenhum comentario na aplicacao.
	 */
	Iterator<Comment> listUserCommentsInTopic(String userID, String topic) throws UserDoesNotExistException, 
	NoCommentsException; 
	
	/**
	 * Devolve um objeto do tipo Iterator que itera sobre todos os utilizadore do tipo fanatico que possuem um fanatismo
	 * sobre o topic/hashtag dado por topic, na aplicacao.
	 * @param topic - nome do topico/hashtag
	 * @return - um Iterator sobre todos os utilizadore do tipo fanatico que possuem um fanatismo sobre topic.
	 * @throws NoTopicFanaticsException - se nao existirem utilizadores do tipo fanatico que tenham um fanatismo sobre topic.
	 */
	Iterator<User> listTopicFanatics(String topic) throws NoTopicFanaticsException;
	
	/**
	 * Devolve um objeto do tipo Iterator que itera sobre todos os posts feitos na aplicacao que tenham o topic/hashtag
	 * dado por topic.
	 * @param topic - nome do topic/hashtag.
	 * @param numberOfPostsToList - numero de posts para iterar/listar. 
	 * @return - um Iterator sobre todos os posts que tenham o topico/hashtag igual a topic.
	 * @throws InvalidNumberException - se o numero de posts a listar for menor que 1.
	 * @throws NoTopicPostsException - se nao existir nenhum post feito na aplicacao que tenha um topico/hashtag 
	 * igual a topic.
	 */
	Iterator<Post> listTopicPosts(String topic, int numberOfPostsToList) throws InvalidNumberException, NoTopicPostsException; 
	
	/**
	 * Devolve um objeto do tipo Post com o maior numero de comentarios na aplicacao.
	 * @return - o Post com o maior numero de comentarios.
	 * @throws NoMessagesException - se nao existirem posts ou mesmo que exitirem nao contiverem comentarios na aplicacao.
	 */
	Post mostPopularPost() throws NoMessagesException;
	
	/**
	 * Devolve um objeto do tipo User com o maior numero de posts na aplicacao.
	 * @return - o User com o maior numero de posts.
	 * @throws NoPostsException - se nao existirem posts na aplicacao.
	 */
	User topPoster() throws NoPostsException;
	
	/**
	 * Devolve um objeto do tipo User com a maior percentagem de comentarios feitos em posts na aplicacao.
	 * @return - o User com a maior percentagem de comentarios feitos na aplicacao.
	 * @throws NoPostsException - se nao existirem posts na aplicacao.
	 */
	User mostResponsive() throws NoPostsException;
	
	/**
	 * Devolve um objeto do tipo User com o maior numero de mentiras na aplicacao.
	 * @return - o User com o maior numero de mentiras.
	 * @throws NoLiesException - se nao existirem mentiras na aplicacao.
	 */
	User mostShameless() throws NoLiesException; 

}
