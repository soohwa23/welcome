package moneymoney.DTO;

public class AccountDTO {

	private String accountNo;
	private String userId;
	private int accountBal;
	private String bankName;
	
	public AccountDTO() {

	}
	
	public AccountDTO(String accountNo, String userId, int accountBal, String bankName) {
		super();
		this.accountNo = accountNo;
		this.userId = userId;
		this.accountBal = accountBal;
		this.bankName = bankName;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getAccountBal() {
		return accountBal;
	}

	public void setAccountBal(int accountBal) {
		this.accountBal = accountBal;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	
} // end AccountDTO
