/*
 * @author Bruno Carmo n:57418 e Sahil Kumar n:57449
 */

import exceptions.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import fakeBook.FakeBook;
import fakeBook.FakeBookClass;
import messages.Comment;
import messages.Post;
import users.User;


/**
* Classe que serve de conexao entre um utilizador e a aplicacao, ou seja, recebe os comandos e 
* processa os usando a classe FakeBook e retornando o feedback de cada comando.
*/

public class Main {

	/*
	 * Comandos do utilizador.
	 */
	private static final String REGISTER                    = "REGISTER";
	private static final String LIST_USERS                  = "USERS";
	private static final String ADD_FRIEND                  = "ADDFRIEND";
	private static final String LIST_USER_FRIENDS           = "FRIENDS";
	private static final String POST                        = "POST";
	private static final String LIST_USER_POSTS             = "USERPOSTS";
	private static final String COMMENT                     = "COMMENT";
	private static final String READ_POST                   = "READPOST";
	private static final String LIST_USER_COMMENTS_IN_TOPIC = "COMMENTSBYUSER";
	private static final String LIST_TOPIC_FANATICS         = "TOPICFANATICS";
	private static final String LIST_TOPIC_POSTS            = "TOPICPOSTS";
	private static final String POPULAR_POST                = "POPULARPOST";
	private static final String TOP_POSTER                  = "TOPPOSTER";
	private static final String RESPONSIVE                  = "RESPONSIVE";
	private static final String SHAMELESS                   = "SHAMELESS";
	private static final String HELP                        = "HELP";
	private static final String EXIT					    = "EXIT";
	
	/*
	 * Feedback dado pelo programa.
	 */
	private static final String REGISTER_SUCCESS         = "%s registered.\n"; // %s: id do utilizador.
	private static final String INVALID_KIND             = "%s is an invalid user kind!\n"; // %s: tipo de utilizador.
	private static final String DUPLICATE_USER_ID        = "%s already exists!\n"; // %s: id do utilizador.
	private static final String LIST_ALL_USERS_FORMAT    = "%s [%s] %d %d %d\n"; // %s: id e kind do utilizador. %d: numero de amigos, posts e comentarios.
	private static final String NEW_FRIEND               = "%s is friend of %s.\n"; // %s: id dos utilizadores que ficaram amigos.
	private static final String NO_USER                  = "%s does not exist!\n"; // %s: id do utilizador.
	private static final String SAME_USER                = "%s cannot be the same as %s!\n"; // %s:id do utilizador.
	private static final String DUPLICATE_FRIENDSHIP     = "%s must really admire %s!\n"; // %s: id dos utilizadores que ja sao amigos.
	private static final String NO_FRIENDS               = "%s has no friends!\n"; // %s: id do utilizador.
	private static final String POST_SUCCESS             = "%s sent a %s post to %d friends. Post id = %d.\n"; // %s: id do utilizador e veracidade do post, respetivamente. %d: numero de amigos e id do post, respetivamente.
	private static final String USER_POSTS               = "%s posts:\n"; // %s: id do utilizador.
	private static final String LIST_USER_POSTS_FORMAT   = "%d. [%s] %s [%d comments]\n"; //%d: id do post e numero de comentarios. %s: tipo de post e o seu conteudo. 
	private static final String NO_POSTS                 = "%s has no posts!\n"; // %s: id do utilizador.
	private static final String NO_ACCESS_TO_POST        = "%s has no access to post %d by %s!\n"; // %s: id do utilizador que ia comentar e id do utilizador que fez o post, respetivamente. %d: id do post.
	private static final String NO_POST                  = "%s has no post %d!\n"; // %s: id do utilizador que seria o suposto autor do post. %d: id do post.
	private static final String CANNOT_COMMENT           = "%s cannot comment on this post!\n"; // %s: id do utilizador que ia comentar.
	private static final String READ_POST_FORMAT         = "[%s %s] %s\n"; // %s: id o utilizador, tipo de mensagem e conteudo do post. 
	private static final String LIST_COMMENTS_FORMAT     = "[%s %s %d %s] %s\n"; // %s:id do utilizador, tipo de post, tipo de comentario, conteudo do comentario. %d: id do post.
	private static final String NO_FANATICISM            = "Oh please, who would be a fanatic of %s?\n"; // %s: tipo de fanatismo.
	private static final String LIST_TOPIC_POSTS_FORMAT  = "%s %d %d: %s\n"; // %s: id do utilizador e conteudo do post. %d: id do post e numero de comentarios no post. 
	private static final String NO_TOPIC_POSTS           = "Oh please, who would write about %s?\n"; // %s: nome/id do topico.
	private static final String POPULAR_POST_FORMAT      = "%s %d %d: %s\n"; // %s: id do utilizador e messagem do post, respetivamente. %d: id do post e numero de comentarios.
	private static final String TOP_FORMAT               = "%s %d %d.\n"; // %s: id do utilizador. %d:numero de posts do utilizador ou numero de posts comentados e numero de comentarios ou numero de posts a que tem acesso, respetivamente.
	private static final String BIGGEST_LIAR_FORMAT      = "%s %d.\n"; // %s: id do utilizador. %d: numero de mentiras. 
	
