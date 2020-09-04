package moneymoney.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import moneymoney.DTO.AccountDTO;
import moneymoney.DTO.CategoriesDTO;
import moneymoney.DTO.TransactionDTO;
import moneymoney.DTO.UserDTO;

public class MoneyDAO {

	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;

	private static MoneyDAO dao = new MoneyDAO();

	private MoneyDAO() {
	}

	public static MoneyDAO getInstance() {
		return dao;
	}

	public List<UserDTO> userInfoSelectAll() {

		List<UserDTO> aList = new ArrayList<UserDTO>();

		try {
			conn = Jdbc.getInit();
			stmt = conn.createStatement();
			String sql = "select * from user_info";
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				UserDTO dto = new UserDTO();
				dto.setUserId(rs.getString("user_id"));
				dto.setUserPw(rs.getString("user_pw"));
				aList.add(dto);
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			Jdbc.close(rs);
			Jdbc.close(stmt);
			Jdbc.close(conn);
		}

		return aList;

	} // end userInfo()

	public List<AccountDTO> accountSelectAll() {

		List<AccountDTO> aList = new ArrayList<AccountDTO>();

		try {
			conn = Jdbc.getInit();
			stmt = conn.createStatement();
			String sql = "select * from account";
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				AccountDTO dto = new AccountDTO();
				dto.setAccountNo(rs.getString("account_no"));
				dto.setUserId(rs.getString("user_id"));
				dto.setAccountBal(rs.getInt("account_bal"));
				dto.setBankName(rs.getString("bank_name"));
				aList.add(dto);
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			Jdbc.close(rs);
			Jdbc.close(stmt);
			Jdbc.close(conn);
		}

		return aList;

	}

	public List<TransactionDTO> transactionSelectAll(UserDTO dto) {

		List<TransactionDTO> aList = new ArrayList<TransactionDTO>();

		try {
			conn = Jdbc.getInit();
			String sql = "select *" + 
					" from account acc, transaction tra" + 
					" where acc.account_no = tra.account_no" + 
					" and user_id = ?" +
					" order by use_date desc";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getUserId());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				TransactionDTO dtoT = new TransactionDTO();
				dtoT.setTransactionNo(rs.getInt("transaction_no"));
				dtoT.setAccountNo(rs.getString("account_no"));
				dtoT.setInOrOut((rs.getString("in_out")).charAt(0));
				dtoT.setUseDate(rs.getString("use_date"));
				dtoT.setCategoryId(rs.getInt("category_id"));
				dtoT.setUseAmount(rs.getInt("use_amount"));
				dtoT.setUseContents(rs.getString("use_contents"));

				aList.add(dtoT);
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			Jdbc.close(rs);
			Jdbc.close(pstmt);
			Jdbc.close(conn);
		}

