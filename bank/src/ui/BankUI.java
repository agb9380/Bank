package ui;

import java.util.List;
import vo.MemberVO;

public class BankUI extends BankBaseUI {
	
	private List<MemberVO> memberList; // 로그인, 회원가입 중복 체크
	
	public static String session; // 로그인된 아이디가 저장되는 변수
	public static String getSession() {
	return session;
	};
	
	@Override
	public void execute() {
		
		System.out.println("하나은행 통합 계좌관리에 접속하신 것을 환영합니다. ");

		while (true) {
			int mainType = scanInt("메뉴를 선택해주세요. [1] 회원가입 [2] 로그인 [3] 서비스 종료 : ");
			switch (mainType) {
			case 1:
				sign();
				login();
				break;
			case 2:
				login();
				break;
			case 3:
				exitUI ex = new exitUI();
				ex.execute();
				break;
			default:
				System.out.println("잘못입력하셨습니다. 다시 입력해주세요.");
			}

		}

	}
	
	public void sign() {
		System.out.println("---------------------------------------------");
		System.out.println("\t하나은행 통합 계좌관리 회원가입 서비스");
		System.out.println("---------------------------------------------");

		String newId;
		String newPwd;
		String newName;
		String newBirthDate;

		while (true) {
			
			try {
				boolean id_exist = false; // 반복문 실행할 때마다 boolean 초기화
				int cnt = 0; // 반복문 실행할 때마다 0으로 초기화
				memberList = service.전체회원목록서비스();
				newId = scanStr("아이디 : ");

				for (int i = 0; i < memberList.size(); i++) {
					if (newId.equals(memberList.get(i).getId())) {
						id_exist = true; // 중복된 아이디가 존재함
						break; // for문 나가기
					}
				}
				
				if (!id_exist) { // 아이디가 존재하지 않는 경우 회원 가입 진행
					cnt++;
					newPwd = scanStr("비밀번호 : ");
					newName = scanStr("이름 : ");
					newBirthDate = scanStr("생년월일 8자리(ex 19930614) : ");

					MemberVO newMember = new MemberVO(newId, newPwd, newName, newBirthDate);
					service.회원가입서비스(newMember);
					System.out.println("축하합니다. 회원가입이 완료되었습니다.");
					break;
				}
				if (cnt == 0) {
					System.out.println("중복된 아이디가 존재합니다. 다른 아이디를 입력해주세요. ");
				} else { // 정상적으로 회원가입 진행, while문 나가기
					break;
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
	
	public void userMenuSelect() {
		while (true) {

			IBankUI ui = null;
			int type = userMenu();

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
			case 5:
				ui = new alterActNameUI();
				break;
			case 6:
				ui = new transferUI();
				break;
			case 7:
				ui = new transHistoryUI();
				break;
			case 8:
				ui = new createAct();
				break;
			case 0:
				ui = new exitUI();
				break;
			}

			if (ui != null) {
				ui.execute();
			} else {
				System.out.println("잘못선택하셨습니다.");
			}
		}

	}

	public int userMenu() {
		System.out.println("---------------------------------------------");
		System.out.println("\t 하나은행 통합계좌 관리 프로그램");
		System.out.println("---------------------------------------------");
		System.out.println("\t[1] 전체 계좌 조회");
		System.out.println("\t[2] 개별 계좌 조회");
		System.out.println("\t[3] 은행별 계좌 조회");
		System.out.println("\t[4] 계좌 삭제");
		System.out.println("\t[5] 계좌 명칭 변경");
		System.out.println("\t[6] 계좌 이체");
		System.out.println("\t[7] 거래 내역 조회");
		System.out.println("\t[8] 계좌 생성");
		System.out.println("\t[0] 종료");
		System.out.println("---------------------------------------------");

		int type = scanInt("메뉴 중 원하는 항목을 선택하세요 : ");
		return type;

	}

	public void login() {

		boolean check_id = false; // 아이디 존재하는 것 확인
		boolean exit_check = false; // 반복문 나가기
		String id;
		String pwd;
		
		System.out.println("---------------------------------------------");
		System.out.println("\t 하나은행 통합계좌 관리 로그인");
		System.out.println("---------------------------------------------");
		
		try {

		} catch (Exception e) {
			e.printStackTrace();
		}

		while (true) {

			// BankService의 인스턴스인 service는, BankService가 상위 클래스(BankBaseUI)에서 protected로
			// 선언되어있어서 사용할 수 있음
			// break은 반복문을 나갈 때 사용, for문과 while문 둘 다 나가려면 break 2번 사용해야함
			try {
				memberList = service.전체회원목록서비스(); // DB에서 받아와서 VO로 저장, list에 값들을 저장함
				id = scanStr("아이디 : "); // VO로 받아온 아이디와 비교
				pwd = scanStr("비밀번호 :");

				for (int i = 0; i < memberList.size(); i++) {
					if (id.equals(memberList.get(i).getId())) {
						check_id = true;
						if (pwd.equals(memberList.get(i).getPwd())) {
							System.out.println("로그인 되었습니다. 반갑습니다." + id + "님");
							exit_check = true;
							session = id; // 아이디가 관리자인 경우 관리자 메뉴 실행
							userMenuSelect();

							break;
						} else
							System.out.println("비밀번호를 잘못 입력하셨습니다.");
						break; // for문 나가기

					}
				}
				if (exit_check == true)
					break;
				// 아이디와 비밀번호 일치하는 경우 while 반복문 완전히 나감
				if (check_id == false)
					// 아이디가 존재하지 않음, 반복문 계속 돌아야함
					System.out.println("아이디가 존재하지 않습니다.");

			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}
}
