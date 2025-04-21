/*
 * @author Bruno Carmo n:57418 e Sahil Kumar n:57449
 */

package fakeBook;


/**
 * Classe de topo que implementa a interface FakeBook, representando a aplicacao, 
 * contendo, por isso, os metodos e variaveis associados as acoes que sao possiveis fazer.
 */


import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

import comparators.LiarComparator;
import comparators.PostsComparator;
import comparators.ResponsivenessComparator;
import exceptions.*;
import messages.Comment;
import messages.CommentClass;
import messages.Post;
import messages.PostClass;
import users.*;


public class FakeBookClass implements FakeBook {

	/**
	 * Tamanho das estruturas de dados estaticas por default.
	 */
	private static final int SIZE = 17;
	
	/**
	 * Stance de comentarios e posts possiveis na aplicacao, respetivamente.
	 */
	private static final String POSITIVE = "positive";
	private static final String NEGATIVE = "negative";
	
	private static final String HONEST = "honest";
	private static final String FAKE = "fake";

	/**
	 * Numero de posts total deste FakeBook.
	 */
	private int numberOfPostsInFB;
	
	/**
	 * Mapa dos utilizadores(User) registados neste FakeBook.
	 */
	private Map<String, User> users;
	
	/**
	 * Mapa dos posts(Post) por topico feitos neste FakeBook.
	 */
	private Map<String, List<Post>> postsByTopic;
	
	/**
	 * Mapa dos utilizadores do tipo fanatic(FanaticUser) por topico registados neste FakeBook.
	 */
	private Map<String, SortedSet<User>> fanaticsByTopic;
	
	/**
	 * Lista de utilizadores(User) que ja mentiram neste FakeBook.
	 */
	private List<User> liars;
	
	/**
	 * Lista de utilizadores(User) que ja postaram ou comentaram neste FakeBook.
	 */
	private List<User> responsives;
	
	/**
	 * Post que representa o post mais popular(mais comentado) neste FakeBook.
	 */
	private Post mostPopularPost;	
	
	/**
	 * User que representa o utilizador com o maior numero de posts feitos neste FakeBook.
	 */
	private User topPoster;

	
	/**
	 * Construtor da classe.
	 * Constroi um FakeBook com as estruturas de dados vazias, os objetos a null e o numero de post a 0.
	 */
	public FakeBookClass() {
		numberOfPostsInFB = 0;

		users = new TreeMap<String, User>();

		postsByTopic = new HashMap<String, List<Post>>(SIZE);
		fanaticsByTopic = new HashMap<String, SortedSet<User>>(SIZE);
		liars = new LinkedList<User>();
		responsives = new LinkedList<User>();

		mostPopularPost = null;
		topPoster = null;		
	}



	@Override
	public int getNumberOfFriendsOf(String userID) {
		return users.get(userID).getNumberOfFriends();
	}
	
	@Override
	public int getNumberOfPostsOf(String userID) {
		return users.get(userID).getNumberOfPosts();
	}

	@Override
	public void register(String kind, String userID, List<String> fanatisms)
			throws UnknownKindException, UserAlreadyExistsException, InvalidHashtagListException {
		UserKinds usrKind = UserKinds.getUserKind(kind);

		if(usrKind == null) {
			throw new UnknownKindException();
		}
		if(users.containsKey(userID)) {
			throw new UserAlreadyExistsException();
		}
		if((kind.equals("fanatic")) && (this.hasDuplicateFanatisms(fanatisms))) {
			throw new InvalidHashtagListException();
		}
		
		switch(usrKind) {
			case SELF_CENTERED:
				addSelfCentered(usrKind.getDescritption(), userID);
				break;
			case NAIVE:
				addNaive(usrKind.getDescritption(), userID);
				break;
			case LIAR:
				addLiar(usrKind.getDescritption(), userID);
				break;
			case FANATIC:
				addFanatic(usrKind.getDescritption(), userID, fanatisms);
				break;
			}
	}

	@Override
	public Iterator<User> listUsers() throws NoUsersException {
		if(users.isEmpty()) {
			throw new NoUsersException();
		}
		
		return users.values().iterator();
	}