		return aList;

	}

	public List<CategoriesDTO> categoriesSelectAll() {

		List<CategoriesDTO> aList = new ArrayList<CategoriesDTO>();

		try {
			conn = Jdbc.getInit();
			stmt = conn.createStatement();
			String sql = "select * from categories";
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				CategoriesDTO dto = new CategoriesDTO();
				dto.setCategoryId(rs.getInt("category_id"));
				dto.setCategoryName(rs.getString("category_name"));
				dto.setInOrOut(rs.getString("in_out").charAt(0));
				aList.add(dto);
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			Jdbc.close(rs);
			Jdbc.close(pstmt);
			Jdbc.close(stmt);
			Jdbc.close(conn);
		}

		return aList;

	}
	
	public int userInfoCheck(UserDTO dto) {
		
		int cnt = 0;
		
		try {
			conn = Jdbc.getInit();
			String sql = "select count(user_id) as count from user_info where user_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getUserId());
			rs = pstmt.executeQuery();
			rs.next();
			cnt = rs.getInt("count");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			Jdbc.close(rs);
			Jdbc.close(pstmt);
			Jdbc.close(conn);
		}
		
		return cnt;
	}
	
	public int loginCheck(String id, String pw) {
		
		String pwCheck = "";
		int chk = -1;
		
		try {
			conn = Jdbc.getInit();
			String sql = "select user_pw from user_info where user_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				pwCheck = rs.getString("user_pw");
				if (pwCheck.equals(pw)) {
					chk = 1;
				} else {
					chk = 0;
				}	
			} else {
				chk = -1;
			} 
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			Jdbc.close(rs);
			Jdbc.close(pstmt);
			Jdbc.close(conn);
		}
		
		return chk;
		
	}
	
	public List<AccountDTO> getAccountBal(AccountDTO dto) {
		
		List<AccountDTO> aList = new ArrayList<AccountDTO>();
		
		try {
			conn = Jdbc.getInit();
			String sql = "select * from account where user_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getUserId());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				AccountDTO aDto = new AccountDTO();
				aDto.setAccountBal(rs.getInt("account_bal"));
				aDto.setBankName(rs.getString("bank_name"));
				aDto.setAccountNo(rs.getString("account_no"));
				aList.add(aDto);
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			Jdbc.close(rs);
			Jdbc.close(pstmt);
			Jdbc.close(conn);
		}
		
		return aList;
	}

	public void userInfoInsert(UserDTO dto) {
		
		try {
			conn = Jdbc.getInit();
			conn.setAutoCommit(false);
			String sql = "insert into user_info(user_id, user_pw) values(?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getUserId());
			pstmt.setString(2, dto.getUserPw());
			pstmt.executeUpdate();
			conn.commit();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			Jdbc.close(rs);
			Jdbc.close(pstmt);
			Jdbc.close(conn);
		}
	}
	
	public int transactionUpdate(TransactionDTO dto) {
		
		int cnt = -1;
		
		try {
			conn = Jdbc.getInit();
			String sql = "update transaction set in_out = ?, set use_date = ?, set category_id = ?,"
					+ " set use_amount = ?, set use_contents = ? where transaction_no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, String.valueOf(dto.getInOrOut()));
			pstmt.setString(2, dto.getUseDate());
			pstmt.setInt(3, dto.getCategoryId());
			pstmt.setInt(4, dto.getUseAmount());
			pstmt.setString(5, dto.getUseContents());
			pstmt.setInt(6, dto.getTransactionNo());
			cnt = pstmt.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
				Jdbc.close(rs);
				Jdbc.close(pstmt);
				Jdbc.close(conn);
		}
		
		return cnt;
	}

	public int transactionDelete(int num) {

		int cnt = -1;
		
		try {
			conn = Jdbc.getInit();
			String sql = "delete from transaction where transaction_no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			cnt = pstmt.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			Jdbc.close(rs);
			Jdbc.close(pstmt);
			Jdbc.close(conn);
		}
		
		return cnt;		
	}

	public int transactionInsert(TransactionDTO dto) {
		int cnt = -1;
		try {
			conn = Jdbc.getInit();
			conn.setAutoCommit(false);
			String sql = "insert into transaction(transaction_no, account_no, in_out,"
					+ " use_date, category_id, use_amount, use_contents)"
					+ " values(TRANSACTION_NO_SEQ.nextval, ?, ?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getAccountNo());
			pstmt.setString(2, String.valueOf(dto.getInOrOut()));
			pstmt.setString(3, dto.getUseDate());
			pstmt.setInt(4, dto.getCategoryId());
			pstmt.setInt(5, dto.getUseAmount());
			pstmt.setString(6, dto.getUseContents());
			cnt = pstmt.executeUpdate();
			conn.commit();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			Jdbc.close(rs);
			Jdbc.close(pstmt);
			Jdbc.close(conn);
		}
		return cnt;
	}

	public void accountInsert(AccountDTO dto) {
		try {
			conn = Jdbc.getInit();
			conn.setAutoCommit(false);
			String sql = "insert into account(account_no, user_id, account_bal, bank_name)"
					+ " values(?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getAccountNo());
			pstmt.setString(2, dto.getUserId());
			pstmt.setInt(3, dto.getAccountBal());
			pstmt.setString(4, dto.getBankName());
			pstmt.executeUpdate();
			conn.commit();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			Jdbc.close(rs);
			Jdbc.close(pstmt);
			Jdbc.close(conn);
		}
	}
	// 삭제 쿼리
	public int accountDelete(AccountDTO dto) {

		int cnt = -1;
		
		try {
			conn = Jdbc.getInit();
			String sql = "delete from account where account_no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getAccountNo());
			cnt = pstmt.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			Jdbc.close(rs);
			Jdbc.close(pstmt);
			Jdbc.close(conn);
		}
		
		return cnt;	
	}

	public int accountUpdate(int accountBal, String accountNo) {
		int cnt = -1;

		try {
			conn = Jdbc.getInit();
			String sql = "UPDATE account SET account_bal = ? where account_no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, accountBal);
			pstmt.setString(2, accountNo);
			cnt = pstmt.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			Jdbc.close(rs);
			Jdbc.close(pstmt);
			Jdbc.close(conn);
		}

		return cnt;
	}
	public int accountInfoCheck(AccountDTO dto) { //계좌 은행, 계좌 번호, 계좌 갯수 비교
		
		int cnt = 0;
		
		String str = dto.getAccountNo();
		
		try {
			conn = Jdbc.getInit();
			String sql = "SELECT ACCOUNT_NO FROM ACCOUNT";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				String accountNo = rs.getString("account_no");
				if(str.equals(accountNo)) {
					cnt = 1;
					break;
				}
			};		
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			Jdbc.close(rs);
			Jdbc.close(stmt);
			Jdbc.close(conn);
		}
		
		return cnt;
	}
	
	public int accountNum(AccountDTO dto) {
		
		int cnt = 0;
		
		try {
			conn = Jdbc.getInit();
			String sql = "select count(account_no) as count from account where user_id = ?"; //통합시 user_id 생각
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getUserId());
			rs = pstmt.executeQuery();
			rs.next(); //다음 줄을 읽게 잡아줌.
			cnt = rs.getInt("count");			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			Jdbc.close(rs);
			Jdbc.close(pstmt);
			Jdbc.close(conn);
		}
		
		return cnt;
	}
	
		public ArrayList<AccountDTO> getAccountNo(AccountDTO dto) {
			ArrayList<AccountDTO> aList = new ArrayList<>();
			
		try {
			conn = Jdbc.getInit();
			String sql = "select * from account where user_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getUserId());
			rs = pstmt.executeQuery();
			while(rs.next()) {
				AccountDTO dt = new AccountDTO();
				dt.setAccountNo(rs.getString("account_no"));
				dt.setBankName(rs.getString("bank_name"));
				aList.add(dt);
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			Jdbc.close(rs);
			Jdbc.close(pstmt);
			Jdbc.close(conn);
		}
		
		return aList;
	}

		
} // end userDAO
