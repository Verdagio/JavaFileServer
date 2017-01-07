package requests;

import java.io.*;
import java.net.*;
import java.util.concurrent.*;

public class RequestLogger  implements Runnable{
	
	private BlockingQueue q;
	private FileWriter fw;
	
	public RequestLogger(BlockingQueue q){
		this.q = q;
		try {
			fw = new FileWriter(new File("log.txt"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//try catch
	}//req loger
	
	@Override
	public void run() {
		
		
		
	}//run
}//request logger


