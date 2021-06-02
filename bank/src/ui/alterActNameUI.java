package ui;

public class alterActNameUI extends BankBaseUI {

	public static String updateActNo;
	public static String updateActName;

	public static String getUpdateActNo() {
		return updateActNo;
	}

	public static String getUpdateActName() {
		return updateActName;
	}

	@Override
	public void execute() {

		try {
			updateActNo = scanStr("명칭을 변경하고 싶은 계좌의 계좌번호를 입력해주세요 : ");
			// 해당 계좌 번호가 존재하는지 여기서 확인???
			updateActName = scanStr("새로운 계좌 명칭 : ");
			service.계좌명칭변경서비스();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
