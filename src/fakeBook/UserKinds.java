/*
 * @author Bruno Carmo n:57418 e Sahil Kumar n:57449
 */

package fakeBook;


/**
 * Enumerado que contem os diferentes tipos de utilizador na aplicacao.
 */


public enum UserKinds {


	/**
	 * Tipos de utilizador na aplicacao.
	 */
	SELF_CENTERED("selfcentered"),
	NAIVE("naive"),
	LIAR("liar"),
	FANATIC("fanatic");


	/**
	 * Tipo de utilizador.
	 */
	private String description;


	/**
	 * Construtor do enumerado.
	 * Inicializa as variaveis de instancia com os parametros recebidos.
	 * @param description - tipo de utilizador.
	 */
	private UserKinds(String description) {
		this.description = description;
	}


	/**
	 * Devolve um tipo de utilizador.
	 * @return - tipo de utilizador.
	 */
	public String getDescritption() {
		return description;
	}

	/**
	 * Devolve o tipo de utilizador com a descricao igual a description(parametro) se 
	 * esse tipo de utilizador existir no enumerado, caso contrario devolve null.
	 * @param description - tipo de utilizador.
	 * @return - tipo de utilizador se existir.
	 */
	public static UserKinds getUserKind(String description) {
		for(UserKinds kind: UserKinds.values()) {
			if(kind.getDescritption().equals(description)) {
				return kind;
			}
		}
		return null;
	}

}
