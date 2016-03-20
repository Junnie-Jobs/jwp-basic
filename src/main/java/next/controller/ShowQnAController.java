package next.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.mvc.Controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.mvc.Controller;
import next.dao.AnswerDao;
import next.dao.QuestionDao;
import next.model.Answer;
import next.model.Question;

public class ShowQnAController implements Controller {

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
				
		String questionId = req.getParameter("questionId");
		System.out.println(questionId);
		
		QuestionDao questionDao = new QuestionDao();
		Question question = questionDao.fingByQuestionId(Long.parseLong(questionId));
		req.setAttribute("question", question);
		
		AnswerDao answerDao = new AnswerDao();
		List<Answer> answers = new ArrayList<Answer>();
		answers = answerDao.findAnswersByQuestionId(Long.parseLong(questionId));
		req.setAttribute("answers",answers);
		
		int countOfAnswers = answers.size();
		req.setAttribute("countOfAnswers", String.valueOf(countOfAnswers));
		
		return "/qna/show.jsp";
	}

}