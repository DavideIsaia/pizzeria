package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import model.Pizza;

public class PizzaDAO {
	public static Pizza savePizza(Pizza pizza) {
		EntityManagerFactory emf = JPAAgent.getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Pizza nuovaPizza = new Pizza();
		nuovaPizza.setUtente(pizza.getUtente());
		nuovaPizza.setNome(pizza.getNome());
		nuovaPizza.setImpasto(pizza.getImpasto());
		nuovaPizza.setListaIngredienti(pizza.getListaIngredienti());
		
		em.persist(nuovaPizza);
		em.getTransaction().commit();
		em.close();
		return pizza;
	}

	public static void deletePizza(int id) {
		EntityManagerFactory emf = JPAAgent.getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Pizza pizza = em.find(Pizza.class, id);

		if (pizza != null) {
			em.remove(pizza);
		}

		em.getTransaction().commit();
		em.close();
	}

}
