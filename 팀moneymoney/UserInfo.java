package moneymoney;

public class UserInfo {
	
	private String userId;
	private String pw;
	private String[] accountNo;
	
	private UserInfo() {
	}
	private static UserInfo userInfo = new UserInfo();
	
	public static UserInfo getInstance() {
		return userInfo;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String[] getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String[] accountNo) {
		this.accountNo = accountNo;
	}
	
} // end class
