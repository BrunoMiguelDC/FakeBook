/*
  * @author Bruno Carmo n:57418 e Sahil Kumar n:57449
 */

package users;


/**
 * Classe que implementa a interface User, contendo, por isso, todos os metodos
 * e variaveis associados as acoes que um utilizador pode ter na aplicacao 
 * comuns a todas as subclasses desta classe, ou seja, todas as diferentes
 * variantes de utilizador, no caso desta aplicacao SelfCenteredClass,
 * NaiveClass, LiarClass e FananticClass.
 */


import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import exceptions.AlreadyFriendsException;
import exceptions.NoCommentsException;
import exceptions.NoFriendsException;
import exceptions.NoPostsException;
import exceptions.PostDoesNotExistException;
import messages.Comment;
import messages.Post;


public abstract class UserClass implements User, Comparable<User> {
	
	/**
	 * Tamanho das estruturas de dados estaticas por default.
	 */
	private static final int SIZE = 17;
	
	/**
	 * Stance de comentarios e posts possiveis na aplicacao, respetivamente.
	 */
	protected static final String POSITIVE = "positive";
	protected static final String NEGATIVE = "negative";
	
	protected static final String HONEST = "honest";
	protected static final String FAKE = "fake";
	
	
	/**
	 * Numero de comentarios deste User.
	 */
	private int numberOfComments;
	
	/**
	 * Numero de mentiras deste User.
	 */
	private int numberOfLies;
	
	/**
	 * Numero de posts recebidos(dos amigos) deste User.
	 */
	private int numberOfReceivedPosts;
	
	/**
	 * Tipo de utilizador.
	 */
	private String kind;
	
	/**
	 * Identificador deste User.
	 */
	private String userID;
	
	/**
	 * Mapa de comentarios(Comment) deste User por topico.
	 */
	private Map<String, List<Comment>> commentsByTopic;
	
	/**
	 * Mapa de Posts recibidos(dos amigos) deste User.
	 */
	private Map<String, List<Post>> receivedPosts;
	
	/**
	 * Conjunto dos posts comentados.
	 */
	private Set<Post> commentedPosts;
	
	/**
	 * Lista de posts deste User.
	 */
	private List<Post> posts;
	
	/**
	 * Mapa de amigos deste User.
	 */
	private SortedMap<String, User> friends;

	/**
	 * Construtor da classe apenas para ser invocado pelas subclasses.
	 * @param kind - tipo de utilizador.
	 * @param userID - identificador do utilizador.
	 */
	protected UserClass(String kind, String userID) {
		this.kind = kind;
		this.userID = userID;
		numberOfComments = 0;
		numberOfLies = 0;
		numberOfReceivedPosts = 0;
	
		commentsByTopic = new HashMap<String, List<Comment>>(SIZE);
		receivedPosts = new HashMap<String, List<Post>>(SIZE);
		friends = new TreeMap<String, User>(); 
		posts = new ArrayList<Post>(SIZE);
		commentedPosts = new HashSet<Post>(SIZE);
	}

	
	@Override
	public int compareTo(User usr) {
		return this.getID().compareTo(usr.getID());
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserClass other = (UserClass) obj;
		if (userID == null) {
			if (other.userID != null)
				return false;
		} else if (!userID.equals(other.userID))
			return false;
		return true;
	}


	@Override
	public abstract boolean canComment(String postStance, String commentStance, List<String> postTopics); 

	@Override
	public abstract boolean canPost(String postStance, List<String> postTopics);

	@Override
	public boolean hasCommentIn(String topic) {
		return commentsByTopic.containsKey(topic);
	}
	
	@Override
	public boolean hasFriendsPost(String friendID, int postID) {
		if(friendID.equals(this.getID())){
			return this.hasPost(postID);
		}
		List<Post> friendsPosts = receivedPosts.get(friendID);
		
		if(friendsPosts == null) {
			return false;
		}
		for(Post p : friendsPosts) {
			if(p.getID() == postID) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public boolean hasPost(int postID) {
		return (postID <= this.getNumberOfPosts()) && (postID > 0);
	}
	
	@Override
	public int getNumberOfAvailablePosts() {
		return posts.size() + numberOfReceivedPosts;
	}
	
	@Override
	public int getNumberOfComments() {
		return numberOfComments;
	}


	@Override
	public int getNumberOfFriends() {
		return friends.size();
	}
	
	@Override
	public float getPercentageOfCommentsByPosts() {
		float percentage = (((float) commentedPosts.size()) / (float) this.getNumberOfAvailablePosts()) * 100;
		return percentage;
	}
	
	@Override
	public int getNumberOfLies() {
		return numberOfLies;
	}
	
	@Override
	public int getNumberOfPosts() {
		return posts.size();
	}

	@Override
	public Iterator<Comment> getAllCommentsIn(String topic) throws NoCommentsException {
		List<Comment> comments = commentsByTopic.get(topic);
		
		if(comments == null) {
			throw new NoCommentsException();
		}
		
		return comments.iterator();
	}

	@Override
	public Iterator<Post> getAllPosts() throws NoPostsException {
		if(posts.isEmpty()) {
			throw new NoPostsException();
		}
		
		return posts.iterator();
	}

	@Override
	public Iterator<User> getAllFriends() throws NoFriendsException {
		if(friends.isEmpty()) {
			throw new NoFriendsException();
		}
		
		return friends.values().iterator();
	}

	@Override
	public Post getPost(int postID) throws PostDoesNotExistException {
		if(!this.hasPost(postID)) {
			throw new PostDoesNotExistException();
		}
		
		return posts.get(postID - 1);
	}

	@Override
	public String getID() {
		return userID;
	}

	@Override
	public String getKind() {
		return kind;
	}

	@Override
	public void addComment(Comment comment){
		Post post = comment.getPost();
		
		commentedPosts.add(post);
		
		Iterator<String> it = post.getTopicsIterator();
		
		while(it.hasNext()) {
			String topicID = it.next();
			List<Comment> list = commentsByTopic.get(topicID);
			
			if(list == null) {
				list = new LinkedList<Comment>();
				
				commentsByTopic.put(topicID, list);
			}	
			list.add(comment);
		}
		numberOfComments++;
	}
	
	@Override
	public void addFriend(User usr) throws AlreadyFriendsException {
		if(friends.containsKey(usr.getID())) {
			throw new AlreadyFriendsException();
		}

		friends.put(usr.getID(), usr);
	}

	@Override
	public void addPost(Post post) {
		posts.add(post);
	}

	@Override
	public void addFriendsPost(Post post) {
		String postAuthor = post.getAuthor().getID();
		List<Post> list = receivedPosts.get(postAuthor);
		
		if(list == null) {
			list = new ArrayList<Post>(SIZE);
			
			receivedPosts.put(postAuthor, list);
		}
		receivedPosts.get(postAuthor).add(post);
		numberOfReceivedPosts++;
	}
	
	@Override
	public void incLies() {
		numberOfLies++;
	}
	
}
