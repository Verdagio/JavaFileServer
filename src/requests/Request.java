package requests;

import java.io.Serializable;
import java.net.*;
import java.util.*;

public class Request implements Serializable, Runnable{
	private static final long serialVersionUID = 1L;
	private Socket socket;
	private String client;
	private String command;
	private String host;
	private Date date;
	private int port;
	
	public Request(){}//default constructor
	
	public Request(String client) {
		date = new Date();
		this.client = client;
	}//request

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

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

	@Override
	public void run() {
		//no changes needed...
	}
	
	
}