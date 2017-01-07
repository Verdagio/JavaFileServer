package client;

public class UI {
	
	boolean active;
	
	public UI(){
		this.active = true;
	}//default
	
	public void menu(){
		System.out.printf("\n1 - Connect to file server\n2 - Print file listing on server\n3 - Download file\n4- Quit\n");
		
	}//menu
	
	public boolean isActive(){
		return active;
	}//active
	
	public void quit(){
		this.active = false;
	}//quit 
	
}//class
