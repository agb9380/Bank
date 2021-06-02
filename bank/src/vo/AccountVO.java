package vo;

public class AccountVO {
	String actNum;
	String bankName;
	String memberId;
	int balance;
	String actName;
	
	public AccountVO() {
		
	}

	public AccountVO(String actNum, String bankName, String memberId, int balance, String actName) {
		super();
		this.actNum = actNum;
		this.bankName = bankName;
		this.memberId = memberId;
		this.balance = balance;
		this.actName = actName;
	}

	public String getActNum() {
		return actNum;
	}

	public void setActNum(String actNum) {
		this.actNum = actNum;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public String getActName() {
		return actName;
	}

	public void setActName(String actName) {
		this.actName = actName;
	}
}