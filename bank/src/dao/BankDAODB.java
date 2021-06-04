package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ui.BankUI;
import ui.alterActNameUI;
import ui.deleteAcntUI;
import ui.searchBankNameUI;
import util.ConnectionFactory;
import util.JDBCClose;
import vo.AccountVO;
import vo.DealVO;
import vo.MemberVO;
import vo.TransactionVO;

public class BankDAODB {

	List<MemberVO> memberList;
	List<AccountVO> accountList;
	List<DealVO> dealList;
//	TransactionVO.java

	// 전체 회원 목록 (로그인 할 때 or 관리자로 회원목록 조회할 때 사용)
	public List<MemberVO> 전체회원목록() throws Exception {

		memberList = new ArrayList<>(); // 멤버 객체를 저장할 수 있는 list
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = new ConnectionFactory().getConnection();
			StringBuilder sql = new StringBuilder();

			sql.append("select member_id, pwd, name, birt_date, enroll_date, trim(RECENT_ACCOUNT_DATE) ");
			sql.append(" from member ");

			pstmt = conn.prepareStatement(sql.toString());

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				String id = rs.getString("member_id");
				String pwd = rs.getString("pwd");
				String name = rs.getString("name");
				String birth_date = rs.getString("birt_date");
				String enroll_date = rs.getString("enroll_date");
				String recent_account_date = rs.getString("trim(RECENT_ACCOUNT_DATE)");
				MemberVO member = new MemberVO(id, pwd, name, birth_date, enroll_date,recent_account_date);
				
				memberList.add(member);

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCClose.close(conn, pstmt);
		}
		return memberList;
	}
	
	
	public List<AccountVO> 전체계좌목록() throws Exception {

		List<AccountVO> accountList = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = new ConnectionFactory().getConnection();
			StringBuilder sql = new StringBuilder();

			sql.append("select ACCOUNT_NUMBER, BANK_NAME,MEMBER_ID,BALANCE, ACCOUNT_NAME ");
			sql.append(" from ACCOUNT ");

			pstmt = conn.prepareStatement(sql.toString());


			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				String actNum = rs.getString("ACCOUNT_NUMBER");
				String bankName = rs.getString("BANK_NAME");
				String memberId = rs.getString("MEMBER_ID");
				int balance = rs.getInt("BALANCE");
				String actName = rs.getString("ACCOUNT_NAME");

				AccountVO account = new AccountVO(actNum, bankName, memberId, balance, actName);
				accountList.add(account);

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCClose.close(conn, pstmt);
		}
		return accountList;
	}
	
	
	public void 회원가입(MemberVO newMember) throws Exception{
		
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = new ConnectionFactory().getConnection();
			StringBuilder sql = new StringBuilder();

			sql.append("INSERT INTO MEMBER (MEMBER_ID,PWD,NAME, BIRT_DATE) ");
			sql.append(" VALUES(?, ?, ?, ?) ");

			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, newMember.getId());
			pstmt.setString(2, newMember.getPwd());
			pstmt.setString(3, newMember.getName());
			pstmt.setString(4, newMember.getBirth_date());
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCClose.close(conn, pstmt);
		}	
		
	}
	
	public List<MemberVO> 최근계좌생성일자() throws Exception{
		
		memberList = new ArrayList<>(); // 멤버 객체를 저장할 수 있는 list
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = new ConnectionFactory().getConnection();
			StringBuilder sql = new StringBuilder();

			sql.append("select nvl(trunc(sysdate-RECENT_ACCOUNT_DATE),-1) as \"RECENT_ACCOUNT_DATE\" from member ");
			sql.append(" WHERE MEMBER_ID = ? ");

			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, BankUI.getSession());
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) { 
				String recent_account_date = rs.getString("RECENT_ACCOUNT_DATE"); //sql result set을 기준으로 get
				MemberVO member = new MemberVO(recent_account_date);
				memberList.add(member);

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCClose.close(conn, pstmt);
		}
		return memberList;
		
		
	}
	
	public List<MemberVO> 개별회원목록() throws Exception {

		memberList = new ArrayList<>(); // 멤버 객체를 저장할 수 있는 list
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = new ConnectionFactory().getConnection();
			StringBuilder sql = new StringBuilder();

			sql.append("SELECT MEMBER_ID, PWD, NAME, birt_date,ENROLL_DATE, trim(RECENT_ACCOUNT_DATE) ");
			sql.append(" from member ");
			sql.append(" WHERE MEMBER_ID = ? ");

			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, BankUI.getSession());
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				String id = rs.getString("member_id");
				String pwd = rs.getString("pwd");
				String name = rs.getString("name");
				String birth_date = rs.getString("birt_date");
				String enroll_date = rs.getString("enroll_date");
				String recent_account_date = rs.getString("trim(RECENT_ACCOUNT_DATE)");

				MemberVO member = new MemberVO(id, pwd, name, birth_date, enroll_date, recent_account_date);
				memberList.add(member);

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCClose.close(conn, pstmt);
		}
		return memberList;
	}
	

