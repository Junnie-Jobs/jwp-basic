package next.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import next.model.User;
import core.jdbc.JdbcTemplate;
import core.jdbc.RowMapper;

public class UserDao {
	
    JdbcTemplate jdbcTemplate = JdbcTemplate.getInstance();
    
    public void insert(User user) {

        String sql = "INSERT INTO USERS VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, user.getUserId(),
                user.getPassword(),
                user.getName(),
                user.getEmail());
    }

    public User findByUserId(String userId) {

        String sql = "SELECT userId, password, name, email FROM USERS WHERE userid=?";       
        return jdbcTemplate.queryForObject(sql, new UserRowMapper(), userId);
    }

    public List<User> findAll() throws SQLException {

        String sql = "SELECT userId, password, name, email FROM USERS";              
        return jdbcTemplate.query(sql, new UserRowMapper());
    }

    public void update(User user) {

        String sql = "UPDATE USERS set password = ?, name = ?, email = ? WHERE userId = ?";
        jdbcTemplate.update(sql, user.getPassword(),
                user.getName(),
                user.getEmail(),
                user.getUserId());
    }
    
//inner class로 만들어 중복 제거하기!    
    private class UserRowMapper implements RowMapper<User>{
    	
		@Override
		public User mapRow(ResultSet rs) throws SQLException {

			return new User(rs.getString("userId"), 
                    rs.getString("password"), 
                    rs.getString("name"),
                    rs.getString("email"));
		}
		}
}

