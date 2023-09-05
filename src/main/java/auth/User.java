package auth;

import javax.servlet.http.HttpSession;

import cart.Cart;

public class User {
	
	public static String sessionKey = "THE_USER";
	private final int id;
	private String nome;
	private String cognome;
	private String email;
	private Cart cart;
	
	public User(int id, String nome, String cognome, String email) {
		super();
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
		this.email = email;
		this.cart = new Cart();
	}

	public static boolean isLogged(HttpSession session) {
		return getUser(session) != null;
	}
	
	public static User getUser(HttpSession session) {
		// prendo l'Object dentro la sessionKey e verifico che sia di tipo User
		try {
			User u = (User)session.getAttribute(sessionKey);
			return u;
		}
		// se per qualche motivo l'oggetto che trovo nella sessionKey non è User -> FAIL
		catch(ClassCastException e) {
			return null;
		}
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getId() {
		return id;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}
	
	

}
