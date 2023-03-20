package dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import model.Impasto;
import model.Ingrediente;
import model.Pizza;
import model.Utente;

public class UtenteDAO {

	static EntityManagerFactory emf = JPAAgent.getEntityManagerFactory();
	static EntityManager em = emf.createEntityManager();

	public Utente loginUtente(String username, String password) {

		List<Utente> listaUtenti = new ArrayList<Utente>();
		TypedQuery<Utente> query = em.createQuery(
				"select u from Utente u where u.username = :username and u.password = :password", Utente.class);
		query.setParameter("username", username);
		query.setParameter("password", password);
		listaUtenti = query.getResultList();
		return listaUtenti.isEmpty() ? null : listaUtenti.get(0);
	}

	public List<Impasto> getAllImpasti() {
		List<Impasto> listaImpasti = new ArrayList<Impasto>();
		TypedQuery<Impasto> query = em.createQuery("SELECT i FROM Impasto i", Impasto.class);
		listaImpasti = query.getResultList();
		return listaImpasti;
	}
	
	public Impasto getImpastoById(int id) {
		Impasto impasto = em.find(Impasto.class, id);
		return impasto;
		
	}

	public List<Ingrediente> getAllIngredienti() {
		List<Ingrediente> listaIngredienti = new ArrayList<Ingrediente>();
		TypedQuery<Ingrediente> query = em.createQuery("SELECT i FROM Ingrediente i", Ingrediente.class);
		listaIngredienti = query.getResultList();
		return listaIngredienti;
	}

	public Ingrediente getIngredienteById(int id) {
		Ingrediente ingrediente = em.find(Ingrediente.class, id);
		return ingrediente;
	}

	public List<Pizza> getlistaPizze(int id) {
		em = JPAAgent.getEntityManagerFactory().createEntityManager();
		Utente utente = em.find(Utente.class, id);
		return utente.getListaPizze();
	}
	
	public Pizza getPizzaById(int id) {
		Pizza pizza = em.find(Pizza.class, id);
		return pizza;
	}
}
