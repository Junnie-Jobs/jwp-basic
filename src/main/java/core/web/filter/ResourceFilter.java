package core.web.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* ****** 요구사항6 ******
자바 기반으로 웹 프로그래밍을 할 경우 한글이 깨진다. 한글이 깨지는 문제를 해결하기 위해 ServletFilter를 활요해 문제를 해결할 수 있다. 
core.web.filter.CharacterEncodingFilter에 어 노테이션 설정을 통해 한글 문제를 해결한다.(힌트 : WebFilter annotation)
 ****** 요구사항6 ****** */

@WebFilter("/*")
public class ResourceFilter implements Filter {
	private static final Logger logger = LoggerFactory.getLogger(ResourceFilter.class);
	private static final List<String> resourcePrefixs = new ArrayList<>();
	static{
		resourcePrefixs.add("/css");
		resourcePrefixs.add("/js");
		resourcePrefixs.add("/fonts");
		resourcePrefixs.add("/images");
		resourcePrefixs.add("/favicon.ico");
	}
	
	private RequestDispatcher defaultRequestDispatcher;
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.defaultRequestDispatcher =   
	            filterConfig.getServletContext().getNamedDispatcher("default");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		String path = req.getRequestURI().substring(req.getContextPath().length());
		if (isResourceUrl(path)) {
			logger.debug("path : {}", path);
			defaultRequestDispatcher.forward(request, response);
		} else {
			chain.doFilter(request, response);
		}
	}
	
	private boolean isResourceUrl(String url) {
		for (String prefix : resourcePrefixs) {
			if (url.startsWith(prefix)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void destroy() {
	}

}
