package moneymoney;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;

import moneymoney.AccountMgr.AccMgPage;
import moneymoney.Chatting.ChatPage;
import moneymoney.Input.InputPage;
import moneymoney.Main.MainPage;
import moneymoney.Main.MainPage2;
import moneymoney.Record.RecordChart;
import moneymoney.Record.RecordDetail;
import moneymoney.Record.RecordPage;

public class MoneyDesign implements MouseListener {
	Color btnColor = new Color(55, 55, 55);
	Color borderColor = new Color(55, 55, 55);
	Color white = new Color(255, 255, 255);
	Color titleBackColor = new Color(188, 188, 188);
	Color tablegrey = new Color(120, 120, 120);
	MoneyPage moneyPage;
	PageInfo pageInfo = PageInfo.getInstance();

	MainPage mainPage;
	MainPage2 mainPage2;
	AccMgPage accMgPage;
	ChatPage chatPage;
	InputPage inputPage;
	RecordPage recordPage;
	RecordDetail recordDetail;
	RecordChart recordChart;

	public MoneyDesign(MoneyPage moneyPage) {
		this.moneyPage = moneyPage;

		mainPage2 = moneyPage.mainPage2;
		accMgPage = moneyPage.accMgPage;
		chatPage = moneyPage.chatPage;
		inputPage = moneyPage.inputPage;
		recordPage = moneyPage.recordPage;

	}

	public void setMainDesign() {
		mainPage = moneyPage.mainPage;
		// Design ////////////////////
		defaultImageMenu();
		mainPage.pnTitle.setBorder(new MatteBorder(0, 0, 1, 0, borderColor));
		mainPage.pnTitle.setBackground(titleBackColor);

		mainPage.pnMenuBar.setBorder(new MatteBorder(1, 0, 0, 0, borderColor));

		mainPage.title.setFont(new Font("Noto Sans KR Medium", Font.PLAIN, 18));
		mainPage.clockLabel.setFont(new Font("Noto Sans KR Medium", Font.PLAIN, 18));

		mainPage.mainTitle.setBorder(new LineBorder(borderColor, 2));
		mainPage.pnMain.setBorder(mainPage.mainTitle);

		mainPage.alarmBtn.setIcon(new ImageIcon("src/moneymoney/images/button/alarm_after.png"));

		mainPage.inputB.setBorderPainted(false);
		mainPage.inputB.setContentAreaFilled(false);
		mainPage.inputB.setFocusable(false);
		mainPage.inputB.setToolTipText("Input");
		mainPage.inputB.setCursor(new Cursor(Cursor.HAND_CURSOR));

		mainPage.recordB.setBorderPainted(false);
		mainPage.recordB.setContentAreaFilled(false);
		mainPage.recordB.setFocusable(false);
		mainPage.recordB.setToolTipText("Record");
		mainPage.recordB.setCursor(new Cursor(Cursor.HAND_CURSOR));

		mainPage.homeB.setBorderPainted(false);
		mainPage.homeB.setContentAreaFilled(false);
		mainPage.homeB.setFocusable(false);
		mainPage.homeB.setToolTipText("Home");
		mainPage.homeB.setCursor(new Cursor(Cursor.HAND_CURSOR));

		mainPage.accMgB.setBorderPainted(false);
		mainPage.accMgB.setContentAreaFilled(false);
		mainPage.accMgB.setFocusable(false);
		mainPage.accMgB.setToolTipText("Account");
		mainPage.accMgB.setCursor(new Cursor(Cursor.HAND_CURSOR));

		mainPage.chatB.setBorderPainted(false);
		mainPage.chatB.setContentAreaFilled(false);
		mainPage.chatB.setFocusable(false);
		mainPage.chatB.setToolTipText("Chat");
		mainPage.chatB.setCursor(new Cursor(Cursor.HAND_CURSOR));
		mainPage.alarmBtn.setBorderPainted(false);
		mainPage.alarmBtn.setContentAreaFilled(false);
		mainPage.alarmBtn.setFocusable(false);
		mainPage.alarmBtn.setToolTipText("알람 OFF");

		mainPage.loginF.setBorder(new MatteBorder(0, 0, 2, 0, borderColor));
		mainPage.loginF.setBackground(null);

		mainPage.pwF.setBorder(new MatteBorder(0, 0, 2, 0, borderColor));
		mainPage.pwF.setBackground(null);

		mainPage.loginB.setBackground(btnColor);
		mainPage.loginB.setForeground(white);
		mainPage.signUpB.setBackground(btnColor);
		mainPage.signUpB.setForeground(white);

		mainPage.idLabel.setHorizontalAlignment(JTextField.RIGHT);
		mainPage.pwLabel.setHorizontalAlignment(JTextField.RIGHT);
		// Font ////////////////////
		mainPage.idLabel.setFont(new Font("Noto Sans KR Medium", Font.PLAIN, 18));
		mainPage.loginF.setFont(new Font("Noto Sans KR Medium", Font.PLAIN, 18));
		mainPage.pwLabel.setFont(new Font("Noto Sans KR Medium", Font.PLAIN, 18));
		mainPage.mainTitle.setTitleFont(new Font("Noto Sans KR Medium", Font.PLAIN, 20));
		// mainPage.pwF.setFont(new Font("휴먼둥근헤드라인", 1, 16));
		mainPage.loginB.setFont(new Font("Noto Sans KR Medium", Font.PLAIN, 18));
		mainPage.signUpB.setFont(new Font("Noto Sans KR Medium", Font.PLAIN, 18));

		mainPage.clockLabel.setFont(new Font("Noto Sans KR Medium", Font.PLAIN, 14));
		// MainPage2 Design
		setMainDesign2();

		mainPage.inputB.addMouseListener(this);
		mainPage.recordB.addMouseListener(this);
		mainPage.homeB.addMouseListener(this);
		mainPage.accMgB.addMouseListener(this);
		mainPage.chatB.addMouseListener(this);
	}

