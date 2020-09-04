package moneymoney.Main;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import moneymoney.PageInfo;
import moneymoney.UserInfo;
import moneymoney.DAO.MoneyDAO;
import moneymoney.DTO.AccountDTO;
import moneymoney.DTO.UserDTO;

public class MainLogic extends JFrame implements ActionListener, Runnable{

	public MainPage mp = new MainPage();
//	AccMgLogic accL = new AccMgLogic();
	UserInfo userInfo = UserInfo.getInstance();
	PageInfo pageInfo = PageInfo.getInstance();
	private Thread thread;
	
	public MainLogic() {
		
		mp.loginF.addActionListener(this);
		mp.pwF.addActionListener(this);
		mp.loginB.addActionListener(this);
		mp.signUpB.addActionListener(this);
		mp.logoutB.addActionListener(this);
		mp.alarmBtn.addActionListener(this);
		
		if (thread == null) {
			thread = new Thread(this);
			thread.start();
		}
		
	} // end MainLogic()
	
	private void init() {
		
		mp.loginF.setText("");
		mp.pwF.setText("");
		mp.loginF.requestFocus();
		
	} // end init()

	@Override
	public void actionPerformed(ActionEvent e) {

		Object obj = e.getSource();
		if (obj == mp.loginB) {
			loginMethod();
		} else if (obj == mp.signUpB) {
			signUpMethod();
		} else if (obj == mp.loginF) {
			loginMethod();
		} else if (obj == mp.pwF) {
			loginMethod();
		} else if (obj == mp.logoutB) {
			UserInfo userInfo = UserInfo.getInstance();
			userInfo.setUserId(null);
			mp.logoutSuccess();
		} else if (obj == mp.alarmBtn) {
			pageInfo.setAlarm(mp.alarmBtn.isSelected() ? 1 : 0);
			if(mp.alarmBtn.isSelected()) {
				mp.alarmBtn.setIcon(new ImageIcon("src/moneymoney/images/button/alarm_after.png"));
				mp.alarmBtn.setToolTipText("알람 OFF");
			} else {
				mp.alarmBtn.setIcon(new ImageIcon("src/moneymoney/images/button/alarm_before.png"));
				mp.alarmBtn.setToolTipText("알람 ON");
			}
		}
		
	} // end actionPerformed()
	
	private void loginMethod() {
		
		MoneyDAO dao = MoneyDAO.getInstance();
		UserDTO dto = new UserDTO();
		String badId = "";
		
		if (mp.loginF.getText().contentEquals(badId) || mp.pwF.getPassword().length == 0) {
			JOptionPane.showMessageDialog(this, "입력정보를 다시 확인해주세요.");
		} else if (dao.loginCheck(mp.loginF.getText(), String.valueOf(mp.pwF.getPassword())) == 1) {
			mp.loginSuccess();
			userInfo.setUserId(mp.loginF.getText());
			showAccountBal();
			init();
		} else if (dao.loginCheck(mp.loginF.getText(), String.valueOf(mp.pwF.getPassword())) == 0) {
			JOptionPane.showMessageDialog(this, "입력된 ID와 비밀번호가 일치하지 않습니다.");
		} else {
			JOptionPane.showMessageDialog(this, "존재하지 않는 ID입니다.");
		}
		
	} // end loginMethod()
	
	private void signUpMethod() {
		
		MoneyDAO dao = MoneyDAO.getInstance();
		UserDTO dto = new UserDTO();
		
		dto.setUserId(mp.loginF.getText());
		dto.setUserPw(String.valueOf(mp.pwF.getPassword()));
		String badId = "";
		
		if (mp.loginF.getText().contentEquals(badId) || mp.pwF.getPassword().length == 0) {
			JOptionPane.showMessageDialog(this, "입력정보를 다시 확인해주세요.");
		} else if (dao.userInfoCheck(dto) > 0) {
			JOptionPane.showMessageDialog(this, "이미 존재하는 ID입니다.");
		} else {
			dao.userInfoInsert(dto);
			JOptionPane.showMessageDialog(this, "성공적으로 가입되었습니다.");
			init();
		}
		
	} // end signUpMethod()
	
