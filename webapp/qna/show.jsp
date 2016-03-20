<%@ page language="java" contentType="text/html;charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="kr">
<head>
    <%@ include file="/include/header.jspf" %>
</head>
<body>
	<%@ include file="/include/navigation.jspf" %>

	<div class="container" id="main">
	    <div class="col-md-12 col-sm-12 col-lg-12">
	        <div class="panel panel-default">
	          <header class="qna-header">
	              <h2 class="qna-title">${question.title }</h2>
	          </header>
	          <div class="content-main">
	              <article class="article">
	                  <div class="article-header">
	                      <div class="article-header-thumb">
	                          <img src="https://graph.facebook.com/v2.3/100000059371774/picture" class="article-author-thumb" alt="">
	                      </div>
	                      <div class="article-header-text">
	                          <a href="/users/92/kimmunsu" class="article-author-name">${question.writer }</a>
	                          <a href="/questions/413" class="article-header-time" title="퍼머링크">
	                              ${question.createdDate }
	                              <i class="icon-link"></i>
	                          </a>
	                      </div>
	                  </div>
	                  <div class="article-doc">
	                      <p>${question.contents }</p>
	                  </div>
	                  <div class="article-util">
	                      <ul class="article-util-list">
	                          <li>
	                              <a class="link-modify-article" href="/questions/423/form">수정</a>
	                          </li>
	                          <li>
	                              <form class="form-delete" action="/questions/423" method="POST">
	                                  <input type="hidden" name="_method" value="DELETE">
	                                  <button class="link-delete-article" type="submit">삭제</button>
	                              </form>
	                          </li>
	                          <li>
	                              <a class="link-modify-article" href="/index.html">목록</a>
	                          </li>
	                      </ul>
	                  </div>
	              </article>
	
	              <div class="qna-comment">
	                  <div class="qna-comment-slipp">
	                      <p class="qna-comment-count"><strong>${countOfAnswers }</strong>개의 의견</p>
	                      <div class="qna-comment-slipp-articles">
	                      	<c:forEach items="${answers }" var="answer" varStatus="status">
	                      	  <article class="article" id="${answer.answerId }">
	                              <div class="article-header">
	                                  <div class="article-header-thumb">
	                                      <img src="https://graph.facebook.com/v2.3/1324855987/picture" class="article-author-thumb" alt="">
	                                  </div>
	                                  <div class="article-header-text">
	                                      <a href="/users/1/자바지기" class="article-author-name">${answer.writer }</a>
	                                      <a href="#answer-1434" class="article-header-time" title="퍼머링크">
	                                          ${answer.createdDate }
	                                      </a>
	                                  </div>
	                              </div>
	                              <div class="article-doc comment-doc">
	                                  <p>${answer.contents }</p>
	                              </div>
	                              <div class="article-util">
	                                  <ul class="article-util-list">
	                                      <li>
	                                          <a class="link-modify-article" href="/questions/413/answers/1405/form">수정</a>
	                                      </li>
	                                      <li>
	                                          <form class="form-delete" action="/questions/413/answers/1405" method="POST">
	                                              <input type="hidden" name="_method" value="DELETE">
	                                              <button type="submit" class="link-delete-article">삭제</button>
	                                          </form>
	                                      </li>
	                                  </ul>
	                              </div>
	                          </article>
	                      	</c:forEach>
	                         
	                         <form class="submit-write">
	                              <div class="form-group" style="padding:14px;">
	                                  <textarea class="form-control" placeholder="Update your status"></textarea>
	                              </div>
	                              <button class="btn btn-success pull-right" type="button">Post</button>
	                              <div class="clearfix" />
	                          </form>
	                      </div>
	                  </div>
	              </div>
	          </div>
	        </div>
	    </div>
	</div>

	<%@ include file="/include/footer.jspf" %>
</body>

</html>