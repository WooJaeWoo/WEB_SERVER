package webServer;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.User;
import model.UserDAO;

@WebServlet("/updateServlet")
public class UpdateServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		try {
			UserDAO.editUser(new User((String) session.getAttribute("userId"),
					req.getParameter("password"), req.getParameter("name"), req.getParameter("email")));
		} catch (SQLException e) {
			
		}
		
		resp.sendRedirect("/");
	}
}
