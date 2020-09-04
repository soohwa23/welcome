package moneymoney.Record;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class RecordChart extends JFrame implements ActionListener {

	public Container contentPane;
	RecordPage recordPage;
	// Container contentPane; // 컨테이너 생성
	int[] arcAngle;
	ChartPanel chartPanel = new ChartPanel();

	Color[] color = { new Color(214, 48, 49), new Color(255, 118, 117), // 색상
			new Color(9, 132, 227), new Color(116, 185, 255), new Color(253, 121, 168), 
			new Color(85, 239, 196), new Color(0, 184, 148), new Color(250, 177, 160), 
			new Color(253, 203, 110), new Color(108, 92, 231), new Color(232, 67 ,147), 
			new Color(178, 190, 195)};

	JPanel chartBtnPn;
	public JButton incomeChtBtn, expenseChtBtn;
	String[] categoryName;

	public RecordChart(RecordPage recordPage) {
		
		this.recordPage = recordPage;
		contentPane = getContentPane();
		incomeChtBtn = new JButton("입금");
		expenseChtBtn = new JButton("출금");
		chartBtnPn = new JPanel();
		
		chartBtnPn.setLayout(new GridLayout(1,2));
		chartBtnPn.setPreferredSize(new Dimension(550, 70));
		chartBtnPn.add(incomeChtBtn);
		chartBtnPn.add(expenseChtBtn);
		
		contentPane.add(chartPanel, BorderLayout.CENTER);
		contentPane.add(chartBtnPn, BorderLayout.SOUTH);
		
		setTitle("통계 차트");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		// contentPane = getContentPane(); // 컨테이너 갯
		setSize(550, 550);
		setVisible(true);
		setLocationRelativeTo(recordPage);

		incomeChtBtn.addActionListener(this);
		expenseChtBtn.addActionListener(this);

	} // end RecordChart()

	void drawChartExpense() { // 차트를 그린다
		
		// 현재는 데이터테이블대로 categoryname을 categorySum에 더해주고 있음
		// category별로 categorySum 더해주기
		
		int sum = 0; // 초기값 0
		for (int i = 0; i < recordPage.data.length; i++) { // 데이터 값만큼 루프
			if (recordPage.data[i][2].equals("출금")) {
				sum += Integer.parseInt(recordPage.data[i][4]);
			}
		}
		if (sum == 0)
			return;
		
		int[] categorySum = new int[12];
		String[] categoryName = {"식비", "교통/차량", "문화생활", "마트/편의점", "패션/미용", "생활용품", "주거/통신", "건강",
				"교육", "경조사/회비", "부모님", "기타"};
		this.categoryName = categoryName;
		arcAngle = new int[categoryName.length];

		for (int i = 0; i < recordPage.data.length; i++) {
			if (recordPage.data[i][2].equals("출금")) {
				switch (recordPage.data[i][3]) {
				case "식비": categorySum[0] += Integer.parseInt(recordPage.data[i][4]); break;
				case "교통/차량": categorySum[1] += Integer.parseInt(recordPage.data[i][4]); break;
				case "문화생활": categorySum[2] += Integer.parseInt(recordPage.data[i][4]); break;
				case "마트/편의점": categorySum[3] += Integer.parseInt(recordPage.data[i][4]); break;
				case "패션/미용": categorySum[4] += Integer.parseInt(recordPage.data[i][4]); break;
				case "생활용품": categorySum[5] += Integer.parseInt(recordPage.data[i][4]); break;
				case "주거/통신": categorySum[6] += Integer.parseInt(recordPage.data[i][4]); break;
				case "건강": categorySum[7] += Integer.parseInt(recordPage.data[i][4]); break;
				case "교육": categorySum[8] += Integer.parseInt(recordPage.data[i][4]); break;
				case "경조사/회비": categorySum[9] += Integer.parseInt(recordPage.data[i][4]); break;
				case "부모님": categorySum[10] += Integer.parseInt(recordPage.data[i][4]); break;
				case "기타": categorySum[11] += Integer.parseInt(recordPage.data[i][4]); break;

				default: break;
				}
			}
		}

		for (int i = 0; i < categorySum.length; i++) {
			arcAngle[i] = (int) Math.round(categorySum[i] / (double) sum * 360);
			chartPanel.repaint(); // 차트패널의 PAINT호출
		}
	}

	void drawChartIncome() { // 차트를 그린다
		
		int sum = 0; // 초기값 0
		for (int i = 0; i < recordPage.data.length; i++) { // 데이터 값만큼 루프
			if (recordPage.data[i][2].equals("입금")) {
				sum += Integer.parseInt(recordPage.data[i][4]);
			}
		}
		if (sum == 0)
			return;

		int[] categorySum = new int[6];
		String[] categoryName = {"월급", "부수입", "용돈", "상여", "금융수익", "기타"};
		this.categoryName = categoryName;
		arcAngle = new int[categoryName.length];

		for (int i = 0; i < recordPage.data.length; i++) {
			if (recordPage.data[i][2].equals("입금")) {
				switch (recordPage.data[i][3]) {
				case "월급": categorySum[0] += Integer.parseInt(recordPage.data[i][4]); break;
				case "부수입": categorySum[1] += Integer.parseInt(recordPage.data[i][4]); break;
				case "용돈": categorySum[2] += Integer.parseInt(recordPage.data[i][4]); break;
				case "상여": categorySum[3] += Integer.parseInt(recordPage.data[i][4]); break;
				case "금융수익": categorySum[4] += Integer.parseInt(recordPage.data[i][4]); break;
				case "기타": categorySum[5] += Integer.parseInt(recordPage.data[i][4]); break;

				default: break;
				}
			}
		}
		
		for (int i = 0; i < categorySum.length; i++) {
			arcAngle[i] = (int) Math.round(categorySum[i] / (double) sum * 360);
			chartPanel.repaint(); // 차트패널의 PAINT호출
		}
}

	class ChartPanel extends JPanel { // 차트 표시 패널

		public void paintComponent(Graphics g) {

			super.paintComponent(g);// 부모 패인트호출

			int startAngle = 0;
			int gap = 0;
			if(categoryName != null) {
				for (int i = 0; i < categoryName.length; i++) {
					gap += 30;
					g.setFont(new Font("Noto Sans KR Medium", Font.PLAIN, 18)); 
					g.setColor(color[i]);
					g.drawString(categoryName[i] + " " + Math.round(arcAngle[i] * 100 / 360) + "%", 50, 30 + gap);
				}
				for (int i = 0; i < categoryName.length; i++) {
					g.setColor(color[i]);
					g.fillArc(230, 70, 250, 250, startAngle, arcAngle[i]);
					startAngle = startAngle + arcAngle[i];
				}
			}
			
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		Object obj = e.getSource();

		if (obj == incomeChtBtn) {
			drawChartIncome();
		} else if (obj == expenseChtBtn) {
			drawChartExpense();
		}
	}
}