package moneymoney.DTO;

public class CategoriesDTO {

	private int categoryId;
	private String categoryName;
	private char inOrOut;
	
	public CategoriesDTO() {

	}

	public CategoriesDTO(int categoryID, String categoryName, char inOrOut) {
		super();
		this.categoryId = categoryID;
		this.categoryName = categoryName;
		this.inOrOut = inOrOut;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public char getInOrOut() {
		return inOrOut;
	}
	public void setInOrOut(char inOrOut) {
		this.inOrOut = inOrOut;
	}
	
} // end CategoriesDTO