	public void showAccountBal() {
		
		MoneyDAO dao = MoneyDAO.getInstance();
		AccountDTO dto = new AccountDTO();
		dto.setUserId(userInfo.getUserId());
		
		//초기화
		mp.mainPage2.amountFirst.setText("");
		mp.mainPage2.amountSecond.setText("");
		mp.mainPage2.amountThird.setText("");
		
		mp.mainPage2.amountFirst.setVisible(false);
		mp.mainPage2.amountSecond.setVisible(false);
		mp.mainPage2.amountThird.setVisible(false);
		//입력
		List<AccountDTO> aList = dao.getAccountBal(dto);
		if (aList.isEmpty()) {
			return;
		} else {
			
			int sum = 0;
			
			for(int i = 0 ; i < aList.size() ; i++) {
				sum += aList.get(i).getAccountBal();
				
				if(i == 0) {
					mp.mainPage2.amountFirstBorder = new TitledBorder(aList.get(0).getBankName());
					mp.mainPage2.amountFirstBorder.setBorder(new LineBorder(Color.black, 2));
					mp.mainPage2.amountFirstBorder.setTitleFont(new Font("Noto Sans KR Medium", Font.PLAIN, 18));
					mp.mainPage2.amountFirst.setBorder(mp.mainPage2.amountFirstBorder);
					mp.mainPage2.amountFirst.setText(aList.get(0).getAccountNo() +  " : " + aList.get(0).getAccountBal() + "원");
					mp.mainPage2.amountFirst.setVisible(true);
				} else if (i == 1) {
					mp.mainPage2.amountSecondBorder = new TitledBorder(aList.get(1).getBankName());
					mp.mainPage2.amountSecondBorder.setBorder(new LineBorder(Color.black, 2));
					mp.mainPage2.amountSecondBorder.setTitleFont(new Font("Noto Sans KR Medium", Font.PLAIN, 18));
					mp.mainPage2.amountSecond.setBorder(mp.mainPage2.amountSecondBorder);
					mp.mainPage2.amountSecond.setText(aList.get(1).getAccountNo() + " : " + aList.get(1).getAccountBal() + "원");
					mp.mainPage2.amountSecond.setVisible(true);
				} else if (i == 2) {
					mp.mainPage2.amountThirdBorder = new TitledBorder(aList.get(2).getBankName());
					mp.mainPage2.amountThirdBorder.setBorder(new LineBorder(Color.black, 2));
					mp.mainPage2.amountThirdBorder.setTitleFont(new Font("Noto Sans KR Medium", Font.PLAIN, 18));
					mp.mainPage2.amountThird.setBorder(mp.mainPage2.amountThirdBorder);
					mp.mainPage2.amountThird.setText(aList.get(2).getAccountNo() + " : " + aList.get(2).getAccountBal() + "원");
					mp.mainPage2.amountThird.setVisible(true);
				}
			}
			mp.mainPage2.amountTotal.setText("총 : " + sum +"원");
			
		}
		
	} // end showAccountBal()

	@Override
	public void run() {

		while (true) {
			Calendar cal = Calendar.getInstance();
			String now = (cal.get(Calendar.HOUR_OF_DAY) < 10 ? ("0" + cal.get(Calendar.HOUR_OF_DAY)) : cal.get(Calendar.HOUR_OF_DAY)) + ":" +
							(cal.get(Calendar.MINUTE) < 10 ? ("0" + cal.get(Calendar.MINUTE)) : cal.get(Calendar.MINUTE)) + ":" +
							(cal.get(Calendar.SECOND) < 10 ? ("0" + cal.get(Calendar.SECOND)) : cal.get(Calendar.SECOND));
			mp.clockLabel.setText("  " + now);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	} // end run()

	
} // end class
