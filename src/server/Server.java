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
	BlockingQueue<Request> q = new ArrayBlockingQueue<>(10);
	
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
			System.out.printf("\n\t\t\t***Server active on port no: %d***", port);
			
			//start the request logger on its own consumer thread 
			new Thread(new RequestLogger(q)).start();
		} catch (IOException e) {
			e.printStackTrace();
		}//try catch
	}//server constructor

/*	Inner class event listener: We use an inner class as we need access to the private server socket,
 * 	The event listener listens for incoming client requests 
 *	EventListener plays the role / borrows functionality of a thread but is not one (extends Thread)
 */
	class EventListener implements Runnable{
		
		//this will control our while loop, we make it volatile because we don't want a cached value, we want the actual
		private volatile boolean running = true;
		private ObjectInputStream ois;
		Socket s;
		Request r;
			
		@Override
		public void run() {
			
			int i = 0;
			
			while(running){
				
				try {
					//Threads will wait here for an incoming request
					s = ss.accept();
					
					//An ObjectInputStream deserializes primitive data and objects previously written using an ObjectOutputStream.
					ois = new ObjectInputStream(s.getInputStream());
					r = (Request) ois.readObject(); //cast it to a REquest object and assign
					
					//check what kind of request is made
					if(r instanceof PoisonRequest){
						//we want to stop the server if this comes in...
						
					}else if(r instanceof ListRequest){
						//handle list requests...
						
					}//if else if
					
					//run on independent thread
					r.setSocket(s);
					new Thread(r).start();
					
					q.put(r);// add request to the blocking queue
					
					new Thread(new RequestLogger(q)).start();
					
					i++;
				} catch (Exception e) {
					// Use a general Exception, we need IO & Class not found Exception handling
					e.printStackTrace();
				}// try catch

				
			}//while
		}//run
	}//inner class
}//class