	@Override
	public void addFriend(String user1ID, String user2ID)
			throws UserDoesNotExistException, DuplicateUserException, AlreadyFriendsException {
		User usr1 = users.get(user1ID);
		User usr2 = users.get(user2ID);

		if(usr1 == null) {
			throw new UserDoesNotExistException(user1ID);
		} 
		if( usr2 == null) {
			throw new UserDoesNotExistException(user2ID);
		}
		if(user1ID.equals(user2ID)) {
			throw new DuplicateUserException();
		}
		
		usr1.addFriend(usr2);	
		usr2.addFriend(usr1);
	}

	@Override
	public Iterator<User> listUserFriends(String userID) throws UserDoesNotExistException, NoFriendsException {
		User usr = users.get(userID);

		if(usr == null) {
			throw new UserDoesNotExistException(userID);
		}
		
		return usr.getAllFriends();
	}

	@Override
	public void post(String userID, String postStance, List<String> topics, String msg) throws UserDoesNotExistException,
	InvalidNumberException, InvalidHashtagListException, InvalidStanceException {
		User usr = users.get(userID);
		
		if(usr == null) {
			throw new UserDoesNotExistException(userID);
		}
		if(this.hasDuplicateTopics(topics)) {
			throw new InvalidHashtagListException();
		}
		if(!usr.canPost(postStance, topics)) {
			throw new InvalidStanceException();
		} 

		if(postStance.equals(FAKE)) {
			usr.incLies();
			if(!liars.contains(usr)) {
				liars.add(usr);
			}
		}
		Post post = new PostClass(usr, postStance, topics, msg, usr.getNumberOfPosts());

		usr.addPost(post);
		
		if(usr.getNumberOfFriends() !=0) {
			Iterator<User> usrFriends = usr.getAllFriends();

			while(usrFriends.hasNext()) {
				User friend = usrFriends.next();

				friend.addFriendsPost(post);
			}
		}
		Iterator<String> postTopics = topics.iterator();

		while(postTopics.hasNext()) {
			String topic = postTopics.next();
			List<Post> posts = postsByTopic.get(topic);

			if(posts == null) {
				posts = new LinkedList<Post>();

				postsByTopic.put(topic, posts);
			}
			posts.add(post);
		}

		if(mostPopularPost == null) {
			mostPopularPost = post;
			topPoster = usr;
		}
		setMostPopularPost(post);
		setTopPoster(usr);
		numberOfPostsInFB++;		
	}

	@Override
	public Iterator<Post> listUserPosts(String userID) throws UserDoesNotExistException, NoPostsException {
		User usr = users.get(userID);

		if(usr == null) {
			throw new UserDoesNotExistException(userID);
		} 
			
		return usr.getAllPosts();
	}

	@Override
	public void comment(String commenterID, String authorID, int postID, String stance, String msg)
			throws UserDoesNotExistException, PostDoesNotExistException, UserHasNoAccessException,
			CannotCommentException, InvalidStanceException {
		User commenter = users.get(commenterID);
		User author = users.get(authorID);

		if(commenter == null) {
			throw new UserDoesNotExistException(commenterID);
		}
		if(author == null) {
			throw new UserDoesNotExistException(authorID);
		}
		if(!author.hasPost(postID)) {
			throw new PostDoesNotExistException();
		}
		if(!(author.getKind().equals("selfcentered")) && (commenter.getKind().equals("selfcentered"))) {
			throw new CannotCommentException();
		}
		if(!commenter.hasFriendsPost(authorID, postID)) {
			throw new UserHasNoAccessException();
		}
		
		Post post = author.getPost(postID);
		
		if(!(commenter.canComment(post.getMessageStance(), stance, post.getTopics()))) {
			throw new InvalidStanceException();
		}

		if(post.getMessageStance().equals(FAKE) && stance.equals(POSITIVE)) {
			commenter.incLies();
			if(!liars.contains(commenter)) {
				liars.add(commenter);
			}
		}
		if(post.getMessageStance().equals(HONEST) && stance.equals(NEGATIVE)) {
			commenter.incLies();
			liars.add(commenter);
			if(!liars.contains(commenter)) {
				liars.add(commenter);
			}
		}
		Comment comment = new CommentClass(commenter, stance, msg, post);
		
		commenter.addComment(comment);
		post.addComment(comment);
		if(!responsives.contains(commenter)) {
			responsives.add(commenter);
		}
		
		setMostPopularPost(post);
		setTopPoster(commenter);
	}


