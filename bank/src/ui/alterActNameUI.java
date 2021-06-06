package ui;

import java.util.List;

import vo.AccountVO;

public class alterActNameUI extends BankBaseUI {

	@Override
	public void execute() {

		String updateActNo;
		String updateActName;
		
		try {
			AccountVO account = new AccountVO();
			
			updateActNo = scanStr("명칭을 변경하고 싶은 계좌의 계좌번호를 입력해주세요 : "); //어떤 계좌로 전달?
			updateActName = scanStr("새로운 계좌 명칭 : ");
			account.setActNum(updateActNo); // 입력한 계좌번호를 객체에 저장
			account.setActName(updateActName);
			
			List<AccountVO> accountList = service.개별계좌조회서비스(account); // 해당 계좌가 존재하는지 확인
			
			if (accountList.size() == 1) {
				System.out.println("입력하신 계좌의 명칭을 변경했습니다.");		
				service.계좌명칭변경서비스(account);			
			}else {
				System.out.println("입력하신 계좌가 존재하지 않습니다.");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
