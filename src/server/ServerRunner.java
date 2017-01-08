package server;

import java.util.Scanner;

public class ServerRunner {
	public static void main(String[]args){
		Scanner in = new Scanner(System.in);
		String path;
		int port;
		try{
			
			port = 7000;
			System.out.println("enter file location: ");
			path = in.nextLine();
			
		}catch(Exception e){
			e.printStackTrace();
			return;
		}//try catch
		
		//start an instance of the server using the input port and path
		new Server(port, path);
	}//main
}//class