	@Override
	public Post readPost(String authorID, int postID)
			throws UserDoesNotExistException, PostDoesNotExistException, NoCommentsException {
		User usr = users.get(authorID);

		if(usr == null) {
			throw new UserDoesNotExistException(authorID);
		} 

		return usr.getPost(postID);
	}


	@Override
	public Iterator<Comment> listUserCommentsInTopic(String userID, String topic)
			throws UserDoesNotExistException, NoCommentsException {
		User usr = users.get(userID);

		if(usr == null) {
			throw new UserDoesNotExistException(userID);
		}
		
		return usr.getAllCommentsIn(topic);
	}

	@Override
	public Iterator<User> listTopicFanatics(String topic) throws NoTopicFanaticsException {
		SortedSet<User> fanatics = fanaticsByTopic.get(topic);

		if(fanatics == null) {
			throw new NoTopicFanaticsException();
		}
		
		return fanatics.iterator();
	}

	@Override
	public Iterator<Post> listTopicPosts(String topic, int numberOfPostsToList)
			throws InvalidNumberException, NoTopicPostsException {
		List<Post> posts = postsByTopic.get(topic);

		if(numberOfPostsToList < 1) {
			throw new InvalidNumberException();
		}
		if(posts == null) {
			throw new NoTopicPostsException();
		}
		
		if(numberOfPostsToList > posts.size()) {
			numberOfPostsToList = posts.size();
		}
		posts.sort(new PostsComparator());
		return posts.subList(0, numberOfPostsToList).iterator();
	}

	@Override
	public Post mostPopularPost() throws NoMessagesException {
		if((mostPopularPost == null) || (mostPopularPost.getNumberOfComments() == 0)) {
			throw new NoMessagesException();
		}
			
		return mostPopularPost;
	}

	@Override
	public User topPoster() throws NoPostsException {
		if(topPoster == null) {
			throw new NoPostsException();
		}
			
		return topPoster;
	}

	@Override
	public User mostResponsive() throws NoPostsException {
		if((responsives.isEmpty()) || (numberOfPostsInFB == 0)) {
			throw new NoPostsException();
		}
	
		responsives.sort(new ResponsivenessComparator());
		return responsives.get(0);
	}

	@Override
	public User mostShameless() throws NoLiesException {
		if(liars.isEmpty()) {
			throw new NoLiesException();
		}
		
		liars.sort(new LiarComparator());
		return liars.get(0);
	}

	/*metodos privados*/
	
	/**
	 * Verifica se existem hashtags duplicados/iguais na lista de fanatismos de um FanaticUser.
	 * Pre: fanatisms != null 
	 * @param fanatisms - lista de fanatismos.
	 * @return - true se existirem hashtags iguais.
	 */
	private boolean hasDuplicateFanatisms(List<String> fanatisms) {
		Set<String> set = new HashSet<String>(fanatisms); 
		set.remove("loves");
		set.remove("hates");
		
		return set.size() < (fanatisms.size() / 2);
	}
	
	/**
	 * Verifica se existem hashtags duplicados/iguais na lista de topicos de um Post.
	 * Pre: topics != null.
	 * @param topics - lista de topicos.
	 * @return - true se existirem hashtags iguais.
	 */
	private boolean hasDuplicateTopics(List<String> topics) {
		Set<String> set = new HashSet<String>(topics);
		
		return set.size() < topics.size();
	}
	
