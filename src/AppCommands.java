/*
 * @author Bruno Carmo n:57418 e Sahil Kumar n:57449
 */


/**
 * Enumerado que contem todos os comandos do sistema.
 */


public enum AppCommands {

	/**
	 * Comandos do sistema.
	 */
	REGISTER("register - registers a new user"),
	USERS("users - lists all users"),
	ADDFRINED("addfriend - adds a new friend"),
	FRIENDS("friends - lists the user friends"),
	POST("post - posts a new message"),
	USERPOSTS("userposts - lists all posts by a user"),
	COMMENT("comment - user comments on a post"),
	READPOST("readpost - prints detailed info on a post"),
	COMMENTSBYUSER("commentsbyuser - shows all the comments by a user on a given post"),
	TOPICFANATICS("topicfanatics - shows a list of fanatic users on a topic"),
	TOPICPOSTS("topicposts - shows a list of posts on a given topic"),
	POPULARPOST("popularpost - shows the most commented post"),
	TOPPOSTER("topposter - shows the user with more posts"),
	RESPONSIVE("responsive - shows the user with a higher percentage of commented posts"),
	SHAMELESS("shameless - shows the top liars"),
	HELP("help - shows the available commands"),
	EXIT("exit - terminates the execution of the program");
	
	/**
	 * Descricao do comando.
	 */
	private String description;


	/**
	 * Construtor do enumerado.
	 * Inicializa a variavel description com o parametro recebido.
	 * Pre: - description != null
	 * @param description - descricao do comando.
	 */
	private AppCommands(String description) {
		this.description = description;
	}
	
	
	/**
	 * Metodo que devolve uma descricao do comando.
	 */
	public String getDescription() {
		return description;
	}
		
}
