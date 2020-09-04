package moneymoney.Main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;

public class MainPage extends JFrame {

	public JButton inputB, recordB;
	public JButton accMgB;
	public JButton chatB;
	public JButton homeB;
	public JPanel pnMenuBar;

	public JLabel title;
	public JLabel idLabel = new JLabel();
	public JLabel pwLabel = new JLabel();
	public JButton loginB, signUpB;
	public JToggleButton alarmBtn;
	public JTextField loginF;
	public JPasswordField pwF;
	public JPanel pnMain, pnTitle;
	public JPanel pnOutline;
	public JLabel clockLabel;

	public MainPage2 mainPage2 = new MainPage2();

	public JButton logoutB = mainPage2.logoutB;
	public TitledBorder mainTitle = new TitledBorder("메인화면");

	public MainPage() {

		inputB = new JButton();
		recordB = new JButton();
		accMgB = new JButton();
		chatB = new JButton();
		homeB = new JButton();

		inputB.setPreferredSize(new Dimension(100, 80));
		recordB.setPreferredSize(new Dimension(100, 80));
		accMgB.setPreferredSize(new Dimension(100, 80));
		chatB.setPreferredSize(new Dimension(100, 80));
		homeB.setPreferredSize(new Dimension(100, 80));

		pnMenuBar = new JPanel();
		pnMenuBar.setLayout(new GridLayout(1, 5));
		pnMenuBar.add(inputB);
		pnMenuBar.add(recordB);
		pnMenuBar.add(homeB);
		pnMenuBar.add(accMgB);
		pnMenuBar.add(chatB);

		add(BorderLayout.SOUTH, pnMenuBar);

		// MenuBar End /////////////////////////////////////

		loginF = new JTextField(30);

		pwF = new JPasswordField(30);

		idLabel.setText("ID : ");

		pwLabel.setText("Password : ");

		clockLabel = new JLabel();

		loginB = new JButton("로그인");

		signUpB = new JButton("회원가입");

		pnMain = new JPanel(null);
		pnMain.setBounds(25, 25, 440, 420);

		alarmBtn = new JToggleButton();
		alarmBtn.setSelected(true);

		title = new JLabel("Money Money");
		title.setHorizontalAlignment(JLabel.CENTER);

		pnTitle = new JPanel(new BorderLayout());
		pnTitle.add(BorderLayout.CENTER, title);
		pnTitle.add(BorderLayout.EAST, alarmBtn);
		pnTitle.add(BorderLayout.WEST, clockLabel);

		add(BorderLayout.NORTH, pnTitle);

		// 크기 및 위치 ////////////////
		idLabel.setBounds(0, 100, 150, 30);
		loginF.setBounds(160, 100, 200, 30);
		pwLabel.setBounds(0, 180, 150, 30);
		pwF.setBounds(160, 180, 200, 30);
		loginB.setBounds(50, 300, 150, 50);
		signUpB.setBounds(250, 300, 150, 50);

		//////////////////////////

		pnMain.add(loginF);
		pnMain.add(idLabel);
		pnMain.add(pwF);
		pnMain.add(pwLabel);
		pnMain.add(loginB);
		pnMain.add(signUpB);

		pnOutline = new JPanel(null);
		pnOutline.add(pnMain);

		// pnOutline.add(new MainPage2().pnMain);

		add(BorderLayout.CENTER, pnOutline);
		setTitle("MoneyMoney");
		setSize(500, 610);
		setVisible(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setIconImage(new ImageIcon("src/moneymoney/images/button/main_icon.png").getImage());

	} // end MainPage()

	public void loginSuccess() {

		pnOutline.remove(pnMain);
		pnOutline.add(mainPage2.pnMain);
		pnOutline.updateUI();

	} // end loginSuccess()

	public void logoutSuccess() {

		pnOutline.removeAll();
		pnOutline.add(pnMain);
		pnOutline.updateUI();
		mainPage2.amountTotal.setText("");
		mainPage2.amountFirst.setText("");
		mainPage2.amountSecond.setText("");
		mainPage2.amountThird.setText("");
		
		mainPage2.amountFirst.setVisible(false);
		mainPage2.amountSecond.setVisible(false);
		mainPage2.amountThird.setVisible(false);
		

	} // end logoutSuccess()

} // end MainPage
