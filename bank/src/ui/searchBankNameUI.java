package ui;

import java.util.List;

import vo.AccountVO;

public class searchBankNameUI extends BankBaseUI {

	
	
	@Override
	public void execute() {
		
		
		try {
			
			AccountVO account = new AccountVO();
			String updateBankName= scanStr("조회하고 싶은 계좌의 은행명을 입력하세요.");
			account.setActName(updateBankName); // 조회하고 싶은 은행명 입력
			
			
			List<AccountVO> accountList = service.은행별계좌조회서비스(account);

			System.out.println("-----------------------------------------");
			System.out.println("\t" + BankUI.getSession()+"님의 " + account.getActName() +"은행 계좌 정보");
			System.out.println("-----------------------------------------");
			System.out.println("계좌번호\t\t은행명\t잔액\t계좌명칭");
			
			for(AccountVO vo : accountList) {
				System.out.println(vo.getActNum()+"\t"+vo.getBankName() +"\t"+vo.getBalance() + "\t" + vo.getActName());
			}
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		
	}

}
