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
import model.Impasto;

@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Login() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		Utente utenteTrovato = UtenteDAO.loginUtente(username, password);
		if (null != utenteTrovato) {
			request.setAttribute("listaImpasti", UtenteDAO.getAllImpasti());
			request.setAttribute("listaIngredienti", UtenteDAO.getAllIngredienti());
			request.setAttribute("listaPizze", UtenteDAO.getlistaPizze());
			session.setAttribute("utenteTrovato", utenteTrovato);
			RequestDispatcher rd = request.getRequestDispatcher("dashboard.jsp");
			rd.forward(request, response);
		} else {
			request.setAttribute("error", "Username o Password non validi");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}

	}
}