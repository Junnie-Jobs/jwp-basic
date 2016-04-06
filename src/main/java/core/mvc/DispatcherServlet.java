package core.mvc;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/*
 * init메서드는 언제 실행이 될까?
톰캣서버가 단계를 나눌 수 있는데,
loadOnStratup이 없으면 서블릿컨테이너가 시작될 때 호출되지 않는다.
시작이 된 다음에 사용자의 요청이 최초로 발생하는 시점에 시작된다.
 * */

@WebServlet(name = "dispatcher", urlPatterns = {"", "/"}, loadOnStartup = 1)
public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(DispatcherServlet.class);

	private RequestMapping rm;

	@Override
	public void init() throws ServletException {
		rm = new RequestMapping();
		rm.initMapping();
	}

	//사용자의 요청이 들어오면 서비스로 모든 요청이 들어오기 시작한다.
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String requestUri = req.getRequestURI();
		logger.debug("Method : {}, Request URI : {}", req.getMethod(), requestUri);

		Controller controller = rm.findController(req.getRequestURI());
		ModelAndView mav;
		try {
			mav = controller.execute(req, resp);
			View view = mav.getView();
			view.render(mav.getModel(), req, resp);
			/*인터페이스를 구현하는 것, 추상화 다형*/
		} catch (Throwable e) {
			logger.error("Exception : {}", e);
			throw new ServletException(e.getMessage());
		}
	}
}
