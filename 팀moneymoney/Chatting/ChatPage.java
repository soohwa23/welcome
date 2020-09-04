package moneymoney.Chatting;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class ChatPage extends JPanel {

	JTextField inputText;
	JScrollPane scroll;
	JPanel gridPanel;
	int nowY = 0;
	GridBagLayout grid = new GridBagLayout();
	int maxValue;
	
	public ChatPage() {
		// 생성
		gridPanel = new JPanel(grid);
		scroll = new JScrollPane(gridPanel);
		inputText = new JTextField("");

		// property
		gridPanel.setBorder(new TitledBorder("머니머니"));
		gridPanel.setSize(400, 400);
		gridPanel.setBackground(Color.white);

		scroll.setBorder(new TitledBorder("chat"));
		scroll.setBounds(5, 5, 475, 385);
		inputText.setBounds(5, 400, 475, 25);

		
		// 이벤트
		scroll.getVerticalScrollBar().addAdjustmentListener(new AdjustmentListener() {
			
			@Override
			public void adjustmentValueChanged(AdjustmentEvent e) {
				JScrollBar src = (JScrollBar) e.getSource();
				if (maxValue < src.getMaximum()) {
					maxValue = src.getMaximum();
					src.setValue(src.getMaximum());
				}
			}
		});

		add(scroll);
		add(inputText);
		setLayout(null);
		setSize(500, 470);
		setVisible(true);

	}

	public void addMyMessage(String message) {
		JLabel text = new JLabel();
		//JTextField text = new JTextField();
		String name = message.split(" : ")[0];
		String msg = message.split(" : ")[1];
		TitledBorder tBorder = new TitledBorder(name);
		text.setBorder(tBorder);
		text.setHorizontalAlignment(4);
		text.setText(msg);
		text.setEnabled(false);
		nowY++;
		create_form(text, 0, nowY, 1, 1);
	}

	public void addOtherMessage(String message) {
		JLabel text = new JLabel();
		String name = message.split(" : ")[0];
		String msg = message.split(" : ")[1];
		TitledBorder tBorder = new TitledBorder(name);
		text.setBorder(tBorder);
		text.setHorizontalAlignment(2);
		text.setText(msg);
		nowY++;
		create_form(text, 0, nowY, 1, 1);
	}

	public void create_form(Component cmpt, int x, int y, int w, int h) {
		cmpt.setFont(new Font("굴림", 1, 16));
		cmpt.setForeground(Color.black);
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridx = x + 1;
		gbc.gridy = y;
		gbc.gridwidth = w;
		gbc.gridheight = h;
		gbc.weightx = 0.1;
		this.grid.setConstraints(cmpt, gbc);
		gridPanel.add(cmpt);
		gridPanel.updateUI();
	}

}
