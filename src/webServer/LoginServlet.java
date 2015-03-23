package webServer;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import exception.PasswordMismatchException;
import exception.UserNotFoundException;
import model.User;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/login.jsp").forward(req,  resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String userId = req.getParameter("userId");
		String password = req.getParameter("password");

		HttpSession session = req.getSession();
		try {
			User.login(userId, password);
			session.setAttribute("userId", userId);
			session.setAttribute("isUpdate", true);
			resp.sendRedirect("/");
			
		} catch (UserNotFoundException | PasswordMismatchException e) {
			forward(req, resp, e.getMessage());
		}
	}

	private void forward(HttpServletRequest req, HttpServletResponse resp,
			String errorMessage) throws ServletException, IOException {
		req.setAttribute("errorMessage", errorMessage);
		RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/login.jsp");
		rd.forward(req, resp);
	}
}
