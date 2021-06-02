package ui;

import java.util.List;
import vo.AccountVO;



public class SearchAllUI extends BankBaseUI {

	@Override
	public void execute() {
		try {
			List<AccountVO> list=service.전체계좌조회서비스();
			
			System.out.println("-----------------------------------------");
			System.out.println("\t" + BankUI.getSession()+"님의 계좌 정보");
			System.out.println("-----------------------------------------");
			System.out.println("계좌번호\t\t은행명\t잔액\t계좌명칭");

			for(AccountVO vo : list) {			
				System.out.println(vo.getActNum()+"\t"+vo.getBankName() +"\t"+vo.getBalance() + "\t" + vo.getActName());
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}


		
	
		
	
}
