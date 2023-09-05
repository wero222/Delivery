package anagrafiche;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import db.DB;

public class Product {

	private int id;
	private String nome;
	private String descrizione;
	private float prezzo;
	
	public Product(int idProdotto) throws Exception {
		PreparedStatement stmt = DB.getPreparedStmt("SELECT * FROM prodotti WHERE ID_PRODOTTO = ?");
		stmt.setInt(1, idProdotto);
		ResultSet rs = stmt.executeQuery();
		rs.next();
		this.id = idProdotto;
		this.nome = rs.getString("NomeProdotto");
		this.descrizione = rs.getString("DescrizioneProdotto");
		this.prezzo = rs.getFloat("PrezzoProdotto");
	}
	
	public Product(int id, String nome, String descrizione, float prezzo) {
		super();
		this.id = id;
		this.nome = nome;
		this.descrizione = descrizione;
		this.prezzo = prezzo;
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

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public float getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(float prezzo) {
		this.prezzo = prezzo;
	}
	
	
	
}
