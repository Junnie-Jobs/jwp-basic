package next.controller.qna;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import core.mvc.AbstractController;
import core.mvc.ModelAndView;
import next.controller.UserSessionUtils;
import next.dao.AnswerDao;
import next.dao.QuestionDao;
import next.model.Answer;
import next.model.Result;
import next.model.User;


/* ****** 요구사항8 ******
상세보기 화면에서 답변하기 기능은 정상적으로 동작한다. 
단, 답변을 추가할 경우 댓글의 수가 증가하지 않는다.
* 답변을 추가하는 시점에 질문(QUESTIONS 테이블)의 댓글 수 (countOfAnswer)도 1 증가해야 한다.
*  데이터베이스 접근 로직은 직접 구현해야 한다.
 ****** 요구사항8 ****** */


public class AddAnswerController extends AbstractController {

    private static final Logger log = LoggerFactory.getLogger(AddAnswerController.class);
    private AnswerDao answerDao = AnswerDao.getInstance();
    private QuestionDao questionDao = QuestionDao.getInstance();

    @Override
    public ModelAndView execute(HttpServletRequest req, HttpServletResponse response) throws Exception {

        if (!UserSessionUtils.isLogined(req.getSession())) {
            return jsonView().addObject("result", Result.fail("You need to login For write Answer"));
        }

        User user = UserSessionUtils.getUserFromSession(req.getSession());
        Answer answer = new Answer(
					        		user.getUserId(), req.getParameter("contents"),
					                Long.parseLong(req.getParameter("questionId")));

        Answer enrolledAnswer = answerDao.insert(answer);
        questionDao.addCountOfAnswer(enrolledAnswer.getQuestionId());

        return jsonView().addObject("answer", enrolledAnswer);

    }
}