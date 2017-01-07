package server;

public class ServerRunner {
	public static void main(String[]args){
		String path;
		int port;
		
		port = Integer.parseInt(args[0]);
		path = args[1];
		
		//start an instance of the server using the input port and path
		new Server(port, path);
	}//main
}//class
