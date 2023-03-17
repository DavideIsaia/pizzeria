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
import model.Impasto;
import model.Ingrediente;
import model.Utente;

@WebServlet("/UpdatePizza")
public class UpdatePizza extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UtenteDAO utenteDAO; 
	private PizzaDAO pizzaDAO;

	public UpdatePizza() {
		super();
		utenteDAO = new UtenteDAO();
		pizzaDAO = new PizzaDAO();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// recupero i parametri dal form
		String nomePizza = request.getParameter("nuovoNome");
		int idImpasto = Integer.parseInt(request.getParameter("idImpasto"));
		String[] idIngredienti = (request.getParameterValues("idIngredienti"));
		ArrayList<Ingrediente> ingredienti = new ArrayList<Ingrediente>();
		for (String id : idIngredienti) {
			ingredienti.add(utenteDAO.getIngredienteById(Integer.parseInt(id)));
		}

		
		if (request.getParameter("idPizza") != null) {
			updatePizza(Integer.parseInt(request.getParameter("idPizza")), 
					nomePizza,
					idImpasto,
					ingredienti, 
					request, response);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	private void updatePizza(int id, String nuovoNome, int idImpasto, ArrayList<Ingrediente> ingredienti, 
			HttpServletRequest request,	HttpServletResponse response) 
					throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		Utente utente = (Utente) session.getAttribute("utenteTrovato");
		int id_user = utente.getId();
		
		// eseguo l'update
		pizzaDAO.updatePizza(id, nuovoNome, idImpasto, ingredienti);
		
		// ritorno alla dashoboard con i nuovi dati
		RequestDispatcher rd = request.getRequestDispatcher("PizzaServlet");
		rd.forward(request, response);
	}

}
