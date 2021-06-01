package service;

import java.util.List;

import dao.BankDAODB;
import vo.Account;
import vo.Member;

public class BankService {
	
	private BankDAODB dao;
	private List<Member> memberList;
	private List<Account> accountList;
	
	public BankService(){  // 생성자 사용, BankService 호출하면, dao 객체 생성, dao의 메소드에 접근할 수 있음
		dao = new BankDAODB();
	}
	
	//전체 회원 조회 => 리턴값이 member를 가지고 있는 List
	public List<Member>전체회원목록서비스() throws Exception {
		 memberList=dao.전체회원목록(); 
		return memberList;
	}
	
	public List<Account> 전체계좌조회서비스() throws Exception{
		accountList=dao.전체계좌조회();
		return accountList;
	}
	
	public List<Account> 개별계좌조회서비스() throws Exception{
		accountList=dao.개별계좌조회();
		return accountList;
	}
	
	public List<Account> 은행별계좌조회서비스() throws Exception{
		accountList=dao.은행별계좌조회();
		return accountList;
	}
	
	public void 계좌삭제서비스() throws Exception{
		dao.계좌삭제();
	}
	
	
		
	
}
