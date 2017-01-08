package requests;

import java.io.IOException;
import java.io.ObjectOutputStream;

public class ConnectionRequest extends Request {

	private static final long serialVersionUID = 1L;
	
	public ConnectionRequest(String ip){
		super(ip);
	}//constructor
	
	public void run(){

		super.streamSetup("Connected...");

	}//run

	public String toString() {
		String message;
		message = "[ ConnectionRequest | " +super.getClient() + " | " + super.getDate().toString() +" ]";
		return message;
	}//to string
	
}//class
