package model;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;

public class UserDAOTest {

	private User TEST_USER = new User("smith", "1234", "SMITHOO", "test@gail.com");

	@Before
	public void setUp() {
	}
	
	@Test
	public void connection() throws SQLException {
		Connection con = UserDAO.getConnection();
		assertNotNull(con);
	}
	
	@Test
	public void addUser() throws Exception {
		UserDAO.addUser(TEST_USER);
	}
	
	@Test
	public void findUser() throws Exception {
		User user = UserDAO.findUser("smith");
		assertEquals(TEST_USER, user);
	}
}

