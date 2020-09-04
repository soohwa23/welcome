package moneymoney;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java0428_inheritance.prob.card.Main;
import moneymoney.AccountMgr.AccMgLogic;
import moneymoney.AccountMgr.AccMgPage;
import moneymoney.Chatting.ChatClient;
import moneymoney.Chatting.ChatPage;
import moneymoney.Chatting.ChatServer;
import moneymoney.Input.InputLogic;
import moneymoney.Input.InputPage;
import moneymoney.Main.MainLogic;
import moneymoney.Main.MainPage;
import moneymoney.Main.MainPage2;
import moneymoney.Record.RecordDetail;
import moneymoney.Record.RecordPage;

public class MoneyPage implements ActionListener {

	public UserInfo userInfo = UserInfo.getInstance();
	public PageInfo pageInfo = PageInfo.getInstance();

	public static MoneyPage moneyPage;
	public MainLogic main;
	public MainPage mainPage;
	public MainPage2 mainPage2;
	public AccMgPage accMgPage;
	public AccMgLogic accMg;
	public ChatClient chatClient;
	public ChatPage chatPage;
	public InputPage inputPage;
	public InputLogic input;
	public RecordPage recordPage;
	public MoneyDesign moneyDesign;
	public RecordDetail recordDetail;

	public static void main(String[] args) {
		moneyPage = new MoneyPage();
		moneyPage.init();
	}

	public void init() {
		///// Loding ////////////////////////////////
//		JFrame frame = new JFrame();
//		frame.setSize(500,600);
//		frame.setVisible(true);
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		frame.setLocationRelativeTo(null);
//		
//		Image img = new ImageIcon("src/moneymoney_ver2/images/home_after.png").getImage();
//		frame.setContentPane(new JPanel() {
//			@Override
//			protected void paintComponent(Graphics g) {
//				g.drawImage(img, 150, 100 , null);
//			}
//		});
//		
//		try {
//			Thread.sleep(2000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//
//		frame.dispose();
		///// Loding ////////////////////////////////

		moneyDesign = new MoneyDesign(this);

		// Main //////////
		main = new MainLogic();
		mainPage = main.mp;
		mainPage2 = main.mp.mainPage2;
		pageInfo.setPageNum(3);
		mainPage.homeB.setIcon(new ImageIcon("src/moneymoney_ver2/images/home_after.png"));
		pageInfo.setAlarm(1);

		// Event //////////
		mainPage.logoutB.addActionListener(this);
		mainPage.accMgB.addActionListener(this);
		mainPage.homeB.addActionListener(this);
		mainPage.chatB.addActionListener(this);
		mainPage.inputB.addActionListener(this);
		mainPage.recordB.addActionListener(this);

		moneyDesign.setMainDesign();
		moneyDesign.changeNowMenu();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if (userInfo.getUserId() == null) {
			JOptionPane.showMessageDialog(mainPage, "로그인을 해주세요!");
			return;
		} else {
			// 각 페이지 초기화 //////////
			if (accMg == null) {
				accMg = new AccMgLogic();
				accMgPage = accMg.acc;
			}
			if (input == null) {
				input = new InputLogic(this);
				inputPage = input.inputPage;
			}
			if (recordPage == null) {
				recordPage = new RecordPage(this);
			}
		}
		if (obj == mainPage.accMgB) {
			mainPage.pnOutline.removeAll();
			mainPage.pnOutline.add(accMgPage);
			mainPage.pnOutline.updateUI();
			pageInfo.setPageNum(4);
			moneyDesign.setAccountMgrDesign();
			moneyDesign.changeNowMenu();
		} else if (obj == mainPage.homeB) {
			mainPage.pnOutline.removeAll();
			main.showAccountBal();
			mainPage.loginSuccess();
			pageInfo.setPageNum(3);
			moneyDesign.changeNowMenu();
		} else if (obj == mainPage.chatB) {
			if (chatClient == null) {
				chatClient = new ChatClient("172.16.5.28", 7777, this);
				chatPage = chatClient.chatPage;
			}
			changeChatPage();

		} else if (obj == mainPage.inputB) {
			if (input == null) {
			} else {
				input.setAccountNo();
			}
			mainPage.pnOutline.removeAll();
			mainPage.pnOutline.add(inputPage);
			mainPage.pnOutline.updateUI();
			pageInfo.setPageNum(1);
			moneyDesign.setInputDesign();
			moneyDesign.changeNowMenu();
		} else if (obj == mainPage.recordB) {
			mainPage.pnOutline.removeAll();
			mainPage.pnOutline.add(recordPage);
			recordPage.setWholeData();
			mainPage.pnOutline.updateUI();
			pageInfo.setPageNum(2);
			moneyDesign.setRecordDesign();
			moneyDesign.changeNowMenu();
		} else if (obj == mainPage.logoutB) {
			if (chatClient != null)
				chatClient.stop();
			accMg = null;
			accMgPage = null;
			chatClient = null;
			chatPage = null;
			input = null;
			inputPage = null;
			recordPage = null;
		} 
	}

	public void changeChatPage() {
		mainPage.pnOutline.removeAll();
		mainPage.pnOutline.add(chatPage);
		mainPage.pnOutline.updateUI();
		pageInfo.setPageNum(5);
		moneyDesign.changeNowMenu();
	}

}
