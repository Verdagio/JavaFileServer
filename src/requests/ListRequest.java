package requests;

import java.io.File;

public class ListRequest extends Request{

	private static final long serialVersionUID = 1L;
	private String path;

	public ListRequest(String ip) {
		super(ip);
	}//default
	
	public void run(){
		
		File f = new File(path);
		File[] files = f.listFiles();
		super.streamSetup(files);
	}//run

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
	public String toString() {
		String message;
		message = "[ ListRequest | " +super.getClient() + " | " + super.getDate().toString() +" ]";
		return message;
	}//to string
	
}//class
