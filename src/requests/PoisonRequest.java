package requests;

public class PoisonRequest extends Request{

	private static final long serialVersionUID = 1L;

	public PoisonRequest(String ip) {
		super(ip);
	}//default
	
	@Override
	public void run(){
		
	}//run
	
	@Override
	public String toString() {
		String message;
		message = "[ PoisonRequest | " +super.getClient() + " | " + super.getDate().toString() +" ]";
		return message;
	}//to string
	
}//class
