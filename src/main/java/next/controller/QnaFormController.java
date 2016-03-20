package next.controller;



import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import core.mvc.Controller;


public class QnaFormController implements Controller{

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
	
		if (!UserSessionUtils.isLogined(req.getSession())) {
			return "redirect:/users/loginForm";
		}
		
		return "redirect:/qna/form.jsp";		
	}


}
