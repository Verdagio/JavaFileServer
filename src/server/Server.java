/* Reference: http://docs.oracle.com/javase/tutorial/networking/sockets/clientServer.html
 * https://learnonline.gmit.ie/mod/resource/view.php?id=65284
 * I used the above as a references when building the server side
 */

package server;

import java.util.concurrent.*;
import requests.*;
import java.io.*;
import java.net.*;

public class Server{
	
	//We will use a server socket to listen on a port for all incoming requests
	private ServerSocket ss;
	private int port;
	private String path;
	
	
	
	//the blocking queue of incoming requests, 10 max
	private BlockingQueue<Request> q = new ArrayBlockingQueue<>(10);
	
	public Server(int port, String path){
		this.port = port;
		this.path = path;
		
		try {
			//start the server socket listening on the port
			ss = new ServerSocket(port);
			
			/*	Create thread that will listen for events
			 * 	set it to max priority for the scheduler and start it 
			 */
			Thread server = new Thread(new EventListener());
			server.setPriority(Thread.MAX_PRIORITY);
			server.start();
			System.out.printf("\n\n\t***Server active on port no: %d***\n\n", port);
			
			//start the request logger on its own consumer thread 
			new Thread(new RequestLogger(q)).start();
		} catch (Exception e) {
			e.printStackTrace();
		}//try catch
	}//server constructor

/*	Inner class event listener: We use an inner class as we need access to the private server socket,
 * 	The event listener listens for incoming client requests 
 *	EventListener plays the role / borrows functionality of a thread but is not one (extends Thread)
 */
	private class EventListener implements Runnable{
		
		//this will control our while loop, we make it volatile because we don't want a cached value, we want the actual
		private volatile boolean running = true;
		private ObjectInputStream outStr;
		private Socket s;
		private Request r;
			
		public void run() {
			
			int i = 0;
			
			while(running){
				
				try {
					//Threads will wait here for an incoming request
					s = ss.accept();
					
					//	1	An ObjectInputStream deserializes primitive data and objects previously written using an ObjectOutputStream.
					//	2	cast it to a Request object and assign
					outStr = new ObjectInputStream(s.getInputStream());	//	1
					r = (Request) outStr.readObject(); 					//	2
					
					//check what kind of request is made
					if(r instanceof PoisonRequest){
						//we want to stop the server if this comes in...
						setRunning(false);
					}else if(r instanceof ListRequest){
						//handle list requests... set path of file directory
						((ListRequest) r).setPath(path);
					}else if(r instanceof ReceiveRequest){
						//handle receive requests... set path of file directory
						((ReceiveRequest) r).setPath(path);
					}//if else if
					
					//job runs on independent thread
					r.setSocket(s);
					new Thread(r, "r"+i).start();
					
					// put request into the blocking queue
					q.put(r);
					
					new Thread(new RequestLogger(q)).start();
					
					i++;
				} catch (Exception e) {
					// Use a general Exception, we need IO & Class not found Exception handling
					e.printStackTrace();
				}// try catch
				
			}//while
		}//run

		public boolean isRunning() {
			return running;
		}

		public void setRunning(boolean running) {
			this.running = running;
		}
	}//inner class
}//class