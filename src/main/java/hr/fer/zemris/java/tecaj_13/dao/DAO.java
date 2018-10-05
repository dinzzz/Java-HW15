package hr.fer.zemris.java.tecaj_13.dao;

import java.util.List;

import hr.fer.zemris.java.tecaj_13.model.BlogComment;
import hr.fer.zemris.java.tecaj_13.model.BlogEntry;
import hr.fer.zemris.java.tecaj_13.model.BlogUser;

/**
 * Interface which supports data operations between the web application and the
 * database. It consists support for handling blog users, blog entries and blog
 * comments.
 * 
 * @author Dinz
 *
 */
public interface DAO {

	/**
	 * Gets the blog entry from the given ID.
	 * 
	 * @param id
	 *            Blog entry id.
	 * @return Blog entry.
	 * @throws DAOException
	 */
	public BlogEntry getBlogEntry(Long id) throws DAOException;

	/**
	 * Gets the blog user from the given ID.
	 * 
	 * @param id
	 *            User ID.
	 * @return Blog user.
	 * @throws DAOException
	 */
	public BlogUser getBlogUser(Long id) throws DAOException;

	/**
	 * Gets the blog user from the given nickname.
	 * 
	 * @param nick
	 *            User nickname.
	 * @return Blog user.
	 * @throws DAOException
	 */
	public BlogUser getBlogUserNick(String nick) throws DAOException;

	/**
	 * Adds the blog user to the database.
	 * 
	 * @param user
	 *            User to be added.
	 * @throws DAOException
	 */
	public void addBlogUser(BlogUser user) throws DAOException;

	/**
	 * Gets all the available users from the database.
	 * 
	 * @return List of all users.
	 * @throws DAOException
	 */
	public List<BlogUser> getAuthors() throws DAOException;

	/**
	 * Gets all the entries whose author is a user with a given nickname.
	 * 
	 * @param nick
	 *            User's nickname.
	 * @return List of entries.
	 * @throws DAOException
	 */
	public List<BlogEntry> getEntriesNick(String nick) throws DAOException;

	/**
	 * Adds the blog entry to the database.
	 * 
	 * @param entry
	 *            Entry to be added.
	 * @throws DAOException
	 */
	public void addBlogEntry(BlogEntry entry) throws DAOException;

	/**
	 * Adds the blog comment to the database.
	 * 
	 * @param comment
	 *            Comment to be added.
	 * @throws DAOException
	 */
	public void addBlogComment(BlogComment comment) throws DAOException;

	/**
	 * Method which updates the blog entry.
	 * 
	 * @param entry
	 *            Entry to be updated.
	 * @throws DAOException
	 */
	public void updateBlogEntry(BlogEntry entry) throws DAOException;
}