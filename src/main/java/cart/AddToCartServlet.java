package cart;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import anagrafiche.Product;
import auth.User;

/**
 * Servlet implementation class AddToCartServlet
 */
@WebServlet("/app/add-to-cart")
public class AddToCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddToCartServlet() {
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
			int pid = Integer.parseInt(request.getParameter("pid"));
			int qta = Integer.parseInt(request.getParameter("qta"));
			// recupero l'oggetto User dalla sessione
			User user = User.getUser(request.getSession());
			Product p = new Product(pid);
			// aggiungo p nel cart
			user.getCart().add(p, qta);
			// aggiungo p nella request e la "inoltro" alla order.jsp
			// attenzione: NON CAMBIA L'URL NEL BROWSER, NON ANDREBBE USATO PER REDIRECT verso jsp
			request.setAttribute("PRODUCT_ADDED", p);
			request.getRequestDispatcher(
				"/app/order.jsp?idRistorante=" + request.getParameter("idRistorante")
			).forward(request, response);
		}
		catch(Exception e) {
			response.sendRedirect(request.getContextPath() + "/app/order.jsp?e=1&idRistorante=" + request.getParameter("idRistorante"));
		}
	}

}