/////////////////////////////////////////////////////////////////////
	
	
	
	public List<AccountVO> 회원별전체계좌조회() throws Exception { //로그인한 아이디가 가지고 있는 계좌 정보 조회
		accountList = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = new ConnectionFactory().getConnection();
			StringBuilder sql = new StringBuilder();

			sql.append("select ACCOUNT_NUMBER, BANK_NAME,MEMBER_ID,BALANCE, ACCOUNT_NAME ");
			sql.append(" from ACCOUNT ");
			sql.append(" WHERE MEMBER_ID= ? ");

			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, BankUI.getSession());

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				String actNum = rs.getString("ACCOUNT_NUMBER");
				String bankName = rs.getString("BANK_NAME");
				String memberId = rs.getString("MEMBER_ID");
				int balance = rs.getInt("BALANCE");
				String actName = rs.getString("ACCOUNT_NAME");

				AccountVO account = new AccountVO(actNum, bankName, memberId, balance, actName);
				accountList.add(account);

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCClose.close(conn, pstmt);
		}
		return accountList;
	}


	public List<AccountVO> 개별계좌조회(AccountVO account) throws Exception {
		
		accountList = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = new ConnectionFactory().getConnection();
			StringBuilder sql = new StringBuilder();

			sql.append("select ACCOUNT_NUMBER, BANK_NAME,MEMBER_ID,BALANCE,ACCOUNT_NAME ");
			sql.append(" from ACCOUNT ");
			sql.append(" WHERE MEMBER_ID= ? ");
			sql.append(" AND account_number= ? ");

			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, BankUI.getSession());
