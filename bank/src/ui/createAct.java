package ui;

import vo.AccountVO;

public class createAct extends BankBaseUI {

	@Override
	public void execute() { // 계좌 생성, 계좌 번호를 임의로 만드는 것 필요함, 한 달에 한 번만 생성할 수 있음
		
		// 입력받아야 하는 값은 계좌 명칭, balance 입력받은 뒤 set으로 객체값 셋팅
		
		System.out.println("---------------------------------------------");
		System.out.println("\t하나은행 계좌 생성 서비스");
		System.out.println("---------------------------------------------");
		
		try {
			
//			한 달 제약조건 추가, 회원 테이블에서 최근 계좌 생성일자를 가져온다
//			member 테이블의 최근 계좌 생성일자 가져와서 비교
//			로그인한 아이디에 해당하는 recent_account_name을 가져오기, [개별계좌조회서비스, 개별계좌조회]에서 recent_account_name가져와야함 AccountVO에 recent_account_name 추가하기
//			가져와서 List<Account> account =service.개별계좌조회서비스
//			account.getRecent_date  <== 이게 해당 회원의 최근 계좌 생성 일자
			
			AccountVO account = new AccountVO();
			String actName =scanStr("생성하고 싶은 계좌의 명칭을 입력하세요 : ");
			int balance = scanInt("새로운 계좌에 얼마를 입금하시겠습니까? : ");
			
			if(balance >= 1000) {
				
				account.setActName(actName);
				account.setBalance(balance);
				
				service.계좌생성서비스(account);
				System.out.println("계좌가 생성되었습니다. ");
				
			}else {
				System.out.println("계좌 생성 시 1000원 이상의 금액을 입금하셔야 합니다.");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

}