	private static final String NO_USERS_IN_FB           = "There are no users!";
	private static final String INVALID_FANATICISMS      = "Invalid fanaticism list!";
	private static final String INVALID_HASHTAGS         = "Invalid hashtags list!";
	private static final String INVALID_POST_STANCE      = "Inadequate stance!";
	private static final String COMMENT_SUCCESS          = "Comment added!";
	private static final String INAVLID_COMMENT_STANCE   = "Invalid comment stance!";
	private static final String NO_COMMENTS              = "No comments!";
	private static final String INVALID_NUMBER_OF_POSTS  = "Invalid number of posts to present!";
	private static final String NO_POSTS_IN_FB_1         = "Social distancing has reached fakebook. Please post something.";
	private static final String NO_POSTS_IN_FB_2         = "Social distancing has reached fakebook. Post something to become the king of posters.";
	private static final String NO_POSTS_IN_FB_3         = "Social distancing has reached fakebook. Post something and then comment your own post to become the king of responsiveness.";
	private static final String NO_POSTS_IN_FB_4         = "Social distancing has reached fakebook. Post a lie and become the king of liars.";
	private static final String UNKNOWN_CMD_MSG          = "Unknown command. Type help to see available commands.";
	private static final String EXIT_MSG                 = "Bye!";
	
	/**
	 * Leitor de comandos introduzidos pelo utilizador.
	 * Pre: in != null
	 * @param in - permite ler os comandos introduzidos pelo utilizador.
	 * @return - comando (String) introduzida pelo utilizador. 
	 */	
	private static String getCommand(Scanner in) {
		return in.next().toUpperCase();
	}
	
