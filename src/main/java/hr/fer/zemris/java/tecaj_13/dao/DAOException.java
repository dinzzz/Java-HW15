package hr.fer.zemris.java.tecaj_13.dao;

/**
 * Class that represents an exception which occurs when there's a problem with
 * data operations between the application and the database.
 * 
 * @author Dinz
 *
 */
public class DAOException extends RuntimeException {

	/**
	 * Serial.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructs a new DAOException with appropriate message and a cause.
	 * 
	 * @param message
	 *            Message of the exception.
	 * @param cause
	 *            Cause of the exception.
	 */
	public DAOException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Constructs a new DAOException with appropriate message.
	 * 
	 * @param message
	 *            Message of the exception.
	 */
	public DAOException(String message) {
		super(message);
	}
}