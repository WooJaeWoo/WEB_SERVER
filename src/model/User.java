package model;

import java.sql.SQLException;

import exception.PasswordMismatchException;
import exception.UserNotFoundException;

public class User {

	private String mId;
	private String mPassword;
	private String mName;

	private String mEmail;
	
	public String getmId() {
		return mId;
	}
	
	public String getmPassword() {
		return mPassword;
	}
	
	public String getmName() {
		return mName;
	}
	
	public String getmEmail() {
		return mEmail;
	}

	public void setmPassword(String mPassword) {
		this.mPassword = mPassword;
	}

	public void setmName(String mName) {
		this.mName = mName;
	}
	
	public void setmEmail(String mEmail) {
		this.mEmail = mEmail;
	}
	
	public User(String mId, String mPassword, String mName, String mEmail) {
		super();
		this.mId = mId;
		this.mPassword = mPassword;
		this.mName = mName;
		this.mEmail = mEmail;
	}
	
	public boolean matchPassword(String password) {
		return this.mPassword.equals(password);
	}
	
	public static boolean login(String userId, String password) throws UserNotFoundException, PasswordMismatchException {
		User user = null;
		try {
			user = UserDAO.findUser(userId);
		} catch (SQLException e) {
		}
		
		if (user == null) {
			throw new UserNotFoundException("존재하지 않는 사용자입니다!");
		}
		
		if (!user.matchPassword(password)) {
			throw new PasswordMismatchException("비밀번호가 틀렸습니다.");
		}
		
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((mEmail == null) ? 0 : mEmail.hashCode());
		result = prime * result + ((mId == null) ? 0 : mId.hashCode());
		result = prime * result + ((mName == null) ? 0 : mName.hashCode());
		result = prime * result
				+ ((mPassword == null) ? 0 : mPassword.hashCode());
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
		User other = (User) obj;
		if (mEmail == null) {
			if (other.mEmail != null)
				return false;
		} else if (!mEmail.equals(other.mEmail))
			return false;
		if (mId == null) {
			if (other.mId != null)
				return false;
		} else if (!mId.equals(other.mId))
			return false;
		if (mName == null) {
			if (other.mName != null)
				return false;
		} else if (!mName.equals(other.mName))
			return false;
		if (mPassword == null) {
			if (other.mPassword != null)
				return false;
		} else if (!mPassword.equals(other.mPassword))
			return false;
		return true;
	}
}
