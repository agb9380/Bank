package ui;

import java.util.List;

import vo.Account;

public class searchBankNameUI extends BankBaseUI {

	public static String inputBankName;
	
	public static String getInputBankName() {
		return inputBankName;
	}
	
	@Override
	public void execute() {
		
		inputBankName = scanStr("조회하고 싶은 계좌의 은행명을 입력하세요.");
		
		try {
			List<Account> accountList = service.은행별계좌조회서비스();

			System.out.println("-----------------------------------------");
			System.out.println("\t" + BankUI.getSession()+"님의 " + searchBankNameUI.getInputBankName() +"은행 계좌 정보");
			System.out.println("-----------------------------------------");
			System.out.println("계좌번호\t\t은행명\t잔액\t계좌명칭");
			
			for(Account vo : accountList) {
				System.out.println(vo.getActNum()+"\t"+vo.getBankName() +"\t"+vo.getBalance() + "\t" + vo.getActName());
			}
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		
	}

}
