package ui;

import java.util.List;

import vo.AccountVO;

public class searchOneUI extends BankBaseUI {

	@Override
	public void execute() {
		
		AccountVO account = new AccountVO();
		String actNum = scanStr("조회하고 싶은 계좌의 계좌번호를 입력하세요.");
		account.setActNum(actNum);
		
		try {	
			List<AccountVO> accountList = service.개별계좌조회서비스(account);
			
			System.out.println("-----------------------------------------");
			System.out.println("\t" + BankUI.getSession()+"님의 계좌 정보");
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
