package hr.fer.zemris.java.tecaj_13.web.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hr.fer.zemris.java.tecaj_13.dao.DAOProvider;
import hr.fer.zemris.java.tecaj_13.model.BlogComment;
import hr.fer.zemris.java.tecaj_13.model.BlogEntry;
import hr.fer.zemris.java.tecaj_13.model.BlogUser;

/**
 * Class that represents an author servlet. This servlet is resposnible for
 * presenting a blog page of the certain author to the page visitors and also
 * offers support for entry addition and modification. If the request is
 * invalid, the servlet will render an error page.
 * 
 * @author Dinz
 *
 */
@WebServlet("/servleti/author/*")
public class AuthorServlet extends HttpServlet {

	/**
	 * Serial
	 */
	private static final long serialVersionUID = 2493972265448878003L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getPathInfo();
		String split[] = path.split("/");

		if (split.length == 2) {
			String nickParam = split[1];
			showBlog(nickParam, req, resp);
		}

		else if (split.length == 3) {
			String nick = split[1];
			String action = split[2];

			if (action.equals("new")) {
				newEntry(nick, req, resp);
			} else if (isInteger(action)) {
				showEntry(nick, action, req, resp);
			}
		} else if (split.length == 4) {
			String nick = split[1];
			String eid = split[2];
			String action = split[3];

			if (isInteger(eid) && action.equals("edit")) {
				editEntry(nick, eid, req, resp);
			} else {
				req.getRequestDispatcher("/WEB-INF/pages/error.jsp").forward(req, resp);
			}
		} else {
			req.getRequestDispatcher("/WEB-INF/pages/error.jsp").forward(req, resp);
		}

	}

	/**
	 * Method which allows the blog user to edit his entry.
	 * 
	 * @param nick
	 *            Nickname of the entry author.
	 * @param eid
	 *            Entry ID.
	 * @param req
	 *            Request.
	 * @param resp
	 *            Response.
	 * @throws ServletException
	 * @throws IOException
	 */
	private void editEntry(String nick, String eid, HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		if (!checkUser(nick, req, resp)) {
			req.getRequestDispatcher("/WEB-INF/pages/error.jsp").forward(req, resp);
		}

		BlogEntry entry = DAOProvider.getDAO().getBlogEntry(Long.parseLong(eid));
		if (entry == null) {
			req.getRequestDispatcher("/WEB-INF/pages/error.jsp").forward(req, resp);
			return;
		}
		
		BlogUser author = entry.getAuthor();
		
		if(!author.getNick().equals(nick)) {
			req.getRequestDispatcher("/WEB-INF/pages/error.jsp").forward(req, resp);
			return;
		}

		String title = entry.getTitle();
		String text = entry.getText();

		req.setAttribute("id", eid);
		req.setAttribute("existing", text);
		req.setAttribute("title", title);
		req.setAttribute("mode", "edit");
		req.setAttribute("nick", nick);
		req.getRequestDispatcher("/WEB-INF/pages/entry.jsp").forward(req, resp);

	}

	/**
	 * Method which allows the presentation of the blog entry to the blog visitor.
	 * 
	 * @param nick
	 *            Nickname of the entry author.
	 * @param id
	 *            Entry ID.
	 * @param req
	 *            Request.
	 * @param resp
	 *            Response.
	 * @throws ServletException
	 * @throws IOException
	 */
	private void showEntry(String nick, String id, HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		BlogEntry entry = DAOProvider.getDAO().getBlogEntry(Long.parseLong(id));
		if (entry == null) {
			req.getRequestDispatcher("/WEB-INF/pages/error.jsp").forward(req, resp);
			return;
		}

		if (!nick.equals(entry.getAuthor().getNick())) {
			req.getRequestDispatcher("/WEB-INF/pages/error.jsp").forward(req, resp);
			return;
		}

		if (req.getSession().getAttribute("current.user.nick") != null) {
			if (req.getSession().getAttribute("current.user.nick").equals(nick)) {
				req.setAttribute("author", true);
			}

			String email = DAOProvider.getDAO()
					.getBlogUserNick(req.getSession().getAttribute("current.user.nick").toString()).getEmail();
			req.setAttribute("email", email);
		}

		String title = entry.getTitle();
		String text = entry.getText();
		List<BlogComment> comments = entry.getComments();
		if (comments == null) {
			comments = new ArrayList<>();
		}

		req.setAttribute("id", id);
		req.setAttribute("nick", nick);
		req.setAttribute("title", title);
		req.setAttribute("text", text);
		req.setAttribute("comments", comments);
		req.getRequestDispatcher("/WEB-INF/pages/show.jsp").forward(req, resp);

	}

	/**
	 * Method which supports an addition of the new blog entry for the blog user.
	 * 
	 * @param nick
	 *            Nickname of the user.
	 * @param req
	 *            Request.
	 * @param resp
	 *            Response.
	 * @throws ServletException
	 * @throws IOException
	 */
	private void newEntry(String nick, HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		if (!checkUser(nick, req, resp)) {
			req.getRequestDispatcher("/WEB-INF/pages/error.jsp").forward(req, resp);
		}

		req.setAttribute("mode", "new");
		req.setAttribute("nick", nick);
		req.getRequestDispatcher("/WEB-INF/pages/entry.jsp").forward(req, resp);

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

	/**
	 * Method which allows the application user to see the blog page of the certain
	 * author.
	 * 
	 * @param nickParam
	 *            Nickname of the author-
	 * @param req
	 *            Request.
	 * @param resp
	 *            Response.
	 * @throws ServletException
	 * @throws IOException
	 */
	private void showBlog(String nickParam, HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		if (nickParam == null) {
			req.getRequestDispatcher("/WEB-INF/pages/error.jsp").forward(req, resp);
			return;
		}

		BlogUser user = DAOProvider.getDAO().getBlogUserNick(nickParam);
		if (user == null) {
			req.getRequestDispatcher("/WEB-INF/pages/error.jsp").forward(req, resp);
			return;
		}

		if (req.getSession().getAttribute("current.user.nick") != null) {
			if (req.getSession().getAttribute("current.user.nick").equals(nickParam)) {
				req.setAttribute("author", true);
			}
		}

		List<BlogEntry> entries = DAOProvider.getDAO().getEntriesNick(nickParam);
		if (entries == null) {
			entries = new ArrayList<>();
		}

		req.setAttribute("nick", user.getNick());
		req.setAttribute("entries", entries);
		req.getRequestDispatcher("/WEB-INF/pages/author.jsp").forward(req, resp);

	}

	/**
	 * Method which checks if the string is parseable to integer instance.
	 * 
	 * @param s
	 *            String to be checked.
	 * @return True if the string is an integer number, false otherwise.
	 */
	private boolean isInteger(String s) {
		try {
			Integer.parseInt(s);
		} catch (NumberFormatException e) {
			return false;
		} catch (NullPointerException e) {
			return false;
		}
		return true;
	}

}
