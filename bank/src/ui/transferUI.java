package ui;

import java.util.List;

import vo.AccountVO;
import vo.TransactionVO;

public class transferUI extends BankBaseUI {
	
	@Override
	public void execute() {
		
		AccountVO account = new AccountVO();
		
		System.out.println("---------------------------------------------");
		System.out.println("하나은행 계좌 이체 서비스");
		System.out.println("---------------------------------------------");
		
	try {
		
		TransactionVO deal = new TransactionVO(); // 
		String withdrawBankName = scanStr("출금할 은행명을 입력하세요. :");
		String withdrawActNo = scanStr("출금할 계좌번호를 입력하세요. :");
		String depositBankName = scanStr("입금할 은행을 입력하세요 :");
		String depositActNo = scanStr("입금할 계좌번호를 입력하세요. :");
		int money = scanInt("얼마를 이체하시겠습니까? : ");
		
		account.setActNum(withdrawActNo); // 해당 계좌의 잔액을 확인하기 위해

		List<AccountVO> accountList =service.개별계좌조회서비스(account); // accountList에 출금할 계좌의 정보 저장
		
		// 이체할 금액이 0이 아니거나, 출금할 계좌의 잔액보다 작은 경우에만 이체 가능
		if (money !=0 && money <=accountList.get(0).getBalance()) {
			deal.setWithdrawBankName(withdrawBankName);
			deal.setWithdrawActNo(withdrawActNo);
			deal.setDepositBankName(depositBankName);
			deal.setDepositActNo(depositActNo);
			deal.setMoney(money);
			
			service.계좌이체서비스(deal);
			System.out.println("이체가 완료되었습니다.");
		}else {
			System.out.println("계좌 이체 실패");
			System.out.println("금액 부족");
		}

		
		
		
		
		
	} catch (Exception e) {
		e.printStackTrace();
	}	
		
	}

}