	/**
	 * Intrepretador de comandos do programa.
	 * Os parametros in e fb sao passados aos varios metodos auxiliares que processao os comandos
	 * do sistema logo as pre condicoes estendem se aos metodos auxiliares.
	 * Pre: cmd != null && in != null && fb != null
	 * @param cmd - comando introduzido pelo utilizador.
	 * @param in - passado como parametro nos metedos que executam o comando introduzido pelo utilizador permitindo ler
	 * os dados introduzidos.
	 * @param fb - objeto do tipo FakeBook que suporta as acoes dos comandos.
	 */
	private static void executeCommand(Scanner in, String cmd, FakeBook fb) {
		switch(cmd) {
			case REGISTER:
				processRegister(in, fb);
				break;
			case LIST_USERS:
				in.nextLine();
				processListUsers(fb);
				break;
			case ADD_FRIEND:
				processAddFriend(in, fb);
				break;
			case LIST_USER_FRIENDS:
				processListUserFriends(in, fb);
				break;
			case POST:
				processPost(in, fb);
				break;
			case LIST_USER_POSTS:
				processListUserPosts(in, fb);
				break;
			case COMMENT:
				processComment(in, fb);
				break;
			case READ_POST:
				processReadPost(in, fb);
				break;
			case LIST_USER_COMMENTS_IN_TOPIC:
				processListUserCommentsInTopic(in, fb);
				break;
			case LIST_TOPIC_FANATICS:
				processListTopicFanatics(in, fb);
				break;
			case LIST_TOPIC_POSTS:
				processListTopicPosts(in, fb);
				break;
			case POPULAR_POST:
				in.nextLine();
				processPopularPost(fb);
				break;
			case TOP_POSTER:
				in.nextLine();
				processTopPoster(fb);
				break;
			case RESPONSIVE:
				in.nextLine();
				processResponsive(fb);
				break;
			case SHAMELESS:
				in.nextLine();
				processShameless(fb);
				break;
			case HELP:
				in.nextLine();
				processHelp();
				break;
			default:
				in.nextLine();
				System.out.println(UNKNOWN_CMD_MSG);	
		}
	}
	
	/*
	 * Cada metodo executa o comando da aplicacao, correspondente.
	 */
	
	/**
	 * Regista um novo utilizador na aplicacao.
	 * Pre: - kind != null && userID != null 
	 * @param in - o input de onde os dados vao ser lidos.
	 * @param fb - o FakeBook no qual se regista o utilizador.
	 */
	private static void processRegister(Scanner in, FakeBook fb) {
		String kind = in.next();
		String userID = in.nextLine().trim();
		List<String> hashtags = null;
		
		try {
			if(kind.equals("fanatic")) {
				int numberOfFanatisms = in.nextInt();
				hashtags = new ArrayList<String>(numberOfFanatisms * 2);
				int i = numberOfFanatisms;
				
				while(i > 0) {
					hashtags.add(in.next());
					hashtags.add(in.next());
					i--;
				}
				in.nextLine();
			}
			fb.register(kind, userID, hashtags);
			System.out.printf(REGISTER_SUCCESS, userID);
			
		} catch (UnknownKindException e) {
			System.out.printf(INVALID_KIND, kind);
		} catch (UserAlreadyExistsException e) {
			System.out.printf(DUPLICATE_USER_ID, userID);
		} catch (InvalidHashtagListException e) {
			System.out.println(INVALID_FANATICISMS);
		}
	}
	
	/**
	 * Lista todos os utilizadores da aplicacao.
	 * @param fb - o FakeBook que contem todos os utlizadores da aplicacao a listar.
	 */
	private static void processListUsers(FakeBook fb) {
		try {
			Iterator<User> it = fb.listUsers();
			
			while(it.hasNext()) {
				User usr = it.next();
				System.out.printf(LIST_ALL_USERS_FORMAT, usr.getID(), usr.getKind(), usr.getNumberOfFriends(), 
						usr.getNumberOfPosts(), usr.getNumberOfComments());
			}
			
		} catch (NoUsersException e) {
			System.out.println(NO_USERS_IN_FB);
		}
	}
	
	/**
	 * Cria uma nova amizade entre dois utilizadores da aplicacao.
	 * Pre: - user1ID != null && user2ID != null
	 * @param in - o input de onde os dados vao ser lidos.
	 * @param fb - o FakeBook no qual se adiciona a amizade.
	 */
	private static void processAddFriend(Scanner in, FakeBook fb) {
		String user1ID = in.nextLine().trim();
		String user2ID = in.nextLine().trim();
		
		try {
			fb.addFriend(user1ID, user2ID);
			System.out.printf(NEW_FRIEND, user1ID, user2ID);
			
		} catch (UserDoesNotExistException e) {
			System.out.printf(NO_USER, e.getMessage());
		} catch (DuplicateUserException e) {
			System.out.printf(SAME_USER, user1ID, user1ID);
		} catch (AlreadyFriendsException e) {
			System.out.printf(DUPLICATE_FRIENDSHIP, user1ID, user2ID);
		}
	}
	
