package ui;

import java.util.List;

import vo.DealVO;

public class transHistoryUI extends BankBaseUI {

	@Override
	public void execute() {
		
		System.out.println("---------------------------------------------");
		System.out.println("\t하나은행 거래내역 조회 서비스");
		System.out.println("---------------------------------------------");
		
		try {
			List<DealVO> dealList=service.거래내역조회서비스();
			System.out.println("---------------------------------------------");
			System.out.println("\t" + BankUI.getSession() +"님의 통합 거래내역");
			System.out.println("---------------------------------------------");
			System.out.println("계좌번호\t\t은행명\t유형\t금액\t거래날짜");
			
			for(DealVO vo : dealList) {
				System.out.println(vo.getActNum() + "\t" +vo.getBankName()+ "\t" + vo.getType() + "\t" + vo.getMoney() +"\t"+ vo.getDealDate());
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	
}
