package hr.fer.zemris.java.tecaj_13.web.servlets;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hr.fer.zemris.java.tecaj_13.dao.DAOProvider;
import hr.fer.zemris.java.tecaj_13.model.BlogUser;

/**
 * Class which represents a registration servlet. This servlet is responsible
 * for the action of the user registration on the web application. When
 * registrated, the user information is stored in the database.
 * 
 * @author Dinz
 *
 */
@WebServlet("/servleti/register")
public class RegistrationServlet extends HttpServlet {

	/**
	 * Serial.
	 */
	private static final long serialVersionUID = -2553458378415075720L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String regParam = req.getParameter("register");
		if (regParam == null) {
			req.getRequestDispatcher("/WEB-INF/pages/registration.jsp").forward(req, resp);
		} else {
			if (!regParam.equals("true")) {
				req.getRequestDispatcher("/WEB-INF/pages/error.jsp").forward(req, resp);
			}

			try {
				BlogUser user = new BlogUser();
				user.setNick(req.getParameter("nick"));
				user.setEmail(req.getParameter("email"));
				user.setFirstName(req.getParameter("name"));
				user.setLastName(req.getParameter("surname"));
				user.setPasswordHash(calcHash(req.getParameter("password")));

				BlogUser info = DAOProvider.getDAO().getBlogUserNick(user.getNick());
				if (info != null) {
					req.setAttribute("userExists", "User with this nickname already exists.");
					req.getRequestDispatcher("/WEB-INF/pages/registration.jsp").forward(req, resp);
					return;
				}

				DAOProvider.getDAO().addBlogUser(user);

				req.getSession().setAttribute("current.user.nick", user.getNick());
				req.getSession().setAttribute("current.user.id", user.getId());
				req.getSession().setAttribute("current.user.fn", user.getFirstName());
				req.getSession().setAttribute("current.user.ln", user.getLastName());

				resp.sendRedirect(req.getContextPath() + "/servleti/main");

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
