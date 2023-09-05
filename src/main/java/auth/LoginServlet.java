package auth;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import db.DB;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String email = request.getParameter("email");
			String pwd = request.getParameter("pwd");
			PreparedStatement stmt = DB.getPreparedStmt(
				"SELECT ID_UTENTE, NomeUtente, CognomeUtente, EmailUtente FROM utenti WHERE EmailUtente = ? AND PSWd = ?"
			);
			stmt.setString(1, email);
			stmt.setString(2, Authentication.MD5(pwd));
			ResultSet rs = stmt.executeQuery();
			// login ok
			if(rs.next()) {
				// creo un oggetto User
				User user = new User(
					rs.getInt("ID_UTENTE"),
					rs.getString("NomeUtente"),
					rs.getString("CognomeUtente"),
					rs.getString("EmailUtente")
				);
				// creo una sessione
				HttpSession session = request.getSession();
				// lo metto in sessione
				session.setAttribute(User.sessionKey, user);
				// redirect -> /app
				response.sendRedirect(request.getContextPath() + "/app");
			}
			else {
				response.sendRedirect(request.getContextPath() + "/login.jsp?e=1");
			}
		}
		catch(Exception e) {
			response.sendRedirect(request.getContextPath() + "/login.jsp?e=1");
		}
	}

}
