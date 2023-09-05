package anagrafiche;

public class Restaurant {
	
	private int id;
	private String nome;
	private float costoConsegna;
	private float ordineMinimo;
	private RestaurantType tipo;
	private Address indirizzo;
	
	public Restaurant(
		int id, 
		String nome, 
		float costoConsegna, 
		float ordineMinimo, 
		RestaurantType tipo,
		Address indirizzo
	) {
		super();
		this.id = id;
		this.nome = nome;
		this.costoConsegna = costoConsegna;
		this.ordineMinimo = ordineMinimo;
		this.tipo = tipo;
		this.indirizzo = indirizzo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public float getCostoConsegna() {
		return costoConsegna;
	}

	public void setCostoConsegna(float costoConsegna) {
		this.costoConsegna = costoConsegna;
	}

	public float getOrdineMinimo() {
		return ordineMinimo;
	}

	public void setOrdineMinimo(float ordineMinimo) {
		this.ordineMinimo = ordineMinimo;
	}

	public RestaurantType getTipo() {
		return tipo;
	}

	public void setTipo(RestaurantType tipo) {
		this.tipo = tipo;
	}

	public Address getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(Address indirizzo) {
		this.indirizzo = indirizzo;
	}
	
	

}
