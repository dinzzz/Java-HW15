package hr.fer.zemris.java.tecaj_13.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import hr.fer.zemris.java.tecaj_13.model.BlogComment;
import hr.fer.zemris.java.tecaj_13.dao.DAO;
import hr.fer.zemris.java.tecaj_13.dao.DAOException;
import hr.fer.zemris.java.tecaj_13.model.BlogEntry;
import hr.fer.zemris.java.tecaj_13.model.BlogUser;

/**
 * Implementation of the support for the data operation between web application
 * and the database. This class contains exact method definition which support
 * operations with blog entries, blog users and blog comments.
 * 
 * @author Dinz
 *
 */
public class JPADAOImpl implements DAO {

	@Override
	public BlogEntry getBlogEntry(Long id) throws DAOException {
		try {
			BlogEntry blogEntry = JPAEMProvider.getEntityManager().find(BlogEntry.class, id);
			return blogEntry;
		} catch (NoResultException ex) {
			return null;
		}
	}

	@Override
	public BlogUser getBlogUser(Long id) throws DAOException {
		BlogUser blogUser = JPAEMProvider.getEntityManager().find(BlogUser.class, id);
		return blogUser;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BlogUser> getAuthors() throws DAOException {
		Query query = JPAEMProvider.getEntityManager().createQuery("from BlogUser");
		List<BlogUser> users = (List<BlogUser>) query.getResultList();
		return users;
	}

	@Override
	public BlogUser getBlogUserNick(String nick) throws DAOException {
		try {
			BlogUser blogUser = (BlogUser) JPAEMProvider.getEntityManager()
					.createQuery("from BlogUser u where u.nick = :nick").setParameter("nick", nick).getSingleResult();
			return blogUser;
		} catch (NoResultException ex) {
			return null;
		}

	}

	@Override
	public void addBlogUser(BlogUser user) throws DAOException {
		EntityManager em = JPAEMProvider.getEntityManager();
		em.persist(user);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BlogEntry> getEntriesNick(String nick) throws DAOException {
		try {
			BlogUser user = getBlogUserNick(nick);
			if (user == null) {
				return null;
			}
			List<BlogEntry> entries = (List<BlogEntry>) JPAEMProvider.getEntityManager()
					.createQuery("from BlogEntry e where e.author = :user").setParameter("user", user).getResultList();
			return entries;
		} catch (NoResultException ex) {
			return null;
		}
	}

	@Override
	public void addBlogEntry(BlogEntry entry) throws DAOException {
		EntityManager em = JPAEMProvider.getEntityManager();
		em.persist(entry);
	}

	@Override
	public void addBlogComment(BlogComment comment) throws DAOException {
		EntityManager em = JPAEMProvider.getEntityManager();
		em.persist(comment);

	}

	@Override
	public void updateBlogEntry(BlogEntry entry) throws DAOException {
		Query query = JPAEMProvider.getEntityManager().createQuery(
				"update BlogEntry set title = :title, text =: text, lastmodifiedat = :lastmodifiedat where id = :id");
		query.setParameter("title", entry.getTitle());
		query.setParameter("text", entry.getText());
		query.setParameter("lastmodifiedat", entry.getLastModifiedAt());
		query.setParameter("id", entry.getId());

		query.executeUpdate();
	}

}