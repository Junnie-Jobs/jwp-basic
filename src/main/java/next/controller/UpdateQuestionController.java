package next.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.mvc.Controller;

import java.sql.Timestamp;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.mvc.Controller;
import next.dao.QuestionDao;
import next.model.Question;

public class UpdateQuestionController implements Controller {

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		String writer = req.getParameter("writer");
		String title = req.getParameter("title");
		String contents = req.getParameter("contents");

		Calendar calender = Calendar.getInstance();
		Timestamp  tsTimestamp = new Timestamp(calender.getTime().getTime());
		Question question = new Question(1,writer,title,contents,tsTimestamp,0);
		
		QuestionDao questionDao = new QuestionDao();		
		questionDao.insert(question);
		
		return "redirect:/";	
	}

}