package moneymoney.Input;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.List;

import javax.swing.JOptionPane;

import moneymoney.MoneyPage;
import moneymoney.UserInfo;
import moneymoney.DAO.MoneyDAO;
import moneymoney.DTO.AccountDTO;
import moneymoney.DTO.CategoriesDTO;
import moneymoney.DTO.TransactionDTO;

public class InputLogic implements ActionListener, FocusListener {

	MoneyPage moneyPage;
	
	List<CategoriesDTO> categories;
	
	public InputPage inputPage;
	UserInfo userInfo = UserInfo.getInstance();
//	public static void main(String[] args) {
//		new InputLogic().init();
//	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if (obj == inputPage.inputBtn) {
			inputData();
		} else if (obj == inputPage.depositBtn) {
			setCategories('0');
		} else if (obj == inputPage.withdrawBtn) {
			setCategories('1');
		}
	}

	public InputLogic(MoneyPage moneyPage) {
		this.moneyPage = moneyPage;
		inputPage = new InputPage();
		inputPage.inputBtn.addActionListener(this);

		MoneyDAO dao = MoneyDAO.getInstance();
		// accountNo Setting
		List<AccountDTO> account = dao.accountSelectAll();
		for (int i = 0; i < account.size(); i++) {
			if (account.get(i).getUserId().equals(userInfo.getUserId()))
				inputPage.accountCob.addItem(account.get(i).getBankName() + " : " + account.get(i).getAccountNo());
		}

		// category Setting
		categories = dao.categoriesSelectAll();
		for (int i = 0; i < categories.size(); i++) {
			if(categories.get(i).getInOrOut() == '0')
				inputPage.categoryCob.addItem(categories.get(i).getCategoryName());
		}
		
		inputPage.dateText.addFocusListener(this);
		inputPage.depositBtn.addActionListener(this);
		inputPage.withdrawBtn.addActionListener(this);
	}

	private void clearData() {
		inputPage.dateText.setText("");
		inputPage.accountCob.setSelectedIndex(0);
		inputPage.categoryCob.setSelectedIndex(0);
		inputPage.amountText.setText("");
		inputPage.contentText.setText("");
	}

	private void inputData() {
		// DAO 가져오기
		MoneyDAO dao = MoneyDAO.getInstance();

		//제약조건
		if (!inputPage.dateText.getText().trim().matches("^\\d{4}-\\d{2}-\\d{2}$")) {
			JOptionPane.showMessageDialog(inputPage, "날짜 형식을 맞춰주세요.(YYYY-MM-DD)");
			inputPage.dateText.requestFocus();
			inputPage.dateText.setText("");
			return;
		}
		if (!inputPage.amountText.getText().trim().matches("^[\\d]+$")) {
			JOptionPane.showMessageDialog(inputPage, "금액은 숫자만 입력해주세요.");
			inputPage.amountText.requestFocus();
			inputPage.amountText.setText("");
			return;
		}

		// 입력 데이터 가져오기
		char inOrOut = inputPage.depositBtn.isSelected() ? '0' : '1';
		String useDate = inputPage.dateText.getText().trim();
		String accountNo = inputPage.accountCob.getSelectedItem().toString().split(" : ")[1];
		int categoryId = 0;
		if(inOrOut == '0')
			categoryId = inputPage.categoryCob.getSelectedIndex() + 13;
		else
			categoryId = inputPage.categoryCob.getSelectedIndex() + 1;
		int useAmount = Integer.parseInt(inputPage.amountText.getText().trim());
		String useContents = inputPage.contentText.getText().trim();
		TransactionDTO dto = new TransactionDTO();		
		dto.setAccountNo(accountNo);
		dto.setCategoryId(categoryId);
		dto.setInOrOut(inOrOut);
		dto.setUseAmount(useAmount);
		dto.setUseContents(useContents);
		dto.setUseDate(useDate);

		// DB INSERT
		int cnt = dao.transactionInsert(dto);
		List<AccountDTO> account = dao.accountSelectAll();
		int accountBal = 0;
		for (int i = 0; i < account.size(); i++) {
			if (account.get(i).getAccountNo().equals(accountNo)) {
				accountBal = account.get(i).getAccountBal();
			}
		}
		if (cnt >= 0) {
			int sum = 0;
			if (inOrOut == '0') {
				sum = accountBal + useAmount;
			} else {
				sum = accountBal - useAmount;
			}
			// 금액 변경
			int accountCnt = dao.accountUpdate(sum, accountNo);
			JOptionPane.showMessageDialog(inputPage, "저장 성공");
			// data refresh
			moneyPage.recordPage.refreshData();
		} else {
			JOptionPane.showMessageDialog(inputPage, "저장 실패");
		}
		clearData();
	}
	
	public void setAccountNo() {
		inputPage.accountCob.removeAllItems();
		MoneyDAO dao = MoneyDAO.getInstance();
		// accountNo Setting
		List<AccountDTO> account = dao.accountSelectAll();
		for (int i = 0; i < account.size(); i++) {
			if (account.get(i).getUserId().equals(userInfo.getUserId()))
				inputPage.accountCob.addItem(account.get(i).getBankName() + " : " + account.get(i).getAccountNo());
		}
	}
	
	private void setCategories(char inOrOut) {
		inputPage.categoryCob.removeAllItems();
		for (int i = 0; i < categories.size(); i++) {
			if(categories.get(i).getInOrOut() == inOrOut)
				inputPage.categoryCob.addItem(categories.get(i).getCategoryName());
		}
	}

	@Override
	public void focusGained(FocusEvent e) {
		if(inputPage.dateText.getText().equals("YYYY-MM-DD")) {
			inputPage.dateText.setText("");
			inputPage.dateText.setForeground(Color.black);
		}
	}

	@Override
	public void focusLost(FocusEvent e) {
		if(inputPage.dateText.getText().equals("")) {
			inputPage.dateText.setText("YYYY-MM-DD");
			inputPage.dateText.setForeground(Color.gray);
		}
	}

}
