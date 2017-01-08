package requests;

import java.io.*;
import java.net.*;
import java.util.concurrent.*;

public class RequestLogger implements Runnable{
	
	private BlockingQueue<Request> q;
	private FileWriter fw;
	private boolean active;
	
	public RequestLogger(BlockingQueue<Request> q) throws Exception{
		this.q = q;
		fw = new FileWriter(new File("log.txt"));
		active = true;
	}//request log
	
	@Override
	public void run() {
		while(active){
			try{
				Request request = q.take();
				System.out.println(request.toString());
				fw.write(request.toString()+"\n");
			}catch(Exception e){
				e.printStackTrace();
			}//try catch
		}//while
		try {
			fw.close();//free up resources when not running
		} catch (IOException e) {
			e.printStackTrace();
		}//try catch
	}//run
}//request logger


