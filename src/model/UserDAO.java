package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

	public static Connection getConnection() throws SQLException {
		String url = "jdbc:mysql://localhost:3306/slipp";
		String id = "root";
		String pw = "1234";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			return DriverManager.getConnection(url, id, pw);
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	public static void addUser(User user) throws SQLException {
		String sql = "INSERT INTO users VALUES(?,?,?,?)";

		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getmId());
			pstmt.setString(2, user.getmPassword());
			pstmt.setString(3, user.getmName());
			pstmt.setString(4, user.getmEmail());
			
			pstmt.executeUpdate();
			
		} finally {
			closeDAO(null, pstmt, conn);
		}
	}

	public static User findUser(String userId) throws SQLException {
		String sql = "SELECT * FROM users WHERE userId = ?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			rs = pstmt.executeQuery();
			
			if (!rs.next()) {
				return null;
			}
			
			return new User(
					rs.getString("userId"),
					rs.getString("password"),
					rs.getString("name"),
					rs.getString("email"));
		} finally {
			closeDAO(rs, pstmt, conn);
		}
	}
	
	public static void editUser(User user) throws SQLException {
		String sql = "UPDATE users SET password = ?, name = ?, email = ? WHERE userId = ?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getmPassword());
			pstmt.setString(2, user.getmName());
			pstmt.setString(3, user.getmEmail());
			pstmt.setString(4, user.getmId());
			
			pstmt.executeUpdate();
			
		} finally {
			closeDAO(null, pstmt, conn);
		}
	}

	private static void closeDAO(ResultSet rs, PreparedStatement pstmt, Connection conn) throws SQLException {
		if (rs != null) {
			rs.close();
		}
		if (pstmt != null) {
			pstmt.close();
		}
		if (conn != null) {				
			conn.close();				
		}
	}
}
