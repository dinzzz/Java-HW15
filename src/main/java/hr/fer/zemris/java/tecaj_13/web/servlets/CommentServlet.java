package hr.fer.zemris.java.tecaj_13.web.servlets;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hr.fer.zemris.java.tecaj_13.dao.DAOProvider;
import hr.fer.zemris.java.tecaj_13.model.BlogComment;
import hr.fer.zemris.java.tecaj_13.model.BlogEntry;

/**
 * Class that represents a comment servlet, servlet which is responsible for the
 * action of commentating on the blog entries.
 * 
 * @author Dinz
 *
 */
@WebServlet("/servleti/comment")
public class CommentServlet extends HttpServlet {

	/**
	 * Serial.
	 */
	private static final long serialVersionUID = 4876023584290667865L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String text = req.getParameter("com");
		String email = req.getParameter("email");
		String id = req.getParameter("id");
		String nick = req.getParameter("nick");

		BlogEntry entry = DAOProvider.getDAO().getBlogEntry(Long.parseLong(id));
		if (entry == null) {
			req.getRequestDispatcher("/WEB-INF/pages/error.jsp").forward(req, resp);
		}

		BlogComment comment = new BlogComment();
		comment.setMessage(text);
		comment.setUsersEMail(email);
		comment.setBlogEntry(entry);
		comment.setPostedOn(new Date());

		DAOProvider.getDAO().addBlogComment(comment);
		resp.sendRedirect(req.getContextPath() + "/servleti/author/" + nick + "/" + id);

	}
}
