package anagrafiche;

public class Address {

	private String indirizzo;
	private String civico;
	private String citta;
	
	public Address(String indirizzo, String civico, String citta) {
		super();
		this.indirizzo = indirizzo;
		this.civico = civico;
		this.citta = citta;
	}

	public String getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	public String getCivico() {
		return civico;
	}

	public void setCivico(String civico) {
		this.civico = civico;
	}

	public String getCitta() {
		return citta;
	}

	public void setCitta(String citta) {
		this.citta = citta;
	}
	
	
	
}
