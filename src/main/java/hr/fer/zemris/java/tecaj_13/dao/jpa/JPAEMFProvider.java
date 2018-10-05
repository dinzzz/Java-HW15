package hr.fer.zemris.java.tecaj_13.dao.jpa;

import javax.persistence.EntityManagerFactory;

/**
 * Class which represents an entity manager factory provider.
 * 
 * @author Dinz
 *
 */
public class JPAEMFProvider {

	/**
	 * Entity manager factory instance.
	 */
	public static EntityManagerFactory emf;

	/**
	 * Gets the entity manager factory instance.
	 * 
	 * @return Entity manager factory instance.
	 */
	public static EntityManagerFactory getEmf() {
		return emf;
	}

	/**
	 * Sets the entity manager factory instance.
	 * 
	 * @param emf
	 *            Entity manager factory instance to be set for usage.
	 */
	public static void setEmf(EntityManagerFactory emf) {
		JPAEMFProvider.emf = emf;
	}
}