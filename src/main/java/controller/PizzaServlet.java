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

@WebServlet("/PizzaServlet")
public class PizzaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UtenteDAO utenteDAO;
	private PizzaDAO pizzaDAO;

	public PizzaServlet() {
		super();
		utenteDAO = new UtenteDAO();
		pizzaDAO = new PizzaDAO();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if(request.getParameter("CancellaPizza") != null) {
			deletePizza(Integer.parseInt(request.getParameter("CancellaPizza")), request, response);
		} else if(request.getParameter("CreaPizza") != null) {
			creaPizza(request, response);
		} else {
			caricaPagina(request, response);
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
		nuovaPizza.setImpasto(utenteDAO.getImpastoById(idImpasto));

		// aggiungo gli ingredienti
		ArrayList<Ingrediente> ingredienti = new ArrayList<Ingrediente>();
		for (String id : idIngredienti) {
			ingredienti.add(utenteDAO.getIngredienteById(Integer.parseInt(id)));
		}
		nuovaPizza.setListaIngredienti(ingredienti);

		// salvo la nuova pizza nel db
		pizzaDAO.savePizza(nuovaPizza);

		//ritorno alla dashoboard con i nuovi dati
		int id_user = utente.getId();
		request.setAttribute("listaIngredienti", utenteDAO.getAllIngredienti());
		request.setAttribute("listaImpasti", utenteDAO.getAllImpasti());
		request.setAttribute("listaPizze", utenteDAO.getlistaPizze(id_user));
		RequestDispatcher rd = request.getRequestDispatcher("dashboard.jsp");
		rd.forward(request, response);
	}

	public void deletePizza(int id, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		pizzaDAO.deletePizza(id);
		//ritorno alla dashoboard con i nuovi dati
		HttpSession session = request.getSession(true);
		Utente utente = (Utente) session.getAttribute("utenteTrovato");
		int id_user = utente.getId();
		RequestDispatcher rd = request.getRequestDispatcher("dashboard.jsp");
		request.setAttribute("listaIngredienti", utenteDAO.getAllIngredienti());
		request.setAttribute("listaImpasti", utenteDAO.getAllImpasti());
		request.setAttribute("listaPizze", utenteDAO.getlistaPizze(id_user));
		rd.forward(request, response);
	}
	
	private void caricaPagina(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		Utente utente = (Utente) session.getAttribute("utenteTrovato");
		int id_user = utente.getId();
		request.setAttribute("listaImpasti", utenteDAO.getAllImpasti());
		request.setAttribute("listaIngredienti", utenteDAO.getAllIngredienti());
		request.setAttribute("listaPizze", utenteDAO.getlistaPizze(id_user));	
		RequestDispatcher rd = request.getRequestDispatcher("dashboard.jsp");
		rd.forward(request, response);
	}

}
