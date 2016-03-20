package next.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

import core.jdbc.JdbcTemplate;
import core.jdbc.RowMapper;
import next.model.Answer;

public class AnswerDao {
	
	public void insert(Answer answer) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		String sql = "INSERT INTO ANSWERS(writer,contents,createdDate,questionId) VALUES (?,?,?,?)";
		jdbcTemplate.update(sql,
				answer.getWriter(),
				answer.getContents(),
				answer.getCreatedDate(),
				answer.getQuestionId());
	}
	
	public Answer findByAnswerId(long answerId) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		String sql = "SELECT answerId, writer, contents, createdDate, questionId FROM ANSWERS WHERE answerId=?";
		
		RowMapper<Answer> rm = new RowMapper<Answer>() {
            @Override
            public Answer mapRow(ResultSet rs) throws SQLException {
                return new Answer(rs.getLong("answerId"),
                		rs.getString("writer"),
                		rs.getString("contents"),
                		rs.getTimestamp("createdDate"),
                		rs.getLong("questionId")
                	); 
            }
        };
        return jdbcTemplate.queryForObject(sql, rm, answerId);	
	}
	
	public List<Answer> findAll() {
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		String sql = "SELECT answerId, writer, contents, createdDate, questionId FROM ANSWERS";
		RowMapper<Answer> rm = new RowMapper<Answer>() {
            @Override
            public Answer mapRow(ResultSet rs) throws SQLException {
                return new Answer(rs.getLong("answerId"),
                		rs.getString("writer"),
                		rs.getString("contents"),
                		rs.getTimestamp("createdDate"),
                		rs.getLong("questionId")
                	); 
            }
        };
        return jdbcTemplate.query(sql, rm);
	}
	
	public List<Answer> findAnswersByQuestionId(long questionId) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		String sql = "SELECT answerId, writer, contents, createdDate, questionId FROM ANSWERS where questionId=?";
		RowMapper<Answer> rm = new RowMapper<Answer>() {
            @Override
            public Answer mapRow(ResultSet rs) throws SQLException {
                return new Answer(rs.getLong("answerId"),
                		rs.getString("writer"),
                		rs.getString("contents"),
                		rs.getTimestamp("createdDate"),
                		rs.getLong("questionId")
                	); 
            }
        };
        return jdbcTemplate.query(sql, rm, questionId);
	}
	
	public void update(Answer answer) {
		Calendar calender = Calendar.getInstance();
		Timestamp  tsTimestamp = new Timestamp(calender.getTime().getTime());
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		String sql = "UPDATE ANSWERS set writer = ?, contents = ?, createdDate = ?, questionId = ? WHERE answerId=?";
		jdbcTemplate.update(sql, answer.getWriter(),
				answer.getContents(),
				tsTimestamp,
				answer.getQuestionId(),
				answer.getAnswerId());
	}


}
