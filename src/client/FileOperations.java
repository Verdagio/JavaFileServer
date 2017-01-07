/* References: http://codereview.stackexchange.com/questions/20961/java-multi-thread-file-server-and-client
 * http://www.rgagnon.com/javadetails/java-0542.html
 * https://www.youtube.com/watch?v=OqPH0SSjjH0&t=94s
 * I used the above as a references for receiving & sending files and modified it as required to fit an OOP design..
 */

package client;

import java.io.*;
import java.net.*;
import java.util.*;

import client.config.*;
import requests.*;

public class FileOperations {
	
	private ObjectOutputStream outStr;
	private ObjectInputStream inStr;
	private	Socket sock;
	private String host;
	private int port;
	private String dir;
	private String ip;
	private File[] files;
	
	public FileOperations(Conf conf){
		this.host = conf.getHost();
		this.port = conf.getPort();
		this.dir = conf.getDir();
	}//constructor

	public void connect(){
		String res;
		
			try {			
				socketSetup();//see method below	
				streamSetup(new ConnectionRequest(ip));	//see method below
	
				res = (String) inStr.readObject();// De-serialize and cast to a string
				
				System.out.println(res);//print out the result
				
			} catch (Exception e) {
				// Use a general Exception, handling unknown and i/o exceptions
				e.printStackTrace();
			} //try / catch
	}//connect
	
	public void recieveFIle(){
		Scanner in = new Scanner(System.in);
		FileOutputStream stream;
		byte[] bytes;
		String fullPath;
		String choice;
		int i=-1;

		choice = in.next();
		
		for(File tmp : files){
			//for each file in the array, assign our temp file the current file from the array
			tmp = files[i];
			i++;
			if(tmp.getName().equals(choice)){
				
				try {
					socketSetup();//see method below					
					streamSetup(new ReceiveRequest(ip, choice));//see method below
					
					bytes = (byte[]) inStr.readObject();
					
					/*	output stream for writing data to a File or to a FileDescriptor.
					 * 	1	create a file output stream to write to the file represented by the File object.
					 * 	2	write b.length bytes from the byte array to this file output stream.
					 * 	3	close this input stream to release any system resources used by stream.
					 */
					
					fullPath = dir +"/"+choice;
					stream = new FileOutputStream(fullPath);		//	1
					stream.write(bytes);							//	2
					stream.close();									//	3
					System.out.printf("%s received from server...", choice);
					
				} catch (Exception e) { //handle multiple types of exceptions
					e.printStackTrace();
				}//try catch
			}//if the name matches 
		}//foreach file in file array
		
		//close the scanner, input / output streams to free resources being used by them
		in.close();
		try {
			inStr.close();
			outStr.close();
		} catch (IOException e) {
			System.out.println("unable to close input & output Streams");
		}//try / catch
	}//receive file from server	
	
	//currently not working needs extra work
	public void sendFile(String file){
		File theFile;
		byte[] bytes;
		FileInputStream fis;
		DataOutputStream data;
		
		try{
			/*		Handle the file
			 * 	1	Pass the specified file from method arguments
			 * 	2	Set the size of the byte array to the length of the file
			 * 	3	Read in the data from the file to the input stream
			 * 	4	De-serialize primitive data
			 * 	5	Blocks until all bytes in array are read.
			 */
			theFile = new File(file);					//	1
			bytes = new byte[(int)theFile.length()];	//	2
			fis = new FileInputStream(theFile);			//	3
			inStr = new ObjectInputStream(fis);			//	4
			inStr.readFully(bytes, 0, bytes.length);	//	5
			
			//Set up connection and send file to server
			socketSetup();
			
		}catch(Exception e){
			e.printStackTrace();
		}//try catch
		
	}//send file to server
	
	public void fileList(){
		int i=0;
		try{
			
			socketSetup();//see method below
			streamSetup(new ListRequest(ip));//see method below
			
			files = (File[]) inStr.readObject();
			
			for(File tmp : files){
				tmp = files[i];
				System.out.printf("%d\t:\t%s",i+1, tmp.getName());
				i++;
			}//for each file in files array print out its title
		}catch(Exception e){
			e.printStackTrace();
		}//try catch
		try {
			inStr.close();
			outStr.close();
		} catch (IOException e) {
			System.out.println("unable to close input & output Streams");
		}//try / catch
	}//list all files
	
	public void quit(){
		
		try{
			socketSetup();//see method below			
			streamSetup(new PoisonRequest(ip));	//see method below
			
			System.out.println("Poison Request Received...");
		}catch(Exception e){
			e.printStackTrace();
		}//try catch
	}//send a poison request
	
	//The following methods are only need to be accessed by this class therefore we make them private (Encapsulation)
	private void socketSetup() throws UnknownHostException, IOException{
		/*	1	create a connection to socket with host & port no.
		 * 	2	local address the socket is bound to
		 */
		sock = new Socket(host, port);								//	1
		ip = sock.getLocalAddress().getHostAddress();				//	2
		
		System.out.printf("\n\t***Connecting Client [ %s ] to %s on port: %d", ip, host, port);
	}
	
	private void streamSetup(Request r) throws IOException{	
		/* I made this method to shorten LOC in the file operation methods. Write it once and RE-USE
		 * 1 writes data types and graphs of objects to an OutputStream and return output stream for sock.
		 * 2 write the request object from method arguments to the ObjectOutputStream.
		 * 3 flush the stream
		 */
		outStr = new ObjectOutputStream(sock.getOutputStream());	//	1
		outStr.writeObject(r);										//	2
		outStr.flush();												//	3
		
		inStr = new ObjectInputStream(sock.getInputStream());
		
		System.out.println("Streams successfully setup...");
	}// stream setup method
}//file operations
