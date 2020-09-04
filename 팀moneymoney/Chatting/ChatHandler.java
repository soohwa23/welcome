package moneymoney.Chatting;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Enumeration;
import java.util.Vector;

public class ChatHandler implements Runnable {
	private Socket socket;
	private DataInputStream dataIn;
	private DataOutputStream dataOut;
	private Thread th;
	private static Vector<ChatHandler> userVec = new Vector<ChatHandler>();
	
	public ChatHandler() {
		
	}

	public ChatHandler(Socket socket) {
		this.socket = socket;
	}
	
	public void initStart() {
		if(th==null) {
			try {
				InputStream is = socket.getInputStream();
				OutputStream os = socket.getOutputStream();
				dataIn = new DataInputStream(new BufferedInputStream(is));
				dataOut = new DataOutputStream(new BufferedOutputStream(os));
				th = new Thread(this);
				th.start();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public void run() {
		userVec.add(this);
		
		while(!Thread.interrupted()) {
			try {
				String message = dataIn.readUTF();
				broadcast(message);
			} catch (IOException e) {
				//e.printStackTrace();
				System.out.println("연결이 종료되었습니다.");
				stop();
				break;
			}
			
		}
	}
	
	public synchronized void broadcast(String message) {
		Enumeration<ChatHandler> enu = userVec.elements();
		while(enu.hasMoreElements()) {
			ChatHandler handler = enu.nextElement();
			try {
				handler.dataOut.writeUTF(message);
				handler.dataOut.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public synchronized void stop() {
		userVec.remove(this);
		if (th != null) {
			if (th == Thread.currentThread()) {
				th.interrupt();
				th = null;
				try {
					dataIn.close();
					dataOut.close();
				} catch (IOException e) {
					//e.printStackTrace();
				} finally {
					
				}
			}
		}
	}//end stop()///////////////////////////////////


}
