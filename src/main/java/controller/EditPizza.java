package controller;

import java.io.IOException;

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
import model.Pizza;
import model.Utente;

@WebServlet("/EditPizza")
public class EditPizza extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UtenteDAO utenteDAO; 

	public EditPizza() {
		super();
		utenteDAO = new UtenteDAO();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			
			// recupero tutti gli impasti e gli ingredienti
			request.setAttribute("listaImpasti", utenteDAO.getAllImpasti());
			request.setAttribute("listaIngredienti", utenteDAO.getAllIngredienti());
			
			// recupero i dati della pizza specifica
			int idPizza = Integer.parseInt(request.getParameter("idPizza"));
			Pizza pizza = utenteDAO.getPizzaById(idPizza);
			
			// setto i dati della pizza per la visualizzazione in pagina
			RequestDispatcher rd = request.getRequestDispatcher("edit.jsp");
			request.setAttribute("pizza", pizza);
			rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
