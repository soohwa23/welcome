package moneymoney.Input;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToggleButton;

public class InputPage extends JPanel {

	public JLabel dateLabel, categoryLabel, amountLabel, contentLabel;
	public JTextField dateText;
	public JLabel accountLabel;
	public JTextField amountText;
	public JTextField contentText;
	public JPanel mainPn;
	public JComboBox<String> accountCob, categoryCob;
	public JButton inputBtn;
	public JToggleButton depositBtn, withdrawBtn;

	public InputPage() {

		dateLabel = new JLabel("날짜");

		dateText = new JTextField("YYYY-MM-DD");
		dateText.setForeground(Color.gray);
		dateText.setColumns(10);

		accountLabel = new JLabel("계좌");

		accountCob = new JComboBox<String>();

		categoryLabel = new JLabel("분류");

		categoryCob = new JComboBox<String>();

		amountLabel = new JLabel("금액");

		amountText = new JTextField();
		amountText.setColumns(10);

		contentLabel = new JLabel("내용");

		contentText = new JTextField();
		contentText.setColumns(10);

		inputBtn = new JButton("입력하기");

		depositBtn = new JToggleButton("입금");
		depositBtn.setSelected(true);

		withdrawBtn = new JToggleButton("출금");

		ButtonGroup inOutSelect = new ButtonGroup();
		inOutSelect.add(depositBtn);
		inOutSelect.add(withdrawBtn);

		mainPn = new JPanel();
		mainPn.setLayout(null);
		mainPn.setSize(500, 600);

		// 크기 및 위치 지정 ///////////////////////////////////
		depositBtn.setBounds(0, 0, 250, 50);
		withdrawBtn.setBounds(250, 0, 250, 50);

		dateLabel.setBounds(100, 100, 50, 25);
		dateText.setBounds(150, 100, 200, 25);

		accountLabel.setBounds(100, 150, 50, 25);
		accountCob.setBounds(150, 150, 200, 25);

		categoryLabel.setBounds(100, 200, 50, 25);
		categoryCob.setBounds(150, 200, 200, 25);

		amountLabel.setBounds(100, 250, 50, 25);
		amountText.setBounds(150, 250, 200, 25);

		contentLabel.setBounds(100, 300, 50, 25);
		contentText.setBounds(150, 300, 200, 25);

		inputBtn.setBounds(100, 350, 250, 50);
		
		// 크기 및 위치 지정 ///////////////////////////////////

		mainPn.add(depositBtn);
		mainPn.add(withdrawBtn);
		mainPn.add(dateLabel);
		mainPn.add(dateText);
		mainPn.add(accountLabel);
		mainPn.add(accountCob);
		mainPn.add(categoryLabel);
		mainPn.add(categoryCob);
		mainPn.add(amountLabel);
		mainPn.add(amountText);
		mainPn.add(contentLabel);
		mainPn.add(contentText);
		mainPn.add(inputBtn);

		add(mainPn);
		setLayout(null);
		setSize(500, 600);
		setVisible(true);
	}

}
