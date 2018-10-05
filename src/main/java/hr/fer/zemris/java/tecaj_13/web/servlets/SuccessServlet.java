package hr.fer.zemris.java.tecaj_13.web.servlets;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hr.fer.zemris.java.tecaj_13.dao.DAOProvider;
import hr.fer.zemris.java.tecaj_13.model.BlogEntry;
import hr.fer.zemris.java.tecaj_13.model.BlogUser;

/**
 * Class which represents a servlet which is responsible for the finalizing
 * actions of adding new entries and editing existing ones based on the mode
 * parameter passed on by the request.
 * 
 * @author Dinz
 *
 */
@WebServlet("/servleti/success")
public class SuccessServlet extends HttpServlet {

	/**
	 * Serial.
	 */
	private static final long serialVersionUID = -8785968106230336431L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		BlogEntry ent = action(req, resp);
		String mode = req.getParameter("mode");

		if (mode.equals("new")) {
			DAOProvider.getDAO().addBlogEntry(ent);
			req.getRequestDispatcher("/WEB-INF/pages/success.jsp").forward(req, resp);

		} else if (mode.equals("edit")) {
			String id = req.getParameter("id");

			ent.setId(Long.parseLong(id));
			DAOProvider.getDAO().updateBlogEntry(ent);
			req.getRequestDispatcher("/WEB-INF/pages/success.jsp").forward(req, resp);
		} else {
			req.getRequestDispatcher("/WEB-INF/pages/error.jsp").forward(req, resp);
		}
	}

	/**
	 * Method which prepares a certain blog entry instance after it has been created
	 * or edited.
	 * 
	 * @param req
	 *            Request.
	 * @param resp
	 *            Response.
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	private BlogEntry action(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String entry = req.getParameter("entry");
		String title = req.getParameter("title");
		String nick = req.getParameter("nick");

		if (!checkUser(nick, req, resp)) {
			req.getRequestDispatcher("/WEB-INF/pages/error.jsp").forward(req, resp);
		}

		BlogUser user = DAOProvider.getDAO().getBlogUserNick(nick);

		BlogEntry blogEntry = new BlogEntry();
		blogEntry.setCreatedAt(new Date());
		blogEntry.setAuthor(user);
		blogEntry.setText(entry);
		blogEntry.setTitle(title);
		blogEntry.setLastModifiedAt(new Date());

		return blogEntry;
	}

	/**
	 * Method which checks the user authorization for the addition and modification
	 * of the blog entries.
	 * 
	 * @param nick
	 *            Nickname of the user.
	 * @param req
	 *            Request.
	 * @param resp
	 *            Response.
	 * @return
	 */
	private boolean checkUser(String nick, HttpServletRequest req, HttpServletResponse resp) {
		if (nick == null) {
			return false;
		}

		BlogUser user = DAOProvider.getDAO().getBlogUserNick(nick);
		if (user == null) {
			return false;
		}

		if (req.getSession().getAttribute("current.user.nick") != null) {
			if (req.getSession().getAttribute("current.user.nick").equals(nick)) {
				return true;
			}
		}

		return false;
	}
}
