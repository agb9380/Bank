package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import ui.BankUI;
import ui.deleteAcntUI;
import ui.searchBankNameUI;
import ui.searchOneUI;
import util.ConnectionFactory;
import util.JDBCClose;
import vo.Account;
import vo.Member;

public class BankDAODB {

	List<Member> memberList;
	List<Account> accountList;

	// 전체 회원 목록 (로그인 할 때 or 관리자로 회원목록 조회할 때 사용)
	public List<Member> 전체회원목록() throws Exception {

		memberList = new ArrayList<>(); // 멤버 객체를 저장할 수 있는 list
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = new ConnectionFactory().getConnection();
			StringBuilder sql = new StringBuilder();

			sql.append("select member_id, pwd, name, birt_date, enroll_date ");
			sql.append(" from member ");

			pstmt = conn.prepareStatement(sql.toString());

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				String id = rs.getString("member_id");
				String pwd = rs.getString("pwd");
				String name = rs.getString("name");
				String birth_date = rs.getString("birt_date");
				String enroll_date = rs.getString("enroll_date");

				Member member = new Member(id, pwd, name, birth_date, enroll_date);
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

	public List<Account> 전체계좌조회() throws Exception {
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

				Account account = new Account(actNum, bankName, memberId, balance, actName);
				accountList.add(account);

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCClose.close(conn, pstmt);
		}
		return accountList;
	}

	public List<Account> 개별계좌조회() throws Exception {

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
			pstmt.setString(2, searchOneUI.getInputAct()); // UI에서 입력받은 계좌번호

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				String actNum = rs.getString("ACCOUNT_NUMBER");
				String bankName = rs.getString("BANK_NAME");
				String memberId = rs.getString("MEMBER_ID");
				int balance = rs.getInt("BALANCE");
				String actName = rs.getString("ACCOUNT_NAME");

				Account account = new Account(actNum, bankName, memberId, balance, actName);
				accountList.add(account);

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCClose.close(conn, pstmt);
		}
		return accountList;

	}

	public List<Account> 은행별계좌조회() {

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

				Account account = new Account(actNum, bankName, memberId, balance, actName);
				accountList.add(account);

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCClose.close(conn, pstmt);
		}
		return accountList;

	}

	public void 계좌삭제() {

		Connection conn = null;
		PreparedStatement pstmt = null;
//		String actNum; // 입력한 계좌 번호가 존재하는지 확인하는 변수
		ArrayList list = new ArrayList();
//		StringBuilder actNum; 

		try {
			conn = new ConnectionFactory().getConnection();
			StringBuilder select_sql = new StringBuilder();
			StringBuilder delete_sql = new StringBuilder();

			select_sql.append("SELECT ACCOUNT_NUMBER FROM ACCOUNT ");
			select_sql.append("WHERE MEMBER_ID= ? ");
			select_sql.append(" AND ACCOUNT_NUMBER= ? ");

			pstmt = conn.prepareStatement(select_sql.toString());
			pstmt.setString(1, BankUI.getSession()); // session
			pstmt.setString(2, deleteAcntUI.getInputDelAct()); // 삭제하고 싶은 계좌 번호

			ResultSet rs = pstmt.executeQuery(); // 해당 계좌번호가 존재하는지 result set 확인

			while (rs.next()) {
				list.add(rs.getString("ACCOUNT_NUMBER"));
				//balance가 0일 때만 삭제할 수 있도록 제약 조건 추가
			}

			if (list.size() == 1) {
				
				
				delete_sql.append("DELETE ACCOUNT WHERE ACCOUNT_NUMBER = ? ");
				
				pstmt = conn.prepareStatement(delete_sql.toString());
				pstmt.setString(1, deleteAcntUI.getInputDelAct());
				pstmt.executeQuery();
				System.out.println("입력하신 계좌를 삭제했습니다.");
			} else {
				System.out.println("입력하신 계좌 번호가 존재하지 않습니다.");
			}


		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCClose.close(conn, pstmt);
		}

	}

}
