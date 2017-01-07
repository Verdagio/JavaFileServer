package client;

public class UI {
	
	boolean active;
	
	public UI(){
		this.active = true;
	}//default
	
	public void menu(){
		System.out.printf("\n\t[ 1 | Connect to file server ]\n\t[ 2 | Print file listing on server ]\n\t[ 3 | Download file ]\n\t[ 4 | Send a file to server(Currently not working) ]\n\t[ 5 | Quit ]\n");
		
	}//menu
	public void connecting(){
		System.out.println("Connecting to Server...");
	}//connecting
	
	public void fileList(){
		System.out.println("List of files...");
	}//list all files
	
	public void sendTo(){
		System.out.printf("Send file to the server...\nEnter file name:\n");
	}//send file to server
	
	public void getFrom(){
		System.out.println("Please choose a file from the list...");
	}
	
	public boolean isActive(){
		return active;
	}//active
	
	public void quit(){
		this.active = false;
	}//quit 
	
}//class
