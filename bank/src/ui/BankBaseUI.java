package ui;

import java.util.Scanner;

import service.BankService;

public abstract class BankBaseUI implements IBankUI {
	
	private Scanner sc;
	protected BankService service; // protected 상속받은 경우 외부에서 접근할 수 있음

	public BankBaseUI() {
		
		sc = new Scanner(System.in);
		service = new BankService();
	}
	
	
	// 출력 후 String 반환
	public String scanStr(String msg) {
		
		System.out.println(msg);
		String str = sc.nextLine();
		return str;
		
	}
	
	// 출력 후 Sring형 받은 뒤, int형 반환 => case로 넘겨서 해당 메소드 실행
	public int scanInt(String msg) {
		
		int num = Integer.parseInt(scanStr(msg));
		return num;
	}
	
}
