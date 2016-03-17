package next.model;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import next.dao.UserDao;

@WebServlet("/users/upadateForm")
public class UpdateFormUserServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		Object object = session.getAttribute(LoginServlet.SESSION_USER_ID);
		
		if(object == null){
			resp.sendRedirect("/");
			return;
		}
		
		String userId = (String)object;
		System.out.println("User Id : " + userId);
		UserDao userDao = new UserDao();
		try {
			User user = userDao.findByUserId(userId);
			req.setAttribute("user", user);
			RequestDispatcher rd = req.getRequestDispatcher("/update.jsp");
			rd.forward(req,resp);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
	}

}