	public void setMainDesign2() {
		mainPage2 = moneyPage.mainPage2;
		// Design
		mainPage2.amountTotal.setFont(new Font("Noto Sans KR Medium", Font.PLAIN, 18));
		mainPage2.amountFirst.setFont(new Font("Noto Sans KR Medium", Font.PLAIN, 16));
		mainPage2.amountSecond.setFont(new Font("Noto Sans KR Medium", Font.PLAIN, 16));
		mainPage2.amountThird.setFont(new Font("Noto Sans KR Medium", Font.PLAIN, 16));

		mainPage2.logoutB.setFont(new Font("Noto Sans KR Medium", Font.PLAIN, 16));
		mainPage2.logoutB.setBackground(btnColor);
		mainPage2.logoutB.setForeground(white);

		mainPage2.mainTitleBorder.setBorder(new LineBorder(borderColor, 2));
		mainPage2.mainTitleBorder.setTitleFont(new Font("Noto Sans KR Medium", Font.PLAIN, 20));

		mainPage2.amountTotBorder.setBorder(new LineBorder(borderColor, 2));
		mainPage2.amountTotBorder.setTitleFont(new Font("Noto Sans KR Medium", Font.PLAIN, 18));

	}

	public void setAccountMgrDesign() {
		accMgPage = moneyPage.accMgPage;
		// Design ////////////////////
		accMgPage.registerBtn.setIcon(new ImageIcon("src/moneymoney/images/button/add_button.png"));
		accMgPage.deleteBtn.setIcon(new ImageIcon("src/moneymoney/images/button/delete_button.png"));

		accMgPage.registerBtn.setBorderPainted(false);
		accMgPage.registerBtn.setContentAreaFilled(false);
		accMgPage.registerBtn.setFocusable(false);
		accMgPage.registerBtn.setToolTipText("Register");
		accMgPage.registerBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));

		accMgPage.deleteBtn.setBorderPainted(false);
		accMgPage.deleteBtn.setContentAreaFilled(false);
		accMgPage.deleteBtn.setFocusable(false);
		accMgPage.deleteBtn.setToolTipText("Delete");
		accMgPage.deleteBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));

		accMgPage.tf.setFont(new Font("Noto Sans KR Medium", Font.PLAIN, 16));
		accMgPage.bankBox.setFont(new Font("Noto Sans KR Medium", Font.PLAIN, 16));
		accMgPage.box.setFont(new Font("Noto Sans KR Medium", Font.PLAIN, 16));

		accMgPage.bankBox.setBackground(Color.LIGHT_GRAY);
		accMgPage.box.setBackground(Color.LIGHT_GRAY);

		accMgPage.tf.setBorder(new MatteBorder(0, 0, 2, 0, borderColor));
		accMgPage.tf.setBackground(null);
	}

	public void setInputDesign() {
		inputPage = moneyPage.inputPage;
		// Design ////////////////////
		inputPage.dateText.setBorder(new MatteBorder(0, 0, 2, 0, borderColor));
		inputPage.dateText.setBackground(null);

		inputPage.amountText.setBorder(new MatteBorder(0, 0, 2, 0, borderColor));
		inputPage.amountText.setBackground(null);

		inputPage.contentText.setBorder(new MatteBorder(0, 0, 2, 0, borderColor));
		inputPage.contentText.setBackground(null);
		// 글씨체,크기 변경
		inputPage.dateLabel.setFont(new Font("Noto Sans KR Medium", Font.PLAIN, 16));
		inputPage.dateText.setFont(new Font("Noto Sans KR Medium", Font.PLAIN, 16));

		inputPage.accountLabel.setFont(new Font("Noto Sans KR Medium", Font.PLAIN, 16));
		inputPage.accountCob.setFont(new Font("Noto Sans KR Medium", Font.PLAIN, 16));

		inputPage.amountLabel.setFont(new Font("Noto Sans KR Medium", Font.PLAIN, 16));
		inputPage.amountText.setFont(new Font("Noto Sans KR Medium", Font.PLAIN, 16));

		inputPage.categoryLabel.setFont(new Font("Noto Sans KR Medium", Font.PLAIN, 16));
		inputPage.categoryCob.setFont(new Font("Noto Sans KR Medium", Font.PLAIN, 16));

		inputPage.contentLabel.setFont(new Font("Noto Sans KR Medium", Font.PLAIN, 16));
		inputPage.contentText.setFont(new Font("Noto Sans KR Medium", Font.PLAIN, 16));

		inputPage.inputBtn.setFont(new Font("Noto Sans KR Medium", Font.PLAIN, 16));
		inputPage.depositBtn.setFont(new Font("Noto Sans KR Medium", Font.PLAIN, 16));
		inputPage.withdrawBtn.setFont(new Font("Noto Sans KR Medium", Font.PLAIN, 16));

		inputPage.accountCob.setBackground(Color.LIGHT_GRAY);
		inputPage.categoryCob.setBackground(Color.LIGHT_GRAY);

		inputPage.depositBtn.setBorderPainted(false);
		inputPage.depositBtn.setBackground(btnColor);
		inputPage.depositBtn.setForeground(white);
		inputPage.depositBtn.setFocusable(false);

		inputPage.withdrawBtn.setBorderPainted(false);
		inputPage.withdrawBtn.setBackground(btnColor);
		inputPage.withdrawBtn.setForeground(white);
		inputPage.withdrawBtn.setFocusable(false);

		inputPage.inputBtn.setBackground(btnColor);
		inputPage.inputBtn.setForeground(white);
		inputPage.inputBtn.setFocusable(false);
	}

	public void setRecordDesign() {
		recordPage = moneyPage.recordPage;

		// Design/////////////////

		// 테이블 디자인
		Border border = new javax.swing.border.LineBorder(tablegrey, 1);
		JTableHeader header = recordPage.table.getTableHeader();
		header.setBackground(tablegrey);
		header.setForeground(white);
		header.setReorderingAllowed(false);
		TableColumnModel columnModel = header.getColumnModel();

//		recordPage.weekBtn.setBorderPainted(false);
//		recordPage.monthBtn.setBorderPainted(false);
//		recordPage.semiAnnualBtn.setBorderPainted(false);

		recordPage.monthBtn.setBackground(btnColor);
		recordPage.weekBtn.setBackground(btnColor);
		recordPage.semiAnnualBtn.setBackground(btnColor);
		recordPage.detailBtn.setBackground(btnColor);
		recordPage.tongBtn.setBackground(btnColor);

		recordPage.monthBtn.setFocusable(false);
		recordPage.weekBtn.setFocusable(false);
		recordPage.semiAnnualBtn.setFocusable(false);
		recordPage.detailBtn.setFocusable(false);
		recordPage.tongBtn.setFocusable(false);

		recordPage.monthBtn.setForeground(white);
		recordPage.weekBtn.setForeground(white);
		recordPage.semiAnnualBtn.setForeground(white);
		recordPage.detailBtn.setForeground(white);
		recordPage.tongBtn.setForeground(white);

		recordPage.weekBtn.setFont(new Font("Noto Sans KR Medium", Font.PLAIN, 18));
		recordPage.monthBtn.setFont(new Font("Noto Sans KR Medium", Font.PLAIN, 18));
		recordPage.semiAnnualBtn.setFont(new Font("Noto Sans KR Medium", Font.PLAIN, 18));
		recordPage.tongBtn.setFont(new Font("Noto Sans KR Medium", Font.PLAIN, 18));
		recordPage.detailBtn.setFont(new Font("Noto Sans KR Medium", Font.PLAIN, 18));

		recordPage.totalTxt.setFont(new Font("Noto Sans KR Medium", Font.PLAIN, 20));
		recordPage.sumTxt.setFont(new Font("Noto Sans KR Medium", Font.PLAIN, 20));
		recordPage.table.setFont(new Font("Noto Sans KR Medium", Font.PLAIN, 14));
		header.setFont(new Font("Noto Sans KR Medium", Font.PLAIN, 18));
	}

	public void setChatDesign() {
		chatPage = moneyPage.chatPage;
		// Design ////////////////////

	}

	public void defaultImageMenu() {
		mainPage.inputB.setIcon(new ImageIcon("src/moneymoney/images/menuBar/input_before.png"));
		mainPage.recordB.setIcon(new ImageIcon("src/moneymoney/images/menuBar/record_before.png"));
		mainPage.homeB.setIcon(new ImageIcon("src/moneymoney/images/menuBar/home_before.png"));
		mainPage.accMgB.setIcon(new ImageIcon("src/moneymoney/images/menuBar/accountMgr_before.png"));
		mainPage.chatB.setIcon(new ImageIcon("src/moneymoney/images/menuBar/chat_before.png"));
	}

	public void changeNowMenu() {
		defaultImageMenu();
		if (pageInfo.getPageNum() == 1) {
			mainPage.inputB.setIcon(new ImageIcon("src/moneymoney/images/menuBar/input_after.png"));
		} else if (pageInfo.getPageNum() == 2) {
			mainPage.recordB.setIcon(new ImageIcon("src/moneymoney/images/menuBar/record_after.png"));
		} else if (pageInfo.getPageNum() == 3) {
			mainPage.homeB.setIcon(new ImageIcon("src/moneymoney/images/menuBar/home_after.png"));
		} else if (pageInfo.getPageNum() == 4) {
			mainPage.accMgB.setIcon(new ImageIcon("src/moneymoney/images/menuBar/accountMgr_after.png"));
		} else if (pageInfo.getPageNum() == 5) {
			mainPage.chatB.setIcon(new ImageIcon("src/moneymoney/images/menuBar/chat_after.png"));
		}
	}

	public void setRecordDetailDesign() {
		recordDetail = recordPage.recordDetail;
		// Design
		recordDetail.incomeBtn.setFont(new Font("Noto Sans KR Medium", Font.PLAIN, 18));
		recordDetail.expensesBtn.setFont(new Font("Noto Sans KR Medium", Font.PLAIN, 18));
		recordDetail.exCate1.setFont(new Font("Noto Sans KR Medium", Font.PLAIN, 14));
		recordDetail.exCate2.setFont(new Font("Noto Sans KR Medium", Font.PLAIN, 14));
		recordDetail.exCate3.setFont(new Font("Noto Sans KR Medium", Font.PLAIN, 14));
		recordDetail.exCate4.setFont(new Font("Noto Sans KR Medium", Font.PLAIN, 14));
		recordDetail.exCate5.setFont(new Font("Noto Sans KR Medium", Font.PLAIN, 14));
		recordDetail.exCate6.setFont(new Font("Noto Sans KR Medium", Font.PLAIN, 14));
		recordDetail.exCate7.setFont(new Font("Noto Sans KR Medium", Font.PLAIN, 14));
		recordDetail.exCate8.setFont(new Font("Noto Sans KR Medium", Font.PLAIN, 14));
		recordDetail.exCate9.setFont(new Font("Noto Sans KR Medium", Font.PLAIN, 14));
		recordDetail.exCate10.setFont(new Font("Noto Sans KR Medium", Font.PLAIN, 14));
		recordDetail.exCate11.setFont(new Font("Noto Sans KR Medium", Font.PLAIN, 14));
		recordDetail.exCate12.setFont(new Font("Noto Sans KR Medium", Font.PLAIN, 14));

		recordDetail.inCate1.setFont(new Font("Noto Sans KR Medium", Font.PLAIN, 14));
		recordDetail.inCate2.setFont(new Font("Noto Sans KR Medium", Font.PLAIN, 14));
		recordDetail.inCate3.setFont(new Font("Noto Sans KR Medium", Font.PLAIN, 14));
		recordDetail.inCate4.setFont(new Font("Noto Sans KR Medium", Font.PLAIN, 14));
		recordDetail.inCate5.setFont(new Font("Noto Sans KR Medium", Font.PLAIN, 14));
		recordDetail.inCate6.setFont(new Font("Noto Sans KR Medium", Font.PLAIN, 14));

		recordDetail.incomeBtn.setBackground(btnColor);
		recordDetail.incomeBtn.setForeground(white);
		recordDetail.incomeBtn.setFocusable(false);
		recordDetail.expensesBtn.setBackground(btnColor);
		recordDetail.expensesBtn.setForeground(white);
		recordDetail.expensesBtn.setFocusable(false);

		recordDetail.exCate1.setBackground(btnColor);
		recordDetail.exCate1.setForeground(white);
		recordDetail.exCate1.setFocusable(false);

		recordDetail.exCate2.setBackground(btnColor);
		recordDetail.exCate2.setForeground(white);
		recordDetail.exCate2.setFocusable(false);

		recordDetail.exCate3.setBackground(btnColor);
		recordDetail.exCate3.setForeground(white);
		recordDetail.exCate3.setFocusable(false);

		recordDetail.exCate4.setBackground(btnColor);
		recordDetail.exCate4.setForeground(white);
		recordDetail.exCate4.setFocusable(false);

		recordDetail.exCate5.setBackground(btnColor);
		recordDetail.exCate5.setForeground(white);
		recordDetail.exCate5.setFocusable(false);

		recordDetail.exCate6.setBackground(btnColor);
		recordDetail.exCate6.setForeground(white);
		recordDetail.exCate6.setFocusable(false);

		recordDetail.exCate7.setBackground(btnColor);
		recordDetail.exCate7.setForeground(white);
		recordDetail.exCate7.setFocusable(false);

		recordDetail.exCate8.setBackground(btnColor);
		recordDetail.exCate8.setForeground(white);
		recordDetail.exCate8.setFocusable(false);

		recordDetail.exCate9.setBackground(btnColor);
		recordDetail.exCate9.setForeground(white);
		recordDetail.exCate9.setFocusable(false);

		recordDetail.exCate10.setBackground(btnColor);
		recordDetail.exCate10.setForeground(white);
		recordDetail.exCate10.setFocusable(false);

		recordDetail.exCate11.setBackground(btnColor);
		recordDetail.exCate11.setForeground(white);
		recordDetail.exCate11.setFocusable(false);

		recordDetail.exCate12.setBackground(btnColor);
		recordDetail.exCate12.setForeground(white);
		recordDetail.exCate12.setFocusable(false);

		recordDetail.inCate1.setBackground(btnColor);
		recordDetail.inCate1.setForeground(white);
		recordDetail.inCate1.setFocusable(false);

		recordDetail.inCate2.setBackground(btnColor);
		recordDetail.inCate2.setForeground(white);
		recordDetail.inCate2.setFocusable(false);

		recordDetail.inCate3.setBackground(btnColor);
		recordDetail.inCate3.setForeground(white);
		recordDetail.inCate3.setFocusable(false);

		recordDetail.inCate4.setBackground(btnColor);
		recordDetail.inCate4.setForeground(white);
		recordDetail.inCate4.setFocusable(false);

		recordDetail.inCate5.setBackground(btnColor);
		recordDetail.inCate5.setForeground(white);
		recordDetail.inCate5.setFocusable(false);

		recordDetail.inCate6.setBackground(btnColor);
		recordDetail.inCate6.setForeground(white);
		recordDetail.inCate6.setFocusable(false);
	}

	public void setRecordChartDesign() {
		recordChart = recordPage.recordChart;

		recordChart.incomeChtBtn.setFont(new Font("Noto Sans KR Medium", Font.PLAIN, 18));
		recordChart.expenseChtBtn.setFont(new Font("Noto Sans KR Medium", Font.PLAIN, 18));

		recordChart.incomeChtBtn.setBackground(btnColor);
		recordChart.incomeChtBtn.setForeground(white);
		recordChart.incomeChtBtn.setFocusable(false);

		recordChart.expenseChtBtn.setBackground(btnColor);
		recordChart.expenseChtBtn.setForeground(white);
		recordChart.expenseChtBtn.setFocusable(false);

		recordChart.contentPane.setBackground(Color.white);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		Object obj = e.getSource();
		if (obj == mainPage.inputB) {
			if (pageInfo.getPageNum() != 1)
				mainPage.inputB.setIcon(new ImageIcon("src/moneymoney/images/menuBar/input_after.png"));
		} else if (obj == mainPage.recordB) {
			if (pageInfo.getPageNum() != 2)
				mainPage.recordB.setIcon(new ImageIcon("src/moneymoney/images/menuBar/record_after.png"));
		} else if (obj == mainPage.homeB) {
			if (pageInfo.getPageNum() != 3)
				mainPage.homeB.setIcon(new ImageIcon("src/moneymoney/images/menuBar/home_after.png"));
		} else if (obj == mainPage.accMgB) {
			if (pageInfo.getPageNum() != 4)
				mainPage.accMgB.setIcon(new ImageIcon("src/moneymoney/images/menuBar/accountMgr_after.png"));
		} else if (obj == mainPage.chatB) {
			if (pageInfo.getPageNum() != 5)
				mainPage.chatB.setIcon(new ImageIcon("src/moneymoney/images/menuBar/chat_after.png"));
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		Object obj = e.getSource();
		if (obj == mainPage.inputB) {
			if (pageInfo.getPageNum() != 1)
				mainPage.inputB.setIcon(new ImageIcon("src/moneymoney/images/menuBar/input_before.png"));
		} else if (obj == mainPage.recordB) {
			if (pageInfo.getPageNum() != 2)
				mainPage.recordB.setIcon(new ImageIcon("src/moneymoney/images/menuBar/record_before.png"));
		} else if (obj == mainPage.homeB) {
			if (pageInfo.getPageNum() != 3)
				mainPage.homeB.setIcon(new ImageIcon("src/moneymoney/images/menuBar/home_before.png"));
		} else if (obj == mainPage.accMgB) {
			if (pageInfo.getPageNum() != 4)
				mainPage.accMgB.setIcon(new ImageIcon("src/moneymoney/images/menuBar/accountMgr_before.png"));
		} else if (obj == mainPage.chatB) {
			if (pageInfo.getPageNum() != 5)
				mainPage.chatB.setIcon(new ImageIcon("src/moneymoney/images/menuBar/chat_before.png"));
		}

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}
}
