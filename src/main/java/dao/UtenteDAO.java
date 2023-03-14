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

	public static Utente loginUtente(String username, String password) {

		List<Utente> listaUtenti = new ArrayList();
		TypedQuery<Utente> query = em.createQuery(
				"select u from Utente u where u.username = :username and u.password = :password", Utente.class);
		query.setParameter("username", username);
		query.setParameter("password", password);
		listaUtenti = query.getResultList();
		return listaUtenti.isEmpty() ? null : listaUtenti.get(0);
	}

	public static List<Impasto> getAllImpasti() {
		List<Impasto> listaImpasti = new ArrayList();
		TypedQuery<Impasto> query = em.createQuery("SELECT i FROM Impasto i", Impasto.class);
		listaImpasti = query.getResultList();
		return listaImpasti;
	}
	
	public static Impasto getImpastoById(int id) {
		Impasto impasto = new Impasto();
		TypedQuery<Impasto> query = em.createQuery("SELECT i FROM Impasto i WHERE i.id = :id", Impasto.class);
		query.setParameter("id", id);
		impasto = query.getSingleResult();
		return impasto;
		
	}

	public static List<Ingrediente> getAllIngredienti() {
		List<Ingrediente> listaIngredienti = new ArrayList();
		TypedQuery<Ingrediente> query = em.createQuery("SELECT i FROM Ingrediente i", Ingrediente.class);
		listaIngredienti = query.getResultList();
		return listaIngredienti;
	}

	public static Ingrediente getIngredienteById(int id) {
		Ingrediente ingrediente = new Ingrediente();
		TypedQuery<Ingrediente> query = em.createQuery("SELECT i FROM Ingrediente i WHERE i.id = :id", Ingrediente.class);
		query.setParameter("id", id);
		ingrediente = query.getSingleResult();
		return ingrediente;
	}

	public static List<Pizza> getlistaPizze() {
		List<Pizza> listaPizze = new ArrayList();
		TypedQuery<Pizza> query = em.createQuery("SELECT i FROM Pizza i", Pizza.class);
		listaPizze = query.getResultList();
		return listaPizze;
	}
}
