package entity;

public class YearMonth {
	private String year;
	private String month;
	public YearMonth(String year, String month) {
		super();
		this.year = year;
		this.month = month;
	}
	public YearMonth() {}

	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}

}
