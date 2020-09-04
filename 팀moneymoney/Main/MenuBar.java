package moneymoney.Main;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MenuBar extends JFrame{

	JButton inputB, recordB, accMgB, chatB;
	JPanel pnMenuBar;
	
	public MenuBar() {
		
		inputB = new JButton("입력");
		recordB = new JButton("내역");
		accMgB = new JButton("계좌 관리");
		chatB = new JButton("채팅");
		
		inputB.setPreferredSize(new Dimension(125, 100));
		recordB.setPreferredSize(new Dimension(125, 100));
		accMgB.setPreferredSize(new Dimension(125, 100));
		chatB.setPreferredSize(new Dimension(125, 100));
		
		pnMenuBar = new JPanel();
		pnMenuBar.setLayout(new GridLayout(1, 4));
		pnMenuBar.add(inputB);
		pnMenuBar.add(recordB);
		pnMenuBar.add(accMgB);
		pnMenuBar.add(chatB);
		
		add(BorderLayout.SOUTH, pnMenuBar);
		
		setSize(500, 600);
		setVisible(true);
		addWindowListener(new WindowAdapter() {
			
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
			
		});
	
	} // end MenuBar()
	
} // end class
