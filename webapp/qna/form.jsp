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
			<div class="panel panel-default content-main">
<form name="question" method="post" action="/qna/create">
	<!--    ****** 요구사항4 ******
			로그인하지 않은 사용자도 질문하기가 가능하다. 로그인한 사용자만 질문이 가능하도록 수정한다. 
			또한 질문할 때 글쓴이를 입력하지 않고 로그인한 사용자 정보를 가져와 글쓴이 이름으로 등록한다.
			(힌트 : session.getAttribute(“user”)와 같이 Session에서 로그인 정보를 가져올 수 있다.)
			 ****** 요구사항4 ****** -->
	<!--              
<div class="form-group">
              <label for="writer">글쓴이</label>
              <input class="form-control" id="writer" name="writer" placeholder="글쓴이"/>
          </div> -->
	<div class="form-group">
		<label for="title">제목</label> <input type="text"
			class="form-control" id="title" name="title" placeholder="제목" />
	</div>
	<div class="form-group">
		<label for="contents">내용</label>
		<textarea name="contents" id="contents" rows="5"
			class="form-control"></textarea>
	</div>
	<button type="submit" class="btn btn-success clearfix pull-right">질문하기</button>
	<div class="clearfix" />
</form>
			</div>
		</div>
	</div>

	<%@ include file="/include/footer.jspf"%>
</body>
</html>