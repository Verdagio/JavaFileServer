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
			sock = new Socket(host, port);//create a connection to socket with 
			
			ip = sock.getLocalAddress().getHostAddress();
			
			outStr = new ObjectOutputStream(sock.getOutputStream());
			outStr.writeObject(new ConnectionRequest(ip));//write the request object to the ObjectOutputStream.
			outStr.flush();//flush the stream.
			
			inStr = new ObjectInputStream(sock.getInputStream());
			
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
		int i=0;

		System.out.printf("\nPlease choose a file from the list\n");
		choice = in.next();
		
		for(File tmp : files){
			tmp = files[i];//assign our temp file the current file from the array
			if(tmp.getName().equals(choice)){
				
				try {
					sock = new Socket(host, port);
					ip = sock.getLocalAddress().getHostAddress();
					
					outStr = new ObjectOutputStream(sock.getOutputStream());
					outStr.writeObject(new ReceiveRequest(ip, choice));
					outStr.flush();
					
					inStr = new ObjectInputStream(sock.getInputStream());
					bytes = (byte[]) inStr.readObject();
					
					//output stream for writing data to a File or to a FileDescriptor.
					fullPath = dir +"/"+choice;
					stream = new FileOutputStream(fullPath);//create a file output stream to write to the file represented by the File object.
					stream.write(bytes);//write b.length bytes from the byte array to this file output stream.
					
					stream.close();//close this input stream and releases any system resources used by stream.
				} catch (Exception e) { //handle multiple types of exceptions
					e.printStackTrace();
				}//try catch
			}//if the name matches 
		}//foreach file in file array
		in.close();//close the scanner free resources being used by it
	}//receive file from server	

	public void fileList(){
		
	}//list all files
	
	public void quit(){
		
	}//send a poison request
	
}//file ops.
