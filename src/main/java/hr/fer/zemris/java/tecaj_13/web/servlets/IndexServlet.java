package hr.fer.zemris.java.tecaj_13.web.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Class that represents a servlet which is responsible for redirecting the main
 * blog page to the desired one.
 * 
 * @author Dinz
 *
 */
@WebServlet("/index.jsp")
public class IndexServlet extends HttpServlet {

	/**
	 * Serial.
	 */
	private static final long serialVersionUID = -9087457223658852582L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.sendRedirect(req.getContextPath() + "/servleti/main");
	}
}
