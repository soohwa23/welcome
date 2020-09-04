package moneymoney.Record;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;

import moneymoney.MoneyPage;
import moneymoney.UserInfo;
import moneymoney.DAO.MoneyDAO;
import moneymoney.DTO.CategoriesDTO;
import moneymoney.DTO.TransactionDTO;
import moneymoney.DTO.UserDTO;

public class RecordPage extends JPanel implements ActionListener {

	public UserInfo userInfo = UserInfo.getInstance();
	// static
	public JLabel sumTxt, totalTxt;
	public JButton tongBtn, detailBtn;
	public JToggleButton weekBtn, monthBtn, semiAnnualBtn, emptyBtn, nowBtn;
	public JTable table;
	// static
	public DefaultTableModel model;
	// static
	public String[][] data;
	public String[] columnNames = { "날짜", "계좌", "입출", "분류", "금액", "내용" };
	public ButtonGroup periodSelectBtn;

	public RecordDetail recordDetail;
	public RecordChart recordChart;
	public MoneyPage moneyPage;
	public RecordPage(MoneyPage moneyPage) {

		this.moneyPage = moneyPage;
		// 프레임 생성
		Color tablegrey=new Color(120,120,120);
		Color white = new Color(255,255,255);
		sumTxt = new JLabel("합계 : ");
		totalTxt = new JLabel();
		Border border= new javax.swing.border.LineBorder(tablegrey,1);
		//JTableHeader h = new JTableHeader();
		
		//TitledBorder totalBorder = new TitledBorder("합계");
		//totalTxt.setBorder(totalBorder);

		tongBtn = new JButton("통계");
		weekBtn = new JToggleButton("1주일");
		monthBtn = new JToggleButton("1개월");
		semiAnnualBtn = new JToggleButton("6개월");
		emptyBtn = new JToggleButton();
		detailBtn = new JButton("상세조회");
		

		JPanel jp1 = new JPanel(null);
		jp1.add(sumTxt);

		jp1.add(totalTxt);
		jp1.add(tongBtn);

		// sumTxt.setBounds(50, 35, 100, 30);
		sumTxt.setBounds(45, 35, 100, 30);
		totalTxt.setBounds(100, 35, 120, 30);
		tongBtn.setBounds(350, 35, 100, 30);

		JPanel jp3 = new JPanel(null);
		jp3.add(weekBtn);
		jp3.add(monthBtn);
		jp3.add(semiAnnualBtn);
		jp3.add(detailBtn);
		weekBtn.setBounds(35, 5, 100, 30);// 위치설정 가로 위치, 세로 위치, 가로크기 ,세로 크기
		monthBtn.setBounds(140, 5, 100, 30);// 위치설정 가로 위치, 세로 위치, 가로크기 ,세로 크기
		semiAnnualBtn.setBounds(245, 5, 100, 30);// 위치설정 가로 위치, 세로 위치, 가로크기 ,세로 크기
		detailBtn.setBounds(350, 5, 100, 30);// 위치설정 가로 위치, 세로 위치, 가로크기 ,세로 크기

		periodSelectBtn = new ButtonGroup();
		periodSelectBtn.add(weekBtn);
		periodSelectBtn.add(monthBtn);
		periodSelectBtn.add(semiAnnualBtn);
		periodSelectBtn.add(emptyBtn);

		JPanel top = new JPanel(new GridLayout(2, 1));
		top.add(jp1);
		top.add(jp3);

		MoneyDAO dao = MoneyDAO.getInstance();
		UserDTO dto1 = new UserDTO();
		dto1.setUserId(userInfo.getUserId());
		List<TransactionDTO> aList = dao.transactionSelectAll(dto1);
		List<CategoriesDTO> categories = dao.categoriesSelectAll();

		data = new String[aList.size()][columnNames.length];

		int t_index = 0;
		// 테이블에 자료 붙여줌
		for (TransactionDTO dto : aList) {
			// data[t_index][0] = String.valueOf(dto.getTransactionNo());// int
			data[t_index][0] = dto.getUseDate().split(" ")[0];// date
			if (dto.getInOrOut() == '0') {
				data[t_index][2] = "입금";
			} else {
				data[t_index][2] = "출금";
			}
			data[t_index][1] = dto.getAccountNo();// string
			data[t_index][3] = categories.get(dto.getCategoryId() - 1).getCategoryName();
			// data[t_index][4] = String.valueOf(dto.getCategoryId());// string
			data[t_index][4] = String.valueOf(dto.getUseAmount());
			data[t_index][5] = dto.getUseContents();// string
			t_index++;

		}

		int sum = 0;
		for (int i = 0; i < data.length; i++) {
			if (data[i][2] == "입금") {
				sum += Integer.parseInt(data[i][4]);
				;
			} else {
				sum -= Integer.parseInt(data[i][4]);
				;
			}
		}
		totalTxt.setText(String.valueOf(sum) + " 원");

		model = new DefaultTableModel(data, columnNames);
		table = new JTable(model);
		JTableHeader header = table.getTableHeader();
		header.setReorderingAllowed(false);
		TableColumnModel columnModel = header.getColumnModel();

		table.setRowHeight(20);

		columnModel.getColumn(0).setPreferredWidth(60);
		columnModel.getColumn(1).setPreferredWidth(80);
		columnModel.getColumn(2).setPreferredWidth(15);
		columnModel.getColumn(3).setPreferredWidth(60);
		columnModel.getColumn(4).setPreferredWidth(50);
		columnModel.getColumn(5).setPreferredWidth(70);
		table.setColumnModel(columnModel);

		setLayout(null);

		table.setEnabled(false);
		
		
		JScrollPane scroll = new JScrollPane(table);
		
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
		DefaultTableCellRenderer dtcr1 = new DefaultTableCellRenderer();
		dtcr.setHorizontalAlignment(SwingConstants.CENTER);
		dtcr1.setHorizontalAlignment(SwingConstants.RIGHT);
		
		columnModel.getColumn(0).setCellRenderer(dtcr);
		columnModel.getColumn(1).setCellRenderer(dtcr);
		columnModel.getColumn(2).setCellRenderer(dtcr);
		columnModel.getColumn(3).setCellRenderer(dtcr);
		columnModel.getColumn(4).setCellRenderer(dtcr1);
		columnModel.getColumn(5).setCellRenderer(dtcr1);
		
		//tableCellCenter(JTable model);
		
		top.setBounds(0, 0, 500, 150);
		scroll.setBounds(0, 150, 485, 300);
		
		add(top);
		add(scroll);

		setSize(500, 450);
		setVisible(true);

		weekBtn.addActionListener(this);
		monthBtn.addActionListener(this);
		semiAnnualBtn.addActionListener(this);
		detailBtn.addActionListener(this);
		tongBtn.addActionListener(this);
	}

