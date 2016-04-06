
$(".answerWrite input[type=submit]").click(addAnswer);

function addAnswer(e) {
  e.preventDefault();

  var queryString = $("form[name=answer]").serialize();

  $.ajax({
    type : 'post',
    url : '/api/qna/addAnswer',
    data : queryString,
    dataType : 'json',
    error: onError,
    success : onSuccess,
  });
}

function onSuccess(json, status){
  var answer = json.answer;
  var answerTemplate = $("#answerTemplate").html();
  var template = answerTemplate.format(answer.writer, new Date(answer.createdDate), answer.contents, answer.answerId, answer.answerId);
  $(".qna-comment-slipp-articles").prepend(template);
}

function onError(xhr, status) {
  alert("error");
}

String.prototype.format = function() {
  var args = arguments;
  return this.replace(/{(\d+)}/g, function(match, number) {
    return typeof args[number] != 'undefined'
        ? args[number]
        : match
        ;
  });
};

//   ****** 요구사항9 ******
//로그인하지 않은 사용자도 질문하기가 가능하다. 로그인한 사용자만 질문이 가능하도록 수정한다. 
//또한 질문할 때 글쓴이를 입력하지 않고 로그인한 사용자 정보를 가져와 글쓴이 이름으로 등록한다.
//(힌트 : session.getAttribute(“user”)와 같이 Session에서 로그인 정보를 가져올 수 있다.)
// ****** 요구사항9 ****** 

function deleteAnswer(e) {
    e.preventDefault();
    var queryString = $(this).closest("form").serialize();

    $.ajax({
      type: 'post',
      url: "/api/qna/deleteAnswer",
      data: queryString,
      dataType: 'json',
      error: onError,
      success: function (json, status) {
        if (json.status) {
          $(this).closest('article').remove();
        }
      }
    });