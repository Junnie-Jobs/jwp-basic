package next.controller.qna;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import core.mvc.Controller;
import next.dao.AnswerDao;
import next.model.Result;

public class DeleteAnswerController implements Controller {

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		AnswerDao answerDao = new AnswerDao();
		long answerId = Long.parseLong(req.getParameter("answerId"));
		
		answerDao.delete(answerId);
		
		Result result = (answerDao.findById(answerId) == null) ? Result.ok() : Result.fail("fail to delete data");

		ObjectMapper objectMapper = new ObjectMapper();
		resp.setContentType("application/json; charset=UTF-8");
		PrintWriter out = resp.getWriter();
		out.print(objectMapper.writeValueAsString(result));
		
		return null;
	}

}