	public void setWholeData() {
		emptyBtn.setSelected(true);
		
		model.setRowCount(0);
		int sum = 0;
		for (int i = 0; i < data.length; i++) {
			model.addRow(data[i]);
			if (data[i][2] == "입금") {
				sum += Integer.parseInt(data[i][4]);
				;
			} else {
				sum -= Integer.parseInt(data[i][4]);
				;
			}
		}
		totalTxt.setText(String.valueOf(sum) + " 원");
	}
	
	public void refreshData() {
		MoneyDAO dao = MoneyDAO.getInstance();
		UserDTO dto1 = new UserDTO();
		dto1.setUserId(userInfo.getUserId());
		List<TransactionDTO> aList = dao.transactionSelectAll(dto1);
		List<CategoriesDTO> categories = dao.categoriesSelectAll();

		data = null;
		data = new String[aList.size()][columnNames.length];

		int t_index = 0;
		// 테이블에 자료 붙여줌
		for (TransactionDTO dto : aList) {
			// data[t_index][0] = String.valueOf(dto.getTransactionNo());// int
			data[t_index][0] = dto.getUseDate().split(" ")[0];// date
			if (dto.getInOrOut() == '0') {
				data[t_index][2] = "입금";
			} else {
				data[t_index][2] = "출금";
			}
			data[t_index][1] = dto.getAccountNo();// string
			data[t_index][3] = categories.get(dto.getCategoryId() - 1).getCategoryName();
			// data[t_index][4] = String.valueOf(dto.getCategoryId());// string
			data[t_index][4] = String.valueOf(dto.getUseAmount());
			data[t_index][5] = dto.getUseContents();// string
			t_index++;

		}
		this.setWholeData();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if (obj == weekBtn) {

			if (nowBtn == weekBtn) {
				emptyBtn.setSelected(true);
				nowBtn = emptyBtn;
				setWholeData();
			} else {
				dateFilter(7);
				nowBtn = weekBtn;
			}
		} else if (obj == monthBtn) {

			if (nowBtn == monthBtn) {
				emptyBtn.setSelected(true);
				nowBtn = emptyBtn;
				setWholeData();
			} else {
				dateFilter(30);
				nowBtn = monthBtn;
			}

		} else if (obj == semiAnnualBtn) {

			if (nowBtn == semiAnnualBtn) {
				emptyBtn.setSelected(true);
				nowBtn = emptyBtn;
				setWholeData();
			} else {
				dateFilter(180);
				nowBtn = semiAnnualBtn;
			}

		} else if (obj == detailBtn) {
			recordDetail = new RecordDetail(this);
			moneyPage.moneyDesign.setRecordDetailDesign();
			
		} else if (obj == tongBtn) {
			recordChart = new RecordChart(this);
			moneyPage.moneyDesign.setRecordChartDesign();
		}
	}// end actionPerformed

	// 마우스 클릭했을 때 액션
//	public void mouseClicked(MouseEvent e) { }

//날짜 기간으로 자료 조회 기능
	public void dateFilter(int day) {
		model.setRowCount(0);
		int sum = 0;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		// 현재시간
		Date nowDate = new Date();
		// 목표시간
		Calendar semi = Calendar.getInstance();
		semi.setTime(nowDate);
		semi.add(Calendar.DATE, -day);
		Date periodDate = semi.getTime();
		// 전체 data를 탐색한다.
		for (int i = 0; i < data.length; i++) {
			try {
				// string -> date 변환
				Date beforeDate = sdf.parse(data[i][0]);
				// 비교
				if (beforeDate.compareTo(periodDate) >= 0 && beforeDate.compareTo(nowDate) < 0) {
					model.addRow(data[i]);
					if (data[i][2].equals("입금")) {
						sum += Integer.parseInt(data[i][4]);
					} else {
						sum -= Integer.parseInt(data[i][4]);
					}
					
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		totalTxt.setText(String.valueOf(sum) + " 원");
	}

}
