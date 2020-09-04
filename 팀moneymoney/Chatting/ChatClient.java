package moneymoney.Chatting;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JScrollBar;

import moneymoney.MoneyPage;
import moneymoney.PageInfo;
import moneymoney.UserInfo;
import moneymoney.DAO.MoneyDAO;
import moneymoney.DTO.AccountDTO;

public class ChatClient implements ActionListener, Runnable {
	
	MoneyPage moneyPage;
	
	private String host;
	private int port;
	private String userName;

	private DataInputStream dataIn;
	private DataOutputStream dataOut;
	Thread th;

	public ChatPage chatPage;

	UserInfo userInfo = UserInfo.getInstance();
	PageInfo pageInfo = PageInfo.getInstance();

	public ChatClient() {

	}

	public ChatClient(String host, int port, MoneyPage moneyPage) {
		this.moneyPage = moneyPage;
		this.host = host;
		this.port = port;
		UserInfo userInfo = UserInfo.getInstance();
		userName = userInfo.getUserId();

//		chatPage.addWindowListener(new WindowAdapter() {
//			@Override
//			public void windowClosing(WindowEvent e) {
//				stop();
//			}
//		});
		chatPage = new ChatPage();
		chatPage.inputText.addActionListener(this);
		initStart();
	}

	public void stop() {
		if (th != null) {
			th.interrupt();
			th = null;
			try {
				dataIn.close();
				dataOut.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		//chatPage.setVisible(false);
		//chatPage.dispose();
		//System.exit(0);
	}// end stop()/////////////////////////////////////

	@Override
	public void actionPerformed(ActionEvent e) {
		if (chatPage.inputText.getText().trim().equals("")) {
			return;
		}
		String message = userName + " : " + chatPage.inputText.getText();
		try {
			dataOut.writeUTF(message);
			dataOut.flush();
			chatPage.inputText.setText("");
			chatPage.inputText.requestFocus();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		// String message = userName + " : " + e.getActionCommand();
	}

	private void initStart() {
		Socket socket = null;
		try {
			socket = new Socket(host, port);
			InputStream is = socket.getInputStream();
			OutputStream os = socket.getOutputStream();
			dataIn = new DataInputStream(new BufferedInputStream(is));
			dataOut = new DataOutputStream(new BufferedOutputStream(os));
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 서버에서 보내준 메시지를 받기위한 스레드 생성
		th = new Thread(this);
		th.start();
	}

//	public static void main(String[] args) {
//		ChatClient client = new ChatClient(, );
//		client.initStart();
//		
//	}

	@Override
	public void run() {
		while (!Thread.interrupted()) {
			try {
				String message = dataIn.readUTF();
				String name = message.split(" : ")[0];
				String msg = message.split(" : ")[1];
				if (msg.equals("@계산기") || msg.equals("@현재잔액")) {
					chatFunction(msg, name);
					continue;
				}
				if (name.equals(userName)) {
					chatPage.addMyMessage(message);
				} else {
					if (pageInfo.getPageNum() != 5 && pageInfo.getAlarm() == 1) {
						Object[] options = {"채팅방 이동", "확인"};
						int result = JOptionPane.showOptionDialog(chatPage, message, "채팅 알람", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
						if(result == JOptionPane.YES_OPTION) {
							moneyPage.changeChatPage();
						} 
					}
					chatPage.addOtherMessage(message);
				}
				// taOutput.append(message + "\n");
			} catch (IOException e) {
				// taOutput.append("-----서버종료됨-----\n");
				if (th != null) {
					th.interrupt();
					th = null;
				}
				// e.printStackTrace();
			}
		}
	}

	public void chatFunction(String msg, String name) {

		if (msg.equals("@계산기")) {
			Runtime rt = Runtime.getRuntime();
			String exeFile = "C:\\Windows\\System32\\calc.exe";
			Process p;
			try {
				p = rt.exec(exeFile);
				p.waitFor();
			} catch (Exception err) {
				err.printStackTrace();
			}
		} else if (msg.equals("@현재잔액")) {
			MoneyDAO dao = MoneyDAO.getInstance();
			List<AccountDTO> aList = new ArrayList<AccountDTO>();
			aList = dao.accountSelectAll();
			int sum = 0;

			for (int i = 0; i < aList.size(); i++) {
				if (aList.get(i).getUserId().equals(userInfo.getUserId())) {
					sum += aList.get(i).getAccountBal();
				}
			}
			if (name.equals(userName)) {
				chatPage.addMyMessage(userName + " : " + sum + "원");
			}
			
		}
	}

}
