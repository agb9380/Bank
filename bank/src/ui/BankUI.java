package ui;

import java.util.List;
import vo.Member;

public class BankUI extends BankBaseUI {

	public static String session; // login()에서 로그인된 아이디가 저장된 변수
	
	public static String getSession() { // session을 사용해서 DB에 접근하기 위함
		return session;
	};
	
	@Override
	public void execute() {
		System.out.println("하나은행에 오신 것을 환영합니다. 로그인해주세요.");
		login(); // 로그인 성공한 뒤 menu 선택
		while (true) {

			IBankUI ui = null;
			int type = menu();

			switch (type) {
			case 1:
				ui = new SearchAllUI();
				break;
			case 2:
				ui = new searchOneUI();
				break;
			case 3:
				ui = new searchBankNameUI();
				break;
			case 4:
				ui = new deleteAcntUI();
				break;
//			case 5:
//				ui = new alterActNameUI();
//				break;
//			case 6:
//				ui = new transferUI();
//				break;
//			case 7:
//				ui = new transHistoryUI();
//				break;
//			case 8 :
//				ui = new createAct();
//			case 0:
//				ui = new exitUI();
//				break;
			}

			if (ui != null) {
				ui.execute();
			}else {
				System.out.println("잘못선택하셨습니다.");
			}
		}

	}

	public int menu() {
		System.out.println("---------------------------------------------");
		System.out.println("\t 통합계좌 관리 프로그램");
		System.out.println("---------------------------------------------");
		System.out.println("\t1. 전체 계좌 조회");
		System.out.println("\t2. 개별 계좌 조회");
		System.out.println("\t3. 은행별 계좌 조회");
		System.out.println("\t4. 계좌 삭제");
		System.out.println("\t5. 계좌 명칭 변경");
		System.out.println("\t6. 계좌 이체");
		System.out.println("\t7. 거래 내역 조회");
		System.out.println("\t8. 계좌 생성");
		System.out.println("\t0. 종료");
		System.out.println("---------------");

		int type = scanInt("메뉴 중 원하는 항목을 선택하세요 : ");
		return type;

	}

	public void login() {

		boolean check_id = false; // 아이디 존재하는 것 확인
		boolean exit_check = false; // 반복문 나가기
		String id;
		String pwd;
		List<Member> list;

		try {

		} catch (Exception e) {
			e.printStackTrace();
		}

		while (true) {

			// BankService의 인스턴스인 service는, BankService가 상위 클래스(BankBaseUI)에서 protected로
			// 선언되어있어서 사용할 수 있음
			try {
				list = service.전체회원목록서비스(); // DB에서 받아와서 VO로 저장, list에 값들을 저장함
				id = scanStr("아이디를 입력하세요."); // VO로 받아온 아이디와 비교
				pwd = scanStr("비밀번호를 입력하세요.");

				for (int i = 0; i < list.size(); i++) {
					if (id.equals(list.get(i).getId())) {
						check_id = true;
						if (pwd.equals(list.get(i).getPwd())) {
							System.out.println("로그인 되었습니다. 반갑습니다." + id + "님");
							exit_check = true;
							session = id;
							break;
						} else
							System.out.println("비밀번호를 잘못 입력하셨습니다.");
						break;

					}
				}
				if (exit_check == true)
					break;

				if (check_id == false)
					// 아이디가 존재하지 않음
					System.out.println("아이디가 존재하지 않습니다.");

			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}
}
