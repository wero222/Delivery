package auth;

import java.io.IOException;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.DB;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
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
		// REGISTER
		String nome = request.getParameter("nome");
		String cognome = request.getParameter("cognome");
		String email = request.getParameter("email");
		String pwd = request.getParameter("pwd");
		
		if(nome == null || cognome == null || email == null || pwd == null) {
			response.sendRedirect(request.getContextPath() + "/register.jsp?e=1");
			return;
		}
		
		try {
			PreparedStatement stmt = DB.getPreparedStmt(
				"INSERT INTO utenti(NomeUtente, CognomeUtente, EmailUtente, PSWd, DataRegistrazione) VALUES (?, ?, ?, ?, NOW())"
			);
			stmt.setString(1, nome);
			stmt.setString(2, cognome);
			stmt.setString(3, email);
			stmt.setString(4, Authentication.MD5(pwd));
			int r = stmt.executeUpdate();
			if(r != 1) {
				throw new Exception();
			}
			response.sendRedirect(request.getContextPath() + "/login.jsp?s=1");
		}
		catch(Exception e) {
			response.sendRedirect(request.getContextPath() + "/register.jsp?e=2");
			return;
		}
		
	}

	
	
	
	
	
}
