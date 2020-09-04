package moneymoney.AccountMgr;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AccMgPage extends JPanel {
	
	public JPanel p1;
	public JButton registerBtn, deleteBtn;
	public JTextField tf;
	public JComboBox box, bankBox;
	public String[] bankName = {"국민은행", "우리은행", "신한은행", "기업은행", "NH농협", "카카오뱅크" };
	public JButton inputB, recordB, accMgB, chatB;
	public JPanel pnMenuBar;
	
	public AccMgPage() {
		
		inputB = new JButton("입력");
		inputB.setFont(inputB.getFont().deriveFont(16.0f));
		
		recordB = new JButton("내역");
		recordB.setFont(recordB.getFont().deriveFont(16.0f));
		
		accMgB = new JButton("계좌 관리");
		accMgB.setFont(accMgB.getFont().deriveFont(16.0f));
		
		chatB = new JButton("채팅");
		chatB.setFont(chatB.getFont().deriveFont(16.0f));
		
		inputB.setPreferredSize(new Dimension(125, 100));
		recordB.setPreferredSize(new Dimension(125, 100));
		accMgB.setPreferredSize(new Dimension(125, 100));
		chatB.setPreferredSize(new Dimension(125, 100));
		
//		pnMenuBar = new JPanel();
//		pnMenuBar.setLayout(new GridLayout(1, 4));
//		pnMenuBar.add(inputB);
//		pnMenuBar.add(recordB);
//		pnMenuBar.add(accMgB);
//		pnMenuBar.add(chatB);
		
//		add(BorderLayout.SOUTH, pnMenuBar);
		
		setLayout(null);
		
		p1 = new JPanel(null);
		
		
		registerBtn = new JButton();
		deleteBtn = new JButton();
	
		tf = new JTextField();
		tf.setText("계좌번호를 입력하세요.");
		tf.setForeground(Color.gray);
		box = new JComboBox<String>();
			
		bankBox = new JComboBox<String>(bankName);
		
		p1.setBounds(0, 0, 550, 450);
		
		registerBtn.setBounds(350, 50, 100, 100);
		deleteBtn.setBounds(350, 180, 100, 100);
		
		p1.add(registerBtn);
		p1.add(deleteBtn);
		
		tf.setBounds(30, 80, 220, 50);
		bankBox.setBounds(250, 80, 100, 50);
		
		p1.add(tf);
		p1.add(bankBox);
		
		box.setBounds(30, 210, 320, 50);
		
		p1.add(box);
		
		add(p1);
		
//		add(BorderLayout.SOUTH, pnMenuBar);
		
		setBounds(0, 0, 600, 500);
		setSize(500, 600);
		setVisible(true);
		//setDefaultCloseOperation(EXIT_ON_CLOSE);

	}
		
//	public static void main(String[] args) {
//		AccMgPage acc = new AccMgPage();
//		
//	}//end main()
	
}// end class
