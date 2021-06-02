package vo;

public class TransactionVO {
	private String withdrawBankName; //출금할 은행명
	private String withdrawActNo; //출금할 계좌
	private int money; // 출금하고, 입금하는 금액
	private String depositBankName; //입금할 은행명
	private String depositActNo; //입금할 계좌
	
	public TransactionVO() {	
	}

	public TransactionVO(String withdrawBankName, String withdrawActNo, int money, String depositBankName,
			String depositActNo) {
		super();
		this.withdrawBankName = withdrawBankName;
		this.withdrawActNo = withdrawActNo;
		this.money = money;
		this.depositBankName = depositBankName;
		this.depositActNo = depositActNo;
	}

	public String getWithdrawBankName() {
		return withdrawBankName;
	}

	public void setWithdrawBankName(String withdrawBankName) {
		this.withdrawBankName = withdrawBankName;
	}

	public String getWithdrawActNo() {
		return withdrawActNo;
	}

	public void setWithdrawActNo(String withdrawActNo) {
		this.withdrawActNo = withdrawActNo;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public String getDepositBankName() {
		return depositBankName;
	}

	public void setDepositBankName(String depositBankName) {
		this.depositBankName = depositBankName;
	}

	public String getDepositActNo() {
		return depositActNo;
	}

	public void setDepositActNo(String depositActNo) {
		this.depositActNo = depositActNo;
	}
	
	
	
	
	
	
}