	/**
	 * Adiciona um utilizador selfcentered ao mapa dos utilizadores registados
	 * neste FakeBook.
	 * @param kind - tipo de utilizador, neste caso, selfcentered.
	 * @param userID - identificador do utilizador.
	 */
	private void addSelfCentered(String kind, String userID) {
		User usr = new SelfCenteredUserClass(kind, userID);

		users.put(userID, usr);
	}

	/**
	 * Adiciona um utilizador naive ao mapa dos utilizadores registados
	 * neste FakeBook.
	 * @param kind - tipo de utilizador, neste caso, naive.
	 * @param userID - identificador do utilizador.
	 */
	private void addNaive(String kind, String userID) {
		User usr = new NaiveUserClass(kind, userID);

		users.put(userID, usr);
	}

	/**
	 * Adiciona um utilizador liar ao mapa de utilizadores registados
	 * neste FakeBook.
	 * @param kind - tipo de utilizador, neste caso, liar.
	 * @param userID - identificador do utilizador.
	 */
	private void addLiar(String kind, String userID) {
		User usr = new LiarUserClass(kind, userID);

		users.put(userID, usr);
	}

	/**
	 * Adiciona um utilizador fanatic ao mapa de utilizadores registados
	 * neste FakeBook.
	 * @param kind - tipo de utilizador, neste caso, fanatic.
	 * @param userID - identificador do utilizador.
	 */
	private void addFanatic(String kind, String userID, List<String> fanatisms) {
		User usr = new FanaticUserClass(kind, userID, fanatisms);

		users.put(userID, usr);

		Iterator<String> it = fanatisms.iterator();

		while(it.hasNext()) {
			it.next();
			String fanatism = it.next();

			SortedSet<User> fanatics = fanaticsByTopic.get(fanatism);

			if(fanatics == null) {
				fanatics = new TreeSet<User>();

				fanaticsByTopic.put(fanatism, fanatics);
			}
			fanatics.add(usr);
		}
	}

	/**
	 * Atualiza a variavel mostPopularPost do tipo Post comparando-a com post.
	 * Esta comparacao depende dos numeros de comentario de cada Post, da ordem 
	 * alfabetica dos autores de cada Post e da ordem numerica dos identificadores 
	 * de cada Post
 	 * @param post - um Post a ser comparado com mostPopularPost.
	 */
	private void setMostPopularPost(Post post) {
		String postAuthorID = post.getAuthor().getID();
		String popularPostAuthorID =  mostPopularPost.getAuthor().getID();
		int postNumberOfComments = post.getNumberOfComments();
		int popularPostNumberOfComments = mostPopularPost.getNumberOfComments();

		if(postNumberOfComments > popularPostNumberOfComments) {
			mostPopularPost = post;
		} else {
			if(postNumberOfComments == popularPostNumberOfComments) {
				int compareToRes =  postAuthorID.compareTo(popularPostAuthorID);

				if(compareToRes < 0) {
					mostPopularPost = post;
				} else {
					if((compareToRes == 0) && (post.getID() > mostPopularPost.getID())) {
						mostPopularPost = post;
					}
				}
			}
		}
	}
	
	/**
	 * Atualiza a variavel topPoster do tipo User comparando-a com usr.
	 * A comparacao depende dos numeros de posts de cada User, dos numeros
	 * de comentarios de cada User, e da ordem alfabetica dos seus identificadores.  
	 * @param usr - um User a ser comparado com topPoster.
	 */
	private void setTopPoster(User usr) {
		int numberOfPosts = usr.getNumberOfPosts();
		int topPosterNumberOfPosts = topPoster.getNumberOfPosts();

		if(numberOfPosts > topPosterNumberOfPosts) {
			topPoster = usr;
		}
		else {
			if(numberOfPosts == topPosterNumberOfPosts) {
				int numberOfComments = usr.getNumberOfComments();
				int topPosterNumberOfComments = topPoster.getNumberOfComments();

				if(numberOfComments > topPosterNumberOfComments) {
					topPoster = usr;
				} else {
					if ((numberOfComments == topPosterNumberOfComments) && (usr.getID().compareTo(topPoster.getID()) < 0)) {
							topPoster = usr;
					}
				}
			}
		}
	}
	
}
