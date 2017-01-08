package requests;

import java.util.concurrent.BlockingQueue;

public class PoisonRequest extends Request{

	private static final long serialVersionUID = 1L;
	
	private BlockingQueue<Request> q;

	public PoisonRequest(String ip) {
		super(ip);
	}//default
	
	public void run(){

		super.streamSetup("Server shutdown..");
		
		try {
			q.put(new PoisonRequest(super.getClient()));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}//run
	
	public String toString() {
		String message;
		message = "[ PoisonRequest | " +super.getClient() + " | " + super.getDate().toString() +" ]";
		return message;
	}//to string
	
}//class
