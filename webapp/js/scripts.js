String.prototype.format = function() {
  var args = arguments;
  return this.replace(/{(\d+)}/g, function(match, number) {
    return typeof args[number] != 'undefined' ? args[number] : match;
  });
};

$(function() {
	$(".answerWrite input[type=submit]").click(addAnswer); // 버튼에 이벤트 핸들로 등록
	$("div.qna-comment").on("click", "form.form-delete", deleteAnswer); //event delegation을 이용해서 selecter 추가.
});

var addAnswer = function(e) {
	e.preventDefault(); // submit이 자동으로 동작하는 것을 막아준다. 
	
	var url = "/api/qna/addAnswer"; //맵핑할 url주소를 할당해주고.
	var queryString = $("form[name=answer]").serialize(); //form의 데이타들을 자동으로 묶어준다.
	/*	<form name="answer" action="/api/qna/answer" method="post">
	 * 제이쿼리는 폼에서 데이터를 수집하는 .serialize()메서드를 제공한다.
	 * 이 메서드는 폼의 정보를 선택하고, 서버로 전송할 수 있도록 문자열에 저장한다.
	 * 또한 쿼리 문자열에서 사용할 수 없는 문자들을 인코딩한다.
	 * */
	$.ajax(url, { //요청을 보낼 페이지의 경로 
		"data": queryString,
		"type": "post",
		"dataType": "json",
	}).done(function(data, status) { //서버가 데이타를 return한 경우,
		console.log(data);
		addAnswerDom(data); //addAnswerDom으로 데이타를 보낸다.
	}).fail(function($xhr, status) {
		console.log(status);
		alert("submit error!");
	});
};

var addAnswerDom = function(data) {
	var answerTemplate = $("#answerTemplate").html(); //answerTemplate에 추가되는 형식의 답변 html을 넣어준다.
	var template = answerTemplate.format(data.writer, new Date(data.createdDate), data.contents, data.answerId, data.answerId);
	
	$("div.qna-comment-slipp-articles").prepend(template); //추가되는 데이타들을 리스트에 append해준다.	
	$("#writer").val(""); //writer칸 초기
	$("#contents").val(""); //contents칸 초기
}

var deleteAnswer = function(e) {
	e.preventDefault();
	
	var url = $(this).prop("action");
	var queryString = $(this).serialize();
	
	$.ajax(url, {
		"data": queryString,
		"type": "post",
		"dataTypd": "json",
	}).done(function(data, status) {
		if(data.status) {
			deleteAnswerDom(e);
		} else {
			console.log(data.message);
		}
	}).fail(function($xhr, status) {
		alert("delete error!");
	});
};

var deleteAnswerDom = function(e) {
	$(e.target).closest("article.article").remove();
};

