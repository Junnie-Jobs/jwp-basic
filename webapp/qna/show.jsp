<%@ page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="kr">
<head>
<%@ include file="/include/header.jspf"%>
</head>
<body>
	<%@ include file="/include/navigation.jspf"%>

	<div class="container" id="main">
		<div class="col-md-12 col-sm-12 col-lg-10 col-lg-offset-1">
			<div class="panel panel-default">
				<header class="qna-header">
					<h2 class="qna-title">${question.title}</h2>
				</header>
				<div class="content-main">
					<article class="article">
						<div class="article-header">
							<div class="article-header-thumb">
								<img
									src="https://graph.facebook.com/v2.3/100000059371774/picture"
									class="article-author-thumb" alt="">
							</div>
							<div class="article-header-text">
								<a href="/users/92/kimmunsu" class="article-author-name">${question.writer}</a>
								<a href="/questions/413" class="article-header-time"
									title="퍼머링크"> <fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss"
										value="${question.createdDate}" /> <i class="icon-link"></i>
								</a>
							</div>
						</div>
						
						<!-- 요구사항 10
						질문 수정이 가능해야 한다. 질문 수정은 글쓴이와 로그인 사용자가 같은 경우에만 수정이 가 능하다. -->
						
						<div class="article-doc">${question.contents}</div>
						<div class="article-util">
							<ul class="article-util-list">
								<li><a class="link-modify-article" href="/qna/updatePost?questionId=${question.questionId}">수정</a></li>
								<li>
									<form class="form-delete" action="/qna/delete" method="POST">
										<input type="hidden" name="_method" value="DELETE">
										<button class="link-delete-article" type="submit">삭제</button>
									</form>
								</li>
								<li><a class="link-modify-article" href="/">목록</a></li>
							</ul>
						</div>
					</article>
					
				<!--    ****** 요구사항5 ******
				로그인하지 않은 사용자도 질문하기가 가능하다. 로그인한 사용자만 질문이 가능하도록 수정한다. 
				또한 질문할 때 글쓴이를 입력하지 않고 로그인한 사용자 정보를 가져와 글쓴이 이름으로 등록한다.
				(힌트 : session.getAttribute(“user”)와 같이 Session에서 로그인 정보를 가져올 수 있다.)
				 ****** 요구사항5 ****** -->
							 
					<div class="qna-comment">
						<div class="qna-comment-slipp">
							<p class="qna-comment-count">
								<strong>${question.countOfComment}</strong>개의 의견
							</p>
							<div class="qna-comment-slipp-articles">
								<c:forEach items="${answers}" var="answer">
									<article class="article">
										<div class="article-header">
											<div class="article-header-thumb">
												<img
													src="https://graph.facebook.com/v2.3/1324855987/picture"
													class="article-author-thumb" alt="">
											</div>
											<div class="article-header-text">
												${answer.writer}
												<div class="article-header-time">
													<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss"
														value="${answer.createdDate}" />
												</div>
											</div>
										</div>
										<div class="article-doc comment-doc">
											<p>${answer.contents}</p>
										</div>
										<div class="article-util">
											<ul class="article-util-list">
												<li><a class="link-modify-article"
													href="/api/qna/reviseAnswer?answerId=${answer.answerId}">수정</a>
												</li>
												<li>
													<form class="form-delete" action="/api/qna/deleteAnswer"
														method="POST">
														<input type="hidden" name="answerId"
															value="${answer.answerId}" />
														<button type="submit" class="link-delete-article">삭제</button>
													</form>
												</li>
											</ul>
										</div>
									</article>
								</c:forEach>
								<div class="answerWrite">
									<form name="answer" method="post" action="/api/qna/addAnswer">
										<input type="hidden" name="questionId"
											value="${question.questionId}">
										<div class="form-group col-lg-12">
											<textarea name="contents" id="contents" class="form-control"
												placeholder=""></textarea>
										</div>
										<input class="btn btn-success pull-right" type="submit"
											value="답변하기" />
										<div class="clearfix" />
									</form>
								</div>
							</div>
						</div>
					</div>

				</div>
			</div>
		</div>
	</div>

	<script type="text/template" id="answerTemplate">
	<article class="article">
		<div class="article-header">
			<div class="article-header-thumb">
				<img src="https://graph.facebook.com/v2.3/1324855987/picture" class="article-author-thumb" alt="">
			</div>
			<div class="article-header-text">
				{0}
				<div class="article-header-time">{1}</div>
			</div>
		</div>
		<div class="article-doc comment-doc">
			{2}
		</div>
		<div class="article-util">
		<ul class="article-util-list">
			<li>
				<a class="link-modify-article" href="/api/qna/updateAnswer/{3}">수정</a>
			</li>
			<li>
				<form class="form-delete" method="POST">
					<input type="hidden" name="answerId" value="{4}" />
					<button type="submit" class="link-delete-article">삭제</button>
				</form>
			</li>
		</ul>
		</div>
	</article>
</script>
	<%@ include file="/include/footer.jspf"%>
</body>
</html>