	/**
	 * Lista todos os amigos de um utilizador.
	 * Pre: - userID != null
	 * @param in - o input de onde os dados vao ser lidos.
	 * @param fb - o FakeBook que contem o utilizador do qual vao ser listados todos os seus amigos.
	 */
	private static void processListUserFriends(Scanner in, FakeBook fb) {
		String userID = in.nextLine().trim();
		
		try {
			Iterator<User> it = fb.listUserFriends(userID);
			User usr = it.next();
			
			System.out.print(usr.getID());
			while(it.hasNext()) {
				usr = it.next();
				System.out.print(", " + usr.getID());
			}
			System.out.printf(".\n");
			
		} catch (UserDoesNotExistException e) {
			System.out.printf(NO_USER, userID);
		} catch (NoFriendsException e) {
			System.out.printf(NO_FRIENDS, userID);
		}
	}
	
	/**
	 * Cria/publica um novo post na aplicacao. 
	 * Pre: - userID != null
	 * @param in - o input de onde os dados vao ser lidos.
	 * @param fb - o FakeBook onde vai ser criado/publicado o post.
	 */
	private static void processPost(Scanner in, FakeBook fb) {
		String userID = in.nextLine().trim();
		int numberOfTopics = in.nextInt();
		
		try {
			List<String> topics = new ArrayList<String>(numberOfTopics);
			int i = numberOfTopics;
			
			while(i > 0) {
				topics.add(in.next());
				i--;
			}
			in.nextLine();
			String postStance = in.next();
			String msg = in.nextLine().trim();
			
			fb.post(userID, postStance, topics,msg);	
			System.out.printf(POST_SUCCESS, userID, postStance, fb.getNumberOfFriendsOf(userID), fb.getNumberOfPostsOf(userID));
		
		} catch (UserDoesNotExistException e) {
			System.out.printf(NO_USER, userID);
		} catch (InvalidNumberException e) {
			System.out.println(INVALID_HASHTAGS);
		} catch (InvalidHashtagListException e) {
			System.out.println(INVALID_HASHTAGS);
		} catch (InvalidStanceException e) {
			System.out.println(INVALID_POST_STANCE);
		}
	}
	
	/**
	 * Lista todos os posts de um dado utilizador.
	 * Pre: - userID != null
	 * @param in - o input de onde os dados vao ser lidos.
	 * @param fb - o FakeBook que contem todos os posts a listar de um utilizador.
	 */
	private static void processListUserPosts(Scanner in, FakeBook fb) {
		String userID = in.nextLine().trim();
		
		try {
			Iterator<Post> it = fb.listUserPosts(userID);
			
			System.out.printf(USER_POSTS, userID);
			while(it.hasNext()) {
				Post post = it.next();
				System.out.printf(LIST_USER_POSTS_FORMAT, post.getID(), post.getMessageStance(), post.getText(),
						post.getNumberOfComments());
			}
			
		} catch (UserDoesNotExistException e) {
			System.out.printf(NO_USER, userID);
		} catch (NoPostsException e) {
			System.out.printf(Main.NO_POSTS, userID);
		}
	}
	
