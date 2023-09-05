package anagrafiche;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import db.DB;

public class RestaurantSummary extends Restaurant {
	
	private int menuCount;

	public RestaurantSummary(
		int id, 
		String nome, 
		float costoConsegna, 
		float ordineMinimo, 
		RestaurantType tipo,
		Address indirizzo, 
		int menuCount
	) {
		super(id, nome, costoConsegna, ordineMinimo, tipo, indirizzo);
		this.menuCount = menuCount;
	}
	
	public static ArrayList<RestaurantSummary> getAll(){
		ArrayList<RestaurantSummary> list = new ArrayList<>();
		
		try {
			ResultSet rs = DB.getStmt().executeQuery(
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
				+ "ORDER BY r.NomeAttivita ASC"
			);
			
			PreparedStatement stmtCount = DB.getPreparedStmt("SELECT COUNT(*) FROM prodotti AS p WHERE p.p_Attivita = ?");
			
			while(rs.next()) {
				
				stmtCount.setInt(1, rs.getInt("ID_Attivita"));
				ResultSet rsCount = stmtCount.executeQuery();
				rsCount.next();
				
				list.add(
					new RestaurantSummary(
						rs.getInt("ID_Attivita"), 
						rs.getString("NomeAttivita"), 
						rs.getFloat("CostoConsegna"), 
						rs.getFloat("OrdineMinimo"), 
						new RestaurantType(
							rs.getInt("ID_Tipologia"), 
							rs.getString("Tipologia")
						), 
						new Address(
							rs.getString("Indirizzo"), 
							rs.getString("Civico"), 
							rs.getString("Citta")
						), 
						rsCount.getInt(1)
					)
				);
			}
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}

	public int getMenuCount() {
		return menuCount;
	}

	public void setMenuCount(int menuCount) {
		this.menuCount = menuCount;
	}
	
	

}
