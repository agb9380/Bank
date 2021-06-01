package ui;

public class deleteAcntUI extends BankBaseUI {
	
	public static String inputDelAct;
	
	public static String getInputDelAct() {
		return inputDelAct;
	}
	
	@Override
	public void execute() {
		
		try {
			inputDelAct=scanStr("삭제하고 싶은 계좌의 계좌번호를 입력하세요.");
			service.계좌삭제서비스();
	
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
	}

}
