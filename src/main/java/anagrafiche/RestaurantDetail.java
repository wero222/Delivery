package anagrafiche;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import db.DB;

public class RestaurantDetail extends Restaurant {
	
	private ArrayList<Product> menu;
	private HashMap<String, ArrayList<Product>> menuGrouped;
	
	// recupera i dati del ristorante a partire dal suo ID
	// e costruisce l'oggetto
	// groupMenu stabilisce se il menu è raggruppato per fascia di prezzo oppure no
	// groupMenu = false -> ArrayList
	// groupMenu = true -> HashMap
	public RestaurantDetail(int id, boolean groupMenu) throws Exception {
		super(id, null, 0, 0, null, null);
		// recupero l'anagrafica del ristorante
		PreparedStatement stmt = DB.getPreparedStmt(
			"SELECT "
				+ "r.ID_Attivita, "
				+ "r.NomeAttivita, "
				+ "r.CostoConsegna, "
				+ "r.OrdineMinimo,"
				+ "i.Indirizzo,"
				+ "i.Civico,"
				+ "i.Citta,"
				+ "tipi.* "
			+ "FROM attivitacommerciali AS r "
			+ "LEFT JOIN attivitacommerciali_indirizzi AS ai ON ai.p_AttivitaCommerciale = r.ID_Attivita "
			+ "LEFT JOIN indirizzi AS i ON ai.p_Indirizzo = i.ID_INDIRIZZO "
			+ "INNER JOIN attivita_tipolgie AS tipi ON tipi.ID_Tipologia = r.p_Tipologia "
			+ "WHERE r.ID_Attivita = ?"
		);
		stmt.setInt(1, id);
		ResultSet rs = stmt.executeQuery();
		rs.next();
		super.setNome(rs.getString("NomeAttivita"));
		super.setCostoConsegna(rs.getFloat("CostoConsegna"));
		super.setOrdineMinimo(rs.getFloat("OrdineMinimo"));
		super.setTipo(new RestaurantType(
			rs.getInt("ID_Tipologia"), 
			rs.getString("Tipologia")
		));
		if(rs.getString("Indirizzo") != null) {
			super.setIndirizzo(new Address(
				rs.getString("Indirizzo"), 
				rs.getString("Civico"), 
				rs.getString("Citta")
			));
		}
		
		// recupero il menu
		PreparedStatement stmtMenu = DB.getPreparedStmt(
			"SELECT * FROM prodotti WHERE p_Attivita = ?"
		);
		stmtMenu.setInt(1, id);
		ResultSet rsMenu = stmtMenu.executeQuery();
		
		if(!groupMenu) {
			// istanzio il menu
			menu = new ArrayList<>();
			while(rsMenu.next()) {
				// popolo il menu
				menu.add(
					new Product(
						rsMenu.getInt("ID_PRODOTTO"),
						rsMenu.getString("NomeProdotto"),
						rsMenu.getString("DescrizioneProdotto"),
						rsMenu.getFloat("PrezzoProdotto")
					)
				);
			}
		}
		else {
			menuGrouped = new HashMap<>();
			menuGrouped.put("LOW", new ArrayList<Product>());
			menuGrouped.put("MEDIUM", new ArrayList<Product>());
			menuGrouped.put("HIGH", new ArrayList<Product>());
			while(rsMenu.next()) {
				String k = "LOW";
				if(rsMenu.getFloat("PrezzoProdotto") > 5 && rsMenu.getFloat("PrezzoProdotto") <= 10) {
					k = "MEDIUM";
				}
				if(rsMenu.getFloat("PrezzoProdotto") > 10) {
					k = "HIGH";
				}
				menuGrouped.get(k).add(
					new Product(
						rsMenu.getInt("ID_PRODOTTO"),
						rsMenu.getString("NomeProdotto"),
						rsMenu.getString("DescrizioneProdotto"),
						rsMenu.getFloat("PrezzoProdotto")
					)
				);
			}
		}
	}

	public RestaurantDetail(
			int id, 
			String nome, 
			float costoConsegna, 
			float ordineMinimo, 
			RestaurantType tipo,
			Address indirizzo, 
			ArrayList<Product> menu
	) {
		super(id, nome, costoConsegna, ordineMinimo, tipo, indirizzo);
		this.menu = menu;
	}

	public ArrayList<Product> getMenu() {
		return menu;
	}

	public void setMenu(ArrayList<Product> menu) {
		this.menu = menu;
	}

	public HashMap<String, ArrayList<Product>> getMenuGrouped() {
		return menuGrouped;
	}

	public void setMenuGrouped(HashMap<String, ArrayList<Product>> menuGrouped) {
		this.menuGrouped = menuGrouped;
	}
	
	

}
