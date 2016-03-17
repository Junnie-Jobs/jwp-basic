package next.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import core.jdbc.ConnectionManager;
import next.model.User;

public class UserDao {
	public void insert(User user) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ConnectionManager.getConnection();
			String sql = "INSERT INTO USERS VALUES (?, ?, ?, ?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user.getUserId());
			pstmt.setString(2, user.getPassword());
			pstmt.setString(3, user.getName());
			pstmt.setString(4, user.getEmail());

			pstmt.executeUpdate();
		} finally {
			if (pstmt != null) {
				pstmt.close();
			}

			if (con != null) {
				con.close();
			}
		}
	}
	
	public List<User> findAll() throws SQLException {
		
		//요구사항1
		//SELECT userId, password, name, email FROM USERS -전체 목록 조회 쿼리
		//목록 조회 while(rs.next()) {}

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<User> userlist = new ArrayList<User>();
		try {
			con = ConnectionManager.getConnection();
			String sql = "SELECT userId, password, name, email FROM users";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()){
				String userId = rs.getString("userId");
				String password = rs.getString("password");
				String name = rs.getString("name");
				String email = rs.getString("email");
				
				User user = new User(userId,password,name,email);
				userlist.add(user);
			}
			
		} finally {
			if (pstmt != null) {
				pstmt.close();
			}
			
			if (con != null) {
				con.close();
			}
		}
		
		return userlist;
		
	}
	
	
	
	public void update(User user) throws SQLException {
		//요구사항2.
		//로그인 사용자는 자신의 개인 정보를 수정할 수 있어야 한다.
		//개인정보수정 쿼리 UPDATE USERS set password = ?, name = ?, email = ? WHERE userId = ?
		
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ConnectionManager.getConnection();
			String sql = "UPDATE USERS set password = ?, name = ?, email = ? WHERE userId = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user.getPassword());
			pstmt.setString(2, user.getName());
			pstmt.setString(3, user.getEmail());
			pstmt.setString(4, user.getUserId());
			
			pstmt.executeUpdate();
		} finally {
			if (pstmt != null) {
				pstmt.close();
			}
			
			if (con != null) {
				con.close();
			}
		}
		
		return ;
	    }
	
	


	public User findByUserId(String userId) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ConnectionManager.getConnection();
			String sql = "SELECT userId, password, name, email FROM USERS WHERE userid=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, userId);

			rs = pstmt.executeQuery();

			User user = null;
			if (rs.next()) {
				user = new User(
						rs.getString("userId"), 
						rs.getString("password"), 
						rs.getString("name"),
						rs.getString("email"));
			}

			return user;
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (con != null) {
				con.close();
			}
		}
	}
}
