package vo;

public class DealVO {
	
	private String actNum;
	private String bankName;
	private int money;
	private String type;
	private String dealDate;
	
	public DealVO(String actNum, String bankName, int money, String type, String dealDate) {
		super();
		this.actNum = actNum;
		this.bankName = bankName;
		this.money = money;
		this.dealDate = dealDate;
		this.type = type;
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

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public String getDealDate() {
		return dealDate;
	}

	public void setDealDate(String dealDate) {
		this.dealDate = dealDate;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	
	
}