	/**
	 * Cria/publica um comentario num post existente. 
	 * Pre: - commenterID != null && authorID != null && 
	 * postID != null && stance != null && msg != null
	 * @param in - o input de onde os dados vao ser lidos.
	 * @param fb - o FakeBook onde vai se criado/publicado o comentario.
	 */
	private static void processComment(Scanner in, FakeBook fb) {
		String commenterID = in.nextLine().trim();
		String authorID = in.nextLine();
		int postID = in.nextInt();
		String stance = in.next();
		String msg = in.nextLine().trim();
		
		try {
			fb.comment(commenterID, authorID, postID, stance, msg);
			System.out.println(COMMENT_SUCCESS);
			
		} catch (UserDoesNotExistException e) {
			System.out.printf(NO_USER, e.getMessage());
		} catch (PostDoesNotExistException e) {
			System.out.printf(NO_POST, authorID, postID);
		} catch (UserHasNoAccessException e) {
			System.out.printf(NO_ACCESS_TO_POST, commenterID, postID, authorID);
		} catch (CannotCommentException e) {
			System.out.printf(CANNOT_COMMENT, commenterID);
		} catch (InvalidStanceException e) {
			System.out.println(INAVLID_COMMENT_STANCE);
		}
	}
	
	/**
	 * Mostra informacoes sobre um post. 
	 * Pre: - authorID != null && postID != null
	 * @param in - o input de onde os dados vao ser lidos.
	 * @param fb - o FakeBook que contem o post a ser lido.
	 */
	private static void processReadPost(Scanner in, FakeBook fb) {
		String authorID = in.nextLine().trim();
		int postID = in.nextInt();
		in.nextLine();
		
		try {
			Post post = fb.readPost(authorID, postID);
			
			System.out.printf(READ_POST_FORMAT, post.getAuthor().getID(), post.getMessageStance(),post.getText());
			
			Iterator<Comment> it = post.getAllComents(); 
			
			while(it.hasNext()) {
				Comment comment = it.next();
				
				System.out.printf(READ_POST_FORMAT, comment.getAuthor().getID(), comment.getMessageStance(),comment.getText());
			}
			
		} catch (UserDoesNotExistException e) {
			System.out.printf(NO_USER, authorID);
		} catch (PostDoesNotExistException e) {
			System.out.printf(NO_POST, authorID, postID);
		} catch (NoCommentsException e) {
			System.out.println(NO_COMMENTS);
		}
	}
	
	/**
	 * Lista todos os comentarios feitos por um utilizador num dado topico.
	 * Pre: - userID != null && topic != null
	 * @param in - o input de onde os dados vao ser lidos.
	 * @param fb - o FakeBook que contem o utilizador que fez os comentarios a serem listados.
	 */
	private static void processListUserCommentsInTopic(Scanner in, FakeBook fb) {
		String userID = in.nextLine().trim();
		String topic = in.nextLine();
		
		try {
			Iterator<Comment> it = fb.listUserCommentsInTopic(userID, topic);
			
			while(it.hasNext()) {
				Comment comment = it.next();
				Post post = comment.getPost();
				
				System.out.printf(LIST_COMMENTS_FORMAT, post.getAuthor().getID(), post.getMessageStance(),post.getID(), 
						comment.getMessageStance(), comment.getText());
			}
			
		} catch (UserDoesNotExistException e) {
			System.out.printf(NO_USER, userID);
		} catch (NoCommentsException e) {
			System.out.println(NO_COMMENTS);
		}
	}
	
	/**
	 * Lista todos os utilizadores do tipo fanaticos com um dado topico.
	 * Pre: - topic != null 
	 * @param in - o input de onde os dados vao ser lidos.
	 * @param fb - o FakeBook que contem os utilizadores fanaticos a listar.
	 */
	private static void processListTopicFanatics(Scanner in, FakeBook fb) {
		String topic = in.nextLine().trim();
		
		try {
			Iterator<User> it = fb.listTopicFanatics(topic);
			
			System.out.print(it.next().getID());
			while(it.hasNext()) {
				User usr = it.next();
				System.out.print(", " + usr.getID());
			}
			System.out.println(".");
			
		} catch (NoTopicFanaticsException e) {
			System.out.printf(NO_FANATICISM, topic);
		}
	}
	
