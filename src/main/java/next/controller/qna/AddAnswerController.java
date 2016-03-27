package next.controller.qna;

import java.io.PrintWriter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

import core.mvc.Controller;
import next.dao.AnswerDao;
import next.model.Answer;

public class AddAnswerController extends HttpServlet implements Controller {

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {

		Answer answer = new Answer(req.getParameter("writer"), req.getParameter("contents"),
				Long.parseLong(req.getParameter("questionId")));

		AnswerDao answerDao = new AnswerDao();

		Answer savedAnswer = answerDao.insert(answer);

		ObjectMapper objectMapper = new ObjectMapper();
		resp.setContentType("application/json; charset=UTF-8");
		PrintWriter out = resp.getWriter();
		out.print(objectMapper.writeValueAsString(savedAnswer));

		 return null;
//		 String json = objectMapper.writeValueAsString(answer);
//		 return json;
	}

}
