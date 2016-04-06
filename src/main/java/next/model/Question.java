package next.model;

import java.util.Date;

public class Question {
	private long questionId;

	private String writer;

	private String title;

	private String contents;

	private Date createdDate;

	private int countOfComment;

	public Question(String writer, String title, String contents) {
		this(0, writer, title, contents, new Date(), 0);
	}

	public Question(long questionId, String writer, String title, String contents, Date createdDate,
			int countOfComment) {
		this.questionId = questionId;
		this.writer = writer;
		this.title = title;
		this.contents = contents;
		this.createdDate = createdDate;
		this.countOfComment = countOfComment;
	}

	public long getQuestionId() {
		return questionId;
	}

	public String getWriter() {
		return writer;
	}

	public String getTitle() {
		return title;
	}

	public String getContents() {
		return contents;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public long getTimeFromCreateDate() {
		return this.createdDate.getTime();
	}

	public int getCountOfComment() {
		return countOfComment;
	}

	@Override
	public String toString() {
		return "Question [questionId=" + questionId + ", writer=" + writer + ", title=" + title + ", contents="
				+ contents + ", createdDate=" + createdDate + ", countOfComment=" + countOfComment + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (questionId ^ (questionId >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Question other = (Question) obj;
		if (questionId != other.questionId)
			return false;
		return true;
	}

	public boolean isCheckUser(User userFromSession) {

		return userFromSession.isCheckUser(this.writer);
	}

	/*
	 * ****** 요구사항10 ****** 
	 * 질문 수정이 가능해야 한다. 
	 * 질문 수정은 글쓴이와 로그인 사용자가 같은 경우에만 수정이 가능하다.
	 * ******  요구사항10 ******
	 */

	public void update(Question newQuestion) {
		this.title = newQuestion.title;
		this.contents = newQuestion.contents;
	}

}