	/**
	 * Lista todos os posts com um dado topico.
	 * Pre: - topic != null && numberOfPostsToList != null
	 * @param in - o input de onde os dados vao ser lidos.
	 * @param fb - o FakeBook que contem os posts a listar com um dadao topico.
	 */
	private static void processListTopicPosts(Scanner in, FakeBook fb) {
		String topic = in.next();
		int numberOfPostsToList = in.nextInt();
		in.nextLine();
		
		try {
			Iterator<Post> it = fb.listTopicPosts(topic, numberOfPostsToList);
			while(it.hasNext()) {
				Post post = it.next();
				System.out.printf(LIST_TOPIC_POSTS_FORMAT, post.getAuthor().getID(), post.getID(), post.getNumberOfComments(),
						post.getText());
			}
			
		} catch (InvalidNumberException e) {
			System.out.println(INVALID_NUMBER_OF_POSTS);
		} catch (NoTopicPostsException e) {
			System.out.printf(NO_TOPIC_POSTS, topic);
		}
	}
	
	/**
	 * Mostra informacoes sobre o post com mais comentarios na aplicacao.
	 * @param fb - o FakeBook que contem o post.
	 */
	private static void processPopularPost(FakeBook fb) {
		try {
			Post post = fb.mostPopularPost();
			
			System.out.printf(POPULAR_POST_FORMAT, post.getAuthor().getID(), post.getID(), post.getNumberOfComments(),
					post.getText());
			
		} catch (NoMessagesException e) {
			System.out.println(NO_POSTS_IN_FB_1);
		}
	}
	
	/**
	 * Mostra informacoes sobre o utilizador com o maior numero de posts na aplicacao.
	 * @param fb - o FakeBook que contem o utilizador.
	 */
	private static void processTopPoster(FakeBook fb) {
		try {
			User usr = fb.topPoster();
			
			System.out.printf(TOP_FORMAT, usr.getID(), usr.getNumberOfPosts(), usr.getNumberOfComments());
			
		} catch (NoPostsException e) {
			System.out.println(NO_POSTS_IN_FB_2);
		}
	}
	
	/**
	 * Mostra informacoes sobre o utilizador com a maior percentagem de posts comentados.
	 * @param fb - o FakeBook que contem o utilizador.
	 */	
	private static void processResponsive(FakeBook fb) {
		try {
			User usr = fb.mostResponsive();
			
			System.out.printf(TOP_FORMAT, usr.getID(), usr.getNumberOfComments(), usr.getNumberOfAvailablePosts());
			
		} catch (NoPostsException e) {
			System.out.println(NO_POSTS_IN_FB_3);
		}
	}
	
	/**
	 * Mostra informacoes sobre o utilizador que mais mentiu na aplicacao.
	 * @param fb - o FakeBook que contem o utilizador.
	 */
	private static void processShameless(FakeBook fb) {
		try {
			User usr = fb.mostShameless();
			
			System.out.printf(BIGGEST_LIAR_FORMAT, usr.getID(), usr.getNumberOfLies());
			
		} catch (NoLiesException e) {
			System.out.println(NO_POSTS_IN_FB_4);
		}
	}
	
	/**
	 * Escreve todos os comandos existentes no programa.
	 * @param dManager - o SafeDocManager a devolver os nomes dos comandos.
	 */
	private static void processHelp() {
		for(AppCommands cmd : AppCommands.values()) {
			System.out.println(cmd.getDescription());
		}
	}
	
	
	
	/**
	 * Cria os objetos necessarios para a aplicacao.
	 * Invoca o intrepretador de comandos e o leitor de comandos.
	 * @param args -argumentos para execucao da aplicacao. Nao utilizado neste programa. 
	 */
	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);
		
		FakeBook fb = new FakeBookClass();
		
		String cmd = getCommand(in);
		
		while(!cmd.equals(EXIT)) {
			executeCommand(in, cmd, fb);
			cmd = getCommand(in);
		}
		System.out.println(EXIT_MSG);
		in.close();
	}

}
