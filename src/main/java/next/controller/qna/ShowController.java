package next.controller.qna;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import next.dao.AnswerDao;
import next.dao.QuestionDao;
import next.model.Answer;
import next.model.Question;
import core.mvc.AbstractController;
import core.mvc.ModelAndView;

/* ****** 요구사항7 ******
next.web.qna package의 ShowController는 멀티 쓰레드 상황에서 문제가 발생할 가능성이 있는 코드이다.
멀티 쓰레드 상황에서 문제가 발생하지 않도록 수정한다. 멀티 쓰레드에서 문제가 되는 이유를 README.md 파일에 작성한다.
 ****** 요구사항7 ****** */

public class ShowController extends AbstractController {
	private QuestionDao questionDao = QuestionDao.getInstance();
	private AnswerDao answerDao = AnswerDao.getInstance();
	private Question question;
	private List<Answer> answers;
	
	@Override
	public ModelAndView execute(HttpServletRequest req, HttpServletResponse response) throws Exception {
		Long questionId = Long.parseLong(req.getParameter("questionId"));
		
		question = questionDao.findById(questionId);
		answers = answerDao.findAllByQuestionId(questionId);
		
		ModelAndView mav = jspView("/qna/show.jsp");
		mav.addObject("question", question);
		mav.addObject("answers", answers);
		return mav;
	}
}
