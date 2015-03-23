package webServer;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.User;
import model.UserDAO;

@WebServlet("/formServlet")
public class FormServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/form.jsp").forward(req,  resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		try {
			UserDAO.addUser(new User(req.getParameter("userId"), req.getParameter("password"),
					req.getParameter("name"), req.getParameter("email")));
		} catch (SQLException e) {
			
		}
		resp.sendRedirect("/");
	}
}
