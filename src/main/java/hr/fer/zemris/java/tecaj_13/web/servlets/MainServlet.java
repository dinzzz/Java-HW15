package hr.fer.zemris.java.tecaj_13.web.servlets;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hr.fer.zemris.java.tecaj_13.dao.DAOProvider;
import hr.fer.zemris.java.tecaj_13.model.BlogUser;

/**
 * This class represents a servlet which is responsible for the main page of the
 * web app which supports the login action and presents the list of all
 * available authors.
 * 
 * @author Dinz
 *
 */
@WebServlet("/servleti/main")
public class MainServlet extends HttpServlet {

	/**
	 * Serial
	 */
	private static final long serialVersionUID = 2883257679449396573L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		mainPage(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		mainPage(req, resp);
	}

	/**
	 * Method which renders the important data about the user login and authors list
	 * on the main page.
	 * 
	 * @param req
	 *            Request.
	 * @param resp
	 *            Response.
	 * @throws ServletException
	 * @throws IOException
	 */
	private void mainPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String loginParam = req.getParameter("login");
		String logoutParam = req.getParameter("logout");
		List<BlogUser> authors = DAOProvider.getDAO().getAuthors();
		req.setAttribute("authors", authors);

		if (logoutParam != null) {
			if (logoutParam.equals("true")) {
				req.getSession().invalidate();
			} else {
				req.getRequestDispatcher("/WEB-INF/pages/error.jsp").forward(req, resp);
			}
		}

		if (loginParam == null) {
			req.getRequestDispatcher("/WEB-INF/pages/main.jsp").forward(req, resp);
		} else {
			if (!loginParam.equals("true")) {
				req.getRequestDispatcher("/WEB-INF/pages/error.jsp").forward(req, resp);
			}

			String nick = req.getParameter("nick");
			String password = req.getParameter("password");

			try {
				String hash = calcHash(password);
				BlogUser user = DAOProvider.getDAO().getBlogUserNick(nick);

				if (user == null) {
					req.setAttribute("noUser", "Invalid nickname.");
					req.getRequestDispatcher("/WEB-INF/pages/main.jsp").forward(req, resp);
					return;
				}
				req.getSession().setAttribute("current.user.nick", user.getNick());
				if (hash.equals(user.getPasswordHash())) {
					req.getSession().setAttribute("current.user.id", user.getId());
					req.getSession().setAttribute("current.user.fn", user.getFirstName());
					req.getSession().setAttribute("current.user.ln", user.getLastName());

				} else {
					req.setAttribute("wrongpw", "Invalid password.");
				}
				req.getRequestDispatcher("/WEB-INF/pages/main.jsp").forward(req, resp);

			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * Method which calculates a SHA-256 representation of the input password.
	 * 
	 * @param password
	 *            Password.
	 * @return SHA representation of the password.
	 * @throws NoSuchAlgorithmException
	 */
	private String calcHash(String password) throws NoSuchAlgorithmException {

		MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
		byte[] input = password.getBytes();
		messageDigest.update(input);
		byte[] digested = messageDigest.digest();

		StringBuffer stringBuffer = new StringBuffer();
		for (byte bytes : digested) {
			stringBuffer.append(String.format("%02x", bytes & 0xff));
		}

		return stringBuffer.toString();
	}

}
