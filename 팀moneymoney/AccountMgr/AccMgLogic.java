package moneymoney.AccountMgr;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import moneymoney.UserInfo;
import moneymoney.DAO.MoneyDAO;
import moneymoney.DTO.AccountDTO;

public class AccMgLogic implements ActionListener, FocusListener {

	public AccMgPage acc;
	String[] accountNo; // accountNo 배열
	UserInfo userInfo = UserInfo.getInstance();
	final int MAX_ACCOUNT = 3;

	public AccMgLogic() {
		acc = new AccMgPage(); // 연결
		acc.registerBtn.addActionListener(this);
		acc.deleteBtn.addActionListener(this);
		acc.tf.addFocusListener(this);
		selectAccountNo();
	}

	private void init() {
		acc.tf.setText("");
		acc.bankBox.setSelectedIndex(0);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if (obj == acc.registerBtn) {
			selectMethod();

		} else if (obj == acc.deleteBtn) {
			deleteMethod();
		}

	}// end actionPerformed()

//	public static void main(String[] args) {
//		new AccMgLogic();
//
//	}// end main()

	public void selectMethod() {
		MoneyDAO dao = MoneyDAO.getInstance();
		AccountDTO dto = new AccountDTO();

		dto.setUserId(userInfo.getUserId());
		dto.setAccountNo(acc.tf.getText());
		dto.setAccountBal(0);
		dto.setBankName(acc.bankBox.getSelectedItem().toString());
		String badId = "";

		if (acc.tf.getText().contentEquals(badId) || acc.tf.getText().contentEquals("계좌번호를 입력하세요.")) {
			JOptionPane.showMessageDialog(acc, "계좌번호를 입력하세요.");
		} else if (dao.accountInfoCheck(dto) == 1) {
			JOptionPane.showMessageDialog(acc, "이미 존재하는 계좌 입니다.");
//			return; 
		} else if (dao.accountNum(dto) >= MAX_ACCOUNT) {// 수정 필요 -통합
			JOptionPane.showMessageDialog(acc, "계좌의 개수가 초과되었습니다.");
//			return;
		} else {
			dao.accountInsert(dto);
			JOptionPane.showMessageDialog(acc, "등록되었습니다.");
			acc.box.addItem(dto.getBankName() + " : " + dto.getAccountNo());
			init();
		}
		return;
	}

	public void selectAccountNo() {
		// dao 가져옴
		MoneyDAO dao = MoneyDAO.getInstance();
		// 조회시 입력용
		AccountDTO dto = new AccountDTO();
		dto.setUserId(userInfo.getUserId());
		// 결과 반환용
		List<AccountDTO> aList = new ArrayList<AccountDTO>();

		// user_id에 해당하는 account_no 가져오는 쿼리 실행
		aList = dao.getAccountNo(dto);

		// aList 개수만큼 반복하면서 item 추가
		for (int i = 0; i < aList.size(); i++) {
			acc.box.addItem(aList.get(i).getBankName() + " : " + aList.get(i).getAccountNo());
		}
		return;
	}

	public void deleteMethod() {
		// 콤보 박스에서 현재 선택된 item을 받아옴
		if(acc.box.getSelectedIndex() == -1){
			JOptionPane.showMessageDialog(acc, "삭제할 계좌가 없습니다.");
			return;
		}
		
		String str = acc.box.getSelectedItem().toString().split(" : ")[1];

		// delete item에 해당하는 delete 쿼리 실행, 액션
		MoneyDAO dao = MoneyDAO.getInstance();
		AccountDTO dto = new AccountDTO();
		dto.setAccountNo(str);
		int cnt = dao.accountDelete(dto);
		if (cnt == 1) {
			JOptionPane.showMessageDialog(acc, "삭제완료");
			acc.box.removeItem(acc.box.getSelectedItem());
		} else {
			JOptionPane.showMessageDialog(acc, "삭제실패");
		}
		return;
	}

	@Override
	public void focusGained(FocusEvent e) {
		if(acc.tf.getText().equals("계좌번호를 입력하세요.")) {
			acc.tf.setText("");
			acc.tf.setForeground(Color.black);
		}
	}

	@Override
	public void focusLost(FocusEvent e) {
		if(acc.tf.getText().equals("")) {
			acc.tf.setText("계좌번호를 입력하세요.");
			acc.tf.setForeground(Color.gray);
		}
		
	}
}// end class
