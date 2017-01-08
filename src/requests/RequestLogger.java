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
		fw = new FileWriter(new File("requestLog.txt"));
		active = true;
	}//request log
	
	public void run() {
		while(active){
			try{
				Request request = q.take();
				System.out.println(request.toString());
				fw.write(request.toString()+"\n");
				if(request instanceof PoisonRequest){
					active = false;
				}
			}catch(Exception e){
				e.printStackTrace();
			}//try catch
		}//while
		
		try {
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}//run
}//request logger


