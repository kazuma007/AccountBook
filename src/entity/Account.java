package entity;
public class Account {
	private int id;
	private String date;
	private String category;
	private String content;
	private int amount;
	private int loginId;
	private String registeredDate;
	public Account(String date, String category, String content, int amount, int loginId) {
		super();
		this.date = date;
		this.category = category;
		this.content = content;
		this.amount = amount;
		this.loginId = loginId;
	}
	public Account(int id, String date, String category, String content, int amount) {
		super();
		this.id = id;
		this.date = date;
		this.category = category;
		this.content = content;
		this.amount = amount;
	}
	public Account() {
		// TODO 自動生成されたコンストラクター・スタブ
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public int getLoginId() {
		return loginId;
	}
	public String getRegisteredDate() {
		return registeredDate;
	}
	public void setRegisteredDate(String registeredDate) {
		this.registeredDate = registeredDate;
	}
}