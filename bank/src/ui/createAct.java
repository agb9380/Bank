package ui;

import java.util.List;

import vo.AccountVO;
import vo.MemberVO;

public class createAct extends BankBaseUI {

	@Override
	public void execute() { // 계좌 생성, 계좌 번호를 임의로 만드는 것 필요함, 한 달에 한 번만 생성할 수 있음

		// 입력받아야 하는 값은 계좌 명칭, balance 입력받은 뒤 set으로 객체값 셋팅

		System.out.println("---------------------------------------------");
		System.out.println("\t하나은행 계좌 생성 서비스");
		System.out.println("---------------------------------------------");

		try {

			List<MemberVO> memberList = service.최근계좌생성일자서비스();
			int recent_account_date = Integer.parseInt(memberList.get(0).getRecent_account_date());
			
			String actName = scanStr("생성하고 싶은 계좌의 명칭을 입력하세요 : ");
			int balance = scanInt("새로운 계좌에 얼마를 입금하시겠습니까? : ");
			
			if(recent_account_date >= 30 || recent_account_date == -1 ) { //현재날짜에서 해당 회원의 최근 계좌 생성일자가 30일이상 차이나면 계좌 생성 가능
																		//null값을 -1로 변경 후, 계좌 등록을 한 번도 하지 않은 고객이 계좌를 등록할 수 있도록 함
				if (balance >= 1000) {
					AccountVO account = new AccountVO();
					account.setActName(actName);
					account.setBalance(balance);
					
					service.계좌생성서비스(account);
					System.out.println("계좌가 생성되었습니다.");
					
				} else {
					System.out.println("계좌 생성 시 1000원 이상의 금액을 입금하셔야 합니다.");
				}
				
				
			}else{
				System.out.println("계좌 생성 실패");
				System.out.println("최근 계좌 개설일로부터 30일이 경과해야 합니다.");
			}
			

		} catch (Exception e) {
			e.printStackTrace();

		}
	}
}
