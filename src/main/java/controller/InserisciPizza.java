package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.PizzaDAO;
import dao.UtenteDAO;
import model.Ingrediente;
import model.Pizza;
import model.Utente;

@WebServlet("/InserisciPizza")
public class InserisciPizza extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public InserisciPizza() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getParameter("CancellaPizza") != null) {
			deletePizza(Integer.parseInt(request.getParameter("CancellaPizza")), request, response);
		} else {
			creaPizza(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	public void creaPizza(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(true);

		// recupero i parametri dal form
		String nomePizza = request.getParameter("nomePizza");
		int idImpasto = Integer.parseInt(request.getParameter("idImpasto"));
		String[] idIngredienti = (request.getParameterValues("idIngredienti"));

		// recupero l'utente dalla session
		Utente utente = (Utente) session.getAttribute("utenteTrovato");

		// creo la nuova pizza
		Pizza nuovaPizza = new Pizza();
		nuovaPizza.setNome(nomePizza);
		nuovaPizza.setUtente(utente);
		nuovaPizza.setImpasto(UtenteDAO.getImpastoById(idImpasto));

		// aggiungo gli ingredienti
		ArrayList<Ingrediente> ingredienti = new ArrayList<Ingrediente>();
		for (String id : idIngredienti) {
			ingredienti.add(UtenteDAO.getIngredienteById(Integer.parseInt(id)));
		}
		nuovaPizza.setListaIngredienti(ingredienti);

		// salvo la nuova pizza nel db
		PizzaDAO.savePizza(nuovaPizza);

		RequestDispatcher rd = request.getRequestDispatcher("dashboard.jsp");
		request.setAttribute("listaIngredienti", UtenteDAO.getAllIngredienti());
		request.setAttribute("listaImpasti", UtenteDAO.getAllImpasti());
		request.setAttribute("listaPizze", UtenteDAO.getlistaPizze());
		rd.forward(request, response);
	}

	public void deletePizza(int id, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PizzaDAO.deletePizza(id);
		RequestDispatcher rd = request.getRequestDispatcher("dashboard.jsp");
		request.setAttribute("listaIngredienti", UtenteDAO.getAllIngredienti());
		request.setAttribute("listaImpasti", UtenteDAO.getAllImpasti());
		request.setAttribute("listaPizze", UtenteDAO.getlistaPizze());
		rd.forward(request, response);
	}

}
