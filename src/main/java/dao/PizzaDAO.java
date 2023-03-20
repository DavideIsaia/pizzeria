package dao;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import model.Ingrediente;
import model.Pizza;

public class PizzaDAO {
	static EntityManagerFactory emf = JPAAgent.getEntityManagerFactory();
	static EntityManager em = emf.createEntityManager();
	private UtenteDAO utenteDAO = new UtenteDAO(); 

	public Pizza savePizza(Pizza pizza) {
		em.getTransaction().begin();
		Pizza nuovaPizza = new Pizza();
		nuovaPizza.setUtente(pizza.getUtente());
		nuovaPizza.setNome(pizza.getNome());
		nuovaPizza.setImpasto(pizza.getImpasto());
		nuovaPizza.setListaIngredienti(pizza.getListaIngredienti());

		em.persist(nuovaPizza);
		em.getTransaction().commit();
		//em.close();
		return pizza;
	}

	public void deletePizza(int id) {
		em.getTransaction().begin();
		Pizza pizza = em.find(Pizza.class, id);

		if (pizza != null) {
			em.remove(pizza);
		}

		em.getTransaction().commit();
		//em.close();
	}

	public void updatePizza(int id, String nuovoNome, int idImpasto, 
			ArrayList<Ingrediente> ingredienti) {
		em.getTransaction().begin();
		Pizza pizza = em.find(Pizza.class, id);
		if (pizza != null) {
			pizza.setUtente(pizza.getUtente());
			pizza.setNome(nuovoNome);
			pizza.setImpasto(utenteDAO.getImpastoById(idImpasto));
			pizza.setListaIngredienti(ingredienti);
			em.persist(pizza);
			em.getTransaction().commit();
		}

		//em.close();
	}
}
