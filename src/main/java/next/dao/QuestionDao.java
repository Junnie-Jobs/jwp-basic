package next.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import next.model.Question;
import core.jdbc.JdbcTemplate;
import core.jdbc.KeyHolder;
import core.jdbc.PreparedStatementCreator;
import core.jdbc.RowMapper;


public class QuestionDao {
	/* ****** 요구사항11 ******
	 Controller에서 접근하는 QuestionDao와 AnswerDao, DAO에서 데이터베이스 접근 로직을 구현할 때 사용하는 JdbcTemplate은 
	 인스턴스를 여러 개 생성할 필요없다. 인스턴스를 하나 만 생성하도록 구현한다.(힌트 싱글톤 패턴)
	
	  ****** 요구사항11 ****** */

	private static QuestionDao questionDao = new QuestionDao();
	private JdbcTemplate jdbcTemplate = JdbcTemplate.getInstance();

	private QuestionDao() {
	}

	public static QuestionDao getInstance() {

		if (questionDao == null)

			return questionDao = new QuestionDao();

		return questionDao;

	}

/* ****** 요구사항3 ******
  질문 목록은 정상적으로 동작하지만 질문하기 기능은 정상적으로 동작하지 않는다. 질문하기 기능을 구현한다.
  질문 추가 로직은 QuestionDao 클래스의 insert method 활용 가능하다. 
  HttpServletRequest에서 값을 추출할 때는 ServletRequestUtils 클래스를 활용 가능하다. 질 문하기를 성공한 후 질문 목록 페이지(“/”)로 이동해야 한다.
   ****** 요구사항3 ****** */
	
	public Question insert(Question question) {
		String sql = "INSERT INTO QUESTIONS (writer, title, contents, createdDate) VALUES (?, ?, ?, ?)";
		PreparedStatementCreator psc = new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement pstmt = con.prepareStatement(sql);
				pstmt.setString(1, question.getWriter());
				pstmt.setString(2, question.getTitle());
				pstmt.setString(3, question.getContents());
				pstmt.setTimestamp(4, new Timestamp(question.getTimeFromCreateDate()));
				return pstmt;
			}
		};
		KeyHolder keyHolder = new KeyHolder();
		jdbcTemplate.update(psc, keyHolder);
		return findById(keyHolder.getId());
	}

	public List<Question> findAll() {

		String sql = "SELECT questionId, writer, title, createdDate, countOfAnswer FROM QUESTIONS "
				+ "order by questionId desc";

		RowMapper<Question> rm = new RowMapper<Question>() {
			@Override
			public Question mapRow(ResultSet rs) throws SQLException {
				return new Question(rs.getLong("questionId"), rs.getString("writer"), rs.getString("title"), null,
						rs.getTimestamp("createdDate"), rs.getInt("countOfAnswer"));
			}

		};

		return jdbcTemplate.query(sql, rm);
	}

	public Question findById(long questionId) {
		String sql = "SELECT questionId, writer, title, contents, createdDate, countOfAnswer FROM QUESTIONS "
				+ "WHERE questionId = ?";

		RowMapper<Question> rm = new RowMapper<Question>() {
			@Override
			public Question mapRow(ResultSet rs) throws SQLException {
				return new Question(rs.getLong("questionId"), rs.getString("writer"), rs.getString("title"),
						rs.getString("contents"), rs.getTimestamp("createdDate"), rs.getInt("countOfAnswer"));
			}
		};

		return jdbcTemplate.queryForObject(sql, rm, questionId);
	}

	public void addCountOfAnswer(long questionId) {


        String sql = "UPDATE QUESTIONS set countOfAnswer = countOfAnswer + 1 WHERE questionId = ?";
        jdbcTemplate.update(sql, questionId);


    }
	
	/*
	 * ****** 요구사항10 ****** 
	 * 질문 수정이 가능해야 한다. 
	 * 질문 수정은 글쓴이와 로그인 사용자가 같은 경우에만 수정이 가능하다.
	 * ******  요구사항10 ******
	 */


	public void update(Question question) {
		
		String sql = "UPDATE QUESTIONS set title = ?, contents = ? WHERE questionId = ?";
        jdbcTemplate.update(sql, 
        		question.getTitle(),
                question.getContents(),
                question.getQuestionId());
	}

}
