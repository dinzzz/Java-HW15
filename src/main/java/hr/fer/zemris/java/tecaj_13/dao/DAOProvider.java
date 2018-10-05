package hr.fer.zemris.java.tecaj_13.dao;

import hr.fer.zemris.java.tecaj_13.dao.jpa.JPADAOImpl;

/**
 * Class that represents a provider for the connection between the web
 * application and the database.
 * 
 * @author Dinz
 *
 */
public class DAOProvider {

	/**
	 * Implementation of the connecting tool which contains precise definition of
	 * the operation methods.
	 */
	private static DAO dao = new JPADAOImpl();

	/**
	 * Gets the current tool for the data operation.
	 * 
	 * @return Data operation support.
	 */
	public static DAO getDAO() {
		return dao;
	}

}