//			pstmt.setString(2, searchOneUI.getInputAct()); // UI에서 입력받은 계좌번호
			pstmt.setString(2, account.getActNum()); // UI에서 입력받은 계좌번호

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				String actNum = rs.getString("ACCOUNT_NUMBER");
				String bankName = rs.getString("BANK_NAME");
				String memberId = rs.getString("MEMBER_ID");
				int balance = rs.getInt("BALANCE");
				String actName = rs.getString("ACCOUNT_NAME");

				AccountVO account2 = new AccountVO(actNum, bankName, memberId, balance, actName);
				accountList.add(account2);

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCClose.close(conn, pstmt);
		}
		return accountList;

	}

	public List<AccountVO> 은행별계좌조회() {

		accountList = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = new ConnectionFactory().getConnection();
			StringBuilder sql = new StringBuilder();

			sql.append("select ACCOUNT_NUMBER, BANK_NAME,MEMBER_ID,BALANCE,ACCOUNT_NAME ");
			sql.append(" from ACCOUNT ");
			sql.append(" WHERE MEMBER_ID= ? ");
			sql.append(" AND BANK_NAME LIKE ?||'%' ");

			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, BankUI.getSession());
			pstmt.setString(2, searchBankNameUI.getInputBankName()); // UI에서 입력받은 계좌번호

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				String actNum = rs.getString("ACCOUNT_NUMBER");
				String bankName = rs.getString("BANK_NAME");
				String memberId = rs.getString("MEMBER_ID");
				int balance = rs.getInt("BALANCE");
				String actName = rs.getString("ACCOUNT_NAME");

				AccountVO account = new AccountVO(actNum, bankName, memberId, balance, actName);
				accountList.add(account);

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCClose.close(conn, pstmt);
		}
		return accountList;

	}

	public void 계좌삭제() { // 계좌 정보 조회(해당 사용자, 원하는 계좌),잔고 확인(0이면 삭제 가능)

		Connection conn = null;
		PreparedStatement pstmt = null;
		ArrayList<String> actNumList = new ArrayList();
		ArrayList<Integer> balList = new ArrayList<>();
 

		try {
			conn = new ConnectionFactory().getConnection();
			StringBuilder select_sql = new StringBuilder();
			StringBuilder delete_sql = new StringBuilder();

			select_sql.append("SELECT ACCOUNT_NUMBER, BALANCE FROM ACCOUNT ");
			select_sql.append(" WHERE MEMBER_ID= ? ");
			select_sql.append(" AND ACCOUNT_NUMBER= ? ");

			pstmt = conn.prepareStatement(select_sql.toString());
			pstmt.setString(1, BankUI.getSession()); // session
			pstmt.setString(2, deleteAcntUI.getInputDelAct()); // 삭제하고 싶은 계좌 번호

			ResultSet rs = pstmt.executeQuery(); // 해당 계좌번호가 존재하는지 result set 확인

			while (rs.next()) {
				actNumList.add(rs.getString("ACCOUNT_NUMBER"));
				balList.add(rs.getInt("BALANCE"));
				
			}

			if (actNumList.size() == 1) {
				
				if(balList.get(0) == 0) { // 조회한 해당 계좌의 잔액이 0일 때만 계좌 삭제 가능
					
					delete_sql.append("DELETE ACCOUNT WHERE ACCOUNT_NUMBER = ? ");
					
					pstmt = conn.prepareStatement(delete_sql.toString());
					pstmt.setString(1, deleteAcntUI.getInputDelAct());
					pstmt.executeQuery();
					System.out.println("입력하신 계좌를 삭제했습니다.");
				}else {
					System.out.println("삭제할 수 없습니다. 계좌에 잔고가 남아있습니다.");
				}
				
			} else {
				System.out.println("입력하신 계좌 번호가 존재하지 않습니다.");
			}


		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCClose.close(conn, pstmt);
		}

	}
	
	
	public void 계좌명칭변경() {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ArrayList<String> actNumList = new ArrayList();

		
		try {
			conn = new ConnectionFactory().getConnection();
			StringBuilder select_sql = new StringBuilder();
			StringBuilder update_sql = new StringBuilder();

			select_sql.append("SELECT ACCOUNT_NUMBER FROM ACCOUNT ");
			select_sql.append("WHERE MEMBER_ID= ? ");
			select_sql.append(" AND ACCOUNT_NUMBER= ? ");

			pstmt = conn.prepareStatement(select_sql.toString());
			pstmt.setString(1, BankUI.getSession()); // session
			pstmt.setString(2, alterActNameUI.getUpdateActNo()); // 삭제하고 싶은 계좌 번호

			ResultSet rs = pstmt.executeQuery(); // 해당 계좌번호가 존재하는지 result set 확인

			while (rs.next()) {
				actNumList.add(rs.getString("ACCOUNT_NUMBER"));
			}

			if (actNumList.size() == 1) { // 
				
				update_sql.append("UPDATE ACCOUNT SET ACCOUNT_NAME= ? ");
				update_sql.append(" WHERE MEMBER_ID= ? ");
				update_sql.append(" AND ACCOUNT_NUMBER= ? ");
					
					pstmt = conn.prepareStatement(update_sql.toString());
					pstmt.setString(1, alterActNameUI.getUpdateActName());
					pstmt.setString(2, BankUI.getSession());
					pstmt.setString(3, alterActNameUI.getUpdateActNo());
					
					pstmt.executeQuery();
					System.out.println("계좌의 명칭을 변경했습니다.");
				
				}
			else {
				System.out.println("입력하신 계좌 번호가 존재하지 않습니다.");
			}
			


		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCClose.close(conn, pstmt);
		}
		
	}
	
	public void 계좌생성(AccountVO account) {
		
		Connection conn =null;
		PreparedStatement pstmt =null;
		
		try {
			
			conn = new ConnectionFactory().getConnection();
			StringBuilder insert_sql = new StringBuilder();
			StringBuilder update_sql = new StringBuilder();
			
			insert_sql.append("INSERT INTO ACCOUNT ");
			insert_sql.append("(ACCOUNT_NUMBER,BANK_NAME,MEMBER_ID,BALANCE,ACCOUNT_NAME) ");
			insert_sql.append(" VALUES(to_char(sysdate, 'YYMMDD')||to_char(systimestamp, 'FF3'), ");
			insert_sql.append(" '하나', ?, ?, ?) ");
			// 1. member_id 2. balance 3. 계좌명칭
			pstmt = conn.prepareStatement(insert_sql.toString());
			pstmt.setString(1, BankUI.getSession());
			pstmt.setInt(2, account.getBalance());
			pstmt.setString(3, account.getActName());
			
			pstmt.executeUpdate();
			
			
			update_sql.append("UPDATE MEMBER SET RECENT_ACCOUNT_DATE = ");
			update_sql.append("(SELECT MAX(OPEN_ACCOUT_DATE) FROM ACCOUNT WHERE MEMBER_ID =?) ");
			update_sql.append(" WHERE MEMBER_ID =? ");
			
			pstmt = conn.prepareStatement(update_sql.toString());
			pstmt.setString(1, BankUI.getSession());
			pstmt.setString(2, BankUI.getSession());
			
			pstmt.executeUpdate();
			

			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCClose.close(conn, pstmt);
		}
		
		
		
	}
	
	public void 계좌이체(TransactionVO deal) { // deal테이블에 insert, 계좌 테이블 update
		
		Connection conn =null;
		PreparedStatement pstmt =null;
		
		try { // account에 제약조건이 잔액 0이상이 걸려있음, 그런데 거래내역에는 이런 제약조건이 없어서, 잔고를 초과하는 거래 발생 시 위(거래내역)에는 반영 / 밑(계좌 잔액)에는 실행되지 않음
			
			conn = new ConnectionFactory().getConnection();
			conn.setAutoCommit(false);   // 입출금 실패 시 롤백하기 위해 오토커밋 해제 
			StringBuilder insert_sql = new StringBuilder();
			
			StringBuilder withdrawUpdate_sql = new StringBuilder();
			StringBuilder depositUpdate_sql = new StringBuilder();
			
			insert_sql.append("INSERT ALL ");
			insert_sql.append(" INTO DEAL(DEAL_ID, ACCOUNT_NUMBER, BANK_NAME, TYPE, MONEY) ");
			insert_sql.append(" VALUES(DEAL_SEQ.NEXTVAL, ?, ?,'출금', ?) "); // 출금 정보
			
			//error
			insert_sql.append(" INTO DEAL(DEAL_ID, ACCOUNT_NUMBER, BANK_NAME, TYPE, MONEY) "); 
			insert_sql.append(" VALUES(DEAL_SEQ.NEXTVAL, ?, ?,'입금', ?) "); // 입금 정보
			insert_sql.append(" SELECT 1 FROM DUAL ");
			
			pstmt = conn.prepareStatement(insert_sql.toString());
			
			pstmt.setString(1, deal.getWithdrawActNo()); //출금 계좌 번호
			pstmt.setString(2, deal.getWithdrawBankName()); //출금할 은행명
			pstmt.setInt(3, deal.getMoney()); //이체할 금액
			pstmt.setString(4, deal.getDepositActNo()); //입금할 계좌 번호
			pstmt.setString(5, deal.getDepositBankName()); // 입금할 은행
			pstmt.setInt(6, deal.getMoney()); // 출금 금액 = 입금 금액
			
			pstmt.executeUpdate();
			
			withdrawUpdate_sql.append( "UPDATE ACCOUNT SET BALANCE = BALANCE- ? ");
			withdrawUpdate_sql.append( " WHERE ACCOUNT_NUMBER =? ");
			
			pstmt = conn.prepareStatement(withdrawUpdate_sql.toString());
			
			pstmt.setInt(1, deal.getMoney());
			pstmt.setString(2, deal.getWithdrawActNo()); // 출금할 계좌 번호
			
			pstmt.executeUpdate();
			
			
			depositUpdate_sql.append( "UPDATE ACCOUNT SET BALANCE = BALANCE+ ? ");
			depositUpdate_sql.append( " WHERE ACCOUNT_NUMBER =? ");
			
			pstmt = conn.prepareStatement(depositUpdate_sql.toString());
			
			pstmt.setInt(1, deal.getMoney());
			pstmt.setString(2, deal.getDepositActNo()); // 입금할 계좌 번호
			
			pstmt.executeUpdate();
			conn.commit(); //실행 완료 후 commit

			
		} catch (Exception e) {
			//에러 발생 rollback
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			JDBCClose.close(conn, pstmt);
		}
		
		
	}
	
	public List<DealVO> 거래내역조회() { // 로그인한 아이디에 해당하는 거래내역 조회
		
		dealList = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		
		try {
			conn = new ConnectionFactory().getConnection();
			StringBuilder sql = new StringBuilder();

			sql.append("SELECT D.ACCOUNT_NUMBER, D.BANK_NAME, D.TYPE,D.MONEY, TO_char(D.DEAL_DATE,'YYYY-MM-DD HH24:MI:SS') as DEAL_DATE ");
			sql.append(" FROM DEAL D, (SELECT A.MEMBER_ID, A.ACCOUNT_NUMBER ");
			sql.append(" FROM ACCOUNT A ,MEMBER M ");
			sql.append(" WHERE A.MEMBER_ID = M.MEMBER_ID) IV ");
			sql.append(" WHERE D.ACCOUNT_NUMBER = IV.ACCOUNT_NUMBER AND MEMBER_ID=? ");
			sql.append(" ORDER BY D.DEAL_DATE ASC ");

			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, BankUI.getSession());

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				String actNum = rs.getString("ACCOUNT_NUMBER");
				String bankName = rs.getString("BANK_NAME");
				int money = rs.getInt("MONEY");
				String type =rs.getString("TYPE");
				String dealDate = rs.getString("DEAL_DATE");
				
				DealVO deal = new DealVO(actNum, bankName, money, type, dealDate);
				dealList.add(deal);
			}
				

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCClose.close(conn, pstmt);
		}
		return dealList;
		
	}

}
