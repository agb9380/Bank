package ui;

import java.util.List;

import vo.AccountVO;
import vo.TransactionVO;

public class transferUI extends BankBaseUI {

	@Override
	public void execute() {

		AccountVO account = new AccountVO();

		System.out.println("---------------------------------------------");
		System.out.println("\t하나은행 계좌 이체 서비스");
		System.out.println("---------------------------------------------");

		try {
			String depositBankName = null; // 입금할 은행명
			String depositActNo = null; // 입금할 은행 계좌번호
			TransactionVO deal = new TransactionVO();

			String withdrawBankName = scanStr("출금할 은행명을 입력하세요. :");
			String withdrawActNo = scanStr("출금할 계좌번호를 입력하세요. :");

			List<AccountVO> withdrawCheck = service.회원별전체계좌조회서비스(); // 출금할 계좌 번호가 존재하는지 확인하는 리스트 (전체계좌조회서비스 이용 => 로그인한
																		// 아이디가 가지고 있는 계좌 정보 조회)

			for (int i = 0; i < withdrawCheck.size(); i++) {
				if (withdrawActNo.equals(withdrawCheck.get(i).getActNum())) { // 입력한 계좌가 존재하는지
					// 전체 계좌 조회에서 존재하는 계좌번호인지 확인
					depositBankName = scanStr("입금할 은행을 입력하세요. :");
					depositActNo = scanStr("입금할 계좌번호를 입력하세요. :");
				}

			}

			List<AccountVO> depositCheck = service.전체계좌목록서비스(); // 전체 계좌 담는 리스트
			
			// 출금 계좌를 잘못입력한 경우, 입금 계좌를 입력하지 않기 때문에 depositActNo = null => nullpointerException => 예외 발생, catch에서 출력
			for (int j = 0; j < depositCheck.size(); j++) {
				if (depositActNo.equals(depositCheck.get(j).getActNum())) {
					int money = scanInt("얼마를 이체하시겠습니까? : ");
					account.setActNum(withdrawActNo);
					List<AccountVO> accountList = service.개별계좌조회서비스(account); // accountList에 출금할 계좌의 정보 저장

					// 이체할 금액이 0이 아니거나, 출금할 계좌의 잔액보다 작은 경우에만 이체 가능
					if (money != 0 && money <= accountList.get(0).getBalance()) {
						deal.setWithdrawBankName(withdrawBankName);
						deal.setWithdrawActNo(withdrawActNo);
						deal.setDepositBankName(depositBankName);
						deal.setDepositActNo(depositActNo);
						deal.setMoney(money);

						service.계좌이체서비스(deal);
						System.out.println("이체가 완료되었습니다.");
						break;
					} else {
						System.out.println("계좌 이체 실패");
						System.out.println("금액 부족");
					}

				} else if (depositCheck.size()-1 == j) { //for문 0~14(등록된 계좌 수) 전체 돌 때까지 depositActNo(입금 계좌)와 동일한 계좌를 발견하지 못함 = depositCheck.size()-1
					System.out.println("입금할 계좌의 정보가 존재하지 않습니다.");
				}
			}
		} catch (Exception e) {
			System.out.println("출금 계좌의 정보를 잘못입력하셨습니다.");
		}

	}
}
