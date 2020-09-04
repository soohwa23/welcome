package moneymoney;

public class PageInfo {

	private int pageNum;
	private int alarm;
	
	private PageInfo() {
	}

	private static PageInfo pageInfo = new PageInfo();

	public static PageInfo getInstance() {
		return pageInfo;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getAlarm() {
		return alarm;
	}

	public void setAlarm(int alarm) {
		this.alarm = alarm;
	}
	

} // end class
