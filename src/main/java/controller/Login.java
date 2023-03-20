package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UtenteDAO;
import model.Utente;

@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UtenteDAO utenteDAO; 

	public Login() {
		super();
		utenteDAO = new UtenteDAO();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		Utente utenteTrovato = utenteDAO.loginUtente(username, password);
		if (null != utenteTrovato) {
			session.setAttribute("utenteTrovato", utenteTrovato);
			request.setAttribute("listaImpasti", utenteDAO.getAllImpasti());
			request.setAttribute("listaIngredienti", utenteDAO.getAllIngredienti());
			request.setAttribute("listaPizze", utenteTrovato.getListaPizze());
			RequestDispatcher rd = request.getRequestDispatcher("dashboard.jsp");
			rd.forward(request, response);
		}

	}
}