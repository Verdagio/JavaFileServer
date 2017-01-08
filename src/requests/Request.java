package requests;

import java.io.File;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.*;
import java.util.*;

public class Request implements Serializable, Runnable{
	private static final long serialVersionUID = 1L;
	private Socket socket;
	private String client;
	private String host;
	private Date date;
	private int port;
	
	public Request(){}//default constructor
	
	public Request(String client) {
		date = new Date();
		this.client = client;
	}//request

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}
	
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
	}

	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket s) {
		this.socket = s;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public void run(){}//Move along folks nothing to see here. . .
	
	public void streamSetup(String str){
		ObjectOutputStream outStr;
		try {
			outStr = new ObjectOutputStream(socket.getOutputStream());
			outStr.writeObject(str);
			outStr.flush();
			outStr.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//try catch
	}// stream setup
	
	public void streamSetup(File[] f){
		ObjectOutputStream outStr;
		try {
			outStr = new ObjectOutputStream(socket.getOutputStream());
			outStr.writeObject(f);
			outStr.flush();
			outStr.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//try catch
	}// stream setup overloaded
	
	public void streamSetup(byte[] b){
		ObjectOutputStream outStr;
		try {
			outStr = new ObjectOutputStream(socket.getOutputStream());
			outStr.writeObject(b);
			outStr.flush();
			outStr.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//try catch
	}// stream setup overloaded
	
}