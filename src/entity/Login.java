package entity;

public class Login {
	private String userId = null;
	private String password = null;
	private int loginId = 0;
	public Login(String userId, String password) {
		super();
		this.userId = userId;
		this.password = password;
	}
	public Login() {
		// TODO 自動生成されたコンストラクター・スタブ
	}

	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getLoginId() {
		return loginId;
	}
	public void setLoginId(int loginId) {
		this.loginId = loginId;
	}
}