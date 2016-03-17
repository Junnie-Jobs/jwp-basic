package next.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import next.model.User;

@WebServlet("/users/login")
public class LoginServlet extends HttpServlet {

	static final String SESSION_USER_ID = "userId";

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String userId = req.getParameter(SESSION_USER_ID);
		String password = req.getParameter("password");
		HttpSession session = req.getSession();

		try {
			User.login(userId, password);
			session.setAttribute(SESSION_USER_ID, userId);

			resp.sendRedirect("/");

		} catch (UserNotFoundException e) {

			req.setAttribute("errorMessage", "존재하지 않는 사용자입니다. 다시 로그인 하세요");
			RequestDispatcher rd = req.getRequestDispatcher("/login.html");
			rd.forward(req, resp);

		} catch (PasswordMismatchException e) {
			req.setAttribute("errorMessage", "비밀번호가 틀립니다.. 다시 로그인 하세요");
			RequestDispatcher rd = req.getRequestDispatcher("/login.html");
			rd.forward(req, resp);
		}
	}

}
