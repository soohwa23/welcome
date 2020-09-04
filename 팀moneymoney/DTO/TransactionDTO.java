package moneymoney.DTO;

public class TransactionDTO {

	private int transactionNo;
	private String accountNo;
	private char inOrOut;
	private String useDate;
	private int categoryId;
	private int useAmount;
	private String useContents;
	
	public TransactionDTO() {

	}

	public TransactionDTO(int transactionNo, String accountNo, char inOrOut, String useDate, int categoryId, int useAmount,
			String useContents) {
		super();
		this.transactionNo = transactionNo;
		this.accountNo = accountNo;
		this.inOrOut = inOrOut;
		this.useDate = useDate;
		this.categoryId = categoryId;
		this.useAmount = useAmount;
		this.useContents = useContents;
	}

	public int getTransactionNo() {
		return transactionNo;
	}

	public void setTransactionNo(int transactionNo) {
		this.transactionNo = transactionNo;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public char getInOrOut() {
		return inOrOut;
	}

	public void setInOrOut(char inOrOut) {
		this.inOrOut = inOrOut;
	}

	public String getUseDate() {
		return useDate;
	}

	public void setUseDate(String useDate) {
		this.useDate = useDate;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public int getUseAmount() {
		return useAmount;
	}

	public void setUseAmount(int useAmount) {
		this.useAmount = useAmount;
	}

	public String getUseContents() {
		return useContents;
	}

	public void setUseContents(String useContents) {
		this.useContents = useContents;
	}
	
} // end TransactionDTO
