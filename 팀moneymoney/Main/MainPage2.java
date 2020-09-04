package moneymoney.Main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class MainPage2 extends JPanel {
	
	public JPanel pnMain;
	public JButton logoutB;
	
	public JTextField amountTotal, amountFirst, amountSecond, amountThird;
	public JPanel pnOutline;
	
	public TitledBorder mainTitleBorder = new TitledBorder("계좌정보");
	public TitledBorder amountTotBorder = new TitledBorder("총 잔액");
	public TitledBorder amountFirstBorder, amountSecondBorder, amountThirdBorder;
	
	public MainPage2() {
		
		amountTotal = new JTextField(20);
		amountTotal.setEditable(false);
		amountTotal.setBounds(80, 40, 250, 70);
		amountTotal.setBorder(amountTotBorder);
		amountTotal.setHorizontalAlignment(4);
//		amountTotal.add(Box.createRigidArea(new Dimension(100, 20)));
		
		amountFirst = new JTextField(20);
		amountFirst.setEditable(false);
		amountFirst.setBounds(80, 140, 250, 60);
		amountFirst.setHorizontalAlignment(4);
		amountFirst.setBorder(new LineBorder(Color.black, 2));
//		amountTotal.add(Box.createRigidArea(new Dimension(80, 20)));
		
		amountSecond = new JTextField(20);
		amountSecond.setEditable(false);
		amountSecond.setBounds(80, 220, 250, 60);
		amountSecond.setHorizontalAlignment(4);
		amountSecond.setBorder(new LineBorder(Color.black, 2));
//		amountTotal.add(Box.createRigidArea(new Dimension(80, 20)));
		
		amountThird = new JTextField(20);
		amountThird.setEditable(false);
		amountThird.setBounds(80, 300, 250, 60);
		amountThird.setHorizontalAlignment(4);
		amountThird.setBorder(new LineBorder(Color.black, 2));
//		amountTotal.add(Box.createRigidArea(new Dimension(80, 20)));
		
		logoutB = new JButton("로그아웃");
		logoutB.setBounds(270, 370, 150, 40);
		
		pnMain = new JPanel(null);
		pnMain.setBounds(25, 25, 440, 420);
		pnMain.setBorder(mainTitleBorder);
		
		pnMain.add(amountTotal);
		pnMain.add(amountFirst);
		pnMain.add(amountSecond);
		pnMain.add(amountThird);
		pnMain.add(logoutB);
		
		pnOutline = new JPanel(null);
		pnOutline.add(pnMain);
		
		add(BorderLayout.CENTER, pnOutline);
		
		setSize(500, 600);
		setVisible(true);
		
	} // end MainPage2()

} // end MainPage2
