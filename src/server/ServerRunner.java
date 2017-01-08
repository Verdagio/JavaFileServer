package server;

public class ServerRunner {
	public static void main(String[]args){
		String path;
		int port;
		System.out.println("1");
		try{
			port = Integer.parseInt(args[0]);
			System.out.println("2");
			path = args[1];
			System.out.println("3");
		}catch(Exception e){
			e.printStackTrace();
			return;
		}//try catch
		
		//start an instance of the server using the input port and path
		System.out.println("4");
		new Server(port, path);
	}//main
}//class
