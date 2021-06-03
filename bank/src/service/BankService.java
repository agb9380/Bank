package service;

import java.util.List;

import dao.BankDAODB;
import vo.AccountVO;
import vo.DealVO;
import vo.MemberVO;
import vo.TransactionVO;

public class BankService {
	
	private BankDAODB dao;
	private List<MemberVO> memberList;
	private List<AccountVO> accountList;
	private List<DealVO> dealList;
	
	public BankService(){  // 생성자 사용, BankService 호출하면, dao 객체 생성, dao의 메소드에 접근할 수 있음
		dao = new BankDAODB();
	}
	
	//전체 회원 조회 => 리턴값이 member를 가지고 있는 List
	public List<MemberVO> 전체회원목록서비스() throws Exception {
		 memberList=dao.전체회원목록(); 
		return memberList;
	}
	
	public List<AccountVO> 전체계좌목록서비스() throws Exception{
		accountList = dao.전체계좌목록();
		return accountList;
	}
	
	
	public List<MemberVO> 개별회원목록서비스() throws Exception {
		memberList=dao.개별회원목록();
		return memberList;
	}
	
	public void 회원가입서비스(MemberVO newMember) throws Exception {
		dao.회원가입(newMember);
	}
	
	public List<MemberVO> 최근계좌생성일자서비스() throws Exception {
		memberList =dao.최근계좌생성일자();
		return memberList; 
	}
	
	public List<AccountVO> 회원별전체계좌조회서비스() throws Exception{
		accountList=dao.회원별전체계좌조회();
		return accountList;
	}
	
	public List<AccountVO> 개별계좌조회서비스(AccountVO account) throws Exception{
		accountList=dao.개별계좌조회(account);
		return accountList;
	}
	
	public List<AccountVO> 은행별계좌조회서비스() throws Exception{
		accountList=dao.은행별계좌조회();
		return accountList;
	}
	
	public void 계좌삭제서비스() throws Exception{
		dao.계좌삭제();
	}
	
	public void 계좌명칭변경서비스() throws Exception{
		dao.계좌명칭변경();
	}
	
	public void 계좌생성서비스(AccountVO account) throws Exception{
		dao.계좌생성(account);
	}
	
	public void 계좌이체서비스(TransactionVO deal) throws Exception{
		dao.계좌이체(deal);
	}
	
	public List<DealVO> 거래내역조회서비스() throws Exception{
		dealList = dao.거래내역조회();
		return dealList;
	}
	
}
