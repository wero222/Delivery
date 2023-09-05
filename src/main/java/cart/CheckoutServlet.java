package cart;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import auth.User;
import db.DB;

/**
 * Servlet implementation class CheckoutServlet
 */
@WebServlet("/app/checkout")
public class CheckoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckoutServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cart cart = User.getUser(request.getSession()).getCart();
		String idRistorante = request.getParameter("idRistorante");
		if(cart.getCount() == 0) {
			response.sendRedirect(request.getContextPath() + "/app/order.jsp?idRistorante="+idRistorante+"&e=1");
			return;
		}
		try {
			// apro una transaction
			DB.getDb().setAutoCommit(false);
			
			// recupero l'id dell'indirizzo del ristorante
			PreparedStatement stmtIndirizzo = DB.getDb().prepareStatement(
				"SELECT p_Indirizzo FROM attivitacommerciali_indirizzi WHERE p_AttivitaCommerciale = ?"
			);
			stmtIndirizzo.setInt(1, Integer.parseInt(idRistorante));
			ResultSet rsIndirizzo = stmtIndirizzo.executeQuery();
			if(!rsIndirizzo.next()) {
				response.sendRedirect(request.getContextPath() + "/app/order.jsp?idRistorante="+idRistorante+"&e=3");
				return;
			}
			int idIndirizzoDa = rsIndirizzo.getInt(1);
			PreparedStatement stmtOrdine = DB.getPreparedStmtWithId(
				  "INSERT INTO `ordini` (`p_StatoOrdine`, `p_IndirizzoDa`, `p_IndirizzoA`, `CodOrdine`, `p_Utente`, `p_AttivitaCommerciale`, `TotaleOrdine`, `Sconto`) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?)"
			);
			stmtOrdine.setInt(1, 1);
			stmtOrdine.setInt(2, idIndirizzoDa);
			stmtOrdine.setInt(3, 1);
			// per il codice ordine utilizzo gli ultimi 10 caratteri del timestamp corrente
			String codiceOrdine = "" + (new Date()).getTime();
			codiceOrdine = codiceOrdine.substring(codiceOrdine.length() - 10);
			stmtOrdine.setString(4, codiceOrdine);
			stmtOrdine.setInt(5, User.getUser(request.getSession()).getId());
			stmtOrdine.setInt(6, Integer.parseInt(idRistorante));
			stmtOrdine.setFloat(7, cart.getTotal());
			stmtOrdine.setInt(8, 0);
			stmtOrdine.executeUpdate();
			
			// prendo l'id che ha assunto l'ordine dopo la INSERT
			ResultSet keys = stmtOrdine.getGeneratedKeys();
			keys.next();
			int idOrdine = keys.getInt(1);

			// genero il dettaglio ordine
			PreparedStatement stmtDettaglio = DB.getPreparedStmt(
				"INSERT INTO ordini_dettagli VALUES(?, ?, ?, ?)"
			);
			// per ogni prodotto nel carrello -> eseguo lo statement
			for(ProductInCart p : User.getUser(request.getSession()).getCart().getItems()) {
				stmtDettaglio.setInt(1, idOrdine);
				stmtDettaglio.setInt(2, p.getId());
				stmtDettaglio.setInt(3, p.getQta());
				stmtDettaglio.setFloat(4, p.getPrezzo());
				stmtDettaglio.executeUpdate();
			}
			
			// svuoto il carrello
			User.getUser(request.getSession()).getCart().clear();
			
			// commit() della transaction
			DB.getDb().commit();
			// chiudo la transaction
			DB.getDb().setAutoCommit(true);
			
			response.sendRedirect(request.getContextPath() + "/app/thankyou.jsp?codOrdine="+codiceOrdine);
			return;
		}
		catch(Exception e) {
			// annullo le operazioni della transaction
			try {
				DB.getDb().rollback();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			response.sendRedirect(request.getContextPath() + "/app/order.jsp?idRistorante="+idRistorante+"&e=2");
			return;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
