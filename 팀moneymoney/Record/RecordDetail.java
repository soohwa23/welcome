package moneymoney.Record;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JToggleButton;

public class RecordDetail extends JFrame implements ActionListener {

	public JLabel searchPeriod, searchType, dash;
	public 	JLabel incomeL, expensesL;
	public JTextArea fromDate, toDate;
	public JToggleButton incomeBtn, expensesBtn;
	public JButton inCate1, inCate2, inCate3, inCate4, inCate5, inCate6;
	public 	JButton exCate1, exCate2, exCate3, exCate4, exCate5, exCate6, exCate7, exCate8, exCate9, exCate10, exCate11,
			exCate12;
	public RecordPage recordPage;
	public JPanel top1, RD2, RD3, RD4;

	public RecordDetail(RecordPage recordPage) {
		this.recordPage = recordPage;
		dash = new JLabel("-");
		searchPeriod = new JLabel("조회기간");
		fromDate = new JTextArea();
		toDate = new JTextArea();
//		searchType = new JLabel("수입 지출유형");
		incomeBtn = new JToggleButton("입금");
		expensesBtn = new JToggleButton("출금");

		ButtonGroup inOutAmountBtn = new ButtonGroup();
		inOutAmountBtn.add(incomeBtn);
		inOutAmountBtn.add(expensesBtn);

		incomeL = new JLabel("수입");
		expensesL = new JLabel("지출");
		inCate1 = new JButton("월급");
		inCate2 = new JButton("부수입");
		inCate3 = new JButton("용돈");
		inCate4 = new JButton("상여");
		inCate5 = new JButton("금융수익");
		inCate6 = new JButton("기타");
		exCate1 = new JButton("식비");
		exCate2 = new JButton("교통/차량");
		exCate3 = new JButton("문화생활");
		exCate4 = new JButton("마트/편의점");
		exCate5 = new JButton("패션/미용");
		exCate6 = new JButton("생활용품");
		exCate7 = new JButton("주거/통신");
		exCate8 = new JButton("건강");
		exCate9 = new JButton("교육");
		exCate10 = new JButton("경조사/회비");
		exCate11 = new JButton("부모님");
		exCate12 = new JButton("기타");

		RD2 = new JPanel(new FlowLayout());
//		RD2.add(searchType);
		RD2.add(incomeBtn);
		RD2.add(expensesBtn);
		// 위치지정
//		searchType.setBounds(10, 50, 100, 20);
		RD2.setBounds(0, 0, 330, 80);
		
		incomeBtn.setPreferredSize(new Dimension(150, 50));
		expensesBtn.setPreferredSize(new Dimension(150, 50));
		
		RD3 = new JPanel(new GridLayout(2,3));
		//RD3.add(incomeL);
		RD3.add(inCate1);
		RD3.add(inCate2);
		RD3.add(inCate3);
		RD3.add(inCate4);
		RD3.add(inCate5);
		RD3.add(inCate6);
		// 위치지정
//		incomeL.setBounds(20, 0, 100, 20);
//		inCate1.setBounds(5, 35, 80, 30);
//		inCate2.setBounds(85, 35, 80, 30);
//		inCate3.setBounds(165, 35, 80, 30);
//		inCate4.setBounds(245, 35, 80, 30);
//		inCate5.setBounds(5, 65, 80, 30);
//		inCate6.setBounds(85, 65, 80, 30);

		RD4 = new JPanel(new GridLayout(4,3));
		
//		RD4.add(expensesL);
		RD4.add(exCate1);
		RD4.add(exCate2);
		RD4.add(exCate3);
		RD4.add(exCate4);
		RD4.add(exCate5);
		RD4.add(exCate6);
		RD4.add(exCate7);
		RD4.add(exCate8);
		RD4.add(exCate9);
		RD4.add(exCate10);
		RD4.add(exCate11);
		RD4.add(exCate12);
		// 위치지정
//		expensesL.setBounds(20, 0, 100, 20);
//		exCate1.setBounds(5, 35, 80, 30);
//		exCate2.setBounds(85, 35, 80, 30);
//		exCate3.setBounds(165, 35, 80, 30);
//		exCate4.setBounds(245, 35, 80, 30);
//		exCate5.setBounds(5, 65, 80, 30);
//		exCate6.setBounds(85, 65, 80, 30);
//		exCate7.setBounds(165, 65, 80, 30);
//		exCate8.setBounds(245, 65, 80, 30);
//		exCate9.setBounds(5, 95, 80, 30);
//		exCate10.setBounds(85, 95, 80, 30);
//		exCate11.setBounds(165, 95, 80, 30);
//		exCate12.setBounds(245, 95, 80, 30);

		RD3.setBounds(0, 100, 335, 120);
		RD4.setBounds(0, 100, 335, 235);
		top1 = new JPanel(null);
		top1.add(RD2);
		
		add(top1);
		setSize(350, 370);
		setVisible(true);

		incomeBtn.addActionListener(this);
		expensesBtn.addActionListener(this);
		exCate1.addActionListener(this);
		exCate2.addActionListener(this);
		exCate3.addActionListener(this);
		exCate4.addActionListener(this);
		exCate5.addActionListener(this);
		exCate6.addActionListener(this);
		exCate7.addActionListener(this);
		exCate8.addActionListener(this);
		exCate9.addActionListener(this);
		exCate10.addActionListener(this);
		exCate11.addActionListener(this);
		exCate12.addActionListener(this);
		inCate1.addActionListener(this);
		inCate2.addActionListener(this);
		inCate3.addActionListener(this);
		inCate4.addActionListener(this);
		inCate5.addActionListener(this);
		inCate6.addActionListener(this);

		incomeBtn.setSelected(true);
		setIncomeUI();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if (obj == exCate1) {
			categoryFilter("출금", "식비");
		} else if (obj == exCate2) {
			categoryFilter("출금", "교통/차량");
		} else if (obj == exCate3) {
			categoryFilter("출금", "문화생활");
		} else if (obj == exCate4) {
			categoryFilter("출금", "마트/편의점");
		} else if (obj == exCate5) {
			categoryFilter("출금", "패션/미용");
		} else if (obj == exCate6) {
			categoryFilter("출금", "생활용품");
		} else if (obj == exCate7) {
			categoryFilter("출금", "주거/통신");
		} else if (obj == exCate8) {
			categoryFilter("출금", "건강");
		} else if (obj == exCate9) {
			categoryFilter("출금", "교육");
		} else if (obj == exCate10) {
			categoryFilter("출금", "경조사/회비");
		} else if (obj == exCate11) {
			categoryFilter("출금", "부모님");
		} else if (obj == exCate12) {
			categoryFilter("출금", "기타");
		} else if (obj == inCate1) {
			categoryFilter("입금", "월급");
		} else if (obj == inCate2) {
			categoryFilter("입금", "부수입");
		} else if (obj == inCate3) {
			categoryFilter("입금", "용돈");
		} else if (obj == inCate4) {
			categoryFilter("입금", "상여");
		} else if (obj == inCate5) {
			categoryFilter("입금", "금융수익");
		} else if (obj == inCate6) {
			categoryFilter("입금", "기타");
		} else if (obj == incomeBtn) {
			setIncomeUI();
		} else if (obj == expensesBtn) {
			this.top1.remove(RD3);
			this.top1.add(RD4);
			this.top1.updateUI();
		}

	}// end actionPerformed

	private void setIncomeUI() {
		top1.remove(RD4);
		top1.add(RD3);
		top1.updateUI();
	}
	
	// 카테고리 열붙이기 및 총합 구하기 메소드
	void categoryFilter(String inOrOut, String category) {
		recordPage.model.setRowCount(0);
		int sum = 0;
		for (int i = 0; i < recordPage.data.length; i++) {
			String inOutStr = recordPage.data[i][2];
			String categoryName = recordPage.data[i][3];
			if (categoryName.equals(category)) {
				if (inOutStr.equals(inOrOut)) {
					recordPage.model.addRow(recordPage.data[i]);
					if (inOutStr.equals("입금")) {
						sum += Integer.parseInt(recordPage.data[i][4]);
					} else {
						sum -= Integer.parseInt(recordPage.data[i][4]);
					}
				}
			}
		}
		recordPage.totalTxt.setText(String.valueOf(sum) + " 원");
	}
}// end class
