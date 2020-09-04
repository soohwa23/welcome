package moneymoney.DTO;

public class UserDTO {

	private String userId;
	private String userPw;
	
	public UserDTO() {

	}
	
	public UserDTO(String userId, String userPw) {
		super();
		this.userId = userId;
		this.userPw = userPw;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserPw() {
		return userPw;
	}

	public void setUserPw(String userPw) {
		this.userPw = userPw;
	}
	
} // end userDTO
